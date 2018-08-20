package com.maomao.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maomao.ssm.bean.Banner;
import com.maomao.ssm.bean.BannerExample;
import com.maomao.ssm.bean.GoodsHomepageCategory;
import com.maomao.ssm.bean.GoodsHomepageCategoryExample;
import com.maomao.ssm.bean.HomeCategory;
import com.maomao.ssm.bean.HomeCategoryExample;
import com.maomao.ssm.bean.HomeContent;
import com.maomao.ssm.bean.HomeContentExample;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.BannerMapper;
import com.maomao.ssm.dao.GoodsHomepageCategoryMapper;
import com.maomao.ssm.dao.HomeCategoryMapper;
import com.maomao.ssm.dao.HomeContentMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.home.CategoryDetail;
import com.maomao.ssm.pojo.home.HomeBannerDetail;
import com.maomao.ssm.pojo.home.HomeDetails;
import com.maomao.ssm.pojo.home.HomeRecommendGoods;
import com.maomao.ssm.pojo.home.RecommendDetail;
import com.maomao.ssm.service.HomeService;
import com.maomao.ssm.utils.JsonUtils;

/**
 * @author:wzy
 * @descrption:获取首页内容
 * @version:
 */
@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private BannerMapper bannerMapper;
	@Autowired
	private GoodsHomepageCategoryMapper goodsHomepageCategoryMapper;
	@Autowired
	private HomeCategoryMapper homeCategoryMapper;
	@Autowired
	private HomeContentMapper homeContentMapper;
	@Autowired
	private JedisClientPool jedisClientPool;
	
	/**
	 * 查询首页详情
	 */
	public JsonData getHomeDetails(String ua) {
		// 查询缓存
		if (jedisClientPool.exists(RedisConst.HOME_DETAIL + ua)) {
			String json = jedisClientPool.get(RedisConst.HOME_DETAIL + ua);
			JsonData reuslt = JsonUtils.jsonToPojo(json, JsonData.class);
			return reuslt;
		}

		HomeDetails homeDetails = new HomeDetails();// 首页详情
		List<HomeBannerDetail> homeBannerList = new ArrayList<HomeBannerDetail>();// 首页banner
		List<CategoryDetail> homeCategoryList = new ArrayList<CategoryDetail>();// 首页分类
		List<RecommendDetail> homeRecommendList = new ArrayList<RecommendDetail>();// 首页推荐位内容
		
		try {
			// 查询banner
			BannerExample example = new BannerExample();
			example.createCriteria().andStatusEqualTo(StatusConst.BANNER_STATUS_ON)
					.andTypeEqualTo(StatusConst.BANNER_STATUS_HOME);// TODO 常量
			example.setOrderByClause("sort DESC");
			List<Banner> bannerList = bannerMapper.selectByExample(example);
			if (bannerList != null && bannerList.size() > 0) {
				for (Banner banner : bannerList) {
					HomeBannerDetail bannerDetail = new HomeBannerDetail(banner, ua);
					homeBannerList.add(bannerDetail);
				}
			}
			homeDetails.setHomeBannerList(homeBannerList);

			// 查询首页分类
			GoodsHomepageCategoryExample example1 = new GoodsHomepageCategoryExample();
			example1.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON);
			example1.setOrderByClause("sort asc");

			List<GoodsHomepageCategory> goodsHomepageCategoryList = goodsHomepageCategoryMapper
					.selectByExample(example1);
			if (goodsHomepageCategoryList != null && goodsHomepageCategoryList.size() > 0) {

				for (GoodsHomepageCategory goodsHomepageCategory : goodsHomepageCategoryList) {
					CategoryDetail categoryDetail = new CategoryDetail(goodsHomepageCategory);
					homeCategoryList.add(categoryDetail);
				}
			}
			homeDetails.setHomeCategoryList(homeCategoryList);

			// 查询推荐内容
			HomeCategoryExample example3 = new HomeCategoryExample();
			example3.createCriteria().andStatusEqualTo(StatusConst.CATEGORY_STATUS_ON).andImgTypeLessThan((byte)3);
			example3.setOrderByClause("sort desc");
			List<HomeCategory> homeCategories = homeCategoryMapper.selectByExample(example3);
			if (homeCategories != null && homeCategories.size() > 0) {
				for (HomeCategory homeCategory : homeCategories) {
					Integer categoryId = homeCategory.getId();
					List<HomeRecommendGoods> recommendGoodsList = new ArrayList<HomeRecommendGoods>();
					HomeContentExample example2 = new HomeContentExample();
					example2.createCriteria().andCategoryIdEqualTo(categoryId)
							.andStatusEqualTo(StatusConst.HOME_CONTENT_STATUS_ON);
					List<HomeContent> homeContentList = homeContentMapper.selectByExample(example2);
					for (HomeContent homeContent : homeContentList) {
						HomeRecommendGoods recommendGoods = new HomeRecommendGoods(homeContent);
						recommendGoodsList.add(recommendGoods);
					}
					RecommendDetail recommendDetail = new RecommendDetail(homeCategory, recommendGoodsList);

					homeRecommendList.add(recommendDetail);
				}
			}
			homeDetails.setHomeRecommendList(homeRecommendList);
			JsonData result = JsonData.setSuccessMessage(homeDetails);
			try {
				String json = JsonUtils.objectToJson(result);
				jedisClientPool.set(RedisConst.HOME_DETAIL + ua, json);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JsonData.setSuccessMessage(homeDetails);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("服务器异常");
		}
	}

	public JsonData getAd(Integer id, String modifiedTime, String ua) {
		// 查询缓存
		if (jedisClientPool.exists(RedisConst.AD_BANNER + ua)) {
			String json = jedisClientPool.get(RedisConst.AD_BANNER + ua);
			JsonData jsonData = JsonUtils.jsonToPojo(json, JsonData.class);
			if (modifiedTime != null) {
				String time = jedisClientPool.get(RedisConst.AD_BANNER + id);
				if (modifiedTime.equals(time)) {
					return JsonData.setErrorMessage("图片已经是最新");
				}
			}
			return jsonData;
		}

		BannerExample example = new BannerExample();
		example.createCriteria().andTypeEqualTo(StatusConst.BANNER_STATUS_START)
				.andStatusEqualTo(StatusConst.BANNER_STATUS_ON);
		List<Banner> banners = bannerMapper.selectByExample(example);
		HomeBannerDetail ad = null;
		if (banners != null && banners.size() > 0) {
			for (Banner banner : banners) {
				ad = new HomeBannerDetail();
				ad.setId(banner.getId());
				ad.setHref(banner.getHref());
				if ("ios".equals(ua)) {
					ad.setImg(banner.getImg().split(StatusConst.IMG_SPLIT_STRING)[1]);
				} else {
					ad.setImg(banner.getImg().split(StatusConst.IMG_SPLIT_STRING)[0]);
				}
				ad.setCreateTime(banner.getCreateTime());
				JsonData jsonData = JsonData.setSuccessMessage(ad);
				String json = JsonUtils.objectToJson(jsonData);
				try {
					jedisClientPool.set(RedisConst.AD_BANNER + ua, json);// 修改图片信息 ,删除缓存
					jedisClientPool.set(RedisConst.AD_BANNER + id, banner.getModifiedTime().getTime() + "");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return jsonData;
			}
		}
		return JsonData.setSuccessMessage(ad);
	}
}
