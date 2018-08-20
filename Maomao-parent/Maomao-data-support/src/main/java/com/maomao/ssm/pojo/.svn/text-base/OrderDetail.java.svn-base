package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsOrderCustom;
import com.maomao.ssm.bean.GoodsService;
import com.maomao.ssm.bean.GoodsWarehouse;

public class OrderDetail implements Serializable {

	private Integer id;
	private String orderNum;
	private Integer userId;
	private Long money;
	private Long payMoney;
	private Date createTime;
	private Long unpayMoney;

	private Integer buyNum;

	private String goodsName;
	private Long pricePurchase;
	private Long priceSales;

	private String category;
	private String brand;

	private String param;

	private Byte getWay;
	private Byte payType;
	private String getWayString;
	private String payTypeString;
	private String userName;
	private String address;
	private String mobile;
	private Long deposit;
	private String sku;

	private Byte warehouseType;
	private String warehouseTypeString;
	private String warehouseProvince;
	private String warehouseCity;
	private String warehouseArea;
	private String warehouseAddress;
	private String warehouseUsername;
	private String warehouseMobile;

	private List<GoodsService> goodsServices;

	private String express;
	private String expressNum;

	private Byte status;
	private String statusString;

	private String refundAddress;
	private String refundName;
	private String refundMobile;

	private String goodsAddress;
	private String goodsAddressMobile;
	private String goodsAddressName;

	public OrderDetail() {
		super();
	}

	public OrderDetail(GoodsOrderCustom goodsOrderCustom, String param, List<GoodsService> goodsServices, String brand,
			String category, GoodsWarehouse goodsWarehouse, String getWayString, String payTypeString,
			String warehouseTypeString, String status, GoodsAddress goodsAddress) {
		super();
		this.id = goodsOrderCustom.getId();
		this.orderNum = goodsOrderCustom.getOrderNum();
		this.userId = goodsOrderCustom.getUserId();
		this.money = goodsOrderCustom.getMoney();
		this.payMoney = goodsOrderCustom.getPayMoney();
		this.createTime = goodsOrderCustom.getCreateTime();
		this.buyNum = goodsOrderCustom.getNum();
		this.goodsName = goodsOrderCustom.getName();
		this.pricePurchase = goodsOrderCustom.getPricePurchase() * goodsOrderCustom.getNum();
		this.priceSales = goodsOrderCustom.getPriceSales() * goodsOrderCustom.getNum();
		this.category = category;
		this.brand = brand;
		this.param = param;
		this.getWay = goodsOrderCustom.getGetWay();
		this.payType = goodsOrderCustom.getPayWay();
		this.userName = goodsOrderCustom.getUserName();
		this.address = goodsOrderCustom.getAddress();
		this.mobile = goodsOrderCustom.getMobile();
		this.warehouseType = goodsWarehouse.getType();
		this.warehouseProvince = goodsWarehouse.getProvince();
		this.warehouseCity = goodsWarehouse.getCity();
		this.warehouseArea = goodsWarehouse.getArea();
		this.warehouseAddress = goodsWarehouse.getAddress();
		this.warehouseUsername = goodsWarehouse.getName();
		this.warehouseMobile = goodsWarehouse.getMobile();
		this.goodsServices = goodsServices;
		this.express = goodsOrderCustom.getExpress();
		this.expressNum = goodsOrderCustom.getExpressNum();
		this.status = goodsOrderCustom.getStatus();
		this.payTypeString = payTypeString;
		this.getWayString = getWayString;
		this.warehouseTypeString = warehouseTypeString;
		this.statusString = status;
		this.refundAddress = goodsOrderCustom.getRefundAddress();
		this.refundMobile = goodsOrderCustom.getRefundMobile();
		this.refundName = goodsOrderCustom.getRefundName();
		this.unpayMoney = goodsOrderCustom.getUnpayMoney();
		this.deposit = goodsOrderCustom.getDeposit();
		this.sku = goodsOrderCustom.getSku();
		if (goodsAddress != null) {
			this.goodsAddress = goodsAddress.getProvince() + goodsAddress.getCity() + goodsAddress.getArea()
					+ goodsAddress.getAddress();
			this.goodsAddressMobile = goodsAddress.getMobile();
			this.goodsAddressName = goodsAddress.getName();
		}
	}

