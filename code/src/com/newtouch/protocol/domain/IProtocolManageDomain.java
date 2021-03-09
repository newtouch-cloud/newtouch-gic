package com.newtouch.protocol.domain;

import java.util.List;

import com.newtouch.protocol.model.vo.ContractType;
import com.newtouch.protocol.model.vo.IProtocolManageModel;
import com.newtouch.protocol.model.vo.ProtocolCategoryModel;
import com.newtouch.protocol.model.vo.ProtocolManageModel;

public interface IProtocolManageDomain {
	/** 
	* 
	* @param model
	* @return List<IProtocolModel>
	* @description:协议管理-查询
	*/
	public List<IProtocolManageModel> queryProtocol(IProtocolManageModel model);
	
	public List<ProtocolManageModel> queryProtocolAll(IProtocolManageModel model);

	public boolean updateProtocol(List<IProtocolManageModel> list) ;
	/** 
	* 
	* @param model
	* @return boolean
	* @description:协议管理修改
	*/
	public boolean modifyProtocl(IProtocolManageModel model);
	/** 
	* 
	* @param model
	* @return List<IProtocolModel>
	* @description:协议管理-导出
	*/
	public List<IProtocolManageModel> queryProtocolForExport(IProtocolManageModel model);
	/** 
	* 
	* @param model
	* @return IProtocolModel
	* @description:协议管理-有签订人工号查询签订人
	*/
	public IProtocolManageModel getProtocolPersonInformation(IProtocolManageModel model);
	/** 
	* 
	* @param model
	* @return boolean
	* @description:协议管理-增加
	*/
	public boolean addProtocol(List<IProtocolManageModel> list);
	/** 
	* 
	* @param model
	* @return IProtocolModel
	* @description:协议管理-修改-页面查询
	*/
	public IProtocolManageModel getProtocolModifyView(IProtocolManageModel model);
	/** 
	* 
	* @param model
	* @return IProtocolModel
	* @description:协议管理-注销
	*/
	public boolean updateProtocolStatus(IProtocolManageModel model);
//	/** 
//	* 
//	* @param model
//	* @return IProtocolModel
//	* @description:协议管理-拼接协议号
//	*/
//	public String getAgreement_no(String branch_id);

	public boolean addProtocolNew(List<ProtocolManageModel> list);
	
	/**
	 * 
	 */
	public Integer addProtocolPdf(IProtocolManageModel model);

	/**
	 * 查询协议类型
	 */
	public List<ProtocolCategoryModel> findCategory();
	
	
	public List<ContractType> findContract();
	public List<ProtocolManageModel> queryProtocolsAgr(IProtocolManageModel model);


}
