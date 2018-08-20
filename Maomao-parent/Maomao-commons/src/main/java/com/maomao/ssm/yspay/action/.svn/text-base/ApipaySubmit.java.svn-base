package com.maomao.ssm.yspay.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;

import com.maomao.ssm.yspay.config.YspayConfig;
import com.maomao.ssm.yspay.utils.Https;
import com.maomao.ssm.yspay.utils.SignUtils;

/**
 * API主入口根据bean传值,取值,遍历签名,验签 最终生成返回
 * 
 * @author chang
 *
 */
public class ApipaySubmit {

	/**
	 * API验证签名工具，把签名值，请求字符编码，返回结果(json body)传递过来进行验证签名 公钥验证签名 用于验证银盛同步响应回来的参数
	 * 
	 * @param request
	 * @param sign
	 * @param responseBody
	 * @param charset
	 * @return
	 * @see
	 */
	public static boolean verifyJsonSign(HttpServletRequest request, String sign, String responseBody, String charset) {
		ServletContext servletContext = request.getServletContext();
		InputStream publicCertFileInputStream = servletContext.getResourceAsStream(YspayConfig.PATH_YSEPAY_PUBLIC_CERT);

		boolean isSign = false;
		try {
			isSign = SignUtils.rsaCheckContent(publicCertFileInputStream, responseBody, sign, charset);
		} catch (Exception e) {
			throw new RuntimeException("验证签名失败，请检查银盛公钥证书文件是否存在");
		}
		return isSign;
	}

	public static boolean verifyJsonSign(String sign, String responseBody, String charset) {
		File file = new File(YspayConfig.PATH_YSEPAY_PUBLIC_CERT);
		InputStream publicCertFileInputStream = null;

		try {
			publicCertFileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean isSign = false;
		try {
			isSign = SignUtils.rsaCheckContent(publicCertFileInputStream, responseBody, sign, charset);
		} catch (Exception e) {
			throw new RuntimeException("验证签名失败，请检查银盛公钥证书文件是否存在");
		}
		return isSign;
	}

	/**
	 * 拼接请求网关参数
	 * 
	 * @param request
	 * @param sParaTemp
	 * @param strMethod
	 * @param strButtonName
	 * @return
	 */
	public static String buildRequest(HttpServletRequest request, Object obj) {
		Map<String, String> sPara = buildRequestPara(request, getProperty(obj));
		// System.out.println("--打印所有参数--"+sPara.toString());
		List<String> keys = new ArrayList<String>(sPara.keySet());

		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append("正在跳转。。。<br/>" + "<form id=\"ysepaysubmit\" name=\"ysepaysubmit\" action=\""
				+ YspayConfig.YSEPAY_GATEWAY_URL + "\" method = \"" + "post" + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = keys.get(i);
			String value = sPara.get(name);

			sbHtml.append("<input type=\"text\" name=\"" + name + "\" value=\"" + StringEscapeUtils.escapeHtml4(value)
					+ "\"/><br/>");
		}

		sbHtml.append("<input type=\"submit\" value=\"" + "确定" + "\" style=\"display;\"></form>");
		sbHtml.append("<script>document.forms['ysepaysubmit'].submit();</script>");

		return sbHtml.toString();
	}
	
	/**
	 * 拼接请求网关参数
	 * @param request
	 * @param obj
	 * @return
	 */
	public static Map<String, String> buildRequestSdk(HttpServletRequest request, Object obj) {
		Map<String, String> sPara = buildRequestPara(request, getProperty(obj));
		return sPara;
	}

	/**
	 * 拼接请求代付参数
	 * 
	 * @param request
	 * @param sParaTemp
	 * @return
	 */
	public static String buildRequestdf(HttpServletRequest request, Object obj) {

		Map<String, String> sPara = buildRequestPara(request, getProperty(obj));
		List<String> keys = new ArrayList<String>(sPara.keySet());
		// System.out.println("--打印所有参数--"+sPara.toString());
		StringBuffer sbHtml = new StringBuffer();
		sbHtml.append("正在跳转。。。<br/>" + "<form id=\"ysepaysubmit\" name=\"ysepaysubmit\" action=\""
				+ YspayConfig.YSEPAY_GATEWAY_URL_DF + "\" method = \"" + "post" + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);

			sbHtml.append("<input type=\"text\" name=\"" + name + "\" value=\"" + StringEscapeUtils.escapeHtml4(value)
					+ "\"/><br/>");
		}

		sbHtml.append("<input type=\"submit\" value=\"" + "确定" + "\" style=\"display;\"></form>");
		sbHtml.append("<script>document.forms['ysepaysubmit'].submit();</script>");

		return sbHtml.toString();

	}

