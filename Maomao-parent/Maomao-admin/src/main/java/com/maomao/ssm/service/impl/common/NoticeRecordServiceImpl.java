package com.maomao.ssm.service.impl.common;

import java.text.MessageFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.status.StatusChecker;
import com.maomao.ssm.bean.Notice;
import com.maomao.ssm.bean.NoticeRecordWithBLOBs;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.NoticeMapper;
import com.maomao.ssm.dao.NoticeRecordMapper;
import com.maomao.ssm.service.common.NoticeRecordService;

@Service
public class NoticeRecordServiceImpl implements NoticeRecordService {

	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private NoticeRecordMapper noticeRecordMapper;

	@Override
	public Integer addNoticeRecord(Integer userId, Integer noticeId, Byte type, String[] params) {
		// 添加消息记录
		Notice notice = noticeMapper.selectByPrimaryKey(noticeId);
		NoticeRecordWithBLOBs noticeRecord = new NoticeRecordWithBLOBs();
		noticeRecord.setModelId(noticeId);
		noticeRecord.setUserId(userId);
		noticeRecord.setType(type);
		if (params != null && params.length > 0) {
			String paramString = "";
			for (String param : params) {
				paramString += param + StatusConst.IMG_SPLIT_STRING;
			}
			paramString.substring(0, paramString.length() - 1);
			noticeRecord.setParam(paramString);
		}
		noticeRecord.setContent(MessageFormat.format(notice.getModel(), params));
		noticeRecord.setCreateTime(new Date());
		noticeRecord.setStatus(NoticeConst.NOTICE_RECORD_STATUS_UNREAD);
		noticeRecordMapper.insertSelective(noticeRecord);
		return noticeRecord.getId();
	}
}
