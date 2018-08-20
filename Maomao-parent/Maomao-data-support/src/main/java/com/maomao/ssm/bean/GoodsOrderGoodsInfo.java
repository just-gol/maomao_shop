package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class GoodsOrderGoodsInfo implements Serializable {
    private Integer id;

    private Integer orderId;

    private Integer goodsOrderGoodsId;

    private Integer goodsOverstockId;

    private Integer goodsOverstockInfoId;

    private Integer goodsOverstockInfoUserId;

    private Integer goodsOverstockUserId;

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

    private String code;

    private Date createTime;

    private String addrezz;

    private Date modifiedTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsOrderGoodsId() {
        return goodsOrderGoodsId;
    }

    public void setGoodsOrderGoodsId(Integer goodsOrderGoodsId) {
        this.goodsOrderGoodsId = goodsOrderGoodsId;
    }

    public Integer getGoodsOverstockId() {
        return goodsOverstockId;
    }

    public void setGoodsOverstockId(Integer goodsOverstockId) {
        this.goodsOverstockId = goodsOverstockId;
    }

    public Integer getGoodsOverstockInfoId() {
        return goodsOverstockInfoId;
    }

    public void setGoodsOverstockInfoId(Integer goodsOverstockInfoId) {
        this.goodsOverstockInfoId = goodsOverstockInfoId;
    }

    public Integer getGoodsOverstockInfoUserId() {
        return goodsOverstockInfoUserId;
    }

    public void setGoodsOverstockInfoUserId(Integer goodsOverstockInfoUserId) {
        this.goodsOverstockInfoUserId = goodsOverstockInfoUserId;
    }

    public Integer getGoodsOverstockUserId() {
        return goodsOverstockUserId;
    }

    public void setGoodsOverstockUserId(Integer goodsOverstockUserId) {
        this.goodsOverstockUserId = goodsOverstockUserId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
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
        this.img = img == null ? null : img.trim();
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
        this.express = express == null ? null : express.trim();
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum == null ? null : expressNum.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAddrezz() {
        return addrezz;
    }

    public void setAddrezz(String addrezz) {
        this.addrezz = addrezz == null ? null : addrezz.trim();
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", goodsOrderGoodsId=").append(goodsOrderGoodsId);
        sb.append(", goodsOverstockId=").append(goodsOverstockId);
        sb.append(", goodsOverstockInfoId=").append(goodsOverstockInfoId);
        sb.append(", goodsOverstockInfoUserId=").append(goodsOverstockInfoUserId);
        sb.append(", goodsOverstockUserId=").append(goodsOverstockUserId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", sku=").append(sku);
        sb.append(", pricePurchase=").append(pricePurchase);
        sb.append(", priceSales=").append(priceSales);
        sb.append(", type=").append(type);
        sb.append(", useEndTime=").append(useEndTime);
        sb.append(", img=").append(img);
        sb.append(", adminId=").append(adminId);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", express=").append(express);
        sb.append(", expressNum=").append(expressNum);
        sb.append(", code=").append(code);
        sb.append(", createTime=").append(createTime);
        sb.append(", addrezz=").append(addrezz);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}