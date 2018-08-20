package com.maomao.ssm.utils;

import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/** 
* @descrption:activeMQ消息发送服务
*/
//@Component
public class SendMessageUtil{
	private static JmsTemplate jmsQueueTemplate;
	
	private static JmsTemplate jmsTopicTemplate;
	
	//@Resource
	public void setJmsQueueTemplate(JmsTemplate jmsQueueTemplate) {
		this.jmsQueueTemplate = jmsQueueTemplate;
	}
	//@Resource
	public void setJmsTopicTemplate(JmsTemplate jmsTopicTemplate) {
		this.jmsTopicTemplate = jmsTopicTemplate;
	}
	//点对点发送消息
	public static  void sendMessageByQueue(String destinationName, String msg) {
		jmsQueueTemplate.send(destinationName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
	public static  void sendMessageByQueue(Destination destination, String msg) {
		jmsQueueTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
	
	public static  void sendMessageByQueue(String destinationName, Map<String,String> mapMsg) {
		jmsQueueTemplate.send(destinationName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {	
				
				MapMessage map = session.createMapMessage();
				if (mapMsg !=null && mapMsg.size()>0) {					
					for (Map.Entry<String,String> entry:mapMsg.entrySet()) {
						String key = entry.getKey();
						String value = entry.getValue();
						map.setString(key, value);
					}
				}
				
				return map;
			}
		});
	}
	
	
	//发布/订阅 模式
	public static  void sendMessageByTopic(String topicName, String msg) {
		jmsTopicTemplate.send(topicName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
	public static  void sendMessageByTopic(Destination destination, String msg) {
		jmsTopicTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
	
	public static  void sendMessageByTopic(String destinationName , Map<String,String> mapMsg) {
		jmsTopicTemplate.send(destinationName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {	
				MapMessage map = session.createMapMessage();
				if (mapMsg !=null && mapMsg.size()>0) {					
					for (Map.Entry<String,String> entry:mapMsg.entrySet()) {
						String key = entry.getKey();
						String value = entry.getValue();
						map.setString(key, value);
					}
				}
				return map;
			}
		});
	}
}

























