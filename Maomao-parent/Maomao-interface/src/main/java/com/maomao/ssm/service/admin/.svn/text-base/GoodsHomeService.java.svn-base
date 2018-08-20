package com.maomao.ssm.service.admin;

import com.maomao.ssm.pojo.JsonData;

public interface GoodsHomeService {
	JsonData addGoodsHomepageCategory(Integer goodsHomepageCategoryId ,String name, String img);
	
	JsonData setHomePageCategoryDown(Integer homepageCategoryId);
	
	JsonData setHomePageCategoryUp(Integer homepageCategoryId);
	
//	JsonData setHomePageCategoryTop(Integer homepageCategoryId);
	
	JsonData delHomePageCategoryById(Integer homepageCategoryId);
	
	JsonData addGoodsHome(Integer goodsHomepageCategoryId, Integer[] goodsIds,Integer category,Byte type);
	
	JsonData getGoodsAll();
	
	JsonData setGoodsHomeUp(Integer goodsHomepageCategoryId, Integer goodsId,Integer category);
	
	JsonData setGoodsHomeDown(Integer goodsHomepageCategoryId, Integer goodsId,Integer category);
	
	JsonData setGoodsHomeTop(Integer goodsHomepageCategoryId, Integer goodsId,Integer category);
	
	JsonData delGoodsHome(Integer goodsHomepageCategoryId, Integer goodsId,Integer category);
	
	JsonData getGoodsHomeAll();
	
	JsonData getGoodsOverstockAll();
	
}
