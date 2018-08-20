package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.Map;

/** 
* @author:wzy
* @descrption:账号信息
* @version:
* @date:2018年4月17日
*/

public class AdminAccountInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String idCard;
	private String mobile;
	private Byte sex;//性别 1男 2女
	private String role;//角色
	private String address;//地址
	private Map<String, String> bank;
	private Map<String, String>  linkman;//联系人
	
	public Map<String, String> getBank() {
		return bank;
	}
	public void setBank(Map<String, String> bank) {
		this.bank = bank;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Map<String, String> getLinkman() {
		return linkman;
	}
	public void setLinkman(Map<String, String> linkman) {
		this.linkman = linkman;
	}
	
	

}































