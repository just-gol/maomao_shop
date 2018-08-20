package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.Date;

import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserApply;

/**
 * 开店申请详情
 * @author Administrator
 *
 */
public class UserApplyDetail implements Serializable{
	private String name; //姓名
	private String mobile; //手机号(登陆账号)
	private String idCard; //身份证
	private String bankMobile; //联系号码
	private String bankAccount; //开户账号
	private String bankName;
	private String bankUsername;
	private Date createTime;
	public UserApplyDetail() {
	}
	public UserApplyDetail(UserApply apply , User user) {
		super();
		this.name = apply.getName();
		this.mobile = user.getMobile();
		this.idCard = apply.getIdCard();
		this.bankMobile = apply.getBankMobile();
		this.bankAccount = apply.getBankAccount();
		this.bankName = apply.getBankName();
		this.bankUsername = apply.getBankUsername();
		this.createTime = apply.getCreateTime();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getBankMobile() {
		return bankMobile;
	}
	public void setBankMobile(String bankMobile) {
		this.bankMobile = bankMobile;
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
	public String getBankUsername() {
		return bankUsername;
	}
	public void setBankUsername(String bankUsername) {
		this.bankUsername = bankUsername;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
