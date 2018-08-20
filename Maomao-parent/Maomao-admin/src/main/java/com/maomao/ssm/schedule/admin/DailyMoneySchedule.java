package com.maomao.ssm.schedule.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.maomao.ssm.bean.CouponRecord;
import com.maomao.ssm.bean.CouponRecordExample;
import com.maomao.ssm.bean.DailyDetail;
import com.maomao.ssm.bean.GoodsOrder;
import com.maomao.ssm.bean.GoodsOrderExample;
import com.maomao.ssm.constant.CouponConts;
import com.maomao.ssm.constant.OrderConst;
import com.maomao.ssm.dao.CouponRecordMapper;
import com.maomao.ssm.dao.DailyDetailMapper;
import com.maomao.ssm.dao.GoodsOrderMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.utils.DateUtils;

@Component
public class DailyMoneySchedule {

	@Autowired
	private JedisClientPool jedisClientPool;
	@Autowired
	private GoodsOrderMapper goodsOrderMapper;
	@Autowired
	private CouponRecordMapper couponRecordMapper;
	@Autowired
	private DailyDetailMapper dailyDetailMapper;

	/**
	 * 添加日结记录 每天0点0分1秒执行一次
	 */
	@Scheduled(cron = "1 0 0 * * ?")
	public void addDailyDetail() {
		Integer REDIS_LIMIT_TIME = 23 * 60 * 60 + 30;
		String REDIS_LIMIT_NAME = "addDailyDetail";
		if (jedisClientPool.exists(REDIS_LIMIT_NAME)) {
			return;
		}
		jedisClientPool.set(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME + "");
		jedisClientPool.expire(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME);
		// 查询订单支付金额 退款金额 优惠券发放金额 使用金额 过期金额
		// 查询订单支付金额
		GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
		goodsOrderExample.createCriteria().andAccountTimeBetween(DateUtils.getYesterdayFirstSecond(),
				DateUtils.getYesterdayLastSecond());
		List<GoodsOrder> goodsOrderPay = goodsOrderMapper.selectByExample(goodsOrderExample);
		Integer orderPayNum = 0;
		Long orderPayMoney = 0l;
		if (goodsOrderPay != null && goodsOrderPay.size() > 0) {
			orderPayNum = goodsOrderPay.size();
			for (GoodsOrder goodsOrder : goodsOrderPay) {
				orderPayMoney += goodsOrder.getPayMoney();
			}
		}
		// 查询订单退款金额
		goodsOrderExample.clear();
		goodsOrderExample.createCriteria()
				.andOverTimeBetween(DateUtils.getYesterdayFirstSecond(), DateUtils.getYesterdayLastSecond())
				.andStatusEqualTo(OrderConst.ORDER_STATUS_REFUNDED);
		List<GoodsOrder> goodsOrderRefund = goodsOrderMapper.selectByExample(goodsOrderExample);
		Long orderRefundMoney = 0l;
		if (goodsOrderRefund != null && goodsOrderRefund.size() > 0) {
			for (GoodsOrder goodsOrder : goodsOrderRefund) {
				orderRefundMoney += goodsOrder.getPayMoney();
			}
		}
		// 查询优惠券发放金额
		CouponRecordExample couponRecordExample = new CouponRecordExample();
		couponRecordExample.createCriteria().andCreateTimeBetween(DateUtils.getYesterdayFirstSecond(),
				DateUtils.getYesterdayLastSecond());
		List<CouponRecord> couponRecordGet = couponRecordMapper.selectByExample(couponRecordExample);
		Integer couponGetNum = 0;
		Long couponGetMoney = 0l;
		if (couponRecordGet != null && couponRecordGet.size() > 0) {
			couponGetNum = couponRecordGet.size();
			for (CouponRecord couponRecord : couponRecordGet) {
				couponGetMoney += couponRecord.getDiscount();
			}
		}
		// 查询优惠券使用金额
		couponRecordExample.clear();
		couponRecordExample.createCriteria()
				.andUseTimeBetween(DateUtils.getYesterdayFirstSecond(), DateUtils.getYesterdayLastSecond())
				.andStatusEqualTo(CouponConts.COUPON_RECORD_STATUS_USED);
		List<CouponRecord> couponRecordUse = couponRecordMapper.selectByExample(couponRecordExample);
		Integer couponUseNum = 0;
		Long couponUseMoney = 0l;
		if (couponRecordUse != null && couponRecordUse.size() > 0) {
			couponUseNum = couponRecordUse.size();
			for (CouponRecord couponRecord : couponRecordUse) {
				couponUseMoney += couponRecord.getDiscount();
			}
		}
		// 查询优惠券过期金额
		couponRecordExample.clear();
		couponRecordExample.createCriteria()
				.andValidityTimeBetween(DateUtils.getYesterdayFirstSecond(), DateUtils.getYesterdayLastSecond())
				.andStatusEqualTo(CouponConts.COUPON_RECORD_STATUS_EXPIRE);
		List<CouponRecord> couponRecordExpire = couponRecordMapper.selectByExample(couponRecordExample);
		Integer couponExpireNum = 0;
		Long couponExpireMoney = 0l;
		if (couponRecordExpire != null && couponRecordExpire.size() > 0) {
			couponExpireNum = couponRecordExpire.size();
			for (CouponRecord couponRecord : couponRecordExpire) {
				couponExpireMoney += couponRecord.getDiscount();
			}
		}
		// 添加日结记录
		DailyDetail DailyDetail = new DailyDetail();
		DailyDetail.setCouponExpireMoney(couponExpireMoney);
		DailyDetail.setCouponExpireNum(couponExpireNum);
		DailyDetail.setCouponGetMoney(couponGetMoney);
		DailyDetail.setCouponGetNum(couponGetNum);
		DailyDetail.setCouponUseMoney(couponUseMoney);
		DailyDetail.setCouponUseNum(couponUseNum);
		DailyDetail.setOrderPayMoney(orderPayMoney);
		DailyDetail.setOrderPayNum(orderPayNum);
		DailyDetail.setOrderRefundMoney(orderRefundMoney);
		DailyDetail.setTime(DateUtils.getYesterdayFirstSecond());
		dailyDetailMapper.insert(DailyDetail);
	}
}
