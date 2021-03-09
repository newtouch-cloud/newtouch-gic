package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.bo.ProblemTypeModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class ProblemTypeDao extends BaseDao implements IProblemTypeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ProblemTypeModel> queryProblemType() {
		return this.getSqlMapClientTemplate().queryForList("problemType.queryProblemType");
	}
}
