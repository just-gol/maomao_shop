package com.maomao.ssm.service;

import com.maomao.ssm.pojo.JsonData;

/** 
* @descrption:商品管理Service
*/

public interface GoodsService {
	JsonData getGoodsByCategoryId(Integer type,Integer categoryId,Integer pages,Integer rows,Integer sortType,String brand,Long startPrice,Long endPrice);
	JsonData goodsSearch(Integer type,String keywords,Integer pages,Integer rows,Integer sortType,String brand,Long startPrice,Long endPrice);
	JsonData getGoodsBrands(Integer categoryId,String name);
	JsonData getGoodsDetails (Integer userId,Integer categoryId,Integer goodsId );
	JsonData addGoodsCollection(Integer userId,Integer goodsId,Integer type);
	JsonData addGoodsShareRecord(Integer userId, Integer goodsId, Integer category);
}
