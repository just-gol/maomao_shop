package com.maomao.ssm.controller.supply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.SupplyCategoryService;

/**
 * @author:wzy
 * @descrption:货源分类管理
 * @version:
 * @date:2018年3月2日
 */
@Controller
@RequestMapping(value = "/item")
public class SupplyCategoryController {

	@Autowired
	private SupplyCategoryService supplyCategoryService;

	/**
	 * 查询货源分类 author:wzy
	 */
	@RequestMapping(value = "/itemCat.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData getSupplyCategory(@RequestParam(defaultValue = "1") int pages,
			@RequestParam(defaultValue = "10") int rows, String keywords, Byte isRecommend) {
		return supplyCategoryService.getSupplyCategory(pages, rows, keywords, isRecommend);
	}

	/**
	 * 删除分类
	 */
	@RequestMapping(value = "/delSupplyCat.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData deleteCategoryById(Integer categoryId) {
		return supplyCategoryService.deleteSupplyCategoryById(categoryId);
	}

	/**
	 * 分类停用
	 */
	@RequestMapping(value = "/stopItemCat.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData stopCategoryById(Integer categoryId) {
		return supplyCategoryService.setCategoryStopById(categoryId);
	}

	/**
	 * 启用分类
	 */
	@RequestMapping(value = "/startItemCat.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData startCategoryById(Integer categoryId) {
		return supplyCategoryService.setCategoryStartById(categoryId);
	}

	/**
	 * 分类上移
	 */
	@RequestMapping(value = "/upItemCat.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData setCategorySortUp(Integer currentCategoryId) {
		return supplyCategoryService.setCategorySortUp(currentCategoryId);
	}

	/**
	 * 分类下移 author:wzy
	 */
	@RequestMapping(value = "/backItemCat.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData setCategorySortBack(Integer categoryId) {
		return supplyCategoryService.setCategorySortBack(categoryId);
	}

	/**
	 * 添加分类
	 * 
	 */
	@RequestMapping(value = "/addCategory.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addCategory(Integer sort, String categoryName, Integer categorySort, String imgURL, String brand,
			String skuKey, Integer maxCategorySort, Byte isRecommend) {
		return supplyCategoryService.addCategroy(sort, categoryName, categorySort, imgURL, brand, skuKey,
				maxCategorySort, isRecommend);
	}

	/**
	 * 根据分类id查询分类信息
	 * 
	 */
	@RequestMapping(value = "/getCatDetail.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getCategoryDetailByCategoryId(Integer categoryId) {
		return supplyCategoryService.getCategoryDetailByCategoryId(categoryId);
	}

	/**
	 * 查询分类下所有品牌
	 * 
	 */
	@RequestMapping(value = "/getBrandList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getBrandListByCategoryId(Integer categoryId, @RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "5") Integer rows, Byte isFenye) {
		return supplyCategoryService.getCategoryBrand(categoryId, pages, rows, isFenye);
	}

	/**
	 * 修改品牌
	 * 
	 */
	@RequestMapping(value = "/editBrand.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData editBrandById(Integer brandId, String name) {
		return supplyCategoryService.editBrandById(brandId, name);
	}

	/**
	 * 删除品牌
	 * 
	 */
	@RequestMapping(value = "/deleteBrand.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData delBrandById(Integer brandId) {
		return supplyCategoryService.deleteBrandById(brandId);
	}

	/**
	 * 修改分类
	 * 
	 */
	@RequestMapping(value = "/editCategory.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData editCategoryById(GoodsCategory goodsCategory, String brand, String skuKey) {
		return supplyCategoryService.editCategoryById(goodsCategory, brand, skuKey);
	}

	/**
	 * 获取分类菜单下拉列表
	 * 
	 */
	@RequestMapping(value = "/getCatList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getCategorySimpleDetail(@RequestParam(defaultValue = "1") Byte isRecommend) {
		return supplyCategoryService.getCategorySimpleDetail(isRecommend);

	}
}
