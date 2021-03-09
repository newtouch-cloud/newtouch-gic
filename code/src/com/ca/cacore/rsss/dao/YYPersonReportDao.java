package com.ca.cacore.rsss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.vo.IYYPersonReportVOModel;
import com.newtouch.core.daobase.BaseDao;
@Component
public class YYPersonReportDao extends BaseDao implements IYYPersonReportDao{
	/**
	 * 
	* 
	* @param model
	* @return List<IYYPersonReportVOModel>
	* @description:查询营运人员效力情况表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IYYPersonReportVOModel> queryYYPersonReport(IYYPersonReportVOModel model) {
		Integer count = (Integer)this.getSqlMapClientTemplate().queryForObject("YYPersonReport.YYPersonReport_Count",model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		List<IYYPersonReportVOModel> list=this.getSqlMapClientTemplate().queryForList("YYPersonReport.queryYYPersonReport",model);
		return list;
	}
	/**
	 * 
	* 
	* @param model
	* @return List<BusinessReportModel>
	* @description:导出营运人员效力情况表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IYYPersonReportVOModel> exportYYPersonReport(
			IYYPersonReportVOModel model) {
		return this.getSqlMapClientTemplate().queryForList("YYPersonReport.exportYYPersonReport",model);

	}
}
