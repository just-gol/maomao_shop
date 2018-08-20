package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.maomao.ssm.bean.GoodsOverstockUserCustom;
import com.maomao.ssm.bean.User;

public class UserShopDetailVO implements Serializable {
	private static final long serialVersionUID = -5346387905190490665L;
	private Integer userId;
	private String mobile;
	private String name;
	private String idCard;
	private String applyMobile;
	private Long creditSurplus;
	private Long creditUsed;
	private Integer onSalesNum;
	private Integer onSalesSubscriptionNum;
	private Integer onSalesNormalNum;
	private Integer subscriptionNum;
	private Integer shareOrderBuyNum;
	private Integer orderBuyNum;
	private Long depositPaid;
	private Long salesMoney;
	private Long salesMoneyIn30Day;
	private Long salesMoneyIn60Day;
	private Long salesMoneyIn90Day;
	private Long increaseAvg;
	private Long refundMoney;
	private Long refundMoneyIn30Day;
	private Integer refundNumIn30Day;
	private Long refundMoneyIn60Day;
	private Integer refundNumIn60Day;
	private Long refundMoneyIn90Day;
	private Integer refundNumIn90Day;
	private Long accumulativeMoney;
	private Integer userWithdrawalsNum;
	private Date userWithdrawalsLastTime;
	private Long userWithdrawalsLastMoney;
	private List<GoodsOverstockUserCustom> goodsOverstockUserCustoms;

	public UserShopDetailVO() {
		super();
	}

	public UserShopDetailVO(User user, String applyMobile, Integer onSalesNum, Integer onSalesSubscriptionNum,
			Integer onSalesNormalNum, Integer subscriptionNum, Integer shareOrderBuyNum, Integer orderBuyNum,
			Long depositPaid, Long salesMoney, Long salesMoneyIn30Day, Long salesMoneyIn60Day, Long salesMoneyIn90Day,
			Long increaseAvg, Long refundMoney, Long refundMoneyIn30Day, Integer refundNumIn30Day,
			Long refundMoneyIn60Day, Integer refundNumIn60Day, Long refundMoneyIn90Day, Integer refundNumIn90Day,
			Integer userWithdrawalsNum, Date userWithdrawalsLastTime, Long userWithdrawalsLastMoney,
			List<GoodsOverstockUserCustom> goodsOverstockUserCustoms) {
		super();
		this.userId = user.getId();
		this.mobile = user.getMobile();
		this.name = user.getName();
		this.idCard = user.getIdCard();
		this.applyMobile = applyMobile;
		this.creditSurplus = user.getCreditSurplus();
		this.creditUsed = user.getCreditTotal() - user.getCreditSurplus();
		this.onSalesNum = onSalesNum;
		this.onSalesSubscriptionNum = onSalesSubscriptionNum;
		this.onSalesNormalNum = onSalesNormalNum;
		this.subscriptionNum = subscriptionNum;
		this.shareOrderBuyNum = shareOrderBuyNum;
		this.orderBuyNum = orderBuyNum;
		this.depositPaid = depositPaid;
		this.salesMoney = salesMoney;
		this.salesMoneyIn30Day = salesMoneyIn30Day;
		this.salesMoneyIn60Day = salesMoneyIn60Day;
		this.salesMoneyIn90Day = salesMoneyIn90Day;
		this.increaseAvg = increaseAvg;
		this.refundMoney = refundMoney;
		this.refundMoneyIn30Day = refundMoneyIn30Day;
		this.refundNumIn30Day = refundNumIn30Day;
		this.refundMoneyIn60Day = refundMoneyIn60Day;
		this.refundNumIn60Day = refundNumIn60Day;
		this.refundMoneyIn90Day = refundMoneyIn90Day;
		this.refundNumIn90Day = refundNumIn90Day;
		this.accumulativeMoney = user.getAccumulativeMoney();
		this.userWithdrawalsNum = userWithdrawalsNum;
		this.userWithdrawalsLastTime = userWithdrawalsLastTime;
		this.userWithdrawalsLastMoney = userWithdrawalsLastMoney;
		this.goodsOverstockUserCustoms = goodsOverstockUserCustoms;
	}

