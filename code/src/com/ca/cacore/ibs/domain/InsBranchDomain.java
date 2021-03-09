package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.IInsBranchDao;
import com.ca.cacore.mass.model.bo.ICompanyBranchModel;

@Service
public class InsBranchDomain implements IInsBranchDomain {
	@Autowired
	private IInsBranchDao insBranchDao;

	@Override
	public List<ICompanyBranchModel> queryInsBranch() {
		return insBranchDao.queryInsBranch();
	}
}
