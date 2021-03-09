package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.PolicyLifeInsuredModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class PolicyLifeInsuredDao extends BaseDao implements IPolicyLifeInsuredDao {

	@Override
	public List<PolicyLifeInsuredModel> queryAllPolicyLifeInsured() {
		return this.getSqlMapClientTemplate().queryForList("policyLifeInsured.queryPolicyLifeInsured");
	}
}
