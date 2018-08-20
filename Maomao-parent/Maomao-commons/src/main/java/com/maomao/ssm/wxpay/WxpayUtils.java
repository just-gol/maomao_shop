package com.maomao.ssm.wxpay;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants.SignType;
import com.github.wxpay.sdk.WXPayUtil;
import com.maomao.ssm.wechat.MyWechatConfig;
import com.maomao.ssm.wechat.WechatUtils;

public class WxpayUtils {
	private String certPath;
	private String notifyUrl;
	private String returnUrl;

	public Map<String, String> unifiedorder(String outtradeno, String goodsName, String money, String ip, String type,
			String timeStart, String timeExpire, String openId) throws Exception {
		WXPayConfig config = new MyWxpayConfig(certPath);
		if (openId != null) {
			config = new MyWechatConfig(certPath);
		}
		WXPay wxpay = new WXPay(config);
		Map<String, String> data = new HashMap<String, String>();
		data.put("body", goodsName);
		data.put("out_trade_no", outtradeno);
		data.put("fee_type", "CNY");
		data.put("total_fee", money);
		data.put("spbill_create_ip", ip);
		data.put("time_start", timeStart);
		data.put("time_expire", timeExpire);
		data.put("notify_url", notifyUrl);
		data.put("trade_type", type);
		if (openId != null) {
			data.put("openid", openId);
		}
		try {
			Map<String, String> resp = wxpay.unifiedOrder(data);
			// System.out.println(resp);
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, String> getH5Sign(Map<String, String> data) throws Exception {
		WXPayConfig config = new MyWxpayConfig();
		data.put("appid", config.getAppID());
		data.put("partnerid", config.getMchID());
		data.put("package", "Sign=WXPay");
		data.put("noncestr", WXPayUtil.generateNonceStr());
		data.put("timestamp", new Date().getTime() / 1000 + "");
		data.put("sign", WXPayUtil.generateSignature(data, config.getKey(), SignType.MD5));
		return data;
	}

	public Map<String, String> getWechatSign(Map<String, String> data) throws Exception {
		WXPayConfig config = new MyWechatConfig(certPath);
		data.put("appId", config.getAppID());
		data.put("nonceStr", WXPayUtil.generateNonceStr());
		data.put("timeStamp", new Date().getTime() / 1000 + "");
		data.put("signType", "MD5");
		data.put("paySign", WXPayUtil.generateSignature(data, config.getKey(), SignType.MD5));
		return data;
	}

	public String getWapPayStringAddRedirectUrl(String mwebUrl) throws Exception {
		mwebUrl += "&redirect_url=" + URLEncoder.encode(returnUrl, "utf-8");
		return mwebUrl;
	}

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

}
