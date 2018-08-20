package com.maomao.ssm.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.HomeCategoryService;

/**
 * @author:wzy
 * @descrption:首页分类管理
 * @version:
 * @date:2018年5月22日
 */
@Controller
public class HomeCategoryController {
	@Autowired
	private HomeCategoryService homeCategoryService;

	// 获取首页分类列表
	@RequestMapping(value = "/getHomePageCategoryList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getHomePageCategoryList() {
		return homeCategoryService.getHomeCategoyrList();
	}

	// 修改 或 新建分类
	@RequestMapping(value = "/saveOrUpdateHomePageCategory.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData saveOrUpdateHomePageCategory(Integer id, String name, String img, Integer categoryId,
			Integer sort) {
		return homeCategoryService.saveOrUpdateHomePageCategory(id, name, img, categoryId, sort);
	}

	// 根据id获取首页分类详情
	@RequestMapping(value = "/getHomePageCategoyrById.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getHomePageCategoyrById(Integer id) {
		return homeCategoryService.getHomePageCategoyrById(id);
	}

	// 上移
	@RequestMapping(value = "/homePageCategoryUp.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData homePageCategoryUp(Integer id) {
		return homeCategoryService.homePageCategoryUp(id);
	}

	// 下移
	@RequestMapping(value = "/homePageCategoryDown.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData homePageCategoryDown(Integer id) {
		return homeCategoryService.homePageCategoryDown(id);
	}

	// 置顶
	@RequestMapping(value = "/homePageCategoryTop.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData homePageCategoryTop(Integer id) {
		return homeCategoryService.homePageCategoryTop(id);
	}

	// 分类商品列表
	@RequestMapping(value = "/getHomeCategoryGoodsList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getHomeCategoryGoodsList() {
		return homeCategoryService.getHomeCategoryGoodsList();
	}

	// 分类下的商品排序列表
	@RequestMapping(value = "/getHomeCategoryGoodsOrderList.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getHomeCategoryGoodsOrderList(Integer id, String keywords) {
		return homeCategoryService.getHomeCategoryGoodsOrderList(id, keywords);
	}

	// 分类下的商品上移
	@RequestMapping(value = "/categoryGoodsUp.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData categoryGoodsUp(Integer categoryId, Integer goodsId) {
		return homeCategoryService.categoryGoodsUp(categoryId, goodsId);
	}

	// 分类下的下移
	@RequestMapping(value = "/categoryGoodsDown.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData categoryGoodsDown(Integer categoryId, Integer goodsId) {
		return homeCategoryService.categoryGoodsDown(categoryId, goodsId);
	}

	// 分类下的置顶
	@RequestMapping(value = "/categoryGoodsTop.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData categoryGoodsTop(Integer categoryId, Integer goodsId) {
		return homeCategoryService.categoryGoodsTop(categoryId, goodsId);
	}

	// 删除分类
	@RequestMapping(value = "/deleteHomePageCategoryById.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData deleteHomePageCategoryById(Integer id) {
		return homeCategoryService.deleteHomePageCategoryById(id);
	}
}
