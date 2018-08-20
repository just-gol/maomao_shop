package com.maomao.ssm.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.GoodsHomeService;

/**
 * 首页分类商品
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value="system")
public class GoodsHomeController {
	@Autowired
	private GoodsHomeService goodsHomeService;
	
	/**
	 * 添加首页分类
	 * @return
	 */
	@RequestMapping(value = "/addGoodsHomepageCategory.action", method = RequestMethod.POST)
	public JsonData addGoodsHomepageCategory(Integer goodsHomepageCategoryId,String name ,String img ){
		return goodsHomeService.addGoodsHomepageCategory(goodsHomepageCategoryId,name,img);
	}
	
	/**
	 * 下移首页分类
	 * @param homepageCategoryId
	 * @return
	 */
	@RequestMapping(value = "/setHomePageCategoryDown.action", method = RequestMethod.POST)
	public JsonData setHomePageCategoryDown(Integer homepageCategoryId){
		return goodsHomeService.setHomePageCategoryDown(homepageCategoryId);
	}
	
	/**
	 * 上移首页分类
	 * @param homepageCategoryId
	 * @return
	 */
	@RequestMapping(value = "/setHomePageCategoryUp.action", method = RequestMethod.POST)
	public JsonData setHomePageCategoryUp(Integer homepageCategoryId){
		return goodsHomeService.setHomePageCategoryUp(homepageCategoryId);
	}
	
	/**
	 * 置顶首页分类
	 * @param homepageCategoryId
	 * @return
	 */
//	@RequestMapping(value = "/setHomePageCategoryTop.action", method = RequestMethod.POST)
//	public JsonData setHomePageCategoryTop(Integer homepageCategoryId){
//		return goodsHomeService.setHomePageCategoryTop(homepageCategoryId);
//	}
//	
	/**
	 * 删除首页分类
	 * @param homepageCategoryId
	 * @return
	 */
	@RequestMapping(value = "/delHomePageCategoryById.action", method = RequestMethod.POST)
	public JsonData delHomePageCategoryById(Integer homepageCategoryId){
		return goodsHomeService.delHomePageCategoryById(homepageCategoryId);
	}
	
	/**
	 * 获取商品列表
	 */
	@RequestMapping(value = "/getGoodsAll.action", method = RequestMethod.GET)
	public JsonData getGoodsAll() {
		return goodsHomeService.getGoodsAll();
	}
	
	/**
	 * 查询所有压货商品	GoodsOverstock
	 * @return
	 */
	@RequestMapping(value = "/getGoodsOverstockAll.action", method = RequestMethod.GET)
	public  JsonData getGoodsOverstockAll(){
		return goodsHomeService.getGoodsOverstockAll();
	}

	/**
	 * 首页商品添加
	 * @param goodsHomepageCategoryId
	 * @param goodsId
	 * @param category 商品类型 0认筹 1普通 2买断
	 * @return
	 */
	@RequestMapping(value = "/addGoodsHome.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addGoodsHome(Integer goodsHomepageCategoryId, Integer[] goodsIds,Integer category,Byte type) {
		try {
			if (goodsHomepageCategoryId == null) {
				return JsonData.setErrorMessage("首页分类id不存在");
			}
			if (category != 2 || type != 1) {
				if (goodsIds == null) {
					return JsonData.setErrorMessage("商品id不存在");
				}
			}
			goodsHomeService.addGoodsHome(goodsHomepageCategoryId, goodsIds,category,type);
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("添加失败");
		}

	}

	/**
	 * 首页商品上移
	 * @param goodsHomepageCategoryId
	 * @param goodsId
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/setGoodsHomeUp.action", method = RequestMethod.POST)
	public JsonData setGoodsHomeUp(Integer goodsHomepageCategoryId, Integer goodsId,Integer category) {
		return goodsHomeService.setGoodsHomeUp(goodsHomepageCategoryId, goodsId,category);
	}

	/**
	 * 首页商品下移
	 * @param goodsHomepageCategoryId
	 * @param goodsId
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/setGoodsHomeDown.action", method = RequestMethod.POST)
	public JsonData setGoodsHomeDown(Integer goodsHomepageCategoryId, Integer goodsId,Integer category) {
		return goodsHomeService.setGoodsHomeDown(goodsHomepageCategoryId, goodsId,category);
	}
	
	/**
	 * 首页商品置顶
	 * @param goodsHomepageCategoryId
	 * @param goodsId
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/setGoodsHomeTop.action", method = RequestMethod.POST)
	public JsonData setGoodsHomeTop(Integer goodsHomepageCategoryId, Integer goodsId,Integer category){
		return goodsHomeService.setGoodsHomeTop(goodsHomepageCategoryId, goodsId,category);
	}
	/**
	 *  首页商品删除
	 * @param goodsHomepageCategoryId
	 * @param goodsId
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/delGoodsHome.action", method = RequestMethod.POST)
	public JsonData delGoodsHome(Integer goodsHomepageCategoryId, Integer goodsId,Integer category) {
		return goodsHomeService.delGoodsHome(goodsHomepageCategoryId, goodsId,category);
	}

	/**
	 * 查询首页商品列表
	 * @return
	 */
	@RequestMapping(value = "/getGoodsHomeAll.action", method = RequestMethod.GET)
	public  JsonData getGoodsHomeAll(){
		return goodsHomeService.getGoodsHomeAll();
	}

}
