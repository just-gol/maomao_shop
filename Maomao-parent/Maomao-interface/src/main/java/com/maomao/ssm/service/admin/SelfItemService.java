package com.maomao.ssm.service.admin;

import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.pojo.JsonData;

/** 
* @author:wzy
* @descrption:自营商品管理
* @version:
* @date:2018年3月6日
*/

public interface SelfItemService {
	JsonData getSelfItemList(Integer pages,Integer rows,Integer categoryId,Byte status,Byte getWay,String name,byte type,Byte warehouseType,Integer adminId);
	JsonData getItemById(Integer itemId);
	JsonData getItemService(Integer itemId);
	JsonData getItemBrand(Integer itemId);
	JsonData getServiceAll();
	JsonData saveItem(GoodsWithBLOBs goods, Byte addressType, String address, String province,
			String city, String area, String mobile, String addressName,String diyAddress, String diyProvince,
			String diyCity, String diyArea, String diyMobile, String diyAddressName,Byte flag,String goodsSku );
	JsonData setItemOnSale(Integer[] itemIds,Integer userId);
	JsonData delItemOnSale(Integer itemIds);
	JsonData getMortgageTotal(Integer adminId);
	JsonData updateGoodsShelvesStock(Integer skuId, Integer stock);
}
