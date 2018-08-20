package com.maomao.ssm.service.impl.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.maomao.ssm.bean.Goods;
import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.bean.GoodsHome;
import com.maomao.ssm.bean.GoodsHomeExample;
import com.maomao.ssm.bean.GoodsHomepageCategory;
import com.maomao.ssm.bean.GoodsHomepageCategoryExample;
import com.maomao.ssm.bean.GoodsOverstock;
import com.maomao.ssm.bean.GoodsOverstockExample;
import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.bean.HomeContent;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.dao.GoodsHomeMapper;
import com.maomao.ssm.dao.GoodsHomeMapperCustom;
import com.maomao.ssm.dao.GoodsHomepageCategoryMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsOverstockMapper;
import com.maomao.ssm.pojo.GoodsPojo;
import com.maomao.ssm.pojo.GoodsHomeList;
import com.maomao.ssm.pojo.GoodsHomepageCategoryPojo;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.GoodsHomeService;

@Service
public class GoodsHomeServiceImpl implements GoodsHomeService {
	@Autowired
	private GoodsHomeMapper goodsHomeMapper;
	@Autowired
	private GoodsHomeMapperCustom goodsHomeMapperCustom;
	@Autowired
	private GoodsOverstockMapper goodsOverstockMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsHomepageCategoryMapper goodsHomepageCategoryMapper;

