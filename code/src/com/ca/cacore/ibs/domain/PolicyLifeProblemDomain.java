package com.ca.cacore.ibs.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.dao.IPolicyLifeProblemDao;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProblemModel;

@Service
public class PolicyLifeProblemDomain implements IPolicyLifeProblemDomain{
	@Autowired private IPolicyLifeProblemDao policyLifeProblemDao;
	/** 
	* @author     GCH
	* @param     一个IPolicyLifeProblemModel对象
	* @return    一个List<IPolicyLifeProblemModel>集合
	* @description:查询所有问题件信息
	*/
	@Override
	public List<IPolicyLifeProblemModel> queryPolicyLifeProblem(IPolicyLifeProblemModel model) {
		return policyLifeProblemDao.queryPolicyLifeProblem(model);
	}
	
	/** 
	* @author     GCH
	* @param     一个IPolicyLifeProblemModel对象
	* @return    一个IPolicyLifeProblemModel对象
	* @description:查询某个问题件信息
	*/
	@Override
	public IPolicyLifeProblemModel getPolicyLifeProblemInfo(IPolicyLifeProblemModel model) {
		return policyLifeProblemDao.getPolicyLifeProblemInfo(model);
	}
	
	/** 
	* @author     GCH  
	* @description:问题件状态更新
	*/
	@Override
	public boolean modifyStatus(IPolicyLifeProblemModel model,IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return policyLifeProblemDao.modifyStatus(model,user);
	}
	
	/** 
	* @author     GCH   
	* @description:问题件信息修改
	*/
	@Override
	public boolean modifyPolicyLifeProblem(IPolicyLifeProblemModel model,IUserModel user) {
		model.setModifyUser(user.getEmp_id());
		return policyLifeProblemDao.modifyPolicyLifeProblem(model,user);
	}
	
	/** 
	* @author     GCH  
	* @description:问题件录入
	*/
	@Override
	public boolean addPolicyLifeProblem(IPolicyLifeProblemModel model,IUserModel user) {
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		return policyLifeProblemDao.addPolicyLifeProblem(model,user);
	}

	/** 
	* @author     GCH
	* @param     一个IPolicyLifeProblemModel对象
	* @return    一个IPolicyLifeProblemModel对象
	* @description:问题件录入时查询要带出的信息
	*/
	@Override
	public IPolicyLifeProblemModel getInfo(IPolicyLifeProblemModel model) {
		return policyLifeProblemDao.getInfo(model);
	}

	@Override
	public IPolicyLifeProblemModel getInfoForClaimsConser(IPolicyLifeProblemModel model) {
		return policyLifeProblemDao.getInfoForClaimsConser(model);
	}

	@Override
	public IPolicyLifeProblemModel getConserClaiProblemInfo(IPolicyLifeProblemModel model) {
		return policyLifeProblemDao.getConserClaiProblemInfo(model);
	}

	@Override
	public List<IPolicyLifeProblemModel> queryConserClaiProblem(IPolicyLifeProblemModel model) {
		return policyLifeProblemDao.queryConserClaiProblem(model);
	}
}
