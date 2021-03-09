package com.ca.cacore.rsss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.vo.IAentLifeInsModel;
import com.ca.cacore.rsss.model.vo.IPersonalBusReportModel;
import com.newtouch.core.daobase.BaseDao;
@Component
public class PersonalBusReportDao extends BaseDao implements IPersonalBusReportDao{
	
	/** 
	* 
	* @param model
	* @return List<IPersonalBusReportModel>
	* @description:业务报表(人身险公司业务)报表查询
	*/
	@SuppressWarnings("unchecked")
	public List<IPersonalBusReportModel> queryPersonalBusReport(IPersonalBusReportModel model) {
		return this.getSqlMapClientTemplate().queryForList("PersonalBusReport.queryPersonalBusReport", model);
	}
	/** 
	* 
	* @param model
	* @param user
	* @return List<IPersonalBusReportModel>
	* @description:业务报表(人身险公司业务)报表导出
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<IPersonalBusReportModel> exportPersonalBusReport(IPersonalBusReportModel model){
		return this.getSqlMapClientTemplate().queryForList("PersonalBusReport.exprotPersonalBusReport",model);
	}
	
	/**
	 * 按机构id和期次查询报表信息
	 * @param model
	 * @return
	 */
	@Override
	public List<IAentLifeInsModel> queryAgentLifeIns(IAentLifeInsModel model) {
		// TODO Auto-generated method stub
		//int count = (Integer) this.getSqlMapClientTemplate().queryForObject("PersonalBusReport.queryAgentLifeIns_count", model);
		//System.out.println(count);
		//model.getPageCount().setAllRows(count);
		//model.getPageCount().calcPage();
		//model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		return this.getSqlMapClientTemplate().queryForList("PersonalBusReport.queryAgentLifeIns",model);
	}
	
	/*
	 * 根据机构id和期次统计报表每列总计
	 */
	@Override
	public IAentLifeInsModel queryTotal(IAentLifeInsModel model) {
		// TODO Auto-generated method stub
		return (IAentLifeInsModel) this.getSqlMapClientTemplate().queryForObject("PersonalBusReport.queryTotal",model);
	}
	@Override
	public List<IAentLifeInsModel> queryByRisk(IAentLifeInsModel agentLife) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("PersonalBusReport.queryByRisk",agentLife);
	}
	@Override
	public IAentLifeInsModel queryTotal1(IAentLifeInsModel agentLife) {
		// TODO Auto-generated method stub
		return (IAentLifeInsModel) this.getSqlMapClientTemplate().queryForObject("PersonalBusReport.queryTotal1",agentLife);
	}
}
