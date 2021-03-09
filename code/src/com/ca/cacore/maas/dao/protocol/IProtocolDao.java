package com.ca.cacore.maas.dao.protocol;

import java.util.List;

import com.ca.cacore.maas.model.bo.IProtocolModel;
import com.ca.cacore.maas.model.bo.ProtocolModel;

/**
* @since:    2014年4月10日   
* @author    JYF
* @description: 协议管理-Dao接口
*/
public interface IProtocolDao {

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
	* @return List<IProtocolModel>
	* @description:协议管理-查询
	*/
	public List<IProtocolModel> queryProtocol(IProtocolModel model);
	
	/** 
	* 
	* @param model
	* @return boolean
	* @description:协议管理-修改
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
	* @description:协议管理-获取签订人信息(由工号查询姓名)
	*/
	public IProtocolModel getProtocolPersonInformation(IProtocolModel model);
	/** 
	* 
	* @param model
	* @return IProtocolModel
	* @description:协议管理-维护-页面查询
	*/
	public IProtocolModel getProtocolModifyView(IProtocolModel model);
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
	public boolean checkAgreement_no(IProtocolModel model);

	public List<IProtocolModel> getProductName(IProtocolModel model);

	public List<IProtocolModel> getProductCode(IProtocolModel model);

	public Integer addProtocolView();

	public Integer updateProtocolStatus(IProtocolModel model);

	public List<ProtocolModel> checkagreement(IProtocolModel model);
}

