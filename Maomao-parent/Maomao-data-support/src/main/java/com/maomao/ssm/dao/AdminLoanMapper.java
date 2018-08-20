package com.maomao.ssm.dao;

import com.maomao.ssm.bean.AdminLoan;
import com.maomao.ssm.bean.AdminLoanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminLoanMapper {
    int countByExample(AdminLoanExample example);

    int deleteByExample(AdminLoanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminLoan record);

    int insertSelective(AdminLoan record);

    List<AdminLoan> selectByExample(AdminLoanExample example);

    AdminLoan selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminLoan record, @Param("example") AdminLoanExample example);

    int updateByExample(@Param("record") AdminLoan record, @Param("example") AdminLoanExample example);

    int updateByPrimaryKeySelective(AdminLoan record);

    int updateByPrimaryKey(AdminLoan record);
}