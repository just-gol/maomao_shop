package com.maomao.ssm.service.impl.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.AdminDataRole;
import com.maomao.ssm.bean.AdminDataRoleExample;
import com.maomao.ssm.bean.AdminExample;
import com.maomao.ssm.bean.AdminRole;
import com.maomao.ssm.bean.AdminRoleExample;
import com.maomao.ssm.bean.DataRole;
import com.maomao.ssm.bean.DataRoleExample;
import com.maomao.ssm.bean.Permission;
import com.maomao.ssm.bean.PermissionExample;
import com.maomao.ssm.bean.Role;
import com.maomao.ssm.bean.RoleExample;
import com.maomao.ssm.bean.RolePermission;
import com.maomao.ssm.bean.RolePermissionExample;
import com.maomao.ssm.constant.AdminConst;
import com.maomao.ssm.constant.RedisConst;
import com.maomao.ssm.constant.RoleConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.AdminDataRoleMapper;
import com.maomao.ssm.dao.AdminMapper;
import com.maomao.ssm.dao.AdminRoleMapper;
import com.maomao.ssm.dao.DataRoleMapper;
import com.maomao.ssm.dao.PermissionMapper;
import com.maomao.ssm.dao.RoleMapper;
import com.maomao.ssm.dao.RolePermissionMapper;
import com.maomao.ssm.pojo.DataRolePojo;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.MenuVO;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.pojo.PermissionIdAndAdminType;
import com.maomao.ssm.service.admin.MenuService;
import com.maomao.ssm.utils.JsonUtils;
import com.maomao.ssm.utils.ListUtils;

