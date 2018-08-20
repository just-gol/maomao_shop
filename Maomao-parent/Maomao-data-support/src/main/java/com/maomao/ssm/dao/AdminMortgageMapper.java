package com.maomao.ssm.dao;

import com.maomao.ssm.bean.AdminMortgage;
import com.maomao.ssm.bean.AdminMortgageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMortgageMapper {
    int countByExample(AdminMortgageExample example);

    int deleteByExample(AdminMortgageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminMortgage record);

    int insertSelective(AdminMortgage record);

    List<AdminMortgage> selectByExample(AdminMortgageExample example);

    AdminMortgage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminMortgage record, @Param("example") AdminMortgageExample example);

    int updateByExample(@Param("record") AdminMortgage record, @Param("example") AdminMortgageExample example);

    int updateByPrimaryKeySelective(AdminMortgage record);

    int updateByPrimaryKey(AdminMortgage record);
}