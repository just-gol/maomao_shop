package com.maomao.ssm.utils;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * 短信验证码
 * 
 * @author huhengda
 *
 */
public class SmsCodeUtils {
	/**
	 * 获取短信验证码内的随机数
	 * 
	 * @return
	 */
	public static String getSmsCode() {
		int random = new Random().nextInt(1000000);
		return new DecimalFormat("000000").format(random);
	}
}
