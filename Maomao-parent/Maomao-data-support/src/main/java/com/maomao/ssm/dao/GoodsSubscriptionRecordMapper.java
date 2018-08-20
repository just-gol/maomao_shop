package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsSubscriptionRecord;
import com.maomao.ssm.bean.GoodsSubscriptionRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsSubscriptionRecordMapper {
    int countByExample(GoodsSubscriptionRecordExample example);

    int deleteByExample(GoodsSubscriptionRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSubscriptionRecord record);

    int insertSelective(GoodsSubscriptionRecord record);

    List<GoodsSubscriptionRecord> selectByExample(GoodsSubscriptionRecordExample example);

    GoodsSubscriptionRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsSubscriptionRecord record, @Param("example") GoodsSubscriptionRecordExample example);

    int updateByExample(@Param("record") GoodsSubscriptionRecord record, @Param("example") GoodsSubscriptionRecordExample example);

    int updateByPrimaryKeySelective(GoodsSubscriptionRecord record);

    int updateByPrimaryKey(GoodsSubscriptionRecord record);
}