package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IInsAgenPersonReportModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IInsAgenPersonReportService {
	
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:保险代理机构人员电子档案查询
	*/
	public ReturnMsg queryInsAgenPersonReport(IInsAgenPersonReportModel model);
	/** 
	* 
	* @param model
	* @return List
	* @description:保险代理机构人员电子档案导出
	*/
	public List<IInsAgenPersonReportModel> exportInsAgenPersonReport(IInsAgenPersonReportModel model);

}
