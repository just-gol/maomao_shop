package com.maomao.ssm.service.v2;

import com.maomao.ssm.pojo.JsonData;

/** 
* @author:wzy
* @descrption:
* @version:
* @date:2018年7月9日
*/

public interface CategoryServiceV2 {
	JsonData getCategoyrDetail(String ua);
	JsonData getCategoryDetailById(Integer categoryId, Integer pages, Integer rows,
			Integer sortType, String brand, Long startPrice, Long endPrice);
}
