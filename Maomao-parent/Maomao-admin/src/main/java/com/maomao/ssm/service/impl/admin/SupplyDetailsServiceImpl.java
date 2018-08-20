package com.maomao.ssm.service.impl.admin;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.AdminDataRole;
import com.maomao.ssm.bean.AdminDataRoleExample;
import com.maomao.ssm.bean.AdminInfo;
import com.maomao.ssm.bean.AdminInfoExample;
import com.maomao.ssm.bean.DataRole;
import com.maomao.ssm.bean.Goods;
import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsAddressExample;
import com.maomao.ssm.bean.GoodsApply;
import com.maomao.ssm.bean.GoodsApplyExample;
import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.bean.GoodsExample.Criteria;
import com.maomao.ssm.bean.GoodsService;
import com.maomao.ssm.bean.GoodsSku;
import com.maomao.ssm.bean.GoodsSkuExample;
import com.maomao.ssm.bean.GoodsWarehouse;
import com.maomao.ssm.bean.GoodsWarehouseExample;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.RoleConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.AdminDataRoleMapper;
import com.maomao.ssm.dao.AdminInfoMapper;
import com.maomao.ssm.dao.DataRoleMapper;
import com.maomao.ssm.dao.GoodsAddressMapper;
import com.maomao.ssm.dao.GoodsApplyMapper;
import com.maomao.ssm.dao.GoodsCategoryMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsServiceMapper;
import com.maomao.ssm.dao.GoodsSkuMapper;
import com.maomao.ssm.dao.GoodsWarehouseMapper;
import com.maomao.ssm.pojo.GoodsAndWarehouse;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.pojo.SupplyQueryVo;
import com.maomao.ssm.service.admin.SupplyDetailsService;
import com.maomao.ssm.utils.ListUtils;

/**
 * @author:wzy
 * @descrption:货源总览
 * @version:
 * @date:2018年3月7日
 */
