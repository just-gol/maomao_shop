package com.maomao.ssm.service.admin;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.pojo.UserCompany;
import com.maomao.ssm.pojo.UserIndividual;
import com.maomao.ssm.pojo.UserOrdinary;

public interface AccountService {

	//删除普通用户信息
	JsonData delUser(Integer[] userIds,Byte type);

	//用户密码重置
	JsonData ResetUserPassword(Integer[] userIds,Byte type);

	// 分页账号查询
	JsonData getIndividualUserList(Integer pages, Integer rows,String queryString,Byte type);

	// 用户添加
	JsonData addIndividualUser(String bankName,String mobile,String name,String idCard,String account,String userName,String invitation,String accountsBank,Byte type);

	// 编辑用户信息
	JsonData editIndividualUser(Integer userId,Byte type);

	//修改用户信息
	JsonData updateIndividualUser(Integer userId,String bankName,String mobile, String name, String idCard,
			String account, String userName, String invitation,String accountsBank,Byte type);

	//供应商分页查询
	JsonData getSupplierUserList(Integer pages, Integer rows,String queryString,Byte type);

	//供应商添加
	JsonData addSupplierUser(String mobile,String password,String name,String idCard,Byte sex, String bankAccount,String bankName,String bankUser,String companyAddress,String legalPersion,String legalPersonMobile,String repaymentBankAccount,String repaymentBankUser,String repaymentBankName,String companyName ,String companyCode,String enclosure,Integer roleId,Integer dataRoleId,String accountsBank,String repaymentAccountsBank,String province, String city,
			String area,String address, String addressMobile, String addressName, Byte type);

	// 编辑供应商信息
	JsonData editSupplierUser(Integer adminId,Byte type);

	// 修改供应商信息
	JsonData updateSupplierUser(Integer adminId, String mobile, String name, String idCard, Byte sex,
			String bankAccount, String bankName, String bankUser, String companyAddress, String legalPersion,
			String legalPersonMobile, String repaymentBankAccount, String repaymentBankUser, String repaymentBankName,
			String companyName, String companyCode, String enclosure, Integer roleId, Integer dataRoleId,String accountsBank,String repaymentAccountsBank,String province, String city,String area,String address, String addressMobile, String addressName, Byte type);

	JsonData delAdminUser(Integer[] adminIds,Byte type);

	JsonData ResetAdminUserPassword(Integer[] adminIds,Byte type);

	JsonData getUserAndAdminList(String queryString);

	JsonData getBankList();

	JsonData getAdminAll(String mobile);

	Integer getAdminId(String mapValue);

	JsonData getSupplierList(String keyWords);
}
