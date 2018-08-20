package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.List;

import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.bean.HomeCategory;
import com.maomao.ssm.bean.HomeContent;

/**
 * 推荐位的pojo
 * @author Administrator
 *
 */
public class HomeDetail implements Serializable{
	private HomeCategory homeCategory;
	private Integer goodsCategoryId;//商品分类id
	private List<HomeContent> homeContentList;
	public HomeDetail() {
		super();
	}
	public HomeDetail(HomeCategory homeCategory, Integer goodsCategoryId) {
		super();
		this.homeCategory = homeCategory;
		this.goodsCategoryId = goodsCategoryId;
	}
	public HomeDetail(HomeCategory homeCategory, Integer goodsCategoryId, List<HomeContent> homeContentList) {
		super();
		this.homeCategory = homeCategory;
		this.goodsCategoryId = goodsCategoryId;
		this.homeContentList = homeContentList;
	}
	public HomeCategory getHomeCategory() {
		return homeCategory;
	}
	public void setHomeCategory(HomeCategory homeCategory) {
		this.homeCategory = homeCategory;
	}
	public Integer getGoodsCategoryId() {
		return goodsCategoryId;
	}
	public void setGoodsCategoryId(Integer goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}
	public List<HomeContent> getHomeContentList() {
		return homeContentList;
	}
	public void setHomeContentList(List<HomeContent> homeContentList) {
		this.homeContentList = homeContentList;
	}

}
