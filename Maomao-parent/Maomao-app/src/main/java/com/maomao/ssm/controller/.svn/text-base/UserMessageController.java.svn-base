package com.maomao.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.UserMessageService;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年1月26日
 */
@Controller
@RequestMapping(value = "message", method = RequestMethod.POST)
public class UserMessageController {
	@Autowired
	private UserMessageService userMessageService;
	
	@RequestMapping("/getUnreadMessageTag.action")
	@ResponseBody
	public JsonData getUnreadMessageTag(Integer userId) {
		return userMessageService.getUnreadMessageTag(userId);
	}

	@RequestMapping("/userMessage.action")
	@ResponseBody
	public JsonData getUserMessage(Integer userId, Integer type, Integer pages, Integer rows) {
		return userMessageService.getUserMessage(userId, type, pages, rows);
	}

	/**
	 * 查看消息详情 author:wzy
	 */
	@RequestMapping("/messageDetail.action")
	@ResponseBody
	public JsonData getMessageDetail(Integer userId, Integer id) {
		return userMessageService.getMessageDetail(userId, id);
	}

	/**
	 * 删除消息 author:wzy
	 */
	@RequestMapping("/delMessage.action")
	@ResponseBody
	public JsonData delMessageById(Integer userId, Integer id, Byte type) {
		return userMessageService.delMessageById(userId, id, type);
	}

	/**
	 * 全部已读
	 */
	@RequestMapping("/readAll.action")
	@ResponseBody
	public JsonData readAll(Integer userId, Byte type) {
		return userMessageService.saveMessageReadAll(userId, type);
	}

}
