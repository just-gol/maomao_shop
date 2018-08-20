package com.maomao.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.UserService;

@Controller
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/home.action")
	@ResponseBody
	public JsonData home(Integer userId) {
		return userService.getHomeDetail(userId);
	}

	@RequestMapping(value = "/getCouponRecordList.action")
	@ResponseBody
	public JsonData getCouponRecordList(Integer userId, Integer pages, Integer rows, Byte type, Long money) {
		return userService.getCouponRecordList(userId, pages, rows, type, money);
	}

	@RequestMapping(value = "/getCollectionList.action")
	@ResponseBody
	public JsonData getCollectionList(Integer userId, Integer pages, Integer rows) {
		return userService.getCollectionList(userId, pages, rows);
	}

	@RequestMapping(value = "/getAddressList.action")
	@ResponseBody
	public JsonData getAddressList(Integer userId, Integer pages, Integer rows) {
		return userService.getAddressList(userId, pages, rows);
	}

	@RequestMapping(value = "/addAddress.action")
	@ResponseBody
	public JsonData addAddress(Integer userId, String name, String mobile, String province, String city, String area,
			String address, Byte isDefault) {
		return userService.addAddress(userId, name, mobile, province, city, area, address, isDefault);
	}

	@RequestMapping(value = "/updateAddressById.action")
	@ResponseBody
	public JsonData updateAddressById(Integer userId, Integer addressId, String name, String mobile, String province,
			String city, String area, String address, Byte isDefault) {
		return userService.updateAddressById(userId, addressId, name, mobile, province, city, area, address, isDefault);
	}

	@RequestMapping(value = "/updateAddressByDefault.action")
	@ResponseBody
	public JsonData updateAddressByDefault(Integer userId, Integer addressId, Integer defaultAddressId) {
		return userService.updateAddressByDefault(userId, addressId, defaultAddressId);
	}

	@RequestMapping(value = "/deleteAddress.action")
	@ResponseBody
	public JsonData deleteAddress(Integer userId, Integer addressId) {
		return userService.deleteAddress(userId, addressId);
	}

	@RequestMapping(value = "/getUserDetail.action")
	@ResponseBody
	public JsonData getUserDetail(Integer userId) {
		return userService.getUserDetail(userId);
	}

	@RequestMapping(value = "/updateUserByIdCard.action")
	@ResponseBody
	public JsonData updateUserByIdCard(Integer userId, String name, String idCard, String code) {
		return userService.updateUserByIdCard(userId, name, idCard, code);
	}

	@RequestMapping(value = "/updatePasswordByPassword.action")
	@ResponseBody
	public JsonData updatePasswordByPassword(Integer userId, String password, String newPassword) {
		return userService.updatePasswordByPassword(userId, password, newPassword);
	}

	@RequestMapping(value = "/updatePasswordBySms.action")
	@ResponseBody
	public JsonData updatePasswordBySms(Integer userId, String password, String code) {
		return userService.updatePasswordBySms(userId, password, code);
	}

	@RequestMapping(value = "/getFeedbackList.action")
	@ResponseBody
	public JsonData getFeedbackList(Integer userId, Integer pages, Integer rows) {
		return userService.getFeedbackList(userId, pages, rows);
	}

	@RequestMapping(value = "/addFeedback.action")
	@ResponseBody
	public JsonData addFeedback(Integer userId, String content) {
		return userService.addFeedback(userId, content);
	}

	@RequestMapping(value = "/getUserBankList.action")
	@ResponseBody
	public JsonData getUserBankList(Integer userId, Integer pages, Integer rows) {
		return userService.getUserBankList(userId, pages, rows);
	}

	@RequestMapping(value = "/addUserBank.action")
	@ResponseBody
	public JsonData addUserBank(Integer userId, String bankName, String account, String userName, String accountsBank) {
		return userService.addUserBank(userId, bankName, account, userName, accountsBank);
	}

	@RequestMapping(value = "/deleteUserBank.action")
	@ResponseBody
	public JsonData deleteUserBank(Integer userId, Integer userBankId) {
		return userService.deleteUserBank(userId, userBankId);
	}

	@RequestMapping(value = "/updateUserByInvitation.action")
	@ResponseBody
	public JsonData updateUserByInvitation(Integer userId, String invitation) {
		return userService.updateUserByInvitation(userId, invitation);
	}

	@RequestMapping(value = "/getBankList.action")
	@ResponseBody
	public JsonData getBankList() {
		return userService.getBankList();
	}

	@RequestMapping(value = "/updatePasswordWithdrawals.action")
	@ResponseBody
	public JsonData updatePasswordWithdrawals(Integer userId, String password) {
		return userService.updatePasswordWithdrawals(userId, password);
	}

	@RequestMapping(value = "/updatePasswordWithdrawalsByPassword.action")
	@ResponseBody
	public JsonData updatePasswordWithdrawalsByPassword(Integer userId, String password, String newPassword) {
		return userService.updatePasswordWithdrawalsByPassword(userId, password, newPassword);
	}

	@RequestMapping(value = "/updatePasswordWithdrawalsBySms.action")
	@ResponseBody
	public JsonData updatePasswordWithdrawalsBySms(Integer userId, String password, String code) {
		return userService.updatePasswordWithdrawalsBySms(userId, password, code);
	}

	@RequestMapping(value = "/adminLogin.action")
	@ResponseBody
	public JsonData adminLogin(String mobile, String password) {
		return userService.adminLogin(mobile, password);
	}

	@RequestMapping(value = "/getAdminOrderCodeGoodsInfoList.action")
	@ResponseBody
	public JsonData getAdminOrderCodeGoodsInfoList(Integer pages, Integer rows, Integer adminId) {
		return userService.getAdminOrderCodeGoodsInfoList(pages, rows, adminId);
	}

	@RequestMapping(value = "/updateOrderGoodsInfoByUse.action")
	@ResponseBody
	public JsonData updateOrderGoodsInfoByUse(Integer adminId, String code) {
		return userService.updateOrderGoodsInfoByUse(adminId, code);
	}
}
