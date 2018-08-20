package com.maomao.ssm.pojo.user;

import java.io.Serializable;

import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserAddress;

public class UserHome implements Serializable {
	private Integer img;
	private String mobile;
	private String address;
	private Integer orderNum;
	private Integer couponNum;
	private Integer collectionNum;
	private Integer orderCodeGoodsInfoNum;
	private String invitation;

	public UserHome() {
		super();
	}

	public UserHome(User user, UserAddress userAddress, Integer orderNum, Integer couponNum, Integer collectionNum,
			Integer orderCodeGoodsInfoNum) {
		super();
		this.img = user.getImg().intValue();
		this.mobile = user.getMobile();
		this.address = userAddress == null ? null
				: userAddress.getProvince() + userAddress.getCity() + userAddress.getArea() + userAddress.getAddress();
		this.orderNum = orderNum;
		this.couponNum = couponNum;
		this.collectionNum = collectionNum;
		this.orderCodeGoodsInfoNum = orderCodeGoodsInfoNum;
		this.invitation = user.getInvitation();
	}

	public UserHome(Integer img, String mobile, String address, Integer orderNum, Integer couponNum,
			Integer collectionNum, String invitation, Integer orderCodeGoodsInfoNum) {
		super();
		this.img = img;
		this.mobile = mobile;
		this.address = address;
		this.orderNum = orderNum;
		this.couponNum = couponNum;
		this.collectionNum = collectionNum;
		this.invitation = invitation;
		this.orderCodeGoodsInfoNum = orderCodeGoodsInfoNum;
	}

	public Integer getImg() {
		return img;
	}

	public void setImg(Integer img) {
		this.img = img;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}

	public Integer getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}

	public String getInvitation() {
		return invitation;
	}

	public void setInvitation(String invitation) {
		this.invitation = invitation;
	}

	public Integer getOrderCodeGoodsInfoNum() {
		return orderCodeGoodsInfoNum;
	}

	public void setOrderCodeGoodsInfoNum(Integer orderCodeGoodsInfoNum) {
		this.orderCodeGoodsInfoNum = orderCodeGoodsInfoNum;
	}

}
