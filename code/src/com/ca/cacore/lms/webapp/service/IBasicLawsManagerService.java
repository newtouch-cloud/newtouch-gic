package com.ca.cacore.lms.webapp.service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.lms.model.vo.IBasicLawsVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
* @since:    2014年3月3日   
* @author    ss
* @description:
*/
public interface IBasicLawsManagerService {
	/** 
	* 获得所有基本法的信息
	* @param model
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg getAllBasicLawsInfo(IBasicLawsVOModel model);
	
	/** 
	* 新增基本法
	* @param model
	* @param user
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg addBasicLaws(IBasicLawsVOModel model,IUserModel user);
	
	/** 
	* 查询基本法明细信息
	* @param model
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg getBasiclawView(IBasicLawsVOModel model);
	
	/** 
	* 修改基本法信息
	* @param model
	* @param user
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg modBasicLaws(IBasicLawsVOModel model,IUserModel user);
	
	/** 
	* 删除基本法的信息
	* @param serno
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg delBasicLaws(String serno);
}
