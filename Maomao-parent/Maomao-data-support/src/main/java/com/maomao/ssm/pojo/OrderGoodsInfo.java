package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.Date;

import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsOrderGoodsInfo;
import com.maomao.ssm.bean.GoodsWarehouse;

public class OrderGoodsInfo implements Serializable {
	private static final long serialVersionUID = -6755526263216088714L;
	private Integer id;
	private String goodsName;
	private String sku;
	private Long pricePurchase;
	private Long priceSales;
	private Byte type;
	private Date useEndTime;
	private String img;
	private Integer adminId;
	private Integer userId;
	private Byte status;
	private String express;
	private String expressNum;
	private String addrezz;

	private String province;
	private String city;
	private String area;
	private String address;
	private String name;
	private String mobile;

	private String adminMobile;
	private String adminName;

	public OrderGoodsInfo() {
		super();
	}

	public OrderGoodsInfo(GoodsOrderGoodsInfo goodsOrderGoodsInfo, GoodsWarehouse goodsWarehouse, Admin admin) {
		super();
		this.id = goodsOrderGoodsInfo.getId();
		this.goodsName = goodsOrderGoodsInfo.getGoodsName();
		this.sku = goodsOrderGoodsInfo.getSku();
		this.pricePurchase = goodsOrderGoodsInfo.getPricePurchase();
		this.priceSales = goodsOrderGoodsInfo.getPriceSales();
		this.type = goodsOrderGoodsInfo.getType();
		this.useEndTime = goodsOrderGoodsInfo.getUseEndTime();
		this.img = goodsOrderGoodsInfo.getImg();
		this.adminId = goodsOrderGoodsInfo.getAdminId();
		this.userId = goodsOrderGoodsInfo.getUserId();
		this.status = goodsOrderGoodsInfo.getStatus();
		this.express = goodsOrderGoodsInfo.getExpress();
		this.expressNum = goodsOrderGoodsInfo.getExpressNum();
		this.addrezz = goodsOrderGoodsInfo.getAddrezz();
		if (goodsWarehouse != null) {
			this.province = goodsWarehouse.getProvince();
			this.city = goodsWarehouse.getCity();
			this.area = goodsWarehouse.getArea();
			this.address = goodsWarehouse.getAddress();
			this.name = goodsWarehouse.getName();
			this.mobile = goodsWarehouse.getMobile();
		}
		this.adminMobile = admin.getMobile();
		this.adminName = admin.getName();
	}

	public OrderGoodsInfo(Integer id, String goodsName, String sku, Long pricePurchase, Long priceSales, Byte type,
			Date useEndTime, String img, Integer adminId, Integer userId, Byte status, String express,
			String expressNum, String addrezz, String province, String city, String area, String address, String name,
			String mobile, String adminMobile, String adminName) {
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.sku = sku;
		this.pricePurchase = pricePurchase;
		this.priceSales = priceSales;
		this.type = type;
		this.useEndTime = useEndTime;
		this.img = img;
		this.adminId = adminId;
		this.userId = userId;
		this.status = status;
		this.express = express;
		this.expressNum = expressNum;
		this.addrezz = addrezz;
		this.province = province;
		this.city = city;
		this.area = area;
		this.address = address;
		this.name = name;
		this.mobile = mobile;
		this.adminMobile = adminMobile;
		this.adminName = adminName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
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

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Date getUseEndTime() {
		return useEndTime;
	}

	public void setUseEndTime(Date useEndTime) {
		this.useEndTime = useEndTime;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
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

	public String getAddrezz() {
		return addrezz;
	}

	public void setAddrezz(String addrezz) {
		this.addrezz = addrezz;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAdminMobile() {
		return adminMobile;
	}

	public void setAdminMobile(String adminMobile) {
		this.adminMobile = adminMobile;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

}
