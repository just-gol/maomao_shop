package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsUser;
import com.maomao.ssm.bean.GoodsUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsUserMapper {
    int countByExample(GoodsUserExample example);

    int deleteByExample(GoodsUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsUser record);

    int insertSelective(GoodsUser record);

    List<GoodsUser> selectByExample(GoodsUserExample example);

    GoodsUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsUser record, @Param("example") GoodsUserExample example);

    int updateByExample(@Param("record") GoodsUser record, @Param("example") GoodsUserExample example);

    int updateByPrimaryKeySelective(GoodsUser record);

    int updateByPrimaryKey(GoodsUser record);
}