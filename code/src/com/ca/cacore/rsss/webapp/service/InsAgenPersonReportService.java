package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.domain.IInsAgenPersonReportDomain;
import com.ca.cacore.rsss.model.vo.IInsAgenPersonReportModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
@Service
public class InsAgenPersonReportService implements IInsAgenPersonReportService{

	@Autowired public IInsAgenPersonReportDomain iinsagenpersonreportdomain;
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description://保险代理机构人员电子档案
	*/
	public ReturnMsg queryInsAgenPersonReport(IInsAgenPersonReportModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IInsAgenPersonReportModel> list = iinsagenpersonreportdomain.queryInsAgenPersonReport(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	/**
	 * @author lds
	 * //保险代理机构人员电子档案
	 */
	@Override
	public List<IInsAgenPersonReportModel> exportInsAgenPersonReport(IInsAgenPersonReportModel model) {
		return iinsagenpersonreportdomain.exportInsAgenPersonReport(model);
	}
}
