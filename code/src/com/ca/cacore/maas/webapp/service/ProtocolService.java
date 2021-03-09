package com.ca.cacore.maas.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.maas.domain.protocol.IProtocolDomain;
import com.ca.cacore.maas.model.bo.IProtocolModel;
import com.ca.cacore.maas.model.bo.ProtocolModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
* @since:    2014年4月10日   
* @author    JYF
* @description:协议管理-service层
*/
@Service
public class ProtocolService implements IProtocolService {

	@Autowired IProtocolDomain protocolDomain;
	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-增加
	*/
	@Override
	public ReturnMsg addProtocol(IProtocolModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		try {
			protocolDomain.addProtocol(model);
			returnMsg.setSuccessMessage("保存成功");
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage(),true); 
		}
		return returnMsg;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-查询
	*/
	@Override
	public ReturnMsg queryProtocol(IProtocolModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		List<IProtocolModel> list=protocolDomain.queryProtocol(model);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-修改
	*/
	@Override
	public ReturnMsg modifyProtocol(IProtocolModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		try {
			protocolDomain.modifyProtocol(model);
			IProtocolModel protocolModel = protocolDomain.getProtocolModifyView(model);
			returnMsg.setSuccessMessage("修改成功");
			returnMsg.setDataTable(TransHelper.obj2map(protocolModel));
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage(),true);
		}
		return returnMsg;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-导出
	*/
	@Override
	public List<IProtocolModel> queryProtocolForExport(IProtocolModel model) {
		List<IProtocolModel> list=protocolDomain.queryProtocolForExport(model);
		return list;
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-获取签订人信息(由工号查询姓名)
	*/
	@Override
	public IProtocolModel getProtocolPersonInformation(IProtocolModel model) {
		return protocolDomain.getProtocolPersonInformation(model);
	}

	/** 
	* 
	* @param model
	* @return 
	* @description:协议管理-维护-页面
	*/
	@Override
	public ReturnMsg getProtocolModifyView(IProtocolModel model) {
		ReturnMsg returnMsg=new ReturnMsg();
		IProtocolModel l=protocolDomain.getProtocolModifyView(model);
		returnMsg.setDataTable(TransHelper.obj2map(l));
		return returnMsg;
	}

	@Override
	public ReturnMsg getProductName(IProtocolModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IProtocolModel> retList = protocolDomain.getProductName(model);
		returnMsg.setDataList(TransHelper.list2MapList(retList));
		return returnMsg;
	}

	@Override
	public ReturnMsg getProductCode(IProtocolModel model) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IProtocolModel> retList = protocolDomain.getProductCode(model);
		returnMsg.setDataList(TransHelper.list2MapList(retList));
		return returnMsg;
	}

	@Override
	public Integer addProtocolView() {
		Integer count = protocolDomain.addProtocolView();
		return count;
	}

	@Override
	public ReturnMsg updateProtocolStatus(IProtocolModel model) {
		ReturnMsg msg = new ReturnMsg();
		Integer flag = protocolDomain.updateProtocolStatus(model);
		try {
			if (flag == 1) {
				msg.setSuccessMessage("注销成功");
			}
		} catch (Exception e) {
			msg.setFailMessage(e.getMessage());
		}
		
		return msg;
	}

	@Override
	public List<ProtocolModel> checkagreement(IProtocolModel model) {
		List<ProtocolModel> list = protocolDomain.checkagreement(model);
		return list;
	}

}
