package com.maomao.ssm.pojo.user;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.maomao.ssm.bean.User;

public class UserDetail implements Serializable {
	private String mobile;
	private String name;
	private String idCard;
	private Integer passwordWithdrawals;

	public UserDetail() {
		super();
	}

	public UserDetail(User user) {
		super();
		this.mobile = user.getMobile();
		this.name = user.getName();
		this.idCard = user.getIdCard();
		this.passwordWithdrawals = StringUtils.isBlank(user.getPasswordWithdrawals()) ? 0 : 1;
	}

	public UserDetail(String mobile, String name, String idCard, Integer passwordWithdrawals) {
		super();
		this.mobile = mobile;
		this.name = name;
		this.idCard = idCard;
		this.passwordWithdrawals = passwordWithdrawals;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getPasswordWithdrawals() {
		return passwordWithdrawals;
	}

	public void setPasswordWithdrawals(Integer passwordWithdrawals) {
		this.passwordWithdrawals = passwordWithdrawals;
	}

}
