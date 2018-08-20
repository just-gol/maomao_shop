package com.maomao.ssm.dao;

import java.util.List;

import com.maomao.ssm.bean.Banner;
import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.bean.HomeCategory;
import com.maomao.ssm.bean.HomeContent;

public interface SystemMapperCustom {
	Banner getAdManagementSort(Byte type);
	
	Banner setAdManagementDown(Integer sort,Byte type);
	
	Banner setAdManagementUp(Integer sort,Byte type);

	List<Banner> getAdManagementList();

	Integer getAdManagementCount();

	Banner getAdManagementTop();

	HomeCategory setRecommendDown(Integer sort);

	HomeCategory setRecommendUp(Integer sort);

	HomeCategory getRecommendTop();

	List<HomeCategory> getRecommendLists();

	Integer getRecommendMinSort();

	Integer getAdManagementMinSort();

	Integer getAdManagementMaxSort();

	GoodsCategory getOnlyGoodsCategory(Integer goodsCategoryId,Integer goodsCategoryId2,String goodsCategoryName);

	List<Banner> getBannerList();

	Banner getClassifyManagementTop();
	
	Integer getHomeContentTop(Integer categoryId);

	Integer getHomeContentMinSort();
	
	Integer getGoodsOverstockTop();
	
	HomeContent getHomeContentUp(Integer categoryId , Integer sort);
}
