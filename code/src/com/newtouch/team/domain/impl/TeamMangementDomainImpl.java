package com.newtouch.team.domain.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.team.dao.TeamMangementDao;
import com.newtouch.team.domain.TeamMangementDomain;
import com.newtouch.team.model.vo.TeamManagementVoModel;
import com.newtouch.team.model.vo.TeamManagementVoModelImpl;
@Service
public class TeamMangementDomainImpl implements TeamMangementDomain {
@Autowired private TeamMangementDao teamMangementDao;
//查看团队
	@Override
	public List<TeamManagementVoModel> SeleteTeam(TeamManagementVoModelImpl teamManagementVoModel) {
		List<TeamManagementVoModel> list = teamMangementDao.SeleteTeam(teamManagementVoModel);
		return list;

	}

	// 团队注销
	@Override
	public Boolean LogoutTeam(TeamManagementVoModel teamManagementVoModel) {
		teamMangementDao.LogoutTeam(teamManagementVoModel);
		return true;
	}
    //修改团队
	@Override
	public Boolean UpdateTeam(TeamManagementVoModel teamManagementVoModel) {
		teamMangementDao.UpdateTeam(teamManagementVoModel);
		return true;
	}
    //查询单个团队
	@Override
	public List<TeamManagementVoModel> SelectOneTeam(String team_id) {
		List<TeamManagementVoModel> list = teamMangementDao.SelectOneTeam(team_id);
		return list;
	}
	//新增团队

	@Override   
	public Boolean AddTeam(TeamManagementVoModel teamManagementVoModel) {
		teamMangementDao.AddTeam(teamManagementVoModel);
		return true;
	}
	//添加检查
		@Override
		public int  CheckTeam(TeamManagementVoModel teamManagementVoModel) {
			List<TeamManagementVoModel> list = teamMangementDao.CheckTeam(teamManagementVoModel);
			return list.size();
		}
}
