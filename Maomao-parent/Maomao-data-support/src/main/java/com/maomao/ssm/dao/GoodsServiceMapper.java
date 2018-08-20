package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsService;
import com.maomao.ssm.bean.GoodsServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsServiceMapper {
    int countByExample(GoodsServiceExample example);

    int deleteByExample(GoodsServiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsService record);

    int insertSelective(GoodsService record);

    List<GoodsService> selectByExampleWithBLOBs(GoodsServiceExample example);

    List<GoodsService> selectByExample(GoodsServiceExample example);

    GoodsService selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsService record, @Param("example") GoodsServiceExample example);

    int updateByExampleWithBLOBs(@Param("record") GoodsService record, @Param("example") GoodsServiceExample example);

    int updateByExample(@Param("record") GoodsService record, @Param("example") GoodsServiceExample example);

    int updateByPrimaryKeySelective(GoodsService record);

    int updateByPrimaryKeyWithBLOBs(GoodsService record);

    int updateByPrimaryKey(GoodsService record);
}