package com.maomao.ssm.service.impl.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.maomao.ssm.bean.Banner;
import com.maomao.ssm.bean.BannerExample;
import com.maomao.ssm.bean.CategoryHomeArticle;
import com.maomao.ssm.bean.CategoryHomeArticleExample;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.BannerMapper;
import com.maomao.ssm.dao.CategoryHomeArticleMapper;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.CategoryHomeArticleAndBannerService;

/**
 * 系统设置
 * 
 * @author Administrator
 *
 */
@Service
public class CategoryHomeArticleAndBannerServiceImpl implements CategoryHomeArticleAndBannerService {

	@Autowired
	private CategoryHomeArticleMapper categoryHomeArticleMapper;

	@Autowired
	private BannerMapper bannerMapper;

	/**
	 * 添加(修改)推文
	 */
	@Override
	public JsonData addCategoryHomeArticle(Integer categoryHomeArticleId, String introduction, String url, String title, Long effectiveTime,
			Byte status) {
		try {
			// 添加
			if (categoryHomeArticleId == null) {
				// 获取当前时间
				Date date = new Date();
				CategoryHomeArticle categoryHomeArticle = new CategoryHomeArticle();
				categoryHomeArticle.setIntroduction(introduction);
				categoryHomeArticle.setUrl(url);
				categoryHomeArticle.setTitle(title);
				categoryHomeArticle.setCreateTime(date);
				categoryHomeArticle.setModifiedTime(date);
				categoryHomeArticle.setEffectiveTime(new Date(effectiveTime));
				// 如果有限期时间大于当前时间
				if (effectiveTime > date.getTime()) {
					categoryHomeArticle.setStatus(StatusConst.CATEGORY_HOME_ARTICLE_STATUS_NORMAL);// 可用
				} else {
					categoryHomeArticle.setStatus(StatusConst.CATEGORY_HOME_ARTICLE_STATUS_DEL);// 不可用
				}
				categoryHomeArticleMapper.insertSelective(categoryHomeArticle);
			} 

			if (categoryHomeArticleId != null) {// 修改
				// 获取当前时间
				Date date = new Date();
				CategoryHomeArticle categoryHomeArticle = categoryHomeArticleMapper.selectByPrimaryKey(categoryHomeArticleId);
				categoryHomeArticle.setIntroduction(introduction);
				categoryHomeArticle.setUrl(url);
				categoryHomeArticle.setTitle(title);
				categoryHomeArticle.setCreateTime(date);
				categoryHomeArticle.setModifiedTime(date);
				categoryHomeArticle.setEffectiveTime(new Date(effectiveTime));

				// 如果有限期时间大于当前时间
				if (effectiveTime > date.getTime()) {
					categoryHomeArticle.setStatus(StatusConst.CATEGORY_HOME_ARTICLE_STATUS_NORMAL);// 可用
				} else {
					categoryHomeArticle.setStatus(StatusConst.CATEGORY_HOME_ARTICLE_STATUS_DEL);// 不可用
				}
				categoryHomeArticleMapper.updateByPrimaryKeySelective(categoryHomeArticle);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 查看推文列表
	 */
	@Override
	public JsonData getCategoryHomeArticleList(Integer pages, Integer rows) {
		// 分页
		PageBean pagebean = null;
		try {
			if (pages == null) {
				pages = 1;
			}
			if (rows == null) {
				rows = 10;
			}
			//获取当前时间
			Date nowDate = new Date();
			
			Page page = PageHelper.startPage(pages, rows);
			
			//查询出未被删除的推文
			CategoryHomeArticleExample example = new CategoryHomeArticleExample();
			example.createCriteria().andStatusNotEqualTo(StatusConst.CATEGORY_HOME_ARTICLE_STATUS_DEL);
			List<CategoryHomeArticle> categoryHomeArticleList = categoryHomeArticleMapper.selectByExample(example);
			
			//判断推文是否过期
			if (categoryHomeArticleList.size() > 0 && categoryHomeArticleList != null) {
				for (CategoryHomeArticle categoryHomeArticle : categoryHomeArticleList) {
					if (categoryHomeArticle.getEffectiveTime().getTime() < nowDate.getTime()) {
						categoryHomeArticle.setStatus(StatusConst.CATEGORY_HOME_ARTICLE_STATUS_DEL);
						categoryHomeArticle.setModifiedTime(new Date());
						categoryHomeArticleMapper.updateByPrimaryKeySelective(categoryHomeArticle);
					}
				}
			}
			
			//查询出未过期的推文
			CategoryHomeArticleExample categoryHomeArticleExample = new CategoryHomeArticleExample();
			categoryHomeArticleExample.createCriteria().andStatusNotEqualTo(StatusConst.CATEGORY_HOME_ARTICLE_STATUS_DEL);
			List<CategoryHomeArticle> homeArticleList = categoryHomeArticleMapper.selectByExample(categoryHomeArticleExample);
			
			pagebean = new PageBean();
			if (homeArticleList.size() > 0 && homeArticleList != null) {
				pagebean.setRows(homeArticleList);
				pagebean.setTotal(page.getTotal());
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("查询失败");
		}
		return JsonData.setSuccessMessage(pagebean);
	}

	/**
	 * 查询指定推文
	 */
	@Override			
	public JsonData getCategoryHomeArticle(Integer categoryHomeArticleId) {
		if (categoryHomeArticleId == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		return JsonData.setSuccessMessage(categoryHomeArticleMapper.selectByPrimaryKey(categoryHomeArticleId));
	}

	/**
	 * 删除推文
	 */
	@Override						 
	public JsonData delCategoryHomeArticle(Integer categoryHomeArticleId,Byte status) {
		if (categoryHomeArticleId == null || status == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		CategoryHomeArticle categoryHomeArticle = categoryHomeArticleMapper.selectByPrimaryKey(categoryHomeArticleId);
		categoryHomeArticle.setStatus(status);
		categoryHomeArticleMapper.updateByPrimaryKeySelective(categoryHomeArticle);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 添加修改活动管理
	 */
	@Override
	public JsonData addAndUpdateBanner(String androidImgURL, String iosImgURL, String href) {
		if (androidImgURL == null || iosImgURL == null || href == null)
			return JsonData.setErrorMessage("参数错误");
		try {
				BannerExample bannerExample = new BannerExample();
				bannerExample.createCriteria().andTypeEqualTo(StatusConst.BANNER_STATUS_HOME);//首页
				List<Banner> bannerList = bannerMapper.selectByExample(bannerExample);
				if (bannerList.size() <= 0 || bannerList == null) {
					return JsonData.setSuccessMessage(bannerList);
				}
				Banner banner = bannerList.get(0);
				banner.setImg(androidImgURL + ";" + iosImgURL);
				banner.setHref(href);
				banner.setType(StatusConst.BANNER_STATUS_HOME);//首页显示
				banner.setStatus(StatusConst.BANNER_STATUS_ON);
				banner.setModifiedTime(new Date());
				bannerMapper.updateByPrimaryKeySelective(banner);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
		return JsonData.setSuccessMessage();
	}

	/**
	 * 删除活动管理
	 */
	@Override
	public JsonData delBanner(Integer bannerId) {
		if (bannerId == null)
			return JsonData.setErrorMessage("参数错误");
		Banner banner = bannerMapper.selectByPrimaryKey(bannerId);
		banner.setStatus(StatusConst.BANNER_STATUS_DEL);// 删除
		bannerMapper.updateByPrimaryKeySelective(banner);
		return JsonData.setSuccessMessage();
	}
	
	/**
	 * 查看活动管理
	 */
	@Override
	public JsonData getBanner() {
		BannerExample bannerExample = new BannerExample();
		bannerExample.createCriteria().andTypeEqualTo(StatusConst.BANNER_STATUS_HOME);//首页
		List<Banner> bannerList = bannerMapper.selectByExample(bannerExample);
		if (bannerList.size() <= 0 || bannerList == null) {
			return JsonData.setSuccessMessage(bannerList);
		}
		return JsonData.setSuccessMessage(bannerList.get(0));
	}

}
