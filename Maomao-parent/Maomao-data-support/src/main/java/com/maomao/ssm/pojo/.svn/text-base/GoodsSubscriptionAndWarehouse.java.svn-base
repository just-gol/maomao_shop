package com.maomao.ssm.pojo;

import java.io.Serializable;

import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsWarehouse;

/**
 * 认筹,仓库bean
 * @author Administrator
 *
 */
public class GoodsSubscriptionAndWarehouse implements Serializable{
	private GoodsSubscriptionWithBLOBs goods; //认筹表
	private GoodsWarehouse goodsWarehouse;//仓库表
	private GoodsAddress goodsAddress;//自取地址 
	public GoodsAddress getGoodsAddress() {
		return goodsAddress;
	}
	public void setGoodsAddress(GoodsAddress goodsAddress) {
		this.goodsAddress = goodsAddress;
	}
	public GoodsSubscriptionAndWarehouse() {
	}
	public GoodsSubscriptionAndWarehouse(GoodsSubscriptionWithBLOBs goods) {
		this.goods = goods;
	}
	public GoodsSubscriptionAndWarehouse(GoodsSubscriptionWithBLOBs goods, GoodsWarehouse goodsWarehouse) {
		this.goods = goods;
		this.goodsWarehouse = goodsWarehouse;
	}
	public GoodsSubscriptionWithBLOBs getGoods() {
		return goods;
	}
	public void setGoods(GoodsSubscriptionWithBLOBs goods) {
		this.goods = goods;
	}
	public GoodsWarehouse getGoodsWarehouse() {
		return goodsWarehouse;
	}
	public void setGoodsWarehouse(GoodsWarehouse goodsWarehouse) {
		this.goodsWarehouse = goodsWarehouse;
	}
	
	
}
