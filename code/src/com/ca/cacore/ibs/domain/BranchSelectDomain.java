package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.BranchModel;
import com.ca.cacore.ibs.dao.IBranchSelectDao;
@Service
public class BranchSelectDomain implements IBranchSelectDomain{
	@Autowired private IBranchSelectDao branchSelectDao;
	@Override
	public List<BranchModel> queryBranch() {
		return branchSelectDao.queryBranch();
	}
}
