package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IInsAgenPersonReportDao;
import com.ca.cacore.rsss.model.vo.IInsAgenPersonReportModel;
@Service
public class InsAgenPersonReportDomain implements IInsAgenPersonReportDomain{
	@Autowired public IInsAgenPersonReportDao iinsagenpersonreportdao;
	/** 
	* 
	* @param model
	* @return List<IInsAgenPersonReportModel>
	* @description:保险代理机构人员电子档案查询
	*/
	public List<IInsAgenPersonReportModel> queryInsAgenPersonReport(IInsAgenPersonReportModel model) {
		return iinsagenpersonreportdao.queryInsAgenPersonReport(model);
	}
	/** 
	* 
	* @param model
	* @param user
	* @return List<IInsCompanyBusModel>
	* @description:保险代理机构人员电子档案导出Domain层方法
	*/
	@Override
	public List<IInsAgenPersonReportModel> exportInsAgenPersonReport(IInsAgenPersonReportModel model) {
		List<IInsAgenPersonReportModel> list=iinsagenpersonreportdao.exportInsAgenPersonReport(model);
		return list;
		}
}
