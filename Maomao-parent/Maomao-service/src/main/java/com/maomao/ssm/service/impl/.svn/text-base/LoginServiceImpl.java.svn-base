package com.maomao.ssm.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;

import com.maomao.ssm.bean.Coupon;
import com.maomao.ssm.bean.CouponExample;
import com.maomao.ssm.bean.Notice;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserExample;
import com.maomao.ssm.bean.UserExample.Criteria;
import com.maomao.ssm.constant.CouponConts;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.SmsConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.CouponMapper;
import com.maomao.ssm.dao.NoticeMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.LoginService;
import com.maomao.ssm.service.common.CouponRecordService;
import com.maomao.ssm.utils.SendSmsUtil;
import com.maomao.ssm.utils.SmsCodeUtils;

@Service
public class LoginServiceImpl implements LoginService {

	private Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private JedisClientPool jedisClientPool;

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CouponRecordService couponRecordService;
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private CouponMapper couponMapper;

	/**
	 * 用户登录
	 */
	public JsonData login(String mobile, String password) {
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(password)) {
			return JsonData.setErrorMessage("用户名或密码错误");
		}
		// 判断用户有没有被限制登录
		if (jedisClientPool.exists(RedisConst.LIMIT_USER + mobile)) {
			return JsonData
					.setErrorMessage("登录失败!,密码错误次数过多,请" + (jedisClientPool.ttl(RedisConst.LIMIT_USER + mobile) > 60
							? (jedisClientPool.ttl(RedisConst.LIMIT_USER + mobile) / 60)
							: 1) + "分钟后重试!");
		}

		// 查询数据库,验证用户名和密码
		String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(mobile).andPasswordEqualTo(md5Password);
		List<User> loginUser = userMapper.selectByExample(example);
		if (loginUser != null && loginUser.size() > 0) {
			Map map = new HashMap();
			List cookie = new ArrayList();
			User user = loginUser.get(0);
			user.setPassword(null);
			Integer userId = user.getId();
			Byte userType = user.getType();
			String randomCookie = UUID.randomUUID().toString();
			String resultStr = "maomao.cookie=" + randomCookie + ";path=/;domain=mao-mall.com";
			cookie.add(resultStr);
			map.put("userId", userId);
			map.put("type", userType);
			map.put("cookie", cookie);
			// 删除之前登录的cookie
			if (jedisClientPool.exists(userId + "")) {
				String oldCookie = jedisClientPool.get(userId + "");
				jedisClientPool.del(oldCookie);
				jedisClientPool.del(userId + "");
			}
			jedisClientPool.set(RedisConst.LOGIN_SUCCESS + randomCookie, userId + "");
			jedisClientPool.set(userId + "", RedisConst.LOGIN_SUCCESS + randomCookie);
			jedisClientPool.expire(RedisConst.LOGIN_SUCCESS + randomCookie, RedisConst.COOKIE_EXPIRE_TIME);// 设置cookie有效期30天
			jedisClientPool.expire(userId + "", RedisConst.COOKIE_EXPIRE_TIME);
			jedisClientPool.del(RedisConst.LIMIT_USER + mobile);
			return JsonData.setSuccessMessage(map);
		}

		// 如果验证失败
		jedisClientPool.incr(RedisConst.LOGIN_ERROR + mobile);
		jedisClientPool.expire(RedisConst.LOGIN_ERROR + mobile, RedisConst.ERROR_TIME);

