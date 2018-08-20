package com.maomao.ssm.service.impl.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.DailyDetail;
import com.maomao.ssm.bean.DailyDetailExample;
import com.maomao.ssm.bean.GoodsOrderCustom;
import com.maomao.ssm.constant.OrderConst;
import com.maomao.ssm.dao.DailyDetailMapper;
import com.maomao.ssm.dao.GoodsOrderMapperCustom;
import com.maomao.ssm.pojo.DailyDetailList;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.DailyDetailService;
import com.maomao.ssm.utils.DateUtils;

@Service
public class DailyDetailServiceImpl implements DailyDetailService {

	@Autowired
	private DailyDetailMapper dailyDetailMapper;
	@Autowired
	private GoodsOrderMapperCustom goodsOrderMapperCustom;

	/**
	 * 获取日结记录列表
	 * 
	 * @param pages
	 * @param rows
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@Override
	public JsonData getDailyDetailList(Integer pages, Integer rows, Long startTime, Long endTime) {
		DailyDetailExample dailyDetailExample = new DailyDetailExample();
		com.maomao.ssm.bean.DailyDetailExample.Criteria dailyDetailCriteria = dailyDetailExample.createCriteria();
		if (startTime != null) {
			dailyDetailCriteria.andTimeGreaterThanOrEqualTo(new Date(startTime));
		}
		if (endTime != null) {
			dailyDetailCriteria.andTimeLessThanOrEqualTo(new Date(endTime));
		}
		dailyDetailExample.setOrderByClause("time DESC");
		// 查询
		PageHelper.startPage(pages, rows);
		List<DailyDetail> dailyDetails = dailyDetailMapper.selectByExample(dailyDetailExample);

		Long orderPayMoneyTotal = 0l, orderRefundMoneyTotal = 0l, couponGetMoneyTotal = 0l, couponUseMoneyTotal = 0l,
				couponExpireMoneyTotal = 0l;
		if (dailyDetails != null && dailyDetails.size() > 0) {
			for (DailyDetail dailyDetail : dailyDetails) {
				orderPayMoneyTotal += dailyDetail.getOrderPayMoney();
				orderRefundMoneyTotal += dailyDetail.getOrderRefundMoney();
				couponGetMoneyTotal += dailyDetail.getCouponGetMoney();
				couponUseMoneyTotal += dailyDetail.getCouponUseMoney();
				couponExpireMoneyTotal += dailyDetail.getCouponExpireMoney();
			}
		}
		// 设置返回值
		PageInfo<DailyDetail> pageInfo = new PageInfo<>(dailyDetails);
		PageBean pagebean = new PageBean();
		// 获取总条数
		pagebean.setTotal(pageInfo.getTotal());
		// 获取当前页数据
		pagebean.setRows(dailyDetails);

		DailyDetailList dailyDetailList = new DailyDetailList(pagebean, orderPayMoneyTotal, orderRefundMoneyTotal,
				couponGetMoneyTotal, couponUseMoneyTotal, couponExpireMoneyTotal);

		return JsonData.setSuccessMessage(dailyDetailList);
	}

	/**
	 * 获取日结记录详情
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public JsonData getDailyDetailDetail(Integer id) {
		if (id == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		DailyDetail dailyDetail = dailyDetailMapper.selectByPrimaryKey(id);
		if (dailyDetail == null) {
			return JsonData.setErrorMessage("金额明细记录不存在");
		}
		return JsonData.setSuccessMessage(dailyDetail);
	}

	/**
	 * 查询日结记录明细
	 * 
	 * @param pages
	 * @param rows
	 * @param queryString
	 * @param id
	 * @return
	 */
	@Override
	public JsonData getDailyDetailOrderList(Integer pages, Integer rows, String queryString, Integer id) {
		if (id == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		// 查询日期
		DailyDetail dailyDetail = dailyDetailMapper.selectByPrimaryKey(id);
		if (dailyDetail == null) {
			return JsonData.setErrorMessage("金额明细记录不存在");
		}
		Date date = dailyDetail.getTime();
		// 查询订单
		Date startTime = DateUtils.getFirstSecond(date);
		Date endTime = DateUtils.getLastSecond(date);
		PageHelper.startPage(pages, rows);
		List<GoodsOrderCustom> goodsOrderCustoms = goodsOrderMapperCustom.selectListByDailyDetail(queryString,
				startTime, endTime, OrderConst.ORDER_STATUS_REFUNDED);
		// 设置返回值
		PageInfo<GoodsOrderCustom> pageInfo = new PageInfo<>(goodsOrderCustoms);
		PageBean pagebean = new PageBean();
		// 获取总条数
		pagebean.setTotal(pageInfo.getTotal());
		// 获取当前页数据
		pagebean.setRows(goodsOrderCustoms);

		return JsonData.setSuccessMessage(pagebean);
	}

}
