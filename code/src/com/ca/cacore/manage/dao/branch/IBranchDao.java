package com.ca.cacore.manage.dao.branch;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IBranchStatusHisModel;
import com.ca.cacore.manage.model.vo.BusinessLicenseHisModel;
import com.ca.cacore.manage.model.vo.IBranchStatusHisVOModel;
import com.ca.cacore.manage.model.vo.IBranchVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.newtouch.component.c11assistant.ITree;

@Repository
public interface IBranchDao {
	/**
	 * 分页查询机构信息
	 * 
	 * @param model
	 * @return
	 */
	public List<IBranchVOModel> queryAllBranch(IBranchModel model);

	public List<IBranchVOModel> exportBranchInfo(IBranchModel model);

	public IBranchModel getByBranch(IBranchModel model);

	public boolean addBranch(IBranchModel model);

	public boolean updateBranch(IBranchModel model);

	public List<IBranchModel> queryByVerifyAll(IBranchModel model);

	public List<IBranchModel> queryByVerifyOtherData(IBranchModel model);

	public IBranchVOModel getBranchView(IBranchModel model);

	public List<IBranchStatusHisVOModel> queryAllBranchStatusHis(IBranchStatusHisVOModel model);

	public boolean addBranchStatusHis(IBranchStatusHisModel model);

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
	 * 过得下级机构信息
	 * 
	 * @param branch_parentid
	 * @return List<IBranchVOModel>
	 * @description:根据上级机构代码，得到所有的下级机构信息
	 */
	public List<IBranchVOModel> getSubBranchInfo(String branch_parentid);


	/**
	 * 判断该机构下是否拥有人员,团队下存在人员，不能注销该团队
	 * 
	 * @param branch_id
	 *            void
	 * @param status
	 * @description:
	 */
	public List<ICustomerVOModel> isHasPerson(ICustomerVOModel model);

	public boolean updateNcDept(IBranchModel model);

	public IBranchModel queryNcBranch(IBranchModel ncmodel);

	public boolean addNcBranch(IBranchModel ncmodel);

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

	public String queryBranchLevelName(String branch_level);
	/**
	 * 查询上级  by zdd 20190611
	 * @param model
	 * @return
	 */
	public String getParentInfo(IBranchModel model) ;
	/**
	 * 查询机构是否存在  by zdd 20190612
	 * @param model
	 * @return
	 */
	public int getIsORNoBranchId(String branch_id);
	/**
	 * 营业执照上传  by zdd 20190612
	 * @param model
	 * @return
	 */
	public void insertBusinessLicense(IBranchModel model);
    /**
     * zdd 20190613
     * @return
     */
	List<String> getBranchLeavel();
	/**
     * zdd 20190613
     * @return
     */
	String getCityByNamez(String arename);

	public List<String> getzzstuts(String str);//zddxiu

	public boolean insertbranchList(List<IBranchModel> lists);

	public int querHeightYON(IBranchModel model);

	public boolean updatebeanchyezhizhao(IBranchModel model); //zdd05

	public int isORNoPower(IBranchModel model);

	public List<BusinessLicenseHisModel> businessLicenseHis(int parseInt);

	void upbusinessLicenseHis(BusinessLicenseHisModel model);
	
	public String branchIdSelect(String branch_id);
	
	public IBranchVOModel queryBranch(IBranchModel model);
	
	public boolean addBranchJunior(IBranchModel model);
	
}