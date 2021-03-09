package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.mass.model.bo.ICompanyBranchModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class InsBranchDao extends BaseDao implements IInsBranchDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ICompanyBranchModel> queryInsBranch() {
		return this.getSqlMapClientTemplate().queryForList("insBranch.queryInsBranch");
	}
}
