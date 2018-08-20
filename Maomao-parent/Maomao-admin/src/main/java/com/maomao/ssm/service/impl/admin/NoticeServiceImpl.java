package com.maomao.ssm.service.impl.admin;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.Notice;
import com.maomao.ssm.bean.NoticeExample;
import com.maomao.ssm.bean.NoticeRecord;
import com.maomao.ssm.bean.NoticeRecordExample;
import com.maomao.ssm.bean.NoticeRecordWithBLOBs;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.dao.NoticeMapper;
import com.maomao.ssm.dao.NoticeRecordMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.NoticeService;
import com.maomao.ssm.service.common.NoticeRecordService;
import com.maomao.ssm.utils.JsonUtils;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;

	@Autowired
	private NoticeRecordMapper noticeRecordMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private NoticeRecordService noticeRecordService;
	/**
	 *
	 * 获取消息列表
	 */
	@Override
	public JsonData getNoticeList(Integer pages, Integer rows, String keywords) {
//		System.out.println("===="+keywords);
		String name = null;	
		try {
			if (keywords != null) 
				name = new String(keywords.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 分页
		PageHelper.startPage(pages, rows);
		NoticeExample example = new NoticeExample();
		example.setOrderByClause("send_Time asc");
		if (StringUtils.isNotBlank(keywords)) {
			//example.or().andNameLike("%" + keywords + "%").andStatusNotEqualTo(NoticeConst.NOTICE_STATUS_DELETED).andTypeEqualTo((byte) 0);
			example.createCriteria().andNameLike("%" + name + "%").andStatusNotEqualTo(NoticeConst.NOTICE_STATUS_DELETED).andTypeEqualTo((byte) 0);
		}
		if (StringUtils.isBlank(keywords)) {
			example.createCriteria().andStatusNotEqualTo(NoticeConst.NOTICE_STATUS_DELETED).andTypeEqualTo((byte) 0);
		}
		List<Notice> noticeList = noticeMapper.selectByExampleWithBLOBs(example);
		
		PageInfo<Notice> pageInfo = new PageInfo<>(noticeList);

		PageBean pagebean = new PageBean();
		// 获取总条数
		pagebean.setTotal(pageInfo.getTotal());
		// 获取当前页数据
		pagebean.setRows(noticeList);

		return JsonData.setSuccessMessage(pagebean);
	}

	/**
	 * 新增消息
	 */
	@Override
	public JsonData addNotice(String modelName, String model, Long sendTime) {
		Notice notice = new Notice();
		notice.setName(modelName);
		notice.setModel(model);
		notice.setSendTime(new Date(sendTime));
		notice.setStatus(NoticeConst.NOTICE_STATUS_ENABLE);
		notice.setCreateTime(new Date());
		notice.setType((byte) 0);
		noticeMapper.insertSelective(notice);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 删除消息
	 */
	@Override
	public JsonData delNotice(Integer modelId) {
		Notice notice = noticeMapper.selectByPrimaryKey(modelId);
		
		if (notice != null ) {
			if (notice.getStatus() == NoticeConst.NOTICE_STATUS_SUC) {
				return JsonData.setErrorMessage("已发送成功的消息不可以删除");
			}
			if (notice.getStatus() == NoticeConst.NOTICE_STATUS_DELETED) {
				return JsonData.setErrorMessage("此消息已经被删除");
			}
			
			notice.setStatus(NoticeConst.NOTICE_STATUS_DELETED);
			notice.setModifiedTime(new Date());
			noticeMapper.updateByPrimaryKeyWithBLOBs(notice);
			
			//获取用户列表
			List<User> userList = userMapper.selectByExample(null);
			List<Integer> userIdlist = new ArrayList<>();
			if (userList != null &&userList.size() >0) {
				for (User user : userList) {
					Integer userId = user.getId();
					userIdlist.add(userId);
				}
			}
			NoticeRecordExample recordExample = new NoticeRecordExample();
			recordExample.createCriteria().andUserIdIn(userIdlist).andModelIdEqualTo(modelId)
					.andStatusNotEqualTo(NoticeConst.NOTICE_RECORD_STATUS_DELETED);
			List<NoticeRecord> noticeRecordList = noticeRecordMapper.selectByExample(recordExample);
			if (noticeRecordList != null && noticeRecordList.size() > 0) {
				for (NoticeRecord noticeRecord : noticeRecordList) {
					noticeRecord.setStatus(NoticeConst.NOTICE_RECORD_STATUS_DELETED);
					noticeRecord.setModifiedTime(new Date());
					noticeRecordMapper.updateByPrimaryKey(noticeRecord);
				}
			}
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 修改消息
	 */
	@Override
	public JsonData updateNotice(Integer modelId, String modelName, String model, Long sendTime) {
		Notice notice = noticeMapper.selectByPrimaryKey(modelId);
		if (notice != null) {
			if (notice.getStatus() == NoticeConst.NOTICE_STATUS_SUC) {
				return JsonData.setErrorMessage("已发送成功的消息不可以修改");
			}
			if (notice.getStatus() == NoticeConst.NOTICE_STATUS_DELETED) {
				return JsonData.setErrorMessage("此消息已被删除");
			}
			notice.setId(modelId);
			notice.setName(modelName);
			notice.setModel(model);
			notice.setSendTime(new Date(sendTime));
			notice.setModifiedTime(new Date());
			noticeMapper.updateByPrimaryKeySelective(notice);
		}
		return JsonData.setSuccessMessage();
	}

	
	/**
	 * 查看指定消息
	 */
	@Override
	public JsonData getNotice(Integer modelId) {
		if (modelId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		try {
			Notice notice = noticeMapper.selectByPrimaryKey(modelId);
			if (notice != null ) {
				if (notice.getStatus() == NoticeConst.NOTICE_STATUS_DELETED) {
					return JsonData.setErrorMessage("此消息已被删除");
				}
				if (notice.getType() == 0) {
					return JsonData.setSuccessMessage(notice);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonData.setErrorMessage("查询失败");
	}
}
