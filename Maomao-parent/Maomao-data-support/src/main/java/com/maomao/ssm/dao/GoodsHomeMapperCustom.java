package com.maomao.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.maomao.ssm.bean.GoodsHome;
import com.maomao.ssm.bean.GoodsHomeCustom;

public interface GoodsHomeMapperCustom {
	GoodsHome getGoodsHomeSort();

	GoodsHome getGoodsHomeMinSort();

	List<Integer> getGoodsHomeList();

	Integer getGoodsHomepageCategorySort();

	List<GoodsHomeCustom> getGoodsHomeCustomWithGoods(@Param("homepageCategoryId") Integer homepageCategoryId,
			@Param("category") Integer category);

	List<GoodsHomeCustom> getGoodsHomeCustomWithGoodsOverstock(@Param("homepageCategoryId") Integer homepageCategoryId,
			@Param("category") Integer category);

}