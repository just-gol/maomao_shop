package com.maomao.ssm.pojo.search;

import java.io.Serializable;
import java.util.List;

import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsOverstockUserWithBLOBs;
import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.bean.GoodsWithBLOBs;

/**
 * @author:wzy
 * @descrption:
 * @version:
 */

public class GoodsDetails implements Serializable {
	private String img;
	private String name;
	private Long priceSales;
	private Integer sales;// 月销量
	private Integer stock;// 库存量
	private List<GoodsServiceDetails> goodsServiceList;
	private String param;// 参数json
	private String detail;// 商品详情
	private Long bonus_avg;// 单笔分红
	private Long sales_award;// 售出奖励金
	private Integer flag;// 1:已收藏,0:未收藏
	private Integer addShop;// 1:已添加,0未添加

	private Long minPrice;
	private Long maxPrice;
	private Long deposit;
	private List<GoodsDetailsSku> sku;
	private List<GoodsDetailsSkuDetails> skuDetail;
	private String goodsAddress;
	private String goodsMobile;
	private String goodsUsername;
	private Byte getWay;
	private Byte payType;

	private Integer buyNum;

	public GoodsDetails() {

	}

	public Integer getAddShop() {
		return addShop;
	}

	public void setAddShop(Integer addShop) {
		this.addShop = addShop;
	}

	public GoodsDetails(GoodsWithBLOBs goodsWithBLOBs, List<GoodsServiceDetails> goodsServiceList) {
		this.setDetail(goodsWithBLOBs.getDetail());
		this.setImg(goodsWithBLOBs.getImgs());
		this.setName(goodsWithBLOBs.getName());
		this.setParam(goodsWithBLOBs.getParam());
		this.setPriceSales(goodsWithBLOBs.getPriceSales());
		this.bonus_avg = goodsWithBLOBs.getRebate();
		this.sales_award = goodsWithBLOBs.getReword();
		this.setSales(goodsWithBLOBs.getSales() + goodsWithBLOBs.getSalesSham());
		this.setStock(goodsWithBLOBs.getStock());
		this.setGoodsServiceList(goodsServiceList);
		this.payType = goodsWithBLOBs.getPayType();
		this.getWay = goodsWithBLOBs.getGetWay();
	}

	public GoodsDetails(GoodsOverstockWithBLOBs goodsOverstock, List<GoodsServiceDetails> goodsServiceList) {
		this.setDetail(goodsOverstock.getDetail());
		this.setImg(goodsOverstock.getImgs());
		this.setName(goodsOverstock.getName());
		this.setParam(goodsOverstock.getParam());
		this.setPriceSales(goodsOverstock.getPrice());
		this.setSales(goodsOverstock.getSales());
		this.setStock(goodsOverstock.getStock());
		this.setGoodsServiceList(goodsServiceList);
		this.buyNum = goodsOverstock.getBuyNum();
	}

	public GoodsDetails(GoodsOverstockUserWithBLOBs goodsOverstock, List<GoodsServiceDetails> goodsServiceList) {
		this.setDetail(goodsOverstock.getDetail());
		this.setImg(goodsOverstock.getImgs());
		this.setName(goodsOverstock.getName());
		this.setParam(goodsOverstock.getParam());
		this.setPriceSales(goodsOverstock.getPrice());
		this.setStock(goodsOverstock.getStock());
		this.setGoodsServiceList(goodsServiceList);
	}

	public GoodsDetails(GoodsWithBLOBs goodsWithBLOBs, List<GoodsServiceDetails> goodsServiceList,
			List<GoodsDetailsSku> sku, List<GoodsDetailsSkuDetails> skuDetail, GoodsAddress goodsAddress) {
		this.setDetail(goodsWithBLOBs.getDetail());
		this.setImg(goodsWithBLOBs.getImgs());
		this.setName(goodsWithBLOBs.getName());
		this.setParam(goodsWithBLOBs.getParam());
		this.setPriceSales(goodsWithBLOBs.getPriceSales());
		this.bonus_avg = goodsWithBLOBs.getRebate();
		this.sales_award = goodsWithBLOBs.getReword();
		this.setSales(goodsWithBLOBs.getSales() + goodsWithBLOBs.getSalesSham());
		this.setStock(goodsWithBLOBs.getStock());
		this.setGoodsServiceList(goodsServiceList);
		this.minPrice = goodsWithBLOBs.getMinPrice();
		this.maxPrice = goodsWithBLOBs.getMaxPrice();
		this.deposit = goodsWithBLOBs.getDeposit();
		this.sku = sku;
		this.skuDetail = skuDetail;
		if (goodsAddress != null) {
			this.goodsAddress = goodsAddress.getProvince() + goodsAddress.getCity() + goodsAddress.getArea()
					+ goodsAddress.getAddress();
			this.goodsUsername = goodsAddress.getName();
			this.goodsMobile = goodsAddress.getMobile();
		}
		this.payType = goodsWithBLOBs.getPayType();
		this.getWay = goodsWithBLOBs.getGetWay();
	}

	public Long getBonus_avg() {
		return bonus_avg;
	}

	public void setBonus_avg(Long bonus_avg) {
		this.bonus_avg = bonus_avg;
	}

	public Long getSales_award() {
		return sales_award;
	}

	public void setSales_award(Long sales_award) {
		this.sales_award = sales_award;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public List<GoodsServiceDetails> getGoodsServiceList() {
		return goodsServiceList;
	}

	public void setGoodsServiceList(List<GoodsServiceDetails> goodsServiceList) {
		this.goodsServiceList = goodsServiceList;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Long minPrice) {
		this.minPrice = minPrice;
	}

	public Long getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Long maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Long getDeposit() {
		return deposit;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}

	public List<GoodsDetailsSku> getSku() {
		return sku;
	}

	public void setSku(List<GoodsDetailsSku> sku) {
		this.sku = sku;
	}

	public List<GoodsDetailsSkuDetails> getSkuDetail() {
		return skuDetail;
	}

	public void setSkuDetail(List<GoodsDetailsSkuDetails> skuDetail) {
		this.skuDetail = skuDetail;
	}

	public String getGoodsAddress() {
		return goodsAddress;
	}

	public void setGoodsAddress(String goodsAddress) {
		this.goodsAddress = goodsAddress;
	}

	public String getGoodsMobile() {
		return goodsMobile;
	}

	public void setGoodsMobile(String goodsMobile) {
		this.goodsMobile = goodsMobile;
	}

	public String getGoodsUsername() {
		return goodsUsername;
	}

	public void setGoodsUsername(String goodsUsername) {
		this.goodsUsername = goodsUsername;
	}

	public Byte getGetWay() {
		return getWay;
	}

	public void setGetWay(Byte getWay) {
		this.getWay = getWay;
	}

	public Byte getPayType() {
		return payType;
	}

	public void setPayType(Byte payType) {
		this.payType = payType;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

}
