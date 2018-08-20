package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsOverstockUser;
import com.maomao.ssm.bean.GoodsOverstockUserExample;
import com.maomao.ssm.bean.GoodsOverstockUserWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsOverstockUserMapper {
    int countByExample(GoodsOverstockUserExample example);

    int deleteByExample(GoodsOverstockUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsOverstockUserWithBLOBs record);

    int insertSelective(GoodsOverstockUserWithBLOBs record);

    List<GoodsOverstockUserWithBLOBs> selectByExampleWithBLOBs(GoodsOverstockUserExample example);

    List<GoodsOverstockUser> selectByExample(GoodsOverstockUserExample example);

    GoodsOverstockUserWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsOverstockUserWithBLOBs record, @Param("example") GoodsOverstockUserExample example);

    int updateByExampleWithBLOBs(@Param("record") GoodsOverstockUserWithBLOBs record, @Param("example") GoodsOverstockUserExample example);

    int updateByExample(@Param("record") GoodsOverstockUser record, @Param("example") GoodsOverstockUserExample example);

    int updateByPrimaryKeySelective(GoodsOverstockUserWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GoodsOverstockUserWithBLOBs record);

    int updateByPrimaryKey(GoodsOverstockUser record);
}