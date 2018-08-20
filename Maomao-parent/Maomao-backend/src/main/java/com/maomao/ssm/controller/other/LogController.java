package com.maomao.ssm.controller.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.LogService;

/**
 * 日志管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/log")
public class LogController {
	
	@Autowired 
	private LogService logService;
	
	@RequestMapping(value = "/getLogList.action",method = RequestMethod.GET)
	@ResponseBody
	public JsonData getLogList(@RequestParam(defaultValue = "1")Integer pages, @RequestParam(defaultValue = "10")Integer rows,String keywords) {
		return logService.getLogList(pages, rows , keywords);
	}
}
