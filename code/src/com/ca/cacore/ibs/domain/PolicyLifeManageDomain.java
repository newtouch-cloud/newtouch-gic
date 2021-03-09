package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.dao.IPolicyLifeManageDao;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.common.CodeTypeConst;
import com.newtouch.component.c11assistant.BusinessException;

@Service
public class PolicyLifeManageDomain implements IPolicyLifeManageDomain{
	@Autowired private IPolicyLifeManageDao policyLifeManageDao;

	@Override
	public List<IPolicyLifeVOModel> queryAllPolicyLife(IPolicyLifeVOModel pl) {
		return policyLifeManageDao.queryAllPolicyLife(pl);
	}

	@Override
	public IPolicyLifeVOModel getPolicyLifeView(IPolicyLifeVOModel m) {
		IPolicyLifeVOModel model = policyLifeManageDao.getPolicyLifeBySeq(m);//投保单明细
		List<IPolicyLifePeopleVOModel> plbList =policyLifeManageDao.getBeneficiaryList(model.getPolicy_id());
		List<IPolicyLifePeopleVOModel> plhList = policyLifeManageDao.getHolderList(model.getPolicy_id());
		List<IPolicyLifePeopleVOModel> pliList= policyLifeManageDao.getInsurantList(model.getPolicy_id());
	    List<IPolicyLifeProductVOModel >productList = policyLifeManageDao.getProductList(model.getPolicy_id());
		model.setBeneficiaryListView(plbList);
		model.setHolderListView(plhList);
		model.setInsurantListView(pliList);
		model.setProductListView(productList);
		return model;
	}

	@Override
	public IPolicyLifePeopleVOModel getHolderView(IPolicyLifePeopleVOModel plp) {
		return policyLifeManageDao.getHolderView(plp);
	}

	@Override
	public IPolicyLifePeopleVOModel getInsurantView(IPolicyLifePeopleVOModel plp) {
		return policyLifeManageDao.getInsurantView(plp);
	}

	@Override
	public IPolicyLifePeopleVOModel getBenefView(IPolicyLifePeopleVOModel plp) {
		return policyLifeManageDao.getBenefView(plp);
	}

	@Override
	public IPolicyLifeProductVOModel getProductView(Integer seq_id) {
		return policyLifeManageDao.getProductView(seq_id);
	}

	@Override
	public boolean modifyCustomer(IPolicyLifePeopleVOModel cus, IUserModel user) {
		cus.setModifyUser(user.getEmp_id());
		return policyLifeManageDao.modifyCustomer(cus);
	}

	@Override
	public boolean modifyProduct(IPolicyLifeProductVOModel plp, IUserModel user) {
		IPolicyLifeProductVOModel model = policyLifeManageDao.getProductView(plp.getSeq_id());
		model.setInsurant_id(plp.getInsurant_id());//第一被保人
		model.setCharge_period(plp.getCharge_period());//缴费期限类型
		model.setCoverage_period(plp.getCoverage_period());//保障期限类型
		model.setCharge_type(plp.getCharge_type());//当期付款方式
		model.setCoverage_year(plp.getCoverage_year());//保障年限
		model.setCharge_year(plp.getCharge_year());//缴费年限
		model.setAmount(plp.getAmount());//保额
		model.setPeriod_prem(plp.getPeriod_prem());//保费
		model.setModifyUser(user.getEmp_id());
		return policyLifeManageDao.modifyProduct(model);
	}

	@Override
	public boolean modifyPolicyLifeStatus(IPolicyLifeVOModel model,IUserModel user) {
		String bef_status = model.getBef_status();
		String aft_status  = model.getAft_status();
		if( Integer.valueOf(model.getAft_status())< Integer.valueOf(model.getBef_status())&&!CodeTypeConst.POLICYLIFE_STATUS_CONTRACTLIFE_EFFECTIVE.equals(bef_status)){
			// 不可以回退(投保单状态不为保单进入正常有效状态)
			throw new BusinessException("变更状态不对，不允许变更");
		}else if(CodeTypeConst.POLICYLIFE_STATUS_FIRSTCOVERED.equals(bef_status)&&(CodeTypeConst.POLICYLIFE_STATUS_CONTRACTLIFE_EFFECTIVE.equals(aft_status)||CodeTypeConst.POLICYLIFE_STATUS_CONTRACTLIFE_BACK.equals(aft_status))){
			//首期带承保的投保单时不可以变为保单进入正常有效状态和承保前撤件
			throw new BusinessException("变更状态不对，不允许变更");
		}else if(CodeTypeConst.POLICYLIFE_STATUS_CONTRACTLIFE_EFFECTIVE.equals(bef_status)&&(CodeTypeConst.POLICYLIFE_STATUS_FIRSTCOVERED.equals(aft_status)||CodeTypeConst.POLICYLIFE_STATUS_COVERED_BACK.equals(aft_status))){
			//保单进入正常有效状态的投保单不可以变为首期待承保和带承保前撤件
			throw new BusinessException("变更状态不对，不允许变更");
		}
		model.setModifyUser(user.getEmp_id());
		return policyLifeManageDao.modifyPolicyLifeStatus(model);
	}

	@Override
	public List<IPolicyLifeVOModel> querySomePolicyLife(IPolicyLifeVOModel pl) {
		return policyLifeManageDao.querySomePolicyLife(pl);
	}

	@Override
	public boolean addPolicyLifeChangeHis(IPolicyLifeVOModel plp,IUserModel user) {
		plp.setCreateUser(user.getEmp_id());
		plp.setModifyUser(user.getEmp_id());
		return policyLifeManageDao.addPolicyLifeChangeHis(plp);
	}

	@Override
	public List<IPolicyLifeVOModel> queryPolicyLifeModify(IPolicyLifeVOModel pl) {
		return policyLifeManageDao.queryPolicyLifeModify(pl);
	}
}
