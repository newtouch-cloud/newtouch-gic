package com.newtouch.core.rightsmgmt.rolemgmt.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.rolemgmt.service.IRoleMgmtService;

@Controller
public class RoleMgmtController extends BaseController {
	@Autowired
	private IRoleMgmtService role;

	@RequestMapping("/goRoleMgmtQueryPage.do")
	public ModelAndView goRoleMgmtQueryPage(HttpServletRequest request,
			HttpServletResponse response) {
		IUserModel usermodel=ServletHelper.getSessionUser(request);
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = role.queryRoleList(map);
		return returnPage(response, msg,"newtouch/core/rightsmgmt/rolemgmt/QueryRoles");
	}

	@RequestMapping("/doRoleMgmtQueryPage.do")
	public ModelAndView doRoleMgmtQueryPage(HttpServletRequest request,
			HttpServletResponse response) {
		IUserModel usermodel=ServletHelper.getSessionUser(request);
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = role.queryRoleList(map);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/rolemgmt/QueryRoles");
	}

	@RequestMapping("/goRoleMgmtAddPage.do")
	public ModelAndView goRoleMgmtAddPage(HttpServletRequest request,
			HttpServletResponse response) {
		return returnPage(response, new ReturnMsg(),
				"newtouch/core/rightsmgmt/rolemgmt/AddRoleBaseInfo");
	}

	//原来的保存
	/*@RequestMapping("/doRoleMgmtAddRole.do")
	public ModelAndView doRoleMgmtAddRole(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = new ReturnMsg();
		msg = role.addRole(map);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/rolemgmt/AddRoleBaseInfo");
	}*/
	//m by liu_yn 保存按钮
	@RequestMapping("/doRoleMgmtAddRole.do")
	public void doRoleMgmtAddRole(HttpServletRequest request,
			HttpServletResponse response) {
		try {
		Map<String, Object> map = this.getRequestMap(request);
		//String role_name = new String(request.getParameter("role_name").getBytes("ISO-8859-1"), "UTF-8");
		//map.put("role_name", role_name);
		ReturnMsg msg = new ReturnMsg();
		msg = role.addRole(map);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, msg);
		request.setAttribute("rmHelper", rmHelper.setReturnParams(msg.getDataTable()));
		if(!msg.isSuccessflag()){
			response.getWriter().write(msg.getMsgStr());
		}else{
			response.getWriter().write("保存成功!");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/goMdfRoleBaseInfo.do")
	public ModelAndView goMdfRoleBaseInfo(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = new ReturnMsg();
		try {
			msg = role.queryRoleList(map);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		if (msg.getDataList().size() > 0) {
			msg.setDataTable(msg.getDataList().get(0));
			msg.getDataList().clear();
		}
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/rolemgmt/MdfRoleBaseInfo");
	}

	@RequestMapping("/doMdfRoleBaseInfo.do")
	public ModelAndView doMdfRoleBaseInfo(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = role.mdfRoleBaseInfo(map);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/rolemgmt/MdfRoleBaseInfo");
	}

	@RequestMapping("/goMdfRoleAuthInfo.do")
	public ModelAndView goMdfRoleMenuInfo(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		map.put("data_auth_type", "MENU");
		ReturnMsg msg = role.queryRoleList(map);
		ReturnMsg authMsg = role.queryRoleAuthList(map);
		if (msg.getDataList().size() > 0) {
			msg.setDataTable(msg.getDataList().get(0));
			msg.getDataList().clear();
			msg.getDataList().addAll(authMsg.getDataList());
		}
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/rolemgmt/MdfRoleAuthInfo");
	}

	@RequestMapping("/doMdfRoleAuthInfo.do")
	public ModelAndView doMdfRoleMenuInfo(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = role.mdfRoleAuthInfo(map);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/rolemgmt/MdfRoleAuthInfo");
	}

	@RequestMapping("/goMdfRoleUserInfo.do")
	public ModelAndView goMdfRoleUserInfo(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = role.queryRoleList(map);
		if (msg.getDataList().size() > 0) {
			msg.setDataTable(msg.getDataList().get(0));
			msg.getDataList().clear();
		}
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/rolemgmt/MdfRoleUserInfo");
	}

	@RequestMapping("/doMdfRoleUserInfoQuery.do")
	public ModelAndView doMdfRoleUserInfoQuery(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = role.queryRoleUserList(map);
		request.setAttribute("opt_no", map.get("opt_no"));
		request.setAttribute("opt_name", map.get("opt_name"));
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/rolemgmt/MdfRoleUserInfo");
	}

	@RequestMapping("/doDeleteRole.do")
	public ModelAndView doDeleteRole(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = role.deleteRoleUserList(map);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/rolemgmt/QueryRoles");
	}

	@RequestMapping("/addMdfRoleUserInfo.do")
	public ModelAndView addMdfRoleUserInfo(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg=new ReturnMsg();
		if("role".equals(map.get("type"))){
			 msg = role.addRoleRelationUser(map,"role_no", "opt_no");

		}else{
			 msg = role.addRoleUserList(map,"role_no", "opt_no");
		}
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/rolemgmt/MdfRoleUserInfo");
	}

}
