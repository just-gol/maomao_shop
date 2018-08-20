package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class UserWithdrawalsHistoryCustom implements Serializable {
	private Integer userId;
	private String name;
	private String mobile;
	private Long money;
	private Integer num;
	private Date lastTime;
	private Long sum;
	private Long accumulativeMoney;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
	}

	public Long getAccumulativeMoney() {
		return accumulativeMoney;
	}

	public void setAccumulativeMoney(Long accumulativeMoney) {
		this.accumulativeMoney = accumulativeMoney;
	}

}
