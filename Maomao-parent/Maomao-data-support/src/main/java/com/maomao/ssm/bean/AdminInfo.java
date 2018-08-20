package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class AdminInfo implements Serializable {
    private Integer id;

    private Integer adminId;

    private String leaglPerson;

    private String legalPersonMobile;

    private String bankAccount;

    private String bankName;

    private String bankUser;

    private String repaymentBankAccount;

    private String repaymentBankName;

    private String repaymentBankUser;

    private String companyName;

    private String companyCode;

    private String companyAddress;

    private String enclosure;

    private Date createTime;

    private Date modifiedTime;

    private Date repayTime;

    private Long mortage;

    private Long loan;

    private String accountsBank;

    private String repaymentAccountsBank;

    private String province;

    private String city;

    private String area;

    private String address;

    private String name;

    private String mobile;

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

    public String getLeaglPerson() {
        return leaglPerson;
    }

    public void setLeaglPerson(String leaglPerson) {
        this.leaglPerson = leaglPerson == null ? null : leaglPerson.trim();
    }

    public String getLegalPersonMobile() {
        return legalPersonMobile;
    }

    public void setLegalPersonMobile(String legalPersonMobile) {
        this.legalPersonMobile = legalPersonMobile == null ? null : legalPersonMobile.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankUser() {
        return bankUser;
    }

    public void setBankUser(String bankUser) {
        this.bankUser = bankUser == null ? null : bankUser.trim();
    }

    public String getRepaymentBankAccount() {
        return repaymentBankAccount;
    }

    public void setRepaymentBankAccount(String repaymentBankAccount) {
        this.repaymentBankAccount = repaymentBankAccount == null ? null : repaymentBankAccount.trim();
    }

    public String getRepaymentBankName() {
        return repaymentBankName;
    }

    public void setRepaymentBankName(String repaymentBankName) {
        this.repaymentBankName = repaymentBankName == null ? null : repaymentBankName.trim();
    }

    public String getRepaymentBankUser() {
        return repaymentBankUser;
    }

    public void setRepaymentBankUser(String repaymentBankUser) {
        this.repaymentBankUser = repaymentBankUser == null ? null : repaymentBankUser.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure == null ? null : enclosure.trim();
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

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public Long getMortage() {
        return mortage;
    }

    public void setMortage(Long mortage) {
        this.mortage = mortage;
    }

    public Long getLoan() {
        return loan;
    }

    public void setLoan(Long loan) {
        this.loan = loan;
    }

    public String getAccountsBank() {
        return accountsBank;
    }

    public void setAccountsBank(String accountsBank) {
        this.accountsBank = accountsBank == null ? null : accountsBank.trim();
    }

    public String getRepaymentAccountsBank() {
        return repaymentAccountsBank;
    }

    public void setRepaymentAccountsBank(String repaymentAccountsBank) {
        this.repaymentAccountsBank = repaymentAccountsBank == null ? null : repaymentAccountsBank.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adminId=").append(adminId);
        sb.append(", leaglPerson=").append(leaglPerson);
        sb.append(", legalPersonMobile=").append(legalPersonMobile);
        sb.append(", bankAccount=").append(bankAccount);
        sb.append(", bankName=").append(bankName);
        sb.append(", bankUser=").append(bankUser);
        sb.append(", repaymentBankAccount=").append(repaymentBankAccount);
        sb.append(", repaymentBankName=").append(repaymentBankName);
        sb.append(", repaymentBankUser=").append(repaymentBankUser);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyCode=").append(companyCode);
        sb.append(", companyAddress=").append(companyAddress);
        sb.append(", enclosure=").append(enclosure);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", repayTime=").append(repayTime);
        sb.append(", mortage=").append(mortage);
        sb.append(", loan=").append(loan);
        sb.append(", accountsBank=").append(accountsBank);
        sb.append(", repaymentAccountsBank=").append(repaymentAccountsBank);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", area=").append(area);
        sb.append(", address=").append(address);
        sb.append(", name=").append(name);
        sb.append(", mobile=").append(mobile);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}