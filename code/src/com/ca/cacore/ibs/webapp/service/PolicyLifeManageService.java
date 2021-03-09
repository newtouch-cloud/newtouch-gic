package com.ca.cacore.ibs.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.domain.IPolicyLifeManageDomain;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c6javaValidate.ValidateHelper;
import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public class PolicyLifeManageService implements IPolicyLifeManageService {
	@Autowired private IPolicyLifeManageDomain policyLifeManageDomain;

	@Override
	public ReturnMsg queryAllPolicyLife(IPolicyLifeVOModel pl) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPolicyLifeVOModel> list = policyLifeManageDomain.queryAllPolicyLife(pl);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public ReturnMsg getPolicyLifeView(IPolicyLifeVOModel m) {
		ReturnMsg returnMsg = new ReturnMsg();
		IPolicyLifeVOModel model = policyLifeManageDomain.getPolicyLifeView(m);
		//model.setResult_flag("false");//修改的时候返回false跳转到添加页面，页面根据标志显示和隐藏相关的按钮
		List<IPolicyLifeVOModel> list = new ArrayList<IPolicyLifeVOModel>();
		list.add(model);
		returnMsg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		return returnMsg;
	}

	@Override
	public ReturnMsg getHolderView(IPolicyLifePeopleVOModel plp) {
		ReturnMsg returnMsg = new ReturnMsg();
		IPolicyLifePeopleVOModel model = policyLifeManageDomain.getHolderView(plp);
		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}

	@Override
	public ReturnMsg getInsurantView(IPolicyLifePeopleVOModel plp) {
		ReturnMsg returnMsg = new ReturnMsg();
		IPolicyLifePeopleVOModel model = policyLifeManageDomain.getInsurantView(plp);
		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}

	@Override
	public ReturnMsg getBenefView(IPolicyLifePeopleVOModel plp) {
		ReturnMsg returnMsg = new ReturnMsg();
		IPolicyLifePeopleVOModel model = policyLifeManageDomain.getBenefView(plp);
		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}

	@Override
	public ReturnMsg getProductView(Integer seq_id) {
		ReturnMsg returnMsg = new ReturnMsg();
		IPolicyLifeProductVOModel model = policyLifeManageDomain.getProductView(seq_id);
		returnMsg.setDataTable(TransHelper.obj2map(model));
		return returnMsg;
	}

	@Override
	public ReturnMsg modifyPolicyLifeStatus(IPolicyLifeVOModel model,IUserModel user) {
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			ValidateHelper.isNullThrowException(model.getInsBranch_id(), "保险公司机构不能为空，请检查");
			ValidateHelper.isNullThrowException(model.getBranch_id(), "销售机构不能为空，请检查");
			ValidateHelper.IsNullOrEmptyThrowException(model.getBef_status(), "变更前的状态不能为空");
			ValidateHelper.IsNullOrEmptyThrowException(model.getAft_status(), "变更后的状态不能为空");
		    //修改投保单的状态
			policyLifeManageDomain.modifyPolicyLifeStatus(model, user);
			//添加投保单状态变更历史
			policyLifeManageDomain.addPolicyLifeChangeHis(model, user);
			returnMsg.setSuccessMessage("修改成功");
		}catch(BusinessException e){
			model.setStatus(model.getBef_status());//失败回显状态信息
			returnMsg.setFailMessage(e.getMessage(),true);
		}
		List<IPolicyLifeVOModel> list = new ArrayList<IPolicyLifeVOModel>();
		list.add(model);
		returnMsg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		return returnMsg;
	}

	@Override
	public ReturnMsg querySomePolicyLife(IPolicyLifeVOModel pl) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPolicyLifeVOModel> list = policyLifeManageDomain.querySomePolicyLife(pl);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}

	@Override
	public ReturnMsg queryPolicyLifeModify(IPolicyLifeVOModel pl) {
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPolicyLifeVOModel> list = policyLifeManageDomain.queryPolicyLifeModify(pl);
		returnMsg.setDataList(TransHelper.list2MapList(list));
		return returnMsg;
	}
	
}
