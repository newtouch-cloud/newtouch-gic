package com.ca.cacore.rsss.domain;

import java.util.List;

import com.ca.cacore.rsss.model.vo.IInsCompanyBusModel;


public interface IInsCompanyBusDomain {
		
	/** 
	* 
	* @param model
	* @return List<IInsCompanyBusModel>
	* @description:业务报表(产险公司业务)报表查询Domain层方法
	*/
	public List<IInsCompanyBusModel> queryInsCompanyBus(IInsCompanyBusModel model);
	/** 
	* 
	* @param model
	* @return List<IInsCompanyBusModel>
	* @description:业务报表(产险公司业务)报表导出Domain层方法
	*/
	public List<IInsCompanyBusModel> exportInsCompanyBus(IInsCompanyBusModel model);

}
