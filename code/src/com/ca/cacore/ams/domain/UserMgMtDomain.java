package com.ca.cacore.ams.domain;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.dao.IUserMgMtDao;
import com.ca.cacore.ams.model.vo.IUserMgMtVOModel;


@Service
public class UserMgMtDomain  implements IUserMgMtDomain{

	@Autowired private IUserMgMtDao userMgmtDao;
	
	@Override
	public IUserMgMtVOModel queryOptPathInfo(IUserMgMtVOModel model) {
		return userMgmtDao.queryOptPathInfo(model);
	}
	@Override
	public boolean updateOptPathInfo(IUserMgMtVOModel model) {
		return userMgmtDao.updateOptPathInfo(model);
	}
	@Override
	public boolean updatePassWord(IUserMgMtVOModel model) {
		return userMgmtDao.updatePassWord(model);
	}
	@Override
	public IUserMgMtVOModel queryUserInfo(IUserMgMtVOModel model) {
		return userMgmtDao.queryUserInfo(model);
	}
	@Override
	public boolean addUserInfo(IUserMgMtVOModel model) {
		return userMgmtDao.addUserInfo(model);
	}
	@Override
	public boolean addOptPathInfo(IUserMgMtVOModel model) {
		return userMgmtDao.addOptPathInfo(model);
	}
	@Override
	public boolean addOperatorRolesInfo(IUserMgMtVOModel model) {
		return userMgmtDao.addOperatorRolesInfo(model);
	}
	@Override
	public boolean addDataAuthInfo(IUserMgMtVOModel model) {
		return userMgmtDao.addDataAuthInfo(model);
	}
	@Override
	public List<IUserMgMtVOModel> queryBranchList(IUserMgMtVOModel model) {
		return userMgmtDao.queryBranchList(model);
	}
	@Override
	public IUserMgMtVOModel statisticalDataYearSalesOrProtocolYear(IUserMgMtVOModel model) {
		return userMgmtDao.statisticalDataYearSalesOrProtocolYear(model);
	}
	@Override
	public IUserMgMtVOModel statisticalDataYearSalesOrProtocolMonth(IUserMgMtVOModel model) {
		return userMgmtDao.statisticalDataYearSalesOrProtocolMonth(model);
	}
	@Override
	public List<IUserMgMtVOModel> querySalesInfo(IUserMgMtVOModel model) {
		return userMgmtDao.querySalesInfo(model);
	}
	@Override
	public List<IUserMgMtVOModel> querySalesInfoFenGongSi(IUserMgMtVOModel model) {
		return userMgmtDao.querySalesInfo(model);
	}
}