/**
 * @author:wzy
 * @descrption:权限菜单管理
 * @version:
 * @date:2018年5月9日
 */
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private JedisClientPool jedisClientPool;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private AdminRoleMapper adminRoleMapper;

	@Autowired
	private DataRoleMapper dataRoleMapper;
	@Autowired
	private AdminDataRoleMapper adminDataRoleMapper;
	@Autowired
	private AdminMapper adminMapper;

	/*
	 * 查询所有权限菜单
	 */
	public JsonData getMenuList() {
		// 查询缓存
		if (jedisClientPool.exists(RedisConst.PERMISSION_TREE_LIST)) {
			String result = jedisClientPool.get(RedisConst.PERMISSION_TREE_LIST);
			if (StringUtils.isNotBlank(result)) {
				try {
					JsonData data = JsonUtils.jsonToPojo(result, JsonData.class);
					return JsonData.setSuccessMessage(data);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		PermissionExample example = new PermissionExample();
		List<Permission> parentList = permissionMapper.selectByExample(example);
		JsonData jsonData = null;
		if (parentList != null && parentList.size() > 0) {
			List<MenuVO> returnList = fn(0, parentList);
			jsonData = JsonData.setSuccessMessage(returnList);
			try {
				String json = JsonUtils.objectToJson(jsonData);
				jedisClientPool.set(RedisConst.PERMISSION_TREE_LIST, json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return JsonData.setSuccessMessage(jsonData);
	}

	/**
	 * 递归查找子菜单 author:wzy
	 *//*
		 * private List<MenuVO> getChild(Integer menuId,PermissionExample example){
		 * example.clear(); example.createCriteria().andParentIdEqualTo(menuId);
		 * List<Permission> list = permissionMapper.selectByExample(example);
		 * List<MenuVO> returnlList = new ArrayList<MenuVO>(); if (list ==null) {
		 * 
		 * return null; }else { for (Permission p : list) { MenuVO vo = new MenuVO();
		 * vo.setName(p.getName()); vo.setId(p.getId());
		 * vo.setParentId(p.getParentId()); vo.setChildMenu(getChild(p.getId(),
		 * example)); returnlList.add(vo); } } return returnlList; }
		 */
	private List<MenuVO> fn(Integer menuId, List<Permission> listVo) {
		List<MenuVO> returnList = new ArrayList<MenuVO>();
		for (Permission p : listVo) {
			if (p.getParentId().equals(menuId)) {
				MenuVO vo = new MenuVO();
				vo.setId(p.getId());
				vo.setName(p.getName());
				vo.setParentId(p.getParentId());
				vo.setChildMenu(fn(p.getId(), listVo));
				returnList.add(vo);
			}
		}
		return returnList;
	}

	/**
	 * 添加/修改角色
	 */
	public JsonData addRole(String roleName, Integer[] ids, Integer roleId) {
		if (StringUtils.isBlank(roleName)) {
			return JsonData.setErrorMessage("参数错误");
		}

		// for (Integer i : ids) {
		// System.out.println("====" + i);
		// }
		//
		// System.out.println("roleId:" + roleId);

		Role role = new Role();
		role.setRoleName(roleName);
		role.setStatus(RoleConst.ROLE_STATUS_ON);
		role.setCreateTime(new Date());
		try {
			if (roleId == null) {
				RoleExample example = new RoleExample();
				example.createCriteria().andRoleNameEqualTo(roleName.trim());
				List<Role> nameList = roleMapper.selectByExample(example);
				if (nameList != null && nameList.size() > 0) {
					return JsonData.setErrorMessage("角色名称已经存在,请重新添加");
				}
				roleMapper.insertSelective(role);
			} else {

				role.setId(roleId);
				role.setModifiedTime(new Date());
				if (jedisClientPool.exists(RoleConst.ROLE_PERMISSION_DETAIL + roleId)) {
					jedisClientPool.del(RoleConst.ROLE_PERMISSION_DETAIL + roleId);
				}
				roleMapper.updateByPrimaryKeySelective(role);

				// 删除角色下登录用户的cookie
				AdminRoleExample example = new AdminRoleExample();
				example.createCriteria().andRoleIdEqualTo(roleId);
				List<AdminRole> adminIdList = adminRoleMapper.selectByExample(example);
				if (adminIdList != null && adminIdList.size() > 0) {
					// 删除登录用户cookie
					for (AdminRole adminRole : adminIdList) {
						if (jedisClientPool.exists(AdminConst.AMDIN_LOGIN_SUCCESS + adminRole.getAdminId() + "")) {
							jedisClientPool.del(AdminConst.AMDIN_LOGIN_SUCCESS + adminRole.getAdminId() + "");
						}
					}
				}

			}
			if (ids != null && ids.length > 0) {
				RolePermissionExample example = new RolePermissionExample();
				example.createCriteria().andRoleIdEqualTo(roleId == null ? role.getId() : roleId);
				List<RolePermission> rolePermission = rolePermissionMapper.selectByExample(example);
//				System.out.println("rolePermission:" + rolePermission);
				rolePermissionMapper.deleteByExample(example);

				for (Integer id : ids) {
					RolePermission permission = new RolePermission();
					permission.setPermissionId(id);
					permission.setRoleId(role.getId());
					rolePermissionMapper.insertSelective(permission);
				}
			}
			// 添加缓存
			jedisClientPool.set(RoleConst.ROLE_PERMISSION_ID_ARRAY + role.getId(), ids.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("添加失败,网络异常,请重新添加!");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 根据角色id查看角色对应的权限
	 */
	public JsonData getRolePermission(Integer roleId) {
		if (roleId == null) {
			return JsonData.setErrorMessage("参数错误");
		}

		if (jedisClientPool.exists(RoleConst.ROLE_PERMISSION_DETAIL + roleId)) {
			String result = jedisClientPool.get(RoleConst.ROLE_PERMISSION_DETAIL + roleId);
			if (StringUtils.isNotBlank(result)) {
				try {
					List<MenuVO> jsonToList = JsonUtils.jsonToList(result, MenuVO.class);
					return JsonData.setSuccessMessage(jsonToList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(example);

		if (rolePermissionList != null && rolePermissionList.size() > 0) {
			List<Integer> ids = new ArrayList<Integer>();
			for (RolePermission p : rolePermissionList) {
				ids.add(p.getPermissionId());
//				System.out.println("PermissionId:" + p.getPermissionId());
			}

			// PermissionExample example2 = new PermissionExample();
			// example2.createCriteria().andIdIn(ids).andParentIdEqualTo(0); //包含
			// 16,1,15的id并且父类id是0的
			// List<Permission> parentList = permissionMapper.selectByExample(example2);

			PermissionExample example2 = new PermissionExample();
			example2.createCriteria().andIdIn(ids).andParentIdEqualTo(0);
			List<Permission> parentList1 = permissionMapper.selectByExample(example2); // 获取包含ids数组并且父类id是0的对象

			PermissionExample example3 = new PermissionExample();
			example3.createCriteria().andIdIn(ids);
			List<Permission> parentList2 = permissionMapper.selectByExample(example3);

			// for (Permission permission : parentList1) {// permission:账号管理:1:0
			// System.out.println("permission:" + permission.getName() + ":" +
			// permission.getId() + ":"
			// + permission.getParentId());
			// }

			List<MenuVO> returnList = null;
			if (parentList1 != null && parentList1.size() > 0) {
				returnList = new ArrayList<MenuVO>(); // menuId=1
				for (Permission root : parentList1) {
					MenuVO rootMenu = new MenuVO();
					Integer menuId = root.getId();
					String name = root.getName();
					rootMenu.setId(root.getId());
					rootMenu.setParentId(root.getParentId());
					rootMenu.setName(name);

					// example2.clear();
					// example2.createCriteria().andParentIdNotEqualTo(0);
					// List<Permission> p = permissionMapper.selectByExample(example2);

					List<MenuVO> child = fn(menuId, parentList2);

					rootMenu.setChildMenu(child);
					returnList.add(rootMenu);
				}
				try {
					String json = JsonUtils.objectToJson(returnList);
					jedisClientPool.set(RoleConst.ROLE_PERMISSION_DETAIL + roleId, json);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return JsonData.setSuccessMessage(returnList);
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 删除角色
	 */
	public JsonData deleteRole(Integer[] roleId) {
		if (roleId == null || roleId.length == 0) {
			return JsonData.setErrorMessage("参数错误");
		}
		for (Integer id : roleId) {
			AdminRoleExample example2 = new AdminRoleExample();
			example2.createCriteria().andAdminIdEqualTo(id);
			List<AdminRole> list = adminRoleMapper.selectByExample(example2);
			if (list != null && list.size() > 0) {
				return JsonData.setErrorMessage("角色已经使用,不允许删除");
			}

			Role role = new Role();
			role.setId(id);
			role.setStatus((byte) -1);
			role.setModifiedTime(new Date());
			roleMapper.updateByPrimaryKeySelective(role);
			RolePermissionExample example = new RolePermissionExample();
			example.createCriteria().andRoleIdEqualTo(id);
			rolePermissionMapper.deleteByExample(example);
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 操作权限列表
	 */
	public JsonData getRoleList(Integer pages, Integer rows) {
		if (pages < 1) {
			pages = 1;
		}
		if (rows < 1) {
			rows = 10;
		}
		Page page = PageHelper.startPage(pages, rows);
		RoleExample example = new RoleExample();
		example.createCriteria().andStatusEqualTo((byte) 0);
		List<Role> list = roleMapper.selectByExample(example);
		List<Map<String, Object>> returnList = null;

		if (list != null && list.size() > 0) {
			returnList = new ArrayList<Map<String, Object>>();
			PermissionExample example2 = new PermissionExample();
			for (Role role : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", role.getId());
				map.put("name", role.getRoleName());
				// System.out.println("====="+role.getId());
				List<Integer> ids = getRolePermissionAsArray(role.getId());
				if (ids != null) {
					example2.clear();
					example2.createCriteria().andIdIn(ids);
					List<Permission> permissions = permissionMapper.selectByExample(example2);
					StringBuilder sb = new StringBuilder();
					if (permissions != null && permissions.size() > 0) {

						int length = permissions.size() > 2 ? 3 : permissions.size();
						for (int i = 0; i < length; i++) {
							if (i == 2) {
								sb.append(permissions.get(i).getName());
							} else {
								sb.append(permissions.get(i).getName()).append(",");
							}
						}
						if (permissions.size() > 2) {
							sb.append("...");
						}
					}
					map.put("permission", sb);
				}
				if (ids == null) {
					map.put("permission", "未分配权限");
				}
				returnList.add(map);
			}
		}

		PageBean pageBean = new PageBean();
		pageBean.setRows(returnList);
		pageBean.setTotal(page.getTotal());
		return JsonData.setSuccessMessage(pageBean);
	}

	/**
	 * 根据角色id查询权限菜单,不转成JsonData
	 */
	public List<MenuVO> getRolePermissionNotJsonData(Integer roleId) {

		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		List<MenuVO> returnList = null;
		List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(example);
		if (rolePermissionList != null && rolePermissionList.size() > 0) {
			List<Integer> ids = new ArrayList<Integer>();
			for (RolePermission p : rolePermissionList) {
				ids.add(p.getPermissionId());
			}

			PermissionExample example2 = new PermissionExample();
			example2.createCriteria().andIdIn(ids).andParentIdEqualTo(0);
			List<Permission> parentList = permissionMapper.selectByExample(example2);
			if (parentList != null && parentList.size() > 0) {
				returnList = new ArrayList<MenuVO>();
				for (Permission root : parentList) {
					MenuVO rootMenu = new MenuVO();
					Integer menuId = root.getId();
					String name = root.getName();
					rootMenu.setId(root.getId());
					rootMenu.setParentId(root.getParentId());
					rootMenu.setName(name);
					example2.clear();
					example2.createCriteria().andParentIdNotEqualTo(0);
					List<Permission> p = permissionMapper.selectByExample(example2);
					List<MenuVO> child = fn(menuId, p);

					rootMenu.setChildMenu(child);
					returnList.add(rootMenu);
				}

			}
			try {
				String json = JsonUtils.objectToJson(returnList);
				// jedisClientPool.set(RoleConst.ROLE_PERMISSION_DETAIL + roleId, json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnList;
	}

	/**
	 * 根据角色id查询权限菜单 ,返回菜单id数组
	 */
	public List<Integer> getRolePermissionAsArray(Integer roleId) {
		if (roleId == null) {
			return null;
		}
		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(example);
		List<Integer> ids = null;
		if (rolePermissionList != null && rolePermissionList.size() > 0) {
			ids = new ArrayList<Integer>();
			for (RolePermission p : rolePermissionList) {
				ids.add(p.getPermissionId());
			}
		}
		return ids;
	}

	/**
	 * 获取角色下拉列表选项
	 */
	public JsonData getRoleSelectList() {
		RoleExample example = new RoleExample();
		example.createCriteria().andStatusEqualTo((byte) 0);
		List<Role> roleList = roleMapper.selectByExample(example);
		List<Map<String, Integer>> returnList = new ArrayList<Map<String, Integer>>();
		if (roleList != null && roleList.size() > 0) {
			for (Role role : roleList) {
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put(role.getRoleName(), role.getId());
				returnList.add(map);
			}
		}
		return JsonData.setSuccessMessage(returnList);
	}

	/**
	 * 获取用户所有权限
	 */
	@Override
	public JsonData getUserPermissionsAll(Integer userId) {
		Integer roleId = null;
		
		PermissionIdAndAdminType permissionIdAndAdminType = null;
		try {
//			if (jedisClientPool.exists(RoleConst.ADMIN_ROLE_ID + userId)) {
//				roleId = Integer.parseInt(jedisClientPool.get(RoleConst.ADMIN_ROLE_ID + userId));
//			} else {
				
				AdminRoleExample example2 = new AdminRoleExample();
				example2.createCriteria().andAdminIdEqualTo(userId);
				List<AdminRole> roleList = adminRoleMapper.selectByExample(example2);

				if (roleList != null && roleList.size() > 0) {
					roleId = roleList.get(0).getRoleId();
					// 添加缓存
//					jedisClientPool.set(RoleConst.ADMIN_ROLE_ID + userId, roleId + "");
//				}
			}
			
			Admin admin = adminMapper.selectByPrimaryKey(userId);
			Byte type = admin.getType();
			RolePermissionExample example = new RolePermissionExample();
			example.createCriteria().andRoleIdEqualTo(roleId);
			List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(example);
			List<Integer> ids = new ArrayList<Integer>();
			permissionIdAndAdminType = new PermissionIdAndAdminType();
			if (rolePermissionList != null && rolePermissionList.size() > 0) {
				for (RolePermission rolePermission : rolePermissionList) {
					ids.add(rolePermission.getPermissionId());
				}
			}
			permissionIdAndAdminType.setList(ids);
			permissionIdAndAdminType.setType(type);
		} catch (Exception e) {
			// e.printStackTrace();
//			System.out.println("用户所对应的角色不存在");
		}
		return JsonData.setSuccessMessage(permissionIdAndAdminType);
	}

	/*
	 * 查询所有的角色
	 *
	 */
	@Override
	public JsonData getRoleAll() {
		RoleExample example = new RoleExample();
		example.createCriteria().andStatusEqualTo(RoleConst.ROLE_STATUS_ON);
		List<Role> roleList = roleMapper.selectByExample(example);
		try {
			if (roleList != null && roleList.size() > 0) {
				return JsonData.setSuccessMessage(roleList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonData.setErrorMessage("查询失败");
	}

	/**
	 * 查询数据角色列表
	 */
	@Override
	public JsonData getDataRoleList(Integer pages, Integer rows) {
		Page page = PageHelper.startPage(pages, rows);
		DataRoleExample dataRoleExample = new DataRoleExample();
		List<DataRole> dataRoles = dataRoleMapper.selectByExampleWithBLOBs(dataRoleExample);
		List<DataRolePojo> dataRolePojos = new ArrayList<DataRolePojo>();
		for (DataRole dataRole : dataRoles) {
			if (RoleConst.DATA_ROLE_TYPE_PART.equals(dataRole.getType())) {
				AdminExample adminExample = new AdminExample();
				adminExample.createCriteria()
						.andIdIn(ListUtils.getIntegerListFromString(dataRole.getScope(), StatusConst.IMG_SPLIT_STRING));
				List<Admin> admins = adminMapper.selectByExample(adminExample);
				String scope = "";
				for (Admin admin : admins) {
					scope += admin.getMobile() + StatusConst.IMG_SPLIT_STRING;
				}
				scope = scope.substring(0, scope.length() - 1);

				dataRolePojos.add(new DataRolePojo(dataRole, scope));
			} else {
				dataRolePojos.add(new DataRolePojo(dataRole));
			}
		}

		// 设置返回值
		PageInfo<DataRolePojo> pageInfo = new PageInfo<>(dataRolePojos);
		PageBean pagebean = new PageBean();
		// 获取总条数
		pagebean.setTotal(pageInfo.getTotal());
		// 获取当前页数据
		pagebean.setRows(dataRolePojos);
		return JsonData.setSuccessMessage(pagebean);
	}

	/**
	 * 查询数据角色详情
	 */
	@Override
	public JsonData getDataRoleDetail(Integer id) {
		DataRole dataRole = dataRoleMapper.selectByPrimaryKey(id);
		if (dataRole == null) {
			return JsonData.setErrorMessage("数据角色不存在");
		}
		DataRolePojo dataRolePojo = null;
		if (RoleConst.DATA_ROLE_TYPE_PART.equals(dataRole.getType())) {
			AdminExample adminExample = new AdminExample();
			adminExample.createCriteria()
					.andIdIn(ListUtils.getIntegerListFromString(dataRole.getScope(), StatusConst.IMG_SPLIT_STRING));
			List<Admin> admins = adminMapper.selectByExample(adminExample);
			dataRolePojo = new DataRolePojo(dataRole, JsonUtils.objectToJson(admins));
		} else {
			dataRolePojo = new DataRolePojo(dataRole);
		}
		return JsonData.setSuccessMessage(dataRolePojo);
	}

	/**
	 * 添加/修改数据角色
	 */
	@Override
	public JsonData addDataRole(String roleName, String scope, Integer id, Byte type) {
		if (StringUtils.isBlank(roleName) || type == null) {
			return JsonData.setErrorMessage("参数错误");
		}

		if (id == null) {// 添加
			DataRole dataRole = new DataRole();
			dataRole.setScope(scope);
			dataRole.setRoleName(roleName);
			dataRole.setType(type);
			dataRole.setCreateTime(new Date());
			dataRoleMapper.insert(dataRole);
		} else {// 修改
			DataRole dataRole = dataRoleMapper.selectByPrimaryKey(id);
			dataRole.setScope(scope);
			dataRole.setRoleName(roleName);
			dataRole.setType(type);
			dataRole.setModifiedTime(new Date());
			dataRoleMapper.updateByPrimaryKeyWithBLOBs(dataRole);
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 删除数据角色
	 */
	@Override
	public JsonData deleteDataRole(Integer id) {
		if (id == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		// 判断该角色是否有账号
		AdminDataRoleExample adminDataRoleExample = new AdminDataRoleExample();
		adminDataRoleExample.createCriteria().andDataRoleIdEqualTo(id);
		List<AdminDataRole> adminDataRoles = adminDataRoleMapper.selectByExample(adminDataRoleExample);
		if (adminDataRoles != null && adminDataRoles.size() > 0) {
			return JsonData.setErrorMessage("该角色已被使用，不能删除");
		}
		dataRoleMapper.deleteByPrimaryKey(id);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 获取使用数据角色
	 */
	@Override
	public JsonData getDataRoleAll() {
		List<DataRole> dataRoles = dataRoleMapper.selectByExample(new DataRoleExample());
		return JsonData.setSuccessMessage(dataRoles);
	}

}
