package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.List;

import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsApply;
import com.maomao.ssm.bean.GoodsBrand;
import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.bean.GoodsWarehouse;
import com.maomao.ssm.bean.GoodsWithBLOBs;

/**
 * 商品下架点击处理后封装的bean
 * 
 * @author Administrator
 *
 */
public class GoodsShelves implements Serializable {
	private Integer goodsApplyId; //下架列表id
	private String reason;//审核驳回理由
	private String goodsName; //商品名称
	private Integer stock; //存储
	private Integer stockSham; //虚拟库存
	private Long pricePurchase; //零售单价
	private Long priceSales; //上架售价
	private Long rebate; //分红金额
	private Long reword; //奖励金

	private String imgs; //商品宣传图片
	private String detail; //商品详情
	private String param; //商品参数
	
	private String goodsBrandName; //服务品牌名称
	private String categoryName; //商品分类名称
	private List<String> goodsServiceName; //商品服务名称
	
	private Byte payType; //支付方式
	private Byte getWay; //配送方式
	
	//仓库地址
	private Byte warehouseType; //仓库存储方式
	private String warehouseProvince; //省
	private String warehouseCity; //市
	private String warehouseArea; //区	
	private String warehouseAddress; //地址详情
	private String warehouseName; //联系人
	private String  warehouseMobile; //联系人手机号
	
	private GoodsAddress goodsAddress;//自取地址
	
	public GoodsAddress getGoodsAddress() {
		return goodsAddress;
	}
	public void setGoodsAddress(GoodsAddress goodsAddress) {
		this.goodsAddress = goodsAddress;
	}
	public GoodsShelves() {
	}
	
	public GoodsShelves(GoodsApply goodsApply, GoodsWithBLOBs goodsWithBLOBs ,GoodsBrand goodsBrand,GoodsWarehouse goodsWarehouse,GoodsCategory goodsCategory) {
		this.goodsApplyId = goodsApply.getId();
		this.goodsName = goodsWithBLOBs.getName();
		this.stock = goodsWithBLOBs.getStock();
		this.stockSham = goodsWithBLOBs.getStockSham();
		this.pricePurchase = goodsWithBLOBs.getPricePurchase();
		this.priceSales = goodsWithBLOBs.getPriceSales();
		this.rebate = goodsWithBLOBs.getRebate();
		this.reword = goodsWithBLOBs.getReword();
		this.imgs = goodsWithBLOBs.getImgs();
		this.detail = goodsWithBLOBs.getDetail();
		this.param = goodsWithBLOBs.getParam();
		this.payType = goodsWithBLOBs.getPayType();
		this.getWay = goodsWithBLOBs.getGetWay();
		this.goodsBrandName = goodsBrand.getName();
		this.categoryName = goodsCategory.getName();
		this.warehouseType = goodsWarehouse.getType();
		this.warehouseProvince = goodsWarehouse.getProvince();
		this.warehouseCity = goodsWarehouse.getCity();
		this.warehouseArea = goodsWarehouse.getArea();
		this.warehouseAddress = goodsWarehouse.getAddress();
		this.warehouseName = goodsWarehouse.getName();
		this.warehouseMobile = goodsWarehouse.getMobile();
	}

	public Integer getGoodsApplyId() {
		return goodsApplyId;
	}

	public void setGoodsApplyId(Integer goodsApplyId) {
		this.goodsApplyId = goodsApplyId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getStockSham() {
		return stockSham;
	}

	public void setStockSham(Integer stockSham) {
		this.stockSham = stockSham;
	}

	public Long getPricePurchase() {
		return pricePurchase;
	}

	public void setPricePurchase(Long pricePurchase) {
		this.pricePurchase = pricePurchase;
	}

	public Long getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Long priceSales) {
		this.priceSales = priceSales;
	}

	public Long getRebate() {
		return rebate;
	}

	public void setRebate(Long rebate) {
		this.rebate = rebate;
	}

	public Long getReword() {
		return reword;
	}

	public void setReword(Long reword) {
		this.reword = reword;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getGoodsBrandName() {
		return goodsBrandName;
	}

	public void setGoodsBrandName(String goodsBrandName) {
		this.goodsBrandName = goodsBrandName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<String> getGoodsServiceName() {
		return goodsServiceName;
	}

	public void setGoodsServiceName(List<String> goodsServiceName) {
		this.goodsServiceName = goodsServiceName;
	}

	public Byte getPayType() {
		return payType;
	}

	public void setPayType(Byte payType) {
		this.payType = payType;
	}

	public Byte getGetWay() {
		return getWay;
	}

	public void setGetWay(Byte getWay) {
		this.getWay = getWay;
	}

	public Byte getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(Byte warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getWarehouseProvince() {
		return warehouseProvince;
	}

	public void setWarehouseProvince(String warehouseProvince) {
		this.warehouseProvince = warehouseProvince;
	}

	public String getWarehouseCity() {
		return warehouseCity;
	}

	public void setWarehouseCity(String warehouseCity) {
		this.warehouseCity = warehouseCity;
	}

	public String getWarehouseArea() {
		return warehouseArea;
	}

	public void setWarehouseArea(String warehouseArea) {
		this.warehouseArea = warehouseArea;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehouseMobile() {
		return warehouseMobile;
	}

	public void setWarehouseMobile(String warehouseMobile) {
		this.warehouseMobile = warehouseMobile;
	}
	
}
