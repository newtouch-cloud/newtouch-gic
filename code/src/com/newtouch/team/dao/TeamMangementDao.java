package com.newtouch.team.dao;

import java.util.List;

import com.newtouch.team.model.vo.TeamManagementVoModel;
import com.newtouch.team.model.vo.TeamManagementVoModelImpl;

public interface TeamMangementDao {
	//查看团队
     public List<TeamManagementVoModel> SeleteTeam(TeamManagementVoModelImpl teamManagementVoModel);
   //注销团队
   	 public Boolean LogoutTeam(TeamManagementVoModel teamManagementVoModel);
   	 //修改团队
   	 public Boolean UpdateTeam(TeamManagementVoModel teamManagementVoModel);
   	 //查看单个团队
   	 public List<TeamManagementVoModel> SelectOneTeam(String team_id);
   //新增团队
   	 public Boolean AddTeam(TeamManagementVoModel teamManagementVoModel);
   //添加检查
   	 public List<TeamManagementVoModel> CheckTeam(TeamManagementVoModel teamManagementVoModel);
}
