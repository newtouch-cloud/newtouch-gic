package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.domain.IYYPersonReportDomain;
import com.ca.cacore.rsss.model.vo.IYYPersonReportVOModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
@Service
public class YYPersonReportService implements IYYPersonReportService{
	
@Autowired private IYYPersonReportDomain iyypersonreportdomain;
	
	/**
	* @param model
	* @return ReturnMsg
	* @description:查询营运人员效力情况表
	 */
	@Override
	public ReturnMsg queryYYPersonReport(IYYPersonReportVOModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IYYPersonReportVOModel> list = iyypersonreportdomain.queryYYPersonReport(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public List<IYYPersonReportVOModel> exportYYPersonReport(
			IYYPersonReportVOModel model) {
		List<IYYPersonReportVOModel> list = iyypersonreportdomain.exportYYPersonReport(model);
		return list;
	}
}
