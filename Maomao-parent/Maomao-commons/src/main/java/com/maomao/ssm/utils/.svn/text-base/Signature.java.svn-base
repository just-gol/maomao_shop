package com.maomao.ssm.utils;

import java.util.Collections;
import java.util.List;

import org.springframework.util.DigestUtils;

/**
 * 签名工具类
 *
 * @author huhengda
 * 
 */
public class Signature {
	/**
	 * 初始私钥字符串
	 */
	private static final String INIT_STRING = "mao-mall.com";

	/**
	 * 生成加密私钥
	 * 
	 * @return
	 */
	public static String createKey() {
		return DigestUtils.md5DigestAsHex(INIT_STRING.getBytes());
	}

	/**
	 * 私钥
	 */
	public static final String SIGNATURE_KEY = "1aadd2c26ae7d4a62822e7c6ff9afa65";

	/**
	 * 私钥参数名称
	 */
	public static final String SIGNATURE_KEY_NAME = "signatureKey";

	/**
	 * 签名名称
	 */
	public static final String SIGNATURE_NAME = "signature";

	/**
	 * 验证签名
	 * 
	 * @param params
	 *            key=value的形式
	 * @param signature
	 * @return
	 */
	public static boolean validateSignatrue(List<String> params,String signature) {
		Collections.sort(params);
		StringBuffer paramsBuffer = new StringBuffer();
		for (String param : params) {
			paramsBuffer.append(param + "&");
		}
		paramsBuffer.append(SIGNATURE_KEY_NAME + "=" + SIGNATURE_KEY);
		return DigestUtils.md5DigestAsHex((paramsBuffer.toString().getBytes())).equals(signature);
	}

}















