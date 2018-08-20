package com.maomao.ssm.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @name: PageController
 * @description:页面跳转Controller
 * @author: wzy
 *
 */
@Controller
public class PageController {
	@RequestMapping("/admin")
	public String showIndex() {
		return "index";
	}
	@RequestMapping("/admin/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}























