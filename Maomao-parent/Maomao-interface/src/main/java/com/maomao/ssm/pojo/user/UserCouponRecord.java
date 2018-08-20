package com.maomao.ssm.pojo.user;

import java.io.Serializable;
import java.util.Date;

import com.maomao.ssm.bean.Coupon;
import com.maomao.ssm.bean.CouponRecord;
import com.maomao.ssm.utils.DateUtils;

public class UserCouponRecord implements Serializable {
	private Integer couponRecordId;
	private Long discount;
	private String name;
	private Long availableMoney;
	private Integer status;
	private Long createTime;
	private Long validityTime;

	public UserCouponRecord() {
		super();
	}

	public UserCouponRecord(Coupon coupon) {
		super();
		this.discount = coupon.getDiscount();
		this.name = coupon.getName();
		this.availableMoney = coupon.getAvaiableMoney();
		if (coupon.getUseStartTime() == null && coupon.getValidityTerm() != 36500) {
			this.validityTime = DateUtils.addDays(new Date(), coupon.getValidityTerm()).getTime();
		}
		if (coupon.getUseStartTime() != null) {
			this.validityTime = DateUtils.addDays(coupon.getUseStartTime(), coupon.getValidityTerm()).getTime();
		}

	}

	public UserCouponRecord(CouponRecord couponRecord) {
		super();
		this.couponRecordId = couponRecord.getId();
		this.discount = couponRecord.getDiscount();
		this.name = couponRecord.getName();
		this.availableMoney = couponRecord.getAvailableMoney();
		this.status = couponRecord.getStatus().intValue();
		this.createTime = couponRecord.getCreateTime().getTime();
		this.validityTime = couponRecord.getValidityTime().getTime();
	}

	public UserCouponRecord(Integer couponRecordId, Long discount, String name, Long availableMoney, Integer status,
			Long createTime, Long validityTime) {
		super();
		this.couponRecordId = couponRecordId;
		this.discount = discount;
		this.name = name;
		this.availableMoney = availableMoney;
		this.status = status;
		this.createTime = createTime;
		this.validityTime = validityTime;
	}

	public Integer getCouponRecordId() {
		return couponRecordId;
	}

	public void setCouponRecordId(Integer couponRecordId) {
		this.couponRecordId = couponRecordId;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAvailableMoney() {
		return availableMoney;
	}

	public void setAvailableMoney(Long availableMoney) {
		this.availableMoney = availableMoney;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(Long validityTime) {
		this.validityTime = validityTime;
	}

}
