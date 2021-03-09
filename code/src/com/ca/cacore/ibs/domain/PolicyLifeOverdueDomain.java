package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IPolicyLifeOverdueDao;
import com.ca.cacore.ibs.model.bo.PolicyLifeOverdueModel;

@Service
public class PolicyLifeOverdueDomain implements IPolicyLifeOverdueDomain  {
	@Autowired private IPolicyLifeOverdueDao policyLifeOverdueDao;

	@Override
	public List<PolicyLifeOverdueModel> queryPolicyLifeOverdue() {
		return policyLifeOverdueDao.queryPolicyLifeOverdue();
	}


	
	
}
