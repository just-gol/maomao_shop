package com.maomao.ssm.service.admin;

import java.util.List;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.MenuVO;

/** 
* @author:wzy
* @descrption:权限管理
* @version:
* @date:2018年5月9日
*/

public interface MenuService {
	JsonData getMenuList();
	JsonData addRole(String roleName,Integer[] ids,Integer roleId);
	JsonData getRolePermission(Integer roleId);
	List<MenuVO> getRolePermissionNotJsonData(Integer roleId);
	JsonData deleteRole(Integer[] roleId);
	JsonData getRoleList(Integer pages,Integer rows);
	List<Integer> getRolePermissionAsArray(Integer roleId);
	JsonData getRoleSelectList();
	JsonData getUserPermissionsAll(Integer userId);
	JsonData getRoleAll();
	JsonData getDataRoleList(Integer pages, Integer rows);
	JsonData getDataRoleDetail(Integer id);
	JsonData addDataRole(String roleName, String scope, Integer id, Byte type);
	JsonData deleteDataRole(Integer id);
	JsonData getDataRoleAll();
	
}

