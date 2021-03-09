package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.PolicyLifeOverdueModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class PolicyLifeOverdueDao extends BaseDao  implements IPolicyLifeOverdueDao{

	@Override
	public List<PolicyLifeOverdueModel> queryPolicyLifeOverdue() {
		return this.getSqlMapClientTemplate().queryForList("policyLifeOverdue.queryPolicyLifeOverdue");
	}
	
}
