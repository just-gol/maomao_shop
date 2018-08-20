package com.maomao.ssm.service.impl.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.GoodsOrderCustom;
import com.maomao.ssm.bean.GoodsOrderExample;
import com.maomao.ssm.bean.GoodsOverstockUser;
import com.maomao.ssm.bean.GoodsOverstockUserCustom;
import com.maomao.ssm.bean.GoodsSubscriptionRecordExample;
import com.maomao.ssm.bean.GoodsUserExample;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserApply;
import com.maomao.ssm.bean.UserApplyExample;
import com.maomao.ssm.bean.UserExample;
import com.maomao.ssm.bean.UserWithdrawals;
import com.maomao.ssm.bean.UserWithdrawalsExample;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.OrderConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.GoodsOrderMapper;
import com.maomao.ssm.dao.GoodsOrderMapperCustom;
import com.maomao.ssm.dao.GoodsOverstockUserMapper;
import com.maomao.ssm.dao.GoodsOverstockUserMapperCustom;
import com.maomao.ssm.dao.GoodsSubscriptionRecordMapper;
import com.maomao.ssm.dao.GoodsUserMapper;
import com.maomao.ssm.dao.UserApplyMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.dao.UserWithdrawalsMapper;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.pojo.UserShopDetailOrderListVO;
import com.maomao.ssm.pojo.UserShopDetailVO;
import com.maomao.ssm.pojo.UserShopListVO;
import com.maomao.ssm.service.admin.UserShopService;
import com.maomao.ssm.service.common.UserMoneyService;
import com.maomao.ssm.utils.DateUtils;

