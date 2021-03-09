package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.domain.IPolicyLifeAckDomain;
import com.ca.cacore.ibs.model.bo.ContractLifeModel;
import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.vo.IPolicyImageVoModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeAckVOModel;
import com.ca.cacore.ibs.model.vo.PolicyImageVoModel;
import com.ca.cacore.common.CodeTypeConst;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public class PolicyLifeAckService implements IPolicyLifeAckService{
	@Autowired private IPolicyLifeAckDomain policyLifeAckDomain;
	@Autowired private IPolicyImageService  policyImageService ;

	@Override
	public ReturnMsg policyLifeAck(IPolicyLifeAckVOModel pla, IUserModel user) throws BusinessException{
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			//校验
			ValidateHelper.isNullThrowException(pla.getSend_code(), "投保单号不能为空，请检查");
			ValidateHelper.isNullThrowException(pla.getInsBranch_id(), "保险公司机构不能为空，请检查");
			ValidateHelper.isNullThrowException(pla.getBranch_id(), "销售机构不能为空，请检查");
			ValidateHelper.IsNullOrEmptyThrowException(pla.getAck_branch_date(),"公司签收日期不能为空");
			ValidateHelper.IsNullOrEmptyThrowException(pla.getAck_customer_date(),"客户签收日期不能为空");
			if(!CodeTypeConst.CONTRACTLIFE_STATUS_WAITACK.equals(pla.getStatus())){
				ValidateHelper.isNullThrowException(null, "保单必须是已承保待客户回执状态");
			}
			//添加回执录入信息
			policyLifeAckDomain.addPolicyLifeAck(pla, user);
			//修改保单回执核销信息
			IContractLifeModel contractLifeModel = new ContractLifeModel();
			contractLifeModel.setPolicy_code(pla.getPolicy_code());
			contractLifeModel.setPolicy_id(pla.getPolicy_id());
			contractLifeModel.setInsBranch_id(pla.getInsBranch_id());
			contractLifeModel.setSign_date(pla.getAck_customer_date());
			contractLifeModel.setRtn_date(pla.getAck_branch_date());
			policyLifeAckDomain.modifyConstractLifeForAdd(contractLifeModel, user);  
		    //添加影像信息
			if(pla.getPolicyImageModel()!=null&&pla.getPolicyImageModel().getFile_ids()!=null){
				policyImageService.concernPolicyImage(pla.getPolicyImageModel(), user);
			}
			returnMsg.setSuccessMessage("保存成功");
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}

	@Override
	public ReturnMsg queryPolicyAnswerList(IPolicyLifeAckVOModel cm) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPolicyLifeAckVOModel> list = policyLifeAckDomain.queryPolicyLifeAckList(cm);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public ReturnMsg getPolicyLifeAckView(Integer seq_id) {
		ReturnMsg returnMsg = new ReturnMsg();
		IPolicyLifeAckVOModel map = policyLifeAckDomain.getPolicyLifeAckViewr(seq_id);
		IPolicyImageVoModel pm=new PolicyImageVoModel();
		pm.setPolicy_id(map.getPolicy_id());
		pm.setBus_type(CodeTypeConst.IMAGE_BUSTYPE_ACK);//回执的影像
		List<IPolicyImageModel> list = policyImageService.getImageList(pm);
		returnMsg.setDataList(TransHelper.list2MapList(list));//影像显示
		returnMsg.setDataTable(TransHelper.obj2map(map));
		return returnMsg;
	}

	@Override
	public ReturnMsg modifyPolicyLifeAck(IPolicyLifeAckVOModel pla, IUserModel user) throws BusinessException {
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			//校验
			ValidateHelper.isNullThrowException(pla.getPolicy_code(), "保单号不能为空，请检查");
			ValidateHelper.isNullThrowException(pla.getInsBranch_id(), "保险公司机构不能为空，请检查");
			ValidateHelper.isNullThrowException(pla.getBranch_id(), "销售机构不能为空，请检查");
			ValidateHelper.IsNullOrEmptyThrowException(pla.getAck_branch_date(),"客户签收日期不能为空");
			ValidateHelper.IsNullOrEmptyThrowException(pla.getAck_customer_date(),"公司签收日期不能为空");
			//修改回执信息
			policyLifeAckDomain.modifyPolicyLifeAck(pla, user);
			//修改保单回执核销信息
			IContractLifeModel contractLifeModel = new ContractLifeModel();
			contractLifeModel.setPolicy_code(pla.getPolicy_code());
			contractLifeModel.setPolicy_id(pla.getPolicy_id());
			contractLifeModel.setInsBranch_id(pla.getInsBranch_id());
			contractLifeModel.setSign_date(pla.getAck_customer_date());
			contractLifeModel.setRtn_date(pla.getAck_branch_date());
			policyLifeAckDomain.modifyClfForModify(contractLifeModel, user); 
			//添加影像上传信息
			if(pla.getPolicyImageModel()!=null&&pla.getPolicyImageModel().getFile_ids()!=null){
				policyImageService.concernPolicyImage(pla.getPolicyImageModel(), user);
			}
			returnMsg.setDataTable(TransHelper.obj2map(pla));//回显信息
			returnMsg.setSuccessMessage("修改成功");
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage(), true);
		}
		return returnMsg;
	}

	@Override
	public IContractLifeModel getContractLife(IContractLifeModel pl) {
		return policyLifeAckDomain.getContractLife(pl);
	}
}