	public UserShopDetailVO(Integer userId, String mobile, String name, String idCard, String applyMobile,
			Long creditSurplus, Long creditUsed, Integer onSalesNum, Integer onSalesSubscriptionNum,
			Integer onSalesNormalNum, Integer subscriptionNum, Integer shareOrderBuyNum, Integer orderBuyNum,
			Long depositPaid, Long salesMoney, Long salesMoneyIn30Day, Long salesMoneyIn60Day, Long salesMoneyIn90Day,
			Long increaseAvg, Long refundMoney, Long refundMoneyIn30Day, Integer refundNumIn30Day,
			Long refundMoneyIn60Day, Integer refundNumIn60Day, Long refundMoneyIn90Day, Integer refundNumIn90Day,
			Long accumulativeMoney, Integer userWithdrawalsNum, Date userWithdrawalsLastTime,
			Long userWithdrawalsLastMoney, List<GoodsOverstockUserCustom> goodsOverstockUserCustoms) {
		super();
		this.userId = userId;
		this.mobile = mobile;
		this.name = name;
		this.idCard = idCard;
		this.applyMobile = applyMobile;
		this.creditSurplus = creditSurplus;
		this.creditUsed = creditUsed;
		this.onSalesNum = onSalesNum;
		this.onSalesSubscriptionNum = onSalesSubscriptionNum;
		this.onSalesNormalNum = onSalesNormalNum;
		this.subscriptionNum = subscriptionNum;
		this.shareOrderBuyNum = shareOrderBuyNum;
		this.orderBuyNum = orderBuyNum;
		this.depositPaid = depositPaid;
		this.salesMoney = salesMoney;
		this.salesMoneyIn30Day = salesMoneyIn30Day;
		this.salesMoneyIn60Day = salesMoneyIn60Day;
		this.salesMoneyIn90Day = salesMoneyIn90Day;
		this.increaseAvg = increaseAvg;
		this.refundMoney = refundMoney;
		this.refundMoneyIn30Day = refundMoneyIn30Day;
		this.refundNumIn30Day = refundNumIn30Day;
		this.refundMoneyIn60Day = refundMoneyIn60Day;
		this.refundNumIn60Day = refundNumIn60Day;
		this.refundMoneyIn90Day = refundMoneyIn90Day;
		this.refundNumIn90Day = refundNumIn90Day;
		this.accumulativeMoney = accumulativeMoney;
		this.userWithdrawalsNum = userWithdrawalsNum;
		this.userWithdrawalsLastTime = userWithdrawalsLastTime;
		this.userWithdrawalsLastMoney = userWithdrawalsLastMoney;
		this.goodsOverstockUserCustoms = goodsOverstockUserCustoms;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getApplyMobile() {
		return applyMobile;
	}

	public void setApplyMobile(String applyMobile) {
		this.applyMobile = applyMobile;
	}

	public Long getCreditSurplus() {
		return creditSurplus;
	}

	public void setCreditSurplus(Long creditSurplus) {
		this.creditSurplus = creditSurplus;
	}

	public Long getCreditUsed() {
		return creditUsed;
	}

	public void setCreditUsed(Long creditUsed) {
		this.creditUsed = creditUsed;
	}

	public Integer getOnSalesNum() {
		return onSalesNum;
	}

	public void setOnSalesNum(Integer onSalesNum) {
		this.onSalesNum = onSalesNum;
	}

	public Integer getOnSalesSubscriptionNum() {
		return onSalesSubscriptionNum;
	}

	public void setOnSalesSubscriptionNum(Integer onSalesSubscriptionNum) {
		this.onSalesSubscriptionNum = onSalesSubscriptionNum;
	}

	public Integer getOnSalesNormalNum() {
		return onSalesNormalNum;
	}

	public void setOnSalesNormalNum(Integer onSalesNormalNum) {
		this.onSalesNormalNum = onSalesNormalNum;
	}

	public Integer getSubscriptionNum() {
		return subscriptionNum;
	}

	public void setSubscriptionNum(Integer subscriptionNum) {
		this.subscriptionNum = subscriptionNum;
	}

	public Integer getShareOrderBuyNum() {
		return shareOrderBuyNum;
	}

	public void setShareOrderBuyNum(Integer shareOrderBuyNum) {
		this.shareOrderBuyNum = shareOrderBuyNum;
	}

	public Integer getOrderBuyNum() {
		return orderBuyNum;
	}

	public void setOrderBuyNum(Integer orderBuyNum) {
		this.orderBuyNum = orderBuyNum;
	}

	public Long getDepositPaid() {
		return depositPaid;
	}

	public void setDepositPaid(Long depositPaid) {
		this.depositPaid = depositPaid;
	}

	public Long getSalesMoney() {
		return salesMoney;
	}

	public void setSalesMoney(Long salesMoney) {
		this.salesMoney = salesMoney;
	}

	public Long getSalesMoneyIn30Day() {
		return salesMoneyIn30Day;
	}

	public void setSalesMoneyIn30Day(Long salesMoneyIn30Day) {
		this.salesMoneyIn30Day = salesMoneyIn30Day;
	}

	public Long getSalesMoneyIn60Day() {
		return salesMoneyIn60Day;
	}

	public void setSalesMoneyIn60Day(Long salesMoneyIn60Day) {
		this.salesMoneyIn60Day = salesMoneyIn60Day;
	}

	public Long getSalesMoneyIn90Day() {
		return salesMoneyIn90Day;
	}

	public void setSalesMoneyIn90Day(Long salesMoneyIn90Day) {
		this.salesMoneyIn90Day = salesMoneyIn90Day;
	}

	public Long getIncreaseAvg() {
		return increaseAvg;
	}

	public void setIncreaseAvg(Long increaseAvg) {
		this.increaseAvg = increaseAvg;
	}

	public Long getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Long refundMoney) {
		this.refundMoney = refundMoney;
	}