	/**
	 * 拼接请求代收参数
	 * 
	 * @param request
	 * @param sParaTemp
	 * @return
	 */
	public static String buildRequestds(HttpServletRequest request, Object obj) {

		Map<String, String> sPara = buildRequestPara(request, getProperty(obj));
		List<String> keys = new ArrayList<String>(sPara.keySet());
		// System.out.println("--打印所有参数--"+sPara.toString());
		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append("正在跳转。。。<br/>" + "<form id=\"ysepaysubmit\" name=\"ysepaysubmit\" action=\""
				+ YspayConfig.YSEPAY_GATEWAY_URL_DS + "\" method = \"" + "post" + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);

			sbHtml.append("<input type=\"text\" name=\"" + name + "\" value=\"" + StringEscapeUtils.escapeHtml4(value)
					+ "\"/><br/>");
		}

		sbHtml.append("<input type=\"submit\" value=\"" + "确定" + "\" style=\"display;\"></form>");
		sbHtml.append("<script>document.forms['ysepaysubmit'].submit();</script>");

		return sbHtml.toString();
	}

	/**
	 * 获取证书路径并且签名
	 * 
	 * @param request
	 * @param sParaTemp
	 * @return
	 */
	private static Map<String, String> buildRequestPara(HttpServletRequest request, Map<String, String> sParaTemp) {

		// 除去数组中的空值和签名参数
		Map<String, String> sPara = SignUtils.paraFilter(sParaTemp);

		// ServletContext servletContext = request.getServletContext();

		// 私钥证书路径
		String partnerCert = YspayConfig.PATH_PARTER_PKCS12;

		// 读取证书
		// InputStream pfxCertFileInputStream =
		// servletContext.getResourceAsStream(partnerCert);

		// 通过file读取证书
		File file = new File(partnerCert);
		InputStream pfxCertFileInputStream = null;
		try {
			pfxCertFileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String mysign = "";
		try {
			// 遍历以及根据重新排序
			String signContent = SignUtils.getSignContent(sPara);

			mysign = SignUtils.rsaSign(signContent, sParaTemp.get("charset"), pfxCertFileInputStream);

		} catch (Exception e) {
			throw new RuntimeException("签名失败，请检查证书文件是否存在，密码是否正确");
		}

		sPara.put("sign", mysign);

		return sPara;
	}

	/**
	 * 异步验证签名
	 * 
	 * @param request
	 * @param params
	 * @return
	 */
	public static boolean verifySign(HttpServletRequest request, Map<String, String> params) {
		ServletContext servletContext = request.getServletContext();
		InputStream publicCertFileInputStream = servletContext.getResourceAsStream(YspayConfig.PATH_YSEPAY_PUBLIC_CERT);
		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}
		boolean isSign = false;
		try {
			isSign = SignUtils.rsaCheckContent(publicCertFileInputStream, params, sign, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException("验证签名失败，请检查银盛公钥证书文件是否存在");
		}

		return isSign;
	}

	public static boolean verifySign(Map<String, String> params) {

		File file = new File(YspayConfig.PATH_YSEPAY_PUBLIC_CERT);
		InputStream publicCertFileInputStream = null;

		try {
			publicCertFileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}
		boolean isSign = false;
		try {
			isSign = SignUtils.rsaCheckContent(publicCertFileInputStream, params, sign, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException("验证签名失败，请检查银盛公钥证书文件是否存在");
		}

		return isSign;
	}

	/**
	 * send发送
	 * 
	 * @param request
	 * @param obj
	 * @return
	 */
	public static String backgroundURL(HttpServletRequest request, Object obj) {

		Map<String, String> sPara = buildRequestPara(request, getProperty(obj));

		try {
			String resText = Https.httpsSend("https://openapi.ysepay.com/gateway.do", sPara);

			return resText;
		} catch (Exception e) {
			throw new RuntimeException("支付发送网络异常" + e.getMessage());
		}
	}

	/**
	 * 取对应的key和value
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, String> getProperty(Object obj) {

		Map<String, String> map = null;
		try {
			Field fields[] = obj.getClass().getDeclaredFields();
			String[] name = new String[fields.length];
			Object[] value = new Object[fields.length];
			Field.setAccessible(fields, true);
			map = new HashMap<String, String>();
			for (int i = 0; i < name.length; i++) {
				name[i] = fields[i].getName();
				value[i] = fields[i].get(obj);

				if (value[i] == null || name[i] == null || "serialVersionUID".equals(name[i])) {
					continue;
				}

				map.put(name[i], String.valueOf(value[i]));
			}
		} catch (Exception e) {
			throw new RuntimeException("获取bean属性值异常:" + e.getMessage());
		}
		return map;
	}
}
