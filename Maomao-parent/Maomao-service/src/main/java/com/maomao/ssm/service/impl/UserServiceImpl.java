package com.maomao.ssm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.AdminExample;
import com.maomao.ssm.bean.AdminInfo;
import com.maomao.ssm.bean.AdminInfoExample;
import com.maomao.ssm.bean.Coupon;
import com.maomao.ssm.bean.CouponExample;
import com.maomao.ssm.bean.CouponRecord;
import com.maomao.ssm.bean.CouponRecordExample;
import com.maomao.ssm.bean.GoodsCollectionExample;
import com.maomao.ssm.bean.GoodsOrderExample;
import com.maomao.ssm.bean.GoodsOrderGoodsInfo;
import com.maomao.ssm.bean.GoodsOrderGoodsInfoExample;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserAddress;
import com.maomao.ssm.bean.UserAddressExample;
import com.maomao.ssm.bean.UserBank;
import com.maomao.ssm.bean.UserBankExample;
import com.maomao.ssm.bean.UserExample;
import com.maomao.ssm.bean.UserExample.Criteria;
import com.maomao.ssm.bean.UserFeedback;
import com.maomao.ssm.bean.UserFeedbackExample;
import com.maomao.ssm.constant.CouponConts;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.OrderConst;
import com.maomao.ssm.constant.PageConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.AdminInfoMapper;
import com.maomao.ssm.dao.AdminMapper;
import com.maomao.ssm.dao.CouponMapper;
import com.maomao.ssm.dao.CouponRecordMapper;
import com.maomao.ssm.dao.GoodsCollectionMapper;
import com.maomao.ssm.dao.GoodsCollectionMapperCustom;
import com.maomao.ssm.dao.GoodsOrderGoodsInfoMapper;
import com.maomao.ssm.dao.GoodsOrderMapper;
import com.maomao.ssm.dao.UserAddressMapper;
import com.maomao.ssm.dao.UserBankMapper;
import com.maomao.ssm.dao.UserFeedbackMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.user.UserAddrezz;
import com.maomao.ssm.pojo.user.UserBankVO;
import com.maomao.ssm.pojo.user.UserCouponRecord;
import com.maomao.ssm.pojo.user.UserDetail;
import com.maomao.ssm.pojo.user.UserFeedbackVO;
import com.maomao.ssm.pojo.user.UserGoodsCollection;
import com.maomao.ssm.pojo.user.UserHome;
import com.maomao.ssm.service.UserService;
import com.maomao.ssm.service.common.AdminLoanService;
import com.maomao.ssm.service.common.AdminMoneyService;
import com.maomao.ssm.service.common.CouponRecordService;
import com.maomao.ssm.utils.JsonUtils;

