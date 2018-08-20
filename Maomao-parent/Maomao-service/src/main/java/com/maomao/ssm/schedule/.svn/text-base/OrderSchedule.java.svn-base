package com.maomao.ssm.schedule;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.maomao.ssm.bean.CouponRecord;
import com.maomao.ssm.bean.Goods;
import com.maomao.ssm.bean.GoodsOrder;
import com.maomao.ssm.bean.GoodsOrderExample;
import com.maomao.ssm.bean.GoodsOrderGoods;
import com.maomao.ssm.bean.GoodsOrderGoodsExample;
import com.maomao.ssm.bean.GoodsOrderGoodsInfo;
import com.maomao.ssm.bean.GoodsOrderGoodsInfoExample;
import com.maomao.ssm.bean.GoodsOverstockUserWithBLOBs;
import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.bean.GoodsSku;
import com.maomao.ssm.bean.GoodsSubscription;
import com.maomao.ssm.bean.GoodsSubscriptionRecord;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsUser;
import com.maomao.ssm.bean.GoodsUserExample;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserExample;
import com.maomao.ssm.constant.CouponConts;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.OrderConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.AdminInfoMapper;
import com.maomao.ssm.dao.AdminMapper;
import com.maomao.ssm.dao.CouponRecordMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsOrderGoodsInfoMapper;
import com.maomao.ssm.dao.GoodsOrderGoodsMapper;
import com.maomao.ssm.dao.GoodsOrderMapper;
import com.maomao.ssm.dao.GoodsOverstockMapper;
import com.maomao.ssm.dao.GoodsOverstockUserMapper;
import com.maomao.ssm.dao.GoodsSkuMapper;
import com.maomao.ssm.dao.GoodsSubscriptionMapper;
import com.maomao.ssm.dao.GoodsSubscriptionRecordMapperCustom;
import com.maomao.ssm.dao.GoodsUserMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.service.common.AdminLoanService;
import com.maomao.ssm.service.common.AdminMoneyService;
import com.maomao.ssm.service.common.NoticeRecordService;
import com.maomao.ssm.service.common.UserMoneyService;
import com.maomao.ssm.utils.DateUtils;

@Component
public class OrderSchedule {

	@Autowired
	private GoodsOrderMapper goodsOrderMapper;

	@Autowired
	private GoodsOrderGoodsMapper goodsOrderGoodsMapper;

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private GoodsSubscriptionMapper goodsSubscriptionMapper;

	@Autowired
	private CouponRecordMapper couponRecordMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserMoneyService userMoneyService;

	@Autowired
	private GoodsUserMapper goodsUserMapper;
	@Autowired
	private GoodsSkuMapper goodsSkuMapper;

	@Autowired
	private GoodsSubscriptionRecordMapperCustom goodsSubscriptionRecordMapperCustom;

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private AdminMoneyService adminMoneyService;

	@Autowired
	private AdminLoanService adminLoanService;

	@Autowired
	private AdminInfoMapper adminInfoMapper;

	@Autowired
	private NoticeRecordService noticeRecordService;
	@Autowired
	private GoodsOverstockMapper goodsOverstockMapper;
	@Autowired
	private GoodsOverstockUserMapper goodsOverstockUserMapper;
	@Autowired
	private GoodsOrderGoodsInfoMapper goodsOrderGoodsInfoMapper;

	@Autowired
	private JedisClientPool jedisClientPool;

