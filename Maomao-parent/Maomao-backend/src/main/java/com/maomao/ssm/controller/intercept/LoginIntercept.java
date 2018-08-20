package com.maomao.ssm.controller.intercept;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.maomao.ssm.constant.AdminConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.utils.CookieUtils;
import com.maomao.ssm.utils.JsonUtils;

/**
 * @author:wzy
 * @descrption:登录拦截器
 * @version:
 */

public class LoginIntercept implements HandlerInterceptor {
	@Autowired
	private JedisClientPool jedisClientPool;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Map<String, String[]> paramsMap = request.getParameterMap();
		// Set<String> keySet = paramsMap.keySet();
		// String params = "";
		// String signature = null;
		// for (String key : keySet) {
		// params += key + "=" + paramsMap.get(key)[0];
		// }
		// System.out.println(params);

		JsonData result = null;
		String cookie = CookieUtils.getCookieValue(request, "token", true);
		// System.out.println("cookie:"+cookie);
		if (StringUtils.isNotBlank(cookie)) {
			String userId = jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS, cookie);
			// System.out.println("userId:"+userId);
			if (StringUtils.isNotBlank(userId)) {
				String redisCookie = jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS + userId, "token");
				// System.out.println("redisCookie:"+redisCookie);
				if (StringUtils.isNotBlank(redisCookie) && redisCookie.equals(cookie)) {
					String userInfo = jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS + userId, "userInfo");
					result = JsonData.setSuccessMessage(JsonUtils.jsonToPojo(userInfo, Map.class));
					return true;
				}
			}

		}
		result = JsonData.setLogoutData("登录失效");
		response.getWriter().write(JsonUtils.objectToJson(result));
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
