package com.maomao.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.maomao.ssm.bean.GoodsExampleCustom;
import com.maomao.ssm.bean.GoodsSku;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.pojo.GoodsMapperBean;
import com.maomao.ssm.pojo.GoodsWithBLOBsList;

public interface GoodsMapperCustom {

	List<GoodsWithBLOBsList> getGoodsList(GoodsMapperBean goodsMapperBean);

	Integer getGoodsCount(GoodsMapperBean goodsMapperBean);

	List<GoodsSku> getGoodsSkuListByMortgage(@Param("dataRoleType") Byte type, @Param("dataRoleScope") String scope);

	List<GoodsWithBLOBs> selectByExampleWithBLOBs(GoodsExampleCustom example);
}
