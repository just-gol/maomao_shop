package com.maomao.ssm.service.common;

public interface UserCreditService {

	public Integer addUserCredit(Integer userId, Long creditOld, Long creditChange, Byte type, Integer bizId);

}
