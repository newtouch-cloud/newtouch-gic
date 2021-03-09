package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IProblemTypeDao;
import com.ca.cacore.ibs.model.bo.ProblemTypeModel;

@Service
public class ProblemTypeDomain implements IProblemTypeDomain{
	@Autowired private IProblemTypeDao problemTypeDao;
	@Override
	public List<ProblemTypeModel> queryProblemType() {
		return problemTypeDao.queryProblemType();
	}
}
