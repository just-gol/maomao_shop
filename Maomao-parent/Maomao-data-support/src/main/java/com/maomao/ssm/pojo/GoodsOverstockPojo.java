package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GoodsOverstockPojo implements Serializable{
	private Integer id;

	private String name;

	private String serviceId;

	private Byte status;

	private Integer sort;

	private Integer stock;

	private Integer sales;

	private Integer salesSham;

	private Long rebate;

	private Long price;

	private Integer buyNum;
	
	private String detail;

	private String imgs;

	private String param;
	
	private String reason;
	
	private List<BuyOutGoods> GoodsOverstockOrgoodsWarehouseOrAddressList;

	public GoodsOverstockPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodsOverstockPojo(Integer id, String name, String serviceId, Byte status, Integer sort, Integer stock,
			Integer sales, Integer salesSham, Long rebate, Long price, Integer buyNum, String detail, String imgs,
			String param, String reason, List<BuyOutGoods> goodsOverstockOrgoodsWarehouseOrAddressList) {
		super();
		this.id = id;
		this.name = name;
		this.serviceId = serviceId;
		this.status = status;
		this.sort = sort;
		this.stock = stock;
		this.sales = sales;
		this.salesSham = salesSham;
		this.rebate = rebate;
		this.price = price;
		this.buyNum = buyNum;
		this.detail = detail;
		this.imgs = imgs;
		this.param = param;
		this.reason = reason;
		GoodsOverstockOrgoodsWarehouseOrAddressList = goodsOverstockOrgoodsWarehouseOrAddressList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public Integer getSalesSham() {
		return salesSham;
	}

	public void setSalesSham(Integer salesSham) {
		this.salesSham = salesSham;
	}

	public Long getRebate() {
		return rebate;
	}

	public void setRebate(Long rebate) {
		this.rebate = rebate;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<BuyOutGoods> getGoodsOverstockOrgoodsWarehouseOrAddressList() {
		return GoodsOverstockOrgoodsWarehouseOrAddressList;
	}

	public void setGoodsOverstockOrgoodsWarehouseOrAddressList(
			List<BuyOutGoods> goodsOverstockOrgoodsWarehouseOrAddressList) {
		GoodsOverstockOrgoodsWarehouseOrAddressList = goodsOverstockOrgoodsWarehouseOrAddressList;
	}

}
