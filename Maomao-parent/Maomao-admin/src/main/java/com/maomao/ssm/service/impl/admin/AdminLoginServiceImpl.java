package com.maomao.ssm.service.impl.admin;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;

import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.AdminExample;
import com.maomao.ssm.bean.AdminInfo;
import com.maomao.ssm.bean.AdminInfoExample;
import com.maomao.ssm.bean.AdminRole;
import com.maomao.ssm.bean.AdminRoleExample;
import com.maomao.ssm.bean.Notice;
import com.maomao.ssm.bean.Role;
import com.maomao.ssm.constant.AdminConst;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.RoleConst;
import com.maomao.ssm.dao.AdminInfoMapper;
import com.maomao.ssm.dao.AdminMapper;
import com.maomao.ssm.dao.AdminRoleMapper;
import com.maomao.ssm.dao.NoticeMapper;
import com.maomao.ssm.dao.RoleMapper;
import com.maomao.ssm.pojo.AdminAccountInfo;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.AdminLoginService;
import com.maomao.ssm.service.admin.MenuService;
import com.maomao.ssm.utils.JsonUtils;
import com.maomao.ssm.utils.SendSmsUtil;
import com.maomao.ssm.utils.SmsCodeUtils;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年2月27日
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private AdminInfoMapper adminInfoMapper;
	@Autowired
	private JedisClientPool jedisClientPool;
	@Autowired
	private MenuService menuService;
	@Autowired
	private AdminRoleMapper adminRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private NoticeMapper noticeMapper;

	/**
	 * 用户登录
	 */
	public JsonData login(String mobile, String password, Byte rember) {
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(password)) {
			return JsonData.setErrorMessage("参数异常");
		}

		// 判断用户有没有被限制登录
		if (jedisClientPool.exists(AdminConst.ADMIN_LOGIN_LIMIT + mobile)) {
			return JsonData.setErrorMessage("登录失败!,密码错误次数过多,请稍后重试");
		}

		AdminExample example = new AdminExample();
		String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
		example.createCriteria().andMobileEqualTo(mobile).andPasswordEqualTo(md5Password);
		List<Admin> loginUser = adminMapper.selectByExample(example);
		if (loginUser != null && loginUser.size() > 0) {
			Admin admin = loginUser.get(0);
			admin.setPassword(null);
			Integer userId = admin.getId();
			String token = UUID.randomUUID().toString().replaceAll("-", "");

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("mobile", admin.getMobile());
			map.put("name", admin.getName());
			map.put("cookie", token);
			map.put("type", admin.getType());
			String role = null;
			// 查询用户对应的权限
			// 查询缓存 ,adminId对应的角色id
			Integer roleId = null;
			roleId = 1;
			if (jedisClientPool.exists(RoleConst.ADMIN_ROLE_ID + userId)) {
				roleId = Integer.parseInt(jedisClientPool.get(RoleConst.ADMIN_ROLE_ID + userId));
			} else {
				AdminRoleExample example2 = new AdminRoleExample();
				example2.createCriteria().andAdminIdEqualTo(userId);
				List<AdminRole> roleList = adminRoleMapper.selectByExample(example2);

				if (roleList != null && roleList.size() > 0) {
					roleId = roleList.get(0).getRoleId();
					// 添加缓存
					jedisClientPool.set(RoleConst.ADMIN_ROLE_ID + userId, roleId + "");
				}
			}
			// 如果需要返回 "用户对应权限的详细信息",返回这个
			/*
			 * if
			 * (jedisClientPool.exists(RoleConst.ROLE_PERMISSION_DETAIL+roleId))
			 * { role =
			 * jedisClientPool.get(RoleConst.ROLE_PERMISSION_DETAIL+roleId);
			 * map.put("role", role); }else { List<MenuVO> roleList =
			 * menuService.getRolePermissionNotJsonData(roleId); map.put("role",
			 * roleList); }
			 */

			// 如果需要返回 "用户对应权限菜单的id数组",返回这个

			List<Integer> ids = menuService.getRolePermissionAsArray(roleId);
			map.put("role", ids);

			String userJson = JsonUtils.objectToJson(map);

			String cookie = token;

			jedisClientPool.hdel(AdminConst.AMDIN_LOGIN_SUCCESS + userId, "userInfo");
			String redisToken = jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS + userId, "token");
			if (StringUtils.isNotBlank(redisToken)) {
				jedisClientPool.hdel(AdminConst.AMDIN_LOGIN_SUCCESS + userId, "token");
				jedisClientPool.hdel(AdminConst.AMDIN_LOGIN_SUCCESS, redisToken);
			}

			jedisClientPool.hset(AdminConst.AMDIN_LOGIN_SUCCESS + userId, "userInfo", userJson);
			jedisClientPool.hset(AdminConst.AMDIN_LOGIN_SUCCESS + userId, "token", token);
			jedisClientPool.hset(AdminConst.AMDIN_LOGIN_SUCCESS, token, userId + "");
			List<Object> returnList = new ArrayList<Object>();
			returnList.add(cookie);
			returnList.add(map);
			return JsonData.setSuccessMessage(returnList);
		}
		jedisClientPool.incr(AdminConst.ADMIN_LOGIN_ERROR + mobile);
		jedisClientPool.expire(AdminConst.ADMIN_LOGIN_ERROR + mobile, AdminConst.ADMIN_LOGIN_ERROR_TIME);

		if (Integer
				.parseInt(jedisClientPool.get(AdminConst.ADMIN_LOGIN_ERROR + mobile)) >= AdminConst.ADMIN_ERROR_COUNT) {
			jedisClientPool.set(AdminConst.ADMIN_LOGIN_LIMIT + mobile, password);
			jedisClientPool.expire(AdminConst.ADMIN_LOGIN_LIMIT + mobile, AdminConst.ADMIN_LOGIN_LIMIT_TIME);
			return JsonData.setErrorMessage("密码错误次数过多,请30分钟后重试或者修改密码");
		}
		return JsonData.setErrorMessage("用户名或密码错误!");
	}

	/**
	 * 发送短信验证码
	 */
	public JsonData getSmsCode(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return JsonData.setErrorMessage("手机号格式错误");
		}

		AdminExample example = new AdminExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List<Admin> admin = adminMapper.selectByExample(example);
		if (admin == null || admin.size() == 0) {
			return JsonData.setErrorMessage("手机号未注册");
		}

		// 发送验证码
		String smsCode = SmsCodeUtils.getSmsCode();
		Notice notice = noticeMapper.selectByPrimaryKey(NoticeConst.NOTICE_ID_SMS);
		jedisClientPool.set(RedisConst.SMS_CODE + mobile, smsCode);
		jedisClientPool.expire(RedisConst.SMS_CODE + mobile, RedisConst.SMS_CODE_EXPIRE_TIME);
		String sms = MessageFormat.format(notice.getModel(), smsCode);
		try {
			SendSmsUtil.sendSms(mobile, sms);
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("验证码获取失败,请重试");
		}
	}

	/**
	 * 校验短信验证码
	 */
	public JsonData checkSmsCode(String mobile, String code) {
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(code)) {
			return JsonData.setErrorMessage("参数错误");
		}
		String smsCode = jedisClientPool.get(RedisConst.SMS_CODE + mobile);
		if (StringUtils.isNotBlank(smsCode)) {
			if (code.equals(smsCode)) {
				String token = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
				jedisClientPool.set(RedisConst.MODIFIED_PASSWORD_TOKEN + token, token);
				jedisClientPool.expire(RedisConst.MODIFIED_PASSWORD_TOKEN, RedisConst.SMS_CODE_EXPIRE_TIME);
				Map<String, String> map = new HashMap<String, String>();
				map.put("token", token);
				return JsonData.setSuccessMessage(map);
			}
		}
		return JsonData.setErrorMessage("验证码错误");
	}

	/**
	 * 重置密码
	 */
	public JsonData updatePassword(String mobile, String password, String token) {
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(password) || StringUtils.isBlank(token)
				|| !jedisClientPool.exists(RedisConst.MODIFIED_PASSWORD_TOKEN + token)
				|| !token.equals(jedisClientPool.get(RedisConst.MODIFIED_PASSWORD_TOKEN + token))) {
			return JsonData.setErrorMessage("参数错误");
		}

		String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes());

		AdminExample example = new AdminExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List<Admin> admins = adminMapper.selectByExample(example);
		if (admins != null && admins.size() > 0) {
			Admin admin = admins.get(0);
			admin.setPassword(md5Pwd);
			try {
				adminMapper.updateByExampleSelective(admin, example);
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("密码重置失败");
			}
			// 重置成功,删除缓存
			if (jedisClientPool.exists(AdminConst.ADMIN_LOGIN_LIMIT + mobile)) {
				jedisClientPool.del(AdminConst.ADMIN_LOGIN_LIMIT + mobile);
			}
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 用户退出
	 */
	public JsonData logout(Integer userId) {
		try {
			jedisClientPool.del(AdminConst.AMDIN_LOGIN_SUCCESS + userId + "");
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			return JsonData.setErrorMessage("服务器异常");
		}
	}

	/**
	 * 修改密码
	 */
	public JsonData modifiedPassword(Integer userId, String oldPassword, String newPassword) {
		if (userId == null || StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
			return JsonData.setErrorMessage("参数错误");
		}
		// 校验原密码
		Admin admin = adminMapper.selectByPrimaryKey(userId);
		if (admin != null) {
			if (DigestUtils.md5DigestAsHex(oldPassword.getBytes()).equals(admin.getPassword())) {
				admin.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
				try {
					adminMapper.updateByPrimaryKeySelective(admin);
					return JsonData.setSuccessMessage();
				} catch (Exception e) {
					return JsonData.setErrorMessage("修改失败");
				}
			}
		}
		return JsonData.setErrorMessage("旧密码输入错误");
	}

	/**
	 * 管理员基本信息
	 */
	public JsonData getAccountInfo(Integer userId) {
		Admin admin = adminMapper.selectByPrimaryKey(userId);
		if (admin == null) {
			return JsonData.setErrorMessage("管理员不存在");
		}
		AdminInfoExample example = new AdminInfoExample();
		example.createCriteria().andAdminIdEqualTo(userId);
		List<AdminInfo> list = adminInfoMapper.selectByExample(example);
		AdminAccountInfo accountInfo = new AdminAccountInfo();
		accountInfo.setName(admin.getName());
		accountInfo.setIdCard(admin.getIdCard());
		accountInfo.setMobile(admin.getMobile());
		accountInfo.setSex(admin.getSex());
		AdminRoleExample adminRoleExample = new AdminRoleExample();
		adminRoleExample.createCriteria().andAdminIdEqualTo(userId);
		List<AdminRole> adminRoles = adminRoleMapper.selectByExample(adminRoleExample);
		if (adminRoles != null && adminRoles.size() > 0) {
			Role role = roleMapper.selectByPrimaryKey(adminRoles.get(0).getRoleId());
			accountInfo.setRole(role.getRoleName());
		}
		if (list != null && list.size() > 0 && admin != null) {
			AdminInfo adminInfo = list.get(0);
			accountInfo.setAddress(adminInfo.getCompanyAddress());
			Map<String, String> map = new HashMap<String, String>();
			map.put("accountName", adminInfo.getBankUser());// 打款户名
			map.put("account", adminInfo.getBankAccount());// 收款账号
			map.put("bankName", adminInfo.getBankName());// 收款银行

			map.put("province", adminInfo.getProvince());
			map.put("city", adminInfo.getCity());
			map.put("area", adminInfo.getArea());
			map.put("address", adminInfo.getAddress());
			map.put("addressMobile", adminInfo.getMobile());
			map.put("addressName", adminInfo.getName());
			accountInfo.setBank(map);
		}
		return JsonData.setSuccessMessage(accountInfo);
	}

	@Override
	public JsonData getHomePage(String userId, String result) {
		Admin admin = adminMapper.selectByPrimaryKey(Integer.parseInt(userId));
		// 管理员信息对象
		AdminInfoExample example = new AdminInfoExample();
		example.createCriteria().andAdminIdEqualTo(Integer.parseInt(userId));
		List<AdminInfo> list = adminInfoMapper.selectByExample(example);
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("name", admin.getName());
		map.put("mobile", admin.getMobile());
		if (list != null && list.size() > 0) {
			AdminInfo adminInfo = list.get(0);
			map.put("province", adminInfo.getProvince());
			map.put("city", adminInfo.getCity());
			map.put("area", adminInfo.getArea());
			map.put("address", adminInfo.getAddress());
			map.put("addressMobile", adminInfo.getMobile());
			map.put("addressName", adminInfo.getName());
		}
		return JsonData.setSuccessMessage(map);
	}
}
