package com.maomao.ssm.service.impl.admin;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.maomao.ssm.bean.Coupon;
import com.maomao.ssm.bean.CouponExample;
import com.maomao.ssm.bean.Notice;
import com.maomao.ssm.bean.NoticeRecordWithBLOBs;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserApply;
import com.maomao.ssm.bean.UserBank;
import com.maomao.ssm.constant.CouponConts;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.CouponMapper;
import com.maomao.ssm.dao.NoticeMapper;
import com.maomao.ssm.dao.NoticeRecordMapper;
import com.maomao.ssm.dao.UserAndAdminNumberMapperCustom;
import com.maomao.ssm.dao.UserApplyMapper;
import com.maomao.ssm.dao.UserBankMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.pojo.Query;
import com.maomao.ssm.pojo.ShopApplyAudit;
import com.maomao.ssm.pojo.UserApplyDetail;
import com.maomao.ssm.service.admin.ShopApplyAuditService;
import com.maomao.ssm.service.common.CouponRecordService;

/**
 * 开店申请审核
 * 
 * @author Administrator
 *
 */
@Service
public class ShopApplyAuditServiceImpl implements ShopApplyAuditService {

	@Autowired
	private UserApplyMapper userApplyMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private NoticeMapper noticeMapper;

	@Autowired
	private NoticeRecordMapper noticeRecordMapper;
	@Autowired
	private JedisClientPool jedisClientPool;

	@Autowired
	private CouponRecordService couponRecordService;
	@Autowired
	private UserBankMapper userBankMapper;
	@Autowired
	private UserAndAdminNumberMapperCustom userAndAdminNumberMapper;
	@Autowired
	private CouponMapper couponMapper;

	// 用户搜索
	@Override
	public JsonData getSearchUser(Integer pages, Integer rows, String queryString) {
		Integer startRows = rows * (pages - 1);// 当前页

		Query query = new Query(startRows, rows, queryString);

		// 获取当前页数据
		List<ShopApplyAudit> shopApplyAuditList = userAndAdminNumberMapper.getShopApplyAuditList(query);

		Integer total = userAndAdminNumberMapper.getUserApplyTotal(queryString);
		PageBean page = new PageBean();
		page.setRows(shopApplyAuditList); // 当前页数据
		page.setTotal(total); // 总记录数
		return JsonData.setSuccessMessage(page);
	}

	// 立即审核
	@Override
	public JsonData getShopApply(Integer userApplyId) {
		if (userApplyId == null)
			return JsonData.setErrorMessage("参数错误");

		// 获取用户开户表
		UserApply userApply = userApplyMapper.selectByPrimaryKey(userApplyId);

		// 获取开户的用户
		Integer userId = userApply.getUserId();
		User user = userMapper.selectByPrimaryKey(userId);

		if (user == null || user.getStatus() == StatusConst.USER_STATSU_DEL) {
			return JsonData.setErrorMessage("用户不存在");
		} else if (user.getCheckStatus() != UserConts.USER_CHECK_STATUS_CHECKING
				|| userApply.getCheckStatus() != UserConts.USER_CHECK_STATUS_CHECKING) {
			return JsonData.setErrorMessage("该用户开店状态不为审核中");
		} else {
			UserApplyDetail userApplyDetail = new UserApplyDetail(userApply, user);
			return JsonData.setSuccessMessage(userApplyDetail);
		}
	}

