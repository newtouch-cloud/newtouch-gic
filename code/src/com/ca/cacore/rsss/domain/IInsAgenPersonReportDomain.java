package com.ca.cacore.rsss.domain;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IInsAgenPersonReportModel;

public interface IInsAgenPersonReportDomain {
	/** 
	* 
	* @param model
	* @return List<IInsAgenPersonReportModel>
	* @description:保险代理机构人员电子档案查询
	*/
	public List<IInsAgenPersonReportModel> queryInsAgenPersonReport(IInsAgenPersonReportModel model);
	/** 
	* 
	* @param model
	* @return List<IInsCompanyBusModel>
	* @description:保险代理机构人员电子档案导出Domain层方法
	*/
	public List<IInsAgenPersonReportModel> exportInsAgenPersonReport(IInsAgenPersonReportModel model);

}
