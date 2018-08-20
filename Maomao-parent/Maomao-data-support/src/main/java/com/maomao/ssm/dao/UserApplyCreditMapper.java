package com.maomao.ssm.dao;

import com.maomao.ssm.bean.UserApplyCredit;
import com.maomao.ssm.bean.UserApplyCreditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserApplyCreditMapper {
    int countByExample(UserApplyCreditExample example);

    int deleteByExample(UserApplyCreditExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserApplyCredit record);

    int insertSelective(UserApplyCredit record);

    List<UserApplyCredit> selectByExample(UserApplyCreditExample example);

    UserApplyCredit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserApplyCredit record, @Param("example") UserApplyCreditExample example);

    int updateByExample(@Param("record") UserApplyCredit record, @Param("example") UserApplyCreditExample example);

    int updateByPrimaryKeySelective(UserApplyCredit record);

    int updateByPrimaryKey(UserApplyCredit record);
}