package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.ibs.model.bo.PolicyStatusModel;

public interface IPolicyStatusDomain {
	public List<PolicyStatusModel> queryPolicyStatus();
	public List<PolicyStatusModel> queryContractLifeStatus() ;
}
