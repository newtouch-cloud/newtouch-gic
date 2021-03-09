package com.ca.cacore.msss.domain;

import java.util.List;

import com.ca.cacore.msss.model.bo.IInsRncDfnModel;
import com.ca.cacore.msss.model.vo.IInsRncDfnVOModel;
import com.ca.cacore.mass.model.bo.CompanyBranchModel;
import com.newtouch.component.c11assistant.ITree;

public interface IInsRncDfnDomain {

	/**
	 * @param 传入IInsRncDfnVOModel model
	 * @return 返回一个List<IInsRncDfnVOModel> 对象
	 * @description 查询出所有产品组合信息，或根据条件查询
	 */
	public List<IInsRncDfnModel> queryInsRncDfnList(IInsRncDfnModel model);
	public boolean insRncDfnAdd(IInsRncDfnModel model);
	public boolean checkRiskCode(IInsRncDfnVOModel model);
	public boolean insRncDfnModify(IInsRncDfnModel model);
	public IInsRncDfnModel getInsRncDfn(IInsRncDfnModel model);
	public boolean checkRiskCodeUnique(IInsRncDfnVOModel model);
	public List<IInsRncDfnModel> queryClassCode(IInsRncDfnModel model);
	public List<IInsRncDfnModel> queryRiskCode(IInsRncDfnModel model);
	public List<IInsRncDfnModel> queryTypeCode(IInsRncDfnModel model);
	public List<CompanyBranchModel> queryCompany();
	public Integer updateStatus(IInsRncDfnModel model);
	public List<ITree> queryCompanyTree();
	public String queryPoundage(IInsRncDfnModel model);
	List<IInsRncDfnModel> exportInfo(IInsRncDfnModel model);//by zdd02 20190617
	public String selectIsOrNobranchname(IInsRncDfnModel model);//by zdd02 20190617
	public boolean insRncDfnAddzdd(IInsRncDfnModel model);//by zdd02 20190617
	public IInsRncDfnModel selectCheckInsRncDfnModel(IInsRncDfnModel model);//by zdd02 20190617
	IInsRncDfnModel checkLimitsBranchid(IInsRncDfnModel model);//by zdd02 20190620
	public List<IInsRncDfnModel> getsysemlist(String branch_id);//zddxiu
	public String isPORL(IInsRncDfnModel model);//zdd0724
	public void insRncDfnUpdate(IInsRncDfnModel model);
}