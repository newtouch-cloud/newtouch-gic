package com.ca.cacore.ams.domain;

import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;

public interface IRegulationStatusDomain {
	
	/** 
* 
* @param model
* @param user
* @return Boolean
* @description: 更新规章编号信息表中的流程状态 
*/
public Boolean updateRegulationStatus(IPreserveRegVOModel model,IUserModel user);

}
