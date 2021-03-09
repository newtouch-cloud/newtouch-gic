package com.newtouch.peoplemanage.domain;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.ca.cacore.manage.model.bo.BranchModel;
import com.newtouch.peoplemanage.model.po.IEducationVOModel;
import com.newtouch.peoplemanage.model.po.IEmpFamilyVOModel;
import com.newtouch.peoplemanage.model.po.IEmpNoteVOModel;
import com.newtouch.peoplemanage.model.po.ILicenseInfoVOModel;
import com.newtouch.peoplemanage.model.vo.IPersonManageVOModel;

/**
 * 人员管理domain层
 * 
 * @author Ming Ying
 * @time 2017-12-01
 */

public interface IPersonManageDomain {
	// 查询人员信息列表
	public List<IPersonManageVOModel> queryPersonVOs(IPersonManageVOModel model);

	// 人员明细查询
	public IPersonManageVOModel queryPersonVOById(String seq_id);

	// 查询某个家庭成员信息
	public List<IEmpFamilyVOModel> queryPersonFamily(String person_id);

	public List<IEducationVOModel> queryPersonEducation(IEducationVOModel model);

	/**
	 * 查询相关事项说明
	 */
	public List<IEmpNoteVOModel> queryEmpNote(String person_no);

	/**
	 * 查询相关事项说明
	 */
	public List<ILicenseInfoVOModel> queryLicenseInfo(String person_no);

	/**
	 * 查询相关事项说明
	 */
	public List<IPersonManageVOModel> queryPositionInfo(String person_no);

	public String getbranch_level(String branch_id);
	
	
	/**
	 * 查询机构层级列表
	 * @return
	 */
	public List<BranchModel> queryBranchLevel();
	
	
	public List<String> checkTraPlanInfoIsTrue(Map<String, List<Object>> dataMap) throws ParseException;
	
	public IPersonManageVOModel queryTeamNameBranchName(IPersonManageVOModel model);
}
