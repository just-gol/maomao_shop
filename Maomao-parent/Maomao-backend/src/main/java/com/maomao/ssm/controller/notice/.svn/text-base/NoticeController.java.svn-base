package com.maomao.ssm.controller.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.NoticeService;

/**
 * 消息管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	/**
	 * 获取消息列表
	 */
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value = "/getNoticeList.action",method = RequestMethod.GET)
	@ResponseBody
	public JsonData getNoticeList(@RequestParam(defaultValue = "1")Integer pages, @RequestParam(defaultValue = "10")Integer rows,Byte type,String keywords) {
		return noticeService.getNoticeList(pages, rows ,keywords);
	}
	
	/**
	 * 新增消息
	 */
	@RequestMapping(value = "/addNotice.action",method = RequestMethod.POST)
	@ResponseBody
	public JsonData addNotice(String modelName,String model,Long sendTime) {
		return noticeService.addNotice(modelName,model,sendTime);
	}
	
	/**
	 * 删除消息
	 */
	@RequestMapping(value = "/delNotice.action",method = RequestMethod.POST)
	@ResponseBody
	public JsonData delNotice(Integer modelId) {
		return noticeService.delNotice(modelId);
	}
	
	/**
	 * 修改发送消息
	 */
	@RequestMapping(value = "/updateNotice.action",method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateNotice(Integer modelId,String modelName,String model,Long sendTime) {
		return noticeService.updateNotice(modelId,modelName,model,sendTime);
	}
	
	
	/**
	 * 查看指定消息
	 */
	@RequestMapping(value = "/getNotice.action",method = RequestMethod.GET)
	@ResponseBody
	public JsonData getNotice(Integer modelId) {
		return noticeService.getNotice(modelId);
	}
}
