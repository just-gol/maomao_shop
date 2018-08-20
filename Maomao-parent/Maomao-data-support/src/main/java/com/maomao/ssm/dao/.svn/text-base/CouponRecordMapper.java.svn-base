package com.maomao.ssm.dao;

import com.maomao.ssm.bean.CouponRecord;
import com.maomao.ssm.bean.CouponRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponRecordMapper {
    int countByExample(CouponRecordExample example);

    int deleteByExample(CouponRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponRecord record);

    int insertSelective(CouponRecord record);

    List<CouponRecord> selectByExample(CouponRecordExample example);

    CouponRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponRecord record, @Param("example") CouponRecordExample example);

    int updateByExample(@Param("record") CouponRecord record, @Param("example") CouponRecordExample example);

    int updateByPrimaryKeySelective(CouponRecord record);

    int updateByPrimaryKey(CouponRecord record);
}