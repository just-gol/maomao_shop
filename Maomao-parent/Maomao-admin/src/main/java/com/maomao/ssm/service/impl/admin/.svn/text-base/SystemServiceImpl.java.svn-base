package com.maomao.ssm.service.impl.admin;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.Banner;
import com.maomao.ssm.bean.BannerExample;
import com.maomao.ssm.bean.CouponExample;
import com.maomao.ssm.bean.CouponExample.Criteria;
import com.maomao.ssm.bean.Goods;
import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.bean.GoodsCategoryExample;
import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.bean.GoodsService;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.bean.HomeCategory;
import com.maomao.ssm.bean.HomeContent;
import com.maomao.ssm.bean.HomeContentExample;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.BannerMapper;
import com.maomao.ssm.dao.CouponMapper;
import com.maomao.ssm.dao.GoodsCategoryMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsServiceMapper;
import com.maomao.ssm.dao.HomeCategoryMapper;
import com.maomao.ssm.dao.HomeContentMapper;
import com.maomao.ssm.dao.SystemMapperCustom;
import com.maomao.ssm.pojo.HomeDetail;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.SystemService;
import com.maomao.ssm.utils.JsonUtils;

@Service
public class SystemServiceImpl implements SystemService {
	@Autowired
	private CouponMapper couponMapper;

	@Autowired
	private GoodsServiceMapper goodsServiceMapper;

	@Autowired
	private BannerMapper bannerMapper;

	// 自定义的Mapper
	@Autowired
	private SystemMapperCustom systemMapper;

	@Autowired
	private HomeCategoryMapper homeCategoryMapper;

	@Autowired
	private HomeContentMapper homecontentMapper;

	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;

	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private JedisClientPool jedisClientPool;

