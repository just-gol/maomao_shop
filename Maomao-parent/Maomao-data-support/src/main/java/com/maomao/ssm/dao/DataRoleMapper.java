package com.maomao.ssm.dao;

import com.maomao.ssm.bean.DataRole;
import com.maomao.ssm.bean.DataRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataRoleMapper {
    int countByExample(DataRoleExample example);

    int deleteByExample(DataRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataRole record);

    int insertSelective(DataRole record);

    List<DataRole> selectByExampleWithBLOBs(DataRoleExample example);

    List<DataRole> selectByExample(DataRoleExample example);

    DataRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataRole record, @Param("example") DataRoleExample example);

    int updateByExampleWithBLOBs(@Param("record") DataRole record, @Param("example") DataRoleExample example);

    int updateByExample(@Param("record") DataRole record, @Param("example") DataRoleExample example);

    int updateByPrimaryKeySelective(DataRole record);

    int updateByPrimaryKeyWithBLOBs(DataRole record);

    int updateByPrimaryKey(DataRole record);
}