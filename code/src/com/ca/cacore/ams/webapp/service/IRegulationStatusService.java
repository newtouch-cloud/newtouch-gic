package com.ca.cacore.ams.webapp.service;

import org.springframework.stereotype.Service;

import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public interface IRegulationStatusService {
	/** 
	* 
	* @param model
	* @param user
	* @return ReturnMsg
	* @description: 更新规章制度信息表中的流程状态 
	*/
	public ReturnMsg updateRegulationStatus(IPreserveRegVOModel model,IUserModel user);
	
}
