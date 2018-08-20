package com.maomao.ssm.pojo.shop;

import java.io.Serializable;

import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.StatusConst;

/** 
* @author:wzy
* @descrption:
* @version:
* @date:2018年1月29日
*/

public class OnSaleGoods implements Serializable{
	private static final long serialVersionUID = -4318644868358981789L;
	private Integer onSaleGoodsId ;//已经上架商品id
	private Integer id;//商品id
	private Integer type;//订单商品类型 0普通 1认筹
	private String img;//;分割
	private String name;
	private Long rebate;
	private Integer stock;
	public OnSaleGoods() {
		
	}
	public OnSaleGoods(GoodsWithBLOBs goodsWithBLOBs,Integer onSaleGoodsId) {
		this.setType(GoodsConst.GOODS_CATEGORY_NORMAL);//1:普通商品分类
		this.setImg(goodsWithBLOBs.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);//TODO | 常量
		this.setRebate(goodsWithBLOBs.getRebate());
		this.setName(goodsWithBLOBs.getName());
		this.setStock(goodsWithBLOBs.getStock());
		this.setId(goodsWithBLOBs.getId());
		this.onSaleGoodsId = onSaleGoodsId;
	}
	public OnSaleGoods(GoodsSubscriptionWithBLOBs goodsSubscription,Integer onSaleGoodsId) {
		this.setType(GoodsConst.GOODS_CATEGORY_SUBSCRIPTION);//0:认筹商品分类
		this.setImg(goodsSubscription.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
		this.setName(goodsSubscription.getName());
		this.setStock(1);
		this.setId(goodsSubscription.getId());
		this.onSaleGoodsId = onSaleGoodsId;
	}
	
	public Integer getOnSaleGoodsId() {
		return onSaleGoodsId;
	}
	public void setOnSaleGoodsId(Integer onSaleGoodsId) {
		this.onSaleGoodsId = onSaleGoodsId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getRebate() {
		return rebate;
	}
	public void setRebate(Long rebate) {
		this.rebate = rebate;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
}










































