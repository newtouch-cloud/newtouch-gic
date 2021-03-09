package com.ca.cacore.ams.webapp.service;

import org.springframework.stereotype.Service;

import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public interface IRegulationManagerService {
	public ReturnMsg queryList(IPreserveRegVOModel model);
	public ReturnMsg queryStatus(IPreserveRegVOModel model);
	public ReturnMsg addRegulationBasic(IPreserveRegVOModel model,IUserModel user) throws BusinessException;
    public ReturnMsg getRegulationView(IPreserveRegVOModel model);
    public ReturnMsg modifyRegulation(IPreserveRegVOModel model,IUserModel user);
    
	/** 
	* 
	* @param
	* @return 
	* @description: 异步检验规章编号是否重复
	*/
	public Boolean checkRegulationIdUnique(IPreserveRegVOModel model);
	
}
