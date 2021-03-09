package com.ca.cacore.ibs.webapp.service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProblemModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IPolicyLifeProblemService {
	public ReturnMsg queryPolicyLifeProblem(IPolicyLifeProblemModel model);
	
	public ReturnMsg getPolicyLifeProblemInfo(IPolicyLifeProblemModel model);
	
	public ReturnMsg modifyStatus(IPolicyLifeProblemModel model,IUserModel user);//修改问题件状态
	
	public ReturnMsg modifyPolicyLifeProblem(IPolicyLifeProblemModel model,IUserModel user);//修改问题件信息
	
	public ReturnMsg addPolicyLifeProblem(IPolicyLifeProblemModel model,IUserModel user) throws BusinessException;//问题件录入
	
	public IPolicyLifeProblemModel getInfo(IPolicyLifeProblemModel model);//问题件录入时查询要带出的信息
	
	/**
	* @param model
	* @return IPolicyLifeProblemModel
	* @description:异步请求理赔和保全时候带出信息
	 */
	public IPolicyLifeProblemModel getInfoForClaimsConser(IPolicyLifeProblemModel model);
	
	
	/**
	* @param model
	* @return IPolicyLifeProblemModel
	* @description:保全和理赔问题件明细
	 */
	public ReturnMsg getConserClaiProblemInfo(IPolicyLifeProblemModel model);
	
	/**
	* @param model
	* @return ReturnMsg
	* @description:查询保全和理赔的问题件信息
	 */
	public ReturnMsg queryConserClaiProblem(IPolicyLifeProblemModel model) ;
}
