package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IInsCompanyBusDao;
import com.ca.cacore.rsss.domain.IInsCompanyBusDomain;
import com.ca.cacore.rsss.model.vo.IAgentPropertyModel;
import com.ca.cacore.rsss.model.vo.IInsCompanyBusModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
@Service
public class InsCompanyBusService implements IInsCompanyBusService{
	
	@Autowired public IInsCompanyBusDomain inscompanybusdomain;
	@Autowired public IInsCompanyBusDao inscompanybusdao;
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:业务报表(产险公司业务)报表查询
	*/
	public ReturnMsg queryInsCompanyBus(IInsCompanyBusModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IInsCompanyBusModel> list = inscompanybusdomain.queryInsCompanyBus(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	/**
	 * @author lds
	 * 业务报表(产险公司业务)报表查询导出
	 */
	@Override
	public List<IInsCompanyBusModel> exportInsCompanyBus(IInsCompanyBusModel model) {
		return inscompanybusdomain.exportInsCompanyBus(model);
	}
	@Override
	public IAgentPropertyModel queryAgentProperty(IAgentPropertyModel model) {
		// TODO Auto-generated method stub
		return inscompanybusdao.queryAgentProperty(model);
	}
	
	/**
	 * 查询产险报表
	 * @param model
	 * @return
	 */
	/*public List<IAgentPropertyModel> queryAgentPropertys(
			IAgentPropertyModel model) {
		// TODO Auto-generated method stub
		return inscompanybusdao.queryAgentProperty(model);
	}*/
	/*
	 * 根据机构id和期次统计报表每列总计
	 */

	@Override
	public IAgentPropertyModel queryTotal(IAgentPropertyModel model) {
		// TODO Auto-generated method stub
		return inscompanybusdao.queryTotal(model);
	}
}	
