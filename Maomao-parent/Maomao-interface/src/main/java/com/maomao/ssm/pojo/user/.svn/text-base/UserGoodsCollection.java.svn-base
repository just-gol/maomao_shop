package com.maomao.ssm.pojo.user;

import java.io.Serializable;

import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.constant.StatusConst;

public class UserGoodsCollection implements Serializable {
	private Integer goodsId;
	private String name;
	private Long price;
	private String img;
	private Integer stock;

	public UserGoodsCollection() {
		super();
	}

	public UserGoodsCollection(GoodsWithBLOBs goods) {
		super();
		this.goodsId = goods.getId();
		this.name = goods.getName();
		this.price = goods.getMinPrice();
		this.img = goods.getImgs() == null ? null : goods.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0];
		this.stock = (goods.getStock() == null ? 0 : goods.getStock())
				+ (goods.getSalesSham() == null ? 0 : goods.getSalesSham());
	}

	public UserGoodsCollection(Integer goodsId, String name, Long price, String img, Integer stock) {
		super();
		this.goodsId = goodsId;
		this.name = name;
		this.price = price;
		this.img = img;
		this.stock = stock;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
