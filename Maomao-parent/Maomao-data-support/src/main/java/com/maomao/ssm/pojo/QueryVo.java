package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author Administrator
 *
 */
public class QueryVo implements Serializable{
	private Integer page;
	private Integer pageSize; //当前页条数
	private Integer startRows; //开始位置
	private String queryString;	//查询的条件
	public QueryVo() {
	}
	
	public QueryVo(Integer page, Integer pageSize, Integer startRows, String queryString) {
		this.page = page;
		this.pageSize = pageSize;
		this.startRows = startRows;
		this.queryString = queryString;
	}

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getStartRows() {
		return startRows;
	}
	public void setStartRows(Integer startRows) {
		this.startRows = startRows;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

}
