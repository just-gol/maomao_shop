package com.maomao.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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

public class LoginIntercept implements HandlerInterceptor{
	@Autowired
	private JedisClientPool jedisClientPool;
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		boolean flag = false;
		
		String cookie = CookieUtils.getCookieValue(request, "maomao.cookie", true);
		if (StringUtils.isNotBlank(cookie)) {
			String userId = jedisClientPool.get(RedisConst.LOGIN_SUCCESS+cookie);
			if (StringUtils.isNotBlank(userId)) {
				String redisCookie = jedisClientPool.get(userId);
				if (StringUtils.isNotBlank(redisCookie) && redisCookie.equals(RedisConst.LOGIN_SUCCESS+cookie)) {
					return true;
				}else {
					flag = true;
				}
				
			}
			
		}
		JsonData jsonData = null;
		if (flag) {
			jsonData = JsonData.setLogoutData("您的账号在另一台设备登录,请重新登录!");
		}else {
			jsonData = JsonData.setLogoutData("登录已过期");
			
		}
		String errorInfo = JsonUtils.objectToJson(jsonData);
		response.getWriter().write(errorInfo);
		return false;	
	}


	
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}





















