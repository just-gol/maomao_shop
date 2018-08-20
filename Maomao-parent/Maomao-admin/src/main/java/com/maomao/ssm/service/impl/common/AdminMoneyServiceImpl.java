package com.maomao.ssm.service.impl.common;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maomao.ssm.bean.AdminMoney;
import com.maomao.ssm.dao.AdminMoneyMapper;
import com.maomao.ssm.service.common.AdminMoneyService;

@Service
public class AdminMoneyServiceImpl implements AdminMoneyService {

	@Autowired
	private AdminMoneyMapper adminMoneyMapper;

	@Override
	public Integer addAdminMoney(Integer adminId, Long moneyOld, Long moneyChange, Byte type, Integer bizId) {
		// 更新admin金额 添加金额明细
		AdminMoney adminMoney = new AdminMoney();
		adminMoney.setAdminId(adminId);
		adminMoney.setMoneyOld(moneyOld);
		adminMoney.setMoneyNew(moneyOld + moneyChange);
		adminMoney.setMoneyChange(moneyChange);
		adminMoney.setBizId(bizId);
		adminMoney.setType(type);
		adminMoney.setCreateTime(new Date());
		// 更新金额
		adminMoneyMapper.insert(adminMoney);
		return adminMoney.getId();
	}
}
