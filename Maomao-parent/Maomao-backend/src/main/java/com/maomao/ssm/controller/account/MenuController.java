package com.maomao.ssm.controller.account;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.bean.RoleExample;
import com.maomao.ssm.constant.AdminConst;
import com.maomao.ssm.dao.RoleMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.MenuService;
import com.maomao.ssm.utils.CookieUtils;
import com.maomao.ssm.utils.JsonUtils;

/**
 * @author:wzy
 * @descrption:权限管理
 * @version:
 * @date:2018年5月8日
 */
@Controller
@RequestMapping(value = "/role")
public class MenuController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private JedisClientPool jedisClientPool;

	/**
	 * 获取所有权限菜单
	 */
	@RequestMapping(value = "/getMeunList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getServicManagementList() {
		return menuService.getMenuList();
	}

	/**
	 * 添加/修改
	 */
	@RequestMapping(value = "/editRole.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData editRole(String roleName, Integer[] ids, Integer roleId) {
		return menuService.addRole(roleName, ids, roleId);
	}

	/**
	 * 查看角色对应权限
	 */
	@RequestMapping(value = "/getRolePermission.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getRolePermission(Integer roleId) {
		return menuService.getRolePermission(roleId);
	}

	/**
	 * 删除角色
	 */
	@RequestMapping(value = "/deleteRole.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData deleteRoleById(Integer[] roleId) {
		return menuService.deleteRole(roleId);
	}

	/**
	 * 查看角色列表
	 */
	@RequestMapping(value = "/getRoleList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getRoleList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows) {
		return menuService.getRoleList(pages, rows);
	}

	/**
	 * 获取角色下拉列表选项
	 */
	@RequestMapping(value = "/getRoleSelectList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getRoleSelectList() {
		return menuService.getRoleSelectList();
	}

	/**
	 * 获取用户所有权限
	 */
	@RequestMapping(value = "/getUserPermissionsAll.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getUserPermissionsAll(HttpServletRequest request) {
		// String cookie = CookieUtils.getCookieValue(request, "token", true);
		// String userId = null;
		// Map map = null;
		// if (StringUtils.isNotBlank(cookie)) {
		// userId = jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS, cookie);//用户id
		// if (StringUtils.isNotBlank(userId)) {
		// String redisCookie = jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS +
		// userId, "token");
		// // System.out.println("redisCookie:"+redisCookie);
		// if (StringUtils.isNotBlank(redisCookie) && redisCookie.equals(cookie)) {
		// String userInfo = jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS +
		// userId, "userInfo");
		// map = JsonUtils.jsonToPojo(userInfo, Map.class);
		// }
		// }
		// }
		// Integer adminId = (Integer) map.get(userId);

		String cookie = CookieUtils.getCookieValue(request, "token", true);
		Integer adminId = null;
		if (StringUtils.isNotBlank(cookie)) {
			adminId = Integer.parseInt((jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS, cookie)));// 用户id
		}
		return menuService.getUserPermissionsAll(adminId);
	}

	/*
	 * 查询所有的角色
	 *
	 */
	@RequestMapping(value = "/getRoleAll.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getRoleAll() {
		return menuService.getRoleAll();
	}

	@RequestMapping(value = "/getDataRoleList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getDataRoleList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows) {
		return menuService.getDataRoleList(pages, rows);
	}

	@RequestMapping(value = "/getDataRoleDetail.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getDataRoleDetail(Integer id) {
		return menuService.getDataRoleDetail(id);
	}

	@RequestMapping(value = "/addDataRole.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addDataRole(String roleName, String scope, Integer id, Byte type) {
		return menuService.addDataRole(roleName, scope, id, type);
	}

	@RequestMapping(value = "/deleteDataRole.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData deleteDataRole(Integer id) {
		return menuService.deleteDataRole(id);
	}

	@RequestMapping(value = "/getDataRoleAll.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getDataRoleAll() {
		return menuService.getDataRoleAll();
	}

}
