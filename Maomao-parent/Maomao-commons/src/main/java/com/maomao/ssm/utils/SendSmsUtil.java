package com.maomao.ssm.utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maomao.ssm.constant.SendSmsConst;
/**
 * 
 * @author  : wzy
 * @time    : 2018年1月25日 下午4:59:15
 * @description:发送短信工具类
 * @version : 1.0
 */

public class SendSmsUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(SendSmsUtil.class);
	public static void main(String[] args) {
		sendSms("18667102905", "【茂茂集市】您的短信验证码为2333，请在3分钟内完成验证。为保证账号安全，请勿泄露此验证码。");
	}
	public static void sendSms(String mobile,String content) {
		try {
			LOGGER.info("开始发送短信,手机号={},内容={}",mobile,content);		
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("action", SendSmsConst.ACTION);
		map.put("userid", SendSmsConst.USER_ID);
		map.put("account", SendSmsConst.ACCOUNT);
		map.put("password", SendSmsConst.PASSWORD);
		map.put("mobile", mobile);
		map.put("content", content);
		map.put("sendTime", "");
		map.put("extno", "");
		String sendResult = SendSmsUtil.sendPost(SendSmsConst.URL, map);
//		System.out.println(sendResult);
		LOGGER.info("短信发送结果,手机号={},发送结果={}", mobile,sendResult);
	}
	
	
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    /**
     * 发送HttpGet请求
     */
    private static String sendGet(String url) {

        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送HttpPost请求，参数为map
     */
    private static String sendPost(String url, Map<String, String> map) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}











