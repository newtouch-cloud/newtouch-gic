package com.ca.cacore.rsss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.vo.IInsBusProReportModel;
import com.newtouch.core.daobase.BaseDao;
@Component
public class InsBusProReportDao extends BaseDao implements IInsBusProReportDao{
	
	/** 
	* 
	* @param model
	* @return List<IInsBusProReportModel>
	* @description:保险代理机构业务协议电子档案查询
	*/
	@SuppressWarnings("unchecked")
	public List<IInsBusProReportModel> queryInsBusProReport(IInsBusProReportModel model) {
		return this.getSqlMapClientTemplate().queryForList("InsBusProReport.queryInsBusProReport", model);
	}
	/** 
	* 
	* @param model
	* @param user
	* @return List<IInsAgenPersonReportModel>
	* @description:保险代理机构业务协议电子档案导出
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<IInsBusProReportModel> exportInsBusProReport(IInsBusProReportModel model){
		return this.getSqlMapClientTemplate().queryForList("InsBusProReport.exprotInsBusProReport",model);
	}
}
