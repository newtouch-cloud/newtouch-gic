package com.ca.cacore.lms.webapp.service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.lms.model.vo.IBasicLawsVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
* @since:    2014年3月3日   
* @author    ss
* @description:
*/
public interface ISubBasicLawsManagerService {
	/** 
	* 获得所有基本法的信息
	* @param model
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg getAllSubBasicLawsInfo(IBasicLawsVOModel model);
	
	/** 
	* 新增基本法
	* @param model
	* @param user
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg addSubBasicLaws(IBasicLawsVOModel model,IUserModel user);
	
	/** 
	* 查询基本法明细信息
	* @param model
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg getSubBasiclawView(IBasicLawsVOModel model);
	
	/** 
	* 修改基本法信息
	* @param model
	* @param user
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg modSubBasicLaws(IBasicLawsVOModel model,IUserModel user);
	
	/** 
	* 删除基本法的信息
	* @param serno
	* @return ReturnMsg
	* @description:
	*/
	public ReturnMsg delSubBasicLaws(String serno);
	/** 
	* 
	* @param basiclaw_no
	* @return String
	* @description:
	*/
	public String getLawsInfo(String basiclaw_no);
}
