package com.ca.cacore.mass.dao.cpyBranchId;

import com.ca.cacore.mass.model.bo.ICompanyBranchModel;

public interface ICpyBranchIdDao {
	public String getCpyBranchId(String branch_parentid, String branch_level);

	String getPCQ(ICompanyBranchModel model);//zdd

	public Integer queryCpySerno();

	public Integer queryCpySerno1(ICompanyBranchModel model);

	public Integer queryCpySerno2(ICompanyBranchModel model);
}
