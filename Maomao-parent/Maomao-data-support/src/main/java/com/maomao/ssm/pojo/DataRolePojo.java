package com.maomao.ssm.pojo;

import java.io.Serializable;

import com.maomao.ssm.bean.DataRole;

public class DataRolePojo implements Serializable {
	private Integer id;

	private String roleName;

	private Byte type;

	private String scope;

	private String scopeString;

	public DataRolePojo() {
		super();
	}

	public DataRolePojo(DataRole dataRole) {
		super();
		this.id = dataRole.getId();
		this.roleName = dataRole.getRoleName();
		this.type = dataRole.getType();
		this.scope = dataRole.getScope();
	}

	public DataRolePojo(DataRole dataRole, String scopeString) {
		super();
		this.id = dataRole.getId();
		this.roleName = dataRole.getRoleName();
		this.type = dataRole.getType();
		this.scope = dataRole.getScope();
		this.scopeString = scopeString;
	}

	public DataRolePojo(Integer id, String roleName, Byte type, String scope, String scopeString) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.type = type;
		this.scope = scope;
		this.scopeString = scopeString;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getScopeString() {
		return scopeString;
	}

	public void setScopeString(String scopeString) {
		this.scopeString = scopeString;
	}

}
