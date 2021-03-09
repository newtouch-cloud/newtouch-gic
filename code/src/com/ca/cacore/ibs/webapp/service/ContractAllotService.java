package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.domain.IContractAllotDomain;
import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;

/**
* @since:    2013年12月23日   
* @author    WanBo
* @description: 保单分配Service层
*/
@Service
public class ContractAllotService extends ServerBase implements IContractAllotService{
	@Autowired private IContractAllotDomain contractAllotDomain;
	
	/**
	 * 查询服务人员所有的保单信息列表
	 * @param IContractAllotHisVOModel
	 * @return List<String>
	 * @description: 根据分配前服务人员代码查询服务人员所有的保单信息列表
	 */
	@Override
	public List<String> queryContList(IContractAllotHisVOModel model) {
		return contractAllotDomain.queryContList(model);
	}
	/**
	 * 更新保单服务人员
	 * @param IContractAllotHisVOModel,IUserModel
	 * @return ReturnMsg
	 * @description: 更新保单服务人员
	 */
	@Override
	public ReturnMsg updateContS(IContractAllotHisVOModel model,IUserModel user) throws BusinessException{
		ReturnMsg returnMsg=new ReturnMsg();
		try{
			if(model.getPolicy_ids() == null){
				throw new BusinessException("没有勾选任何保单,请重新分配");
			}
			boolean up =  contractAllotDomain.updateContS(model, user);//调用domain层更新组织信息
			boolean add=contractAllotDomain.addContHis(model, user);//调用domain层增加历史记录
			if(up&&add){
				returnMsg.setSuccessMessage("保存成功");
			}
		}catch(BusinessException be){
			returnMsg.setFailMessage(be.getMessage(), true);
		}
		return returnMsg;
	}
	/**
	 * 查询分配前服务人员以及机构,保单等信息
	 * @param IContractAllotHisVOModel
	 * @return List<String>
	 * @description: 根据保单号查询分配前服务人员以及机构,保单等信息
	 */
	@Override
	public List<String> queryContInfo(IContractAllotHisVOModel model) {
		return contractAllotDomain.queryContInfo(model);
	}
	/**
	 * 通过分配后服务人员代码查询姓名
	 * @param IContractAllotHisVOModel
	 * @return IContractAllotHisVOModel
	 * @description: 通过分配后服务人员代码查询姓名
	 */
	@Override
	public IContractAllotHisVOModel queryAftSName(IContractAllotHisVOModel model) {
		return contractAllotDomain.queryAftSName(model);
	}
	/**
	 * 根据分配前服务人员代码查询服务人员代码 
	 * @param IContractAllotHisVOModel
	 * @return IContractAllotHisVOModel
	 * @description: 根据分配前服务人员代码查询服务人员代码 
	 */
	@Override
	public IContractAllotHisVOModel queryServiceId(IContractAllotHisVOModel model) {
		return contractAllotDomain.queryServiceId(model);
	}
	/**
	 * 根据保单号查询保单号
	 * @param IContractAllotHisVOModel
	 * @return IContractAllotHisVOModel
	 * @description: 根据保单号查询保单号
	 */
	@Override
	public IContractAllotHisVOModel queryPCode(IContractAllotHisVOModel model) {
		return contractAllotDomain.queryPCode(model);
	}

}
