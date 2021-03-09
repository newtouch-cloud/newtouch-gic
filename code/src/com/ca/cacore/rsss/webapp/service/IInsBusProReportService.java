package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IInsBusProReportModel;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IInsBusProReportService {
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:保险代理机构业务协议电子档案查询
	*/
	public ReturnMsg queryInsBusProReport(IInsBusProReportModel model);
	/** 
	* 
	* @param model
	* @return List
	* @description:保险代理机构业务协议电子档案导出
	*/
	public List<IInsBusProReportModel> exportInsBusProReport(IInsBusProReportModel model);

}
