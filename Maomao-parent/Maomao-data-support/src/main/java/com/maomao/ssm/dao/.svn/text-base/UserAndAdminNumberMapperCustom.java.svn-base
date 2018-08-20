package com.maomao.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.maomao.ssm.pojo.Query;
import com.maomao.ssm.pojo.ShopApplyAudit;

public interface UserAndAdminNumberMapperCustom {

	Integer queryInternal(String queryString);

	Integer querySupplier(String queryString);

	Integer queryOrdinaryNum(String queryString);

	Integer queryIndividual(String queryString);

	Integer getIndividualTotal(@Param(value = "queryString") String queryString);

	Integer getSupplierUserList(@Param(value = "queryString") String queryString);

	Integer getInternalList(@Param(value = "queryString") String queryString);

	Integer getUserApplyTotal(@Param(value = "queryString")String queryString);

	List<ShopApplyAudit> getShopApplyAuditList(Query query);

}
