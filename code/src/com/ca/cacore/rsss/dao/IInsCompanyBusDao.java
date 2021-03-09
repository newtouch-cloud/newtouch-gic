package com.ca.cacore.rsss.dao;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IAgentPropertyModel;
import com.ca.cacore.rsss.model.vo.IInsCompanyBusModel;


public interface IInsCompanyBusDao {
	
/**
 * @author lds
 *	业务报表(产险公司业务)查询dao接口方法
 */
	public List<IInsCompanyBusModel> queryInsCompanyBus(IInsCompanyBusModel model);

	/** 
	* 
	* @param model
	* @return List<IInsCompanyBusModel>
	* @description:业务报表(产险公司业务)报表导出
	*/
	public List<IInsCompanyBusModel> exportInsCompanyBus(IInsCompanyBusModel model);
	
	/**
	 * 查询产险公司业务报表
	 * @param model
	 * @return
	 */
	public IAgentPropertyModel queryAgentProperty(IAgentPropertyModel model);

	/**
	 * 根据机构id和期次统计报表每列总计
	 * @param model
	 * @return
	 */
	public IAgentPropertyModel queryTotal(IAgentPropertyModel model);
}
