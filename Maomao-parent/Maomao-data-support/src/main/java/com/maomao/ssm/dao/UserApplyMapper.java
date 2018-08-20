package com.maomao.ssm.dao;

import com.maomao.ssm.bean.UserApply;
import com.maomao.ssm.bean.UserApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserApplyMapper {
    int countByExample(UserApplyExample example);

    int deleteByExample(UserApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserApply record);

    int insertSelective(UserApply record);

    List<UserApply> selectByExample(UserApplyExample example);

    UserApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserApply record, @Param("example") UserApplyExample example);

    int updateByExample(@Param("record") UserApply record, @Param("example") UserApplyExample example);

    int updateByPrimaryKeySelective(UserApply record);

    int updateByPrimaryKey(UserApply record);
}