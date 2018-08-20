package com.maomao.ssm.service.admin;

import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.pojo.JsonData;

/** 
* @author:wzy
* @descrption:
* @version:
* @date:2018年3月2日
*/

public interface SupplyCategoryService {
	JsonData getSupplyCategory(int page,int rows,String keywords,Byte isRecommend);
	JsonData deleteSupplyCategoryById(Integer supplyCategoryId);
	JsonData setCategoryStopById(Integer categoryId);
	JsonData setCategoryStartById(Integer categoryId);
	JsonData setCategorySortUp(Integer categoryId);
	JsonData setCategorySortBack(Integer categoryId);
	JsonData addCategroy(Integer sort,String categoryName,Integer categorySort,String imgURL,String brand,String skuKey,Integer maxCategorySort,Byte isRecommend);
	JsonData getCategoryBrand(Integer categoryId,Integer pages,Integer rows,Byte isFenye);
	JsonData editBrandById(Integer brandId,String name);
	JsonData deleteBrandById(Integer brandId);
	JsonData editCategoryById(GoodsCategory goodsCategory,String brand,String skuKey);
	JsonData getCategoryDetailByCategoryId(Integer categoryId);
	JsonData getCategorySimpleDetail(Byte isRecommend);
}

























