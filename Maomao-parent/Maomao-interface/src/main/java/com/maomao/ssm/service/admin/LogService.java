package com.maomao.ssm.service.admin;

import java.util.Date;

import com.maomao.ssm.pojo.JsonData;

public interface LogService {
	public void addLog(Integer adminId,String methodName,String params,String ip,Date createTime,Byte type);

	public JsonData getLogList(Integer pages, Integer rows, String keywords);
}
