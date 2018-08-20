package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.List;

import com.maomao.ssm.bean.AdminMonthDetail;
import com.maomao.ssm.bean.GoodsOrderCustom;

public class MonthDetailSalesList implements Serializable {
	private int salesNum;
	private Long loan;
	private Long money;
	private List<GoodsOrderCustom> goodsOrders;

	public MonthDetailSalesList() {
		super();
	}

	public MonthDetailSalesList(int salesNum, AdminMonthDetail adminMonthDetail, List<GoodsOrderCustom> goodsOrders) {
		super();
		this.salesNum = salesNum;
		this.loan = adminMonthDetail.getLoanChange();
		this.money = adminMonthDetail.getMoneyChange();
		this.goodsOrders = goodsOrders;
	}

	public MonthDetailSalesList(int salesNum, Long loan, Long money, List<GoodsOrderCustom> goodsOrders) {
		super();
		this.salesNum = salesNum;
		this.loan = loan;
		this.money = money;
		this.goodsOrders = goodsOrders;
	}

	public int getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(int salesNum) {
		this.salesNum = salesNum;
	}

	public Long getLoan() {
		return loan;
	}

	public void setLoan(Long loan) {
		this.loan = loan;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public List<GoodsOrderCustom> getGoodsOrders() {
		return goodsOrders;
	}

	public void setGoodsOrders(List<GoodsOrderCustom> goodsOrders) {
		this.goodsOrders = goodsOrders;
	}

}
