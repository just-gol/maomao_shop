package com.maomao.ssm.pojo;

import java.io.Serializable;

import com.maomao.ssm.bean.User;
/**
 * 普通账户页面显示
 * @author Administrator
 *
 */
public class UserOrdinary implements Serializable{
	private Integer userId;
	private String mobile;
	private String password;
	private String userName;
	private String idCard;
	public UserOrdinary() {
	}

	public UserOrdinary(User user) {
		super();
		this.userId = user.getId();
		this.mobile = user.getMobile();
		this.userName = user.getName();
		this.idCard = user.getIdCard();
		this.password = user.getPassword();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
