package com.maomao.ssm.dao;

import com.maomao.ssm.bean.UserMoney;
import com.maomao.ssm.bean.UserMoneyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMoneyMapper {
    int countByExample(UserMoneyExample example);

    int deleteByExample(UserMoneyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserMoney record);

    int insertSelective(UserMoney record);

    List<UserMoney> selectByExample(UserMoneyExample example);

    UserMoney selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserMoney record, @Param("example") UserMoneyExample example);

    int updateByExample(@Param("record") UserMoney record, @Param("example") UserMoneyExample example);

    int updateByPrimaryKeySelective(UserMoney record);

    int updateByPrimaryKey(UserMoney record);
}