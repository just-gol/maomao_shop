package com.maomao.ssm.service.impl.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maomao.ssm.bean.Admin;
import com.maomao.ssm.bean.AdminInfo;
import com.maomao.ssm.bean.AdminInfoExample;
import com.maomao.ssm.bean.AdminMonthDetail;
import com.maomao.ssm.bean.AdminMonthDetailExample;
import com.maomao.ssm.bean.DailyDetail;
import com.maomao.ssm.bean.Permission;
import com.maomao.ssm.bean.User;
import com.maomao.ssm.bean.UserExample;
import com.maomao.ssm.bean.UserWithdrawals;
import com.maomao.ssm.bean.UserWithdrawalsExample;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.AdminInfoMapper;
import com.maomao.ssm.dao.AdminMapper;
import com.maomao.ssm.dao.AdminMonthDetailMapper;
import com.maomao.ssm.dao.DailyDetailMapper;
import com.maomao.ssm.dao.PermissionMapper;
import com.maomao.ssm.dao.UserMapper;
import com.maomao.ssm.dao.UserWithdrawalsMapper;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.ExportService;

/**
 * @author:wzy
 * @descrption:导出excel
 * @version:
 * @date:2018年4月13日
 */
@Service
public class ExportServiceImpl implements ExportService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserWithdrawalsMapper userWithdrawalsMapper;
	@Autowired
	private AdminMonthDetailMapper adminMonthDetailMapper;
	@Autowired
	private AdminInfoMapper adminInfoMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private DailyDetailMapper dailyDetailMapper;
	/**
	 * 导出excel 1:导出授信管理 2:导出提现申请 3:导出贷款月结 4:车商提现导出 5:
	 */
	public List<Map<String, Object>> exportXLS(Byte type) {
		if (type != null) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if (type == 1) {
				UserExample example = new UserExample();
				example.createCriteria().andTypeEqualTo(StatusConst.USER_TYPE_SHOP);
				List<User> listUser = userMapper.selectByExample(example);
				if (listUser != null && listUser.size() > 0) {
					for (int i = 0; i < listUser.size(); i++) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("xuhao", i + 1);
						map.put("name", listUser.get(i).getName() == null ? "" : listUser.get(i).getName());
						map.put("idCard", listUser.get(i).getIdCard() == null ? "" : listUser.get(i).getIdCard());
						map.put("mobile", listUser.get(i).getMobile() == null ? "" : listUser.get(i).getMobile());
						map.put("account", listUser.get(i).getMobile() == null ? "" : listUser.get(i).getMobile());
						list.add(map);
					}

				}
			}
			if (type == 2 || type == 4) {
				UserWithdrawalsExample example = new UserWithdrawalsExample();
				if (type == 2) {
					example.createCriteria().andStatusEqualTo((byte) 0).andCategoryEqualTo((byte) 0);
				} else {
					example.createCriteria().andStatusEqualTo((byte) 0).andCategoryEqualTo((byte) 1);
				}
				List<UserWithdrawals> userWithdrawals = userWithdrawalsMapper.selectByExample(example);
				if (userWithdrawals != null && userWithdrawals.size() > 0) {
					for (int i = 0; i < userWithdrawals.size(); i++) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("xuhao", i + 1);
						User user = userMapper.selectByPrimaryKey(userWithdrawals.get(i).getUserId());
						if (user != null) {
							map.put("name", user.getName() == null ? "" : user.getName());
							map.put("mobile", user.getMobile() == null ? "" : user.getMobile());
							map.put("money", userWithdrawals.get(i).getMoney() / 100);
							map.put("bankName", userWithdrawals.get(i).getBankName() == null ? ""
									: userWithdrawals.get(i).getBankName());// 开户行
							map.put("bankUserName", userWithdrawals.get(i).getUserName() == null ? ""
									: userWithdrawals.get(i).getUserName());// 户名
							map.put("account", userWithdrawals.get(i).getAccount());
							map.put("time",
									new SimpleDateFormat("yyyy/MM/dd").format(userWithdrawals.get(i).getCreateTime()));
							map.put("status", "已打款");
							list.add(map);
						}

					}
				}
			}
			if (type == 3) {
				AdminMonthDetailExample example = new AdminMonthDetailExample();
				// 结束日期
				Calendar c = Calendar.getInstance();
				c.set(Calendar.DAY_OF_MONTH, 1);
				Date endDate = c.getTime();
				// 开始日期
				c.add(Calendar.MONTH, -1);
				c.set(Calendar.DAY_OF_MONTH, 1);
				Date startDate = c.getTime();

				example.createCriteria().andCreateTimeBetween(startDate, endDate);
				List<AdminMonthDetail> list2 = adminMonthDetailMapper.selectByExample(example);

				if (list2 != null && list2.size() > 0) {
					for (int i = 0; i < list2.size(); i++) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("xuhao", i + 1);
						Admin admin = adminMapper.selectByPrimaryKey(list2.get(i).getAdminId());
						map.put("name", admin.getName() == null ? "" : admin.getName());
						map.put("idCard", admin.getIdCard() == null ? "" : admin.getIdCard());
						map.put("huankuanMoney", list2.get(i).getMoneyChange() == null ? "" : admin.getIdCard());

						AdminInfoExample example2 = new AdminInfoExample();
						example2.createCriteria().andAdminIdEqualTo(list2.get(i).getAdminId());
						List<AdminInfo> adminList = adminInfoMapper.selectByExample(example2);

						map.put("bankName01", adminList.get(0).getRepaymentBankName() == null ? ""
								: adminList.get(0).getRepaymentBankName());// 开户行
						map.put("account01", adminList.get(0).getRepaymentBankAccount() == null ? ""
								: adminList.get(0).getRepaymentBankAccount() == null);// 账户
						map.put("accountName01", adminList.get(0).getRepaymentBankUser() == null ? ""
								: adminList.get(0).getRepaymentBankUser());// 户名

						map.put("dakuanMoney", list2.get(i).getMoneyChange());
						map.put("bankName02", adminList.get(0).getBankName());// 开户行
						map.put("account02",
								adminList.get(0).getBankAccount() == null ? "" : adminList.get(0).getBankAccount());// 账户
						map.put("accountName02",
								adminList.get(0).getBankUser() == null ? "" : adminList.get(0).getBankUser());// 户名
						if (list2.get(i).getLoanImg() == null && list2.get(i).getMoneyImg() == null) {
							map.put("status", "未打款");
						} else {
							map.put("status", "已打款");
						}
						list.add(map);
					}
				}
			}
			if (type != null && type == 5) {

				List<DailyDetail> dailyDetailList = dailyDetailMapper.selectByExample(null);
				int xuhao = 0;
				if (dailyDetailList != null && dailyDetailList.size() > 0) {
					for (DailyDetail dailyDetail : dailyDetailList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("xuhao", xuhao++);
						map.put("time", new SimpleDateFormat("yyyy-dd-mm").format(dailyDetail.getTime()));// 日期
						map.put("orderPayMoneyTotal", dailyDetail.getOrderPayMoney() * 1.0 / 100);// 支付金额
						map.put("orderRefundMoneyTotal", dailyDetail.getOrderRefundMoney() * 1.0 / 100);// 退款金额
						map.put("couponGetMoneyTotal", dailyDetail.getCouponGetMoney() * 10 / 100);// 优惠券发放金额
						map.put("couponUseMoneyTotal", dailyDetail.getCouponUseMoney() * 1.0 / 100);// 优惠券使用金额
						map.put("couponExpireMoneyTotal", dailyDetail.getCouponExpireMoney() * 1.0 / 100);// 优惠券过期金额
						list.add(map);
					}
				}
			}
			return list;
		}

		return null;
	}

	public JsonData getList() {
		List<Permission> list = permissionMapper.selectByExample(null);
		Set<Integer> set = new HashSet<Integer>();
		for (Permission permission : list) {
			set.add(permission.getParentId());
		}

		return JsonData.setSuccessMessage(permissionMapper.selectByExample(null));
	}

}
