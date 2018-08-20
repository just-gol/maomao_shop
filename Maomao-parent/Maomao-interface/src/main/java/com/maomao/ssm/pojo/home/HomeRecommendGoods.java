package com.maomao.ssm.pojo.home;

import java.io.Serializable;

import com.maomao.ssm.bean.HomeContent;

/** 
* @author:wzy
* @descrption:首页推荐位分类
* @version:
*/

public class HomeRecommendGoods implements Serializable{
	private String name;//商品名称
	private String img;//图片
	private String url;//跳转链接
	private Long price;//商品价格
	private String introduction;//商品内容简介
	
	public HomeRecommendGoods(HomeContent homeContent) {
		this.name = homeContent.getName();
		this.img=homeContent.getImg();
		this.url = homeContent.getUrl();
		this.introduction=homeContent.getIntroduction();
		this.price = homeContent.getPrice();
	}
	
	public HomeRecommendGoods() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
}
































