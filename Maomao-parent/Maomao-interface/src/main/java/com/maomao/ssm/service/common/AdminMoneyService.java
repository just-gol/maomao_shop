package com.maomao.ssm.service.common;

public interface AdminMoneyService {

	public Integer addAdminMoney(Integer adminId, Long moneyOld, Long moneyChange, Byte type, Integer bizId);

}
