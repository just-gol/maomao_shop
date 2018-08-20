package com.maomao.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.maomao.ssm.bean.UserApplyCustom;

public interface UserApplyMapperCustom {
	List<UserApplyCustom> getUserApplyList(@Param("keyWords") String keyWords, @Param("creditReal") Byte creditReal,
			@Param("userType") Byte userType, @Param("status") Byte status);
}
