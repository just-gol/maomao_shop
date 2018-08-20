package com.maomao.ssm.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.ShopApplyAuditService;
/**
 * 开店申请审核
 * @author Administrator
 * @date:2018年3月5日
 */
@Controller
@RequestMapping(value = "/shop")
public class ShopApplyAuditController {
	
	@Autowired
	private ShopApplyAuditService shopApplyAuditService;
	
	//用户搜索
	@RequestMapping(value = "/getSearchUser.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData searchUser(@RequestParam(defaultValue="1")Integer pages,@RequestParam(defaultValue="10")Integer rows,String queryString){
		return shopApplyAuditService.getSearchUser(pages,rows,queryString);
	}
	
	//立即审核
	@RequestMapping(value = "/getShopApply.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getShopApply(Integer userApplyId){
		return shopApplyAuditService.getShopApply(userApplyId);
	}
	
	//审核通过
	@RequestMapping(value = "/shopApproved.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData shopApproved(Integer userApplyId,Byte creditReal,Long loan){
		return shopApplyAuditService.shopApproved(userApplyId,creditReal,loan);
	}
	
	//拒绝受理
	@RequestMapping(value = "/shopRefused.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData shopRefused(Integer userApplyId,String reason){
		return shopApplyAuditService.shopRefused(userApplyId,reason);
	}
}
