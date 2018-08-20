package com.maomao.ssm.pojo.v2;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/** 
* @author:wzy
* @descrption:我的合卖
* @version:
* @date:2018年7月18日
*/

public class Hemai implements Serializable{
	private static final long serialVersionUID = 1L;
	private long money;
	private long totalMoney;
	List<Map<String, Object>> onSaleList;
	List<Map<String, Object>> hemaiList;
	public long getMoney() {
		return money;
	}
	public void setMoney(long money) {
		this.money = money;
	}
	public long getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(long totalMoney) {
		this.totalMoney = totalMoney;
	}
	public List<Map<String, Object>> getOnSaleList() {
		return onSaleList;
	}
	public void setOnSaleList(List<Map<String, Object>> onSaleList) {
		this.onSaleList = onSaleList;
	}
	public List<Map<String, Object>> getHemaiList() {
		return hemaiList;
	}
	public void setHemaiList(List<Map<String, Object>> hemaiList) {
		this.hemaiList = hemaiList;
	}
	

}





























