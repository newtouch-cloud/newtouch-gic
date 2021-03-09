package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.newtouch.core.daobase.BaseDao;

@SuppressWarnings("unchecked")
@Component
public class PolicyLifeManageDao extends BaseDao implements IPolicyLifeManageDao{

	@Override
	public List<IPolicyLifeVOModel> queryAllPolicyLife(IPolicyLifeVOModel pl) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("policyLifeManage.queryPolicyLife_count",pl);
		pl.getPageCount().setAllRows(count);
		pl.getPageCount().calcPage();
		pl.getPageCount().setNowPage(pl.getPageCount().getNowPage()+1);
		return (List<IPolicyLifeVOModel>) this.getSqlMapClientTemplate().queryForList("policyLifeManage.queryPolicyLife",pl);
	}
	
	@Override
	public List<IPolicyLifeVOModel> querySomePolicyLife(IPolicyLifeVOModel pl) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("policyLifeManage.querySomePolicyLife_count",pl);
		pl.getPageCount().setAllRows(count);
		pl.getPageCount().calcPage();
		pl.getPageCount().setNowPage(pl.getPageCount().getNowPage()+1);
		return (List<IPolicyLifeVOModel>) this.getSqlMapClientTemplate().queryForList("policyLifeManage.querySomePolicyLife",pl);
	}
	
	public IPolicyLifeVOModel getPolicyLifeBySeq(IPolicyLifeVOModel model) {
		return (IPolicyLifeVOModel) this.getSqlMapClientTemplate().queryForObject("policyLifeManage.getPolicyLifeBySeq",model);
	}

	@Override
	public  List<IPolicyLifePeopleVOModel> getInsurantList(Long policy_id) {
		return (List<IPolicyLifePeopleVOModel>) this.getSqlMapClientTemplate().queryForList("policyLifeManage.getInsurantList",policy_id);
	}

	@Override
	public  List<IPolicyLifePeopleVOModel> getHolderList(Long policy_id) {
		return (List<IPolicyLifePeopleVOModel>) this.getSqlMapClientTemplate().queryForList("policyLifeManage.getHolderList",policy_id);
	}

	@Override
	public  List<IPolicyLifePeopleVOModel> getBeneficiaryList(Long policy_id) {
		return (List<IPolicyLifePeopleVOModel>) this.getSqlMapClientTemplate().queryForList("policyLifeManage.getBeneficiaryList",policy_id);
	}

	@Override
	public IPolicyLifePeopleVOModel getHolderView(IPolicyLifePeopleVOModel plp) {
		return (IPolicyLifePeopleVOModel) this.getSqlMapClientTemplate().queryForObject("policyLifeManage.getHolderView",plp);
	}

	@Override
	public IPolicyLifePeopleVOModel getInsurantView(IPolicyLifePeopleVOModel plp) {
		return (IPolicyLifePeopleVOModel) this.getSqlMapClientTemplate().queryForObject("policyLifeManage.getInsurantView",plp);
	}

	@Override
	public IPolicyLifePeopleVOModel getBenefView(IPolicyLifePeopleVOModel plp) {
		return (IPolicyLifePeopleVOModel) this.getSqlMapClientTemplate().queryForObject("policyLifeManage.getBenefView",plp);
	}

	@Override
	public IPolicyLifeProductVOModel getProductView(Integer seq_id) {
		return (IPolicyLifeProductVOModel) this.getSqlMapClientTemplate().queryForObject("policyLifeManage.getProductView",seq_id);
	}

	@Override
	public List<IPolicyLifeProductVOModel> getProductList(Long policy_id) {
		return (List<IPolicyLifeProductVOModel>) this.getSqlMapClientTemplate().queryForList("policyLifeManage.getProductList",policy_id);
	}

	@Override
	public boolean modifyCustomer(IPolicyLifePeopleVOModel cus) {
		this.getSqlMapClientTemplate().update("policyLifeManage.updateCustomers",cus);
		return true;
	}

	@Override
	public boolean modifyCustomerContact(IPolicyLifePeopleVOModel cus) {
		this.getSqlMapClientTemplate().update("policyLifeManage.updateCustomerContact",cus);
		return true;
	}

	@Override
	public IPolicyLifePeopleVOModel getCustomerInfo(String customer_id) {
		return (IPolicyLifePeopleVOModel) this.getSqlMapClientTemplate().queryForObject("policyLifeManage.getCustomerInfo",customer_id);
	}

	@Override
	public boolean modifyProduct(IPolicyLifeProductVOModel plp) {
		this.getSqlMapClientTemplate().update("policyLifeManage.updateProductInfo",plp);
		return true;
	}

	@Override
	public boolean modifyPolicyLifeStatus(IPolicyLifeVOModel model) {
		this.getSqlMapClientTemplate().update("policyLifeManage.modifyPlfStatus",model);
		return true;
	}
	
	@Override
	public boolean addPolicyLifeChangeHis(IPolicyLifeVOModel plp) {
		this.getSqlMapClientTemplate().insert("policyLifeManage.addPolicyChangeHis",plp);
		return true;
	}

	@Override
	public List<IPolicyLifeVOModel> queryPolicyLifeModify(IPolicyLifeVOModel pl) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("policyLifeManage.queryPolicyLifeModify_count",pl);
		pl.getPageCount().setAllRows(count);
		pl.getPageCount().calcPage();
		pl.getPageCount().setNowPage(pl.getPageCount().getNowPage()+1);
		return (List<IPolicyLifeVOModel>) this.getSqlMapClientTemplate().queryForList("policyLifeManage.queryPolicyLifeModify",pl);
	}
	
}
