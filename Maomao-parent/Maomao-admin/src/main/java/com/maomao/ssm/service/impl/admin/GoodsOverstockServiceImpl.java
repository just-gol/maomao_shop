package com.maomao.ssm.service.impl.admin;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsAddressExample;
import com.maomao.ssm.bean.GoodsOverstock;
import com.maomao.ssm.bean.GoodsOverstockExample;
import com.maomao.ssm.bean.GoodsOverstockExample.Criteria;
import com.maomao.ssm.bean.GoodsOverstockInfo;
import com.maomao.ssm.bean.GoodsOverstockInfoExample;
import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.bean.GoodsWarehouse;
import com.maomao.ssm.bean.GoodsWarehouseExample;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.dao.GoodsAddressMapper;
import com.maomao.ssm.dao.GoodsOverstockInfoMapper;
import com.maomao.ssm.dao.GoodsOverstockMapper;
import com.maomao.ssm.dao.GoodsWarehouseMapper;
import com.maomao.ssm.dao.SystemMapperCustom;
import com.maomao.ssm.pojo.BuyOutGoods;
import com.maomao.ssm.pojo.GoodsOverstockOrgoodsWarehouseOrAddressList;
import com.maomao.ssm.pojo.GoodsOverstockPojo;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.GoodsOverstockService;
import com.maomao.ssm.utils.JsonUtils;

/**
 * 买断管理
 * 
 * @author Administrator
 *
 */
@Service
public class GoodsOverstockServiceImpl implements GoodsOverstockService {
	@Autowired
	private GoodsAddressMapper goodsAddressMapper;
	@Autowired
	private GoodsOverstockMapper goodsOverstockMapper;
	@Autowired
	private GoodsOverstockInfoMapper goodsOverstockInfoMapper;
	@Autowired
	private GoodsWarehouseMapper goodsWarehouseMapper;
	@Autowired
	private SystemMapperCustom systemMapperCustom;

