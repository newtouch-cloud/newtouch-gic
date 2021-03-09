package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.PolicyStatusModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class PolicyStatusDao  extends BaseDao implements IPolicyStatusDao{

	@Override
	public List<PolicyStatusModel> queryPolicyStatus() {
		return this.getSqlMapClientTemplate().queryForList("policyStatus.queryPolicyStatus");
	}

	@Override
	public List<PolicyStatusModel> queryContractLifeStatus() {
		return this.getSqlMapClientTemplate().queryForList("policyStatus.queryContractLifeStatus");
	}
	
}
