package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.BankAccountTypeModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class BankAccountTypeDao extends BaseDao implements IBankAccountTypeDao{
	@Override
	public List<BankAccountTypeModel> queryBankAccountTypeModel() {
		return this.getSqlMapClientTemplate().queryForList("bankAccountType.queryBankAccountType");
	}
}
