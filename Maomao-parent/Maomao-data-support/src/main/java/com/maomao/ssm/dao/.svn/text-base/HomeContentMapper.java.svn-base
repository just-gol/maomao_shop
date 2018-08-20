package com.maomao.ssm.dao;

import com.maomao.ssm.bean.HomeContent;
import com.maomao.ssm.bean.HomeContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HomeContentMapper {
    int countByExample(HomeContentExample example);

    int deleteByExample(HomeContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HomeContent record);

    int insertSelective(HomeContent record);

    List<HomeContent> selectByExample(HomeContentExample example);

    HomeContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HomeContent record, @Param("example") HomeContentExample example);

    int updateByExample(@Param("record") HomeContent record, @Param("example") HomeContentExample example);

    int updateByPrimaryKeySelective(HomeContent record);

    int updateByPrimaryKey(HomeContent record);
}