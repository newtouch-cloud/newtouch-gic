package com.ca.cacore.rsss.dao;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IInsBusProReportModel;

public interface IInsBusProReportDao {
	
	/**
	 * @author lds
	 *	保险代理机构业务协议电子档案查询dao接口方法
	 */
		public List<IInsBusProReportModel> queryInsBusProReport(IInsBusProReportModel model);

		/** 
		* 
		* @param model
		* @return List<IInsBusProReportModel>
		* @description:保险代理机构业务协议电子档案报表导出
		*/
		public List<IInsBusProReportModel> exportInsBusProReport(IInsBusProReportModel model);

}
