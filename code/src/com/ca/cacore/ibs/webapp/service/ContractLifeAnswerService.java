package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.domain.IContractLifeAnswerDomain;
import com.ca.cacore.ibs.model.bo.ContractLifeModel;
import com.ca.cacore.ibs.model.bo.IContractLifeAnswerModel;
import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.bo.IPolicyLifeModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeModel;
import com.ca.cacore.ibs.model.vo.IContractLifeAnswerVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;
import com.ca.cacore.common.CodeTypeConst;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public class ContractLifeAnswerService implements IContractLifeAnswerService{
	@Autowired private IContractLifeAnswerDomain contractLifeAnswerDomain;
	
	@Override
	public ReturnMsg addPolicyAnswer(IContractLifeAnswerModel pam, IUserModel user) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			//校验
			ValidateHelper.isNullThrowException(pam.getInsBranch_id(), "保险公司机构不能为空");
			ValidateHelper.IsNullOrEmptyThrowException(pam.getBranch_id(),"机构代码不能为空");
			if(pam.getPolicy_id()==null||"".equals(pam.getPolicy_id())){
				ValidateHelper.isNullThrowException(null, "保单号不能为空，请检查");
			}
			ValidateHelper.isNullThrowException(pam.getAnswer_type(), "回访方式不能为空，请检查");
			ValidateHelper.isNullThrowException(pam.getIs_success(), "回访结果不能为空，请检查");
			contractLifeAnswerDomain.addPolicyAnswer(pam, user);//添加回访记录
			IContractLifeModel cm = new ContractLifeModel();
			cm.setInsBranch_id(pam.getInsBranch_id());
			cm.setPolicy_code(pam.getPolicy_code());
			cm.setIs_answered(pam.getIs_success());//回访是否成功
			IContractLifeModel cmDb = contractLifeAnswerDomain.getConstractMaster(cm);
			 if(CodeTypeConst.CONTRACTLIFE_IS_ANSWER_Y.equals(cmDb.getIs_answered())){
				throw new BusinessException("保单已经回访");
			}
		    contractLifeAnswerDomain.modifyConstractMaster(cm,user);//修改保单记录pl.
			IPolicyLifeModel pl =new PolicyLifeModel();
			pl.setInsBranch_id(pam.getInsBranch_id());
			pl.setPolicy_code(pam.getPolicy_code());
			pl.setIs_answered(pam.getIs_success());
			//contractLifeAnswerDomain.modifyPolicyLife(pl, user);//修改投保单表   不维护
			returnMsg.setSuccessMessage("保存成功");
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}

	@Override
	public IContractLifeModel getConstractMasterInfo(IContractLifeModel cm){
		return contractLifeAnswerDomain.getConstractMaster(cm);
	}
	
	@Override
	public ReturnMsg getPolicyAnswer(IContractLifeAnswerVOModel cm){
		ReturnMsg returnMsg = new ReturnMsg();
		IContractLifeAnswerVOModel map = contractLifeAnswerDomain.getPolicyAnswer(cm);
		returnMsg.setDataTable(TransHelper.obj2map(map));
		return returnMsg;
	}

	@Override
	public ReturnMsg queryPolicyAnswerInfo(IContractLifeVOModel cm) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IContractLifeVOModel>list = contractLifeAnswerDomain.queryPolicyAnswerInfo(cm);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public ReturnMsg modifyPolicyAnswer(IContractLifeAnswerVOModel pa, IUserModel user) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			//校验
			ValidateHelper.isNullThrowException(pa.getInsBranch_id(), "保险公司机构不能为空，请检查");
			ValidateHelper.isNullThrowException(pa.getInsBranch_id(), "保险公司机构不能为空，请检查");
			if(pa.getPolicy_id()==null||"".equals(pa.getPolicy_id())){
				ValidateHelper.isNullThrowException(null, "保单号不能为空，请检查");
			}
			ValidateHelper.isNullThrowException(pa.getAnswer_type(), "回访方式不能为空，请检查");
			ValidateHelper.isNullThrowException(pa.getIs_success(), "回访结果不能为空，请检查");
		   contractLifeAnswerDomain.modifyPolicyAnswer(pa, user);//修 回访记录
			IContractLifeModel cm = new ContractLifeModel();
			cm.setInsBranch_id(pa.getInsBranch_id());
			cm.setPolicy_code(pa.getPolicy_code());
			cm.setIs_answered(pa.getIs_success());// 回访是否成功
			contractLifeAnswerDomain.modifyConstractMaster(cm,user);//修改保单记录
			IPolicyLifeModel pl =new PolicyLifeModel();
			pl.setInsBranch_id(pa.getInsBranch_id());
			pl.setPolicy_code(pa.getPolicy_code());
			pl.setIs_answered(pa.getIs_success());
		//	contractLifeAnswerDomain.modifyPolicyLife(pl, user);//修改投保单表  不维护
			IContractLifeAnswerVOModel model = contractLifeAnswerDomain.getPolicyAnswer(pa);
			returnMsg.setDataTable(TransHelper.obj2map(model));
			returnMsg.setSuccessMessage("修改成功");
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}
}
