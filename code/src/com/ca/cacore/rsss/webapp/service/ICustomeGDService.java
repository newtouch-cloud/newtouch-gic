package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import com.ca.cacore.rsss.model.vo.ICustomeGDVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface ICustomeGDService {
	
	/**
	 * 
	* 
	* @param model
	* @return ReturnMsg
	* @description:查询客户群体分布表
	 */
	public ReturnMsg queryCustomeGD(ICustomeGDVOModel model);
	/**
	 * 
	* 
	* @param model
	* @return List<ICustomeGDVOModel>
	* @description:导出客户群体分布表
	 */
	public List<ICustomeGDVOModel> exportCustomeGD(ICustomeGDVOModel model);
}
