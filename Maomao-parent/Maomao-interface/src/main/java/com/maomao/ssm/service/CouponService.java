package com.maomao.ssm.service;

import com.maomao.ssm.pojo.JsonData;

public interface CouponService {

	public JsonData getUngetCouponList(Integer userId);

	public JsonData addCouponRecordByUnget(Integer userId);
}
