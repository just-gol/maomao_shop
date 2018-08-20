package com.maomao.ssm.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.UserShopService;

@Controller
@RequestMapping("/userShop")
public class UserShopController {
	@Autowired
	private UserShopService userShopService;

	@RequestMapping(value = "/getUserShopList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getUserShopList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, String queryString) {
		return userShopService.getUserShopList(pages, rows, queryString);
	}

	@RequestMapping(value = "/getUserShopDetail.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getUserShopDetail(Integer userId) {
		return userShopService.getUserShopDetail(userId);
	}

	@RequestMapping(value = "/getUserShopDetailOrderList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getUserShopDetailOrderList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, Integer userId) {
		return userShopService.getUserShopDetailOrderList(pages, rows, userId);
	}

	@RequestMapping(value = "/updateGoodsOverstockUserByRefund.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateGoodsOverstockUserByRefund(Integer id) {
		return userShopService.updateGoodsOverstockUserByRefund(id);
	}

}
