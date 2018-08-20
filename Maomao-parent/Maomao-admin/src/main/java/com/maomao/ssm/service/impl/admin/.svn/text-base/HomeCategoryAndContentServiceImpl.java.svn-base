package com.maomao.ssm.service.impl.admin;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.maomao.ssm.bean.GoodsOverstockWithBLOBs;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.bean.HomeCategory;
import com.maomao.ssm.bean.HomeCategoryExample;
import com.maomao.ssm.bean.HomeCategoryExample.Criteria;
import com.maomao.ssm.bean.HomeContent;
import com.maomao.ssm.bean.HomeContentExample;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsOverstockMapper;
import com.maomao.ssm.dao.HomeCategoryMapper;
import com.maomao.ssm.dao.HomeContentMapper;
import com.maomao.ssm.dao.SystemMapperCustom;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.HomeCategoryAndContentService;
/**
 * 系统推荐,精选,推荐......商品管理
 * @author Administrator
 *
 */
@Service
public class HomeCategoryAndContentServiceImpl implements HomeCategoryAndContentService {
	@Autowired
	private HomeCategoryMapper homeCategoryMapper;
	
	@Autowired
	private HomeContentMapper homeContentMapper;
	
	@Autowired
	private GoodsOverstockMapper goodsOverstockMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private SystemMapperCustom systemMapperCustom;
	
	
	/**
	 * 查看精选商品列表
	 * imgType:推荐商品类型: 3精选 4推荐 5店铺分享 6店铺压货
	 * @param pages
	 * @param rows
	 * @return
	 */
	@Override
	public JsonData getGoodsQualityList(String keyword,Byte imgType) {
		
			//获取首页推荐位分类id
			HomeCategoryExample categoryExample = new HomeCategoryExample();
			categoryExample.setOrderByClause("sort desc");
			Criteria criteria = categoryExample.createCriteria();
			criteria.andImgTypeEqualTo((byte)imgType).andStatusEqualTo(StatusConst.HOME_CATEGORY_STATUS_ON);
			List<HomeCategory> homeCategoryList = homeCategoryMapper.selectByExample(categoryExample);
			int size = homeCategoryList.size();
			if (size <= 0 || homeCategoryList == null) {
				return JsonData.setSuccessMessage(homeCategoryList);
			}
			
			HomeCategory homeCategory = homeCategoryList.get(0);
			Integer homeCategoryId = homeCategory.getId();
			HomeContentExample contentExample = new HomeContentExample();
			contentExample.setOrderByClause("sort desc");
			com.maomao.ssm.bean.HomeContentExample.Criteria contentCriteria = contentExample.createCriteria();
			contentCriteria.andCategoryIdEqualTo(homeCategoryId).andStatusEqualTo(StatusConst.HOME_CONTENT_STATUS_ON);
			if (StringUtils.isNotBlank(keyword)) {
	//			try {
	//				keyword = new String(keyword.getBytes("ISO-8859-1"), "utf-8");
					contentCriteria.andNameLike("%"+keyword+"%");
	//			} catch (UnsupportedEncodingException e) {
	//				e.printStackTrace();
	//			}
			}
			List<HomeContent> homeContentList = homeContentMapper.selectByExample(contentExample);
			return JsonData.setSuccessMessage(homeContentList);
	}
	
	/**
	 * 添加精选商品
	 * id:买断或者普通商品id
	 */
	@Override
	public JsonData addGoodsQuality(Integer[] ids,Byte type,Byte imgType) {
		if (ids == null) {
			return JsonData.setErrorMessage("id为null");
		}
		//精选商品新增
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_QUALITY) {
			//获取精选商品分类,取第一个,下面雷同
			HomeCategory homeCategory = getHomeCategoryMethod(imgType);
			
			if (type == 1) {
				//买断商品新增
				return getGoodsOverstockWithBLOBsMethod(ids, homeCategory);
			}
			if (type == 0) { 
				//普通商品新增,不包含合卖
				return getGoodsWithBLOBsMethod(ids, homeCategory);
			}
		}
		
