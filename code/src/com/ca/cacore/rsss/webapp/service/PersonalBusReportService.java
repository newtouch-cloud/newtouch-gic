package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IPersonalBusReportDao;
import com.ca.cacore.rsss.domain.IPersonalBusReportDomain;
import com.ca.cacore.rsss.model.vo.IAentLifeInsModel;
import com.ca.cacore.rsss.model.vo.IPersonalBusReportModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
@Service
public class PersonalBusReportService implements IPersonalBusReportService{
	
	@Autowired public IPersonalBusReportDomain ipersonalbusreportdomain;
	@Autowired public IPersonalBusReportDao iperrsonalbusreportdao;
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:业务报表(人身险公司业务)报表查询
	*/
	public ReturnMsg queryPersonalBusReport(IPersonalBusReportModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPersonalBusReportModel> list = ipersonalbusreportdomain.queryPersonalBusReport(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	/**
	 * @author lds
	 * 业务报表(人身险公司业务)报表查询导出
	 */
	@Override
	public List<IPersonalBusReportModel> exportPersonalBusReport(IPersonalBusReportModel model) {
		return ipersonalbusreportdomain.exportPersonalBusReport(model);
	}
	@Override
	public ReturnMsg queryAgentLifeIns(IAentLifeInsModel model) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();
		List<IAentLifeInsModel> list = iperrsonalbusreportdao.queryAgentLifeIns(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	
	/**
	 * 按机构id和期次查询报表信息
	 * @param model
	 * @return
	 */
	@Override
	public List<IAentLifeInsModel> queryAgentLifeInss(IAentLifeInsModel model) {
		// TODO Auto-generated method stub
		return iperrsonalbusreportdao.queryAgentLifeIns(model);
	}
	
	/*
	 * 根据机构id和期次统计报表每列总计
	 */
	@Override
	public IAentLifeInsModel queryTotal(IAentLifeInsModel model) {
		// TODO Auto-generated method stub
		return iperrsonalbusreportdao.queryTotal(model);
	}
	@Override
	public List<IAentLifeInsModel> queryByRisk(IAentLifeInsModel agentLife) {
		// TODO Auto-generated method stub
		return iperrsonalbusreportdao.queryByRisk(agentLife);
	}
	@Override
	public IAentLifeInsModel queryTotal1(IAentLifeInsModel agentLife) {
		// TODO Auto-generated method stub
		return iperrsonalbusreportdao.queryTotal1(agentLife);
	}
	
}
