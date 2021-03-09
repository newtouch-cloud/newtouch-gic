package com.ca.cacore.manage.webapp.service;

import java.util.List;

import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.vo.BusinessLicenseHisModel;
import com.ca.cacore.manage.model.vo.IBranchStatusHisVOModel;
import com.ca.cacore.manage.model.vo.IBranchVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.core.returnmsg.ReturnMsg;

public interface IBranchService {
	public ReturnMsg queryAllBranch(IBranchModel model);

	public List<IBranchVOModel> exportBranchInfo(IBranchModel model);

	public ReturnMsg getByBranchView(IBranchModel model);

	public ReturnMsg addBranch(IBranchModel model, IUserModel user) throws BusinessException;

	public ReturnMsg updateBranch(IBranchModel model, IUserModel user) throws BusinessException;

	public ReturnMsg getByBranchModifyVO(IBranchModel model);
	
	public ReturnMsg getByBranchAddVO(IBranchModel model);

	public ReturnMsg getBranchAddVO(IBranchModel model);

	public ReturnMsg updateStatus(IBranchModel model, IUserModel user) throws BusinessException;

	public int checkRepeatReturnInt(IBranchModel model);

	public ReturnMsg queryAllBranchStatusHis(IBranchStatusHisVOModel model);

	public ReturnMsg queryBranchTree(IBranchModel model);

	public ReturnMsg queryBranchTreeTeam(IBranchModel model);

	public ReturnMsg queryBranchTreeExceptCentral(IBranchModel mdoel);

	public Integer queryForVerifyData(IBranchModel model);

	/**
	 * 工作流使用
	 * 
	 * @param model
	 * @return
	 */
	public ReturnMsg queryBranchTree4WF(IBranchModel model);

	/**
	 * 查询省市县 孙豪
	 * 
	 * @return
	 */
	public ReturnMsg queryProvince();

	public ReturnMsg queryCity(String parent_code);

	public ReturnMsg queryArea(String parent_code);

	//by zdd 20190611
	public String insertBranchzdd(IBranchModel branchModel, IUserModel userModel);
	//by zdd 20190612
	public String importbranchIMgORPdf(IBranchModel model);
	//by zdd 20190612
	String getPCAByCode(String code);
	//by zdd 20190613
	List<String> getBranchLeavel();

	List<String> getzzstuts(String str);//zddxiu

	public String insertbranchList(IBranchModel model);

	public ReturnMsg businessLicenseHis(int parseInt);

	public ReturnMsg upbusinessLicenseHis(BusinessLicenseHisModel model);
	
	public ReturnMsg addBranchJunior(IBranchModel branchModel, IUserModel userModel) throws BusinessException;
	
}  