	public Long getRefundMoneyIn30Day() {
		return refundMoneyIn30Day;
	}

	public void setRefundMoneyIn30Day(Long refundMoneyIn30Day) {
		this.refundMoneyIn30Day = refundMoneyIn30Day;
	}

	public Integer getRefundNumIn30Day() {
		return refundNumIn30Day;
	}

	public void setRefundNumIn30Day(Integer refundNumIn30Day) {
		this.refundNumIn30Day = refundNumIn30Day;
	}

	public Long getRefundMoneyIn60Day() {
		return refundMoneyIn60Day;
	}

	public void setRefundMoneyIn60Day(Long refundMoneyIn60Day) {
		this.refundMoneyIn60Day = refundMoneyIn60Day;
	}

	public Integer getRefundNumIn60Day() {
		return refundNumIn60Day;
	}

	public void setRefundNumIn60Day(Integer refundNumIn60Day) {
		this.refundNumIn60Day = refundNumIn60Day;
	}

	public Long getRefundMoneyIn90Day() {
		return refundMoneyIn90Day;
	}

	public void setRefundMoneyIn90Day(Long refundMoneyIn90Day) {
		this.refundMoneyIn90Day = refundMoneyIn90Day;
	}

	public Integer getRefundNumIn90Day() {
		return refundNumIn90Day;
	}

	public void setRefundNumIn90Day(Integer refundNumIn90Day) {
		this.refundNumIn90Day = refundNumIn90Day;
	}

	public Long getAccumulativeMoney() {
		return accumulativeMoney;
	}

	public void setAccumulativeMoney(Long accumulativeMoney) {
		this.accumulativeMoney = accumulativeMoney;
	}

	public Integer getUserWithdrawalsNum() {
		return userWithdrawalsNum;
	}

	public void setUserWithdrawalsNum(Integer userWithdrawalsNum) {
		this.userWithdrawalsNum = userWithdrawalsNum;
	}

	public Date getUserWithdrawalsLastTime() {
		return userWithdrawalsLastTime;
	}

	public void setUserWithdrawalsLastTime(Date userWithdrawalsLastTime) {
		this.userWithdrawalsLastTime = userWithdrawalsLastTime;
	}

	public Long getUserWithdrawalsLastMoney() {
		return userWithdrawalsLastMoney;
	}

	public void setUserWithdrawalsLastMoney(Long userWithdrawalsLastMoney) {
		this.userWithdrawalsLastMoney = userWithdrawalsLastMoney;
	}

	public List<GoodsOverstockUserCustom> getGoodsOverstockUserCustoms() {
		return goodsOverstockUserCustoms;
	}

	public void setGoodsOverstockUserCustoms(List<GoodsOverstockUserCustom> goodsOverstockUserCustoms) {
		this.goodsOverstockUserCustoms = goodsOverstockUserCustoms;
	}

}
