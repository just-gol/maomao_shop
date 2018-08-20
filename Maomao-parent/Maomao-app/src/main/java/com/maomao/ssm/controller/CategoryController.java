package com.maomao.ssm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.CategoryService;

/** 
* @author:wzy
* @descrption: 商品分类Controllern
* @version:
*/
@Controller
@RequestMapping(value="/category",method=RequestMethod.POST)
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 获取分类详情
	 */
	@RequestMapping("/category.action")
	@ResponseBody
	public JsonData getCategoryDetail (HttpServletRequest request) {
		String ua = request.getHeader("user-agent");
		return categoryService.getCategoryDetail(ua);
	}

}


























