package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IInsCompanyBusDao;
import com.ca.cacore.rsss.model.vo.IInsCompanyBusModel;

@Service
public class InsCompanyBusDomain implements IInsCompanyBusDomain{
	
	@Autowired public IInsCompanyBusDao inscompanybusdao;
	/** 
	* 
	* @param model
	* @return List<IInsCompanyBusModel>
	* @description:业务报表(产险公司业务)报表查询
	*/
	public List<IInsCompanyBusModel> queryInsCompanyBus(IInsCompanyBusModel model) {
		return inscompanybusdao.queryInsCompanyBus(model);
	}
	/** 
	* 
	* @param model
	* @param user
	* @return List<IInsCompanyBusModel>
	* @description:业务报表(产险公司业务)报表导出
	*/
	@Override
	public List<IInsCompanyBusModel> exportInsCompanyBus(IInsCompanyBusModel model) {
      	List<IInsCompanyBusModel> list=inscompanybusdao.exportInsCompanyBus(model);
		return list;
		}
}
