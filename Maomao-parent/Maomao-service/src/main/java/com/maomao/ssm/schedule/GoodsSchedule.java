package com.maomao.ssm.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.maomao.ssm.bean.GoodsOrder;
import com.maomao.ssm.bean.GoodsOrderExample;
import com.maomao.ssm.bean.GoodsOrderGoods;
import com.maomao.ssm.bean.GoodsOrderGoodsExample;
import com.maomao.ssm.bean.GoodsSubscription;
import com.maomao.ssm.bean.GoodsSubscriptionExample;
import com.maomao.ssm.bean.GoodsSubscriptionRecord;
import com.maomao.ssm.bean.GoodsSubscriptionRecordExample;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsUser;
import com.maomao.ssm.bean.GoodsUserExample;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.OrderConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.GoodsOrderGoodsMapper;
import com.maomao.ssm.dao.GoodsOrderMapper;
import com.maomao.ssm.dao.GoodsSubscriptionMapper;
import com.maomao.ssm.dao.GoodsSubscriptionRecordMapper;
import com.maomao.ssm.dao.GoodsSubscriptionRecordMapperCustom;
import com.maomao.ssm.dao.GoodsUserMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.service.common.NoticeRecordService;
import com.maomao.ssm.service.common.UserCreditService;

@Component
public class GoodsSchedule {

	@Autowired
	private GoodsSubscriptionMapper goodsSubscriptionMapper;

	@Autowired
	private GoodsSubscriptionRecordMapper goodsSubscriptionRecordMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserCreditService userCreditService;

	@Autowired
	private GoodsUserMapper goodsUserMapper;

	@Autowired
	private GoodsOrderGoodsMapper goodsOrderGoodsMapper;

	@Autowired
	private GoodsOrderMapper goodsOrderMapper;

	@Autowired
	private GoodsSubscriptionRecordMapperCustom goodsSubscriptionRecordMapperCustom;

	@Autowired
	private NoticeRecordService noticeRecordService;

	@Autowired
	private JedisClientPool jedisClientPool;

