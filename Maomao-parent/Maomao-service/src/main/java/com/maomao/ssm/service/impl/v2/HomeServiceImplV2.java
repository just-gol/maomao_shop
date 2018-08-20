package com.maomao.ssm.service.impl.v2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.maomao.ssm.bean.Banner;
import com.maomao.ssm.bean.BannerExample;
import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.bean.GoodsHome;
import com.maomao.ssm.bean.GoodsHomeCustom;
import com.maomao.ssm.bean.GoodsHomeExample;
import com.maomao.ssm.bean.GoodsHomeExample.Criteria;
import com.maomao.ssm.bean.GoodsHomepageCategory;
import com.maomao.ssm.bean.GoodsHomepageCategoryExample;
import com.maomao.ssm.bean.GoodsOverstockExample;
import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.bean.GoodsService;
import com.maomao.ssm.bean.GoodsServiceExample;
import com.maomao.ssm.bean.GoodsSubscriptionExample;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.bean.HomeCategory;
import com.maomao.ssm.bean.HomeCategoryExample;
import com.maomao.ssm.bean.HomeContent;
import com.maomao.ssm.bean.HomeContentExample;
import com.maomao.ssm.bean.UserMoney;
import com.maomao.ssm.bean.UserMoneyExample;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.BannerMapper;
import com.maomao.ssm.dao.GoodsHomeMapper;
import com.maomao.ssm.dao.GoodsHomeMapperCustom;
import com.maomao.ssm.dao.GoodsHomepageCategoryMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsOverstockMapper;
import com.maomao.ssm.dao.GoodsServiceMapper;
import com.maomao.ssm.dao.GoodsSubscriptionMapper;
import com.maomao.ssm.dao.HomeCategoryMapper;
import com.maomao.ssm.dao.HomeContentMapper;
import com.maomao.ssm.dao.UserMoneyMapper;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.pojo.search.GoodsServiceDetails;
import com.maomao.ssm.pojo.search.SearchBean;
import com.maomao.ssm.pojo.v2.Home;
import com.maomao.ssm.service.v2.HomeServiceV2;
import com.maomao.ssm.utils.ListUtils;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年7月11日
 */
@Service
public class HomeServiceImplV2 implements HomeServiceV2 {
	@Autowired
	private UserMoneyMapper userMoneyMapper;
	@Autowired
	private BannerMapper bannerMapper;
	@Autowired
	private HomeCategoryMapper homeCategoryMapper;
	@Autowired
	private HomeContentMapper homeContentMapper;
	@Autowired
	private GoodsHomepageCategoryMapper goodsHomepageCategoryMapper;
	@Autowired
	private GoodsHomeMapper goodsHomeMapper;
	@Autowired
	private GoodsSubscriptionMapper goodsSubscriptionMapper;
	@Autowired
	private GoodsServiceMapper goodsServiceMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsOverstockMapper goodsOverstockMapper;
	@Autowired
	private GoodsHomeMapperCustom goodsHomeMapperCustom;

	/**
	 * 首页详情
	 */
	public JsonData homeDetail(Integer userId, String ua) {

		Home home = new Home();

		// 收益
		long money = 0L;
		if (userId != null) {
			UserMoneyExample example = new UserMoneyExample();
			Calendar todayStart = Calendar.getInstance();
			todayStart.set(Calendar.HOUR_OF_DAY, 0);
			todayStart.set(Calendar.MINUTE, 0);
			todayStart.set(Calendar.SECOND, 0);
			todayStart.set(Calendar.MILLISECOND, 0);
			Date start = todayStart.getTime();

			Calendar todayEnd = Calendar.getInstance();
			todayEnd.set(Calendar.HOUR_OF_DAY, 23);
			todayEnd.set(Calendar.MINUTE, 59);
			todayEnd.set(Calendar.SECOND, 59);
			todayEnd.set(Calendar.MILLISECOND, 999);
			Date end = todayEnd.getTime();

			example.createCriteria().andUserIdEqualTo(userId).andMoneyChangeGreaterThan(0L)
					.andCreateTimeGreaterThan(start).andCreateTimeLessThan(end);
			List<UserMoney> userMoneyList = userMoneyMapper.selectByExample(example);
			if (userMoneyList != null && userMoneyList.size() > 0)
				for (UserMoney userMoney : userMoneyList)
					money += userMoney.getMoneyChange();
		}

		home.setMoney(money);

		// 首页分类
		List<Map<String, Object>> categoryList = new ArrayList<Map<String, Object>>();
		GoodsHomepageCategoryExample example6 = new GoodsHomepageCategoryExample();
		example6.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON);
		example6.setOrderByClause("sort asc");

