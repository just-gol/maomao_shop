package com.maomao.ssm.pojo.order;

import java.io.Serializable;
import java.util.List;

import com.maomao.ssm.bean.GoodsOrderCustom;
import com.maomao.ssm.bean.GoodsOrderGoodsInfo;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.utils.JsonUtils;

public class Order implements Serializable {
	private static final long serialVersionUID = 5946470510655977185L;

	private Integer orderId;
	private String orderNum;
	private String goodsImg;
	private String goodsName;
	private Long money;
	private Long goodsPrice;
	private Integer goodsNum;
	private String addressUser;
	private String addressDetail;
	private String addressMobile;
	private Byte status;
	private Byte payType;
	private Byte getWay;
	private Byte payWay;
	private String express;
	private String expressNum;
	private Integer category;
	private Integer goodsId;
	private Long createTime;
	private Long couponMoney;
	private String refundAddress;
	private String refundName;
	private String refundMobile;
	private String sku;
	private Long deposit;
	private String code;
	private Long unpayMoney;

	private Integer orderCategory;
	private List<GoodsOrderGoodsInfo> goodsOrderGoodsInfos;

	public Order() {
		super();
	}

	public Order(GoodsOrderCustom goodsOrderCustom) {
		super();
		this.orderId = goodsOrderCustom.getId();
		this.orderNum = goodsOrderCustom.getOrderNum();
		this.goodsImg = goodsOrderCustom.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0];
		this.goodsName = goodsOrderCustom.getName();
		this.money = goodsOrderCustom.getMoney();
		this.goodsPrice = goodsOrderCustom.getPriceSales();
		this.goodsNum = goodsOrderCustom.getNum();
		this.addressUser = goodsOrderCustom.getUserName();
		this.addressDetail = goodsOrderCustom.getAddress();
		this.addressMobile = goodsOrderCustom.getMobile();
		this.status = goodsOrderCustom.getStatus();
		this.payType = goodsOrderCustom.getPayType();
		this.getWay = goodsOrderCustom.getGetWay();
		this.payWay = goodsOrderCustom.getPayWay();
		this.express = goodsOrderCustom.getExpress();
		this.expressNum = goodsOrderCustom.getExpressNum();
		this.category = goodsOrderCustom.getCategory();
		this.goodsId = goodsOrderCustom.getBizId();
		this.createTime = goodsOrderCustom.getCreateTime().getTime();
		this.couponMoney = goodsOrderCustom.getCouponMoney();
		this.refundAddress = goodsOrderCustom.getRefundAddress();
		this.refundName = goodsOrderCustom.getRefundName();
		this.refundMobile = goodsOrderCustom.getRefundMobile();
		this.sku = JsonUtils.getJsonValue(goodsOrderCustom.getSku());
		this.deposit = goodsOrderCustom.getDeposit();
		this.code = goodsOrderCustom.getCode();
		this.unpayMoney = goodsOrderCustom.getUnpayMoney();
		this.orderCategory = goodsOrderCustom.getOrderCategory();
	}

	public Order(GoodsOrderCustom goodsOrderCustom, List<GoodsOrderGoodsInfo> goodsOrderGoodsInfos) {
		super();
		this.orderId = goodsOrderCustom.getId();
		this.orderNum = goodsOrderCustom.getOrderNum();
		this.goodsImg = goodsOrderCustom.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0];
		this.goodsName = goodsOrderCustom.getName();
		this.money = goodsOrderCustom.getMoney();
		this.goodsPrice = goodsOrderCustom.getPriceSales();
		this.goodsNum = goodsOrderCustom.getNum();
		this.addressUser = goodsOrderCustom.getUserName();
		this.addressDetail = goodsOrderCustom.getAddress();
		this.addressMobile = goodsOrderCustom.getMobile();
		this.status = goodsOrderCustom.getStatus();
		this.payType = goodsOrderCustom.getPayType();
		this.getWay = goodsOrderCustom.getGetWay();
		this.payWay = goodsOrderCustom.getPayWay();
		this.category = goodsOrderCustom.getCategory();
		this.goodsId = goodsOrderCustom.getBizId();
		this.createTime = goodsOrderCustom.getCreateTime().getTime();
		this.couponMoney = goodsOrderCustom.getCouponMoney();
		this.orderCategory = goodsOrderCustom.getOrderCategory();
		this.goodsOrderGoodsInfos = goodsOrderGoodsInfos;
	}

	public Order(Integer orderId, String orderNum, String goodsImg, String goodsName, Long money, Long goodsPrice,
			Integer goodsNum, String addressUser, String addressDetail, String addressMobile, Byte status, Byte payType,
			Byte getWay, Byte payWay, String express, String expressNum, Integer category, Integer goodsId,
			Long createTime, Long couponMoney, String refundAddress, String refundName, String refundMobile, String sku,
			Long deposit, String code, Long unpayMoney) {
		super();
		this.orderId = orderId;
		this.orderNum = orderNum;
		this.goodsImg = goodsImg;
		this.goodsName = goodsName;
		this.money = money;
		this.goodsPrice = goodsPrice;
		this.goodsNum = goodsNum;
		this.addressUser = addressUser;
		this.addressDetail = addressDetail;
		this.addressMobile = addressMobile;
		this.status = status;
		this.payType = payType;
		this.getWay = getWay;
		this.payWay = payWay;
		this.express = express;
		this.expressNum = expressNum;
		this.category = category;
		this.goodsId = goodsId;
		this.createTime = createTime;
		this.couponMoney = couponMoney;
		this.refundAddress = refundAddress;
		this.refundName = refundName;
		this.refundMobile = refundMobile;
		this.sku = sku;
		this.deposit = deposit;
		this.code = code;
		this.unpayMoney = unpayMoney;
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

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public Long getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Long goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getAddressUser() {
		return addressUser;
	}

	public void setAddressUser(String addressUser) {
		this.addressUser = addressUser;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getAddressMobile() {
		return addressMobile;
	}

	public void setAddressMobile(String addressMobile) {
		this.addressMobile = addressMobile;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getPayType() {
		return payType;
	}

	public void setPayType(Byte payType) {
		this.payType = payType;
	}

	public Byte getGetWay() {
		return getWay;
	}

	public void setGetWay(Byte getWay) {
		this.getWay = getWay;
	}

	public Byte getPayWay() {
		return payWay;
	}

	public void setPayWay(Byte payWay) {
		this.payWay = payWay;
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

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(Long couponMoney) {
		this.couponMoney = couponMoney;
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Long getDeposit() {
		return deposit;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getUnpayMoney() {
		return unpayMoney;
	}

	public void setUnpayMoney(Long unpayMoney) {
		this.unpayMoney = unpayMoney;
	}

	public Integer getOrderCategory() {
		return orderCategory;
	}

	public void setOrderCategory(Integer orderCategory) {
		this.orderCategory = orderCategory;
	}

	public List<GoodsOrderGoodsInfo> getGoodsOrderGoodsInfos() {
		return goodsOrderGoodsInfos;
	}

	public void setGoodsOrderGoodsInfos(List<GoodsOrderGoodsInfo> goodsOrderGoodsInfos) {
		this.goodsOrderGoodsInfos = goodsOrderGoodsInfos;
	}

}
