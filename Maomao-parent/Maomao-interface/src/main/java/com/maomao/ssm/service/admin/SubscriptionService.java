package com.maomao.ssm.service.admin;

import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsWarehouse;
import com.maomao.ssm.pojo.JsonData;

/**
 * @author:wzy
 * @descrption:认筹商品服务
 * @version:
 * @date:2018年3月7日
 */

public interface SubscriptionService {
	JsonData getSubList(Integer pages, Integer rows, Integer categoryId, Byte status, String name);

	JsonData saveSubItem(GoodsSubscriptionWithBLOBs goods, Long bonus, GoodsWarehouse goodsWarehouse, String diyAddress,
			String diyProvince, String diyCity, String diyArea, String diyMobile, String diyAddressName);

	JsonData getSubItem(Integer itemId);

	JsonData saveSubItemStatus(Integer itemId, Byte type, String reason, Integer adminId);

	JsonData getSubDetail(Integer pages, Integer rows, Integer itemId);
	
	JsonData confirmBounsAvg(Integer goodsId);
	
	JsonData updateSelleStatus(Integer goodsId,Byte sellStatus);
}






















