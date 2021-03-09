package com.ca.cacore.ibs.webapp.service;


import java.util.List;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 
 * @author WanBo
 * @description 保单分配 Service层接口
 */
public interface IContractAllotService {
	/**
	 * 查询服务人员所有的保单信息列表
	 * @param IContractAllotHisVOModel
	 * @return List<String>
	 * @description: 根据分配前服务人员代码查询服务人员所有的保单信息列表
	 */
	public List<String> queryContList(IContractAllotHisVOModel model);
	/**
	 * 更新保单服务人员
	 * @param IContractAllotHisVOModel,IUserModel
	 * @return ReturnMsg
	 * @description: 更新保单服务人员
	 */
	public ReturnMsg  updateContS(IContractAllotHisVOModel model,IUserModel user) throws BusinessException;
	/**
	 * 查询分配前服务人员以及机构,保单等信息
	 * @param IContractAllotHisVOModel
	 * @return List<String>
	 * @description: 根据保单号查询分配前服务人员以及机构,保单等信息
	 */
	public List<String> queryContInfo(IContractAllotHisVOModel model);
	/**
	 * 通过分配后服务人员代码查询姓名
	 * @param IContractAllotHisVOModel
	 * @return IContractAllotHisVOModel
	 * @description: 通过分配后服务人员代码查询姓名
	 */
	public IContractAllotHisVOModel queryAftSName(IContractAllotHisVOModel model);//通过分配后服务人员查询姓名
	/**
	 * 根据分配前服务人员代码查询服务人员代码 
	 * @param IContractAllotHisVOModel
	 * @return IContractAllotHisVOModel
	 * @description: 根据分配前服务人员代码查询服务人员代码 
	 */
	public IContractAllotHisVOModel queryServiceId(IContractAllotHisVOModel model);//通过分配前服务人员代码查询是否存在
	/**
	 * 根据保单号查询保单号
	 * @param IContractAllotHisVOModel
	 * @return IContractAllotHisVOModel
	 * @description: 根据保单号查询保单号
	 */
	public IContractAllotHisVOModel queryPCode(IContractAllotHisVOModel model);//通过保单号查询保单号是否存在
}
