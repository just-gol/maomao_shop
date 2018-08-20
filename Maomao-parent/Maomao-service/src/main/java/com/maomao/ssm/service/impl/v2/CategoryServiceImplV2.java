package com.maomao.ssm.service.impl.v2;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.maomao.ssm.bean.Banner;
import com.maomao.ssm.bean.BannerExample;
import com.maomao.ssm.bean.CategoryHomeArticle;
import com.maomao.ssm.bean.CategoryHomeArticleExample;
import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.bean.GoodsCategoryExample;
import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.bean.GoodsExample.Criteria;
import com.maomao.ssm.bean.GoodsOverstockExample;
import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.bean.GoodsServiceExample;
import com.maomao.ssm.bean.GoodsSubscriptionExample;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.BannerMapper;
import com.maomao.ssm.dao.CategoryHomeArticleMapper;
import com.maomao.ssm.dao.GoodsAddressMapper;
import com.maomao.ssm.dao.GoodsBrandMapper;
import com.maomao.ssm.dao.GoodsCategoryMapper;
import com.maomao.ssm.dao.GoodsCollectionMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsMapperCustom;
import com.maomao.ssm.dao.GoodsOverstockInfoMapper;
import com.maomao.ssm.dao.GoodsOverstockMapper;
import com.maomao.ssm.dao.GoodsServiceMapper;
import com.maomao.ssm.dao.GoodsSkuMapper;
import com.maomao.ssm.dao.GoodsSubscriptionMapper;
import com.maomao.ssm.dao.GoodsUserMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.category.CategoryDetails;
import com.maomao.ssm.pojo.home.CategoryDetail;
import com.maomao.ssm.pojo.home.HomeBannerDetail;
import com.maomao.ssm.pojo.search.GoodsServiceDetails;
import com.maomao.ssm.pojo.search.SearchBean;
import com.maomao.ssm.service.v2.CategoryServiceV2;
import com.maomao.ssm.utils.JsonUtils;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年7月9日
 */
