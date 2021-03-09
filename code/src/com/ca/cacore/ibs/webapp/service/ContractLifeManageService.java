package com.ca.cacore.ibs.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.domain.IContractLifeManageDomain;
import com.ca.cacore.ibs.model.vo.IContractLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public class ContractLifeManageService implements IContractLifeManageService{
	@Autowired private IContractLifeManageDomain contractLifeManageDomain ;

	@Override
	public ReturnMsg queryContractMaster(IContractLifeVOModel cm) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IContractLifeVOModel>list = contractLifeManageDomain.queryContractMaster(cm);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public ReturnMsg getContractLifeView(Integer seq_id) {
		ReturnMsg returnMsg = new ReturnMsg();
		IContractLifeVOModel model = contractLifeManageDomain.getContractLifeBySeq(seq_id);
		model.setResult_flag("false");
		List<IContractLifeVOModel> list = new ArrayList<IContractLifeVOModel>();
		list.add(model);
	    returnMsg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		return returnMsg;
	}

	@Override
	public ReturnMsg getHolderView(String customer_id) {
		ReturnMsg returnMsg = new ReturnMsg();
		IContractLifePeopleVOModel model = contractLifeManageDomain.getHolderView(customer_id);
		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}

	@Override
	public ReturnMsg getInsurantView(String customer_id) {
		ReturnMsg returnMsg = new ReturnMsg();
		IContractLifePeopleVOModel model = contractLifeManageDomain.getInsurantView(customer_id);
		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}

	@Override
	public ReturnMsg getBenefView(String customer_id) {
		ReturnMsg returnMsg = new ReturnMsg();
		IContractLifePeopleVOModel model = contractLifeManageDomain.getBenefView(customer_id);
		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}

	@Override
	public ReturnMsg getProductView(int seq_id) {
		ReturnMsg returnMsg = new ReturnMsg();
		IContractLifeProductVOModel model = contractLifeManageDomain.getProductView(seq_id);
		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}

	@Override
	public ReturnMsg modifyContractLifeStatus(IContractLifeVOModel model,IUserModel user) {
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			ValidateHelper.isNullThrowException(model.getInsBranch_id(), "保险公司机构不能为空，请检查");
			ValidateHelper.isNullThrowException(model.getBranch_id(), "销售机构不能为空，请检查");
			ValidateHelper.IsNullOrEmptyThrowException(model.getBef_status(), "变更前的状态不能为空");
			ValidateHelper.IsNullOrEmptyThrowException(model.getAft_status(), "变更后的状态不能为空");
			//修改保单的状态
			contractLifeManageDomain.modifyContractLifeStatus(model, user);
			//添加变更历史
			contractLifeManageDomain.addContractLifeChangeHis(model, user);
			returnMsg.setSuccessMessage("修改成功");
		}catch(BusinessException e){
			model.setStatus(model.getBef_status());//失败回显状态信息
			returnMsg.setFailMessage(e.getMessage(),true);
		}
		List<IContractLifeVOModel> list = new ArrayList<IContractLifeVOModel>();
		list.add(model);
		returnMsg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		return returnMsg;
	}

	@Override
	public ReturnMsg queryCLFModifyStatus(IContractLifeVOModel cm) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IContractLifeVOModel>list = contractLifeManageDomain.queryCLFModifyStatus(cm);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public ReturnMsg queryCLFModify(IContractLifeVOModel cm) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IContractLifeVOModel>list = contractLifeManageDomain.queryCLFModify(cm);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
}
