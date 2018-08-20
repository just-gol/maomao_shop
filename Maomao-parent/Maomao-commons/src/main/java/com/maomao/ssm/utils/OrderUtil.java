package com.maomao.ssm.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.maomao.ssm.constant.UserConts;

public class OrderUtil {

	private static final String BASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 获取订单编号
	 * 
	 * @return
	 */
	public static String getOrderNum(Byte type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String now = sdf.format(new Date());
		Random random = new Random();
		int randomNum = random.nextInt(10000000);
		randomNum = randomNum % 2 == 0 ? UserConts.USER_TYPE_B.equals(type) ? randomNum : randomNum + 1
				: UserConts.USER_TYPE_C.equals(type) ? randomNum : randomNum + 1;
		now += new DecimalFormat("0000000").format(randomNum);
		return now;
	}

	/**
	 * 获取订单取货码
	 * 
	 * @return
	 */
	public static String getOrderCode() {
		String code = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			code += BASE.charAt(random.nextInt(BASE.length()));
		}
		return code;
	}

}
