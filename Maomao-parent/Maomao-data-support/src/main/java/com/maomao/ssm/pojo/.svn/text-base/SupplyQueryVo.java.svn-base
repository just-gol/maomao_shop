package com.maomao.ssm.pojo;

import java.io.Serializable;

import com.maomao.ssm.bean.Goods;
import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.constant.GoodsConst;

/** 
* @author:wzy
* @descrption:货源总览VO
* @version:
* @date:2018年3月7日
*/

public class SupplyQueryVo implements Serializable{
	private static final long serialVersionUID = -2896613576176645063L;
	private Byte flag;//0:普通商品  1:认筹商品
	private String name;//商品名称
	private String categoryName;//分类名称
	private String price;//零售单价
	private Integer stock;//库存量
	private String goodsProperty;//商品性质
	private String owner;//商品所属公司
	private String goodsStatus;//商品状态
	private Byte warehouseType;//仓储类型
	private Integer id;//商品id
	private GoodsAddress goodsAddress; //自取地址
	private Byte status;
	
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public GoodsAddress getGoodsAddress() {
		return goodsAddress;
	}
	public void setGoodsAddress(GoodsAddress goodsAddress) {
		this.goodsAddress = goodsAddress;
	}
	public SupplyQueryVo() {
	}
	public SupplyQueryVo(Goods goods,String categoryName,String owner,Byte warehouseType,String price) {
		this.flag = 0;
		this.id = goods.getId();
		this.name=goods.getName();
		this.categoryName = categoryName;
		this.price = price;
		this.stock = goods.getStock();
		this.owner =owner;
		this.warehouseType = warehouseType;
		this.status = goods.getStatus();
		Byte type = goods.getType();
		if (type==GoodsConst.GOODS_TYPE_SELF) {
			this.goodsProperty = "自营商品";
			this.owner = "平台";
		}else if (type==GoodsConst.GOODS_TYPE_NORMAL) {
			this.goodsProperty = "常规商品";
		}else if (type==GoodsConst.GOODS_TYPE_MORTGAGE) {
			this.goodsProperty = "质押商品";
		}else if (type==GoodsConst.GOODS_TYPE_VR_MORTGAGE) {
			this.goodsProperty = "虚拟质押";
		}
		Byte status = goods.getStatus();
		if (status == GoodsConst.GOODS_STATUS_OFF_SHELF) {
			this.goodsStatus="未上架";
		}else if (status == GoodsConst.GOODS_STATUS_OFF_SHELF_CHECKING) {
			this.goodsStatus="审核中";
		}else if (status==GoodsConst.GOODS_STATUS_ON_SALE) {
			this.goodsStatus="已上架";
		}else if (status == GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED) {
			this.goodsStatus="申请驳回";
		}
		
		
	}
	/*public SupplyQueryVo(GoodsSubscription goodsSubscription,String categoryName,String owner,Byte warehouseType) {
		this.flag = 1;
		this.id = goodsSubscription.getId();
		this.name=goodsSubscription.getName();
		this.categoryName = categoryName;
		this.price = new DecimalFormat(".00").format(((double)goodsSubscription.getPriceSales())/100);
		this.stock = goodsSubscription.getStock();
		this.owner=owner;
		this.warehouseType = warehouseType;
		this.goodsProperty="认筹商品";
		Byte status = goodsSubscription.getSubscriptionStatus();
		if (status == GoodsConst.GOODS_SUBSCRIPTION_STATUS_UNSTART) {
			this.goodsStatus="未开始";
		}else if (status == GoodsConst.GOODS_SUBSCRIPTION_STATUS_SUBSCRIBING) {
			this.goodsStatus="未完成";
		}else if (status==GoodsConst.GOODS_SUBSCRIPTION_STATUS_ON_SALE) {
			this.goodsStatus="已完成";
		}else if (status == GoodsConst.GOODS_SUBSCRIPTION_STATUS_FINISHED) {
			this.goodsStatus="已结束";
		}else if (status == GoodsConst.GOODS_SUBSCRIPTION_STATUS_SUBSCRIBE_FAILED) {
			this.goodsStatus="认筹失败";
		}else if (status == GoodsConst.GOODS_SUBSCRIPTION_STATUS_ON_CKECK) {
			this.goodsStatus="审核中";
		}else if (status == GoodsConst.GOODS_SUBSCRIPTION_STATUS_SALE_FAILED) {
			this.goodsStatus="分销失败";
		}
	}*/
	
	public Byte getFlag() {
		return flag;
	}
	public void setFlag(Byte flag) {
		this.flag = flag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getGoodsProperty() {
		return goodsProperty;
	}
	public void setGoodsProperty(String goodsProperty) {
		this.goodsProperty = goodsProperty;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getGoodsStatus() {
		return goodsStatus;
	}
	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Byte getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(Byte warehouseType) {
		this.warehouseType = warehouseType;
	}
	
}































