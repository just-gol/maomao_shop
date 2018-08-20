package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsAddressMapper {
    int countByExample(GoodsAddressExample example);

    int deleteByExample(GoodsAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAddress record);

    int insertSelective(GoodsAddress record);

    List<GoodsAddress> selectByExample(GoodsAddressExample example);

    GoodsAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsAddress record, @Param("example") GoodsAddressExample example);

    int updateByExample(@Param("record") GoodsAddress record, @Param("example") GoodsAddressExample example);

    int updateByPrimaryKeySelective(GoodsAddress record);

    int updateByPrimaryKey(GoodsAddress record);
}