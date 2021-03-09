package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IPolicyLifeInsuredDao;
import com.ca.cacore.ibs.model.bo.PolicyLifeInsuredModel;

@Service
public class PolicyLifeInsuredDomain implements IPolicyLifeInsuredDomain {
	@Autowired private IPolicyLifeInsuredDao policyLifeInsuredDao;

	@Override
	public List<PolicyLifeInsuredModel> queryAllPolicyLifeInsured() {
		return policyLifeInsuredDao.queryAllPolicyLifeInsured();
	}
	
}
