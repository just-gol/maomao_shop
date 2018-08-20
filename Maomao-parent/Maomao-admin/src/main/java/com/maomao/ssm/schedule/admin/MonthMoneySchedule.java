package com.maomao.ssm.schedule.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.maomao.ssm.bean.AdminMonthDetail;
import com.maomao.ssm.dao.AdminMonthDetailMapper;
import com.maomao.ssm.dao.AdminMonthDetailMapperCustom;
import com.maomao.ssm.pojo.JedisClientPool;

@Component
public class MonthMoneySchedule {

	@Autowired
	private AdminMonthDetailMapperCustom adminMonthDetailMapperCustom;
	@Autowired
	private AdminMonthDetailMapper adminMonthDetailMapper;
	@Autowired
	private JedisClientPool jedisClientPool;

	/**
	 * 添加月结记录 每月2号凌晨0点执行一次
	 */
	@Scheduled(cron = "0 0 0 2 * ?")
	public void addMonthDetail() {
		Integer REDIS_LIMIT_TIME = 29 * 24 * 60 * 60;
		String REDIS_LIMIT_NAME = "addMonthDetail";
		if (jedisClientPool.exists(REDIS_LIMIT_NAME)) {
			return;
		}
		jedisClientPool.set(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME + "");
		jedisClientPool.expire(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME);
		// 查询贷款还款
		// 查询货款
		List<AdminMonthDetail> adminMonthDetails = adminMonthDetailMapperCustom.getMonthDetailInsertList();
		// 添加月结记录
		for (AdminMonthDetail adminMonthDetail : adminMonthDetails) {
			adminMonthDetail.setCreateTime(new Date());
			adminMonthDetailMapper.insert(adminMonthDetail);
		}
	}
}
