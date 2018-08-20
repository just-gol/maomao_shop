package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable {
    private Integer id;

    private Long itemId;

    private String name;

    private Long pricePurchase;

    private Long priceSales;

    private Integer categoryId;

    private Integer stock;

    private Integer stockSham;

    private Integer sales;

    private Integer salesSham;

    private Long reword;

    private Long rebate;

    /**
     * 0下架 1申请中 2上架  3申请驳回 -1删除
     */
    private Byte status;

    private Date crateTime;

    private Date modifiedTime;

    private Integer brandId;

    private String serviceId;

    private Byte payType;

    private Byte getWay;

    private Integer checkAdmin;

    private Date checkTime;

    private String checkReason;

    private Byte type;

    private Integer adminId;

    private Date limitTime;

    private Long minPrice;

    private Long maxPrice;

    private Long deposit;

    private Integer sort;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getSalesSham() {
        return salesSham;
    }

    public void setSalesSham(Integer salesSham) {
        this.salesSham = salesSham;
    }

    public Long getReword() {
        return reword;
    }

    public void setReword(Long reword) {
        this.reword = reword;
    }

    public Long getRebate() {
        return rebate;
    }

    public void setRebate(Long rebate) {
        this.rebate = rebate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
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

    public Integer getCheckAdmin() {
        return checkAdmin;
    }

    public void setCheckAdmin(Integer checkAdmin) {
        this.checkAdmin = checkAdmin;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason == null ? null : checkReason.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Date getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Date limitTime) {
        this.limitTime = limitTime;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemId=").append(itemId);
        sb.append(", name=").append(name);
        sb.append(", pricePurchase=").append(pricePurchase);
        sb.append(", priceSales=").append(priceSales);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", stock=").append(stock);
        sb.append(", stockSham=").append(stockSham);
        sb.append(", sales=").append(sales);
        sb.append(", salesSham=").append(salesSham);
        sb.append(", reword=").append(reword);
        sb.append(", rebate=").append(rebate);
        sb.append(", status=").append(status);
        sb.append(", crateTime=").append(crateTime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", brandId=").append(brandId);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", payType=").append(payType);
        sb.append(", getWay=").append(getWay);
        sb.append(", checkAdmin=").append(checkAdmin);
        sb.append(", checkTime=").append(checkTime);
        sb.append(", checkReason=").append(checkReason);
        sb.append(", type=").append(type);
        sb.append(", adminId=").append(adminId);
        sb.append(", limitTime=").append(limitTime);
        sb.append(", minPrice=").append(minPrice);
        sb.append(", maxPrice=").append(maxPrice);
        sb.append(", deposit=").append(deposit);
        sb.append(", sort=").append(sort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}