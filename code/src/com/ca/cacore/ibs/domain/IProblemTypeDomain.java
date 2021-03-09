package com.ca.cacore.ibs.domain;

import java.util.List;

import com.ca.cacore.ibs.model.bo.ProblemTypeModel;

public interface IProblemTypeDomain {
	public List<ProblemTypeModel> queryProblemType();
}
