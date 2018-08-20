package com.maomao.ssm.pojo.search;

import java.io.Serializable;
import java.util.List;

public class GoodsDetailsSku implements Serializable {
	private Integer keyId;
	private String name;
	private List<String> values;

	public Integer getKeyId() {
		return keyId;
	}

	public void setKeyId(Integer keyId) {
		this.keyId = keyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