		if (Integer.parseInt(jedisClientPool.get(RedisConst.LOGIN_ERROR + mobile)) >= RedisConst.ERROR_COUNT) {
			jedisClientPool.set(RedisConst.LIMIT_USER + mobile, password);
			jedisClientPool.expire(RedisConst.LIMIT_USER + mobile, RedisConst.LIMIT_TIME);
			return JsonData.setErrorMessage("登录失败!,密码错误次数过多,请30分钟后重试!");
		}
		return JsonData.setErrorMessage("用户名或密码错误!");
	}

	/**
	 * 用户注册
	 */
	public JsonData register(String mobile, String randomCode, String password, String invitation) {
		// 验证参数
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(randomCode) || StringUtils.isBlank(password)) {
			return JsonData.setErrorMessage("参数错误！");
		}
		// 判断用户是否已注册
		UserExample userExample = new UserExample();
		userExample.createCriteria().andMobileEqualTo(mobile);
		int registerUserNum = userMapper.countByExample(userExample);
		if (registerUserNum > 0) {
			return JsonData.setErrorMessage("手机号已注册！");
		}
		User user = new User();
		// 判断邀请人
		int invitationId = 0;
		if (!StringUtils.isBlank(invitation)) {
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
			// 邀请人正常 发优惠卷
			user.setInvitation(invitation);
			invitationId = users.get(0).getId();
		}

		// 判断随机码
		if (!jedisClientPool.exists(RedisConst.SMS_RANDOM_CODE + mobile)) {
			return JsonData.setErrorMessage("随机码不存在，请重新获取验证码！");
		}
		if (!jedisClientPool.get(RedisConst.SMS_RANDOM_CODE + mobile).equals(randomCode)) {
			return JsonData.setErrorMessage("随机码错误，请重新获取验证码！");
		}

		// 注册
		user.setMobile(mobile);
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		user.setType(UserConts.USER_TYPE_B);
		user.setStatus(UserConts.USER_STATUS);
		user.setImg(UserConts.USER_IMG_DEFAULT);
		user.setMoney(0L);
		user.setCreditTotal(0l);
		user.setCreditSurplus(0l);
		user.setCreditReal(UserConts.USER_CREDIT_REAL_FALSE);
		user.setCreateTime(new Date());
		user.setCheckStatus(UserConts.USER_CHECK_STATUS_TRUE);
		user.setAccumulativeMoney(0l);
		user.setLoan(0l);
		try {
			userMapper.insert(user);
		} catch (Exception e) {
			log.error("UserService LoginService() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("添加用户出错");
		}
		int userId = user.getId();
		try {
			// 发放优惠券
			if (!StringUtils.isBlank(invitation)) {
				CouponExample couponExample = new CouponExample();
				couponExample.createCriteria().andTypeEqualTo(CouponConts.COUPON_TYPE_INVITATION)
						.andStatusEqualTo(CouponConts.COUPON_STARTUS_NOT_DEL);
				List<Coupon> coupons = couponMapper.selectByExample(couponExample);
				couponRecordService.addCouponRecord(userId, coupons);
				couponRecordService.addCouponRecord(invitationId, coupons);
			}

			// 注册优惠券
			CouponExample couponExample = new CouponExample();
			couponExample.createCriteria().andTypeEqualTo(CouponConts.COUPON_TYPE_REGISTER)
					.andStatusEqualTo(CouponConts.COUPON_STARTUS_NOT_DEL);
			List<Coupon> coupons = couponMapper.selectByExample(couponExample);
			couponRecordService.addCouponRecord(userId, coupons);
		} catch (Exception e) {
			log.error("UserService LoginService() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("添加优惠券出错");
		}
		jedisClientPool.del(RedisConst.SMS_RANDOM_CODE + mobile);

		return JsonData.setSuccessMessage();

	}

	/**
	 * 用户注销
	 */
	public JsonData logout(Integer userId) {
		try {
			String cookie = jedisClientPool.get(userId + "");
			jedisClientPool.del(userId + "");
			jedisClientPool.del(cookie);
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			return JsonData.setErrorMessage("服务器异常");
		}
	}

	/**
	 * 获取验证码
	 */
	public JsonData getSmsCode(String mobile, Integer type) {
		// 验证参数
		if (StringUtils.isBlank(mobile) || (!SmsConst.SMS_CODE_TYPE_REGISTER.equals(type)
				&& !SmsConst.SMS_CODE_TYPE_FORGET_PASSWORD.equals(type)
				&& !SmsConst.SMS_CODE_TYPE_BINDING_ID_CARD.equals(type)
				&& !SmsConst.SMS_CODE_TYPE_UPDATE_PASSWORD.equals(type))) {
			return JsonData.setErrorMessage("参数错误，请检查！");
		}

		// 设置发送间隔120s
		if (jedisClientPool.exists(RedisConst.SMS_CODE_LIMIT + mobile)) {
			return JsonData.setErrorMessage("请勿在两分钟内重新获取验证码");
		}
		jedisClientPool.expire(RedisConst.SMS_CODE_LIMIT + mobile, RedisConst.SMS_CODE_LIMIT_TIME);

		// 获取用户，验证手机号
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andMobileEqualTo(mobile);
		List<User> users = userMapper.selectByExample(userExample);

		if (SmsConst.SMS_CODE_TYPE_REGISTER.equals(type) && (users != null && users.size() > 0)) {
			return JsonData.setErrorMessage("手机号已被注册！");
		}
		if ((SmsConst.SMS_CODE_TYPE_FORGET_PASSWORD.equals(type) || SmsConst.SMS_CODE_TYPE_BINDING_ID_CARD.equals(type)
				|| SmsConst.SMS_CODE_TYPE_UPDATE_PASSWORD.equals(type)) && (users == null || users.size() == 0)) {
			return JsonData.setErrorMessage("手机号未注册！");
		}
		if (SmsConst.SMS_CODE_TYPE_BINDING_ID_CARD.equals(type)
				&& (!StringUtils.isBlank(users.get(0).getName()) && !StringUtils.isBlank(users.get(0).getIdCard()))) {
			return JsonData.setErrorMessage("已绑定身份证！");
		}
		// 删除验证码错误次数
		jedisClientPool.del(RedisConst.SMS_CODE_ERROR + mobile);
		// 发送验证码
		Notice notice = noticeMapper.selectByPrimaryKey(NoticeConst.NOTICE_ID_SMS);
		String smsCode = jedisClientPool.get(RedisConst.SMS_CODE + mobile);
		if (smsCode == null) {
			smsCode = SmsCodeUtils.getSmsCode();
			jedisClientPool.set(RedisConst.SMS_CODE + mobile, smsCode);
		}
		jedisClientPool.expire(RedisConst.SMS_CODE + mobile, RedisConst.SMS_CODE_EXPIRE_TIME);
		String sms = MessageFormat.format(notice.getModel(), smsCode);
		// TODO 调用短信接口，发送短信 sms
		// System.out.println("smsCode=" + smsCode);
		SendSmsUtil.sendSms(mobile, sms);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 获取随机码
	 */
	public JsonData getRandomCode(String mobile, String code) {
		// 验证参数
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(code)) {
			return JsonData.setErrorMessage("手机号或验证码不能为空！");
		}

		// 判断验证码是否存在
		if (jedisClientPool.exists(RedisConst.SMS_CODE + mobile)) {
			// 判断错误次数
			if (jedisClientPool.exists(RedisConst.SMS_CODE_ERROR + mobile) && Integer
					.parseInt(jedisClientPool.get(RedisConst.SMS_CODE_ERROR + mobile)) >= RedisConst.ERROR_COUNT) {
				return JsonData.setErrorMessage("验证码错误超过6次，请重新获取！");
			}

			String redisSmsCode = jedisClientPool.get(RedisConst.SMS_CODE + mobile);
			// 判断验证码是否相同
			if (redisSmsCode.equals(code)) {
				jedisClientPool.del(RedisConst.SMS_CODE + mobile);
				String uuid = UUID.randomUUID().toString();
				jedisClientPool.set(RedisConst.SMS_RANDOM_CODE + mobile, uuid);
				jedisClientPool.expire(RedisConst.SMS_RANDOM_CODE + mobile, RedisConst.SMS_RANDOM_CODE_LIMIT_TIME);
				jedisClientPool.del(RedisConst.SMS_CODE_ERROR + mobile);
				// System.out.println("uuid=" + uuid);
				Map<String, String> map = new HashMap<String, String>();
				map.put("msg", uuid);
				return JsonData.setSuccessMessage(map);
			}
			// 不同 设置错误次数
			jedisClientPool.incr(RedisConst.SMS_CODE_ERROR + mobile);
			return JsonData.setErrorMessage("验证码错误");
		}
		return JsonData.setErrorMessage("请重新获取验证码！");
	}

	/**
	 * 根据验证码修改密码
	 */
	@Override
	public JsonData updatePassword(String mobile, String password, String randomCode) {
		// 验证参数
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(randomCode) || StringUtils.isBlank(password)) {
			return JsonData.setErrorMessage("参数错误！");
		}

		// 判断随机码
		if (!jedisClientPool.exists(RedisConst.SMS_RANDOM_CODE + mobile)) {
			return JsonData.setErrorMessage("随机码不存在，请重新获取验证码！");
		}
		if (!jedisClientPool.get(RedisConst.SMS_RANDOM_CODE + mobile).equals(randomCode)) {
			return JsonData.setErrorMessage("随机码错误，请重新获取验证码！");
		}

		// 查询用户
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andMobileEqualTo(mobile);
		List<User> users = userMapper.selectByExample(userExample);

		if (users == null || users.size() == 0) {
			return JsonData.setErrorMessage("用户未注册！");
		}

		User user = users.get(0);
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

		try {
			userMapper.updateByPrimaryKey(user);
		} catch (Exception e) {
			log.error("UserService updatePassword() error:" + e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setServerErrorMessage("修改密码出错");
		}

		// 删除随机码
		jedisClientPool.del(RedisConst.SMS_RANDOM_CODE + mobile);
		return JsonData.setSuccessMessage();
	}

}
