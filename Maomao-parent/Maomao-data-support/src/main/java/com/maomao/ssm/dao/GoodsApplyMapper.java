package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsApply;
import com.maomao.ssm.bean.GoodsApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsApplyMapper {
    int countByExample(GoodsApplyExample example);

    int deleteByExample(GoodsApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsApply record);

    int insertSelective(GoodsApply record);

    List<GoodsApply> selectByExample(GoodsApplyExample example);

    GoodsApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsApply record, @Param("example") GoodsApplyExample example);

    int updateByExample(@Param("record") GoodsApply record, @Param("example") GoodsApplyExample example);

    int updateByPrimaryKeySelective(GoodsApply record);

    int updateByPrimaryKey(GoodsApply record);
}