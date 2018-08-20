package com.maomao.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.maomao.ssm.bean.AdminMonthDetail;
import com.maomao.ssm.bean.AdminMonthDetailCustom;

public interface AdminMonthDetailMapperCustom {

	public List<AdminMonthDetail> getMonthDetailInsertList();

	public List<AdminMonthDetailCustom> getMonthDetailListByMonth(@Param("queryString") String queryString);

	public List<AdminMonthDetailCustom> getMonthDetailListByAdminId(@Param("adminId") Integer adminId);

	public AdminMonthDetailCustom getMonthDetailById(@Param("id") Integer id);
}
