package com.maomao.ssm.pojo;

import java.io.Serializable;

import com.maomao.ssm.bean.GoodsOrderCustom;

public class UserShopDetailOrderListVO implements Serializable {
	private String orderNum;
	private String name;
	private Integer category;
	private Long priceSales;
	private Long rebate;
	private Byte orderSrc;

	public UserShopDetailOrderListVO() {
		super();
	}

	public UserShopDetailOrderListVO(GoodsOrderCustom goodsOrderCustom) {
		super();
		this.orderNum = goodsOrderCustom.getOrderNum();
		this.name = goodsOrderCustom.getName();
		this.category = goodsOrderCustom.getCategory();
		this.priceSales = goodsOrderCustom.getMoney();
		this.rebate = goodsOrderCustom.getRebate() * goodsOrderCustom.getNum();
		this.orderSrc = goodsOrderCustom.getOrderSrc();
	}

	public UserShopDetailOrderListVO(String orderNum, String name, Integer category, Long priceSales, Long rebate,
			Byte orderSrc) {
		super();
		this.orderNum = orderNum;
		this.name = name;
		this.category = category;
		this.priceSales = priceSales;
		this.rebate = rebate;
		this.orderSrc = orderSrc;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Long getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Long priceSales) {
		this.priceSales = priceSales;
	}

	public Long getRebate() {
		return rebate;
	}

	public void setRebate(Long rebate) {
		this.rebate = rebate;
	}

	public Byte getOrderSrc() {
		return orderSrc;
	}

	public void setOrderSrc(Byte orderSrc) {
		this.orderSrc = orderSrc;
	}

}
