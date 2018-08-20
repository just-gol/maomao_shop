package com.maomao.ssm.service.impl;

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

import com.alipay.api.AlipayApiException;
import com.github.pagehelper.PageHelper;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.maomao.ssm.alipay.AlipayUtils;
import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.AdminInfo;
import com.maomao.ssm.bean.AdminInfoExample;
import com.maomao.ssm.bean.CouponRecord;
import com.maomao.ssm.bean.CouponRecordExample;
import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsAddressExample;
import com.maomao.ssm.bean.GoodsOrder;
import com.maomao.ssm.bean.GoodsOrderCustom;
import com.maomao.ssm.bean.GoodsOrderExample;
import com.maomao.ssm.bean.GoodsOrderGoods;
import com.maomao.ssm.bean.GoodsOrderGoodsExample;
import com.maomao.ssm.bean.GoodsOrderGoodsInfo;
import com.maomao.ssm.bean.GoodsOrderGoodsInfoExample;
import com.maomao.ssm.bean.GoodsOverstockInfo;
import com.maomao.ssm.bean.GoodsOverstockInfoExample;
import com.maomao.ssm.bean.GoodsOverstockInfoUser;
import com.maomao.ssm.bean.GoodsOverstockInfoUserExample;
import com.maomao.ssm.bean.GoodsOverstockUserWithBLOBs;
import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.bean.GoodsSku;
import com.maomao.ssm.bean.GoodsSubscription;
import com.maomao.ssm.bean.GoodsSubscriptionRecord;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsUser;
import com.maomao.ssm.bean.GoodsUserExample;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserAddress;
import com.maomao.ssm.bean.UserAddressExample;
import com.maomao.ssm.bean.UserExample;
import com.maomao.ssm.constant.CouponConts;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.OrderConst;
import com.maomao.ssm.constant.PageConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.AdminInfoMapper;
import com.maomao.ssm.dao.AdminMapper;
import com.maomao.ssm.dao.CouponRecordMapper;
import com.maomao.ssm.dao.GoodsAddressMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsOrderGoodsInfoMapper;
import com.maomao.ssm.dao.GoodsOrderGoodsMapper;
import com.maomao.ssm.dao.GoodsOrderMapper;
import com.maomao.ssm.dao.GoodsOrderMapperCustom;
import com.maomao.ssm.dao.GoodsOverstockInfoMapper;
import com.maomao.ssm.dao.GoodsOverstockInfoUserMapper;
import com.maomao.ssm.dao.GoodsOverstockMapper;
import com.maomao.ssm.dao.GoodsOverstockUserMapper;
import com.maomao.ssm.dao.GoodsSkuMapper;
import com.maomao.ssm.dao.GoodsSubscriptionMapper;
import com.maomao.ssm.dao.GoodsSubscriptionRecordMapper;
import com.maomao.ssm.dao.GoodsSubscriptionRecordMapperCustom;
import com.maomao.ssm.dao.GoodsUserMapper;
import com.maomao.ssm.dao.UserAddressMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.order.Order;
import com.maomao.ssm.pojo.order.OrderComfirm;
import com.maomao.ssm.pojo.order.OrderCreate;
import com.maomao.ssm.service.OrderService;
import com.maomao.ssm.service.common.AdminLoanService;
import com.maomao.ssm.service.common.AdminMoneyService;
import com.maomao.ssm.service.common.NoticeRecordService;
import com.maomao.ssm.service.common.UserCreditService;
import com.maomao.ssm.service.common.UserMoneyService;
import com.maomao.ssm.utils.DateUtils;
import com.maomao.ssm.utils.JsonUtils;
import com.maomao.ssm.utils.OrderUtil;
import com.maomao.ssm.wxpay.MyWxpayConfig;
import com.maomao.ssm.wxpay.WxpayUtils;
import com.maomao.ssm.yspay.action.ApipaySubmit;

@Service
public class OrderServiceImpl implements OrderService {

	private Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private GoodsOrderMapper goodsOrderMapper;

	@Autowired
	private UserAddressMapper userAddressMapper;

	@Autowired
	private CouponRecordMapper couponRecordMapper;

	@Autowired
	private JedisClientPool jedisClientPool;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private GoodsSubscriptionMapper goodsSubscriptionMapper;

	@Autowired
	private GoodsOrderGoodsMapper goodsOrderGoodsMapper;

	@Autowired
	private UserMoneyService userMoneyService;

	@Autowired
	private UserCreditService userCreditService;

	@Autowired
	private GoodsSubscriptionRecordMapperCustom goodsSubscriptionRecordMapperCustom;

	@Autowired
	private GoodsUserMapper goodsUserMapper;

	@Autowired
	private GoodsOrderMapperCustom goodsOrderMapperCustom;

	@Autowired
	private GoodsSubscriptionRecordMapper goodsSubscriptionRecordMapper;

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
	private AlipayUtils alipayUtils;
	@Autowired
	private WxpayUtils wxpayUtils;

	@Autowired
	private GoodsSkuMapper goodsSkuMapper;

	@Autowired
	private GoodsAddressMapper goodsAddressMapper;
	@Autowired
	private GoodsOrderGoodsInfoMapper goodsOrderGoodsInfoMapper;
	@Autowired
	private GoodsOverstockUserMapper goodsOverstockUserMapper;
	@Autowired
	private GoodsOverstockMapper goodsOverstockMapper;
	@Autowired
	private GoodsOverstockInfoUserMapper goodsOverstockInfoUserMapper;
	@Autowired
	private GoodsOverstockInfoMapper goodsOverstockInfoMapper;

	/**
	 * 获取订单列表
	 */
	@Override
	public JsonData getOrderList(Integer userId, Integer pages, Integer rows) {
		// 验证参数
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		pages = pages == null || pages < 3 ? PageConst.PAGE_DEFAULT : pages;
		rows = rows == null ? pages == PageConst.PAGE_DEFAULT ? PageConst.ROWS_DEFAULT_FIRST : PageConst.ROWS_DEFAULT
				: rows;
		// 查询订单
		PageHelper.startPage(pages, rows);
		List<GoodsOrderCustom> orders = goodsOrderMapperCustom.selectListByUserId(userId);
		// 构造返回值
		List<Order> orderList = new ArrayList<Order>();
		for (GoodsOrderCustom order : orders) {
			orderList.add(new Order(order));
		}
		return JsonData.setSuccessMessage(orderList);
	}

	/**
	 * 获取订单详情
	 */
	@Override
	public JsonData getOrderDetail(Integer userId, Integer orderId) {
		// 验证参数
		if (userId == null || orderId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询订单
		GoodsOrderCustom goodsOrderCustom = goodsOrderMapperCustom.selectByOrderId(orderId);
		Order order = new Order(goodsOrderCustom);
		// 压货备货订单 压货售出订单
		if (goodsOrderCustom.getCategory().equals(OrderConst.ORDER_CATEGORY_OVERSTOCK_PURCHASE)
				|| goodsOrderCustom.getCategory().equals(OrderConst.ORDER_CATEGORY_OVERSTOCK_SALES)) {
			GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
			goodsOrderGoodsInfoExample.createCriteria().andOrderIdEqualTo(orderId);
			goodsOrderGoodsInfoExample.setOrderByClause("type");
			List<GoodsOrderGoodsInfo> goodsOrderGoodsInfos = goodsOrderGoodsInfoMapper
					.selectByExample(goodsOrderGoodsInfoExample);
			order = new Order(goodsOrderCustom, goodsOrderGoodsInfos);
		}
		return JsonData.setSuccessMessage(order);
	}

	/**
	 * 确认订单
	 */
	@Override
	public JsonData getOrderConfirm(Integer userId, Integer goodsId, Integer skuId, Integer goodsNum,
			Integer category) {
		// 验证参数
		if (userId == null || goodsId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询默认收货地址
		UserAddressExample userAddressExample = new UserAddressExample();
		com.maomao.ssm.bean.UserAddressExample.Criteria userAddressCriteria = userAddressExample.createCriteria();
		userAddressCriteria.andUserIdEqualTo(userId).andDefaultAddressEqualTo(UserConts.USER_ADDRESS_DEFAULT_TRUE);
		List<UserAddress> userAddresses = userAddressMapper.selectByExample(userAddressExample);
		UserAddress userAddress = null;
		if (userAddresses != null && userAddresses.size() > 0) {
			userAddress = userAddresses.get(0);
		}
		// 压货备货
		if (OrderConst.ORDER_CATEGORY_OVERSTOCK_PURCHASE.equals(category)) {
			GoodsOverstockWithBLOBs goodsOverstockWithBLOBs = goodsOverstockMapper.selectByPrimaryKey(goodsId);
			// 判断购买数量是否够
			if (goodsNum < goodsOverstockWithBLOBs.getBuyNum()) {
				return JsonData.setErrorMessage("购买数量少于最少购买量");
			}
			// 判断存库是否够
			if (goodsOverstockWithBLOBs.getStock() < goodsNum) {
				return JsonData.setErrorMessage("库存不足");
			}
			// 构造返回值
			OrderComfirm orderComfirm = new OrderComfirm(userAddress);
			return JsonData.setSuccessMessage(orderComfirm);
		}
		// 查询可用优惠券数量
		Long availableMoney = 0l;
		// 认筹
		if (OrderConst.ORDER_CATEGORY_SUBSCRIPTION.equals(category)) {
			GoodsSubscriptionWithBLOBs goodsSubscription = goodsSubscriptionMapper.selectByPrimaryKey(goodsId);
			// 判断存库是否够
			if (goodsSubscription.getStock() < goodsNum) {
				return JsonData.setErrorMessage("库存不足");
			}
			// 计算可用金额
			availableMoney = goodsSubscription.getPriceSales() * goodsNum;
			if (goodsSubscription.getDeposit() != null && goodsSubscription.getDeposit() != 0) {
				availableMoney = goodsSubscription.getDeposit() * goodsNum;
			}
		}
		// 普通
		if (OrderConst.ORDER_CATEGORY_NORMAL.equals(category)) {
			GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(goodsId);
			GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(skuId);
			// 判断存库是否够
			if (goodsSku.getStock() < goodsNum) {
				return JsonData.setErrorMessage("库存不足");
			}
			// 计算可用金额
			if (goods.getDeposit() != null && goods.getDeposit() != 0) {
				availableMoney = goods.getDeposit() * goodsNum;
			} else {
				availableMoney = goodsSku.getPriceSales() * goodsNum;
			}
		}
		// 压货购买
		if (OrderConst.ORDER_CATEGORY_OVERSTOCK_SALES.equals(category)) {
			GoodsOverstockUserWithBLOBs goodsOverstockUserWithBLOBs = goodsOverstockUserMapper
					.selectByPrimaryKey(goodsId);
			// 判断是否自己购买
			if (goodsOverstockUserWithBLOBs.getUserId().equals(userId)) {
				OrderComfirm orderComfirm = new OrderComfirm(userAddress);
				return JsonData.setSuccessMessage(orderComfirm);
			}
			// 判断存库是否够
			if (goodsOverstockUserWithBLOBs.getStock() < goodsNum) {
				return JsonData.setErrorMessage("库存不足");
			}
			// 计算可用金额
			availableMoney = goodsOverstockUserWithBLOBs.getPrice() * goodsNum;
		}
		CouponRecordExample couponRecordExample = new CouponRecordExample();
		com.maomao.ssm.bean.CouponRecordExample.Criteria couponRecordCriteria = couponRecordExample.createCriteria();
		couponRecordCriteria.andUserIdEqualTo(userId).andStatusEqualTo(CouponConts.COUPON_RECORD_STATUS_UNUSED)
				.andAvailableMoneyLessThanOrEqualTo(availableMoney);
		int couponNum = couponRecordMapper.countByExample(couponRecordExample);
		// 构造返回值
		OrderComfirm orderComfirm = new OrderComfirm(userAddress, couponNum);
		return JsonData.setSuccessMessage(orderComfirm);
	}

	/**
	 * 下单
	 */
	@Override
	public JsonData addOrder(Integer userId, Integer goodsId, Integer category, Integer addressId, Integer goodsNum,
			Byte payType, Byte getWay, Integer couponRecordId, Byte orderSrc, Integer salesUserId, Long timestamp,
			Integer skuId) {
		// 验证参数
		if (userId == null || goodsId == null || category == null || goodsNum == null || orderSrc == null
				|| timestamp == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 验证重复提交
		if (jedisClientPool.exists(RedisConst.ORDER_COMFIRM + userId + timestamp)) {
			return JsonData.setErrorMessage("请勿重复提交");
		}
		jedisClientPool.set(RedisConst.ORDER_COMFIRM + userId + timestamp, RedisConst.ORDER_COMFIRM_EXPIRE_TIME + "");
		jedisClientPool.expire(RedisConst.ORDER_COMFIRM + userId + timestamp, RedisConst.ORDER_COMFIRM_EXPIRE_TIME);
		// 压货备货
		if (OrderConst.ORDER_CATEGORY_OVERSTOCK_PURCHASE.equals(category)) {
			// 查询用户信息
			User user = userMapper.selectByPrimaryKey(userId);
			// 生成订单信息
			GoodsOrder goodsOrder = new GoodsOrder();
			goodsOrder.setUserId(userId);
			goodsOrder.setOrderNum(OrderUtil.getOrderNum(user.getType()));
			goodsOrder.setStatus(OrderConst.ORDER_STATUS_UNPAY);
			goodsOrder.setCreateTime(new Date());
			goodsOrder.setOrderSrc(orderSrc);
			goodsOrder.setPayType(payType);
			goodsOrder.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_FALES);
			// 设置地址
			// 查询收货地址
			UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
			if (userAddress == null) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("收货地址错误");
			}
			goodsOrder.setUserName(userAddress.getName());
			goodsOrder.setMobile(userAddress.getMobile());
			goodsOrder.setAddress(userAddress.getProvince() + userAddress.getCity() + userAddress.getArea()
					+ userAddress.getAddress());

			// 查询商品
			GoodsOverstockWithBLOBs goodsOverstock = goodsOverstockMapper.selectByPrimaryKey(goodsId);
			if (goodsOverstock == null) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("压货商品信息不存在");
			}
			// 判断存库是否够
			if (goodsOverstock.getStock() < goodsNum || goodsOverstock.getStock() < goodsOverstock.getBuyNum()) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("库存不足");
			}
			// 判断是否大于最少压货数量
			if (goodsNum < goodsOverstock.getBuyNum()) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("购买数量少于最少购买量");
			}

