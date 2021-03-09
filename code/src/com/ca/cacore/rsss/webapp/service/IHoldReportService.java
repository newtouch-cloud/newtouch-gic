package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import com.ca.cacore.rsss.model.vo.HoldReportVOModel;
import com.newtouch.core.returnmsg.ReturnMsg;
/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:专业保险中介机构业务人员持证情况报表service 接口
 */
public interface IHoldReportService {
	
	/**
	 * 
	* 
	* @param model
	* @return ReturnMsg
	* @description:查询专业保险中介机构业务人员持证情况报表
	 */
	public ReturnMsg queryReport(HoldReportVOModel model);
	
	/**
	 * 
	* 
	* @param model
	* @return List<HoldReportVOModel>
	* @description:获取专业保险中介机构业务人员持证情况报表
	 */
	public List<HoldReportVOModel> getReport(HoldReportVOModel model);

}
