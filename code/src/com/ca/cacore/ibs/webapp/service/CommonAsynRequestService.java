package com.ca.cacore.ibs.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.domain.ICommonAsynRequestDomain;
import com.ca.cacore.ibs.model.vo.ContractLifeVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeInfoVOModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;



@Service
public class CommonAsynRequestService implements ICommonAsynRequestService{
	@Autowired private ICommonAsynRequestDomain commonAsynRequestDomain;

	@Override
	public IPolicyLifePeopleVOModel getSalesInfo(String sales_id) {
		return commonAsynRequestDomain.getSalesInfo(sales_id);
	}

	@Override
	public IProductLlifeModel getProductInfo(IProductLlifeModel model) {
		return commonAsynRequestDomain.getProductInfo(model);
	}

	@Override
	public List<IPolicyLifePeopleVOModel> getCustomerInfo(IPolicyLifePeopleVOModel plp) {
		return commonAsynRequestDomain.getCustomerInfo(plp);
	}

	@Override
	public boolean  validateSendCode(IPolicyLifeVOModel model) {
		IPolicyLifeVOModel m = commonAsynRequestDomain.validateSendCode(model);
		if(m==null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String modifyPolicyLifeStatus(PolicyLifeInfoVOModel pl) {
		//调用核心的接口，返回状态 result_flag : true or flag
		String result_flag ="true";
		pl.setResult_flag(result_flag);
		if("true".equals(result_flag)){
			pl.setStatus("14");//二次提交时保险公司通过：更新投保单状态为首期待承保
			commonAsynRequestDomain.modifyPolicyLifeStatus(pl);
		}
		return result_flag;
	}

	@Override
	public boolean validatePolicyCode(ContractLifeVOModel model) {
		ContractLifeVOModel m = commonAsynRequestDomain.validatePolicyCode(model);
		if(m==null){
			return true;
		}else{
			return false;
		}
	}
}
