package com.test;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JsonData implements Serializable{
	//响应业务状态码
	private Integer code;
	
	//响应中的数据
	private Object data;
	
	//响应中的消息
	private String message;
	
	
	public JsonData(Integer code, Object data, String message) {
		this.code = code;
		this.data = data;
		this.message = message;
	}
	public JsonData(){
		
	}
	
	public static JsonData setUndefinedMessage(Integer code, Object data, String message) {
		return new JsonData(code, data, message);
	}

	public static JsonData setErrorMessage(Object data, String message) {
		return new JsonData(500, data, message);
	}

	public static JsonData setErrorMessage(String message) {
		Map<Object,Object> map = new HashMap<Object, Object>();
		return setErrorMessage(map, message);
	}

	public static JsonData setServerErrorMessage(Object data, String message) {
		return new JsonData(300, data, message);
	}

	public static JsonData setServerErrorMessage(String message) {
		Map<Object,Object> map = new HashMap<Object, Object>();
		return setServerErrorMessage(map, message);
	}

	public static JsonData setSuccessMessage(Object data) {
		
		if (data==null ) {
			Map<Object,Object> map = new HashMap<Object, Object>();
			return new JsonData(200, map, "success");
		}
		
		
		if (data instanceof String) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("msg", data.toString());
			return new JsonData(200, map, "success");
		}
		
		if ((data instanceof Collection) && ((Collection)(data)).size()==0) {
			Map<Object,Object> map = new HashMap<Object, Object>();
			return new JsonData(200, map, "success");
		}
		
		return new JsonData(200, data, "success");
	}

	public static JsonData setSuccessMessage() {
		Map<Object,Object> map = new HashMap<Object, Object>();
		return setSuccessMessage(map);
	}

	public static JsonData setLogoutData() {
		Map<Object,Object> map = new HashMap<Object, Object>();
		return new JsonData(400, map, "logout");
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}












