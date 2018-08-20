package com.maomao.ssm.dao;

import com.maomao.ssm.bean.AdminMonthDetail;
import com.maomao.ssm.bean.AdminMonthDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMonthDetailMapper {
    int countByExample(AdminMonthDetailExample example);

    int deleteByExample(AdminMonthDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminMonthDetail record);

    int insertSelective(AdminMonthDetail record);

    List<AdminMonthDetail> selectByExample(AdminMonthDetailExample example);

    AdminMonthDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminMonthDetail record, @Param("example") AdminMonthDetailExample example);

    int updateByExample(@Param("record") AdminMonthDetail record, @Param("example") AdminMonthDetailExample example);

    int updateByPrimaryKeySelective(AdminMonthDetail record);

    int updateByPrimaryKey(AdminMonthDetail record);
}