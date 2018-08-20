package com.maomao.ssm.service.impl.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.AdminMoney;
import com.maomao.ssm.bean.AdminMonthDetail;
import com.maomao.ssm.bean.AdminMonthDetailCustom;
import com.maomao.ssm.bean.GoodsOrderCustom;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.AdminMapper;
import com.maomao.ssm.dao.AdminMonthDetailMapper;
import com.maomao.ssm.dao.AdminMonthDetailMapperCustom;
import com.maomao.ssm.dao.GoodsOrderMapperCustom;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.MonthDetailSalesList;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.MonthMoneyService;
import com.maomao.ssm.service.common.AdminMoneyService;

@Service
public class MonthMoneyServiceImpl implements MonthMoneyService {

	@Autowired
	private AdminMonthDetailMapperCustom adminMonthDetailMapperCustom;
	@Autowired
	private AdminMonthDetailMapper adminMonthDetailMapper;
	@Autowired
	private GoodsOrderMapperCustom goodsOrderMapperCustom;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private AdminMoneyService adminMoneyService;

	/**
	 * 获取月结列表
	 */
	@Override
	public JsonData getMonthDetailList(Integer pages, Integer rows, String queryString) {
		// 分页
		PageHelper.startPage(pages, rows);
		List<AdminMonthDetailCustom> adminMonthDetailCustoms = adminMonthDetailMapperCustom
				.getMonthDetailListByMonth(queryString);

		PageInfo<AdminMonthDetailCustom> pageInfo = new PageInfo<>(adminMonthDetailCustoms);

		PageBean pagebean = new PageBean();
		// 获取总条数
		pagebean.setTotal(pageInfo.getTotal());
		// 获取当前页数据
		pagebean.setRows(adminMonthDetailCustoms);
		return JsonData.setSuccessMessage(pagebean);
	}

	/**
	 * 获取月结详情
	 */
	@Override
	public JsonData getMonthDetail(Integer id) {
		AdminMonthDetailCustom adminMonthDetailCustom = adminMonthDetailMapperCustom.getMonthDetailById(id);
		return JsonData.setSuccessMessage(adminMonthDetailCustom);
	}

	/**
	 * 获取历史月结记录
	 */
	@Override
	public JsonData getHistoryMonthDetailList(Integer pages, Integer rows, Integer adminId) {
		// 分页
		PageHelper.startPage(pages, rows);
		List<AdminMonthDetailCustom> adminMonthDetailCustoms = adminMonthDetailMapperCustom
				.getMonthDetailListByAdminId(adminId);
		int currentMonth = adminMonthDetailCustoms.get(adminMonthDetailCustoms.size() - 1).getUploadTime() != null ? 0
				: (adminMonthDetailCustoms.size() - 1);
		adminMonthDetailCustoms.remove(currentMonth);
		PageInfo<AdminMonthDetailCustom> pageInfo = new PageInfo<>(adminMonthDetailCustoms);
		PageBean pagebean = new PageBean();
		// 获取总条数
		pagebean.setTotal(pageInfo.getTotal());
		// 获取当前页数据
		pagebean.setRows(adminMonthDetailCustoms);

		return JsonData.setSuccessMessage(pagebean);
	}

	/**
	 * 上传打款凭证
	 */
	@Override
	public JsonData updateByUploadImgs(Integer id, String loanImg, String moneyImg, Long uploadTime) {
		if (id == null || StringUtils.isBlank(moneyImg) || StringUtils.isBlank(loanImg)) {
			return JsonData.setErrorMessage("非法参数");
		}
		AdminMonthDetail adminMonthDetail = adminMonthDetailMapper.selectByPrimaryKey(id);
		if (adminMonthDetail == null) {
			return JsonData.setErrorMessage("月结明细不存在");
		}
		if (adminMonthDetail.getUploadTime() != null) {
			return JsonData.setErrorMessage("已上传过打款凭证");
		}
		adminMonthDetail.setLoanImg(loanImg);
		adminMonthDetail.setMoneyImg(moneyImg);
		if (uploadTime == null) {
			adminMonthDetail.setUploadTime(new Date());
		}
		adminMonthDetail.setUploadTime(new Date(uploadTime));

		// 修改管理员货款金额
		Admin admin = adminMapper.selectByPrimaryKey(adminMonthDetail.getAdminId());

		adminMoneyService.addAdminMoney(admin.getId(), admin.getMoney(), -adminMonthDetail.getMoneyChange(),
				UserConts.USER_MONEY_TYPE_WITHDRAWALS, adminMonthDetail.getId());
		admin.setMoney(admin.getMoney() - adminMonthDetail.getMoneyChange());
		adminMapper.updateByPrimaryKey(admin);
		adminMonthDetailMapper.updateByPrimaryKey(adminMonthDetail);
		return JsonData.setSuccessMessage();
	}

	/**
	 * 获取销售明细
	 */
	@Override
	public JsonData getSalesDetailList(Integer id) {
		if (id == null) {
			return JsonData.setErrorMessage("非法参数");
		}
		AdminMonthDetail adminMonthDetail = adminMonthDetailMapper.selectByPrimaryKey(id);
		if (adminMonthDetail == null) {
			return JsonData.setErrorMessage("月结明细不存在");
		}
		Date createTime = adminMonthDetail.getCreateTime();
		String queryTime = new SimpleDateFormat("yyyy-MM").format(DateUtils.addMonths(createTime, -1));
		List<GoodsOrderCustom> goodsOrderCustoms = goodsOrderMapperCustom
				.selectListByAdminAndMonth(adminMonthDetail.getAdminId(), queryTime);
		int totalNum = 0;
		for (GoodsOrderCustom goodsOrderCustom : goodsOrderCustoms) {
			totalNum += goodsOrderCustom.getNum();
		}
		MonthDetailSalesList monthDetailSalesList = new MonthDetailSalesList(totalNum, adminMonthDetail,
				goodsOrderCustoms);
		return JsonData.setSuccessMessage(monthDetailSalesList);
	}

}
