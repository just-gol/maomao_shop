package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 开店申请
 * @author Administrator
 *
 */
public class ShopApplyAudit implements Serializable{
	private Integer userApplyId;//用户开店申请id
	private Date createTime; //申请时间
	private String name; //用户姓名
	private String mobile; //申请账号读取登录的手机号
	public ShopApplyAudit() {
	}
	public ShopApplyAudit(Integer userApplyId, Date createTime, String name, String mobile) {
		super();
		this.userApplyId = userApplyId;
		this.createTime = createTime;
		this.name = name;
		this.mobile = mobile;
	}
	public Integer getUserApplyId() {
		return userApplyId;
	}
	public void setUserApplyId(Integer userApplyId) {
		this.userApplyId = userApplyId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	@Override
	public String toString() {
		return "ShopApplyAudit [userApplyId=" + userApplyId + ", createTime=" + createTime + ", name=" + name
				+ ", mobile=" + mobile + "]";
	}
}
