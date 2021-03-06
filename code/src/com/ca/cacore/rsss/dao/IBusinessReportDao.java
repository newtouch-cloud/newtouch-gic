package com.ca.cacore.rsss.dao;

import java.util.List;

import com.ca.cacore.rsss.model.vo.BusinessReportVOModel1;
import com.ca.cacore.rsss.model.vo.BusinessReportVOModel2;

/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:保险代理机构业务汇总表(一)保险代理机构业务汇总表(二) dao 接口
 */
public interface IBusinessReportDao {
	
	/**
	* @param model
	* @return List<BusinessReportVOModel1>
	* @description:查询保险代理机构业务汇总表(一)
	 */
	public List<BusinessReportVOModel1> queryReport1(BusinessReportVOModel1 model);

	/**
	* @param model
	* @return List<BusinessReportVOModel2>
	* @description:查询保险代理机构业务汇总表(二)
	 */
	public List<BusinessReportVOModel2> queryReport2(BusinessReportVOModel2 model);

}
