package com.maomao.ssm.service;

import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.maomao.ssm.pojo.JsonData;

public interface OrderService {

	public JsonData getOrderList(Integer userId, Integer pages, Integer rows);

	public JsonData getOrderDetail(Integer userId, Integer orderId);

	public JsonData getOrderConfirm(Integer userId, Integer goodsId, Integer skuId, Integer goodsNum, Integer category);

	public JsonData addOrder(Integer userId, Integer goodsId, Integer category, Integer addressId, Integer goodsNum,
			Byte payType, Byte getWay, Integer couponRecordId, Byte orderSrc, Integer salesUserId, Long tempstamp,
			Integer skuId);

	public JsonData getOrderPayInfo(Integer userId, Integer orderId, Byte payWay, String ip);

	public JsonData updateOrderByFinish(Integer userId, Integer orderId);

	public JsonData addGoodsSubscriptionRecord(Integer userId, Integer goodsSubscriptionId, Integer num);

	public boolean updateOrderByAlipayNotice(Map<String, String> params) throws AlipayApiException;

	public boolean updateOrderByYspayNotice(Map<String, String> params);

	public JsonData getOrderWapPayString(Integer userId, Integer orderId, Byte payWay, String ip, String ua,
			String openId);

	public JsonData updateOrderByPay(Integer userId, Integer orderId, String orderNum);

	public String updateOrderByWxpayNotice(String notifyData) throws Exception;

	public JsonData getGoodsOrderGoodsInfoList(Integer pages, Integer rows, Integer userId);

	public JsonData getGoodsOrderGoodsInfoDetail(Integer goodsOrderGoodsInfoId);

}
