package com.ca.cacore.rsss.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.dao.IHoldReportDao;
import com.ca.cacore.rsss.model.vo.HoldReportVOModel;
/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:专业保险中介机构业务人员持证情况报表domain 实现类
 */
@Service
public class HoldReportDomain implements IHoldReportDomain {
	@Autowired private  IHoldReportDao holdReportDao;
	
	
	/**
	 * 
	* 2014-1-15
	* @param model
	* @return List<IHoldReportVOModel>
	* @description:查询专业保险中介机构业务人员持证情况报表
	 */
	@Override
	public List<HoldReportVOModel> queryReport(HoldReportVOModel model) {
		
		return holdReportDao.queryReport(model);
	}



}
