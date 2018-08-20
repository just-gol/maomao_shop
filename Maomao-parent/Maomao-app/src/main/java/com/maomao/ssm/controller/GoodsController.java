package com.maomao.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.GoodsService;

/**
 * @descrption:商品管理Controller
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;

	/**
	 * 根据分类Id查询商品信息
	 */
	@RequestMapping(value = "categoryGoods.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getGoodsByCateogoryId(Integer type, Integer categoryId,
			@RequestParam(defaultValue = "1") Integer pages, @RequestParam(defaultValue = "10") Integer rows,
			Integer sortType, String brand, Long startPrice, Long endPrice) {
		return goodsService.getGoodsByCategoryId(type, categoryId, pages, rows, sortType, brand, startPrice, endPrice);
	}

	/**
	 * 根据分类Id查询品牌信息
	 */
	@RequestMapping(value = "goodsBrands.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getGoodsBrands(Integer categoryId, String name) {
		return goodsService.getGoodsBrands(categoryId, name);
	}

	/**
	 * 商品搜索
	 */
	@RequestMapping(value = "goodsSearch.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData goodsSearch(Integer type, String keywords, @RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, Integer sortType, String brand, Long startPrice,
			Long endPrice) {
		return goodsService.goodsSearch(type, keywords, pages, rows, sortType, brand, startPrice, endPrice);
	}

	/**
	 * 查询商品详情
	 */
	@RequestMapping(value = "goodsDetails.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData goodsDetails(Integer userId, Integer categoryId, Integer goodsId) {
		return goodsService.getGoodsDetails(userId, categoryId, goodsId);
	}

	/**
	 * 添加/取消收藏
	 */
	@RequestMapping(value = "goodsCollection.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addGoodsCollection(Integer userId, Integer goodsId, Integer type) {
		return goodsService.addGoodsCollection(userId, goodsId, type);
	}
	
	/**
	 * 分享记录
	 */
	@RequestMapping(value = "addGoodsShareRecord.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addGoodsShareRecord(Integer userId, Integer goodsId, Integer category) {
		return goodsService.addGoodsShareRecord(userId, goodsId, category);
	}
}
