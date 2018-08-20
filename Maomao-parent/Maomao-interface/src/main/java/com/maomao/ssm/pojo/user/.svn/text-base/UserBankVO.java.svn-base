package com.maomao.ssm.pojo.user;

import java.io.Serializable;

import com.maomao.ssm.bean.UserBank;

public class UserBankVO implements Serializable {
	private static final long serialVersionUID = 6770041807954051898L;
	private Integer userBankId;
	private String bankName;
	private String account;
	private String userName;
	private String accountsBank;

	public UserBankVO() {
		super();
	}

	public UserBankVO(UserBank userBank) {
		super();
		this.userBankId = userBank.getId();
		this.bankName = userBank.getBankName();
		this.account = userBank.getAccount();
		this.userName = userBank.getUserName();
		this.accountsBank = userBank.getAccountsBank();
	}

	public UserBankVO(Integer userBankId, String bankName, String account, String userName, String accountsBank) {
		super();
		this.userBankId = userBankId;
		this.bankName = bankName;
		this.account = account;
		this.userName = userName;
		this.accountsBank = accountsBank;
	}

	public Integer getUserBankId() {
		return userBankId;
	}

	public void setUserBankId(Integer userBankId) {
		this.userBankId = userBankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountsBank() {
		return accountsBank;
	}

	public void setAccountsBank(String accountsBank) {
		this.accountsBank = accountsBank;
	}

}
