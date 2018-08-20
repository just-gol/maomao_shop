package com.maomao.ssm.pojo.search;

import java.io.Serializable;

import com.maomao.ssm.bean.GoodsSku;

public class GoodsDetailsSkuDetails implements Serializable {
	private String prop;
	private Integer skuId;
	private Long priceSales;
	private Integer stock;
	private Integer sales;

	public GoodsDetailsSkuDetails() {
		super();
	}

	public GoodsDetailsSkuDetails(String prop, GoodsSku goodSku) {
		super();
		this.prop = prop;
		this.skuId = goodSku.getId();
		this.priceSales = goodSku.getPriceSales();
		this.stock = goodSku.getStock();
		this.sales = goodSku.getSales() + goodSku.getSalesSham();
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public Long getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Long priceSales) {
		this.priceSales = priceSales;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

}
