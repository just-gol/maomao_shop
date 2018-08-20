package com.maomao.ssm.service.admin;

import java.util.List;

import com.maomao.ssm.bean.HomeContent;
import com.maomao.ssm.pojo.HomeCategoryBean;
import com.maomao.ssm.pojo.HomeContentBean;
import com.maomao.ssm.pojo.HomeContentBeanList;
import com.maomao.ssm.pojo.JsonData;

/** 
* @author:wzy
* @descrption:
* @version:
* @date:2018年3月8日
*/

public interface SystemService {
	JsonData getCouponList(Integer pages ,Integer rows,String startDate ,String endDate ,String keywords);
	
	JsonData getServicManagementList(Integer pages, Integer rows);

	JsonData getServicManagementList(String name, String detail);

	JsonData startServicManagement(Integer goodsServiceId);

	JsonData stopServicManagement(Integer goodsServiceId);

	JsonData delServicManagement(Integer goodsServiceId);

	JsonData editServicManagement(Integer goodsServiceId);

	JsonData updateServicManagement(Integer goodsServiceId,String name, String detail);
	
	JsonData addLink(Integer bannerId ,String AndroidImgURL,String IosImgURL,String href,Byte type);

	JsonData setAdManagementDown(Integer bannerId,Byte type);
	
	JsonData setAdManagementUp(Integer bannerId,Byte type);

	JsonData setAdManagementTop(Integer bannerId,Byte type);

	JsonData getAdManagementList(Byte type);

	JsonData addRecommend(Integer goodsCategoryId ,Integer HomeCategoryId, Byte imgType,String categoryName, String introduction,String homeContentBeanList);

	JsonData setRecommendDown(Integer homeCategoryId);

	JsonData setRecommendUp(Integer homeCategoryId);

	JsonData setRecommendTop(Integer homeCategoryId);

	JsonData getRecommendList();

	JsonData delRecommend(Integer categoryId);

	JsonData saveRecommend(Integer HomeCategoryId,Integer goodsCategoryId,String categoryName,Byte imgType, String introduction, String homeContentBeanList);

	JsonData getGoodsList(Integer goodsCategoryId);

	JsonData delContent(Integer homeContentId);

	JsonData delBanner(Integer bannerId,Byte type);

	JsonData getGoodsDetailList(String queryString);

}



































