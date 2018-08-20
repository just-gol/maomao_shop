package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.List;

/** 
* @author:wzy
* @descrption:
* @version:
* @date:2018年5月9日
*/

public class MenuVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer id;
	private Integer parentId;
	private List<MenuVO> childMenu;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public List<MenuVO> getChildMenu() {
		return childMenu;
	}
	public void setChildMenu(List<MenuVO> childMenu) {
		this.childMenu = childMenu;
	}
	
}




