	/**
	 * 自动关闭15分钟未付款订单 每分钟执行一次
	 */
	@Scheduled(cron = "0 * * * * ?")
	public void updateOrderByClosed() {
		Integer REDIS_LIMIT_TIME = 55;
		String REDIS_LIMIT_NAME = "updateOrderByClosed";
		if (jedisClientPool.exists(REDIS_LIMIT_NAME)) {
			return;
		}
		jedisClientPool.set(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME + "");
		jedisClientPool.expire(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME);

		// 关闭所有15分钟前未付款的订单
		GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
		goodsOrderExample.createCriteria().andStatusEqualTo(OrderConst.ORDER_STATUS_UNPAY)
				.andCreateTimeLessThanOrEqualTo(DateUtils.getBeforeMinDate(OrderConst.ORDER_AUTO_CLOSE_TIME));
		List<GoodsOrder> goodsOrders = goodsOrderMapper.selectByExample(goodsOrderExample);
		if (goodsOrders == null || goodsOrders.size() == 0) {
			return;
		}
		GoodsOrder goodsOrder = new GoodsOrder();
		goodsOrder.setStatus(OrderConst.ORDER_STATUS_CLOSED);
		goodsOrder.setOverTime(new Date());
		goodsOrderMapper.updateByExampleSelective(goodsOrder, goodsOrderExample);
		// 返还库存
		for (GoodsOrder order : goodsOrders) {
			// 获取订单商品信息
			GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
			goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(order.getId());
			GoodsOrderGoods goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample).get(0);
			// 判断是普通商品还是认筹商品
			if (OrderConst.ORDER_CATEGORY_NORMAL.equals(goodsOrderGoods.getCategory())) {
				// 获取商品
				Goods goods = goodsMapper.selectByPrimaryKey(goodsOrderGoods.getBizId());
				goods.setStock(goods.getStock() + goodsOrderGoods.getNum());
				goodsMapper.updateByPrimaryKey(goods);
				GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(goodsOrderGoods.getSkuId());
				goodsSku.setStock(goodsSku.getStock() + goodsOrderGoods.getNum());
				goodsSkuMapper.updateByPrimaryKey(goodsSku);
			}
			if (OrderConst.ORDER_CATEGORY_SUBSCRIPTION.equals(goodsOrderGoods.getCategory())) {
				// 获取商品
				GoodsSubscriptionWithBLOBs goodsSubscription = goodsSubscriptionMapper
						.selectByPrimaryKey(goodsOrderGoods.getBizId());
				goodsSubscription.setStock(goodsSubscription.getStock() + goodsOrderGoods.getNum());
				goodsSubscriptionMapper.updateByPrimaryKey(goodsSubscription);
			}
			if (OrderConst.ORDER_CATEGORY_OVERSTOCK_PURCHASE.equals(goodsOrderGoods.getCategory())) {
				// 获取商品
				GoodsOverstockWithBLOBs goodsOverstock = goodsOverstockMapper
						.selectByPrimaryKey(goodsOrderGoods.getBizId());
				goodsOverstock.setStock(goodsOverstock.getStock() + goodsOrderGoods.getNum());
				goodsOverstockMapper.updateByPrimaryKey(goodsOverstock);
			}
			if (OrderConst.ORDER_CATEGORY_OVERSTOCK_SALES.equals(goodsOrderGoods.getCategory())) {
				// 获取商品
				GoodsOverstockUserWithBLOBs goodsOverstockUser = goodsOverstockUserMapper
						.selectByPrimaryKey(goodsOrderGoods.getBizId());
				goodsOverstockUser.setStock(goodsOverstockUser.getStock() + goodsOrderGoods.getNum());
				goodsOverstockUserMapper.updateByPrimaryKey(goodsOverstockUser);
			}

			// 返优惠券
			if (order.getCouponRecordId() != null && order.getCouponRecordId() != 0) {
				CouponRecord couponRecord = couponRecordMapper.selectByPrimaryKey(order.getCouponRecordId());
				couponRecord.setStatus(CouponConts.COUPON_RECORD_STATUS_UNUSED);
				couponRecord.setUseTime(null);
				couponRecord.setOrderId(null);
				couponRecordMapper.updateByPrimaryKey(couponRecord);
			}
		}
	}

	/**
	 * 自动完成15天前发货的订单 每30分钟执行一次
	 */
	@Scheduled(cron = "0 0/30 * * * ?")
	public void updateOrderByFinish() {
		Integer REDIS_LIMIT_TIME = 29 * 60;
		String REDIS_LIMIT_NAME = "updateOrderByFinish";
		if (jedisClientPool.exists(REDIS_LIMIT_NAME)) {
			return;
		}
		jedisClientPool.set(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME + "");
		jedisClientPool.expire(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME);
		// 完成所有15天前发货的订单
		GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
		goodsOrderExample.createCriteria().andStatusEqualTo(OrderConst.ORDER_STATUS_DELIVERED)
				.andSendTimeLessThanOrEqualTo(DateUtils.getBeforeDayDate(OrderConst.ORDER_AUTO_FINISH_TIME));
		List<GoodsOrder> goodsOrders = goodsOrderMapper.selectByExample(goodsOrderExample);
		if (goodsOrders == null || goodsOrders.size() == 0) {
			return;
		}
		for (GoodsOrder goodsOrder : goodsOrders) {
			Integer orderId = goodsOrder.getId();
			Integer userId = goodsOrder.getUserId();
			// 获取订单商品信息
			GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
			goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(orderId);
			GoodsOrderGoods goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample).get(0);
			// 修改订单状态
			goodsOrder.setStatus(OrderConst.ORDER_STATUS_FINISHED);
			goodsOrder.setOverTime(new Date());
//			if (GoodsConst.GOODS_CATEGORY_NORMAL.equals(goodsOrderGoods.getCategory())) {
//				goodsOrder.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_TRUE);
//			}
			goodsOrderMapper.updateByPrimaryKey(goodsOrder);
//			// 判断是普通商品还是认筹商品
			if (GoodsConst.GOODS_CATEGORY_NORMAL.equals(goodsOrderGoods.getCategory())) {
				// 返利
				// 查询销售用户
				Integer salesUserId = goodsOrder.getSalesUserId();
				if (salesUserId != null) {
					User salesUser = userMapper.selectByPrimaryKey(salesUserId);
					// 更新金额
					userMoneyService.addUserMoney(salesUserId, salesUser.getMoney(),
							goodsOrderGoods.getRebate() * goodsOrderGoods.getNum(), UserConts.USER_MONEY_TYPE_REBATE,
							orderId);
					salesUser.setMoney(salesUser.getMoney() + goodsOrderGoods.getRebate() * goodsOrderGoods.getNum());
					userMapper.updateByPrimaryKey(salesUser);
				}
				// 奖励金
				// 查询邀请人
				User user = userMapper.selectByPrimaryKey(userId);
				String invitationUserMobile = user.getInvitation();
				if (!StringUtils.isBlank(invitationUserMobile)) {
					UserExample userExample = new UserExample();
					userExample.createCriteria().andMobileEqualTo(invitationUserMobile);
					User invitationUser = userMapper.selectByExample(userExample).get(0);
					// 更新金额
					userMoneyService.addUserMoney(invitationUser.getId(), invitationUser.getMoney(),
							goodsOrderGoods.getReword() * goodsOrderGoods.getNum(), UserConts.USER_MONEY_TYPE_REWORD,
							orderId);
					invitationUser.setMoney(
							invitationUser.getMoney() + goodsOrderGoods.getReword() * goodsOrderGoods.getNum());
					userMapper.updateByPrimaryKey(invitationUser);
				}
//				// 常规商品
//				if (GoodsConst.GOODS_TYPE_NORMAL.equals(goodsOrderGoods.getType())) {
//					// 查询上传admin
//					Admin admin = adminMapper.selectByPrimaryKey(goodsOrder.getAdminId());
//					// 更新admin金额 添加金额明细
//					adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(),
//							goodsOrderGoods.getPricePurchase() * goodsOrderGoods.getNum(),
//							UserConts.USER_MONEY_TYPE_SALES, orderId);
//					admin.setMoney(admin.getMoney() + goodsOrderGoods.getPricePurchase() * goodsOrderGoods.getNum());
//					adminMapper.updateByPrimaryKey(admin);
//				}
//				// 质押商品
//				if (GoodsConst.GOODS_TYPE_MORTGAGE.equals(goodsOrderGoods.getType())
//						|| GoodsConst.GOODS_TYPE_VR_MORTGAGE.equals(goodsOrderGoods.getType())) {
//					// 查询上传admin
//					Admin admin = adminMapper.selectByPrimaryKey(goodsOrder.getAdminId());
//					AdminInfoExample adminInfoExample = new AdminInfoExample();
//					adminInfoExample.createCriteria().andAdminIdEqualTo(admin.getId());
//					AdminInfo adminInfo = adminInfoMapper.selectByExample(adminInfoExample).get(0);
//					// 判断贷款是否能还清且有多余
//					Long money = goodsOrderGoods.getPricePurchase() * goodsOrderGoods.getNum();
//					// 贷款已还清
//					if (adminInfo.getLoan() == 0) {
//						// 更新金额
//						adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), money,
//								UserConts.USER_MONEY_TYPE_SALES, orderId);
//						admin.setMoney(admin.getMoney() + money);
//						adminMapper.updateByPrimaryKey(admin);
//					}
//
//					// 能
//					if (adminInfo.getLoan() < money && adminInfo.getLoan() != 0) {
//						// 更新金额
//						// 还清贷款
//						adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -adminInfo.getLoan(),
//								orderId);
//						adminInfo.setLoan(0l);
//						adminInfoMapper.updateByPrimaryKey(adminInfo);
//						// 剩余部分添加到账户内
//						adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), money - adminInfo.getLoan(),
//								UserConts.USER_MONEY_TYPE_SALES, orderId);
//						admin.setMoney(admin.getMoney() + money - adminInfo.getLoan());
//						adminMapper.updateByPrimaryKey(admin);
//					}
//					// 不能 或 刚好还清
//					if (adminInfo.getLoan() >= money) {
//						// 还贷款
//						// 更新金额
//						adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -money, orderId);
//						adminInfo.setLoan(admin.getMoney() - adminInfo.getLoan());
//						adminInfoMapper.updateByPrimaryKey(adminInfo);
//					}
//
//				}
			}
			// 认筹商品
			if (GoodsConst.GOODS_CATEGORY_SUBSCRIPTION.equals(goodsOrderGoods.getCategory())) {
				// 查询认筹记录 // TODO 认筹商品手动分红
				List<GoodsSubscriptionRecord> goodsSubscriptionRecords = goodsSubscriptionRecordMapperCustom
						.selectGroupByUserId(goodsOrderGoods.getBizId());
				for (GoodsSubscriptionRecord record : goodsSubscriptionRecords) {
					/*
					 * User user = userMapper.selectByPrimaryKey(record.getUserId()); // 返还信用额度
					 * UserCredit userCredit = new UserCredit(); userCredit.setUserId(user.getId());
					 * userCredit.setCreditOld(user.getCreditSurplus());
					 * userCredit.setCreditChange(record.getPriceSubcriptionTotal());
					 * userCredit.setCreditNew(user.getCreditSurplus() +
					 * record.getPriceSubcriptionTotal()); userCredit.setBizId(orderId);
					 * userCredit.setType(UserConts.USER_CREDIT_TYPE_SALE_RETURN);
					 * userCredit.setCreateTime(new Date());
					 * user.setCreditSurplus(userCredit.getCreditNew()); // 分红 UserMoney userMoney =
					 * new UserMoney(); userMoney.setUserId(user.getId());
					 * userMoney.setMoneyOld(user.getMoney()); userMoney.setMoneyNew(user.getMoney()
					 * + record.getBonusTotal()); userMoney.setMoneyChange(record.getBonusTotal());
					 * userMoney.setBizId(orderId);
					 * userMoney.setType(UserConts.USER_MONEY_TYPE_BONUS);
					 * userMoney.setCreateTime(new Date()); user.setMoney(userMoney.getMoneyNew());
					 */
					// 删除上架的认筹商品记录
					GoodsUserExample goodsUserExample = new GoodsUserExample();
					goodsUserExample.createCriteria().andUserIdEqualTo(record.getUserId())
							.andBizIdEqualTo(record.getGoodsSubscriptionId())
							.andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
					GoodsUser goodsUser = new GoodsUser();
					goodsUser.setStatus(GoodsConst.GOODS_STATUS_DELETED);
					// 更新数据 // XXX 修改成批量操作
					// userCreditMapper.insert(userCredit);
					// userMoneyMapper.insert(userMoney);
					// userMapper.updateByPrimaryKey(user);
					goodsUserMapper.updateByExampleSelective(goodsUser, goodsUserExample);

					// 添加通知记录
					noticeRecordService.addNoticeRecord(record.getUserId(),
							NoticeConst.NOTICE_ID_SUBCRIPTION_ORDER_FINISH, NoticeConst.NOTICE_RECORD_TYPE_SYSTEM,
							new String[] { goodsOrderGoods.getName(),
									((double) record.getPriceSubcriptionTotal()) / 100 + "",
									((double) goodsOrderGoods.getReword()) / 100 + "" });
				}
				// 店铺销售奖励
				/*
				 * Integer salesUserId = goodsOrder.getSalesUserId(); User salesUser =
				 * userMapper.selectByPrimaryKey(salesUserId); UserMoney salesUserMoney = new
				 * UserMoney(); salesUserMoney.setUserId(salesUserId);
				 * salesUserMoney.setMoneyOld(salesUser.getMoney());
				 * salesUserMoney.setMoneyNew(salesUser.getMoney() +
				 * goodsOrderGoods.getRebate());
				 * salesUserMoney.setMoneyChange(goodsOrderGoods.getRebate());
				 * salesUserMoney.setBizId(orderId);
				 * salesUserMoney.setType(UserConts.USER_MONEY_TYPE_REBATE);
				 * salesUserMoney.setCreateTime(new Date());
				 * salesUser.setMoney(salesUserMoney.getMoneyNew());
				 * userMoneyMapper.insert(salesUserMoney);
				 * userMapper.updateByPrimaryKey(salesUser);
				 */
				// 更新认筹商品状态
				GoodsSubscription goodsSubscription = goodsSubscriptionMapper
						.selectByPrimaryKey(goodsOrderGoods.getBizId());
				goodsSubscription.setSubscriptionStatus(GoodsConst.GOODS_SUBSCRIPTION_STATUS_FINISHED);
				goodsSubscriptionMapper.updateByPrimaryKey(goodsSubscription);
			}
		}
	}

	/**
	 * 自动过期 已过期的核销压货商品信息 每30分钟执行一次
	 */
	@Scheduled(cron = "0 0/30 * * * ?")
	public void updateOrderGoodsInfoByExpire() {
		Integer REDIS_LIMIT_TIME = 29 * 60;
		String REDIS_LIMIT_NAME = "updateOrderGoodsInfoByExpire";
		if (jedisClientPool.exists(REDIS_LIMIT_NAME)) {
			return;
		}
		jedisClientPool.set(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME + "");
		jedisClientPool.expire(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME);

		// 完成所有15天前发货的订单
		GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
		goodsOrderGoodsInfoExample.createCriteria().andStatusEqualTo(OrderConst.ORDER_STATUS_DELIVERED)
				.andTypeEqualTo(GoodsConst.GOODS_OVERSTOCK_TYPE_CODE).andUseEndTimeLessThan(new Date());
		GoodsOrderGoodsInfo goodsOrderGoodsInfo = new GoodsOrderGoodsInfo();
		goodsOrderGoodsInfo.setStatus(OrderConst.ORDER_STATUS_CLOSED);
		goodsOrderGoodsInfo.setModifiedTime(new Date());
		goodsOrderGoodsInfoMapper.updateByExampleSelective(goodsOrderGoodsInfo, goodsOrderGoodsInfoExample);
	}

}
