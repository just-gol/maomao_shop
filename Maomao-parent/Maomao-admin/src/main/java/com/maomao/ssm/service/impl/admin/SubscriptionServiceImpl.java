package com.maomao.ssm.service.impl.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsAddressExample;
import com.maomao.ssm.bean.GoodsOrder;
import com.maomao.ssm.bean.GoodsOrderGoods;
import com.maomao.ssm.bean.GoodsOrderGoodsExample;
import com.maomao.ssm.bean.GoodsSubscription;
import com.maomao.ssm.bean.GoodsSubscriptionExample;
import com.maomao.ssm.bean.GoodsSubscriptionExample.Criteria;
import com.maomao.ssm.bean.GoodsSubscriptionRecord;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsWarehouse;
import com.maomao.ssm.bean.GoodsWarehouseExample;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.OrderConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.GoodsAddressMapper;
import com.maomao.ssm.dao.GoodsOrderGoodsMapper;
import com.maomao.ssm.dao.GoodsOrderMapper;
import com.maomao.ssm.dao.GoodsSubscriptionMapper;
import com.maomao.ssm.dao.GoodsSubscriptionRecordMapperCustom;
import com.maomao.ssm.dao.GoodsWarehouseMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.GoodsSubscriptionAndWarehouse;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.SubscriptionService;
import com.maomao.ssm.service.common.UserCreditService;
import com.maomao.ssm.service.common.UserMoneyService;

