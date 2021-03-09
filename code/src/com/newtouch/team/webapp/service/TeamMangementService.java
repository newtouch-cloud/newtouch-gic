package com.newtouch.team.webapp.service;

import org.springframework.stereotype.Service;

import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.team.model.vo.TeamManagementVoModel;
import com.newtouch.team.model.vo.TeamManagementVoModelImpl;

@Service
public interface TeamMangementService {
	//查看团队
	ReturnMsg SeleteTeam(TeamManagementVoModelImpl teamManagementVoModel);
	//注销团队
	ReturnMsg LogoutTeam(TeamManagementVoModel teamManagementVoModel);
	//修改团队
	ReturnMsg UpdateTeam(TeamManagementVoModel teamManagementVoModel);
	//查看单个团队
	ReturnMsg SelectOneTeam(String team_id);
	//新增团队
	ReturnMsg AddTeam(TeamManagementVoModel teamManagementVoModel);
	//添加检查
	public int  CheckTeam(TeamManagementVoModel teamManagementVoModel);
}
