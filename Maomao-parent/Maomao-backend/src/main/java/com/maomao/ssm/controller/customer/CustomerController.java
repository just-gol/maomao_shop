package com.maomao.ssm.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.UserFeedBackService;

/**
 * @author:wzy
 * @descrption:在线客服
 * @version:
 * @date:2018年5月8日
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private UserFeedBackService userFeedBackService;

	/**
	 * 获取问题反馈列表
	 */
	@RequestMapping(value = "/getFeebackListUnSolved.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getFeebackListUnSolved(@RequestParam(defaultValue = "1") Integer pages,@RequestParam(defaultValue = "10") Integer rows,Long startTime, Long endTime, String keywords,Integer adminId,Byte type) {
		return userFeedBackService.getFeebackListUnSolved(pages, rows, startTime, endTime, keywords, adminId,type);
	}

	/**
	 * 处理反馈人员下拉列表
	 */
	@RequestMapping(value = "/getFeedbackAdminList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getFeedbackAdminList() {
		return userFeedBackService.getFeedbackAdminList();
	}
	/**
	 * 查看反馈
	 */
	@RequestMapping(value = "/getFeedbackDetail.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getFeedbackDetail(Integer id,String reason,Byte type,Integer adminId) {
		return userFeedBackService.getFeedbackDetail(id, reason,type, adminId);
	}
	
	
}




