@Service
public class CategoryServiceImplV2 implements CategoryServiceV2 {
	@Autowired
	private BannerMapper bannerMapper;
	@Autowired
	private GoodsCategoryMapper categoryMapper;
	@Autowired
	private JedisClientPool jedisClientPool;
	@Autowired
	private CategoryHomeArticleMapper categoryHomeArticleMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsSubscriptionMapper goodsSubscriptionMapper;
	@Autowired
	private GoodsMapperCustom goodsMapperCustom;
	@Autowired
	private GoodsBrandMapper goodsBrandMapper;
	@Autowired
	private GoodsServiceMapper goodsServiceMapper;
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
	private GoodsOverstockInfoMapper stockInfoMapper;
	// 分类详情
	public JsonData getCategoyrDetail(String ua) {
		// 查询缓存
		if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL_V2 + ua)) {
			String json = jedisClientPool.get(RedisConst.CATEGORY_DETAIL_V2 + ua);
			JsonData jsonData = JsonUtils.jsonToPojo(json, JsonData.class);
			return jsonData;
		}
		CategoryDetails categoryDetails = new CategoryDetails();
		List<HomeBannerDetail> bannerList = new ArrayList<HomeBannerDetail>();
		List<CategoryDetail> categoryList = new ArrayList<CategoryDetail>();

		try {
			// 查询banner
			BannerExample example1 = new BannerExample();
			example1.createCriteria().andStatusEqualTo(StatusConst.BANNER_STATUS_ON)
					.andTypeEqualTo(StatusConst.BANNER_STATUS_CATEGORY);
			example1.setOrderByClause("sort DESC");
			List<Banner> banners = bannerMapper.selectByExample(example1);
			if (banners != null && banners.size() > 0) {
				for (Banner banner : banners) {
					String img = banner.getImg();
					String href = banner.getHref();
					Integer sort = banner.getSort();
					Date createTime = banner.getCreateTime();
					HomeBannerDetail bannerDetail = new HomeBannerDetail();
					if ("ios".equals(ua)) {
						bannerDetail.setImg(img.split(StatusConst.IMG_SPLIT_STRING)[1]);
					} else {
						bannerDetail.setImg(img.split(StatusConst.IMG_SPLIT_STRING)[0]);
					}
					bannerDetail.setHref(href);
					bannerList.add(bannerDetail);
				}
			}
			categoryDetails.setBannerList(bannerList);

			// 查询推荐文章
			CategoryHomeArticleExample articelExample = new CategoryHomeArticleExample();
			articelExample.createCriteria().andStatusEqualTo((byte) 1).andEffectiveTimeGreaterThan(new Date());
			List<CategoryHomeArticle> articles = categoryHomeArticleMapper.selectByExample(articelExample);
			List<Map<String, Object>> artiList = new ArrayList<Map<String,Object>>();
			
			if (articles != null && articles.size() > 0) {
				for (CategoryHomeArticle article : articles) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("title", article.getTitle());// 标题
					map.put("introduction", article.getIntroduction());
					map.put("url", article.getUrl());
					artiList.add(map);
				}
			}
			categoryDetails.setArticle(artiList);

			// 查询分类
			GoodsCategoryExample example2 = new GoodsCategoryExample();
			example2.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON)
					.andIsRecommendEqualTo(GoodsConst.GOODS_CATEGORY_IS_RECOMMEND_NORMAL);
			example2.setOrderByClause("category_sort ASC");
			List<GoodsCategory> goodsCategoryList = categoryMapper.selectByExample(example2);
			if (goodsCategoryList != null && goodsCategoryList.size() > 0) {
				for (GoodsCategory goodsCategory : goodsCategoryList) {
					Integer categoryId = goodsCategory.getId();
					String img = goodsCategory.getImg();
					String name = goodsCategory.getName();
					Date createTime = goodsCategory.getCrateTime();
					CategoryDetail categoryDetail = new CategoryDetail();
					categoryDetail.setCategoryId(categoryId);
					categoryDetail.setImg(img);
					categoryDetail.setCreateTime(createTime);
					categoryDetail.setName(name);
					categoryList.add(categoryDetail);
				}
			}
			categoryDetails.setCategoryList(categoryList);
			JsonData jsonData = JsonData.setSuccessMessage(categoryDetails);
			try {
				String json = JsonUtils.objectToJson(jsonData);
				jedisClientPool.set(RedisConst.CATEGORY_DETAIL_V2 + ua, json);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return jsonData;
		} catch (Exception e) {
			return JsonData.setServerErrorMessage("服务器异常");
		}
	}

	// 根据分类id查询分类详情   type=-1 买断商品  , category
	public JsonData getCategoryDetailById(Integer categoryId, Integer pages, Integer rows,
			Integer sortType, String brand, Long startPrice, Long endPrice) {
		if (categoryId == null ) {
			return JsonData.setErrorMessage("参数非法");
		}

		// TODO 商家用户查询众筹商品表
		GoodsSubscriptionExample example = new GoodsSubscriptionExample();
		GoodsExample example1 = new GoodsExample();
		GoodsOverstockExample example2 = new GoodsOverstockExample();
		
		
		if (categoryId == 0) {
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
		
				com.maomao.ssm.bean.GoodsOverstockExample.Criteria maiduanCriteria = example2.createCriteria();
				maiduanCriteria.andStatusEqualTo(GoodsConst.GOODS_OVERSTOCK_STATUS_ON_SALE);
				Criteria criteria = example1.createCriteria();
				criteria.andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
				.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED)
				.andCategoryIdEqualTo(categoryId);
				if (startPrice != null) {
					criteria.andMinPriceGreaterThanOrEqualTo(startPrice);
					maiduanCriteria.andPriceGreaterThanOrEqualTo(startPrice);
				}
				if (endPrice != null) {
					criteria.andMinPriceLessThanOrEqualTo(endPrice);
					maiduanCriteria.andPriceLessThanOrEqualTo(endPrice);
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

			// 零售价升序
			if (sortType == 0 && categoryId == 0) {
				example.setOrderByClause("price_sales asc");
			}
	
			// 零售价降序
			if (sortType == 1 && categoryId == 0) {
				example.setOrderByClause("price_sales desc");
			}
			

			// 分红升序
			if (sortType == 2 && categoryId == 0) {
				example.setOrderByClause("bonus_avg asc");
			} else if (sortType == 2 && categoryId != 0) {
				example1.setOrderByClause("rebate asc");
			} else if (sortType == -1 && categoryId != 0) {
				example2.setOrderByClause("rebate asc");
			}
			// 分红降序
			if (sortType == 3 && categoryId == 0) {
				example.setOrderByClause("bonus_avg desc");
			} else if (sortType == 3 && categoryId != 0) {
				example1.setOrderByClause("rebate desc");
			}else if (sortType == -1 && categoryId != 0) {
				example2.setOrderByClause("rebate desc");
			}
			// 综合排序
			if (sortType == 4 && categoryId != 0) {
				example1.setOrderByClause("sort desc");
			}
			if (sortType == 5 && categoryId != 0) {
				example1.setOrderByClause("sort asc");
			}

		}
		if (categoryId == 0) {
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

		} else if (categoryId==-1){//买断商品
			List<GoodsOverstockWithBLOBs> list = goodsOverstockMapper.selectByExampleWithBLOBs(example2);
			if (list!=null && list.size()>0) {
				for (GoodsOverstockWithBLOBs goodsOverstock : list) {
					List<GoodsServiceDetails> goodsServiceList = new ArrayList<GoodsServiceDetails>();
					String serviceId = goodsOverstock.getServiceId();
					if (StringUtils.isNotBlank(serviceId)) { 
						String[] splitId = serviceId.split(";");
						for (String id : splitId) {
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

					SearchBean searchBean = new SearchBean(goodsOverstock, goodsServiceList);
					goodsList.add(searchBean);
				}
			}
			
		}else {
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
}
