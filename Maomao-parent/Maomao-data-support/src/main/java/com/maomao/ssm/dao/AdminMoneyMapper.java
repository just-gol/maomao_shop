package com.maomao.ssm.dao;

import com.maomao.ssm.bean.AdminMoney;
import com.maomao.ssm.bean.AdminMoneyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMoneyMapper {
    int countByExample(AdminMoneyExample example);

    int deleteByExample(AdminMoneyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminMoney record);

    int insertSelective(AdminMoney record);

    List<AdminMoney> selectByExample(AdminMoneyExample example);

    AdminMoney selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminMoney record, @Param("example") AdminMoneyExample example);

    int updateByExample(@Param("record") AdminMoney record, @Param("example") AdminMoneyExample example);

    int updateByPrimaryKeySelective(AdminMoney record);

    int updateByPrimaryKey(AdminMoney record);
}