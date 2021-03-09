package com.ca.cacore.rsss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.vo.IInsAgenPersonReportModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class InsAgenPersonReportDao extends BaseDao implements IInsAgenPersonReportDao{

	/** 
	* 
	* @param model
	* @return List<IInsAgenPersonReportModel>
	* @description:保险代理机构人员电子档案查询
	*/
	@SuppressWarnings("unchecked")
	public List<IInsAgenPersonReportModel> queryInsAgenPersonReport(IInsAgenPersonReportModel model) {
		return this.getSqlMapClientTemplate().queryForList("InsAgenPersonReport.queryInsAgenPersonReport", model);
	}
	/** 
	* 
	* @param model
	* @param user
	* @return List<IInsAgenPersonReportModel>
	* @description:保险代理机构人员电子档案导出
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<IInsAgenPersonReportModel> exportInsAgenPersonReport(IInsAgenPersonReportModel model){
		return this.getSqlMapClientTemplate().queryForList("InsAgenPersonReport.exprotInsAgenPersonReport",model);
	}
}
