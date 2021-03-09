package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IBusinessReportDao;
import com.ca.cacore.rsss.model.vo.BusinessReportVOModel1;
import com.ca.cacore.rsss.model.vo.BusinessReportVOModel2;
/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:保险代理机构业务汇总表(一)保险代理机构业务汇总表(二) domain 实现类
 */
@Service
public class BusinessReportDomain implements IBusinessReportDomain {
	@Autowired private  IBusinessReportDao businessReportDao;
	
	
	/**
	 * 
	* 
	* @param model
	* @return List<BusinessReportVOModel1>
	* @description:查询保险代理机构业务汇总表(一)
	 */
	@Override
	public List<BusinessReportVOModel1> queryReport1(BusinessReportVOModel1 model) {
		
		return businessReportDao.queryReport1(model);
	}
	
	/**
	 * 
	* 
	* @param model
	* @return List<BusinessReportVOModel2>
	* @description:查询保险代理机构业务汇总表(二)
	 */
	@Override
	public List<BusinessReportVOModel2> queryReport2(BusinessReportVOModel2 model) {
		
		return businessReportDao.queryReport2(model);
	}

}
