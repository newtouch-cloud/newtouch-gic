package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.ibs.model.bo.PolicyLifeOverdueModel;

public interface IPolicyLifeOverdueDomain {
	public List<PolicyLifeOverdueModel>queryPolicyLifeOverdue();
}
