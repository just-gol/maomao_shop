package com.maomao.ssm.pojo.search;

import java.io.Serializable;
import java.util.Date;

import com.maomao.ssm.bean.GoodsService;

/**
 * @author:wzy
 * @descrption:
 * @version:
 */

public class GoodsServiceDetails implements Serializable {
	private String name;
	private String detail;// 服务内容

	public GoodsServiceDetails(GoodsService goodsService) {
		super();
		this.name = goodsService.getName();
		this.detail = goodsService.getDetail();
		this.createTime = goodsService.getCrateTime();
	}
	
	public GoodsServiceDetails() {
		super();
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	private Date createTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