	public OrderDetail(GoodsOrderCustom goodsOrderCustom, String param, List<GoodsService> goodsServices, String brand,
			String category, GoodsWarehouse goodsWarehouse, GoodsAddress goodsAddress) {
		super();
		this.id = goodsOrderCustom.getId();
		this.orderNum = goodsOrderCustom.getOrderNum();
		this.userId = goodsOrderCustom.getUserId();
		this.money = goodsOrderCustom.getMoney();
		this.payMoney = goodsOrderCustom.getPayMoney();
		this.createTime = goodsOrderCustom.getCreateTime();
		this.buyNum = goodsOrderCustom.getNum();
		this.goodsName = goodsOrderCustom.getName();
		this.pricePurchase = goodsOrderCustom.getPricePurchase() * goodsOrderCustom.getNum();
		this.priceSales = goodsOrderCustom.getPriceSales() * goodsOrderCustom.getNum();
		this.category = category;
		this.brand = brand;
		this.param = param;
		this.getWay = goodsOrderCustom.getGetWay();
		this.payType = goodsOrderCustom.getPayWay();
		this.userName = goodsOrderCustom.getUserName();
		this.address = goodsOrderCustom.getAddress();
		this.mobile = goodsOrderCustom.getMobile();
		this.warehouseType = goodsWarehouse.getType();
		this.warehouseProvince = goodsWarehouse.getProvince();
		this.warehouseCity = goodsWarehouse.getCity();
		this.warehouseArea = goodsWarehouse.getArea();
		this.warehouseAddress = goodsWarehouse.getAddress();
		this.warehouseUsername = goodsWarehouse.getName();
		this.warehouseMobile = goodsWarehouse.getMobile();
		this.goodsServices = goodsServices;
		this.express = goodsOrderCustom.getExpress();
		this.expressNum = goodsOrderCustom.getExpressNum();
		this.status = goodsOrderCustom.getStatus();
		this.unpayMoney = goodsOrderCustom.getUnpayMoney();
		this.sku = goodsOrderCustom.getSku();
		if (goodsAddress != null) {
			this.goodsAddress = goodsAddress.getProvince() + goodsAddress.getCity() + goodsAddress.getArea()
					+ goodsAddress.getAddress();
			this.goodsAddressMobile = goodsAddress.getMobile();
			this.goodsAddressName = goodsAddress.getName();
		}
	}

