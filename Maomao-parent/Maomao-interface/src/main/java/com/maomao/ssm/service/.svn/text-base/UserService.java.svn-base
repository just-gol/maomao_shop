package com.maomao.ssm.service;

import java.util.List;

import com.maomao.ssm.bean.User;
import com.maomao.ssm.pojo.JsonData;

public interface UserService {
	public List<User> getUsers();

	public User getUser(Integer id);

	public JsonData getHomeDetail(Integer userId);

	public JsonData getCouponRecordList(Integer userId, Integer pages, Integer rows, Byte type, Long money);

	public JsonData getCollectionList(Integer userId, Integer pages, Integer rows);

	public JsonData getAddressList(Integer userId, Integer pages, Integer rows);

	public JsonData addAddress(Integer userId, String name, String mobile, String province, String city, String area,
			String address, Byte isDefault);

	public JsonData updateAddressById(Integer userId, Integer addressId, String name, String mobile, String province,
			String city, String area, String address, Byte isDefault);

	public JsonData updateAddressByDefault(Integer userId, Integer addressId, Integer defaultAddressId);

	public JsonData deleteAddress(Integer userId, Integer addressId);

	public JsonData getUserDetail(Integer userId);

	public JsonData updateUserByIdCard(Integer userId, String name, String idCard, String code);

	public JsonData updatePasswordByPassword(Integer userId, String password, String newPassword);

	public JsonData updatePasswordBySms(Integer userId, String password, String code);

	public JsonData getFeedbackList(Integer userId, Integer pages, Integer rows);

	public JsonData addFeedback(Integer userId, String content);

	public JsonData getUserBankList(Integer userId, Integer pages, Integer rows);

	public JsonData addUserBank(Integer userId, String bankName, String account, String userName, String accountsBank);

	public JsonData deleteUserBank(Integer userId, Integer userBankId);

	public JsonData updateUserByInvitation(Integer userId, String invitation);

	public JsonData getBankList();

	public JsonData updatePasswordWithdrawals(Integer userId, String password);

	public JsonData updatePasswordWithdrawalsByPassword(Integer userId, String password, String newPassword);

	public JsonData updatePasswordWithdrawalsBySms(Integer userId, String password, String code);

	public JsonData adminLogin(String mobile, String password);

	public JsonData getAdminOrderCodeGoodsInfoList(Integer pages, Integer rows, Integer adminId);

	public JsonData updateOrderGoodsInfoByUse(Integer adminId, String code);

}
