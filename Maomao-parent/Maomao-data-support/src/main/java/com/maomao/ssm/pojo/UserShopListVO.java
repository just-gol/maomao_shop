package com.maomao.ssm.pojo;

import java.io.Serializable;

import com.maomao.ssm.bean.User;

public class UserShopListVO implements Serializable {
	private Integer userId;
	private String name;
	private String mobile;
	private String idCard;
	private Long creditSurplus;
	private Integer onSalesNum;
	private Long salesMoney;
	private Long refundMoney;

	public UserShopListVO() {
		super();
	}

	public UserShopListVO(User user, Integer onSalesNum, Long salesMoney, Long refundMoney) {
		super();
		this.userId = user.getId();
		this.name = user.getName();
		this.mobile = user.getMobile();
		this.idCard = user.getIdCard();
		this.creditSurplus = user.getCreditSurplus();
		this.onSalesNum = onSalesNum;
		this.salesMoney = salesMoney;
		this.refundMoney = refundMoney;
	}

	public UserShopListVO(Integer userId, String name, String mobile, String idCard, Long creditSurplus,
			Integer onSalesNum, Long salesMoney, Long refundMoney) {
		super();
		this.userId = userId;
		this.name = name;
		this.mobile = mobile;
		this.idCard = idCard;
		this.creditSurplus = creditSurplus;
		this.onSalesNum = onSalesNum;
		this.salesMoney = salesMoney;
		this.refundMoney = refundMoney;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Long getCreditSurplus() {
		return creditSurplus;
	}

	public void setCreditSurplus(Long creditSurplus) {
		this.creditSurplus = creditSurplus;
	}

	public Integer getOnSalesNum() {
		return onSalesNum;
	}

	public void setOnSalesNum(Integer onSalesNum) {
		this.onSalesNum = onSalesNum;
	}

	public Long getSalesMoney() {
		return salesMoney;
	}

	public void setSalesMoney(Long salesMoney) {
		this.salesMoney = salesMoney;
	}

	public Long getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Long refundMoney) {
		this.refundMoney = refundMoney;
	}

}
