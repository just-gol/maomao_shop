package com.maomao.ssm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.HomeService;
import com.maomao.ssm.utils.UserAgentUtils;

/**
 * @author:wzy
 * @descrption:首页内容Controller
 * @version:
 */
@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private HomeService homService;

	@RequestMapping("/home.action")
	@ResponseBody
	public JsonData getHome(HttpServletRequest request) {
		String ua = request.getHeader("user-agent");
		return homService.getHomeDetails(UserAgentUtils.getPhoneType(ua));
	}

	@RequestMapping("/ad.action")
	@ResponseBody
	public JsonData getAd(Integer id, String modifiedTime, HttpServletRequest request) {
		String ua = request.getHeader("user-agent");
		return homService.getAd(id, modifiedTime, UserAgentUtils.getPhoneType(ua));
	}
}
