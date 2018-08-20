package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class UserWithdrawals implements Serializable {
    private Integer id;

    private Integer userId;

    private Long money;

    /**
     * 0:申请中,1:已打款,2:打款失败
     */
    private Byte status;

    private String bankName;

    private String account;

    private Date createTime;

    /**
     * 提现类型:0支付宝 1微信 2银行卡
     */
    private Byte type;

    private Integer adminId;

    private String userName;

    private Byte category;

    private String accountsBank;

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

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Byte getCategory() {
        return category;
    }

    public void setCategory(Byte category) {
        this.category = category;
    }

    public String getAccountsBank() {
        return accountsBank;
    }

    public void setAccountsBank(String accountsBank) {
        this.accountsBank = accountsBank == null ? null : accountsBank.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", money=").append(money);
        sb.append(", status=").append(status);
        sb.append(", bankName=").append(bankName);
        sb.append(", account=").append(account);
        sb.append(", createTime=").append(createTime);
        sb.append(", type=").append(type);
        sb.append(", adminId=").append(adminId);
        sb.append(", userName=").append(userName);
        sb.append(", category=").append(category);
        sb.append(", accountsBank=").append(accountsBank);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}