		List<GoodsHomepageCategory> goodsHomepageCategoryList = goodsHomepageCategoryMapper.selectByExample(example6);
		if (goodsHomepageCategoryList != null && goodsHomepageCategoryList.size() > 0)
			for (GoodsHomepageCategory goodsHomepageCategory : goodsHomepageCategoryList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", goodsHomepageCategory.getId());
				map.put("name", goodsHomepageCategory.getName());
				map.put("img", goodsHomepageCategory.getImg());
				categoryList.add(map);
			}
		home.setCategoryList(categoryList);

		// 查询banner
		BannerExample example1 = new BannerExample();
		example1.createCriteria().andStatusEqualTo(StatusConst.BANNER_STATUS_ON)
				.andTypeEqualTo(StatusConst.BANNER_STATUS_HOME);
		example1.setOrderByClause("sort DESC");
		List<Banner> bannerList = bannerMapper.selectByExample(example1);
		if (bannerList != null && bannerList.size() > 0) {
			Banner b = bannerList.get(0);
			Map<String, Object> map = new HashMap<String, Object>();
			String img = null;
			if ("ios".equals(ua))
				img = b.getImg().split(StatusConst.IMG_SPLIT_STRING)[1];
			else
				img = b.getImg().split(StatusConst.IMG_SPLIT_STRING)[0];
			map.put("url", b.getHref());
			map.put("img", img);
			map.put("id", b.getId());
			home.setBanner(map);
		}

		// 推荐商品
		List<Map<String, Object>> recomendList = new ArrayList<Map<String, Object>>();

