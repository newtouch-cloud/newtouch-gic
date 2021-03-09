package com.ca.cacore.ams.dao;

import org.springframework.stereotype.Component;

import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class RegulationStatusDao extends BaseDao implements IRegulationStatusDao{


	/** 
	* @param model
	* @return IPreserveRegVOModel
	* @description: 更新规章制度信息表中的规章状态 
	*/
	@Override
	public Boolean updateRegulationStatus(IPreserveRegVOModel model,
			IUserModel user) {
		this.getSqlMapClientTemplate().update("regulationManager.updateRegulationStatus",model);
		return true;
	}

}
