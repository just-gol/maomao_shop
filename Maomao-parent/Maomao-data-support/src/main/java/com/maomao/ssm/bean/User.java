package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer id;

    private String nickname;

    private String mobile;

    private String password;

    private Byte type;

    private Byte status;

    private String invitation;

    private String idCard;

    private String name;

    private Byte img;

    private Long money;

    private Long creditTotal;

    private Long creditSurplus;

    private Byte creditReal;

    private Date createTime;

    private Date modifiedTime;

    private Byte checkStatus;

    private Long accumulativeMoney;

    private Date repayTime;

    private Long loan;

    private String passwordWithdrawals;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getInvitation() {
        return invitation;
    }

    public void setInvitation(String invitation) {
        this.invitation = invitation == null ? null : invitation.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getImg() {
        return img;
    }

    public void setImg(Byte img) {
        this.img = img;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(Long creditTotal) {
        this.creditTotal = creditTotal;
    }

    public Long getCreditSurplus() {
        return creditSurplus;
    }

    public void setCreditSurplus(Long creditSurplus) {
        this.creditSurplus = creditSurplus;
    }

    public Byte getCreditReal() {
        return creditReal;
    }

    public void setCreditReal(Byte creditReal) {
        this.creditReal = creditReal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Long getAccumulativeMoney() {
        return accumulativeMoney;
    }

    public void setAccumulativeMoney(Long accumulativeMoney) {
        this.accumulativeMoney = accumulativeMoney;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public Long getLoan() {
        return loan;
    }

    public void setLoan(Long loan) {
        this.loan = loan;
    }

    public String getPasswordWithdrawals() {
        return passwordWithdrawals;
    }

    public void setPasswordWithdrawals(String passwordWithdrawals) {
        this.passwordWithdrawals = passwordWithdrawals == null ? null : passwordWithdrawals.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nickname=").append(nickname);
        sb.append(", mobile=").append(mobile);
        sb.append(", password=").append(password);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", invitation=").append(invitation);
        sb.append(", idCard=").append(idCard);
        sb.append(", name=").append(name);
        sb.append(", img=").append(img);
        sb.append(", money=").append(money);
        sb.append(", creditTotal=").append(creditTotal);
        sb.append(", creditSurplus=").append(creditSurplus);
        sb.append(", creditReal=").append(creditReal);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", accumulativeMoney=").append(accumulativeMoney);
        sb.append(", repayTime=").append(repayTime);
        sb.append(", loan=").append(loan);
        sb.append(", passwordWithdrawals=").append(passwordWithdrawals);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}