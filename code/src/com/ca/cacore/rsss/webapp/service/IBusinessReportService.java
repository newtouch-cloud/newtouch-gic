package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import com.ca.cacore.rsss.model.vo.BusinessReportVOModel1;
import com.ca.cacore.rsss.model.vo.BusinessReportVOModel2;
import com.newtouch.core.returnmsg.ReturnMsg;
/**
 * 
* @since:    2014年1月14日   
* @author    ZhangChen
* @description:保险代理机构业务汇总表(一)保险代理机构业务汇总表(二)service 接口
 */
public interface IBusinessReportService {
	
	/**
	 * 
	* 
	* @param model
	* @return ReturnMsg
	* @description:查询保险代理机构业务汇总表(一)
	 */
	public ReturnMsg queryReport1(BusinessReportVOModel1 model);
	
	/**
	* @param model
	* @return ReturnMsg
	* @description:查询保险代理机构业务汇总表(二)
	 */
	public ReturnMsg queryReport2(BusinessReportVOModel2 model);
	
	/**
	* @param model
	* @return List<BusinessReportVOModel1>
	* @description:获取保险代理机构业务汇总表(一)
	 */
	public List<BusinessReportVOModel1> getReport1(BusinessReportVOModel1 model);

	/**
	 * 2014-1-20
	* @param model
	* @return List<BusinessReportVOModel1>
	* @description:获取保险代理机构业务汇总表(二)
	 */
	public List<BusinessReportVOModel2> getReport2(BusinessReportVOModel2 model);
}
