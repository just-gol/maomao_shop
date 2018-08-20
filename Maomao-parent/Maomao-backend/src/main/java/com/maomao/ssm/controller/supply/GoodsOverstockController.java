package com.maomao.ssm.controller.supply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.maomao.ssm.bean.GoodsOverstock;
import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.GoodsOverstockService;

/**
 * 买断管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = "/supply")
public class GoodsOverstockController {
	@Autowired
	private GoodsOverstockService goodsOverstockService;
	/**
	 * 申请买断,修改申请买断
	 * @param goodsOverstock 压货商品
	 * @param detail	压货商品详情，图片途径
	 * @param imgs	压货商品图片 以“;”间隔
	 * @param param 参数json
	 * @param GoodsOverstockInfoList 压货商品信息集合
	 * @param goodsWarehouseList 仓库集合
	 * @param status 商品状态
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdateBuyOutGoods.action", method = RequestMethod.POST)
	public JsonData addAndUpdateBuyOutGoods(GoodsOverstockWithBLOBs goodsOverstock,String detail,String imgs, String param,String GoodsOverstockOrgoodsWarehouseOrAddressList,Byte status ){
		return goodsOverstockService.addAndUpdateBuyOutGoods(goodsOverstock,detail,imgs,param,GoodsOverstockOrgoodsWarehouseOrAddressList,status);
	}
	
	/**
	 * 获取自取地址详情
	 */
	@RequestMapping(value = "/getGoodsAddressList.action", method = RequestMethod.GET)
	public JsonData getGoodsAddressList(){
		return goodsOverstockService.getGoodsAddressList();
	}
	
	/**
	 * 获取压货商品详情
	 * @param goodsOverstockId
	 * @return
	 */
	@RequestMapping(value = "/getBuyOutGoodsById.action", method = RequestMethod.GET)
	public JsonData getBuyOutGoodsById(Integer id){
		return goodsOverstockService.getBuyOutGoodsById(id);
	}
	
	/**
	 * 压货商品删除(下架申请)和处理其他
	 */
	@RequestMapping(value = "/delAndUpAndOtherBuyOutGoods.action", method = RequestMethod.POST)
	public JsonData delAndUpAndOtherBuyOutGoods(Integer[] goodsOverstockIds,Byte status,String reason){
		return goodsOverstockService.delAndUpAndOtherBuyOutGoods(goodsOverstockIds,status,reason);
	}
	
	/**
	 * 获取压货商品列表
	 */
	@RequestMapping(value = "/getBuyOutGoodsList.action", method = RequestMethod.GET)
	public JsonData getBuyOutGoodsList(Integer pages, Integer rows,Byte status,String goodsOverstockName){
		return goodsOverstockService.getBuyOutGoodsList(pages,rows,status,goodsOverstockName);
	}
	
	/***
	 * 删除压货商品信息对象
	 */
	@RequestMapping(value = "/delGoodsOverstockInfo.action", method = RequestMethod.POST)
	public JsonData delGoodsOverstockInfo(Integer goodsOverstockInfoId,Byte status){
		return goodsOverstockService.delGoodsOverstockInfo(goodsOverstockInfoId,status);
	}
	
	/**
	 * 删除自取地址
	 */
	@RequestMapping(value = "/delGoodsAddress.action", method = RequestMethod.POST)
	public JsonData delGoodsAddress(Integer goodsAddressId){
		return goodsOverstockService.delGoodsAddress(goodsAddressId);
	}
	
	/**
	 * 上架商品修改库存
	 */
	@RequestMapping(value = "/updateGoodsOverstock.action", method = RequestMethod.POST)
	public JsonData updateGoodsOverstock(Integer goodsOverstockId , Integer stock){
		return goodsOverstockService.updateGoodsOverstock(goodsOverstockId,stock);
	}
}
