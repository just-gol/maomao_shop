package com.maomao.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.maomao.ssm.pojo.SupplyCategory;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年3月6日
 */

public interface SupplyCategoryMapper {

	List<SupplyCategory> getSupplyCategoryList(@Param("keywords") String keywords,
			@Param("isRecommend") Byte isRecommend);

}
