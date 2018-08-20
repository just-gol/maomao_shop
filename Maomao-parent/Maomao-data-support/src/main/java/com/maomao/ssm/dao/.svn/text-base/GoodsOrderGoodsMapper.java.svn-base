package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsOrderGoods;
import com.maomao.ssm.bean.GoodsOrderGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsOrderGoodsMapper {
    int countByExample(GoodsOrderGoodsExample example);

    int deleteByExample(GoodsOrderGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsOrderGoods record);

    int insertSelective(GoodsOrderGoods record);

    List<GoodsOrderGoods> selectByExample(GoodsOrderGoodsExample example);

    GoodsOrderGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsOrderGoods record, @Param("example") GoodsOrderGoodsExample example);

    int updateByExample(@Param("record") GoodsOrderGoods record, @Param("example") GoodsOrderGoodsExample example);

    int updateByPrimaryKeySelective(GoodsOrderGoods record);

    int updateByPrimaryKey(GoodsOrderGoods record);
}