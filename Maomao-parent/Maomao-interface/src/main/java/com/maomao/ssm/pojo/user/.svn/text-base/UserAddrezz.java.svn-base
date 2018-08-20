package com.maomao.ssm.pojo.user;

import java.io.Serializable;

import com.maomao.ssm.bean.UserAddress;

public class UserAddrezz implements Serializable {
	private Integer addressId;
	private String name;
	private String mobile;
	private String province;
	private String city;
	private String area;
	private String address;
	private Integer isDefault;

	public UserAddrezz() {
		super();
	}

	public UserAddrezz(UserAddress userAddress) {
		super();
		this.addressId = userAddress.getId();
		this.name = userAddress.getName();
		this.mobile = userAddress.getMobile();
		this.province = userAddress.getProvince();
		this.city = userAddress.getCity();
		this.area = userAddress.getArea();
		this.address = userAddress.getAddress();
		this.isDefault = userAddress.getDefaultAddress().intValue();
	}

	public UserAddrezz(Integer addressId, String name, String mobile, String province, String city, String area,
			String address, Integer isDefault) {
		super();
		this.addressId = addressId;
		this.name = name;
		this.mobile = mobile;
		this.province = province;
		this.city = city;
		this.area = area;
		this.address = address;
		this.isDefault = isDefault;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

}
