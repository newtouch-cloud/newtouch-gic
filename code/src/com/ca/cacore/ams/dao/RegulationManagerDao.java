package com.ca.cacore.ams.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class RegulationManagerDao extends BaseDao implements IRegulationManagerDao{

	@Override
	public List<IPreserveRegVOModel> queryList(IPreserveRegVOModel model) {
		int count=(Integer) this.getSqlMapClientTemplate().queryForObject("regulationManager.getAllRegulation_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return this.getSqlMapClientTemplate().queryForList("regulationManager.getAllRegulation", model);
	}
	/** 
	* @param model
	* @return List<IPreserveRegVOModel>
	* @description:规章制度的查询(待发布的记录)
	*/
	@Override
	public List<IPreserveRegVOModel> queryStatus(IPreserveRegVOModel model) {
		int count=(Integer) this.getSqlMapClientTemplate().queryForObject("regulationManager.getStatus_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return this.getSqlMapClientTemplate().queryForList("regulationManager.getStatusAll", model);
	}

	@Override
	public boolean addRegulationBasic(IPreserveRegVOModel model, IUserModel user) {
		this.getSqlMapClientTemplate().insert("regulationManager.addRegulationManager",model);
		return true;
	}

	@Override
	public IPreserveRegVOModel getRegulationView(IPreserveRegVOModel model) {
		return (IPreserveRegVOModel)this.getSqlMapClientTemplate().queryForObject("regulationManager.getRegulation", model);
	}

	@Override
	public boolean modifyRegulation(IPreserveRegVOModel model) {
		this.getSqlMapClientTemplate().update("regulationManager.modifyRegulation",model);
		return true;
	}

	@Override
	public boolean updateRegulationStatus(IPreserveRegVOModel model,
			IUserModel user) {
		this.getSqlMapClientTemplate().update("regulationManager.updateRegulationStatus",model);
		return true;
	}
	
	@Override
	public boolean updateRegulationTime(IPreserveRegVOModel model) {
		this.getSqlMapClientTemplate().update("regulationManager.updateRegulationTime", model);
		return true;
	}
	@Override
	public Integer checkRegulationIdUnique(IPreserveRegVOModel model) {
		return  (Integer)this.getSqlMapClientTemplate().queryForObject("regulationManager.checkRegulationId",model);
	}
}
