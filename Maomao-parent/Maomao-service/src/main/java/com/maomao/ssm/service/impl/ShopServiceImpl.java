package com.maomao.ssm.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;

import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.bean.GoodsOrderGoods;
import com.maomao.ssm.bean.GoodsOrderGoodsExample;
import com.maomao.ssm.bean.GoodsSubscription;
import com.maomao.ssm.bean.GoodsSubscriptionExample;
import com.maomao.ssm.bean.GoodsSubscriptionRecord;
import com.maomao.ssm.bean.GoodsSubscriptionRecordExample;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsUser;
import com.maomao.ssm.bean.GoodsUserExample;
import com.maomao.ssm.bean.GoodsUserExample.Criteria;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserApply;
import com.maomao.ssm.bean.UserApplyCredit;
import com.maomao.ssm.bean.UserApplyCreditExample;
import com.maomao.ssm.bean.UserApplyExample;
import com.maomao.ssm.bean.UserMoney;
import com.maomao.ssm.bean.UserMoneyExample;
import com.maomao.ssm.bean.UserWithdrawals;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsOrderGoodsMapper;
import com.maomao.ssm.dao.GoodsSubscriptionMapper;
import com.maomao.ssm.dao.GoodsSubscriptionRecordMapper;
import com.maomao.ssm.dao.GoodsSubscriptionRecordMapperCustom;
import com.maomao.ssm.dao.GoodsUserMapper;
import com.maomao.ssm.dao.UserApplyCreditMapper;
import com.maomao.ssm.dao.UserApplyMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.dao.UserMoneyMapper;
import com.maomao.ssm.dao.UserWithdrawalsMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.shop.AccountDetail;
import com.maomao.ssm.pojo.shop.AccountLogs;
import com.maomao.ssm.pojo.shop.ApplyCredit;
import com.maomao.ssm.pojo.shop.OnSaleGoods;
import com.maomao.ssm.pojo.shop.OnSubscriptionGoods;
import com.maomao.ssm.pojo.shop.ShopDetail;
import com.maomao.ssm.service.ShopService;
import com.maomao.ssm.service.common.NoticeRecordService;
import com.maomao.ssm.service.common.UserCreditService;
import com.maomao.ssm.service.common.UserMoneyService;

/**
 * @author:wzy
 * @descrption:店铺相关
 * @version:
 * @date:2018年1月27日
 */
@Service
public class ShopServiceImpl implements ShopService {
	// 日志记录
	private static final Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	private UserApplyMapper userApplyMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GoodsUserMapper goodsUserMapper;
	@Autowired
	private GoodsSubscriptionMapper goodsSubscriptionMapper;
	@Autowired
	private UserMoneyMapper userMoneyMapper;
	@Autowired
	private UserWithdrawalsMapper userWithdrawalsMapper;
	@Autowired
	private GoodsOrderGoodsMapper goodsOrderGoodsMapper;
	@Autowired
	private UserApplyCreditMapper userApplyCreditMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsSubscriptionRecordMapper goodsSubscriptionRecordMapper;
	@Autowired
	private GoodsSubscriptionRecordMapperCustom goodsSubscriptionRecordMapperCustom;
	@Autowired
	private NoticeRecordService noticeRecordService;
	@Autowired
	private JedisClientPool jedisClientPool;
	@Autowired
	private UserMoneyService userMoneyService;
	@Autowired
	private UserCreditService userCreditService;

