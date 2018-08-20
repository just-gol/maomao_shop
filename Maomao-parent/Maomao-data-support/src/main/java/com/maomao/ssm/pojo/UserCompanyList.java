package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员显示
 * @author Administrator
 *
 */
public class UserCompanyList implements Serializable{
	private Integer adminId;
	private String mobile;
	private String password;
	private String name;
	private String idCard;
	private List<String> roleName;//角色集合

	public UserCompanyList() {
	}
	public Integer getAdminId() {
		return adminId;
	}
	
	public UserCompanyList(Integer adminId, String mobile, String password, String name, String idCard) {
		super();
		this.adminId = adminId;
		this.mobile = mobile;
		this.password = password;
		this.name = name;
		this.idCard = idCard;
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
	public List<String> getRoleName() {
		return roleName;
	}
	public void setRoleName(List<String> roleName) {
		this.roleName = roleName;
	}
	
}
