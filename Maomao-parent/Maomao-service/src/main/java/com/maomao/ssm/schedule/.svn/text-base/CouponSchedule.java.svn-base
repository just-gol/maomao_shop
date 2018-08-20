package com.maomao.ssm.schedule;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.maomao.ssm.bean.CouponRecord;
import com.maomao.ssm.bean.CouponRecordCustom;
import com.maomao.ssm.bean.CouponRecordExample;
import com.maomao.ssm.constant.CouponConts;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.dao.CouponRecordMapper;
import com.maomao.ssm.dao.CouponRecordMapperCustom;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.service.common.NoticeRecordService;
import com.maomao.ssm.utils.DateUtils;

@Component
public class CouponSchedule {

	@Autowired
	private CouponRecordMapper couponRecordMapper;

	@Autowired
	private CouponRecordMapperCustom couponRecordMapperCustom;

	@Autowired
	private NoticeRecordService noticeRecordService;

	@Autowired
	private JedisClientPool jedisClientPool;

	/**
	 * 自动设置优惠券过期 每10分钟执行一次
	 */
	@Scheduled(cron = "0 0/10 * * * ?")
	public void updateCouponByExpire() {
		Integer REDIS_LIMIT_TIME = 9 * 60 + 30;
		String REDIS_LIMIT_NAME = "updateCouponByExpire";
		if (jedisClientPool.exists(REDIS_LIMIT_NAME)) {
			return;
		}
		jedisClientPool.set(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME + "");
		jedisClientPool.expire(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME);

		// 查询已过期状态未变的优惠券
		CouponRecordExample couponRecordExample = new CouponRecordExample();
		couponRecordExample.createCriteria().andValidityTimeLessThanOrEqualTo(new Date())
				.andStatusEqualTo(CouponConts.COUPON_RECORD_STATUS_UNUSED);
		CouponRecord couponRecord = new CouponRecord();
		couponRecord.setStatus(CouponConts.COUPON_RECORD_STATUS_EXPIRE);
		couponRecordMapper.updateByExampleSelective(couponRecord, couponRecordExample);
	}

	/**
	 * 自动添加优惠券过期通知 每天4点执行一次
	 */
	@Scheduled(cron = "0 0 4 * * ?")
	public void addNoticeByCouponExpire() {
		Integer REDIS_LIMIT_TIME = 23 * 60 * 60;
		String REDIS_LIMIT_NAME = "addNoticeByCouponExpire";
		if (jedisClientPool.exists(REDIS_LIMIT_NAME)) {
			return;
		}
		jedisClientPool.set(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME + "");
		jedisClientPool.expire(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME);

		// 查询已过期状态未变的优惠券
		CouponRecordExample couponRecordExample = new CouponRecordExample();
		couponRecordExample.createCriteria().andValidityTimeLessThanOrEqualTo(DateUtils.getAfterDayDate(1))
				.andStatusEqualTo(CouponConts.COUPON_RECORD_STATUS_UNUSED);
		List<CouponRecordCustom> couponRecords = couponRecordMapperCustom
				.getCouponRecordNumByExample(couponRecordExample);
		if (couponRecords == null || couponRecords.size() == 0) {
			return;
		}
		// 添加通知记录
		for (CouponRecordCustom couponRecord : couponRecords) {
			noticeRecordService.addNoticeRecord(couponRecord.getUserId(), NoticeConst.NOTICE_ID_COUPON_EXPIRE,
					NoticeConst.NOTICE_RECORD_TYPE_SYSTEM, new String[] { couponRecord.getCouponNum() + "" });
		}
	}
	
	/**
	 * 自动设置优惠券可用 每10分钟执行一次
	 */
	@Scheduled(cron = "0 5/10 * * * ?")
	public void updateCouponByStart() {
		Integer REDIS_LIMIT_TIME = 9 * 60 + 30;
		String REDIS_LIMIT_NAME = "updateCouponByStart";
		if (jedisClientPool.exists(REDIS_LIMIT_NAME)) {
			return;
		}
		jedisClientPool.set(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME + "");
		jedisClientPool.expire(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME);

		// 查询不可用状态的优惠券
		CouponRecordExample couponRecordExample = new CouponRecordExample();
		couponRecordExample.createCriteria().andUseStartTimeLessThanOrEqualTo(new Date())
				.andStatusEqualTo(CouponConts.COUPON_RECORD_STATUS_UNSTART);
		CouponRecord couponRecord = new CouponRecord();
		couponRecord.setStatus(CouponConts.COUPON_RECORD_STATUS_UNUSED);
		couponRecordMapper.updateByExampleSelective(couponRecord, couponRecordExample);
	}
}