	/**
	 * 我的店铺
	 */
	public JsonData getShopStatus(Integer userId, Byte type) {
		if (userId == null || type == null) {
			return JsonData.setErrorMessage("参数非法");
		}

		if (type == StatusConst.USER_TYPE_PERSONAL) {// 个人用户
			UserApplyExample example = new UserApplyExample();
			example.createCriteria().andUserIdEqualTo(userId);
			example.setOrderByClause("id desc");
			List<UserApply> list = userApplyMapper.selectByExample(example);
			Byte status = 0;
			Map<String, Byte> map = new HashMap<String, Byte>();
			if (list != null && list.size() > 0) {
				UserApply userApply = list.get(0);
				status = userApply.getCheckStatus();// 0未申请 1审核中 2审核未通过 3审核通过
			}
			map.put("type", status);
			return JsonData.setSuccessMessage(map);
		}
		if (type == StatusConst.USER_TYPE_SHOP) {// 商家用户
			// 店铺详情
			User user = userMapper.selectByPrimaryKey(userId);
			if (user != null) {
				ShopDetail shopDetail = new ShopDetail();
				shopDetail.setMoney(user.getMoney());// 收益金额
				shopDetail.setTotal(user.getCreditTotal());// 认筹总金额
				shopDetail.setSurplus(user.getCreditSurplus());// 剩余认筹金额

				GoodsUserExample example = new GoodsUserExample();
				example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
				List<GoodsUser> list = goodsUserMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					shopDetail.setGoodsNum(list.size());
				} else {
					shopDetail.setGoodsNum(0);
				}

				List<GoodsSubscriptionRecord> list2 = goodsSubscriptionRecordMapperCustom
						.selectGroupByGoodsSubscriptionId(userId);
				if (list2 != null && list2.size() > 0) {
					Integer count = 0;
					for (GoodsSubscriptionRecord record : list2) {
						GoodsSubscriptionExample example3 = new GoodsSubscriptionExample();
						example3.createCriteria().andIdEqualTo(record.getGoodsSubscriptionId())
								.andSubscriptionStatusEqualTo((byte) 0);
						List<GoodsSubscription> list3 = goodsSubscriptionMapper.selectByExample(example3);
						if (list3 != null && list3.size() > 0) {
							count = count + 1;
						}
					}
					shopDetail.setSubscriptionNum(count);

				} else {
					shopDetail.setSubscriptionNum(0);
				}
				return JsonData.setSuccessMessage(shopDetail);
			}
		}
		return JsonData.setErrorMessage("参数非法");
	}

	/**
	 * 开店申请提交
	 */
	public JsonData addApplyShop(UserApply userApply) {
		try {
			userApplyMapper.insert(userApply);
			Integer userId = userApply.getUserId();
			User user = userMapper.selectByPrimaryKey(userId);
			user.setCheckStatus(UserConts.USER_CHECK_STATUS_CHECKING);
			userMapper.updateByPrimaryKey(user);

			// 添加消息记录
			noticeRecordService.addNoticeRecord(userId, NoticeConst.NOTICE_ID_USER_APPLY,
					NoticeConst.NOTICE_RECORD_TYPE_SYSTEM, new String[] {});

			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("申请失败,请重试");
		}

	}

	/**
	 * 账户明细
	 */
	public JsonData getAccountDetail(Integer userId) {
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法");
		}

		User user = userMapper.selectByPrimaryKey(userId);
		if (user != null) {
			AccountDetail accountDetail = new AccountDetail();
			accountDetail.setAccumulativeMoney(user.getAccumulativeMoney());
			accountDetail.setMoney(user.getMoney());
			accountDetail.setTotalMoney(user.getAccumulativeMoney() + user.getMoney());
			List<AccountLogs> accountLogList = new ArrayList<AccountLogs>();

			UserMoneyExample example = new UserMoneyExample();
			example.createCriteria().andUserIdEqualTo(userId);
			example.setOrderByClause("create_time desc");
			List<UserMoney> list = userMoneyMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				for (UserMoney userMoney : list) {
					AccountLogs accountLogs = new AccountLogs();
					Integer bizId = userMoney.getBizId();// 变更业务id
					byte type = userMoney.getType();// 变更类型
					StringBuilder sb = new StringBuilder();
					switch (type) {
					case 0:// 奖励金 0:奖励金, 1:返利 ,2:提现,3:认筹分红
						GoodsOrderGoodsExample example2 = new GoodsOrderGoodsExample();
						example2.createCriteria().andOrderIdEqualTo(bizId);
						List<GoodsOrderGoods> list2 = goodsOrderGoodsMapper.selectByExample(example2);
						if (list2 != null) {
							GoodsOrderGoods goodsOrderGoods = list2.get(0);
							accountLogs.setTitle("奖励金");
							accountLogs.setName(goodsOrderGoods.getName());
							accountLogs.setTime(goodsOrderGoods.getCreateTime());
							accountLogs.setMoney(userMoney.getMoneyChange());
						}
						break;
					case 1:// 返利
						GoodsOrderGoodsExample example3 = new GoodsOrderGoodsExample();
						example3.createCriteria().andOrderIdEqualTo(bizId);
						List<GoodsOrderGoods> list3 = goodsOrderGoodsMapper.selectByExample(example3);
						if (list3 != null) {
							GoodsOrderGoods goodsOrderGoods = list3.get(0);
							accountLogs.setTitle("返利金");
							accountLogs.setName(goodsOrderGoods.getName());
							accountLogs.setTime(goodsOrderGoods.getCreateTime());
							accountLogs.setMoney(userMoney.getMoneyChange());
						}
						break;
					case 2:// 提现
						UserWithdrawals userWithdrawals = userWithdrawalsMapper.selectByPrimaryKey(bizId);
						if (userWithdrawals != null) {
							accountLogs.setTitle("提现");
							String bankName = userWithdrawals.getBankName();
							String simpleAccount = userWithdrawals.getAccount()
									.substring(userWithdrawals.getAccount().length() - 4);
							sb.append(bankName).append("(").append(simpleAccount).append(")");
							accountLogs.setName(sb.toString());
							accountLogs.setTime(userWithdrawals.getCreateTime());
							accountLogs.setMoney(userMoney.getMoneyChange());
						}
						break;
					case 3:// 认筹分红
						GoodsOrderGoodsExample example4 = new GoodsOrderGoodsExample();
						example4.createCriteria().andOrderIdEqualTo(bizId);
						List<GoodsOrderGoods> list4 = goodsOrderGoodsMapper.selectByExample(example4);
						if (list4 != null) {
							GoodsOrderGoods goodsOrderGoods = list4.get(0);
							accountLogs.setTitle("认筹分红");
							accountLogs.setName(goodsOrderGoods.getName());
							accountLogs.setTime(goodsOrderGoods.getCreateTime());
							accountLogs.setMoney(userMoney.getMoneyChange());
						}
						break;
					case 4:// 售出奖励
						GoodsOrderGoodsExample example5 = new GoodsOrderGoodsExample();
						example5.createCriteria().andOrderIdEqualTo(bizId);
						List<GoodsOrderGoods> list5 = goodsOrderGoodsMapper.selectByExample(example5);
						if (list5 != null) {
							GoodsOrderGoods goodsOrderGoods = list5.get(0);
							accountLogs.setTitle("售出奖励");
							accountLogs.setName(goodsOrderGoods.getName());
							accountLogs.setTime(goodsOrderGoods.getCreateTime());
							accountLogs.setMoney(userMoney.getMoneyChange());
						}
						break;
					case 5:// 提现失败
						UserWithdrawals userWithdrawal = userWithdrawalsMapper.selectByPrimaryKey(bizId);
						if (userWithdrawal != null) {
							accountLogs.setTitle("提现失败");
							String bankName = userWithdrawal.getBankName();
							String simpleAccount = userWithdrawal.getAccount().substring(13);
							sb.append(bankName).append("(").append(simpleAccount).append(")");
							accountLogs.setName(sb.toString());
							accountLogs.setTime(userWithdrawal.getCreateTime());
							accountLogs.setMoney(userMoney.getMoneyChange());
						}
						break;
					}
					accountLogList.add(accountLogs);

				}
			}
			accountDetail.setAccountLogs(accountLogList);
			return JsonData.setSuccessMessage(accountDetail);
		}
		return JsonData.setSuccessMessage();// TODO 返回200
	}

	/**
	 * 申请调额
	 */
	public JsonData getApplyCredit(Integer userId) {
		if (userId != null) {

			User user = userMapper.selectByPrimaryKey(userId);
			if (user != null) {
				ApplyCredit applyCredit = new ApplyCredit(user);
				return JsonData.setSuccessMessage(applyCredit);
			}
		}
		return JsonData.setErrorMessage("参数非法");
	}

	/**
	 * 申请调额提交
	 */
	public JsonData addApplyCredit(Integer userId, Long credit) {
		if (userId != null && credit != null) {
			UserApplyCreditExample example = new UserApplyCreditExample();
			example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(StatusConst.USER_APPLY_STATUS_IN);
			List<UserApplyCredit> userList = userApplyCreditMapper.selectByExample(example);
			// 查询到 ,说明用户已经提交过,且正在审核
			if (userList != null && userList.size() > 0) {
				return JsonData.setErrorMessage("审核中");
			}

			UserApplyCredit userApplyCredit = new UserApplyCredit();
			userApplyCredit.setApplyCredit(credit);
			userApplyCredit.setCreateTime(new Date());
			userApplyCredit.setUserId(userId);
			userApplyCredit.setStatus(StatusConst.USER_APPLY_STATUS_IN);// TODO
			try {
				userApplyCreditMapper.insert(userApplyCredit);
				return JsonData.setSuccessMessage();
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("申请失败,请重试");
			}
		}
		return JsonData.setErrorMessage("参数非法");
	}

	/**
	 * 已上架商品
	 */
	public JsonData getOnSaleGoods(Integer userId, String keywords) {
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法");
		}

		List<OnSaleGoods> onSaleGoodsList = new ArrayList<OnSaleGoods>();

		GoodsUserExample example = new GoodsUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId).andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);// 上架状态
		example.setOrderByClause("category asc");
		List<GoodsUser> list = goodsUserMapper.selectByExample(example);

		if (list != null && list.size() > 0) {

			for (GoodsUser goodsUser : list) {
				OnSaleGoods onSaleGoods = null;
				Integer category = goodsUser.getCategory();
				Integer bizId = goodsUser.getBizId();
				Integer onSaleGoodsId = goodsUser.getId();
				if (category.equals(GoodsConst.GOODS_CATEGORY_NORMAL)) {// 普通商品

					if (StringUtils.isNotBlank(keywords)) {
						GoodsExample example2 = new GoodsExample();
						example2.createCriteria().andIdEqualTo(bizId).andNameLike("%" + keywords + "%");
						List<GoodsWithBLOBs> goodsList = goodsMapper.selectByExampleWithBLOBs(example2);
						if (goodsList != null && goodsList.size() > 0) {
							onSaleGoods = new OnSaleGoods(goodsList.get(0), onSaleGoodsId);
						}
					} else {
						GoodsWithBLOBs goodsWithBLOBs = goodsMapper.selectByPrimaryKey(bizId);
						if (goodsWithBLOBs != null) {
							onSaleGoods = new OnSaleGoods(goodsWithBLOBs, onSaleGoodsId);
						}
					}
				}
				if (category.equals(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION)) {// 认筹商品
					if (StringUtils.isNotBlank(keywords)) {
						GoodsSubscriptionExample example2 = new GoodsSubscriptionExample();
						example2.createCriteria().andIdEqualTo(bizId).andNameLike("%" + keywords + "%");
						List<GoodsSubscriptionWithBLOBs> goodsSubscriptionWithBLOBs = goodsSubscriptionMapper
								.selectByExampleWithBLOBs(example2);
						if (goodsSubscriptionWithBLOBs != null && goodsSubscriptionWithBLOBs.size() > 0) {
							onSaleGoods = new OnSaleGoods(goodsSubscriptionWithBLOBs.get(0), onSaleGoodsId);
						}
					} else {
						GoodsSubscriptionWithBLOBs goodsSubscription = goodsSubscriptionMapper
								.selectByPrimaryKey(bizId);
						if (goodsSubscription != null) {
							onSaleGoods = new OnSaleGoods(goodsSubscription, onSaleGoodsId);

							GoodsSubscriptionRecordExample example2 = new GoodsSubscriptionRecordExample();
							example2.createCriteria().andUserIdEqualTo(userId).andGoodsSubscriptionIdEqualTo(bizId);
							List<GoodsSubscriptionRecord> list2 = goodsSubscriptionRecordMapper
									.selectByExample(example2);
							if (list2 != null && list2.size() > 0) {
								GoodsSubscriptionRecord goodsSubscriptionRecord = list2.get(0);
								onSaleGoods.setRebate(goodsSubscriptionRecord.getPriceSubcriptionTotal());
							}
						}
					}
				}
				if (onSaleGoods != null) {
					onSaleGoodsList.add(onSaleGoods);
				}
			}

		}
		return JsonData.setSuccessMessage(onSaleGoodsList);
	}

	/**
	 * 认筹中商品
	 */
	public JsonData getOnSubscriptionGoods(Integer userId) {
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法");
		}

		List<OnSubscriptionGoods> onSubscriptionGoodsList = new ArrayList<OnSubscriptionGoods>();

		List<GoodsSubscriptionRecord> list = goodsSubscriptionRecordMapperCustom
				.selectGroupByGoodsSubscriptionId(userId);
		if (list != null && list.size() > 0) {
			for (GoodsSubscriptionRecord goodsSubscriptionRecord : list) {
				OnSubscriptionGoods onSubscriptionGoods = new OnSubscriptionGoods();
				onSubscriptionGoods.setMyPrice(goodsSubscriptionRecord.getPriceSubcriptionTotal());
				Integer id = goodsSubscriptionRecord.getGoodsSubscriptionId();// 认筹商品id
				onSubscriptionGoods.setId(id);
				onSubscriptionGoods.setType((byte) 0);// 0普通 1认筹

				GoodsSubscriptionExample goodsSubscriptionExample = new GoodsSubscriptionExample();
				goodsSubscriptionExample.createCriteria().andIdEqualTo(id)
						.andSubscriptionStatusEqualTo(GoodsConst.GOODS_SUBSCRIPTION_STATUS_SUBSCRIBING);
				List<GoodsSubscriptionWithBLOBs> goodsSubscriptions = goodsSubscriptionMapper
						.selectByExampleWithBLOBs(goodsSubscriptionExample);
				if (goodsSubscriptions == null || goodsSubscriptions.size() == 0) {
					continue;
				}
				GoodsSubscriptionWithBLOBs goodsSub = goodsSubscriptions.get(0);
				if (goodsSub != null) {
					onSubscriptionGoods.setImg(goodsSub.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
					onSubscriptionGoods.setName(goodsSub.getName());
					onSubscriptionGoods.setSubscriptionPrice(goodsSub.getPriceSubscription());// 认筹价
				}
				onSubscriptionGoodsList.add(onSubscriptionGoods);
			}
		}
		return JsonData.setSuccessMessage(onSubscriptionGoodsList);
	}

	/**
	 * 申请提现
	 */
	public JsonData setWithdraw(Integer userId, String bankName, String account, Long money, String userName,
			String password, Byte category,String accountsBank) {
		if (userId == null || StringUtils.isBlank(bankName) || StringUtils.isBlank(account) || money == null
				|| money <= 0L) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 判断提现密码 by hhd
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return JsonData.setErrorMessage("用户不存在");
		}
		if (StringUtils.isBlank(user.getPasswordWithdrawals())) {
			return JsonData.setErrorMessage("未设置提现密码");
		}
		// 判断错误次数
		if (jedisClientPool.exists(RedisConst.PASSWORD_WITHDRAWALS_ERROR + userId) && Integer.parseInt(
				jedisClientPool.get(RedisConst.PASSWORD_WITHDRAWALS_ERROR + userId)) >= RedisConst.ERROR_COUNT) {
			return JsonData.setErrorMessage("提现密码错误超过6次，请修改提现密码！");
		}
		if (!user.getPasswordWithdrawals().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
			jedisClientPool.incr(RedisConst.PASSWORD_WITHDRAWALS_ERROR + userId);
			return JsonData.setErrorMessage("提现密码错误");
		}
		jedisClientPool.del(RedisConst.PASSWORD_WITHDRAWALS_ERROR + userId);
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("用户id为:" + userId + "申请提现,提现金额为:" + money + ",提现账号为:" + account);
		}

		UserWithdrawals userWithdrawals = new UserWithdrawals();
		userWithdrawals.setUserId(userId);
		userWithdrawals.setAccountsBank(accountsBank);
		userWithdrawals.setBankName(bankName);
		userWithdrawals.setAccount(account);
		userWithdrawals.setMoney(money);
		userWithdrawals.setStatus(StatusConst.USER_WITHDRAWALS_STATUS_IN);// 申请中
		userWithdrawals.setType(StatusConst.USER_WITHDRAWALS_TYPE_BANK);
		userWithdrawals.setCreateTime(new Date());
		userWithdrawals.setUserName(userName);
		if (category == null || UserConts.USER_WITHDRAWALS_CATEGORY_MONEY.equals(category)) {
			if (user.getMoney() < money) {
				return JsonData.setErrorMessage("可提现余额不足");
			}
			userWithdrawals.setCategory(UserConts.USER_WITHDRAWALS_CATEGORY_MONEY);
			userMoneyService.addUserMoney(user.getId(), user.getMoney(), -userWithdrawals.getMoney(),
					UserConts.USER_MONEY_TYPE_WITHDRAWALS, userWithdrawals.getId());
			user.setMoney(user.getMoney() - userWithdrawals.getMoney());
		} else {
			if (user.getCreditSurplus() < money) {
				return JsonData.setErrorMessage("信用额度不足");
			}
			userWithdrawals.setCategory(UserConts.USER_WITHDRAWALS_CATEGORY_CREDIT);
			userCreditService.addUserCredit(user.getId(), user.getCreditSurplus(), -userWithdrawals.getMoney(),
					UserConts.USER_CREDIT_TYPE_WITHDRAWALS, userWithdrawals.getId());
			user.setCreditSurplus(user.getCreditSurplus() - userWithdrawals.getMoney());
			user.setCreditTotal(user.getCreditTotal() - userWithdrawals.getMoney());
		}
		try {
			userWithdrawalsMapper.insert(userWithdrawals);
			userMapper.updateByPrimaryKey(user);

			// 添加消息记录
			noticeRecordService.addNoticeRecord(userId, NoticeConst.NOTICE_ID_WITHDRAWALS_APPLY,
					NoticeConst.NOTICE_RECORD_TYPE_SYSTEM,
					new String[] { new SimpleDateFormat("yyyy年MM月dd日").format(new Date()),
							new DecimalFormat("0.00").format((double) money / 100),
							account.substring(account.length() - 4, account.length()) });

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("用户id为:" + userId + "申请提现,提现金额为:" + money + ",提现账号为:" + account, "申请提现失败,原因:" + e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("申请失败,请稍后重试");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 商品下架
	 */
	public JsonData setGoodsOffSale(Integer onSaleGoodsId) {
		if (onSaleGoodsId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsUser goodsUser = goodsUserMapper.selectByPrimaryKey(onSaleGoodsId);
		if (goodsUser == null) {
			return JsonData.setErrorMessage("商品不存在");
		}
		if (GoodsConst.GOODS_CATEGORY_SUBSCRIPTION.equals(goodsUser.getCategory())) {
			return JsonData.setErrorMessage("认筹商品不能下架");
		}
		goodsUser.setStatus(GoodsConst.GOODS_STATUS_DELETED);
		goodsUserMapper.updateByPrimaryKeySelective(goodsUser);
		return JsonData.setSuccessMessage();

	}

	/**
	 * 添加商品到我的店铺
	 */
	public JsonData addGoodsByUser(Integer userId, Integer goodsId) {
		if (userId == null || goodsId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		User user = userMapper.selectByPrimaryKey(userId);
		if (user != null && user.getType() == StatusConst.USER_TYPE_PERSONAL) {
			return JsonData.setErrorMessage(null);
		}

		GoodsUserExample example = new GoodsUserExample();
		example.createCriteria().andUserIdEqualTo(userId).andBizIdEqualTo(goodsId).andStatusEqualTo((byte) 2);
		List<GoodsUser> goodsUsers = goodsUserMapper.selectByExample(example);
		if (goodsUsers != null && goodsUsers.size() > 0) {
			return JsonData.setErrorMessage("已经添加过该商品");
		}

		GoodsUser goodsUser = new GoodsUser();
		goodsUser.setUserId(userId);
		goodsUser.setBizId(goodsId);
		goodsUser.setCategory(1);
		goodsUser.setStatus((byte) 2);
		try {
			goodsUserMapper.insertSelective(goodsUser);
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("添加失败");
		}
	}

	/**
	 * 我要提现
	 */
	@Override
	public JsonData getPasswordWithdrawals(Integer userId) {
		if (userId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return JsonData.setErrorMessage("用户不存在");
		}
		if (StringUtils.isBlank(user.getPasswordWithdrawals())) {
			return JsonData.setSuccessMessage(0);
		}
		return JsonData.setSuccessMessage(1);
	}
}
