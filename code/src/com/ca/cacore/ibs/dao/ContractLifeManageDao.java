package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.ibs.model.vo.IContractLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;
import com.newtouch.core.daobase.BaseDao;

@SuppressWarnings("unchecked")
@Component
public class ContractLifeManageDao extends BaseDao  implements IContractLifeManageDao{
	
	@Override
	public List<IContractLifeVOModel> queryContractMaster(IContractLifeVOModel cm) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("contractLifeManage.queryContractMaster_count",cm);
		cm.getPageCount().setAllRows(count);
		cm.getPageCount().calcPage();
		cm.getPageCount().setNowPage(cm.getPageCount().getNowPage()+1);
		return (List<IContractLifeVOModel>) this.getSqlMapClientTemplate().queryForList("contractLifeManage.queryContractMaster",cm);
	}

	@Override
	public IContractLifeVOModel getContractLifeBySeq(Integer seq_id) {
		return (IContractLifeVOModel) this.getSqlMapClientTemplate().queryForObject("contractLifeManage.getContractLifeBySeq",seq_id);
	}

	@Override
	public List<IContractLifePeopleVOModel> getInsurantList(Long policy_id) {
		return this.getSqlMapClientTemplate().queryForList("contractLifeManage.getInsurantList",policy_id);
	}

	@Override
	public List<IContractLifePeopleVOModel> getHolderList(Long policy_id) {
		return this.getSqlMapClientTemplate().queryForList("contractLifeManage.getHolderList",policy_id);
	}

	@Override
	public List<IContractLifePeopleVOModel> getBeneficiaryList(Long policy_id) {
		return this.getSqlMapClientTemplate().queryForList("contractLifeManage.getBeneficiaryList",policy_id);
	}

	@Override
	public IContractLifePeopleVOModel getHolderView(String customer_id) {
		return (IContractLifePeopleVOModel) this.getSqlMapClientTemplate().queryForObject("contractLifeManage.getHolderView", customer_id);
	}

	@Override
	public IContractLifePeopleVOModel getInsurantView(String customer_id) {
		return (IContractLifePeopleVOModel) this.getSqlMapClientTemplate().queryForObject("contractLifeManage.getInsurantView", customer_id);
	}

	@Override
	public IContractLifePeopleVOModel getBenefView(String customer_id) {
		return (IContractLifePeopleVOModel) this.getSqlMapClientTemplate().queryForObject("contractLifeManage.getBenefView", customer_id);
	}

	@Override
	public IContractLifeProductVOModel getProductView(int seq_id) {
		return (IContractLifeProductVOModel) this.getSqlMapClientTemplate().queryForObject("contractLifeManage.getProductView", seq_id);
	}

	@Override
	public List<IContractLifeProductVOModel> getProductList(Long policy_id) {
		return this.getSqlMapClientTemplate().queryForList("contractLifeManage.getProductList",policy_id);
	}

	@Override
	public boolean modifyContractLifeStatus(IContractLifeVOModel model) {
		this.getSqlMapClientTemplate().update("contractLifeManage.updateContractLifeStatus",model);
		return true;
	}

	@Override
	public boolean addContractLifeChangeHis(IContractLifeVOModel model) {
		this.getSqlMapClientTemplate().insert("contractLifeManage.addContractChangeHis",model);
		return true;
	}

	@Override
	public List<IContractLifeVOModel> queryCLFModifyStatus(IContractLifeVOModel cm) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("contractLifeManage.queryCLF_modify_status_count",cm);
		cm.getPageCount().setAllRows(count);
		cm.getPageCount().calcPage();
		cm.getPageCount().setNowPage(cm.getPageCount().getNowPage()+1);
		return (List<IContractLifeVOModel>) this.getSqlMapClientTemplate().queryForList("contractLifeManage.queryCLFModifyStatus",cm);
	}

	@Override
	public List<IContractLifeVOModel> queryCLFModify(IContractLifeVOModel cm) {
		Integer count  = (Integer) this.getSqlMapClientTemplate().queryForObject("contractLifeManage.query_contractLife_modify_count",cm);
		cm.getPageCount().setAllRows(count);
		cm.getPageCount().calcPage();
		cm.getPageCount().setNowPage(cm.getPageCount().getNowPage()+1);
		return (List<IContractLifeVOModel>) this.getSqlMapClientTemplate().queryForList("contractLifeManage.queryContractLifeModify",cm);
	}
	
}
