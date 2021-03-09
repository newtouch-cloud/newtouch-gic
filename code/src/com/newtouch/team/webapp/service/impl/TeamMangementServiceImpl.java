package com.newtouch.team.webapp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.team.domain.TeamMangementDomain;
import com.newtouch.team.model.vo.TeamManagementVoModel;
import com.newtouch.team.model.vo.TeamManagementVoModelImpl;
import com.newtouch.team.webapp.service.TeamMangementService;
@Service
public class TeamMangementServiceImpl implements TeamMangementService{
  @Autowired
  private TeamMangementDomain teamMangementDomain;
  //查看团队
  public ReturnMsg SeleteTeam(TeamManagementVoModelImpl teamManagementVoModel){
	  ReturnMsg returnMsg =  new ReturnMsg();
	  List<TeamManagementVoModel> list = teamMangementDomain.SeleteTeam(teamManagementVoModel);
	  returnMsg.setDataList(TransHelper.list2MapList(list));
	  return returnMsg;
  }
  //团队注销
  @Override
  public ReturnMsg LogoutTeam(TeamManagementVoModel teamManagementVoModel) {
	ReturnMsg returnMsg = new ReturnMsg();
	teamMangementDomain.LogoutTeam(teamManagementVoModel);
	returnMsg.setSuccessMessage("注销成功");
	return returnMsg;
}
  //修改团队
  @Override
  public ReturnMsg UpdateTeam(TeamManagementVoModel teamManagementVoModel){
	  ReturnMsg returnMsg=new ReturnMsg();
	  try {
		    teamMangementDomain.UpdateTeam(teamManagementVoModel);
			returnMsg.setDataTable(TransHelper.obj2map(teamManagementVoModel));
			returnMsg.setSuccessMessage("修改成功");
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		
	  return returnMsg;
  }
  //查看单个团队
  @Override
  public ReturnMsg SelectOneTeam(String team_id){
	  ReturnMsg returnMsg = new ReturnMsg();
	  List<TeamManagementVoModel> list = teamMangementDomain.SelectOneTeam(team_id);
	  List<Map<String,Object>> list_result= TransHelper.list2MapList(list);
		Map<String,Object> map=list_result.get(0);
		returnMsg.setDataTable(map);
		return returnMsg;
	  
  }
//新增团队
  @Override
  public ReturnMsg AddTeam(TeamManagementVoModel teamManagementVoModel) {
	  ReturnMsg returnMsg=new ReturnMsg();
	  teamMangementDomain.AddTeam(teamManagementVoModel);
	  returnMsg.setSuccessMessage("保存成功");
	return returnMsg;
}
  //添加检查
  @Override
  public int  CheckTeam(TeamManagementVoModel teamManagementVoModel) {
	  return teamMangementDomain.CheckTeam(teamManagementVoModel);
}
}
