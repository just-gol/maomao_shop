package com.maomao.ssm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.maomao.ssm.bean.Goods;
import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsAddressExample;
import com.maomao.ssm.bean.GoodsBrand;
import com.maomao.ssm.bean.GoodsCollection;
import com.maomao.ssm.bean.GoodsCollectionExample;
import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.bean.GoodsExample.Criteria;
import com.maomao.ssm.bean.GoodsExampleCustom;
import com.maomao.ssm.bean.GoodsOverstockExample;
import com.maomao.ssm.bean.GoodsOverstockUserExample;
import com.maomao.ssm.bean.GoodsOverstockUserWithBLOBs;
import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.bean.GoodsServiceExample;
import com.maomao.ssm.bean.GoodsShareRecord;
import com.maomao.ssm.bean.GoodsSku;
import com.maomao.ssm.bean.GoodsSkuExample;
import com.maomao.ssm.bean.GoodsSubscriptionExample;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsUserExample;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.GoodsAddressMapper;
import com.maomao.ssm.dao.GoodsBrandMapper;
import com.maomao.ssm.dao.GoodsCollectionMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsMapperCustom;
import com.maomao.ssm.dao.GoodsOverstockMapper;
import com.maomao.ssm.dao.GoodsOverstockUserMapper;
import com.maomao.ssm.dao.GoodsServiceMapper;
import com.maomao.ssm.dao.GoodsShareRecordMapper;
import com.maomao.ssm.dao.GoodsSkuMapper;
import com.maomao.ssm.dao.GoodsSubscriptionMapper;
import com.maomao.ssm.dao.GoodsUserMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.list.SetList;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.search.GoodsDetails;
import com.maomao.ssm.pojo.search.GoodsDetailsSku;
import com.maomao.ssm.pojo.search.GoodsDetailsSkuDetails;
import com.maomao.ssm.pojo.search.GoodsServiceDetails;
import com.maomao.ssm.pojo.search.SearchBean;
import com.maomao.ssm.pojo.search.SearchDetailBean;
import com.maomao.ssm.pojo.search.SubscriptionGoodsDetails;
import com.maomao.ssm.service.GoodsService;
import com.maomao.ssm.utils.JsonUtils;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsMapperCustom goodsMapperCustom;
	@Autowired
	private GoodsBrandMapper goodsBrandMapper;
	@Autowired
	private JedisClientPool jedisClientPool;
	@Autowired
	private GoodsServiceMapper goodsServiceMapper;
	@Autowired
	private GoodsSubscriptionMapper goodsSubscriptionMapper;
	@Autowired
	private GoodsCollectionMapper goodsCollectionMapper;
	@Autowired
	private GoodsSkuMapper goodsSkuMapper;
	@Autowired
	private GoodsAddressMapper goodsAddressMapper;
	@Autowired
	private GoodsUserMapper goodsUserMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GoodsOverstockMapper goodsOverstockMapper;
	@Autowired
	private GoodsOverstockUserMapper goodsOverstockUserMapper;
	@Autowired
	private GoodsShareRecordMapper goodsShareRecordMapper;

	/**
	 * 根据分类Id查询商品信息
	 */
	public JsonData getGoodsByCategoryId(Integer type, Integer categoryId, Integer pages, Integer rows,
			Integer sortType, String brand, Long startPrice, Long endPrice) {
		if (categoryId == null || type == null) {
			return JsonData.setErrorMessage("参数非法");
		}

		// TODO 商家用户查询众筹商品表
		GoodsSubscriptionExample example = new GoodsSubscriptionExample();
		GoodsExample example1 = new GoodsExample();

		if (type.byteValue() == UserConts.USER_TYPE_B && categoryId == 0) {
			com.maomao.ssm.bean.GoodsSubscriptionExample.Criteria criteria = example.createCriteria();
			criteria.andSubscriptionStatusEqualTo(GoodsConst.GOODS_SUBSCRIPTION_STATUS_SUBSCRIBING);
			if (startPrice != null) {
				criteria.andPriceSalesGreaterThanOrEqualTo(startPrice);
			}
			if (endPrice != null) {
				criteria.andPriceSalesLessThanOrEqualTo(endPrice);
			}
			if (StringUtils.isNotBlank(brand)) {
				String[] split = brand.split(";");
				List<Integer> list1 = new ArrayList<Integer>();
				for (String string : split) {
					list1.add(Integer.parseInt(string));
				}
				criteria.andBrandIdIn(list1);
			}

		} else {

			Criteria criteria = example1.createCriteria();
			criteria.andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
					.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED)
					.andCategoryIdEqualTo(categoryId);
			if (startPrice != null) {
				criteria.andMinPriceGreaterThanOrEqualTo(startPrice);
			}
			if (endPrice != null) {
				criteria.andMinPriceLessThanOrEqualTo(endPrice);
			}
			if (StringUtils.isNotBlank(brand)) {
				String[] split = brand.split(";");
				List<Integer> list1 = new ArrayList<Integer>();
				for (String string : split) {
					list1.add(Integer.parseInt(string));
				}
				criteria.andBrandIdIn(list1);
			}
		}

		List<SearchBean> goodsList = new ArrayList<SearchBean>();

		Integer statPage = 1;
		if (pages >= 3) {
			statPage = pages;
		} else {
			rows = 20;
		}

		PageHelper.startPage(statPage, rows);
		example1.setOrderByClause("sort asc");
		if (sortType != null) {
			// 销量降序
			if (sortType == 1) {
				example.setOrderByClause("(sales+sales_sham) desc");
				example1.setOrderByClause("(sales+sales_sham) desc");
			}

			// 零售价升序
			if (sortType == 2 && categoryId == 0) {
				example.setOrderByClause("price_sales asc");
				example1.setOrderByClause("price_sales asc");
			}
			if (sortType == 2 && categoryId != 0) {
				example.setOrderByClause("min_price asc");
				example1.setOrderByClause("min_price asc");
			}
			// 零售价降序
			if (sortType == 3 && categoryId == 0) {
				example.setOrderByClause("price_sales desc");
				example1.setOrderByClause("price_sales desc");
			}
			if (sortType == 3 && categoryId != 0) {
				example.setOrderByClause("min_price desc");
				example1.setOrderByClause("min_price desc");
			}

			// 分红升序
			if (sortType == 4 && categoryId == 0) {
				example.setOrderByClause("bonus_avg asc");
			} else if (sortType == 4 && categoryId != 0) {
				example1.setOrderByClause("rebate asc");
			}
			// 分红降序
			if (sortType == 5 && categoryId == 0) {
				example.setOrderByClause("bonus_avg desc");
			} else if (sortType == 5 && categoryId != 0) {
				example1.setOrderByClause("rebate desc");
			}
		}
		if (type.byteValue() == UserConts.USER_TYPE_B && categoryId == 0) {
			List<GoodsSubscriptionWithBLOBs> list = goodsSubscriptionMapper.selectByExampleWithBLOBs(example);
			if (list != null && list.size() > 0) {
				for (GoodsSubscriptionWithBLOBs goods : list) {
					List<GoodsServiceDetails> goodsServiceList = new ArrayList<GoodsServiceDetails>();
					String serviceId = goods.getServiceId();
					if (StringUtils.isNotBlank(serviceId)) { // NullPointerException
						String[] splitId = serviceId.split(";");
						for (String id : splitId) {// TODO in
							GoodsServiceExample example3 = new GoodsServiceExample();
							example3.createCriteria().andIdEqualTo(Integer.parseInt(id)).andStatusEqualTo((byte) 1);
							List<com.maomao.ssm.bean.GoodsService> services = goodsServiceMapper
									.selectByExample(example3);
							if (services != null && services.size() > 0) {
								for (com.maomao.ssm.bean.GoodsService goodsService : services) {
									GoodsServiceDetails goodsServiceDetails = new GoodsServiceDetails();
									goodsServiceDetails.setName(goodsService.getName());
									goodsServiceDetails.setCreateTime(goodsService.getCrateTime());
									goodsServiceList.add(goodsServiceDetails);
								}
							}
						}
					}

					SearchBean searchBean = new SearchBean(goods, goodsServiceList);
					goodsList.add(searchBean);
				}
			} else {
				return JsonData.setSuccessMessage();
			}

		} else {

			List<GoodsWithBLOBs> list = goodsMapper.selectByExampleWithBLOBs(example1);
			if (list != null && list.size() > 0) {
				for (GoodsWithBLOBs goods : list) {
					List<GoodsServiceDetails> goodsServiceList = new ArrayList<GoodsServiceDetails>();
					String serviceId = goods.getServiceId();
					if (StringUtils.isNotBlank(serviceId)) { // NullPointerException
						String[] splitId = serviceId.split(";");
						for (String id : splitId) {// TODO in
							GoodsServiceExample example3 = new GoodsServiceExample();
							example3.createCriteria().andIdEqualTo(Integer.parseInt(id)).andStatusEqualTo((byte) 1);
							List<com.maomao.ssm.bean.GoodsService> services = goodsServiceMapper
									.selectByExample(example3);
							if (services != null && services.size() > 0) {
								for (com.maomao.ssm.bean.GoodsService goodsService : services) {
									GoodsServiceDetails goodsServiceDetails = new GoodsServiceDetails();
									goodsServiceDetails.setName(goodsService.getName());
									goodsServiceDetails.setCreateTime(goodsService.getCrateTime());
									goodsServiceList.add(goodsServiceDetails);
								}
							}
						}
					}

					SearchBean searchBean = new SearchBean(goods, goodsServiceList);
					goodsList.add(searchBean);
				}
			} else {
				return JsonData.setSuccessMessage();
			}
		}

		return JsonData.setSuccessMessage(goodsList);
	}

	/**
	 * 根据分类Id查询品牌
	 */
	public JsonData getGoodsBrands(Integer categoryId, String name) {
		if (categoryId == null && StringUtils.isBlank(name)) {
			return JsonData.setErrorMessage("参数非法");
		}
		GoodsExample example = new GoodsExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
				.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED);
		if (categoryId != null) {
			criteria.andCategoryIdEqualTo(categoryId);
		}
		if (!StringUtils.isBlank(name)) {
			criteria.andNameLike("%" + name + "%");
		}
		List<Goods> goodsList = goodsMapper.selectByExample(example);
		Set<Integer> set = new HashSet<Integer>();
		if (goodsList != null && goodsList.size() > 0) {
			for (Goods goods : goodsList) {
				Integer brandId = goods.getBrandId();
				set.add(brandId);
			}
		}
		List<GoodsBrand> returnList = new ArrayList<GoodsBrand>();
		if (set.size() > 0) {
			for (Integer id : set) {
				GoodsBrand goodsBrand = goodsBrandMapper.selectByPrimaryKey(id);
				returnList.add(goodsBrand);
			}
		}
		return JsonData.setSuccessMessage(returnList);
	}

	/**
	 * 商品搜索
	 */
	public JsonData goodsSearch(Integer type, String keywords, Integer pages, Integer rows, Integer sortType,
			String brand, Long startPrice, Long endPrice) {
		if (StringUtils.isBlank(keywords)) {
			return JsonData.setErrorMessage("参数非法");
		}
		GoodsExampleCustom example1 = new GoodsExampleCustom();
		GoodsExampleCustom.Criteria criteria = example1.createCriteria();
		criteria.andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
				.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED)
				.andMyCriterion("LOWER(name) LIKE ", "%" + keywords.toLowerCase() + "%", "name");
		if (startPrice != null) {
			criteria.andMinPriceGreaterThanOrEqualTo(startPrice);
		}
		if (endPrice != null) {
			criteria.andMinPriceLessThanOrEqualTo(endPrice);
		}
		if (StringUtils.isNotBlank(brand)) {
			String[] split = brand.split(";");
			List<Integer> list1 = new ArrayList<Integer>();
			for (String string : split) {
				list1.add(Integer.parseInt(string));
			}
			criteria.andBrandIdIn(list1);
		}

		Integer statPage = 1;
		if (pages >= 3) {
			statPage = pages;
		} else {
			rows = 20;
		}

		PageHelper.startPage(statPage, rows);
		if (sortType != null) {
			// 销量降序
			if (sortType == 1) {
				example1.setOrderByClause("(sales+sales_sham) desc");
			}

			// 零售价升序
			if (sortType == 2) {
				example1.setOrderByClause("min_price asc");
			}
			// 零售价降序
			if (sortType == 3) {
				example1.setOrderByClause("min_price desc");
			}

			// 分红升序
			if (sortType == 4) {
				example1.setOrderByClause("rebate asc");
			}
			// 分红降序
			if (sortType == 5) {
				example1.setOrderByClause("rebate desc");
			}
		}
		List<GoodsWithBLOBs> list = goodsMapperCustom.selectByExampleWithBLOBs(example1);
		List<SearchDetailBean> searchList = new ArrayList<SearchDetailBean>();
		if (list != null && list.size() > 0) {
			for (GoodsWithBLOBs goods : list) {
				List<GoodsServiceDetails> goodsServiceList = new ArrayList<GoodsServiceDetails>();
				String serviceId = goods.getServiceId();
				if (StringUtils.isNotBlank(serviceId)) { // NullPointerException
					String[] splitId = serviceId.split(";"); // TODO in
					for (String id : splitId) {
						GoodsServiceExample example = new GoodsServiceExample();
						example.createCriteria().andIdEqualTo(Integer.parseInt(id)).andStatusEqualTo((byte) 1);
						List<com.maomao.ssm.bean.GoodsService> services = goodsServiceMapper.selectByExample(example);
						if (services != null && services.size() > 0) {
							for (com.maomao.ssm.bean.GoodsService goodsService : services) {
								GoodsServiceDetails goodsServiceDetails = new GoodsServiceDetails();
								goodsServiceDetails.setName(goodsService.getName());
								goodsServiceDetails.setCreateTime(goodsService.getCrateTime());
								goodsServiceList.add(goodsServiceDetails);
							}
						}
					}
				}
				SearchDetailBean searchDetailBean = new SearchDetailBean(goods, goodsServiceList);
				searchList.add(searchDetailBean);
			}
		} else {
			return JsonData.setSuccessMessage();
		}
		return JsonData.setSuccessMessage(searchList);
	}

	/**
	 * 查询商品详情 categoryId: 0:合卖 2:压货商品 3:分享出去的压货商品 ,用户压货商品表
	 */
	public JsonData getGoodsDetails(Integer userId, Integer categoryId, Integer goodsId) {
		if (categoryId != null && categoryId == 0) {

			GoodsSubscriptionExample example = new GoodsSubscriptionExample();
			example.createCriteria().andIdEqualTo(goodsId);
			List<GoodsSubscriptionWithBLOBs> resultList = goodsSubscriptionMapper.selectByExampleWithBLOBs(example);
			if (resultList != null && resultList.size() > 0) {
				GoodsSubscriptionWithBLOBs goodsSub = resultList.get(0);
				if (!GoodsConst.GOODS_SUBSCRIPTION_STATUS_SUBSCRIBING.equals(goodsSub.getSubscriptionStatus())
						&& !GoodsConst.GOODS_SUBSCRIPTION_STATUS_ON_SALE.equals(goodsSub.getSubscriptionStatus())
						&& !GoodsConst.GOODS_SUBSCRIPTION_STATUS_FINISHED.equals(goodsSub.getSubscriptionStatus())) {
					return JsonData.setErrorMessage("合卖商品状态错误");
				}
				List<GoodsServiceDetails> goodsServiceList = new ArrayList<GoodsServiceDetails>();
				String serviceId = goodsSub.getServiceId();
				if (StringUtils.isNotBlank(serviceId)) { // NullPointerException
					String[] splitId = serviceId.split(";");
					for (String id : splitId) {
						GoodsServiceExample example5 = new GoodsServiceExample();
						example5.createCriteria().andIdEqualTo(Integer.parseInt(id)).andStatusEqualTo((byte) 1);
						List<com.maomao.ssm.bean.GoodsService> services = goodsServiceMapper.selectByExample(example5);
						if (services != null && services.size() > 0) {
							for (com.maomao.ssm.bean.GoodsService goodsService : services) {
								GoodsServiceDetails goodsServiceDetails = new GoodsServiceDetails();
								goodsServiceDetails.setName(goodsService.getName());
								goodsServiceDetails.setCreateTime(goodsService.getCrateTime());
								goodsServiceList.add(goodsServiceDetails);
							}
						}
					}
				}
				// 查询自取地址
				GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
				goodsAddressExample.createCriteria().andBizIdEqualTo(goodsId)
						.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION);
				List<GoodsAddress> goodsAddresses = goodsAddressMapper.selectByExample(goodsAddressExample);
				GoodsAddress goodsAddress = null;
				if (goodsAddresses != null && goodsAddresses.size() > 0) {
					goodsAddress = goodsAddresses.get(0);
				}
				SubscriptionGoodsDetails subGoodsDetails = new SubscriptionGoodsDetails(goodsSub, goodsServiceList,
						goodsAddress);

				// 判断有没有确认合卖的权限
				subGoodsDetails.setMoney(0);
				if (userId != null) {
					User user = userMapper.selectByPrimaryKey(userId);
					if (user != null && user.getCreditTotal() != null && user.getCreditTotal() > 0) {
						subGoodsDetails.setMoney(1);
					}
				}

				subGoodsDetails.setStatus(goodsSub.getSellStatus());
				JsonData jsonData = JsonData.setSuccessMessage(subGoodsDetails);
				return jsonData;
			} else {
				return JsonData.setErrorMessage("商品已下架");// TODO
			}
		} else if (categoryId == 2) {

			GoodsOverstockWithBLOBs goodsOverstock = goodsOverstockMapper.selectByPrimaryKey(goodsId);
			if (!goodsOverstock.getStatus().equals(GoodsConst.GOODS_OVERSTOCK_STATUS_ON_SALE)) {
				return JsonData.setServerErrorMessage("商品已下架");
			}
			List<GoodsServiceDetails> goodsServiceList = new ArrayList<GoodsServiceDetails>();
			if (StringUtils.isNotBlank(goodsOverstock.getServiceId())) {
				String[] serviceIds = goodsOverstock.getServiceId().split(StatusConst.IMG_SPLIT_STRING);
				List<Integer> serviceIdList = new ArrayList<Integer>();
				for (String id : serviceIds) {
					serviceIdList.add(Integer.parseInt(id));
				}
				GoodsServiceExample example3 = new GoodsServiceExample();
				example3.createCriteria().andStatusEqualTo((byte) 1).andIdIn(serviceIdList);
				List<com.maomao.ssm.bean.GoodsService> goodsServices = goodsServiceMapper
						.selectByExampleWithBLOBs(example3);
				if (goodsServices != null && goodsServices.size() > 0) {
					for (com.maomao.ssm.bean.GoodsService goodsService : goodsServices) {
						GoodsServiceDetails goodsServiceDetails = new GoodsServiceDetails();
						goodsServiceDetails.setCreateTime(goodsService.getCrateTime());
						goodsServiceDetails.setDetail(goodsService.getDetail());
						goodsServiceDetails.setName(goodsService.getName());
						goodsServiceList.add(goodsServiceDetails);
					}
				}
			}
			GoodsDetails goodsDetails = new GoodsDetails(goodsOverstock, goodsServiceList);
			JsonData jsonData = JsonData.setSuccessMessage(goodsDetails);
			return jsonData;
		} else if (categoryId == 3) {
			GoodsOverstockUserExample example = new GoodsOverstockUserExample();
			com.maomao.ssm.bean.GoodsOverstockUserExample.Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(goodsId);
			List<GoodsOverstockUserWithBLOBs> goodsOverstockUsers = goodsOverstockUserMapper
					.selectByExampleWithBLOBs(example);
			if (goodsOverstockUsers != null && goodsOverstockUsers.size() > 0) {
				GoodsOverstockUserWithBLOBs goodsOverstock = goodsOverstockUsers.get(0);
				List<GoodsServiceDetails> goodsServiceList = new ArrayList<GoodsServiceDetails>();
				if (StringUtils.isNotBlank(goodsOverstock.getServiceId())) {
					String[] serviceIds = goodsOverstock.getServiceId().split(StatusConst.IMG_SPLIT_STRING);
					List<Integer> serviceIdList = new ArrayList<Integer>();
					for (String id : serviceIds) {
						serviceIdList.add(Integer.parseInt(id));
					}
					GoodsServiceExample example3 = new GoodsServiceExample();
					example3.createCriteria().andStatusEqualTo((byte) 1).andIdIn(serviceIdList);
					List<com.maomao.ssm.bean.GoodsService> goodsServices = goodsServiceMapper
							.selectByExampleWithBLOBs(example3);
					if (goodsServices != null && goodsServices.size() > 0) {
						for (com.maomao.ssm.bean.GoodsService goodsService : goodsServices) {
							GoodsServiceDetails goodsServiceDetails = new GoodsServiceDetails();
							goodsServiceDetails.setCreateTime(goodsService.getCrateTime());
							goodsServiceDetails.setDetail(goodsService.getDetail());
							goodsServiceDetails.setName(goodsService.getName());
							goodsServiceList.add(goodsServiceDetails);
						}
					}
				}
				GoodsDetails goodsDetails = new GoodsDetails(goodsOverstock, goodsServiceList);
				JsonData jsonData = JsonData.setSuccessMessage(goodsDetails);
				return jsonData;

			}
		} else {
			GoodsExample example = new GoodsExample();
			example.createCriteria().andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
					.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED).andIdEqualTo(goodsId);
			List<GoodsWithBLOBs> resultList = goodsMapper.selectByExampleWithBLOBs(example);

			if (resultList != null && resultList.size() > 0) {
				GoodsWithBLOBs goodsWithBLOBs = resultList.get(0);
				List<GoodsServiceDetails> goodsServiceList = new ArrayList<GoodsServiceDetails>();
				if (StringUtils.isNotBlank(goodsWithBLOBs.getServiceId())) {
					String[] serviceIds = goodsWithBLOBs.getServiceId().split(StatusConst.IMG_SPLIT_STRING);
					List<Integer> serviceIdList = new ArrayList<Integer>();
					for (String id : serviceIds) {
						serviceIdList.add(Integer.parseInt(id));
					}
					GoodsServiceExample example2 = new GoodsServiceExample();
					example2.createCriteria().andStatusEqualTo((byte) 1).andIdIn(serviceIdList);
					List<com.maomao.ssm.bean.GoodsService> goodsServices = goodsServiceMapper
							.selectByExampleWithBLOBs(example2);
					if (goodsServices != null && goodsServices.size() > 0) {
						for (com.maomao.ssm.bean.GoodsService goodsService : goodsServices) {
							GoodsServiceDetails goodsServiceDetails = new GoodsServiceDetails();
							goodsServiceDetails.setCreateTime(goodsService.getCrateTime());
							goodsServiceDetails.setDetail(goodsService.getDetail());
							goodsServiceDetails.setName(goodsService.getName());
							goodsServiceList.add(goodsServiceDetails);
						}
					}
				}
				// 获取商品规格 by hhd
				GoodsSkuExample goodsSkuExample = new GoodsSkuExample();
				goodsSkuExample.createCriteria().andGoodsIdEqualTo(goodsId)
						.andStatusEqualTo(GoodsConst.GOODS_SKU_STATUS_NORMAL);
				List<GoodsSku> goodsSkus = goodsSkuMapper.selectByExample(goodsSkuExample);
				if (goodsSkus == null || goodsSkus.size() == 0) {
					return JsonData.setErrorMessage("商品规格获取失败，请刷新重试");
				}
				// 构造sku返回值
				List<GoodsDetailsSku> sku = new ArrayList<GoodsDetailsSku>();
				List<GoodsDetailsSkuDetails> skuDetail = new ArrayList<GoodsDetailsSkuDetails>();
				int size = JsonUtils.jsonToPojo(goodsSkus.get(0).getSku(), Map.class).size();
				for (int i = 0; i < size; i++) {
					sku.add(new GoodsDetailsSku());
				}
				for (GoodsSku goodsSku : goodsSkus) {
					Map<String, String> skuItem = JsonUtils.jsonToPojo(goodsSku.getSku(), Map.class);
					int i = 0;
					for (String key : skuItem.keySet()) {
						sku.get(i).setKeyId(i);
						sku.get(i).setName(key);
						if (sku.get(i).getValues() == null) {
							sku.get(i).setValues(new SetList<String>());
						}
						sku.get(i).getValues().add(skuItem.get(key));
						i++;
					}
				}
				for (GoodsSku goodsSku : goodsSkus) {
					Map<String, String> skuItem = JsonUtils.jsonToPojo(goodsSku.getSku(), Map.class);
					int i = 0;
					String prop = "";
					for (String key : skuItem.keySet()) {
						List<String> values = sku.get(i).getValues();
						for (int j = 0; j < values.size(); j++) {
							if (values.get(j).equals(skuItem.get(key))) {
								prop += sku.get(i).getKeyId() + ":" + j + ";";
							}
						}
						i++;
					}
					prop = prop.substring(0, prop.length() - 1);
					skuDetail.add(new GoodsDetailsSkuDetails(prop, goodsSku));
				}
				// 查询自取地址
				GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
				goodsAddressExample.createCriteria().andBizIdEqualTo(goodsId)
						.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
				List<GoodsAddress> goodsAddresses = goodsAddressMapper.selectByExample(goodsAddressExample);
				GoodsAddress goodsAddress = null;
				if (goodsAddresses != null && goodsAddresses.size() > 0) {
					goodsAddress = goodsAddresses.get(0);
				}
				GoodsDetails goodsDetails = new GoodsDetails(goodsWithBLOBs, goodsServiceList, sku, skuDetail,
						goodsAddress);
				// 判断是否加入过店铺
				goodsDetails.setAddShop(0);
				if (userId != null) {
					GoodsUserExample goodsUserExample = new GoodsUserExample();
					goodsUserExample.createCriteria().andUserIdEqualTo(userId)
							.andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
							.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL).andBizIdEqualTo(goodsId);// 上架状态
					int count = goodsUserMapper.countByExample(goodsUserExample);
					if (count > 0) {
						goodsDetails.setAddShop(1);
					}
				}

				// 判断用户是否收藏过该商品
				goodsDetails.setFlag(0);
				if (userId != null) {
					GoodsCollectionExample example3 = new GoodsCollectionExample();
					example3.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId);
					List<GoodsCollection> collections = goodsCollectionMapper.selectByExample(example3);
					if (collections != null && collections.size() > 0) {
						goodsDetails.setFlag(1);
					}
				}
				JsonData jsonData = JsonData.setSuccessMessage(goodsDetails);
				return jsonData;

			} else {
				return JsonData.setErrorMessage("商品已下架");
			}
		}
		return JsonData.setErrorMessage("参数错误");
	}

	/**
	 * 商品收藏/取消收藏
	 */
	public JsonData addGoodsCollection(Integer userId, Integer goodsId, Integer type) {
		if (userId == null || goodsId == null || type == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		if (jedisClientPool
				.exists(RedisConst.GOODS_NORMAL_DETAIL + userId + RedisConst.REDIS_KEY_SPLIT_STRING + goodsId)) {
			jedisClientPool.del(RedisConst.GOODS_NORMAL_DETAIL + userId + RedisConst.REDIS_KEY_SPLIT_STRING + goodsId);
		}
		if (type == 1) {// 添加收藏
			try {
				GoodsCollectionExample example = new GoodsCollectionExample();
				example.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId);
				List<GoodsCollection> userCollections = goodsCollectionMapper.selectByExample(example);
				if (userCollections == null || userCollections.size() == 0) {
					GoodsCollection collection = new GoodsCollection();
					collection.setCreateTime(new Date());
					collection.setGoodsId(goodsId);
					collection.setUserId(userId);
					goodsCollectionMapper.insert(collection);
					return JsonData.setSuccessMessage();
				} else {
					return JsonData.setErrorMessage("您已经收藏过该商品");
				}
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setServerErrorMessage("加入收藏失败");
			}
		}
		if (type == 0) {// 取消收藏
			try {
				GoodsCollectionExample example = new GoodsCollectionExample();
				example.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId);
				goodsCollectionMapper.deleteByExample(example);
				return JsonData.setSuccessMessage();
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("取消收藏失败");// TODO 日志
			}
		}
		return JsonData.setErrorMessage("参数非法");
	}

	/**
	 * 添加用户分享记录
	 */
	@Override
	public JsonData addGoodsShareRecord(Integer userId, Integer goodsId, Integer category) {
		if (userId == null || goodsId == null || category == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		GoodsShareRecord goodsShareRecord = new GoodsShareRecord();
		goodsShareRecord.setUserId(userId);
		goodsShareRecord.setBizId(goodsId);
		goodsShareRecord.setCategory(category);
		goodsShareRecord.setCreateTime(new Date());
		goodsShareRecordMapper.insert(goodsShareRecord);
		return null;
	}
}
