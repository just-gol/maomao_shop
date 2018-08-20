package com.maomao.ssm.wxpay;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

public class MyWxpayConfig implements WXPayConfig {

	private byte[] certData;

	private String appID = "wx339229a323638c03";
	private String mchID = "1505447521";
	private String key = "IuPrNYYE5WKnp5Mksx1baPiJX8jUR6Ng";

	public MyWxpayConfig(String certPath) throws Exception {
		File file = new File(certPath);
		InputStream certStream = new FileInputStream(file);
		this.certData = new byte[(int) file.length()];
		certStream.read(this.certData);
		certStream.close();
	}

	public MyWxpayConfig() {
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
