package com.maomao.ssm.exception;

public class ServerException extends Exception {
	private static final long serialVersionUID = -7022371978652931002L;
	private String message;

	public ServerException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
