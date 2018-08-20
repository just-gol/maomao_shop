package com.maomao.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.maomao.ssm.bean.UserWithdrawalsCustom;
import com.maomao.ssm.bean.UserWithdrawalsHistoryCustom;

public interface UserWithdrawalsMapperCustom {
	public List<UserWithdrawalsCustom> getUserWithdrawalsList(@Param("queryString") String queryString,
			@Param("status") Byte status, @Param("category") Byte category);

	public List<UserWithdrawalsHistoryCustom> getHistoryUserWithdrawalsList(@Param("type") Byte type,
			@Param("queryString") String queryString, @Param("category") Byte category);

	public UserWithdrawalsCustom getUserWithdrawalsSumByUserId(@Param("userId") Integer userId,
			@Param("status") Byte status, @Param("category") Byte category);
}
