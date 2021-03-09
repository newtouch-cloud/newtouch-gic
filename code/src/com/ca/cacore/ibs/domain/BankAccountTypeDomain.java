package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.BankAccountTypeDao;
import com.ca.cacore.ibs.model.bo.BankAccountTypeModel;

@Service
public class BankAccountTypeDomain implements IBankAccountTypeDomain {
	@Autowired private BankAccountTypeDao bankAccountTypeDao;

	@Override
	public List<BankAccountTypeModel> queryBankAccountTypeModel() {
		return bankAccountTypeDao.queryBankAccountTypeModel();
	}
}
