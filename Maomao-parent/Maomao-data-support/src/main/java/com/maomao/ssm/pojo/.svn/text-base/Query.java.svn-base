package com.maomao.ssm.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 分页bean条件
 * @author Administrator
 *
 */
public class Query implements Serializable{
	private Integer startRows;
	private Integer rows;
	private String queryString;
	private Date startTime; //开始时间
	private Date endTime;//结束时间
	
	public Query() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Query(Integer startRows, Integer rows, String queryString) {
		super();
		this.startRows = startRows;
		this.rows = rows;
		this.queryString = queryString;
	}

	public Query(Date startTime, Date endTime, String queryString) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.queryString = queryString;
	}
	
	public Integer getStartRows() {
		return startRows;
	}
	public void setStartRows(Integer startRows) {
		this.startRows = startRows;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
}
