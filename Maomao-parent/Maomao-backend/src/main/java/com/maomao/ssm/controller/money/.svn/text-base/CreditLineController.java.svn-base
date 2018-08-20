package com.maomao.ssm.controller.money;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.CreditLineService;

/** 
* @author:wzy
* @descrption:授信管理
* @version:
* @date:2018年4月3日
*/
@Controller
@RequestMapping("/money")
public class CreditLineController {
	@Autowired
	private CreditLineService creditLineService;
	
	
	/**
	 * 查看店铺授信管理列表
	 * author:wzy
	 */
	@RequestMapping(value = "/shopCreditList.action",method = RequestMethod.POST)
	@ResponseBody
	public JsonData getShopCreditLineList(@RequestParam(defaultValue = "1")Integer pages, @RequestParam(defaultValue = "10")Integer rows,Byte type,String keywords) {
		return creditLineService.getShopCreditLineList(pages, rows, type,keywords);
	}
	
	/**
	 * 根据id查看店铺授信详情
	 */
	@RequestMapping(value = "/shopCreditDetail.action",method = RequestMethod.POST)
	@ResponseBody
	public JsonData getShopCreditLineById(Integer id) {
		return creditLineService.getShopCreditLineById(id);
	}
	/**
	 * 修改店铺授信详情
	 */
	@RequestMapping(value = "/updateShopCredit.action",method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateShopCreditLineById(Integer id,Byte type,Long money,Long time) {
		return creditLineService.updateShopCreditLineById(id, type, money, time);
	}
	
	/**
	 * 公司贷款管理
	 */
	@RequestMapping(value = "/companyCredit.action",method = RequestMethod.POST)
	@ResponseBody
	public JsonData getCompanyCreditLineList(@RequestParam(defaultValue = "1")Integer pages, @RequestParam(defaultValue = "10")Integer rows,Byte type,String keywords) {
		return creditLineService.getCompanyCreditLineList(pages, rows, keywords);
	}
	
	/**
	 * 公司贷款修改
	 */
	@RequestMapping(value = "/updateCompanyCredit.action",method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateCompanyCreditLineById(Integer id,Long zhiyaMoney,Long dakuanMoney,Long time) {
		return creditLineService.updateCompanyCreditLineById(id, zhiyaMoney, dakuanMoney, time);
	}
	
	/**
	 * 申请调额
	 */
	/**
	 * 获取调额用户数量
	 */
	@RequestMapping(value = "/userApplyCount.action",method = RequestMethod.GET)
	@ResponseBody
	public JsonData getUserApplyCount() {
		return creditLineService.getUserApplyCount();
	}
	
	/**
	 * 获取申请调额列表
	 */
	@RequestMapping(value = "/userApplyCreditList.action",method = RequestMethod.POST)
	@ResponseBody
	public JsonData getUserApplyCreditList(@RequestParam(defaultValue = "1")Integer pages, @RequestParam(defaultValue = "10")Integer rows,Byte type,String keywords) {
		return creditLineService.getUserApplyList(pages, rows, type, keywords);
	}
	
	/**
	 * 根据id获取申请调额详情
	 */
	@RequestMapping(value = "/userApplyCreditDetail.action",method = RequestMethod.POST)
	@ResponseBody
	public JsonData getUserApplyCreditDetailById(Integer id) {
		return creditLineService.getUserApplyDetailById(id);
	}
	
	/**
	 * 编辑申请调额
	 */
	@RequestMapping(value = "/updateUserApply.action",method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateUserApply(Integer id,Byte type,Long money,Long time) {
		return creditLineService.updateUserApply(id, type, money, time);
	}
	
	/**
	 * 拒绝申请调额
	 */
	@RequestMapping(value = "/refuseUserApply.action",method = RequestMethod.POST)
	@ResponseBody
	public JsonData upateUserApplyRefuse(Integer id,String msg) {
		return creditLineService.updateUserApplyRefuse(id, msg);
	}
}






























