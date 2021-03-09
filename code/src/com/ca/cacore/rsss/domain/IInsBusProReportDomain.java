package com.ca.cacore.rsss.domain;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IInsBusProReportModel;

public interface IInsBusProReportDomain {
	/** 
	* 
	* @param model
	* @return List<IInsBusProReportModel>
	* @description:保险代理机构业务协议电子档案查询
	*/
	public List<IInsBusProReportModel> queryInsBusProReport(IInsBusProReportModel model);
	/** 
	* 
	* @param model
	* @return List<IInsBusProReportModel>
	* @description:保险代理机构业务协议电子档案导出Domain层方法
	*/
	public List<IInsBusProReportModel> exportInsBusProReport(IInsBusProReportModel model);

}
