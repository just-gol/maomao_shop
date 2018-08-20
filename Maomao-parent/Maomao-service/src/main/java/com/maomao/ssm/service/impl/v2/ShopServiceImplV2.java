package com.maomao.ssm.service.impl.v2;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.bean.GoodsOrderGoods;
import com.maomao.ssm.bean.GoodsOrderGoodsExample;
import com.maomao.ssm.bean.GoodsOverstockInfoUser;
import com.maomao.ssm.bean.GoodsOverstockInfoUserExample;
import com.maomao.ssm.bean.GoodsOverstockUser;
import com.maomao.ssm.bean.GoodsOverstockUserExample;
import com.maomao.ssm.bean.GoodsOverstockUserWithBLOBs;
import com.maomao.ssm.bean.GoodsShareRecord;
import com.maomao.ssm.bean.GoodsShareRecordExample;
import com.maomao.ssm.bean.GoodsSubscriptionExample;
import com.maomao.ssm.bean.GoodsSubscriptionRecord;
import com.maomao.ssm.bean.GoodsSubscriptionRecordExample;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsUser;
import com.maomao.ssm.bean.GoodsUserExample;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.bean.HomeCategory;
import com.maomao.ssm.bean.HomeCategoryExample;
import com.maomao.ssm.bean.HomeContent;
import com.maomao.ssm.bean.HomeContentExample;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserMoney;
import com.maomao.ssm.bean.UserMoneyExample;
import com.maomao.ssm.bean.UserWithdrawals;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsOrderGoodsMapper;
import com.maomao.ssm.dao.GoodsOverstockInfoMapper;
import com.maomao.ssm.dao.GoodsOverstockInfoUserMapper;
import com.maomao.ssm.dao.GoodsOverstockUserMapper;
import com.maomao.ssm.dao.GoodsShareRecordMapper;
import com.maomao.ssm.dao.GoodsSubscriptionMapper;
import com.maomao.ssm.dao.GoodsSubscriptionRecordMapper;
import com.maomao.ssm.dao.GoodsUserMapper;
import com.maomao.ssm.dao.HomeCategoryMapper;
import com.maomao.ssm.dao.HomeContentMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.dao.UserMoneyMapper;
import com.maomao.ssm.dao.UserWithdrawalsMapper;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.shop.AccountDetail;
import com.maomao.ssm.pojo.shop.AccountLogs;
import com.maomao.ssm.pojo.v2.Account;
import com.maomao.ssm.pojo.v2.Hemai;
import com.maomao.ssm.pojo.v2.MyShop;
import com.maomao.ssm.pojo.v2.ShopShare;
import com.maomao.ssm.pojo.v2.ShopUnShare;
import com.maomao.ssm.service.v2.ShopServiceV2;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年7月11日
 */
@Service
public class ShopServiceImplV2 implements ShopServiceV2 {
	@Autowired
	private GoodsUserMapper goodsUserMapper;
	@Autowired
	private GoodsOverstockInfoUserMapper overStockInfoUserMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsOverstockInfoMapper goodsOverstockInfoMapper;
	@Autowired
	private HomeCategoryMapper homeCategoryMapper;
	@Autowired
	private HomeContentMapper homeContentMapper;
	@Autowired
	private GoodsOverstockUserMapper goodsOverstockUserMapper;
	@Autowired
	private UserMoneyMapper userMoneyMapper;
	@Autowired
	private GoodsOrderGoodsMapper goodsOrderGoodsMapper;
	@Autowired
	private UserWithdrawalsMapper userWithdrawalsMapper;
	@Autowired
	private GoodsShareRecordMapper shareRecordMapper;
	@Autowired
	private GoodsSubscriptionMapper subscriptionMapper;
	@Autowired
	private GoodsSubscriptionRecordMapper recordMapper;
	@Autowired
	private GoodsOverstockInfoUserMapper goodsOverstockInfoUserMapper;

