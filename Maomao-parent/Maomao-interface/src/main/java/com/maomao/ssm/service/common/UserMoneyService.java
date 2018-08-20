package com.maomao.ssm.service.common;

public interface UserMoneyService {

	public Integer addUserMoney(Integer userId, Long moneyOld, Long moneyChange, Byte type, Integer bizId);

}
