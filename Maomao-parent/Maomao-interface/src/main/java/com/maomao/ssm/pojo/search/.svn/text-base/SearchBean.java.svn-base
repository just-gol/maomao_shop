package com.maomao.ssm.pojo.search;

import java.io.Serializable;
import java.util.List;

import com.maomao.ssm.bean.GoodsHomeCustom;
import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.constant.StatusConst;

/**
 * @author:wzy
 * @descrption:商品搜索结果bean
 * @version:
 */

public class SearchBean implements Serializable {
	private Integer id;
	private Long price;
	private Integer sales;
	private Long rebate;
	private String img;
	private String name;
	private Integer stock;
	private List<GoodsServiceDetails> goodsService;
	private Long deposit;
	private Integer category;
	private Integer sort;
	private String serviceId;

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public SearchBean() {

	}

	public SearchBean(GoodsHomeCustom goodsHomeCustom) {
		super();
		this.id = goodsHomeCustom.getId();
		this.price = goodsHomeCustom.getPrice();
		this.sales = goodsHomeCustom.getSales() + goodsHomeCustom.getSalesSham();
		this.rebate = goodsHomeCustom.getRebate();
		this.img = goodsHomeCustom.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0];
		this.name = goodsHomeCustom.getName();
		this.stock = goodsHomeCustom.getStock();
		this.deposit = goodsHomeCustom.getDeposit();
		this.category = goodsHomeCustom.getCategory();
		this.sort = goodsHomeCustom.getSort();
		this.serviceId = goodsHomeCustom.getServiceId();
	}

	public SearchBean(GoodsWithBLOBs goods, List<GoodsServiceDetails> goodsService) {
		this.setId(goods.getId());
		this.setPrice(goods.getMinPrice());
		this.setRebate(goods.getRebate());
		this.setSales(goods.getSales() + goods.getSalesSham());
		this.setImg(goods.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
		this.setName(goods.getName());
		this.setStock(goods.getStock());
		this.setGoodsService(goodsService);
		this.category = 1;
		this.deposit = goods.getDeposit();
	}

	public SearchBean(GoodsSubscriptionWithBLOBs goods, List<GoodsServiceDetails> goodsService) {
		this.setId(goods.getId());
		this.setPrice(goods.getPriceSales());
		this.setRebate(goods.getBonusAvg());
		this.setImg(goods.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
		this.setName(goods.getName());
		this.setStock(goods.getStock());
		this.setGoodsService(goodsService);
		this.category = 0;
		this.deposit = goods.getDeposit();
	}

	public SearchBean(GoodsOverstockWithBLOBs goods, List<GoodsServiceDetails> goodsService) {
		this.setId(goods.getId());
		this.setPrice(goods.getPrice());
		this.setRebate(goods.getRebate());
		this.setImg(goods.getImgs().split(StatusConst.IMG_SPLIT_STRING)[0]);
		this.setName(goods.getName());
		this.setStock(goods.getStock());
		this.setGoodsService(goodsService);
		this.category = 2;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<GoodsServiceDetails> getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(List<GoodsServiceDetails> goodsService) {
		this.goodsService = goodsService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Long getRebate() {
		return rebate;
	}

	public void setRebate(Long rebate) {
		this.rebate = rebate;
	}

	public Long getDeposit() {
		return deposit;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}
