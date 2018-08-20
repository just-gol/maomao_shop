package com.maomao.ssm.dao;

import com.maomao.ssm.bean.DailyDetail;
import com.maomao.ssm.bean.DailyDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyDetailMapper {
    int countByExample(DailyDetailExample example);

    int deleteByExample(DailyDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DailyDetail record);

    int insertSelective(DailyDetail record);

    List<DailyDetail> selectByExample(DailyDetailExample example);

    DailyDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DailyDetail record, @Param("example") DailyDetailExample example);

    int updateByExample(@Param("record") DailyDetail record, @Param("example") DailyDetailExample example);

    int updateByPrimaryKeySelective(DailyDetail record);

    int updateByPrimaryKey(DailyDetail record);
}