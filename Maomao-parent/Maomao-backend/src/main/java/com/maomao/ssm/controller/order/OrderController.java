package com.maomao.ssm.controller.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.constant.AdminConst;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.AdminOrderService;
import com.maomao.ssm.utils.CookieUtils;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
	@Autowired
	AdminOrderService adminOrderService;
	@Autowired
	JedisClientPool jedisClientPool;

	@RequestMapping(value = "/getOrderList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getOrderList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, Byte status, String queryString, Integer category,
			HttpServletRequest request) {
		String cookie = CookieUtils.getCookieValue(request, "token", true);
		Integer adminId = Integer.parseInt(jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS, cookie));
		return adminOrderService.getOrderList(pages, rows, status, queryString, adminId, category);
	}

	@RequestMapping(value = "/getOrderDetail.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getOrderDetail(Integer orderId, HttpServletRequest request) {
		String cookie = CookieUtils.getCookieValue(request, "token", true);
		Integer adminId = Integer.parseInt(jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS, cookie));
		return adminOrderService.getOrderDetail(orderId, adminId);
	}

	@RequestMapping(value = "/updateOrderByDeliver.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateOrderByDeliver(Integer orderId, Integer goodsOrderGoodsInfoId, String expressNum,
			String express) {
		return adminOrderService.updateOrderByDeliver(orderId, goodsOrderGoodsInfoId, expressNum, express);
	}

	@RequestMapping(value = "/updateOrderByRefund.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData updateOrderByRefund(Integer orderId) {
		return adminOrderService.updateOrderByRefund(orderId);
	}

	@RequestMapping(value = "/updateOrderByRefundAddress.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateOrderByRefundAddress(Integer orderId, String refundAddress, String refundName,
			String refundMobile) {
		return adminOrderService.updateOrderByRefundAddress(orderId, refundAddress, refundName, refundMobile);
	}

	@RequestMapping(value = "/updateOrderByRefundMoney.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData updateOrderByRefundMoney(Integer orderId) {
		return adminOrderService.updateOrderByRefundMoney(orderId);
	}

	@RequestMapping(value = "/getOrderDetailByCode.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getOrderDetailByCode(String code) {
		return adminOrderService.getOrderDetailByCode(code);
	}

	@RequestMapping(value = "/updateOrderByCode.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateOrderByCode(Integer orderId) {
		return adminOrderService.updateOrderByCode(orderId);
	}

}
