package com.maomao.ssm.dao;

import com.maomao.ssm.bean.UserBank;
import com.maomao.ssm.bean.UserBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBankMapper {
    int countByExample(UserBankExample example);

    int deleteByExample(UserBankExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBank record);

    int insertSelective(UserBank record);

    List<UserBank> selectByExample(UserBankExample example);

    UserBank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserBank record, @Param("example") UserBankExample example);

    int updateByExample(@Param("record") UserBank record, @Param("example") UserBankExample example);

    int updateByPrimaryKeySelective(UserBank record);

    int updateByPrimaryKey(UserBank record);
}