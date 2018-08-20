package com.maomao.ssm.service.common;

import java.util.List;

import com.maomao.ssm.bean.Coupon;

public interface CouponRecordService {

	public List<Integer[]> addCouponRecord(Integer userId, List<Coupon> coupons);
}
