package com.maomao.ssm.dao;

import java.util.List;

import com.maomao.ssm.bean.GoodsSubscriptionRecord;

public interface GoodsSubscriptionRecordMapperCustom {

	public List<GoodsSubscriptionRecord> selectGroupByUserId(Integer goodsSubscriptionId);
	public List<GoodsSubscriptionRecord> selectGroupByGoodsSubscriptionId(Integer userId);
}
