package com.maomao.ssm.service.impl.common;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maomao.ssm.bean.UserMoney;
import com.maomao.ssm.dao.UserMoneyMapper;
import com.maomao.ssm.service.common.UserMoneyService;

@Service
public class UserMoneyServiceImpl implements UserMoneyService {

	@Autowired
	private UserMoneyMapper userMoneyMapper;

	@Override
	public Integer addUserMoney(Integer userId, Long moneyOld, Long moneyChange, Byte type, Integer bizId) {
		UserMoney userMoney = new UserMoney();
		userMoney.setCreateTime(new Date());
		userMoney.setMoneyChange(moneyChange);
		userMoney.setMoneyOld(moneyOld);
		userMoney.setMoneyNew(moneyOld + moneyChange);
		userMoney.setType(type);
		userMoney.setUserId(userId);
		userMoney.setBizId(bizId);
		userMoneyMapper.insert(userMoney);
		return userMoney.getId();
	}
}