		HomeCategoryExample example3 = new HomeCategoryExample();
		example3.createCriteria().andStatusEqualTo((byte) 1).andImgTypeEqualTo((byte) 4);
		List<HomeCategory> homeCategories = homeCategoryMapper.selectByExample(example3);
		if (homeCategories != null && homeCategories.size() > 0) {
			Integer categoryId = homeCategories.get(0).getId();
			HomeContentExample example4 = new HomeContentExample();
			example4.createCriteria().andCategoryIdEqualTo(categoryId).andStatusEqualTo((byte) 1);
			List<HomeContent> homeContents = homeContentMapper.selectByExample(example4);
			if (homeContents != null && homeContents.size() > 0)
				for (HomeContent homeContent : homeContents) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", homeContent.getId());
					map.put("reword", homeContent.getReword());
					map.put("name", homeContent.getName());
					if (homeContent.getUrl().contains("goodsDetail")) {// 普通1 买断-1
						map.put("category", 1);
					} else {
						map.put("category", -1);
					}
					map.put("url", homeContent.getUrl());
					map.put("img", homeContent.getImg());
					map.put("price", homeContent.getPrice());
					recomendList.add(map);
				}
		}

		home.setRecommend(recomendList);

		return JsonData.setSuccessMessage(home);
	}

	/**
	 * 首页爆款
	 */
	public JsonData baoKuan(Integer pages, Integer rows) {
		List<Map<String, Object>> returList = new ArrayList<Map<String, Object>>();

		HomeCategoryExample example3 = new HomeCategoryExample();
		example3.createCriteria().andStatusEqualTo((byte) 1).andImgTypeEqualTo((byte) 3);
		List<HomeCategory> homeCategories = homeCategoryMapper.selectByExample(example3);
		if (homeCategories != null && homeCategories.size() > 0) {
			Integer categoryId = homeCategories.get(0).getId();
			HomeContentExample example4 = new HomeContentExample();
			example4.createCriteria().andCategoryIdEqualTo(categoryId).andStatusEqualTo((byte) 1);
			Page page = PageHelper.startPage(pages, rows);

			List<HomeContent> homeContents = homeContentMapper.selectByExample(example4);
			if (homeContents != null && homeContents.size() > 0)
				for (HomeContent homeContent : homeContents) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", homeContent.getId());
					map.put("name", homeContent.getName());
					map.put("reword", homeContent.getReword());
					map.put("introduction", homeContent.getIntroduction());
					map.put("name", homeContent.getName());
					if (homeContent.getUrl().contains("goodsDetail")) {// 普通1 买断-1
						map.put("category", 1);
					} else {
						map.put("category", -1);
					}
					map.put("url", homeContent.getUrl());
					map.put("img", homeContent.getImg().split(StatusConst.IMG_SPLIT_STRING)[0]);
					map.put("price", homeContent.getPrice());
					returList.add(map);
				}
			PageBean pageBean = new PageBean();
			pageBean.setTotal(page.getTotal());
			pageBean.setRows(returList);
			return JsonData.setSuccessMessage(pageBean);
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 首页分类详情 4:综合排序升序 2:分红升序 3:分红降序
	 */
	public JsonData homeCategoryDetail(Integer pages, Integer rows, Integer categoryId, Integer sortType) {
		if (categoryId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		// 4:综合排序降序 5:综合排序升序 2:分红升序 3:分红降序
		if (sortType == null) {
			sortType = 5;
		}
		// 普通商品
		List<GoodsHomeCustom> goodsHomeCustomGoodz = goodsHomeMapperCustom.getGoodsHomeCustomWithGoods(categoryId, 1);
		// 压货商品
		List<GoodsHomeCustom> goodsHomeCustomGoodsOverstocks = goodsHomeMapperCustom
				.getGoodsHomeCustomWithGoods(categoryId, 2);
		List<SearchBean> searchBeans = new ArrayList<SearchBean>();
		for (GoodsHomeCustom goodsHomeCustomGoods : goodsHomeCustomGoodz) {
			searchBeans.add(new SearchBean(goodsHomeCustomGoods));
		}
		for (GoodsHomeCustom goodsHomeCustomGoodsOverstock : goodsHomeCustomGoodsOverstocks) {
			searchBeans.add(new SearchBean(goodsHomeCustomGoodsOverstock));
		}
		// 排序
		if (sortType == 2) {
			Collections.sort(searchBeans, new Comparator<SearchBean>() {
				public int compare(SearchBean s1, SearchBean s2) {
					return s1.getRebate().compareTo(s2.getRebate());
				}
			});
		}
		if (sortType == 3) {
			Collections.sort(searchBeans, new Comparator<SearchBean>() {
				public int compare(SearchBean s1, SearchBean s2) {
					return s2.getRebate().compareTo(s1.getRebate());
				}
			});
		}
		if (sortType == 5) {
			Collections.sort(searchBeans, new Comparator<SearchBean>() {
				public int compare(SearchBean s1, SearchBean s2) {
					return s1.getSort().compareTo(s2.getSort());
				}
			});
		}
		// 分页
		List<SearchBean> resultList = new ArrayList<SearchBean>();
		if (searchBeans != null && searchBeans.size() > 0) {
			Integer total = searchBeans.size();// 总记录数 Integer startIndex =
			Integer startIndex = (pages - 1) * rows;// (pages - 1) * rows;// 起始索引
			resultList = new ArrayList<SearchBean>(
					searchBeans.subList(startIndex, pages * rows > total ? total : (startIndex + rows)));
		}
		// 查询服务
		for (SearchBean searchBean : resultList) {
			if (StringUtils.isEmpty(searchBean.getServiceId())) {
				continue;
			}
			GoodsServiceExample goodsServiceExample = new GoodsServiceExample();
			goodsServiceExample.createCriteria()
					.andIdIn(
							ListUtils.getIntegerListFromString(searchBean.getServiceId(), StatusConst.IMG_SPLIT_STRING))
					.andStatusEqualTo(StatusConst.GOODS_SERVICE_STATUS_ON);
			List<GoodsService> goodsServices = goodsServiceMapper.selectByExample(goodsServiceExample);
			List<GoodsServiceDetails> goodsServiceDetails = new ArrayList<GoodsServiceDetails>();
			for (GoodsService goodsService : goodsServices) {
				goodsServiceDetails.add(new GoodsServiceDetails(goodsService));
			}
			searchBean.setGoodsService(goodsServiceDetails);
		}
		return JsonData.setSuccessMessage(resultList);
	}

	/**
	 * 商品分享 0合卖,1普通,2买断
	 */
	public JsonData share(Integer goodsId, Integer category, Integer userId) {
		if (goodsId == null || category == null)
			return JsonData.setErrorMessage("参数错误");
		Map<String, Object> result = new HashMap<String, Object>();
		boolean flag = false;
		if (category == 0) {
			GoodsSubscriptionExample example = new GoodsSubscriptionExample();
			example.createCriteria().andSubscriptionStatusEqualTo((byte) 1).andIdEqualTo(goodsId);
			List<GoodsSubscriptionWithBLOBs> goodsList = goodsSubscriptionMapper.selectByExampleWithBLOBs(example);
			if (goodsList != null && goodsList.size() > 0) {
				GoodsSubscriptionWithBLOBs goodsSubscription = goodsList.get(0);
				result.put("price", goodsSubscription.getPriceSales());
				result.put("name", goodsSubscription.getName());
				result.put("img", goodsSubscription.getImgs().split(";")[0]);
				result.put("url", "http://app.mao-mall.com/#/?goodsId=" + goodsId + "&category=0&userId=" + userId);
			} else
				flag = true;
		} else if (category == 1) {
			GoodsExample example = new GoodsExample();
			example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE).andIdEqualTo(goodsId);
			List<GoodsWithBLOBs> goodsList = goodsMapper.selectByExampleWithBLOBs(example);
			if (goodsList != null && goodsList.size() > 0) {
				GoodsWithBLOBs goodsWithBLOBs = goodsList.get(0);
				result.put("price", goodsWithBLOBs.getPriceSales());
				result.put("name", goodsWithBLOBs.getName());
				result.put("img", goodsWithBLOBs.getImgs().split(";")[0]);
				result.put("url", "http://app.mao-mall.com/#/?goodsId=" + goodsId + "&category=1&userId=" + userId);
			} else {
				flag = true;
			}
		} else {
			GoodsOverstockExample example = new GoodsOverstockExample();
			example.createCriteria().andIdEqualTo(goodsId);
			List<GoodsOverstockWithBLOBs> goodsList = goodsOverstockMapper.selectByExampleWithBLOBs(example);
			if (goodsList != null && goodsList.size() > 0 && goodsList.get(0).getStatus() == 2) {
				GoodsOverstockWithBLOBs goodsOverstock = goodsList.get(0);
				result.put("price", goodsOverstock.getPrice());
				result.put("name", goodsOverstock.getName());
				result.put("img", goodsOverstock.getImgs().split(";")[0]);
				result.put("url", "http://app.mao-mall.com/#/?goodsId=" + goodsId + "&category=2&userId=" + userId);
			} else
				flag = true;
		}
		if (flag) {
			return JsonData.setErrorMessage("商品已下架");
		}
		return JsonData.setSuccessMessage(result);
	}
}
