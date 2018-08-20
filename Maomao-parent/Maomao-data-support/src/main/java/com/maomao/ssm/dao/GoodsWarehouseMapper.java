package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsWarehouse;
import com.maomao.ssm.bean.GoodsWarehouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsWarehouseMapper {
    int countByExample(GoodsWarehouseExample example);

    int deleteByExample(GoodsWarehouseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsWarehouse record);

    int insertSelective(GoodsWarehouse record);

    List<GoodsWarehouse> selectByExample(GoodsWarehouseExample example);

    GoodsWarehouse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsWarehouse record, @Param("example") GoodsWarehouseExample example);

    int updateByExample(@Param("record") GoodsWarehouse record, @Param("example") GoodsWarehouseExample example);

    int updateByPrimaryKeySelective(GoodsWarehouse record);

    int updateByPrimaryKey(GoodsWarehouse record);
}