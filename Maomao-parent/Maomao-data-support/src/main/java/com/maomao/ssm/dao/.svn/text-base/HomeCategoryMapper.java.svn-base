package com.maomao.ssm.dao;

import com.maomao.ssm.bean.HomeCategory;
import com.maomao.ssm.bean.HomeCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HomeCategoryMapper {
    int countByExample(HomeCategoryExample example);

    int deleteByExample(HomeCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HomeCategory record);

    int insertSelective(HomeCategory record);

    List<HomeCategory> selectByExample(HomeCategoryExample example);

    HomeCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HomeCategory record, @Param("example") HomeCategoryExample example);

    int updateByExample(@Param("record") HomeCategory record, @Param("example") HomeCategoryExample example);

    int updateByPrimaryKeySelective(HomeCategory record);

    int updateByPrimaryKey(HomeCategory record);
}