		//推荐商品
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_RECOMMENDED) {
			HomeCategory homeCategory = getHomeCategoryMethod(imgType);
			
			if (type == 1) {
				//买断商品新增
				return getGoodsOverstockWithBLOBsMethod(ids, homeCategory);
			}
			if (type == 0) { 
				//普通商品新增,不包含合卖
				return getGoodsWithBLOBsMethod(ids, homeCategory);
			}
		}
		
		//店铺分享
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_SHARE) {
			HomeCategory homeCategory = getHomeCategoryMethod(imgType);
			
			if (type == 1) {
				//买断商品新增
				return getGoodsOverstockWithBLOBsMethod(ids, homeCategory);
			}
			if (type == 0) { 
				//普通商品新增,不包含合卖
				return getGoodsWithBLOBsMethod(ids, homeCategory);
			}
		}
		
		//店铺压货
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_PRESSUREGOODS) {
			HomeCategory homeCategory = getHomeCategoryMethod(imgType);
			
			if (type == 1) {
				//买断商品新增
				return getGoodsOverstockWithBLOBsMethod(ids, homeCategory);
			}
			if (type == 0) { 
				//普通商品新增,不包含合卖
				return getGoodsWithBLOBsMethod(ids, homeCategory);
			}
		}
		return JsonData.setErrorMessage(imgType+"添加失败");
	}
	
	/**
	 * 普通商品新增
	 * @param id
	 * @param homeCategory
	 */
	private JsonData getGoodsWithBLOBsMethod(Integer[] ids, HomeCategory homeCategory) {
		try {
			for (Integer id : ids) {
				String url = "inapp://goodsDetail?goodsId="+id;
				Integer categoryId = homeCategory.getId();
				GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(id);
				HomeContent homeContent = new HomeContent();
				homeContent.setName(goods.getName());
				homeContent.setImg(goods.getImgs());
				homeContent.setPrice(goods.getMinPrice());
				homeContent.setReword(goods.getRebate());
				homeContent.setCategoryId(categoryId);
				homeContent.setUrl(url);
				
				Integer sortTop = systemMapperCustom.getHomeContentTop(categoryId);
				Integer sort = null;
				if (sortTop == null) {
					sort = 1;
				}else{
					sort = sortTop;
					sort++;
				}
				homeContent.setSort(sort);
				homeContent.setCreateTime(new Date());
				homeContent.setModifiedTime(new Date());
				homeContent.setStatus(StatusConst.HOME_CONTENT_STATUS_ON);
				homeContentMapper.insertSelective(homeContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("添加失败");
		}
		return JsonData.setSuccessMessage();
	}
	
	/**
	 * 压货商品新增
	 * @param id
	 * @param url 使用goodsId拼接,需要获取商品性质
	 * @param homeCategory
	 */
	private JsonData getGoodsOverstockWithBLOBsMethod(Integer[] ids, HomeCategory homeCategory) {
		try {
			
			for (Integer id : ids) {
				String url = "inapp://goodsOverstock?goodsId="+id;
				Integer categoryId = homeCategory.getId();
				
				GoodsOverstockWithBLOBs goodsOverstockWithBLOBs = goodsOverstockMapper.selectByPrimaryKey(id);
				HomeContent homeContent = new HomeContent();
				homeContent.setName(goodsOverstockWithBLOBs.getName());
				homeContent.setImg(goodsOverstockWithBLOBs.getImgs());
				homeContent.setPrice(goodsOverstockWithBLOBs.getPrice());
				homeContent.setReword(goodsOverstockWithBLOBs.getRebate());
				homeContent.setCategoryId(categoryId);
				homeContent.setUrl(url);
				
				Integer sortTop = systemMapperCustom.getHomeContentTop(categoryId);
				Integer sort = null;
				if (sortTop == null ) {
					sort = 1;
				}else{
					sort = sortTop;
					sort++;
				}
				homeContent.setSort(sort);
				homeContent.setCreateTime(new Date());
				homeContent.setModifiedTime(new Date());
				homeContent.setStatus(StatusConst.HOME_CONTENT_STATUS_ON);
				homeContentMapper.insertSelective(homeContent);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("添加失败");
		}
		return JsonData.setSuccessMessage();
	}
	
	/**
	 * 根据imgType获取首页推荐位分类
	 * @param imgType
	 * @return
	 */
	private HomeCategory getHomeCategoryMethod(Byte imgType) {
		HomeCategoryExample categoryExample = new HomeCategoryExample();
		categoryExample.createCriteria().andImgTypeEqualTo((byte)imgType).andStatusEqualTo(StatusConst.HOME_CATEGORY_STATUS_ON);
		List<HomeCategory> homeCategoryList = homeCategoryMapper.selectByExample(categoryExample);
		HomeCategory homeCategory = null;
		if (homeCategoryList.size() > 0 ) {
				homeCategory = homeCategoryList.get(0);
		}
		return homeCategory;
	}
	
	/**
	 * 删除
	 */
	@Override
	public JsonData delGoodsQuality(Integer id, Byte imgType) {
		if (id == null || imgType == null) {
			return JsonData.setErrorMessage("参数为null");
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_QUALITY) {
			return delHomeContentMethod(id);
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_RECOMMENDED) {
			return delHomeContentMethod(id);
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_SHARE) {
			return delHomeContentMethod(id);
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_PRESSUREGOODS) {
			return delHomeContentMethod(id);
		}
		return JsonData.setErrorMessage(imgType+"删除失败");
	}

	/**
	 * 删除精选|推荐|分享|压货商品
	 */
	private JsonData delHomeContentMethod(Integer id) {
		HomeContent homeContent = homeContentMapper.selectByPrimaryKey(id);
		Integer categoryId = homeContent.getCategoryId();
		Integer sort = homeContent.getSort();
		homeContent.setStatus(StatusConst.HOME_CONTENT_STATUS_DEL);//删除
		homeContentMapper.updateByPrimaryKeySelective(homeContent);
		
		HomeContentExample contentExample = new HomeContentExample();
		contentExample.createCriteria().andStatusEqualTo(StatusConst.HOME_CONTENT_STATUS_ON).andCategoryIdEqualTo(categoryId);
		List<HomeContent> homeContentList = homeContentMapper.selectByExample(contentExample);
		if (homeContentList.size() < 0) {
			return JsonData.setErrorMessage("推荐内容不存在");
		}
		for (HomeContent homeContent2 : homeContentList) {
			Integer sort2 = homeContent2.getSort();
			if (sort2 > sort) {
				sort2--;
				homeContent2.setSort(sort2);
				homeContentMapper.updateByPrimaryKeySelective(homeContent2);
			}
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 下移
	 */
	@Override
	public JsonData setGoodsQualityDown(Integer id,Byte imgType) {
		if (id == null || imgType == null) {
			return JsonData.setErrorMessage("参数为null");
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_QUALITY) {
			return getHomeContentDownMethod(id);
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_RECOMMENDED) {
			return getHomeContentDownMethod(id);
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_SHARE) {
			return getHomeContentDownMethod(id);
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_PRESSUREGOODS) {
			return getHomeContentDownMethod(id);
		}
		return JsonData.setErrorMessage("下移失败");
	}
	
	/**
	 * 上移
	 */
	@Override
	public JsonData setGoodsQualityUp(Integer id, Byte imgType) {
		if (id == null || imgType == null) {
			return JsonData.setErrorMessage("参数为null");
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_QUALITY) {
			return getHomeContentUpMethod(id);
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_RECOMMENDED) {
			return getHomeContentUpMethod(id);
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_SHARE) {
			return getHomeContentUpMethod(id);
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_PRESSUREGOODS) {
			return getHomeContentUpMethod(id);
		}
		return JsonData.setErrorMessage("上移失败");
	}
	
	/**
	 * 推荐内容上移
	 * @param id
	 * @return
	 */
	private JsonData getHomeContentUpMethod(Integer id) {
		try {
			HomeContent content = homeContentMapper.selectByPrimaryKey(id);
			Integer categoryId = content.getCategoryId();
			HomeContentExample example = new HomeContentExample();
			example.createCriteria().andIdEqualTo(id).andCategoryIdEqualTo(categoryId);
			
			List<HomeContent> homeContentList = homeContentMapper.selectByExample(example);
			if (homeContentList.size() <= 0) {
				return JsonData.setErrorMessage("首页推荐内容为空");
			}
			HomeContent homeContent = homeContentList.get(0);
			Integer sort = homeContent.getSort();
		
			HomeContentExample contentExample = new HomeContentExample();
			contentExample.createCriteria().andSortEqualTo(sort+1).andCategoryIdEqualTo(categoryId).andStatusEqualTo(StatusConst.HOME_CONTENT_STATUS_ON);
			List<HomeContent> homeContentList2 = homeContentMapper.selectByExample(contentExample);
			
//		HomeContent homeContentUp =  systemMapperCustom.getHomeContentUp(categoryId, sort);
//		Integer sortUp = homeContentUp.getSort();
//			if (sortUp == sort) {
//				return JsonData.setErrorMessage("已经在首位");
//			}
//			homeContent.setSort(sortUp);
//			homeContentUp.setSort(sort);
			
			if (homeContentList2.size() <= 0 || homeContentList2 == null) {
				return JsonData.setErrorMessage("已经在首位");
			}
			
			HomeContent homeContent2 = homeContentList2.get(0);
			homeContent.setSort(homeContent2.getSort());
			homeContent2.setSort(sort);
			homeContentMapper.updateByPrimaryKeySelective(homeContent);
			homeContentMapper.updateByPrimaryKeySelective(homeContent2);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("上移失败");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 推荐位内容下移
	 * @param id
	 * @return
	 */
	private JsonData getHomeContentDownMethod(Integer id) {
		try {
			HomeContent content = homeContentMapper.selectByPrimaryKey(id);
			Integer categoryId = content.getCategoryId();
			
			HomeContentExample example = new HomeContentExample();
			example.createCriteria().andIdEqualTo(id).andCategoryIdEqualTo(categoryId);
			
			List<HomeContent> homeContentList = homeContentMapper.selectByExample(example);
			if (homeContentList.size() <= 0) {
				return JsonData.setErrorMessage("首页推荐内容为空");
			}
			HomeContent homeContent = homeContentList.get(0);
			Integer sort = homeContent.getSort();
			
			//获取最小sort
//			Integer minSort = systemMapperCustom.getHomeContentMinSort();
//			if (sort == minSort) {
//				return JsonData.setErrorMessage("已经在尾位");
//			}
			HomeContentExample contentExample = new HomeContentExample();
			contentExample.createCriteria().andSortEqualTo(sort-1).andCategoryIdEqualTo(categoryId).andStatusEqualTo(StatusConst.HOME_CONTENT_STATUS_ON);
			List<HomeContent> homeContentList2 = homeContentMapper.selectByExample(contentExample);
			if (homeContentList2.size() <= 0 || homeContentList2 == null) {
				return JsonData.setErrorMessage("已经在尾位");
			}
			HomeContent homeContent2 = homeContentList2.get(0);
			homeContent.setSort(homeContent2.getSort());
			homeContent2.setSort(sort);
			homeContentMapper.updateByPrimaryKeySelective(homeContent);
			homeContentMapper.updateByPrimaryKeySelective(homeContent2);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("下移失败");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 置顶
	 */
	@Override
	public JsonData setGoodsQualityTop(Integer id, Byte imgType) {
		if (id == null || imgType == null) {
			return JsonData.setErrorMessage("参数为null");
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_QUALITY) {
			return getHomeContentTopMethod(id);
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_RECOMMENDED) {
			return getHomeContentTopMethod(id);
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_SHARE) {
			return getHomeContentTopMethod(id);
		}
		if (imgType == StatusConst.HOMECATEGORY_IMG_TYPE_PRESSUREGOODS) {
			return getHomeContentTopMethod(id);
		}
		return JsonData.setErrorMessage("置顶失败");
	}
	/**
	 * 推荐位内容置顶
	 * @param id
	 * @return
	 */
	private JsonData getHomeContentTopMethod(Integer id) {
		try {
			HomeContent homeContent = homeContentMapper.selectByPrimaryKey(id);
			Integer categoryId = homeContent.getCategoryId();
			Integer sort = homeContent.getSort();
			//获取最大sort
			Integer maxSort = systemMapperCustom.getHomeContentTop(categoryId);
			if (sort == maxSort) {
				return JsonData.setErrorMessage("已经在首位");
			}
			
			//获取推荐内容列表
			HomeContentExample example = new HomeContentExample();
			example.createCriteria().andStatusEqualTo(StatusConst.HOME_CONTENT_STATUS_ON).andCategoryIdEqualTo(categoryId);
			List<HomeContent> homeContentList = homeContentMapper.selectByExample(example );
			if (homeContentList.size() <= 0) {
				return JsonData.setErrorMessage("推荐内容不存在");
			}
			
			for (HomeContent homeContent2 : homeContentList) {
				Integer sort2 = homeContent2.getSort();
				//如果选中对象sort小于遍历的sort则-1
				if (sort2 > sort) {
					sort2--;
					homeContent2.setSort(sort2);
					homeContentMapper.updateByPrimaryKeySelective(homeContent2);
				}
			}
			homeContent.setSort(maxSort);
			homeContentMapper.updateByPrimaryKeySelective(homeContent);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("置顶失败");
		}
		return JsonData.setSuccessMessage();
	}
}
