package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.List;

public class PermissionIdAndAdminType implements Serializable{
	private List<Integer> list;
	private Byte type;
	public PermissionIdAndAdminType() {
		super();
	}
	public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public PermissionIdAndAdminType(List<Integer> list, Byte type) {
		super();
		this.list = list;
		this.type = type;
	}
	
}
