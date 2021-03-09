package com.ca.cacore.mass.dao;

import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.mass.model.bo.ICompanyBranchModel;
import com.newtouch.component.c11assistant.ITree;

import java.util.List;
import java.util.Map;

public interface IInsBranchManageDao {
	/**
	 * @author GCH
	 * @param model
	 * @return List<IInsBranchVOModel>
	 * @description:主查询：查询保险公司信息列表
	 */
	public List<ICompanyBranchModel> insBranchQuery(ICompanyBranchModel model);

	/**
	 * @author GCH
	 * @param model
	 * @param user
	 * @return boolean
	 * @description:新增保险公司
	 */
	public boolean insBranchAdd(ICompanyBranchModel model, IUserModel user);

	/**
	 * @author GCH
	 * @param model
	 * @return IInsBranchVOModel
	 * @description:保险公司明细查询
	 */
	public ICompanyBranchModel getInsBranch(ICompanyBranchModel model);

	/**
	 * @author GCH
	 * @param model
	 * @param user
	 * @return boolean
	 * @description:保险公司信息修改
	 */
	public boolean insBranchModify(ICompanyBranchModel model, IUserModel user);

	/**
	 * @author GCH
	 * @param model
	 * @return IInsBranchVOModel
	 * @description:查询保险公司名称是否存在
	 */
	public ICompanyBranchModel checkInsbName(ICompanyBranchModel model);

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

	public String selectMaxOneLevel();

	public List<ITree> queryBranchTreeZ(IBranchModel model);

	public List<String> getIsPOrLZ();

	public int findIsExist(ICompanyBranchModel model);

	List<String> queryIdbyUserName(String userName);
}
