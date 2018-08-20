package com.maomao.ssm.service.admin;

import com.maomao.ssm.pojo.JsonData;

/** 
* @author:wzy
* @descrption:货源总览
* @version:
* @date:2018年3月7日
*/

public interface SupplyDetailsService {
	JsonData getSubpplyDetail(Integer pages,Integer rows,Integer categoryId,Integer startStock,Integer endStock,Byte goodsProperty,Byte goodsStatus,String goodsName,Byte type,Integer adminId);

	JsonData searchSubpplyDetail(Integer goodsId);

	JsonData supdateSubpplyDetail(Integer goodsId, String goodsName, Integer categoryId, Integer stock,
			Integer stockSham, Long pricePurchase,Long priceSales, Integer brandId, String param, String serviceId, Byte payType,
			Byte getWay, Integer category, Byte type, String province, String city, String area, String address,
			String name, String mobile,Byte flag);

	JsonData upSubpplyDetail(Integer goodsId);

	JsonData delPledge(Integer goodsId);
}































