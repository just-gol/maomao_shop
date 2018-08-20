package com.maomao.ssm.service;

import com.maomao.ssm.pojo.JsonData;

/** 
* @author:wzy
* @descrption:
* @version:
* @date:2018年1月26日
*/

public interface UserMessageService {
	JsonData getUserMessage(Integer userId,Integer type,Integer pages,Integer rows);
	JsonData getMessageDetail(Integer userId,Integer id);
	JsonData delMessageById(Integer userId,Integer id,Byte type);
	JsonData saveMessageReadAll(Integer userId,Byte type);
	JsonData getUnreadMessageTag(Integer userId);
}
