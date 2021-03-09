package com.ca.cacore.rsss.domain;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IYYPersonReportVOModel;

public interface IYYPersonReportDomain {
	

	/**
	* @param model
	* @return List<IYYPersonReportVOModel>
	* @description:查询营运人员效力情况表
	 */
	public List<IYYPersonReportVOModel> queryYYPersonReport(IYYPersonReportVOModel model);
	/**
	* @param model
	* @return List<IYYPersonReportVOModel>
	* @description:导出营运人员效力情况表
	 */
	public List<IYYPersonReportVOModel> exportYYPersonReport(IYYPersonReportVOModel model);
	
}
