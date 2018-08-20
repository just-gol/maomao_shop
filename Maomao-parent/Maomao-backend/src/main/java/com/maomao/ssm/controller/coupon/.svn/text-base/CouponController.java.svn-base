package com.maomao.ssm.controller.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.CouponService;

/**
 * 优惠券管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="coupon")
public class CouponController {
	
	@Autowired
	private CouponService couponService;
	
	/**
	 * 查询优惠券列表
	 */
	@RequestMapping(value = "/getCouponList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getCouponList(@RequestParam(defaultValue = "1") Integer pages,@RequestParam(defaultValue = "10") Integer rows ,Long startTime,Long endTime,String queryString) {
		return couponService.getCouponList(pages, rows,startTime,endTime,queryString);
	}
	
	/**
	 * 新增优惠券
	 */
	@RequestMapping(value = "/addCoupon.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addCoupon(String couponName , Byte type , Long availableMoney, Long discount , Long useStartTime ,Long useEndTime,Integer validityTerm, Integer getNum,Integer stockTotal,Long startTime ,Long endTime) {
		return couponService.addCoupon(couponName,type,availableMoney,discount,useStartTime,useEndTime,validityTerm,getNum,stockTotal,startTime,endTime);
		
	}
	
	/**
	 * 删除优惠券
	 */
	@RequestMapping(value = "/delCoupon.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData delCoupon(Integer couponId) {
		return couponService.delCoupon(couponId);
	}
	
	/**
	 * 查看指定优惠券
	 */
	@RequestMapping(value = "/getCoupon.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getCoupon(Integer couponId) {
		return couponService.getCoupon(couponId);
	}
}
