package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class UserCredit implements Serializable {
    private Integer id;

    private Integer userId;

    private Long creditOld;

    private Long creditNew;

    private Long creditChange;

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

    public Long getCreditOld() {
        return creditOld;
    }

    public void setCreditOld(Long creditOld) {
        this.creditOld = creditOld;
    }

    public Long getCreditNew() {
        return creditNew;
    }

    public void setCreditNew(Long creditNew) {
        this.creditNew = creditNew;
    }

    public Long getCreditChange() {
        return creditChange;
    }

    public void setCreditChange(Long creditChange) {
        this.creditChange = creditChange;
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
        sb.append(", creditOld=").append(creditOld);
        sb.append(", creditNew=").append(creditNew);
        sb.append(", creditChange=").append(creditChange);
        sb.append(", bizId=").append(bizId);
        sb.append(", type=").append(type);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}