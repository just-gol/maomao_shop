package com.maomao.ssm.listener;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @descrption:发送短信监听服务
*/

public class SendSmsCodeListener implements MessageListener{
	private static Logger logger = LoggerFactory.getLogger(SendSmsCodeListener.class);
	
	public void onMessage(Message message) {
		if (message instanceof MapMessage) {
			MapMessage msg = (MapMessage) message;
			try {
				String mobile = msg.getString("mobile");
				String content = msg.getString("content");
				//TODO
				//调用短信发送服务
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("短信发送失败"+e.getMessage());
			} 
		}
	}
}





























