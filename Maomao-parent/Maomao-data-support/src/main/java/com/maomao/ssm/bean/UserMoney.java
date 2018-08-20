package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class UserMoney implements Serializable {
    private Integer id;

    private Integer userId;

    private Long moneyOld;

    private Long moneyNew;

    private Long moneyChange;

    private Integer bizId;

    private Byte type;

    private Date createTime;

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

    public Long getMoneyOld() {
        return moneyOld;
    }

    public void setMoneyOld(Long moneyOld) {
        this.moneyOld = moneyOld;
    }

    public Long getMoneyNew() {
        return moneyNew;
    }

    public void setMoneyNew(Long moneyNew) {
        this.moneyNew = moneyNew;
    }

    public Long getMoneyChange() {
        return moneyChange;
    }

    public void setMoneyChange(Long moneyChange) {
        this.moneyChange = moneyChange;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", moneyOld=").append(moneyOld);
        sb.append(", moneyNew=").append(moneyNew);
        sb.append(", moneyChange=").append(moneyChange);
        sb.append(", bizId=").append(bizId);
        sb.append(", type=").append(type);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}