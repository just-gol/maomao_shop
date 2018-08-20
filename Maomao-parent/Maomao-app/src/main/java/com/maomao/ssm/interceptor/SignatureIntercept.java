package com.maomao.ssm.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.utils.JsonUtils;
import com.maomao.ssm.utils.Signature;
/**
 * @author: wzy
 * @Description:
 */
public class SignatureIntercept implements HandlerInterceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SignatureIntercept.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LOGGER.info("uri={}",request.getRequestURI());
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		Map<String, String[]> paramsMap = request.getParameterMap();		
		if (!paramsMap.containsKey(Signature.SIGNATURE_NAME)) {
			JsonData json = JsonData.setErrorMessage("签名错误");
			String result = JsonUtils.objectToJson(json);
			response.getWriter().write(result);
			return false;
		}
		
		Set<String> keySet = paramsMap.keySet();
		List<String> params = new ArrayList<String>();
		String signature = null;
		for (String key : keySet) {
			if (Signature.SIGNATURE_NAME.equals(key)) {
				signature = paramsMap.get(key)[0];
				continue;
			}
			params.add(key + "=" + paramsMap.get(key)[0]);
		}
		
		if (!Signature.validateSignatrue(params, signature)) {
			JsonData json = JsonData.setErrorMessage("签名错误");
			String result = JsonUtils.objectToJson(json);
			response.getWriter().write(result);
			return false;
		}	
		return true;

	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

}



















