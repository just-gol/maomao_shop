package com.maomao.ssm.pojo.home;

import java.io.Serializable;
import java.util.Date;

import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.bean.GoodsHomepageCategory;

/** 
* @author:wzy
* @descrption:
* @version:
*/

public class CategoryDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer categoryId;
	private String img; 
	private Date createTime;
	private String name;
	public CategoryDetail (GoodsHomepageCategory goodsHomepageCategory) {
		this.categoryId= goodsHomepageCategory.getGoodsCategoryid();
		this.img = goodsHomepageCategory.getImg();
		this.createTime = goodsHomepageCategory.getCreateTime();
		this.name = goodsHomepageCategory.getName();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDetail() {
		
	}

	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
