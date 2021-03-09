package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.ibs.model.bo.ProblemStatusModel;

public interface IProblemStatusDomain {
	public List<ProblemStatusModel> queryProblemStatus();
}
