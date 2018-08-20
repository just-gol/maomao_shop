package com.maomao.ssm.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.UserOrdinary;
import com.maomao.ssm.service.admin.AccountService;

/**
 * 账号管理
 * 
 * @author Administrator
 * @date:2018年2月27日
 */
@Controller
@RequestMapping(value = "/user")
public class AccountController {
	@Autowired
	private AccountService accountService;

	// 删除用户信息(包括批量,个人&上架)
	@RequestMapping(value = "/delUser.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData delUser(Integer[] userIds, Byte type) {
		return accountService.delUser(userIds, type);
	}

	// 用户密码重置(包括批量,个人&商家)
	@RequestMapping(value = "/ResetUserPassword.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData ResetUserPassword(Integer[] userIds, Byte type) {
		return accountService.ResetUserPassword(userIds, type);
	}

	// 用户分页查询
	@RequestMapping(value = "/getIndividualUserList.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getIndividualUserList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, String queryString, Byte type) {
		return accountService.getIndividualUserList(pages, rows, queryString, type);
	}

	/**
	 * 用户添加
	 */
	@RequestMapping(value = "/addIndividualUser.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addIndividualUser(String bankName, String mobile, String name, String idCard, String account,
			String userName, String invitation, String accountsBank, Byte type) {
		return accountService.addIndividualUser(bankName, mobile, name, idCard, account, userName, invitation,
				accountsBank, type);
	}

	// 编辑用户信息
	@RequestMapping(value = "/editIndividualUser.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData editIndividualUser(Integer userId, Byte type) {
		return accountService.editIndividualUser(userId, type);
	}

	// 修改用户信息
	@RequestMapping(value = "/updateIndividualUser.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateIndividualUser(Integer userId, String bankName, String mobile, String name, String idCard,
			String account, String userName, String invitation, String accountsBank, Byte type) {
		return accountService.updateIndividualUser(userId, bankName, mobile, name, idCard, account, userName,
				invitation, accountsBank, type);
	}

	// 管理员分页查询
	@RequestMapping(value = "/getSupplierUserList.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getSupplierUserList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, String queryString, Byte type) {
		return accountService.getSupplierUserList(pages, rows, queryString, type);
	}

	/**
	 * 管理员添加
	 *addressName:联系人
	 *addressMobile:联系方式
	 * @return
	 */
	@RequestMapping(value = "/addSupplierUser.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addSupplierUser(String mobile,String password,String name,String idCard,Byte sex, String bankAccount,String bankName,String bankUser,String companyAddress,String legalPersion,String legalPersonMobile,String repaymentBankAccount,String repaymentBankUser,String repaymentBankName,String companyName ,String companyCode,String enclosure,Integer roleId,Integer dataRoleId,String accountsBank,String repaymentAccountsBank,String province, String city,
			String area,String address, String addressMobile, String addressName, Byte type){
		return accountService.addSupplierUser(mobile, password, name, idCard, sex, bankAccount, bankName, bankUser,
				companyAddress, legalPersion, legalPersonMobile, repaymentBankAccount, repaymentBankUser,
				repaymentBankName, companyName, companyCode, enclosure, roleId, dataRoleId,accountsBank,repaymentAccountsBank,province,city,area,address,addressMobile,addressName, type);
	}

	// 编辑管理员信息
	@RequestMapping(value = "/editSupplierUser.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData editSupplierUser(Integer adminId, Byte type) {
		return accountService.editSupplierUser(adminId, type);
	}

	// 修改管理员信息
	@RequestMapping(value = "/updateSupplierUser.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateSupplierUser(Integer adminId, String mobile, String name, String idCard, Byte sex,
			String bankAccount, String bankName, String bankUser, String companyAddress, String legalPersion,
			String legalPersonMobile, String repaymentBankAccount, String repaymentBankUser, String repaymentBankName,
			String companyName, String companyCode, String enclosure, Integer roleId, Integer dataRoleId,String accountsBank,String repaymentAccountsBank,String province, String city,String area,String address, String addressMobile, String addressName, Byte type){
		return accountService.updateSupplierUser(adminId, mobile, name, idCard, sex, bankAccount, bankName, bankUser,
				companyAddress, legalPersion, legalPersonMobile, repaymentBankAccount, repaymentBankUser,
				repaymentBankName, companyName, companyCode, enclosure, roleId, dataRoleId, accountsBank, repaymentAccountsBank,province,city,area,address,addressMobile,addressName, type);
	}

	// 删除管理员信息
	@RequestMapping(value = "/delAdminUser.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData delAdminUser(Integer[] adminIds, Byte type) {
		return accountService.delAdminUser(adminIds, type);
	}

	// 管理员密码重置
	@RequestMapping(value = "/ResetAdminUserPassword.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData ResetAdminUserPassword(Integer[] adminIds, Byte type) {
		return accountService.ResetAdminUserPassword(adminIds, type);
	}

	// 用户数量查询
	@RequestMapping(value = "/getUserAndAdminList.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getUserAndAdminList(String queryString) {
		return accountService.getUserAndAdminList(queryString);
	}

	// 获取银行列表
	@RequestMapping(value = "/getBankList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getBankList() {
		return accountService.getBankList();
	}

	// 获取所有管理员
	@RequestMapping(value = "/getAdminAll.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getAdminAll(String mobile) {
		return accountService.getAdminAll(mobile);
	}
	
	/**
	 * 查询供应商列表
	 */
	@RequestMapping(value = "/getSupplierList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getSupplierList(String keyWords){
		return accountService.getSupplierList(keyWords);
	}
}
