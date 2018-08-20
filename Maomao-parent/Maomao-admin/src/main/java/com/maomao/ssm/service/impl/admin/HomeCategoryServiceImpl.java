package com.maomao.ssm.service.impl.admin;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.Goods;
import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.bean.GoodsCategoryExample;
import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.bean.GoodsExample.Criteria;
import com.maomao.ssm.bean.GoodsHome;
import com.maomao.ssm.bean.GoodsHomeExample;
import com.maomao.ssm.bean.GoodsHomepageCategory;
import com.maomao.ssm.bean.GoodsHomepageCategoryExample;
import com.maomao.ssm.bean.GoodsOverstock;
import com.maomao.ssm.bean.GoodsOverstockExample;
import com.maomao.ssm.bean.GoodsSku;
import com.maomao.ssm.bean.GoodsSkuExample;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.dao.AdminMapper;
import com.maomao.ssm.dao.GoodsCategoryMapper;
import com.maomao.ssm.dao.GoodsHomeMapper;
import com.maomao.ssm.dao.GoodsHomeMapperCustom;
import com.maomao.ssm.dao.GoodsHomepageCategoryMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsOverstockMapper;
import com.maomao.ssm.dao.GoodsSkuMapper;
import com.maomao.ssm.pojo.GoodsHomeList;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.HomeCategoryService;
import com.maomao.ssm.service.admin.SystemService;

/**
 * @author:wzy
 * @descrption:首页分类管理
 * @version:
 * @date:2018年5月22日
 */
