package com.ca.cacore.maas.webapp.service;

import java.util.List;

import com.ca.cacore.maas.model.bo.IProtocolModel;
import com.ca.cacore.maas.model.bo.ProtocolModel;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
* @since:    2014年4月10日   
* @author    JYF
* @description:协议管理-Service层接口
*/
public interface IProtocolService {

	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:协议管理-增加
	*/
	public ReturnMsg addProtocol(IProtocolModel model);
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:协议管理-查询
	*/
	public ReturnMsg queryProtocol(IProtocolModel model);
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:协议管理-维护
	*/
	public ReturnMsg modifyProtocol(IProtocolModel model);
	/** 
	* 
	* @param model
	* @return List<IProtocolModel>
	* @description:协议管理-导出
	*/
	public List<IProtocolModel> queryProtocolForExport(IProtocolModel model);
	/** 
	* 
	* @param model
	* @return IProtocolModel
	* @description:协议管理-签订人工号查询签订人
	*/
	public IProtocolModel getProtocolPersonInformation(IProtocolModel model);
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:协议管理-维护-页面查询
	*/
	public ReturnMsg getProtocolModifyView(IProtocolModel model);
	public ReturnMsg getProductName(IProtocolModel model);
	public ReturnMsg getProductCode(IProtocolModel model);
	public Integer addProtocolView();
	public ReturnMsg updateProtocolStatus(IProtocolModel model);
	public List<ProtocolModel> checkagreement(IProtocolModel model);
}
