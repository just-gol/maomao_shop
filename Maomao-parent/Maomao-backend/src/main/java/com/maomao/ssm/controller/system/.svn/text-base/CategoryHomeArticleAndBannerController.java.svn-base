package com.maomao.ssm.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.maomao.ssm.bean.CategoryHomeArticle;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.CategoryHomeArticleAndBannerService;

/**
 * 系统设置:推文活动管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="system")
public class CategoryHomeArticleAndBannerController {
	/**
	 * 添加(修改)推文
	 */
	@Autowired
	private CategoryHomeArticleAndBannerService categoryHomeArticleAndBannerService;
	
	@RequestMapping(value = "/addTweets.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addTweets(Integer categoryHomeArticleId, String introduction,String url,String title,Long effectiveTime,Byte status){
		return categoryHomeArticleAndBannerService.addCategoryHomeArticle(categoryHomeArticleId,introduction,url,title,effectiveTime,status);
	}
	
	/**
	 * 查看推文列表
	 */
	@RequestMapping(value = "/getTweetsList.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getTweetsList(Integer pages, Integer rows){
		return categoryHomeArticleAndBannerService.getCategoryHomeArticleList(pages,rows);
	}
	
	
	/**
	 * 查询指定推文
	 */
	@RequestMapping(value = "/getTweets.action", method = RequestMethod.GET)
	@ResponseBody
	JsonData getTweets(Integer categoryHomeArticleId){
		return categoryHomeArticleAndBannerService.getCategoryHomeArticle(categoryHomeArticleId);
	}
	
	/**
	 * 删除推文
	 */
	@RequestMapping(value = "/delTweets.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData delTweets(Integer categoryHomeArticleId,Byte status){
		return categoryHomeArticleAndBannerService.delCategoryHomeArticle(categoryHomeArticleId,status);
	}
	
	/**
	 * 添加修改活动管理
	 */
	@RequestMapping(value = "/addAndUpdateActivity.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData addAndUpdateActivity(String androidImgURL, String iosImgURL, String href){
		return categoryHomeArticleAndBannerService.addAndUpdateBanner(androidImgURL,iosImgURL,href);
	}
	
	/**
	 * 删除活动管理
	 */
	@RequestMapping(value = "/delActivity.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonData delActivity(Integer bannerId){
		return categoryHomeArticleAndBannerService.delBanner(bannerId);
	}
	
	/**
	 * 查看活动管理
	 */
	@RequestMapping(value = "/getActivity.action", method = RequestMethod.GET)
	@ResponseBody
	public JsonData getActivity(){
		return categoryHomeArticleAndBannerService.getBanner();
	}
	
	
	
}
