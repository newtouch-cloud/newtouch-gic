package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.BranchModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class BranchSelectDao extends BaseDao implements IBranchSelectDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<BranchModel> queryBranch() {
		return this.getSqlMapClientTemplate().queryForList("branchSelect.queryBranch");
	}
}
