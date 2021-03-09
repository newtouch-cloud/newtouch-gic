package com.newtouch.protocol.webapp.service;


import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.protocol.model.vo.ContractType;
import com.newtouch.protocol.model.vo.IProtocolManageModel;
import com.newtouch.protocol.model.vo.ProtocolCategoryModel;

public interface IProtocolManageService {
	/** 
	* 
	* @param model 
	 * @return ReturnMsg
	* @description:协议管理-增加
	*/
	public ReturnMsg addProtocol(List<IProtocolManageModel> list);
	public ReturnMsg updateProtocol(List<IProtocolManageModel> list) ;
	
	/**
	 * 保存pdf上传路径
	 * @param model
	 * @return
	 */
	public ReturnMsg addProtocolPdf(IProtocolManageModel model);
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:协议管理-查询
	*/
	public ReturnMsg queryProtocol(IProtocolManageModel model);
	
	public ReturnMsg queryProtocolAll(IProtocolManageModel model);
	
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:协议管理-修改
	*/
	public ReturnMsg modifyProtocol(IProtocolManageModel model);
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
	* @description:协议管理-签订人工号查询签订人
	*/
	public IProtocolManageModel getProtocolPersonInformation(IProtocolManageModel model);
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:协议管理-修改-页面查询
	*/
	public ReturnMsg getProtocolModifyView(IProtocolManageModel model,String type);
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:协议管理-注销
	*/
	public ReturnMsg updateProtocolStatus(IProtocolManageModel model);
	/** 
	* 
	* @param model
	* @return ReturnMsg
	* @description:协议管理-查询协议号总数
	*/
	
	
    /**
     * 导入协议操作
     */
	public String importProtocol(MultipartFile file, IUserModel user,String protocol_category) throws IOException;

	/**
	 * 查询协议编号
	 */
	public List<ProtocolCategoryModel> findCategory();
	
	/**
	 * 查询合同类型
	 */
	public List<ContractType> findContract();

}
