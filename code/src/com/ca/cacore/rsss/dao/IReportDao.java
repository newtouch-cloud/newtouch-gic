package com.ca.cacore.rsss.dao;

import java.util.List;

import com.ca.cacore.rsss.model.vo.HoldReportVOModel;

/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:保险代理机构业务汇总表(一)保险代理机构业务汇总表(二) dao 接口
 */
public interface IReportDao {
	
	/**
	 * 
	* 
	* @param model
	* @return List<HoldReportVOModel>
	* @description:查询专业保险中介机构业务人员持证情况报表
	 */
	public List<HoldReportVOModel> queryReport(HoldReportVOModel model);

}
