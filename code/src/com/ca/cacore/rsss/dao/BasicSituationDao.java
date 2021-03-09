package com.ca.cacore.rsss.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.rsss.model.vo.IBasicInfomationModel;
import com.ca.cacore.rsss.model.vo.IBasicSituationModel;
import com.newtouch.core.daobase.BaseDao;
@Component
public class BasicSituationDao extends BaseDao implements IBasicSituationDao{
	/** 
	* 
	* @param model
	* @return List<IBasicSituationModel>
	* @description:基本情况表
	*/
	@SuppressWarnings("unchecked")
	public List<IBasicSituationModel> queryBasicSituation(IBasicSituationModel model) {
		return this.getSqlMapClientTemplate().queryForList("BasicSituation.queryBasicSituation", model);
	}
	/** 
	* 
	* @param model
	* @param user
	* @return List<IPersonalBusReportModel>
	* @description:基本情况表
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<IBasicSituationModel> exportBasicSituation(IBasicSituationModel model){
		return this.getSqlMapClientTemplate().queryForList("BasicSituation.exprotBasicSituation",model);
	}
	
	@Override
	public List<IBasicInfomationModel> queryBasicInfomation(IBasicInfomationModel model) {
		// TODO Auto-generated method stub
		//int count = (Integer) this.getSqlMapClientTemplate().queryForObject("BasicSituation.queryBasicInfomation_count", model);
		//System.out.println(count);
		//model.getPageCount().setAllRows(count);
		//model.getPageCount().calcPage();
		//model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		return this.getSqlMapClientTemplate().queryForList("BasicSituation.queryBasicInfomation",model);
	}
	@Override
	public IBasicInfomationModel queryBasicInfomation1(
			IBasicInfomationModel model) {
		// TODO Auto-generated method stub
		return (IBasicInfomationModel) this.getSqlMapClientTemplate().queryForObject("BasicSituation.queryBasicInfomation1",model);
	}
}
