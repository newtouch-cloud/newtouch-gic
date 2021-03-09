package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IBusinessReportCDao;
import com.ca.cacore.rsss.model.vo.IBusinessReportModel;
@Service
public class BusinessReportCDomain implements IBusinessReportCDomain{
	
@Autowired private  IBusinessReportCDao businessReportcDao;
	
	
	/**
	 * 
	* 
	* @param model
	* @return List<BusinessReportVOModel1>
	* @description:查询保险代理机构业务汇总表
	 */
	@Override
	public List<IBusinessReportModel> queryBusinessReport(IBusinessReportModel model) {
		return businessReportcDao.queryBusinessReport(model);
	}


	@Override
	public List<IBusinessReportModel> exportBusinessReport(
			IBusinessReportModel model) {
		return businessReportcDao.exportBusinessReport(model);
	}
	
}
