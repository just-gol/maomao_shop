package com.maomao.ssm.service;

import com.maomao.ssm.bean.UserApply;
import com.maomao.ssm.pojo.JsonData;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年1月27日
 */

public interface ShopService {
	JsonData getShopStatus(Integer userId, Byte type);

	JsonData addApplyShop(UserApply userApply);

	JsonData getAccountDetail(Integer userId);

	JsonData getApplyCredit(Integer userId);

	JsonData addApplyCredit(Integer userId, Long credit);

	JsonData getOnSaleGoods(Integer userId, String keywords);

	JsonData getOnSubscriptionGoods(Integer userId);

	JsonData setWithdraw(Integer userId, String bankName, String account, Long money, String userName, String password,
			Byte category,String accountsBank);

	JsonData setGoodsOffSale(Integer onSaleGoodsId);

	JsonData addGoodsByUser(Integer userId, Integer goodsId);

	JsonData getPasswordWithdrawals(Integer userId);

}

















