package com.ca.cacore.ibs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.manage.model.bo.BranchModel;
import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.ibs.model.vo.CmainPolicyINSPersonVOMModel;
import com.ca.cacore.ibs.model.vo.CmainPolicyINSVOMModel;
import com.ca.cacore.ibs.model.vo.CmainPolicyVOMModel;
import com.ca.cacore.ibs.model.vo.CodeVOModel;
import com.ca.cacore.ibs.model.vo.ICmainPolicyVOModel;
import com.ca.cacore.msss.model.vo.IInsRncDfnVOModel;
import com.ca.cacore.msss.model.vo.InsRncDfnVOModel;
import com.newtouch.core.daobase.BaseDao;

/**
 * 导入保单
* @since:    2014年11月22日   
* @author    SUNXM
* @description:
 */
@Repository
public class CmainPolicyDao  extends BaseDao implements ICmainPolicyDao {

	/**
	 * 查询保单是否存在
	* 
	* @param policyno
	* @return 
	* @description:
	 */
	public List<CmainPolicyVOMModel> findPolicy(String policyno) {
		return this.getSqlMapClientTemplate().queryForList("CmainPolicy.queryPolicyByPolicyNo", policyno);
	}
	
	@Override
	public List<CmainPolicyINSVOMModel> findPolicyByNo(String policyno) {
		return this.getSqlMapClientTemplate().queryForList("CmainPolicy.queryPolicyByPolicyNoProp", policyno);
	}
	@Override
	public List<CmainPolicyINSVOMModel> findPolicyByNoPerson(String policyno) {
		return this.getSqlMapClientTemplate().queryForList("CmainPolicy.queryPolicyByPolicyNoPerson", policyno);
	}


	/**
	 * 更新主保单信息
	* 
	* @param policy 
	* @description:
	 */
	public void updateMainPolicy(ICmainPolicyVOModel policy) {
		this.getSqlMapClientTemplate().update("CmainPolicy.updateMainPolicy", policy);
	}


	/**
	 * 根据险种代码和产品来源判断险种是否存在
	* 
	* @param riskcode 
	 * @return 
	* @description:
	 */
	@Override
	public InsRncDfnVOModel checkRiskCode(IInsRncDfnVOModel riskcode) {
		return (InsRncDfnVOModel) this.getSqlMapClientTemplate().queryForObject("CmainPolicy.checkRiskCode", riskcode);
	}


	/**
	 * 更新客户信息
	* 
	* @param policy 
	* @description:
	 */
	public void modifyCustomer(ICmainPolicyVOModel policy) {
		this.getSqlMapClientTemplate().update("CmainPolicy.updateCustomer", policy);
		
	}



	/**
	 * 添加客户联系人信息
	* 
	* @param policy 
	* @description:
	 */
	public void addCustomerContact(ICmainPolicyVOModel policy) {
		this.getSqlMapClientTemplate().insert("CmainPolicy.addCustomerContact", policy);
	}


	/**
	 * 添加客户基本信息日志
	* 
	* @param policy 
	* @description:
	 */
	public void addCustomerLog(ICmainPolicyVOModel policy) {
		this.getSqlMapClientTemplate().insert("CmainPolicy.addCustomerLog", policy);
	}


	/**
	 * 添加主保单信息
	* 
	* @param policy 
	* @description:
	 */
	@Override
	public void addMainPolicy(ICmainPolicyVOModel policy) {
		this.getSqlMapClientTemplate().insert("CmainPolicy.addPolicy", policy);
		
	}
	/**
	 * 添加主保单信息
	* 
	* @param policy 
	* @description:
	 */
	@Override
	public void addMainINSPolicy(CmainPolicyINSVOMModel policy){
		this.getSqlMapClientTemplate().insert("CmainPolicy.addPolicy2", policy);
	}
	
	@Override
	public IBranchModel queryBranchById(String branch_id) {
		return (BranchModel)this.getSqlMapClientTemplate().queryForObject("CmainPolicy.query_branch_by_id", branch_id);
	}
	
	
	/**
	 * 添加主保单信息
	 * 
	 * @param policy 
	 * @description:
	 */
	@Override
	public void addMainINSPersonPolicy(CmainPolicyINSPersonVOMModel policy) {
		this.getSqlMapClientTemplate().insert("CmainPolicy.addPolicy2Person", policy);
	}
	/**
	 * 添加主保单信息
	* 
	* @param policy 
	* @description:
	 */
	@Override
	public void addBusiness(CmainPolicyINSVOMModel policy) {
		this.getSqlMapClientTemplate().insert("CmainPolicy.addBusiness", policy);
	}
	
