package com.maomao.ssm.dao;

import com.maomao.ssm.bean.AdminDataRole;
import com.maomao.ssm.bean.AdminDataRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminDataRoleMapper {
    int countByExample(AdminDataRoleExample example);

    int deleteByExample(AdminDataRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminDataRole record);

    int insertSelective(AdminDataRole record);

    List<AdminDataRole> selectByExample(AdminDataRoleExample example);

    AdminDataRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminDataRole record, @Param("example") AdminDataRoleExample example);

    int updateByExample(@Param("record") AdminDataRole record, @Param("example") AdminDataRoleExample example);

    int updateByPrimaryKeySelective(AdminDataRole record);

    int updateByPrimaryKey(AdminDataRole record);
}