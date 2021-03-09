package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IBusinessReportModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IBusinessReportCService {
		
	/**
	 * 
	* 
	* @param model
	* @return ReturnMsg
	* @description:查询保险代理机构业务汇总表
	 */
	public ReturnMsg queryBusinessReport(IBusinessReportModel model);
	/**
	 * 
	* 
	* @param model
	* @return List<IBusinessReportModel>
	* @description:导出保险代理机构业务汇总表
	 */
	public List<IBusinessReportModel> exportBusinessReport(IBusinessReportModel model);
}
