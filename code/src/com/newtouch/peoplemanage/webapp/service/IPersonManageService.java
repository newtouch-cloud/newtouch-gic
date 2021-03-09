package com.newtouch.peoplemanage.webapp.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ca.cacore.manage.model.bo.BranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.peoplemanage.model.po.GleaderInfoVOModel;
import com.newtouch.peoplemanage.model.po.IEducationVOModel;
import com.newtouch.peoplemanage.model.po.IEnumInfoVOModel;
import com.newtouch.peoplemanage.model.vo.IPersonManageVOModel;

/**
 * 人员管理service
 * 
 * @author Ming Ying
 *
 */
public interface IPersonManageService {
	// 按条件查询并分页显示
	public ReturnMsg queryPersonVOs(IPersonManageVOModel model);

	public ReturnMsg PersonInfoView(String seq_id);

	public ReturnMsg queryPersonFamily(String person_id);

	public ReturnMsg queryPersonEducation(IEducationVOModel model);

	public ReturnMsg queryEmpNote(String person_no);

	/**
	 * 查询相关事项说明
	 */
	public ReturnMsg queryLicenseInfo(String person_no);

	/**
	 * 查询相关事项说明
	 */
	public ReturnMsg queryPositionInfo(String person_no);

	/**
	 * 导出人员信息
	 * 
	 * @param person_no
	 * @return
	 */
	public ByteArrayOutputStream sentpersioninfo(String person_no);

	/**
	 * 导出执业证信息
	 * 
	 * @param person_no
	 * @return
	 */
	public ByteArrayOutputStream sentzhiyezheng(String person_no);

	public String getbranch_level(String branch_id);
	
	/**
	 * a by liu_yn
	 * @return
	 */
	public List<BranchModel> queryBranchLevel();
	
	public List<IPersonManageVOModel> queryPersonInfoList(IPersonManageVOModel model);
	
	public String importPersonInfos(MultipartFile file,IUserModel user)throws IOException, ParseException;
	
	public void modifySysEmployee(IPersonManageVOModel model);
	
	public void modifySysEmployeeInfo(IPersonManageVOModel model);
	
	public void modifySysAgentInfo(IPersonManageVOModel model);
	
	public void modifySysEmpNote(IPersonManageVOModel model);
	
	public List<IEnumInfoVOModel> querySelectList(String up_enum);
	
	public ReturnMsg queryGleaderInfo(String person_no);
	
	public ReturnMsg updateApprovalFile(GleaderInfoVOModel gleaderInfo);
	
	public ReturnMsg updateGleaderInfo(GleaderInfoVOModel gleaderInfo);

	public int queryUserDataAuth(IPersonManageVOModel personVO);
	
	public Boolean findSysAgentInfo(IPersonManageVOModel model);
	
	public Boolean findSysEmpNote(IPersonManageVOModel model);
	
	public Boolean findGleaderInfo(String person_no);
	
	public void insertSysAgentInfo(IPersonManageVOModel model);
	
	public void insertSysEmpNote(IPersonManageVOModel model);
	
	public ReturnMsg insertGleaderInfo(GleaderInfoVOModel gleaderInfo); 
	
	public void updatePersonClass(IPersonManageVOModel model);
	
	public void saveEmployeePosition(IPersonManageVOModel model);
	
	public void saveEmployee(IPersonManageVOModel model);
	
	public void saveEmployeeInfo(IPersonManageVOModel model);
	
	public void saveEduction(IPersonManageVOModel model);
	
	public String queryTeamNameBranchName(IPersonManageVOModel model);
	void modifySysEmployee1(IPersonManageVOModel personVO);
	public String findSysEmployor(String personNo);
}
