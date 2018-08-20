package com.maomao.ssm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maomao.ssm.bean.Banner;
import com.maomao.ssm.bean.BannerExample;
import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.bean.GoodsCategoryExample;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.BannerMapper;
import com.maomao.ssm.dao.GoodsCategoryMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.category.CategoryDetails;
import com.maomao.ssm.pojo.home.CategoryDetail;
import com.maomao.ssm.pojo.home.HomeBannerDetail;
import com.maomao.ssm.service.CategoryService;
import com.maomao.ssm.utils.JsonUtils;

/**
 * @descrption:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	private Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	private BannerMapper bannerMapper;
	@Autowired
	private GoodsCategoryMapper categoryMapper;
	@Autowired
	private JedisClientPool jedisClientPool;

	/**
	 * 查询分类详情
	 */
	public JsonData getCategoryDetail(String ua) {
		// 查询缓存
		if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + ua)) {
			String json = jedisClientPool.get(RedisConst.CATEGORY_DETAIL + ua);
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
				jedisClientPool.set(RedisConst.CATEGORY_DETAIL + ua, json);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return jsonData;
		} catch (Exception e) {
			log.error("CategoryService getCategoryDetail method error-" + e.getMessage(), e);
			return JsonData.setServerErrorMessage("服务器异常");
		}

	}
}
