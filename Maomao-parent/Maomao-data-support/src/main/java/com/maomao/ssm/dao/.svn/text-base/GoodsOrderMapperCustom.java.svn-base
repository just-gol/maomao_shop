package com.maomao.ssm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.maomao.ssm.bean.GoodsOrderCustom;
import com.maomao.ssm.bean.GoodsOrderExample;

public interface GoodsOrderMapperCustom {

	public List<GoodsOrderCustom> selectListByUserId(Integer userId);

	public GoodsOrderCustom selectByOrderId(Integer orderId);

	public List<GoodsOrderCustom> selectListByExample(GoodsOrderExample goodsOrderExample);

	public List<GoodsOrderCustom> selectOverstockListByExample(GoodsOrderExample goodsOrderExample);

	public List<GoodsOrderCustom> selectListByAdminAndMonth(Integer adminId, String queryTime);

	public GoodsOrderCustom selectByCode(@Param("code") String code, @Param("status") Byte status);

	public List<GoodsOrderCustom> selectListByDailyDetail(@Param("queryString") String queryString,
			@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("status") Byte refundStatus);

	public GoodsOrderCustom selectSumByExample(GoodsOrderExample goodsOrderExample);

	public List<GoodsOrderCustom> selectListOverstock(@Param("queryString") String queryString,
			@Param("type") Byte type, @Param("scope") String scope, @Param("category") Integer category);

	public List<GoodsOrderCustom> selectListByGoodsIdAndStatus(@Param("bizId") Integer bizId,
			@Param("category") Integer category, @Param("status") Byte status);

}
