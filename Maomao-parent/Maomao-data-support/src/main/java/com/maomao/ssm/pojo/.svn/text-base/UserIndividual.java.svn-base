package com.maomao.ssm.pojo;

import java.io.Serializable;

import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserBank;

/**
 * 个人用户
 * 
 * @author Administrator
 *
 */
public class UserIndividual implements Serializable {
	private Integer userBankId;
	private Integer userId;
	private String mobile;
	private String password;
	private String name;//姓名
	private String idCard;//用户身份证
	private String account; // 打款账户
	private String bankName; // 打款开户行名称
	private String bankUserName; // 打款户名
	private String[] invitation; // 邀请用户
	public UserIndividual() {
		super();
	}
	public UserIndividual(User user){
		this.userId = user.getId();
		this.name = user.getName();
		this.mobile = user.getMobile();
		this.idCard = user.getIdCard();
	}
	public UserIndividual(User user,UserBank userBank) {
		super();
		this.userId = user.getId();
		this.userBankId = userBank.getId();
		this.mobile = user.getMobile();
		this.password = user.getPassword();
		this.name = user.getName();
		this.idCard = user.getIdCard();
		this.account = userBank.getAccount();
		this.bankName = userBank.getBankName();
		this.bankUserName = userBank.getUserName();
		this.invitation = user.getInvitation()==null?null:user.getInvitation().split(";");
	}

	public Integer getUserBankId() {
		return userBankId;
	}
	public void setUserBankId(Integer userBankId) {
		this.userBankId = userBankId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankUserName() {
		return bankUserName;
	}
	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}
	public String[] getInvitation() {
		return invitation;
	}
	public void setInvitation(String[] invitation) {
		this.invitation = invitation;
	}
	
}
