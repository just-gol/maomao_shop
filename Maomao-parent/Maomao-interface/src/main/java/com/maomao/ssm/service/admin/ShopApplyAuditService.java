package com.maomao.ssm.service.admin;

import com.maomao.ssm.pojo.JsonData;

public interface ShopApplyAuditService {

	//立即审核
	JsonData getShopApply(Integer userApplyId);
	
	//审核通过
	JsonData shopApproved(Integer userApplyId,Byte creditReal,Long loan);
	
	//拒绝受理
	JsonData shopRefused(Integer userApplyId,String reason);

	//用户搜索
	JsonData getSearchUser(Integer pages, Integer rows, String queryString);

}
