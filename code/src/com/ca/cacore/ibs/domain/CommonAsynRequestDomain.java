package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.ibs.dao.ICommonAsynRequestDao;
import com.ca.cacore.ibs.dao.IPolicyLifeDao;
import com.ca.cacore.ibs.model.vo.ContractLifeVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeInfoVOModel;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.utils.stringutil.StringUtil;

@Service
public class CommonAsynRequestDomain implements ICommonAsynRequestDomain{
	@Autowired private ICommonAsynRequestDao commonAsynRequestDao;
	@Autowired private IPolicyLifeDao policyLifeDao;
	
	@Override
	public IPolicyLifePeopleVOModel getSalesInfo(String sales_id) {
		return commonAsynRequestDao.getSalesInfo(sales_id);
	}
	public IProductLlifeModel getProductInfo(IProductLlifeModel model) {
		return commonAsynRequestDao.getProductInfo(model);
	}

	@Override
	public List<IPolicyLifePeopleVOModel> getCustomerInfo(IPolicyLifePeopleVOModel plp) {
		return commonAsynRequestDao.getCustomerInfo(plp);
	}

	@Override
	public IPolicyLifeVOModel validateSendCode(IPolicyLifeVOModel model) {
		return commonAsynRequestDao.validateSendCode(model);
	}

	@Override
	public  boolean modifyPolicyLifeStatus(PolicyLifeInfoVOModel pl) {
		PolicyLifeInfoVOModel model = policyLifeDao.getPolicyLifeView(pl.getPolicy_id());
		model.setStatus(pl.getStatus());
		return policyLifeDao.modifyPolicyLife(model);
	}

	@Override
	public String getBranchID() {
		String branch_id = commonAsynRequestDao.getBranchID();
		try {
			//调用现成的方法对取出的识别码进行10位补0
			branch_id=StringUtil.alignLeft(branch_id, 3);
		} catch (Message e) {
			e.printStackTrace();
		}
		return  branch_id;
	}

	@Override
	public CustomerVOModel getCustomerInfoByCusBranID(CustomerVOModel model) {
		return commonAsynRequestDao.getCustomerInfoByCusBranID(model);
	}

	@Override
	public ContractLifeVOModel validatePolicyCode(ContractLifeVOModel model) {
		return commonAsynRequestDao.validatePolicyCode(model);
	}
	
}
