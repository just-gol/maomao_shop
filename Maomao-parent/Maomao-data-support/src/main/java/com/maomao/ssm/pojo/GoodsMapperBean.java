package com.maomao.ssm.pojo;

import java.io.Serializable;

/**
 * 自营,常规,质押商品查询的条件
 * 
 * @author Administrator
 *
 */
public class GoodsMapperBean implements Serializable {
	private Integer pages; // 当前页
	private Integer rows; // 当前页条数
	private Integer startRows; // 开始位置
	private Integer categoryId; // 商品分类名称
	private Byte warehouseType; // 存储方式
	private byte type; // 商品性质
	private String goodsName; // 商品名称
	private Byte getWay; // 配送方式
	private Byte status; // 商品状态
	private Byte dataRoleType;// 数据权限类型 0全部 1部分 2自己
	private String dataRoleScope; // 数据权限adminId

	public GoodsMapperBean() {
		super();
	}

	public GoodsMapperBean(Integer pages, Integer rows, Integer startRows, Integer categoryId, Byte warehouseType,
			byte type, String goodsName, Byte getWay, Byte status, Byte dataRoleType, String dataRoleScope) {
		super();
		this.pages = pages;
		this.rows = rows;
		this.startRows = startRows;
		this.categoryId = categoryId;
		this.warehouseType = warehouseType;
		this.type = type;
		this.goodsName = goodsName;
		this.getWay = getWay;
		this.status = status;
		this.dataRoleScope = dataRoleScope;
		this.dataRoleType = dataRoleType;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getStartRows() {
		return startRows;
	}

	public void setStartRows(Integer startRows) {
		this.startRows = startRows;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Byte getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(Byte warehouseType) {
		this.warehouseType = warehouseType;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Byte getGetWay() {
		return getWay;
	}

	public void setGetWay(Byte getWay) {
		this.getWay = getWay;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getDataRoleType() {
		return dataRoleType;
	}

	public void setDataRoleType(Byte dataRoleType) {
		this.dataRoleType = dataRoleType;
	}

	public String getDataRoleScope() {
		return dataRoleScope;
	}

	public void setDataRoleScope(String dataRoleScope) {
		this.dataRoleScope = dataRoleScope;
	}

}
