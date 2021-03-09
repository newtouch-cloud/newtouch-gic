package com.newtouch.team.dao.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.newtouch.core.daobase.BaseDao;
import com.newtouch.team.dao.TeamMangementDao;
import com.newtouch.team.model.vo.TeamManagementVoModel;
import com.newtouch.team.model.vo.TeamManagementVoModelImpl;
import com.newtouch.newutil.string.StrUtil;

@Component
public class TeamMangementDaoImpl extends BaseDao implements TeamMangementDao {
	/*
	 * 查询团队
	 */
	@Override
	public List<TeamManagementVoModel> SeleteTeam(TeamManagementVoModelImpl teamManagementVoModel) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("TeamVo.queryCount", teamManagementVoModel);
		teamManagementVoModel.getPageCount().setAllRows(count);
		teamManagementVoModel.getPageCount().calcPage();
		teamManagementVoModel.getPageCount().setNowPage(teamManagementVoModel.getPageCount().getNowPage() + 1);
		List<TeamManagementVoModel> list = (List<TeamManagementVoModel>) this.getSqlMapClientTemplate().queryForList("TeamVo.SeleteTeam",
				teamManagementVoModel);
		return list;
	}

	/*
	 * 注销团队
	 */
	@Override
	public Boolean LogoutTeam(TeamManagementVoModel teamManagementVoModel) {
		Date recall_date = (Date) this.getSqlMapClientTemplate().queryForObject("TeamVo.querySysdate", teamManagementVoModel);
		teamManagementVoModel.setRecall_date(recall_date);
		this.getSqlMapClientTemplate().update("TeamVo.logoutTeam", teamManagementVoModel);
		return true;
	}

	/*
	 * 修改团队
	 */
	@Override
	public Boolean UpdateTeam(TeamManagementVoModel teamManagementVoModel) {
		Date sysdate = (Date) this.getSqlMapClientTemplate().queryForObject("TeamVo.querySysdate", teamManagementVoModel);
		teamManagementVoModel.setModifydate(sysdate);
		this.getSqlMapClientTemplate().update("TeamVo.UpdateTeam", teamManagementVoModel);
		return true;
	}

	/*
	 * 查看单个团队
	 */
	@Override
	public List<TeamManagementVoModel> SelectOneTeam(String team_id) {
		List<TeamManagementVoModel> list = this.getSqlMapClientTemplate().queryForList("TeamVo.SelectOneTeam", team_id);
		return list;
	}

	// 新增团队
	@Override
	public Boolean AddTeam(TeamManagementVoModel teamManagementVoModel) {
		int count = (Integer) this.getSqlMapClientTemplate().queryForObject("TeamVo.TeamCount", teamManagementVoModel);
		String count1 = StrUtil.alignLeft(count, 3);
		String branch_id = teamManagementVoModel.getBranch_id();
		StringBuilder branch = new StringBuilder();
		branch.append(branch_id);
		branch.append(count1);
		String branch1 = branch.toString();
		Date sysdate = (Date) this.getSqlMapClientTemplate().queryForObject("TeamVo.querySysdate", teamManagementVoModel);
		teamManagementVoModel.setTeam_id(branch1);
		teamManagementVoModel.setCreatedate(sysdate);
		teamManagementVoModel.setModifydate(sysdate);
		this.getSqlMapClientTemplate().insert("TeamVo.AddTeam", teamManagementVoModel);
		return true;
	}

	/*
	 * 添加检查
	 */
	@Override
	public List<TeamManagementVoModel> CheckTeam(TeamManagementVoModel teamManagementVoModel) {
		return (List<TeamManagementVoModel>) this.getSqlMapClientTemplate().queryForList("TeamVo.CheckTeam", teamManagementVoModel);
	}
}
