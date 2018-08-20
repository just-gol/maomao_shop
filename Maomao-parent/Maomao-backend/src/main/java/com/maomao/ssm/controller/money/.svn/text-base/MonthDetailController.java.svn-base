package com.maomao.ssm.controller.money;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.MonthMoneyService;

@Controller
@RequestMapping("/money")
public class MonthDetailController {
	@Autowired
	private MonthMoneyService monthMoneyService;

	@RequestMapping(value = "/getMonthDetailList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getMonthDetailList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, String queryString) {
		return monthMoneyService.getMonthDetailList(pages, rows, queryString);
	}

	@RequestMapping(value = "/getMonthDetail.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getMonthDetail(Integer id) {
		return monthMoneyService.getMonthDetail(id);
	}

	@RequestMapping(value = "/getHistoryMonthDetailList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getHistoryMonthDetailList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, Integer adminId) {
		return monthMoneyService.getHistoryMonthDetailList(pages, rows, adminId);
	}

	@RequestMapping(value = "/updateByUploadImgs.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateByUploadImgs(Integer id, String loanImg, String moneyImg, Long uploadTime) {
		return monthMoneyService.updateByUploadImgs(id, loanImg, moneyImg, uploadTime);
	}

	@RequestMapping(value = "/getSalesDetailList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getSalesDetailList(Integer id) {
		return monthMoneyService.getSalesDetailList(id);
	}
}
