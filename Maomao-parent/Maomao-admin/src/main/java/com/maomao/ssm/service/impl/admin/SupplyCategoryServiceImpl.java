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
import com.maomao.ssm.bean.Goods;
import com.maomao.ssm.bean.GoodsBrand;
import com.maomao.ssm.bean.GoodsBrandExample;
import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.bean.GoodsCategoryExample;
import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.bean.GoodsSku;
import com.maomao.ssm.bean.GoodsSkuExample;
import com.maomao.ssm.bean.GoodsSkuKey;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.GoodsBrandMapper;
import com.maomao.ssm.dao.GoodsCategoryMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsSkuKeyMapper;
import com.maomao.ssm.dao.GoodsSkuMapper;
import com.maomao.ssm.dao.SupplyCategoryMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.pojo.SupplyCategory;
import com.maomao.ssm.service.admin.SupplyCategoryService;

/**
 * @author:wzy
 * @descrption:货源管理
 * @version:
 * @date:2018年3月2日
 */
@Service
public class SupplyCategoryServiceImpl implements SupplyCategoryService {

	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	@Autowired
	private SupplyCategoryMapper supplyCategoryMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsBrandMapper goodsBrandMapper;
	@Autowired
	private JedisClientPool jedisClientPool;
	@Autowired
	private GoodsSkuMapper skuMapper;
	@Autowired
	private GoodsSkuKeyMapper keyMapper;

