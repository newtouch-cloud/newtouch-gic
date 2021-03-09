package com.ca.cacore.rsss.dao;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IInsAgenPersonReportModel;

public interface IInsAgenPersonReportDao {
	/**
	 * @author lds
	 *	保险代理机构人员电子档案查询dao接口方法
	 */
		public List<IInsAgenPersonReportModel> queryInsAgenPersonReport(IInsAgenPersonReportModel model);

		/** 
		* 
		* @param model
		* @return List<IInsAgenPersonReportModel>
		* @description:保险代理机构人员电子档案报表导出
		*/
		public List<IInsAgenPersonReportModel> exportInsAgenPersonReport(IInsAgenPersonReportModel model);

}
