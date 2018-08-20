package com.maomao.ssm.controller.shelves;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.bean.Goods;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.GoodsShelvesService;

/**
 * 商品下架审核
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/goods")
public class GoodsShelvesController {

	@Autowired
	private GoodsShelvesService goodsShelvesService;

	
	//查询商品分类
	@RequestMapping(value = "/findAllGoodsCategory.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData findAllGoodsCategory(){
		return goodsShelvesService.findAllGoodsCategory();
	}
	
	// 查询商品下架列表(现返回参数:当前页和总记录数)
	@RequestMapping(value = "/getGoodsAuditList.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getGoodsAuditList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, Byte goodsType, String goodsName, Integer categoryId,Byte goodsWarehouseType,Integer startStock,Integer endStock) {
		return goodsShelvesService.getGoodsAuditList(pages, rows, goodsType,goodsName,categoryId,goodsWarehouseType,startStock,endStock);
	}
	
	//查询商品品牌
	@RequestMapping(value = "/findAllGoodsBrandList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData findAllGoodsBrandList(){
		return goodsShelvesService.findAllGoodsBrandList();
	}
	
	//查看处理指定的下架申请
	@RequestMapping(value = "/getGoodsAudit.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getGoodsAudit(Integer goodsApplyId){
		return goodsShelvesService.getGoodsAudit(goodsApplyId);
	}
	
	//商品下架 
	@RequestMapping(value = "/goodsShelvest.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData goodsShelvest(Integer goodsApplyId){
		return goodsShelvesService.goodsShelvest(goodsApplyId);
	}
	
	//驳回下架
	@RequestMapping(value = "/goodsCancel.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData goodsCancel(Integer goodsApplyId,String reason){
		return goodsShelvesService.goodsCancel(goodsApplyId,reason);
	}
}
