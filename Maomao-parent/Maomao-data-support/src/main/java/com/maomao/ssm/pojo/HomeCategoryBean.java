package com.maomao.ssm.pojo;

import java.io.Serializable;
/**
 * 首页推荐位分类
 * @author Administrator
 *
 */
public class HomeCategoryBean implements Serializable{
	private Integer categoryId; //首页推荐位分类id
	private String categoryName; //推荐位分类名称
	private String cateImg;//分类图片
	private String categoryUrl; //跳转的url
	private Byte imgType; //图片类型 小图1,大图2
	private String categoryIntroduction; //分类简介
	private Integer sort;	//排序 按排序号逆序排
	public HomeCategoryBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HomeCategoryBean(Integer categoryId, String categoryName, String cateImg, String categoryUrl, Byte imgType,
			String categoryIntroduction, Integer sort) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.cateImg = cateImg;
		this.categoryUrl = categoryUrl;
		this.imgType = imgType;
		this.categoryIntroduction = categoryIntroduction;
		this.sort = sort;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCateImg() {
		return cateImg;
	}
	public void setCateImg(String cateImg) {
		this.cateImg = cateImg;
	}
	public String getCategoryUrl() {
		return categoryUrl;
	}
	public void setCategoryUrl(String categoryUrl) {
		this.categoryUrl = categoryUrl;
	}
	public Byte getImgType() {
		return imgType;
	}
	public void setImgType(Byte imgType) {
		this.imgType = imgType;
	}
	public String getCategoryIntroduction() {
		return categoryIntroduction;
	}
	public void setCategoryIntroduction(String categoryIntroduction) {
		this.categoryIntroduction = categoryIntroduction;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
