package com.ca.cacore.maas.domain.protocol;

import java.util.List;

import com.ca.cacore.maas.model.bo.IProtocolModel;
import com.ca.cacore.maas.model.bo.ProtocolModel;

/**
* @since:    2014年4月10日   
* @author    JYF
* @description:协议管理-dao层接口
*/
public interface IProtocolDomain {

	/** 
	* 
	* @param model
	* @return List<IProtocolModel>
	* @description:协议管理-查询
	*/
	public List<IProtocolModel> queryProtocol(IProtocolModel model);
	/** 
	* 
	* @param model
	* @return boolean
	* @description:协议管理-维护
	*/
	public boolean modifyProtocol(IProtocolModel model);
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
	* @description:协议管理-有签订人工号查询签订人
	*/
	public IProtocolModel getProtocolPersonInformation(IProtocolModel model);
	/** 
	* 
	* @param model
	* @return boolean
	* @description:协议管理-增加
	*/
	public boolean addProtocol(IProtocolModel model);
	/** 
	* 
	* @param model
	* @return IProtocolModel
	* @description:协议管理-维护-页面查询
	*/
	public IProtocolModel getProtocolModifyView(IProtocolModel model);
	public List<IProtocolModel> getProductName(IProtocolModel model);
	public List<IProtocolModel> getProductCode(IProtocolModel model);
	public Integer addProtocolView();
	public Integer updateProtocolStatus(IProtocolModel model);
	public List<ProtocolModel> checkagreement(IProtocolModel model);
}
