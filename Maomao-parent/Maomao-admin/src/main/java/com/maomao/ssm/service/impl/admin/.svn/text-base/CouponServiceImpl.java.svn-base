package com.maomao.ssm.service.impl.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.Coupon;
import com.maomao.ssm.bean.CouponExample;
import com.maomao.ssm.bean.CouponExample.Criteria;
import com.maomao.ssm.constant.CouponConts;
import com.maomao.ssm.dao.CouponMapper;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.CouponService;

@Service
public class CouponServiceImpl implements CouponService {
	
	@Autowired
	private CouponMapper couponMapper;
	
	/**
	 * 查询优惠券列表
	 */
	@Override
	public JsonData getCouponList(Integer pages, Integer rows, Long startTime, Long endTime, String queryString) {
		PageBean pagebean = null;
		try {
			// 分页
			Page startPage = PageHelper.startPage(pages, rows);
			CouponExample couponExample = new CouponExample();
			Criteria create = couponExample.createCriteria();
			create.andStatusEqualTo(CouponConts.COUPON_STARTUS_NOT_DEL);
//			couponExample.or(couponExample.createCriteria().andValidityTermEqualTo(CouponConts.COUPON_TIME_FOREVER));
	//		System.out.println("startTime:"+startTime);
			Integer day = null;
			if (startTime != null && endTime != null) {
				 day = (int) ((endTime- startTime) / (24 * 60 * 60 * 1000));
	//			 System.out.println("day:"+day);
			}
			
			if (queryString != null) {
					queryString = new String(queryString.getBytes("iso8859-1"),"UTF-8");
	//				System.out.println("queryString:"+queryString);
					if ("注册".contains(queryString)) {
						create.andTypeEqualTo(CouponConts.COUPON_TYPE_REGISTER);
					}
					if ("邀请".contains(queryString)) {
						create.andTypeEqualTo(CouponConts.COUPON_TYPE_INVITATION);
					}
					if ("开店".contains(queryString)) {
						create.andTypeEqualTo(CouponConts.COUPON_TYPE_SHOP);
					}
					if ("通用".contains(queryString)) {
						create.andTypeEqualTo(CouponConts.COUPON_TYPE_NORMAL);
					}
			}
			if (startTime != null) {
				create.andUseStartTimeGreaterThanOrEqualTo(new Date(startTime));
			}
			if (day != null) {
				create.andValidityTermLessThanOrEqualTo(day);
			}
			
			//查询优惠券列表
			List<Coupon> couponList = couponMapper.selectByExample(couponExample);
			List<Map<String,Object>> list = new ArrayList<>();
			if (couponList != null && couponList.size() > 0) {
				for (Coupon coupon : couponList) {
					Map<String,Object> map = new HashMap<String, Object>();
					//优惠券id
					map.put("couponId", coupon.getId());
					
					//使用范围
					map.put("couponName",coupon.getName());
					
					//满减下限 
					if (coupon.getAvaiableMoney() != 0) {
						map.put("availableMoney",coupon.getAvaiableMoney());
					}
					if (coupon.getAvaiableMoney() == 0) {
						map.put("availableMoney","无限制");
					}
					
					//折扣金额
					map.put("discount",coupon.getDiscount());
					
					//有效期限 
					if (!coupon.getValidityTerm().equals(CouponConts.COUPON_TIME_FOREVER) && coupon.getUseStartTime() != null) {
						//开始
						map.put("useStartTime", coupon.getUseStartTime());
						Integer time = coupon.getValidityTerm();
						//往后推迟的天数换算成毫秒值
						Long validityTerm = new Long(time);
						validityTerm = validityTerm * 24 * 60 * 60 * 1000; 
						Long useStartTime = coupon.getUseStartTime().getTime();
						map.put("userEndTime",new Date(validityTerm+useStartTime));
					}
					
					//coupon.getValidityTerm() == 36500 则有效期永久
					if (coupon.getValidityTerm().equals(CouponConts.COUPON_TIME_FOREVER)) {
						map.put("validityTerm", "永久");
					}
					
					//如果coupon.getUseStartTime() == null 则优惠券领取后多少天有效
					if (coupon.getUseStartTime() == null && (!coupon.getValidityTerm().equals(CouponConts.COUPON_TIME_FOREVER))) {
						map.put("validityTerm", coupon.getValidityTerm());
					}
					
					//发放数量  
					if (coupon.getStockTotal() != null) {
						if (coupon.getStockTotal() == -1) {
							map.put("sendStock","不限");//发放数量
//							map.put("stock","-"); //剩余库存
						}
						if (coupon.getStockTotal() != -1) {
							map.put("sendStock",coupon.getStockTotal());//发放数量
						}
					}
					
					//剩余数量
					if (coupon.getStock() != null) {
						if (coupon.getStock() == -1) {
							map.put("stock","-"); //剩余库存
						}
						
						if (coupon.getStock() != -1) {
							map.put("stock",coupon.getStock()); //剩余库存
						}
					}
					
					//优惠券使用范围
					map.put("type", coupon.getType());
					list.add(map);
				}
			}
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(list);
			
			 pagebean = new PageBean();
			// 获取总条数
			pagebean.setTotal(startPage.getTotal());
	//		System.out.println("总条数:"+startPage.getTotal());
			// 获取当前页数据
			pagebean.setRows(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonData.setSuccessMessage(pagebean);
	}

	/**
	 * 新增优惠券
	 */
	@Override
	public JsonData addCoupon(String couponName, Byte type, Long availableMoney, Long discount, Long useStartTime,Long useEndTime,
			Integer validityTerm, Integer getNum,Integer stockTotal,Long startTime ,Long endTime) {
		Coupon coupon = new Coupon();
		coupon.setName(couponName); //优惠券名称
		coupon.setType(type); //类型 
		coupon.setAvaiableMoney(availableMoney);//满减下限 ,0表示无限制
		coupon.setDiscount(discount); //折扣金额
		//有效期,永久
		if (validityTerm != null && validityTerm.equals(CouponConts.COUPON_TIME_FOREVER)) {
			coupon.setValidityTerm(CouponConts.COUPON_TIME_FOREVER);
		}else if (useStartTime == null && validityTerm != null && (!validityTerm.equals(CouponConts.COUPON_TIME_FOREVER))) { //如果开始时间为null,则是领取时间
			coupon.setValidityTerm(validityTerm); //领取后多少天有效
		}else{
			//有效期限
			coupon.setUseStartTime(new Date(useStartTime));//开始
			
			Integer start = (int) (useStartTime / (24 * 60 * 60 * 1000)); //开始
			Integer end = (int) (useEndTime / (24 * 60 * 60 * 1000));	//结束
			coupon.setValidityTerm(end-start);
		}
		
		//发放数量,如果stockTotal=-1 发放数量不限
		coupon.setStockTotal(stockTotal);
		//剩余数量
		coupon.setStock(stockTotal);
		
		//如果领取数量为null默认为1张
		if (getNum == null) {
			coupon.setGetNum(1);
		}else{
			coupon.setGetNum(getNum);
		}
		
		//发放时间,如果是null表示不限制时间
		if (startTime != null) {
			coupon.setStartTime(new Date(startTime));
		}
		if (endTime != null) {
			coupon.setEndTime(new Date(endTime));
		}
		coupon.setStatus(CouponConts.COUPON_STARTUS_NOT_DEL);
		coupon.setCreateTime(new Date());
		couponMapper.insertSelective(coupon);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 删除优惠券
	 */
	@Override
	public JsonData delCoupon(Integer couponId) {
		if (couponId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
		coupon.setStatus(CouponConts.COUPON_STARTUS_DEL);
		coupon.setModifiedTime(new Date());
		couponMapper.updateByPrimaryKeySelective(coupon);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 查看指定优惠券
	 */
	@Override
	public JsonData getCoupon(Integer couponId) {
		if (couponId == null) {
			return JsonData.setErrorMessage("参数非法");
		}
		Coupon coupon = null;
		try {
			coupon = couponMapper.selectByPrimaryKey(couponId);
			if (coupon.getStatus() == CouponConts.COUPON_STARTUS_DEL) {
				return JsonData.setErrorMessage("该优惠券不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("查询失败");
		}
		return JsonData.setSuccessMessage(coupon);
	}
}
