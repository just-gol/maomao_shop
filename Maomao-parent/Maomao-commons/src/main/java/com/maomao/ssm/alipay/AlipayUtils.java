package com.maomao.ssm.alipay;

import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;

public class AlipayUtils {

	private static final String APP_ID = "2018022702282038";
	private static final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC5atoO6jllfOBWbFnpLgRLL7az1+atlyaVzCkK36puWgUFEavD3rpTVpC4jVfj4IFbDMmvjHLd1GjhSL8SqIbHiKOdM0xSbnw4/ur+Wa7LM+mLfI8eblpUOtbTf0YJ3FWD1UV6I3Ya/NfGXi5Z8Ik8VSs03uTqiD8mO+8OKP/zTLqqIDNfglT382Rerc3L4Y6efU7N0ZFO9ZbCI5KkF/T27oWy+fvXcEyW+MHtZDNbXPhSHjFzN/WIZLXwyc6qDgFrOTnOyL43KzouKNGHn8N/RHCD6Yy1+9nBkn7RxgxHAbgCRFyZdpNlNz/Eb9FD41PLxdiwLmVu1TyEn6+rvxXjAgMBAAECggEAWgoSZCXHw4nVowIw0zQ+b4h3D3Rrl6aL3ThsQdSFfdsHDwQaxh4VmzyxQH2azZULG/zkv/PTH/fzClt6NgRSlvi6gy8TBACVIFRiJyvD3veRlOPi2CM8iCSeh8wJZeZyPHfHql0oEEjyO2km1xQggITXW3BV97I8AEQgfV7Zk/q8m7x/oibqL6Na7R9gUFlWIDs7HJGwLuQamYxejDcqEZweM95GJzIYikOi33IqnIuLBDrpjL0JvCx+p124cJf/OX/7Sc+UxRJqBHTUfWh5ntCmAZU/42tW6nKIG3s/T3fHzE8NSAEHrGhj2eMJiMS64fwDJVx3FJq64WfSZ0SRkQKBgQD1dpST/cdAYeu6eZB0UKgRZEmCVkdS6RdqG30rICvlvnP5LjCx01FY1aMEG7iOo65L6ewQrWGLDEoGmkOMgLkEBZlj1RoGG5iLqR2Nc3ZIIlLXgaLM5ELN7ZZbc0mJn8TnV+7pMIzJlADpqFXkOYQi1Tnmmf+iZpo1f3Fpz1FbKQKBgQDBYGwF10PhwGrbi/PfB6a9I+z39mEXuc36WL6E+9ou9LsQLs7rdIiewm9/StxlyJ0Z0r0xvYobWrY3hAzoinOdYUE7b5OGm5dzelSkxg4fwXmyBhpuYGGUyWlY0kCsPCWtrmvdW1UdJkLHQRrCVibzUwNfrMseDKHVcawllG1WKwKBgQDG0GcDfzfV4/okrbXdID8g5xPLJ8rZUsaOQPme5o3npd0VZN44CisHQvKorzNLBTR1dGCqGWS6VRfYRTaVJOW2ZjqZa8+KRuvFoxK2sWCKiJKEzxFn1zJszRVNuRFFEU1Q/B4cbiB6XRBoMNpw4k0uUXp2VyGBDo206yaXRz5kEQKBgD2Rd5M8gI5l9x/DpXfDNeFOiOpB9ZPaQqyMiMxKa6M7pDt4y8THq5/whBNhQF7fyrUoFbi6gjhHX3C5iGqZekoHZv+HfM8ExumrbzuvG1wc+6QksVGnOipnHjV7a7/qvoxX1lv58lV/kw9huSVazLf4HEDgASOmHfiZYpfsdAtVAoGBAKwwxpU0BGIxmwYMJ+RvsJ5TS8VpPCulcDHj9NaXpbDtBlhkKW5+hiPkYeIA5Nfhca7pbBw2sDwl6SXv6mjGAYqUcCLawWLdCRC/AtJhyWDPq6NlOftu6EppAWodKeHQ3vrpMSdvJ/06hU1mgMsTIN5xI7aBiUHWQKaS1WHu5l3i";
	private static final String CHARSET = "utf-8";
	private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtBDvZc2a+qcV2VhprRtdhJvRhYEdurtOwuveSoU7mL1q8FIKb7+n5yjqA01ahcYWv0ceVDB60I7Vdmp3p7qh/lsEUnal8KG3K6i3AgeQnLhb2640u13qtzpzL9bRCv5jSrFTf2By6sltsO3mlXq/YrrU+1cjg4td0rZltWqPOcmkRw6HfV+2O4TuOcyfWGx11i9TrsNkmIg7pQ3DTO7FLeK6CmpSp8ES3i/ntwWSw4561D6Dw4EuYaFMHGcjzsjdjP4ipwWoQcGYV3oWxt0PKPsiNHWa4ArAfmDs4WgtJcIBcrps0Fuwr/wB//ZVQv5Y38JqaNaOnnolIEOnwWKzLQIDAQAB";
	private String notifyUrl;
	private String returnUrl;

	public String getAppOrderString(String outtradeno, String goodsName, String money) throws AlipayApiException {
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID,
				APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(goodsName);
		model.setSubject(goodsName);
		model.setOutTradeNo(outtradeno);
		model.setTimeoutExpress("30m");
		model.setTotalAmount(money);
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		// 这里和普通的接口调用不同，使用的是sdkExecute
		AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
		System.out.println(response.getBody());// 就是orderString 可以直接给客户端请求，无需再做处理。
		return response.getBody();
	}

	public static boolean validateAlipayInfo(Map<String, String> params) throws AlipayApiException {
		// 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
		// publicKey, String charset, String sign_type)
		boolean flag = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, "RSA2");
		return flag;
	}

	public String getWapOrderString(String outtradeno, String goodsName, String money)
			throws AlipayApiException {
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID,
				APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(goodsName);
		model.setSubject(goodsName);
		model.setOutTradeNo(outtradeno);
		model.setTimeoutExpress("30m");
		model.setTotalAmount(money);
		model.setProductCode("QUICK_WAP_WAY");
		request.setBizModel(model);
		request.setReturnUrl(returnUrl);
		request.setNotifyUrl(notifyUrl);
		// 这里和普通的接口调用不同，使用的是sdkExecute
		AlipayTradeWapPayResponse response = alipayClient.pageExecute(request);
		System.out.println(response.getBody());// 就是orderString 可以直接给客户端请求，无需再做处理。
		return response.getBody();
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

	public static void main(String[] args) throws AlipayApiException {
		new AlipayUtils().getAppOrderString("111", "111", "111");
	}
}
