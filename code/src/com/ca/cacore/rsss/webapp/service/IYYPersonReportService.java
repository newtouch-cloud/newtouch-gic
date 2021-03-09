package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IYYPersonReportVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IYYPersonReportService {
	
	/**
	 * 
	* 
	* @param model
	* @return List<IYYPersonReportVOModel>
	* @description:查询营运人员效力情况表
	 */
	public ReturnMsg queryYYPersonReport(IYYPersonReportVOModel model);
	/**
	 * 
	* 
	* @param model
	* @return List<IYYPersonReportVOModel>
	* @description:导出营运人员效力情况表
	 */
	public List<IYYPersonReportVOModel> exportYYPersonReport(IYYPersonReportVOModel model);
}
