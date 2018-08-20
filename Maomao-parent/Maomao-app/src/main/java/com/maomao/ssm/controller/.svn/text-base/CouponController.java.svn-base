package com.maomao.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.CouponService;

@Controller
@RequestMapping(value = "/coupon", method = RequestMethod.POST)
public class CouponController {
	@Autowired
	private CouponService couponService;

	/**
	 * 获取首页未领取优惠券
	 */
	@RequestMapping("/getUngetCouponList.action")
	@ResponseBody
	public JsonData getUngetCouponList(Integer userId) {
		return couponService.getUngetCouponList(userId);
	}

	/**
	 * 领取未领取优惠券
	 */
	@RequestMapping("/addCouponRecordByUnget.action")
	@ResponseBody
	public JsonData addCouponRecordByUnget(Integer userId) {
		return couponService.addCouponRecordByUnget(userId);
	}
}
