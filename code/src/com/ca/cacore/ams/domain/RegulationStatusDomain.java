package com.ca.cacore.ams.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.dao.IRegulationStatusDao;
import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;

@Service
public class RegulationStatusDomain implements IRegulationStatusDomain{
	@Autowired private IRegulationStatusDao regulationStatusDao;

	/** 
	* @param model
	* @return IPreserveRegVOModel
	* @description: 更新规章编号信息表中的流程状态 
	*/
	@Override
	public Boolean updateRegulationStatus(IPreserveRegVOModel model,
			IUserModel user) {
		boolean up=true;
		for(int i=0; i<model.getSeq_ids().length; i++){
			model.setSeq_id(model.getSeq_ids()[i]);//规章制度的seq_id
			//修改用户信息
			model.setModifyuser(user.getEmp_id());
			up=regulationStatusDao.updateRegulationStatus(model, user);//调用dao层更新规章制度查询相应的信息
		}
		return up;
	}
	
}
