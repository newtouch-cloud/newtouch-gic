package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.dao.IContractLifeAnswerDao;
import com.ca.cacore.ibs.model.bo.IContractLifeAnswerModel;
import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.bo.IPolicyLifeModel;
import com.ca.cacore.ibs.model.vo.IContractLifeAnswerVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;

@Service
public class ContractLifeAnswerDomain implements IContractLifeAnswerDomain{
	@Autowired private IContractLifeAnswerDao contractLifeAnswerDao ;
	
	@Override
	public boolean addPolicyAnswer(IContractLifeAnswerModel pam,IUserModel user) {
		pam.setCreateUser(user.getEmp_id());//创建人
		pam.setModifyUser(user.getEmp_id());//修改人
		return contractLifeAnswerDao.addPolicyAnswer(pam);
	}

	@Override
	public boolean modifyConstractMaster(IContractLifeModel cm,IUserModel user) {
	     cm.setModifyUser(user.getEmp_id());//修改人
		return contractLifeAnswerDao.modifyConstractMaster(cm);
	}

	@Override
	public IContractLifeModel getConstractMaster(IContractLifeModel cm) {
		return contractLifeAnswerDao.getConstractMaster(cm);
	}
	
	public List<IContractLifeVOModel>  queryPolicyAnswerInfo(IContractLifeVOModel cm){
		return  contractLifeAnswerDao.queryPolicyAnswerInfo(cm);
	}

	@Override
	public IContractLifeAnswerVOModel getPolicyAnswer(IContractLifeAnswerVOModel cm) {
		return contractLifeAnswerDao.getPolicyAnswer(cm);
	}

	@Override
	public boolean modifyPolicyAnswer(IContractLifeAnswerVOModel pa,IUserModel user) {
		IContractLifeAnswerVOModel model = contractLifeAnswerDao.getPolicyAnswer(pa);
		model.setInsBranch_id(pa.getInsBranch_id());
		model.setPolicy_id(pa.getPolicy_id());
		model.setAnswer_type(pa.getAnswer_type());
		model.setAnswer_notes(pa.getAnswer_notes());
		model.setIs_success(pa.getIs_success());
		model.setModifyUser(user.getEmp_id());
		return contractLifeAnswerDao.modifyPolicyAnswer(model);
	}

	@Override
	public boolean modifyPolicyLife(IPolicyLifeModel pl, IUserModel user) {
		IPolicyLifeModel model = contractLifeAnswerDao.getPolicyLife(pl);
		if(model!=null){
			pl.setIs_answered(pl.getIs_answered());
			model.setModifyUser(user.getEmp_id());
		}
		return contractLifeAnswerDao.modifyPolicyLife(model);
	}
}
