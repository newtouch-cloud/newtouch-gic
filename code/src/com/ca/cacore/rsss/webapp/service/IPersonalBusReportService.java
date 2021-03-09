package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IAentLifeInsModel;
import com.ca.cacore.rsss.model.vo.IPersonalBusReportModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IPersonalBusReportService {
	
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:业务报表(人身险公司业务)报表查询
	*/
	public ReturnMsg queryPersonalBusReport(IPersonalBusReportModel model);
	/** 
	* 
	* @param model
	* @return List<IPersonalBusReportModel>
	* @description:业务报表(人身险公司业务)报表导出
	*/
	public List<IPersonalBusReportModel> exportPersonalBusReport(IPersonalBusReportModel model);

	public ReturnMsg queryAgentLifeIns(IAentLifeInsModel model);

	/**
	 * 按机构id和期次查询报表信息
	 * @param model
	 * @return
	 */
	public List<IAentLifeInsModel> queryAgentLifeInss(IAentLifeInsModel model);
	
	/*
	 * 根据机构id和期次统计报表每列总计
	 */
	public IAentLifeInsModel queryTotal(IAentLifeInsModel model);
	
	public List<IAentLifeInsModel> queryByRisk(IAentLifeInsModel agentLife);
	
	public IAentLifeInsModel queryTotal1(IAentLifeInsModel agentLife);
}
