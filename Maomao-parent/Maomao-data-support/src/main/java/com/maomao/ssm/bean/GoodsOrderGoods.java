package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class GoodsOrderGoods implements Serializable {
    private Integer id;

    private Integer orderId;

    private Integer category;

    private Byte type;

    private Integer bizId;

    private String name;

    private Integer num;

    private Long reword;

    private Long rebate;

    private String imgs;

    private Date createTime;

    private Long pricePurchase;

    private Long priceSales;

    private String sku;

    private Integer skuId;

    private Long deposit;

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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getReword() {
        return reword;
    }

    public void setReword(Long reword) {
        this.reword = reword;
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
        this.imgs = imgs == null ? null : imgs.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", category=").append(category);
        sb.append(", type=").append(type);
        sb.append(", bizId=").append(bizId);
        sb.append(", name=").append(name);
        sb.append(", num=").append(num);
        sb.append(", reword=").append(reword);
        sb.append(", rebate=").append(rebate);
        sb.append(", imgs=").append(imgs);
        sb.append(", createTime=").append(createTime);
        sb.append(", pricePurchase=").append(pricePurchase);
        sb.append(", priceSales=").append(priceSales);
        sb.append(", sku=").append(sku);
        sb.append(", skuId=").append(skuId);
        sb.append(", deposit=").append(deposit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}