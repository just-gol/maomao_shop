package com.maomao.ssm.controller.money;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.DailyDetailService;

@Controller
@RequestMapping("/daily")
public class DailyDetailController {
	@Autowired
	private DailyDetailService dailyDetailService;

	@RequestMapping(value = "/getDailyDetailList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getDailyDetailList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, Long startTime, Long endTime) {
		return dailyDetailService.getDailyDetailList(pages, rows, startTime, endTime);
	}

	@RequestMapping(value = "/getDailyDetailDetail.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getDailyDetailDetail(Integer id) {
		return dailyDetailService.getDailyDetailDetail(id);
	}

	@RequestMapping(value = "/getDailyDetailOrderList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getDailyDetailOrderList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, String queryString, Integer id) {
		return dailyDetailService.getDailyDetailOrderList(pages, rows, queryString, id);
	}
}
