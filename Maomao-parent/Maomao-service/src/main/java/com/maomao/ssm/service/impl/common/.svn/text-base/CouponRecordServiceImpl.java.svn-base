package com.maomao.ssm.service.impl.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maomao.ssm.bean.Coupon;
import com.maomao.ssm.bean.CouponRecord;
import com.maomao.ssm.constant.CouponConts;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.dao.CouponMapper;
import com.maomao.ssm.dao.CouponRecordMapper;
import com.maomao.ssm.service.common.CouponRecordService;
import com.maomao.ssm.service.common.NoticeRecordService;
import com.maomao.ssm.utils.DateUtils;

@Service
public class CouponRecordServiceImpl implements CouponRecordService {

	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private CouponRecordMapper couponRecordMapper;
	@Autowired
	private NoticeRecordService noticeRecordService;

	@Override
	public List<Integer[]> addCouponRecord(Integer userId, List<Coupon> coupons) {
		if (coupons == null || coupons.size() == 0) {
			return null;
		}
		List<Integer[]> idList = new ArrayList<Integer[]>();
		for (Coupon coupon : coupons) {
			// 判断库存
			if (coupon.getStock() == 0) {
				continue;
			}
			// 判断时间
			if (coupon.getStartTime() != null && (DateUtils.compareDate(coupon.getStartTime(), new Date())
					|| DateUtils.compareDate(new Date(), coupon.getEndTime()))) {
				continue;
			}
			// 发放张数
			Integer getNum = 0;
			if (coupon.getStock() == -1 || coupon.getStock() >= coupon.getGetNum()) {
				getNum = coupon.getGetNum();
			} else {
				getNum = coupon.getStock();
			}

			Integer[] ids = new Integer[getNum];
			for (int i = 0; i < getNum; i++) {
				ids[i] = addCouponRecord(userId, coupon);
			}
			// 减少库存
			if (coupon.getStock() != -1) {
				coupon.setStock(coupon.getStock() - getNum);
				couponMapper.updateByPrimaryKey(coupon);
			}
			idList.add(ids);
		}
		return idList;
	}

	private Integer addCouponRecord(Integer userId, Coupon coupon) {
		// 发放优惠券
		CouponRecord couponRecord = new CouponRecord();
		couponRecord.setCouponId(coupon.getId());
		couponRecord.setUserId(userId);
		couponRecord.setCreateTime(new Date());
		if (coupon.getUseStartTime() != null) {
			couponRecord.setValidityTime(DateUtils.addDays(coupon.getUseStartTime(), coupon.getValidityTerm()));
			couponRecord.setUseStartTime(coupon.getUseStartTime());
		} else {
			couponRecord.setValidityTime(DateUtils.addDays(new Date(), coupon.getValidityTerm()));
			couponRecord.setUseStartTime(new Date());
		}
		if (coupon.getUseStartTime() == null || DateUtils.compareDate(new Date(), coupon.getUseStartTime())) {
			couponRecord.setStatus(CouponConts.COUPON_RECORD_STATUS_UNUSED);
		} else {
			couponRecord.setStatus(CouponConts.COUPON_RECORD_STATUS_UNSTART);
		}
		couponRecord.setDiscount(coupon.getDiscount());
		couponRecord.setAvailableMoney(coupon.getAvaiableMoney());
		couponRecord.setType(coupon.getType());
		couponRecord.setName(coupon.getName());
		couponRecord.setIntroduction(coupon.getIntroduction());
		// 添加消息记录
		noticeRecordService.addNoticeRecord(userId, NoticeConst.NOTICE_ID_COUPON_GET,
				NoticeConst.NOTICE_RECORD_TYPE_SYSTEM, new String[] {});
		couponRecordMapper.insert(couponRecord);
		return couponRecord.getId();
	}

}
