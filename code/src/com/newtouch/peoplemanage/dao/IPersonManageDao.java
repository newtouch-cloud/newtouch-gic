package com.newtouch.peoplemanage.dao;

import java.util.List;

import com.ca.cacore.manage.model.bo.BranchModel;
import com.newtouch.peoplemanage.model.po.GleaderInfoVOModel;
import com.newtouch.peoplemanage.model.po.IEducationVOModel;
import com.newtouch.peoplemanage.model.po.IEmpFamilyVOModel;
import com.newtouch.peoplemanage.model.po.IEmpNoteVOModel;
import com.newtouch.peoplemanage.model.po.IEnumInfoVOModel;
import com.newtouch.peoplemanage.model.po.IGleaderInfoVOModel;
import com.newtouch.peoplemanage.model.po.ILicenseInfoVOModel;
import com.newtouch.peoplemanage.model.vo.IPersonManageVOModel;

/**
 * 人员管理dao层接口
 * 
 * @author Ming Ying
 * @time 2017-12-01
 */

public interface IPersonManageDao {

	/**
	 * 查询人员信息
	 * 
	 * @param model
	 * @return IPersonManageVOModel
	 */
	public List<IPersonManageVOModel> queryPersonVOs(IPersonManageVOModel model);

	/**
	 * 查询某个人员明细
	 * 
	 * @param model
	 * @return IPersonManageVOModel
	 */
	public IPersonManageVOModel queryPersonVOById(String seq_id);

	/**
	 * 查询某个人家庭成员信息
	 * 
	 * @param person_id
	 * @return
	 */
	public List<IEmpFamilyVOModel> queryPersonFamily(String person_id);

	/**
	 * 查询某个人的教育/工作经历
	 * 
	 * @param person_id
	 * @return
	 */
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
	 * 查询机构层级
	 * @return
	 */
	public List<BranchModel> queryBranchLevel();
	
	
	public void updatePersonInfo(IPersonManageVOModel model);
	
	public void importUpdateGleaderInfo(IPersonManageVOModel model);
	
	public Integer findPersonIsExist(String person_no);
	
	public void modifySysEmployee(IPersonManageVOModel model);
	public void modifySysEmployee1(IPersonManageVOModel model);
	
	public void modifySysEmployeeInfo(IPersonManageVOModel model);
	
	public void modifySysAgentInfo(IPersonManageVOModel model);
	
	public void modifySysEmpNote(IPersonManageVOModel model);
	
	public List<IPersonManageVOModel> queryEmpNoteInfo(String person_no);
	
	public List<IEnumInfoVOModel> querySelectList(String up_enum);
	
	public IGleaderInfoVOModel queryGleaderInfo(String person_no);
	
	public void updateApprovalFile(GleaderInfoVOModel gleaderInfo);
	
	public void updateGleaderInfo(GleaderInfoVOModel gleaderInfo);

	public String queryGleaderPermission(IPersonManageVOModel model);

	public int queryUserDataAuth(IPersonManageVOModel personVO);
	
	public int findSysAgentInfo(IPersonManageVOModel model);
	
	public int findSysEmpNote(IPersonManageVOModel model);
	
	public int findGleaderInfo(String person_no);
	
	public void insertSysAgentInfo(IPersonManageVOModel model);
	
	public void insertSysEmpNote(IPersonManageVOModel model);
	
	public void insertGleaderInfo(GleaderInfoVOModel gleaderInfo);
	
	public void importInsertGleaderInfo(IPersonManageVOModel model);
	
	public void updatePersonClass(IPersonManageVOModel model);
	
	public void saveEmployeePosition(IPersonManageVOModel model);
	
	public void saveEmployee(IPersonManageVOModel model);
	
	public void saveEmployeeInfo(IPersonManageVOModel model);
	
	public void saveEduction(IPersonManageVOModel model);
	
	public IPersonManageVOModel queryTeamNameBranchName(IPersonManageVOModel model);
	public String findSysEmployor(String personNo);
}
