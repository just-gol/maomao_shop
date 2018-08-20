package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.List;

import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.AdminInfo;

/**
 * 公司店铺用户
 * @author Administrator
 *
 */
public class UserCompany implements Serializable{
	private Integer adminId;
	private Integer adminInfoId;
	private List<Integer> roleList;//角色集合
	private List<Integer> dataRoleList; //数据角色id
	private String mobile; //手机
	private String password; //密码
	private String name; //姓名
	private String idCard; //身份证
	private Byte sex; //性别 1:男 2:女

	private String bankAccount; //收款账户
	private String bankName; //收款银行
	private String bankUser; //收款户名

	private String companyAddress; //地址	
	private String legalPerson; //法人
	
	private String legalPersonMobile;//法人联系方式
	private String repaymentBankAccount; //还款账户
	private String repaymentBankName; //还款开户行
	private String repaymentBankUser; //还款户名

	private String companyName;//企业名称
	private String companyCode;//企业信用代码
	private String enclosure; //附件
	
	//仓储地址
	private String province;
	private String city;
	private String area;
	private String address;
	private String addressMobile;
	private String addressName;
	
	public UserCompany() {
	}
	public UserCompany(Admin amdin , AdminInfo adminInfo) {
		super();
		this.adminId = amdin.getId();
		this.adminInfoId = adminInfo.getId();
		this.mobile = amdin.getMobile();
		this.password = amdin.getPassword();
		this.name = amdin.getName();
		this.idCard = amdin.getIdCard();
		this.sex = amdin.getSex();
		this.bankAccount = adminInfo.getBankAccount();
		this.bankName = adminInfo.getBankName();
		this.bankUser = adminInfo.getBankUser();
		this.companyAddress = adminInfo.getCompanyAddress();
		this.legalPerson = adminInfo.getLeaglPerson();
		this.legalPersonMobile = adminInfo.getLegalPersonMobile();
		this.repaymentBankAccount = adminInfo.getRepaymentBankAccount();
		this.repaymentBankName = adminInfo.getRepaymentBankAccount();
		this.repaymentBankUser = adminInfo.getRepaymentBankAccount();
		this.companyName = adminInfo.getCompanyName();
		this.companyCode = adminInfo.getCompanyCode();
		this.enclosure = adminInfo.getEnclosure();
		this.province =	adminInfo.getProvince();
		this.city = adminInfo.getCity();
		this.area = adminInfo.getArea();
		this.address = adminInfo.getAddress();
		this.addressMobile = adminInfo.getMobile();
		this.addressName = adminInfo.getName();
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public Integer getAdminInfoId() {
		return adminInfoId;
	}
	public void setAdminInfoId(Integer adminInfoId) {
		this.adminInfoId = adminInfoId;
	}
	public List<Integer> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Integer> roleList) {
		this.roleList = roleList;
	}
	public List<Integer> getDataRoleList() {
		return dataRoleList;
	}
	public void setDataRoleList(List<Integer> dataRoleList) {
		this.dataRoleList = dataRoleList;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Byte getSex() {
		return sex;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankUser() {
		return bankUser;
	}
	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getLegalPersonMobile() {
		return legalPersonMobile;
	}
	public void setLegalPersonMobile(String legalPersonMobile) {
		this.legalPersonMobile = legalPersonMobile;
	}
	public String getRepaymentBankAccount() {
		return repaymentBankAccount;
	}
	public void setRepaymentBankAccount(String repaymentBankAccount) {
		this.repaymentBankAccount = repaymentBankAccount;
	}
	public String getRepaymentBankName() {
		return repaymentBankName;
	}
	public void setRepaymentBankName(String repaymentBankName) {
		this.repaymentBankName = repaymentBankName;
	}
	public String getRepaymentBankUser() {
		return repaymentBankUser;
	}
	public void setRepaymentBankUser(String repaymentBankUser) {
		this.repaymentBankUser = repaymentBankUser;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getEnclosure() {
		return enclosure;
	}
	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressMobile() {
		return addressMobile;
	}
	public void setAddressMobile(String addressMobile) {
		this.addressMobile = addressMobile;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
}
