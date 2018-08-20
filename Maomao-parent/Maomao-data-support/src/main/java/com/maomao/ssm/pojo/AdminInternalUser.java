package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 账号管理,趣贸后台用户显示
 * @author Administrator
 *
 */
public class AdminInternalUser implements Serializable {
	private Integer adminId; //管理员id
	private String mobile; //手机号
	private String password; //密码
	private String name; //姓名
	private String leaglPersonId; //身份证
	private Byte sex; //性别
	private List<Integer> roleList;//角色集合
	private List<Integer> dataRoleList; //数据角色id
	
	public List<Integer> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Integer> roleList) {
		this.roleList = roleList;
	}
	public List<Integer> getDataRoleList() {
		return dataRoleList;
	}
	public void setDataRoleList(List<Integer> dataRoleList) {
		this.dataRoleList = dataRoleList;
	}
	public AdminInternalUser() {
	}
	public AdminInternalUser(Integer adminId, String mobile, String password, String name, String leaglPersonId,
			Byte sex) {
		super();
		this.adminId = adminId;
		this.mobile = mobile;
		this.password = password;
		this.name = name;
		this.leaglPersonId = leaglPersonId;
		this.sex = sex;
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
	public String getLeaglPersonId() {
		return leaglPersonId;
	}
	public void setLeaglPersonId(String leaglPersonId) {
		this.leaglPersonId = leaglPersonId;
	}
	public Byte getSex() {
		return sex;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
	}

}
