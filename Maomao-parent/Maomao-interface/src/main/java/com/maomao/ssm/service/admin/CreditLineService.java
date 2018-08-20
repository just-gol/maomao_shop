package com.maomao.ssm.service.admin;

import com.maomao.ssm.pojo.JsonData;

/** 
* @author:wzy
* @descrption:授信管理
* @version:
* @date:2018年4月3日
*/

public interface CreditLineService {
	//查看店铺授信管理列表
	JsonData getShopCreditLineList (Integer pages, Integer rows,Byte type,String keywords);
	JsonData getShopCreditLineById(Integer id);
	JsonData updateShopCreditLineById(Integer id,Byte type,Long money,Long time);

	//公司贷款管理
	JsonData getCompanyCreditLineList(Integer pages, Integer rows,String keywords);
	//公司贷款修改
	JsonData updateCompanyCreditLineById(Integer id,Long zhiyaMoney,Long dakuanMoney,Long time);
	
	//申请调额  ,获取申请调额用户数量
	JsonData getUserApplyCount();
	JsonData getUserApplyList(Integer pages, Integer rows,Byte type,String keywords);
	JsonData getUserApplyDetailById(Integer id);
	JsonData updateUserApply(Integer id,Byte type,Long money,Long time);
	JsonData updateUserApplyRefuse(Integer id,String msg);
}






















