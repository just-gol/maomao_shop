package com.maomao.ssm.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.HomeCategoryAndContentService;

/**
 * 系统推荐,精选,推荐......商品管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value="system")
public class HomeCategoryAndContentController {
	@Autowired
	private HomeCategoryAndContentService homeCategoryAndContentService;
	
	/**
	 * 查看精选商品列表
	 * imgType:推荐商品类型: 3精选 4推荐 5店铺分享 6店铺压货
	 * @param pages
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="getGoodsQualityList", method = RequestMethod.GET)
	public JsonData getGoodsQualityList(String keyword,Byte imgType){
		return homeCategoryAndContentService.getGoodsQualityList(keyword,imgType);
	}
	
	/**
	 * 添加精选商品
	 * id:买断或者普通商品id
	 */
	@RequestMapping(value="addGoodsQuality", method = RequestMethod.POST)
	public JsonData addGoodsQuality(Integer[] ids , Byte type,Byte imgType){
		return homeCategoryAndContentService.addGoodsQuality(ids,type,imgType);
	}
	
	/**
	 * 上移
	 */
	@RequestMapping(value="setGoodsQualityUp", method = RequestMethod.POST)
	public JsonData setGoodsQualityUp(Integer id,Byte imgType){
		return homeCategoryAndContentService.setGoodsQualityUp(id,imgType);
	}
	
	/**
	 * 下移
	 */
	@RequestMapping(value="setGoodsQualityDown", method = RequestMethod.POST)
	public JsonData setGoodsQualityDown(Integer id,Byte imgType){
		return homeCategoryAndContentService.setGoodsQualityDown(id,imgType);
	}
	
	/**
	 * 置顶
	 */
	@RequestMapping(value="setGoodsQualityTop", method = RequestMethod.POST)
	public JsonData setGoodsQualityTop(Integer id,Byte imgType){
		return homeCategoryAndContentService.setGoodsQualityTop(id,imgType);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="delGoodsQuality", method = RequestMethod.POST)
	public JsonData delGoodsQuality(Integer id,Byte imgType){
		return homeCategoryAndContentService.delGoodsQuality(id,imgType);
	}
	
}
