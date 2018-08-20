package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.maomao.ssm.bean.GoodsOrderCustom;
import com.maomao.ssm.bean.GoodsOverstockUserWithBLOBs;
import com.maomao.ssm.bean.GoodsService;

public class OrderOverstockDetail implements Serializable {
	private static final long serialVersionUID = -7127070241384217300L;
	private Integer id;
	private String orderNum;
	private Integer userId;
	private Long money;
	private Long payMoney;
	private Date createTime;
	private Integer buyNum;

	private String userName;
	private String address;
	private String mobile;

	private String goodsName;
	private Long pricePurchase;
	private Long priceSales;

	private List<GoodsService> goodsServices;
	private String param;

	private List<OrderGoodsInfo> orderGoodsInfos;

	public OrderOverstockDetail() {
		super();
	}

	public OrderOverstockDetail(GoodsOrderCustom goodsOrderCustom,
			GoodsOverstockUserWithBLOBs goodsOverstockUserWithBLOBs, List<OrderGoodsInfo> orderGoodsInfos,
			List<GoodsService> goodsServices, String param) {
		super();
		this.id = goodsOrderCustom.getId();
		this.orderNum = goodsOrderCustom.getOrderNum();
		this.userId = goodsOrderCustom.getUserId();
		this.money = goodsOrderCustom.getMoney();
		this.payMoney = goodsOrderCustom.getPayMoney();
		this.createTime = goodsOrderCustom.getCreateTime();
		this.buyNum = goodsOrderCustom.getNum();
		this.userName = goodsOrderCustom.getUserName();
		this.address = goodsOrderCustom.getAddress();
		this.mobile = goodsOrderCustom.getMobile();
		this.goodsName = goodsOrderCustom.getName();
		this.priceSales = goodsOrderCustom.getPriceSales() * goodsOrderCustom.getNum();
		this.orderGoodsInfos = orderGoodsInfos;
		this.goodsServices = goodsServices;
		this.param = param;
	}

	public OrderOverstockDetail(Integer id, String orderNum, Integer userId, Long money, Long payMoney, Date createTime,
			Integer buyNum, String userName, String address, String mobile, String goodsName, Long pricePurchase,
			Long priceSales, List<OrderGoodsInfo> orderGoodsInfos, List<GoodsService> goodsServices, String param) {
		super();
		this.id = id;
		this.orderNum = orderNum;
		this.userId = userId;
		this.money = money;
		this.payMoney = payMoney;
		this.createTime = createTime;
		this.buyNum = buyNum;
		this.userName = userName;
		this.address = address;
		this.mobile = mobile;
		this.goodsName = goodsName;
		this.pricePurchase = pricePurchase;
		this.priceSales = priceSales;
		this.orderGoodsInfos = orderGoodsInfos;
		this.goodsServices = goodsServices;
		this.param = param;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public Long getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Long payMoney) {
		this.payMoney = payMoney;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getPricePurchase() {
		return pricePurchase;
	}

	public void setPricePurchase(Long pricePurchase) {
		this.pricePurchase = pricePurchase;
	}

	public Long getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Long priceSales) {
		this.priceSales = priceSales;
	}

	public List<OrderGoodsInfo> getOrderGoodsInfos() {
		return orderGoodsInfos;
	}

	public void setOrderGoodsInfos(List<OrderGoodsInfo> orderGoodsInfos) {
		this.orderGoodsInfos = orderGoodsInfos;
	}

	public List<GoodsService> getGoodsServices() {
		return goodsServices;
	}

	public void setGoodsServices(List<GoodsService> goodsServices) {
		this.goodsServices = goodsServices;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
