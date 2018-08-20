package com.maomao.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.maomao.ssm.bean.NoticeRecord;
import com.maomao.ssm.bean.NoticeRecordExample;
import com.maomao.ssm.bean.NoticeRecordWithBLOBs;
import com.maomao.ssm.constant.PageConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.NoticeRecordMapper;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.notice.NoticeDetail;
import com.maomao.ssm.pojo.notice.UserNotice;
import com.maomao.ssm.service.UserMessageService;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年1月26日
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {
	@Autowired
	private NoticeRecordMapper noticeRecordMapper;

	/**
	 * 用户消息列表
	 */
	public JsonData getUserMessage(Integer userId, Integer type, Integer pages, Integer rows) {
		if (userId == null || type == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		pages = pages == null || pages < 3 ? PageConst.PAGE_DEFAULT : pages;
		rows = rows == null ? pages == PageConst.PAGE_DEFAULT ? PageConst.ROWS_DEFAULT_FIRST : PageConst.ROWS_DEFAULT
				: rows;
		PageHelper.startPage(pages, rows);

		NoticeRecordExample example = new NoticeRecordExample();
		example.createCriteria().andUserIdEqualTo(userId).andStatusNotEqualTo(StatusConst.NOTICE_RECORD_STATUS_DEL)
				.andTypeEqualTo(type.byteValue());
		example.setOrderByClause("create_time DESC");
		List<NoticeRecordWithBLOBs> noticeRecords = noticeRecordMapper.selectByExampleWithBLOBs(example);
		List<UserNotice> noticeList = new ArrayList<UserNotice>();
		if (noticeRecords != null && noticeRecords.size() > 0) {
			for (NoticeRecordWithBLOBs noticeRecordWithBLOBs : noticeRecords) {
				UserNotice userNotice = new UserNotice(noticeRecordWithBLOBs);
				noticeList.add(userNotice);
			}
		}
		return JsonData.setSuccessMessage(noticeList);
	}

	/**
	 * 查看消息详情
	 */
	public JsonData getMessageDetail(Integer userId, Integer id) {
		if (userId == null || id == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		NoticeRecordExample example = new NoticeRecordExample();
		example.createCriteria().andUserIdEqualTo(userId).andIdEqualTo(id);
		List<NoticeRecordWithBLOBs> list = noticeRecordMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			NoticeRecordWithBLOBs noticeRecordWithBLOBs = list.get(0);
			NoticeDetail noticeDetail = new NoticeDetail(noticeRecordWithBLOBs);
			noticeRecordWithBLOBs.setStatus(StatusConst.NOTICE_RECORD_STATUS_READ_YES);// 0未读
																						// -1删除
			noticeRecordMapper.updateByExampleSelective(noticeRecordWithBLOBs, example);
			return JsonData.setSuccessMessage(noticeDetail);
		} else {
			return JsonData.setErrorMessage("暂无内容");
		}
	}

	/**
	 * 删除消息
	 */
	public JsonData delMessageById(Integer userId, Integer id, Byte type) {
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法");
		}

		if (type != null && type == StatusConst.NOTICE_RECORD_DEL_ALL) {
			NoticeRecordExample example = new NoticeRecordExample();
			example.createCriteria().andUserIdEqualTo(userId)
					.andStatusEqualTo(StatusConst.NOTICE_RECORD_STATUS_READ_YES);
			List<NoticeRecord> list = noticeRecordMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				for (NoticeRecord noticeRecord : list) {
					try {
						noticeRecord.setStatus(StatusConst.NOTICE_RECORD_STATUS_DEL);// -1:删除
						noticeRecordMapper.updateByPrimaryKey(noticeRecord);
					} catch (Exception e) {
						e.printStackTrace();
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return JsonData.setErrorMessage("删除失败");
					}
				}
				return JsonData.setSuccessMessage();
			} else {// TODO 返回300
				return JsonData.setErrorMessage("删除失败");
			}
		}
		try {
			NoticeRecordExample example = new NoticeRecordExample();
			example.createCriteria().andUserIdEqualTo(userId).andIdEqualTo(id);
			List<NoticeRecord> list = noticeRecordMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				NoticeRecord noticeRecord = list.get(0);
				noticeRecord.setStatus(StatusConst.NOTICE_RECORD_STATUS_DEL);
				noticeRecordMapper.updateByPrimaryKey(noticeRecord);
				return JsonData.setSuccessMessage();
			} else {
				return JsonData.setErrorMessage("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("删除失败");
		}
	}

	/**
	 * 全部已读
	 */
	public JsonData saveMessageReadAll(Integer userId, Byte type) { // type:0系统通知,1物流通知
		if (userId == null || userId <= 0 || type == null) {
			return JsonData.setErrorMessage("参数错误");
		}

		NoticeRecordExample example = new NoticeRecordExample();
		example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(StatusConst.NOTICE_RECORD_STATUS_READ_NO)
				.andTypeEqualTo(type);

		NoticeRecordWithBLOBs noticeRecordWithBLOBs = new NoticeRecordWithBLOBs();
		noticeRecordWithBLOBs.setStatus(StatusConst.NOTICE_RECORD_STATUS_READ_YES);
		noticeRecordMapper.updateByExampleSelective(noticeRecordWithBLOBs, example);

		return JsonData.setSuccessMessage();
	}

	/**
	 * 获取未读消息标志
	 */
	@Override
	public JsonData getUnreadMessageTag(Integer userId) {
		if (userId == null) {
			return JsonData.setErrorMessage("参数错误");
		}

		NoticeRecordExample noticeRecordExample = new NoticeRecordExample();
		noticeRecordExample.createCriteria().andUserIdEqualTo(userId)
				.andStatusEqualTo(StatusConst.NOTICE_RECORD_STATUS_READ_NO);

		int count = noticeRecordMapper.countByExample(noticeRecordExample);

		return JsonData.setSuccessMessage(count);
	}
}
