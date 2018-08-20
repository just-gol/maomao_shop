package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class AdminMonthDetailCustom implements Serializable {
	private Integer id;
	private Integer adminId;
	private String mobile;
	private String name;
	private String idCard;
	private Long loanChange;
	private String bankAccount;
	private String bankName;
	private String bankUser;
	private Long moneyChange;
	private String repaymentBankAccount;
	private String repaymentBankName;
	private String repaymentBankUser;
	private Date uploadTime;
	private String loanImg;
	private String moneyImg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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

	public Long getLoanChange() {
		return loanChange;
	}

	public void setLoanChange(Long loanChange) {
		this.loanChange = loanChange;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankUser() {
		return bankUser;
	}

	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}

	public Long getMoneyChange() {
		return moneyChange;
	}

	public void setMoneyChange(Long moneyChange) {
		this.moneyChange = moneyChange;
	}

	public String getRepaymentBankAccount() {
		return repaymentBankAccount;
	}

	public void setRepaymentBankAccount(String repaymentBankAccount) {
		this.repaymentBankAccount = repaymentBankAccount;
	}

	public String getRepaymentBankName() {
		return repaymentBankName;
	}

	public void setRepaymentBankName(String repaymentBankName) {
		this.repaymentBankName = repaymentBankName;
	}

	public String getRepaymentBankUser() {
		return repaymentBankUser;
	}

	public void setRepaymentBankUser(String repaymentBankUser) {
		this.repaymentBankUser = repaymentBankUser;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getLoanImg() {
		return loanImg;
	}

	public void setLoanImg(String loanImg) {
		this.loanImg = loanImg;
	}

	public String getMoneyImg() {
		return moneyImg;
	}

	public void setMoneyImg(String moneyImg) {
		this.moneyImg = moneyImg;
	}

}
