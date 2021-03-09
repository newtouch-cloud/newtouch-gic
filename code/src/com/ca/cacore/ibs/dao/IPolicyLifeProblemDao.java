package com.ca.cacore.ibs.dao;

import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProblemModel;

public interface IPolicyLifeProblemDao {
	public List<IPolicyLifeProblemModel> queryPolicyLifeProblem(IPolicyLifeProblemModel model);//查询所有问题件信息
	
	public IPolicyLifeProblemModel getPolicyLifeProblemInfo(IPolicyLifeProblemModel model);//查询问题件信息
	
	public boolean modifyStatus(IPolicyLifeProblemModel model,IUserModel user);//问题件状态修改
	
	public boolean modifyPolicyLifeProblem(IPolicyLifeProblemModel model,IUserModel user);//问题件信息修改
	
	public boolean addPolicyLifeProblem(IPolicyLifeProblemModel model,IUserModel user);//问题件录入
	
	public IPolicyLifeProblemModel getInfo(IPolicyLifeProblemModel model);//查询带出信息
	
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
	public IPolicyLifeProblemModel getConserClaiProblemInfo(IPolicyLifeProblemModel model);
	
	/**
	* @param model
	* @return List<IPolicyLifeProblemModel>
	* @description:查询保全和理赔的问题件信息
	 */
	public List<IPolicyLifeProblemModel> queryConserClaiProblem(IPolicyLifeProblemModel model);
}