/**
 * @author:wzy
 * @descrption:认筹管理
 * @version:
 * @date:2018年3月7日
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	@Autowired
	private GoodsSubscriptionMapper goodsSubscriptionMapper;
	@Autowired
	private GoodsSubscriptionRecordMapperCustom goodsSubscriptionRecordMapperCustom;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GoodsWarehouseMapper goodsWarehouseMapper;
	@Autowired
	private GoodsAddressMapper goodsAddressMapper;
	@Autowired
	private GoodsOrderMapper goodsOrderMapper;
	@Autowired
	private GoodsOrderGoodsMapper goodsOrderGoodsMapper;
	@Autowired
	private UserMoneyService userMoneyService;
	@Autowired
	private UserCreditService userCreditService;

	/**
	 * 认筹列表
	 */
	public JsonData getSubList(Integer pages, Integer rows, Integer categoryId, Byte status, String name) {
		if (pages < 1 || rows < 1) {
			pages = 1;
			rows = 10;
		}

		PageHelper.startPage(pages, rows);
		GoodsSubscriptionExample example = new GoodsSubscriptionExample();
		example.setOrderByClause("create_time desc");
		Criteria criteria = example.createCriteria();

		if (categoryId != null) {
			criteria.andCategoryIdEqualTo(categoryId);
		}
		if (status != null) {
			criteria.andSubscriptionStatusEqualTo(status);
		}

		if (StringUtils.isNotBlank(name)) {
			criteria.andNameLike("%" + name + "%");
		}
		example.setOrderByClause("create_time DESC");
		List<GoodsSubscription> subList = goodsSubscriptionMapper.selectByExample(example);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		if (subList != null && subList.size() > 0) {
			for (GoodsSubscription goods : subList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", goods.getId());
				// System.out.println("id:"+goods.getId());
				map.put("name", goods.getName());
				map.put("price_subscription", goods.getPriceSubscription());// 认筹价格
				map.put("price_sales", goods.getPriceSales());
				map.put("num_subscription_total", goods.getNumSubscriptionTotal());// 认筹份数
				map.put("num_subscription", goods.getNumSubscription());// 已认筹份数
				map.put("bonus_avg", goods.getBonusAvg());// 单笔分红
				map.put("startTime", goods.getSubscriptionStartTime());// 认筹开始时间
				map.put("endTime", goods.getSubscriptionEndTime());// 认筹结束时间
				map.put("sellStatus", goods.getSellStatus()); // 认筹中状态
				// System.out.println("subscriptionStatus:"+goods.getSubscriptionStatus());
				switch (goods.getSubscriptionStatus()) {
				case -1:
					map.put("status", "认筹未开始");
					break;
				case 0:
					map.put("status", "认筹已开启");
					break;
				case 1:
					map.put("status", "商品已上架");
					break;
				case 2:
					map.put("status", "已售出");
					break;
				case 3:
					map.put("status", "认筹失败");
					break;
				case 4:
					map.put("status", "分销失败");
					break;
				case -2:
					map.put("status", "认筹审核中");
					break;
				case -3:
					map.put("status", "认筹被拒绝");
					break;
				case -4:
					map.put("status", "认筹被退回");
					break;
				}
				returnList.add(map);
			}
		}
		PageInfo<GoodsSubscription> pageInfo = new PageInfo<GoodsSubscription>(subList);
		PageBean pageBean = new PageBean();
		pageBean.setTotal(pageInfo.getTotal());
		pageBean.setRows(returnList);

		return JsonData.setSuccessMessage(pageBean);
	}

	/**
	 * 添加认筹商品
	 */
	public JsonData saveSubItem(GoodsSubscriptionWithBLOBs goods, Long bonus, GoodsWarehouse goodsWarehouse,
			String diyAddress, String diyProvince, String diyCity, String diyArea, String diyMobile,
			String diyAddressName) {
		if (goods == null || bonus == null || goodsWarehouse == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		Integer goodsId = goods.getId();
		try {
			if (goodsId == null) {
				// 认筹份数
				Integer numSubscriptionTotal = goods.getNumSubscriptionTotal();
				// 单笔分红
				Long bonusAvg = bonus / numSubscriptionTotal;
				goods.setBonusAvg(bonusAvg);
				goods.setPriceSubscription(goods.getPriceSales());
				Long priceSubscriptionAvg = goods.getPriceSales() / numSubscriptionTotal;
				goods.setPriceSubscriptionAvg(priceSubscriptionAvg);
				if (goods.getDeposit() == null) {
					goods.setDeposit(0l);
				}
				goods.setNumSubscription(0);
				goods.setSubscriptionStatus(GoodsConst.GOODS_SUBSCRIPTION_STATUS_ON_CKECK);// 认筹申请中
				goods.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_FALES);// 认筹申请中
				goods.setCreateTime(new Date());
				goods.setSellStatus(GoodsConst.GOODS_SUBSCRIPTION_SELL_STATUS_ONSALE);
				goodsSubscriptionMapper.insertSelective(goods);
				goodsId = goods.getId();

				// 仓库对象
				goodsWarehouse.setBizId(goodsId);
				goodsWarehouse.setCategory(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION);// 商品分类认筹
				goodsWarehouse.setCreateTime(new Date());
				goodsWarehouseMapper.insertSelective(goodsWarehouse);

				if (goods.getGetWay() == GoodsConst.GOODS_GET_WAY_SELF) {// 自取地址
					GoodsAddress goodsAddress = new GoodsAddress();
					goodsAddress.setBizId(goodsId);
					goodsAddress.setProvince(diyProvince);
					goodsAddress.setCity(diyCity);
					goodsAddress.setArea(diyArea);
					goodsAddress.setAddress(diyAddress);
					goodsAddress.setMobile(diyMobile);
					goodsAddress.setName(diyAddressName);
					goodsAddress.setCategory(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION);
					goodsAddress.setCreateTime(new Date());
					goodsAddressMapper.insertSelective(goodsAddress);
				}

			} else {
				Integer numSubscriptionTotal = goods.getNumSubscriptionTotal();
				goods.setPriceSubscription(goods.getPriceSales());
				Long priceSubscriptionAvg = goods.getPriceSales() / numSubscriptionTotal;
				goods.setPriceSubscriptionAvg(priceSubscriptionAvg);
				if (goods.getDeposit() == null) {
					goods.setDeposit(0l);
				}
				goods.setSubscriptionStatus(GoodsConst.GOODS_SUBSCRIPTION_STATUS_ON_CKECK);
				goods.setModifiedTime(new Date());
				goodsSubscriptionMapper.updateByPrimaryKeySelective(goods);
				// 仓库对象
				goodsWarehouse.setBizId(goodsId);
				goodsWarehouse.setCreateTime(new Date());
				goodsWarehouseMapper.updateByPrimaryKeySelective(goodsWarehouse);

				// 自取地址
				if (goods.getGetWay() == GoodsConst.GOODS_GET_WAY_SELF) {// 自取
					GoodsAddressExample addressExample = new GoodsAddressExample();
					addressExample.createCriteria().andBizIdEqualTo(goods.getId())
							.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
					List<GoodsAddress> goodsAddressList = goodsAddressMapper.selectByExample(addressExample);
					if (goodsAddressList != null && goodsAddressList.size() > 0) {
						GoodsAddress goodsAddress = goodsAddressList.get(0);// 自取地址对象
						goodsAddress.setBizId(goods.getId());
						goodsAddress.setProvince(diyProvince);
						goodsAddress.setCity(diyCity);
						goodsAddress.setArea(diyArea);
						goodsAddress.setAddress(diyAddress);
						goodsAddress.setMobile(diyMobile);
						goodsAddress.setName(diyAddressName);
						goodsAddress.setCategory(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION);
						goodsAddress.setCreateTime(new Date());
						goodsAddressMapper.updateByPrimaryKeySelective(goodsAddress);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("添加失败");
		}

		return JsonData.setSuccessMessage();
	}

	/**
	 * 根据认筹id查询认筹商品信息
	 */
	public JsonData getSubItem(Integer itemId) {
		if (itemId == null || itemId < 0) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsSubscriptionAndWarehouse goodsSubscriptionAndWarehouse = new GoodsSubscriptionAndWarehouse();
		try {
			// 认筹商品对象
			GoodsSubscriptionWithBLOBs goods = goodsSubscriptionMapper.selectByPrimaryKey(itemId);
			// 仓库对象
			GoodsWarehouseExample example = new GoodsWarehouseExample();
			example.createCriteria().andBizIdEqualTo(itemId).andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION);
			List<GoodsWarehouse> goodsWarehouseList = goodsWarehouseMapper.selectByExample(example);

			// 自取地址
			GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
			goodsAddressExample.createCriteria().andBizIdEqualTo(itemId)
					.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION);
			List<GoodsAddress> goodsAddressList = goodsAddressMapper.selectByExample(goodsAddressExample);

			if (goods != null) {
				goodsSubscriptionAndWarehouse.setGoods(goods);
			}
			if (goodsWarehouseList != null && goodsWarehouseList.size() > 0) {
				goodsSubscriptionAndWarehouse.setGoodsWarehouse(goodsWarehouseList.get(0));
			}
			if (goodsAddressList != null && goodsAddressList.size() > 0) {
				goodsSubscriptionAndWarehouse.setGoodsAddress(goodsAddressList.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("未查询到商品信息");
		}
		// System.out.println("==="+goodsSubscriptionAndWarehouse.getGoodsAddress().getProvince());
		return JsonData.setSuccessMessage(goodsSubscriptionAndWarehouse);
	}

	/**
	 * 处理认筹审核
	 */
	public JsonData saveSubItemStatus(Integer itemId, Byte type, String reason, Integer adminId) {
		// type :-3 拒绝,-1:通过(状态变为未开始) -4:退回
		if (itemId == null || type == null || itemId < 0) {
			return JsonData.setErrorMessage("参数错误");
		}

		GoodsSubscriptionWithBLOBs goods = goodsSubscriptionMapper.selectByPrimaryKey(itemId);

		if (goods != null) {
			try {
				if (type == GoodsConst.GOODS_SUBSCRIPTION_STATUS_CHECK_FAILED
						|| type == GoodsConst.GOODS_SUBSCRIPTION_STATUS_SET_BACK) {
					goods.setCheckReason(reason);
				}
				goods.setSubscriptionStatus(type);
				goods.setAdminId(adminId);
				goods.setCheckTime(new Date());
				goodsSubscriptionMapper.updateByPrimaryKeyWithBLOBs(goods);
				return JsonData.setSuccessMessage();
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
		return JsonData.setErrorMessage("网络异常");
	}

	/**
	 * 认筹明细记录
	 */
	public JsonData getSubDetail(Integer pages, Integer rows, Integer itemId) {
		if (itemId == null || itemId < 0) {
			return JsonData.setErrorMessage("参数错误");
		}

		if (pages < 1 || rows < 1) {
			pages = 1;
			rows = 10;
		}

		PageHelper.startPage(pages, rows);

		List<GoodsSubscriptionRecord> goodsSubscriptionRecords = goodsSubscriptionRecordMapperCustom
				.selectGroupByUserId(itemId);

		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		if (goodsSubscriptionRecords != null && goodsSubscriptionRecords.size() > 0) {
			for (GoodsSubscriptionRecord goodsSubscriptionRecord : goodsSubscriptionRecords) {
				Map<String, Object> map = new HashMap<String, Object>();
				Integer userId = goodsSubscriptionRecord.getUserId();
				User user = userMapper.selectByPrimaryKey(userId);
				if (user != null) {
					map.put("user", user.getName());
					map.put("mobile", user.getMobile());
				}
				map.put("userNum", goodsSubscriptionRecord.getSubscriptionNum());// 用户认筹份数
				map.put("money", goodsSubscriptionRecord.getPriceSubcriptionTotal());// 认筹总额
				map.put("bonus", goodsSubscriptionRecord.getBonusTotal());// 分红总额
				returnList.add(map);
			}
		}
		PageInfo<GoodsSubscriptionRecord> pageInfo = new PageInfo<GoodsSubscriptionRecord>(goodsSubscriptionRecords);
		PageBean pageBean = new PageBean();
		pageBean.setRows(returnList);
		pageBean.setTotal(pageInfo.getTotal());
		return JsonData.setSuccessMessage(pageBean);
	}

	/**
	 * 确认分红
	 */
	public JsonData confirmBounsAvg(Integer goodsId) {
		if (goodsId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsSubscriptionWithBLOBs sub = goodsSubscriptionMapper.selectByPrimaryKey(goodsId);
		if (sub == null || sub.getSubscriptionStatus() != GoodsConst.GOODS_SUBSCRIPTION_STATUS_FINISHED) {
			return JsonData.setErrorMessage("商品状态异常");
		}
		// 查询订单 及商品
		GoodsOrderGoodsExample example2 = new GoodsOrderGoodsExample();
		example2.createCriteria().andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION).andBizIdEqualTo(goodsId);
		List<GoodsOrderGoods> goodsOrderGoodsList = goodsOrderGoodsMapper.selectByExample(example2);
		Integer orderId = goodsOrderGoodsList.get(0).getOrderId();// 订单id
		long reword = goodsOrderGoodsList.get(0).getReword();// 销售奖励
		GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);

		// 判断结算状态
		if (!OrderConst.ORDER_STATUS_SETTLEMENT_FALES.equals(sub.getStatusSettlement())
				|| !OrderConst.ORDER_STATUS_SETTLEMENT_FALES.equals(goodsOrder.getStatusSettlement())) {
			return JsonData.setErrorMessage("商品状态异常");
		}

		List<GoodsSubscriptionRecord> subRecordList = goodsSubscriptionRecordMapperCustom.selectGroupByUserId(goodsId);
		if (subRecordList != null && subRecordList.size() > 0) {
			for (GoodsSubscriptionRecord subRecord : subRecordList) {
				long bonusTotal = subRecord.getBonusTotal();// 分红总额
				Integer userId = subRecord.getUserId();
				User user = userMapper.selectByPrimaryKey(userId);

				// 返还信用额度
				userCreditService.addUserCredit(user.getId(), user.getCreditSurplus(),
						subRecord.getPriceSubcriptionTotal(), UserConts.USER_CREDIT_TYPE_SALE_RETURN, orderId);
				user.setCreditSurplus(user.getCreditSurplus() + subRecord.getPriceSubcriptionTotal());
				// 用户分红
				// userMoneyService.addUserMoney(userId, user.getMoney(),
				// bonusTotal,
				// UserConts.USER_MONEY_TYPE_BONUS,
				// orderId);
				// user.setMoney(user.getMoney() + bonusTotal);
				userCreditService.addUserCredit(userId, user.getCreditSurplus(), bonusTotal,
						UserConts.USER_CREDIT_TYPE_BONUS, orderId);
				user.setCreditTotal(user.getCreditTotal() + bonusTotal);
				user.setCreditSurplus(user.getCreditSurplus() + bonusTotal);
				userMapper.updateByPrimaryKeySelective(user);
			}
			// 售出用户额外奖励
			Integer salesUserId = goodsOrder.getSalesUserId();// 销售用户id
			User salesUser = userMapper.selectByPrimaryKey(salesUserId);
			userMoneyService.addUserMoney(salesUserId, salesUser.getMoney(), reword, UserConts.USER_MONEY_TYPE_SALES,
					orderId);

			salesUser.setCreditTotal(salesUser.getCreditTotal() + reword);
			salesUser.setCreditSurplus(salesUser.getCreditSurplus() + reword);
			userMapper.updateByPrimaryKeySelective(salesUser);
			// 修改订单和认筹状态
			goodsOrder.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_TRUE);
			sub.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_TRUE);
			goodsOrderMapper.updateByPrimaryKeySelective(goodsOrder);
			goodsSubscriptionMapper.updateByPrimaryKeyWithBLOBs(sub);
			return JsonData.setSuccessMessage();
		}
		return JsonData.setErrorMessage("未查询到分红信息");
	}

	/**
	 * 修改合卖中状态
	 */
	public JsonData updateSelleStatus(Integer goodsId, Byte sellStatus) {
		if (goodsId == null) {
			return JsonData.setErrorMessage("此商品不存在!");
		}
		if (sellStatus == null) {
			return JsonData.setErrorMessage("状态不能为空!");
		}
		GoodsSubscriptionWithBLOBs goodsSubscriptionWithBLOBs = goodsSubscriptionMapper.selectByPrimaryKey(goodsId);
		goodsSubscriptionWithBLOBs.setSellStatus(sellStatus);
		goodsSubscriptionWithBLOBs.setModifiedTime(new Date());
		goodsSubscriptionMapper.updateByPrimaryKeySelective(goodsSubscriptionWithBLOBs);
		return JsonData.setSuccessMessage(goodsSubscriptionWithBLOBs);
	}
}
