package com.maomao.ssm.service.impl.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.AdminInfo;
import com.maomao.ssm.bean.AdminInfoExample;
import com.maomao.ssm.bean.Goods;
import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.bean.GoodsSubscriptionRecord;
import com.maomao.ssm.bean.GoodsUser;
import com.maomao.ssm.bean.GoodsUserExample;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserApply;
import com.maomao.ssm.bean.UserApplyCredit;
import com.maomao.ssm.bean.UserApplyCreditExample;
import com.maomao.ssm.bean.UserApplyCustom;
import com.maomao.ssm.bean.UserApplyExample;
import com.maomao.ssm.bean.UserBank;
import com.maomao.ssm.bean.UserBankExample;
import com.maomao.ssm.bean.UserExample;
import com.maomao.ssm.bean.UserExample.Criteria;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.AdminInfoMapper;
import com.maomao.ssm.dao.AdminMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsSubscriptionRecordMapperCustom;
import com.maomao.ssm.dao.GoodsUserMapper;
import com.maomao.ssm.dao.UserApplyCreditMapper;
import com.maomao.ssm.dao.UserApplyMapper;
import com.maomao.ssm.dao.UserApplyMapperCustom;
import com.maomao.ssm.dao.UserBankMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.CreditLineService;
import com.maomao.ssm.service.common.NoticeRecordService;

/**
 * @author:wzy
 * @descrption:
 * @version:
 * @date:2018年4月3日
 */
