package com.maomao.ssm.exception;

public class DataException extends Exception {

	private static final long serialVersionUID = -3842301557253546826L;
	
	private String message;

	public DataException(String message) {
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
