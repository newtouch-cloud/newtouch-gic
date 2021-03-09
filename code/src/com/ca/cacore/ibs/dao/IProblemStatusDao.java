package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.ibs.model.bo.ProblemStatusModel;

public interface IProblemStatusDao {
	public List<ProblemStatusModel> queryProblemStatus();
}
