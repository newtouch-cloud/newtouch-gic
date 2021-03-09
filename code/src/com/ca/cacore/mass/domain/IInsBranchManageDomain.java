package com.ca.cacore.mass.domain;

import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.mass.model.bo.ICompanyBranchModel;
import com.newtouch.component.c11assistant.ITree;

import java.util.List;
import java.util.Map;

public interface IInsBranchManageDomain {
	/**
	 * @author GCH
	 * @param model
	 * @return List<IInsBranchVOModel>
	 * @description:查询保险公司信息列表
	 */
	public List<ICompanyBranchModel> insBranchQuery(ICompanyBranchModel model);

	/**
	 * @author GCH
	 * @param model
	 * @return List<IInsBranchVOModel>
	 * @description:新增保险公司
	 */
	public boolean insBranchAdd(ICompanyBranchModel model, IUserModel user);

	/**
	 * @author GCH
	 * @param model
	 * @return List<IInsBranchVOModel>
	 * @description:保险公司明细查询
	 */
	public ICompanyBranchModel getInsBranch(ICompanyBranchModel model);

	/**
	 * @author GCH
	 * @param model
	 * @return List<IInsBranchVOModel>
	 * @description:保险公司信息修改
	 */
	public boolean insBranchModify(ICompanyBranchModel model, IUserModel user);

	/**
	 * @author GCH
	 * @param model
	 * @return List<IInsBranchVOModel>
	 * @description:查询保险公司名称是否已经存在
	 */
	public boolean checkInsbName(ICompanyBranchModel model);

	/**
	 * 
	 * @return List<IInsBranchVOModel>
	 * @description:2014-2-25 获取保险公司代码和名称--by张晨用于投诉录入
	 */
	public List<ICompanyBranchModel> getAllInsBranch();

	public List<ITree> queryBranchTree(Map<String,Object> param);

	public int checkpower0626(String emp_id);//zddxiu

	public List<ITree> querySalesFirmBranchTree2();//zddxiu

	public List<ICompanyBranchModel> getbranchSort();//zddxiu

	public String getfirstLeavelsort(String brand_id);//zddxiu

	public List<ITree> queryBranchTreeZ(IBranchModel model);

	public List<String> getIsPOrLZ();

	List<String> queryIdbyUserName(String userName);
}
