package com.maomao.ssm.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.SystemService;

/**
 * @author:wzy
 * @descrption:系统管理
 * @version:
 * @date:2018年3月8日
 */
@Controller
@RequestMapping(value = "/system")
public class SystemController {

	@Autowired
	private SystemService systemService;

	/**
	 * 服务管理
	 */
	@RequestMapping(value = "/getServicManagementList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getServicManagementList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows) {
		return systemService.getServicManagementList(pages, rows);
	}

	// 新增服务
	@RequestMapping(value = "/addServicManagement.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addServicManagement(String name, String detail) {
		return systemService.getServicManagementList(name, detail);
	}

	// 启动服务
	@RequestMapping(value = "/startServicManagement.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData startServicManagement(Integer goodsServiceId) {
		return systemService.startServicManagement(goodsServiceId);
	}

	// 禁用服务
	@RequestMapping(value = "/stopServicManagement.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData stopServicManagement(Integer goodsServiceId) {
		return systemService.stopServicManagement(goodsServiceId);
	}

	// 删除服务
	@RequestMapping(value = "/delServicManagement.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData delServicManagement(Integer goodsServiceId) {
		return systemService.delServicManagement(goodsServiceId);
	}

	// 编辑服务
	@RequestMapping(value = "/editServicManagement.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData editServicManagement(Integer goodsServiceId) {
		return systemService.editServicManagement(goodsServiceId);
	}

	// 修改服务
	@RequestMapping(value = "/updateServicManagement.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateServicManagement(Integer goodsServiceId, String name, String detail) {
		return systemService.updateServicManagement(goodsServiceId, name, detail);
	}

	
	
	/**
	 * 广告位管理
	 * 
	 */

	//新建, 修改广告位
	@RequestMapping(value = "/addLink.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addLink(Integer bannerId ,String AndroidImgURL,String IosImgURL, String href,Byte type) {
		return systemService.addLink(bannerId,AndroidImgURL,IosImgURL,href,type);
	}

	// TODO:上移下移如果按desc排序就会颠倒过来
	// 下移
	@RequestMapping(value = "/setAdManagementDown.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData setAdManagementDown(Integer bannerId,Byte type) {
		return systemService.setAdManagementDown(bannerId,type);
	}

	// 上移
	@RequestMapping(value = "/setAdManagementUp.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData setAdManagementUp(Integer bannerId,Byte type) {
		return systemService.setAdManagementUp(bannerId,type);
	}

	// 置顶
	@RequestMapping(value = "/setAdManagementTop.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData setAdManagementTop(Integer bannerId,Byte type) {
		return systemService.setAdManagementTop(bannerId,type);
	}

	// 查询广告位
	@RequestMapping(value = "/getAdManagementList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getAdManagementList(Byte type) {
		return systemService.getAdManagementList(type);
	}
	
	//删除广告位,分类位
	@RequestMapping(value = "/delBanner.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData delBanner(Integer bannerId,Byte type) {
		return systemService.delBanner(bannerId,type);
	}

	/**
	 * 推荐位管理
	 * 
	 */

	// 下移推荐位
	@RequestMapping(value = "/setRecommendDown.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData setRecommendDown(Integer homeCategoryId) {
		return systemService.setRecommendDown(homeCategoryId);
	}

	// 上移
	@RequestMapping(value = "/setRecommendUp.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData setRecommendUp(Integer homeCategoryId) {
		return systemService.setRecommendUp(homeCategoryId);
	}
	
	// 置顶
	@RequestMapping(value = "/setRecommendTop.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData setRecommendTop(Integer homeCategoryId) {
		return systemService.setRecommendTop(homeCategoryId);
	}
	
	//查询推荐位
	@RequestMapping(value = "/getRecommendList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getRecommendList() {
		return systemService.getRecommendList();
	}
	
	/*
	 *  新增推荐位
	 *  homeContentBeanList:推荐位内容字符串
	 */
	@RequestMapping(value = "/addRecommend.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addRecommend(Integer goodsCategoryId ,Integer HomeCategoryId, Byte imgType,String categoryName, String introduction,String homeContentBeanList) {
		return systemService.addRecommend(goodsCategoryId,HomeCategoryId,imgType,categoryName,introduction,homeContentBeanList);
	}
	
	/**
	 * categoryId:推荐位分类id
	 * @return
	 * 
	 */
	//保存推荐位
	@RequestMapping(value = "/saveRecommend.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData saveRecommend(Integer HomeCategoryId,Integer goodsCategoryId,String categoryName,Byte imgType, String introduction,String homeContentBeanList) {
		return systemService.saveRecommend(HomeCategoryId,goodsCategoryId,categoryName,imgType,introduction,homeContentBeanList);
	}
	
	//删除推荐位
	@RequestMapping(value = "/delRecommend.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData delRecommend(Integer categoryId) {
		return systemService.delRecommend(categoryId);
	}
	
	//删除推荐位内容
	@RequestMapping(value = "/delContent.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData delContent(Integer homeContentId) {
		return systemService.delContent(homeContentId);
	}
	
	
	//获取分类下所有商品
	@RequestMapping(value = "/getGoodsList.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getGoodsList(Integer goodsCategoryId){
		return systemService.getGoodsList(goodsCategoryId);
	}
	
	/**
	 * 查询商品列表
	 */
	@RequestMapping(value = "/getGoodsDetailList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getGoodsDetailList(String queryString){
		return systemService.getGoodsDetailList(queryString);
	}
}
