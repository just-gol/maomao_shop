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

import com.maomao.ssm.constant.AdminConst;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.SupplyDetailsService;
import com.maomao.ssm.utils.CookieUtils;

/**
 * @author:wzy
 * @descrption:货源总览
 * @version:
 * @date:2018年3月2日
 */
@Controller
@RequestMapping(value = "/supply")
public class SupplyDetailsController {

	@Autowired
	private SupplyDetailsService supplyDetailsService;
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
	 * 查看货源总览 type:仓储类型
	 */
	@RequestMapping("/getSubpplyDetail.action")
	@ResponseBody
	public JsonData getSubpplyDetail(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "10") Integer rows, Integer categoryId, Integer startStock, Integer endStock,
			Byte goodsProperty, Byte goodsStatus, String goodsName, Byte type, HttpServletRequest request) {
		String cookie = CookieUtils.getCookieValue(request, "token", true);
		Integer adminId = Integer.parseInt(jedisClientPool.hget(AdminConst.AMDIN_LOGIN_SUCCESS, cookie));
		return supplyDetailsService.getSubpplyDetail(pages, rows, categoryId, startStock, endStock, goodsProperty,
				goodsStatus, goodsName, type, adminId);
	}

	/**
	 * 根据商品id查询指定货源
	 */
	@RequestMapping(value = "/searchSubpplyDetail.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData searchSubpplyDetail(Integer goodsId) {
		return supplyDetailsService.searchSubpplyDetail(goodsId);
	}

	/**
	 * 根据商品id修改指定货源 仓库类型:type 仓储方式联系人:name
	 */
	@RequestMapping(value = "/updateSubpplyDetail.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData supdateSubpplyDetail(Integer goodsId, String goodsName, Integer categoryId, Integer stock,
			Integer stockSham, Long pricePurchase, Long priceSales, Integer brandId, String param, String serviceId,
			Byte payType, Byte getWay, Integer category, Byte type, String province, String city, String area,
			String address, String name, String mobile, Byte flag) {
		return supplyDetailsService.supdateSubpplyDetail(goodsId, goodsName, categoryId, stock, stockSham,
				pricePurchase, priceSales, brandId, param, serviceId, payType, getWay, category, type, province, city,
				area, address, name, mobile, flag);
	}

	/**
	 * 上架,商品 type:仓库类型
	 */
	@RequestMapping(value = "/upSubpplyDetail.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData upSubpplyDetail(Integer goodsId) {
		return supplyDetailsService.upSubpplyDetail(goodsId);
	}

	// TODO:下架申请和删除商品接口在SelfItemController类中

	/**
	 * 解除质押商品
	 */
	@RequestMapping(value = "/delPledge.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData delPledge(Integer goodsId) {
		return supplyDetailsService.delPledge(goodsId);
	}
}
