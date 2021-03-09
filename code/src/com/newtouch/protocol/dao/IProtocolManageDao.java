package com.newtouch.protocol.dao;

import java.util.List;

import com.newtouch.protocol.model.vo.ContractType;
import com.newtouch.protocol.model.vo.IProtocolManageModel;
import com.newtouch.protocol.model.vo.ProtocolCategoryModel;
import com.newtouch.protocol.model.vo.ProtocolManageModel;


public interface IProtocolManageDao {

	/** 
	* 
	* @param model
	* @return boolean
	* @description:协议管理-增加
	*/
	public boolean addProtocol(List<IProtocolManageModel> list);
	public boolean updateProtocol(List<IProtocolManageModel> list);
	public boolean addProtocolNew(List<ProtocolManageModel> list);

	/** 
	* 
	* @param model
	* @return List<IProtocolModel>
	* @description:协议管理-查询
	*/
	public List<IProtocolManageModel> queryProtocol(IProtocolManageModel model);
	public List<ProtocolManageModel> queryProtocolAll(IProtocolManageModel model);

	/** 
	* 
	* @param model
	* @return boolean
	* @description:协议管理-修改
	*/
	public boolean modifyProtocol(IProtocolManageModel model);
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
	* @description:协议管理-获取签订人信息(由工号查询姓名)
	*/
	public IProtocolManageModel getProtocolPersonInformation(IProtocolManageModel model);
	/** 
	* 
	* @param model
	* @return IProtocolModel
	* @description:协议管理-维护-页面查询
	*/
	public IProtocolManageModel getProtocolModifyView(IProtocolManageModel model);
	/**
	 * 保险公司协议查询重复机构
	 * @param branch_id
	 * @return
	 */
	public boolean checkBranch(String branch_id);
	/**
	 * 保险公司协议查询重复协议号
	 * 
	 */
	public boolean checkAgreement_no(IProtocolManageModel model);
	/**
	 * 注销
	 * 
	 */
	public boolean updateProtocolStatus(IProtocolManageModel model);
//	/**
//	 * 拼接协议号
//	 * 
//	 */
//	public String getAgreement_no(String branch_id);

	public Integer addProtocolPdf(IProtocolManageModel model);
	
	public List<ProtocolCategoryModel> findCategory();
	
	public List<ContractType> findContract();
	
	public List<ProtocolManageModel> queryProtocolsAgr(IProtocolManageModel model);

	/**
	 * 根据id和name查询机构是否存在
	 */
	public Integer findSysBranch(IProtocolManageModel model);
	
	public Integer findCpyBranch(IProtocolManageModel model);

	public ProtocolManageModel findAgreementNo();
	
	public Integer findSupProtocol(ProtocolManageModel model);
	
	public boolean saveSupProtocol(ProtocolManageModel model);
	
	public boolean updateSupProtocol(IProtocolManageModel model);
	
	
}
