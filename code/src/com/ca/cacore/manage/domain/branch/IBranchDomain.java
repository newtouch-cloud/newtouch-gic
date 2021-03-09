package com.ca.cacore.manage.domain.branch;

import java.util.List;

import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.vo.BusinessLicenseHisModel;
import com.ca.cacore.manage.model.vo.IBranchStatusHisVOModel;
import com.ca.cacore.manage.model.vo.IBranchVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ITree;

public interface IBranchDomain {
	public List<IBranchVOModel> queryAllBranch(IBranchModel model);

	public List<IBranchVOModel> exportBranchInfo(IBranchModel model);

	public IBranchModel getByBranch(IBranchModel model);

	public String addBranch(IBranchModel model,IUserModel userModel) throws BusinessException;

	public boolean updateBranch(IBranchModel model, IUserModel userModel) throws BusinessException;

	public IBranchVOModel getBranchView(IBranchModel model);

	public Integer updateStatus(IBranchModel model, IUserModel user);

	public IBranchVOModel getBranchAddVO(IBranchModel model);

	public int checkRepeatReturnInt(IBranchModel model);

	public List<IBranchStatusHisVOModel> queryAllBranchStatusHis(IBranchStatusHisVOModel model);

	public List<ITree> queryBranchTree(IBranchModel model);

	public List<ITree> queryBranchTreeTeam(IBranchModel model);

	public List<ITree> queryBranchTreeExceptCentral(IBranchModel model);

	public Integer queryForVerifyData(IBranchModel model);

	/**
	 * 工作流使用
	 * 
	 * @param model
	 * @return
	 */
	public List<ITree> queryBranchTree4WF(IBranchModel model);

	/**
	 * 校验该机构的下级机构是否全部为无效 ss
	 * 
	 * @param branch_parentid
	 * @param status
	 * @description:根据上级机构代码校验是否所有的下级机构都无效 ss
	 */
	public void checkIsAllBranchInvlid(String branch_parentid, String status);


	public String nextBranchId(String branch_parentId);

	/**
	 * 判断该机构下是否拥有人员,团队下存在人员，不能注销该团队
	 * 
	 * @param branch_id
	 *            void
	 * @param status
	 * @description:
	 */
	public List<ICustomerVOModel> isHasPerson(ICustomerVOModel model);

	/**
	 * 查询省市县 孙豪
	 * 
	 * @return
	 */
	public List<IBranchModel> queryProvince();

	public List<IBranchModel> queryCity(String parent_code);

	public List<IBranchModel> queryArea(String parent_code);

	/**
	 * 根据省市县code查询name
	 * 
	 * @param area_code
	 * @return
	 */
	public IBranchVOModel getCityByCode(String area_code);
    //by zdd 20190611
	String insertBranchZdd(IBranchModel model, IUserModel user) throws BusinessException;
	//by zdd 20190612
	public String importbranchIMgORPdf(IBranchModel model);
	//by zdd 20190613
	List<String> getBranchLeavel();
	//by zdd 20190613
	String getCityByNamez(String arename);

	public List<String> getzzstuts(String str);//zddxiu

	public String insertbranchList(IBranchModel model);

	public List<BusinessLicenseHisModel> businessLicenseHis(int parseInt);

	public String upbusinessLicenseHis(BusinessLicenseHisModel model);

	public String branchIdSelect(String branch_id);
	
	public IBranchVOModel queryBranch(IBranchModel model);
	
	public String addBranchJunior(IBranchModel model, IUserModel userModel) throws BusinessException;
	
}
