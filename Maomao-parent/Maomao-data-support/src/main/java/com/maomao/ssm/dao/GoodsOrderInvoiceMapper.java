package com.maomao.ssm.dao;

import com.maomao.ssm.bean.GoodsOrderInvoice;
import com.maomao.ssm.bean.GoodsOrderInvoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsOrderInvoiceMapper {
    int countByExample(GoodsOrderInvoiceExample example);

    int deleteByExample(GoodsOrderInvoiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsOrderInvoice record);

    int insertSelective(GoodsOrderInvoice record);

    List<GoodsOrderInvoice> selectByExample(GoodsOrderInvoiceExample example);

    GoodsOrderInvoice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsOrderInvoice record, @Param("example") GoodsOrderInvoiceExample example);

    int updateByExample(@Param("record") GoodsOrderInvoice record, @Param("example") GoodsOrderInvoiceExample example);

    int updateByPrimaryKeySelective(GoodsOrderInvoice record);

    int updateByPrimaryKey(GoodsOrderInvoice record);
}