	// 审核通过
	@Override
	public JsonData shopApproved(Integer userApplyId, Byte creditReal, Long loan) {
		if (userApplyId == null || loan == null || creditReal == null)
			return JsonData.setErrorMessage("参数错误");
		try {

			// 审核通过改变用户状态
			UserApply userApply = userApplyMapper.selectByPrimaryKey(userApplyId);
			if (userApply == null)
				return JsonData.setErrorMessage("参数错误");

			userApply.setCheckStatus(UserConts.USER_CHECK_STATUS_TRUE);
			userApplyMapper.updateByPrimaryKeySelective(userApply);
			Integer userId = userApply.getUserId();

			User user = userMapper.selectByPrimaryKey(userId);
			user.setCreditReal((byte) creditReal); // 是否真实授信 0否 1是
			user.setName(userApply.getName());
			user.setIdCard(userApply.getIdCard());
			user.setCreditTotal(loan);
			user.setCreditSurplus(loan);
			user.setType(StatusConst.USER_TYPE_SHOP);// 审核通过c用户变成b用户
			user.setCheckStatus(UserConts.USER_CHECK_STATUS_TRUE); // 开店申请状态:通过
			user.setModifiedTime(new Date());
			userMapper.updateByPrimaryKeySelective(user);

			// 用户银行卡对象
			UserBank userBank = new UserBank();
			userBank.setUserId(userId);
			userBank.setAccount(userApply.getBankAccount());
			userBank.setBankName(userApply.getBankName());
			userBank.setUserName(userApply.getBankUsername());
			userBank.setCreateTime(new Date());
			userBankMapper.insertSelective(userBank);

			Notice notice = noticeMapper.selectByPrimaryKey(NoticeConst.NOTICE_ID_USER_APPLY_SUCCESS); // 开店申请成功
			NoticeRecordWithBLOBs record = new NoticeRecordWithBLOBs();
			record.setModelId(NoticeConst.NOTICE_ID_USER_APPLY_SUCCESS);
			record.setUserId(userId);
			record.setType(StatusConst.NOTICE_RECORD_TYPE_SYSTEM_INFO);// 系统公告
			record.setContent(notice.getModel());
			record.setCreateTime(new Date());
			record.setStatus(StatusConst.NOTICE_RECORD_STATUS_READ_NO);
			noticeRecordMapper.insert(record);

			// 开店优惠券
			CouponExample couponExample = new CouponExample();
			couponExample.createCriteria().andTypeEqualTo(CouponConts.COUPON_TYPE_SHOP).andStatusEqualTo(CouponConts.COUPON_STARTUS_NOT_DEL);
			List<Coupon> coupons = couponMapper.selectByExample(couponExample);
			couponRecordService.addCouponRecord(userId, coupons);

			// 清除登陆缓存
			String cookie = jedisClientPool.get(userId + "");
			jedisClientPool.del(userId + "");
			jedisClientPool.del(cookie);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("开店审核失败");
		}
		return JsonData.setSuccessMessage();
	}

	// 拒绝受理
	@Override
	public JsonData shopRefused(Integer userApplyId, String reason) {
		// System.out.println(userApplyId + "==" + reason);
		if (userApplyId == null || reason == null)
			return JsonData.setErrorMessage("参数错误");

		try {
			UserApply apply = userApplyMapper.selectByPrimaryKey(userApplyId);
			apply.setReason(reason);
			apply.setCheckStatus(UserConts.USER_CHECK_STATUS_FALSE);

			Integer userId = apply.getUserId();
			User user = userMapper.selectByPrimaryKey(userId);
			user.setCheckStatus(UserConts.USER_CHECK_STATUS_FALSE);
			user.setModifiedTime(new Date());

			userApplyMapper.updateByPrimaryKeySelective(apply);
			userMapper.updateByPrimaryKeySelective(user);

			Notice notice = noticeMapper.selectByPrimaryKey(NoticeConst.NOTICE_ID_USER_APPLY_FAILED); // 开店申请失败

			NoticeRecordWithBLOBs record = new NoticeRecordWithBLOBs();
			record.setModelId(NoticeConst.NOTICE_ID_USER_APPLY_FAILED);
			record.setUserId(userId);
			record.setType(StatusConst.NOTICE_RECORD_TYPE_SYSTEM_INFO);// 系统公告
			record.setContent(MessageFormat.format(notice.getModel(), apply.getReason()));
			record.setParam(apply.getReason());
			record.setCreateTime(new Date());
			record.setStatus(StatusConst.NOTICE_RECORD_STATUS_READ_NO);
			noticeRecordMapper.insert(record);

		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("开店拒绝受理失败");
		}
		return JsonData.setSuccessMessage();
	}
}
