package com.maomao.ssm.controller.supply;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.maomao.ssm.bean.GoodsSubscriptionWithBLOBs;
import com.maomao.ssm.bean.GoodsWarehouse;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.SubscriptionService;

/**
 * @author:wzy
 * @descrption:认筹商品管理
 * @version:
 * @date:2018年3月2日
 */
@Controller
@RequestMapping(value = "/sub")
public class SubscriptionItemController {
	@Autowired
	private SubscriptionService subscriptionService;

	// 日期解析
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// 转换日期格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 注册自定义的编辑器
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

	}

	/**
	 * 查询认筹商品列表
	 */
	@RequestMapping(value = "/subList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getSubList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, Integer categoryId, Byte status, String name) {
		return subscriptionService.getSubList(pages, rows, categoryId, status, name);
	}

	/**
	 * 根据认筹id查询认筹商品信息 bonus:总额
	 */
	@RequestMapping(value = "/getSubItem.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getSubItem(Integer itemId) {
		return subscriptionService.getSubItem(itemId);
	}

	/**
	 * 添加认筹商品
	 */
	@RequestMapping(value = "/saveSubItem.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData saveSubItem(GoodsSubscriptionWithBLOBs goods, Long bonus, GoodsWarehouse goodsWarehouse,
			String diyAddress, String diyProvince, String diyCity, String diyArea, String diyMobile,
			String diyAddressName) {
		return subscriptionService.saveSubItem(goods, bonus, goodsWarehouse, diyAddress, diyProvince, diyCity, diyArea,
				diyMobile, diyAddressName);
	}

	/**
	 * 
	 * 处理认筹审核
	 */
	@RequestMapping(value = "/saveSubItemStatus.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData saveSubItemStatus(Integer itemId, Byte type, String reason, Integer adminId) {
		return subscriptionService.saveSubItemStatus(itemId, type, reason, adminId);
	}

	/**
	 * 
	 * 认筹明细
	 */
	@RequestMapping(value = "/getSubDetail.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getSubDetail(Integer pages, Integer rows, Integer itemId) {
		return subscriptionService.getSubDetail(pages, rows, itemId);
	}
	/**
	 * 
	 * 认筹分红
	 */
	@RequestMapping(value = "/confirmBounsAvg.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData confirmBounsAvg(Integer goodsId) {
		return subscriptionService.confirmBounsAvg(goodsId);
	}

	/**
	 * 修改合卖中状态
	 */
	@RequestMapping(value = "/updateSelleStatus.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateSelleStatus(Integer goodsId,Byte sellStatus){
		return subscriptionService.updateSelleStatus(goodsId, sellStatus);
	}
}
























