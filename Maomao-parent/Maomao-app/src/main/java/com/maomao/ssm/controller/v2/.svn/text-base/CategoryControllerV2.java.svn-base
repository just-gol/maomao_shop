package com.maomao.ssm.controller.v2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.v2.CategoryServiceV2;

/** 
* @author:wzy
* @descrption:
* @version:
* @date:2018年7月9日
*/
@Controller
@RequestMapping(value="/category/v2",method=RequestMethod.POST)
public class CategoryControllerV2 {
	
	@Autowired
	private CategoryServiceV2 categoryServiceV2;

	// 分类详情
	@RequestMapping("/category.action")
	@ResponseBody
	public JsonData getCategoryDetail(HttpServletRequest request) {
		String ua = request.getHeader("user-agent");
		return categoryServiceV2.getCategoyrDetail(ua);
	}
	
	
	/**
	 * 根据分类id查询商品
	 */
	@RequestMapping(value = "categoryGoods.action")
	@ResponseBody
	public JsonData getGoodsByCateogoryId(Integer categoryId,
			@RequestParam(defaultValue = "1") Integer pages, @RequestParam(defaultValue = "10") Integer rows,
			Integer sortType, String brand, Long startPrice, Long endPrice) {
		return categoryServiceV2.getCategoryDetailById(categoryId, pages, rows, sortType, brand, startPrice, endPrice);
	}
}



























