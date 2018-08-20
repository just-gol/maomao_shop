package com.maomao.ssm.service.impl.admin;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
import com.maomao.ssm.bean.GoodsService;
import com.maomao.ssm.bean.GoodsServiceExample;
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
import com.maomao.ssm.dao.GoodsBrandMapper;
import com.maomao.ssm.dao.GoodsCategoryMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsMapperCustom;
import com.maomao.ssm.dao.GoodsServiceMapper;
import com.maomao.ssm.dao.GoodsSkuMapper;
import com.maomao.ssm.dao.GoodsWarehouseMapper;
import com.maomao.ssm.pojo.GoodsAndWarehouse;
import com.maomao.ssm.pojo.GoodsMapperBean;
import com.maomao.ssm.pojo.GoodsWithBLOBsList;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.SelfItemService;
import com.maomao.ssm.utils.JsonUtils;
import com.maomao.ssm.utils.ListUtils;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年3月6日
 */
@Service
public class SelfItemServiceImpl implements SelfItemService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	@Autowired
	private GoodsServiceMapper goodsServiceMapper;
	@Autowired
	private GoodsApplyMapper goodsApplyMapper;
	@Autowired
	private GoodsWarehouseMapper goodsWarehouseMapper;
	@Autowired
	private GoodsMapperCustom goodsMapperCustom;
	@Autowired
	private GoodsAddressMapper goodsAddressMapper;
	@Autowired
	private GoodsSkuMapper skuMapper;
	@Autowired
	private AdminInfoMapper adminInfoMapper;
	@Autowired
	private AdminDataRoleMapper adminDataRoleMapper;
	@Autowired
	private DataRoleMapper dataRoleMapper;

	/**
	 * 获取商品列表
	 */
	public JsonData getSelfItemList(Integer pages, Integer rows, Integer categoryId, Byte status, Byte getWay,
			String name, byte type, Byte warehouseType, Integer adminId) {

		Integer startRows = rows * (pages - 1);// 当前页

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
		if (RoleConst.DATA_ROLE_TYPE_SELF.equals(dataRole.getType())) {
			dataRole.setScope(adminId + "");
		}
		String scope = null;
		if (dataRole.getScope() != null) {
			scope = dataRole.getScope().replace(StatusConst.IMG_SPLIT_STRING, ",");
		}

		GoodsMapperBean goodsMapperBean = new GoodsMapperBean(pages, rows, startRows, categoryId, warehouseType, type,
				name, getWay, status, dataRole.getType(), scope);

		List<GoodsWithBLOBsList> goodsWithBLOBsList = goodsMapperCustom.getGoodsList(goodsMapperBean);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		if (goodsWithBLOBsList != null && goodsWithBLOBsList.size() > 0) {
			for (GoodsWithBLOBsList goodsWithBLOBs : goodsWithBLOBsList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("goodsId", goodsWithBLOBs.getGoodsId());
				map.put("goodsName", goodsWithBLOBs.getGoodsName());
				// 商品性质
				if (goodsWithBLOBs.getType() == GoodsConst.GOODS_TYPE_MORTGAGE) {
					// 商品质押
					map.put("property", "商品质押");
				}
				if (goodsWithBLOBs.getType() == GoodsConst.GOODS_TYPE_VR_MORTGAGE) {
					// 虚拟质押
					map.put("property", "虚拟质押");
				}

				categoryId = goodsWithBLOBs.getCategoryId();
				// 查询下拉列表选中的商品分类名称
				GoodsCategory category = goodsCategoryMapper.selectByPrimaryKey(categoryId);
				map.put("category", category.getName()); // 分类名称

				GoodsSkuExample example2 = new GoodsSkuExample();
				example2.setOrderByClause("price_purchase desc");
				example2.createCriteria().andGoodsIdEqualTo(goodsWithBLOBs.getGoodsId()).andStatusEqualTo((byte) 0);
				List<GoodsSku> goodsSkuList = skuMapper.selectByExample(example2);
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

				map.put("price", minPrice + "~" + maxPrice); // 零售价
				map.put("stock", stockCount);// 库存

				Byte goodsStatus = goodsWithBLOBs.getStatus(); // 状态
				if (goodsStatus == GoodsConst.GOODS_STATUS_OFF_SHELF) {
					// 下架
					map.put("statusName", "未上架");
				}
				if (goodsStatus == GoodsConst.GOODS_STATUS_OFF_SHELF_CHECKING) {
					// 审核中
					map.put("statusName", "审核中");
				}
				if (goodsStatus == GoodsConst.GOODS_STATUS_ON_SALE) {
					// 已上架
					map.put("statusName", "已上架");
				}
				if (goodsStatus == GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED) {
					// 审核失败
					map.put("statusName", "审核驳回");
				}
				// 质押时间
				map.put("limitTime", goodsWithBLOBs.getLimitTime());
				returnList.add(map);
			}
		}
		// 获得总记录数
		Integer total = goodsMapperCustom.getGoodsCount(goodsMapperBean);
		PageBean pageBean = new PageBean();
		pageBean.setTotal(total);
		pageBean.setRows(returnList);
		return JsonData.setSuccessMessage(pageBean);
	}

	/**
	 * 根据商品id查询商品详情
	 */
	public JsonData getItemById(Integer itemId) {
		if (itemId == null || itemId <= 0) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsAndWarehouse goodsAndWarehouse = new GoodsAndWarehouse();
		try {
			GoodsWithBLOBs goodsWithBLOBs = goodsMapper.selectByPrimaryKey(itemId);

			GoodsWarehouseExample example = new GoodsWarehouseExample();
			example.createCriteria().andBizIdEqualTo(itemId).andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
			// 仓库对象
			List<GoodsWarehouse> goodsWarehouseList = goodsWarehouseMapper.selectByExample(example);

			// 商品规格
			GoodsSkuExample example2 = new GoodsSkuExample();
			example2.createCriteria().andGoodsIdEqualTo(itemId);
			List<GoodsSku> goodsSkusList = skuMapper.selectByExample(example2);

			// 自取地址
			GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
			goodsAddressExample.createCriteria().andBizIdEqualTo(itemId)
					.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
			List<GoodsAddress> goodsAddressList = goodsAddressMapper.selectByExample(goodsAddressExample);

			// 下架驳回理由
			GoodsApplyExample applyExample = new GoodsApplyExample();
			applyExample.setOrderByClause("create_time desc");
			applyExample.createCriteria().andGoodsIdEqualTo(itemId)
					.andStatusEqualTo(GoodsConst.GOODS_APPLY_ONSALE_FAILED);
			List<GoodsApply> applyList = goodsApplyMapper.selectByExample(applyExample);
			if (goodsWithBLOBs != null) {// 如果下架驳回对象存在
				goodsAndWarehouse.setGoods(goodsWithBLOBs);
			}
			if (applyList != null && applyList.size() > 0) {
				GoodsApply goodsApply = applyList.get(0);
				goodsAndWarehouse.setReason(goodsApply.getReason());
			}
			if (goodsWarehouseList != null && goodsWarehouseList.size() > 0) {
				goodsAndWarehouse.setGoodsWarehouse(goodsWarehouseList.get(0));
			}
			if (goodsSkusList != null && goodsSkusList.size() > 0) {
				goodsAndWarehouse.setSkuList(goodsSkusList);
			}
			if (goodsAddressList != null && goodsAddressList.size() > 0) {
				goodsAndWarehouse.setGoodsAddress(goodsAddressList.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("查询失败");
		}
		return JsonData.setSuccessMessage(goodsAndWarehouse);
	}

	/**
	 * 根据商品id查询商品服务项
	 */
	public JsonData getItemService(Integer itemId) {
		if (itemId == null || itemId <= 0) {
			return JsonData.setErrorMessage("参数错误");
		}

		GoodsWithBLOBs goodsWithBLOBs = goodsMapper.selectByPrimaryKey(itemId);
		List<String> list = new ArrayList<String>();
		if (goodsWithBLOBs != null) {
			String goodsServiceId = goodsWithBLOBs.getServiceId();
			if (StringUtils.isNotBlank(goodsServiceId)) {
				String[] ids = goodsServiceId.split(StatusConst.GOODS_SERVICE_ID_SPLIT_STRING);
				for (String id : ids) {
					GoodsService goodsService = goodsServiceMapper.selectByPrimaryKey(Integer.parseInt(id));
					list.add(goodsService.getName());
				}
				return JsonData.setSuccessMessage(list);
			}
		}
		return JsonData.setErrorMessage("该商品无服务项");
	}

	/**
	 * 根据商品id查询商品
	 */
	public JsonData getItemBrand(Integer itemId) {
		return null;
	}

	/**
	 * 查询所有的服务项
	 */
	public JsonData getServiceAll() {
		GoodsServiceExample example = new GoodsServiceExample();
		example.createCriteria().andStatusEqualTo(StatusConst.GOODS_SERVICE_STATUS_ON);
		List<GoodsService> list = goodsServiceMapper.selectByExample(example);
		return JsonData.setSuccessMessage(list);
	}

	/**
	 * 添加商品
	 */
	public JsonData saveItem(GoodsWithBLOBs goods, Byte addressType, String address, String province, String city,
			String area, String mobile, String addressName, String diyAddress, String diyProvince, String diyCity,
			String diyArea, String diyMobile, String diyAddressName, Byte flag, String goodsSku) {
		if (goods == null || StringUtils.isBlank(goodsSku)) {
			return JsonData.setErrorMessage("参数错误");
		}
		Long minPrice, maxPrice;
		List<GoodsSku> skuList = JsonUtils.jsonToList(goodsSku, GoodsSku.class);
		minPrice = maxPrice = skuList.get(0).getPriceSales();
		try {
			if (goods.getId() != null) {
				Integer stockCount = 0;
				Integer salesSham = 0;
				// 插入规格
				if (goodsSku != null && skuList.size() > 0) {
					for (GoodsSku sku : skuList) {
						if (flag != null && flag == 0) {
							goods.setStatus(GoodsConst.GOODS_STATUS_ON_SALE);
							if (sku.getPriceSales() < minPrice) {
								minPrice = sku.getPriceSales();
							}
							if (sku.getPriceSales() > maxPrice) {
								maxPrice = sku.getPriceSales();
							}
							goods.setMinPrice(minPrice);
							goods.setMaxPrice(maxPrice);
							if (sku.getSalesSham() != null && sku.getSalesSham() > 0) {
								salesSham += sku.getSalesSham();
							}
							
							
						}

						stockCount += sku.getStock();
						if (sku.getId() == null) {
							sku.setGoodsId(goods.getId());
							sku.setStatus(GoodsConst.GOODS_SKU_STATUS_NORMAL);
							sku.setSales(0);
							if (sku.getSalesSham() == null) {
								sku.setSalesSham(0);
							}
							skuMapper.insertSelective(sku);
						} else {
							skuMapper.updateByPrimaryKeySelective(sku);
						}
					}
				}
				if (goods.getDeposit() == null) {
					goods.setDeposit(0L);
				}
				if (flag != null && flag == 0) {
					//插入排序
					Integer categoryId = goods.getCategoryId();
					GoodsExample goodsExample = new GoodsExample();
					goodsExample.setOrderByClause("sort desc");
					goodsExample.createCriteria().andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE).andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED).andCategoryIdEqualTo(categoryId);
					List<Goods> getGoodsList = goodsMapper.selectByExample(goodsExample);
					if (getGoodsList!=null && getGoodsList.size()>0) {
						Goods getGoods = getGoodsList.get(0);
						goods.setSort(getGoods.getSort()+1);
					}
					
					goods.setSalesSham(salesSham);
				}
				goods.setStock(stockCount);
				goods.setModifiedTime(new Date());
				goodsMapper.updateByPrimaryKeySelective(goods);
				GoodsWarehouseExample example = new GoodsWarehouseExample();
				example.createCriteria().andBizIdEqualTo(goods.getId())
						.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
				List<GoodsWarehouse> list = goodsWarehouseMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					GoodsWarehouse warehouse = list.get(0);
					warehouse.setAddress(StringUtils.isBlank(address) ? null : address);
					warehouse.setArea(StringUtils.isBlank(area) ? null : area);
					warehouse.setType(addressType == null ? null : addressType);
					warehouse.setAddress(StringUtils.isBlank(address) ? null : address);
					warehouse.setName(StringUtils.isBlank(addressName) ? null : addressName);
					warehouse.setProvince(StringUtils.isBlank(province) ? null : province);
					warehouse.setMobile(StringUtils.isBlank(mobile) ? null : mobile);
					goodsWarehouseMapper.updateByPrimaryKeySelective(warehouse);
				}

				if (goods.getGetWay() == GoodsConst.GOODS_GET_WAY_SELF
						|| goods.getGetWay() == GoodsConst.GOODS_GET_WAY_ALL) {// 自取
					GoodsAddressExample addressExample = new GoodsAddressExample();
					addressExample.createCriteria().andBizIdEqualTo(goods.getId())
							.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
					List<GoodsAddress> goodsAddressList = goodsAddressMapper.selectByExample(addressExample);
					if (goodsAddressList != null && goodsAddressList.size() > 0) {
						GoodsAddress goodsAddress = goodsAddressList.get(0);// 自取地址对象
						goodsAddress.setBizId(goods.getId());
						goodsAddress.setProvince(diyProvince);
						goodsAddress.setCity(diyCity);
						goodsAddress.setArea(diyArea);
						goodsAddress.setAddress(diyAddress);
						goodsAddress.setMobile(diyMobile);
						goodsAddress.setName(diyAddressName);
						goodsAddress.setCategory(GoodsConst.GOODS_CATEGORY_NORMAL);
						goodsAddress.setCreateTime(new Date());
						goodsAddressMapper.updateByPrimaryKeySelective(goodsAddress);
					}
				}
			} else {
				goods.setCrateTime(new Date());
				goods.setStatus(GoodsConst.GOODS_STATUS_OFF_SHELF);
				goods.setSales(0);
				goods.setSalesSham(0);
				goods.setStockSham(0);
				if (goods.getDeposit() == null) {
					goods.setDeposit(0L);
				}
				goodsMapper.insertSelective(goods);
				Integer goodsId = goods.getId(); // 商品id

				Integer stockCount = 0;
				// 插入规格
				if (goodsSku != null && skuList.size() > 0) {
					for (GoodsSku sku : skuList) {
						sku.setSales(0);
						sku.setSalesSham(0);
						sku.setStatus(GoodsConst.GOODS_SKU_STATUS_NORMAL);
						stockCount += sku.getStock();
						sku.setGoodsId(goodsId);
						skuMapper.insert(sku);
					}
				}
				goods = goodsMapper.selectByPrimaryKey(goodsId);
				goods.setStock(stockCount);
				goodsMapper.updateByPrimaryKey(goods);

				if (goods.getGetWay() == GoodsConst.GOODS_GET_WAY_SELF
						|| goods.getGetWay() == GoodsConst.GOODS_GET_WAY_ALL) {// 自取
					GoodsAddress goodsAddress = new GoodsAddress();
					goodsAddress.setBizId(goodsId);
					goodsAddress.setProvince(diyProvince);
					goodsAddress.setCity(diyCity);
					goodsAddress.setArea(diyArea);
					goodsAddress.setAddress(diyAddress);
					goodsAddress.setMobile(diyMobile);
					goodsAddress.setName(diyAddressName);
					goodsAddress.setCategory(GoodsConst.GOODS_CATEGORY_NORMAL);
					goodsAddress.setCreateTime(new Date());
					goodsAddressMapper.insertSelective(goodsAddress);
				}
				GoodsWarehouse warehouse = new GoodsWarehouse();
				warehouse.setBizId(goodsId);
				warehouse.setCategory(GoodsConst.GOODS_CATEGORY_NORMAL);// 订单类型:普通
				warehouse.setType(addressType);
				warehouse.setProvince(province);
				warehouse.setCity(city);
				warehouse.setArea(area);
				warehouse.setAddress(address);
				warehouse.setName(addressName);
				warehouse.setMobile(mobile);
				warehouse.setCreateTime(new Date());
				goodsWarehouseMapper.insertSelective(warehouse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 商品下架申请 itemIds:商品id
	 */
	public JsonData setItemOnSale(Integer[] itemIds, Integer userId) {
		if (itemIds == null || itemIds.length < 0 || userId == null || userId < 1) {
			return JsonData.setErrorMessage("参数错误");
		}
		GoodsExample goodsExample = new GoodsExample();
		goodsExample.createCriteria().andIdIn(Arrays.asList(itemIds));
		List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
		for (Goods goods : goodsList) {
			goods.setStatus(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECKING);
			goods.setModifiedTime(new Date());
			goodsMapper.updateByPrimaryKey(goods);
		}

		GoodsApplyExample example = new GoodsApplyExample();
		example.createCriteria().andUserIdEqualTo(userId).andGoodsIdIn(Arrays.asList(itemIds))
				.andStatusEqualTo(GoodsConst.GOODS_APPLY_ONSALE_CHECKING);
		List<GoodsApply> goodsApplyList = goodsApplyMapper.selectByExample(example);
		if (goodsApplyList != null && goodsApplyList.size() > 0) {
			return JsonData.setErrorMessage("已申请下架 ,请等待审核");
		}

		for (int i = 0; i < itemIds.length; i++) {
			GoodsApply goodsApply = new GoodsApply();
			goodsApply.setUserId(userId);
			goodsApply.setGoodsId(itemIds[i]);
			goodsApply.setCreateTime(new Date());
			goodsApply.setStatus(GoodsConst.GOODS_APPLY_ONSALE_CHECKING);// 申请中
			goodsApply.setModifiedTime(new Date());
			goodsApplyMapper.insertSelective(goodsApply);
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 下架商品删除
	 */
	@Override
	public JsonData delItemOnSale(Integer itemIds) {
		if (itemIds == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		try {
			GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(itemIds);
			if (goods != null) {
				if (goods.getStatus() == GoodsConst.GOODS_STATUS_OFF_SHELF)
					goods.setStatus(GoodsConst.GOODS_STATUS_DELETED);
				goodsMapper.updateByPrimaryKey(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("删除失败");
		}
		return JsonData.setSuccessMessage("删除成功");
	}

	/**
	 * 质押商品统计
	 */
	@Override
	public JsonData getMortgageTotal(Integer adminId) {
		// 构造查询条件
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
		if (RoleConst.DATA_ROLE_TYPE_SELF.equals(dataRole.getType())) {
			dataRole.setScope(adminId + "");
		}
		String scope = null;
		if (dataRole.getScope() != null) {
			scope = dataRole.getScope().replace(StatusConst.IMG_SPLIT_STRING, ",");
		}

		// 授信金额条件
		AdminInfoExample adminInfoExample = new AdminInfoExample();
		if (RoleConst.DATA_ROLE_TYPE_PART.equals(dataRole.getType())) {
			adminInfoExample.createCriteria().andMortageGreaterThan(0l).andAdminIdIn(
					ListUtils.getIntegerListFromString(dataRole.getScope(), StatusConst.IMG_SPLIT_STRING));
		}
		if (RoleConst.DATA_ROLE_TYPE_SELF.equals(dataRole.getType())) {
			adminInfoExample.createCriteria().andMortageGreaterThan(0l).andAdminIdEqualTo(adminId);
		}
		if (RoleConst.DATA_ROLE_TYPE_ALL.equals(dataRole.getType())) {
			adminInfoExample.createCriteria().andMortageGreaterThan(0l);
		}
		// 授信金额
		List<AdminInfo> adminInfos = adminInfoMapper.selectByExample(adminInfoExample);
		Long totalMortgage = 0l;
		if (adminInfos != null && adminInfos.size() > 0) {
			for (AdminInfo adminInfo : adminInfos) {
				totalMortgage += adminInfo.getMortage() + adminInfo.getLoan();
			}
		}
		// 库存 总价
		List<GoodsSku> goodsSkus = goodsMapperCustom.getGoodsSkuListByMortgage(dataRole.getType(), scope);
		Integer totalStock = 0;
		Long totalPrice = 0l;
		if (goodsSkus != null && goodsSkus.size() > 0) {
			for (GoodsSku sku : goodsSkus) {
				totalStock += sku.getStock();
				totalPrice += sku.getPricePurchase() * sku.getStock();
			}
		}
		// 构造返回值
		Map<String, String> result = new HashMap<String, String>();
		result.put("totalMortgage", totalMortgage + "");
		result.put("totalStock", totalStock + "");
		result.put("totalPrice", totalPrice + "");

		return JsonData.setSuccessMessage(result);
	}

	/**
	 * 修改上架商品库存
	 */
	@Override
	public JsonData updateGoodsShelvesStock(Integer skuId, Integer stock) {
		if (skuId == null ) {
			return JsonData.setErrorMessage("参数为空");
		}
		GoodsSku goodsSku = skuMapper.selectByPrimaryKey(skuId);
		if (goodsSku.getStatus() != GoodsConst.GOODS_SKU_STATUS_NORMAL) {
			return JsonData.setErrorMessage("sku不存在");
		}
		goodsSku.setStock(stock);
		//更新sku
		skuMapper.updateByPrimaryKeySelective(goodsSku);
		
		//查询出商品对象
		Integer goodsId = goodsSku.getGoodsId();
		GoodsWithBLOBs goodsWithBLOBs = goodsMapper.selectByPrimaryKey(goodsId);
		if (goodsWithBLOBs.getStatus() != GoodsConst.GOODS_STATUS_ON_SALE) {
			return JsonData.setErrorMessage("此商品不是上架商品");
		}
		
		//获取这个商品下所有的sku
		GoodsSkuExample example = new GoodsSkuExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId).andStatusEqualTo(GoodsConst.GOODS_SKU_STATUS_NORMAL);
		List<GoodsSku> skuList = skuMapper.selectByExample(example);
		if (skuList.size() <= 0) {
			return JsonData.setErrorMessage("sku为空");
		}
		
		Integer stockCount = stock;
		for (GoodsSku sku : skuList) {
			if (sku.getId() == skuId) {
				continue;
			}
			stockCount += sku.getStock();
		}
		
		goodsWithBLOBs.setStock(stockCount);
		goodsWithBLOBs.setModifiedTime(new Date());
		goodsMapper.updateByPrimaryKeySelective(goodsWithBLOBs);
		
		return JsonData.setSuccessMessage();
	}
}
