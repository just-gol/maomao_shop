package com.maomao.ssm.pojo.home;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.maomao.ssm.bean.HomeCategory;

/** 
* @author:wzy
* @descrption:
* @version:
*/

public class RecommendDetail implements Serializable{
	private String name ;//分类名称
	private String introduction;//分类简介
	private String url;//显示更多
	private Byte imgType;//图片类型 0小图 1中图 2大图
	private Date createTime;//创建时间
	private List<HomeRecommendGoods> recommendGoodsList;

	
	public RecommendDetail (HomeCategory homeCategory,List<HomeRecommendGoods> recommendGoodsList) {
		this.name = homeCategory.getName();
		this.introduction =homeCategory.getIntroduction();
		this.url = homeCategory.getUrl();
		this.imgType = homeCategory.getImgType();
		this.createTime = homeCategory.getCreateTime();
		this.recommendGoodsList = recommendGoodsList;
	}
	
	public RecommendDetail() {
		
	}
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Byte getImgType() {
		return imgType;
	}

	public void setImgType(Byte imgType) {
		this.imgType = imgType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<HomeRecommendGoods> getRecommendGoodsList() {
		return recommendGoodsList;
	}

	public void setRecommendGoodsList(List<HomeRecommendGoods> recommendGoodsList) {
		this.recommendGoodsList = recommendGoodsList;
	}

	
}






















