package com.ca.cacore.rsss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.vo.IAgentPropertyModel;
import com.ca.cacore.rsss.model.vo.IInsCompanyBusModel;
import com.newtouch.core.daobase.BaseDao;
@Component
public class InsCompanyBusDao extends BaseDao implements IInsCompanyBusDao{
	
	/** 
	* 
	* @param model
	* @return List<IInsCompanyBusModel>
	* @description:业务报表(产险公司业务)报表查询
	*/
	public List<IInsCompanyBusModel> queryInsCompanyBus(IInsCompanyBusModel model) {
		return this.getSqlMapClientTemplate().queryForList("insCompanyBus.queryInsCompanyBus", model);
	}
	/** 
	* 
	* @param model
	* @param user
	* @return List<IInsCompanyBusModel>
	* @description:业务报表(产险公司业务)报表导出
	*/
	@Override
	public List<IInsCompanyBusModel> exportInsCompanyBus(IInsCompanyBusModel model){
		return this.getSqlMapClientTemplate().queryForList("insCompanyBus.exprotInsCompanyBus",model);
	}
	@Override
	public IAgentPropertyModel queryAgentProperty(IAgentPropertyModel model) {
		//int count = (Integer) this.getSqlMapClientTemplate().queryForObject("insCompanyBus.queryAgentProperty_count", model);
		//System.out.println(count);
		//model.getPageCount().setAllRows(count);
		//model.getPageCount().calcPage();
		//model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		// TODO Auto-generated method stub
		return (IAgentPropertyModel)this.getSqlMapClientTemplate().queryForObject("insCompanyBus.queryAgentProperty",model);
	}
	
	/**
	 * 根据机构id和期次统计报表每列总计
	 * @param model
	 * @return
	 */
	@Override
	public IAgentPropertyModel queryTotal(IAgentPropertyModel model) {
		// TODO Auto-generated method stub
		return (IAgentPropertyModel) this.getSqlMapClientTemplate().queryForObject("insCompanyBus.queryTotal",model);
	}
}