@Service
public class HomeCategoryServiceImpl implements HomeCategoryService {
	@Autowired
	private GoodsHomepageCategoryMapper homepageCategoryMapper;
	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	@Autowired
	private JedisClientPool jedisClientPool;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsCategoryMapper oldCategoryMapper;
	@Autowired
	private GoodsSkuMapper skuMapper;
	@Autowired
	private AdminMapper adminMapper;
	/**
	 * 获取分类列表
	 */
	public JsonData getHomeCategoyrList() {

		GoodsHomepageCategoryExample example = new GoodsHomepageCategoryExample();
		example.setOrderByClause("sort asc");
		example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON);
		List<GoodsHomepageCategory> homepageCategoryList = homepageCategoryMapper.selectByExample(example);

		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		if (homepageCategoryList != null && homepageCategoryList.size() > 0) {
			for (GoodsHomepageCategory homepageCategory : homepageCategoryList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", homepageCategory.getId() + "");// 分类id
				map.put("name", homepageCategory.getName());
				map.put("img", homepageCategory.getImg());
				GoodsCategory goodsCategory = goodsCategoryMapper
						.selectByPrimaryKey(homepageCategory.getGoodsCategoryid());
				map.put("url", goodsCategory == null ? "未关联分类" : goodsCategory.getName());
				returnList.add(map);
			}
		}
		return JsonData.setSuccessMessage(returnList);
	}

	/**
	 * 修改 或 新建分类
	 */
	public JsonData saveOrUpdateHomePageCategory(Integer id, String name, String img, Integer categoryId,
			Integer sort) {
		try {
			GoodsHomepageCategory goodsHomepageCategory = new GoodsHomepageCategory();
			goodsHomepageCategory.setName(name);
			goodsHomepageCategory.setImg(img);
			goodsHomepageCategory.setGoodsCategoryid(categoryId);
			if (id != null) {
				goodsHomepageCategory.setId(id);
				goodsHomepageCategory.setModifiedTime(new Date());
				homepageCategoryMapper.updateByPrimaryKeySelective(goodsHomepageCategory);
			} else {
				GoodsHomepageCategoryExample example = new GoodsHomepageCategoryExample();
				example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON);
				int count = homepageCategoryMapper.countByExample(example);
				if (count >= 8) {
					return JsonData.setErrorMessage("分类已经达到上限");
				}
				goodsHomepageCategory.setCreateTime(new Date());
				goodsHomepageCategory.setStatus(GoodsConst.GOODS_CATEGORY_STATUS_ON);
				goodsHomepageCategory.setSort(sort + 1);
				homepageCategoryMapper.insertSelective(goodsHomepageCategory);
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("网络异常,操作失败");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 根据id获取首页分类详情
	 */
	public JsonData getHomePageCategoyrById(Integer id) {
		if (id == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		Map<String, String> map = null;
		GoodsHomepageCategory goodsHomepageCategory = homepageCategoryMapper.selectByPrimaryKey(id);
		if (goodsHomepageCategory != null) {
			map = new HashMap<String, String>();
			map.put("name", goodsHomepageCategory.getName());
			map.put("img", goodsHomepageCategory.getImg());
			map.put("categoryId", goodsHomepageCategory.getGoodsCategoryid() + "");
		}
		return JsonData.setSuccessMessage(map);
	}

	/**
	 * 分类上移
	 */
	public JsonData homePageCategoryUp(Integer id) {
		if (id == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsHomepageCategory currentHomepageCategory = homepageCategoryMapper.selectByPrimaryKey(id);
		if (currentHomepageCategory != null && currentHomepageCategory.getSort() == 1) {
			return JsonData.setErrorMessage("已经是第一位了,移不动了");
		}

		GoodsHomepageCategoryExample example = new GoodsHomepageCategoryExample();
		example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON)
				.andSortEqualTo(currentHomepageCategory.getSort() - 1);
		GoodsHomepageCategory upHomepageCategory = homepageCategoryMapper.selectByExample(example).get(0);
		upHomepageCategory.setSort(currentHomepageCategory.getSort());
		currentHomepageCategory.setSort(currentHomepageCategory.getSort() - 1);

		try {
			homepageCategoryMapper.updateByPrimaryKeySelective(currentHomepageCategory);
			homepageCategoryMapper.updateByPrimaryKeySelective(upHomepageCategory);
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
			}
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
	}

	/**
	 * 分类下移
	 */
	public JsonData homePageCategoryDown(Integer id) {
		if (id == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsHomepageCategory currentHomepageCategory = homepageCategoryMapper.selectByPrimaryKey(id);

		GoodsHomepageCategoryExample example = new GoodsHomepageCategoryExample();
		example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON)
				.andSortEqualTo(currentHomepageCategory.getSort() + 1);
		GoodsHomepageCategory downHomepageCategory = homepageCategoryMapper.selectByExample(example).get(0);
		downHomepageCategory.setSort(currentHomepageCategory.getSort());
		currentHomepageCategory.setSort(currentHomepageCategory.getSort() + 1);

		try {
			homepageCategoryMapper.updateByPrimaryKeySelective(currentHomepageCategory);
			homepageCategoryMapper.updateByPrimaryKeySelective(downHomepageCategory);
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
			}
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
	}

	/**
	 * 分类置顶
	 */
	public JsonData homePageCategoryTop(Integer id) {
		if (id == null) {
			return JsonData.setErrorMessage("参数错误");
		}

		GoodsHomepageCategory currentHomepageCategory = homepageCategoryMapper.selectByPrimaryKey(id);

		GoodsHomepageCategoryExample example = new GoodsHomepageCategoryExample();
		example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON)
				.andSortLessThan(currentHomepageCategory.getSort());
		List<GoodsHomepageCategory> topList = homepageCategoryMapper.selectByExample(example);
		if (topList != null && topList.size() > 0) {
			for (GoodsHomepageCategory goodsHomepageCategory : topList) {
				goodsHomepageCategory.setSort(goodsHomepageCategory.getSort() + 1);
				homepageCategoryMapper.updateByPrimaryKeySelective(goodsHomepageCategory);
			}
		}
		currentHomepageCategory.setSort(1);
		homepageCategoryMapper.updateByPrimaryKeySelective(currentHomepageCategory);
		if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
			jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
		}
		if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
			jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
		}
		if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
			jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 删除分类
	 */
	public JsonData deleteHomePageCategoryById(Integer id) {
		if (id == null) {
			return JsonData.setErrorMessage("参数错误");
		}

		GoodsHomepageCategory homepageCategory = homepageCategoryMapper.selectByPrimaryKey(id);
		homepageCategory.setId(id);
		homepageCategory.setStatus(GoodsConst.GOODS_CATEGORY_STATUS_DEL);

		GoodsHomepageCategoryExample example = new GoodsHomepageCategoryExample();
		example.createCriteria().andSortGreaterThan(homepageCategory.getSort())
				.andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON);
		List<GoodsHomepageCategory> homepageCategoryList = homepageCategoryMapper.selectByExample(example);
		try {
			if (homepageCategoryList != null && homepageCategoryList.size() > 0) {
				for (GoodsHomepageCategory goodsHomepageCategory : homepageCategoryList) {
					goodsHomepageCategory.setSort(goodsHomepageCategory.getSort() - 1);
					homepageCategoryMapper.updateByPrimaryKeySelective(goodsHomepageCategory);
				}
			}

			homepageCategoryMapper.updateByPrimaryKeySelective(homepageCategory);
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
			}
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("参数错误");
		}

	}

	/**
	 * 分类商品列表
	 */
	public JsonData getHomeCategoryGoodsList() {
		GoodsCategoryExample example = new GoodsCategoryExample();
		example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON);
		List<GoodsCategory> oldGoodsCategoryList = oldCategoryMapper.selectByExample(example);
		List<Map<String, String>> returnList = null;
		if (oldGoodsCategoryList != null && oldGoodsCategoryList.size() > 0) {
			GoodsExample goodsExample = new GoodsExample();
			returnList = new ArrayList<Map<String, String>>();

			for (GoodsCategory oldGoodsCategory : oldGoodsCategoryList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", oldGoodsCategory.getId() + "");
				map.put("name", oldGoodsCategory.getName());
				goodsExample.clear();
				goodsExample.createCriteria().andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
						.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED)
						.andCategoryIdEqualTo(oldGoodsCategory.getId());
				int count = goodsMapper.countByExample(goodsExample);
				map.put("count", count + "");
				returnList.add(map);
			}
		}
		return JsonData.setSuccessMessage(returnList);
	}

	/**
	 * 分类页商品排序列表
	 */
	public JsonData getHomeCategoryGoodsOrderList(Integer id, String keywords) {
		if (id == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsExample example = new GoodsExample();
		Criteria criteria = example.createCriteria();
		example.setOrderByClause("sort asc");
		criteria.andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
				.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED).andCategoryIdEqualTo(id);
		if (StringUtils.isNotBlank(keywords)) {
			criteria.andNameLike("%" + keywords + "%");
		}
		List<Goods> goodsList = goodsMapper.selectByExample(example);
		List<Map<String, Object>> returnList = null;
		if (goodsList != null && goodsList.size() > 0) {
			GoodsSkuExample example3 = new GoodsSkuExample();
			returnList = new ArrayList<Map<String, Object>>();

			for (Goods goods : goodsList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", goods.getName());
				map.put("sort", goods.getSort());
				map.put("id", goods.getId());
				example3.clear();
				example3.createCriteria().andGoodsIdEqualTo(goods.getId()).andStatusEqualTo((byte) 0);
				List<GoodsSku> goodsSkuList = skuMapper.selectByExample(example3);
				String maxPrice = new DecimalFormat(".00").format(goods.getMaxPrice() / 100);
				String minPrice = new DecimalFormat(".00").format(goods.getMinPrice() / 100);
				Integer stockCount = 0;
				if (goodsSkuList != null && goodsSkuList.size() > 0) {
					for (GoodsSku goodsSku : goodsSkuList) {
						stockCount += goodsSku.getStock();
					}
				}
				String price = minPrice + "~" + maxPrice;
				map.put("price", price);// 上架单价
				map.put("stockCount", stockCount);// 库存量
				Byte type = goods.getType();
				String goodsProperty = "";
				String owner = "";
				if (type == GoodsConst.GOODS_TYPE_SELF) {
					goodsProperty = "自营商品";
					owner = "平台";
				} else {
					if (type == GoodsConst.GOODS_TYPE_NORMAL) {
						goodsProperty = "常规商品";
					}
					if (type == GoodsConst.GOODS_TYPE_MORTGAGE) {
						goodsProperty = "质押商品";
					}
					if (type == GoodsConst.GOODS_TYPE_VR_MORTGAGE) {
						goodsProperty = "虚拟质押";
					}
					Admin admin = adminMapper.selectByPrimaryKey(goods.getAdminId());
					if (admin != null) {
						owner = admin.getName() + "(" + admin.getMobile() + ")";
					}
				}
				map.put("goodsProperty", goodsProperty);
				map.put("owner", owner);
				returnList.add(map);
			}
		}
		return JsonData.setSuccessMessage(returnList);
	}

	/**
	 * 上移
	 */
	public JsonData categoryGoodsUp(Integer categoryId, Integer goodsId) {
		if (categoryId == null || goodsId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsExample example = new GoodsExample();
		example.createCriteria().andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
				.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED).andIdEqualTo(goodsId);
		List<GoodsWithBLOBs> goodsList = goodsMapper.selectByExampleWithBLOBs(example);
		if (goodsList != null && goodsList.size() > 0) {
			GoodsWithBLOBs currentGoods = goodsList.get(0);
			GoodsExample example2 = new GoodsExample();
			example2.createCriteria().andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
					.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED)
					.andSortEqualTo(currentGoods.getSort() - 1).andCategoryIdEqualTo(categoryId);
			List<GoodsWithBLOBs> goodsList2 = goodsMapper.selectByExampleWithBLOBs(example2);
			GoodsWithBLOBs fontGoods = goodsList2.get(0);
			System.out.println(currentGoods.getSort());
			fontGoods.setSort(currentGoods.getSort());

			currentGoods.setSort(currentGoods.getSort() - 1);
			goodsMapper.updateByPrimaryKeySelective(currentGoods);
			goodsMapper.updateByPrimaryKeySelective(fontGoods);
			return JsonData.setSuccessMessage();
		}

		return JsonData.setErrorMessage("操作失败,商品已经下架或者删除");
	}

	/**
	 * 下移
	 */
	public JsonData categoryGoodsDown(Integer categoryId, Integer goodsId) {
		if (categoryId == null || goodsId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsExample example = new GoodsExample();
		example.createCriteria().andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
				.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED).andIdEqualTo(goodsId);
		List<GoodsWithBLOBs> goodsList = goodsMapper.selectByExampleWithBLOBs(example);

		if (goodsList != null && goodsList.size() > 0) {
			GoodsWithBLOBs currentGoods = goodsList.get(0);
			Integer currentSort = currentGoods.getSort();
			GoodsExample example2 = new GoodsExample();
			example2.createCriteria().andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
					.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED)
					.andSortEqualTo(currentSort + 1).andCategoryIdEqualTo(categoryId);
			List<GoodsWithBLOBs> goodsList2 = goodsMapper.selectByExampleWithBLOBs(example2);
			GoodsWithBLOBs fontGoods = goodsList2.get(0);
			System.out.println("=====" + currentSort);
			fontGoods.setSort(currentSort);
			goodsMapper.updateByPrimaryKeySelective(fontGoods);
			currentGoods.setSort(currentSort + 1);
			goodsMapper.updateByPrimaryKeySelective(currentGoods);
			return JsonData.setSuccessMessage();
		}

		return JsonData.setErrorMessage("操作失败,商品已经下架或者删除");
	}

	/**
	 * 置顶
	 */
	public JsonData categoryGoodsTop(Integer categoryId, Integer goodsId) {
		if (categoryId == null || goodsId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsExample example = new GoodsExample();
		example.createCriteria().andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
				.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED).andIdEqualTo(goodsId);
		List<GoodsWithBLOBs> goodsList = goodsMapper.selectByExampleWithBLOBs(example);
		if (goodsList != null && goodsList.size() > 0) {
			GoodsWithBLOBs currentGoods = goodsList.get(0);

			GoodsExample example2 = new GoodsExample();
			example2.createCriteria().andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
					.andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED)
					.andSortLessThan(currentGoods.getSort()).andCategoryIdEqualTo(categoryId);
			List<GoodsWithBLOBs> goodsList2 = goodsMapper.selectByExampleWithBLOBs(example2);
			if (goodsList2 != null && goodsList2.size() > 0) {
				for (GoodsWithBLOBs changeGoods : goodsList2) {
					changeGoods.setSort(changeGoods.getSort() + 1);
					goodsMapper.updateByPrimaryKeySelective(changeGoods);
				}
			}
			currentGoods.setSort(1);
			goodsMapper.updateByPrimaryKeySelective(currentGoods);
			return JsonData.setSuccessMessage();
		}

		return JsonData.setErrorMessage("操作失败,商品已经下架或者删除");
	}
}