	/**
	 * 查询货源分类
	 */
	public JsonData getSupplyCategory(int page, int rows, String keywords, Byte isRecommend) {
		if (page < 1) {
			page = 1;
		}
		PageHelper.startPage(page, rows);// page:当前页 ,rows:每页显示的记录数

		List<SupplyCategory> list = supplyCategoryMapper.getSupplyCategoryList(keywords, isRecommend);

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				GoodsExample example = new GoodsExample();
				example.createCriteria().andCategoryIdEqualTo(list.get(i).getId())
						.andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
				List<Goods> goodsList = goodsMapper.selectByExample(example);
				if (goodsList != null && goodsList.size() > 0) {
					Integer goodsId = goodsList.get(0).getId();
					GoodsSkuExample example2 = new GoodsSkuExample();
					example2.createCriteria().andGoodsIdEqualTo(goodsId).andStatusEqualTo((byte) 0);
					List<GoodsSku> skuList = skuMapper.selectByExample(example2);
					Integer stock = 0;
					if (skuList != null && skuList.size() > 0) {
						for (GoodsSku goodsSku : skuList) {
							stock += goodsSku.getStock();
						}
					}
					list.get(i).setStock(stock);
				}
			}
		}
		// 取分页信息
		PageInfo<SupplyCategory> pageInfo = new PageInfo<SupplyCategory>(list);
		PageBean pageBean = new PageBean();
		pageBean.setTotal(pageInfo.getTotal());
		pageBean.setRows(list);
		return JsonData.setSuccessMessage(pageBean);
	}

	/**
	 * 删除分类
	 */
	public JsonData deleteSupplyCategoryById(Integer supplyCategoryId) {
		if (supplyCategoryId == null || supplyCategoryId <= 0) {
			return JsonData.setErrorMessage("参数错误");
		}
		try {
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "default");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(supplyCategoryId);
		if (goodsCategory != null) {
			Integer categoryId = goodsCategory.getId();
			try {
				goodsCategory.setStatus(GoodsConst.GOODS_CATEGORY_STATUS_DEL);
				GoodsCategoryExample example2 = new GoodsCategoryExample();
				example2.createCriteria().andCategorySortGreaterThan(goodsCategory.getCategorySort())
						.andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON);
				List<GoodsCategory> categoryList = goodsCategoryMapper.selectByExample(example2);
				if (categoryList != null && categoryList.size() > 0) {
					for (GoodsCategory updateCategoyr : categoryList) {
						updateCategoyr.setCategorySort(updateCategoyr.getCategorySort() - 1);
						goodsCategoryMapper.updateByPrimaryKeySelective(updateCategoyr);
					}
				}

				goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
				// 删除分类下所有商品
				GoodsExample example = new GoodsExample();
				example.createCriteria().andCategoryIdEqualTo(categoryId)
						.andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
				List<GoodsWithBLOBs> goodsWithBLOBs = goodsMapper.selectByExampleWithBLOBs(example);
				if (goodsWithBLOBs != null && goodsWithBLOBs.size() > 0) {
					for (GoodsWithBLOBs categoryGoods : goodsWithBLOBs) {
						categoryGoods.setStatus(GoodsConst.GOODS_STATUS_OFF_SHELF);// 下架
						goodsMapper.updateByPrimaryKeyWithBLOBs(categoryGoods);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("删除失败");
			}
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 停用分类
	 */
	public JsonData setCategoryStopById(Integer categoryId) {
		if (categoryId == null || categoryId <= 0) {
			return JsonData.setErrorMessage("参数错误");
		}
		try {
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "default");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
		if (goodsCategory != null) {
			try {
				goodsCategory.setStatus(GoodsConst.GOODS_CATEGORY_STATUS_STOP);
				goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("操作失败");
			}
		}

		return JsonData.setSuccessMessage();
	}

	/**
	 * 启用分类
	 */
	public JsonData setCategoryStartById(Integer categoryId) {
		if (categoryId == null || categoryId <= 0) {
			return JsonData.setErrorMessage("参数错误");
		}
		try {
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "default");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
		if (goodsCategory != null) {
			try {
				goodsCategory.setStatus(GoodsConst.GOODS_CATEGORY_STATUS_ON);
				goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("操作失败");
			}
			return JsonData.setSuccessMessage();
		}
		return JsonData.setErrorMessage("网络异常");
	}

	/**
	 * 分类上移
	 */
	public JsonData setCategorySortUp(Integer categoryId) {
		if (categoryId == null) {
			return JsonData.setErrorMessage("参数错误");
		}

		GoodsCategory currentCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
		if (currentCategory.getCategorySort() == 1) {
			return JsonData.setErrorMessage("该分类排序已经是第一位了,移不动了~");
		}
		GoodsCategoryExample example = new GoodsCategoryExample();
		example.createCriteria().andCategorySortEqualTo(currentCategory.getCategorySort() - 1)
				.andStatusNotEqualTo(GoodsConst.GOODS_STATUS_DELETED)
				.andIsRecommendEqualTo(GoodsConst.GOODS_CATEGORY_IS_RECOMMEND_NORMAL);
		GoodsCategory fontCategory = goodsCategoryMapper.selectByExample(example).get(0);
		fontCategory.setCategorySort(currentCategory.getCategorySort());
		currentCategory.setCategorySort(currentCategory.getCategorySort() - 1);
		try {
			goodsCategoryMapper.updateByPrimaryKeySelective(currentCategory);
			goodsCategoryMapper.updateByPrimaryKeySelective(fontCategory);
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "default");
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
	public JsonData setCategorySortBack(Integer categoryId) {
		if (categoryId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsCategory currentCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);

		GoodsCategoryExample example = new GoodsCategoryExample();
		example.createCriteria().andCategorySortEqualTo(currentCategory.getCategorySort() + 1)
				.andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON)
				.andIsRecommendEqualTo(GoodsConst.GOODS_CATEGORY_IS_RECOMMEND_NORMAL);
		GoodsCategory backCategory = goodsCategoryMapper.selectByExample(example).get(0);
		backCategory.setCategorySort(currentCategory.getCategorySort());
		currentCategory.setCategorySort(currentCategory.getCategorySort() + 1);
		try {
			goodsCategoryMapper.updateByPrimaryKeySelective(currentCategory);
			goodsCategoryMapper.updateByPrimaryKeySelective(backCategory);
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "default");
			}
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
	}

	/**
	 * 添加分类
	 */
	public JsonData addCategroy(Integer sort, String categoryName, Integer categorySort, String imgURL, String brand,
			String skuKey, Integer maxCategorySort, Byte isRecommend) {
		if (categoryName == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		try {
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "default");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GoodsCategoryExample example = new GoodsCategoryExample();

			example.createCriteria().andNameEqualTo(categoryName).andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON)
					.andIsRecommendEqualTo(isRecommend);
			List<GoodsCategory> resutList = goodsCategoryMapper.selectByExample(example);
			if (resutList != null && resutList.size() > 0) {
				return JsonData.setErrorMessage("分类已存在,不允许重复添加");
			}
			if (GoodsConst.GOODS_CATEGORY_IS_RECOMMEND_NORMAL.equals(isRecommend)) {
				example.clear();
				example.createCriteria().andCategorySortEqualTo(categorySort);
				List<GoodsCategory> categoryList = goodsCategoryMapper.selectByExample(example);
				if (categoryList != null && categoryList.size() > 0) {
					GoodsCategory goodsCategory = categoryList.get(0);
					goodsCategory.setCategorySort(maxCategorySort);
					goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
				}

				GoodsCategory goodsCategory = new GoodsCategory();
				goodsCategory.setName(categoryName);
				goodsCategory.setCrateTime(new Date());
				goodsCategory.setImg(imgURL);
				goodsCategory.setStatus(GoodsConst.GOODS_CATEGORY_STATUS_ON);
				goodsCategory.setSort(sort);
				goodsCategory.setCategorySort(categorySort);
				goodsCategory.setIsRecommend(isRecommend);

				goodsCategoryMapper.insertSelective(goodsCategory);

				if (StringUtils.isNotBlank(brand)) {
					String[] split = brand.split(";");
					for (String name : split) {
						GoodsBrand goodsBrand = new GoodsBrand();
						goodsBrand.setName(name);
						goodsBrand.setCreateTime(new Date());
						goodsBrand.setCategoryId(goodsCategory.getId());
						goodsBrandMapper.insert(goodsBrand);
					}
				}
				if (StringUtils.isNotBlank(skuKey)) {
					String[] keys = skuKey.split(";");
					for (String key : keys) {
						GoodsSkuKey skuKeyValue = new GoodsSkuKey();
						skuKeyValue.setCategoryId(goodsCategory.getId());
						skuKeyValue.setSkuKey(key);
						keyMapper.insertSelective(skuKeyValue);
					}
				}
			}
			if (GoodsConst.GOODS_CATEGORY_IS_RECOMMEND_SUBSCRIPTION.equals(isRecommend)) {
				GoodsCategory goodsCategory = new GoodsCategory();
				goodsCategory.setName(categoryName);
				goodsCategory.setCrateTime(new Date());
				goodsCategory.setImg(imgURL);
				goodsCategory.setStatus(GoodsConst.GOODS_CATEGORY_STATUS_ON);
				goodsCategory.setSort(sort);
				goodsCategory.setCategorySort(categorySort);
				goodsCategory.setIsRecommend(isRecommend);
				goodsCategoryMapper.insertSelective(goodsCategory);
				if (StringUtils.isNotBlank(brand)) {
					String[] split = brand.split(";");
					for (String name : split) {
						GoodsBrand goodsBrand = new GoodsBrand();
						goodsBrand.setName(name);
						goodsBrand.setCreateTime(new Date());
						goodsBrand.setCategoryId(goodsCategory.getId());
						goodsBrandMapper.insert(goodsBrand);
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
	 * 查询分类下所有品牌
	 */
	public JsonData getCategoryBrand(Integer categoryId, Integer pages, Integer rows, Byte isFenye) {
		if (categoryId == null || categoryId <= 0) {
			return JsonData.setErrorMessage("参数错误");
		}
		if (pages < 1) {
			pages = 1;
		}
		if (isFenye == StatusConst.IS_FENYE_NO) {// 不分页
			GoodsBrandExample example = new GoodsBrandExample();
			example.createCriteria().andCategoryIdEqualTo(categoryId);
			List<GoodsBrand> goodsBrands = goodsBrandMapper.selectByExample(example);
			return JsonData.setSuccessMessage(goodsBrands);
		} else {
			PageHelper.startPage(pages, rows);
			GoodsBrandExample example = new GoodsBrandExample();
			example.createCriteria().andCategoryIdEqualTo(categoryId);
			List<GoodsBrand> goodsBrands = goodsBrandMapper.selectByExample(example);
			PageBean pageBean = new PageBean();
			if (goodsBrands != null && goodsBrands.size() > 0) {
				PageInfo<GoodsBrand> pageInfo = new PageInfo<GoodsBrand>(goodsBrands);
				pageBean.setTotal(pageInfo.getTotal());
				pageBean.setRows(goodsBrands);
			}
			return JsonData.setSuccessMessage(pageBean);
		}

	}

	/**
	 * 编辑品牌
	 */
	public JsonData editBrandById(Integer brandId, String name) {
		if (brandId == null || brandId <= 0 || StringUtils.isBlank(name)) {
			return JsonData.setErrorMessage("参数错误");
		}

		GoodsBrand goodsBrand = goodsBrandMapper.selectByPrimaryKey(brandId);
		try {
			if (goodsBrand != null) {
				goodsBrand.setName(name);
				goodsBrandMapper.updateByPrimaryKeySelective(goodsBrand);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("修改失败");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 删除品牌
	 */
	public JsonData deleteBrandById(Integer brandId) {
		if (brandId == null || brandId <= 0) {
			return JsonData.setErrorMessage("参数错误");
		}
		try {
			GoodsExample example = new GoodsExample();
			example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE).andBrandIdEqualTo(brandId);
			int count = goodsMapper.countByExample(example);
			if (count != 0) {
				return JsonData.setErrorMessage("品牌下还有商品，不允许删除");
			}

			goodsBrandMapper.deleteByPrimaryKey(brandId);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("删除失败");
		}

		return JsonData.setSuccessMessage();
	}

	/**
	 * 修改分类
	 */
	public JsonData editCategoryById(GoodsCategory goodsCategory, String brand, String skuKey) {
		if (goodsCategory == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		try {
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "default");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (GoodsConst.GOODS_CATEGORY_IS_RECOMMEND_NORMAL.equals(goodsCategory.getIsRecommend())) {
				GoodsCategory category1 = goodsCategoryMapper.selectByPrimaryKey(goodsCategory.getId());
				Integer categoySort1 = category1.getCategorySort();
				goodsCategory.setModifiedTime(new Date());

				if (categoySort1.equals(goodsCategory.getCategorySort())) {
					goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
				} else {
					GoodsCategoryExample example = new GoodsCategoryExample();
					example.createCriteria().andCategorySortEqualTo(goodsCategory.getCategorySort());
					GoodsCategory category = goodsCategoryMapper.selectByExample(example).get(0);
					category.setCategorySort(categoySort1);
					goodsCategoryMapper.updateByPrimaryKeySelective(category);
				}

				if (StringUtils.isNotBlank(brand)) {
					String[] split = brand.split(";");
					for (String name : split) {
						GoodsBrand goodsBrand = new GoodsBrand();
						goodsBrand.setName(name);
						goodsBrand.setCreateTime(new Date());
						goodsBrand.setCategoryId(goodsCategory.getId());
						goodsBrandMapper.insert(goodsBrand);
					}
				}
				if (StringUtils.isNotBlank(skuKey)) {
					String[] keys = skuKey.split(";");
					for (String key : keys) {
						GoodsSkuKey skuKeyValue = new GoodsSkuKey();
						skuKeyValue.setCategoryId(goodsCategory.getId());
						skuKeyValue.setSkuKey(key);
						keyMapper.insertSelective(skuKeyValue);
					}
				}
			}
			if (GoodsConst.GOODS_CATEGORY_IS_RECOMMEND_SUBSCRIPTION.equals(goodsCategory.getIsRecommend())) {
				goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
				if (StringUtils.isNotBlank(brand)) {
					String[] split = brand.split(";");
					for (String name : split) {
						GoodsBrand goodsBrand = new GoodsBrand();
						goodsBrand.setName(name);
						goodsBrand.setCreateTime(new Date());
						goodsBrand.setCategoryId(goodsCategory.getId());
						goodsBrandMapper.insert(goodsBrand);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("修改失败");
		}

		return JsonData.setSuccessMessage();
	}

	/**
	 * 根据分类id查询分类信息
	 */
	public JsonData getCategoryDetailByCategoryId(Integer categoryId) {
		if (categoryId == null || categoryId <= 0) {
			return JsonData.setErrorMessage("参数错误");
		}

		GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
		return JsonData.setSuccessMessage(goodsCategory);
	}

	/**
	 * 获取分类下拉菜单列表
	 */
	public JsonData getCategorySimpleDetail(Byte isRecommend) {
		GoodsCategoryExample example = new GoodsCategoryExample();
		example.createCriteria().andStatusEqualTo(StatusConst.CATEGORY_STATUS_ON).andIsRecommendEqualTo(isRecommend);
		List<GoodsCategory> list = goodsCategoryMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			List<Map<String, Object>> cate = new ArrayList<Map<String, Object>>();
			for (GoodsCategory goodsCategory : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("categoryId", goodsCategory.getId());
				map.put("name", goodsCategory.getName());

				cate.add(map);
			}
			return JsonData.setSuccessMessage(cate);
		}
		return JsonData.setSuccessMessage();
	}

}
