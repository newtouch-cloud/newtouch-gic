package com.ca.cacore.ams.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ams.model.vo.IUserMgMtVOModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class UserMgMtDao extends BaseDao implements IUserMgMtDao{

	@Override
	public IUserMgMtVOModel queryOptPathInfo(IUserMgMtVOModel model) {
		return (IUserMgMtVOModel) this.getSqlMapClientTemplate().queryForObject("UserMgmt.queryOptPathInfo",model);
	}

	@Override
	public boolean updateOptPathInfo(IUserMgMtVOModel model) {
		this.getSqlMapClientTemplate().update("UserMgmt.updateOptPathInfo",model);
		return true;
	}

	@Override
	public boolean updatePassWord(IUserMgMtVOModel model) {
		this.getSqlMapClientTemplate().update("UserMgmt.updatePassWord",model);
		return true;
	}

	@Override
	public IUserMgMtVOModel queryUserInfo(IUserMgMtVOModel model) {
		return (IUserMgMtVOModel) this.getSqlMapClientTemplate().queryForObject("UserMgmt.queryUserInfo",model);
	}

	@Override
	public boolean addUserInfo(IUserMgMtVOModel model) {
		this.getSqlMapClientTemplate().insert("UserMgmt.addUserInfo",model);
		return true;
	}

	@Override
	public boolean addOptPathInfo(IUserMgMtVOModel model) {
		this.getSqlMapClientTemplate().insert("UserMgmt.addOptPathInfo",model);
		return true;
	}

	@Override
	public boolean addOperatorRolesInfo(IUserMgMtVOModel model) {
		this.getSqlMapClientTemplate().insert("UserMgmt.addOperatorRolesInfo",model);
		return true;
	}

	@Override
	public boolean addDataAuthInfo(IUserMgMtVOModel model) {
		this.getSqlMapClientTemplate().insert("UserMgmt.addDataAuthInfo",model);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IUserMgMtVOModel> queryBranchList(IUserMgMtVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("UserMgmt.queryBranchList",model);
	}

	@Override
	public IUserMgMtVOModel statisticalDataYearSalesOrProtocolYear(IUserMgMtVOModel model) {
		return (IUserMgMtVOModel) this.getSqlMapClientTemplate().queryForObject("UserMgmt.statisticalDataYearSalesOrProtocolYear",model);
	}
	
	@Override
	public IUserMgMtVOModel statisticalDataYearSalesOrProtocolMonth(IUserMgMtVOModel model) {
		return (IUserMgMtVOModel) this.getSqlMapClientTemplate().queryForObject("UserMgmt.statisticalDataYearSalesOrProtocolMonth",model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IUserMgMtVOModel> querySalesInfo(IUserMgMtVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("UserMgmt.querySalesInfo",model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IUserMgMtVOModel> querySalesInfoFenGongSi(IUserMgMtVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("UserMgmt.querySalesInfoFenGongSi",model);
	}
}