	/**
	 * 我的店铺
	 */
	public JsonData myShop(Integer userId) {
		if (userId == null) {
			return JsonData.setErrorMessage("参数错误");
		}

		MyShop myShop = new MyShop();
		// 受益金额
		User user = userMapper.selectByPrimaryKey(userId);
		if (user != null) {
			long money = user.getMoney();// 受益金额
			// 已经分享商品 ShopShare
			List<Map<String, Object>> shareList = new ArrayList<Map<String, Object>>();
			GoodsShareRecordExample example1 = new GoodsShareRecordExample();
			example1.createCriteria().andUserIdEqualTo(userId).andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);// 上架状态
			List<GoodsShareRecord> goodsUserList = shareRecordMapper.selectByExample(example1);
			List<Map<String, Object>> shareRecommend = null;
			if (goodsUserList != null && goodsUserList.size() > 0) {
				GoodsExample example = new GoodsExample();
				if (goodsUserList.size() > 3) {
					for (int i = 0; i < 3; i++) {
						example.clear();
						Map<String, Object> goodsUserGreater3 = new HashMap<String, Object>();
						Integer bizId = goodsUserList.get(i).getBizId();
						example.createCriteria().andIdEqualTo(bizId).andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
						List<GoodsWithBLOBs> goodsList = goodsMapper.selectByExampleWithBLOBs(example);
						if (goodsList != null && goodsList.size() > 0) {
							GoodsWithBLOBs goods = goodsList.get(0);
							goodsUserGreater3.put("id", goods.getId());
							goodsUserGreater3.put("category", 1);
							goodsUserGreater3.put("name", goods.getName());
							goodsUserGreater3.put("rebate", goods.getRebate());
							goodsUserGreater3.put("stock", goods.getStock());
							goodsUserGreater3.put("img", goods.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
							shareList.add(goodsUserGreater3);
						}
					}
				} else {
					for (GoodsShareRecord goodsUser : goodsUserList) {
						Map<String, Object> goodsUserLess3 = new HashMap<String, Object>();
						example.clear();
						Integer bizId = goodsUser.getBizId();
						example.createCriteria().andIdEqualTo(bizId).andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
						List<GoodsWithBLOBs> goodsList = goodsMapper.selectByExampleWithBLOBs(example);
						if (goodsList != null && goodsList.size() > 0) {
							GoodsWithBLOBs goods = goodsList.get(0);
							goodsUserLess3.put("id", goods.getId());
							goodsUserLess3.put("categoryId", 1);
							goodsUserLess3.put("name", goods.getName());
							goodsUserLess3.put("rebate", goods.getRebate());
							goodsUserLess3.put("stock", goods.getStock());
							goodsUserLess3.put("img", goods.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
							shareList.add(goodsUserLess3);
						}
					}
				}

				// 已经分享的推荐商品
				HomeCategoryExample example2 = new HomeCategoryExample();
				example2.createCriteria().andStatusEqualTo((byte) 1).andImgTypeEqualTo((byte) 5);
				List<HomeCategory> homeCategories = homeCategoryMapper.selectByExample(example2);
				shareRecommend = new ArrayList<Map<String, Object>>();// 推荐商品列表
				if (homeCategories != null && homeCategories.size() > 0) {
					Integer categoryId = homeCategories.get(0).getId();
					HomeContentExample example3 = new HomeContentExample();
					example3.createCriteria().andStatusEqualTo((byte) 1).andCategoryIdEqualTo(categoryId);
					List<HomeContent> hoemContents = homeContentMapper.selectByExample(example3);
					if (hoemContents != null && homeCategories.size() > 0) {
						for (HomeContent homeContent : hoemContents) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", homeContent.getId());
							if (homeContent.getUrl().contains("goodsDetail")) {
								map.put("category", 1);
							} else {
								map.put("category", -1);
							}
							map.put("name", homeContent.getName());
							map.put("price", homeContent.getPrice());
							map.put("reword", homeContent.getReword());
							shareRecommend.add(map);
						}
					}
				}

			}
			ShopShare shopShare = new ShopShare(shareList, shareRecommend);
			myShop.setShopShare(shopShare);
			// 购买未分享 goods overstock user
			List<Map<String, Object>> unshareList = new ArrayList<Map<String, Object>>();
			GoodsOverstockUserExample example = new GoodsOverstockUserExample();
			example.createCriteria().andStatusEqualTo((byte) 2).andUserIdEqualTo(userId);
			List<GoodsOverstockUser> goodsOverstockInfoUserList = goodsOverstockUserMapper.selectByExample(example);
			if (goodsOverstockInfoUserList != null && goodsOverstockInfoUserList.size() > 0) {
				GoodsOverstockInfoUserExample example4 = new GoodsOverstockInfoUserExample();
				if (goodsOverstockInfoUserList.size() > 3) {

					for (int i = 0; i < 3; i++) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", goodsOverstockInfoUserList.get(i).getId());
						map.put("name", goodsOverstockInfoUserList.get(i).getName());
						// map.put("type",
						// goodsOverstockInfoUserList.get(i).getType());// 0
						example4.clear();
						example4.setOrderByClause("use_end_time asc");
						example4.createCriteria()
								.andGoodsOverstockUserIdEqualTo(goodsOverstockInfoUserList.get(i).getId())
								.andUseEndTimeGreaterThan(new Date()).andTypeEqualTo((byte) 0);
						List<GoodsOverstockInfoUser> infoUserList = goodsOverstockInfoUserMapper
								.selectByExample(example4);
						if (infoUserList != null && infoUserList.size() > 0) {
							GoodsOverstockInfoUser goodsOverstockInfoUser = infoUserList.get(0);
							map.put("time", goodsOverstockInfoUser.getUseEndTime());
						} else {
							map.put("time", null);
						}
						// GoodsOverstockUserWithBLOBs
						// goodsOverstockUserWithBLOBs =
						// goodsOverstockUserMapper
						// .selectByPrimaryKey(goodsOverstockInfoUserList.get(i).getGoodsOverstockUserId());
						map.put("stock", goodsOverstockInfoUserList.get(i).getStock());
						map.put("rebate", goodsOverstockInfoUserList.get(i).getRebate());
						map.put("category", -1);
						unshareList.add(map);
					}
				} else {
					for (GoodsOverstockUser goodsOverstockInfoUser : goodsOverstockInfoUserList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", goodsOverstockInfoUser.getId());
						map.put("name", goodsOverstockInfoUser.getName());
						example4.clear();
						example4.setOrderByClause("use_end_time asc");
						example4.createCriteria().andGoodsOverstockUserIdEqualTo(goodsOverstockInfoUser.getId())
								.andUseEndTimeGreaterThan(new Date()).andTypeEqualTo((byte) 0);
						List<GoodsOverstockInfoUser> infoUserList = goodsOverstockInfoUserMapper
								.selectByExample(example4);
						if (infoUserList != null && infoUserList.size() > 0) {
							GoodsOverstockInfoUser goodsOverstockInfoUser2 = infoUserList.get(0);
							map.put("time", goodsOverstockInfoUser2.getUseEndTime());
						} else {
							map.put("time", null);
						}
						// map.put("type", goodsOverstockInfoUser.getType());//
						// 0核销
						// 1实物
						// map.put("time",
						// goodsOverstockInfoUser.getUseEndTime());
						// GoodsOverstockUserWithBLOBs
						// goodsOverstockUserWithBLOBs =
						// goodsOverstockUserMapper
						// .selectByPrimaryKey(goodsOverstockInfoUser.getGoodsOverstockUserId());
						map.put("stock", goodsOverstockInfoUser.getStock());
						map.put("category", -1);
						map.put("rebate", goodsOverstockInfoUser.getRebate());
						unshareList.add(map);
					}
				}
			}

			// 购买分享的推荐商品
			HomeCategoryExample example2 = new HomeCategoryExample();
			example2.createCriteria().andStatusEqualTo((byte) 1).andImgTypeEqualTo((byte) 6);
			List<HomeCategory> homeCategories = homeCategoryMapper.selectByExample(example2);
			List<Map<String, Object>> shareRecommend2 = new ArrayList<Map<String, Object>>();// 推荐商品列表
			if (homeCategories != null && homeCategories.size() > 0) {
				Integer categoryId = homeCategories.get(0).getId();
				HomeContentExample example3 = new HomeContentExample();
				example3.createCriteria().andStatusEqualTo((byte) 1).andCategoryIdEqualTo(categoryId);
				List<HomeContent> hoemContents = homeContentMapper.selectByExample(example3);
				if (hoemContents != null && homeCategories.size() > 0) {
					for (HomeContent homeContent : hoemContents) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", homeContent.getId());
						map.put("name", homeContent.getName());
						if (homeContent.getUrl().contains("goodsDetail")) {
							map.put("category", 1);

						} else {
							map.put("category", -1);
						}
						map.put("price", homeContent.getPrice());
						map.put("reword", homeContent.getReword());
						shareRecommend2.add(map);
					}
				}
			}
			ShopUnShare shopUnShare = new ShopUnShare(unshareList, shareRecommend2);
			myShop.setShopUnShare(shopUnShare);

