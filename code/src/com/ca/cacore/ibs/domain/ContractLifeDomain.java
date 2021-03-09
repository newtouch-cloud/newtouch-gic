package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.dao.IContractLifeDao;
import com.ca.cacore.ibs.dao.IContractLifeManageDao;
import com.ca.cacore.ibs.dao.IPolicyLifeManageDao;
import com.ca.cacore.ibs.model.bo.IContractLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.IContractLifeHolderModel;
import com.ca.cacore.ibs.model.bo.IContractLifeInsurantModel;
import com.ca.cacore.ibs.model.vo.ContractLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.ContractLifeSaveVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;

@Service
public class ContractLifeDomain implements IContractLifeDomain{
	@Autowired private IContractLifeDao  contractLifeDao ;
	@Autowired private IContractLifeManageDao contractLifeManageDao ;
	@Autowired private IPolicyLifeManageDao policyLifeManageDao;

	@Override
	public boolean addContractLifeHolder(IContractLifeHolderModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLifeHolder(model);
	}

	@Override
	public boolean addContractLifeInsured(IContractLifeInsurantModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLifeInsured(model);
	}

	@Override
	public boolean addContractLifeBeneficiary(IContractLifeBeneficiaryModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLifeBeneficiary(model);
	}

	@Override
	public boolean addContractLife(ContractLifeSaveVOModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLife(model);
	}

	@Override
	public boolean addContractLifeProduct(ContractLifeProductVOModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLifeProduct(model);
	}

	@Override
	public boolean addContractLifeProductPre(ContractLifeProductVOModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLifeProductPre(model);
	}

	@Override
	public boolean addContractLifeProductFee(ContractLifeProductVOModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLifeProductFee(model);
	}

	@Override
	public IContractLifeVOModel getContractLifeView(IContractLifeVOModel cl) {
		IContractLifeVOModel m = contractLifeDao.getContractLifeView(cl.getPolicy_code());
		 List<IContractLifePeopleVOModel> beneList = contractLifeManageDao.getBeneficiaryList(cl.getPolicy_id());
		 List<IContractLifePeopleVOModel> holdList = contractLifeManageDao.getHolderList(cl.getPolicy_id());
		 List<IContractLifePeopleVOModel> insurList = contractLifeManageDao.getInsurantList(cl.getPolicy_id());
		 List<IContractLifeProductVOModel> proList = contractLifeManageDao.getProductList(cl.getPolicy_id());
		 m.setBeneficiaryListView(beneList);
		 m.setHolderListView(holdList);
		 m.setInsurantListView(insurList);
		 m.setProductListView(proList);
		return m;
	}

	@Override
	public boolean modifyContractLife(ContractLifeSaveVOModel pl, IUserModel user) {
		pl.setModifyUser(user.getEmp_id());
		return contractLifeDao.modifyContractLife(pl);
	}

	@Override
	public boolean modifycontractLifeHolder(IContractLifeHolderModel model,IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.modifycontractLifeHolder(model);
	}

	@Override
	public boolean modifyContractLifeInsured(IContractLifeInsurantModel model,IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.modifyContractLifeInsured(model);
	}

	@Override
	public boolean modifyContractLifeBeneficiary(IContractLifeBeneficiaryModel model, IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.modifyContractLifeBeneficiary(model);
	}

	@Override
	public boolean modifyContractLifeProduct(ContractLifeProductVOModel model,IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.modifyContractLifeProduct(model);
	}

	@Override
	public boolean modifyContractLifePrem(ContractLifeProductVOModel model,IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.modifyContractLifePrem(model);
	}

	@Override
	public boolean modifyContractLifeProductFee(ContractLifeProductVOModel model, IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.modifyContractLifeProductFee(model);
	}

	@Override
	public boolean addContractLifeProductInsFee(ContractLifeProductVOModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLifeProductInsFee(model);
	}

	@Override
	public boolean modifyContractLifeProductInsFee(ContractLifeProductVOModel model, IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.modifyContractLifeProductInsFee(model);
	}

	@Override
	public Integer getSeq_id() {
		return contractLifeDao.getSeq_id();
	}

	@Override
	public boolean addContractLifeLog(ContractLifeSaveVOModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLifeLog(model);
	}

	@Override
	public boolean addContractLifeProductLog(ContractLifeProductVOModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLifeProductLog(model);
	}

	@Override
	public boolean addContractLifeProductInsFeeLog(ContractLifeProductVOModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLifeProductInsFeeLog(model);
	}

	@Override
	public boolean addContractLifeProductPreLog(ContractLifeProductVOModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLifeProductPreLog(model);
	}

	@Override
	public boolean addContractLifeProductFeeLog(ContractLifeProductVOModel model, IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeDao.addContractLifeProductInsFeeLog(model);
	}

	@Override
	public List<ContractLifeProductVOModel> sumContractlifePrem(Long policy_id) {
		return contractLifeDao.sumContractlifePrem(policy_id);
	}

	@Override
	public boolean modifyPolicyLifeStatus(IPolicyLifeVOModel model,IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return policyLifeManageDao.modifyPolicyLifeStatus(model);
	}
	
}
