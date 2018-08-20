package com.maomao.ssm.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.LoginService;
import com.maomao.ssm.wechat.AccessToken;
import com.maomao.ssm.wechat.WechatUtils;

/**
 * @descrption:用户登录/注册处理Controller
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private WechatUtils wechatUtils;

	@RequestMapping(value = "/login.action") // TODO ,method=RequestMethod.POST
	@ResponseBody
	public JsonData login(String mobile, String password, HttpServletRequest request, HttpServletResponse response) {
		JsonData result = loginService.login(mobile, password);
		return result;
	}

	@RequestMapping(value = "/register.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData register(String mobile, String password, String invitation, String randomCode) {
		return loginService.register(mobile, randomCode, password, invitation);
	}

	@RequestMapping(value = "/getSmsCode.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getSmsCode(String mobile, Integer type) {
		return loginService.getSmsCode(mobile, type);
	}

	@RequestMapping(value = "/getRandomCode.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getRandomCode(String mobile, String code) {
		return loginService.getRandomCode(mobile, code);
	}

	@RequestMapping(value = "/logout.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData logout(Integer userId) {
		JsonData result = loginService.logout(userId);
		return result;
	}

	@RequestMapping(value = "/updatePassword.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData updatePassword(String mobile, String password, String randomCode) {
		JsonData result = loginService.updatePassword(mobile, password, randomCode);
		return result;
	}

	@RequestMapping(value = "/wechatLogin.action")
	@ResponseBody
	public void wechatLogin(String state, HttpServletResponse response) throws Exception {
		String result = wechatUtils.getCodeUrl(URLEncoder.encode(state, "UTF-8"));
		response.sendRedirect(result);
	}

	@RequestMapping(value = "/wechatRedirect.action")
	public void wechatRedirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		AccessToken accessToken = wechatUtils.getAccessToken(code);
		String openId = accessToken.getOpenid();
		Cookie cookie = new Cookie("openId", openId);
		cookie.setPath("/");
		response.addCookie(cookie);

		response.sendRedirect(URLDecoder.decode(state, "UTF-8"));
	}

}
