package com.maomao.ssm.controller.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.v2.ShopServiceV2;

/**
 * @author:wzy
 * @descrption:店铺
 * @version:
 * @date:2018年7月11日
 */
@Controller
@RequestMapping(value = "/shop/v2", method = RequestMethod.POST)
public class ShopControllerV2 {

	@Autowired
	private ShopServiceV2 shopService;

	/**
	 * 我的店铺 author:wzy
	 */
	@RequestMapping(value = "/shop.action")
	@ResponseBody
	public JsonData getShopDetail(Integer userId) {
		return shopService.myShop(userId);
	}

	@RequestMapping(value = "/showAll.action")
	@ResponseBody
	public JsonData showAll(Integer type, Integer userId) {
		return shopService.showAll(type, userId);
	}

	/**
	 * 商品分享记录
	 */
	@RequestMapping(value = "/record.action")
	@ResponseBody
	public JsonData share(Integer userId, Integer category, Integer goodsId) {
		return shopService.shareRecord(userId, category, goodsId);
	}

	/**
	 * 我的合卖
	 */
	@RequestMapping(value = "/hemai.action")
	@ResponseBody
	public JsonData hemai(Integer userId) {
		return shopService.hemai(userId);
	}
}