	/**
	 * 添加主保单信息
	* 
	* @param policy 
	* @description:
	 */
	@Override
	public void addBusinessPerson(CmainPolicyINSPersonVOMModel policy) {
		this.getSqlMapClientTemplate().insert("CmainPolicy.addBusinessPerson", policy);
	}
	
	
	/**
	 * 添加主保单信息
	* 
	* @param policy 
	* @description:
	 */
	@Override
	public void addInter(CmainPolicyINSVOMModel policy) {
		this.getSqlMapClientTemplate().insert("CmainPolicy.addInter", policy);
	}
	
	
	/**
	 * 添加主保单信息
	* 
	* @param policy 
	* @description:
	 */
	@Override
	public void addInterPerson(CmainPolicyINSPersonVOMModel policy) {
		this.getSqlMapClientTemplate().insert("CmainPolicy.addInterPerson", policy);
	}
	
	/**
	 * 添加客户信息
	* 
	* @param policy 
	* @description:
	 */
	@Override
	public Integer addCustomer(ICmainPolicyVOModel policy) {
		return (Integer) this.getSqlMapClientTemplate().insert("CmainPolicy.addCustomer", policy);
	}


	/**
	 * 根据保单no获得关联客户seq_id
	* 
	* @param policyno
	* @return 
	* @description:
	 */
	@Override
	public Integer getCustomeSeqId(String policyno) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("CmainPolicy.queryCustomerSeq_IdByPolicyno", policyno);
	}



	/**
	 * 更新客户联系人信息
	* 
	* @param policy 
	* @description:
	 */
	public void modifyCustomerContract(ICmainPolicyVOModel policy) {
		this.getSqlMapClientTemplate().update("CmainPolicy.modifyCustomerContract", policy);
	}


	@Override
	public ICmainPolicyVOModel queryCustomerByPolicyNo(String policyno) {
		return (ICmainPolicyVOModel) this.getSqlMapClientTemplate().queryForObject("CmainPolicy.queryCustomerByPolicyNo", policyno);
	}


	/**
	 * 导入时删除复复的产品
	* 
	* @param policy 
	* @description:
	 */
	@Override
	public void deleteRisk(ICmainPolicyVOModel policy) {
		this.getSqlMapClientTemplate().delete("CmainPolicy.deleteRisk", policy);
	}


	/*
	 * 导入时删除客户信息
	 */
	@Override
	public void deleteCustomer(CmainPolicyVOMModel policy) {
		this.getSqlMapClientTemplate().delete("CmainPolicy.deleteCustomer", policy);
	}

	/**
	 * 导入时删除保单信息
	* 
	* @param policy 
	* @description:
	 */
	@Override
	public void deletePolicy(CmainPolicyVOMModel policy) {
		this.getSqlMapClientTemplate().delete("CmainPolicy.deletePolicy", policy);		
	}

	@Override
	public void deleteMainINSPolicy(CmainPolicyINSVOMModel policy) {
		this.getSqlMapClientTemplate().delete("CmainPolicy.deleteMainINSPolicy", policy);
	}
	
	@Override
	public void deleteMainINSPersonPolicy(CmainPolicyINSPersonVOMModel policy) {
		this.getSqlMapClientTemplate().delete("CmainPolicy.deleteMainINSPersonPolicy", policy);
	}


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CodeVOModel> findCode(CodeVOModel codeVOModel) {
		return (ArrayList<CodeVOModel>)this.getSqlMapClientTemplate().queryForList("CmainPolicy.findCodeVOModel", codeVOModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<HashMap<String,String>>find_cpy_branch(HashMap<String,String> map){
		return (ArrayList<HashMap<String,String>>)this.getSqlMapClientTemplate().queryForList("CmainPolicy.find_cpy_branch", map);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<HashMap<String,String>>find_sys_branch(HashMap<String,String> map){
		return (ArrayList<HashMap<String,String>>)this.getSqlMapClientTemplate().queryForList("CmainPolicy.find_sys_branch", map);
	}

	@Override
	public String findByChannel_no(String channel_no) {
		// TODO Auto-generated method stub
		return (String) this.getSqlMapClientTemplate().queryForObject("CmainPolicy.findByChannel_no", channel_no);
	}

	@Override
	public List<CmainPolicyINSPersonVOMModel> queryPerson(
			CmainPolicyINSPersonVOMModel model) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("CmainPolicy.queryPerson", model);
	}

	@Override
	public List<CmainPolicyINSVOMModel> queryCmainPolicyINSVOMModel(CmainPolicyINSVOMModel policy) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("CmainPolicy.queryCmainPolicyINSVOMModel", policy);
	}

	@Override
	public CmainPolicyINSPersonVOMModel findCmainPolicyINSPersonVOMModel(CmainPolicyINSPersonVOMModel policy) {
		// TODO Auto-generated method stub
		return (CmainPolicyINSPersonVOMModel)this.getSqlMapClientTemplate().queryForObject("CmainPolicy.findCmainPolicyINSPersonVOMModel", policy);
	}

	

	
}
