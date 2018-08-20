package com.maomao.ssm.pojo.search;

import java.io.Serializable;
import java.util.List;

import com.alibaba.dubbo.common.status.StatusChecker;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.constant.StatusConst;

/**
 * @author:wzy
 * @descrption:
 * @version:
 */

public class SearchDetailBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer categoryId;
	private String name;
	private List<GoodsServiceDetails> goodsService;
	private Long priceSales;
	private Integer sales;
	private Integer stock;
	private String imgs;
	private Long rebate;
	private Long deposit;

	public Long getRebate() {
		return rebate;
	}

	public void setRebate(Long rebate) {
		this.rebate = rebate;
	}

	public SearchDetailBean() {

	}

	public SearchDetailBean(GoodsWithBLOBs goods, List<GoodsServiceDetails> goodsService) {
		this.setId(goods.getId());
		this.setCategoryId(goods.getCategoryId());
		this.setImgs(goods.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
		this.setName(goods.getName());
		this.setPriceSales(goods.getMinPrice());
		this.setSales(goods.getSales() + goods.getSalesSham());// 销量
		this.setStock(goods.getStock());// 库存量
		this.setGoodsService(goodsService);
		this.rebate = goods.getRebate();
		this.deposit = goods.getDeposit();
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GoodsServiceDetails> getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(List<GoodsServiceDetails> goodsService) {
		this.goodsService = goodsService;
	}

	public Long getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Long priceSales) {
		this.priceSales = priceSales;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Long getDeposit() {
		return deposit;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}

}
