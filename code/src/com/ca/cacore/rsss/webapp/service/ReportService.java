package com.ca.cacore.rsss.webapp.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.domain.IHoldReportDomain;
import com.ca.cacore.rsss.model.vo.HoldReportVOModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:专业保险中介机构业务人员持证情况报表service实现类
 */
@Service
public class ReportService implements IReportService {
	@Autowired private IHoldReportDomain holdReportDomain;
	
	/**
	 * 2014-1-15
	* @param model
	* @return ReturnMsg
	* @description:查询专业保险中介机构业务人员持证情况报表
	 */
	@Override
	public ReturnMsg queryReport(HoldReportVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<HoldReportVOModel> list = holdReportDomain.queryReport(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	/**
	 * 2014-1-17
	* @param model
	* @return List<IHoldReportVOModel>
	* @description:查询专业保险中介机构业务人员持证情况报表
	 */
	@Override
	public List<HoldReportVOModel> getReport(HoldReportVOModel model) {

		return holdReportDomain.queryReport(model);
	}

}
