package com.maomao.ssm.service.admin;

import java.util.Date;

import com.maomao.ssm.pojo.JsonData;

public interface MonthMoneyService {

	public JsonData getMonthDetailList(Integer pages, Integer rows, String queryString);

	public JsonData getHistoryMonthDetailList(Integer pages, Integer rows, Integer adminId);

	public JsonData getMonthDetail(Integer id);

	public JsonData updateByUploadImgs(Integer id, String loanImg, String moneyImg, Long uploadTime);

	public JsonData getSalesDetailList(Integer id);

}
