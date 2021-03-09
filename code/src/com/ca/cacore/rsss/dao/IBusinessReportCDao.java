package com.ca.cacore.rsss.dao;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IBusinessReportModel;

public interface IBusinessReportCDao {
	
	/**
	* @param model
	* @return List<IBusinessReportModel>
	* @description:查询保险代理机构业务汇总表
	 */
	public List<IBusinessReportModel> queryBusinessReport(IBusinessReportModel model);
	

	/**
	* @param model
	* @return List<IBusinessReportModel>
	* @description:导出保险代理机构业务汇总表
	 */
	public List<IBusinessReportModel> exportBusinessReport(IBusinessReportModel model);

}
