package com.ca.cacore.rsss.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.rsss.domain.IInsBusProReportDomain;
import com.ca.cacore.rsss.model.vo.IInsBusProReportModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
@Service
public class InsBusProReportService implements IInsBusProReportService{

	@Autowired public IInsBusProReportDomain iinsbusproreportdomain;
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:保险代理机构业务协议电子档案查询
	*/
	public ReturnMsg queryInsBusProReport(IInsBusProReportModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IInsBusProReportModel> list = iinsbusproreportdomain.queryInsBusProReport(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	/**
	 * @author lds
	 * 保险代理机构业务协议电子档案导出
	 */
	@Override
	public List<IInsBusProReportModel> exportInsBusProReport(IInsBusProReportModel model) {
		return iinsbusproreportdomain.exportInsBusProReport(model);
	}
}
