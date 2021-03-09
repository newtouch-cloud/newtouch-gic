package com.ca.cacore.ams.domain;


import java.util.List;

import com.ca.cacore.ams.model.vo.IUserMgMtVOModel;


public interface IUserMgMtDomain {

	public IUserMgMtVOModel queryOptPathInfo(IUserMgMtVOModel model);
	
	public boolean updateOptPathInfo(IUserMgMtVOModel model);
	
	public boolean updatePassWord(IUserMgMtVOModel model);
	
	public IUserMgMtVOModel queryUserInfo(IUserMgMtVOModel model);

	public boolean addUserInfo(IUserMgMtVOModel model);
	
	public boolean addOptPathInfo(IUserMgMtVOModel model);
	
    public boolean addOperatorRolesInfo(IUserMgMtVOModel model);
	
	public boolean addDataAuthInfo(IUserMgMtVOModel model);
	
	public List<IUserMgMtVOModel> queryBranchList(IUserMgMtVOModel model);

	public IUserMgMtVOModel statisticalDataYearSalesOrProtocolYear(IUserMgMtVOModel model);
	
	public IUserMgMtVOModel statisticalDataYearSalesOrProtocolMonth(IUserMgMtVOModel model);
	
	public List<IUserMgMtVOModel> querySalesInfo(IUserMgMtVOModel model);
	
	public List<IUserMgMtVOModel> querySalesInfoFenGongSi(IUserMgMtVOModel model);
}
