package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.ProblemTypeModel;

public interface IProblemTypeDao {
	public List<ProblemTypeModel> queryProblemType();
}
