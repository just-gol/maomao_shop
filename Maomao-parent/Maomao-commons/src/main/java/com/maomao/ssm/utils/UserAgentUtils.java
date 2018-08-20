package com.maomao.ssm.utils;

public class UserAgentUtils {

	private static final String ANDROID = "android";
	private static final String IPHONE = "iphone";
	private static final String IOS = "ios";
	private static final String DEAFAULT = "default";

	public static String getPhoneType(String ua) {
		if (ua.toLowerCase().contains(ANDROID)) {
			return ANDROID;
		}
		if (ua.toLowerCase().contains(IOS) || ua.toLowerCase().contains(IPHONE)) {
			return IOS;
		}
		return DEAFAULT;
	}
}
