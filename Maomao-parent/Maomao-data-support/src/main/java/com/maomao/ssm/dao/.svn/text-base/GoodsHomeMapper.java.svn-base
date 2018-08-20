package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsHome;
import com.maomao.ssm.bean.GoodsHomeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsHomeMapper {
    int countByExample(GoodsHomeExample example);

    int deleteByExample(GoodsHomeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsHome record);

    int insertSelective(GoodsHome record);

    List<GoodsHome> selectByExample(GoodsHomeExample example);

    GoodsHome selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsHome record, @Param("example") GoodsHomeExample example);

    int updateByExample(@Param("record") GoodsHome record, @Param("example") GoodsHomeExample example);

    int updateByPrimaryKeySelective(GoodsHome record);

    int updateByPrimaryKey(GoodsHome record);
}