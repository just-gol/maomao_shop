package com.maomao.ssm.wechat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

public class MyWechatConfig implements WXPayConfig
{

	private byte[] certData;

	private String appID = "wx59af6e1b7a5f3b2c";
	private String mchID = "1505447521";
	private String key = "IuPrNYYE5WKnp5Mksx1baPiJX8jUR6Ng";

	public MyWechatConfig(String certPath) throws Exception {
		File file = new File(certPath);
		InputStream certStream = new FileInputStream(file);
		this.certData = new byte[(int) file.length()];
		certStream.read(this.certData);
		certStream.close();
	}

	public MyWechatConfig() {
	}

	public String getAppID() {
		return appID;
	}

	public String getMchID() {
		return mchID;
	}

	public String getKey() {
		return key;
	}

	public InputStream getCertStream() {
		ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
		return certBis;
	}

	public int getHttpConnectTimeoutMs() {
		return 8000;
	}

	public int getHttpReadTimeoutMs() {
		return 10000;
	}

}
