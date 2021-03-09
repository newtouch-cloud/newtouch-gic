package com.ca.cacore.rsss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.vo.IBusinessReportModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class BusinessReportCDao extends BaseDao implements IBusinessReportCDao{
	
	/**
	 * 
	* 
	* @param model
	* @return List<BusinessReportModel>
	* @description:查询保险代理机构业务汇总表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IBusinessReportModel> queryBusinessReport(IBusinessReportModel model) {
		
		return this.getSqlMapClientTemplate().queryForList("BusinessReport.queryBusinessReport",model);
		
	}
	/**
	 * 
	* 
	* @param model
	* @return List<BusinessReportModel>
	* @description:导出保险代理机构业务汇总表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IBusinessReportModel> exportBusinessReport(
			IBusinessReportModel model) {
		return this.getSqlMapClientTemplate().queryForList("BusinessReport.exportBusinessReport",model);

	}
}
