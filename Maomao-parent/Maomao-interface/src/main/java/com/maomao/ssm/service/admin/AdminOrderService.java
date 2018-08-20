package com.maomao.ssm.service.admin;

import com.maomao.ssm.pojo.JsonData;

public interface AdminOrderService {

	public JsonData getOrderList(Integer pages, Integer rows, Byte status, String queryString, Integer adminId,
			Integer category);

	public JsonData getOrderDetail(Integer orderId, Integer adminId);

	public JsonData updateOrderByDeliver(Integer orderId, Integer goodsOrderGoodsInfoId, String expressNum,
			String express);

	public JsonData updateOrderByRefund(Integer orderId);

	public JsonData updateOrderByRefundAddress(Integer orderId, String refundAddress, String refundName,
			String refundMobile);

	public JsonData updateOrderByRefundMoney(Integer orderId);

	public JsonData getOrderDetailByCode(String code);

	public JsonData updateOrderByCode(Integer orderId);

}
