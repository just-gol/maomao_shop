package com.maomao.ssm.pojo.search;

import java.io.Serializable;
import java.util.List;

import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;

/**
 * @author:wzy
 * @descrption:
 * @version:
 */

public class SubscriptionGoodsDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String img;
	private String name;
	private Long priceSales;
	private List<GoodsServiceDetails> goodsServiceList;
	private String param;
	private String detail;
	private Long bonus_avg;// 单笔分红
	private Long sales_award;// 奖励金
	private Long price_avg;// 认筹单价
	private Integer total;// 认筹份数
	private Integer num;// 已认筹份数
	private Long deposit;
	private String goodsAddress;
	private String goodsMobile;
	private String goodsUsername;
	private Byte getWay;
	private Byte payType;
	private Integer stock;
	private Byte status;
	private Long sellEndTime;
	private Integer money;
	public SubscriptionGoodsDetails() {

	}

	public SubscriptionGoodsDetails(GoodsSubscriptionWithBLOBs goodsSub, List<GoodsServiceDetails> goodsServiceList,
			GoodsAddress goodsAddress) {
		this.setBonus_avg(goodsSub.getBonusAvg());
		this.setDetail(goodsSub.getDetail());
		this.setImg(goodsSub.getImgs());
		this.setName(goodsSub.getName());
		this.setNum(goodsSub.getNumSubscription());
		this.setParam(goodsSub.getParam());
		this.setPrice_avg(goodsSub.getPriceSubscriptionAvg());
		this.setPriceSales(goodsSub.getPriceSales());
		this.setSales_award(goodsSub.getSalesAward());
		this.setTotal(goodsSub.getNumSubscriptionTotal());
		this.setGoodsServiceList(goodsServiceList);
		this.deposit = goodsSub.getDeposit();
		if (goodsAddress != null) {
			this.goodsAddress = goodsAddress.getProvince() + goodsAddress.getCity() + goodsAddress.getArea()
					+ goodsAddress.getAddress();
			this.goodsUsername = goodsAddress.getName();
			this.goodsMobile = goodsAddress.getMobile();
		}
		this.payType = goodsSub.getPayType();
		this.getWay = goodsSub.getGetWay();
		this.stock = goodsSub.getStock();
		this.sellEndTime=goodsSub.getSalesEndTime().getTime();
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Long getSellEndTime() {
		return sellEndTime;
	}

	public void setSellEndTime(Long sellEndTime) {
		this.sellEndTime = sellEndTime;
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

	public Long getPrice_avg() {
		return price_avg;
	}

	public void setPrice_avg(Long price_avg) {
		this.price_avg = price_avg;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Long getDeposit() {
		return deposit;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