			// 账户明细
			AccountDetail accountDetail = new AccountDetail();
			List<AccountLogs> accountLogList = new ArrayList<AccountLogs>();

			UserMoneyExample example4 = new UserMoneyExample();
			example4.createCriteria().andUserIdEqualTo(userId);
			example4.setOrderByClause("create_time desc");
			List<UserMoney> list = userMoneyMapper.selectByExample(example4);
			if (list != null && list.size() > 0) {
				if (list.size() > 3) {
					for (int i = 0; i < 3; i++) {
						AccountLogs accountLogs = new AccountLogs();
						Integer bizId = list.get(0).getBizId();// 变更业务id
						byte type = list.get(0).getType();// 变更类型
						StringBuilder sb = new StringBuilder();
						switch (type) {
						case 0:// 奖励金 0:奖励金, 1:返利 ,2:提现,3:认筹分红
							GoodsOrderGoodsExample example5 = new GoodsOrderGoodsExample();
							example5.createCriteria().andOrderIdEqualTo(bizId);
							List<GoodsOrderGoods> list2 = goodsOrderGoodsMapper.selectByExample(example5);
							if (list2 != null) {
								GoodsOrderGoods goodsOrderGoods = list2.get(0);
								accountLogs.setTitle("奖励金");
								accountLogs.setName(goodsOrderGoods.getName());
								accountLogs.setTime(goodsOrderGoods.getCreateTime());
								accountLogs.setMoney(list.get(0).getMoneyChange());
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
								accountLogs.setMoney(list.get(0).getMoneyChange());
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
								accountLogs.setMoney(list.get(0).getMoneyChange());
							}
							break;
						case 3:// 认筹分红
							GoodsOrderGoodsExample example6 = new GoodsOrderGoodsExample();
							example6.createCriteria().andOrderIdEqualTo(bizId);
							List<GoodsOrderGoods> list4 = goodsOrderGoodsMapper.selectByExample(example6);
							if (list4 != null) {
								GoodsOrderGoods goodsOrderGoods = list4.get(0);
								accountLogs.setTitle("认筹分红");
								accountLogs.setName(goodsOrderGoods.getName());
								accountLogs.setTime(goodsOrderGoods.getCreateTime());
								accountLogs.setMoney(list.get(0).getMoneyChange());
							}
							break;
						case 4:// 购买商品
							GoodsOrderGoodsExample example8 = new GoodsOrderGoodsExample();
							example8.createCriteria().andOrderIdEqualTo(bizId);
							List<GoodsOrderGoods> list6 = goodsOrderGoodsMapper.selectByExample(example8);
							if (list6 != null) {
								GoodsOrderGoods goodsOrderGoods = list6.get(0);
								accountLogs.setTitle("购买商品");
								accountLogs.setName(goodsOrderGoods.getName());
								accountLogs.setTime(goodsOrderGoods.getCreateTime());
								accountLogs.setMoney(list.get(0).getMoneyChange());
							}
							break;
						case 5:// 售出奖励
							GoodsOrderGoodsExample example7 = new GoodsOrderGoodsExample();
							example7.createCriteria().andOrderIdEqualTo(bizId);
							List<GoodsOrderGoods> list5 = goodsOrderGoodsMapper.selectByExample(example7);
							if (list5 != null) {
								GoodsOrderGoods goodsOrderGoods = list5.get(0);
								accountLogs.setTitle("售出奖励");
								accountLogs.setName(goodsOrderGoods.getName());
								accountLogs.setTime(goodsOrderGoods.getCreateTime());
								accountLogs.setMoney(list.get(0).getMoneyChange());
							}
							break;
						}
						accountLogList.add(accountLogs);
					}
				} else {
					for (UserMoney userMoney : list) {
						AccountLogs accountLogs = new AccountLogs();
						Integer bizId = userMoney.getBizId();// 变更业务id
						byte type = userMoney.getType();// 变更类型
						StringBuilder sb = new StringBuilder();
						switch (type) {
						case 0:// 奖励金 0:奖励金, 1:返利 ,2:提现,3:认筹分红
							GoodsOrderGoodsExample example5 = new GoodsOrderGoodsExample();
							example5.createCriteria().andOrderIdEqualTo(bizId);
							List<GoodsOrderGoods> list2 = goodsOrderGoodsMapper.selectByExample(example5);
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
							GoodsOrderGoodsExample example6 = new GoodsOrderGoodsExample();
							example6.createCriteria().andOrderIdEqualTo(bizId);
							List<GoodsOrderGoods> list4 = goodsOrderGoodsMapper.selectByExample(example6);
							if (list4 != null) {
								GoodsOrderGoods goodsOrderGoods = list4.get(0);
								accountLogs.setTitle("认筹分红");
								accountLogs.setName(goodsOrderGoods.getName());
								accountLogs.setTime(goodsOrderGoods.getCreateTime());
								accountLogs.setMoney(userMoney.getMoneyChange());
							}
							break;
						case 4:// 购买商品
							GoodsOrderGoodsExample example8 = new GoodsOrderGoodsExample();
							example8.createCriteria().andOrderIdEqualTo(bizId);
							List<GoodsOrderGoods> list6 = goodsOrderGoodsMapper.selectByExample(example8);
							if (list6 != null) {
								GoodsOrderGoods goodsOrderGoods = list6.get(0);
								accountLogs.setTitle("购买商品");
								accountLogs.setName(goodsOrderGoods.getName());
								accountLogs.setTime(goodsOrderGoods.getCreateTime());
								accountLogs.setMoney(userMoney.getMoneyChange());
							}
							break;
						case 5:// 售出奖励
							GoodsOrderGoodsExample example7 = new GoodsOrderGoodsExample();
							example7.createCriteria().andOrderIdEqualTo(bizId);
							List<GoodsOrderGoods> list5 = goodsOrderGoodsMapper.selectByExample(example7);
							if (list5 != null) {
								GoodsOrderGoods goodsOrderGoods = list5.get(0);
								accountLogs.setTitle("售出奖励");
								accountLogs.setName(goodsOrderGoods.getName());
								accountLogs.setTime(goodsOrderGoods.getCreateTime());
								accountLogs.setMoney(userMoney.getMoneyChange());
							}
							break;
						}
						accountLogList.add(accountLogs);
					}

				}
			}
			Account account = new Account();
			account.setAccountLogs(accountLogList);
			myShop.setAccount(account);
			return JsonData.setSuccessMessage(myShop);
		} else {
			return JsonData.setErrorMessage("用户不存在");

		}
	}

	/**
	 * 查看全部 0:已经分享 1:购买未分享 2:账户明细
	 */
	public JsonData showAll(Integer type, Integer userId) {
		if (type == 0) {
			List<Map<String, Object>> shareList = new ArrayList<Map<String, Object>>();
			GoodsShareRecordExample example1 = new GoodsShareRecordExample();
			example1.createCriteria().andUserIdEqualTo(userId).andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
			List<GoodsShareRecord> goodsUserList = shareRecordMapper.selectByExample(example1);
			if (goodsUserList != null && goodsUserList.size() > 0) {
				GoodsExample example = new GoodsExample();
				for (GoodsShareRecord goodsUser : goodsUserList) {
					Map<String, Object> goodsUserLess3 = new HashMap<String, Object>();
					example.clear();
					Integer bizId = goodsUser.getBizId();
					example.createCriteria().andIdEqualTo(bizId).andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
					List<GoodsWithBLOBs> goodsList = goodsMapper.selectByExampleWithBLOBs(example);
					if (goodsList != null && goodsList.size() > 0) {
						GoodsWithBLOBs goods = goodsList.get(0);
						goodsUserLess3.put("id", goods.getId());
						goodsUserLess3.put("categoryId", goods.getCategoryId());
						goodsUserLess3.put("name", goods.getName());
						goodsUserLess3.put("rebate", goods.getRebate());
						goodsUserLess3.put("stock", goods.getStock());
						goodsUserLess3.put("img", goods.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
						shareList.add(goodsUserLess3);
					}
				}
			}
			return JsonData.setSuccessMessage(shareList);

		} else if (type == 1) {
			List<Map<String, Object>> unshareList = new ArrayList<Map<String, Object>>();
			GoodsOverstockUserExample example = new GoodsOverstockUserExample();// TODO去掉info
			example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo((byte) 2);
			List<GoodsOverstockUser> goodsOverstockInfoUserList = goodsOverstockUserMapper.selectByExample(example);

			GoodsOverstockInfoUserExample example4 = new GoodsOverstockInfoUserExample();

			if (goodsOverstockInfoUserList != null && goodsOverstockInfoUserList.size() > 0) {

				for (GoodsOverstockUser goodsOverstockInfoUser : goodsOverstockInfoUserList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", goodsOverstockInfoUser.getId());
					map.put("name", goodsOverstockInfoUser.getName());
					// map.put("type", goodsOverstockInfoUser.getType());// 0核销
					example4.clear();
					example4.setOrderByClause("use_end_time asc");
					example4.createCriteria().andGoodsOverstockUserIdEqualTo(goodsOverstockInfoUser.getId())
							.andUseEndTimeGreaterThan(new Date()).andTypeEqualTo((byte) 0);
					List<GoodsOverstockInfoUser> infoUserList = goodsOverstockInfoUserMapper.selectByExample(example4);
					if (infoUserList != null && infoUserList.size() > 0) {
						GoodsOverstockInfoUser goodsOverstockInfoUser2 = infoUserList.get(0);
						map.put("time", goodsOverstockInfoUser2.getUseEndTime());
					} else {
						map.put("time", null);
					} // 1实物
					// map.put("time", goodsOverstockInfoUser.getUseEndTime());
					// GoodsOverstockUserWithBLOBs goodsOverstockUserWithBLOBs =
					// goodsOverstockUserMapper
					// .selectByPrimaryKey(goodsOverstockInfoUser.getGoodsOverstockUserId());
					map.put("stock", goodsOverstockInfoUser.getStock());
					map.put("rebate", goodsOverstockInfoUser.getRebate());
					unshareList.add(map);
				}
			}
			return JsonData.setSuccessMessage(unshareList);
		} else if (type == 2) {
			User user = userMapper.selectByPrimaryKey(userId);
			// 账户明细
			AccountDetail accountDetail = new AccountDetail();
			accountDetail.setAccumulativeMoney(user.getAccumulativeMoney());
			accountDetail.setMoney(user.getMoney());
			accountDetail.setTotalMoney(user.getAccumulativeMoney() + user.getMoney());
			List<AccountLogs> accountLogList = new ArrayList<AccountLogs>();

			UserMoneyExample example4 = new UserMoneyExample();
			example4.createCriteria().andUserIdEqualTo(userId);
			example4.setOrderByClause("create_time desc");
			List<UserMoney> list = userMoneyMapper.selectByExample(example4);
			if (list != null && list.size() > 0) {
				for (UserMoney userMoney : list) {
					AccountLogs accountLogs = new AccountLogs();
					Integer bizId = userMoney.getBizId();// 变更业务id
					byte type1 = userMoney.getType();// 变更类型
					StringBuilder sb = new StringBuilder();
					switch (type1) {
					case 0:// 奖励金 0:奖励金, 1:返利 ,2:提现,3:认筹分红
						GoodsOrderGoodsExample example5 = new GoodsOrderGoodsExample();
						example5.createCriteria().andOrderIdEqualTo(bizId);
						List<GoodsOrderGoods> list2 = goodsOrderGoodsMapper.selectByExample(example5);
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
						GoodsOrderGoodsExample example6 = new GoodsOrderGoodsExample();
						example6.createCriteria().andOrderIdEqualTo(bizId);
						List<GoodsOrderGoods> list4 = goodsOrderGoodsMapper.selectByExample(example6);
						if (list4 != null) {
							GoodsOrderGoods goodsOrderGoods = list4.get(0);
							accountLogs.setTitle("认筹分红");
							accountLogs.setName(goodsOrderGoods.getName());
							accountLogs.setTime(goodsOrderGoods.getCreateTime());
							accountLogs.setMoney(userMoney.getMoneyChange());
						}
						break;
					case 4:// 购买商品
						GoodsOrderGoodsExample example8 = new GoodsOrderGoodsExample();
						example8.createCriteria().andOrderIdEqualTo(bizId);
						List<GoodsOrderGoods> list6 = goodsOrderGoodsMapper.selectByExample(example8);
						if (list6 != null) {
							GoodsOrderGoods goodsOrderGoods = list6.get(0);
							accountLogs.setTitle("购买商品");
							accountLogs.setName(goodsOrderGoods.getName());
							accountLogs.setTime(goodsOrderGoods.getCreateTime());
							accountLogs.setMoney(userMoney.getMoneyChange());
						}

						GoodsOrderGoodsExample example7 = new GoodsOrderGoodsExample();
						example7.createCriteria().andOrderIdEqualTo(bizId);
						List<GoodsOrderGoods> list5 = goodsOrderGoodsMapper.selectByExample(example7);
						if (list5 != null) {
							GoodsOrderGoods goodsOrderGoods = list5.get(0);
							accountLogs.setTitle("售出奖励");
							accountLogs.setName(goodsOrderGoods.getName());
							accountLogs.setTime(goodsOrderGoods.getCreateTime());
							accountLogs.setMoney(userMoney.getMoneyChange());
						}
						break;

					case 5: // 提现失败
						UserWithdrawals userWithdrawalsFaile = userWithdrawalsMapper.selectByPrimaryKey(bizId);
						if (userWithdrawalsFaile != null) {
							accountLogs.setTitle("提现失败");
							String bankName = userWithdrawalsFaile.getBankName();
							String simpleAccount = userWithdrawalsFaile.getAccount()
									.substring(userWithdrawalsFaile.getAccount().length() - 4);
							sb.append(bankName).append("(").append(simpleAccount).append(")");
							accountLogs.setName(sb.toString());
							accountLogs.setTime(userWithdrawalsFaile.getCreateTime());
							accountLogs.setMoney(userMoney.getMoneyChange());
						}

						break;

					case 6:// 压货商品备货
						GoodsOverstockUserWithBLOBs overstockUserWithBLOBs = goodsOverstockUserMapper
								.selectByPrimaryKey(bizId);
						if (overstockUserWithBLOBs != null) {
							accountLogs.setTitle("压货商品备货");
							accountLogs.setMoney(userMoney.getMoneyChange());
							accountLogs.setTime(overstockUserWithBLOBs.getCreateTime());
						}

						break;
					case 7:// 压货商品退款
						accountLogs.setTitle("压货商品退款");
						accountLogs.setMoney(userMoney.getMoneyChange());
						accountLogs.setTime(userMoney.getCreateTime());

						break;

					}
					accountLogList.add(accountLogs);
				}
			}
			accountDetail.setAccountLogs(accountLogList);
			return JsonData.setSuccessMessage(accountDetail);
		}
		return JsonData.setErrorMessage("参数错误");
	}

	/**
	 * 商品分享记录
	 */
	public JsonData shareRecord(Integer userId, Integer category, Integer goodsId) {
		if (userId == null || category == null || goodsId == null) {
			return JsonData.setErrorMessage("参数错误");
		}

		GoodsShareRecord record = new GoodsShareRecord();
		record.setBizId(goodsId);
		record.setCategory(category);
		record.setUserId(userId);
		record.setCreateTime(new Date());
		shareRecordMapper.insert(record);

		return JsonData.setSuccessMessage();
	}

	/**
	 * 合卖账户
	 */
	public JsonData hemai(Integer userId) {
		if (userId == null) {
			return JsonData.setErrorMessage("参数错误");
		}

		User user = userMapper.selectByPrimaryKey(userId);
		if (user != null) {
			if (user.getCreditTotal() == 0) {
				return JsonData.setErrorMessage("用户未开通合卖权限");
			}
			Hemai hemai = new Hemai();
			hemai.setMoney(user.getCreditSurplus());
			hemai.setTotalMoney(user.getCreditTotal());
			List<Map<String, Object>> onsaleList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> hemailist = new ArrayList<Map<String, Object>>();
			GoodsUserExample example = new GoodsUserExample();
			example.createCriteria().andUserIdEqualTo(userId).andCategoryEqualTo(0).andStatusEqualTo((byte) 2);
			List<GoodsUser> goodsList = goodsUserMapper.selectByExample(example);
			if (goodsList != null && goodsList.size() > 0) {
				GoodsSubscriptionExample example2 = new GoodsSubscriptionExample();
				GoodsSubscriptionRecordExample example3 = new GoodsSubscriptionRecordExample();
				for (GoodsUser goodsUser : goodsList) {
					example2.clear();
					example2.createCriteria().andIdEqualTo(goodsUser.getBizId());
					List<GoodsSubscriptionWithBLOBs> goodsSubscriptionWithBLOBsList = subscriptionMapper
							.selectByExampleWithBLOBs(example2);
					if (goodsSubscriptionWithBLOBsList != null && goodsSubscriptionWithBLOBsList.size() > 0) {
						GoodsSubscriptionWithBLOBs goodsSubscriptionWithBLOBs = goodsSubscriptionWithBLOBsList.get(0);
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", goodsSubscriptionWithBLOBs.getId());
						map.put("name", goodsSubscriptionWithBLOBs.getName());
						map.put("img", goodsSubscriptionWithBLOBs.getImgs().split(";")[0]);
						map.put("price", goodsSubscriptionWithBLOBs.getPriceSubscription());
						example3.clear();
						example3.createCriteria().andUserIdEqualTo(userId)
								.andGoodsSubscriptionIdEqualTo(goodsSubscriptionWithBLOBs.getId());
						List<GoodsSubscriptionRecord> recordList = recordMapper.selectByExample(example3);
						Long totalMoney = 0L;
						if (recordList != null && recordList.size() > 0) {
							for (GoodsSubscriptionRecord goodsSubscriptionRecord : recordList) {
								totalMoney += goodsSubscriptionRecord.getPriceSubcriptionTotal();
							}
						}
						map.put("totalMoney", totalMoney);
						onsaleList.add(map);
					}
				}
			}
			hemai.setOnSaleList(onsaleList);
			GoodsSubscriptionRecordExample example4 = new GoodsSubscriptionRecordExample();
			example4.createCriteria().andUserIdEqualTo(userId);
			List<GoodsSubscriptionRecord> list3 = recordMapper.selectByExample(example4);
			if (list3 != null && list3.size() > 0) {
				Set<Integer> set = new HashSet<Integer>();
				for (GoodsSubscriptionRecord goodsSubscriptionRecord : list3) {
					set.add(goodsSubscriptionRecord.getGoodsSubscriptionId());
				}

				GoodsSubscriptionExample example5 = new GoodsSubscriptionExample();
				example5.createCriteria().andSellStatusEqualTo((byte) 1).andIdIn(new ArrayList(set));
				List<GoodsSubscriptionWithBLOBs> subList = subscriptionMapper.selectByExampleWithBLOBs(example5);
				if (subList != null && subList.size() > 0) {
					for (GoodsSubscriptionWithBLOBs goodsSubscription : subList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("name", goodsSubscription.getName());
						map.put("img", goodsSubscription.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
						map.put("id", goodsSubscription.getId());
						map.put("price", goodsSubscription.getPriceSubscription());
						long totalMoney = 0L;
						GoodsSubscriptionRecordExample example6 = new GoodsSubscriptionRecordExample();
						example6.createCriteria().andUserIdEqualTo(userId)
								.andGoodsSubscriptionIdEqualTo(goodsSubscription.getId());
						List<GoodsSubscriptionRecord> recordList = recordMapper.selectByExample(example6);
						if (recordList != null && recordList.size() > 0) {
							for (GoodsSubscriptionRecord goodsSubscriptionRecord : recordList) {
								totalMoney += goodsSubscriptionRecord.getPriceSubcriptionTotal();
							}
						}
						map.put("totalMoney", totalMoney);
						hemailist.add(map);
					}
				}
			}
			hemai.setHemaiList(hemailist);
			return JsonData.setSuccessMessage(hemai);
		} else {
			return JsonData.setErrorMessage("用户不存在");
		}
	}
}
