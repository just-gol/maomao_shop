package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.List;
/**
 * 分页Bean
 * @author Administrator
 *
 */
public class PageBean implements Serializable{
	private long total; //总记录数
	private List<?> rows; //当前页数据
	public PageBean() {
	}
	
	public PageBean(long total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
