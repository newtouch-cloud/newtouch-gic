package com.newtouch.team.webapp.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
/*import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;*/
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.team.model.vo.TeamManagementVoModel;
import com.newtouch.team.model.vo.TeamManagementVoModelImpl;
import com.newtouch.team.webapp.service.TeamMangementService;

@Controller
public class TeamMangementController extends BaseController{
	@Autowired 
	private TeamMangementService teamMangementService;
	private Integer flag;
	/**
	* @param req
	* @param res
	* @return
	* @throws ModelAndView
	* @description:重定向到主页面面和查询
	 */
	
	  @RequestMapping("/Team/toQueryTeam.do")
	   public ModelAndView Login(HttpServletRequest req,HttpServletResponse res){
	   	return new ModelAndView("ca/cacore/team/TeamMangement");
	   }
	/*
	 * 查看团队/部门
	 */
	@RequestMapping("/team/teammangement/queryteam.do")
	public ModelAndView SeleteTeam(HttpServletRequest req,HttpServletResponse res){
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构编码
		String team_name = ActionHelper.getNullToStr(req.getParameter("team_name"));//团队名称
		String status = ActionHelper.getNullToStr(req.getParameter("status"));//团队状态
		String team_type = ActionHelper.getNullToStr(req.getParameter("team_type"));
		Date found_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("found_date")));//创建时间 自
		Date recall_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("recall_date")));//至
        IUserModel user = ServletHelper.getSessionUser(req);
		String deptlist =user.getDept_list();//查询权限
		TeamManagementVoModelImpl teamManagementVoModel = new TeamManagementVoModel();
		teamManagementVoModel.setBranch_id(branch_id);
		teamManagementVoModel.setTeam_name(team_name);
		teamManagementVoModel.setStatus(status);
		teamManagementVoModel.setFound_date(found_date);
		teamManagementVoModel.setRecall_date(recall_date);
		teamManagementVoModel.setDeptlist(deptlist);
		teamManagementVoModel.setTeam_type(team_type);
		teamManagementVoModel.setPageCount(ActionHelper.getPage(req));//分页
		ReturnMsg returnMsg = teamMangementService.SeleteTeam(teamManagementVoModel);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,teamManagementVoModel.getPageCount(),true));
		return returnPage(res,returnMsg,"ca/cacore/team/TeamMangement");
	}
	
	/*
	 * 注销团队/部门
	 */
	@RequestMapping("/team/teammangement/logoutteam.do")
	public ModelAndView LogoutTeam(HttpServletRequest req,HttpServletResponse res){
		String team_id = ActionHelper.getNullToStr(req.getParameter("team_id"));//团队/部门编码
		TeamManagementVoModel teamManagementVoModel = new TeamManagementVoModel();
		teamManagementVoModel.setTeam_id(team_id);
		ReturnMsg flag = teamMangementService.LogoutTeam(teamManagementVoModel);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, flag));
		return new ModelAndView("ca/cacore/team/TeamMangement");
		
	}
	
	/*
	 * 修改团队/部门
	 */
	@RequestMapping("/team/teammangement/Updateteam.do")
	public ModelAndView UpadteTeam(HttpServletRequest req,HttpServletResponse res){
		String team_id = ActionHelper.getNullToStr(req.getParameter("team_id"));//团队/部门编码
		String team_name = ActionHelper.getNullToStr(req.getParameter("team_name"));//团队/部门名称
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//上级部门编码
		IUserModel user = ServletHelper.getSessionUser(req);
		String modifyuser =user.getUserName();//修改人
		TeamManagementVoModel teamManagementVoModel = new TeamManagementVoModel();
		teamManagementVoModel.setTeam_id(team_id);
		teamManagementVoModel.setTeam_name(team_name);
		teamManagementVoModel.setModifyuser(modifyuser);
		teamManagementVoModel.setBranch_id(branch_id);
		ReturnMsg flag = teamMangementService.UpdateTeam(teamManagementVoModel);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, flag));
		return new ModelAndView("ca/cacore/team/TeamMangementUpdate");
	}
	/*
	 * 查询单个团队/部门信息(修改)
	 */
	@RequestMapping("/team/teammangement/SelectOneteam.do")
	public ModelAndView SelectOneTeam(HttpServletRequest req,HttpServletResponse res){
		String team_id = ActionHelper.getNullToStr(req.getParameter("team_id"));//团队部门编码
		ReturnMsg returnMsg = teamMangementService.SelectOneTeam(team_id);
 	    req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,new PageCount(),true));
		
		return new ModelAndView("ca/cacore/team/TeamMangementUpdate");
	}
	/*
	 * 团队/部门新增
	 */
	@RequestMapping("/team/teammangement/addTeam.do")
	public ModelAndView AddteammangementTeam(HttpServletRequest req,HttpServletResponse res){
		String team_name = ActionHelper.getNullToStr(req.getParameter("team_name"));//团队名称
		Date found_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("found_date")));//团队创建时间
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//归属机构编码
		String team_type = ActionHelper.getNullToStr(req.getParameter("team_type"));//区别团队或者部门
        IUserModel user = ServletHelper.getSessionUser(req);
		String username =user.getUserName();//修改人
		TeamManagementVoModel teamManagementVoModel = new TeamManagementVoModel();
		teamManagementVoModel.setTeam_name(team_name);//团队名称
		teamManagementVoModel.setFound_date(found_date);//创建时间
		teamManagementVoModel.setBranch_id(branch_id);//商机代码
		teamManagementVoModel.setTeam_type(team_type);//区别团队或者部门
		teamManagementVoModel.setCreateuser(username);
		teamManagementVoModel.setModifyuser(username);
		ReturnMsg flag = teamMangementService.AddTeam(teamManagementVoModel);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, flag));
		req.setAttribute("removeflag","1");
		return new ModelAndView("ca/cacore/team/TeamMangementAdd");
	}
	/*
     * 团队/部门新增保存
     */
    @RequestMapping("/team/teammangement/keepTeamMangement.do")
    public ModelAndView KeepDepartMent(HttpServletRequest req,HttpServletResponse res){
   	 return new ModelAndView("ca/cacore/team/TeamMangementAdd"); 
    }
    /*
     * 添加检查
     */
    @RequestMapping("/team/teammangement/addCheckRepeat.do")
   public ModelAndView AddCheckRepeat(HttpServletRequest req,HttpServletResponse res) throws IOException{
       
       TeamManagementVoModel teamManagementVoModel = new TeamManagementVoModel();
       teamManagementVoModel.setBranch_id(ActionHelper.getNullToStr(req.getParameter("branch_id")));
       teamManagementVoModel.setTeam_name(ActionHelper.getNullToStr(req.getParameter("team_name")));
		Integer count=teamMangementService.CheckTeam(teamManagementVoModel);
		String returnInfo="";
		if (count==0) {
			//没有相同的机构名称，那么返回输入成功
			returnInfo="{isSuccess:"+true+"}";
		}else {
			//有相同的证件信息，那么返回失败
			returnInfo="{isSuccess:"+false+"}";
		}
		res.getWriter().print(returnInfo);
		return null;
       
       
      
    }
    
        
}
