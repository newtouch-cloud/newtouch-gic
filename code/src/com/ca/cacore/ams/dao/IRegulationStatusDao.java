package com.ca.cacore.ams.dao;

import org.springframework.stereotype.Repository;

import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;


@Repository
public interface IRegulationStatusDao {
    
	/** 
	* 
	* @param model
	* @param user
	* @return Boolean
	* @description: 更新规章制度信息表中的流程状态 
	*/
	public Boolean updateRegulationStatus(IPreserveRegVOModel model,IUserModel user);
	
}
