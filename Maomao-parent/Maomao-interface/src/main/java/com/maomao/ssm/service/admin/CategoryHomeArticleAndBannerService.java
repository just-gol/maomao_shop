package com.maomao.ssm.service.admin;

import com.maomao.ssm.pojo.JsonData;

public interface CategoryHomeArticleAndBannerService {

	JsonData addCategoryHomeArticle(Integer categoryHomeArticleId,String introduction,String url,String title,Long effectiveTime,Byte status);

	JsonData getCategoryHomeArticleList(Integer pages, Integer rows);

	
	JsonData getCategoryHomeArticle(Integer categoryHomeArticleId);

	JsonData delCategoryHomeArticle(Integer categoryHomeArticleId,Byte status);
	
	JsonData addAndUpdateBanner(String androidImgURL, String iosImgURL, String href);
	
	JsonData delBanner(Integer bannerId);


	JsonData getBanner();



}
