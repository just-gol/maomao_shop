package com.maomao.ssm.exception.resolver;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.maomao.ssm.exception.DataException;
import com.maomao.ssm.exception.ServerException;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.utils.JsonUtils;

public class AppExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
		/* 使用response返回 */
		response.setStatus(HttpStatus.OK.value()); // 设置状态码
		response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
		response.setCharacterEncoding("UTF-8"); // 避免乱码
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		String result = JsonUtils.objectToJson(JsonData.setErrorMessage("未知错误"));
		if (ex instanceof DataException) {
			result = JsonUtils.objectToJson(JsonData.setErrorMessage(((DataException) ex).getMessage()));
		}
		if (ex instanceof ServerException) {
			result = JsonUtils.objectToJson(JsonData.setServerErrorMessage(((ServerException) ex).getMessage()));
		}
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mv;
	}

}
