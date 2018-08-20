package com.maomao.ssm.dao;

import java.util.List;

import com.maomao.ssm.bean.GoodsWithBLOBs;

public interface GoodsCollectionMapperCustom {
	public List<GoodsWithBLOBs> selectByUserId(Integer userId);
}