	/**
	 * 首页分类商品添加
	 */
	@Override
	public JsonData addGoodsHomepageCategory(Integer goodsHomepageCategoryId ,String name, String img) {
		if (name == null || img == null) {
			return JsonData.setErrorMessage("参数为null");
		}
		if (goodsHomepageCategoryId == null) {
			GoodsHomepageCategory goodsHomepageCategory = new GoodsHomepageCategory();
			goodsHomepageCategory.setName(name);
			goodsHomepageCategory.setImg(img);
			goodsHomepageCategory.setStatus((byte) 1);
			Integer goodsHomepageCategorySort = goodsHomeMapperCustom.getGoodsHomepageCategorySort();
			Integer sort = null;
			if (goodsHomepageCategorySort == null) {
				sort = 1;
			} else {
				sort = goodsHomepageCategorySort;
				sort++;
			}
			goodsHomepageCategory.setSort(sort);
			goodsHomepageCategory.setCreateTime(new Date());
			goodsHomepageCategory.setModifiedTime(new Date());
			goodsHomepageCategoryMapper.insertSelective(goodsHomepageCategory);
		}
		if (goodsHomepageCategoryId != null) {
			GoodsHomepageCategory goodsHomepageCategory = goodsHomepageCategoryMapper.selectByPrimaryKey(goodsHomepageCategoryId);
			goodsHomepageCategory.setName(name);
			goodsHomepageCategory.setImg(img);
			goodsHomepageCategory.setStatus((byte) 1);
			goodsHomepageCategory.setModifiedTime(new Date());
			goodsHomepageCategoryMapper.updateByPrimaryKeySelective(goodsHomepageCategory);
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 分类下移
	 */
	public JsonData setHomePageCategoryDown(Integer homepageCategoryId) {
		if (homepageCategoryId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsHomepageCategory currentHomepageCategory = goodsHomepageCategoryMapper
				.selectByPrimaryKey(homepageCategoryId);
		if (currentHomepageCategory != null && currentHomepageCategory.getSort() == 1) {
			return JsonData.setErrorMessage("已经在尾位");
		}
		Integer sort =  currentHomepageCategory.getSort();
		GoodsHomepageCategoryExample example = new GoodsHomepageCategoryExample();
		example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON)
				.andSortEqualTo(sort - 1);

		List<GoodsHomepageCategory> downHomepageCategoryList = goodsHomepageCategoryMapper.selectByExample(example);
		if (downHomepageCategoryList.size() <= 0 || downHomepageCategoryList == null) {
			return JsonData.setErrorMessage("已经在尾位");
		}
		GoodsHomepageCategory downHomepageCategory = downHomepageCategoryList.get(0);
		Integer sort2 = downHomepageCategory.getSort();
		
		downHomepageCategory.setSort(sort);
		downHomepageCategory.setModifiedTime(new Date());

		currentHomepageCategory.setSort(sort2);
		currentHomepageCategory.setModifiedTime(new Date());

		try {
			goodsHomepageCategoryMapper.updateByPrimaryKeySelective(currentHomepageCategory);
			goodsHomepageCategoryMapper.updateByPrimaryKeySelective(downHomepageCategory);
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
	}

	/**
	 * 分类上移
	 */
	public JsonData setHomePageCategoryUp(Integer homepageCategoryId) {
		if (homepageCategoryId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		try {
		// 获取当前首页分类对象
		GoodsHomepageCategory currentHomepageCategory = goodsHomepageCategoryMapper
				.selectByPrimaryKey(homepageCategoryId);

		Integer sort =  currentHomepageCategory.getSort();
		GoodsHomepageCategoryExample example = new GoodsHomepageCategoryExample();
		example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON)
				.andSortEqualTo(sort + 1);

		List<GoodsHomepageCategory> upHomepageCategoryList = goodsHomepageCategoryMapper.selectByExample(example);
		if (upHomepageCategoryList.size() <= 0 || upHomepageCategoryList == null) {
			return JsonData.setErrorMessage("已经在首位");
		}

		GoodsHomepageCategory upHomepageCategory = upHomepageCategoryList.get(0);
		Integer sort2 = upHomepageCategory.getSort();
		// 交换位置
		upHomepageCategory.setSort(sort);
		upHomepageCategory.setModifiedTime(new Date());

		currentHomepageCategory.setSort(sort2);
		currentHomepageCategory.setModifiedTime(new Date());

		
		goodsHomepageCategoryMapper.updateByPrimaryKeySelective(currentHomepageCategory);
		goodsHomepageCategoryMapper.updateByPrimaryKeySelective(upHomepageCategory);
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
	 public JsonData setHomePageCategoryTop(Integer homepageCategoryId) {
	 if (homepageCategoryId == null) {
	 return JsonData.setErrorMessage("参数错误");
	 }
	
	 GoodsHomepageCategory currentHomepageCategory = goodsHomepageCategoryMapper.selectByPrimaryKey(homepageCategoryId);
	 Integer sort = currentHomepageCategory.getSort();
	 
	 GoodsHomepageCategoryExample example = new GoodsHomepageCategoryExample();
	 example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON).andSortLessThan(sort);
	 List<GoodsHomepageCategory> topList = goodsHomepageCategoryMapper.selectByExample(example);
	 
	 Integer maxSolr = topList.get(0).getSort();//定义最大值为该数组的第一个数
	 if (topList != null && topList.size() > 0) {
		 for (int i = 0; i < topList.size(); i++) {
			if (maxSolr < topList.get(i).getSort()) {
				maxSolr = topList.get(i).getSort();
			}
		}
	 }
	 currentHomepageCategory.setSort(maxSolr);//置顶
	 goodsHomepageCategoryMapper.updateByPrimaryKeySelective(currentHomepageCategory);
	 
	 for (GoodsHomepageCategory goodsHomepageCategory : topList) {
		 Integer sort2 = goodsHomepageCategory.getSort();
		 if (sort2 > sort) {
			sort2--;
			goodsHomepageCategory.setSort(sort2);
			goodsHomepageCategoryMapper.updateByPrimaryKeySelective(goodsHomepageCategory);
		}
	}
	 return JsonData.setSuccessMessage();
	 }

	/**
	 * 删除分类,并且删除分类下面关联的商品
	 */
	public JsonData delHomePageCategoryById(Integer homepageCategoryId) {
		if (homepageCategoryId == null) {
			return JsonData.setErrorMessage("参数错误");
		}

		GoodsHomepageCategory homepageCategory = goodsHomepageCategoryMapper.selectByPrimaryKey(homepageCategoryId);
		homepageCategory.setId(homepageCategoryId);
		homepageCategory.setStatus(GoodsConst.GOODS_CATEGORY_STATUS_DEL);

		// 查询出大于删除sort的所有对象
		GoodsHomepageCategoryExample example = new GoodsHomepageCategoryExample();
		example.createCriteria().andSortGreaterThan(homepageCategory.getSort())
				.andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON);
		List<GoodsHomepageCategory> homepageCategoryList = goodsHomepageCategoryMapper.selectByExample(example);
		try {
			if (homepageCategoryList != null && homepageCategoryList.size() > 0) {
				for (GoodsHomepageCategory goodsHomepageCategory : homepageCategoryList) {
					goodsHomepageCategory.setSort(goodsHomepageCategory.getSort() - 1);
					goodsHomepageCategory.setModifiedTime(new Date());
					goodsHomepageCategoryMapper.updateByPrimaryKeySelective(goodsHomepageCategory);
				}
			}
			goodsHomepageCategoryMapper.updateByPrimaryKeySelective(homepageCategory);

			// 查询出此分类下的首页商品
			GoodsHomeExample homeExample = new GoodsHomeExample();
			homeExample.createCriteria().andGoodsHomepageCategoryIdEqualTo(homepageCategoryId);
			List<GoodsHome> goodsHomeList = goodsHomeMapper.selectByExample(homeExample);
			if (goodsHomeList.size() <= 0 || goodsHomeList == null) {
				// 如果首页商品不存在直接结束
				return JsonData.setSuccessMessage();
			}
			// 删除首页商品
			goodsHomeMapper.deleteByExample(homeExample);

			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("参数错误");
		}

	}

	/**
	 * 首页商品添加
	 * 
	 * @param goodsHomepageCategoryId
	 * @param goodsId 首页商品对象id
	 * @return
	 * @return
	 */
	@Override
	public JsonData addGoodsHome(Integer goodsHomepageCategoryId, Integer[] goodsIds, Integer category, Byte type) {
		if (type == 0) {// 单品
			for (Integer goodsId : goodsIds) {
				GoodsHome goodsHome = new GoodsHome();
				goodsHome.setGoodsId(goodsId);
				goodsHome.setGoodsHomepageCategoryId(goodsHomepageCategoryId);
				goodsHome.setCategory(category);
				GoodsHome goodsHomeSort = goodsHomeMapperCustom.getGoodsHomeSort();
				Integer sort = null;
				if (goodsHomeSort.getSort() == null) {
					sort = 1;
				} else {
					sort = goodsHomeSort.getSort();
					sort++;
				}
				goodsHome.setSort(sort);// 排序字段
				goodsHome.setCreateTime(new Date());
				goodsHome.setModifiedTime(new Date());
				goodsHomeMapper.insertSelective(goodsHome);
			}
			return JsonData.setSuccessMessage();
		}

		if (type == 1 &&  category == 1) {// 分类    下普通商品
			for (Integer goodsCategoryId : goodsIds) {
				// 获取分类下的普通商品列表
				GoodsExample example = new GoodsExample();
				example.createCriteria().andCategoryIdEqualTo(goodsCategoryId)
						.andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
				List<Goods> goodsList = goodsMapper.selectByExample(example);
				if (goodsList.size() <= 0) {
					return JsonData.setErrorMessage("商品列表为空");
				}
				for (Goods goods : goodsList) {
					Integer goodsId = goods.getId();
					GoodsHome goodsHome = new GoodsHome();
					goodsHome.setGoodsId(goodsId);
					goodsHome.setGoodsHomepageCategoryId(goodsHomepageCategoryId);
					goodsHome.setCategory(category);
					GoodsHome goodsHomeSort = goodsHomeMapperCustom.getGoodsHomeSort();
					Integer sort = null;
					if (goodsHomeSort.getSort() == null) {
						sort = 1;
					} else {
						sort = goodsHomeSort.getSort();
						sort++;
					}
					goodsHome.setSort(sort);// 排序字段
					goodsHome.setCreateTime(new Date());
					goodsHome.setModifiedTime(new Date());
					goodsHomeMapper.insertSelective(goodsHome);
				}
				return JsonData.setSuccessMessage();
			}
		}
		if (type == 1 && category == 2) {//分类    下买断商品
			GoodsOverstockExample overstockExample = new GoodsOverstockExample();
			overstockExample.createCriteria().andStatusEqualTo(GoodsConst.GOODS_OVERSTOCK_STATUS_ON_SALE);
			List<GoodsOverstock> goodsOverstockList = goodsOverstockMapper.selectByExample(overstockExample);
			if (goodsOverstockList.size() <= 0) {
				return JsonData.setErrorMessage("买断商品列表为空");
			}
			for (GoodsOverstock goodsOverstock : goodsOverstockList) {
				Integer goodsId = goodsOverstock.getId();
				GoodsHome goodsHome = new GoodsHome();
				goodsHome.setGoodsId(goodsId);
				goodsHome.setGoodsHomepageCategoryId(goodsHomepageCategoryId);
				goodsHome.setCategory(category);
				GoodsHome goodsHomeSort = goodsHomeMapperCustom.getGoodsHomeSort();
				Integer sort = null;
				if (goodsHomeSort.getSort() == null) {
					sort = 1;
				} else {
					sort = goodsHomeSort.getSort();
					sort++;
				}
				goodsHome.setSort(sort);// 排序字段
				goodsHome.setCreateTime(new Date());
				goodsHome.setModifiedTime(new Date());
				goodsHomeMapper.insertSelective(goodsHome);
			}
			return JsonData.setSuccessMessage();
		}
		return JsonData.setErrorMessage("添加失败");
	}

	/**
	 * 首页商品上移
	 * 
	 * @param goodsHomepageCategoryId
	 * @param goodsId 首页商品对象id
	 */
	@Override
	public JsonData setGoodsHomeUp(Integer goodsHomepageCategoryId, Integer goodsId, Integer category) {
		if (goodsHomepageCategoryId == null) {
			return JsonData.setErrorMessage("首页分类id不存在");
		}
		if (goodsId == null) {
			return JsonData.setErrorMessage("商品id不存在");
		}

		// 获取当前首页商品对象
		GoodsHomeExample goodsHomeExample = new GoodsHomeExample();
		goodsHomeExample.createCriteria().andGoodsHomepageCategoryIdEqualTo(goodsHomepageCategoryId)
				.andIdEqualTo(goodsId).andCategoryEqualTo(category);
		List<GoodsHome> goodsHomeList = goodsHomeMapper.selectByExample(goodsHomeExample);

		// 获取最大的sort对象
		GoodsHome goodsHomeSort = goodsHomeMapperCustom.getGoodsHomeSort();

		if (goodsHomeList.size() < 0 || goodsHomeList == null) {
			return JsonData.setErrorMessage("首页商品为空");
		}
		GoodsHome goodsHome = goodsHomeList.get(0);
		Integer sort = goodsHome.getSort();
		if (sort == null || sort == goodsHomeSort.getSort()) {
			return JsonData.setErrorMessage("sort为空或者sort已经在首位");
		}

		GoodsHomeExample goodsHomeExample2 = new GoodsHomeExample();
		goodsHomeExample2.createCriteria().andSortEqualTo(sort + 1);
		List<GoodsHome> goodsHomeList2 = goodsHomeMapper.selectByExample(goodsHomeExample2);
		if (goodsHomeList2.size() <= 0 || goodsHomeList2 == null) {
			return JsonData.setErrorMessage("首页商品为空");
		}
		GoodsHome goodsHome2 = goodsHomeList2.get(0);
		// 进行上移
		goodsHome.setSort(goodsHome2.getSort());
		goodsHome.setModifiedTime(new Date());
		goodsHome2.setSort(sort);
		goodsHome2.setModifiedTime(new Date());
		goodsHomeMapper.updateByPrimaryKeySelective(goodsHome);
		goodsHomeMapper.updateByPrimaryKeySelective(goodsHome2);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 首页商品下移
	 * 
	 * @param goodsHomepageCategoryId
	 * @param goodsId
	 */
	@Override
	public JsonData setGoodsHomeDown(Integer goodsHomepageCategoryId, Integer goodsId, Integer category) {
		if (goodsHomepageCategoryId == null) {
			return JsonData.setErrorMessage("首页分类id不存在");
		}
		if (goodsId == null) {
			return JsonData.setErrorMessage("商品id不存在");
		}

		// 获取当前首页商品对象
		GoodsHomeExample goodsHomeExample = new GoodsHomeExample();
		goodsHomeExample.createCriteria().andGoodsHomepageCategoryIdEqualTo(goodsHomepageCategoryId)
				.andIdEqualTo(goodsId).andCategoryEqualTo(category);
		List<GoodsHome> goodsHomeList = goodsHomeMapper.selectByExample(goodsHomeExample);

		GoodsHome goodsHomeSort = goodsHomeMapperCustom.getGoodsHomeMinSort();

		if (goodsHomeList.size() < 0 || goodsHomeList == null) {
			return JsonData.setErrorMessage("首页商品为空");
		}
		GoodsHome goodsHome = goodsHomeList.get(0);
		Integer sort = goodsHome.getSort();
		if (sort == null || sort == goodsHomeSort.getSort()) {
			return JsonData.setErrorMessage("sort为空或者sort已经在尾位");
		}

		GoodsHomeExample goodsHomeExample2 = new GoodsHomeExample();
		goodsHomeExample2.createCriteria().andSortEqualTo(sort - 1);
		List<GoodsHome> goodsHomeList2 = goodsHomeMapper.selectByExample(goodsHomeExample2);
		if (goodsHomeList2.size() <= 0 || goodsHomeList2 == null) {
			return JsonData.setErrorMessage("首页商品为空");
		}
		GoodsHome goodsHome2 = goodsHomeList2.get(0);
		// 进行下移
		goodsHome.setSort(goodsHome2.getSort());
		goodsHome.setModifiedTime(new Date());
		goodsHome2.setSort(sort);
		goodsHome2.setModifiedTime(new Date());
		goodsHomeMapper.updateByPrimaryKeySelective(goodsHome);
		goodsHomeMapper.updateByPrimaryKeySelective(goodsHome2);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 首页商品置顶
	 */
	@Override
	public JsonData setGoodsHomeTop(Integer goodsHomepageCategoryId, Integer goodsId, Integer category) {
		if (goodsHomepageCategoryId == null) {
			return JsonData.setErrorMessage("首页分类id不存在");
		}
		if (goodsId == null) {
			return JsonData.setErrorMessage("商品id不存在");
		}
		// 获取当前首页商品对象
		GoodsHomeExample goodsHomeExample = new GoodsHomeExample();
		goodsHomeExample.createCriteria().andGoodsHomepageCategoryIdEqualTo(goodsHomepageCategoryId)
				.andIdEqualTo(goodsId).andCategoryEqualTo(category);
		List<GoodsHome> goodsHomeList = goodsHomeMapper.selectByExample(goodsHomeExample);

		if (goodsHomeList.size() <= 0 || goodsHomeList == null) {
			return JsonData.setErrorMessage("首页商品为空");
		}
		GoodsHome goodsHome = goodsHomeList.get(0);
		Integer sort = goodsHome.getSort();

		// 获取最大的sort对象
		GoodsHome goodsHomeSort = goodsHomeMapperCustom.getGoodsHomeSort();
		Integer maxSort = goodsHomeSort.getSort();
		if (sort == maxSort) {
			return JsonData.setErrorMessage("已经在首位");
		}

		// 获取所有首页商品
		// GoodsHomeExample goodsHomeExample2 = new GoodsHomeExample();
		List<GoodsHome> list = goodsHomeMapper.selectByExample(null);
		if (list.size() <= 0 || list == null) {
			return JsonData.setErrorMessage("首页商品为空");
		}

		// 把需要置顶的sort上面的sort全部-1
		for (GoodsHome goodsHome2 : list) {
			Integer sort2 = goodsHome2.getSort();
			if (sort2 > sort) {
				sort2--;
				goodsHome2.setSort(sort2);
				goodsHome2.setModifiedTime(new Date());
				goodsHomeMapper.updateByPrimaryKeySelective(goodsHome2);
			}
		}
		goodsHome.setSort(maxSort);
		goodsHome.setModifiedTime(new Date());
		goodsHomeMapper.updateByPrimaryKeySelective(goodsHome);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 删除首页商品对象
	 * 
	 * @param goodsHomepageCategoryId
	 * @param goodsId 首页商品对象id
	 * @return
	 */
	@Override
	public JsonData delGoodsHome(Integer goodsHomepageCategoryId, Integer goodsId, Integer category) {
		if (goodsHomepageCategoryId == null) {
			return JsonData.setErrorMessage("首页分类id不存在");
		}
		if (goodsId == null) {
			return JsonData.setErrorMessage("商品id不存在");
		}

		// 获取当前首页商品对象
		GoodsHomeExample goodsHomeExample = new GoodsHomeExample();
		goodsHomeExample.createCriteria().andGoodsHomepageCategoryIdEqualTo(goodsHomepageCategoryId)
				.andIdEqualTo(goodsId).andCategoryEqualTo(category);
		List<GoodsHome> goodsHomeList = goodsHomeMapper.selectByExample(goodsHomeExample);
		if (goodsHomeList.size() <= 0 || goodsHomeList == null) {
			return JsonData.setErrorMessage("首页商品为空");
		}
		GoodsHome goodsHome = goodsHomeList.get(0);
		Integer sort = goodsHome.getSort();
		goodsHomeMapper.deleteByPrimaryKey(goodsHome.getId());

		// 删除后,大于被删除的sort,全部减一
		GoodsHomeExample goodsHomeExample2 = new GoodsHomeExample();
		List<GoodsHome> GoodsHomeList = goodsHomeMapper.selectByExample(goodsHomeExample2);
		for (GoodsHome goodsHome2 : GoodsHomeList) {
			Integer sort2 = goodsHome2.getSort();
			if (sort2 > sort) {
				sort2--;
				goodsHome2.setSort(sort2);
				goodsHome2.setModifiedTime(new Date());
				goodsHomeMapper.updateByPrimaryKeySelective(goodsHome2);
			}
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 查询首页商品列表
	 * @return
	 */
	@Override
	public JsonData getGoodsHomeAll() {
		try {
			//获取首页分类id
			List<Integer> list = goodsHomeMapperCustom.getGoodsHomeList();
			if (list.size() < 0 || list == null) {
				return JsonData.setErrorMessage("未查询到数据");
			}
			GoodsHomeList goodsHomeList = new GoodsHomeList();
			List<GoodsHomepageCategoryPojo> goodsHomepageCategoryPojoList = new ArrayList<>();
			
			//查询分类列表
			GoodsHomepageCategoryExample categoryExample = new GoodsHomepageCategoryExample();
			categoryExample.setOrderByClause("sort desc");
			categoryExample.createCriteria().andStatusEqualTo(GoodsConst.GOODS_HOMEPAGE_CATEGORY_STATUS_ON);
			List<GoodsHomepageCategory> goodsHomepageCategoryList = goodsHomepageCategoryMapper.selectByExample(categoryExample);
			if (goodsHomepageCategoryList.size() <= 0) {
				return JsonData.setErrorMessage("首页分类不存在");
			}
			for (GoodsHomepageCategory goodsHomepageCategory : goodsHomepageCategoryList) {
				
				GoodsHomepageCategoryPojo goodsHomepageCategoryPojo = new GoodsHomepageCategoryPojo();
				goodsHomepageCategoryPojo.setGoodsHomepageCategory(goodsHomepageCategory);//添加首页分类对象
				
				//获取首页分类id
				Integer goodsHomepageCategoryId =  goodsHomepageCategory.getId();
				goodsHomepageCategoryPojo.setGoodsHomepageCategoryId(goodsHomepageCategory.getId());
				goodsHomepageCategoryPojo.setHomepageCategoryName(goodsHomepageCategory.getName());
				goodsHomepageCategoryPojo.setHomepageCategoryImg(goodsHomepageCategory.getImg());
				goodsHomepageCategoryPojo.setGoodsHomepageCategorySort(goodsHomepageCategory.getSort());
				
				// 通过指定的首页分类id查询首页商品对象
				GoodsHomeExample goodsHomeExample = new GoodsHomeExample();
				goodsHomeExample.setOrderByClause("sort desc");
				goodsHomeExample.createCriteria().andGoodsHomepageCategoryIdEqualTo(goodsHomepageCategoryId);
				
				List<GoodsPojo> list2 = new ArrayList<>();
				List<GoodsHome> goodsHomes = goodsHomeMapper.selectByExample(goodsHomeExample);

				// 通过首页商品对象获取商品对象
				for (GoodsHome goodsHome : goodsHomes) {
					Integer goodsHomeId = goodsHome.getId();
			//		System.out.println("goodsHomeId:"+goodsHomeId);
					Integer goodsId = goodsHome.getGoodsId();
					Integer category = goodsHome.getCategory();
					Integer sort = goodsHome.getSort();
					if (category == 1) { //商品性质普通
						GoodsWithBLOBs goodsWithBLOBs = goodsMapper.selectByPrimaryKey(goodsId);
						GoodsPojo goodsPojo = new GoodsPojo();
						goodsPojo.setGoodsHomeId(goodsHomeId);
						goodsPojo.setGoodsId(goodsId);
						goodsPojo.setGoodsName(goodsWithBLOBs.getName());
						goodsPojo.setMinPrice(goodsWithBLOBs.getMinPrice());
						goodsPojo.setCategory(category);//商品性质
						goodsPojo.setStock(goodsWithBLOBs.getStock());
						goodsPojo.setGoodsHomeSort(sort);
						list2.add(goodsPojo);
					}
					if (category == 2) {//买断
						GoodsOverstockWithBLOBs goodsOverstockWithBLOBs = goodsOverstockMapper.selectByPrimaryKey(goodsId);
						GoodsPojo goodsPojo = new GoodsPojo();
						goodsPojo.setGoodsHomeId(goodsHomeId);
						goodsPojo.setGoodsId(goodsId);
						goodsPojo.setGoodsName(goodsOverstockWithBLOBs.getName());
						goodsPojo.setMinPrice(goodsOverstockWithBLOBs.getPrice());
						goodsPojo.setCategory(category);//商品性质
						goodsPojo.setStock(goodsOverstockWithBLOBs.getStock());
						goodsPojo.setGoodsHomeSort(sort);
						list2.add(goodsPojo);
					}
			    }
				goodsHomepageCategoryPojo.setGoodsList(list2);
				goodsHomepageCategoryPojoList.add(goodsHomepageCategoryPojo);
			}
			goodsHomeList.setGoodsHomepageCategoryPojoList(goodsHomepageCategoryPojoList);
			return JsonData.setSuccessMessage(goodsHomepageCategoryPojoList);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("查询失败");
		}
	}

	/**
	 * 查看所有压货商品
	 */
	@Override
	public JsonData getGoodsOverstockAll() {
		GoodsOverstockExample example = new GoodsOverstockExample();
		example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_OVERSTOCK_STATUS_ON_SALE);// 已上架的

		List<GoodsOverstock> goodsOverstockList = goodsOverstockMapper.selectByExample(example);
		if (goodsOverstockList.size() <= 0) {
			return JsonData.setErrorMessage("压货商品不存在");
		}
		return JsonData.setSuccessMessage(goodsOverstockList);
	}

	/**
	 * 查询商品列表
	 */
	@Override
	public JsonData getGoodsAll() {
		GoodsExample goodsExample = new GoodsExample();
		goodsExample.createCriteria().andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);// 获取所有上架商品列表
		List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
		if (goodsList.size() > 0 && goodsList != null) {
			return JsonData.setSuccessMessage(goodsList);
		}
		return JsonData.setErrorMessage("未查询到数据");
	}

}
