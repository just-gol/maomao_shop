package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsSkuKey;
import com.maomao.ssm.bean.GoodsSkuKeyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsSkuKeyMapper {
    int countByExample(GoodsSkuKeyExample example);

    int deleteByExample(GoodsSkuKeyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSkuKey record);

    int insertSelective(GoodsSkuKey record);

    List<GoodsSkuKey> selectByExample(GoodsSkuKeyExample example);

    GoodsSkuKey selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsSkuKey record, @Param("example") GoodsSkuKeyExample example);

    int updateByExample(@Param("record") GoodsSkuKey record, @Param("example") GoodsSkuKeyExample example);

    int updateByPrimaryKeySelective(GoodsSkuKey record);

    int updateByPrimaryKey(GoodsSkuKey record);
}