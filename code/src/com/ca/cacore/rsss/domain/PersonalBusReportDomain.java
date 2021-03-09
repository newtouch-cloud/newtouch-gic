package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IPersonalBusReportDao;
import com.ca.cacore.rsss.model.vo.IPersonalBusReportModel;
@Service
public class PersonalBusReportDomain implements IPersonalBusReportDomain{
	
	@Autowired public IPersonalBusReportDao iperrsonalbusreportdao;
	/** 
	* 
	* @param model
	* @return List<IPersonalBusReportModel>
	* @description:业务报表(人身险公司业务)报表查询
	*/
	public List<IPersonalBusReportModel> queryPersonalBusReport(IPersonalBusReportModel model) {
		return iperrsonalbusreportdao.queryPersonalBusReport(model);
	}
	/** 
	* 
	* @param model
	* @param user
	* @return List<IPersonalBusReportModel>
	* @description:业务报表(人身险公司业务)报表导出
	*/
	@Override
	public List<IPersonalBusReportModel> exportPersonalBusReport(IPersonalBusReportModel model) {
		List<IPersonalBusReportModel> list=iperrsonalbusreportdao.exportPersonalBusReport(model);
		return list;
		}
}
