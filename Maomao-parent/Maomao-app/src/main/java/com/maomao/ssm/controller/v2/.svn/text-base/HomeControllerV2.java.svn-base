package com.maomao.ssm.controller.v2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.v2.HomeServiceV2;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年7月11日
 */
@Controller
@RequestMapping(value = "/home/v2", method = RequestMethod.POST)
public class HomeControllerV2 {
	@Autowired
	private HomeServiceV2 homeServiceV2;

	/**
	 * 我的店铺 author:wzy
	 */
	@RequestMapping("/home.action")
	@ResponseBody
	public JsonData getShopDetail(Integer userId, HttpServletRequest request) {
		String ua = request.getHeader("user-agent");
		return homeServiceV2.homeDetail(userId, ua);
	}

	/**
	 * 爆款推荐
	 */
	@RequestMapping("/baokuan.action")
	@ResponseBody
	public JsonData baokuan(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows) {
		return homeServiceV2.baoKuan(pages, rows);
	}

	/**
	 * 根据首页分类id查询内容
	 */
	@RequestMapping("/homecategory.action")
	@ResponseBody
	public JsonData homecategory(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, Integer categoryId,Integer sortType) {
		return homeServiceV2.homeCategoryDetail(pages, rows, categoryId,sortType);
	}

	/**
	 * 商品分享
	 */
	@RequestMapping("/share.action")
	@ResponseBody
	public JsonData share(Integer goodsId, Integer category, Integer userId) {
		return homeServiceV2.share(goodsId, category, userId);
	}
}
