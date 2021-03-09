package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IPolicyStatusDao;
import com.ca.cacore.ibs.model.bo.PolicyStatusModel;

@Service
public class PolicyStatusDomain implements IPolicyStatusDomain {
	@Autowired private IPolicyStatusDao policyStatusDao;

	@Override
	public List<PolicyStatusModel> queryPolicyStatus() {
		return policyStatusDao.queryPolicyStatus();
	}

	@Override
	public List<PolicyStatusModel> queryContractLifeStatus() {
		return policyStatusDao.queryContractLifeStatus();
	}

}
