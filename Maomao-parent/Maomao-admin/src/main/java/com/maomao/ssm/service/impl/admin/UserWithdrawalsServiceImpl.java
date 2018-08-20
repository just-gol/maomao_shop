package com.maomao.ssm.service.impl.admin;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserWithdrawals;
import com.maomao.ssm.bean.UserWithdrawalsCustom;
import com.maomao.ssm.bean.UserWithdrawalsExample;
import com.maomao.ssm.bean.UserWithdrawalsHistoryCustom;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.dao.UserWithdrawalsMapper;
import com.maomao.ssm.dao.UserWithdrawalsMapperCustom;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.UserWithdrawalsService;
import com.maomao.ssm.service.common.NoticeRecordService;
import com.maomao.ssm.service.common.UserCreditService;
import com.maomao.ssm.service.common.UserMoneyService;

@Service
public class UserWithdrawalsServiceImpl implements UserWithdrawalsService {

	@Autowired
	private UserWithdrawalsMapperCustom userWithdrawalsMapperCustom;
	@Autowired
	private UserWithdrawalsMapper userWithdrawalsMapper;
	@Autowired
	private UserMoneyService userMoneyService;
	@Autowired
	private UserCreditService userCreditService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private NoticeRecordService noticeRecordService;

	/**
	 * 提现列表
	 */
	@Override
	public JsonData getUserWithdrawalsList(Integer pages, Integer rows, String queryString, Byte category) {
		if (category == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 分页
		PageHelper.startPage(pages, rows);
		List<UserWithdrawalsCustom> userWithdrawalsCustoms = userWithdrawalsMapperCustom
				.getUserWithdrawalsList(queryString, UserConts.USER_WITHDRAWALS_STATUS_CHECKING, category);

		PageInfo<UserWithdrawalsCustom> pageInfo = new PageInfo<>(userWithdrawalsCustoms);

		PageBean pagebean = new PageBean();
		// 获取总条数
		pagebean.setTotal(pageInfo.getTotal());
		// 获取当前页数据
		pagebean.setRows(userWithdrawalsCustoms);
		return JsonData.setSuccessMessage(pagebean);
	}

	/**
	 * 修改提现状态
	 */
	@Override
	public JsonData updateUserWithdrawalsByStatus(Integer id, Byte status) {
		if (id == null || status == null || (!UserConts.USER_WITHDRAWALS_STATUS_FAILED.equals(status)
				&& !UserConts.USER_WITHDRAWALS_STATUS_SUCCESS.equals(status))) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 成功 user_money 失败 返还金额
		if (UserConts.USER_WITHDRAWALS_STATUS_SUCCESS.equals(status)) {
			UserWithdrawals userWithdrawals = userWithdrawalsMapper.selectByPrimaryKey(id);
			if (userWithdrawals == null) {
				return JsonData.setErrorMessage("提现记录不存在");
			}
			if (!UserConts.USER_WITHDRAWALS_STATUS_CHECKING.equals(userWithdrawals.getStatus())) {
				return JsonData.setErrorMessage("提现记录状态错误");
			}
			userWithdrawals.setStatus(UserConts.USER_WITHDRAWALS_STATUS_SUCCESS);
			userWithdrawalsMapper.updateByPrimaryKey(userWithdrawals);
			if (UserConts.USER_WITHDRAWALS_CATEGORY_MONEY.equals(userWithdrawals.getCategory())) {
				User user = userMapper.selectByPrimaryKey(userWithdrawals.getUserId());
				user.setAccumulativeMoney(user.getAccumulativeMoney() + userWithdrawals.getMoney());
				userMapper.updateByPrimaryKey(user);
			}

			// 添加消息记录
			noticeRecordService.addNoticeRecord(userWithdrawals.getUserId(),
					NoticeConst.NOTICE_ID_WITHDRAWALS_APPLY_SUCCESS, NoticeConst.NOTICE_RECORD_TYPE_SYSTEM,
					new String[] { new DecimalFormat("0.00").format((double) userWithdrawals.getMoney() / 100) });
			return JsonData.setSuccessMessage();
		}
		if (UserConts.USER_WITHDRAWALS_STATUS_FAILED.equals(status)) {
			UserWithdrawals userWithdrawals = userWithdrawalsMapper.selectByPrimaryKey(id);
			if (userWithdrawals == null) {
				return JsonData.setErrorMessage("提现记录不存在");
			}
			if (!UserConts.USER_WITHDRAWALS_STATUS_CHECKING.equals(userWithdrawals.getStatus())) {
				return JsonData.setErrorMessage("提现记录状态错误");
			}
			userWithdrawals.setStatus(UserConts.USER_WITHDRAWALS_STATUS_FAILED);

			User user = userMapper.selectByPrimaryKey(userWithdrawals.getUserId());
			if (userWithdrawals.getCategory() != null) {
				if (userWithdrawals.getCategory().equals(UserConts.USER_WITHDRAWALS_CATEGORY_MONEY)) {// 余额提现
					userMoneyService.addUserMoney(user.getId(), user.getMoney(), userWithdrawals.getMoney(),
							UserConts.USER_MONEY_TYPE_WITHDRAWALS_FAILED, id);
					user.setMoney(user.getMoney() + userWithdrawals.getMoney());
				} else {// 信用额度提现
					userCreditService.addUserCredit(user.getId(), user.getCreditSurplus(), userWithdrawals.getMoney(),
							UserConts.USER_CREDIT_TYPE_WITHDRAWALS_FAILED, id);
					user.setCreditSurplus(user.getCreditSurplus() + userWithdrawals.getMoney());
					user.setCreditTotal(user.getCreditTotal() + userWithdrawals.getMoney());
				}
			}

			userMapper.updateByPrimaryKey(user);
			userWithdrawalsMapper.updateByPrimaryKey(userWithdrawals);
			return JsonData.setSuccessMessage();
		}
		return JsonData.setErrorMessage("参数非法");
	}

	/**
	 * 历史提现列表
	 */
	@Override
	public JsonData getHistoryUserWithdrawalsList(Integer pages, Integer rows, String queryString) {
		// 分页
		PageHelper.startPage(pages, rows);
		List<UserWithdrawalsHistoryCustom> userWithdrawalsHistoryCustoms = userWithdrawalsMapperCustom
				.getHistoryUserWithdrawalsList(UserConts.USER_TYPE_B, queryString,
						UserConts.USER_WITHDRAWALS_CATEGORY_MONEY);
		PageInfo<UserWithdrawalsHistoryCustom> pageInfo = new PageInfo<>(userWithdrawalsHistoryCustoms);

		PageBean pagebean = new PageBean();
		// 获取总条数
		pagebean.setTotal(pageInfo.getTotal());
		// 获取当前页数据
		pagebean.setRows(userWithdrawalsHistoryCustoms);
		return JsonData.setSuccessMessage(pagebean);
	}

	/**
	 * 历史提现详情
	 */
	@Override
	public JsonData getHistoryUserWithdrawalsDetail(Integer pages, Integer rows, Integer userId) {
		// 分页
		PageHelper.startPage(pages, rows);
		UserWithdrawalsExample userWithdrawalsExample = new UserWithdrawalsExample();
		userWithdrawalsExample.createCriteria().andUserIdEqualTo(userId)
				.andCategoryEqualTo(UserConts.USER_WITHDRAWALS_CATEGORY_MONEY);
		List<UserWithdrawals> userWithdrawals = userWithdrawalsMapper.selectByExample(userWithdrawalsExample);

		PageInfo<UserWithdrawals> pageInfo = new PageInfo<>(userWithdrawals);

		PageBean pagebean = new PageBean();
		// 获取总条数
		pagebean.setTotal(pageInfo.getTotal());
		// 获取当前页数据
		pagebean.setRows(userWithdrawals);
		return JsonData.setSuccessMessage(pagebean);
	}

}
