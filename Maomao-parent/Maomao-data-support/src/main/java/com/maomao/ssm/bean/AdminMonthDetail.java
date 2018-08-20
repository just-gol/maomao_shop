package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class AdminMonthDetail implements Serializable {
    private Integer id;

    private Integer adminId;

    private Date createTime;

    private Long loanChange;

    private Long moneyChange;

    private Date uploadTime;

    private String loanImg;

    private String moneyImg;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getLoanChange() {
        return loanChange;
    }

    public void setLoanChange(Long loanChange) {
        this.loanChange = loanChange;
    }

    public Long getMoneyChange() {
        return moneyChange;
    }

    public void setMoneyChange(Long moneyChange) {
        this.moneyChange = moneyChange;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getLoanImg() {
        return loanImg;
    }

    public void setLoanImg(String loanImg) {
        this.loanImg = loanImg == null ? null : loanImg.trim();
    }

    public String getMoneyImg() {
        return moneyImg;
    }

    public void setMoneyImg(String moneyImg) {
        this.moneyImg = moneyImg == null ? null : moneyImg.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adminId=").append(adminId);
        sb.append(", createTime=").append(createTime);
        sb.append(", loanChange=").append(loanChange);
        sb.append(", moneyChange=").append(moneyChange);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", loanImg=").append(loanImg);
        sb.append(", moneyImg=").append(moneyImg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}