package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.domain.IBusinessReportCDomain;
import com.ca.cacore.rsss.model.vo.IBusinessReportModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
@Service
public class BusinessReportCService implements IBusinessReportCService {
		
@Autowired private IBusinessReportCDomain businessReportcDomain;
	
	/**
	* @param model
	* @return ReturnMsg
	* @description:查询保险代理机构业务汇总表(一)
	 */
	@Override
	public ReturnMsg queryBusinessReport(IBusinessReportModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IBusinessReportModel> list = businessReportcDomain.queryBusinessReport(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public List<IBusinessReportModel> exportBusinessReport(
			IBusinessReportModel model) {
		List<IBusinessReportModel> list = businessReportcDomain.exportBusinessReport(model);
		return list;
	}
}
