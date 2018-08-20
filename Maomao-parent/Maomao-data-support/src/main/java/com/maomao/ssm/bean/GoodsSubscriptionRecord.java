package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class GoodsSubscriptionRecord implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer goodsSubscriptionId;

    private Integer subscriptionNum;

    private Date createTime;

    private Long priceSubcriptionTotal;

    private Long bonusTotal;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsSubscriptionId() {
        return goodsSubscriptionId;
    }

    public void setGoodsSubscriptionId(Integer goodsSubscriptionId) {
        this.goodsSubscriptionId = goodsSubscriptionId;
    }

    public Integer getSubscriptionNum() {
        return subscriptionNum;
    }

    public void setSubscriptionNum(Integer subscriptionNum) {
        this.subscriptionNum = subscriptionNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getPriceSubcriptionTotal() {
        return priceSubcriptionTotal;
    }

    public void setPriceSubcriptionTotal(Long priceSubcriptionTotal) {
        this.priceSubcriptionTotal = priceSubcriptionTotal;
    }

    public Long getBonusTotal() {
        return bonusTotal;
    }

    public void setBonusTotal(Long bonusTotal) {
        this.bonusTotal = bonusTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", goodsSubscriptionId=").append(goodsSubscriptionId);
        sb.append(", subscriptionNum=").append(subscriptionNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", priceSubcriptionTotal=").append(priceSubcriptionTotal);
        sb.append(", bonusTotal=").append(bonusTotal);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}