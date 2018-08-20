package com.maomao.ssm.service.admin;

import com.maomao.ssm.pojo.JsonData;

/** 
* @author:wzy
* @descrption:SKU service
* @version:
* @date:2018年4月27日
*/

public interface SkuService {
	//根据分类获得sku的key
	JsonData getSkuKeyProperty(Integer categoryId);
	
	//根据id删除sku的key
	JsonData deleteSkuKeyById(Integer skuKeyId);
	
	//根据id删除商品sku
	JsonData deleteGoodsSkuById(Integer skuId);
}

 




























