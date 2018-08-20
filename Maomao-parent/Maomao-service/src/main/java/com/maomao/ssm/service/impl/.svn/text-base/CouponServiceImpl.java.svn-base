package com.maomao.ssm.service.impl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maomao.ssm.bean.Coupon;
import com.maomao.ssm.bean.CouponCustom;
import com.maomao.ssm.dao.CouponMapperCustom;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.user.UserCouponRecord;
import com.maomao.ssm.service.CouponService;
import com.maomao.ssm.service.common.CouponRecordService;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponMapperCustom couponMapperCustom;
	@Autowired
	private CouponRecordService couponRecordService;

	/**
	 * 获取首页未领取优惠券列表
	 */
	@Override
	public JsonData getUngetCouponList(Integer userId) {
		// 验证参数
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询用户未领取的通用优惠券
		List<CouponCustom> couponCustoms = couponMapperCustom.getUngetCouponListByUserId(userId);
		List<UserCouponRecord> ungetCouponList = new ArrayList<UserCouponRecord>();
		if (couponCustoms != null && couponCustoms.size() > 0) {
			for (CouponCustom couponCustom : couponCustoms) {
				if (couponCustom.getSum() == 0) {
					ungetCouponList.add(new UserCouponRecord(couponCustom));
				}
			}
		}
		return JsonData.setSuccessMessage(ungetCouponList);
	}

	/**
	 * 领取未领取优惠券
	 */
	@Override
	public JsonData addCouponRecordByUnget(Integer userId) {
		// 验证参数
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询用户未领取的通用优惠券
		List<CouponCustom> couponCustoms = couponMapperCustom.getUngetCouponListByUserId(userId);
		List<Coupon> coupons = new ArrayList<Coupon>();
		for (CouponCustom couponCustom : couponCustoms) {
			if (couponCustom.getSum() == 0) {
				coupons.add(couponCustom);
			}
		}
		// 领取优惠券
		couponRecordService.addCouponRecord(userId, coupons);
		return JsonData.setSuccessMessage();
	}

}
