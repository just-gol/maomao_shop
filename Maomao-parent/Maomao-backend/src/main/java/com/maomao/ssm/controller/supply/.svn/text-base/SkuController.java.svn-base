package com.maomao.ssm.controller.supply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.SkuService;

/** 
* @author:wzy
* @descrption:
* @version:
* @date:2018年4月27日
*/
@Controller
public class SkuController {
	@Autowired
	private SkuService skuService;
	
	/**
	 * 获取sku的key
	 * author:wzy
	 */
	@RequestMapping(value="skuKey.action",method=RequestMethod.GET)
	@ResponseBody
	public JsonData getSkuKeyProperty(Integer categoryId) {
		return skuService.getSkuKeyProperty(categoryId);
	}
	
	/**
	 * 根据id删除sku 的key
	 */
	
	@RequestMapping(value="deleteSkuKey.action",method=RequestMethod.GET)
	@ResponseBody
	public JsonData deleteSkuKeyById(Integer skuKeyId) {
		return skuService.deleteSkuKeyById(skuKeyId);
	}
	/**
	 * 根据id删除sku
	 */
	
	@RequestMapping(value="deleteGoodsSku.action",method=RequestMethod.GET)
	@ResponseBody
	public JsonData deleteGoodsSku(Integer skuId) {
		return skuService.deleteGoodsSkuById(skuId);
	}
}






















