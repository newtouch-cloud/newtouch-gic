package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.dao.IContractLifeManageDao;
import com.ca.cacore.ibs.model.vo.IContractLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;
import com.newtouch.component.c11assistant.BusinessException;

@Service
public class ContractLifeManageDomain implements IContractLifeManageDomain{
	@Autowired private IContractLifeManageDao contractLifeManageDao ;
	
	@Override
	public List<IContractLifeVOModel> queryContractMaster(IContractLifeVOModel cm) {
		return contractLifeManageDao.queryContractMaster(cm);
	}

	@Override
	public IContractLifeVOModel getContractLifeBySeq(Integer seq_id) {
		IContractLifeVOModel model = contractLifeManageDao.getContractLifeBySeq(seq_id);
		List<IContractLifePeopleVOModel> holderList  = contractLifeManageDao.getHolderList(model.getPolicy_id());//投保人集合
		 List<IContractLifePeopleVOModel>insurantList = contractLifeManageDao.getInsurantList(model.getPolicy_id()) ;//被保人集合
		List<IContractLifePeopleVOModel >beneficiaryList =  contractLifeManageDao.getBeneficiaryList(model.getPolicy_id());//受益人集合
		List<IContractLifeProductVOModel >productList  = contractLifeManageDao.getProductList(model.getPolicy_id());
		model.setHolderListView(holderList);
		model.setInsurantListView(insurantList);
		model.setBeneficiaryListView(beneficiaryList);
		model.setProductListView(productList);
		return model;
	}

	@Override
	public IContractLifePeopleVOModel getHolderView(String customer_id) {
		return contractLifeManageDao.getHolderView(customer_id);
	}

	@Override
	public IContractLifePeopleVOModel getInsurantView(String customer_id) {
		return contractLifeManageDao.getInsurantView(customer_id);
	}

	@Override
	public IContractLifePeopleVOModel getBenefView(String customer_id) {
		return contractLifeManageDao.getBenefView(customer_id);
	}

	@Override
	public IContractLifeProductVOModel getProductView(int seq_id) {
		return contractLifeManageDao.getProductView(seq_id);
	}

	@Override
	public boolean modifyContractLifeStatus(IContractLifeVOModel model,IUserModel user) {
		if(Integer.valueOf(model.getBef_status())>Integer.valueOf(model.getAft_status())){
			//变更状态不可以回退
			throw new BusinessException("变更状态不对，不允许变更");
		}
		model.setModifyUser(user.getEmp_id());
		return contractLifeManageDao.modifyContractLifeStatus(model);
	}

	@Override
	public boolean addContractLifeChangeHis(IContractLifeVOModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return contractLifeManageDao.addContractLifeChangeHis(model);
	}

	@Override
	public List<IContractLifeVOModel> queryCLFModifyStatus(IContractLifeVOModel cm) {
		return contractLifeManageDao.queryCLFModifyStatus(cm);
	}

	@Override
	public List<IContractLifeVOModel> queryCLFModify(IContractLifeVOModel cm) {
		return contractLifeManageDao.queryCLFModify(cm);
	}
	
}
