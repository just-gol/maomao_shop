package com.maomao.ssm.controller.supply;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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

import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.constant.AdminConst;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.SelfItemService;
import com.maomao.ssm.utils.CookieUtils;

/**
 * @author:wzy
 * @descrption:自营商品管理
 * @version:
 * @date:2018年3月2日
 */
@Controller
@RequestMapping(value = "/supply")
public class SelfItemController {
	@Autowired
	private SelfItemService selfItemService;
	@Autowired
	private JedisClientPool jedisClientPool;

	// 日期解析
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// 转换日期格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 注册自定义的编辑器
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

	}

	/**
	 * 获取自营商品列表
	 */
	@RequestMapping(value = "/getSelfItemList.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getSelfItemList(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, Integer categoryId, Byte status, Byte getWay, String name,
			byte type, Byte warehouseType, HttpServletRequest request) {
		String cookie = CookieUtils.getCookieValue(request, "token", true);
		Integer adminId = Integer.parseInt(jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS, cookie));
		return selfItemService.getSelfItemList(pages, rows, categoryId, status, getWay, name, type, warehouseType,
				adminId);
	}

	/**
	 * 根据商品id查询商品详情
	 */

	@RequestMapping(value = "/getItemById.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getItemById(Integer goodsId) {
		return selfItemService.getItemById(goodsId);
	}

	/**
	 * 根据商品id查询商品服务项
	 */

	@RequestMapping(value = "/getItemService.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getItemService(Integer itemId) {
		return selfItemService.getItemService(itemId);

	}

	/**
	 * 获取所有服务项
	 */

	@RequestMapping(value = "/getServiceAll.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getItemServiceAll() {
		return selfItemService.getServiceAll();

	}

	/**
	 * 添加商品
	 */

	@RequestMapping(value = "/saveItem.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData saveItem(GoodsWithBLOBs goods, Byte addressType, String address, String province, String city,
			String area, String mobile, String addressName, String diyAddress, String diyProvince, String diyCity,
			String diyArea, String diyMobile, String diyAddressName, Byte flag, String goodsSku) {
		return selfItemService.saveItem(goods, addressType, address, province, city, area, mobile, addressName,
				diyAddress, diyProvince, diyCity, diyArea, diyMobile, diyAddressName, flag, goodsSku);
	}

	/**
	 * 商品下架申请
	 */

	@RequestMapping(value = "/setItemOnSale.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData setItemOnSale(Integer[] itemIds, Integer userId) {
		return selfItemService.setItemOnSale(itemIds, userId);

	}

	/**
	 * 下架商品删除
	 */
	@RequestMapping(value = "/delItemOnSale.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData delItemOnSale(Integer itemIds) {
		return selfItemService.delItemOnSale(itemIds);
	}

	/**
	 * 质押商品统计
	 */
	@RequestMapping(value = "/getMortgageTotal.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getMortgageTotal(HttpServletRequest request) {
		String cookie = CookieUtils.getCookieValue(request, "token", true);
		Integer adminId = Integer.parseInt(jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS, cookie));
		return selfItemService.getMortgageTotal(adminId);
	}

	/**
	 * 修改上架商品库存
	 */
	@RequestMapping(value = "/updateGoodsShelvesStock.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData updateGoodsShelvesStock(Integer skuId , Integer stock) {
		return selfItemService.updateGoodsShelvesStock(skuId, stock);
	}
}