@Service
public class UserShopServiceImpl implements UserShopService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserApplyMapper userApplyMapper;
	@Autowired
	private UserWithdrawalsMapper userWithdrawalsMapper;
	@Autowired
	private GoodsUserMapper goodsUserMapper;
	@Autowired
	private GoodsOrderMapper goodsOrderMapper;
	@Autowired
	private GoodsOrderMapperCustom goodsOrderMapperCustom;
	@Autowired
	private GoodsSubscriptionRecordMapper goodsSubscriptionRecordMapper;
	@Autowired
	private GoodsOverstockUserMapperCustom goodsOverstockUserMapperCustom;
	@Autowired
	private GoodsOverstockUserMapper goodsOverstockUserMapper;
	@Autowired
	private UserMoneyService userMoneyService;

	/**
	 * b类用户列表
	 */
	@Override
	public JsonData getUserShopList(Integer pages, Integer rows, String queryString) {
		// 查询b类用户
		UserExample userExample = new UserExample();
		if (StringUtils.isNotBlank(queryString)) {
			userExample.createCriteria().andTypeEqualTo(UserConts.USER_TYPE_B).andMobileLike("%" + queryString + "%")
					.andStatusEqualTo(StatusConst.USER_STATSU_NOT_DEL)
					.andCheckStatusEqualTo(UserConts.USER_CHECK_STATUS_TRUE);
		} else {
			userExample.createCriteria().andTypeEqualTo(UserConts.USER_TYPE_B)
					.andStatusEqualTo(StatusConst.USER_STATSU_NOT_DEL)
					.andCheckStatusEqualTo(UserConts.USER_CHECK_STATUS_TRUE);
		}
		userExample.setOrderByClause("mobile ASC");
		PageHelper.startPage(pages, rows);
		List<User> users = userMapper.selectByExample(userExample);

		List<UserShopListVO> userShopListVOs = new ArrayList<UserShopListVO>();
		for (User user : users) {
			// 查询上架数量
			GoodsUserExample goodsUserExample = new GoodsUserExample();
			goodsUserExample.createCriteria().andUserIdEqualTo(user.getId())
					.andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
			Integer onSalesNum = goodsUserMapper.countByExample(goodsUserExample);
			// 查询销售总额
			GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
			goodsOrderExample.createCriteria().andSalesUserIdEqualTo(user.getId())
					.andStatusNotEqualTo(OrderConst.ORDER_STATUS_UNPAY)
					.andStatusNotEqualTo(OrderConst.ORDER_STATUS_CLOSED);
			GoodsOrderCustom goodsOrderCustomSales = goodsOrderMapperCustom.selectSumByExample(goodsOrderExample);
			// XXX 计算销售总额 考虑定金
			Long salesMoney = goodsOrderCustomSales.getMoney() + goodsOrderCustomSales.getUnpayMoney();
			// 查询退款总额
			goodsOrderExample.clear();
			goodsOrderExample.createCriteria().andSalesUserIdEqualTo(user.getId())
					.andStatusEqualTo(OrderConst.ORDER_STATUS_REFUNDED);
			GoodsOrderCustom goodsOrderCustomRefund = goodsOrderMapperCustom.selectSumByExample(goodsOrderExample);
			// XXX 计算退款金额 考虑定金
			Long refundMoney = goodsOrderCustomRefund.getMoney() + goodsOrderCustomRefund.getUnpayMoney();

			userShopListVOs.add(new UserShopListVO(user, onSalesNum, salesMoney, refundMoney));
		}

		PageBean pagebean = new PageBean();
		// 获取总条数
		pagebean.setTotal(((Page<User>) users).getTotal());
		// 获取当前页数据
		pagebean.setRows(userShopListVOs);
		return JsonData.setSuccessMessage(pagebean);
	}

	/**
	 * b类用户详情
	 */
	@Override
	public JsonData getUserShopDetail(Integer userId) {
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		// 查用户信息 授信
		User user = userMapper.selectByPrimaryKey(userId);
		// 查询联系方式
		UserApplyExample userApplyExample = new UserApplyExample();
		userApplyExample.createCriteria().andCheckStatusEqualTo(UserConts.USER_CHECK_STATUS_TRUE)
				.andUserIdEqualTo(userId);
		List<UserApply> userApplies = userApplyMapper.selectByExample(userApplyExample);
		String applyMobile = null;
		if (userApplies != null && userApplies.size() > 0) {
			applyMobile = userApplies.get(0).getBankMobile();
		}
		// 查店铺信息
		// 上架总数
		GoodsUserExample goodsUserExample = new GoodsUserExample();
		goodsUserExample.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
		Integer onSalesNum = goodsUserMapper.countByExample(goodsUserExample);
		// 合卖商品数量
		goodsUserExample.clear();
		goodsUserExample.createCriteria().andUserIdEqualTo(user.getId())
				.andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
				.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION);
		Integer onSalesSubscriptionNum = goodsUserMapper.countByExample(goodsUserExample);
		// 常规商品数量
		goodsUserExample.clear();
		goodsUserExample.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE)
				.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
		Integer onSalesNormalNum = goodsUserMapper.countByExample(goodsUserExample);
		// 参与合卖次数
		GoodsSubscriptionRecordExample goodsSubscriptionRecordExample = new GoodsSubscriptionRecordExample();
		goodsSubscriptionRecordExample.createCriteria().andUserIdEqualTo(userId);
		Integer subscriptionNum = goodsSubscriptionRecordMapper.countByExample(goodsSubscriptionRecordExample);
		// 分享成交量
		GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
		goodsOrderExample.createCriteria().andSalesUserIdEqualTo(userId).andOrderSrcEqualTo(OrderConst.ORDER_SRC_WEB)
				.andStatusNotEqualTo(OrderConst.ORDER_STATUS_UNPAY).andStatusNotEqualTo(OrderConst.ORDER_STATUS_CLOSED);
		Integer shareOrderBuyNum = goodsOrderMapper.countByExample(goodsOrderExample);
		// 店铺购买量
		goodsOrderExample.clear();
		goodsOrderExample.createCriteria().andSalesUserIdEqualTo(userId).andOrderSrcEqualTo(OrderConst.ORDER_SRC_APP)
				.andStatusNotEqualTo(OrderConst.ORDER_STATUS_UNPAY).andStatusNotEqualTo(OrderConst.ORDER_STATUS_CLOSED);
		Integer orderBuyNum = goodsOrderMapper.countByExample(goodsOrderExample);
		// 定金支付金额
		goodsOrderExample.clear();
		goodsOrderExample.createCriteria().andSalesUserIdEqualTo(userId).andUnpayMoneyNotEqualTo(0l)
				.andPayTimeIsNotNull();
		GoodsOrderCustom goodsOrderCustomDeposit = goodsOrderMapperCustom.selectSumByExample(goodsOrderExample);
		Long depositPaid = goodsOrderCustomDeposit.getMoney();

		// 查销售
		// 销售总额
		goodsOrderExample.clear();
		goodsOrderExample.createCriteria().andSalesUserIdEqualTo(userId)
				.andStatusNotEqualTo(OrderConst.ORDER_STATUS_UNPAY).andStatusNotEqualTo(OrderConst.ORDER_STATUS_CLOSED);
		GoodsOrderCustom goodsOrderCustomSales = goodsOrderMapperCustom.selectSumByExample(goodsOrderExample);
		Long salesMoney = goodsOrderCustomSales.getMoney() + goodsOrderCustomSales.getUnpayMoney();
		// 30天销售总额
		goodsOrderExample.clear();
		goodsOrderExample.createCriteria().andSalesUserIdEqualTo(userId)
				.andStatusNotEqualTo(OrderConst.ORDER_STATUS_UNPAY).andStatusNotEqualTo(OrderConst.ORDER_STATUS_CLOSED)
				.andCreateTimeGreaterThanOrEqualTo(DateUtils.getBeforeDayDate(30));
		GoodsOrderCustom goodsOrderCustomSalesIn30Day = goodsOrderMapperCustom.selectSumByExample(goodsOrderExample);
		Long salesMoneyIn30Day = goodsOrderCustomSalesIn30Day.getMoney() + goodsOrderCustomSalesIn30Day.getUnpayMoney();
		// 60天销售总额
		goodsOrderExample.clear();
		goodsOrderExample.createCriteria().andSalesUserIdEqualTo(userId)
				.andStatusNotEqualTo(OrderConst.ORDER_STATUS_UNPAY).andStatusNotEqualTo(OrderConst.ORDER_STATUS_CLOSED)
				.andCreateTimeGreaterThanOrEqualTo(DateUtils.getBeforeDayDate(60));
		GoodsOrderCustom goodsOrderCustomSalesIn60Day = goodsOrderMapperCustom.selectSumByExample(goodsOrderExample);
		Long salesMoneyIn60Day = goodsOrderCustomSalesIn60Day.getMoney() + goodsOrderCustomSalesIn60Day.getUnpayMoney();
		// 90天销售总额
		goodsOrderExample.clear();
		goodsOrderExample.createCriteria().andSalesUserIdEqualTo(userId)
				.andStatusNotEqualTo(OrderConst.ORDER_STATUS_UNPAY).andStatusNotEqualTo(OrderConst.ORDER_STATUS_CLOSED)
				.andCreateTimeGreaterThanOrEqualTo(DateUtils.getBeforeDayDate(90));
		GoodsOrderCustom goodsOrderCustomSalesIn90Day = goodsOrderMapperCustom.selectSumByExample(goodsOrderExample);
		Long salesMoneyIn90Day = goodsOrderCustomSalesIn90Day.getMoney() + goodsOrderCustomSalesIn90Day.getUnpayMoney();
		// 平均增长率
		Long salesMoneyFisrt = (salesMoneyIn90Day - salesMoneyIn60Day);
		Long salesMoneySecond = (salesMoneyIn60Day - salesMoneyIn30Day);
		Long salesMoneyThird = salesMoneyIn30Day;
		Long increaseAvg = 0l;
		if (salesMoneyFisrt == 0 && salesMoneySecond == 0) {
			increaseAvg = 0l;
		}
		if (salesMoneyFisrt == 0 && salesMoneySecond > 0) {
			increaseAvg = (salesMoneyThird - salesMoneySecond) / salesMoneySecond * 100;
		}
		if (salesMoneyFisrt > 0 && salesMoneySecond == 0) {
			increaseAvg = (salesMoneySecond - salesMoneyFisrt) / salesMoneyFisrt * 100;
		}
		if (salesMoneyFisrt > 0 && salesMoneySecond > 0) {
			increaseAvg = ((salesMoneySecond - salesMoneyFisrt) / salesMoneyFisrt * 100
					+ (salesMoneyThird - salesMoneySecond) / salesMoneySecond * 100) / 2;
		}
		// 查退货
		// 退货总额
		goodsOrderExample.clear();
		goodsOrderExample.createCriteria().andSalesUserIdEqualTo(userId)
				.andStatusEqualTo(OrderConst.ORDER_STATUS_REFUNDED);
		GoodsOrderCustom goodsOrderCustomRefund = goodsOrderMapperCustom.selectSumByExample(goodsOrderExample);
		Long refundMoney = goodsOrderCustomRefund.getMoney() + goodsOrderCustomRefund.getUnpayMoney();
		// 30天退货总额
		goodsOrderExample.clear();
		goodsOrderExample.createCriteria().andSalesUserIdEqualTo(userId)
				.andStatusNotEqualTo(OrderConst.ORDER_STATUS_UNPAY).andStatusNotEqualTo(OrderConst.ORDER_STATUS_CLOSED)
				.andCreateTimeGreaterThanOrEqualTo(DateUtils.getBeforeDayDate(30));
		GoodsOrderCustom goodsOrderCustomRefundIn30Day = goodsOrderMapperCustom.selectSumByExample(goodsOrderExample);
		Long refundMoneyIn30Day = goodsOrderCustomRefundIn30Day.getMoney()
				+ goodsOrderCustomRefundIn30Day.getUnpayMoney();
		Integer refundNumIn30Day = goodsOrderMapper.countByExample(goodsOrderExample);
		// 60天退货总额
		goodsOrderExample.clear();
		goodsOrderExample.createCriteria().andSalesUserIdEqualTo(userId)
				.andStatusNotEqualTo(OrderConst.ORDER_STATUS_UNPAY).andStatusNotEqualTo(OrderConst.ORDER_STATUS_CLOSED)
				.andCreateTimeGreaterThanOrEqualTo(DateUtils.getBeforeDayDate(60));
		GoodsOrderCustom goodsOrderCustomRefundIn60Day = goodsOrderMapperCustom.selectSumByExample(goodsOrderExample);
		Long refundMoneyIn60Day = goodsOrderCustomRefundIn60Day.getMoney()
				+ goodsOrderCustomRefundIn60Day.getUnpayMoney();
		Integer refundNumIn60Day = goodsOrderMapper.countByExample(goodsOrderExample);
		// 90天退货总额
		goodsOrderExample.clear();
		goodsOrderExample.createCriteria().andSalesUserIdEqualTo(userId)
				.andStatusNotEqualTo(OrderConst.ORDER_STATUS_UNPAY).andStatusNotEqualTo(OrderConst.ORDER_STATUS_CLOSED)
				.andCreateTimeGreaterThanOrEqualTo(DateUtils.getBeforeDayDate(90));
		GoodsOrderCustom goodsOrderCustomRefundIn90Day = goodsOrderMapperCustom.selectSumByExample(goodsOrderExample);
		Long refundMoneyIn90Day = goodsOrderCustomRefundIn90Day.getMoney()
				+ goodsOrderCustomRefundIn90Day.getUnpayMoney();
		Integer refundNumIn90Day = goodsOrderMapper.countByExample(goodsOrderExample);
		// 查提现
		// 最近提现信息&提现次数
		UserWithdrawalsExample userWithdrawalsExample = new UserWithdrawalsExample();
		userWithdrawalsExample.createCriteria().andUserIdEqualTo(userId);
		userWithdrawalsExample.setOrderByClause("create_time DESC");
		List<UserWithdrawals> userWithdrawals = userWithdrawalsMapper.selectByExample(userWithdrawalsExample);
		Integer userWithdrawalsNum = 0;
		Date userWithdrawalsLastTime = null;
		Long userWithdrawalsLastMoney = 0l;
		if (userWithdrawals != null && userWithdrawals.size() > 0) {
			userWithdrawalsNum = userWithdrawals.size();
			userWithdrawalsLastTime = userWithdrawals.get(0).getCreateTime();
			userWithdrawalsLastMoney = userWithdrawals.get(0).getMoney();
		}
		// 查压货商品
		List<GoodsOverstockUserCustom> goodsOverstockUserCustoms = goodsOverstockUserMapperCustom
				.getShopGoodsOverstockUserList(userId, GoodsConst.GOODS_WAREHOUSE_OVERSTOCK);

		UserShopDetailVO userShopDetailVO = new UserShopDetailVO(user, applyMobile, onSalesNum, onSalesSubscriptionNum,
				onSalesNormalNum, subscriptionNum, shareOrderBuyNum, orderBuyNum, depositPaid, salesMoney,
				salesMoneyIn30Day, salesMoneyIn60Day, salesMoneyIn90Day, increaseAvg, refundMoney, refundMoneyIn30Day,
				refundNumIn30Day, refundMoneyIn60Day, refundNumIn60Day, refundMoneyIn90Day, refundNumIn90Day,
				userWithdrawalsNum, userWithdrawalsLastTime, userWithdrawalsLastMoney, goodsOverstockUserCustoms);
		return JsonData.setSuccessMessage(userShopDetailVO);
	}

	/**
	 * 获取订单列表
	 */
	@Override
	public JsonData getUserShopDetailOrderList(Integer pages, Integer rows, Integer userId) {
		if (userId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		PageHelper.startPage(pages, rows);
		GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
		goodsOrderExample.createCriteria().andSalesUserIdEqualTo(userId).andPayMoneyIsNotNull();
		List<GoodsOrderCustom> goodsOrderCustoms = goodsOrderMapperCustom.selectListByExample(goodsOrderExample);

		List<UserShopDetailOrderListVO> userShopDetailOrderListVOs = new ArrayList<UserShopDetailOrderListVO>();
		for (GoodsOrderCustom goodsOrderCustom : goodsOrderCustoms) {
			userShopDetailOrderListVOs.add(new UserShopDetailOrderListVO(goodsOrderCustom));
		}
		// 设置返回值
		PageInfo<UserShopDetailOrderListVO> pageInfo = new PageInfo<>(userShopDetailOrderListVOs);
		PageBean pagebean = new PageBean();
		// 获取总条数
		pagebean.setTotal(pageInfo.getTotal());
		// 获取当前页数据
		pagebean.setRows(userShopDetailOrderListVOs);
		return JsonData.setSuccessMessage(pagebean);
	}

	/**
	 * 压货商品退款
	 */
	@Override
	public JsonData updateGoodsOverstockUserByRefund(Integer id) {
		if (id == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		// 查询用户压货商品
		GoodsOverstockUser goodsOverstockUser = goodsOverstockUserMapper.selectByPrimaryKey(id);
		// 判断状态
		if (!goodsOverstockUser.getStatus().equals(GoodsConst.GOODS_OVERSTOCK_USER_STATUS_ON_SALE)) {
			return JsonData.setErrorMessage("商品状态错误");
		}
		// 查询是否有该商品的未支付订单
		List<GoodsOrderCustom> goodsOrderCustoms = goodsOrderMapperCustom.selectListByGoodsIdAndStatus(id,
				OrderConst.ORDER_CATEGORY_OVERSTOCK_SALES, OrderConst.ORDER_STATUS_UNPAY);
		if (goodsOrderCustoms != null && goodsOrderCustoms.size() > 0) {
			return JsonData.setErrorMessage("该商品还有未支付订单，请稍后重试");
		}
		// 退款
		User user = userMapper.selectByPrimaryKey(goodsOverstockUser.getUserId());
		goodsOverstockUser.setStatus(GoodsConst.GOODS_OVERSTOCK_USER_STATUS_REFUND);
		goodsOverstockUser.setModifiedTime(new Date());
		userMoneyService.addUserMoney(goodsOverstockUser.getUserId(), user.getMoney(),
				goodsOverstockUser.getPrice() * goodsOverstockUser.getStock(),
				UserConts.USER_MONEY_TYPE_GOODS_OVERSTOCK_REFUND, goodsOverstockUser.getId());
		user.setMoney(user.getMoney() + goodsOverstockUser.getPrice() * goodsOverstockUser.getStock());
		userMapper.updateByPrimaryKey(user);
		goodsOverstockUserMapper.updateByPrimaryKey(goodsOverstockUser);
		return JsonData.setSuccessMessage();
	}

}
