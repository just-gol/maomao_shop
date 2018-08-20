package com.maomao.ssm.pojo.category;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.maomao.ssm.pojo.home.CategoryDetail;
import com.maomao.ssm.pojo.home.HomeBannerDetail;


/** 
* @author:wzy
* @descrption:分类详情
* @version:
*/

public class CategoryDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<HomeBannerDetail> bannerList;
	private List<CategoryDetail> categoryList;
	private List<Map<String, Object>> article;
	

	public List<Map<String, Object>> getArticle() {
		return article;
	}
	public void setArticle(List<Map<String, Object>> article) {
		this.article = article;
	}
	public List<HomeBannerDetail> getBannerList() {
		return bannerList;
	}
	public void setBannerList(List<HomeBannerDetail> bannerList) {
		this.bannerList = bannerList;
	}
	public List<CategoryDetail> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<CategoryDetail> categoryList) {
		this.categoryList = categoryList;
	}
}











