			GoodsOrderGoods goodsOrderGoods = new GoodsOrderGoods();
			goodsOrderGoods.setCategory(category);
			goodsOrderGoods.setBizId(goodsId);
			goodsOrderGoods.setNum(goodsNum);
			goodsOrderGoods.setCreateTime(new Date());
			goodsOrderGoods.setName(goodsOverstock.getName());
			goodsOrderGoods.setPriceSales(goodsOverstock.getPrice());
			goodsOrderGoods.setRebate(goodsOverstock.getRebate());
			goodsOrderGoods.setImgs(goodsOverstock.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
			goodsOrderGoods.setDeposit(0l);
			goodsOrder.setOrderCategory(category);

			goodsOrder.setMoney(goodsOverstock.getPrice() * goodsNum);
			goodsOrder.setUnpayMoney(0l);

			// 减少库存
			goodsOverstock.setStock(goodsOverstock.getStock() - goodsNum);
			goodsOverstock.setSales(goodsOverstock.getSales() + goodsNum);
			goodsOverstockMapper.updateByPrimaryKey(goodsOverstock);
			goodsOrderMapper.insert(goodsOrder);
			goodsOrderGoods.setOrderId(goodsOrder.getId());
			goodsOrderGoodsMapper.insert(goodsOrderGoods);

			// 查询压货商品具体信息
			GoodsOverstockInfoExample goodsOverstockInfoExample = new GoodsOverstockInfoExample();
			goodsOverstockInfoExample.createCriteria().andGoodsOverstockIdEqualTo(goodsId)
					.andStatusEqualTo(GoodsConst.GOODS_OVERSTOCK_INFO_STATUS_NORMAL);
			List<GoodsOverstockInfo> goodsOverstockInfos = goodsOverstockInfoMapper
					.selectByExample(goodsOverstockInfoExample);
			if (goodsOverstockInfos == null || goodsOverstockInfos.size() == 0) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("压货商品具体信息不存在");
			}
			for (GoodsOverstockInfo goodsOverstockInfo : goodsOverstockInfos) {
				GoodsOrderGoodsInfo goodsOrderGoodsInfo = new GoodsOrderGoodsInfo();
				goodsOrderGoodsInfo.setOrderId(goodsOrder.getId());
				goodsOrderGoodsInfo.setGoodsOrderGoodsId(goodsOrderGoods.getId());
				goodsOrderGoodsInfo.setGoodsOverstockId(goodsOverstock.getId());
				goodsOrderGoodsInfo.setGoodsOverstockInfoId(goodsOverstockInfo.getId());
				goodsOrderGoodsInfo.setGoodsName(goodsOverstockInfo.getGoodsName());
				goodsOrderGoodsInfo.setSku(goodsOverstockInfo.getSku());
				goodsOrderGoodsInfo.setPricePurchase(goodsOverstockInfo.getPricePurchase());
				goodsOrderGoodsInfo.setPriceSales(goodsOverstockInfo.getPriceSales());
				goodsOrderGoodsInfo.setType(goodsOverstockInfo.getType());
				goodsOrderGoodsInfo.setUseEndTime(goodsOverstockInfo.getUseEndTime());
				goodsOrderGoodsInfo.setImg(goodsOverstockInfo.getImg());
				goodsOrderGoodsInfo.setAdminId(goodsOverstockInfo.getAdminId());
				goodsOrderGoodsInfo.setUserId(userId);
				goodsOrderGoodsInfo.setCreateTime(new Date());
				goodsOrderGoodsInfo.setModifiedTime(new Date());
				if (goodsOverstockInfo.getType().equals(GoodsConst.GOODS_OVERSTOCK_TYPE_CODE)) {
					GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
					goodsAddressExample.createCriteria().andBizIdEqualTo(goodsOverstockInfo.getId())
							.andCategoryEqualTo(GoodsConst.GOODS_WAREHOUSE_OVERSTOCK);
					List<GoodsAddress> goodsAddresses = goodsAddressMapper.selectByExample(goodsAddressExample);
					String addrezz = "";
					for (GoodsAddress goodsAddress : goodsAddresses) {
						addrezz += goodsAddress.getAddress() + StatusConst.IMG_SPLIT_STRING;
					}
					addrezz = addrezz.substring(0, addrezz.length() - 1);
					goodsOrderGoodsInfo.setAddrezz(addrezz);
				}
				goodsOrderGoodsInfoMapper.insert(goodsOrderGoodsInfo);
			}

