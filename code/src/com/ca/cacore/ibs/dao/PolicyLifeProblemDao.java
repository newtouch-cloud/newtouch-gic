package com.ca.cacore.ibs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProblemModel;
import com.newtouch.core.daobase.BaseDao;

@Component
public class PolicyLifeProblemDao extends BaseDao implements IPolicyLifeProblemDao{

	/** 
	* @author     GCH
	* @param     一个IPolicyLifeProblemModel对象
	* @return    一个List<IPolicyLifeProblemModel>集合
	* @description:查询所有问题件信息
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<IPolicyLifeProblemModel> queryPolicyLifeProblem(IPolicyLifeProblemModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("policyLifeProblem.queryPolicyLifeProblem_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IPolicyLifeProblemModel>)this.getSqlMapClientTemplate().queryForList("policyLifeProblem.queryPolicyLifeProblem", model);
	}

	/** 
	* @author     GCH
	* @param     一个IPolicyLifeProblemModel对象
	* @return    一个IPolicyLifeProblemModel对象
	* @description:查询某个问题件信息
	*/
	@Override
	public IPolicyLifeProblemModel getPolicyLifeProblemInfo(IPolicyLifeProblemModel model) {
		return (IPolicyLifeProblemModel) this.getSqlMapClientTemplate().queryForObject("policyLifeProblem.getPolicyLifeProblemInfo", model);
	}

	/** 
	* @author     GCH
	* @param     一个IPolicyLifeProblemModel对象
	* @return    一个boolean类型的对象
	* @description:修改问题件状态
	*/
	@Override
	public boolean modifyStatus(IPolicyLifeProblemModel model,IUserModel user) {
		this.getSqlMapClientTemplate().update("policyLifeProblem.modifyStatus", model);
		return true;
	}

	/** 
	* @author     GCH
	* @param     一个IPolicyLifeProblemModel对象
	* @return    一个boolean类型的对象
	* @description:问题件信息修改
	*/
	@Override
	public boolean modifyPolicyLifeProblem(IPolicyLifeProblemModel model,IUserModel user) {
		this.getSqlMapClientTemplate().update("policyLifeProblem.modifyPolicyLifeProblem", model);
		return true;
	}

	/** 
	* @author     GCH
	* @param     一个IPolicyLifeProblemModel对象
	* @return    一个boolean类型的对象
	* @description:问题件录入
	*/
	@Override
	public boolean addPolicyLifeProblem(IPolicyLifeProblemModel model,IUserModel user) {
		this.getSqlMapClientTemplate().insert("policyLifeProblem.addPolicyLifeProblem", model);
		return true;
	}

	/** 
	* @author     GCH   
	* @description:问题件录入时，查询带出信息
	*/
	@Override
	public IPolicyLifeProblemModel getInfo(IPolicyLifeProblemModel model) {
		return (IPolicyLifeProblemModel) this.getSqlMapClientTemplate().queryForObject("policyLifeProblem.getInfo", model);
	}

	@Override
	public IPolicyLifeProblemModel getInfoForClaimsConser(IPolicyLifeProblemModel model) {
		return (IPolicyLifeProblemModel) this.getSqlMapClientTemplate().queryForObject("policyLifeProblem.getInfoForClaimsConser", model);
	}

	@Override
	public IPolicyLifeProblemModel getConserClaiProblemInfo(IPolicyLifeProblemModel model) {
		return (IPolicyLifeProblemModel) this.getSqlMapClientTemplate().queryForObject("policyLifeProblem.getConserClaiProblemInfo", model);
	}

	@Override
	public List<IPolicyLifeProblemModel> queryConserClaiProblem(IPolicyLifeProblemModel model) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("policyLifeProblem.queryConserClaiProblem_count", model);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage()+1);
		return (List<IPolicyLifeProblemModel>)this.getSqlMapClientTemplate().queryForList("policyLifeProblem.queryConserClaiProblem", model);

	}
}
