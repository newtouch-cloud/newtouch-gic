package com.ca.cacore.ibs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.ibs.model.vo.CmainPolicyINSPersonVOMModel;
import com.ca.cacore.ibs.model.vo.CmainPolicyINSVOMModel;
import com.ca.cacore.ibs.model.vo.CmainPolicyVOMModel;
import com.ca.cacore.ibs.model.vo.CodeVOModel;
import com.ca.cacore.ibs.model.vo.ICmainPolicyVOModel;
import com.ca.cacore.msss.model.vo.IInsRncDfnVOModel;
import com.ca.cacore.msss.model.vo.InsRncDfnVOModel;
/**
 * 导入保单Dao
* @since:    2014年11月22日   
* @author    SUNXM
* @description:
 */
public interface ICmainPolicyDao {

	/**
	 * 查询保单是否存在
	* 
	* @param policy
	* @return List<CmainPolicyVOMModel>
	* @description:
	 */
	public List<CmainPolicyVOMModel> findPolicy(String policyno);

	/**
	 * 更新主保单信息
	* 
	* @param policy void
	* @description:
	 */
	public void updateMainPolicy(ICmainPolicyVOModel policy);

	/**
	 * 根据险种代码和产品来源判断险种是否存在
	* 
	* @param riskcode void
	 * @return 
	* @description:
	 */
	public InsRncDfnVOModel checkRiskCode(IInsRncDfnVOModel riskcode);

	/**
	 * 更新客户信息
	* 
	* @param policy void
	* @description:
	 */
	public void modifyCustomer(ICmainPolicyVOModel policy);

	/**
	 * 添加客户联系人信息
	* 
	* @param policy void
	* @description:
	 */
	public void addCustomerContact(ICmainPolicyVOModel policy);

	/**
	 * 添加客户信息日志
	* 
	* @param policy void
	* @description:
	 */
	public void addCustomerLog(ICmainPolicyVOModel policy);

	/**
	 * 主保单信息添加
	* 
	* @param policy void
	* @description:
	 */
	public void addMainPolicy(ICmainPolicyVOModel policy);

	/**
	 * 导入保单时添加客户信息
	* 
	* @param policy void
	 * @return 
	* @description:
	 */
	public Integer addCustomer(ICmainPolicyVOModel policy);

	/**
	 * 根据保单id获取关联的客户seq_id
	* 
	* @param policyno
	* @return Integer
	* @description:
	 */
	public Integer getCustomeSeqId(String policyno);

	/**

	/**
	 * 更新客户联系人信息
	* 
	* @param policy void
	* @description:
	 */
	public void modifyCustomerContract(ICmainPolicyVOModel policy);

	/**
	 * 根据policyno查询客户信息
	* 
	* @param policyno
	* @return ICmainPolicyVOModel
	* @description:
	 */
	public ICmainPolicyVOModel queryCustomerByPolicyNo(String policyno);

	/**
	 * 删除重复的产品
	* 
	* @param policy void
	* @description:
	 */
	public void deleteRisk(ICmainPolicyVOModel policy);
	
	/*
	 *导入时删除客户信息
	 */
	public void deleteCustomer(CmainPolicyVOMModel policy);

	/**
	 * 导入时删除保单
	* 
	* @param policy void
	* @description:
	 */
	public void deletePolicy(CmainPolicyVOMModel policy);

	void addMainINSPolicy(CmainPolicyINSVOMModel policy);
	
	void addMainINSPersonPolicy(CmainPolicyINSPersonVOMModel policy);

	void addBusiness(CmainPolicyINSVOMModel policy);

	void addInter(CmainPolicyINSVOMModel policy);

	public void deleteMainINSPolicy(CmainPolicyINSVOMModel policy);
	
	public void deleteMainINSPersonPolicy(CmainPolicyINSPersonVOMModel policy);
	
	public ArrayList<CodeVOModel> findCode(CodeVOModel codeVOModel);

	public void addBusinessPerson(CmainPolicyINSPersonVOMModel policy);

	public void addInterPerson(CmainPolicyINSPersonVOMModel policy);

	ArrayList<HashMap<String, String>> find_cpy_branch(HashMap<String, String> map);

	ArrayList<HashMap<String, String>> find_sys_branch(HashMap<String, String> map);

	public IBranchModel queryBranchById(String branch_id);

	List<CmainPolicyINSVOMModel> findPolicyByNo(String policyno);

	List<CmainPolicyINSVOMModel> findPolicyByNoPerson(String policyno);

	public String findByChannel_no(String channel_no);

	public List<CmainPolicyINSPersonVOMModel> queryPerson(CmainPolicyINSPersonVOMModel model);

	public List<CmainPolicyINSVOMModel> queryCmainPolicyINSVOMModel(CmainPolicyINSVOMModel policy);

	public CmainPolicyINSPersonVOMModel findCmainPolicyINSPersonVOMModel(CmainPolicyINSPersonVOMModel policy);

	

}
