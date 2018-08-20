package com.maomao.ssm.dao;

import java.util.List;

import com.maomao.ssm.bean.CouponRecordCustom;
import com.maomao.ssm.bean.CouponRecordExample;

public interface CouponRecordMapperCustom {
	public List<CouponRecordCustom> getCouponRecordNumByExample(CouponRecordExample example);
}
