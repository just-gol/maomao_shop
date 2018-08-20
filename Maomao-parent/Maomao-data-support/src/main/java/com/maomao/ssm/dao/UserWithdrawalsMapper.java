package com.maomao.ssm.dao;

import com.maomao.ssm.bean.UserWithdrawals;
import com.maomao.ssm.bean.UserWithdrawalsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWithdrawalsMapper {
    int countByExample(UserWithdrawalsExample example);

    int deleteByExample(UserWithdrawalsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserWithdrawals record);

    int insertSelective(UserWithdrawals record);

    List<UserWithdrawals> selectByExample(UserWithdrawalsExample example);

    UserWithdrawals selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserWithdrawals record, @Param("example") UserWithdrawalsExample example);

    int updateByExample(@Param("record") UserWithdrawals record, @Param("example") UserWithdrawalsExample example);

    int updateByPrimaryKeySelective(UserWithdrawals record);

    int updateByPrimaryKey(UserWithdrawals record);
}