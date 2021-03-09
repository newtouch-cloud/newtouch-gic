package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IInsBusProReportDao;
import com.ca.cacore.rsss.model.vo.IInsBusProReportModel;
@Service
public class InsBusProReportDomain implements IInsBusProReportDomain {
	
	@Autowired public IInsBusProReportDao iinsbusproreportdao;
	/** 
	* 
	* @param model
	* @return List<IInsBusProReportModel>
	* @description:保险代理机构业务协议电子档案查询
	*/
	public List<IInsBusProReportModel> queryInsBusProReport(IInsBusProReportModel model) {
		return iinsbusproreportdao.queryInsBusProReport(model);
	}
	/** 
	* 
	* @param model
	* @param user
	* @return List<IInsBusProReportModel>
	* @description:保险代理机构业务协议电子档案导出Domain层方法
	*/
	@Override
	public List<IInsBusProReportModel> exportInsBusProReport(IInsBusProReportModel model) {
		List<IInsBusProReportModel> list=iinsbusproreportdao.exportInsBusProReport(model);
		return list;
		}
}
