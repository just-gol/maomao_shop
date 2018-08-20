package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsOverstockInfo;
import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.bean.GoodsWarehouse;

/**
 * 压货商品bean
 * 
 * @author Administrator
 *
 */
public class BuyOutGoods  implements Serializable {
	private GoodsOverstockInfo GoodsOverstockInfo; // 压货商品信息
	private GoodsWarehouse goodsWarehouse; // 仓储
	private List<GoodsAddress> addressList; // 自取地址
	public BuyOutGoods() {
	}
	public BuyOutGoods(com.maomao.ssm.bean.GoodsOverstockInfo goodsOverstockInfo, GoodsWarehouse goodsWarehouse,
			List<GoodsAddress> addressList) {
		super();
		GoodsOverstockInfo = goodsOverstockInfo;
		this.goodsWarehouse = goodsWarehouse;
		this.addressList = addressList;
	}
	public GoodsOverstockInfo getGoodsOverstockInfo() {
		return GoodsOverstockInfo;
	}
	public void setGoodsOverstockInfo(GoodsOverstockInfo goodsOverstockInfo) {
		GoodsOverstockInfo = goodsOverstockInfo;
	}
	public GoodsWarehouse getGoodsWarehouse() {
		return goodsWarehouse;
	}
	public void setGoodsWarehouse(GoodsWarehouse goodsWarehouse) {
		this.goodsWarehouse = goodsWarehouse;
	}
	public List<GoodsAddress> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<GoodsAddress> addressList) {
		this.addressList = addressList;
	}
	
}
