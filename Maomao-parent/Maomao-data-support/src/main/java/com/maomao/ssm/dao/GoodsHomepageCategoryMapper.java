package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsHomepageCategory;
import com.maomao.ssm.bean.GoodsHomepageCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsHomepageCategoryMapper {
    int countByExample(GoodsHomepageCategoryExample example);

    int deleteByExample(GoodsHomepageCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsHomepageCategory record);

    int insertSelective(GoodsHomepageCategory record);

    List<GoodsHomepageCategory> selectByExample(GoodsHomepageCategoryExample example);

    GoodsHomepageCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsHomepageCategory record, @Param("example") GoodsHomepageCategoryExample example);

    int updateByExample(@Param("record") GoodsHomepageCategory record, @Param("example") GoodsHomepageCategoryExample example);

    int updateByPrimaryKeySelective(GoodsHomepageCategory record);

    int updateByPrimaryKey(GoodsHomepageCategory record);
}