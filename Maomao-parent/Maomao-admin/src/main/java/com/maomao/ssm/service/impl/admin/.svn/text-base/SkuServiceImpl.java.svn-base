package com.maomao.ssm.service.impl.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maomao.ssm.bean.GoodsSkuKey;
import com.maomao.ssm.bean.GoodsSkuKeyExample;
import com.maomao.ssm.dao.GoodsSkuKeyMapper;
import com.maomao.ssm.dao.GoodsSkuMapper;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.SkuService;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年4月27日
 */
@Service
public class SkuServiceImpl implements SkuService {
	@Autowired
	private GoodsSkuKeyMapper skuKeyMapper;
	@Autowired
	private GoodsSkuMapper skuMapper;
	/**
	 * 根据分类id获取sku的key
	 */
	public JsonData getSkuKeyProperty(Integer categoryId) {
		if (categoryId == null) {
			return JsonData.setErrorMessage("参数错误");
		}

		GoodsSkuKeyExample example = new GoodsSkuKeyExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		List<GoodsSkuKey> list = skuKeyMapper.selectByExample(example);
		List<Map<String, Object>> returnList  = null;
		if (list != null && list.size() > 0) {
			returnList = new ArrayList<Map<String,Object>>();
			for (GoodsSkuKey goodsSkuKey : list) {
				Map<String, Object> map= new HashMap<String, Object>();
				map.put("skuId", goodsSkuKey.getId());
				map.put("skuKey", goodsSkuKey.getSkuKey());
				returnList.add(map);
			}
		}
		return JsonData.setSuccessMessage(returnList);
	}
	/**
	 * 根据分类id删除sku的key
	 */
	public JsonData deleteSkuKeyById(Integer skuKeyId) {
		if (skuKeyId==null) {
			return JsonData.setErrorMessage("参数错误");
		}
		try {
			skuKeyMapper.deleteByPrimaryKey(skuKeyId);
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			return JsonData.setErrorMessage("删除失败");
		}
	}
	
	
	/**
	 * 删除商品的sku
	 */
	public JsonData deleteGoodsSkuById(Integer skuId) {
		if (skuId==null) {
			return JsonData.setErrorMessage("参数错误");
		}
		try {
			skuMapper.deleteByPrimaryKey(skuId);
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			return JsonData.setErrorMessage("删除失败");
		}
	}

}


