@Service
public class CreditLineServiceImpl implements CreditLineService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserBankMapper userBankMapper;
	@Autowired
	private UserApplyMapper userApplyMapper;
	@Autowired
	private GoodsUserMapper goodsUserMapper;
	@Autowired
	private GoodsSubscriptionRecordMapperCustom subscriptionCustom;
	@Autowired
	private AdminInfoMapper adminInfoMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private UserApplyCreditMapper userApplyCreditMapper;
	@Autowired
	private UserApplyMapperCustom userApplyMapperCustom;
	@Autowired
	private NoticeRecordService noticeRecordService;

	/**
	 * 查看店铺授信列表 type:0虚拟授信 1真实授信
	 */
	public JsonData getShopCreditLineList(Integer pages, Integer rows, Byte type, String keywords) {
		if (pages < 1) {
			pages = 1;
		}
		Page page = PageHelper.startPage(pages, rows);
		List<User> users = null;
		List<Map<String, Object>> retrunList = null;

		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(keywords)) {
			example.or().andNameLike("%" + keywords + "%").andTypeEqualTo(UserConts.USER_TYPE_B);
			example.or().andMobileLike("%" + keywords + "%").andTypeEqualTo(UserConts.USER_TYPE_B);
		} else {
			criteria.andTypeEqualTo(UserConts.USER_TYPE_B);
		}

		if (type != null) {
			example.createCriteria().andCreditRealEqualTo(type);

		}

		users = userMapper.selectByExample(example);

		if (users != null && users.size() > 0) {
			retrunList = new ArrayList<Map<String, Object>>();
			for (User user : users) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", user.getId());
				map.put("account", user.getMobile());
				map.put("name", user.getName());
				if (user.getCreditReal() == 0) {
					map.put("creditType", "虚拟授信");
				} else {
					map.put("creditType", "真实授信");
				}
				map.put("leaveMoney", user.getCreditSurplus());// 剩余额度
				map.put("totalMoney", user.getCreditTotal());// 总信用额度
				map.put("useMoney", (user.getCreditTotal() == null ? 0 : user.getCreditTotal())
						- (user.getCreditSurplus() == null ? 0 : user.getCreditSurplus()));// 剩余额度
				map.put("time", user.getRepayTime());
				retrunList.add(map);
			}
		}

		PageBean pageBean = new PageBean();
		pageBean.setTotal(page.getTotal());
		pageBean.setRows(retrunList);
		return JsonData.setSuccessMessage(pageBean);
	}

	/**
	 * 编辑店铺授信管理
	 */
	public JsonData updateShopCreditLineById(Integer id, Byte type, Long money, Long time) {
		if (id == null || id < 0) {
			return JsonData.setErrorMessage("参数错误");
		}

		User user = userMapper.selectByPrimaryKey(id);
		if (type != null) {
			user.setCreditReal(type);
			user.setCreditSurplus(user.getCreditSurplus() - user.getCreditTotal() + money);
			user.setCreditTotal(money);
		}
		if (time != null) {
			user.setRepayTime(new Date(time));
		}
		try {
			userMapper.updateByPrimaryKeySelective(user);
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonData.setErrorMessage("参数错误");
		}

	}

	/**
	 * 根据id查看店铺授信管理
	 */
	public JsonData getShopCreditLineById(Integer id) {
		if (id == null || id < 0) {
			return JsonData.setErrorMessage("参数错误");
		}

		User user = userMapper.selectByPrimaryKey(id);
		if (user != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", user.getName());// 姓名
			map.put("idCard", user.getIdCard());// 身份证号
			map.put("mobile", user.getMobile());// 登录账号
			UserBankExample example = new UserBankExample();
			example.createCriteria().andUserIdEqualTo(user.getId());
			List<UserBank> list = userBankMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				map.put("bank", list);
			} else {
				map.put("bank", "暂无可用银行卡");
			}
			UserApplyExample example2 = new UserApplyExample();
			example2.createCriteria().andUserIdEqualTo(user.getId());
			example2.setOrderByClause("id DESC");
			List<UserApply> list2 = userApplyMapper.selectByExample(example2);
			if (list2 != null && list2.size() > 0) {
				map.put("applyTime", list2.get(0).getCreateTime());
			}
			map.put("creditType", user.getCreditReal());// 0虚拟授信 1真实授信
			map.put("money", user.getCreditTotal());// 授信金额
			map.put("time", user.getRepayTime());// 还款截止时间

			GoodsUserExample example3 = new GoodsUserExample();
			example3.createCriteria().andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE).andUserIdEqualTo(user.getId());
			List<GoodsUser> list3 = goodsUserMapper.selectByExample(example3);
			map.put("onsaleGoods", (list3 != null && list3.size() > 0) ? list3.size() : 0);// 已上架商品数量

			/*
			 * GoodsSubscriptionRecordExample example4 = new
			 * GoodsSubscriptionRecordExample();
			 * example4.createCriteria().andUserIdEqualTo(user.getId());
			 * List<GoodsSubscriptionRecord> list4 = recordMapper.selectByExample(example4);
			 * Set<Integer> set = null; if (list4!= null && list4.size()>0) { set = new
			 * HashSet<Integer>(); for (GoodsSubscriptionRecord record : list4) {
			 * set.add(record.getGoodsSubscriptionId()); } }
			 */

			List<GoodsSubscriptionRecord> list4 = subscriptionCustom.selectGroupByGoodsSubscriptionId(user.getId());
			map.put("subscription", (list4 != null && list4.size() > 0) ? list4.size() : 0);// 认筹商品数量
			map.put("useMoney", user.getCreditTotal() - user.getCreditSurplus());// 认筹金额
			return JsonData.setSuccessMessage(map);
		}
		return JsonData.setSuccessMessage();
	}

	// 查看公司贷款管理列表
	public JsonData getCompanyCreditLineList(Integer pages, Integer rows, String keywords) {
		if (pages < 1) {
			pages = 1;
		}

		Page page = PageHelper.startPage(pages, rows);
		List<User> users = null;
		List<Map<String, Object>> retrunList = null;

		AdminInfoExample example2 = new AdminInfoExample();
		if (StringUtils.isNotBlank(keywords)) {
			example2.or().andLegalPersonMobileLike("%" + keywords + "%");
			example2.or().andCompanyNameLike("%" + keywords + "%");
			example2.or().andLeaglPersonLike("%" + keywords + "%");
		}
		List<AdminInfo> list = adminInfoMapper.selectByExample(example2);
		if (list != null && list.size() > 0) {
			retrunList = new ArrayList<Map<String, Object>>();
			for (AdminInfo adminInfo : list) {
				Admin admin = adminMapper.selectByPrimaryKey(adminInfo.getAdminId());

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", adminInfo.getId());// id
				map.put("name", adminInfo.getLeaglPerson());// 法人姓名
				map.put("company", adminInfo.getCompanyName());// 公司姓名
				map.put("account", admin.getMobile());// 登录账号
				map.put("zhiyaMoney", adminInfo.getMortage());// 质押金
				map.put("daikuanMoney", adminInfo.getLoan());// 贷款金额
				map.put("totalMoney", (adminInfo.getMortage() == null ? 0 : adminInfo.getMortage())
						+ (adminInfo.getLoan() == null ? 0 : adminInfo.getLoan()));// 授信总额
				long zhiyaGoodsMoney = 0;
				// 抵押商品总额
				GoodsExample example = new GoodsExample();
				example.createCriteria().andAdminIdEqualTo(adminInfo.getAdminId())
						.andStatusNotEqualTo(GoodsConst.GOODS_STATUS_DELETED);
				List<Goods> goodsList = goodsMapper.selectByExample(example);
				if (goodsList != null && goodsList.size() > 0) {
					for (Goods goods : goodsList) {
						zhiyaGoodsMoney += (goods.getPricePurchase() == null ? 0 : goods.getPricePurchase());
					}
				}
				map.put("zhiyaGoodsMoney", zhiyaGoodsMoney);
				try {
					map.put("time", adminInfo.getRepayTime());// 还款截止时间

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					map.put("time", "未知");
				}
				retrunList.add(map);

			}
		}

		PageBean pageBean = new PageBean();
		pageBean.setTotal(page.getTotal());
		pageBean.setRows(retrunList);
		return JsonData.setSuccessMessage(pageBean);
	}

	// 公司贷款修改
	public JsonData updateCompanyCreditLineById(Integer id, Long zhiyaMoney, Long dakuanMoney, Long time) {
		if (id == null) {
			return JsonData.setErrorMessage("参数错误");
		}
		AdminInfo adminInfo = new AdminInfo();
		adminInfo.setId(id);
		adminInfo.setMortage(zhiyaMoney);
		adminInfo.setLoan(dakuanMoney);
		adminInfo.setRepayTime(new Date(time));
		try {
			adminInfoMapper.updateByPrimaryKeySelective(adminInfo);
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return JsonData.setErrorMessage("修改失败");
	}

	// 获取申请调额用户数量
	public JsonData getUserApplyCount() {

		UserApplyCreditExample example = new UserApplyCreditExample();
		example.createCriteria().andStatusEqualTo(StatusConst.USER_APPLY_STATUS_IN);
		Integer count = userApplyCreditMapper.countByExample(example);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("count", count);
		return JsonData.setSuccessMessage(map);
	}

	// 申请调额列表
	public JsonData getUserApplyList(Integer pages, Integer rows, Byte type, String keywords) {
		if (pages < 1) {
			pages = 1;
		}

		Page page = PageHelper.startPage(pages, rows);
		List<UserApplyCustom> userApplyCustoms = userApplyMapperCustom.getUserApplyList(keywords, type,
				UserConts.USER_TYPE_B, StatusConst.USER_APPLY_STATUS_IN);
		PageBean pageBean = new PageBean();
		pageBean.setTotal(page.getTotal());
		pageBean.setRows(userApplyCustoms);
		return JsonData.setSuccessMessage(pageBean);

	}
	// 查看申请调额

	public JsonData getUserApplyDetailById(Integer id) {
		if (id == null || id < 0) {
			return JsonData.setErrorMessage("参数错误");
		}

		User user = userMapper.selectByPrimaryKey(id);
		if (user != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", user.getName());// 姓名
			map.put("idCard", user.getIdCard());// 身份证号
			map.put("mobile", user.getMobile());// 登录账号
			UserBankExample example = new UserBankExample();
			example.createCriteria().andUserIdEqualTo(user.getId());
			List<UserBank> list = userBankMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				map.put("bank", list);
			} else {
				map.put("bank", "暂无可用银行卡");
			}
			UserApplyExample example2 = new UserApplyExample();
			example2.createCriteria().andUserIdEqualTo(user.getId()).andCheckStatusEqualTo((byte) 2);
			List<UserApply> list2 = userApplyMapper.selectByExample(example2);
			if (list2 != null && list2.size() > 0) {
				map.put("applyTime", list2.get(0).getCreateTime());
			}
			map.put("creditType", user.getCreditReal());// 0虚拟授信 1真实授信
			map.put("money", user.getCreditTotal());// 授信金额
			try {
				map.put("time", user.getRepayTime());// 还款截止时间

			} catch (Exception e) {
				e.printStackTrace();
				map.put("time", "未知");
				// TODO: handle exception
			}

			UserApplyCreditExample example4 = new UserApplyCreditExample();
			example4.createCriteria().andUserIdEqualTo(id).andStatusEqualTo(StatusConst.USER_APPLY_STATUS_IN);
			List<UserApplyCredit> applies = userApplyCreditMapper.selectByExample(example4);
			UserApplyCredit applyCredit = null;
			if (applies != null && applies.size() > 0) {
				applyCredit = applies.get(0);
			}
			map.put("applyMoney", applyCredit == null ? 0 : applyCredit.getApplyCredit());// 申请额度

			GoodsUserExample example3 = new GoodsUserExample();
			example3.createCriteria().andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE).andUserIdEqualTo(user.getId());
			List<GoodsUser> list3 = goodsUserMapper.selectByExample(example3);
			map.put("onsaleGoods", (list3 != null && list3.size() > 0) ? list3.size() : 0);// 已上架商品数量

			/*
			 * GoodsSubscriptionRecordExample example4 = new
			 * GoodsSubscriptionRecordExample();
			 * example4.createCriteria().andUserIdEqualTo(user.getId());
			 * List<GoodsSubscriptionRecord> list4 = recordMapper.selectByExample(example4);
			 * Set<Integer> set = null; if (list4!= null && list4.size()>0) { set = new
			 * HashSet<Integer>(); for (GoodsSubscriptionRecord record : list4) {
			 * set.add(record.getGoodsSubscriptionId()); } }
			 */

			List<GoodsSubscriptionRecord> list4 = subscriptionCustom.selectGroupByUserId(user.getId());
			map.put("subscription", (list4 != null && list4.size() > 0) ? list4.size() : 0);// 认筹商品数量
			map.put("useMoney", user.getCreditTotal() - user.getCreditSurplus());// 认筹金额
			return JsonData.setSuccessMessage(map);
		}
		return JsonData.setSuccessMessage();
	}

	// 修改申请调额
	public JsonData updateUserApply(Integer id, Byte type, Long money, Long time) {
		if (id == null || id < 0) {
			return JsonData.setErrorMessage("参数错误");
		}

		User user = userMapper.selectByPrimaryKey(id);
		if (type != null) {
			user.setCreditReal(type);
		}
		if (time != null) {
			user.setRepayTime(new Date(time));
		}

		try {
			UserApplyCreditExample example = new UserApplyCreditExample();
			example.createCriteria().andUserIdEqualTo(id).andStatusEqualTo(StatusConst.USER_APPLY_STATUS_IN);
			List<UserApplyCredit> userApplyCreditList = userApplyCreditMapper.selectByExample(example);
			UserApplyCredit applyCredit = userApplyCreditList.get(0);
			applyCredit.setStatus(StatusConst.USER_APPLY_STATUS_SUCCESS);
			userApplyCreditMapper.updateByPrimaryKeySelective(applyCredit);
			Long creditOld = user.getCreditTotal();
			user.setCreditSurplus(user.getCreditSurplus() + money - user.getCreditTotal());
			user.setCreditTotal(money);
			userMapper.updateByPrimaryKeySelective(user);

			// 添加消息记录
			noticeRecordService.addNoticeRecord(applyCredit.getUserId(), NoticeConst.NOTICE_ID_CREDIT_UPDATE_SUCCESS,
					NoticeConst.NOTICE_RECORD_TYPE_SYSTEM, new String[] { creditOld + "", user.getCreditTotal() + "" });
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("参数错误");
		}
	}

	// 申请调额拒绝
	public JsonData updateUserApplyRefuse(Integer id, String msg) {
		UserApplyCreditExample example = new UserApplyCreditExample();
		example.createCriteria().andUserIdEqualTo(id).andStatusEqualTo(StatusConst.USER_APPLY_STATUS_IN);
		List<UserApplyCredit> userApplyCreditList = userApplyCreditMapper.selectByExample(example);
		UserApplyCredit applyCredit = userApplyCreditList.get(0);
		applyCredit.setErrorMsg(msg);
		applyCredit.setStatus(StatusConst.USER_APPLY_STATUS_REFUSE);
		try {
			userApplyCreditMapper.updateByPrimaryKeySelective(applyCredit);
			// 添加消息记录
			noticeRecordService.addNoticeRecord(applyCredit.getUserId(), NoticeConst.NOTICE_ID_CREDIT_UPDATE_FAILED,
					NoticeConst.NOTICE_RECORD_TYPE_SYSTEM, new String[] { msg });
			return JsonData.setSuccessMessage();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("操作失败");
		}
	}
}
