package com.maomao.ssm.wechat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.maomao.ssm.utils.JsonUtils;
import com.maomao.ssm.utils.http.HttpUtils;

public class WechatUtils {

	private static final String APP_ID = "wx59af6e1b7a5f3b2c";
	private static final String APP_SECRET = "cf9927b1d2e3cae2789ad001344daee8";
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";

	private static final String CODE_BASE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
	private static final String CODE_USERINFO_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";

	private String redirectUri;

	public AccessToken getAccessToken(String code) {
		String param = "appid=" + APP_ID + "&secret=" + APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
		String result = HttpUtils.sendGet(ACCESS_TOKEN_URL, param);
		AccessToken accessToken = JsonUtils.jsonToPojo(result, AccessToken.class);
		return accessToken;
	}

	public UserInfo getUserInfo(String accessToken, String openId) {
		String param = "access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
		String result = HttpUtils.sendGet(USER_INFO_URL, param);
		UserInfo userInfo = JsonUtils.jsonToPojo(result, UserInfo.class);
		return userInfo;
	}

	public String getCodeUrl(String state) throws UnsupportedEncodingException {
		String param = "appid=" + APP_ID + "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8")
				+ "&response_type=code&scope=snsapi_base&state=" + state + "#wechat_redirect";
		return CODE_BASE_URL + "?" + param;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

}
