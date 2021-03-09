package com.ca.cacore.rsss.dao;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IAentLifeInsModel;
import com.ca.cacore.rsss.model.vo.IPersonalBusReportModel;

public interface IPersonalBusReportDao {
	
	/**
	 * @author lds
	 *	业务报表(人身险公司业务)查询dao接口方法
	 */
		public List<IPersonalBusReportModel> queryPersonalBusReport(IPersonalBusReportModel model);

		/** 
		* 
		* @param model
		* @return List<IPersonalBusReportModel>
		* @description:业务报表(人身险公司业务)报表导出
		*/
		public List<IPersonalBusReportModel> exportPersonalBusReport(IPersonalBusReportModel model);
		
		/**
		 * 按机构id和期次查询报表信息
		 * @param model
		 * @return
		 */
		public List<IAentLifeInsModel> queryAgentLifeIns(IAentLifeInsModel model);
		
		/*
		 * 根据机构id和期次统计报表每列总计
		 */
		public IAentLifeInsModel queryTotal(IAentLifeInsModel model);

		public List<IAentLifeInsModel> queryByRisk(IAentLifeInsModel agentLife);

		public IAentLifeInsModel queryTotal1(IAentLifeInsModel agentLife);
}	
