package com.maomao.ssm.service.impl.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.AdminDataRole;
import com.maomao.ssm.bean.AdminDataRoleExample;
import com.maomao.ssm.bean.AdminInfo;
import com.maomao.ssm.bean.AdminInfoExample;
import com.maomao.ssm.bean.DataRole;
import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsAddressExample;
import com.maomao.ssm.bean.GoodsOrder;
import com.maomao.ssm.bean.GoodsOrderCustom;
import com.maomao.ssm.bean.GoodsOrderExample;
import com.maomao.ssm.bean.GoodsOrderGoods;
import com.maomao.ssm.bean.GoodsOrderGoodsExample;
import com.maomao.ssm.bean.GoodsOrderGoodsInfo;
import com.maomao.ssm.bean.GoodsOrderGoodsInfoExample;
import com.maomao.ssm.bean.GoodsOverstockUserWithBLOBs;
import com.maomao.ssm.bean.GoodsService;
import com.maomao.ssm.bean.GoodsServiceExample;
import com.maomao.ssm.bean.GoodsSubscription;
import com.maomao.ssm.bean.GoodsSubscriptionRecord;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsUser;
import com.maomao.ssm.bean.GoodsUserExample;
import com.maomao.ssm.bean.GoodsWarehouse;
import com.maomao.ssm.bean.GoodsWarehouseExample;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserExample;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.OrderConst;
import com.maomao.ssm.constant.RoleConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.AdminDataRoleMapper;
import com.maomao.ssm.dao.AdminInfoMapper;
import com.maomao.ssm.dao.AdminMapper;
import com.maomao.ssm.dao.DataRoleMapper;
import com.maomao.ssm.dao.GoodsAddressMapper;
import com.maomao.ssm.dao.GoodsBrandMapper;
import com.maomao.ssm.dao.GoodsCategoryMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsOrderGoodsInfoMapper;
import com.maomao.ssm.dao.GoodsOrderGoodsMapper;
import com.maomao.ssm.dao.GoodsOrderMapper;
import com.maomao.ssm.dao.GoodsOrderMapperCustom;
import com.maomao.ssm.dao.GoodsOverstockUserMapper;
import com.maomao.ssm.dao.GoodsServiceMapper;
import com.maomao.ssm.dao.GoodsSubscriptionMapper;
import com.maomao.ssm.dao.GoodsSubscriptionRecordMapperCustom;
import com.maomao.ssm.dao.GoodsUserMapper;
import com.maomao.ssm.dao.GoodsWarehouseMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.OrderDetail;
import com.maomao.ssm.pojo.OrderGoodsInfo;
import com.maomao.ssm.pojo.OrderOverstockDetail;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.AdminOrderService;
import com.maomao.ssm.service.common.AdminLoanService;
import com.maomao.ssm.service.common.AdminMoneyService;
import com.maomao.ssm.service.common.NoticeRecordService;
import com.maomao.ssm.service.common.UserMoneyService;
import com.maomao.ssm.utils.ListUtils;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

	@Autowired
	private GoodsOrderMapperCustom goodsOrderMapperCustom;

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private GoodsSubscriptionMapper goodsSubscriptionMapper;

	@Autowired
	private GoodsWarehouseMapper goodsWarehouseMapper;

	@Autowired
	private GoodsServiceMapper goodsServiceMapper;

	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;

	@Autowired
	private GoodsBrandMapper goodsBrandMapper;
	@Autowired
	private GoodsOrderMapper goodsOrderMapper;
	@Autowired
	private GoodsOrderGoodsMapper goodsOrderGoodsMapper;
	@Autowired
	private NoticeRecordService noticeRecordService;
	@Autowired
	private AdminLoanService adminLoanService;
	@Autowired
	private UserMoneyService userMoneyService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private AdminMoneyService adminMoneyService;
	@Autowired
	private AdminInfoMapper adminInfoMapper;
	@Autowired
	private GoodsSubscriptionRecordMapperCustom goodsSubscriptionRecordMapperCustom;
	@Autowired
	private GoodsUserMapper goodsUserMapper;
	@Autowired
	private AdminDataRoleMapper adminDataRoleMapper;
	@Autowired
	private DataRoleMapper dataRoleMapper;
	@Autowired
	private GoodsAddressMapper goodsAddressMapper;
	@Autowired
	private GoodsOrderGoodsInfoMapper goodsOrderGoodsInfoMapper;
	@Autowired
	private GoodsOverstockUserMapper goodsOverstockUserMapper;

	/**
	 * 获取订单列表
	 */
	@Override
	public JsonData getOrderList(Integer pages, Integer rows, Byte status, String queryString, Integer adminId,
			Integer category) {
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
		if (!OrderConst.ORDER_CATEGORY_OVERSTOCK_SALES.equals(category)) {
			// 非压货订单
			GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
			if (!StringUtils.isBlank(queryString)) {
				if (RoleConst.DATA_ROLE_TYPE_PART.equals(dataRole.getType())) {
					goodsOrderExample.or(goodsOrderExample.createCriteria().andUserNameEqualTo(queryString)
							.andStatusEqualTo(status).andAdminIdIn(ListUtils
									.getIntegerListFromString(dataRole.getScope(), StatusConst.IMG_SPLIT_STRING)));
					goodsOrderExample.or(goodsOrderExample.createCriteria().andMobileEqualTo(queryString)
							.andStatusEqualTo(status).andAdminIdIn(ListUtils
									.getIntegerListFromString(dataRole.getScope(), StatusConst.IMG_SPLIT_STRING)));
					goodsOrderExample.or(goodsOrderExample.createCriteria().andOrderNumLike("%" + queryString + "%")
							.andStatusEqualTo(status).andAdminIdIn(ListUtils
									.getIntegerListFromString(dataRole.getScope(), StatusConst.IMG_SPLIT_STRING)));
				}
				if (RoleConst.DATA_ROLE_TYPE_SELF.equals(dataRole.getType())) {
					goodsOrderExample.or(goodsOrderExample.createCriteria().andUserNameEqualTo(queryString)
							.andStatusEqualTo(status).andAdminIdEqualTo(adminId));
					goodsOrderExample.or(goodsOrderExample.createCriteria().andMobileEqualTo(queryString)
							.andStatusEqualTo(status).andAdminIdEqualTo(adminId));
					goodsOrderExample.or(goodsOrderExample.createCriteria().andOrderNumLike("%" + queryString + "%")
							.andStatusEqualTo(status).andAdminIdEqualTo(adminId));
				}
				if (RoleConst.DATA_ROLE_TYPE_ALL.equals(dataRole.getType())) {
					goodsOrderExample.or(goodsOrderExample.createCriteria().andUserNameEqualTo(queryString)
							.andStatusEqualTo(status));
					goodsOrderExample.or(
							goodsOrderExample.createCriteria().andMobileEqualTo(queryString).andStatusEqualTo(status));
					goodsOrderExample.or(goodsOrderExample.createCriteria().andOrderNumLike("%" + queryString + "%")
							.andStatusEqualTo(status));
				}
			} else {
				if (RoleConst.DATA_ROLE_TYPE_PART.equals(dataRole.getType())) {
					goodsOrderExample.createCriteria().andStatusEqualTo(status).andAdminIdIn(
							ListUtils.getIntegerListFromString(dataRole.getScope(), StatusConst.IMG_SPLIT_STRING));
				}
				if (RoleConst.DATA_ROLE_TYPE_SELF.equals(dataRole.getType())) {
					goodsOrderExample.createCriteria().andStatusEqualTo(status).andAdminIdEqualTo(adminId);
				}
				if (RoleConst.DATA_ROLE_TYPE_ALL.equals(dataRole.getType())) {
					goodsOrderExample.createCriteria().andStatusEqualTo(status);
				}
			}
			// 查询
			PageHelper.startPage(pages, rows);
			List<GoodsOrderCustom> goodsOrderCustoms = goodsOrderMapperCustom.selectListByExample(goodsOrderExample);
			for (GoodsOrderCustom goodsOrderCustom : goodsOrderCustoms) {
				goodsOrderCustom.setPayTypeString(GoodsConst.getPayTypeString(goodsOrderCustom.getPayType()));
				goodsOrderCustom.setGetWayString(GoodsConst.getGetWayString(goodsOrderCustom.getGetWay()));
				goodsOrderCustom.setRefundStatusString("已发货订单");
				if (goodsOrderCustom.getSendTime() == null) {
					goodsOrderCustom.setRefundStatusString("未发货订单");
				}
			}
			// 设置返回值
			PageInfo<GoodsOrderCustom> pageInfo = new PageInfo<>(goodsOrderCustoms);
			PageBean pagebean = new PageBean();
			// 获取总条数
			pagebean.setTotal(pageInfo.getTotal());
			// 获取当前页数据
			pagebean.setRows(goodsOrderCustoms);
			return JsonData.setSuccessMessage(pagebean);
		} else {
			// 压货订单
			PageHelper.startPage(pages, rows);
			Byte type = dataRole.getType();
			String scope = dataRole.getScope();
			if (RoleConst.DATA_ROLE_TYPE_SELF.equals(dataRole.getType())) {
				scope = adminId + "";
			}
			// 查询
			PageHelper.startPage(pages, rows);
			List<GoodsOrderCustom> goodsOrderCustoms = goodsOrderMapperCustom.selectListOverstock(queryString, type,
					scope, category);
			// 设置返回值
			PageInfo<GoodsOrderCustom> pageInfo = new PageInfo<>(goodsOrderCustoms);
			PageBean pagebean = new PageBean();
			// 获取总条数
			pagebean.setTotal(pageInfo.getTotal());
			// 获取当前页数据
			pagebean.setRows(goodsOrderCustoms);
			return JsonData.setSuccessMessage(pagebean);
		}
	}

	/**
	 * 获取订单详情
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public JsonData getOrderDetail(Integer orderId, Integer adminId) {
		// 验证参数
		if (orderId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询订单及订单商品
		GoodsOrderCustom goodsOrderCustom = goodsOrderMapperCustom.selectByOrderId(orderId);

		if (goodsOrderCustom == null) {
			return JsonData.setErrorMessage("订单不存在");
		}

		// 查询商品参数/服务项/品牌/分类/自取地址
		String param = null;
		String goodsServiceIdsString = null;
		Integer categoryId = null;
		Integer brandId = null;
		if (!goodsOrderCustom.getOrderCategory().equals(OrderConst.ORDER_CATEGORY_OVERSTOCK_SALES)) {
			if (goodsOrderCustom.getCategory() == 0) {
				GoodsSubscriptionWithBLOBs goodsSubscriptionWithBLOBs = goodsSubscriptionMapper
						.selectByPrimaryKey(goodsOrderCustom.getBizId());
				param = goodsSubscriptionWithBLOBs.getParam();
				goodsServiceIdsString = goodsSubscriptionWithBLOBs.getServiceId();
				categoryId = goodsSubscriptionWithBLOBs.getCategoryId();
				brandId = goodsSubscriptionWithBLOBs.getBrandId();
			} else {
				GoodsWithBLOBs goodsWithBLOBs = goodsMapper.selectByPrimaryKey(goodsOrderCustom.getBizId());
				param = goodsWithBLOBs.getParam();
				goodsServiceIdsString = goodsWithBLOBs.getServiceId();
				categoryId = goodsWithBLOBs.getCategoryId();
				brandId = goodsWithBLOBs.getBrandId();
			}
			// 查询服务项
			List<GoodsService> goodsServices = null;
			if (goodsServiceIdsString != null) {
				String[] goodsServiceIdString = goodsServiceIdsString.split(StatusConst.IMG_SPLIT_STRING);
				List<Integer> idList = new ArrayList<Integer>();
				for (String goodsServiceId : goodsServiceIdString) {
					idList.add(Integer.parseInt(goodsServiceId));
				}
				GoodsServiceExample goodsServiceExample = new GoodsServiceExample();
				goodsServiceExample.createCriteria().andIdIn(idList);
				goodsServices = goodsServiceMapper.selectByExampleWithBLOBs(goodsServiceExample);
			}
			// 查询品牌
			String brand = null;
			if (brandId != null) {
				brand = goodsBrandMapper.selectByPrimaryKey(brandId).getName();
			}
			// 查询分类
			String category = null;
			if (categoryId != null) {
				category = goodsCategoryMapper.selectByPrimaryKey(categoryId).getName();
			}
			// 查询仓库
			GoodsWarehouseExample goodsWarehouseExample = new GoodsWarehouseExample();
			goodsWarehouseExample.createCriteria().andCategoryEqualTo(goodsOrderCustom.getCategory())
					.andBizIdEqualTo(goodsOrderCustom.getBizId());
			List<GoodsWarehouse> goodsWarehouses = goodsWarehouseMapper.selectByExample(goodsWarehouseExample);
			GoodsWarehouse goodsWarehouse = null;
			if (goodsWarehouses != null && goodsWarehouses.size() > 0) {
				goodsWarehouse = goodsWarehouses.get(0);
			}
			// 查询自取地址
			GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
			goodsAddressExample.createCriteria().andCategoryEqualTo(goodsOrderCustom.getCategory())
					.andBizIdEqualTo(goodsOrderCustom.getBizId());
			List<GoodsAddress> goodsAddrezz = goodsAddressMapper.selectByExample(goodsAddressExample);
			GoodsAddress goodsAddress = null;
			if (goodsAddrezz != null && goodsAddrezz.size() > 0) {
				goodsAddress = goodsAddrezz.get(0);
			}
			// 构造返回值
			OrderDetail orderDetail = new OrderDetail(goodsOrderCustom, param, goodsServices, brand, category,
					goodsWarehouse, GoodsConst.getGetWayString(goodsOrderCustom.getGetWay()),
					GoodsConst.getPayTypeString(goodsOrderCustom.getPayType()),
					GoodsConst.getWarehouseTypeString(goodsWarehouse.getType()),
					OrderConst.getStatusString(goodsOrderCustom.getStatus()), goodsAddress);
			return JsonData.setSuccessMessage(orderDetail);
		} else {
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
			// 压货商品购买订单
			GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
			if (RoleConst.DATA_ROLE_TYPE_PART.equals(dataRole.getType())) {
				goodsOrderGoodsInfoExample.createCriteria()
						.andAdminIdIn(
								ListUtils.getIntegerListFromString(dataRole.getScope(), StatusConst.IMG_SPLIT_STRING))
						.andOrderIdEqualTo(orderId);
			}
			if (RoleConst.DATA_ROLE_TYPE_SELF.equals(dataRole.getType())) {
				goodsOrderGoodsInfoExample.createCriteria().andAdminIdEqualTo(adminId).andOrderIdEqualTo(orderId);
			}
			if (RoleConst.DATA_ROLE_TYPE_ALL.equals(dataRole.getType())) {
				goodsOrderGoodsInfoExample.createCriteria().andOrderIdEqualTo(orderId);
			}
			List<GoodsOrderGoodsInfo> goodsOrderGoodsInfos = goodsOrderGoodsInfoMapper
					.selectByExample(goodsOrderGoodsInfoExample);
			if (goodsOrderGoodsInfos == null || goodsOrderGoodsInfos.size() == 0) {
				return JsonData.setErrorMessage("无权限查看该订单");
			}
			// 查供应商
			Admin admin = adminMapper.selectByPrimaryKey(adminId);
			// 查询仓储地址
			List<OrderGoodsInfo> orderGoodsInfos = new ArrayList<OrderGoodsInfo>();
			for (GoodsOrderGoodsInfo goodsOrderGoodsInfo : goodsOrderGoodsInfos) {
				if (goodsOrderGoodsInfo.getType().equals(GoodsConst.GOODS_OVERSTOCK_TYPE_EXPRESS)) {
					GoodsWarehouseExample goodsWarehouseExample = new GoodsWarehouseExample();
					goodsWarehouseExample.createCriteria()
							.andBizIdEqualTo(goodsOrderGoodsInfo.getGoodsOverstockInfoId())
							.andCategoryEqualTo(GoodsConst.GOODS_WAREHOUSE_OVERSTOCK);
					GoodsWarehouse goodsWarehouse = goodsWarehouseMapper.selectByExample(goodsWarehouseExample).get(0);
					orderGoodsInfos.add(new OrderGoodsInfo(goodsOrderGoodsInfo, goodsWarehouse, admin));
				} else {
					orderGoodsInfos.add(new OrderGoodsInfo(goodsOrderGoodsInfo, null, admin));
				}
			}

			// 查询用户压货商品
			GoodsOverstockUserWithBLOBs goodsOverstockUser = goodsOverstockUserMapper
					.selectByPrimaryKey(goodsOrderCustom.getBizId());
			// 查询服务项
			goodsServiceIdsString = goodsOverstockUser.getServiceId();
			List<GoodsService> goodsServices = null;
			if (goodsServiceIdsString != null) {
				String[] goodsServiceIdString = goodsServiceIdsString.split(StatusConst.IMG_SPLIT_STRING);
				List<Integer> idList = new ArrayList<Integer>();
				for (String goodsServiceId : goodsServiceIdString) {
					idList.add(Integer.parseInt(goodsServiceId));
				}
				GoodsServiceExample goodsServiceExample = new GoodsServiceExample();
				goodsServiceExample.createCriteria().andIdIn(idList);
				goodsServices = goodsServiceMapper.selectByExampleWithBLOBs(goodsServiceExample);
			}
			// 参数
			param = goodsOverstockUser.getParam();
			// 返回值
			OrderOverstockDetail orderOverstockDetail = new OrderOverstockDetail(goodsOrderCustom, goodsOverstockUser,
					orderGoodsInfos, goodsServices, param);
			return JsonData.setSuccessMessage(orderOverstockDetail);
		}
	}

	/**
	 * 确认发货
	 */
	@Override
	public JsonData updateOrderByDeliver(Integer orderId, Integer goodsOrderGoodsInfoId, String expressNum,
			String express) {
		// 验证参数
		if ((orderId == null && goodsOrderGoodsInfoId == null) || (orderId != null && goodsOrderGoodsInfoId != null)
				|| StringUtils.isBlank(express) || StringUtils.isBlank(expressNum)) {
			return JsonData.setErrorMessage("参数非法");
		}
		if (orderId != null) {
			// 查询订单
			GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
			if (goodsOrder == null) {
				return JsonData.setErrorMessage("订单不存在");
			}
			if (!OrderConst.ORDER_STATUS_TRANSFERRED.equals(goodsOrder.getStatus())) {
				return JsonData.setErrorMessage("订单状态错误，不能发货");
			}
			// 发货
			goodsOrder.setStatus(OrderConst.ORDER_STATUS_DELIVERED);
			goodsOrder.setSendTime(new Date());
			goodsOrder.setExpressNum(expressNum);
			goodsOrder.setExpress(express);
			goodsOrder.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_TRUE);
			goodsOrderMapper.updateByPrimaryKey(goodsOrder);

			// 添加消息记录
			GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
			goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(orderId);
			GoodsOrderGoods goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample).get(0);

			noticeRecordService.addNoticeRecord(goodsOrder.getUserId(), NoticeConst.NOTICE_ID_ORDER_DELIVERED,
					NoticeConst.NOTICE_RECORD_TYPE_EXPRESS, new String[] { goodsOrderGoods.getName() });
			// 供应商结钱
			// 常规商品
			if (GoodsConst.GOODS_TYPE_NORMAL.equals(goodsOrderGoods.getType())) {
				// 查询上传admin
				Admin admin = adminMapper.selectByPrimaryKey(goodsOrder.getAdminId());

				// 更新金额
				adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(),
						goodsOrderGoods.getPricePurchase() * goodsOrderGoods.getNum(), UserConts.USER_MONEY_TYPE_SALES,
						orderId);
				admin.setMoney(admin.getMoney() + goodsOrderGoods.getPricePurchase() * goodsOrderGoods.getNum());
				adminMapper.updateByPrimaryKey(admin);
			}
			// 质押商品
			if (GoodsConst.GOODS_TYPE_MORTGAGE.equals(goodsOrderGoods.getType())
					|| GoodsConst.GOODS_TYPE_VR_MORTGAGE.equals(goodsOrderGoods.getType())) {
				// 查询上传admin
				Admin admin = adminMapper.selectByPrimaryKey(goodsOrder.getAdminId());
				AdminInfoExample adminInfoExample = new AdminInfoExample();
				adminInfoExample.createCriteria().andAdminIdEqualTo(admin.getId());
				AdminInfo adminInfo = adminInfoMapper.selectByExample(adminInfoExample).get(0);
				// 判断贷款是否能还清且有多余
				Long money = goodsOrderGoods.getPricePurchase() * goodsOrderGoods.getNum();
				// 贷款已还清
				if (adminInfo.getLoan() == 0) {
					// 更新金额
					adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), money,
							UserConts.USER_MONEY_TYPE_SALES, orderId);
					admin.setMoney(admin.getMoney() + money);
					adminMapper.updateByPrimaryKey(admin);
				}
				// 能
				if (adminInfo.getLoan() < money) {
					// 更新金额
					// 还清贷款
					adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -adminInfo.getLoan(), orderId);
					adminInfo.setLoan(0l);
					adminInfoMapper.updateByPrimaryKey(adminInfo);
					// 剩余部分添加到账户内
					adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), money - adminInfo.getLoan(),
							UserConts.USER_MONEY_TYPE_SALES, orderId);
					admin.setMoney(admin.getMoney() + money - adminInfo.getLoan());
					adminMapper.updateByPrimaryKey(admin);
				}
				// 不能 或者 刚好还清
				if (adminInfo.getLoan() >= money) {
					// 更新金额
					// 还贷款
					adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -money, orderId);
					adminInfo.setLoan(adminInfo.getLoan() - money);
					adminInfoMapper.updateByPrimaryKey(adminInfo);
				}

			}
		}
		if (goodsOrderGoodsInfoId != null) {
			// 查询订单
			GoodsOrderGoodsInfo goodsOrderGoodsInfo = goodsOrderGoodsInfoMapper
					.selectByPrimaryKey(goodsOrderGoodsInfoId);
			if (goodsOrderGoodsInfo == null) {
				return JsonData.setErrorMessage("订单商品信息不存在");
			}
			if (!OrderConst.ORDER_STATUS_TRANSFERRED.equals(goodsOrderGoodsInfo.getStatus())) {
				return JsonData.setErrorMessage("订单状态错误，不能发货");
			}
			// 发货
			goodsOrderGoodsInfo.setStatus(OrderConst.ORDER_STATUS_DELIVERED);
			goodsOrderGoodsInfo.setExpressNum(expressNum);
			goodsOrderGoodsInfo.setExpress(express);
			goodsOrderGoodsInfo.setModifiedTime(new Date());
			goodsOrderGoodsInfoMapper.updateByPrimaryKey(goodsOrderGoodsInfo);

			// 添加消息记录
			noticeRecordService.addNoticeRecord(goodsOrderGoodsInfo.getUserId(), NoticeConst.NOTICE_ID_ORDER_DELIVERED,
					NoticeConst.NOTICE_RECORD_TYPE_EXPRESS, new String[] { goodsOrderGoodsInfo.getGoodsName() });

			// 供应商结钱
			GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(goodsOrderGoodsInfo.getOrderId());
			goodsOrder.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_TRUE);
			goodsOrderMapper.updateByPrimaryKey(goodsOrder);
			// 查询上传admin
			Admin admin = adminMapper.selectByPrimaryKey(goodsOrderGoodsInfo.getAdminId());
			if (admin.getType().equals(StatusConst.ADMIN_TYPE_SUPPLIER)) {
				AdminInfoExample adminInfoExample = new AdminInfoExample();
				adminInfoExample.createCriteria().andAdminIdEqualTo(admin.getId());
				AdminInfo adminInfo = adminInfoMapper.selectByExample(adminInfoExample).get(0);
				// 判断贷款是否能还清且有多余
				Long adminMoney = goodsOrderGoodsInfo.getPricePurchase();
				// 贷款已还清
				if ((adminInfo != null && adminInfo.getLoan() == 0) || adminInfo == null) {
					// 更新金额
					adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), adminMoney,
							UserConts.USER_MONEY_TYPE_SALES, goodsOrder.getId());
					admin.setMoney(admin.getMoney() + adminMoney);
					adminMapper.updateByPrimaryKey(admin);
				}

				// 能
				if (adminInfo != null && adminInfo.getLoan() < adminMoney && adminInfo.getLoan() != 0) {
					// 更新金额
					// 还清贷款
					adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -adminInfo.getLoan(),
							goodsOrder.getId());
					adminInfo.setLoan(0l);
					adminInfoMapper.updateByPrimaryKey(adminInfo);
					// 剩余部分添加到账户内
					adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), adminMoney - adminInfo.getLoan(),
							UserConts.USER_MONEY_TYPE_SALES, goodsOrder.getId());
					admin.setMoney(admin.getMoney() + adminMoney - adminInfo.getLoan());
					adminMapper.updateByPrimaryKey(admin);
				}
				// 不能 或 刚好还清
				if (adminInfo != null && adminInfo.getLoan() >= adminMoney) {
					// 还贷款
					// 更新金额
					adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -adminMoney, goodsOrder.getId());
					adminInfo.setLoan(admin.getMoney() - adminInfo.getLoan());
					adminInfoMapper.updateByPrimaryKey(adminInfo);
				}
			}
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 申请退货
	 */
	@Override
	public JsonData updateOrderByRefund(Integer orderId) {
		// 验证参数
		if (orderId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询订单
		GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
		if (goodsOrder == null) {
			return JsonData.setErrorMessage("订单不存在");
		}
		if (!OrderConst.ORDER_STATUS_FINISHED.equals(goodsOrder.getStatus())
				&& !OrderConst.ORDER_STATUS_TRANSFERRED.equals(goodsOrder.getStatus())) {
			return JsonData.setErrorMessage("订单状态错误，不能退货");
		}
		// 申请退货
		goodsOrder.setStatus(OrderConst.ORDER_STATUS_SERVICE);
		goodsOrderMapper.updateByPrimaryKey(goodsOrder);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 发送退货地址
	 */
	@Override
	public JsonData updateOrderByRefundAddress(Integer orderId, String refundAddress, String refundName,
			String refundMobile) {
		// 验证参数
		if (orderId == null || StringUtils.isBlank(refundAddress) || StringUtils.isBlank(refundName)
				|| StringUtils.isBlank(refundMobile)) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询订单
		GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
		if (goodsOrder == null) {
			return JsonData.setErrorMessage("订单不存在");
		}
		if (!OrderConst.ORDER_STATUS_SERVICE.equals(goodsOrder.getStatus())) {
			return JsonData.setErrorMessage("订单状态错误，不能发送退货地址");
		}
		// 发送退货地址
		goodsOrder.setRefundAddress(refundAddress);
		goodsOrder.setRefundMobile(refundMobile);
		goodsOrder.setRefundName(refundName);
		goodsOrderMapper.updateByPrimaryKey(goodsOrder);

		// 添加消息记录
		GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
		goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(orderId);
		GoodsOrderGoods goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample).get(0);

		noticeRecordService.addNoticeRecord(goodsOrder.getUserId(), NoticeConst.NOTICE_ID_REFUND,
				NoticeConst.NOTICE_RECORD_TYPE_SYSTEM, new String[] { goodsOrderGoods.getName(),
						refundAddress + "(联系人：" + refundName + "+" + refundMobile + ")" });

		return JsonData.setSuccessMessage();
	}

	/**
	 * 退款
	 */
	@Override
	public JsonData updateOrderByRefundMoney(Integer orderId) {
		// 验证参数
		if (orderId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询订单
		GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
		if (goodsOrder == null) {
			return JsonData.setErrorMessage("订单不存在");
		}
		if (!OrderConst.ORDER_STATUS_SERVICE.equals(goodsOrder.getStatus())) {
			return JsonData.setErrorMessage("订单状态错误，不能退款");
		}

		if (goodsOrder.getSendTime() != null && goodsOrder.getRefundAddress() == null) {
			return JsonData.setErrorMessage("已发货订单必须发送退货地址");
		}

		// 退款
		goodsOrder.setStatus(OrderConst.ORDER_STATUS_REFUNDED);
		goodsOrderMapper.updateByPrimaryKey(goodsOrder);

		// 添加消息记录
		GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
		goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(orderId);
		GoodsOrderGoods goodsOrderGoods = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample).get(0);
		noticeRecordService.addNoticeRecord(goodsOrder.getUserId(), NoticeConst.NOTICE_ID_REFUND_SUCCESS,
				NoticeConst.NOTICE_RECORD_TYPE_SYSTEM, new String[] { goodsOrderGoods.getName() });

		return JsonData.setSuccessMessage();
	}

	/**
	 * 根据取货码获取订单详情
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public JsonData getOrderDetailByCode(String code) {
		// 验证参数
		if (StringUtils.isBlank(code)) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询订单及订单商品
		GoodsOrderCustom goodsOrderCustom = goodsOrderMapperCustom.selectByCode(code.toUpperCase(),
				OrderConst.ORDER_STATUS_TRANSFERRED);
		if (goodsOrderCustom == null) {
			return JsonData.setErrorMessage("订单不存在");
		}
		// 查询商品参数/服务项/品牌/分类
		String param = null;
		String goodsServiceIdsString = null;
		Integer categoryId = null;
		Integer brandId = null;
		if (goodsOrderCustom.getCategory() == 0) {
			GoodsSubscriptionWithBLOBs goodsSubscriptionWithBLOBs = goodsSubscriptionMapper
					.selectByPrimaryKey(goodsOrderCustom.getBizId());
			param = goodsSubscriptionWithBLOBs.getParam();
			goodsServiceIdsString = goodsSubscriptionWithBLOBs.getServiceId();
			categoryId = goodsSubscriptionWithBLOBs.getCategoryId();
			brandId = goodsSubscriptionWithBLOBs.getBrandId();
		} else {
			GoodsWithBLOBs goodsWithBLOBs = goodsMapper.selectByPrimaryKey(goodsOrderCustom.getBizId());
			param = goodsWithBLOBs.getParam();
			goodsServiceIdsString = goodsWithBLOBs.getServiceId();
			categoryId = goodsWithBLOBs.getCategoryId();
			brandId = goodsWithBLOBs.getBrandId();
		}
		// 查询服务项
		List<GoodsService> goodsServices = null;
		if (goodsServiceIdsString != null) {
			String[] goodsServiceIdString = goodsServiceIdsString.split(StatusConst.IMG_SPLIT_STRING);
			List<Integer> idList = new ArrayList<Integer>();
			for (String goodsServiceId : goodsServiceIdString) {
				idList.add(Integer.parseInt(goodsServiceId));
			}
			GoodsServiceExample goodsServiceExample = new GoodsServiceExample();
			goodsServiceExample.createCriteria().andIdIn(idList);
			goodsServices = goodsServiceMapper.selectByExampleWithBLOBs(goodsServiceExample);
		}
		// 查询品牌
		String brand = null;
		if (brandId != null) {
			brand = goodsBrandMapper.selectByPrimaryKey(brandId).getName();
		}
		// 查询分类
		String category = null;
		if (categoryId != null) {
			category = goodsCategoryMapper.selectByPrimaryKey(categoryId).getName();
		}
		// 查询仓库
		GoodsWarehouseExample goodsWarehouseExample = new GoodsWarehouseExample();
		goodsWarehouseExample.createCriteria().andCategoryEqualTo(goodsOrderCustom.getCategory())
				.andBizIdEqualTo(goodsOrderCustom.getBizId());
		List<GoodsWarehouse> goodsWarehouses = goodsWarehouseMapper.selectByExample(goodsWarehouseExample);
		GoodsWarehouse goodsWarehouse = null;
		if (goodsWarehouses != null && goodsWarehouses.size() > 0) {
			goodsWarehouse = goodsWarehouses.get(0);
		}
		// 查询自取地址
		GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
		goodsAddressExample.createCriteria().andCategoryEqualTo(goodsOrderCustom.getCategory())
				.andBizIdEqualTo(goodsOrderCustom.getBizId());
		List<GoodsAddress> goodsAddrezz = goodsAddressMapper.selectByExample(goodsAddressExample);
		GoodsAddress goodsAddress = null;
		if (goodsAddrezz != null && goodsAddrezz.size() > 0) {
			goodsAddress = goodsAddrezz.get(0);
		}
		// 构造返回值
		OrderDetail orderDetail = new OrderDetail(goodsOrderCustom, param, goodsServices, brand, category,
				goodsWarehouse, GoodsConst.getGetWayString(goodsOrderCustom.getGetWay()),
				GoodsConst.getPayTypeString(goodsOrderCustom.getPayType()),
				GoodsConst.getWarehouseTypeString(goodsWarehouse.getType()),
				OrderConst.getStatusString(goodsOrderCustom.getStatus()), goodsAddress);
		return JsonData.setSuccessMessage(orderDetail);
	}

	/**
	 * 线下核销
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public JsonData updateOrderByCode(Integer orderId) {
		// 验证参数
		if (orderId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查询订单及订单商品
		GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
		GoodsOrderGoodsExample goodsOrderGoodsExample = new GoodsOrderGoodsExample();
		goodsOrderGoodsExample.createCriteria().andOrderIdEqualTo(orderId);
		List<GoodsOrderGoods> goodsOrderGoodz = goodsOrderGoodsMapper.selectByExample(goodsOrderGoodsExample);
		if (goodsOrderGoodz == null || goodsOrderGoodz.size() == 0) {
			return JsonData.setErrorMessage("订单商品不存在");
		}
		GoodsOrderGoods goodsOrderGoods = goodsOrderGoodz.get(0);
		// 修改订单状态
		if (!OrderConst.ORDER_STATUS_TRANSFERRED.equals(goodsOrder.getStatus()) || goodsOrder.getUnpayMoney() == null) {
			return JsonData.setErrorMessage("订单状态错误");
		}
		goodsOrder.setStatus(OrderConst.ORDER_STATUS_FINISHED);
		if (GoodsConst.GOODS_CATEGORY_NORMAL.equals(goodsOrderGoods.getCategory())) {
			goodsOrder.setStatusSettlement(OrderConst.ORDER_STATUS_SETTLEMENT_TRUE);
		}
		goodsOrderMapper.updateByPrimaryKey(goodsOrder);
		// 分红返利 货源结算
		if (goodsOrder.getUnpayMoney() == 0) {// 非定金方式
			// 普通商品
			if (GoodsConst.GOODS_CATEGORY_NORMAL.equals(goodsOrderGoods.getCategory())) {
				// 返利
				// 查询销售用户
				Integer salesUserId = goodsOrder.getSalesUserId();
				if (salesUserId != null) {
					User salesUser = userMapper.selectByPrimaryKey(salesUserId);
					// 更新金额
					userMoneyService.addUserMoney(salesUserId, salesUser.getMoney(),
							goodsOrderGoods.getRebate() * goodsOrderGoods.getNum(), UserConts.USER_MONEY_TYPE_REBATE,
							orderId);
					salesUser.setMoney(salesUser.getMoney() + goodsOrderGoods.getRebate() * goodsOrderGoods.getNum());
					userMapper.updateByPrimaryKey(salesUser);
				}
				// 奖励金
				// 查询邀请人
				User user = userMapper.selectByPrimaryKey(goodsOrder.getUserId());
				String invitationUserMobile = user.getInvitation();
				if (!StringUtils.isBlank(invitationUserMobile)) {
					UserExample userExample = new UserExample();
					userExample.createCriteria().andMobileEqualTo(invitationUserMobile);
					User invitationUser = userMapper.selectByExample(userExample).get(0);

					// 更新金额
					userMoneyService.addUserMoney(invitationUser.getId(), invitationUser.getMoney(),
							goodsOrderGoods.getReword() * goodsOrderGoods.getNum(), UserConts.USER_MONEY_TYPE_REWORD,
							orderId);
					invitationUser.setMoney(
							invitationUser.getMoney() + goodsOrderGoods.getReword() * goodsOrderGoods.getNum());
					userMapper.updateByPrimaryKey(invitationUser);
				}
				// 常规商品
				if (GoodsConst.GOODS_TYPE_NORMAL.equals(goodsOrderGoods.getType())) {
					// 查询上传admin
					Admin admin = adminMapper.selectByPrimaryKey(goodsOrder.getAdminId());

					// 更新金额
					adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(),
							goodsOrderGoods.getPricePurchase() * goodsOrderGoods.getNum(),
							UserConts.USER_MONEY_TYPE_SALES, orderId);
					admin.setMoney(admin.getMoney() + goodsOrderGoods.getPricePurchase() * goodsOrderGoods.getNum());
					adminMapper.updateByPrimaryKey(admin);
				}
				// 质押商品
				if (GoodsConst.GOODS_TYPE_MORTGAGE.equals(goodsOrderGoods.getType())
						|| GoodsConst.GOODS_TYPE_VR_MORTGAGE.equals(goodsOrderGoods.getType())) {
					// 查询上传admin
					Admin admin = adminMapper.selectByPrimaryKey(goodsOrder.getAdminId());
					AdminInfoExample adminInfoExample = new AdminInfoExample();
					adminInfoExample.createCriteria().andAdminIdEqualTo(admin.getId());
					AdminInfo adminInfo = adminInfoMapper.selectByExample(adminInfoExample).get(0);
					// 判断贷款是否能还清且有多余
					Long money = goodsOrderGoods.getPricePurchase() * goodsOrderGoods.getNum();
					// 贷款已还清
					if (adminInfo.getLoan() == 0) {
						// 更新金额
						adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), money,
								UserConts.USER_MONEY_TYPE_SALES, orderId);
						admin.setMoney(admin.getMoney() + money);
						adminMapper.updateByPrimaryKey(admin);
					}
					// 能
					if (adminInfo.getLoan() < money) {
						// 更新金额
						// 还清贷款
						adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -adminInfo.getLoan(),
								orderId);
						adminInfo.setLoan(0l);
						adminInfoMapper.updateByPrimaryKey(adminInfo);
						// 剩余部分添加到账户内
						adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), money - adminInfo.getLoan(),
								UserConts.USER_MONEY_TYPE_SALES, orderId);
						admin.setMoney(money - adminInfo.getLoan());
						adminMapper.updateByPrimaryKey(admin);
					}
					// 不能 或者 刚好还清
					if (adminInfo.getLoan() >= money) {
						// 更新金额
						// 还贷款
						adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -money, orderId);
						adminInfo.setLoan(adminInfo.getLoan() - money);
						adminInfoMapper.updateByPrimaryKey(adminInfo);
					}

				}

			}
			// 认筹商品
			if (GoodsConst.GOODS_CATEGORY_SUBSCRIPTION.equals(goodsOrderGoods.getCategory())) {
				// 查询认筹记录
				List<GoodsSubscriptionRecord> goodsSubscriptionRecords = goodsSubscriptionRecordMapperCustom
						.selectGroupByUserId(goodsOrderGoods.getBizId());
				// XXX 认筹商品手动分红
				for (GoodsSubscriptionRecord record : goodsSubscriptionRecords) {
					// 删除上架的认筹商品记录
					GoodsUserExample goodsUserExample = new GoodsUserExample();
					goodsUserExample.createCriteria().andUserIdEqualTo(record.getUserId())
							.andBizIdEqualTo(record.getGoodsSubscriptionId())
							.andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
					GoodsUser goodsUser = new GoodsUser();
					goodsUser.setStatus(GoodsConst.GOODS_STATUS_DELETED);
					goodsUserMapper.updateByExampleSelective(goodsUser, goodsUserExample);

					// 添加通知记录
					noticeRecordService.addNoticeRecord(record.getUserId(),
							NoticeConst.NOTICE_ID_SUBCRIPTION_ORDER_FINISH, NoticeConst.NOTICE_RECORD_TYPE_SYSTEM,
							new String[] { goodsOrderGoods.getName(),
									((double) record.getPriceSubcriptionTotal()) / 100 + "",
									((double) goodsOrderGoods.getReword()) / 100 + "" });
				}
				// 更新认筹商品状态
				GoodsSubscription goodsSubscription = goodsSubscriptionMapper
						.selectByPrimaryKey(goodsOrderGoods.getBizId());
				goodsSubscription.setSubscriptionStatus(GoodsConst.GOODS_SUBSCRIPTION_STATUS_FINISHED);
				goodsSubscriptionMapper.updateByPrimaryKey(goodsSubscription);
			}
		} else {// 定金方式
			// 普通商品
			if (GoodsConst.GOODS_CATEGORY_NORMAL.equals(goodsOrderGoods.getCategory())) {
				// 返利
				// 查询销售用户
				Integer salesUserId = goodsOrder.getSalesUserId();
				if (salesUserId != null) {
					User salesUser = userMapper.selectByPrimaryKey(salesUserId);
					// 更新金额
					userMoneyService.addUserMoney(salesUserId, salesUser.getMoney(),
							goodsOrderGoods.getRebate() * goodsOrderGoods.getNum(), UserConts.USER_MONEY_TYPE_REBATE,
							orderId);
					salesUser.setMoney(salesUser.getMoney() + goodsOrderGoods.getRebate() * goodsOrderGoods.getNum());
					userMapper.updateByPrimaryKey(salesUser);
				}
				// 奖励金
				// 查询邀请人
				User user = userMapper.selectByPrimaryKey(goodsOrder.getUserId());
				String invitationUserMobile = user.getInvitation();
				if (!StringUtils.isBlank(invitationUserMobile)) {
					UserExample userExample = new UserExample();
					userExample.createCriteria().andMobileEqualTo(invitationUserMobile);
					User invitationUser = userMapper.selectByExample(userExample).get(0);

					// 更新金额
					userMoneyService.addUserMoney(invitationUser.getId(), invitationUser.getMoney(),
							goodsOrderGoods.getReword() * goodsOrderGoods.getNum(), UserConts.USER_MONEY_TYPE_REWORD,
							orderId);
					invitationUser.setMoney(
							invitationUser.getMoney() + goodsOrderGoods.getReword() * goodsOrderGoods.getNum());
					userMapper.updateByPrimaryKey(invitationUser);
				}
				// 常规商品
				if (GoodsConst.GOODS_TYPE_NORMAL.equals(goodsOrderGoods.getType())) {
					// 查询上传admin
					Admin admin = adminMapper.selectByPrimaryKey(goodsOrder.getAdminId());
					Long my = (goodsOrderGoods.getPriceSales() - goodsOrderGoods.getPricePurchase())
							* goodsOrderGoods.getNum();
					Long adminMoney = goodsOrder.getPayMoney() - my;
					if (adminMoney != 0) {
						adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), adminMoney,
								UserConts.USER_MONEY_TYPE_SALES, orderId);
						admin.setMoney(admin.getMoney() + adminMoney);
						adminMapper.updateByPrimaryKey(admin);
					}
				}
				// 质押商品
				if (GoodsConst.GOODS_TYPE_MORTGAGE.equals(goodsOrderGoods.getType())
						|| GoodsConst.GOODS_TYPE_VR_MORTGAGE.equals(goodsOrderGoods.getType())) {
					// 查询上传admin
					Admin admin = adminMapper.selectByPrimaryKey(goodsOrder.getAdminId());
					AdminInfoExample adminInfoExample = new AdminInfoExample();
					adminInfoExample.createCriteria().andAdminIdEqualTo(admin.getId());
					AdminInfo adminInfo = adminInfoMapper.selectByExample(adminInfoExample).get(0);
					// 平台收益
					Long my = (goodsOrderGoods.getPriceSales() - goodsOrderGoods.getPricePurchase())
							* goodsOrderGoods.getNum();
					// 判断贷款是否能还清且有多余
					Long money = goodsOrderGoods.getPricePurchase() * goodsOrderGoods.getNum();
					// 贷款已还清
					if (adminInfo.getLoan() == 0) {
						// 更新金额
						Long adminMoney = goodsOrder.getPayMoney() - my;
						if (adminMoney != 0) {
							adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), adminMoney,
									UserConts.USER_MONEY_TYPE_SALES, orderId);
							admin.setMoney(admin.getMoney() + adminMoney);
							adminMapper.updateByPrimaryKey(admin);
						}
					}
					// 能
					if (adminInfo.getLoan() < money) {
						Long adminMoney = goodsOrder.getPayMoney() - adminInfo.getLoan() - my;
						if (adminMoney != 0) {
							// 定金还贷 多账户退 少尾款补
							adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), adminMoney,
									UserConts.USER_MONEY_TYPE_SALES, orderId);
							admin.setMoney(admin.getMoney() + adminMoney);
							adminMapper.updateByPrimaryKey(admin);
							// 还清贷款
							adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -adminInfo.getLoan(),
									orderId);
							adminInfo.setLoan(0l);
							adminInfoMapper.updateByPrimaryKey(adminInfo);
						}
					}
					// 不能 或者 刚好还清
					if (adminInfo.getLoan() >= money) {
						// 更新金额
						// 还贷款
						adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -money, orderId);
						adminInfo.setLoan(adminInfo.getLoan() - money);
						adminInfoMapper.updateByPrimaryKey(adminInfo);
					}
				}

			}
			// 认筹商品
			if (GoodsConst.GOODS_CATEGORY_SUBSCRIPTION.equals(goodsOrderGoods.getCategory())) {
				// 查询认筹记录
				List<GoodsSubscriptionRecord> goodsSubscriptionRecords = goodsSubscriptionRecordMapperCustom
						.selectGroupByUserId(goodsOrderGoods.getBizId());
				// XXX 认筹商品手动分红
				for (GoodsSubscriptionRecord record : goodsSubscriptionRecords) {
					// 删除上架的认筹商品记录
					GoodsUserExample goodsUserExample = new GoodsUserExample();
					goodsUserExample.createCriteria().andUserIdEqualTo(record.getUserId())
							.andBizIdEqualTo(record.getGoodsSubscriptionId())
							.andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
					GoodsUser goodsUser = new GoodsUser();
					goodsUser.setStatus(GoodsConst.GOODS_STATUS_DELETED);
					goodsUserMapper.updateByExampleSelective(goodsUser, goodsUserExample);

					// 添加通知记录
					noticeRecordService.addNoticeRecord(record.getUserId(),
							NoticeConst.NOTICE_ID_SUBCRIPTION_ORDER_FINISH, NoticeConst.NOTICE_RECORD_TYPE_SYSTEM,
							new String[] { goodsOrderGoods.getName(),
									((double) record.getPriceSubcriptionTotal()) / 100 + "",
									((double) goodsOrderGoods.getReword()) / 100 + "" });
				}
				// 更新认筹商品状态
				GoodsSubscription goodsSubscription = goodsSubscriptionMapper
						.selectByPrimaryKey(goodsOrderGoods.getBizId());
				goodsSubscription.setSubscriptionStatus(GoodsConst.GOODS_SUBSCRIPTION_STATUS_FINISHED);
				goodsSubscriptionMapper.updateByPrimaryKey(goodsSubscription);
			}
		}
		return JsonData.setSuccessMessage();
	}
}
