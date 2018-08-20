package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.List;

import com.maomao.ssm.bean.Goods;
import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsSku;
import com.maomao.ssm.bean.GoodsWarehouse;

/**
 * 商品,仓库,商品服务bean
 * @author Administrator
 *
 */
public class GoodsAndWarehouse implements Serializable {
	private Goods Goods;
	private GoodsWarehouse goodsWarehouse;
	private List<String> goodsServiceName; //商品服务名称
	private String reason;
	private List<GoodsSku> skuList;
	private GoodsAddress goodsAddress;
	
	public GoodsAddress getGoodsAddress() {
		return goodsAddress;
	}

	public void setGoodsAddress(GoodsAddress goodsAddress) {
		this.goodsAddress = goodsAddress;
	}

	public List<GoodsSku> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<GoodsSku> skuList) {
		this.skuList = skuList;
	}

	public GoodsAndWarehouse() {
	}
	
	public GoodsAndWarehouse(com.maomao.ssm.bean.Goods goods) {
		Goods = goods;
	}
	public GoodsAndWarehouse(com.maomao.ssm.bean.Goods goods,String reason) {
		Goods = goods;
		this.reason = reason;
	}
	public GoodsAndWarehouse(com.maomao.ssm.bean.Goods goods, GoodsWarehouse goodsWarehouse) {
		Goods = goods;
		this.goodsWarehouse = goodsWarehouse;
	}
	
	public GoodsAndWarehouse(com.maomao.ssm.bean.Goods goods, GoodsWarehouse goodsWarehouse,String reason) {
		Goods = goods;
		this.goodsWarehouse = goodsWarehouse;
		this.reason = reason;
	}
	public GoodsAndWarehouse(com.maomao.ssm.bean.Goods goods,List<String> goodsServiceName) {
		Goods = goods;
		this.goodsServiceName = goodsServiceName;
	}
	public GoodsAndWarehouse(com.maomao.ssm.bean.Goods goods, GoodsWarehouse goodsWarehouse,List<String> goodsServiceName) {
		Goods = goods;
		this.goodsWarehouse = goodsWarehouse;
		this.goodsServiceName = goodsServiceName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public List<String> getGoodsServiceName() {
		return goodsServiceName;
	}
	public void setGoodsServiceName(List<String> goodsServiceName) {
		this.goodsServiceName = goodsServiceName;
	}
	public Goods getGoods() {
		return Goods;
	}
	public void setGoods(Goods goods) {
		Goods = goods;
	}
	public GoodsWarehouse getGoodsWarehouse() {
		return goodsWarehouse;
	}
	public void setGoodsWarehouse(GoodsWarehouse goodsWarehouse) {
		this.goodsWarehouse = goodsWarehouse;
	}
}
