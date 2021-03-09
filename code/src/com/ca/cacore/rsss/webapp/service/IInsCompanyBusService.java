package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IAgentPropertyModel;
import com.ca.cacore.rsss.model.vo.IInsCompanyBusModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IInsCompanyBusService {
		
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:业务报表(产险公司业务)报表查询
	*/
	public ReturnMsg queryInsCompanyBus(IInsCompanyBusModel model);
	/** 
	* 
	* @param model
	* @return List
	* @description:业务报表(产险公司业务)报表导出
	*/
	public List<IInsCompanyBusModel> exportInsCompanyBus(IInsCompanyBusModel model);
	
	/**
	 * 查询产险公司业务报表
	 * @param model
	 * @return
	 */
	public IAgentPropertyModel queryAgentProperty(IAgentPropertyModel model);
	
	/**
	 * 查询产险报表
	 * @param model
	 * @return
	 */
	//public List<IAgentPropertyModel> queryAgentPropertys(IAgentPropertyModel model);
	
	/*
	 * 根据机构id和期次统计报表每列总计
	 */
	public IAgentPropertyModel queryTotal(IAgentPropertyModel model);
	
	
}