	/**
	 * 获取自取地址详情
	 */
	@Override
	public JsonData getGoodsAddressList() {
		List<String> addressList = new ArrayList<>();
		try {
			List<GoodsAddress> goodsAddressList = goodsAddressMapper.selectByExample(null);
			if (goodsAddressList.size() > 0 && goodsAddressList != null) {
				for (GoodsAddress goodsAddress : goodsAddressList) {
					addressList.add(goodsAddress.getAddress());// 获取自取地址
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("查询失败");
		}
		return JsonData.setSuccessMessage(addressList);
	}

	/**
	 * 申请买断,修改申请买断
	 */
	@Override
	public JsonData addAndUpdateBuyOutGoods(GoodsOverstockWithBLOBs goodsOverstock, String detail, String imgs,
			String param, String GoodsOverstockOrgoodsWarehouseOrAddressList, Byte status) {
		if (goodsOverstock == null || GoodsOverstockOrgoodsWarehouseOrAddressList == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		
		Integer goodsOverstockId = goodsOverstock.getId();
		if (goodsOverstockId == null) {// 买断发起申请中
			// 定义售价变量
			Long totalPrice = 0L;
			try {
				// 压货商品信息集合
				List<GoodsOverstockOrgoodsWarehouseOrAddressList> list = JsonUtils.jsonToList(
						GoodsOverstockOrgoodsWarehouseOrAddressList, GoodsOverstockOrgoodsWarehouseOrAddressList.class);

				for (GoodsOverstockOrgoodsWarehouseOrAddressList goodsList : list) {
					GoodsOverstockInfo goodsOverstockInfo = goodsList.getGoodsOverstockInfo();
					// 统计压货商品总价
					totalPrice += goodsOverstockInfo.getPriceSales();
				}
				// 压货商品
				GoodsOverstockWithBLOBs goodsOverstockWithBLOBs = new GoodsOverstockWithBLOBs();
				goodsOverstockWithBLOBs.setDetail(detail);
				goodsOverstockWithBLOBs.setImgs(imgs);
				goodsOverstockWithBLOBs.setParam(param);
				goodsOverstockWithBLOBs.setServiceId(goodsOverstock.getServiceId() == null ? null : goodsOverstock.getServiceId());
				goodsOverstockWithBLOBs.setName(goodsOverstock.getName() == null ? null : goodsOverstock.getName());
				goodsOverstockWithBLOBs.setStock(goodsOverstock.getStock() == null? 0 : goodsOverstock.getStock());
				goodsOverstockWithBLOBs.setBuyNum(goodsOverstock.getBuyNum() == null ? 0 : goodsOverstock.getBuyNum());
				goodsOverstockWithBLOBs.setPrice(totalPrice == null ? 0L : totalPrice);
				goodsOverstockWithBLOBs.setStatus(GoodsConst.GOODS_OVERSTOCK_STATUS_ON_CKECK);//买断发起申请中
				goodsOverstockWithBLOBs.setRebate(goodsOverstock.getRebate() == null ? 0L : goodsOverstock.getRebate());
				goodsOverstockWithBLOBs.setSales(goodsOverstock.getSales() == null ? 0 :goodsOverstock.getSales() );//销量
				goodsOverstockWithBLOBs.setSalesSham(goodsOverstock.getSalesSham() == null ? 0 : goodsOverstock.getSalesSham());//虚拟销量
				goodsOverstockWithBLOBs.setCreateTime(new Date());
				goodsOverstockWithBLOBs.setModifiedTime(new Date());
				goodsOverstockWithBLOBs.setSort(0);
				goodsOverstockMapper.insertSelective(goodsOverstockWithBLOBs);
				goodsOverstockId = goodsOverstockWithBLOBs.getId();

				if (list.size() > 0 && list != null) {
					for (GoodsOverstockOrgoodsWarehouseOrAddressList goodsList : list) {
						GoodsOverstockInfo goodsOverstockInfo = goodsList.getGoodsOverstockInfo();
						if (goodsOverstockInfo.getAdminId() == null) {
							return JsonData.setErrorMessage("商家id不存在");
						}
						if (goodsOverstockInfo.getId() == null) { // id不存在
							if (goodsOverstockInfo.getType() == GoodsConst.GOODS_OVERSTOCK_TYPE_CODE) { // 抵用券 自取地址
								goodsOverstockInfo.setStatus(GoodsConst.GOODS_OVERSTOCK_INFO_STATUS_NORMAL);// 商品状态.可用
								goodsOverstockInfo.setCreateTime(new Date());
								goodsOverstockInfo.setModifiedTime(new Date());
								goodsOverstockInfo.setGoodsOverstockId(goodsOverstockId);// 与压货商品表关联
								goodsOverstockInfoMapper.insertSelective(goodsOverstockInfo);
								// 添加自取地址
								List<GoodsAddress> addressList = goodsList.getGoodsAddressList();
								for (GoodsAddress goodsAddress : addressList) {
									if (goodsAddress.getId() == null) {
										goodsAddress.setCategory(GoodsConst.GOODS_WAREHOUSE_OVERSTOCK);//订单商品 压货
										goodsAddress.setBizId(goodsOverstockInfo.getAdminId());
										goodsAddress.setCreateTime(new Date());
										goodsAddressMapper.insertSelective(goodsAddress);
									}
								}
							}
							if (goodsOverstockInfo.getType() == GoodsConst.GOODS_OVERSTOCK_TYPE_EXPRESS) { // 实物
								goodsOverstockInfo.setStatus(GoodsConst.GOODS_OVERSTOCK_INFO_STATUS_NORMAL);// 商品状态: 存在
								goodsOverstockInfo.setGoodsOverstockId(goodsOverstockId);// 与压货商品表关联
								goodsOverstockInfo.setCreateTime(new Date());
								goodsOverstockInfo.setModifiedTime(new Date());
								goodsOverstockInfoMapper.insertSelective(goodsOverstockInfo);

								// 仓库对象
								GoodsWarehouse goodsWarehouse = goodsList.getGoodsWarehouse();
								if (goodsWarehouse.getId() == null) {
									goodsWarehouse.setBizId(goodsOverstockInfo.getAdminId());// 关联供应商id
									goodsWarehouse.setCategory(GoodsConst.GOODS_WAREHOUSE_OVERSTOCK);// 压货
									goodsWarehouse.setCreateTime(new Date());
									goodsWarehouseMapper.insertSelective(goodsWarehouse);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("添加失败");
			}
			return JsonData.setSuccessMessage();
		} else if (goodsOverstockId != null ) {//0未上架 -4买断发起退回
			try {
				// 定义售价变量
				Long totalPrice = 0L;
				// 压货商品信息集合
				List<GoodsOverstockOrgoodsWarehouseOrAddressList> list = JsonUtils.jsonToList(
						GoodsOverstockOrgoodsWarehouseOrAddressList, GoodsOverstockOrgoodsWarehouseOrAddressList.class);

				for (GoodsOverstockOrgoodsWarehouseOrAddressList goodsList : list) {
					GoodsOverstockInfo goodsOverstockInfo = goodsList.getGoodsOverstockInfo();
					// 统计压货商品总价
					totalPrice += goodsOverstockInfo.getPriceSales();
				}
				// 压货商品
				GoodsOverstockWithBLOBs goodsOverstockWithBLOBs = goodsOverstockMapper
						.selectByPrimaryKey(goodsOverstockId);
				goodsOverstockWithBLOBs.setDetail(detail);
				goodsOverstockWithBLOBs.setImgs(imgs);
				goodsOverstockWithBLOBs.setParam(param);
				goodsOverstockWithBLOBs.setServiceId(goodsOverstock.getServiceId() == null ? null : goodsOverstock.getServiceId());
				goodsOverstockWithBLOBs.setName(goodsOverstock.getName() == null ? null : goodsOverstock.getName());
				goodsOverstockWithBLOBs.setStock(goodsOverstock.getStock() == null ? 0 : goodsOverstock.getStock());
				goodsOverstockWithBLOBs.setBuyNum(goodsOverstock.getBuyNum() == null ? 0 : goodsOverstock.getBuyNum());
				goodsOverstockWithBLOBs.setPrice(totalPrice == null ? 0L : totalPrice);
				goodsOverstockWithBLOBs.setStatus(status);
				goodsOverstockWithBLOBs.setRebate(goodsOverstock.getRebate() == null ? 0L : goodsOverstock.getRebate());
				goodsOverstockWithBLOBs.setSales(goodsOverstock.getSales() == null ? 0 :goodsOverstock.getSales() );//销量
				goodsOverstockWithBLOBs.setSalesSham(goodsOverstock.getSalesSham() == null ? 0 : goodsOverstock.getSalesSham());//虚拟销量
				goodsOverstockWithBLOBs.setCreateTime(new Date());
				goodsOverstockWithBLOBs.setModifiedTime(new Date());
				goodsOverstockMapper.updateByPrimaryKeySelective(goodsOverstockWithBLOBs);
				if (list.size() > 0 && list != null) {
					for (GoodsOverstockOrgoodsWarehouseOrAddressList goodsList : list) {
						GoodsOverstockInfo goodsOverstockInfo = goodsList.getGoodsOverstockInfo();

						if (goodsOverstockInfo.getAdminId() == null) {
							return JsonData.setErrorMessage("商家id不存在");
						}
						if (goodsOverstockInfo.getId() != null) {//压货商品详情对象存在
							if (goodsOverstockInfo.getType() == GoodsConst.GOODS_OVERSTOCK_TYPE_CODE) { // 抵用券
								goodsOverstockInfo.setModifiedTime(new Date());
								goodsOverstockInfoMapper.updateByPrimaryKeySelective(goodsOverstockInfo);

								// 自取地址
								List<GoodsAddress> goodsAddressList = goodsList.getGoodsAddressList();
								if (goodsAddressList.size() > 0 && goodsAddressList != null) {
									for (GoodsAddress goodsAddress : goodsAddressList) {
//										if (goodsAddress.getId() != null) {
//											goodsAddressMapper.updateByPrimaryKeySelective(goodsAddress);
//
//										} 
										if (goodsAddress.getId() == null) {
											goodsAddress.setCategory(GoodsConst.GOODS_WAREHOUSE_OVERSTOCK);// 压货
											goodsAddress.setBizId(goodsOverstockInfo.getAdminId());
											goodsAddress.setCreateTime(new Date());
											goodsAddressMapper.insertSelective(goodsAddress);
										} 
									}
								}
							}
							if (goodsOverstockInfo.getType() == GoodsConst.GOODS_OVERSTOCK_TYPE_EXPRESS) { // 实物
								goodsOverstockInfo.setModifiedTime(new Date());
								goodsOverstockInfoMapper.updateByPrimaryKeySelective(goodsOverstockInfo);
								// 仓库
								GoodsWarehouse goodsWarehouse = goodsList.getGoodsWarehouse();
								if (goodsWarehouse.getId() != null) {
									goodsWarehouse.setBizId(goodsOverstockInfo.getAdminId());// 关联供应商id
									goodsWarehouseMapper.updateByPrimaryKeySelective(goodsWarehouse);
								}
							}
						}
						//TODO:修改过程中,新增压货商品详情对象
						if (goodsOverstockInfo.getId() == null) { // 压货商品详情对象不存在
							if (goodsOverstockInfo.getType() == GoodsConst.GOODS_OVERSTOCK_TYPE_CODE) { // 抵用券 自取地址
								goodsOverstockInfo.setStatus(GoodsConst.GOODS_OVERSTOCK_INFO_STATUS_NORMAL);// 商品状态.可用
								goodsOverstockInfo.setCreateTime(new Date());
								goodsOverstockInfo.setModifiedTime(new Date());
								goodsOverstockInfo.setGoodsOverstockId(goodsOverstockId);// 与压货商品表关联
								goodsOverstockInfoMapper.insertSelective(goodsOverstockInfo);
								// 添加自取地址
								List<GoodsAddress> addressList = goodsList.getGoodsAddressList();
								for (GoodsAddress goodsAddress : addressList) {
									if (goodsAddress.getId() == null) {
										goodsAddress.setCategory(GoodsConst.GOODS_WAREHOUSE_OVERSTOCK);//订单商品 压货
										goodsAddress.setBizId(goodsOverstockInfo.getAdminId());
										goodsAddress.setCreateTime(new Date());
										goodsAddressMapper.insertSelective(goodsAddress);
									}
								}
							}
							if (goodsOverstockInfo.getType() == GoodsConst.GOODS_OVERSTOCK_TYPE_EXPRESS) { // 实物
								goodsOverstockInfo.setStatus(GoodsConst.GOODS_OVERSTOCK_INFO_STATUS_NORMAL);// 商品状态: 存在
								goodsOverstockInfo.setGoodsOverstockId(goodsOverstockId);// 与压货商品表关联
								goodsOverstockInfo.setCreateTime(new Date());
								goodsOverstockInfo.setModifiedTime(new Date());
								goodsOverstockInfoMapper.insertSelective(goodsOverstockInfo);

								// 仓库对象
								GoodsWarehouse goodsWarehouse = goodsList.getGoodsWarehouse();
								if (goodsWarehouse.getId() == null) {
									goodsWarehouse.setBizId(goodsOverstockInfo.getAdminId());// 关联供应商id
									goodsWarehouse.setCategory(GoodsConst.GOODS_WAREHOUSE_OVERSTOCK);// 压货
									goodsWarehouse.setCreateTime(new Date());
									goodsWarehouseMapper.insertSelective(goodsWarehouse);
								}
							}
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
		return JsonData.setErrorMessage("操作失败");
	}

	/**
	 * 获取压货商品详情
	 * @param goodsOverstockId
	 * @return
	 */
	@Override
	public JsonData getBuyOutGoodsById(Integer goodsOverstockId) {
		if (goodsOverstockId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		GoodsOverstockPojo goodsOverstockPojo = new GoodsOverstockPojo();
		// 获取压货商品对象
		GoodsOverstockWithBLOBs goodsOverstockWithBLOBs = goodsOverstockMapper.selectByPrimaryKey(goodsOverstockId);
		
		goodsOverstockPojo.setId(goodsOverstockId);
		goodsOverstockPojo.setName(goodsOverstockWithBLOBs.getName());
		goodsOverstockPojo.setStock(goodsOverstockWithBLOBs.getStock());
		goodsOverstockPojo.setDetail(goodsOverstockWithBLOBs.getDetail());
		goodsOverstockPojo.setImgs(goodsOverstockWithBLOBs.getImgs());
		goodsOverstockPojo.setServiceId(goodsOverstockWithBLOBs.getServiceId());
		goodsOverstockPojo.setSales(goodsOverstockWithBLOBs.getSales());
		goodsOverstockPojo.setRebate(goodsOverstockWithBLOBs.getRebate());
		goodsOverstockPojo.setBuyNum(goodsOverstockWithBLOBs.getBuyNum());
		goodsOverstockPojo.setReason(goodsOverstockWithBLOBs.getReason());
		
		// 获取压货商品信息对象列表
		GoodsOverstockInfoExample example = new GoodsOverstockInfoExample();
		example.createCriteria().andGoodsOverstockIdEqualTo(goodsOverstockId).andStatusNotEqualTo((byte) -1);
		List<GoodsOverstockInfo> goodsOverstockInfoList = goodsOverstockInfoMapper.selectByExample(example);

		// 仓库 压货商品信息 地址对象 集合
		List<BuyOutGoods> buyOutGoodsList = new ArrayList<>();
		if (goodsOverstockInfoList.size() > 0 && goodsOverstockInfoList != null) {
			for (GoodsOverstockInfo goodsOverstockInfo : goodsOverstockInfoList) {
				BuyOutGoods buyOutGoods = new BuyOutGoods();
				buyOutGoods.setGoodsOverstockInfo(goodsOverstockInfo);
				if (goodsOverstockInfo.getAdminId() == null) {
					return JsonData.setErrorMessage("商家id不存在");
				}
				// 判断类型是实物还是抵用券
				if (goodsOverstockInfo.getType() == GoodsConst.GOODS_OVERSTOCK_TYPE_CODE) {// 抵用券
					// 自取地址
					GoodsAddressExample addressExample = new GoodsAddressExample();
					addressExample.createCriteria().andBizIdEqualTo(goodsOverstockInfo.getAdminId())
							.andCategoryEqualTo(GoodsConst.GOODS_WAREHOUSE_OVERSTOCK);
					List<GoodsAddress> goodsAddressList = goodsAddressMapper.selectByExample(addressExample);
					if (goodsAddressList.size() > 0 && goodsAddressList != null) {
						buyOutGoods.setAddressList(goodsAddressList);
					}
				}
				if (goodsOverstockInfo.getType() == GoodsConst.GOODS_OVERSTOCK_TYPE_EXPRESS) {// 实物
					// 供应商id存在获取仓储地址
					GoodsWarehouseExample warehouseExample = new GoodsWarehouseExample();
					warehouseExample.createCriteria().andBizIdEqualTo(goodsOverstockInfo.getAdminId())
							.andCategoryEqualTo(GoodsConst.GOODS_WAREHOUSE_OVERSTOCK);
					// 获取仓储地址,仅存在一个
					List<GoodsWarehouse> goodsWarehouseList = goodsWarehouseMapper.selectByExample(warehouseExample);
					if (goodsWarehouseList.size() > 0 && goodsWarehouseList != null) {
						if (goodsWarehouseList.get(0) != null) {
							buyOutGoods.setGoodsWarehouse(goodsWarehouseList.get(0));
						}
					}
				}
				buyOutGoodsList.add(buyOutGoods);
			}
			goodsOverstockPojo.setGoodsOverstockOrgoodsWarehouseOrAddressList(buyOutGoodsList);
		}
		return JsonData.setSuccessMessage(goodsOverstockPojo);
	}

	/**
	 * 压货商品删除(下架申请)和处理其他
	 */
	@Override
	public JsonData delAndUpAndOtherBuyOutGoods(Integer[] goodsOverstockIds, Byte status,String reason) {
		if (goodsOverstockIds == null || status == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		// 获取压货商品对象
		GoodsOverstockExample goodsOverstockExample = new GoodsOverstockExample();
		goodsOverstockExample.createCriteria().andIdIn(Arrays.asList(goodsOverstockIds));

		List<GoodsOverstock> goodsOverstockList = goodsOverstockMapper.selectByExample(goodsOverstockExample);
		if (goodsOverstockList.size() <= 0 || goodsOverstockList == null) {
			return JsonData.setErrorMessage("压货商品为空");
		}
		// 删除
		if (status == GoodsConst.GOODS_OVERSTOCK_STATUS_DEL) {
			for (GoodsOverstock goodsOverstock : goodsOverstockList) {
				goodsOverstock.setStatus(status);// 删除
				goodsOverstock.setModifiedTime(new Date());
				goodsOverstockMapper.updateByPrimaryKey(goodsOverstock);
				// 压货商品信息对象
				GoodsOverstockInfoExample example = new GoodsOverstockInfoExample();
				example.createCriteria().andGoodsOverstockIdEqualTo(goodsOverstock.getId());
				List<GoodsOverstockInfo> goodsOverstockInfoList = goodsOverstockInfoMapper.selectByExample(example);
				if (goodsOverstockInfoList.size() <= 0 || goodsOverstockInfoList == null) {
					return JsonData.setErrorMessage("压货商品信息为空");
				}
				for (GoodsOverstockInfo goodsOverstockInfo : goodsOverstockInfoList) {
					goodsOverstockInfo.setStatus(status);
					goodsOverstockInfo.setModifiedTime(new Date());
					goodsOverstockInfoMapper.updateByPrimaryKeySelective(goodsOverstockInfo);
				}
			}
			return JsonData.setSuccessMessage();
		}
		// 未上架
		if (status == GoodsConst.GOODS_OVERSTOCK_STATUS_OFF_SHELF) {
			for (GoodsOverstock goodsOverstock : goodsOverstockList) {
				if (goodsOverstock.getStatus() == GoodsConst.GOODS_OVERSTOCK_STATUS_ON_SALE) {// 已上架的商品下架
					goodsOverstock.setStatus(status);
					goodsOverstock.setModifiedTime(new Date());
					goodsOverstockMapper.updateByPrimaryKey(goodsOverstock);
					// 压货商品信息对象
				//	getGoodsOverstockInfoMethod(goodsOverstock);
				}
			}
			return JsonData.setSuccessMessage();
		}

		// -2买断发起申请中 -4买断发起退回 -3买断发起拒绝 2已上架 0未上架 -1删除
			for (GoodsOverstock goodsOverstock : goodsOverstockList) { // 上架
				//if (goodsOverstock.getStatus() == -2) {// 申请买断状态
				
					if (status == GoodsConst.GOODS_OVERSTOCK_STATUS_ON_SALE) {
						Integer sort = goodsOverstock.getSort();
						// 获取最大的sort TODO:
						Integer sortTop = systemMapperCustom.getGoodsOverstockTop();
						if (sortTop == null) {
							sortTop = 1;
						} else {
							sort = sortTop;
							sort++;
						}
						goodsOverstock.setStatus(status);
						goodsOverstock.setSort(sort);
						goodsOverstock.setModifiedTime(new Date());
						goodsOverstockMapper.updateByPrimaryKey(goodsOverstock);
						return JsonData.setSuccessMessage();
					}

					if (status == GoodsConst.GOODS_OVERSTOCK_STATUS_CHECK_FAILED) {
						goodsOverstock.setStatus(status);
						goodsOverstock.setReason(reason);//拒绝理由
						goodsOverstock.setModifiedTime(new Date());
						goodsOverstockMapper.updateByPrimaryKey(goodsOverstock);
						return JsonData.setSuccessMessage();
					}

					if (status == GoodsConst.GOODS_OVERSTOCK_STATUS_SET_BACK) {
						goodsOverstock.setStatus(status);
						goodsOverstock.setReason(reason);//拒绝理由
						goodsOverstock.setModifiedTime(new Date());
						goodsOverstockMapper.updateByPrimaryKey(goodsOverstock);
						return JsonData.setSuccessMessage();
					}
					
					if (status == GoodsConst.GOODS_OVERSTOCK_STATUS_ON_CKECK) { //买断发起申请中
						goodsOverstock.setStatus(status);
						goodsOverstock.setReason(reason);//拒绝理由
						goodsOverstock.setModifiedTime(new Date());
						goodsOverstockMapper.updateByPrimaryKey(goodsOverstock);
						return JsonData.setSuccessMessage();
					}
		}
		return JsonData.setErrorMessage("操作失败");
	}

	/**
	 * 获取压货商品列表
	 */
	@Override
	public JsonData getBuyOutGoodsList(Integer pages, Integer rows, Byte status, String goodsOverstockName) {
		if (pages == null) {
			pages = 1;
		}
		if (rows == null) {
			rows = 10;
		}
		// 分页
		Page page = PageHelper.startPage(pages, rows);

		// 获取压货商品对象
		GoodsOverstockExample goodsOverstockExample = new GoodsOverstockExample();
		goodsOverstockExample.setOrderByClause("create_time desc");//创建时间逆序
		Criteria criteria = goodsOverstockExample.createCriteria();
		criteria.andStatusNotEqualTo(GoodsConst.GOODS_OVERSTOCK_STATUS_DEL);// 未删除
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		if (goodsOverstockName != null) {
	//			goodsOverstockName = new String(goodsOverstockName.getBytes("ISO-8859-1"), "utf-8");
				criteria.andNameLike("%" + goodsOverstockName + "%");
		}

		List<GoodsOverstock> goodsOverstockList = goodsOverstockMapper.selectByExample(goodsOverstockExample);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (goodsOverstockList.size() > 0 && goodsOverstockList != null) {
			for (GoodsOverstock goodsOverstock : goodsOverstockList) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 获取id
				map.put("goodsOverstockId", goodsOverstock.getId());

				// 获取压货商品名称
				map.put("goodsOverstockName", goodsOverstock.getName());

				// 压货商品售价
				map.put("price", goodsOverstock.getPrice());

				// 库存
				map.put("stock", goodsOverstock.getStock());

				// 商品状态
				map.put("status", goodsOverstock.getStatus());
				
				//退回理由
				map.put("reason",goodsOverstock.getReason());

				// 通过压货商品获取压货商品信息集合
				GoodsOverstockInfoExample goodsOverstockInfoExample = new GoodsOverstockInfoExample();
				goodsOverstockInfoExample.createCriteria().andStatusNotEqualTo(GoodsConst.GOODS_OVERSTOCK_INFO_STATUS_DEL)
						.andGoodsOverstockIdEqualTo(goodsOverstock.getId());

				List<GoodsOverstockInfo> goodsOverstockInfoList = goodsOverstockInfoMapper
						.selectByExample(goodsOverstockInfoExample);
				if (goodsOverstockInfoList.size() > 0 && goodsOverstockInfoList != null) {
					// 获取压货商品信息数量
					Integer GoodsOverstockInfoNum = goodsOverstockInfoList.size();
					map.put("GoodsOverstockInfoNum", GoodsOverstockInfoNum);
				}
				list.add(map);
			}
		}
		PageBean pageBean = new PageBean();
		pageBean.setRows(list);
		pageBean.setTotal(page.getTotal());
		return JsonData.setSuccessMessage(pageBean);
	}

	/**
	 * 压货商品信息方法,删除
	 * 
	 * @param status
	 * @param goodsOverstock
	 */
//	private void getGoodsOverstockInfoMethod(Byte status, GoodsOverstock goodsOverstock) {
//		if (status == -1) { // 删除
//			GoodsOverstockInfoExample example = new GoodsOverstockInfoExample();
//			example.createCriteria().andGoodsOverstockIdEqualTo(goodsOverstock.getId());
//			List<GoodsOverstockInfo> goodsOverstockInfoList = goodsOverstockInfoMapper.selectByExample(example);
//			if (goodsOverstockInfoList.size() < 0 || goodsOverstockInfoList == null) {
//				for (GoodsOverstockInfo goodsOverstockInfo : goodsOverstockInfoList) {
//					goodsOverstockInfo.setStatus(status);
//					goodsOverstockInfoMapper.updateByPrimaryKeySelective(goodsOverstockInfo);
//				}
//			}
//		}
//	}
	
	/**
	 * 压货商品信息方法,其他(排序删除)
	 * @param goodsOverstock
	 */
/*	private void getGoodsOverstockInfoMethod(GoodsOverstock goodsOverstock) {
		GoodsOverstockInfoExample example = new GoodsOverstockInfoExample();
		example.createCriteria().andGoodsOverstockIdEqualTo(goodsOverstock.getId());
		List<GoodsOverstockInfo> goodsOverstockInfoList = goodsOverstockInfoMapper.selectByExample(example);
		if (goodsOverstockInfoList.size() < 0 || goodsOverstockInfoList == null) {
			for (GoodsOverstockInfo goodsOverstockInfo : goodsOverstockInfoList) {
				goodsOverstockInfo.setStatus((byte) 1);
				goodsOverstockInfoMapper.updateByPrimaryKeySelective(goodsOverstockInfo);
			}
		}
	}*/

	/**
	 * 删除压货商品信息对象
	 */
	public JsonData delGoodsOverstockInfo(Integer goodsOverstockInfoId, Byte status) {
		if (goodsOverstockInfoId == null || status == null) {
			return JsonData.setErrorMessage("参数为null");
		}
		GoodsOverstockInfo goodsOverstockInfo = goodsOverstockInfoMapper.selectByPrimaryKey(goodsOverstockInfoId);
		goodsOverstockInfo.setStatus(status);// 删除
		goodsOverstockInfoMapper.updateByPrimaryKeySelective(goodsOverstockInfo);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 删除自取地址
	 */
	@Override							    
	public JsonData delGoodsAddress(Integer goodsAddressId) {
		if (goodsAddressId == null) {
			return JsonData.setErrorMessage("参数为null");
		}
		goodsAddressMapper.deleteByPrimaryKey(goodsAddressId);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 上架商品修改库存
	 */
	@Override
	public JsonData updateGoodsOverstock(Integer goodsOverstockId, Integer stock) {
		if (goodsOverstockId == null || stock == null) {
			return JsonData.setErrorMessage("参数为null");
		}
		GoodsOverstockWithBLOBs goodsOverstockWithBLOBs = goodsOverstockMapper.selectByPrimaryKey(goodsOverstockId);
		if (goodsOverstockWithBLOBs.getStatus() != GoodsConst.GOODS_OVERSTOCK_STATUS_ON_SALE) {
			return JsonData.setServerErrorMessage("此商品不是上架商品");
		}
		goodsOverstockWithBLOBs.setStock(stock);
		goodsOverstockMapper.updateByPrimaryKeySelective(goodsOverstockWithBLOBs);
		return JsonData.setSuccessMessage();
	}
}