	public OrderDetail(Integer id, String orderNum, Integer userId, Long money, Long payMoney, Date createTime,
			Long unpayMoney, Integer buyNum, String goodsName, Long pricePurchase, Long priceSales, String category,
			String brand, String param, Byte getWay, Byte payType, String getWayString, String payTypeString,
			String userName, String address, String mobile, Long deposit, String sku, Byte warehouseType,
			String warehouseTypeString, String warehouseProvince, String warehouseCity, String warehouseArea,
			String warehouseAddress, String warehouseUsername, String warehouseMobile, List<GoodsService> goodsServices,
			String express, String expressNum, Byte status, String statusString, String refundAddress,
			String refundName, String refundMobile, String goodsAddress, String goodsAddressMobile,
			String goodsAddressName) {
		super();
		this.id = id;
		this.orderNum = orderNum;
		this.userId = userId;
		this.money = money;
		this.payMoney = payMoney;
		this.createTime = createTime;
		this.unpayMoney = unpayMoney;
		this.buyNum = buyNum;
		this.goodsName = goodsName;
		this.pricePurchase = pricePurchase;
		this.priceSales = priceSales;
		this.category = category;
		this.brand = brand;
		this.param = param;
		this.getWay = getWay;
		this.payType = payType;
		this.getWayString = getWayString;
		this.payTypeString = payTypeString;
		this.userName = userName;
		this.address = address;
		this.mobile = mobile;
		this.deposit = deposit;
		this.sku = sku;
		this.warehouseType = warehouseType;
		this.warehouseTypeString = warehouseTypeString;
		this.warehouseProvince = warehouseProvince;
		this.warehouseCity = warehouseCity;
		this.warehouseArea = warehouseArea;
		this.warehouseAddress = warehouseAddress;
		this.warehouseUsername = warehouseUsername;
		this.warehouseMobile = warehouseMobile;
		this.goodsServices = goodsServices;
		this.express = express;
		this.expressNum = expressNum;
		this.status = status;
		this.statusString = statusString;
		this.refundAddress = refundAddress;
		this.refundName = refundName;
		this.refundMobile = refundMobile;
		this.goodsAddress = goodsAddress;
		this.goodsAddressMobile = goodsAddressMobile;
		this.goodsAddressName = goodsAddressName;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Byte getGetWay() {
		return getWay;
	}

	public void setGetWay(Byte getWay) {
		this.getWay = getWay;
	}

	public Byte getPayType() {
		return payType;
	}

	public void setPayType(Byte payType) {
		this.payType = payType;
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

	public Byte getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(Byte warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getWarehouseProvince() {
		return warehouseProvince;
	}

	public void setWarehouseProvince(String warehouseProvince) {
		this.warehouseProvince = warehouseProvince;
	}

	public String getWarehouseCity() {
		return warehouseCity;
	}

	public void setWarehouseCity(String warehouseCity) {
		this.warehouseCity = warehouseCity;
	}

	public String getWarehouseArea() {
		return warehouseArea;
	}

	public void setWarehouseArea(String warehouseArea) {
		this.warehouseArea = warehouseArea;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	public String getWarehouseUsername() {
		return warehouseUsername;
	}

	public void setWarehouseUsername(String warehouseUsername) {
		this.warehouseUsername = warehouseUsername;
	}

	public String getWarehouseMobile() {
		return warehouseMobile;
	}

	public void setWarehouseMobile(String warehouseMobile) {
		this.warehouseMobile = warehouseMobile;
	}

	public List<GoodsService> getGoodsServices() {
		return goodsServices;
	}

	public void setGoodsServices(List<GoodsService> goodsServices) {
		this.goodsServices = goodsServices;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getExpressNum() {
		return expressNum;
	}

	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getGetWayString() {
		return getWayString;
	}

	public void setGetWayString(String getWayString) {
		this.getWayString = getWayString;
	}

	public String getPayTypeString() {
		return payTypeString;
	}

	public void setPayTypeString(String payTypeString) {
		this.payTypeString = payTypeString;
	}

	public String getWarehouseTypeString() {
		return warehouseTypeString;
	}

	public void setWarehouseTypeString(String warehouseTypeString) {
		this.warehouseTypeString = warehouseTypeString;
	}

	public String getStatusString() {
		return statusString;
	}

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	public String getRefundAddress() {
		return refundAddress;
	}

	public void setRefundAddress(String refundAddress) {
		this.refundAddress = refundAddress;
	}

	public String getRefundName() {
		return refundName;
	}

	public void setRefundName(String refundName) {
		this.refundName = refundName;
	}

	public String getRefundMobile() {
		return refundMobile;
	}

	public void setRefundMobile(String refundMobile) {
		this.refundMobile = refundMobile;
	}

	public Long getUnpayMoney() {
		return unpayMoney;
	}

	public void setUnpayMoney(Long unpayMoney) {
		this.unpayMoney = unpayMoney;
	}

	public Long getDeposit() {
		return deposit;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getGoodsAddress() {
		return goodsAddress;
	}

	public void setGoodsAddress(String goodsAddress) {
		this.goodsAddress = goodsAddress;
	}

	public String getGoodsAddressMobile() {
		return goodsAddressMobile;
	}

	public void setGoodsAddressMobile(String goodsAddressMobile) {
		this.goodsAddressMobile = goodsAddressMobile;
	}

	public String getGoodsAddressName() {
		return goodsAddressName;
	}

	public void setGoodsAddressName(String goodsAddressName) {
		this.goodsAddressName = goodsAddressName;
	}

}
