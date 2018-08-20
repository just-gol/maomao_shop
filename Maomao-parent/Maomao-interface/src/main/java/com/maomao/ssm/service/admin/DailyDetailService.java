package com.maomao.ssm.service.admin;

import com.maomao.ssm.pojo.JsonData;

public interface DailyDetailService {

	public JsonData getDailyDetailList(Integer pages, Integer rows, Long startTime, Long endTime);

	public JsonData getDailyDetailDetail(Integer id);

	public JsonData getDailyDetailOrderList(Integer pages, Integer rows, String queryString, Integer id);

}
