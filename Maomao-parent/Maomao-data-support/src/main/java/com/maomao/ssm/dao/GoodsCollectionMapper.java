package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsCollection;
import com.maomao.ssm.bean.GoodsCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsCollectionMapper {
    int countByExample(GoodsCollectionExample example);

    int deleteByExample(GoodsCollectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsCollection record);

    int insertSelective(GoodsCollection record);

    List<GoodsCollection> selectByExample(GoodsCollectionExample example);

    GoodsCollection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsCollection record, @Param("example") GoodsCollectionExample example);

    int updateByExample(@Param("record") GoodsCollection record, @Param("example") GoodsCollectionExample example);

    int updateByPrimaryKeySelective(GoodsCollection record);

    int updateByPrimaryKey(GoodsCollection record);
}