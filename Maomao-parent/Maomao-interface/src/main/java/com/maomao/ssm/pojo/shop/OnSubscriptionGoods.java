package com.maomao.ssm.pojo.shop;

import java.io.Serializable;

/** 
* @author:wzy
* @descrption:认筹中商品
* @version:
* @date:2018年1月29日
*/

public class OnSubscriptionGoods implements Serializable{
	private byte type;
	private Integer id;//认筹商品id
	private String img;
	private String name;
	private Long subscriptionPrice;//商品认筹价
	private Long myPrice;//我的认筹金额
	
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSubscriptionPrice() {
		return subscriptionPrice;
	}
	public void setSubscriptionPrice(Long subscriptionPrice) {
		this.subscriptionPrice = subscriptionPrice;
	}
	public Long getMyPrice() {
		return myPrice;
	}
	public void setMyPrice(Long myPrice) {
		this.myPrice = myPrice;
	}
	
}


































