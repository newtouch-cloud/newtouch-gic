package com.ca.cacore.rsss.domain;

import java.util.List;

import com.ca.cacore.rsss.model.vo.HoldReportVOModel;

/**
 * 
* @since:    2014年1月14日   
* @author    ZhangChen
* @description:专业保险中介机构业务人员持证情况报表 domain 接口
 */
public interface IReportDomain {
	
	/**
	 * 
	* 2014-1-15
	* @param model
	* @return List<HoldReportVOModel>
	* @description:专业保险中介机构业务人员持证情况报表
	 */
	public List<HoldReportVOModel> queryReport(HoldReportVOModel model);

}
