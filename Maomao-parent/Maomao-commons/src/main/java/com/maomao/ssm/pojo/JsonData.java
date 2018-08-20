package com.maomao.ssm.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonData implements Serializable {
	// 响应业务状态码
	private Integer code;

	// 响应中的数据
	private Object data;

	// 响应中的消息
	private String message;

	public JsonData(Integer code, Object data, String message) {
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public JsonData() {

	}

	public static JsonData setUndefinedMessage(Integer code, Object data, String message) {
		return new JsonData(code, data, message);
	}

	public static JsonData setErrorMessage(Object data, String message) {
		return new JsonData(500, data, message);
	}

	public static JsonData setErrorMessage(String message) {
		return setErrorMessage(null, message);
	}

	public static JsonData setServerErrorMessage(Object data, String message) {
		return new JsonData(300, data, message);
	}

	public static JsonData setServerErrorMessage(String message) {
		return setServerErrorMessage(null, message);
	}

	public static JsonData setSuccessMessage(Object data) {
		return new JsonData(200, data, "success");
	}

	public static JsonData setSuccessMessage() {
		return setSuccessMessage(null);
	}

	public static JsonData setLogoutData(String msg) {
		return new JsonData(400, null, msg);
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
