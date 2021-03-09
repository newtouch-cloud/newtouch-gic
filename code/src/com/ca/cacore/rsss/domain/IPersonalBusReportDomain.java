package com.ca.cacore.rsss.domain;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IPersonalBusReportModel;

public interface IPersonalBusReportDomain {
	/** 
	* 
	* @param model
	* @return List<IPersonalBusReportModel>
	* @description:业务报表(人身险公司业务)报表查询Domain层方法
	*/
	public List<IPersonalBusReportModel> queryPersonalBusReport(IPersonalBusReportModel model);
	/** 
	* 
	* @param model
	* @return List<IPersonalBusReportModel>
	* @description:业务报表(人身险司业务)报表导出Domain层方法
	*/
	public List<IPersonalBusReportModel> exportPersonalBusReport(IPersonalBusReportModel model);

}
