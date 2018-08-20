package com.maomao.ssm.schedule.admin;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.maomao.ssm.bean.Notice;
import com.maomao.ssm.bean.NoticeExample;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.dao.NoticeMapper;
import com.maomao.ssm.dao.NoticeRecordMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.dao.NoticeMapperCustomer;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.common.NoticeRecordService;
import com.maomao.ssm.utils.JsonUtils;

@Component
public class NoticeSchedule {
	@Autowired
	private NoticeMapperCustomer noticeMapperCustomer;
	@Autowired
	private JedisClientPool jedisClientPool;
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private NoticeRecordService noticeRecordService;
	@Autowired
	private UserMapper userMapper;

	/**
	 * 消息倒计时发送
	 */
	@Scheduled(cron = "0 0/60 * * * ?")
	public void getCountdownNoticeRecordList() {
//		System.out.println("消息倒计时:"+new Date());
		// 查询缓存
		if (jedisClientPool.exists(RedisConst.NOTICE_GET_ALL)) {
			String notice = jedisClientPool.get(RedisConst.NOTICE_GET_ALL);
			if (StringUtils.isNotBlank(notice)) {
				return;
			}
		}
		jedisClientPool.set(RedisConst.NOTICE_GET_ALL, "");
		jedisClientPool.expire(RedisConst.NOTICE_GET_ALL,RedisConst.NOTICE_EXPIRE_TIME);
	//	List<Notice> noticeList = noticeMapperCustomer.getCountdownNoticeRecordList();
		
		//获取未发送的消息集合
		NoticeExample noticeExample = new NoticeExample();
		noticeExample.createCriteria().andStatusEqualTo(NoticeConst.NOTICE_STATUS_ENABLE).andTypeEqualTo((byte)0).andSendTimeLessThanOrEqualTo(new Date());
		List<Notice> noticeList = noticeMapper.selectByExample(noticeExample);
		
		// 获取用户列表
		List<User> userList = userMapper.selectByExample(null);

		if (noticeList != null && noticeList.size() > 0) {
			for (Notice notice : noticeList) {
				notice.setStatus(NoticeConst.NOTICE_STATUS_SUC);
				noticeMapper.updateByPrimaryKeySelective(notice);
				Integer noticeId = notice.getId();
				if (userList != null && userList.size() > 0) {
					for (User user : userList) {
						noticeRecordService.addNoticeRecord(user.getId(), noticeId,
								NoticeConst.NOTICE_RECORD_TYPE_SYSTEM, null);
					}
				}
			}
		}
	}
}
