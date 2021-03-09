package com.ca.cacore.rsss.domain;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IBusinessReportModel;

public interface IBusinessReportCDomain {
	
	/**
	* @param model
	* @return List<BusinessReportModel>
	* @description:查询保险代理机构业务汇总表
	 */
	public List<IBusinessReportModel> queryBusinessReport(IBusinessReportModel model);
	/**
	* @param model
	* @return List<BusinessReportModel>
	* @description:导出保险代理机构业务汇总表
	 */
	public List<IBusinessReportModel> exportBusinessReport(IBusinessReportModel model);
	
}
