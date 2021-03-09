package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.domain.IBusinessReportDomain;
import com.ca.cacore.rsss.model.vo.BusinessReportVOModel1;
import com.ca.cacore.rsss.model.vo.BusinessReportVOModel2;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
/**
 * 
* @since:    2014年1月14日   
* @author    ZhangChen
* @description:保险代理机构业务汇总表(一)保险代理机构业务汇总表(二)service 实现类
 */
@Service
public class BusinessReportService implements IBusinessReportService {
	@Autowired private IBusinessReportDomain businessReportDomain;
	
	/**
	 * 2014-1-14
	* @param model
	* @return ReturnMsg
	* @description:查询保险代理机构业务汇总表(一)
	 */
	@Override
	public ReturnMsg queryReport1(BusinessReportVOModel1 model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<BusinessReportVOModel1> list = businessReportDomain.queryReport1(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	
	/**
	 * 2014-1-15
	* @param model
	* @return List<BusinessReportVOModel1>
	* @description:获取保险代理机构业务汇总表(一)
	 */
	@Override
	public List<BusinessReportVOModel1> getReport1(BusinessReportVOModel1 model) {
		
		return  businessReportDomain.queryReport1(model);
	}
	
	/**
	 * 2014-1-15
	* @param model
	* @return ReturnMsg
	* @description:查询保险代理机构业务汇总表(二)
	 */
	@Override
	public ReturnMsg queryReport2(BusinessReportVOModel2 model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<BusinessReportVOModel2> list = businessReportDomain.queryReport2(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	
	
	/**
	 * 2014-1-15
	* @param model
	* @return List<BusinessReportVOModel1>
	* @description:获取保险代理机构业务汇总表(二)
	 */
	@Override
	public List<BusinessReportVOModel2> getReport2(BusinessReportVOModel2 model) {
		
		return  businessReportDomain.queryReport2(model);
	}

	

}
