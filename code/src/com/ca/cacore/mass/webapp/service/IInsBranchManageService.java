package com.ca.cacore.mass.webapp.service;

import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.mass.model.bo.ICompanyBranchModel;
import com.newtouch.core.returnmsg.ReturnMsg;

import java.util.List;
import java.util.Map;

public interface IInsBranchManageService {
	// 保险公司信息列表查询
	public ReturnMsg insBranchQuery(ICompanyBranchModel model);

	// 新增保险公司
	public ReturnMsg insBranchAdd(ICompanyBranchModel model, IUserModel user);

	// 查询保险公司信息
	public ReturnMsg getInsBranch(ICompanyBranchModel model);

	// 修改保险公司信息
	public ReturnMsg insBranchModify(ICompanyBranchModel model, IUserModel user);

	// 检查保险公司名称是否已经存在
	public String checkInsbName(ICompanyBranchModel model);

	/**
	 * 
	 * @return List<IInsBranchVOModel>
	 * @description:2014-2-25 获取保险公司代码和名称--by张晨用于投诉录入
	 */
	public List<ICompanyBranchModel> getAllInsBranch();

	// 查询机构树
	public ReturnMsg queryBranchTree(Map<String,Object> param);

	public int checkpower0626(String emp_id);//zddxiu

	public ReturnMsg querySalesFirmBranchTree2();//zddxiu

	public List<ICompanyBranchModel> getbranchSort();//zddxiu

	public String getfirstLeavelsort(String brand_id);//zddxiu

	public ReturnMsg queryBranchTreeZ(IBranchModel model);

	public List<String> getIsPOrLZ();

	public ReturnMsg getInsBranch1(ICompanyBranchModel companyModel);

	List<String> queryIdbyUserName(String userName);
}