@Service
public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserAddressMapper userAddressMapper;

	@Autowired
	private GoodsOrderMapper goodsOrderMapper;

	@Autowired
	private CouponRecordMapper couponRecordMapper;

	@Autowired
	private GoodsCollectionMapper goodsCollectionMapper;

	@Autowired
	private GoodsCollectionMapperCustom goodsCollectionMapperCustom;

	@Autowired
	private JedisClientPool jedisClientPool;

	@Autowired
	private UserFeedbackMapper userFeedBackMapper;

	@Autowired
	private UserBankMapper userBankMapper;

	@Autowired
	private CouponRecordService couponRecordService;
	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private AdminMoneyService adminMoneyService;
	@Autowired
	private AdminInfoMapper adminInfoMapper;
	@Autowired
	private GoodsOrderGoodsInfoMapper goodsOrderGoodsInfoMapper;
	@Autowired
	private AdminLoanService adminLoanService;

	@Override
	public List<User> getUsers() {
		return userMapper.selectByExample(null);
	}

	@Override
	public User getUser(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 个人中心首页
	 */
	@Override
	public JsonData getHomeDetail(Integer userId) {
		// 验证参数
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法！");
		}

		// 查询用户
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return JsonData.setErrorMessage("用户不存在！");
		}
		// 查询默认收货地址
		UserAddressExample userAddressExample = new UserAddressExample();
		com.maomao.ssm.bean.UserAddressExample.Criteria userAddressCriteria = userAddressExample.createCriteria();
		userAddressCriteria.andUserIdEqualTo(userId).andDefaultAddressEqualTo(UserConts.USER_ADDRESS_DEFAULT_TRUE);
		List<UserAddress> userAddresses = userAddressMapper.selectByExample(userAddressExample);
		UserAddress userAddress = null;
		if (userAddresses != null && userAddresses.size() != 0) {
			userAddress = userAddresses.get(0);
		}
		// 查询订单数量
		GoodsOrderExample goodsOrderExample = new GoodsOrderExample();
		com.maomao.ssm.bean.GoodsOrderExample.Criteria goodsOrderCriteria = goodsOrderExample.createCriteria();
		goodsOrderCriteria.andUserIdEqualTo(userId).andStatusBetween(OrderConst.ORDER_STATUS_UNPAY,
				OrderConst.ORDER_STATUS_DELIVERED);
		int orderNum = goodsOrderMapper.countByExample(goodsOrderExample);
		// 查询优惠券数量
		CouponRecordExample couponRecordExample = new CouponRecordExample();
		com.maomao.ssm.bean.CouponRecordExample.Criteria couponRecordCriteria = couponRecordExample.createCriteria();
		couponRecordCriteria.andUserIdEqualTo(userId).andStatusEqualTo(CouponConts.COUPON_RECORD_STATUS_UNUSED);
		int couponRecordNum = couponRecordMapper.countByExample(couponRecordExample);
		// 查询收藏数量
		GoodsCollectionExample goodsCollectionExample = new GoodsCollectionExample();
		com.maomao.ssm.bean.GoodsCollectionExample.Criteria goodsCollectionCriteria = goodsCollectionExample
				.createCriteria();
		goodsCollectionCriteria.andUserIdEqualTo(userId);
		int goodsCollectionNum = goodsCollectionMapper.countByExample(goodsCollectionExample);
		// 查询可用优惠数量
		GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
		goodsOrderGoodsInfoExample.createCriteria().andUserIdEqualTo(userId)
				.andUseEndTimeGreaterThanOrEqualTo(new Date()).andTypeEqualTo(GoodsConst.GOODS_OVERSTOCK_TYPE_CODE)
				.andStatusEqualTo(OrderConst.ORDER_STATUS_DELIVERED);
		int goodsOrderGoodsInfoNum = goodsOrderGoodsInfoMapper.countByExample(goodsOrderGoodsInfoExample);
		// 构造返回值
		UserHome userHome = new UserHome(user, userAddress, orderNum, couponRecordNum, goodsCollectionNum,
				goodsOrderGoodsInfoNum);
		return JsonData.setSuccessMessage(userHome);
	}

	/**
	 * 我的优惠券列表
	 */
	@Override
	public JsonData getCouponRecordList(Integer userId, Integer pages, Integer rows, Byte type, Long money) {
		// 验证参数
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法！");
		}

		pages = pages == null || pages < 3 ? PageConst.PAGE_DEFAULT : pages;
		rows = rows == null ? pages == PageConst.PAGE_DEFAULT ? PageConst.ROWS_DEFAULT_FIRST : PageConst.ROWS_DEFAULT
				: rows;

		// 查询优惠券列表
		CouponRecordExample couponRecordExample = new CouponRecordExample();
		if (type != null && type == 0) {
			couponRecordExample.createCriteria().andUserIdEqualTo(userId)
					.andStatusEqualTo(CouponConts.COUPON_RECORD_STATUS_UNUSED)
					.andAvailableMoneyLessThanOrEqualTo(money);
			couponRecordExample.setOrderByClause("validity_time ASC");
		} else {
			com.maomao.ssm.bean.CouponRecordExample.Criteria couponRecordCriteria = couponRecordExample
					.createCriteria();
			couponRecordCriteria.andUserIdEqualTo(userId).andStatusEqualTo(CouponConts.COUPON_RECORD_STATUS_UNUSED);
			com.maomao.ssm.bean.CouponRecordExample.Criteria couponRecordCriteria2 = couponRecordExample
					.createCriteria();
			couponRecordCriteria2.andUserIdEqualTo(userId).andStatusEqualTo(CouponConts.COUPON_RECORD_STATUS_EXPIRE);
			couponRecordExample.or(couponRecordCriteria2);
			couponRecordExample.setOrderByClause("status ASC, validity_time ASC");
		}
		PageHelper.startPage(pages, rows);
		List<CouponRecord> couponRecords = couponRecordMapper.selectByExample(couponRecordExample);
		// 构造返回值
		List<UserCouponRecord> userCouponRecords = new ArrayList<UserCouponRecord>();
		for (CouponRecord couponRecord : couponRecords) {
			userCouponRecords.add(new UserCouponRecord(couponRecord));
		}

		return JsonData.setSuccessMessage(userCouponRecords);
	}

	/**
	 * 我的收藏列表
	 */
	@Override
	public JsonData getCollectionList(Integer userId, Integer pages, Integer rows) {
		// 验证参数
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法！");
		}
		pages = pages == null || pages < 3 ? PageConst.PAGE_DEFAULT : pages;
		rows = rows == null ? pages == PageConst.PAGE_DEFAULT ? PageConst.ROWS_DEFAULT_FIRST : PageConst.ROWS_DEFAULT
				: rows;
		// 查询收藏列表
		PageHelper.startPage(pages, rows);
		List<GoodsWithBLOBs> goodsCollections = goodsCollectionMapperCustom.selectByUserId(userId);
		// 构造返回值
		List<UserGoodsCollection> userGoodsCollections = new ArrayList<UserGoodsCollection>();
		for (GoodsWithBLOBs goods : goodsCollections) {
			userGoodsCollections.add(new UserGoodsCollection(goods));
		}
		return JsonData.setSuccessMessage(userGoodsCollections);
	}

	/**
	 * 收货地址列表
	 */
	@Override
	public JsonData getAddressList(Integer userId, Integer pages, Integer rows) {
		// 验证参数
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法！");
		}
		pages = pages == null || pages < 3 ? PageConst.PAGE_DEFAULT : pages;
		rows = rows == null ? pages == PageConst.PAGE_DEFAULT ? PageConst.ROWS_DEFAULT_FIRST : PageConst.ROWS_DEFAULT
				: rows;
		// 查询收货地址
		PageHelper.startPage(pages, rows);
		UserAddressExample userAddressExample = new UserAddressExample();
		com.maomao.ssm.bean.UserAddressExample.Criteria userAddressCriteria = userAddressExample.createCriteria();
		userAddressCriteria.andUserIdEqualTo(userId);
		userAddressExample.setOrderByClause("default_address DESC, create_time DESC");
		PageHelper.startPage(pages, rows);
		List<UserAddress> userAddresses = userAddressMapper.selectByExample(userAddressExample);
		// 构造返回值
		List<UserAddrezz> userAddrezzes = new ArrayList<UserAddrezz>();
		for (UserAddress userAddress : userAddresses) {
			userAddrezzes.add(new UserAddrezz(userAddress));
		}

		return JsonData.setSuccessMessage(userAddrezzes);
	}

	/**
	 * 添加收货地址
	 */
	@Override
	public JsonData addAddress(Integer userId, String name, String mobile, String province, String city, String area,
			String address, Byte isDefault) {
		// 验证参数
		if (userId == null || StringUtils.isBlank(name) || StringUtils.isBlank(mobile) || StringUtils.isBlank(province)
				|| StringUtils.isBlank(city) || StringUtils.isBlank(address)) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 查询是否有默认地址
		UserAddressExample userAddressExample = new UserAddressExample();
		com.maomao.ssm.bean.UserAddressExample.Criteria userAddressCriteria = userAddressExample.createCriteria();
		userAddressCriteria.andUserIdEqualTo(userId).andDefaultAddressEqualTo(UserConts.USER_ADDRESS_DEFAULT_TRUE);
		List<UserAddress> userAddresses = userAddressMapper.selectByExample(userAddressExample);
		// 构造do
		UserAddress userAddress = new UserAddress();
		userAddress.setUserId(userId);
		userAddress.setName(name);
		userAddress.setMobile(mobile);
		userAddress.setProvince(province);
		userAddress.setCity(city);
		userAddress.setArea(area);
		userAddress.setAddress(address);
		userAddress.setCreateTime(new Date());
		userAddress.setDefaultAddress(UserConts.USER_ADDRESS_DEFAULT_FASLE.equals(isDefault)
				? userAddresses.size() > 0 ? UserConts.USER_ADDRESS_DEFAULT_FASLE : UserConts.USER_ADDRESS_DEFAULT_TRUE
				: isDefault);
		// 持久化对象
		try {
			// 判断新增地址是否默认且已有默认地址
			if (UserConts.USER_ADDRESS_DEFAULT_TRUE.equals(isDefault) && userAddresses.size() > 0) {
				UserAddress defaultUserAddress = userAddresses.get(0);
				defaultUserAddress.setDefaultAddress(UserConts.USER_ADDRESS_DEFAULT_FASLE);
				userAddressMapper.updateByPrimaryKey(defaultUserAddress);
			}
			userAddressMapper.insert(userAddress);
		} catch (Exception e) {
			log.error("UserService addAddress() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("添加收货地址出错");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 修改收货地址
	 */
	@Override
	public JsonData updateAddressById(Integer userId, Integer addressId, String name, String mobile, String province,
			String city, String area, String address, Byte isDefault) {
		// 验证参数
		if (userId == null || addressId == null || StringUtils.isBlank(name) || StringUtils.isBlank(province)
				|| StringUtils.isBlank(city) || StringUtils.isBlank(mobile) || StringUtils.isBlank(address)) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 查询默认地址
		UserAddressExample userAddressExample = new UserAddressExample();
		com.maomao.ssm.bean.UserAddressExample.Criteria userAddressCriteria = userAddressExample.createCriteria();
		userAddressCriteria.andUserIdEqualTo(userId).andDefaultAddressEqualTo(UserConts.USER_ADDRESS_DEFAULT_TRUE);
		List<UserAddress> userAddresses = userAddressMapper.selectByExample(userAddressExample);
		// 构造do
		UserAddress userAddress = new UserAddress();
		userAddress.setId(addressId);
		userAddress.setUserId(userId);
		userAddress.setName(name);
		userAddress.setMobile(mobile);
		userAddress.setProvince(province);
		userAddress.setCity(city);
		userAddress.setArea(area);
		userAddress.setAddress(address);
		userAddress.setModifiedTime(new Date());
		userAddress.setDefaultAddress(isDefault);
		// 更新
		try {
			// 判断新增地址是否默认且已有默认地址
			if (UserConts.USER_ADDRESS_DEFAULT_TRUE.equals(isDefault) && userAddresses.size() > 0) {
				UserAddress defaultUserAddress = userAddresses.get(0);
				defaultUserAddress.setDefaultAddress(UserConts.USER_ADDRESS_DEFAULT_FASLE);
				userAddressMapper.updateByPrimaryKey(defaultUserAddress);
			}
			userAddressMapper.updateByPrimaryKeySelective(userAddress);
		} catch (Exception e) {
			log.error("UserService updateAddressById() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("修改收货地址出错");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 设置默认地址
	 */
	@Override
	public JsonData updateAddressByDefault(Integer userId, Integer addressId, Integer defaultAddressId) {
		// 验证参数
		if (userId == null || addressId == null || defaultAddressId == null) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 判断原默认地址与新默认地址是否为同一个
		if (addressId.equals(defaultAddressId)) {
			return JsonData.setErrorMessage("该地址已经是默认地址！");
		}
		try {
			// 取消默认地址
			UserAddress userAddress = new UserAddress();
			userAddress.setId(defaultAddressId);
			userAddress.setDefaultAddress(UserConts.USER_ADDRESS_DEFAULT_FASLE);
			userAddress.setModifiedTime(new Date());
			userAddressMapper.updateByPrimaryKeySelective(userAddress);
			// 设置默认地址
			userAddress.setId(addressId);
			userAddress.setDefaultAddress(UserConts.USER_ADDRESS_DEFAULT_TRUE);
			userAddress.setModifiedTime(new Date());
			userAddressMapper.updateByPrimaryKeySelective(userAddress);
		} catch (Exception e) {
			log.error("UserService updateAddressByDefault() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("设置默认收货地址出错");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 删除收货地址
	 */
	@Override
	public JsonData deleteAddress(Integer userId, Integer addressId) {
		// 验证参数
		if (userId == null || addressId == null) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 删除收货地址
		try {
			userAddressMapper.deleteByPrimaryKey(addressId);
			// 设置默认收货地址
			UserAddressExample userAddressExample = new UserAddressExample();
			com.maomao.ssm.bean.UserAddressExample.Criteria userAddressCriteria = userAddressExample.createCriteria();
			userAddressCriteria.andUserIdEqualTo(userId);
			userAddressExample.setOrderByClause("default_address DESC, create_time DESC");
			List<UserAddress> userAddresses = userAddressMapper.selectByExample(userAddressExample);
			if (userAddresses != null && userAddresses.size() != 0) {
				UserAddress userAddress = userAddresses.get(0);
				if (UserConts.USER_ADDRESS_DEFAULT_FASLE.equals(userAddress.getDefaultAddress())) {
					userAddress.setDefaultAddress(UserConts.USER_ADDRESS_DEFAULT_TRUE);
					userAddressMapper.updateByPrimaryKey(userAddress);
				}
			}
		} catch (Exception e) {
			log.error("UserService updateAddressByDefault() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("删除收货地址出错");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 获取用户详情
	 */
	@Override
	public JsonData getUserDetail(Integer userId) {
		// 验证参数
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 查询用户信息
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return JsonData.setErrorMessage("用户不存在！");
		}
		// 构造返回值
		UserDetail userDetail = new UserDetail(user);
		return JsonData.setSuccessMessage(userDetail);
	}

	/**
	 * 绑定身份证
	 */
	@Override
	public JsonData updateUserByIdCard(Integer userId, String name, String idCard, String code) {
		// 验证参数
		if (userId == null || StringUtils.isBlank(name) || StringUtils.isBlank(idCard) || StringUtils.isBlank(code)) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 查询用户信息
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return JsonData.setErrorMessage("用户不存在！");
		}
		if (!StringUtils.isBlank(user.getName()) && !StringUtils.isBlank(user.getIdCard())) {
			return JsonData.setErrorMessage("已绑定身份证，修改请联系客服！");
		}
		String mobile = user.getMobile();
		// 确认验证码
		if (!jedisClientPool.exists(RedisConst.SMS_CODE + mobile)) {
			return JsonData.setErrorMessage("请重新获取验证码");
		}
		// 判断错误次数
		if (jedisClientPool.exists(RedisConst.SMS_CODE_ERROR + mobile) && Integer
				.parseInt(jedisClientPool.get(RedisConst.SMS_CODE_ERROR + mobile)) >= RedisConst.ERROR_COUNT) {
			return JsonData.setErrorMessage("验证码错误超过6次，请重新获取！");
		}
		String redisSmsCode = jedisClientPool.get(RedisConst.SMS_CODE + mobile);
		// 判断验证码是否相同
		if (!redisSmsCode.equals(code)) {
			// 不同 设置错误次数
			jedisClientPool.incr(RedisConst.SMS_CODE_ERROR + mobile);
		}
		// 相同
		jedisClientPool.del(RedisConst.SMS_CODE + mobile);
		jedisClientPool.del(RedisConst.SMS_CODE_ERROR + mobile);
		// 绑定身份证
		user.setName(name);
		user.setIdCard(idCard);
		user.setModifiedTime(new Date());
		try {
			userMapper.updateByPrimaryKey(user);
		} catch (Exception e) {
			log.error("UserService updateUserByIdCard() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("绑定身份证出错");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 原密码修改密码
	 */
	@Override
	public JsonData updatePasswordByPassword(Integer userId, String password, String newPassword) {
		// 验证参数
		if (userId == null || StringUtils.isBlank(password) || StringUtils.isBlank(newPassword)) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 查询用户信息
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return JsonData.setErrorMessage("用户不存在！");
		}
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return JsonData.setErrorMessage("密码错误！");
		}
		// 修改密码
		user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
		user.setModifiedTime(new Date());
		try {
			userMapper.updateByPrimaryKey(user);
		} catch (Exception e) {
			log.error("UserService updatePasswordByPassword() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("原密码修改密码出错");
		}
		// 删除cookie
		String cookie = jedisClientPool.get(userId + "");
		jedisClientPool.del(userId + "");
		jedisClientPool.del(RedisConst.LOGIN_SUCCESS + cookie);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 验证码修改密码
	 */
	@Override
	public JsonData updatePasswordBySms(Integer userId, String password, String code) {
		// 验证参数
		if (userId == null || StringUtils.isBlank(password) || StringUtils.isBlank(code)) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 查询用户信息
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return JsonData.setErrorMessage("用户不存在！");
		}
		String mobile = user.getMobile();
		// 确认验证码
		if (!jedisClientPool.exists(RedisConst.SMS_CODE + mobile)) {
			return JsonData.setErrorMessage("请重新获取验证码");
		}
		// 判断错误次数
		if (jedisClientPool.exists(RedisConst.SMS_CODE_ERROR + mobile) && Integer
				.parseInt(jedisClientPool.get(RedisConst.SMS_CODE_ERROR + mobile)) >= RedisConst.ERROR_COUNT) {
			return JsonData.setErrorMessage("验证码错误超过6次，请重新获取！");
		}
		String redisSmsCode = jedisClientPool.get(RedisConst.SMS_CODE + mobile);
		// 判断验证码是否相同
		if (!redisSmsCode.equals(code)) {
			// 不同 设置错误次数
			jedisClientPool.incr(RedisConst.SMS_CODE_ERROR + mobile);
			return JsonData.setErrorMessage("验证码错误");
		}
		// 相同
		jedisClientPool.del(RedisConst.SMS_CODE + mobile);
		jedisClientPool.del(RedisConst.SMS_CODE_ERROR + mobile);
		// 修改密码
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		user.setModifiedTime(new Date());
		try {
			userMapper.updateByPrimaryKey(user);
		} catch (Exception e) {
			log.error("UserService updatePasswordBySms() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("验证码修改密码出错");
		}
		// 删除cookie
		String cookie = jedisClientPool.get(userId + "");
		jedisClientPool.del(userId + "");
		jedisClientPool.del(RedisConst.LOGIN_SUCCESS + cookie);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 我的反馈列表
	 */
	@Override
	public JsonData getFeedbackList(Integer userId, Integer pages, Integer rows) {
		// 验证参数
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法！");
		}
		pages = pages == null || pages < 3 ? PageConst.PAGE_DEFAULT : pages;
		rows = rows == null ? pages == PageConst.PAGE_DEFAULT ? PageConst.ROWS_DEFAULT_FIRST : PageConst.ROWS_DEFAULT
				: rows;
		// 查询反馈列表
		PageHelper.startPage(pages, rows);
		UserFeedbackExample userFeedbackExample = new UserFeedbackExample();
		com.maomao.ssm.bean.UserFeedbackExample.Criteria userFeedbackCriteria = userFeedbackExample.createCriteria();
		userFeedbackCriteria.andUserIdEqualTo(userId);
		userFeedbackExample.setOrderByClause("create_time DESC");
		PageHelper.startPage(pages, rows);
		List<UserFeedback> userFeedbacks = userFeedBackMapper.selectByExample(userFeedbackExample);
		// 构造返回值
		List<UserFeedbackVO> userFeedbackVOs = new ArrayList<UserFeedbackVO>();
		for (UserFeedback userFeedback : userFeedbacks) {
			userFeedbackVOs.add(new UserFeedbackVO(userFeedback));
		}
		return JsonData.setSuccessMessage(userFeedbackVOs);
	}

	/**
	 * 用户反馈
	 */
	@Override
	public JsonData addFeedback(Integer userId, String content) {
		// 验证参数
		if (userId == null || StringUtils.isBlank(content)) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 添加反馈
		UserFeedback userFeedback = new UserFeedback();
		userFeedback.setContent(content);
		userFeedback.setUserId(userId);
		userFeedback.setCreateTime(new Date());
		try {
			userFeedBackMapper.insert(userFeedback);
		} catch (Exception e) {
			log.error("UserService addFeedback() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("用户反馈出错");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 银行卡列表
	 */
	@Override
	public JsonData getUserBankList(Integer userId, Integer pages, Integer rows) {
		// 验证参数
		if (userId == null) {
			return JsonData.setErrorMessage("参数非法！");
		}
		pages = pages == null || pages < 3 ? PageConst.PAGE_DEFAULT : pages;
		rows = rows == null ? pages == PageConst.PAGE_DEFAULT ? PageConst.ROWS_DEFAULT_FIRST : PageConst.ROWS_DEFAULT
				: rows;
		// 查询银行卡列表
		PageHelper.startPage(pages, rows);
		UserBankExample userBankExample = new UserBankExample();
		com.maomao.ssm.bean.UserBankExample.Criteria userBankCriteria = userBankExample.createCriteria();
		userBankCriteria.andUserIdEqualTo(userId);
		List<UserBank> userBanks = userBankMapper.selectByExample(userBankExample);
		// 构造返回值
		List<UserBankVO> userBankVOs = new ArrayList<UserBankVO>();
		if (userBanks != null && userBanks.size() > 0) {
			for (UserBank userBank : userBanks) {
				userBankVOs.add(new UserBankVO(userBank));
			}
		}
		return JsonData.setSuccessMessage(userBankVOs);
	}

	/**
	 * 添加银行卡
	 */
	@Override
	public JsonData addUserBank(Integer userId, String bankName, String account, String userName, String accountsBank) {
		// 验证参数
		if (userId == null || StringUtils.isBlank(bankName) || StringUtils.isBlank(account)
				|| StringUtils.isBlank(userName)) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 判断银行卡是否重复
		UserBankExample userBankExample = new UserBankExample();
		com.maomao.ssm.bean.UserBankExample.Criteria userBankCriteria = userBankExample.createCriteria();
		userBankCriteria.andUserIdEqualTo(userId).andAccountEqualTo(account);
		List<UserBank> userBanks = userBankMapper.selectByExample(userBankExample);
		if (userBanks != null && userBanks.size() != 0) {
			return JsonData.setErrorMessage("请勿重复绑定银行卡！");
		}
		// 添加银行卡
		UserBank userBank = new UserBank();
		userBank.setAccount(account);
		userBank.setBankName(bankName);
		userBank.setUserId(userId);
		userBank.setUserName(userName);
		userBank.setAccountsBank(accountsBank);
		userBank.setCreateTime(new Date());
		try {
			userBankMapper.insert(userBank);
		} catch (Exception e) {
			log.error("UserService addUserBank() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("添加银行卡出错");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 删除银行卡
	 */
	@Override
	public JsonData deleteUserBank(Integer userId, Integer userBankId) {
		// 验证参数
		if (userId == null || userBankId == null) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 删除银行卡
		try {
			userBankMapper.deleteByPrimaryKey(userBankId);
		} catch (Exception e) {
			log.error("UserService deleteUserBank() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("删除银行卡出错");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 填写邀请码
	 */
	@Override
	public JsonData updateUserByInvitation(Integer userId, String invitation) {
		// 验证参数
		if (userId == null || invitation == null) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 查询用户信息
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return JsonData.setErrorMessage("用户不存在！");
		}
		if (!StringUtils.isBlank(user.getInvitation())) {
			return JsonData.setErrorMessage("已填写邀请人！");
		}

		// 查询邀请人信息
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(invitation);
		List<User> users = userMapper.selectByExample(example);
		if (users == null || users.size() == 0) {
			return JsonData.setErrorMessage("邀请人未注册！");
		}
		if (!UserConts.USER_TYPE_B.equals(users.get(0).getType())) {
			return JsonData.setErrorMessage("邀请人未开店，不能进行邀请！");
		}
		if (user.getMobile().equals(invitation)) {
			return JsonData.setErrorMessage("请不要填写自己的手机号！");
		}
		// 邀请人正常 发优惠卷
		user.setInvitation(invitation);
		Integer invitationId = users.get(0).getId();
		try {
			userMapper.updateByPrimaryKey(user);
			CouponExample couponExample = new CouponExample();
			couponExample.createCriteria().andTypeEqualTo(CouponConts.COUPON_TYPE_INVITATION)
					.andStatusEqualTo(CouponConts.COUPON_STARTUS_NOT_DEL);
			List<Coupon> coupons = couponMapper.selectByExample(couponExample);
			couponRecordService.addCouponRecord(userId, coupons);
			couponRecordService.addCouponRecord(invitationId, coupons);
		} catch (Exception e) {
			log.error("UserService updateUserByInvitation() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("添加优惠券出错");
		}

		return JsonData.setSuccessMessage();
	}

	/**
	 * 获取银行列表
	 */
	@Override
	public JsonData getBankList() {
		String bankList = jedisClientPool.get(RedisConst.BANK_LIST);
		return JsonUtils.jsonToPojo(bankList, JsonData.class);
	}

	/**
	 * 设置提现密码
	 */
	@Override
	public JsonData updatePasswordWithdrawals(Integer userId, String password) {
		// 验证参数
		if (userId == null || StringUtils.isBlank(password)) {
			return JsonData.setErrorMessage("参数非法！");
		}
		if (!password.matches("^\\d{6}$")) {
			return JsonData.setErrorMessage("提现密码应为6位数字");
		}
		// 查询用户信息
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return JsonData.setErrorMessage("用户不存在！");
		}
		if (!StringUtils.isBlank(user.getPasswordWithdrawals())) {
			return JsonData.setErrorMessage("已填写邀请人！");
		}
		user.setPasswordWithdrawals(DigestUtils.md5DigestAsHex(password.getBytes()));
		user.setModifiedTime(new Date());
		try {
			userMapper.updateByPrimaryKey(user);
		} catch (Exception e) {
			log.error("UserService updatePasswordWithdrawals() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("修改提现密码出错");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 原密码修改提现密码
	 */
	@Override
	public JsonData updatePasswordWithdrawalsByPassword(Integer userId, String password, String newPassword) {
		// 验证参数
		if (userId == null || StringUtils.isBlank(password) || StringUtils.isBlank(newPassword)) {
			return JsonData.setErrorMessage("参数非法！");
		}
		if (!password.matches("^\\d{6}$")) {
			return JsonData.setErrorMessage("提现密码应为6位数字");
		}
		// 查询用户信息
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return JsonData.setErrorMessage("用户不存在！");
		}
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPasswordWithdrawals())) {
			return JsonData.setErrorMessage("密码错误！");
		}
		// 修改密码
		user.setPasswordWithdrawals(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
		user.setModifiedTime(new Date());
		try {
			userMapper.updateByPrimaryKey(user);
		} catch (Exception e) {
			log.error("UserService updatePasswordByPassword() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("原密码修改密码出错");
		}
		// 删除错误次数
		jedisClientPool.del(RedisConst.PASSWORD_WITHDRAWALS_ERROR + userId);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 验证码修改提现密码
	 */
	@Override
	public JsonData updatePasswordWithdrawalsBySms(Integer userId, String password, String code) {
		// 验证参数
		if (userId == null || StringUtils.isBlank(password) || StringUtils.isBlank(code)) {
			return JsonData.setErrorMessage("参数非法！");
		}
		if (!password.matches("^\\d{6}$")) {
			return JsonData.setErrorMessage("提现密码应为6位数字");
		}
		// 查询用户信息
		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return JsonData.setErrorMessage("用户不存在！");
		}
		String mobile = user.getMobile();
		// 确认验证码
		if (!jedisClientPool.exists(RedisConst.SMS_CODE + mobile)) {
			return JsonData.setErrorMessage("请重新获取验证码");
		}
		// 判断错误次数
		if (jedisClientPool.exists(RedisConst.SMS_CODE_ERROR + mobile) && Integer
				.parseInt(jedisClientPool.get(RedisConst.SMS_CODE_ERROR + mobile)) >= RedisConst.ERROR_COUNT) {
			return JsonData.setErrorMessage("验证码错误超过6次，请重新获取！");
		}
		String redisSmsCode = jedisClientPool.get(RedisConst.SMS_CODE + mobile);
		// 判断验证码是否相同
		if (!redisSmsCode.equals(code)) {
			// 不同 设置错误次数
			jedisClientPool.incr(RedisConst.SMS_CODE_ERROR + mobile);
			return JsonData.setErrorMessage("验证码错误");
		}
		// 相同
		jedisClientPool.del(RedisConst.SMS_CODE + mobile);
		jedisClientPool.del(RedisConst.SMS_CODE_ERROR + mobile);
		// 修改密码
		user.setPasswordWithdrawals(DigestUtils.md5DigestAsHex(password.getBytes()));
		user.setModifiedTime(new Date());
		try {
			userMapper.updateByPrimaryKey(user);
		} catch (Exception e) {
			log.error("UserService updatePasswordBySms() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("验证码修改密码出错");
		}
		// 删除错误次数
		jedisClientPool.del(RedisConst.PASSWORD_WITHDRAWALS_ERROR + userId);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 供应商登录
	 */
	@Override
	public JsonData adminLogin(String mobile, String password) {
		if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
			return JsonData.setErrorMessage("参数非法！");
		}
		AdminExample adminExample = new AdminExample();
		adminExample.createCriteria().andMobileEqualTo(mobile)
				.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
		List<Admin> admins = adminMapper.selectByExample(adminExample);
		if (admins == null || admins.size() == 0) {
			return JsonData.setErrorMessage("帐号或密码错误");
		}
		Map<String, Integer> result = new HashMap<String, Integer>();
		result.put("adminId", admins.get(0).getId());
		return JsonData.setSuccessMessage(result);
	}

	/**
	 * 核销列表
	 */
	@Override
	public JsonData getAdminOrderCodeGoodsInfoList(Integer pages, Integer rows, Integer adminId) {
		// 验证参数
		if (adminId == null) {
			return JsonData.setErrorMessage("参数非法！");
		}
		pages = pages == null || pages < 3 ? PageConst.PAGE_DEFAULT : pages;
		rows = rows == null ? pages == PageConst.PAGE_DEFAULT ? PageConst.ROWS_DEFAULT_FIRST : PageConst.ROWS_DEFAULT
				: rows;
		// 查询核销列表
		PageHelper.startPage(pages, rows);
		GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
		goodsOrderGoodsInfoExample.createCriteria().andAdminIdEqualTo(adminId)
				.andStatusEqualTo(OrderConst.ORDER_STATUS_FINISHED)
				.andTypeEqualTo(GoodsConst.GOODS_OVERSTOCK_TYPE_CODE);
		goodsOrderGoodsInfoExample.setOrderByClause("modified_time DESC");
		List<GoodsOrderGoodsInfo> goodsOrderGoodsInfos = goodsOrderGoodsInfoMapper
				.selectByExample(goodsOrderGoodsInfoExample);
		return JsonData.setSuccessMessage(goodsOrderGoodsInfos);
	}

	/**
	 * 核销
	 */
	@Override
	public JsonData updateOrderGoodsInfoByUse(Integer adminId, String code) {
		// 验证参数
		if (adminId == null || StringUtils.isEmpty(code)) {
			return JsonData.setErrorMessage("参数非法！");
		}
		// 查询商品信息
		GoodsOrderGoodsInfoExample goodsOrderGoodsInfoExample = new GoodsOrderGoodsInfoExample();
		goodsOrderGoodsInfoExample.createCriteria().andAdminIdEqualTo(adminId).andCodeEqualTo(code.toUpperCase())
				.andTypeEqualTo(GoodsConst.GOODS_OVERSTOCK_TYPE_CODE);
		List<GoodsOrderGoodsInfo> goodsOrderGoodsInfos = goodsOrderGoodsInfoMapper
				.selectByExample(goodsOrderGoodsInfoExample);
		if (goodsOrderGoodsInfos == null || goodsOrderGoodsInfos.size() == 0) {
			return JsonData.setErrorMessage("核销码错误");
		}
		GoodsOrderGoodsInfo goodsOrderGoodsInfo = goodsOrderGoodsInfos.get(0);
		if (goodsOrderGoodsInfo.getStatus().equals(OrderConst.ORDER_STATUS_FINISHED)) {
			return JsonData.setErrorMessage("核销码已使用");
		}
		if (goodsOrderGoodsInfo.getStatus().equals(OrderConst.ORDER_STATUS_CLOSED)) {
			return JsonData.setErrorMessage("核销码已过期");
		}
		// 核销成功 修改状态
		goodsOrderGoodsInfo.setModifiedTime(new Date());
		goodsOrderGoodsInfo.setStatus(OrderConst.ORDER_STATUS_FINISHED);
		goodsOrderGoodsInfoMapper.updateByPrimaryKey(goodsOrderGoodsInfo);
		// 供应商结钱
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
						UserConts.USER_MONEY_TYPE_SALES, goodsOrderGoodsInfo.getOrderId());
				admin.setMoney(admin.getMoney() + adminMoney);
				adminMapper.updateByPrimaryKey(admin);
			}

			// 能
			if (adminInfo != null && adminInfo.getLoan() < adminMoney && adminInfo.getLoan() != 0) {
				// 更新金额
				// 还清贷款
				adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -adminInfo.getLoan(),
						goodsOrderGoodsInfo.getOrderId());
				adminInfo.setLoan(0l);
				adminInfoMapper.updateByPrimaryKey(adminInfo);
				// 剩余部分添加到账户内
				adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), adminMoney - adminInfo.getLoan(),
						UserConts.USER_MONEY_TYPE_SALES, goodsOrderGoodsInfo.getOrderId());
				admin.setMoney(admin.getMoney() + adminMoney - adminInfo.getLoan());
				adminMapper.updateByPrimaryKey(admin);
			}
			// 不能 或 刚好还清
			if (adminInfo != null && adminInfo.getLoan() >= adminMoney) {
				// 还贷款
				// 更新金额
				adminLoanService.addAdminLoan(admin.getId(), adminInfo.getLoan(), -adminMoney,
						goodsOrderGoodsInfo.getOrderId());
				adminInfo.setLoan(admin.getMoney() - adminInfo.getLoan());
				adminInfoMapper.updateByPrimaryKey(adminInfo);
			}
		}
		return JsonData.setSuccessMessage();
	}
}
