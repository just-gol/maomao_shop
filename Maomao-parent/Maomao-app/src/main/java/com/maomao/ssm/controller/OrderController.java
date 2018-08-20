package com.maomao.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.OrderService;
import com.maomao.ssm.utils.CookieUtils;
import com.maomao.ssm.utils.HttpUtils;
import com.maomao.ssm.utils.IpUtils;

@Controller
@RequestMapping(value = "/order", method = RequestMethod.POST)
public class OrderController {
	@Autowired
	private OrderService orderServivce;

	@RequestMapping(value = "/getOrderList.action")
	@ResponseBody
	public JsonData getOrderList(Integer userId, Integer pages, Integer rows) {
		return orderServivce.getOrderList(userId, pages, rows);
	}

	@RequestMapping(value = "/getOrderDetail.action")
	@ResponseBody
	public JsonData getOrderDetail(Integer userId, Integer orderId) {
		return orderServivce.getOrderDetail(userId, orderId);
	}

	@RequestMapping(value = "/getOrderConfirm.action")
	@ResponseBody
	public JsonData getOrderConfirm(Integer userId, Integer goodsId, Integer skuId, Integer goodsNum,
			Integer category) {
		return orderServivce.getOrderConfirm(userId, goodsId, skuId, goodsNum, category);
	}

	@RequestMapping(value = "/addOrder.action")
	@ResponseBody
	public JsonData addOrder(Integer userId, Integer goodsId, Integer category, Integer addressId, Integer goodsNum,
			Byte payType, Byte getWay, Integer couponRecordId, Byte orderSrc, Integer salesUserId, Long timestamp,
			Integer skuId) {
		return orderServivce.addOrder(userId, goodsId, category, addressId, goodsNum, payType, getWay, couponRecordId,
				orderSrc, salesUserId, timestamp, skuId);
	}

	@RequestMapping(value = "/getOrderPayInfo.action")
	@ResponseBody
	public JsonData getOrderPayInfo(Integer userId, Integer orderId, Byte payWay, HttpServletRequest request) {
		return orderServivce.getOrderPayInfo(userId, orderId, payWay, IpUtils.getIp(request));
	}

	@RequestMapping(value = "/updateOrderByFinish.action")
	@ResponseBody
	public JsonData updateOrderByFinish(Integer userId, Integer orderId) {
		return orderServivce.updateOrderByFinish(userId, orderId);
	}

	@RequestMapping(value = "/addGoodsSubscriptionRecord.action")
	@ResponseBody
	public JsonData addGoodsSubscriptionRecord(Integer userId, Integer goodsSubscriptionId, Integer num) {
		return orderServivce.addGoodsSubscriptionRecord(userId, goodsSubscriptionId, num);
	}

	@RequestMapping(value = "/updateOrderByAlipayNotice.action")
	@ResponseBody
	public void updateOrderByAlipayNotice(HttpServletRequest request, HttpServletResponse response) {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			boolean tag = orderServivce.updateOrderByAlipayNotice(params);
			if (tag) {
				out.println("success");
			} else {
				out.println("fail");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AlipayApiException e) {
			e.printStackTrace();
			out.println("fail");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@RequestMapping(value = "/getOrderWapPayString.action")
	@ResponseBody
	public JsonData getOrderWapPayString(Integer userId, Integer orderId, Byte payWay, HttpServletRequest request) {
		String ip = IpUtils.getIp(request);
		if (ip.startsWith("192.168.1.")) {
			ip = "124.160.111.142";
		}
		String ua = request.getHeader("user-agent").toLowerCase();
		String openId = CookieUtils.getCookieValue(request, "openId");
		return orderServivce.getOrderWapPayString(userId, orderId, payWay, ip, ua, openId);
	}

	@RequestMapping(value = "/updateOrderByPay.action")
	@ResponseBody
	public JsonData updateOrderByPay(Integer userId, Integer orderId, String orderNum) {
		return orderServivce.updateOrderByPay(userId, orderId, orderNum);
	}

	@RequestMapping(value = "/updateOrderByYspayNotice.action")
	@ResponseBody
	public void updateOrderByYspayNotice(HttpServletRequest request, HttpServletResponse response) {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			boolean tag = orderServivce.updateOrderByYspayNotice(params);
			if (tag) {
				out.println("success");
			} else {
				out.println("fail");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@RequestMapping(value = "/updateOrderByWxpayNotice.action")
	@ResponseBody
	public void updateOrderByWxpayNotice(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			String notifyData = HttpUtils.readReqStr(request);
			String result = orderServivce.updateOrderByWxpayNotice(notifyData);
			out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			out.println(
					"<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[exception]]></return_msg></xml>");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@RequestMapping(value = "/getGoodsOrderGoodsInfoList.action")
	@ResponseBody
	public JsonData getGoodsOrderGoodsInfoList(Integer pages, Integer rows, Integer userId) {
		return orderServivce.getGoodsOrderGoodsInfoList(pages, rows, userId);
	}
	
	@RequestMapping(value = "/getGoodsOrderGoodsInfoDetail.action")
	@ResponseBody
	public JsonData getGoodsOrderGoodsInfoDetail(Integer goodsOrderGoodsInfoId) {
		return orderServivce.getGoodsOrderGoodsInfoDetail(goodsOrderGoodsInfoId);
	}

}