			OrderCreate orderCreate = new OrderCreate(goodsOrder);
			return JsonData.setSuccessMessage(orderCreate);
		}
		// 压货购买
		if (OrderConst.ORDER_CATEGORY_OVERSTOCK_SALES.equals(category)) {
			// 查询用户信息
			User user = userMapper.selectByPrimaryKey(userId);
			// 生成订单信息
			GoodsOrder goodsOrder = new GoodsOrder();
			goodsOrder.setUserId(userId);
			goodsOrder.setOrderNum(OrderUtil.getOrderNum(user.getType()));
			goodsOrder.setStatus(OrderConst.ORDER_STATUS_UNPAY);
			goodsOrder.setCreateTime(new Date());
			goodsOrder.setOrderSrc(orderSrc);
			goodsOrder.setPayType(payType);
			goodsOrder.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_FALES);
			goodsOrder.setSalesUserId(salesUserId);
			// 设置地址
			// 查询收货地址
			UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
			if (userAddress == null) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("收货地址错误");
			}
			goodsOrder.setUserName(userAddress.getName());
			goodsOrder.setMobile(userAddress.getMobile());
			goodsOrder.setAddress(userAddress.getProvince() + userAddress.getCity() + userAddress.getArea()
					+ userAddress.getAddress());

			// 查询商品
			GoodsOverstockUserWithBLOBs goodsOverstockUser = goodsOverstockUserMapper.selectByPrimaryKey(goodsId);
			if (goodsOverstockUser == null) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("用户压货商品信息不存在");
			}
			// 判断状态
			if (!goodsOverstockUser.getStatus().equals(GoodsConst.GOODS_OVERSTOCK_USER_STATUS_ON_SALE)) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("商品已下架或已删除");
			}
			// 判断存库是否够
			if (goodsOverstockUser.getStock() < goodsNum) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("库存不足");
			}

			GoodsOrderGoods goodsOrderGoods = new GoodsOrderGoods();
			goodsOrderGoods.setCategory(category);
			goodsOrderGoods.setBizId(goodsId);
			goodsOrderGoods.setNum(goodsNum);
			goodsOrderGoods.setCreateTime(new Date());
			goodsOrderGoods.setName(goodsOverstockUser.getName());
			goodsOrderGoods.setPriceSales(goodsOverstockUser.getPrice());
			goodsOrderGoods.setRebate(goodsOverstockUser.getRebate());
			goodsOrderGoods.setImgs(goodsOverstockUser.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
			goodsOrderGoods.setDeposit(0l);
			goodsOrder.setOrderCategory(category);
			// 查询优惠券
			CouponRecord couponRecord = null;
			if (couponRecordId != null) {
				couponRecord = couponRecordMapper.selectByPrimaryKey(couponRecordId);
				if (couponRecord != null && !couponRecord.getStatus().equals(CouponConts.COUPON_RECORD_STATUS_UNUSED)) {
					jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
					return JsonData.setErrorMessage("优惠券不可用");
				}
			}
			// XXX 目前只有抵用券 不判断类型
			if (couponRecord != null) {
				goodsOrder.setCouponRecordId(couponRecordId);
				goodsOrder.setCouponMoney(couponRecord.getDiscount());
			}

			// 计算订单金额
			Long money = goodsOrderGoods.getPriceSales() * goodsNum;
			Long unpayMoney = 0l;
			if (couponRecord != null) {
				money -= couponRecord.getDiscount();
			}
			// 使用优惠券后 若金额小于等于0 金额归0
			if (money <= 0) {
				money = 0l;
			}
			goodsOrder.setMoney(money);
			goodsOrder.setUnpayMoney(unpayMoney);

			// 判断是否自己使用
			if (goodsOverstockUser.getUserId().equals(userId)) {
				goodsOrder.setMoney(0l);
				goodsOrder.setPayMoney(0l);
				goodsOrder.setUnpayMoney(0l);
				goodsOrder.setAccountTime(new Date());
				goodsOrder.setPayTime(new Date());
				goodsOrder.setOverTime(new Date());
				goodsOrder.setStatus(OrderConst.ORDER_STATUS_FINISHED);
				goodsOrder.setPayAccount(user.getMobile());
			}

			// 减少库存
			goodsOverstockUser.setStock(goodsOverstockUser.getStock() - goodsNum);
			goodsOverstockUserMapper.updateByPrimaryKey(goodsOverstockUser);
			goodsOrderMapper.insert(goodsOrder);
			goodsOrderGoods.setOrderId(goodsOrder.getId());
			goodsOrderGoodsMapper.insert(goodsOrderGoods);
			// 压货用户结钱
			if (goodsOverstockUser.getUserId().equals(userId)) {
				userMoneyService.addUserMoney(user.getId(), user.getMoney(), goodsOrderGoods.getRebate() * goodsNum,
						UserConts.USER_MONEY_TYPE_REBATE, goodsOrder.getId());
				user.setMoney(user.getMoney() + goodsOrderGoods.getRebate() * goodsNum);
				userMapper.updateByPrimaryKey(user);
			}
			// 使用优惠券
			if (couponRecord != null) {
				couponRecord.setStatus(CouponConts.COUPON_RECORD_STATUS_USED);
				couponRecord.setOrderId(goodsOrder.getId());
				try {
					couponRecordMapper.updateByPrimaryKey(couponRecord);
				} catch (Exception e) {
					log.error("OrderService addOrder Method error:" + e.getMessage(), e);
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return JsonData.setServerErrorMessage("修改优惠券状态出错");
				}
			}
			// 查询压货商品具体信息
			GoodsOverstockInfoUserExample goodsOverstockInfoUserExample = new GoodsOverstockInfoUserExample();
			goodsOverstockInfoUserExample.createCriteria().andGoodsOverstockUserIdEqualTo(goodsId);
			List<GoodsOverstockInfoUser> goodsOverstockInfoUsers = goodsOverstockInfoUserMapper
					.selectByExample(goodsOverstockInfoUserExample);
			if (goodsOverstockInfoUsers == null || goodsOverstockInfoUsers.size() == 0) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("用户压货商品具体信息不存在");
			}
			for (GoodsOverstockInfoUser goodsOverstockInfoUser : goodsOverstockInfoUsers) {
				for (int i = 0; i < goodsNum; i++) {
					GoodsOrderGoodsInfo goodsOrderGoodsInfo = new GoodsOrderGoodsInfo();
					goodsOrderGoodsInfo.setOrderId(goodsOrder.getId());
					goodsOrderGoodsInfo.setGoodsOrderGoodsId(goodsOrderGoods.getId());
					goodsOrderGoodsInfo.setGoodsOverstockId(goodsOverstockInfoUser.getGoodsOverstockId());
					goodsOrderGoodsInfo.setGoodsOverstockInfoId(goodsOverstockInfoUser.getGoodsOverstockInfoId());
					goodsOrderGoodsInfo.setGoodsOverstockUserId(goodsOverstockInfoUser.getGoodsOverstockUserId());
					goodsOrderGoodsInfo.setGoodsOverstockInfoUserId(goodsOverstockInfoUser.getId());
					goodsOrderGoodsInfo.setGoodsName(goodsOverstockInfoUser.getGoodsName());
					goodsOrderGoodsInfo.setSku(goodsOverstockInfoUser.getSku());
					goodsOrderGoodsInfo.setPricePurchase(goodsOverstockInfoUser.getPricePurchase());
					goodsOrderGoodsInfo.setPriceSales(goodsOverstockInfoUser.getPriceSales());
					goodsOrderGoodsInfo.setType(goodsOverstockInfoUser.getType());
					goodsOrderGoodsInfo.setUseEndTime(goodsOverstockInfoUser.getUseEndTime());
					goodsOrderGoodsInfo.setImg(goodsOverstockInfoUser.getImg());
					goodsOrderGoodsInfo.setAdminId(goodsOverstockInfoUser.getAdminId());
					goodsOrderGoodsInfo.setUserId(userId);
					goodsOrderGoodsInfo.setCreateTime(new Date());
					goodsOrderGoodsInfo.setModifiedTime(new Date());
					if (goodsOrderGoodsInfo.getType().equals(GoodsConst.GOODS_OVERSTOCK_TYPE_CODE)) {
						String code = "";
						int count = 1;
						while (count > 0) {
							code = OrderUtil.getOrderCode();
							GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
							goodsOrderGoodsInfoExample.createCriteria().andCodeEqualTo(code)
									.andStatusEqualTo(OrderConst.ORDER_STATUS_DELIVERED);
							count = goodsOrderGoodsInfoMapper.countByExample(goodsOrderGoodsInfoExample);
						}
						goodsOrderGoodsInfo.setCode(code);
						goodsOrderGoodsInfo.setAddrezz(goodsOverstockInfoUser.getAddrezz());
						if (DateUtils.compareDate(goodsOrderGoodsInfo.getUseEndTime(), new Date())) {
							goodsOrderGoodsInfo.setStatus(OrderConst.ORDER_STATUS_DELIVERED);
						} else {
							goodsOrderGoodsInfo.setStatus(OrderConst.ORDER_STATUS_CLOSED);
						}
					}
					if (goodsOrderGoodsInfo.getType().equals(GoodsConst.GOODS_OVERSTOCK_TYPE_EXPRESS)) {
						goodsOrderGoodsInfo.setStatus(OrderConst.ORDER_STATUS_UNPAY);
						// 是否自己购买
						if (goodsOrder.getUserId().equals(goodsOrder.getSalesUserId())) {
							// 查询上传admin
							Admin admin = adminMapper.selectByPrimaryKey(goodsOrderGoodsInfo.getAdminId());
							if (admin.getType().equals(StatusConst.ADMIN_TYPE_SUPPLIER)) {
								AdminInfoExample adminInfoExample = new AdminInfoExample();
								adminInfoExample.createCriteria().andAdminIdEqualTo(admin.getId());
								AdminInfo adminInfo = adminInfoMapper.selectByExample(adminInfoExample).get(0);
								// 判断贷款是否能还清且有多余
								Long adminMoney = goodsOrderGoodsInfo.getPricePurchase();
								// 贷款已还清
								if ((adminInfo != null && adminInfo.getLoan() == 0) || adminInfo == null) {
									// 更新金额
									adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), adminMoney,
											UserConts.USER_MONEY_TYPE_SALES, goodsOrder.getId());
									admin.setMoney(admin.getMoney() + adminMoney);
									adminMapper.updateByPrimaryKey(admin);
								}
								// 能
								if (adminInfo != null && adminInfo.getLoan() < adminMoney && adminInfo.getLoan() != 0) {
									// 更新金额
									// 还清贷款
									adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(),
											-adminInfo.getLoan(), goodsOrder.getId());
									adminInfo.setLoan(0l);
									adminInfoMapper.updateByPrimaryKey(adminInfo);
									// 剩余部分添加到账户内
									adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(),
											adminMoney - adminInfo.getLoan(), UserConts.USER_MONEY_TYPE_SALES,
											goodsOrder.getId());
									admin.setMoney(admin.getMoney() + adminMoney - adminInfo.getLoan());
									adminMapper.updateByPrimaryKey(admin);
								}
								// 不能 或 刚好还清
								if (adminInfo != null && adminInfo.getLoan() >= adminMoney) {
									// 还贷款
									// 更新金额
									adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -adminMoney,
											goodsOrder.getId());
									adminInfo.setLoan(admin.getMoney() - adminInfo.getLoan());
									adminInfoMapper.updateByPrimaryKey(adminInfo);
								}
							}
							// 更新状态
							goodsOrderGoodsInfo.setStatus(OrderConst.ORDER_STATUS_TRANSFERRED);
						}
					}
					goodsOrderGoodsInfoMapper.insert(goodsOrderGoodsInfo);
				}
			}
			OrderCreate orderCreate = new OrderCreate(goodsOrder);
			return JsonData.setSuccessMessage(orderCreate);
		}

		// 普通商品 or 合卖商品
		// 查询优惠券是否可用
		CouponRecord couponRecord = null;
		if (couponRecordId != null) {
			couponRecord = couponRecordMapper.selectByPrimaryKey(couponRecordId);
			if (couponRecord != null && !couponRecord.getStatus().equals(CouponConts.COUPON_RECORD_STATUS_UNUSED)) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("优惠券不可用");
			}
		}
		// 查询用户信息
		User user = userMapper.selectByPrimaryKey(userId);
		// 生成订单信息
		GoodsOrder goodsOrder = new GoodsOrder();
		goodsOrder.setUserId(userId);
		goodsOrder.setOrderNum(OrderUtil.getOrderNum(user.getType()));
		goodsOrder.setGetWay(getWay);
		goodsOrder.setStatus(OrderConst.ORDER_STATUS_UNPAY);
		goodsOrder.setCreateTime(new Date());
		goodsOrder.setOrderSrc(orderSrc);
		goodsOrder.setSalesUserId(salesUserId);
		goodsOrder.setPayType(payType);
		goodsOrder.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_FALES);
		goodsOrder.setOrderCategory(category);
		if (OrderConst.ORDER_SRC_APP.equals(orderSrc) && UserConts.USER_TYPE_B.equals(user.getType())) {
			goodsOrder.setSalesUserId(userId);
		}
		// 设置地址
		if (GoodsConst.GOODS_GET_WAY_EXPRESS.equals(getWay)) {
			// 查询收货地址
			UserAddress userAddress = userAddressMapper.selectByPrimaryKey(addressId);
			if (userAddress == null) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("收货地址错误");
			}
			goodsOrder.setUserName(userAddress.getName());
			goodsOrder.setMobile(userAddress.getMobile());
			goodsOrder.setAddress(userAddress.getProvince() + userAddress.getCity() + userAddress.getArea()
					+ userAddress.getAddress());
		}
		if (GoodsConst.GOODS_GET_WAY_SELF.equals(getWay)) {
			// 查询自取地址
			GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
			goodsAddressExample.createCriteria().andBizIdEqualTo(goodsId).andCategoryEqualTo(category);
			GoodsAddress goodsAddress = goodsAddressMapper.selectByExample(goodsAddressExample).get(0);
			goodsOrder.setUserName(goodsAddress.getName());
			goodsOrder.setMobile(goodsAddress.getMobile());
			goodsOrder.setAddress(goodsAddress.getProvince() + goodsAddress.getCity() + goodsAddress.getArea()
					+ goodsAddress.getAddress());
			String code = "";
			int count = 1;
			while (count > 0) {
				code = OrderUtil.getOrderCode();
				GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
				goodsOrderExample.createCriteria().andCodeEqualTo(code)
						.andStatusEqualTo(OrderConst.ORDER_STATUS_TRANSFERRED);
				count = goodsOrderMapper.countByExample(goodsOrderExample);
			}
			goodsOrder.setCode(OrderUtil.getOrderCode());
		}
		// XXX 目前只有抵用券 不判断类型
		if (couponRecord != null) {
			goodsOrder.setCouponRecordId(couponRecordId);
			goodsOrder.setCouponMoney(couponRecord.getDiscount());
		}
		// 查询商品信息
		GoodsOrderGoods goodsOrderGoods = new GoodsOrderGoods();
		goodsOrderGoods.setCategory(category);
		goodsOrderGoods.setBizId(goodsId);
		goodsOrderGoods.setNum(goodsNum);
		goodsOrderGoods.setCreateTime(new Date());
		// 普通
		if (OrderConst.ORDER_CATEGORY_NORMAL.equals(category)) {
			GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(goodsId);
			GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(skuId);
			// 判断存库是否够
			if (goodsSku.getStock() < goodsNum) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("库存不足");
			}
			// 判断优惠券可用金额
			if ((couponRecord != null && goods.getDeposit() != null && goods.getDeposit() != 0
					&& couponRecord.getAvailableMoney() > goodsNum * goods.getDeposit())
					|| (couponRecord != null && (goods.getDeposit() == null || goods.getDeposit() == 0)
							&& couponRecord.getAvailableMoney() > goodsNum * goodsSku.getPriceSales())) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("优惠券未达到可用金额");
			}
			goodsOrderGoods.setName(goods.getName());
			goodsOrderGoods.setPriceSales(goodsSku.getPriceSales());
			goodsOrderGoods.setPricePurchase(goodsSku.getPricePurchase());
			goodsOrderGoods.setRebate(goods.getRebate());
			goodsOrderGoods.setReword(goods.getReword());
			goodsOrderGoods.setImgs(goods.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
			goodsOrderGoods.setType(goods.getType());
			goodsOrderGoods.setSkuId(skuId);
			goodsOrderGoods.setSku(goodsSku.getSku());
			goodsOrderGoods.setDeposit(goods.getDeposit());
			goodsOrder.setAdminId(goods.getAdminId());
			// 减少库存 增加销量
			goods.setStock(goods.getStock() - goodsNum);
			goods.setSales(goods.getSales() + goodsNum);
			goodsSku.setStock(goodsSku.getStock() - goodsNum);
			goodsSku.setSales(goodsSku.getSales() + goodsNum);
			try {
				goodsMapper.updateByPrimaryKeyWithBLOBs(goods);
				goodsSkuMapper.updateByPrimaryKey(goodsSku);
			} catch (Exception e) {
				log.error("OrderService addOrder Method error:" + e.getMessage(), e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setServerErrorMessage("普通商品减少库存出错");
			}
		}
		// 认筹
		if (OrderConst.ORDER_CATEGORY_SUBSCRIPTION.equals(category)) {
			GoodsSubscriptionWithBLOBs goodsSubscription = goodsSubscriptionMapper.selectByPrimaryKey(goodsId);
			// 判断认筹商品是否结束销售
			if (!GoodsConst.GOODS_SUBSCRIPTION_STATUS_ON_SALE.equals(goodsSubscription.getSubscriptionStatus())) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("商品不在销售日期内");
			}
			// 判断存库是否够
			if (goodsSubscription.getStock() < goodsNum) {
				jedisClientPool.del(RedisConst.ORDER_COMFIRM + userId + timestamp);
				return JsonData.setErrorMessage("库存不足");
			}
			// 判断优惠券可用金额
			if ((couponRecord != null && goodsSubscription.getDeposit() != null && goodsSubscription.getDeposit() != 0
					&& couponRecord.getAvailableMoney() > goodsNum * goodsSubscription.getDeposit())
					|| (couponRecord != null
							&& (goodsSubscription.getDeposit() == null || goodsSubscription.getDeposit() == 0)
							&& couponRecord.getAvailableMoney() > goodsNum * goodsSubscription.getPriceSales())) {
				return JsonData.setErrorMessage("优惠券未达到可用金额");
			}
			goodsOrderGoods.setName(goodsSubscription.getName());
			goodsOrderGoods.setPriceSales(goodsSubscription.getPriceSales());
			goodsOrderGoods.setPricePurchase(goodsSubscription.getPriceSales());
			goodsOrderGoods.setRebate(goodsSubscription.getBonusAvg());
			goodsOrderGoods.setReword(goodsSubscription.getSalesAward());
			goodsOrderGoods.setImgs(goodsSubscription.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
			goodsOrderGoods.setDeposit(goodsSubscription.getDeposit());
			goodsOrder.setAdminId(goodsSubscription.getAdminId());
			// 减少库存
			goodsSubscription.setStock(goodsSubscription.getStock() - goodsNum);
			try {
				goodsSubscriptionMapper.updateByPrimaryKeyWithBLOBs(goodsSubscription);
			} catch (Exception e) {
				log.error("OrderService addOrder Method error:" + e.getMessage(), e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setServerErrorMessage("认筹商品减少库存出错");
			}
		}

		// 计算订单金额
		Long money = 0l;
		Long unpayMoney = 0l;
		if (goodsOrderGoods.getDeposit() != null && goodsOrderGoods.getDeposit() != 0) {
			money = goodsOrderGoods.getDeposit() * goodsNum;
			unpayMoney = (goodsOrderGoods.getPriceSales() - goodsOrderGoods.getDeposit()) * goodsNum;
		} else {
			money = goodsOrderGoods.getPriceSales() * goodsNum;
		}
		if (couponRecord != null) {
			money -= couponRecord.getDiscount();
		}
		// 使用优惠券后 若金额小于等于0 金额归0
		if (money <= 0) {
			money = 0l;
		}
		goodsOrder.setMoney(money);
		goodsOrder.setUnpayMoney(unpayMoney);
		// 插入订单 及其他相关数据
		try {
			goodsOrderMapper.insert(goodsOrder);
			goodsOrderGoods.setOrderId(goodsOrder.getId());
			goodsOrderGoodsMapper.insert(goodsOrderGoods);
		} catch (Exception e) {
			log.error("OrderService addOrder Method error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("生成订单出错");
		}

		// 使用优惠券
		if (couponRecord != null) {
			couponRecord.setStatus(CouponConts.COUPON_RECORD_STATUS_USED);
			couponRecord.setOrderId(goodsOrder.getId());
			try {
				couponRecordMapper.updateByPrimaryKey(couponRecord);
			} catch (Exception e) {
				log.error("OrderService addOrder Method error:" + e.getMessage(), e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setServerErrorMessage("修改优惠券状态出错");
			}
		}
		// 构造返回值
		OrderCreate orderCreate = new OrderCreate(goodsOrder);
		return JsonData.setSuccessMessage(orderCreate);
	}

	/**
	 * 获取订单支付信息
	 */
	@Override
	public JsonData getOrderPayInfo(Integer userId, Integer orderId, Byte payWay, String ip) {
		// 验证参数
		if (userId == null || orderId == null || payWay == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 更新订单信息
		GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
		if (goodsOrder == null) {
			return JsonData.setErrorMessage("订单不存在");
		}
		if (!OrderConst.ORDER_STATUS_UNPAY.equals(goodsOrder.getStatus())) {
			return JsonData.setErrorMessage("订单已支付");
		}
		String result = null;
		Map<String, String> map = new HashMap<String, String>();
		if (jedisClientPool.hget(RedisConst.ORDER_PAY_INFO + payWay, goodsOrder.getOrderNum()) != null) {
			result = jedisClientPool.hget(RedisConst.ORDER_PAY_INFO + payWay, goodsOrder.getOrderNum());
			map = JsonUtils.jsonToPojo(result, Map.class);
		} else {
			goodsOrder.setPayWay(payWay);
			try {
				goodsOrderMapper.updateByPrimaryKey(goodsOrder);
			} catch (Exception e) {
				log.error("OrderService getOrderPayInfo Method error:" + e.getMessage(), e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setServerErrorMessage("更新订单信息出错");
			}
			// 查询订单商品
			GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
			goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(orderId);
			GoodsOrderGoods goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample).get(0);
			if (OrderConst.ORDER_PAY_WAY_ALIPAY.equals(payWay)) {
				try {
					result = alipayUtils.getAppOrderString(goodsOrder.getOrderNum(), goodsOrderGoods.getName(),
							((double) goodsOrder.getMoney()) / 100 + "");
					map.put("msg", result);
				} catch (AlipayApiException e) {
					log.error("OrderService getOrderPayInfo Method error:" + e.getMessage(), e);
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return JsonData.setServerErrorMessage("获取支付宝支付信息出错");
				}
			}
			if (OrderConst.ORDER_PAY_WAY_WECHAT.equals(payWay)) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					Map<String, String> wxpayResult = wxpayUtils.unifiedorder(goodsOrder.getOrderNum(),
							goodsOrderGoods.getName(), goodsOrder.getMoney() + "", ip, "APP",
							sdf.format(goodsOrder.getCreateTime()), sdf.format(DateUtils
									.getAfterMinDate(goodsOrder.getCreateTime(), OrderConst.ORDER_AUTO_CLOSE_TIME)),
							null);
					if (wxpayResult == null || "FAIL".equals(wxpayResult.get("return_code"))) {
						log.error("OrderService getOrderPayInfo Method error:" + wxpayResult.toString());
						return JsonData.setErrorMessage("获取微信支付信息出错");
					}
					map.put("prepayid", wxpayResult.get("prepay_id"));
					map = wxpayUtils.getH5Sign(map);
				} catch (Exception e) {
					log.error("OrderService getOrderPayInfo Method error:" + e.getMessage(), e);
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return JsonData.setServerErrorMessage("获取微信支付信息出错");
				}
			}
			// 余额
			if (OrderConst.ORDER_PAY_WAY_USER_MONEY.equals(payWay)) {
				if (!goodsOrder.getOrderCategory().equals(OrderConst.ORDER_CATEGORY_OVERSTOCK_PURCHASE)) {
					return JsonData.setErrorMessage("压货商品进货才能使用余额支付");
				}
				// 判断余额 是否够
				User user = userMapper.selectByPrimaryKey(userId);
				if (user.getMoney() < goodsOrder.getMoney()) {
					return JsonData.setErrorMessage("余额不足，请选择其他支付方式");
				}
				// 扣除余额
				userMoneyService.addUserMoney(userId, user.getMoney(), -goodsOrder.getMoney(),
						UserConts.USER_MONEY_TYPE_GOODS_OVERSTOCK, goodsOrder.getId());
				user.setMoney(user.getMoney() - goodsOrder.getMoney());
				userMapper.updateByPrimaryKey(user);
				// 修改订单状态
				goodsOrder.setStatus(OrderConst.ORDER_STATUS_FINISHED);
				goodsOrder.setPayAccount(user.getMobile());
				goodsOrder.setPayMoney(goodsOrder.getMoney());
				goodsOrder.setPayTime(new Date());
				goodsOrder.setAccountTime(new Date());
				goodsOrder.setOverTime(new Date());
				goodsOrder.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_TRUE);
				goodsOrderMapper.updateByPrimaryKey(goodsOrder);
				// 压货商品加入店铺
				GoodsOverstockWithBLOBs goodsOverstock = goodsOverstockMapper
						.selectByPrimaryKey(goodsOrderGoods.getBizId());
				GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
				goodsOrderGoodsInfoExample.createCriteria().andOrderIdEqualTo(orderId);
				List<GoodsOrderGoodsInfo> goodsOrderGoodsInfos = goodsOrderGoodsInfoMapper
						.selectByExample(goodsOrderGoodsInfoExample);
				// 添加用户压货商品
				GoodsOverstockUserWithBLOBs goodsOverstockUser = new GoodsOverstockUserWithBLOBs();
				goodsOverstockUser.setName(goodsOrderGoods.getName());
				goodsOverstockUser.setDetail(goodsOverstock.getDetail());
				goodsOverstockUser.setImgs(goodsOverstock.getImgs());
				goodsOverstockUser.setCreateTime(new Date());
				goodsOverstockUser.setModifiedTime(new Date());
				goodsOverstockUser.setParam(goodsOverstock.getParam());
				goodsOverstockUser.setServiceId(goodsOverstock.getServiceId());
				goodsOverstockUser.setStatus(GoodsConst.GOODS_OVERSTOCK_USER_STATUS_ON_SALE);
				goodsOverstockUser.setStock(goodsOrderGoods.getNum());
				goodsOverstockUser.setRebate(goodsOrderGoods.getRebate());
				goodsOverstockUser.setPrice(goodsOrderGoods.getPriceSales());
				goodsOverstockUser.setStockTotal(goodsOrderGoods.getNum());
				goodsOverstockUser.setGoodsOverstockId(goodsOverstock.getId());
				goodsOverstockUser.setUserId(goodsOrder.getUserId());
				goodsOverstockUserMapper.insert(goodsOverstockUser);
				for (GoodsOrderGoodsInfo goodsOrderGoodsInfo : goodsOrderGoodsInfos) {
					GoodsOverstockInfoUser goodsOverstockInfoUser = new GoodsOverstockInfoUser();
					goodsOverstockInfoUser.setGoodsOverstockId(goodsOrderGoodsInfo.getGoodsOverstockId());
					goodsOverstockInfoUser.setGoodsOverstockInfoId(goodsOrderGoodsInfo.getGoodsOverstockInfoId());
					goodsOverstockInfoUser.setGoodsOverstockUserId(goodsOverstockUser.getId());
					goodsOverstockInfoUser.setGoodsName(goodsOrderGoodsInfo.getGoodsName());
					goodsOverstockInfoUser.setSku(goodsOrderGoodsInfo.getSku());
					goodsOverstockInfoUser.setPricePurchase(goodsOrderGoodsInfo.getPricePurchase());
					goodsOverstockInfoUser.setPriceSales(goodsOrderGoodsInfo.getPriceSales());
					goodsOverstockInfoUser.setType(goodsOrderGoodsInfo.getType());
					goodsOverstockInfoUser.setUseEndTime(goodsOrderGoodsInfo.getUseEndTime());
					goodsOverstockInfoUser.setImg(goodsOrderGoodsInfo.getImg());
					goodsOverstockInfoUser.setAdminId(goodsOrderGoodsInfo.getAdminId());
					goodsOverstockInfoUser.setUserId(goodsOrder.getUserId());
					goodsOverstockInfoUser.setCreateTime(new Date());
					goodsOverstockInfoUser.setModifiedTime(new Date());
					goodsOverstockInfoUser.setAddrezz(goodsOrderGoodsInfo.getAddrezz());
					goodsOverstockInfoUser.setStatus(GoodsConst.GOODS_OVERSTOCK_INFO_STATUS_NORMAL);
					goodsOverstockInfoUserMapper.insert(goodsOverstockInfoUser);
				}
				return JsonData.setSuccessMessage();
			}
			// YsPayOrderBean bean = new YsPayOrderBean();
			// bean.setMethod("ysepay.online.sdkpay");
			// bean.setPartner_id(YspayConfig.PARTNER_ID);
			// bean.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
			// Date()));
			// bean.setCharset(YspayConfig.DEFAULT_CHARSET);
			// bean.setSign_type(YspayConfig.SIGN_ALGORITHM);
			// bean.setVersion(YspayConfig.VERSION);
			// bean.setNotify_url(YspayConfig.NOTIFY_URL);
			// // 测试business_code=01000010 正式=3010002
			// bean.setBiz_content("{\"out_trade_no\":\"" + goodsOrder.getOrderNum() +
			// "\",\"subject\":\""
			// + goodsOrderGoods.getName() + "\",\"total_amount\":\"" + ((double)
			// goodsOrder.getMoney()) / 100
			// + "\",\"seller_id\":\"" + YspayConfig.PARTNER_ID + "\",\"seller_name\":\"" +
			// YspayConfig.SELLER_NAME
			// +
			// "\",\"timeout_express\":\"30m\",\"business_code\":\"3010002\",\"bank_type\":\""
			// + ParamUtils.getSdkBankType(payWay) + "\"}");
			// Map<String, String> param = ApipaySubmit.buildRequestSdk(null, bean);
			// result = Https.httpsSend(YspayConfig.YSEPAY_GATEWAY_URL, param);

			jedisClientPool.hset(RedisConst.ORDER_PAY_INFO + payWay, goodsOrder.getOrderNum(),
					JsonUtils.objectToJson(map));
			// System.out.println(result);
		}
		return JsonData.setSuccessMessage(map);
	}

	/**
	 * 同步支付
	 */
	@Override
	public JsonData updateOrderByPay(Integer userId, Integer orderId, String orderNum) {
		// 验证参数
		if (userId == null || (orderId == null && StringUtils.isBlank(orderNum))
				|| (orderId != null && !StringUtils.isBlank(orderNum))) {
			return JsonData.setErrorMessage("参数非法");
		}
		GoodsOrder goodsOrder = null;
		if (orderId != null) {
			goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
		}
		if (orderNum != null) {
			GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
			goodsOrderExample.createCriteria().andOrderNumEqualTo(orderNum);
			List<GoodsOrder> goodsOrders = goodsOrderMapper.selectByExample(goodsOrderExample);
			if (goodsOrders == null || goodsOrders.size() == 0) {
				goodsOrder = goodsOrders.get(0);
			}
		}
		if (goodsOrder == null) {
			return JsonData.setErrorMessage("订单不存在");
		}
		if (OrderConst.ORDER_STATUS_TRANSFERRED.equals(goodsOrder.getStatus())) {
			return JsonData.setSuccessMessage();
		}
		if (!OrderConst.ORDER_STATUS_UNPAY.equals(goodsOrder.getStatus())) {
			return JsonData.setErrorMessage("订单状态错误！");
		}

		GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
		goodsOrderExample.createCriteria().andIdEqualTo(orderId).andStatusEqualTo(OrderConst.ORDER_STATUS_UNPAY);
		goodsOrder.setStatus(OrderConst.ORDER_STATUS_PAID);
		goodsOrder.setPayTime(new Date());
		if (goodsOrder.getMoney() == 0l) {
			goodsOrder.setStatus(OrderConst.ORDER_STATUS_TRANSFERRED);
			goodsOrder.setAccountTime(new Date());
		}
		// 更新订单状态
		try {
			goodsOrderMapper.updateByExampleSelective(goodsOrder, goodsOrderExample);
		} catch (Exception e) {
			log.error("OrderService getOrderPayInfo Method error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("更新订单信息出错");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 完成订单
	 */
	@Override
	public JsonData updateOrderByFinish(Integer userId, Integer orderId) {
		// 验证参数
		if (userId == null || orderId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询订单
		GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
		if (goodsOrder == null) {
			return JsonData.setErrorMessage("订单不存在");
		}
		if (!OrderConst.ORDER_STATUS_DELIVERED.equals(goodsOrder.getStatus())) {
			return JsonData.setErrorMessage("订单状态错误！不能完成订单");
		}
		// 查询订单商品
		GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
		goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(goodsOrder.getId());
		GoodsOrderGoods goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample).get(0);
		// 修改订单状态
		goodsOrder.setStatus(OrderConst.ORDER_STATUS_FINISHED);
		goodsOrder.setOverTime(new Date());
		// if (GoodsConst.GOODS_CATEGORY_NORMAL.equals(goodsOrderGoods.getCategory())) {
		// goodsOrder.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_TRUE);
		// }
		// 更新订单状态
		try {
			goodsOrderMapper.updateByPrimaryKey(goodsOrder);
		} catch (Exception e) {
			log.error("OrderService getOrderPayInfo Method error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("更新订单信息出错");
		}
		// 普通商品
		if (GoodsConst.GOODS_CATEGORY_NORMAL.equals(goodsOrderGoods.getCategory())) {
			// 返利
			// 查询销售用户
			Integer salesUserId = goodsOrder.getSalesUserId();
			if (salesUserId != null) {
				User salesUser = userMapper.selectByPrimaryKey(salesUserId);
				// 更新金额
				try {
					userMoneyService.addUserMoney(salesUserId, salesUser.getMoney(),
							goodsOrderGoods.getRebate() * goodsOrderGoods.getNum(), UserConts.USER_MONEY_TYPE_REBATE,
							orderId);
					salesUser.setMoney(salesUser.getMoney() + goodsOrderGoods.getRebate() * goodsOrderGoods.getNum());
					userMapper.updateByPrimaryKey(salesUser);
				} catch (Exception e) {
					log.error("OrderService updateOrderByFinish Method error:" + e.getMessage(), e);
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return JsonData.setServerErrorMessage("返利出错");
				}
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
				try {
					userMoneyService.addUserMoney(invitationUser.getId(), invitationUser.getMoney(),
							goodsOrderGoods.getReword() * goodsOrderGoods.getNum(), UserConts.USER_MONEY_TYPE_REWORD,
							orderId);
					invitationUser.setMoney(
							invitationUser.getMoney() + goodsOrderGoods.getReword() * goodsOrderGoods.getNum());
					userMapper.updateByPrimaryKey(invitationUser);
				} catch (Exception e) {
					log.error("OrderService updateOrderByFinish Method error:" + e.getMessage(), e);
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return JsonData.setServerErrorMessage("奖励金出错");
				}
			}
			// // 常规商品
			// if (GoodsConst.GOODS_TYPE_NORMAL.equals(goodsOrderGoods.getType())) {
			// // 查询上传admin
			// Admin admin = adminMapper.selectByPrimaryKey(goodsOrder.getAdminId());
			//
			// // 更新金额
			// try {
			// adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(),
			// goodsOrderGoods.getPricePurchase() * goodsOrderGoods.getNum(),
			// UserConts.USER_MONEY_TYPE_SALES, orderId);
			// admin.setMoney(admin.getMoney() + goodsOrderGoods.getPricePurchase() *
			// goodsOrderGoods.getNum());
			// adminMapper.updateByPrimaryKey(admin);
			// } catch (Exception e) {
			// log.error("OrderService updateOrderByFinish Method error:" + e.getMessage(),
			// e);
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// return JsonData.setServerErrorMessage("货源结算出错");
			// }
			// }
			// // 质押商品
			// if (GoodsConst.GOODS_TYPE_MORTGAGE.equals(goodsOrderGoods.getType())
			// || GoodsConst.GOODS_TYPE_VR_MORTGAGE.equals(goodsOrderGoods.getType())) {
			// // 查询上传admin
			// Admin admin = adminMapper.selectByPrimaryKey(goodsOrder.getAdminId());
			// AdminInfoExample adminInfoExample = new AdminInfoExample();
			// adminInfoExample.createCriteria().andAdminIdEqualTo(admin.getId());
			// AdminInfo adminInfo =
			// adminInfoMapper.selectByExample(adminInfoExample).get(0);
			// // 判断贷款是否能还清且有多余
			// Long money = goodsOrderGoods.getPricePurchase() * goodsOrderGoods.getNum();
			// // 贷款已还清
			// if (adminInfo.getLoan() == 0) {
			// // 更新金额
			// adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), money,
			// UserConts.USER_MONEY_TYPE_SALES, orderId);
			// admin.setMoney(admin.getMoney() + money);
			// adminMapper.updateByPrimaryKey(admin);
			// }
			// // 能
			// if (adminInfo.getLoan() < money) {
			// // 更新金额
			// try {
			// // 还清贷款
			// adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(),
			// -adminInfo.getLoan(),
			// orderId);
			// adminInfo.setLoan(0l);
			// adminInfoMapper.updateByPrimaryKey(adminInfo);
			// // 剩余部分添加到账户内
			// adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), money -
			// adminInfo.getLoan(),
			// UserConts.USER_MONEY_TYPE_SALES, orderId);
			// admin.setMoney(admin.getMoney() + money - adminInfo.getLoan());
			// adminMapper.updateByPrimaryKey(admin);
			// } catch (Exception e) {
			// log.error("OrderService updateOrderByFinish Method error:" + e.getMessage(),
			// e);
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// return JsonData.setServerErrorMessage("货源结算出错");
			// }
			// }
			// // 不能 或者 刚好还清
			// if (adminInfo.getLoan() >= money) {
			// // 更新金额
			// try {
			// // 还贷款
			// adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -money,
			// orderId);
			// adminInfo.setLoan(adminInfo.getLoan() - money);
			// adminInfoMapper.updateByPrimaryKey(adminInfo);
			// } catch (Exception e) {
			// log.error("OrderService updateOrderByFinish Method error:" + e.getMessage(),
			// e);
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// return JsonData.setServerErrorMessage("货源结算出错");
			// }
			// }
			//
			// }
			//
		}
		// 认筹商品
		if (GoodsConst.GOODS_CATEGORY_SUBSCRIPTION.equals(goodsOrderGoods.getCategory())) {
			// 查询认筹记录
			List<GoodsSubscriptionRecord> goodsSubscriptionRecords = goodsSubscriptionRecordMapperCustom
					.selectGroupByUserId(goodsOrderGoods.getBizId());
			try {
				// TODO 认筹商品手动分红
				for (GoodsSubscriptionRecord record : goodsSubscriptionRecords) {

					User user = userMapper.selectByPrimaryKey(record.getUserId());
					// 分红
					// UserMoney userMoney = new UserMoney();
					// userMoney.setUserId(user.getId());
					// userMoney.setMoneyOld(user.getMoney());
					// userMoney.setMoneyNew(user.getMoney() + record.getBonusTotal());
					// userMoney.setMoneyChange(record.getBonusTotal());
					// userMoney.setBizId(orderId);
					// userMoney.setType(UserConts.USER_MONEY_TYPE_BONUS);
					// userMoney.setCreateTime(new Date());
					// user.setMoney(userMoney.getMoneyNew());

					// 删除上架的认筹商品记录
					GoodsUserExample goodsUserExample = new GoodsUserExample();
					goodsUserExample.createCriteria().andUserIdEqualTo(record.getUserId())
							.andBizIdEqualTo(record.getGoodsSubscriptionId())
							.andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
					GoodsUser goodsUser = new GoodsUser();
					goodsUser.setStatus(GoodsConst.GOODS_STATUS_DELETED);
					// 更新数据 // XXX 修改成批量操作

					// 返还信用额度
					// userCreditService.addUserCredit(user.getId(), user.getCreditSurplus(),
					// record.getPriceSubcriptionTotal(), UserConts.USER_CREDIT_TYPE_SALE_RETURN,
					// orderId);
					// user.setCreditSurplus(user.getCreditSurplus() +
					// record.getPriceSubcriptionTotal());
					// // userMoneyMapper.insert(userMoney);
					// userMapper.updateByPrimaryKey(user);

					goodsUserMapper.updateByExampleSelective(goodsUser, goodsUserExample);

					// 添加通知记录
					noticeRecordService.addNoticeRecord(record.getUserId(),
							NoticeConst.NOTICE_ID_SUBCRIPTION_ORDER_FINISH, NoticeConst.NOTICE_RECORD_TYPE_SYSTEM,
							new String[] { goodsOrderGoods.getName(),
									((double) record.getPriceSubcriptionTotal()) / 100 + "",
									((double) goodsOrderGoods.getReword()) / 100 + "" });
				}
				/*
				 * // 店铺销售奖励 Integer salesUserId = goodsOrder.getSalesUserId(); User salesUser =
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
			} catch (Exception e) {
				log.error("OrderService updateOrderByFinish Method error:" + e.getMessage(), e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setServerErrorMessage("认筹商品订单完成出错");
			}
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 认筹
	 */
	@Override
	public JsonData addGoodsSubscriptionRecord(Integer userId, Integer goodsSubscriptionId, Integer num) {
		// 验证参数
		if (userId == null || goodsSubscriptionId == null || num == null || num < 0) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 判断认筹权限
		User user = userMapper.selectByPrimaryKey(userId);
		if (!UserConts.USER_TYPE_B.equals(user.getType())) {
			return JsonData.setErrorMessage("请先申请开店");
		}
		// 查询认筹商品
		GoodsSubscriptionWithBLOBs goodsSubscription = goodsSubscriptionMapper.selectByPrimaryKey(goodsSubscriptionId);
		// 判断商品状态
		if (!GoodsConst.GOODS_SUBSCRIPTION_STATUS_SUBSCRIBING.equals(goodsSubscription.getSubscriptionStatus())) {
			return JsonData.setErrorMessage("认筹已结束");
		}
		// 判断商品是否开始认筹
		if (DateUtils.compareDate(goodsSubscription.getSubscriptionStartTime(), new Date())) {
			return JsonData.setErrorMessage("认筹未开始");
		}
		// 判断认筹数量
		if (goodsSubscription.getNumSubscriptionTotal() - goodsSubscription.getNumSubscription() < num) {
			return JsonData.setErrorMessage("认筹份数超过剩余份数，请重新填写");
		}
		// 判断信用额度
		if (num * goodsSubscription.getPriceSubscriptionAvg() > user.getCreditSurplus()) {
			return JsonData.setErrorMessage("信用额度不足");
		}
		// 认筹 减少商品数量 减少信用额度
		goodsSubscription.setNumSubscription(goodsSubscription.getNumSubscription() + num);

		// 添加认筹记录
		GoodsSubscriptionRecord goodsSubscriptionRecord = new GoodsSubscriptionRecord();
		goodsSubscriptionRecord.setUserId(userId);
		goodsSubscriptionRecord.setGoodsSubscriptionId(goodsSubscriptionId);
		goodsSubscriptionRecord.setSubscriptionNum(num);
		goodsSubscriptionRecord.setCreateTime(new Date());
		goodsSubscriptionRecord.setPriceSubcriptionTotal(goodsSubscription.getPriceSubscriptionAvg() * num);
		goodsSubscriptionRecord.setBonusTotal(goodsSubscription.getBonusAvg() * num);
		try {
			goodsSubscriptionRecordMapper.insert(goodsSubscriptionRecord);
		} catch (Exception e) {
			log.error("OrderService addGoodsSubscriptionRecord Method error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("添加认筹记录出错");
		}
		try {
			// 减少信用额度
			userCreditService.addUserCredit(user.getId(), user.getCreditSurplus(),
					-goodsSubscriptionRecord.getPriceSubcriptionTotal(), UserConts.USER_CREDIT_TYPE_SUBSCRIPTION,
					goodsSubscriptionRecord.getId());
			user.setCreditSurplus(user.getCreditSurplus() - goodsSubscriptionRecord.getPriceSubcriptionTotal());
			userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			log.error("OrderService addGoodsSubscriptionRecord Method error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("减少信用额度出错");
		}
		// 判断认筹数量 若认筹成功 改变状态 商品上架
		if (goodsSubscription.getNumSubscription() >= goodsSubscription.getNumSubscriptionTotal()) {
			goodsSubscription.setSubscriptionStatus(GoodsConst.GOODS_SUBSCRIPTION_STATUS_ON_SALE);
			// 查询认筹记录
			List<GoodsSubscriptionRecord> records = goodsSubscriptionRecordMapperCustom
					.selectGroupByUserId(goodsSubscriptionId);
			try {
				for (GoodsSubscriptionRecord record : records) {
					// XXX 不确定事务提交之前 能否查到刚添加的认筹记录
					GoodsUser goodsUser = new GoodsUser();
					goodsUser.setUserId(record.getUserId());
					goodsUser.setItemId(goodsSubscription.getItemId());
					goodsUser.setCategory(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION);
					goodsUser.setBizId(goodsSubscriptionId);
					goodsUser.setStatus(GoodsConst.GOODS_STATUS_ON_SALE);
					goodsUserMapper.insert(goodsUser);
					// 添加消息记录
					noticeRecordService.addNoticeRecord(record.getUserId(),
							NoticeConst.NOTICE_ID_GOODS_SUBCRIBE_SUCCESS, NoticeConst.NOTICE_RECORD_TYPE_SYSTEM,
							new String[] { goodsSubscription.getName(),
									new SimpleDateFormat("yyyy-MM-dd").format(goodsSubscription.getSalesEndTime()) });
				}
			} catch (Exception e) {
				log.error("OrderService addGoodsSubscriptionRecord Method error:" + e.getMessage(), e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setServerErrorMessage("认筹商品上架出错");
			}
		}
		// 修改认筹商品信息
		try {
			goodsSubscriptionMapper.updateByPrimaryKeyWithBLOBs(goodsSubscription);
		} catch (Exception e) {
			log.error("OrderService addGoodsSubscriptionRecord Method error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("修改认筹商品信息出错");
		}
		// XXX 是否需要站内消息

		return JsonData.setSuccessMessage();
	}

	/**
	 * 支付宝异步通知
	 */
	@Override
	public boolean updateOrderByAlipayNotice(Map<String, String> param) throws AlipayApiException {
		// System.out.println("start");
		boolean tag = false;
		tag = AlipayUtils.validateAlipayInfo(param);
		if (!tag) {
			// System.out.println("fail");
			return false;
		}
		// 拼接支付宝异步通知字符串 并存文件
		String alipayNoticeString = "";
		for (String key : param.keySet()) {
			alipayNoticeString += key + "=" + param.get(key) + "&";
		}
		// TODO 存文件
		// 获取参数
		String tradeStatus = param.get("trade_status");
		String orderNum = param.get("out_trade_no");
		String payAccount = param.get("buyer_logon_id");
		String money = param.get("total_amount");
		String payMoney = param.get("receipt_amount");
		log.info("alipay notie orderNum error-orderNum=" + orderNum + ":" + alipayNoticeString);
		if ("TRADE_FINISHED".equals(tradeStatus)) {
			// System.out.println("TRADE_FINISHED");
			return true;
		}
		if ("TRADE_SUCCESS".equals(tradeStatus)) {
			// System.out.println("TRADE_SUCCESS");
			// TODO 处理支付宝业务
			// 根据订单id 查询订单
			GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
			goodsOrderExample.createCriteria().andOrderNumEqualTo(orderNum);
			List<GoodsOrder> goodsOrders = goodsOrderMapper.selectByExample(goodsOrderExample);
			if (goodsOrders == null || goodsOrders.size() != 1) {
				// System.out.println("orderNum error");
				log.error("alipay notie orderNum error-orderNum=" + orderNum + ":" + alipayNoticeString);
				return false;
			}
			// 判断数据是否正确
			GoodsOrder goodsOrder = goodsOrders.get(0);
			if (((double) goodsOrder.getMoney()) / 100 != Double.parseDouble(money)) {
				// System.out.println("money error");
				log.error("alipay notie money error-orderNum=" + goodsOrder.getOrderNum() + ":" + alipayNoticeString);
				return false;
			}
			// 修改订单业务数据
			goodsOrder.setPayMoney(new Double(Double.parseDouble(payMoney) * 100).longValue());
			goodsOrder.setStatus(OrderConst.ORDER_STATUS_TRANSFERRED);
			goodsOrder.setPayAccount(payAccount);
			goodsOrder.setAccountTime(new Date());
			if (goodsOrder.getPayTime() == null) {
				goodsOrder.setPayTime(new Date());
			}
			// 压货购买
			if (OrderConst.ORDER_CATEGORY_OVERSTOCK_SALES.equals(goodsOrder.getOrderCategory())) {
				goodsOrder.setStatus(OrderConst.ORDER_STATUS_FINISHED);
				goodsOrder.setOverTime(new Date());
				// 修改状态
				GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
				goodsOrderGoodsInfoExample.createCriteria().andOrderIdEqualTo(goodsOrder.getId())
						.andTypeEqualTo(GoodsConst.GOODS_OVERSTOCK_TYPE_EXPRESS);
				GoodsOrderGoodsInfo goodsOrderGoodsInfo = new GoodsOrderGoodsInfo();
				goodsOrderGoodsInfo.setStatus(OrderConst.ORDER_STATUS_TRANSFERRED);
				goodsOrderGoodsInfo.setModifiedTime(new Date());
				goodsOrderGoodsInfoMapper.updateByExampleSelective(goodsOrderGoodsInfo, goodsOrderGoodsInfoExample);
				// 压货用户结钱
				GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
				goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(goodsOrder.getId());
				GoodsOrderGoods goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample).get(0);
				User user = userMapper.selectByPrimaryKey(goodsOrder.getSalesUserId());
				// 非自己购买使用
				if (!goodsOrder.getSalesUserId().equals(goodsOrder.getUserId())) {
					userMoneyService.addUserMoney(user.getId(), user.getMoney(), goodsOrder.getMoney(),
							UserConts.USER_MONEY_TYPE_SALES, goodsOrder.getId());
					user.setMoney(user.getMoney() + goodsOrder.getMoney());
				}
				userMoneyService.addUserMoney(user.getId(), user.getMoney(),
						goodsOrderGoods.getRebate() * goodsOrderGoods.getNum(), UserConts.USER_MONEY_TYPE_REBATE,
						goodsOrder.getId());
				user.setMoney(user.getMoney() + goodsOrderGoods.getRebate() * goodsOrderGoods.getNum());
				userMapper.updateByPrimaryKey(user);
			}
			// 压货备货
			if (OrderConst.ORDER_CATEGORY_OVERSTOCK_PURCHASE.equals(goodsOrder.getOrderCategory())) {
				goodsOrder.setStatus(OrderConst.ORDER_STATUS_FINISHED);
				goodsOrder.setOverTime(new Date());
				GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
				goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(goodsOrder.getId());
				GoodsOrderGoods goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample).get(0);
				// 压货商品加入店铺
				GoodsOverstockWithBLOBs goodsOverstock = goodsOverstockMapper
						.selectByPrimaryKey(goodsOrderGoods.getBizId());
				GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
				goodsOrderGoodsInfoExample.createCriteria().andOrderIdEqualTo(goodsOrder.getId());
				List<GoodsOrderGoodsInfo> goodsOrderGoodsInfos = goodsOrderGoodsInfoMapper
						.selectByExample(goodsOrderGoodsInfoExample);
				// 添加用户压货商品
				GoodsOverstockUserWithBLOBs goodsOverstockUser = new GoodsOverstockUserWithBLOBs();
				goodsOverstockUser.setName(goodsOrderGoods.getName());
				goodsOverstockUser.setDetail(goodsOverstock.getDetail());
				goodsOverstockUser.setImgs(goodsOverstock.getImgs());
				goodsOverstockUser.setCreateTime(new Date());
				goodsOverstockUser.setModifiedTime(new Date());
				goodsOverstockUser.setParam(goodsOverstock.getParam());
				goodsOverstockUser.setServiceId(goodsOverstock.getServiceId());
				goodsOverstockUser.setStatus(GoodsConst.GOODS_OVERSTOCK_USER_STATUS_ON_SALE);
				goodsOverstockUser.setStock(goodsOrderGoods.getNum());
				goodsOverstockUser.setRebate(goodsOrderGoods.getRebate());
				goodsOverstockUser.setPrice(goodsOrderGoods.getPriceSales());
				goodsOverstockUser.setStockTotal(goodsOrderGoods.getNum());
				goodsOverstockUser.setGoodsOverstockId(goodsOverstock.getId());
				goodsOverstockUser.setUserId(goodsOrder.getUserId());
				goodsOverstockUserMapper.insert(goodsOverstockUser);
				for (GoodsOrderGoodsInfo goodsOrderGoodsInfo : goodsOrderGoodsInfos) {
					GoodsOverstockInfoUser goodsOverstockInfoUser = new GoodsOverstockInfoUser();
					goodsOverstockInfoUser.setGoodsOverstockId(goodsOrderGoodsInfo.getGoodsOverstockId());
					goodsOverstockInfoUser.setGoodsOverstockInfoId(goodsOrderGoodsInfo.getGoodsOverstockInfoId());
					goodsOverstockInfoUser.setGoodsOverstockUserId(goodsOverstockUser.getId());
					goodsOverstockInfoUser.setGoodsName(goodsOrderGoodsInfo.getGoodsName());
					goodsOverstockInfoUser.setSku(goodsOrderGoodsInfo.getSku());
					goodsOverstockInfoUser.setPricePurchase(goodsOrderGoodsInfo.getPricePurchase());
					goodsOverstockInfoUser.setPriceSales(goodsOrderGoodsInfo.getPriceSales());
					goodsOverstockInfoUser.setType(goodsOrderGoodsInfo.getType());
					goodsOverstockInfoUser.setUseEndTime(goodsOrderGoodsInfo.getUseEndTime());
					goodsOverstockInfoUser.setImg(goodsOrderGoodsInfo.getImg());
					goodsOverstockInfoUser.setAdminId(goodsOrderGoodsInfo.getAdminId());
					goodsOverstockInfoUser.setUserId(goodsOrder.getUserId());
					goodsOverstockInfoUser.setCreateTime(new Date());
					goodsOverstockInfoUser.setModifiedTime(new Date());
					goodsOverstockInfoUser.setAddrezz(goodsOrderGoodsInfo.getAddrezz());
					goodsOverstockInfoUser.setStatus(GoodsConst.GOODS_OVERSTOCK_INFO_STATUS_NORMAL);
					goodsOverstockInfoUserMapper.insert(goodsOverstockInfoUser);
				}
			}

			// 更新订单
			goodsOrderMapper.updateByPrimaryKey(goodsOrder);
			// System.out.println("update success");
			return true;
		}
		// System.out.println("status error");
		return false;
	}

	/**
	 * 手机网站支付
	 */
	@Override
	public JsonData getOrderWapPayString(Integer userId, Integer orderId, Byte payWay, String ip, String ua,
			String openId) {
		// 验证参数
		if (userId == null || orderId == null || payWay == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询订单
		GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
		if (goodsOrder == null) {
			return JsonData.setErrorMessage("订单不存在");
		}
		if (!OrderConst.ORDER_STATUS_UNPAY.equals(goodsOrder.getStatus())) {
			return JsonData.setErrorMessage("订单状态错误，请刷新重试");
		}
		goodsOrder.setPayWay(payWay);
		try {
			goodsOrderMapper.updateByPrimaryKey(goodsOrder);
		} catch (Exception e) {
			log.error("OrderService getOrderWapPayString Method error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("更新订单信息出错");
		}
		// 查询订单商品
		GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
		goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(goodsOrder.getId());
		GoodsOrderGoods goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample).get(0);
		// 银盛支付
		// YsPayOrderBean bean = new YsPayOrderBean();
		// if (OrderConst.ORDER_PAY_WAY_ALIPAY.equals(payWay)) {
		// bean.setMethod("ysepay.online.wap.directpay.createbyuser");
		// bean.setPartner_id(YspayConfig.PARTNER_ID);
		// bean.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
		// Date()));
		// bean.setCharset(YspayConfig.DEFAULT_CHARSET);
		// bean.setSign_type(YspayConfig.SIGN_ALGORITHM);
		// bean.setNotify_url(YspayConfig.NOTIFY_URL);
		// bean.setReturn_url(YspayConfig.RETURN_URL);
		// bean.setVersion(YspayConfig.VERSION);
		// bean.setOut_trade_no(goodsOrder.getOrderNum());
		// bean.setSubject(goodsOrderGoods.getName());
		// bean.setTotal_amount(((double) goodsOrder.getMoney()) / 100);
		// bean.setSeller_id(YspayConfig.PARTNER_ID);
		// bean.setSeller_name(YspayConfig.SELLER_NAME);
		// bean.setTimeout_express("30m");
		// // 测试
		// // bean.setBusiness_code("01000010");
		// // 正式
		// bean.setBusiness_code("3010002");
		// bean.setBank_type(ParamUtils.getSdkBankType(payWay));
		// bean.setPay_mode("native");
		// }
		// if (OrderConst.ORDER_PAY_WAY_WECHAT.equals(payWay)) {
		// bean.setMethod("ysepay.online.weChat.app.pay");
		// bean.setPartner_id(YspayConfig.PARTNER_ID);
		// bean.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
		// Date()));
		// bean.setCharset(YspayConfig.DEFAULT_CHARSET);
		// bean.setSign_type(YspayConfig.SIGN_ALGORITHM);
		// bean.setNotify_url(YspayConfig.NOTIFY_URL);
		// bean.setReturn_url(YspayConfig.RETURN_URL);
		// bean.setVersion(YspayConfig.VERSION);
		// // 测试business_code=01000010 正式=3010002
		// bean.setBiz_content("{\"out_trade_no\":\"" + goodsOrder.getOrderNum() +
		// "\",\"subject\":\""
		// + goodsOrderGoods.getName() + "\",\"total_amount\":\"" + ((double)
		// goodsOrder.getMoney()) / 100
		// + "\",\"seller_id\":\"" + YspayConfig.PARTNER_ID + "\",\"seller_name\":\"" +
		// YspayConfig.SELLER_NAME
		// +
		// "\",\"timeout_express\":\"30m\",\"business_code\":\"3010002\",\"open_id\":\"ZS\"}");
		// }
		// String result = ApipaySubmit.buildRequest(null, bean);

		// 支付宝手机支付
		String result = "";
		Map<String, String> map = new HashMap<String, String>();
		if (OrderConst.ORDER_PAY_WAY_ALIPAY.equals(payWay)) {
			try {
				result = alipayUtils.getWapOrderString(goodsOrder.getOrderNum(), goodsOrderGoods.getName(),
						((double) goodsOrder.getMoney()) / 100 + "");
			} catch (AlipayApiException e) {
				log.error("OrderService getOrderWapPayString Method error:" + e.getMessage(), e);
				return JsonData.setServerErrorMessage("获取订单支付信息出错");
			}
		}
		if (OrderConst.ORDER_PAY_WAY_WECHAT.equals(payWay)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			// 判断请求是否来自微信 是 微信公众号支付 否 微信h5支付
			try {
				if (ua.indexOf("micromessenger") > 0) {
					Map<String, String> wxpayResult = wxpayUtils.unifiedorder(goodsOrder.getOrderNum(),
							goodsOrderGoods.getName(), goodsOrder.getMoney() + "", ip, "JSAPI",
							sdf.format(goodsOrder.getCreateTime()), sdf.format(DateUtils
									.getAfterMinDate(goodsOrder.getCreateTime(), OrderConst.ORDER_AUTO_CLOSE_TIME)),
							openId);
					if (wxpayResult == null || "FAIL".equals(wxpayResult.get("return_code"))) {
						log.error("OrderService getOrderPayInfo Method error:" + wxpayResult.toString());
						return JsonData.setErrorMessage("获取微信支付信息出错");
					}
					map.put("package", "prepay_id=" + wxpayResult.get("prepay_id"));
					wxpayUtils.getWechatSign(map);
				} else {
					Map<String, String> wxpayResult = wxpayUtils.unifiedorder(goodsOrder.getOrderNum(),
							goodsOrderGoods.getName(), goodsOrder.getMoney() + "", ip, "MWEB",
							sdf.format(goodsOrder.getCreateTime()), sdf.format(DateUtils
									.getAfterMinDate(goodsOrder.getCreateTime(), OrderConst.ORDER_AUTO_CLOSE_TIME)),
							null);
					if (wxpayResult == null || "FAIL".equals(wxpayResult.get("return_code"))) {
						log.error("OrderService getOrderPayInfo Method error:" + wxpayResult.toString());
						return JsonData.setErrorMessage("获取微信支付信息出错");
					}
					result = wxpayUtils.getWapPayStringAddRedirectUrl(wxpayResult.get("mweb_url"));
				}
			} catch (Exception e) {
				log.error("OrderService getOrderWapPayString Method error:" + e.getMessage(), e);
				return JsonData.setServerErrorMessage("获取订单支付信息出错");
			}
		}
		if (OrderConst.ORDER_PAY_WAY_USER_MONEY.equals(payWay)) {
			if (!goodsOrder.getOrderCategory().equals(OrderConst.ORDER_CATEGORY_OVERSTOCK_PURCHASE)) {
				return JsonData.setErrorMessage("压货商品进货才能使用余额支付");
			}
			// 判断余额 是否够
			User user = userMapper.selectByPrimaryKey(userId);
			if (user.getMoney() < goodsOrder.getMoney()) {
				return JsonData.setErrorMessage("余额不足，请选择其他支付方式");
			}
			// 扣除余额
			userMoneyService.addUserMoney(userId, user.getMoney(), -goodsOrder.getMoney(),
					UserConts.USER_MONEY_TYPE_GOODS_OVERSTOCK, goodsOrder.getId());
			user.setMoney(user.getMoney() - goodsOrder.getMoney());
			userMapper.updateByPrimaryKey(user);
			// 修改订单状态
			goodsOrder.setStatus(OrderConst.ORDER_STATUS_FINISHED);
			goodsOrder.setPayAccount(user.getMobile());
			goodsOrder.setPayMoney(goodsOrder.getMoney());
			goodsOrder.setPayTime(new Date());
			goodsOrder.setAccountTime(new Date());
			goodsOrder.setOverTime(new Date());
			goodsOrder.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_TRUE);
			goodsOrderMapper.updateByPrimaryKey(goodsOrder);
			// 压货商品加入店铺
			GoodsOverstockWithBLOBs goodsOverstock = goodsOverstockMapper
					.selectByPrimaryKey(goodsOrderGoods.getBizId());
			GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
			goodsOrderGoodsInfoExample.createCriteria().andOrderIdEqualTo(orderId);
			List<GoodsOrderGoodsInfo> goodsOrderGoodsInfos = goodsOrderGoodsInfoMapper
					.selectByExample(goodsOrderGoodsInfoExample);
			// 添加用户压货商品
			GoodsOverstockUserWithBLOBs goodsOverstockUser = new GoodsOverstockUserWithBLOBs();
			goodsOverstockUser.setName(goodsOrderGoods.getName());
			goodsOverstockUser.setDetail(goodsOverstock.getDetail());
			goodsOverstockUser.setImgs(goodsOverstock.getImgs());
			goodsOverstockUser.setCreateTime(new Date());
			goodsOverstockUser.setModifiedTime(new Date());
			goodsOverstockUser.setParam(goodsOverstock.getParam());
			goodsOverstockUser.setServiceId(goodsOverstock.getServiceId());
			goodsOverstockUser.setStatus(GoodsConst.GOODS_OVERSTOCK_USER_STATUS_ON_SALE);
			goodsOverstockUser.setStock(goodsOrderGoods.getNum());
			goodsOverstockUser.setRebate(goodsOrderGoods.getRebate());
			goodsOverstockUser.setPrice(goodsOrderGoods.getPriceSales());
			goodsOverstockUser.setStockTotal(goodsOrderGoods.getNum());
			goodsOverstockUser.setUserId(goodsOrder.getUserId());
			goodsOverstockUser.setGoodsOverstockId(goodsOverstock.getId());
			goodsOverstockUserMapper.insert(goodsOverstockUser);
			for (GoodsOrderGoodsInfo goodsOrderGoodsInfo : goodsOrderGoodsInfos) {
				GoodsOverstockInfoUser goodsOverstockInfoUser = new GoodsOverstockInfoUser();
				goodsOverstockInfoUser.setGoodsOverstockId(goodsOrderGoodsInfo.getGoodsOverstockId());
				goodsOverstockInfoUser.setGoodsOverstockInfoId(goodsOrderGoodsInfo.getGoodsOverstockInfoId());
				goodsOverstockInfoUser.setGoodsOverstockUserId(goodsOverstockUser.getId());
				goodsOverstockInfoUser.setGoodsName(goodsOrderGoodsInfo.getGoodsName());
				goodsOverstockInfoUser.setSku(goodsOrderGoodsInfo.getSku());
				goodsOverstockInfoUser.setPricePurchase(goodsOrderGoodsInfo.getPricePurchase());
				goodsOverstockInfoUser.setPriceSales(goodsOrderGoodsInfo.getPriceSales());
				goodsOverstockInfoUser.setType(goodsOrderGoodsInfo.getType());
				goodsOverstockInfoUser.setUseEndTime(goodsOrderGoodsInfo.getUseEndTime());
				goodsOverstockInfoUser.setImg(goodsOrderGoodsInfo.getImg());
				goodsOverstockInfoUser.setAdminId(goodsOrderGoodsInfo.getAdminId());
				goodsOverstockInfoUser.setUserId(goodsOrder.getUserId());
				goodsOverstockInfoUser.setCreateTime(new Date());
				goodsOverstockInfoUser.setModifiedTime(new Date());
				goodsOverstockInfoUser.setAddrezz(goodsOrderGoodsInfo.getAddrezz());
				goodsOverstockInfoUser.setStatus(GoodsConst.GOODS_OVERSTOCK_INFO_STATUS_NORMAL);
				goodsOverstockInfoUserMapper.insert(goodsOverstockInfoUser);
			}
			return JsonData.setSuccessMessage();
		}
		if (!"".equals(result)) {
			map.put("msg", result);
		}
		return JsonData.setSuccessMessage(map);
	}

	/**
	 * 银盛支付异步通知
	 */
	@Override
	public boolean updateOrderByYspayNotice(Map<String, String> param) {
		boolean verifyResult = ApipaySubmit.verifySign(param);
		// 状态
		String trade_status = param.get("trade_status");
		if (verifyResult) {// 验证成功
			if (trade_status.equals("TRADE_FINISHED")) {
				return true;
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 拼接银盛异步通知字符串 并存文件
				String ysPayNoticeString = "";
				for (String key : param.keySet()) {
					ysPayNoticeString += key + "=" + param.get(key) + "&";
				}
				String orderNum = param.get("out_trade_no");
				String money = param.get("total_amount");

				// 根据订单id 查询订单
				GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
				goodsOrderExample.createCriteria().andOrderNumEqualTo(orderNum);
				List<GoodsOrder> goodsOrders = goodsOrderMapper.selectByExample(goodsOrderExample);
				if (goodsOrders == null || goodsOrders.size() != 1) {
					// System.out.println("orderNum error");
					log.error("alipay notie orderNum error-orderNum=" + orderNum + ":" + ysPayNoticeString);
					return false;
				}
				// 判断数据是否正确
				GoodsOrder goodsOrder = goodsOrders.get(0);
				if (((double) goodsOrder.getMoney()) / 100 != Double.parseDouble(money)) {
					// System.out.println("money error");
					log.error(
							"alipay notie money error-orderNum=" + goodsOrder.getOrderNum() + ":" + ysPayNoticeString);
					return false;
				}
				// 修改订单业务数据
				goodsOrder.setPayMoney(new Double(Double.parseDouble(money) * 100).longValue());
				goodsOrder.setStatus(OrderConst.ORDER_STATUS_TRANSFERRED);
				goodsOrder.setPayAccount("yspay");
				goodsOrder.setAccountTime(new Date());
				if (goodsOrder.getPayTime() == null) {
					goodsOrder.setPayTime(new Date());
				}
				// 更新订单
				goodsOrderMapper.updateByPrimaryKey(goodsOrder);
				return true;
			}
		}
		return false;
	}

	/**
	 * 微信异步通知
	 */
	@Override
	public String updateOrderByWxpayNotice(String notifyData) throws Exception {

		MyWxpayConfig config = new MyWxpayConfig(wxpayUtils.getCertPath());
		WXPay wxpay = new WXPay(config);

		Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData); // 转换成map

		String wxpayNoticeString = "";
		for (String key : notifyMap.keySet()) {
			wxpayNoticeString += key + "=" + notifyMap.get(key) + "&";
		}
		log.info("wxpay notice :" + wxpayNoticeString);

		if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
			if ("SUCCESS".equals(notifyMap.get("return_code"))) {
				String orderNum = notifyMap.get("out_trade_no");
				String payAccount = notifyMap.get("openid");
				String money = notifyMap.get("total_fee");
				String payMoney = notifyMap.get("cash_fee");
				// 签名正确
				// 进行处理。
				// 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
				GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
				goodsOrderExample.createCriteria().andOrderNumEqualTo(orderNum);
				List<GoodsOrder> goodsOrders = goodsOrderMapper.selectByExample(goodsOrderExample);
				if (goodsOrders == null || goodsOrders.size() != 1) {
					// System.out.println("orderNum error");
					log.error("wxpay notice orderNum error-orderNum=" + orderNum + ":" + wxpayNoticeString);
					return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[out_trade_no error]]></return_msg></xml>";
				}
				// 判断数据是否正确
				GoodsOrder goodsOrder = goodsOrders.get(0);
				if (goodsOrder.getMoney() != Double.parseDouble(money)) {
					// System.out.println("money error");
					log.error(
							"wxpay notice money error-orderNum=" + goodsOrder.getOrderNum() + ":" + wxpayNoticeString);
					return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[total_fee error]]></return_msg></xml>";
				}
				// 修改订单业务数据
				goodsOrder.setPayMoney(new Double(Double.parseDouble(payMoney) * 100).longValue());
				goodsOrder.setStatus(OrderConst.ORDER_STATUS_TRANSFERRED);
				goodsOrder.setPayAccount(payAccount);
				goodsOrder.setAccountTime(new Date());
				if (goodsOrder.getPayTime() == null) {
					goodsOrder.setPayTime(new Date());
				}
				// 压货购买
				if (OrderConst.ORDER_CATEGORY_OVERSTOCK_SALES.equals(goodsOrder.getOrderCategory())) {
					goodsOrder.setStatus(OrderConst.ORDER_STATUS_FINISHED);
					goodsOrder.setOverTime(new Date());
					// 修改状态
					GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
					goodsOrderGoodsInfoExample.createCriteria().andOrderIdEqualTo(goodsOrder.getId())
							.andTypeEqualTo(GoodsConst.GOODS_OVERSTOCK_TYPE_EXPRESS);
					GoodsOrderGoodsInfo goodsOrderGoodsInfo = new GoodsOrderGoodsInfo();
					goodsOrderGoodsInfo.setStatus(OrderConst.ORDER_STATUS_TRANSFERRED);
					goodsOrderGoodsInfo.setModifiedTime(new Date());
					goodsOrderGoodsInfoMapper.updateByExampleSelective(goodsOrderGoodsInfo, goodsOrderGoodsInfoExample);
					// 压货用户结钱
					GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
					goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(goodsOrder.getId());
					GoodsOrderGoods goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample)
							.get(0);
					User user = userMapper.selectByPrimaryKey(goodsOrder.getSalesUserId());
					// 非自己是购买使用
					if (!goodsOrder.getSalesUserId().equals(goodsOrder.getUserId())) {
						userMoneyService.addUserMoney(user.getId(), user.getMoney(), goodsOrder.getMoney(),
								UserConts.USER_MONEY_TYPE_SALES, goodsOrder.getId());
						user.setMoney(user.getMoney() + goodsOrder.getMoney());
					}
					userMoneyService.addUserMoney(user.getId(), user.getMoney(),
							goodsOrderGoods.getRebate() * goodsOrderGoods.getNum(), UserConts.USER_MONEY_TYPE_REBATE,
							goodsOrder.getId());
					user.setMoney(user.getMoney() + goodsOrderGoods.getRebate() * goodsOrderGoods.getNum());
					userMapper.updateByPrimaryKey(user);
				}
				// 压货备货
				if (OrderConst.ORDER_CATEGORY_OVERSTOCK_PURCHASE.equals(goodsOrder.getOrderCategory())) {
					goodsOrder.setStatus(OrderConst.ORDER_STATUS_FINISHED);
					goodsOrder.setOverTime(new Date());
					GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
					goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(goodsOrder.getId());
					GoodsOrderGoods goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample)
							.get(0);
					// 压货商品加入店铺
					GoodsOverstockWithBLOBs goodsOverstock = goodsOverstockMapper
							.selectByPrimaryKey(goodsOrderGoods.getBizId());
					GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
					goodsOrderGoodsInfoExample.createCriteria().andOrderIdEqualTo(goodsOrder.getId());
					List<GoodsOrderGoodsInfo> goodsOrderGoodsInfos = goodsOrderGoodsInfoMapper
							.selectByExample(goodsOrderGoodsInfoExample);
					// 添加用户压货商品
					GoodsOverstockUserWithBLOBs goodsOverstockUser = new GoodsOverstockUserWithBLOBs();
					goodsOverstockUser.setName(goodsOrderGoods.getName());
					goodsOverstockUser.setDetail(goodsOverstock.getDetail());
					goodsOverstockUser.setImgs(goodsOverstock.getImgs());
					goodsOverstockUser.setCreateTime(new Date());
					goodsOverstockUser.setModifiedTime(new Date());
					goodsOverstockUser.setParam(goodsOverstock.getParam());
					goodsOverstockUser.setServiceId(goodsOverstock.getServiceId());
					goodsOverstockUser.setStatus(GoodsConst.GOODS_OVERSTOCK_USER_STATUS_ON_SALE);
					goodsOverstockUser.setStock(goodsOrderGoods.getNum());
					goodsOverstockUser.setRebate(goodsOrderGoods.getRebate());
					goodsOverstockUser.setPrice(goodsOrderGoods.getPriceSales());
					goodsOverstockUser.setStockTotal(goodsOrderGoods.getNum());
					goodsOverstockUser.setUserId(goodsOrder.getUserId());
					goodsOverstockUser.setGoodsOverstockId(goodsOverstock.getId());
					goodsOverstockUser.setUserId(goodsOrder.getUserId());
					goodsOverstockUserMapper.insert(goodsOverstockUser);
					for (GoodsOrderGoodsInfo goodsOrderGoodsInfo : goodsOrderGoodsInfos) {
						GoodsOverstockInfoUser goodsOverstockInfoUser = new GoodsOverstockInfoUser();
						goodsOverstockInfoUser.setGoodsOverstockId(goodsOrderGoodsInfo.getGoodsOverstockId());
						goodsOverstockInfoUser.setGoodsOverstockInfoId(goodsOrderGoodsInfo.getGoodsOverstockInfoId());
						goodsOverstockInfoUser.setGoodsOverstockUserId(goodsOverstockUser.getId());
						goodsOverstockInfoUser.setGoodsName(goodsOrderGoodsInfo.getGoodsName());
						goodsOverstockInfoUser.setSku(goodsOrderGoodsInfo.getSku());
						goodsOverstockInfoUser.setPricePurchase(goodsOrderGoodsInfo.getPricePurchase());
						goodsOverstockInfoUser.setPriceSales(goodsOrderGoodsInfo.getPriceSales());
						goodsOverstockInfoUser.setType(goodsOrderGoodsInfo.getType());
						goodsOverstockInfoUser.setUseEndTime(goodsOrderGoodsInfo.getUseEndTime());
						goodsOverstockInfoUser.setImg(goodsOrderGoodsInfo.getImg());
						goodsOverstockInfoUser.setAdminId(goodsOrderGoodsInfo.getAdminId());
						goodsOverstockInfoUser.setUserId(goodsOrder.getUserId());
						goodsOverstockInfoUser.setCreateTime(new Date());
						goodsOverstockInfoUser.setModifiedTime(new Date());
						goodsOverstockInfoUser.setAddrezz(goodsOrderGoodsInfo.getAddrezz());
						goodsOverstockInfoUserMapper.insert(goodsOverstockInfoUser);
					}
				}
				// 更新订单
				goodsOrderMapper.updateByPrimaryKey(goodsOrder);
				return "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";
			} else {
				log.error("wxpayNotice error:{return_code=" + notifyMap.get("return_code") + "&return_msg="
						+ notifyMap.get("return_msg") + "}");
				return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[return code error]]></return_msg></xml>";
			}
		} else {
			// 签名错误，如果数据里没有sign字段，也认为是签名错误
			return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[sign error]]></return_msg></xml>";
		}
	}

	/**
	 * 获取用户已够买的核销商品列表
	 */
	@Override
	public JsonData getGoodsOrderGoodsInfoList(Integer pages, Integer rows, Integer userId) {
		// 验证参数
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		pages = pages == null || pages < 3 ? PageConst.PAGE_DEFAULT : pages;
		rows = rows == null ? pages == PageConst.PAGE_DEFAULT ? PageConst.ROWS_DEFAULT_FIRST : PageConst.ROWS_DEFAULT
				: rows;
		// 核销商品
		PageHelper.startPage(pages, rows);
		GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
		goodsOrderGoodsInfoExample.createCriteria().andTypeEqualTo(GoodsConst.GOODS_OVERSTOCK_TYPE_CODE)
				.andUserIdEqualTo(userId);
		goodsOrderGoodsInfoExample.setOrderByClause("status ASC,use_end_time ASC");
		List<GoodsOrderGoodsInfo> goodsOrderGoodsInfos = goodsOrderGoodsInfoMapper
				.selectByExample(goodsOrderGoodsInfoExample);
		// 构造返回值
		return JsonData.setSuccessMessage(goodsOrderGoodsInfos);
	}

	/**
	 * 获取用户已够买的核销商品详情
	 */
	@Override
	public JsonData getGoodsOrderGoodsInfoDetail(Integer goodsOrderGoodsInfoId) {
		// 验证参数
		if (goodsOrderGoodsInfoId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询订单
		GoodsOrderGoodsInfo goodsOrderGoodsInfo = goodsOrderGoodsInfoMapper.selectByPrimaryKey(goodsOrderGoodsInfoId);
		// 构造返回值
		return JsonData.setSuccessMessage(goodsOrderGoodsInfo);
	}

}
