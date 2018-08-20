package com.maomao.ssm.pojo.order;

import java.io.Serializable;

import com.maomao.ssm.bean.UserAddress;

public class OrderComfirm implements Serializable {
	private static final long serialVersionUID = 3342331710912451742L;

	private Integer addressId;
	private String mobile;
	private String name;
	private String address;
	private Integer couponNum;

	public OrderComfirm() {
		super();
	}
	
	public OrderComfirm(UserAddress userAddress) {
		super();
		if (userAddress != null) {
			this.addressId = userAddress.getId();
			this.mobile = userAddress.getMobile();
			this.name = userAddress.getName();
			this.address = userAddress.getProvince() + userAddress.getCity() + userAddress.getArea()
					+ userAddress.getAddress();
		}
	}

	public OrderComfirm(UserAddress userAddress, Integer couponNum) {
		super();
		if (userAddress != null) {
			this.addressId = userAddress.getId();
			this.mobile = userAddress.getMobile();
			this.name = userAddress.getName();
			this.address = userAddress.getProvince() + userAddress.getCity() + userAddress.getArea()
					+ userAddress.getAddress();
		}
		this.couponNum = couponNum;
	}

	public OrderComfirm(Integer addressId, String mobile, String name, String address, Integer couponNum) {
		super();
		this.addressId = addressId;
		this.mobile = mobile;
		this.name = name;
		this.address = address;
		this.couponNum = couponNum;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}

}
