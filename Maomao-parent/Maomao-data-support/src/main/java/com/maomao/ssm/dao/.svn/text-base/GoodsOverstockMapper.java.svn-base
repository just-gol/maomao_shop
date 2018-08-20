package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsOverstock;
import com.maomao.ssm.bean.GoodsOverstockExample;
import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsOverstockMapper {
    int countByExample(GoodsOverstockExample example);

    int deleteByExample(GoodsOverstockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsOverstockWithBLOBs record);

    int insertSelective(GoodsOverstockWithBLOBs record);

    List<GoodsOverstockWithBLOBs> selectByExampleWithBLOBs(GoodsOverstockExample example);

    List<GoodsOverstock> selectByExample(GoodsOverstockExample example);

    GoodsOverstockWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsOverstockWithBLOBs record, @Param("example") GoodsOverstockExample example);

    int updateByExampleWithBLOBs(@Param("record") GoodsOverstockWithBLOBs record, @Param("example") GoodsOverstockExample example);

    int updateByExample(@Param("record") GoodsOverstock record, @Param("example") GoodsOverstockExample example);

    int updateByPrimaryKeySelective(GoodsOverstockWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GoodsOverstockWithBLOBs record);

    int updateByPrimaryKey(GoodsOverstock record);
}