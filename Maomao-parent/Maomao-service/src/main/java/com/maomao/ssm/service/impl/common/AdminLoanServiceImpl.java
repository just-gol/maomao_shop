package com.maomao.ssm.service.impl.common;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maomao.ssm.bean.AdminLoan;
import com.maomao.ssm.dao.AdminLoanMapper;
import com.maomao.ssm.service.common.AdminLoanService;

@Service
public class AdminLoanServiceImpl implements AdminLoanService {

	@Autowired
	private AdminLoanMapper adminLoanMapper;

	@Override
	public Integer addAdminLoan(Integer adminId, Long loanOld, Long loanChange, Integer bizId) {
		AdminLoan adminLoan = new AdminLoan();
		adminLoan.setAdminId(adminId);
		adminLoan.setLoanOld(loanOld);
		adminLoan.setLoanNew(loanOld + loanChange);
		adminLoan.setLoanChange(loanChange);
		adminLoan.setBizId(bizId);
		adminLoan.setCreateTime(new Date());
		adminLoanMapper.insert(adminLoan);
		return adminLoan.getId();
	}
}
