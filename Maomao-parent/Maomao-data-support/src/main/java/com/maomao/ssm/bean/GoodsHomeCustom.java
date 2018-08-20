package com.maomao.ssm.bean;

import java.io.Serializable;

public class GoodsHomeCustom implements Serializable {
	private static final long serialVersionUID = 2560443148242268294L;
	private Integer id;
	private Integer goodsHomepageCategoryId;
	private Integer sort;
	private Integer category;
	private Long price;
	private Integer sales;
	private Integer salesSham;
	private Long rebate;
	private String imgs;
	private String name;
	private Integer stock;
	private Long deposit;
	private String serviceId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsHomepageCategoryId() {
		return goodsHomepageCategoryId;
	}

	public void setGoodsHomepageCategoryId(Integer goodsHomepageCategoryId) {
		this.goodsHomepageCategoryId = goodsHomepageCategoryId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Long getRebate() {
		return rebate;
	}

	public void setRebate(Long rebate) {
		this.rebate = rebate;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSalesSham() {
		return salesSham;
	}

	public void setSalesSham(Integer salesSham) {
		this.salesSham = salesSham;
	}

	public Long getDeposit() {
		return deposit;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}
