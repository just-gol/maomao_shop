package com.maomao.ssm.service.admin;

import com.maomao.ssm.pojo.JsonData;

public interface CouponService {

	JsonData getCouponList(Integer pages, Integer rows, Long startTime, Long endTime, String queryString);

	JsonData addCoupon(String couponName, Byte type, Long availableMoney, Long discount, Long useStartTime,Long useEndTime,
			Integer validityTerm, Integer getNum,Integer stockTotal,Long startTime ,Long endTime);

	JsonData delCoupon(Integer couponId);

	JsonData getCoupon(Integer couponId);

}
