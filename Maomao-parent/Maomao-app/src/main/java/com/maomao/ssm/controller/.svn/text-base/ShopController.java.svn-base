package com.maomao.ssm.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.bean.UserApply;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.ShopService;

/**
 * @author:wzy
 * @descrption:店铺controller
 * @version:
 * @date:2018年1月27日
 */
@Controller
@RequestMapping(value = "/shop", method = RequestMethod.POST)
public class ShopController {
	@Autowired
	private ShopService shopService;

	/**
	 * 我的店铺 author:wzy
	 */
	@RequestMapping("/myShop.action")
	@ResponseBody
	public JsonData getShopStatus(Integer userId, Byte type) {
		return shopService.getShopStatus(userId, type);
	}

	/**
	 * 提交开店申请 author:wzy
	 */
	@RequestMapping("/applyShop.action")
	@ResponseBody
	public JsonData addApplyShop(UserApply userApply) {
		userApply.setCreateTime(new Date());
		userApply.setCheckStatus(UserConts.USER_CHECK_STATUS_CHECKING);
		return shopService.addApplyShop(userApply);
	}

	/**
	 * 账户明细
	 */
	@RequestMapping("/accountDetail.action")
	@ResponseBody
	public JsonData getAccountDetail(Integer userId) {
		return shopService.getAccountDetail(userId);
	}

	/**
	 * 申请调额
	 *
	 */
	@RequestMapping("/applyCredit.action")
	@ResponseBody
	public JsonData getApplyCredit(Integer userId) {
		return shopService.getApplyCredit(userId);
	}

	/**
	 * 申请调额提交
	 *
	 */
	@RequestMapping("/addApplyCredit.action")
	@ResponseBody
	public JsonData addApplyCredit(Integer userId, Long credit) {
		return shopService.addApplyCredit(userId, credit);
	}

	/**
	 * 已上架商品
	 *
	 */
	@RequestMapping("/onSaleGoods.action")
	@ResponseBody
	public JsonData getOnSaleGoods(Integer userId, String keywords) {
		return shopService.getOnSaleGoods(userId, keywords);
	}

	/**
	 * 认筹中商品
	 *
	 */
	@RequestMapping("/onSubscription.action")
	@ResponseBody
	public JsonData getOnSubscription(Integer userId) {
		return shopService.getOnSubscriptionGoods(userId);
	}

	/**
	 * 提现
	 *
	 */
	@RequestMapping("/setWithdraw.action")
	@ResponseBody
	public JsonData setWithdraw(Integer userId, String bankName, String account, Long money, String userName,
			String password, Byte category,String accountsBank) {
		return shopService.setWithdraw(userId, bankName, account, money, userName, password, category,accountsBank);
	}

	/**
	 * 商品下架
	 *
	 */
	@RequestMapping("/closeSale.action")
	@ResponseBody
	public JsonData closeSale(Integer onSaleGoodsId) {
		return shopService.setGoodsOffSale(onSaleGoodsId);
	}

	/**
	 * 添加商品到我的店铺
	 *
	 */
	@RequestMapping("/addGoods.action")
	@ResponseBody
	public JsonData addGoodsByUser(Integer userId, Integer goodsId) {
		return shopService.addGoodsByUser(userId, goodsId);
	}

	/**
	 * 我要提现
	 *
	 */
	@RequestMapping("/getPasswordWithdrawals.action")
	@ResponseBody
	public JsonData getPasswordWithdrawals(Integer userId) {
		return shopService.getPasswordWithdrawals(userId);
	}
}
