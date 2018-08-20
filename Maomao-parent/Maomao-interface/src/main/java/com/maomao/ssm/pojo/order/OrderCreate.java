package com.maomao.ssm.pojo.order;

import java.io.Serializable;

import com.maomao.ssm.bean.GoodsOrder;

public class OrderCreate implements Serializable{
	private static final long serialVersionUID = 1696773858356344746L;
	
	private Integer orderId;
	private String orderNum;
	private Long money;

	public OrderCreate() {
		super();
	}

	public OrderCreate(GoodsOrder goodsOrder) {
		super();
		this.orderId = goodsOrder.getId();
		this.orderNum = goodsOrder.getOrderNum();
		this.money = goodsOrder.getMoney();
	}

	public OrderCreate(Integer orderId, String orderNum, Long money) {
		super();
		this.orderId = orderId;
		this.orderNum = orderNum;
		this.money = money;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

}
