package com.maomao.ssm.service.impl.admin;

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
import org.springframework.util.DigestUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.AdminDataRole;
import com.maomao.ssm.bean.AdminDataRoleExample;
import com.maomao.ssm.bean.AdminExample;
import com.maomao.ssm.bean.AdminInfo;
import com.maomao.ssm.bean.AdminInfoExample;
import com.maomao.ssm.bean.AdminRole;
import com.maomao.ssm.bean.AdminRoleExample;
import com.maomao.ssm.bean.GoodsWarehouse;
import com.maomao.ssm.bean.GoodsWarehouseExample;
import com.maomao.ssm.bean.Role;
import com.maomao.ssm.bean.RolePermissionExample;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserBank;
import com.maomao.ssm.bean.UserBankExample;
import com.maomao.ssm.bean.UserExample;
import com.maomao.ssm.constant.AdminConst;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.AdminDataRoleMapper;
import com.maomao.ssm.dao.AdminInfoMapper;
import com.maomao.ssm.dao.AdminMapper;
import com.maomao.ssm.dao.AdminRoleMapper;
import com.maomao.ssm.dao.DataRoleMapper;
import com.maomao.ssm.dao.GoodsWarehouseMapper;
import com.maomao.ssm.dao.PermissionMapper;
import com.maomao.ssm.dao.RoleMapper;
import com.maomao.ssm.dao.RolePermissionMapper;
import com.maomao.ssm.dao.UserAndAdminNumberMapperCustom;
import com.maomao.ssm.dao.UserApplyMapper;
import com.maomao.ssm.dao.UserBankMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.AdminInternalUser;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.pojo.UserAndAdminNumber;
import com.maomao.ssm.pojo.UserCompany;
import com.maomao.ssm.pojo.UserCompanyList;
import com.maomao.ssm.pojo.UserIndividual;
import com.maomao.ssm.pojo.UserOrdinary;
import com.maomao.ssm.service.admin.AccountService;
import com.maomao.ssm.utils.JsonUtils;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserBankMapper userBankMapper;

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private AdminInfoMapper adminInfoMapper;

	// 自定义mapper
	@Autowired
	private UserAndAdminNumberMapperCustom adminNumberMapper;

	@Autowired
	private JedisClientPool jedisClientPool;
	@Autowired
	private UserApplyMapper userApplyMapper;
	@Autowired
	private UserAndAdminNumberMapperCustom userAndAdminNumberMapper;

	@Autowired
	private AdminRoleMapper adminRoleMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private AdminDataRoleMapper adminDataRoleMapper;

	@Autowired
	private DataRoleMapper dataRoleMapper;

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private GoodsWarehouseMapper goodsWarehouseMapper;

	/**
	 * 删除用户
	 */
	@Override
	public JsonData delUser(Integer[] userIds, Byte type) {
		if (type != null && type == StatusConst.USER_TYPE_PERSONAL) {// 个人
			UserExample example = new UserExample();
			example.createCriteria().andIdIn(Arrays.asList(userIds));
			User user = new User();
			user.setStatus(StatusConst.USER_STATSU_DEL); // -1删除用户
			try {
				userMapper.updateByExampleSelective(user, example);
				return JsonData.setSuccessMessage("删除成功");
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("删除失败");
			}
		} else if (type != null && type == StatusConst.USER_TYPE_SHOP) {// 商家用户
			UserExample example = new UserExample();
			example.createCriteria().andIdIn(Arrays.asList(userIds));
			User user = new User();
			user.setStatus(StatusConst.USER_STATSU_DEL); // -1删除用户
			try {
				userMapper.updateByExampleSelective(user, example);
				return JsonData.setSuccessMessage("删除成功");
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("删除失败");
			}
		}
		return JsonData.setErrorMessage("删除失败");
	}

	// 用户密码重置
	@Override
	public JsonData ResetUserPassword(Integer[] userIds, Byte type) {
		if (type != null && type == StatusConst.USER_TYPE_PERSONAL) {// 个人
			UserExample example = new UserExample();
			example.createCriteria().andIdIn(Arrays.asList(userIds));
			User user = new User();
			String password = "123456";
			user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			try {
				userMapper.updateByExampleSelective(user, example);
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("密码重置失败");
			}
			return JsonData.setSuccessMessage();
		} else if (type != null && type == StatusConst.USER_TYPE_SHOP) {// 商家用户
			UserExample example = new UserExample();
			example.createCriteria().andIdIn(Arrays.asList(userIds));
			User user = new User();
			String password = "123456";
			user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			try {
				userMapper.updateByExampleSelective(user, example);
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("密码重置失败");
			}
			return JsonData.setSuccessMessage();
		}
		return JsonData.setErrorMessage("密码重置失败");
	}

	// 用户分页账号查询
	@Override
	public JsonData getIndividualUserList(Integer pages, Integer rows, String queryString, Byte type) {
		// 分页
		Page page = PageHelper.startPage(pages, rows);

		if (type == null)
			return JsonData.setErrorMessage("参数非法");

		if (type == StatusConst.USER_TYPE_PERSONAL) {
			UserExample example = new UserExample();
			example.setOrderByClause("mobile asc");
			// 如果queryString不为空
			if (StringUtils.isNotBlank(queryString)) {
				example.or().andMobileLike("%" + queryString + "%").andTypeEqualTo(type)
						.andStatusEqualTo(StatusConst.USER_STATSU_NOT_DEL);
				example.or().andNameLike("%" + queryString + "%").andTypeEqualTo(type)
						.andStatusEqualTo(StatusConst.USER_STATSU_NOT_DEL);
			}

			if (StringUtils.isBlank(queryString))
				example.createCriteria().andTypeEqualTo(type).andStatusEqualTo(StatusConst.USER_STATSU_NOT_DEL);

			// 查询所有用户列表
			List<User> userList = userMapper.selectByExample(example);

			PageInfo<User> pageInfo = new PageInfo<>(userList);

			PageBean pagebean = new PageBean();
			// 获取总条数
			pagebean.setTotal(pageInfo.getTotal());
			// 获取当前页数据
			pagebean.setRows(userList);
			return JsonData.setSuccessMessage(pagebean);
		} else if (type == StatusConst.USER_TYPE_SHOP) {// 商家用户
			UserExample example = new UserExample();
			example.setOrderByClause("mobile asc");
			// 如果queryString不为空
			if (StringUtils.isNotBlank(queryString)) {
				example.or().andMobileLike("%" + queryString + "%").andTypeEqualTo(type)
						.andStatusEqualTo(StatusConst.USER_STATSU_NOT_DEL)
						.andCheckStatusEqualTo(UserConts.USER_CHECK_STATUS_TRUE);
				example.or().andNameLike("%" + queryString + "%").andTypeEqualTo(type)
						.andStatusEqualTo(StatusConst.USER_STATSU_NOT_DEL)
						.andCheckStatusEqualTo(UserConts.USER_CHECK_STATUS_TRUE);
			}
			if (StringUtils.isBlank(queryString))
				example.createCriteria().andTypeEqualTo(type).andStatusEqualTo(StatusConst.USER_STATSU_NOT_DEL)
						.andCheckStatusEqualTo(UserConts.USER_CHECK_STATUS_TRUE);

			// 查询用户列表
			List<User> userList = userMapper.selectByExample(example);
			List<Map<String,Object>> list  = new ArrayList<>();
			if (userList != null && userList.size() > 0) {
				for (User user : userList) {
					Map<String,Object> map = new HashMap<>();
					map.put("id", user.getId());
					map.put("mobile", user.getMobile());
					map.put("name",user.getName());
					map.put("idCard",user.getIdCard());
					map.put("type", user.getType());
					if (user.getInvitation() != null) {
						map.put("invitationLen",user.getInvitation().split(";").length);
					}else{
						map.put("invitationLen",0);
					}
					list.add(map);
				}
			}
			PageBean pagebean = new PageBean();
			// 获取总条数
			pagebean.setTotal(page.getTotal());
			// 获取当前页数据
			pagebean.setRows(list);
			return JsonData.setSuccessMessage(pagebean);
		}
		return JsonData.setErrorMessage("查询失败");
	}

	// 用户添加
	@Override
	public JsonData addIndividualUser(String bankName, String mobile, String name, String idCard, String account,
			String userName, String invitation,String accountsBank, Byte type) {
		String password = "123456";
		try {
			if (type != null && type == StatusConst.USER_TYPE_SHOP) {// 商家用户
				if (StringUtils.isBlank(bankName) || StringUtils.isBlank(mobile) || StringUtils.isBlank(name)
						|| StringUtils.isBlank(idCard) || StringUtils.isBlank(account) || StringUtils.isBlank(userName))
					return JsonData.setErrorMessage("参数非法");

				UserExample userExample = new UserExample();
				userExample.createCriteria().andMobileEqualTo(mobile);
				List<User> userList = userMapper.selectByExample(userExample);
				if (userList != null && userList.size() > 0) {
					return JsonData.setErrorMessage("手机号已经被注册");
				}

				// 插入用户
				User user = new User();
				user.setMobile(mobile);
				user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));// 密码加密
				user.setName(name);
				user.setIdCard(idCard);
				user.setType(type); // 商家用户
				user.setStatus(StatusConst.USER_STATSU_NOT_DEL); // 0:未删除
				user.setCheckStatus(UserConts.USER_CHECK_STATUS_TRUE);
				user.setMoney(0L);
				user.setImg((byte) 0);
				user.setCreditTotal(0L);
				user.setCreditSurplus(0L);
				user.setCreditReal(UserConts.USER_CREDIT_REAL_FALSE);
				user.setAccumulativeMoney(0L);
				user.setInvitation(null);
				user.setLoan(0L);
				user.setCreateTime(new Date());
				/*
				 * TODO:现阶段暂时不需要,只能查看 // 判断邀请人是否唯一 List<User> userList =
				 * userMapper.selectByExample(null); // 查询出所有用户 boolean flag = true; for (User u
				 * : userList) { if (invitation.equals(u.getInvitation())) { flag = false;
				 * break; } } if (flag) user.setInvitation(invitation); // 邀请用户唯一进行添加 else
				 * user.setInvitation(null); // 不唯一设置成null return
				 * JsonData.setErrorMessage("此用户已经被邀请");
				 */
				userMapper.insertSelective(user);
				Integer userId = user.getId();

				// 用户银行卡对象
				UserBank userBank = new UserBank();
				userBank.setUserId(userId);
				userBank.setAccount(account);
				userBank.setBankName(bankName);
				userBank.setUserName(userName);
				userBank.setCreateTime(new Date());
				userBank.setAccountsBank(accountsBank);
				userBankMapper.insertSelective(userBank);
			} else if (type != null && type == StatusConst.USER_TYPE_PERSONAL) {// 个人用户
				if (StringUtils.isBlank(mobile))
					return JsonData.setErrorMessage("参数非法");

				UserExample userExample = new UserExample();
				userExample.createCriteria().andMobileEqualTo(mobile);
				List<User> userList = userMapper.selectByExample(userExample);
				if (userList != null && userList.size() > 0) {
					return JsonData.setErrorMessage("手机号已经被注册");
				}

				// 密码加密
				password = DigestUtils.md5DigestAsHex(password.getBytes());
				User user = new User();
				user.setMobile(mobile);
				user.setPassword(password);
				user.setName(name);
				user.setIdCard(idCard);
				user.setType(type);// 个人用户
				user.setStatus(StatusConst.USER_STATSU_NOT_DEL); // 0:未删除
				user.setCheckStatus(UserConts.USER_CHECK_STATUS_UNOPEN);
				user.setMoney(0L);
				user.setImg(UserConts.USER_IMG_DEFAULT);
				user.setCreditTotal(0L);
				user.setCreditSurplus(0L);
				user.setCreditReal(UserConts.USER_CREDIT_REAL_FALSE);
				user.setAccumulativeMoney(0L);
				user.setLoan(0L);
				user.setInvitation(null);
				user.setCreateTime(new Date());
				// 插入用户
				userMapper.insertSelective(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("添加失败");
		}
		return JsonData.setSuccessMessage("添加成功");
	}

	// 编辑用户信息TODO:删除是从数据库直接删掉所以没有进行判断删除字段
	@Override
	public JsonData editIndividualUser(Integer userId, Byte type) {
		if (userId == null || type == null) {
			return JsonData.setErrorMessage("参数非法");
		}

		if (type == StatusConst.USER_TYPE_SHOP) {// 商家用户
			User user = userMapper.selectByPrimaryKey(userId);
			if (user.getCheckStatus() == UserConts.USER_CHECK_STATUS_TRUE) {
				UserBankExample userBankExample = new UserBankExample();
				userBankExample.createCriteria().andUserIdEqualTo(userId);
				List<UserBank> userBankList = userBankMapper.selectByExample(userBankExample);
				UserIndividual userIndividual = null;
				if (userBankList != null && userBankList.size() > 0) {
					UserBank userBank = userBankList.get(0);
					if (userBank != null && user != null) {
						userIndividual = new UserIndividual(user, userBank);
					}
					return JsonData.setSuccessMessage(userIndividual);
				}
			}
			return JsonData.setErrorMessage("开店用户不存在!");
		} else if (type == StatusConst.USER_TYPE_PERSONAL) {// 个人用户
			User user = userMapper.selectByPrimaryKey(userId);
			UserOrdinary userOrdinary = null;
			if (user != null) {
				userOrdinary = new UserOrdinary(user);
			}
			return JsonData.setSuccessMessage(userOrdinary);
		}
		return JsonData.setErrorMessage("编辑失败");
	}

	// 修改用户信息
	@Override
	public JsonData updateIndividualUser(Integer userId, String bankName, String mobile, String name, String idCard,
			String account, String userName, String invitation,String accountsBank, Byte type) {
		try {
			if (type != null && type == StatusConst.USER_TYPE_SHOP) {// 商家用户
				if (userId == null || StringUtils.isBlank(bankName) || StringUtils.isBlank(mobile)
						|| StringUtils.isBlank(name) || StringUtils.isBlank(idCard) || StringUtils.isBlank(account)
						|| StringUtils.isBlank(userName))
					return JsonData.setErrorMessage("参数非法");
				
				UserExample userExample = new UserExample();
				userExample.createCriteria().andMobileEqualTo(mobile).andIdNotEqualTo(userId);
				List<User> userList = userMapper.selectByExample(userExample);
				if (userList != null && userList.size() > 0) {
					return JsonData.setErrorMessage("手机号已经被注册");
				}
				
				// 插入用户
				User user = userMapper.selectByPrimaryKey(userId);
				user.setId(userId);
				user.setMobile(mobile);
				// user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));//
				// 密码加密
				user.setCheckStatus(UserConts.USER_CHECK_STATUS_TRUE);
				user.setName(name);
				user.setIdCard(idCard);
				user.setType(type); // 类型:b类(商家用户)
				user.setStatus(StatusConst.USER_STATSU_NOT_DEL); // 0:未删除
				user.setModifiedTime(new Date());
				// 用户银行卡信息
				UserBankExample bankExample = new UserBankExample();
				bankExample.createCriteria().andUserIdEqualTo(userId);
				List<UserBank> userBankList = userBankMapper.selectByExample(bankExample);
				if (userBankList != null && userBankList.size() > 0) {
					for (UserBank userBank : userBankList) {
						userBank.setUserId(userId);
						userBank.setAccount(account);
						userBank.setBankName(bankName);
						userBank.setUserName(userName);
						userBank.setAccountsBank(accountsBank);
						userBank.setCreateTime(new Date());
						userBankMapper.updateByPrimaryKeySelective(userBank);
					}
				}

				user.setInvitation(invitation);
				userMapper.updateByPrimaryKeySelective(user);
				return JsonData.setSuccessMessage("更新成功");
			} else if (type != null && type == StatusConst.USER_TYPE_PERSONAL) {// 个人用户
				if (userId == null || StringUtils.isBlank(mobile))
					return JsonData.setErrorMessage("参数非法");
				
				UserExample userExample = new UserExample();
				userExample.createCriteria().andMobileEqualTo(mobile).andIdNotEqualTo(userId);
				List<User> userList = userMapper.selectByExample(userExample);
				if (userList != null && userList.size() > 0) {
					return JsonData.setErrorMessage("手机号已经被注册");
				}
				
				User user = new User();
				user.setId(userId);
				user.setMobile(mobile);
				// user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));//
				// 密码加密
				user.setName(name);
				user.setIdCard(idCard);
				user.setType(type); // 类型:c类(个人用户)
				user.setStatus(StatusConst.USER_STATSU_NOT_DEL); // 0:未删除
				user.setModifiedTime(new Date());
				userMapper.updateByPrimaryKeySelective(user);
				return JsonData.setSuccessMessage("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("更新失败");
		}
		return JsonData.setSuccessMessage("更新失败");
	}

	// 管理员分页查询
	@Override
	public JsonData getSupplierUserList(Integer pages, Integer rows, String queryString, Byte type) {
		if (type == null)
			return JsonData.setErrorMessage("参数非法");
		// 分页
		PageHelper.startPage(pages, rows);
		if (type == StatusConst.ADMIN_TYPE_SUPPLIER) {// 供应商
			AdminExample example = new AdminExample();
			example.setOrderByClause("mobile asc");
			// 如果queryString不为空
			if (StringUtils.isNotBlank(queryString)) {
				example.or().andMobileLike("%" + queryString + "%").andTypeEqualTo(type)
						.andStatusEqualTo(StatusConst.ADMIN_STATSU_NOT_DEL);
				example.or().andNameLike("%" + queryString + "%").andTypeEqualTo(type)
						.andStatusEqualTo(StatusConst.ADMIN_STATSU_NOT_DEL);
			}

			if (StringUtils.isBlank(queryString))
				example.createCriteria().andTypeEqualTo(type).andStatusEqualTo(StatusConst.ADMIN_STATSU_NOT_DEL);
			// 查询所有用户列表
			List<Admin> adminList = adminMapper.selectByExample(example);

			List<UserCompanyList> userCompanyList = new ArrayList<>();
			if (adminList != null && adminList.size() > 0) {
				for (Admin admin : adminList) {
					UserCompanyList userCompany = new UserCompanyList();
					// 创建用户角色表
					AdminRoleExample adminRoleExample = new AdminRoleExample();
					adminRoleExample.createCriteria().andAdminIdEqualTo(admin.getId());
					List<AdminRole> adminRoleList = adminRoleMapper.selectByExample(adminRoleExample);
					List<String> roleList = new ArrayList<>();
					if (adminRoleList != null && adminRoleList.size() > 0) {
						for (AdminRole adminRole : adminRoleList) {
							Integer roleId = adminRole.getRoleId();
							Role role = roleMapper.selectByPrimaryKey(roleId);
							roleList.add(role.getRoleName());
						}
					}
					userCompany.setAdminId(admin.getId());
					userCompany.setMobile(admin.getMobile());
					userCompany.setName(admin.getName());
					userCompany.setPassword(admin.getPassword());
					userCompany.setIdCard(admin.getIdCard());
					userCompany.setRoleName(roleList);
					userCompanyList.add(userCompany);
				}
			}

			PageInfo<UserCompanyList> pageInfo = new PageInfo<>(userCompanyList);

			PageBean pagebean = new PageBean();
			// 获取总条数
			Integer total = userAndAdminNumberMapper.getSupplierUserList(queryString);
			// 获取总条数
			pagebean.setTotal(total);
			// 获取当前页数据
			pagebean.setRows(userCompanyList);
			return JsonData.setSuccessMessage(pagebean);
		} else if (type == StatusConst.ADMIN__TYPE_INTERNAL) {// 内部
			AdminExample example = new AdminExample();
			example.setOrderByClause("mobile asc");
			// 如果queryString不为空
			if (StringUtils.isNotBlank(queryString)) {
				example.or().andMobileLike("%" + queryString + "%").andTypeEqualTo(type)
						.andStatusEqualTo(StatusConst.ADMIN_STATSU_NOT_DEL);
				example.or().andNameLike("%" + queryString + "%").andTypeEqualTo(type)
						.andStatusEqualTo(StatusConst.ADMIN_STATSU_NOT_DEL);
			}

			if (StringUtils.isBlank(queryString))
				example.createCriteria().andTypeEqualTo(type).andStatusEqualTo(StatusConst.ADMIN_STATSU_NOT_DEL);
			// 查询所有用户列表
			List<Admin> adminList = adminMapper.selectByExample(example);
			List<UserCompanyList> userCompanyList = new ArrayList<>();
			if (adminList != null && adminList.size() > 0) {
				for (Admin admin : adminList) {
					UserCompanyList userCompany = new UserCompanyList();
					AdminRoleExample adminRoleExample = new AdminRoleExample();
					adminRoleExample.createCriteria().andAdminIdEqualTo(admin.getId());
					List<AdminRole> adminRoleList = adminRoleMapper.selectByExample(adminRoleExample);
					List<String> roleList = new ArrayList<>();
					if (adminRoleList != null && adminRoleList.size() > 0) {
						for (AdminRole adminRole : adminRoleList) {
							Integer roleId = adminRole.getRoleId();
							Role role = roleMapper.selectByPrimaryKey(roleId);
							roleList.add(role.getRoleName());
						}
					}
					userCompany.setAdminId(admin.getId());
					userCompany.setMobile(admin.getMobile());
					userCompany.setName(admin.getName());
					userCompany.setPassword(admin.getPassword());
					userCompany.setIdCard(admin.getIdCard());
					userCompany.setRoleName(roleList);
					userCompanyList.add(userCompany);
				}
			}

			PageInfo<UserCompanyList> pageInfo = new PageInfo<>(userCompanyList);
			PageBean pagebean = new PageBean();
			// 获取总条数
			Integer total = userAndAdminNumberMapper.getInternalList(queryString);
			pagebean.setTotal(total);
			// 获取当前页数据
			pagebean.setRows(userCompanyList);
			return JsonData.setSuccessMessage(pagebean);
		}
		return JsonData.setErrorMessage("查询失败");
	}

	// 管理员添加
	@Override
	public JsonData addSupplierUser(String mobile,String password,String name,String idCard,Byte sex, String bankAccount,String bankName,String bankUser,String companyAddress,String legalPersion,String legalPersonMobile,String repaymentBankAccount,String repaymentBankUser,String repaymentBankName,String companyName ,String companyCode,String enclosure,Integer roleId,Integer dataRoleId,String accountsBank,String repaymentAccountsBank,String province, String city,String area,String address, String addressMobile, String addressName, Byte type) {
		try {
			if (type != null && type == StatusConst.ADMIN_TYPE_SUPPLIER) {// 供应商
				if (StringUtils.isBlank(mobile) || StringUtils.isBlank(password) || StringUtils.isBlank(name)
						|| StringUtils.isBlank(idCard) || StringUtils.isBlank(bankAccount)
						|| StringUtils.isBlank(bankName) || StringUtils.isBlank(bankUser)
						|| StringUtils.isBlank(companyAddress) || StringUtils.isBlank(legalPersion)
						|| StringUtils.isBlank(legalPersonMobile) || StringUtils.isBlank(companyName)
						|| StringUtils.isBlank(companyCode) || StringUtils.isBlank(enclosure) || roleId == null || dataRoleId == null)
					return JsonData.setErrorMessage("参数非法");

				AdminExample adminExample = new AdminExample();
				adminExample.createCriteria().andMobileEqualTo(mobile);
				List<Admin> adminList = adminMapper.selectByExample(adminExample);
				if (adminList != null && adminList.size() > 0) {
					return JsonData.setErrorMessage("手机号已经被注册");
				}

				// TODO:Type类型 0内部 1供应商
				// 创建管理员对象
				Admin admin = new Admin();
				admin.setMobile(mobile);
				admin.setType(type);// 类型:供应商
				admin.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));// 密码加密
				admin.setName(name);
				admin.setSex((byte) sex); // 性别 1男 2女
				admin.setStatus(StatusConst.ADMIN_STATSU_NOT_DEL);// 未删除
				admin.setIdCard(idCard);
				admin.setMoney(0L);
				admin.setCreateTime(new Date());
				adminMapper.insertSelective(admin);

				Integer adminId = admin.getId();
				
				//管理员信息对象
				AdminInfo adminInfo = new AdminInfo();
				adminInfo.setAdminId(adminId);
				adminInfo.setBankAccount(bankAccount);
				adminInfo.setBankName(bankName);
				adminInfo.setBankUser(bankUser);
				adminInfo.setCompanyAddress(companyAddress);
				adminInfo.setLeaglPerson(legalPersion);
				adminInfo.setLegalPersonMobile(legalPersonMobile);
				adminInfo.setRepaymentBankAccount(repaymentBankAccount);
				adminInfo.setRepaymentBankUser(repaymentBankUser);
				adminInfo.setRepaymentBankName(repaymentBankName);
				adminInfo.setCompanyName(companyName);
				adminInfo.setCompanyCode(companyCode);
				adminInfo.setEnclosure(enclosure);
				adminInfo.setMortage(0L);
				adminInfo.setLoan(0L);
				adminInfo.setCreateTime(new Date());
				adminInfo.setAccountsBank(accountsBank);
				adminInfo.setRepaymentAccountsBank(repaymentAccountsBank);
				adminInfo.setProvince(province);
				adminInfo.setCity(city);
				adminInfo.setArea(area);
				adminInfo.setAddress(address);
				adminInfo.setName(addressName);
				adminInfo.setMobile(addressMobile);
				adminInfoMapper.insertSelective(adminInfo);

				// 创建用户角色对象
				AdminRole adminRole = new AdminRole();
				adminRole.setRoleId(roleId);
				adminRole.setAdminId(adminId);
				adminRole.setCreateTime(new Date());
				adminRoleMapper.insertSelective(adminRole);

				// 创建管理员数据角色
				AdminDataRole adminDataRole = new AdminDataRole();
				adminDataRole.setAdminId(adminId);
				adminDataRole.setDataRoleId(dataRoleId);
				adminDataRole.setCreateTime(new Date());
				adminDataRoleMapper.insertSelective(adminDataRole);
				
				return JsonData.setSuccessMessage();
			} else if (type != null && type == StatusConst.ADMIN__TYPE_INTERNAL) {// 内部
				if (StringUtils.isBlank(mobile) || roleId == null || dataRoleId == null)
					return JsonData.setErrorMessage("参数非法");

				AdminExample adminExample = new AdminExample();
				adminExample.createCriteria().andMobileEqualTo(mobile);
				List<Admin> adminList = adminMapper.selectByExample(adminExample);
				if (adminList != null && adminList.size() > 0) {
					return JsonData.setErrorMessage("手机号已经被注册");
				}
				Admin admin = new Admin();
				admin.setMobile(mobile);
				admin.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
				admin.setName(name);
				admin.setSex(sex); // 性别 1男 2女
				admin.setType(type); // 类型内部
				admin.setStatus(StatusConst.ADMIN_STATSU_NOT_DEL); // 0:未删除
				admin.setIdCard(idCard);
				admin.setMoney(0L);
				admin.setCreateTime(new Date());
				adminMapper.insertSelective(admin);

				Integer adminId = admin.getId();
				// 创建管理员角色对象
				AdminRole adminRole = new AdminRole();
				adminRole.setRoleId(roleId);
				adminRole.setAdminId(adminId);
				adminRole.setCreateTime(new Date());
				adminRoleMapper.insert(adminRole);

				// 创建管理员数据角色
				AdminDataRole adminDataRole = new AdminDataRole();
				adminDataRole.setAdminId(adminId);
				adminDataRole.setDataRoleId(dataRoleId);
				adminDataRole.setCreateTime(new Date());
				adminDataRoleMapper.insertSelective(adminDataRole);
				return JsonData.setSuccessMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("添加失败");
		}
		return JsonData.setErrorMessage("添加失败");
	}

	// 编辑管理员信息
	@Override
	public JsonData editSupplierUser(Integer adminId, Byte type) {
		if (adminId == null || type == null)
			return JsonData.setErrorMessage("参数非法");

		if (type == StatusConst.ADMIN_TYPE_SUPPLIER) {// 供应商
			// 管理员
			Admin admin = adminMapper.selectByPrimaryKey(adminId);
			AdminInfoExample example = new AdminInfoExample();
			example.createCriteria().andAdminIdEqualTo(adminId);
			List<AdminInfo> adminInfoList = adminInfoMapper.selectByExample(example);

			// 创建用户角色对象
			AdminRoleExample adminRoleExample = new AdminRoleExample();
			adminRoleExample.createCriteria().andAdminIdEqualTo(admin.getId());
			List<AdminRole> adminRoleList = adminRoleMapper.selectByExample(adminRoleExample);
			
			// 获取角色id
			List<Integer> roleList = new ArrayList<>();
			if (adminRoleList != null && adminRoleList.size() > 0) {
				for (AdminRole adminRole : adminRoleList) {
					roleList.add(adminRole.getRoleId());
				}
			}
			
			// 创建数据角色对象
			AdminDataRoleExample adminDataRoleExample = new AdminDataRoleExample();
			adminDataRoleExample.createCriteria().andAdminIdEqualTo(adminId);
			List<AdminDataRole> adminDataRoleList = adminDataRoleMapper.selectByExample(adminDataRoleExample);
			
			// 获取数据角色id
			List<Integer> dataRoleList = new ArrayList<>();
			if (adminDataRoleList != null && adminDataRoleList.size() > 0) {
				for (AdminDataRole adminDataRole : adminDataRoleList) {
					dataRoleList.add(adminDataRole.getDataRoleId());
				}
			}
			UserCompany userCompany = null;
			if (adminInfoList != null && adminInfoList.size() > 0) {
				AdminInfo adminInfo = adminInfoList.get(0);
				if (adminInfo != null && admin != null) {
					// 页面所需数据的pojo
					userCompany = new UserCompany(admin, adminInfo);
				}
				if (roleList != null && roleList.size() > 0) {
					userCompany.setRoleList(roleList);
				}

				if (dataRoleList != null && dataRoleList.size() > 0) {
					userCompany.setDataRoleList(dataRoleList);
				}
			}
			return JsonData.setSuccessMessage(userCompany);
		} else if (type == StatusConst.ADMIN__TYPE_INTERNAL) {// 内部
			Admin admin = adminMapper.selectByPrimaryKey(adminId);

			AdminInternalUser adminInternalUser = new AdminInternalUser(adminId, admin.getMobile(), admin.getPassword(),
					admin.getName(), admin.getIdCard(), admin.getSex());

			// 创建用户角色对象
			AdminRoleExample adminRoleExample = new AdminRoleExample();
			adminRoleExample.createCriteria().andAdminIdEqualTo(admin.getId());
			List<AdminRole> adminRoleList = adminRoleMapper.selectByExample(adminRoleExample);
			// 获取角色id
			List<Integer> roleList = new ArrayList<>();
			if (adminRoleList != null && adminRoleList.size() > 0) {
				for (AdminRole adminRole : adminRoleList) {
					roleList.add(adminRole.getRoleId());
				}
			}
			// 创建数据角色对象
			AdminDataRoleExample adminDataRoleExample = new AdminDataRoleExample();
			adminDataRoleExample.createCriteria().andAdminIdEqualTo(adminId);
			List<AdminDataRole> adminDataRoleList = adminDataRoleMapper.selectByExample(adminDataRoleExample);
			// 获取数据角色id
			List<Integer> dataRoleList = new ArrayList<>();
			if (adminDataRoleList != null && adminDataRoleList.size() > 0) {
				for (AdminDataRole adminDataRole : adminDataRoleList) {
					dataRoleList.add(adminDataRole.getDataRoleId());
				}
			}
			if (roleList != null && roleList.size() > 0) {
				adminInternalUser.setRoleList(roleList);
			}

			if (dataRoleList != null && dataRoleList.size() > 0) {
				adminInternalUser.setDataRoleList(dataRoleList);
			}
			return JsonData.setSuccessMessage(adminInternalUser);
		}
		return JsonData.setErrorMessage("编辑失败");
	}

	// 修改管理员信息
	@Override
	public JsonData updateSupplierUser(Integer adminId, String mobile, String name, String idCard, Byte sex,
			String bankAccount, String bankName, String bankUser, String companyAddress, String legalPersion,
			String legalPersonMobile, String repaymentBankAccount, String repaymentBankUser, String repaymentBankName,
			String companyName, String companyCode, String enclosure, Integer roleId, Integer dataRoleId,String accountsBank,String repaymentAccountsBank,String province, String city,String area,String address, String addressMobile, String addressName, Byte type) {
		try {
			if (type != null && type == StatusConst.ADMIN_TYPE_SUPPLIER) {// 供应商
				if (adminId == null || StringUtils.isBlank(mobile) || StringUtils.isBlank(name)
						|| StringUtils.isBlank(idCard) || StringUtils.isBlank(bankAccount)
						|| StringUtils.isBlank(bankName) || StringUtils.isBlank(bankUser)
						|| StringUtils.isBlank(companyAddress) || StringUtils.isBlank(legalPersion)
						|| StringUtils.isBlank(legalPersonMobile) || StringUtils.isBlank(companyName)
						|| StringUtils.isBlank(companyCode) || roleId == null || dataRoleId == null)
					return JsonData.setErrorMessage("参数非法");
				
				AdminExample adminExample = new AdminExample();
				adminExample.createCriteria().andMobileEqualTo(mobile).andIdNotEqualTo(adminId);
				List<Admin> adminList = adminMapper.selectByExample(adminExample);
				if (adminList != null && adminList.size() > 0) {
					return JsonData.setErrorMessage("手机号已经被注册");
				}
				
				// 创建管理员对象
				Admin admin = new Admin();
				admin.setId(adminId);
				admin.setMobile(mobile);
				admin.setType(type);// 供应商
				// admin.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));//
				// 密码加密
				admin.setName(name);
				admin.setSex((byte) sex); // 性别 1男 2女
				admin.setStatus(StatusConst.ADMIN_STATSU_NOT_DEL);// 0:未删除 -1:删除
				admin.setIdCard(idCard);
				admin.setModifiedTime(new Date());
				adminMapper.updateByPrimaryKeySelective(admin);

				AdminInfoExample example = new AdminInfoExample();
				example.createCriteria().andAdminIdEqualTo(adminId);
				List<AdminInfo> adminInfoList = adminInfoMapper.selectByExample(example);
				if (adminInfoList != null && adminInfoList.size() > 0) {
					for (AdminInfo adminInfo : adminInfoList) {
						adminInfo.setAdminId(adminId);
						adminInfo.setBankAccount(bankAccount);
						adminInfo.setBankName(bankName);
						adminInfo.setBankUser(bankUser);
						adminInfo.setCompanyAddress(companyAddress);
						adminInfo.setLeaglPerson(legalPersion);
						adminInfo.setLegalPersonMobile(legalPersonMobile);
						adminInfo.setRepaymentBankAccount(repaymentBankAccount);
						adminInfo.setRepaymentBankUser(repaymentBankUser);
						adminInfo.setRepaymentBankName(repaymentBankName);
						adminInfo.setCompanyName(companyName);
						adminInfo.setEnclosure(enclosure);
						adminInfo.setCompanyCode(companyName);
						adminInfo.setAccountsBank(accountsBank);
						adminInfo.setRepaymentAccountsBank(repaymentAccountsBank);
						adminInfo.setProvince(province);
						adminInfo.setCity(city);
						adminInfo.setArea(area);
						adminInfo.setAddress(address);
						adminInfo.setName(addressName);
						adminInfo.setMobile(addressMobile);
						adminInfo.setModifiedTime(new Date());
						adminInfoMapper.updateByPrimaryKeySelective(adminInfo);
					}
				}
				// 修改用户角色对象
				AdminRoleExample adminRoleExample = new AdminRoleExample();
				adminRoleExample.createCriteria().andAdminIdEqualTo(adminId);
				List<AdminRole> adminRoleList = adminRoleMapper.selectByExample(adminRoleExample);
				if (adminRoleList != null && adminRoleList.size() > 0) {
					AdminRole adminRole = adminRoleList.get(0);
					adminRole.setRoleId(roleId);
					adminRole.setCreateTime(new Date());
					adminRoleMapper.updateByPrimaryKeySelective(adminRole);
				} else {
					AdminRole adminRole = new AdminRole();
					adminRole.setAdminId(adminId);
					adminRole.setRoleId(roleId);
					adminRole.setCreateTime(new Date());
					adminRoleMapper.insert(adminRole);
				}

				// 修改管理员数据角色
				AdminDataRoleExample adminDataRoleExample = new AdminDataRoleExample();
				adminDataRoleExample.createCriteria().andAdminIdEqualTo(adminId);
				List<AdminDataRole> adminDataRoleList = adminDataRoleMapper.selectByExample(adminDataRoleExample);
				if (adminDataRoleList != null && adminDataRoleList.size() > 0) {
					AdminDataRole adminDataRole = adminDataRoleList.get(0);
					adminDataRole.setDataRoleId(dataRoleId);
					adminDataRole.setCreateTime(new Date());
					adminDataRoleMapper.updateByPrimaryKeySelective(adminDataRole);
				} else {
					AdminDataRole adminDataRole = new AdminDataRole();
					adminDataRole.setAdminId(adminId);
					adminDataRole.setDataRoleId(dataRoleId);
					adminDataRole.setCreateTime(new Date());
					adminDataRoleMapper.insert(adminDataRole);
				}
				
				//修改仓储
				GoodsWarehouseExample goodsWarehouseExample = new GoodsWarehouseExample();
				goodsWarehouseExample.createCriteria().andBizIdEqualTo(adminId);
				List<GoodsWarehouse> goodsWarehouseList = goodsWarehouseMapper.selectByExample(goodsWarehouseExample);
				if (goodsWarehouseList.size() <= 0) {
					return JsonData.setErrorMessage("仓储地址不存在");
				}
				return JsonData.setSuccessMessage();
			} else if (type != null && type == StatusConst.ADMIN__TYPE_INTERNAL) {// 内部
				if (adminId == null || StringUtils.isBlank(mobile) || roleId == null || dataRoleId == null)
					return JsonData.setErrorMessage("参数非法");
				
				AdminExample adminExample = new AdminExample();
				adminExample.createCriteria().andMobileEqualTo(mobile).andIdNotEqualTo(adminId);
				List<Admin> adminList = adminMapper.selectByExample(adminExample);
				if (adminList != null && adminList.size() > 0) {
					return JsonData.setErrorMessage("手机号已经被注册");
				}
				
				Admin admin = new Admin();
				admin.setId(adminId);
				admin.setMobile(mobile);
				// admin.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
				admin.setName(name);
				admin.setSex(sex);
				admin.setType(type);// 内部
				admin.setStatus(StatusConst.ADMIN_STATSU_NOT_DEL);
				admin.setIdCard(idCard);
				admin.setModifiedTime(new Date());
				adminMapper.updateByPrimaryKeySelective(admin);

				// 修改用户角色对象
				AdminRoleExample adminRoleExample = new AdminRoleExample();
				adminRoleExample.createCriteria().andAdminIdEqualTo(adminId);
				List<AdminRole> adminRoleList = adminRoleMapper.selectByExample(adminRoleExample);
				if (adminRoleList != null && adminRoleList.size() > 0) {
					AdminRole adminRole = adminRoleList.get(0);
					adminRole.setRoleId(roleId);
					adminRole.setCreateTime(new Date());
					adminRoleMapper.updateByPrimaryKeySelective(adminRole);
				} else {
					AdminRole adminRole = new AdminRole();
					adminRole.setAdminId(adminId);
					adminRole.setRoleId(roleId);
					adminRole.setCreateTime(new Date());
					adminRoleMapper.insert(adminRole);
				}

				// 修改管理员数据角色
				AdminDataRoleExample adminDataRoleExample = new AdminDataRoleExample();
				adminDataRoleExample.createCriteria().andAdminIdEqualTo(adminId);
				List<AdminDataRole> adminDataRoleList = adminDataRoleMapper.selectByExample(adminDataRoleExample);
				if (adminDataRoleList != null && adminDataRoleList.size() > 0) {
					AdminDataRole adminDataRole = adminDataRoleList.get(0);
					adminDataRole.setDataRoleId(dataRoleId);
					adminDataRole.setCreateTime(new Date());
					adminDataRoleMapper.updateByPrimaryKeySelective(adminDataRole);
				} else {
					AdminDataRole adminDataRole = new AdminDataRole();
					adminDataRole.setAdminId(adminId);
					adminDataRole.setDataRoleId(dataRoleId);
					adminDataRole.setCreateTime(new Date());
					adminDataRoleMapper.insert(adminDataRole);
				}
				return JsonData.setSuccessMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("更新失败");
		}
		return JsonData.setErrorMessage("更新失败");
	}

	// 删除管理员信息
	@Override
	public JsonData delAdminUser(Integer[] adminIds, Byte type) {
		if (type != null && type == StatusConst.ADMIN_TYPE_SUPPLIER) {// 供应商
			try {
				// 管理员对象
				AdminExample example = new AdminExample();
				example.createCriteria().andIdIn(Arrays.asList(adminIds));
				Admin admin = new Admin();
				admin.setStatus(StatusConst.ADMIN_STATSU_DEL);// -1代表删除
				adminMapper.updateByExampleSelective(admin, example);

				// 管理员角色对象
				AdminRoleExample adminRoleExample = new AdminRoleExample();
				adminRoleExample.createCriteria().andAdminIdIn(Arrays.asList(adminIds));
				List<AdminRole> adminRoleList = adminRoleMapper.selectByExample(adminRoleExample);
				List<Integer> roleList = new ArrayList<>();
				// 获取角色id
				if (adminRoleList != null && adminRoleList.size() > 0) {
					for (AdminRole adminRole : adminRoleList) {
						roleList.add(adminRole.getRoleId());
					}
				}
				// 删除
				adminRoleMapper.deleteByExample(adminRoleExample);

				// 查询出角色对应的资源
				RolePermissionExample rolePermissionExample = new RolePermissionExample();
				rolePermissionExample.createCriteria().andRoleIdIn(roleList);

				// 管理员角色对象,获取该角色所涉及到的用户
				AdminRoleExample adminRoleExample2 = new AdminRoleExample();
				adminRoleExample2.createCriteria().andRoleIdIn(roleList);
				List<AdminRole> adminRoleList2 = adminRoleMapper.selectByExample(adminRoleExample2);
				// 获取用户id
				List<Integer> adminList = new ArrayList<>();
				if (adminRoleList2 != null && adminRoleList2.size() > 0) {
					for (AdminRole adminRole : adminRoleList2) {
						adminList.add(adminRole.getAdminId());
					}
				}

				// 客户端删除的用户id
				List<Integer> list = Arrays.asList(adminIds);
				// 如果删除的用户包含该角色所有的用户
				if (list.containsAll(adminList)) {
					// 删除角色资源对象
					rolePermissionMapper.deleteByExample(rolePermissionExample);
				}

				// 管理员数据角色对象
				AdminDataRoleExample adminDataRoleExample = new AdminDataRoleExample();
				adminDataRoleExample.createCriteria().andAdminIdIn(Arrays.asList(adminIds));
				// 删除
				adminDataRoleMapper.deleteByExample(adminDataRoleExample);
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("删除失败");
			}
			return JsonData.setSuccessMessage();
		} else if (type != null && type == StatusConst.ADMIN__TYPE_INTERNAL) {// 内部
			try {
				AdminExample example = new AdminExample();
				example.createCriteria().andIdIn(Arrays.asList(adminIds));
				Admin admin = new Admin();
				admin.setStatus(StatusConst.ADMIN_STATSU_DEL);// -1代表删除
				adminMapper.updateByExampleSelective(admin, example);

				// 管理员角色对象
				AdminRoleExample adminRoleExample = new AdminRoleExample();
				adminRoleExample.createCriteria().andAdminIdIn(Arrays.asList(adminIds));
				List<AdminRole> adminRoleList = adminRoleMapper.selectByExample(adminRoleExample);
				List<Integer> roleList = new ArrayList<>();
				// 获取角色id
				if (adminRoleList != null && adminRoleList.size() > 0) {
					for (AdminRole adminRole : adminRoleList) {
						roleList.add(adminRole.getRoleId());
					}
				}
				// 删除
				adminRoleMapper.deleteByExample(adminRoleExample);

				// 查询出角色对应的资源
				RolePermissionExample rolePermissionExample = new RolePermissionExample();
				rolePermissionExample.createCriteria().andRoleIdIn(roleList);

				// 管理员角色对象,获取该角色所涉及到的用户
				AdminRoleExample adminRoleExample2 = new AdminRoleExample();
				adminRoleExample2.createCriteria().andRoleIdIn(roleList);
				List<AdminRole> adminRoleList2 = adminRoleMapper.selectByExample(adminRoleExample2);
				// 获取角色id
				List<Integer> adminList = new ArrayList<>();
				if (adminRoleList2 != null && adminRoleList2.size() > 0) {
					for (AdminRole adminRole : adminRoleList2) {
						adminList.add(adminRole.getAdminId());
					}
				}

				// 客户端删除的用户id
				List<Integer> list = Arrays.asList(adminIds);
				// 如果删除的用户包含该角色所有的用户
				if (list.containsAll(adminList)) {
					// 删除角色资源对象
					rolePermissionMapper.deleteByExample(rolePermissionExample);
				}

				// 管理员数据角色对象
				AdminDataRoleExample adminDataRoleExample = new AdminDataRoleExample();
				adminDataRoleExample.createCriteria().andAdminIdIn(Arrays.asList(adminIds));
				// 删除
				adminDataRoleMapper.deleteByExample(adminDataRoleExample);

			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("删除失败");
			}
			return JsonData.setSuccessMessage();
		}
		return JsonData.setErrorMessage("删除失败");
	}

	// 管理员密码重置
	@Override
	public JsonData ResetAdminUserPassword(Integer[] adminIds, Byte type) {
		if (type != null && type == StatusConst.ADMIN_TYPE_SUPPLIER) {// 供应商
			AdminExample example = new AdminExample();
			example.createCriteria().andIdIn(Arrays.asList(adminIds));
			Admin admin = new Admin();
			String password = "123456";
			admin.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			try {
				adminMapper.updateByExampleSelective(admin, example);
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("密码重置失败");
			}
			return JsonData.setSuccessMessage();
		} else if (type != null && type == StatusConst.ADMIN__TYPE_INTERNAL) {// 内部
			AdminExample example = new AdminExample();
			example.createCriteria().andIdIn(Arrays.asList(adminIds));
			Admin admin = new Admin();
			String password = "123456";
			admin.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			try {
				adminMapper.updateByExampleSelective(admin, example);
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return JsonData.setErrorMessage("密码重置失败");
			}
			return JsonData.setSuccessMessage();
		}
		return JsonData.setErrorMessage("密码重置失败");
	}

	// 用户数量查询
	@Override
	public JsonData getUserAndAdminList(String queryString) {
		UserAndAdminNumber number = null;
		try {
			// 管理员用户数量
			Integer internalNum = adminNumberMapper.queryInternal(queryString);
			// 供应商数量
			Integer supplierNum = adminNumberMapper.querySupplier(queryString);
			// 普通用户数量
			Integer ordinaryNum = adminNumberMapper.queryOrdinaryNum(queryString);

			// 个人用户数量
			Integer individualNum = adminNumberMapper.queryIndividual(queryString);

			number = new UserAndAdminNumber(internalNum, supplierNum, ordinaryNum, individualNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonData.setSuccessMessage(number);
	}

	// 获取银行列表
	@Override
	public JsonData getBankList() {
		String bankList = jedisClientPool.get(RedisConst.BANK_LIST);
		return JsonUtils.jsonToPojo(bankList, JsonData.class);
	}

	/**
	 * 获取所有管理员账号
	 */
	@Override
	public JsonData getAdminAll(String mobile) {
		AdminExample adminExample = new AdminExample();
		if (StringUtils.isNotBlank(mobile)) {
			adminExample.createCriteria().andMobileLike("%" + mobile + "%")
					.andStatusEqualTo(AdminConst.ADMIN_STATUS_NORMAL);
		} else {
			adminExample.createCriteria().andStatusEqualTo(AdminConst.ADMIN_STATUS_NORMAL);
		}
		List<Admin> admins = adminMapper.selectByExample(adminExample);
		return JsonData.setSuccessMessage(admins);
	}

	@Override
	public Integer getAdminId(String mapValue) {
		AdminExample example = new AdminExample();
		example.createCriteria().andMobileEqualTo(mapValue);
		List<Admin> adminList = adminMapper.selectByExample(example);
		if (adminList !=null && adminList.size() > 0) {
			return	adminList.get(0).getId();
		}
		return null;
	}
	
	/**
	 * 获取供应商列表
	 */
	public JsonData getSupplierList(String keyWords){
		AdminExample example = new AdminExample();
		example.setOrderByClause("mobile asc");
		// 如果queryString不为空
		if (StringUtils.isNotBlank(keyWords)) {
			example.or().andNameLike("%" + keyWords + "%").andStatusEqualTo(StatusConst.ADMIN_STATSU_NOT_DEL).andTypeEqualTo(StatusConst.USER_TYPE_PERSONAL);
		}

		if (StringUtils.isBlank(keyWords))
			example.createCriteria().andTypeEqualTo(StatusConst.USER_TYPE_PERSONAL).andStatusEqualTo(StatusConst.ADMIN_STATSU_NOT_DEL);
		// 查询所有用户列表
		List<Admin> adminList = adminMapper.selectByExample(example);
		return JsonData.setSuccessMessage(adminList);
	}
}
