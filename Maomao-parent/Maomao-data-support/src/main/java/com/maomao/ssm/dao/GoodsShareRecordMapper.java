package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsShareRecord;
import com.maomao.ssm.bean.GoodsShareRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsShareRecordMapper {
    int countByExample(GoodsShareRecordExample example);

    int deleteByExample(GoodsShareRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsShareRecord record);

    int insertSelective(GoodsShareRecord record);

    List<GoodsShareRecord> selectByExample(GoodsShareRecordExample example);

    GoodsShareRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsShareRecord record, @Param("example") GoodsShareRecordExample example);

    int updateByExample(@Param("record") GoodsShareRecord record, @Param("example") GoodsShareRecordExample example);

    int updateByPrimaryKeySelective(GoodsShareRecord record);

    int updateByPrimaryKey(GoodsShareRecord record);
}