	/**
	 * 自动设置认筹商品开始 每1分钟执行一次
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void updateGoodsSubscriptionByStart() {
		Integer REDIS_LIMIT_TIME = 59;
		String REDIS_LIMIT_NAME = "updateGoodsSubscriptionByStart";
		if (jedisClientPool.exists(REDIS_LIMIT_NAME)) {
			return;
		}
		jedisClientPool.set(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME + "");
		jedisClientPool.expire(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME);

		GoodsSubscriptionExample goodsSubscriptionExample = new GoodsSubscriptionExample();
		goodsSubscriptionExample.createCriteria().andSubscriptionStartTimeLessThanOrEqualTo(new Date())
				.andSubscriptionStatusEqualTo(GoodsConst.GOODS_SUBSCRIPTION_STATUS_UNSTART);
		GoodsSubscriptionWithBLOBs goodsSubscription = new GoodsSubscriptionWithBLOBs();
		goodsSubscription.setSubscriptionStatus(GoodsConst.GOODS_SUBSCRIPTION_STATUS_SUBSCRIBING);
		goodsSubscriptionMapper.updateByExampleSelective(goodsSubscription, goodsSubscriptionExample);
	}

	/**
	 * 自动下架认筹失败的认筹商品 每天0点执行一次
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void updateGoodsSubscriptionBySubscribeFailed() {
		Integer REDIS_LIMIT_TIME = 23 * 60 * 60 + 30;
		String REDIS_LIMIT_NAME = "updateGoodsSubscriptionBySubscribeFailed";
		if (jedisClientPool.exists(REDIS_LIMIT_NAME)) {
			return;
		}
		jedisClientPool.set(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME + "");
		jedisClientPool.expire(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME);

		// 取认筹中已过期的商品
		GoodsSubscriptionExample goodsSubscriptionExample = new GoodsSubscriptionExample();
		goodsSubscriptionExample.createCriteria()
				.andSubscriptionStatusEqualTo(GoodsConst.GOODS_SUBSCRIPTION_STATUS_SUBSCRIBING)
				.andSubscriptionEndTimeLessThanOrEqualTo(new Date());
		List<GoodsSubscription> goodsSubscriptions = goodsSubscriptionMapper.selectByExample(goodsSubscriptionExample);
		if (goodsSubscriptions == null || goodsSubscriptions.size() == 0) {
			return;
		}
		// 修改认筹商品状态
		GoodsSubscriptionWithBLOBs goodsSubscription = new GoodsSubscriptionWithBLOBs();
		goodsSubscription.setSubscriptionStatus(GoodsConst.GOODS_SUBSCRIPTION_STATUS_SUBSCRIBE_FAILED);
		goodsSubscriptionMapper.updateByExampleSelective(goodsSubscription, goodsSubscriptionExample);
		// 返还信用额度
		for (GoodsSubscription goods : goodsSubscriptions) {
			// 查询认筹记录 // XXX 批量操作
			GoodsSubscriptionRecordExample goodsSubscriptionRecordExample = new GoodsSubscriptionRecordExample();
			goodsSubscriptionRecordExample.createCriteria().andGoodsSubscriptionIdEqualTo(goods.getId());
			List<GoodsSubscriptionRecord> goodsSubscriptionRecords = goodsSubscriptionRecordMapper
					.selectByExample(goodsSubscriptionRecordExample);
			// XXX 批量操作
			for (GoodsSubscriptionRecord goodsSubscriptionRecord : goodsSubscriptionRecords) {
				// 查询用户信息
				User user = userMapper.selectByPrimaryKey(goodsSubscriptionRecord.getUserId());
				// 返还信用额度
				userCreditService.addUserCredit(user.getId(), user.getCreditSurplus(),
						goodsSubscriptionRecord.getPriceSubcriptionTotal(), UserConts.USER_CREDIT_TYPE_FAILED_RETURN,
						goodsSubscriptionRecord.getId());
				user.setCreditSurplus(user.getCreditSurplus() + goodsSubscriptionRecord.getPriceSubcriptionTotal());
				userMapper.updateByPrimaryKey(user);
			}
			// 添加消息记录
			List<GoodsSubscriptionRecord> goodsSubscriptionRecords2 = goodsSubscriptionRecordMapperCustom
					.selectGroupByUserId(goods.getId());
			for (GoodsSubscriptionRecord goodsSubscriptionRecord : goodsSubscriptionRecords2) {
				noticeRecordService.addNoticeRecord(goodsSubscriptionRecord.getUserId(),
						NoticeConst.NOTICE_ID_GOODS_SUBCRIBE_FAILED, NoticeConst.NOTICE_RECORD_TYPE_SYSTEM,
						new String[] { goods.getName() });
			}

		}
	}

	/**
	 * 自动下架销售失败的认筹商品 每天2点执行一次
	 */
	@Scheduled(cron = "0 0 2 * * ?")
	public void updateGoodsSubscriptionBySaleFailed() {
		Integer REDIS_LIMIT_TIME = 23 * 60 * 60 + 30;
		String REDIS_LIMIT_NAME = "updateGoodsSubscriptionBySaleFailed";
		if (jedisClientPool.exists(REDIS_LIMIT_NAME)) {
			return;
		}
		jedisClientPool.set(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME + "");
		jedisClientPool.expire(REDIS_LIMIT_NAME, REDIS_LIMIT_TIME);

		// 取销售中已过期的商品
		GoodsSubscriptionExample goodsSubscriptionExample = new GoodsSubscriptionExample();
		goodsSubscriptionExample.createCriteria()
				.andSubscriptionStatusEqualTo(GoodsConst.GOODS_SUBSCRIPTION_STATUS_ON_SALE)
				.andSalesEndTimeLessThanOrEqualTo(new Date());
		List<GoodsSubscription> goodsSubscriptions = goodsSubscriptionMapper.selectByExample(goodsSubscriptionExample);
		if (goodsSubscriptions == null || goodsSubscriptions.size() == 0) {
			return;
		}
		// 修改认筹商品状态
		GoodsSubscriptionWithBLOBs goodsSubscription = new GoodsSubscriptionWithBLOBs();
		goodsSubscription.setSubscriptionStatus(GoodsConst.GOODS_SUBSCRIPTION_STATUS_SALE_FAILED);
		goodsSubscriptionMapper.updateByExampleSelective(goodsSubscription, goodsSubscriptionExample);

		for (GoodsSubscription goods : goodsSubscriptions) {
			// 查询认筹记录 // XXX 批量操作
			GoodsSubscriptionRecordExample goodsSubscriptionRecordExample = new GoodsSubscriptionRecordExample();
			goodsSubscriptionRecordExample.createCriteria().andGoodsSubscriptionIdEqualTo(goods.getId());
			List<GoodsSubscriptionRecord> goodsSubscriptionRecords = goodsSubscriptionRecordMapper
					.selectByExample(goodsSubscriptionRecordExample);
			// XXX 批量操作
			for (GoodsSubscriptionRecord goodsSubscriptionRecord : goodsSubscriptionRecords) {
				// 查询用户信息
				User user = userMapper.selectByPrimaryKey(goodsSubscriptionRecord.getUserId());
				// 返还信用额度
				userCreditService.addUserCredit(user.getId(), user.getCreditSurplus(),
						goodsSubscriptionRecord.getPriceSubcriptionTotal(), UserConts.USER_CREDIT_TYPE_FAILED_RETURN,
						goodsSubscriptionRecord.getId());
				user.setCreditSurplus(user.getCreditSurplus() + goodsSubscriptionRecord.getPriceSubcriptionTotal());
				userMapper.updateByPrimaryKey(user);
			}
			// 关闭未支付订单
			GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
			goodsOrderGoodsExample.createCriteria().andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION)
					.andBizIdEqualTo(goods.getId());
			List<GoodsOrderGoods> goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample);
			if (goodsOrderGoods != null && goodsOrderGoods.size() > 0) {
				GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
				// 最后一个订单商品的对应订单
				goodsOrderExample.createCriteria()
						.andIdEqualTo(goodsOrderGoods.get(goodsOrderGoods.size() - 1).getOrderId());
				GoodsOrder goodsOrder = new GoodsOrder();
				goodsOrder.setStatus(OrderConst.ORDER_STATUS_CLOSED);
				goodsOrder.setOverTime(new Date());
				goodsOrderMapper.updateByExampleSelective(goodsOrder, goodsOrderExample);
			}
			// 下架所有店铺中的该商品
			List<GoodsSubscriptionRecord> goodsSubscriptionRecords2 = goodsSubscriptionRecordMapperCustom
					.selectGroupByUserId(goods.getId());
			if (goodsSubscriptionRecords2 != null && goodsSubscriptionRecords2.size() > 0) {
				List<Integer> userIdList = new ArrayList<Integer>();
				for (GoodsSubscriptionRecord goodsSubscriptionRecord : goodsSubscriptionRecords2) {
					userIdList.add(goodsSubscriptionRecord.getUserId());
					// 添加消息记录
					noticeRecordService.addNoticeRecord(goodsSubscriptionRecord.getUserId(),
							NoticeConst.NOTICE_ID_GOODS_SUBCRIPTION_OFF_SHELF, NoticeConst.NOTICE_RECORD_TYPE_SYSTEM,
							new String[] { goods.getName() });
				}
				GoodsUserExample goodsUserExample = new GoodsUserExample();
				goodsUserExample.createCriteria().andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION)
						.andBizIdEqualTo(goods.getId()).andUserIdIn(userIdList);
				GoodsUser goodsUser = new GoodsUser();
				goodsUser.setStatus(GoodsConst.GOODS_STATUS_OFF_SHELF);
				goodsUserMapper.updateByExampleSelective(goodsUser, goodsUserExample);

			}
		}
	}
}
