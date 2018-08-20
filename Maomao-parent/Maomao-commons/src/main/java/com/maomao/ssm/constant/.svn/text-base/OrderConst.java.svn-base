package com.maomao.ssm.constant;

public class OrderConst {

	// 订单状态 0未支付 1已支付 2已到账 3已发货/未核销 4已完成/已核销 5已关闭/已过期 6退货/售后 7已退款 -1删除
	public static final Byte ORDER_STATUS_UNPAY = 0;
	public static final Byte ORDER_STATUS_PAID = 1;
	public static final Byte ORDER_STATUS_TRANSFERRED = 2;
	public static final Byte ORDER_STATUS_DELIVERED = 3;
	public static final Byte ORDER_STATUS_FINISHED = 4;
	public static final Byte ORDER_STATUS_CLOSED = 5;
	public static final Byte ORDER_STATUS_SERVICE = 6;
	public static final Byte ORDER_STATUS_REFUNDED = 7;
	public static final Byte ORDER_STATUS_DELETE = -1;

	// 订单自动关闭时间 30m
	public static final int ORDER_AUTO_CLOSE_TIME = 30;

	// 订单自动收货时间 15d
	public static final int ORDER_AUTO_FINISH_TIME = 15;

	// 订单商品分类 0认筹 1普通
	public static final Integer GOODS_CATEGORY_SUBSCRIPTION = 0;
	public static final Integer GOODS_CATEGORY_NORMAL = 1;

	// 订单来源 0app 1网页
	public static final Byte ORDER_SRC_APP = 0;
	public static final Byte ORDER_SRC_WEB = 1;

	// 支付方式 0支付宝 1微信 2余额
	public static final Byte ORDER_PAY_WAY_ALIPAY = 0;
	public static final Byte ORDER_PAY_WAY_WECHAT = 1;
	public static final Byte ORDER_PAY_WAY_USER_MONEY = 2;

	// 结算状态 0未结算 1已结算
	public static final Byte ORDER_STATUS_SETTLEMENT_FALES = 0;
	public static final Byte ORDER_STATUS_SETTLEMENT_TRUE = 1;
	
	// 订单类型 0合卖订单 1普通商品 2压货商品备货 3压货商品售出
	public static final Integer ORDER_CATEGORY_SUBSCRIPTION = 0;
	public static final Integer ORDER_CATEGORY_NORMAL = 1;
	public static final Integer ORDER_CATEGORY_OVERSTOCK_PURCHASE = 2;
	public static final Integer ORDER_CATEGORY_OVERSTOCK_SALES = 3;

	public static String getStatusString(Byte status) {
		if (status == null) {
			return null;
		}
		switch (status) {
		case 0:
			return "未支付";
		case 1:
			return "已支付";
		case 2:
			return "已到账";
		case 3:
			return "已发货";
		case 4:
			return "已完成";
		case 5:
			return "已关闭";
		case 6:
			return "退货/售后";
		case 7:
			return "已退款";
		default:
			return null;
		}
	}
}
