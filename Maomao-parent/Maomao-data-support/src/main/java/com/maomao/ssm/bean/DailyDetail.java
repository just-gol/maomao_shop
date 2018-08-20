package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class DailyDetail implements Serializable {
    private Integer id;

    private Long orderPayMoney;

    private Long orderRefundMoney;

    private Long couponGetMoney;

    private Long couponUseMoney;

    private Long couponExpireMoney;

    private Integer orderPayNum;

    private Integer couponGetNum;

    private Integer couponUseNum;

    private Integer couponExpireNum;

    private Date time;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOrderPayMoney() {
        return orderPayMoney;
    }

    public void setOrderPayMoney(Long orderPayMoney) {
        this.orderPayMoney = orderPayMoney;
    }

    public Long getOrderRefundMoney() {
        return orderRefundMoney;
    }

    public void setOrderRefundMoney(Long orderRefundMoney) {
        this.orderRefundMoney = orderRefundMoney;
    }

    public Long getCouponGetMoney() {
        return couponGetMoney;
    }

    public void setCouponGetMoney(Long couponGetMoney) {
        this.couponGetMoney = couponGetMoney;
    }

    public Long getCouponUseMoney() {
        return couponUseMoney;
    }

    public void setCouponUseMoney(Long couponUseMoney) {
        this.couponUseMoney = couponUseMoney;
    }

    public Long getCouponExpireMoney() {
        return couponExpireMoney;
    }

    public void setCouponExpireMoney(Long couponExpireMoney) {
        this.couponExpireMoney = couponExpireMoney;
    }

    public Integer getOrderPayNum() {
        return orderPayNum;
    }

    public void setOrderPayNum(Integer orderPayNum) {
        this.orderPayNum = orderPayNum;
    }

    public Integer getCouponGetNum() {
        return couponGetNum;
    }

    public void setCouponGetNum(Integer couponGetNum) {
        this.couponGetNum = couponGetNum;
    }

    public Integer getCouponUseNum() {
        return couponUseNum;
    }

    public void setCouponUseNum(Integer couponUseNum) {
        this.couponUseNum = couponUseNum;
    }

    public Integer getCouponExpireNum() {
        return couponExpireNum;
    }

    public void setCouponExpireNum(Integer couponExpireNum) {
        this.couponExpireNum = couponExpireNum;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderPayMoney=").append(orderPayMoney);
        sb.append(", orderRefundMoney=").append(orderRefundMoney);
        sb.append(", couponGetMoney=").append(couponGetMoney);
        sb.append(", couponUseMoney=").append(couponUseMoney);
        sb.append(", couponExpireMoney=").append(couponExpireMoney);
        sb.append(", orderPayNum=").append(orderPayNum);
        sb.append(", couponGetNum=").append(couponGetNum);
        sb.append(", couponUseNum=").append(couponUseNum);
        sb.append(", couponExpireNum=").append(couponExpireNum);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}