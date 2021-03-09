package com.newtouch.peoplemanage.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ca.cacore.manage.model.bo.BranchModel;
import com.newtouch.core.daobase.BaseDao;
import com.newtouch.peoplemanage.model.po.GleaderInfoVOModel;
import com.newtouch.peoplemanage.model.po.IEducationVOModel;
import com.newtouch.peoplemanage.model.po.IEmpFamilyVOModel;
import com.newtouch.peoplemanage.model.po.IEmpNoteVOModel;
import com.newtouch.peoplemanage.model.po.IEnumInfoVOModel;
import com.newtouch.peoplemanage.model.po.IGleaderInfoVOModel;
import com.newtouch.peoplemanage.model.po.ILicenseInfoVOModel;
import com.newtouch.peoplemanage.model.vo.IPersonManageVOModel;

@Component
public class PersonManageDao extends BaseDao implements IPersonManageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<IPersonManageVOModel> queryPersonVOs(IPersonManageVOModel model) {
		System.out.println(model.getDept_list());
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("personManage.queryPersonVO_Count", model);
		System.out.println(count);
		model.getPageCount().setAllRows(count);
		model.getPageCount().calcPage();
		model.getPageCount().setNowPage(model.getPageCount().getNowPage() + 1);
		return this.getSqlMapClientTemplate().queryForList("personManage.queryPersonVOs", model);
	}

	@Override
	public IPersonManageVOModel queryPersonVOById(String person_no) {
		// TODO Auto-generated method stub
		return (IPersonManageVOModel) this.getSqlMapClientTemplate().queryForObject("personManage.queryPersonVOById", person_no);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IEmpFamilyVOModel> queryPersonFamily(String person_id) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("personManage.queryPersonFamily", person_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IEducationVOModel> queryPersonEducation(IEducationVOModel model) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("personManage.queryPersonEducation", model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IEmpNoteVOModel> queryEmpNote(String person_no) {

		return this.getSqlMapClientTemplate().queryForList("personManage.queryPersonNote", person_no);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ILicenseInfoVOModel> queryLicenseInfo(String person_no) {

		return this.getSqlMapClientTemplate().queryForList("personManage.queryLicenseInfo", person_no);
	}

	@Override
	public List<IPersonManageVOModel> queryPositionInfo(String person_no) {
		return this.getSqlMapClientTemplate().queryForList("personManage.queryPositionInfo", person_no);
	}

	@Override
	public String getbranch_level(String branch_id) {
		return (String) this.getSqlMapClientTemplate().queryForObject("personManage.getbranch_level",branch_id);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<BranchModel> queryBranchLevel() {
		// TODO Auto-generated method stub
		return (List<BranchModel>)this.getSqlMapClientTemplate().queryForList("personManage.queryBranchLevel");
	}
	
	@Override
	public void updatePersonInfo(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("personManage.updatePersonInfo",model);
		this.getSqlMapClientTemplate().update("personManage.updatePerson",model);
	}

	@Override
	public void importUpdateGleaderInfo(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("personManage.importUpdateGleaderInfo",model);
	}

	@Override
	public Integer findPersonIsExist(String person_no) {
		// TODO Auto-generated method stub
		return (Integer)this.getSqlMapClientTemplate().queryForObject("personManage.findPersonIsExist",person_no);
	}

	@Override
	public void modifySysEmployee(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("personManage.modifySysEmployee",model);
	}

	@Override
	public void modifySysEmployee1(IPersonManageVOModel model) {
		this.getSqlMapClientTemplate().update("personManage.modifySysEmployee1",model);
	}

	@Override
	public void modifySysEmployeeInfo(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("personManage.modifySysEmployeeInfo",model);
	}

	@Override
	public void modifySysAgentInfo(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("personManage.modifySysAgentInfo",model);
	}

	@Override
	public void modifySysEmpNote(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("personManage.modifySysEmpNote",model);
	}

	@Override
	public List<IPersonManageVOModel> queryEmpNoteInfo(String person_no) {
		// TODO Auto-generated method stub
		return (List<IPersonManageVOModel>)this.getSqlMapClientTemplate().queryForList("personManage.queryEmpNoteInfo",person_no);
	}

	@Override
	public List<IEnumInfoVOModel> querySelectList(String up_enum) {
		// TODO Auto-generated method stub
		return (List<IEnumInfoVOModel>)this.getSqlMapClientTemplate().queryForList("personManage.querySelectList",up_enum);
	}

	@Override
	public IGleaderInfoVOModel queryGleaderInfo(String person_no) {
		// TODO Auto-generated method stub
		return (IGleaderInfoVOModel)this.getSqlMapClientTemplate().queryForObject("personManage.queryGleaderInfo",person_no);
	}

	@Override
	public void updateApprovalFile(GleaderInfoVOModel gleaderInfo) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("personManage.updateApprovalFile",gleaderInfo);
	}

	@Override
	public void updateGleaderInfo(GleaderInfoVOModel gleaderInfo) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("personManage.updateGleaderInfo",gleaderInfo);
	}

	@Override
	public String queryGleaderPermission(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		return (String) this.getSqlMapClientTemplate().queryForObject("personManage.queryGleaderPermission",model);
	}
	
	//查询当前用户是否有操作高管的权限
	@Override
	public int queryUserDataAuth(IPersonManageVOModel personVO) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("personManage.queryUserDataAuth",personVO);
	}

	@Override
	public int findSysAgentInfo(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		return (Integer)this.getSqlMapClientTemplate().queryForObject("personManage.findSysAgentInfo",model);
	
	}

	@Override
	public int findSysEmpNote(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		return (Integer)this.getSqlMapClientTemplate().queryForObject("personManage.findSysEmpNote",model);
	}

	@Override
	public void insertSysAgentInfo(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("personManage.insertSysAgentInfo",model);
	}

	@Override
	public void insertSysEmpNote(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("personManage.insertSysEmpNote",model);
	}

	@Override
	public int findGleaderInfo(String person_no) {
		// TODO Auto-generated method stub
		return (Integer)this.getSqlMapClientTemplate().queryForObject("personManage.findGleaderInfo",person_no);
	}

	@Override
	public void insertGleaderInfo(GleaderInfoVOModel gleaderInfo) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("personManage.insertGleaderInfo",gleaderInfo);
	}

	@Override
	public void importInsertGleaderInfo(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("personManage.importInsertGleaderInfo",model);
	}

	@Override
	public void updatePersonClass(IPersonManageVOModel model) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("personManage.updatePersonClass",model);
	}	
	
	@Override
	public void saveEmployeePosition(IPersonManageVOModel model) {
		this.getSqlMapClientTemplate().insert("personManage.saveEmployeePosition",model);
		
	}
	
	@Override
	public void saveEmployeeInfo(IPersonManageVOModel model) {
		this.getSqlMapClientTemplate().insert("personManage.saveEmployeeInfo",model);
	}
	
	@Override
	public void saveEmployee(IPersonManageVOModel model) {
		this.getSqlMapClientTemplate().insert("personManage.saveEmployee",model);
	}
	
	@Override
	public void saveEduction(IPersonManageVOModel model) {
		this.getSqlMapClientTemplate().insert("personManage.saveEduction",model);
	}
	@Override
	public IPersonManageVOModel queryTeamNameBranchName(IPersonManageVOModel model) {
		return (IPersonManageVOModel) this.getSqlMapClientTemplate().queryForObject("personManage.queryTeamNameBranchName",model);
	}
	@Override
	public String findSysEmployor(String personNo) {
		// TODO Auto-generated method stub
		return (String) this.getSqlMapClientTemplate().queryForObject("personManage.findSysEmployor",personNo);
	}
	
}
