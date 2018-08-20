package com.maomao.ssm.service.impl.common;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maomao.ssm.bean.UserCredit;
import com.maomao.ssm.constant.UserConts;
import com.maomao.ssm.dao.UserCreditMapper;
import com.maomao.ssm.service.common.UserCreditService;

@Service
public class UserCreditServiceImpl implements UserCreditService {

	@Autowired
	private UserCreditMapper userCreditMapper;

	@Override
	public Integer addUserCredit(Integer userId, Long creditOld, Long creditChange, Byte type, Integer bizId) {
		UserCredit userCredit = new UserCredit();
		userCredit.setUserId(userId);
		userCredit.setCreditOld(creditOld);
		userCredit.setCreditChange(creditChange);
		userCredit.setCreditNew(creditOld + creditChange);
		userCredit.setBizId(bizId);
		userCredit.setType(UserConts.USER_CREDIT_TYPE_SALE_RETURN);
		userCredit.setCreateTime(new Date());
		userCredit.setType(type);
		userCreditMapper.insert(userCredit);
		return userCredit.getId();
	}
}
