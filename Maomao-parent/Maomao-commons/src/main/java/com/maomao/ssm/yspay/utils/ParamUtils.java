package com.maomao.ssm.yspay.utils;

import com.maomao.ssm.constant.OrderConst;

public class ParamUtils {
	public static String getSdkBankType(Byte payWay) {
		if (OrderConst.ORDER_PAY_WAY_ALIPAY.equals(payWay)) {
			return "1903000";
		}
		if (OrderConst.ORDER_PAY_WAY_WECHAT.equals(payWay)) {
			return "1902000";
		}
		return null;
	}
}
