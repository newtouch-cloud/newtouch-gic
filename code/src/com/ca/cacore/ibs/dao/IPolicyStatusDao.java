package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.PolicyStatusModel;

public interface IPolicyStatusDao {
	public List<PolicyStatusModel> queryPolicyStatus();
	public List<PolicyStatusModel> queryContractLifeStatus();
}
