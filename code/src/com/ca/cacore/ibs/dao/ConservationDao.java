package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IConservationModel;
import com.ca.cacore.ibs.model.vo.IConservationVOModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class ConservationDao extends BaseDao implements IConservationDao{

	@Override
	public IConservationVOModel getPoliInfo(IConservationModel model) {
		return (IConservationVOModel) this.getSqlMapClientTemplate().queryForObject("conservation.getPoliInfo", model);
	}

	@Override
	public Boolean addConservation(IConservationModel model, IUserModel user) {
		this.getSqlMapClientTemplate().insert("conservation.addConservation", model);
		return true;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IConservationVOModel> getApplyInfo(Long policy_id) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("conservation.getApplyInfo", policy_id);
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<IConservationVOModel> queryConservations(
			IConservationVOModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("conservation.queryConservations_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IConservationVOModel>)this.getSqlMapClientTemplate().queryForList("conservation.queryConservations", model);
	}

	@Override
	public IConservationVOModel getConservationsInfo(IConservationModel model) {
		// TODO Auto-generated method stub
		return (IConservationVOModel) this.getSqlMapClientTemplate().queryForObject("conservation.getConservationsInfo", model);
	}

	@Override
	public Boolean modifyConservation(IConservationModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("conservation.modifyConservation",model);
		return true;
	}

	
}
