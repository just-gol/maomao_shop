package com.maomao.ssm.service.impl.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.AdminExample;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserExample;
import com.maomao.ssm.bean.UserFeedback;
import com.maomao.ssm.bean.UserFeedbackExample;
import com.maomao.ssm.bean.UserFeedbackExample.Criteria;
import com.maomao.ssm.dao.AdminMapper;
import com.maomao.ssm.dao.GoodsCategoryMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.UserFeedbackMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.JedisClientPool;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.UserFeedBackService;

/**
 * @author:wzy
 * @descrption:在线客服
 * @version:
 * @date:2018年5月18日
 */
@Service
public class UserFeedBackServiceImpl implements UserFeedBackService {
	@Autowired
	private UserFeedbackMapper feedbackMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private JedisClientPool jedisClientPool;
	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	/**
	 * 获取问题返回列表
	 */
	public JsonData getFeebackListUnSolved(Integer pages, Integer rows, Long startTime, Long endTime, String keywords,Integer adminId,Byte type) {
		pages = pages < 1 ? 1 : pages;
		rows = rows < 1 ? 1 : rows;
		
		if (type == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		//==============
		/*
		GoodsCategoryExample categoryExample = new GoodsCategoryExample();
		categoryExample.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON);
		List<GoodsCategory> categoryList = goodsCategoryMapper.selectByExample(categoryExample);
		if (categoryList!=null && categoryList.size()>0) {
			GoodsExample example = new GoodsExample();
			for (GoodsCategory goodsCategory : categoryList) {
				example.clear();
				example.setOrderByClause("crate_time desc");
				example.createCriteria().andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE).andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED).andCategoryIdEqualTo(goodsCategory.getId());
				 List<GoodsWithBLOBs> goodsList = goodsMapper.selectByExampleWithBLOBs(example);
				if (goodsList!=null && goodsList.size()>0) {
					for (int i = 1;i<=goodsList.size();i++) {
						GoodsWithBLOBs goodsWithBLOBs = goodsList.get(i-1);
						goodsWithBLOBs.setSort(i);
						goodsMapper.updateByPrimaryKeyWithBLOBs(goodsWithBLOBs);
					}
				}
				
			}
		}
		*/
		
		
		//===============
	
		UserFeedbackExample example = new UserFeedbackExample();
		UserExample example2 = new UserExample();
		List<Integer> ids = new ArrayList<Integer>();
		Criteria criteria = example.createCriteria();
		if (startTime != null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(new Date(startTime));
		}
		if (endTime != null) {
			criteria.andCreateTimeLessThanOrEqualTo(new Date(endTime));
		}
		if(adminId!=null){
			criteria.andAdminIdEqualTo(adminId);
		}
		if (type == 0) {//未处理
			criteria.andReplyIsNull();
		}else {
			criteria.andReplyIsNotNull();
		}
		if (StringUtils.isNotBlank(keywords)) {
			example2.or(example2.createCriteria().andNameLike("%"+keywords+"%"));
			example2.or(example2.createCriteria().andMobileLike("%"+keywords+"%"));
			List<User> userList = userMapper.selectByExample(example2);
			if (userList != null && userList.size() > 0) {
				for (User user : userList) {
					ids.add(user.getId());
				}
				example.or().andContentLike("%"+keywords+"%");
				criteria.andUserIdIn(ids);
			}else{
				criteria.andContentLike("%"+keywords+"%");
			}
		}
		
		Page page = PageHelper.startPage(pages, rows);
		List<UserFeedback> feedbackList = feedbackMapper.selectByExample(example);
		List<Map<String, Object>> retListList = new ArrayList<Map<String,Object>>();
		
		if (feedbackList!=null && feedbackList.size()>0) {
			for (UserFeedback userFeedback : feedbackList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", userFeedback.getId());
				map.put("time", userFeedback.getCreateTime());//返回时间
				User user = userMapper.selectByPrimaryKey(userFeedback.getUserId());
				if (user!=null) {
					map.put("name", user.getName());//姓名
					String idCard = user.getIdCard();
					String sex = "";
					if (StringUtils.isNotBlank(idCard)) {
						if (idCard.length()==18) {
							sex = Integer.parseInt(idCard.substring(16).substring(0,1))%2==0?"女":"男";
						}else if(idCard.length()==15) {
							sex = Integer.parseInt(idCard.substring(14,15))%2==0?"女":"男";
						}else {
							sex = "未知";
						}
					}
					map.put("sex", sex);
					map.put("mobile", user.getMobile());
					map.put("content", userFeedback.getContent());
					if (type==1) {
						Admin admin = adminMapper.selectByPrimaryKey(userFeedback.getAdminId());
						map.put("admin",admin==null?"": admin.getName());//处理人
						long resolveTime = (userFeedback.getReplyTime().getTime()-userFeedback.getCreateTime().getTime())/1000/60/60;
						map.put("resolveTime", resolveTime+"小时"	);//处理周期
					}
					retListList.add(map);
				}
			}
		}
		PageBean pageBean = new PageBean();
		pageBean.setTotal(page.getTotal());
		pageBean.setRows(retListList);
		return JsonData.setSuccessMessage(pageBean);
	}
	
	/**
	 * 获取用户反馈处理人员下拉列表
	 */
	public JsonData getFeedbackAdminList() {
		
		UserFeedbackExample example = new UserFeedbackExample();
		example.createCriteria().andAdminIdIsNotNull();
		List<UserFeedback> feedbackList = feedbackMapper.selectByExample(example);
		Set<Integer> set = new HashSet<Integer>();
		if (feedbackList!=null && feedbackList.size()>0) {
			for (UserFeedback feedback : feedbackList) {
				set.add(feedback.getAdminId());
			}
		}
		if(set.size()>0){
			List<Integer > ids =new ArrayList<Integer>();
			ids.addAll(set);
			AdminExample example2 = new AdminExample();
			example2.createCriteria().andIdIn(ids);
			List<Admin> adminList = adminMapper.selectByExample(example2);
			List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
			if (adminList!=null && adminList.size()>0) {
				for (Admin admin : adminList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", admin.getName());
					map.put("id", admin.getId());
					returnList.add(map);
				}
			}
			return JsonData.setSuccessMessage(returnList);
		}
		
		return JsonData.setSuccessMessage();
	}
	
	/*
	 * 获取未处理反馈详情
	 */
	public JsonData getFeedbackDetail(Integer id,String reason,Byte type,Integer adminId) {//type:0未处理 1:已处理
		if (id == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		if (StringUtils.isNotBlank(reason)) {
			
		}
		
		UserFeedback userFeedback = feedbackMapper.selectByPrimaryKey(id);
		if (StringUtils.isNotBlank(reason)) {
			userFeedback.setReplyTime(new Date());
			userFeedback.setReply(reason);
			userFeedback.setAdminId(adminId);
			feedbackMapper.updateByPrimaryKeySelective(userFeedback);
			return JsonData.setSuccessMessage();
		}
		User user = userMapper.selectByPrimaryKey(userFeedback.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", user.getName());
		map.put("mobile", user.getMobile());
		String sex = "";
		if (StringUtils.isNotBlank(user.getIdCard())) {
			String idCard = user.getIdCard();
			if (idCard.length()==18) {
				sex = Integer.parseInt(idCard.substring(16).substring(0,1))%2==0?"女":"男";
			}else if(idCard.length()==15) {
				sex = Integer.parseInt(idCard.substring(14,15))%2==0?"女":"男";
			}else {
				sex = "未知";
			}
		}
		map.put("sex", sex);
		map.put("time",userFeedback.getCreateTime());
		map.put("content", userFeedback.getContent());
		if (type!=null && type==1) {
			Admin admin = adminMapper.selectByPrimaryKey(userFeedback.getAdminId());
			map.put("adminName", admin.getName());//处理人
			long resolveTime = (userFeedback.getReplyTime().getTime()-userFeedback.getCreateTime().getTime())/1000/60/60;
			map.put("resolveTime", resolveTime+"小时"	);//处理周期
			map.put("reason", userFeedback.getReply());
		}
		return JsonData.setSuccessMessage(map);
	}
}












































