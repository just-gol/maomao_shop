package com.maomao.ssm.controller.money;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.UserWithdrawalsService;

@Controller
@RequestMapping("/withdrawals")
public class UserWithdrawalsController {

	@Autowired
	private UserWithdrawalsService userWithdrawalsService;

	@RequestMapping(value = "/getUserWithdrawalsList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getUserWithdrawalsList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, String queryString, Byte category) {
		return userWithdrawalsService.getUserWithdrawalsList(pages, rows, queryString, category);
	}

	@RequestMapping(value = "/updateUserWithdrawalsByStatus.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateUserWithdrawalsByStatus(Integer id, Byte status) {
		return userWithdrawalsService.updateUserWithdrawalsByStatus(id, status);
	}

	@RequestMapping(value = "/getHistoryUserWithdrawalsList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getHistoryUserWithdrawalsList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, String queryString) {
		return userWithdrawalsService.getHistoryUserWithdrawalsList(pages, rows, queryString);
	}

	@RequestMapping(value = "/getHistoryUserWithdrawalsDetail.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getHistoryUserWithdrawalsDetail(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, Integer userId) {
		return userWithdrawalsService.getHistoryUserWithdrawalsDetail(pages, rows, userId);
	}
}
