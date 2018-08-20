package com.maomao.ssm.service.admin;

import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.pojo.JsonData;

public interface GoodsOverstockService {

	JsonData getGoodsAddressList();

	JsonData addAndUpdateBuyOutGoods(GoodsOverstockWithBLOBs goodsOverstock, String detail, String imgs, String param,
			String GoodsOverstockOrgoodsWarehouseOrAddressList,Byte status);

	JsonData getBuyOutGoodsById(Integer goodsOverstockId);
	
	JsonData delAndUpAndOtherBuyOutGoods(Integer[] goodsOverstockIds,Byte status,String reason);

	JsonData getBuyOutGoodsList(Integer pages, Integer rows, Byte status, String goodsOverstockName);
	
	JsonData delGoodsOverstockInfo(Integer goodsOverstockInfoId,Byte status);

	JsonData delGoodsAddress(Integer goodsAddressId);
	
	JsonData updateGoodsOverstock(Integer goodsOverstockId , Integer stock);
}
