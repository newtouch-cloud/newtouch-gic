package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.dao.IPolicyLifeAckDao;
import com.ca.cacore.ibs.model.bo.ContractLifeModel;
import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeAckVOModel;
import com.ca.cacore.common.CodeTypeConst;
import com.newtouch.component.c11assistant.BusinessException;

@Service
public class PolicyLifeAckDomain implements IPolicyLifeAckDomain {
	@Autowired private IPolicyLifeAckDao policyLifeAckDao;

	@Override
	public boolean addPolicyLifeAck(IPolicyLifeAckVOModel pla,IUserModel user) {
		if(pla.getAck_branch_dateStr().compareTo(pla.getAck_branch_dateStr())<0){
			throw new BusinessException("公司签收的回执日期必须大于等于客户签收回执日期");
		}else if(pla.getAck_customer_dateStr().compareTo(pla.getHold_dateStr())<0){
			throw new BusinessException("客户签收的回执日期必须大于等于投保日期");
		}
		pla.setCreateUser(user.getEmp_id());
		pla.setModifyUser(user.getEmp_id());
		return policyLifeAckDao.addPolicyLifeAck(pla);
	}

	@Override
	public boolean modifyConstractLifeForAdd(IContractLifeModel cl,IUserModel user) {
		IContractLifeModel m  = new ContractLifeModel();
		m.setPolicy_code(cl.getPolicy_code());
		m.setInsBranch_id(cl.getInsBranch_id());
		IContractLifeModel mDb = policyLifeAckDao.getContractLife(m);//根据保单号和保险公司查询保单信息
		//校验是否已经回执
		if(mDb.getRtn_date()!=null){
			throw new BusinessException("保单已经得到回执");
		}
		mDb.setPolicy_id(cl.getPolicy_id());
		mDb.setRtn_date(cl.getRtn_date());
		mDb.setSign_date(cl.getSign_date());
		mDb.setModifyUser(user.getEmp_id());
		mDb.setStatus(CodeTypeConst.CONTRACTLIFE_STATUS_EFFECTIVE);
		return policyLifeAckDao.modifyConstractLife(mDb);
	}

	@Override
	public List<IPolicyLifeAckVOModel> queryPolicyLifeAckList(IPolicyLifeAckVOModel cm) {
		return policyLifeAckDao.queryPolicyLifeAckList(cm);
	}

	@Override
	public IPolicyLifeAckVOModel getPolicyLifeAckViewr(Integer seq_id) {
		return policyLifeAckDao.getPolicyLifeAckViewr(seq_id);
	}

	@Override
	public boolean modifyPolicyLifeAck(IPolicyLifeAckVOModel pa,IUserModel user) {
		if(pa.getAck_branch_dateStr().compareTo(pa.getAck_branch_dateStr())<0){
			throw new BusinessException("公司签收的回执日期必须大于等于客户签收回执日期");
		}else if(pa.getAck_customer_dateStr().compareTo(pa.getHold_dateStr())<0){
			throw new BusinessException("客户签收的回执日期必须大于等于投保日期");
		}
		IPolicyLifeAckVOModel model = policyLifeAckDao.getPolicyLifeAckViewr(pa.getSeq_id());
		model.setAck_branch_date(pa.getAck_branch_date());
		model.setAck_customer_date(pa.getAck_customer_date());
		model.setAck_notes(pa.getAck_notes());
		pa.setModifyUser(user.getEmp_id());
		return policyLifeAckDao.modifyPolicyLifeAck(pa);
	}
	
	@Override
	public boolean modifyClfForModify(IContractLifeModel cl,IUserModel user) {
		IContractLifeModel m  = new ContractLifeModel();
		m.setPolicy_code(cl.getPolicy_code());
		m.setInsBranch_id(cl.getInsBranch_id());
		IContractLifeModel mDb = policyLifeAckDao.getContractLife(m);//根据保单号和保险公司查询保单信息
		mDb.setPolicy_id(cl.getPolicy_id());
		mDb.setRtn_date(cl.getRtn_date());
		mDb.setSign_date(cl.getSign_date());
		mDb.setModifyUser(user.getEmp_id());
		return policyLifeAckDao.modifyConstractLife(mDb);
	}

	@Override
	public IContractLifeModel getContractLife(IContractLifeModel pl) {
		return policyLifeAckDao.getContractLife(pl);
	}
}
