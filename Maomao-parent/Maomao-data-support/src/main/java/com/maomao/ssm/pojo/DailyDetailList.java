package com.maomao.ssm.pojo;

import java.io.Serializable;

public class DailyDetailList implements Serializable{
	private PageBean list;
	private Long orderPayMoneyTotal = 0l;
	private Long orderRefundMoneyTotal = 0l;
	private Long couponGetMoneyTotal = 0l;
	private Long couponUseMoneyTotal = 0l;
	private Long couponExpireMoneyTotal = 0l;

	public DailyDetailList() {
		super();
	}

	public DailyDetailList(PageBean list, Long orderPayMoneyTotal, Long orderRefundMoneyTotal, Long couponGetMoneyTotal,
			Long couponUseMoneyTotal, Long couponExpireMoneyTotal) {
		super();
		this.list = list;
		this.orderPayMoneyTotal = orderPayMoneyTotal;
		this.orderRefundMoneyTotal = orderRefundMoneyTotal;
		this.couponGetMoneyTotal = couponGetMoneyTotal;
		this.couponUseMoneyTotal = couponUseMoneyTotal;
		this.couponExpireMoneyTotal = couponExpireMoneyTotal;
	}

	public PageBean getList() {
		return list;
	}

	public void setList(PageBean list) {
		this.list = list;
	}

	public Long getOrderPayMoneyTotal() {
		return orderPayMoneyTotal;
	}

	public void setOrderPayMoneyTotal(Long orderPayMoneyTotal) {
		this.orderPayMoneyTotal = orderPayMoneyTotal;
	}

	public Long getOrderRefundMoneyTotal() {
		return orderRefundMoneyTotal;
	}

	public void setOrderRefundMoneyTotal(Long orderRefundMoneyTotal) {
		this.orderRefundMoneyTotal = orderRefundMoneyTotal;
	}

	public Long getCouponGetMoneyTotal() {
		return couponGetMoneyTotal;
	}

	public void setCouponGetMoneyTotal(Long couponGetMoneyTotal) {
		this.couponGetMoneyTotal = couponGetMoneyTotal;
	}

	public Long getCouponUseMoneyTotal() {
		return couponUseMoneyTotal;
	}

	public void setCouponUseMoneyTotal(Long couponUseMoneyTotal) {
		this.couponUseMoneyTotal = couponUseMoneyTotal;
	}

	public Long getCouponExpireMoneyTotal() {
		return couponExpireMoneyTotal;
	}

	public void setCouponExpireMoneyTotal(Long couponExpireMoneyTotal) {
		this.couponExpireMoneyTotal = couponExpireMoneyTotal;
	}

}
