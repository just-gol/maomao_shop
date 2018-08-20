package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsSubscription;
import com.maomao.ssm.bean.GoodsSubscriptionExample;
import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsSubscriptionMapper {
    int countByExample(GoodsSubscriptionExample example);

    int deleteByExample(GoodsSubscriptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSubscriptionWithBLOBs record);

    int insertSelective(GoodsSubscriptionWithBLOBs record);

    List<GoodsSubscriptionWithBLOBs> selectByExampleWithBLOBs(GoodsSubscriptionExample example);

    List<GoodsSubscription> selectByExample(GoodsSubscriptionExample example);

    GoodsSubscriptionWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsSubscriptionWithBLOBs record, @Param("example") GoodsSubscriptionExample example);

    int updateByExampleWithBLOBs(@Param("record") GoodsSubscriptionWithBLOBs record, @Param("example") GoodsSubscriptionExample example);

    int updateByExample(@Param("record") GoodsSubscription record, @Param("example") GoodsSubscriptionExample example);

    int updateByPrimaryKeySelective(GoodsSubscriptionWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GoodsSubscriptionWithBLOBs record);

    int updateByPrimaryKey(GoodsSubscription record);
}