@Service
public class SupplyDetailsServiceImpl implements SupplyDetailsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsCategoryMapper categoryMapper;
	@Autowired
	private AdminInfoMapper adminInfoMapper;
	@Autowired
	private GoodsWarehouseMapper goodsWarehouseMapper;
	@Autowired
	private GoodsServiceMapper goodsServiceMapper;
	@Autowired
	private GoodsApplyMapper goodsApplyMapper;
	@Autowired
	private GoodsSkuMapper skuMapper;
	@Autowired
	private GoodsAddressMapper goodsAddressMapper;
	@Autowired
	private AdminDataRoleMapper adminDataRoleMapper;
	@Autowired
	private DataRoleMapper dataRoleMapper;

	/**
	 * 查看货源总览列表
	 */
	public JsonData getSubpplyDetail(Integer pages, Integer rows, Integer categoryId, Integer startStock,
			Integer endStock, Byte goodsProperty, Byte goodsStatus, String goodsName, Byte type, Integer adminId) {
		if (pages < 1 || rows < 1) {
			pages = 1;
			rows = 20;
		}

		List<SupplyQueryVo> returnList = new ArrayList<SupplyQueryVo>();
		// goodsProperty : -1 认筹商品
		/*
		 * if (goodsProperty != null && goodsProperty == -1) { Page pageHelper =
		 * PageHelper.startPage(pages, rows); GoodsSubscriptionExample example = new
		 * GoodsSubscriptionExample();
		 * com.maomao.ssm.bean.GoodsSubscriptionExample.Criteria criteria =
		 * example.createCriteria(); if (categoryId != null) {
		 * criteria.andCategoryIdEqualTo(categoryId); } if (startStock != null) {
		 * criteria.andStockGreaterThanOrEqualTo(startStock); } if (endStock != null) {
		 * criteria.andStockLessThanOrEqualTo(endStock); } if (goodsStatus != null) { if
		 * (goodsStatus != GoodsConst.GOODS_SUBSCRIPTION_STATUS_SOLD_OUT) {
		 * criteria.andSubscriptionStatusEqualTo(goodsStatus); } else {
		 * criteria.andStockEqualTo(0); } }
		 * 
		 * List<GoodsSubscription> list =
		 * goodsSubscriptionMapper.selectByExample(example); if (list != null &&
		 * list.size() > 0) { for (GoodsSubscription goodsSubscription : list) {
		 * GoodsCategory goodsCategory =
		 * categoryMapper.selectByPrimaryKey(goodsSubscription.getCategoryId()); String
		 * categoryName = goodsCategory == null ? null : goodsCategory.getName();
		 * AdminInfo adminInfo =
		 * adminInfoMapper.selectByPrimaryKey(goodsSubscription.getAdminId()); String
		 * owner = adminInfo == null ? null : adminInfo.getCompanyName();
		 * 
		 * // 认筹商品id Integer id = goodsSubscription.getId(); GoodsWarehouseExample
		 * warehouseExample = new GoodsWarehouseExample();
		 * warehouseExample.createCriteria().andBizIdEqualTo(id).andCategoryEqualTo(
		 * GoodsConst.GOODS_CATEGORY_SUBSCRIPTION); // 获取仓储对象 List<GoodsWarehouse>
		 * goodsWarehouseList = goodsWarehouseMapper.selectByExample(warehouseExample);
		 * Byte warehouseType = null;// 仓储类型 if (goodsWarehouseList != null &&
		 * goodsWarehouseList.size() > 0) { GoodsWarehouse goodsWarehouse =
		 * goodsWarehouseList.get(0); warehouseType = goodsWarehouse.getType(); } if
		 * (type != null && warehouseType != null) { if (type != warehouseType) {
		 * continue; } }
		 * 
		 * //自取地址 GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
		 * goodsAddressExample.createCriteria().andBizIdEqualTo(id).andCategoryEqualTo(
		 * GoodsConst.GOODS_CATEGORY_SUBSCRIPTION); List<GoodsAddress> goodsAddressList
		 * = goodsAddressMapper.selectByExample(goodsAddressExample);
		 * 
		 * SupplyQueryVo vo = new SupplyQueryVo(goodsSubscription, categoryName, owner,
		 * warehouseType); if (goodsAddressList != null && goodsAddressList.size() > 0)
		 * { if (goodsAddressList.get(0) != null) {
		 * vo.setGoodsAddress(goodsAddressList.get(0)); } } returnList.add(vo); } }
		 * 
		 * PageBean pageBean = new PageBean(); pageBean.setTotal(pageHelper.getTotal());
		 * pageBean.setRows(returnList); return JsonData.setSuccessMessage(pageBean); }
		 */

		GoodsExample example = new GoodsExample();
		example.setOrderByClause("crate_time desc");
		Criteria criteria = example.createCriteria();

		AdminDataRoleExample adminDataRoleExample = new AdminDataRoleExample();
		adminDataRoleExample.createCriteria().andAdminIdEqualTo(adminId);
		List<AdminDataRole> adminDataRoles = adminDataRoleMapper.selectByExample(adminDataRoleExample);
		if (adminDataRoles == null || adminDataRoles.size() == 0) {
			return JsonData.setErrorMessage("未分配数据权限");
		}
		DataRole dataRole = dataRoleMapper.selectByPrimaryKey(adminDataRoles.get(0).getDataRoleId());
		if (dataRole == null) {
			return JsonData.setErrorMessage("未分配数据权限");
		}
		if (RoleConst.DATA_ROLE_TYPE_PART.equals(dataRole.getType())) {
			criteria.andAdminIdIn(
					ListUtils.getIntegerListFromString(dataRole.getScope(), StatusConst.IMG_SPLIT_STRING));
		}
		if (RoleConst.DATA_ROLE_TYPE_SELF.equals(dataRole.getType())) {
			criteria.andAdminIdEqualTo(adminId);
		}

		// GoodsSubscriptionExample example2 = new GoodsSubscriptionExample();
		// com.maomao.ssm.bean.GoodsSubscriptionExample.Criteria criteria2 =
		// example2.createCriteria();
		if (categoryId != null) {
			criteria.andCategoryIdEqualTo(categoryId);
			// criteria2.andCategoryIdEqualTo(categoryId);

		}
		if (startStock != null) {
			criteria.andStockGreaterThanOrEqualTo(startStock);
			// criteria2.andStockGreaterThanOrEqualTo(startStock);
		}
		if (endStock != null) {
			criteria.andStockLessThanOrEqualTo(endStock);
			// criteria2.andStockLessThanOrEqualTo(endStock);
		}
		if (goodsStatus != null) {
			if (goodsStatus != GoodsConst.GOODS_STATUS_SOLD_OUT) {
				criteria.andStatusEqualTo(goodsStatus);
				// criteria2.andStockLessThanOrEqualTo(endStock);
			} else {
				criteria.andStockEqualTo(0);
				// criteria2.andStockEqualTo(0);
			}
		}
		if (goodsProperty != null) {
			criteria.andTypeEqualTo(goodsProperty);
		}
		if (StringUtils.isNotBlank(goodsName)) {
			criteria.andNameLike("%" + goodsName + "%");
			// criteria2.andNameLike("%" + goodsName + "%");
		}

		Page page = PageHelper.startPage(pages, rows);
		List<Goods> list1 = goodsMapper.selectByExample(example);
		if (list1 != null && list1.size() > 0) {
			for (Goods goods : list1) {
				GoodsCategory goodsCategory = categoryMapper.selectByPrimaryKey(goods.getCategoryId());
				String categoryName = goodsCategory == null ? null : goodsCategory.getName();
				
				AdminInfoExample example2 = new AdminInfoExample();
				example2.createCriteria().andAdminIdEqualTo(goods.getAdminId());
				 List<AdminInfo> adminInfoList = adminInfoMapper.selectByExample(example2);
				 AdminInfo adminInfo = null;
				 if (adminInfoList!=null && adminInfoList.size()>0) {
					adminInfo = adminInfoList.get(0);
				}
				 String owner = (adminInfo == null ? null : adminInfo.getCompanyName());

				// 商品id
				Integer id = goods.getId();
				GoodsWarehouseExample warehouseExample = new GoodsWarehouseExample();
				warehouseExample.createCriteria().andBizIdEqualTo(id)
						.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
				// 获取仓储对象
				List<GoodsWarehouse> goodsWarehouseList = goodsWarehouseMapper.selectByExample(warehouseExample);
				Byte warehouseType = null;// 仓储类型
				if (goodsWarehouseList != null && goodsWarehouseList.size() > 0) {
					GoodsWarehouse goodsWarehouse = goodsWarehouseList.get(0);
					warehouseType = goodsWarehouse.getType();
				}
				if (type != null && warehouseType != null) {
					if (type != warehouseType) {
						continue;
					}
				}
				GoodsSkuExample example3 = new GoodsSkuExample();
				example3.setOrderByClause("price_purchase desc");
				example3.createCriteria().andGoodsIdEqualTo(id).andStatusEqualTo((byte) 0);
				List<GoodsSku> goodsSkuList = skuMapper.selectByExample(example3);
				String maxPrice = "0.00";
				String minPrice = "0.00";
				Integer stockCount = 0;
				if (goodsSkuList != null && goodsSkuList.size() > 0) {
					maxPrice = new DecimalFormat(".00").format(((double) goodsSkuList.get(0).getPricePurchase()) / 100);
					minPrice = new DecimalFormat(".00")
							.format(((double) goodsSkuList.get(goodsSkuList.size() - 1).getPricePurchase()) / 100);
					for (GoodsSku goodsSku : goodsSkuList) {
						stockCount += goodsSku.getStock();
					}
				}
				String price = minPrice + "~" + maxPrice;
				SupplyQueryVo vo = new SupplyQueryVo(goods, categoryName, owner, warehouseType, price);

				// 自取地址
				GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
				goodsAddressExample.createCriteria().andBizIdEqualTo(id)
						.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
				List<GoodsAddress> goodsAddressList = goodsAddressMapper.selectByExample(goodsAddressExample);
				if (goodsAddressList != null && goodsAddressList.size() > 0) {
					if (goodsAddressList.get(0) != null) {
						vo.setGoodsAddress(goodsAddressList.get(0));
					}
				}
				returnList.add(vo);
			}
		}

		/*
		 * List<GoodsSubscription> list2 =
		 * goodsSubscriptionMapper.selectByExample(example2); if (list2 != null &&
		 * list2.size() > 0) { for (GoodsSubscription goodsSubscription : list2) {
		 * GoodsCategory goodsCategory =
		 * categoryMapper.selectByPrimaryKey(goodsSubscription.getCategoryId()); String
		 * categoryName = goodsCategory == null ? null : goodsCategory.getName();
		 * AdminInfo adminInfo =
		 * adminInfoMapper.selectByPrimaryKey(goodsSubscription.getAdminId()); String
		 * owner = adminInfo == null ? null : adminInfo.getCompanyName();
		 * 
		 * // 认筹商品id Integer id = goodsSubscription.getId(); GoodsWarehouseExample
		 * warehouseExample = new GoodsWarehouseExample();
		 * warehouseExample.createCriteria().andBizIdEqualTo(id).andCategoryEqualTo(
		 * GoodsConst.GOODS_CATEGORY_SUBSCRIPTION); // 获取仓储对象 List<GoodsWarehouse>
		 * goodsWarehouseList = goodsWarehouseMapper.selectByExample(warehouseExample);
		 * Byte warehouseType = null;// 仓储类型 if (goodsWarehouseList != null &&
		 * goodsWarehouseList.size() > 0) { GoodsWarehouse goodsWarehouse =
		 * goodsWarehouseList.get(0); warehouseType = goodsWarehouse.getType(); } if
		 * (type != null) { if (type != warehouseType) { continue; } } SupplyQueryVo vo
		 * = new SupplyQueryVo(goodsSubscription, categoryName, owner, warehouseType);
		 * //自取地址 GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
		 * goodsAddressExample.createCriteria().andBizIdEqualTo(id).andCategoryEqualTo(
		 * GoodsConst.GOODS_CATEGORY_SUBSCRIPTION); List<GoodsAddress> goodsAddressList
		 * = goodsAddressMapper.selectByExample(goodsAddressExample); if
		 * (goodsAddressList != null && goodsAddressList.size() > 0) { if
		 * (goodsAddressList.get(0) != null) {
		 * vo.setGoodsAddress(goodsAddressList.get(0)); } } returnList.add(vo); } }
		 */

		PageBean pageBean = new PageBean();
		PageInfo<Goods> info = new PageInfo<Goods>(list1);
		/*
		 * if (returnList != null && returnList.size() > 0) { Integer total =
		 * returnList.size();// 总记录数 Integer startIndex = (pages - 1) * rows;// 起始索引
		 * List<SupplyQueryVo> subList = returnList.subList(startIndex,
		 * pages*rows>total?total:(startIndex + rows)); pageBean.setTotal(total);
		 * pageBean.setRows(subList); }
		 */
		pageBean.setTotal(page.getTotal());
		pageBean.setRows(returnList);
		return JsonData.setSuccessMessage(pageBean);
	}

	/**
	 * 根据商品id查询指定货源
	 */
	@Override
	public JsonData searchSubpplyDetail(Integer goodsId) {
		if (goodsId == null)
			return JsonData.setErrorMessage("参数非法");
		// 商品对象
		GoodsWithBLOBs goodsWithBLOBs = goodsMapper.selectByPrimaryKey(goodsId);
		if (goodsWithBLOBs == null) {
			return JsonData.setErrorMessage("商品不存在");
		}

		String reason = null;
		// 查看商品拒绝理由
		GoodsApplyExample example1 = new GoodsApplyExample();
		example1.setOrderByClause("create_time desc");
		example1.createCriteria().andGoodsIdEqualTo(goodsId);
		List<GoodsApply> goodsApplyList = goodsApplyMapper.selectByExample(example1);
		if (goodsApplyList != null && goodsApplyList.size() > 0) {
			GoodsApply goodsApply = goodsApplyList.get(0);
			reason = goodsApply.getReason();
		}

		// 获取商品服务id
		String serviceId = goodsWithBLOBs.getServiceId();
		List<String> goodsServiceName = new ArrayList<>();
		if (!StringUtils.isBlank(serviceId)) {
			String[] services = serviceId.split(";");
			for (String s : services) {
				GoodsService goodsService = goodsServiceMapper.selectByPrimaryKey(Integer.parseInt(s));
				goodsServiceName.add(goodsService.getName() == null ? "" : goodsService.getName());
			}
		}
		// 规格
		GoodsSkuExample example2 = new GoodsSkuExample();
		example2.createCriteria().andGoodsIdEqualTo(goodsId);
		List<GoodsSku> goodsSkusList = skuMapper.selectByExample(example2);

		// 仓库对象
		GoodsWarehouseExample example = new GoodsWarehouseExample();
		example.createCriteria().andBizIdEqualTo(goodsId).andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
		// 仓库对象
		List<GoodsWarehouse> goodsWarehouseList = goodsWarehouseMapper.selectByExample(example);
		GoodsWarehouse goodsWarehouse = null;
		if (goodsWarehouseList != null && goodsWarehouseList.size() > 0) {
			goodsWarehouse = goodsWarehouseList.get(0);
		}

		// 自取地址
		GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
		goodsAddressExample.createCriteria().andBizIdEqualTo(goodsId)
				.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
		List<GoodsAddress> goodsAddrezz = goodsAddressMapper.selectByExample(goodsAddressExample);
		GoodsAddress goodsAddress = null;
		if (goodsAddrezz != null && goodsAddrezz.size() > 0) {
			goodsAddress = goodsAddrezz.get(0);
		}

		// 构造返回值
		GoodsAndWarehouse goodsAndWarehouse = new GoodsAndWarehouse(goodsWithBLOBs, goodsWarehouse, goodsServiceName);
		goodsAndWarehouse.setSkuList(goodsSkusList);
		goodsAndWarehouse.setGoodsAddress(goodsAddress);
		goodsAndWarehouse.setReason(reason);
		return JsonData.setSuccessMessage(goodsAndWarehouse);
	}

	/**
	 * 根据商品id修改指定货源
	 */
	@Override
	public JsonData supdateSubpplyDetail(Integer goodsId, String goodsName, Integer categoryId, Integer stock,
			Integer stockSham, Long pricePurchase, Long priceSales, Integer brandId, String param, String serviceId,
			Byte payType, Byte getWay, Integer category, Byte type, String province, String city, String area,
			String address, String name, String mobile, Byte flag) {
		if (goodsId == null || StringUtils.isBlank(goodsName) || categoryId == null || stock == null
				|| pricePurchase == null || payType == null || StringUtils.isBlank(name) || StringUtils.isBlank(mobile))
			return JsonData.setErrorMessage("参数非法");

		// 商品对象
		GoodsWithBLOBs goodsWithBLOBs = new GoodsWithBLOBs();
		goodsWithBLOBs.setId(goodsId);
		goodsWithBLOBs.setName(goodsName);
		goodsWithBLOBs.setCategoryId(categoryId);
		goodsWithBLOBs.setStock(stock);
		goodsWithBLOBs.setStockSham(stockSham);
		if (pricePurchase != null) {
			goodsWithBLOBs.setPricePurchase(pricePurchase);
		}

		goodsWithBLOBs.setBrandId(brandId);
		goodsWithBLOBs.setParam(param);
		goodsWithBLOBs.setServiceId(serviceId);
		goodsWithBLOBs.setPayType(payType);
		goodsWithBLOBs.setGetWay(getWay);
		goodsWithBLOBs.setModifiedTime(new Date());
		if (flag != null && flag == 0) {
			goodsWithBLOBs.setPriceSales(priceSales);
			goodsWithBLOBs.setStatus(GoodsConst.GOODS_STATUS_ON_SALE);
		}

		GoodsWarehouseExample example = new GoodsWarehouseExample();
		example.createCriteria().andBizIdEqualTo(goodsId).andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
		// 仓库对象
		GoodsWarehouse goodsWarehouse = goodsWarehouseMapper.selectByExample(example).get(0);
		goodsWarehouse.setBizId(goodsId);
		goodsWarehouse.setCategory(category);
		goodsWarehouse.setType(type);
		goodsWarehouse.setProvince(province);
		goodsWarehouse.setCity(city);
		goodsWarehouse.setArea(area);
		goodsWarehouse.setAddress(address);
		goodsWarehouse.setName(name);
		goodsWarehouse.setMobile(mobile);

		try {
			goodsMapper.updateByPrimaryKeySelective(goodsWithBLOBs);
			goodsWarehouseMapper.updateByPrimaryKeySelective(goodsWarehouse);
			GoodsAndWarehouse goodsAndWarehouse = new GoodsAndWarehouse(goodsWithBLOBs, goodsWarehouse);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("修改失败");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 上架,商品
	 */
	@Override
	public JsonData upSubpplyDetail(Integer goodsId) {

		if (goodsId == null)
			return JsonData.setErrorMessage("参数非法");

		try {
			GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(goodsId);
			goods.setStatus(GoodsConst.GOODS_STATUS_ON_SALE);
			goodsMapper.updateByPrimaryKeySelective(goods);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 解除质押商品
	 */
	@Override
	public JsonData delPledge(Integer goodsId) {
		if (goodsId == null)
			return JsonData.setErrorMessage("参数非法");

		try {
			GoodsWithBLOBs goodsWithBLOBs = goodsMapper.selectByPrimaryKey(goodsId);
			if (goodsWithBLOBs != null) {
				// 解除质押
				if (goodsWithBLOBs.getType() == GoodsConst.GOODS_TYPE_MORTGAGE
						|| goodsWithBLOBs.getType() == GoodsConst.GOODS_TYPE_VR_MORTGAGE)
					goodsMapper.deleteByPrimaryKey(goodsId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("解除质押失败");
		}
		return JsonData.setSuccessMessage();
	}
}