	/**
	 * 优惠券管理
	 */
	public JsonData getCouponList(Integer pages, Integer rows, String startDate, String endDate, String keywords) {
		if (pages < 1 || rows < 0) {
			pages = 1;
			rows = 10;
		}

		CouponExample example = new CouponExample();
		Criteria criteria = example.createCriteria();
		if (startDate != null) {
			String format = "yyyy-MM-dd HH:mm:ss";
			try {
				Date date = new SimpleDateFormat(format).parse(startDate);
				// criteria.and
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		couponMapper.selectByExample(example);
		return null;

	}

	/**
	 * 服务管理
	 */
	@Override
	public JsonData getServicManagementList(Integer pages, Integer rows) {
		// 分页
		PageHelper.startPage(pages, rows);
		List<GoodsService> goodsServiceList = goodsServiceMapper.selectByExampleWithBLOBs(null);
		if (goodsServiceList != null && goodsServiceList.size() > 0) {
			PageInfo<GoodsService> pageInfo = new PageInfo<>(goodsServiceList);
			PageBean pagebean = new PageBean();
			// 获取总条数
			pagebean.setTotal(pageInfo.getTotal());
			// 获取当前页数据
			pagebean.setRows(goodsServiceList);
			return JsonData.setSuccessMessage(pagebean);
		}
		return JsonData.setErrorMessage("未查到数据");
	}

	// 新增服务
	@Override
	public JsonData getServicManagementList(String name, String detail) {
		if (StringUtils.isBlank(name) || StringUtils.isBlank(detail))
			return JsonData.setErrorMessage("参数非法");

		try {
			// 创建服务对象
			GoodsService goodsService = new GoodsService();
			goodsService.setName(name);
			goodsService.setDetail(detail);
			goodsService.setCrateTime(new Date());
			goodsService.setStatus(StatusConst.GOODS_SERVICE_STATUS_ON); // 状态启动
			goodsServiceMapper.insertSelective(goodsService);
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("添加失败");
		}
	}

	// 启动服务
	@Override
	public JsonData startServicManagement(Integer goodsServiceId) {
		if (goodsServiceId == null)
			return JsonData.setErrorMessage("参数非法");

		GoodsService goodsService = goodsServiceMapper.selectByPrimaryKey(goodsServiceId);
		if (goodsService.getStatus() == -1)
			return JsonData.setErrorMessage("该服务已删除");

		try {
			goodsService.setStatus(StatusConst.GOODS_SERVICE_STATUS_ON); // 状态启动
			goodsService.setModifiedTime(new Date());
			goodsServiceMapper.updateByPrimaryKeySelective(goodsService);
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("启动失败");
		}
	}

	// 禁用服务
	@Override
	public JsonData stopServicManagement(Integer goodsServiceId) {
		if (goodsServiceId == null)
			return JsonData.setErrorMessage("参数非法");

		GoodsService goodsService = goodsServiceMapper.selectByPrimaryKey(goodsServiceId);
		if (goodsService.getStatus() == -1)
			return JsonData.setErrorMessage("该服务已删除");

		try {
			goodsService.setStatus(StatusConst.GOODS_SERVICE_STATUS_FORBIDEN); // 状态:禁用
			goodsService.setModifiedTime(new Date());
			goodsServiceMapper.updateByPrimaryKeySelective(goodsService);
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("禁用失败");
		}
	}

	// 删除服务
	@Override
	public JsonData delServicManagement(Integer goodsServiceId) {
		if (goodsServiceId == null)
			return JsonData.setErrorMessage("参数非法");
		try {
			goodsServiceMapper.deleteByPrimaryKey(goodsServiceId);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("删除失败");
		}
		return JsonData.setSuccessMessage();
	}

	// 编辑服务
	@Override
	public JsonData editServicManagement(Integer goodsServiceId) {
		if (goodsServiceId == null)
			return JsonData.setErrorMessage("参数非法");

		GoodsService goodsService = goodsServiceMapper.selectByPrimaryKey(goodsServiceId);
		if (goodsService.getStatus() != StatusConst.GOODS_SERVICE_STATUS_ON)
			return JsonData.setErrorMessage("该服务已停用或已被删除");

		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", goodsService.getId());
			map.put("name", goodsService.getName());
			map.put("detail", goodsService.getDetail());
			list.add(map);
			return JsonData.setSuccessMessage(list);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("编辑失败");
		}
	}

	// 修改服务
	@Override
	public JsonData updateServicManagement(Integer goodsServiceId, String name, String detail) {
		if (goodsServiceId == null || StringUtils.isBlank(name) || StringUtils.isBlank(detail))
			return JsonData.setErrorMessage("参数非法");

		try {
			// 创建服务对象
			GoodsService goodsService = goodsServiceMapper.selectByPrimaryKey(goodsServiceId);
			if (goodsService != null) {
				goodsService.setName(name);
				goodsService.setDetail(detail);
				goodsService.setModifiedTime(new Date());
				goodsServiceMapper.updateByPrimaryKeySelective(goodsService);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("修改失败");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 广告位管理
	 */

	// 新增,修改广告位,分类位
	@Override
	public JsonData addLink(Integer bannerId, String AndroidImgURL, String IosImgURL, String href, Byte type) {
		if (AndroidImgURL == null || IosImgURL == null || type == null)
			return JsonData.setErrorMessage("参数错误");
		
		try {
			if (type == StatusConst.BANNER_STATUS_START) { // 启动页
				if (jedisClientPool.exists(RedisConst.AD_BANNER + "ios")) {
					jedisClientPool.del(RedisConst.AD_BANNER + "ios");
				}
				if (jedisClientPool.exists(RedisConst.AD_BANNER + "android")) {
					jedisClientPool.del(RedisConst.AD_BANNER + "android");
				}
				if (jedisClientPool.exists(RedisConst.AD_BANNER + "default")) {
					jedisClientPool.del(RedisConst.AD_BANNER + "default");
				}
				if (bannerId == null) { // id不存在:新增
				//	Integer sort = Integer.MAX_VALUE;
					Banner banner = new Banner();
					banner.setImg(AndroidImgURL + ";" + IosImgURL);
					banner.setHref(href);
				//	banner.setType(type);// 类型 0在首页显示 1在分类显示 2在启动页
				//	banner.setSort(sort);
					banner.setType(StatusConst.BANNER_STATUS_START);//启动页显示
					banner.setStatus(StatusConst.BANNER_STATUS_ON);// 启动状态
					banner.setCreateTime(new Date());
					bannerMapper.insertSelective(banner);
				}
				if (bannerId != null) { // id存在:修改
					Banner banner = bannerMapper.selectByPrimaryKey(bannerId);
					if (banner != null) {
						banner.setImg(AndroidImgURL + ";" + IosImgURL);
						banner.setHref(href);
				//		banner.setType(type);// 类型 0在首页显示 1在分类显示 2在启动页
				//		banner.setStatus(StatusConst.BANNER_STATUS_ON);// 启动状态
						banner.setModifiedTime(new Date());
						bannerMapper.updateByPrimaryKeySelective(banner);
					}
				}
			}

			if (type == StatusConst.BANNER_STATUS_HOME) {// 广告位
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
				}
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
				}
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
				}
				if (bannerId == null) { // id不存在:新增
					Banner bannerSort = systemMapper.getAdManagementSort(type);
					Integer sort = 0;
					if (bannerSort == null) {
						sort = 0; // 如果sort是空的说明没有首页推荐位对象,设置为0
					} else {
						sort = bannerSort.getSort();
						sort++;// 否则最大的+1;
					}
					Banner banner = new Banner();
					banner.setImg(AndroidImgURL + ";" + IosImgURL);
					banner.setHref(href);
					banner.setType(type);// 类型 0在首页显示 1在分类显示 2在启动页
					banner.setSort(sort);
					banner.setStatus(StatusConst.BANNER_STATUS_ON);// 启动状态
					banner.setCreateTime(new Date());
					bannerMapper.insertSelective(banner);
				}
				if (bannerId != null) { // id存在:修改
					Banner banner = bannerMapper.selectByPrimaryKey(bannerId);
					if (banner != null) {
						banner.setImg(AndroidImgURL + ";" + IosImgURL);
						banner.setHref(href);
				//		banner.setType(type);// 类型 0在首页显示 1在分类显示 2在启动页
						banner.setStatus(StatusConst.BANNER_STATUS_ON);// 启动状态
						banner.setModifiedTime(new Date());
						bannerMapper.updateByPrimaryKeySelective(banner);
					}
				}

			} else if (type == StatusConst.BANNER_STATUS_CATEGORY) {// 分类位
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "ios")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "ios");
				}
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "android")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "android");
				}
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "default")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "default");
				}
				if (bannerId == null) { // id不存在:新增
					Banner bannerSort = systemMapper.getAdManagementSort(type);
					Integer sort = 0;
					if (bannerSort == null) {
						sort = 0; // 如果sort是空的说明没有首页推荐位对象,设置为0
					} else {
						sort = bannerSort.getSort();
						sort++;// 否则最大的+1;
					}
					Banner banner = new Banner();
					banner.setImg(AndroidImgURL + ";" + IosImgURL);
					banner.setHref(href);
					banner.setSort(sort);
					banner.setType(type);// 类型 0在首页显示 1在分类显示 2在启动页
					banner.setStatus(StatusConst.BANNER_STATUS_ON);// 启动状态
					banner.setCreateTime(new Date());
					bannerMapper.insertSelective(banner);
				}
				if (bannerId != null) { // id存在:修改
					Banner banner = bannerMapper.selectByPrimaryKey(bannerId);
					if (banner != null) {
						banner.setImg(AndroidImgURL + ";" + IosImgURL);
						banner.setHref(href);
						banner.setModifiedTime(new Date());
						bannerMapper.updateByPrimaryKeySelective(banner);
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

	// 下移
	@Override
	public JsonData setAdManagementDown(Integer bannerId, Byte type) {
		if (bannerId == null || type == null)
			return JsonData.setErrorMessage("参数非法");
		try {
			if (type == StatusConst.BANNER_STATUS_HOME) {// 广告位
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
				}
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
				}
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
				}
				Banner banner = bannerMapper.selectByPrimaryKey(bannerId); // 获取选中的对象
				Integer minSort = systemMapper.getAdManagementMinSort();
				Banner bannerDown = null;
				if (banner != null && minSort != null) {
					Integer sort = banner.getSort();
					if (sort == minSort)
						return JsonData.setErrorMessage("此广告位已在尾位");

					bannerDown = systemMapper.setAdManagementDown(sort, type);
					if (bannerDown != null) {
						Integer sortDown = bannerDown.getSort();
						banner.setSort(sortDown);
						bannerDown.setSort(sort);
					}
				}
				bannerMapper.updateByPrimaryKeySelective(banner);
				bannerMapper.updateByPrimaryKeySelective(bannerDown);
			} else if (type == StatusConst.BANNER_STATUS_CATEGORY) {// 分类位
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "ios")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "ios");
				}
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "android")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "android");
				}
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "default")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "default");
				}
				Banner banner = bannerMapper.selectByPrimaryKey(bannerId); // 获取选中的对象
		//		Integer minSort = systemMapper.getAdManagementMinSort();
				Banner bannerDown = null;
				if (banner != null) {
					Integer sort = banner.getSort();
					if (sort == 1)
						return JsonData.setErrorMessage("此广告位已在尾位");
		//			bannerDown = systemMapper.setAdManagementDown(sort, type);
					BannerExample bannerExample = new BannerExample();
					bannerExample.createCriteria().andTypeEqualTo(StatusConst.BANNER_STATUS_CATEGORY).andSortEqualTo(sort-1).andStatusEqualTo(StatusConst.BANNER_STATUS_ON);
					List<Banner> bannerList = bannerMapper.selectByExample(bannerExample);
					if (bannerList.size() <= 0) {
						return JsonData.setErrorMessage("此广告位已在尾位");
					}
					 bannerDown = bannerList.get(0);
					if (bannerDown != null) {
						Integer sortDown = bannerDown.getSort();
						banner.setSort(sortDown);
						bannerDown.setSort(sort);
					}
				}
				bannerMapper.updateByPrimaryKeySelective(banner);
				bannerMapper.updateByPrimaryKeySelective(bannerDown);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
		return JsonData.setSuccessMessage();
	}

	// 上移
	@Override
	public JsonData setAdManagementUp(Integer bannerId, Byte type) {
		if (bannerId == null || type == null)
			return JsonData.setErrorMessage("参数非法");
		try {
			if (type == StatusConst.BANNER_STATUS_HOME) {// 广告位
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
				}
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
				}
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
				}
				Integer maxSort = systemMapper.getAdManagementMaxSort();
				Banner banner = bannerMapper.selectByPrimaryKey(bannerId);
				Banner bannerUp = null;
				if (banner != null && maxSort != null) {// 类型
					Integer sort = banner.getSort();
					if (sort == maxSort)
						return JsonData.setErrorMessage("此广告位已在首位");
					bannerUp = systemMapper.setAdManagementUp(sort, type);
					if (bannerUp != null) {
						Integer sortUp = bannerUp.getSort();
						banner.setSort(sortUp);
						bannerUp.setSort(sort);
					}
				}
				bannerMapper.updateByPrimaryKeySelective(banner);
				bannerMapper.updateByPrimaryKeySelective(bannerUp);
			} else if (type == StatusConst.BANNER_STATUS_CATEGORY) {// 分类位
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "ios")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "ios");
				}
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "android")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "android");
				}
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "default")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "default");
				}
		//		Integer maxSort = systemMapper.getAdManagementMaxSort();
				Banner banner = bannerMapper.selectByPrimaryKey(bannerId);
				Banner bannerUp = null;
				if (banner != null ) {// 类型
					Integer sort = banner.getSort();
					BannerExample bannerExample = new BannerExample();
					bannerExample.createCriteria().andTypeEqualTo(StatusConst.BANNER_STATUS_CATEGORY).andSortEqualTo(sort+1).andStatusEqualTo(StatusConst.BANNER_STATUS_ON);
					List<Banner> bannerList = bannerMapper.selectByExample(bannerExample);
					if (bannerList.size() <= 0) {
						return JsonData.setErrorMessage("此广告位已在首位");
					}
					bannerUp = bannerList.get(0);
					if (bannerUp != null) {
						Integer sortUp = bannerUp.getSort();
						banner.setSort(sortUp);
						bannerUp.setSort(sort);
					}
				}
				bannerMapper.updateByPrimaryKeySelective(banner);
				bannerMapper.updateByPrimaryKeySelective(bannerUp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
		return JsonData.setSuccessMessage();
	}

	// 置顶
	@Override
	public JsonData setAdManagementTop(Integer bannerId, Byte type) {
		if (bannerId == null)
			return JsonData.setErrorMessage("参数非法");
		try {
			if (type == StatusConst.BANNER_STATUS_HOME) {// 广告位
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
				}
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
				}
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
				}
				Banner banner = bannerMapper.selectByPrimaryKey(bannerId); // 查询当前对象
				Banner bannerTop = systemMapper.getAdManagementTop(); // 获得排在最前面的对象
				List<Banner> bannerList = bannerMapper.selectByExample(null);
				if (banner != null && bannerTop != null) {
					Integer sort = banner.getSort();
					Integer sortTop = bannerTop.getSort();
					if (bannerList != null && bannerList.size() > 0) {
						for (Banner banner2 : bannerList) {
							if (banner2.getSort() > sort && (banner2.getType() == StatusConst.BANNER_STATUS_HOME)) {
								Integer sort2 = banner2.getSort();
								sort2--;
								banner2.setSort(sort2);
								bannerMapper.updateByPrimaryKey(banner2);
							}
						}
					}
					banner.setSort(sortTop);
			//		bannerTop.setSort(sort);
				}
				bannerMapper.updateByPrimaryKeySelective(banner);
		//		bannerMapper.updateByPrimaryKeySelective(bannerTop);
			} else if (type == StatusConst.BANNER_STATUS_CATEGORY) {// 分类位
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "ios")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "ios");
				}
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "android")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "android");
				}
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "default")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "default");
				}
				Banner banner = bannerMapper.selectByPrimaryKey(bannerId); // 查询当前对象
				Banner bannerTop = systemMapper.getClassifyManagementTop(); // 获得排在最前面的对象
				
				BannerExample bannerExample = new BannerExample();
				bannerExample.createCriteria().andTypeEqualTo(StatusConst.BANNER_STATUS_CATEGORY).andStatusEqualTo(StatusConst.BANNER_STATUS_ON);
				List<Banner> bannerList = bannerMapper.selectByExample(bannerExample);
				if (bannerList.size() <= 0) {
					return JsonData.setErrorMessage("分类位不存在");
				}
				if (banner != null && bannerTop != null) {
					Integer sort = banner.getSort();
					Integer sortTop = bannerTop.getSort();
					if (bannerList != null && bannerList.size() > 0) {
						for (Banner banner2 : bannerList) {
							if (banner2.getSort() > sort ) {
								Integer sort2 = banner2.getSort();
								sort2--;
								banner2.setSort(sort2);
								bannerMapper.updateByPrimaryKey(banner2);
							}
						}
					}
					banner.setSort(sortTop);
		//			bannerTop.setSort(sort);
				}
				bannerMapper.updateByPrimaryKeySelective(banner);
	//			bannerMapper.updateByPrimaryKeySelective(bannerTop);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
		return JsonData.setSuccessMessage();
	}
	// 查询广告位,启动页,分类页
	@Override
	public JsonData getAdManagementList(Byte type) {
		if (type != null) {
			if (type == StatusConst.BANNER_STATUS_CATEGORY) {// 分类位
					BannerExample bannerExample = new BannerExample();
					bannerExample.createCriteria().andTypeEqualTo(type).andStatusEqualTo(StatusConst.BANNER_STATUS_ON);
					bannerExample.setOrderByClause("sort desc");
					List<Banner> bannerList = bannerMapper.selectByExample(bannerExample);
					List<Map<String,Object>> mapList = new ArrayList<>();
					if (bannerList != null && bannerList.size() > 0) {
						for (Banner banner : bannerList) {
							Map<String,Object> map = new HashMap<String,Object>();
							Integer categoryId = null;//分类id
							String href = banner.getHref();
							if (StringUtils.isNotBlank(href) && !(href.startsWith("http"))) {
								int indexOf = href.indexOf("=");
								int beginIndex = href.indexOf("?");
								String sub = href.substring(beginIndex+1, indexOf);
								 if (sub.equalsIgnoreCase("goodsId")) {
									 Integer goodsId = Integer.parseInt(href.substring(indexOf + 1));// 获取用户商品id
									 GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(goodsId);
									 if (goods != null) {
										 categoryId = goods.getCategoryId();
										 map.put("href","商品详情链接"+"("+goods.getName()+")" );
										 map.put("categoryId",categoryId );
									 }
								}else if(sub.equalsIgnoreCase("categoryId")){
									 categoryId = Integer.parseInt(href.substring(indexOf + 1));// 获取分类商品id
									 GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
									 if (goodsCategory != null) {
										 map.put("href","商品列表链接"+"("+goodsCategory.getName()+")");
									}
								}
							}else if (StringUtils.isNotBlank(href) && href.startsWith("http")) {
								 map.put("href","外部链接");
							}
							map.put("id", banner.getId());
							map.put("img",banner.getImg());
							map.put("modifiedTime",banner.getModifiedTime());
							map.put("sort",banner.getSort());
							map.put("status",banner.getStatus());
							map.put("type",banner.getType());
							map.put("createTime", banner.getCreateTime());
							mapList.add(map);
						}
						return JsonData.setSuccessMessage(mapList);
					}
			} else {
				List<Banner> bannerList = systemMapper.getBannerList();
				List<Map<String,Object>> mapList = new ArrayList<>();
				if (bannerList != null && bannerList.size() > 0) {
					for (Banner banner : bannerList) {
						Map<String,Object> map = new HashMap<String,Object>();
						Integer categoryId = null;//分类id
						String href = banner.getHref();
						if (StringUtils.isNotBlank(href) && !(href.startsWith("http"))) {
							int indexOf = href.indexOf("=");
							int beginIndex = href.indexOf("?");
							String sub = href.substring(beginIndex+1, indexOf);
							 if (sub.equalsIgnoreCase("goodsId")) {
								 Integer goodsId = Integer.parseInt(href.substring(indexOf + 1));// 获取用户商品id
								 GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(goodsId);
								 if (goods != null) {
									 categoryId = goods.getCategoryId();
									 map.put("href","商品详情链接"+"("+goods.getName()+")" );
									 map.put("categoryId",categoryId );
								 }
							}else if(sub.equalsIgnoreCase("categoryId")){
								categoryId = Integer.parseInt(href.substring(indexOf + 1));// 获取分类商品id
								 GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
								 if (goodsCategory != null) {
									 map.put("href","商品列表链接"+"("+goodsCategory.getName()+")");
								}
							}
						}else if (StringUtils.isNotBlank(href) && href.startsWith("http")) {//外部链接
							 map.put("href","外部链接");
						}
						map.put("id", banner.getId());
						map.put("img",banner.getImg());
						map.put("modifiedTime",banner.getModifiedTime());
						map.put("sort",banner.getSort());
						map.put("status",banner.getStatus());
						map.put("type",banner.getType());
						map.put("createTime", banner.getCreateTime());
						mapList.add(map);
					}
					return JsonData.setSuccessMessage(mapList);
				}
			}
		}
		return JsonData.setErrorMessage("查询失败");
	}

	/**
	 * 删除广告位,分类位
	 */
	@Override
	public JsonData delBanner(Integer bannerId, Byte type) {

		if (bannerId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		try {
			Banner banner = bannerMapper.selectByPrimaryKey(bannerId);
			if (banner.getType().equals(StatusConst.BANNER_STATUS_START)) { //启动页
				return JsonData.setErrorMessage("启动页不能删除!");
			}
			if (type == StatusConst.BANNER_STATUS_HOME) {// 广告位
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
				}
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
				}
				if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
					jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
				}
				bannerMapper.deleteByPrimaryKey(bannerId);
			}
			if (type == StatusConst.BANNER_STATUS_CATEGORY) { // 分类位
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "ios")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "ios");
				}
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "android")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "android");
				}
				if (jedisClientPool.exists(RedisConst.CATEGORY_DETAIL + "default")) {
					jedisClientPool.del(RedisConst.CATEGORY_DETAIL + "default");
				}
				banner = bannerMapper.selectByPrimaryKey(bannerId);
				Integer sort = banner.getSort();
				banner.setStatus(StatusConst.BANNER_STATUS_DEL);
				bannerMapper.updateByPrimaryKeySelective(banner);
				
				BannerExample bannerExample = new BannerExample();
				bannerExample.createCriteria().andTypeEqualTo(StatusConst.BANNER_STATUS_CATEGORY).andStatusEqualTo(StatusConst.BANNER_STATUS_ON);
				List<Banner> bannerList = bannerMapper.selectByExample(bannerExample);
				if (bannerList.size() <= 0) {
					return JsonData.setErrorMessage("分类位不存在");
				}
				for (Banner banner2 : bannerList) {
					Integer sort2 = banner2.getSort();
					if (sort2 > sort) {
						sort2--;
						banner2.setSort(sort2);
						banner2.setModifiedTime(new Date());
						bannerMapper.updateByPrimaryKeySelective(banner2);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("删除失败");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 推荐位管理 TODO:查询,上下移置顶没对status做判断
	 * 
	 */

	// 下移
	@Override
	public JsonData setRecommendDown(Integer homeCategoryId) {
		if (homeCategoryId == null)
			return JsonData.setErrorMessage("参数非法");

		try {
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
			}
			// 创建首页推荐位分类对象
			HomeCategory homeCategory = homeCategoryMapper.selectByPrimaryKey(homeCategoryId);
			Integer minSort = systemMapper.getRecommendMinSort();
			HomeCategory HomeCategoryDown = null;
			if (homeCategory != null && minSort != null) {
				// 获得选中对象的排序号
				Integer sort = homeCategory.getSort();
				if (sort == minSort)
					return JsonData.setErrorMessage("此广告位已在尾位");

				HomeCategoryDown = systemMapper.setRecommendDown(sort);
				if (HomeCategoryDown != null) {
					// 获得下一个对象的排序号
					Integer sortDown = HomeCategoryDown.getSort();
					// 交替位置
					homeCategory.setSort(sortDown);
					HomeCategoryDown.setSort(sort);
				}
			}
			// 更新对象
			homeCategoryMapper.updateByPrimaryKeySelective(homeCategory);
			homeCategoryMapper.updateByPrimaryKeySelective(HomeCategoryDown);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
		return JsonData.setSuccessMessage();
	}

	// 上移
	@Override
	public JsonData setRecommendUp(Integer homeCategoryId) {
		if (homeCategoryId == null)
			return JsonData.setErrorMessage("参数非法");

		try {
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
			}

			// 创建首页推荐位分类对象
			HomeCategory homeCategory = homeCategoryMapper.selectByPrimaryKey(homeCategoryId);
			HomeCategory HomeCategoryUp = null;
			if (homeCategory != null) {
				// 获得选中对象的排序号
				Integer sort = homeCategory.getSort();
				HomeCategoryUp = systemMapper.setRecommendUp(sort);
				if (HomeCategoryUp != null) {
					// 获得上一个对象的排序号
					Integer sortUp = HomeCategoryUp.getSort();
					// 交替位置
					homeCategory.setSort(sortUp);
					HomeCategoryUp.setSort(sort);
				}
			}
			// 更新对象
			homeCategoryMapper.updateByPrimaryKeySelective(homeCategory);
			homeCategoryMapper.updateByPrimaryKeySelective(HomeCategoryUp);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
		return JsonData.setSuccessMessage();
	}

	// 置顶
	@Override
	public JsonData setRecommendTop(Integer homeCategoryId) {
		if (homeCategoryId == null)
			return JsonData.setErrorMessage("参数非法");

		try {
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
			}
			// 创建首页推荐位分类对象
			HomeCategory homeCategory = homeCategoryMapper.selectByPrimaryKey(homeCategoryId);
			HomeCategory HomeCategoryTop = systemMapper.getRecommendTop();
			List<HomeCategory> homeCategoryList = homeCategoryMapper.selectByExample(null);
			if (homeCategory != null && HomeCategoryTop != null) {
				// 获得选中对象的排序号
				Integer sort = homeCategory.getSort();
				// 获得第一个对象的排序号
				Integer sortTop = HomeCategoryTop.getSort();
				
				if (homeCategoryList != null && homeCategoryList.size() > 0) {
					for (HomeCategory homeCategory2 : homeCategoryList) {
						if (homeCategory2.getSort() > sort && (homeCategory2.getStatus() == StatusConst.CATEGORY_STATUS_ON)) {
							Integer sort2 = homeCategory2.getSort();
							sort2--;
							homeCategory2.setSort(sort2);
							homeCategoryMapper.updateByPrimaryKey(homeCategory2);
						}
					}
				}
				homeCategory.setSort(sortTop);
//				HomeCategoryTop.setSort(sort);
			}
			// 更新对象
			homeCategoryMapper.updateByPrimaryKeySelective(homeCategory);
//			homeCategoryMapper.updateByPrimaryKeySelective(HomeCategoryTop);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
		return JsonData.setSuccessMessage();
	}

	// 查询推荐位
	@Override
	public JsonData getRecommendList() {
		// 首页推荐位分类列表
		List<HomeCategory> homeCategoryList = systemMapper.getRecommendLists();

		// 内容分类
		HomeContentExample contentExample = null;

		List<HomeDetail> homeDetailList = new ArrayList<>();
		// 遍历首页推荐位分类
		if (homeCategoryList != null && homeCategoryList.size() > 0) {
			for (HomeCategory homeCategory : homeCategoryList) {
				if (homeCategory != null) {
					HomeDetail homeDetail = new HomeDetail();
					Integer homeCategoryId = homeCategory.getId();

					String url = homeCategory.getUrl();
					int indexOf = url.indexOf("=");
					int goodsCategoryId = Integer.parseInt(url.substring(indexOf + 1)); // 商品分类id

					// System.out.println("goodsCategoryId====" +
					// goodsCategoryId);

					contentExample = new HomeContentExample();
					contentExample.createCriteria().andCategoryIdEqualTo(homeCategoryId);

					// 获取首页推荐位内容列表
					List<HomeContent> homeContentList = homecontentMapper.selectByExample(contentExample);
					if (homeCategoryList != null && homeCategoryList.size() > 0) {
						homeDetail.setHomeCategory(homeCategory);
						homeDetail.setGoodsCategoryId(goodsCategoryId);
						homeDetail.setHomeContentList(homeContentList);
						homeDetailList.add(homeDetail);
						// }
					} else {
						homeDetail.setHomeCategory(homeCategory);
						homeDetail.setGoodsCategoryId(goodsCategoryId);
						homeDetailList.add(homeDetail);
					}
				}
			}
		}
		return JsonData.setSuccessMessage(homeDetailList);
	}

	// 新增推荐位
	@Override
	public JsonData addRecommend(Integer goodsCategoryId, Integer HomeCategoryId, Byte imgType, String categoryName,
			String introduction, String homeContentBeanList) {
		try {
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
		}

		// 判断参数是否为null
		if (goodsCategoryId == null || imgType == null || StringUtils.isBlank(categoryName))
			return JsonData.setErrorMessage("参数非法");

		try {
			// 获取最大的sort
			HomeCategory HomeCategoryTop = systemMapper.getRecommendTop();
			Integer sort = 0;
			if (HomeCategoryTop == null) {
				sort = 0; // 如果sort是空的说明没有首页推荐位对象,设置为0
			} else {
				sort = HomeCategoryTop.getSort();
				sort++;// 否则最大的+1;
			}
			HomeCategory homeCategory = new HomeCategory();
			homeCategory.setImgType(imgType);
			homeCategory.setSort(sort);
			homeCategory.setIntroduction(introduction);// 简介

			List<HomeCategory> homeCategoryList = homeCategoryMapper.selectByExample(null); // 首页推荐位列表
			for (HomeCategory homeCategory2 : homeCategoryList) {
				// 判断新增的推荐位名称和商品分类是否已经存在
				String url = homeCategory2.getUrl();
				int indexOf = url.indexOf("=");
				if (homeCategory2.getName().equals(categoryName)
						|| Integer.parseInt(url.substring(indexOf + 1)) == goodsCategoryId) {
					return JsonData.setErrorMessage("推荐位名称和商品分类已经存在!");
				}
			}

			homeCategory.setName(categoryName);
			// 拼接url
			homeCategory.setUrl("inapp://goodsList?categoryId=" + goodsCategoryId);
			homeCategory.setCreateTime(new Date());
			homeCategory.setStatus(StatusConst.CATEGORY_STATUS_ON);// 启动
			homeCategoryMapper.insert(homeCategory);

			// Integer homeCategoryId = homeCategory.getId();
			//
			// System.out.println("homeCategoryId:" + homeCategoryId);
			// List<HomeContent> homeContentBeanLists =
			// JsonUtils.jsonToList(homeContentBeanList, HomeContent.class);
			//
			// for (HomeContent homeContent : homeContentBeanLists) {
			// homeContent.setCategoryId(homeCategoryId);
			// homeContent.setCreateTime(new Date());
			// homeContent.setStatus(StatusConst.HOME_CONTENT_STATUS_ON);// 启动
			// homecontentMapper.insertSelective(homeContent);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("添加失败");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 保存推荐位
	 * 
	 * @param HomeCategoryId
	 * @param goodsCategoryId
	 *            可能改变过的商品分类id
	 * @return
	 */

	@Override
	public JsonData saveRecommend(Integer HomeCategoryId, Integer goodsCategoryId, String categoryName, Byte imgType,
			String introduction, String homeContentBeanList) {
		try {
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
		}

		// 判断参数是否为null
		if (StringUtils.isBlank(categoryName))
			return JsonData.setErrorMessage("参数非法");

		// 从首页商品分类对象中获取商品分类id
		HomeCategory homeCategory = homeCategoryMapper.selectByPrimaryKey(HomeCategoryId);
		String url = homeCategory.getUrl();
		int indexOf = url.indexOf("=");
		Integer goodsCategoryId2 = Integer.parseInt(url.substring(indexOf + 1));

		List<HomeCategory> homeCategoryList = homeCategoryMapper.selectByExample(null); // 首页推荐位列表
		if (goodsCategoryId != goodsCategoryId2) { // 商品分类改变
			for (HomeCategory homeCategory2 : homeCategoryList) {// 遍历首页推荐位列表判断改变的goodsCategoryId是否已经存在
				String url2 = homeCategory2.getUrl();
				indexOf = url2.indexOf("=");
				Integer goodsCategoryId3 = Integer.parseInt(url2.substring(indexOf + 1));
				if (goodsCategoryId == goodsCategoryId3) {
					return JsonData.setErrorMessage("分类已经存在");
				}
			}
		}
		// 进行修改
		homeCategory.setName(categoryName);
		homeCategory.setUrl("inapp://goodsList?categoryId=" + goodsCategoryId);
		homeCategory.setImgType(imgType);
		homeCategory.setIntroduction(introduction);
		homeCategory.setModifiedTime(new Date());
		homeCategoryMapper.updateByPrimaryKey(homeCategory);

		// 改变分类时,删除原有分类信息
		if (goodsCategoryId != goodsCategoryId2) {// 商品分类改变
			Integer id = homeCategory.getId();// 首页推荐位分类id
			HomeContentExample homeContentExample = new HomeContentExample();
			homeContentExample.createCriteria().andCategoryIdEqualTo(id);
			homecontentMapper.deleteByExample(homeContentExample);// 删除所有首页推荐位内容

			List<HomeContent> homeContentBeanLists = JsonUtils.jsonToList(homeContentBeanList, HomeContent.class);
			for (HomeContent homeContent : homeContentBeanLists) { // TODO:取出url中的商品信息,查询出商品价格设置到推荐位内容中
				String contentUrl = homeContent.getUrl();
				indexOf = contentUrl.indexOf("=");
				Integer goodsId = Integer.parseInt(contentUrl.substring(indexOf + 1));// 获取用户商品id
				GoodsWithBLOBs goods = null;
				if (goodsId != null)
					goods = goodsMapper.selectByPrimaryKey(goodsId);
				Long minPrice = goods.getMinPrice();// 商品售价
				homeContent.setCategoryId(HomeCategoryId);
				homeContent.setPrice(minPrice);
				homeContent.setCreateTime(new Date());
				homeContent.setStatus(StatusConst.HOME_CONTENT_STATUS_ON);// 启动
				homecontentMapper.insertSelective(homeContent);
			}
			return JsonData.setSuccessMessage();
		}
		// 判断首页推荐位内容是新增还是修改,判断是否有HomeContentId
		List<HomeContent> homeContentBeanLists = JsonUtils.jsonToList(homeContentBeanList, HomeContent.class);

		for (HomeContent homeContent : homeContentBeanLists) {
			Integer contentId = homeContent.getId();
			if (contentId == null) {
				String contentUrl = homeContent.getUrl();
				indexOf = contentUrl.indexOf("=");
				Integer goodsId = Integer.parseInt(contentUrl.substring(indexOf + 1));// 获取用户商品id
				GoodsWithBLOBs goods = null;
				if (goodsId != null)
					goods = goodsMapper.selectByPrimaryKey(goodsId);
				Long minPrice = goods.getMinPrice();// 商品售价
				homeContent.setCategoryId(HomeCategoryId);
				homeContent.setPrice(minPrice);
				homeContent.setModifiedTime(new Date());
				homeContent.setStatus(StatusConst.HOME_CONTENT_STATUS_ON);// 启动
				homecontentMapper.insertSelective(homeContent);
			} else {
				String contentUrl = homeContent.getUrl();
				indexOf = contentUrl.indexOf("=");
				Integer goodsId = Integer.parseInt(contentUrl.substring(indexOf + 1));// 获取用户商品id
				GoodsWithBLOBs goods = null;
				if (goodsId != null)
					goods = goodsMapper.selectByPrimaryKey(goodsId);
				Long minPrice = goods.getMinPrice();// 商品售价
				homeContent.setCategoryId(HomeCategoryId);
				homeContent.setPrice(minPrice);
				homeContent.setModifiedTime(new Date());
				homeContent.setStatus(StatusConst.HOME_CONTENT_STATUS_ON);// 启动
				homecontentMapper.updateByPrimaryKeySelective(homeContent);
			}
		}
		return JsonData.setSuccessMessage();
	}

	// 删除推荐位
	@Override
	public JsonData delRecommend(Integer categoryId) {
		try {
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
		}

		if (categoryId == null)
			return JsonData.setErrorMessage("参数非法");

		// 分类内容对象
		HomeContentExample contentExample = new HomeContentExample();
		contentExample.createCriteria().andCategoryIdEqualTo(categoryId);

		homeCategoryMapper.deleteByPrimaryKey(categoryId);
		homecontentMapper.deleteByExample(contentExample);
		return JsonData.setSuccessMessage("删除成功");
	}

	/**
	 * 删除推荐位内容
	 */
	@Override
	public JsonData delContent(Integer homeContentId) {

		if (homeContentId == null)
			return JsonData.setErrorMessage("参数非法");
		try {
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "ios")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "ios");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "android")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "android");
			}
			if (jedisClientPool.exists(RedisConst.HOME_DETAIL + "default")) {
				jedisClientPool.del(RedisConst.HOME_DETAIL + "default");
			}
			homecontentMapper.deleteByPrimaryKey(homeContentId);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("删除失败");
		}
		return JsonData.setSuccessMessage("删除成功");
	}

	/**
	 * 获取分类下的商品
	 */
	@Override
	public JsonData getGoodsList(Integer goodsCategoryId) {
		List<Goods> goodsList = goodsMapper.selectByExample(null);
		List<Goods> list = new ArrayList<>();
		if (goodsList != null && goodsList.size() > 0) {
			for (Goods goods : goodsList) {
				if (goods.getCategoryId() == goodsCategoryId) {
					list.add(goods);
				}
			}
		}
		return JsonData.setSuccessMessage(list);
	}

	/**
	 * 查询商品列表
	 */
	@Override
	public JsonData getGoodsDetailList(String queryString) {
		GoodsExample goodsExample = new GoodsExample();
		com.maomao.ssm.bean.GoodsExample.Criteria criteria = goodsExample.createCriteria();
		criteria.andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE).andStatusNotEqualTo(GoodsConst.GOODS_STATUS_DELETED);
		if (queryString != null) {
			try {
				queryString = new String(queryString.getBytes("iso8859-1"),"UTF-8");
				criteria.andNameLike("%"+queryString+"%");
			} catch (UnsupportedEncodingException e) {
			}
		}
		
		List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
//		List<Map<String,Object>> list = new ArrayList<>();
//		if (goodsList != null && goodsList.size() > 0) {
//			for (Goods goods : goodsList) {
//				Map<String,Object> map = new HashMap();
//				map.put("goodsId",goods.getId());
//				map.put("goodsName", goods.getName());
//				map.put("goodsType", goods.getType());
//				list.add(map);
//			}
//		}
		return JsonData.setSuccessMessage(goodsList);
	}

}
