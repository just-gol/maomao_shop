package com.maomao.ssm.yspay.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * 加载配置文件根据配置文件,赋值到常量中
 */
public class YspayConfig {
	private static final String absolutePathOFF = "off"; // 绝对路径关闭
	private static Properties p = new Properties();
	private static String fixPath;
	private static String OS = System.getProperty("os.name").toLowerCase();

	static {
		try {
			fixPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			fixPath = URLDecoder.decode(fixPath, "utf-8");
			if (fixPath.startsWith("/") && OS.indexOf("linux") < 0 // &&
																	// OS.indexOf("mac")
																	// > 0
																	// 把文件的开始符号/截取掉了，导致找不到文件

			) {

				fixPath = fixPath.replace('/', '\\'); // 将/换成\
				fixPath = fixPath.replace("file:", ""); // 去掉file:
				fixPath = fixPath.replace("classes\\", ""); // 去掉class\
				fixPath = fixPath.substring(1);
			}
			// String propertiesPath = fixPath + propertiesFileName;
			// YspayCore
			// .debug("======================银盛支付配置文件必须存在应用服务运行时的WEB-INF\\classes指定目录中======================");
			// YspayCore.debug("银盛支付配置文件路径" + propertiesPath);
			// InputStream inputStream = new FileInputStream(propertiesPath);
			
			// 使用API内配置文件方式
			String filePath = YspayConfig.class.getResource("/").getPath();
			InputStream inputStream = new FileInputStream(filePath + File.separator +"yspay"+File.separator + "Yspay_Merchant.properties");BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			p.load(bf);
			inputStream.close();

		} catch (Throwable e) {
			throw new RuntimeException("加载银盛支付配置文件失败", e);
		}
	}

	public static final String absolutePathFlag = p.getProperty("absolutePathFlag");

	// 使用商户自己的私钥签名请求时，采用的字符编码
	public static final String DEFAULT_CHARSET = getAbsolutePath(p.getProperty("DEFAULT_CHARSET"));
	// 合作商家私钥pkcs12证书密码
	public static final String PASSWORD_PARTNER_PKCS12 = getAbsolutePath(p.getProperty("PASSWORD_PARTNER_PKCS12"));
	// rsa算法名
	public static final String RSA_ALGORITHM = getAbsolutePath(p.getProperty("RSA_ALGORITHM"));
	// 合作商家私钥pkcs12证书路径
	public static final String PATH_PARTER_PKCS12 = getAbsolutePath(p.getProperty("PATH_PARTER_PKCS12"));
	// 银盛公钥pkcs12证书路径
	public static final String PATH_YSEPAY_PUBLIC_CERT = getAbsolutePath(p.getProperty("PATH_YSEPAY_PUBLIC_CERT"));
	// 银盛支付接入网关url
	public static final String YSEPAY_GATEWAY_URL = getAbsolutePath(p.getProperty("YSEPAY_GATEWAY_URL"));
	// 代付url
	public static final String YSEPAY_GATEWAY_URL_DF = getAbsolutePath(p.getProperty("YSEPAY_GATEWAY_URL_DF"));
	// 代收url
	public static final String YSEPAY_GATEWAY_URL_DS = getAbsolutePath(p.getProperty("YSEPAY_GATEWAY_URL_DS"));
	// 版本号
	public static final String VERSION = getAbsolutePath(p.getProperty("VERSION"));
	// 签名算法
	public static final String SIGN_ALGORITHM = getAbsolutePath(p.getProperty("SIGN_ALGORITHM"));
	// 银盛支付服务器主动通知商户网站里指定的页面http路径。
	public static final String NOTIFY_URL = getAbsolutePath(p.getProperty("NOTIFY_URL"));
	// 银盛支付处理完请求后，当前页面自动跳转到商户网站里指定页面的http路径。
	public static final String RETURN_URL = getAbsolutePath(p.getProperty("RETURN_URL"));
	// 商户号
	public static final String PARTNER_ID = getAbsolutePath(p.getProperty("PARTNER_ID"));
	//商户名
	public static final String SELLER_NAME = getAbsolutePath(p.getProperty("SELLER_NAME"));

	/**
	 * 根据相对路径获取绝对路径
	 * 
	 * @param path
	 *            相对路径(相对与classes目录) File.separator 在windows环境下会出问题
	 * @return
	 */
	private static String getAbsolutePath(String path) {
		if (absolutePathOFF.equals(absolutePathFlag)) { // 相对路径
			String outPath = fixPath;
			path = path.replaceAll("\\\\", "/");
			path = path.replaceAll("/\\./", "/");
			if (path.startsWith("./"))
				path = path.substring(2);
			if (path.startsWith("/"))
				path = path.substring(1);
			if (path.startsWith("../")) {
				String[] inStr = path.split("\\.\\./");
				int count = inStr.length - 1;// 向上多少级目录
				// String[] fixStr = fixPath.split(File.separator);
				int i = 0;
				while (i <= count) {
					int lastFirst = outPath.lastIndexOf('/');
					outPath = outPath.substring(0, lastFirst);
					i++;
				}
				outPath = outPath + "/" + inStr[count];
			} else {
				outPath = outPath + path;
			}
			return outPath;
		}
		return path;
	}
	
	public static void main(String[] args) {
		System.out.println(YspayConfig.DEFAULT_CHARSET);
	}
}
