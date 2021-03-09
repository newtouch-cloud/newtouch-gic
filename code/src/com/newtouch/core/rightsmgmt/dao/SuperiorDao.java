package com.newtouch.core.rightsmgmt.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.newtouch.core.daobase.BaseDao;
import com.newtouch.core.rightsmgmt.model.ISuperiorModel;
import com.newtouch.core.rightsmgmt.model.SuperiorModel;

@Component
public class SuperiorDao  extends BaseDao implements ISuperiorDao{

	@Override
	public SuperiorModel querySuperior(ISuperiorModel model) {
		// TODO Auto-generated method stub
		return (SuperiorModel) this.getSqlMapClientTemplate().queryForObject("Superior.querySuperior",model);
	}

	@Override
	public ArrayList<SuperiorModel> querySuperiorList(ISuperiorModel model) {
		// TODO Auto-generated method stub
		return (ArrayList<SuperiorModel>) this.getSqlMapClientTemplate().queryForList("Superior.querySuperiorList",model);
	}

	@Override
	public void insertSuperior(ISuperiorModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("Superior.insertSuperior",model);
	}

	@Override
	public void updateSuperior(ISuperiorModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("Superior.updateSuperior",model);
	}

	@Override
	public ArrayList<SuperiorModel> findSupersByBranchId(ISuperiorModel model) {
		// TODO Auto-generated method stub
		return (ArrayList<SuperiorModel>) this.getSqlMapClientTemplate().queryForList("Superior.findSupersByBranchId",model);
	}

	@Override
	public ArrayList<SuperiorModel> querySuperiorInfos(ISuperiorModel model) {
		// TODO Auto-generated method stub
		return (ArrayList<SuperiorModel>) this.getSqlMapClientTemplate().queryForList("Superior.querySuperiorInfos",model);
	}

	@Override
	public void deleteSuperior(ISuperiorModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("Superior.deleteSuperior",model);
	}

	@Override
	public ISuperiorModel queryOpt(ISuperiorModel model) {
		// TODO Auto-generated method stub
		return (SuperiorModel) this.getSqlMapClientTemplate().queryForObject("Superior.queryOpt",model);
	}

	@Override
	public Boolean queryIsExistByName(String superior) {
		// TODO Auto-generated method stub
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("Superior.queryIsExistByName",superior);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}
	
	
	

}
