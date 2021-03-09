package com.ca.cacore.manage.webapp.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IRoleAuthsModel;
import com.ca.cacore.manage.model.bo.IRoleModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.RoleAuthsModel;
import com.ca.cacore.manage.model.bo.RoleModel;
import com.ca.cacore.manage.model.bo.UserModel;
import com.ca.cacore.manage.webapp.service.IRoleService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ThreadLocalHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.ReturnMsg;


@Controller
public class RoleController extends BaseController{
	
	@Autowired private IRoleService roleService;

	private static IUserModel user = new UserModel();
	

	/*
	 *角色表分页查询
	 */

	@RequestMapping("/Role/roleSelect.do")
	public ModelAndView roleSelect(HttpServletRequest req
			                        ,HttpServletResponse res) throws Exception {
		
		ThreadLocalHelper.set("isOpen", true);
		String role_type = ActionHelper.getNullToStr(req.getParameter("role_type"));
		String role_id = ActionHelper.getNullToStr(req.getParameter("role_id"));
		String role_name = ActionHelper.getNullToStr(req.getParameter("role_name"));
		String status = ActionHelper.getNullToStr(req.getParameter("status"));
		IRoleModel model = new RoleModel();
		model.setRole_type(role_type);
		model.setRole_id(role_id);
		model.setRole_name(role_name);
		model.setStatus(status);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = roleService.queryAllRole(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		return returnPage(res, returnMsg, "ca/cacore/manage/role/role_Select");
		
	}
	
	/*
	 * 查看详细信息
	 */
	
	@RequestMapping("/Role/roleView.do")
	public ModelAndView roleView(HttpServletRequest req
			                      ,HttpServletResponse res) throws Exception {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		IRoleModel model = new RoleModel();
		model.setSeq_id(seq_id);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = roleService.getRole(model);
		req.setAttribute("returnMsg", returnMsg);
		return new ModelAndView("ca/cacore/manage/role/role_View");
	}
	
	/*
	 * 添加角色
	 */
	@RequestMapping("/Role/roleAdd.do")
	public ModelAndView roleAdd(HttpServletRequest req
			                     ,HttpServletResponse res) throws Exception {
		String role_type = ActionHelper.getNullToStr(req.getParameter("role_type"));
		String role_id = ActionHelper.getNullToStr(req.getParameter("role_id"));
		String role_name = ActionHelper.getNullToStr(req.getParameter("role_name"));
		String remark = ActionHelper.getNullToStr(req.getParameter("remark"));
		
		IRoleModel model = new RoleModel();
		model.setRole_type(role_type);
		model.setRole_id(role_id);
		model.setRole_name(role_name);
		model.setRemark(remark);
		
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = roleService.addRole(model,user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,true));
		return new ModelAndView("ca/cacore/manage/role/role_Add");
	}
	
	/*
	 * 跳转到修改页面
	 */
	
	@RequestMapping("/Role/roleGoModify.do")
	public ModelAndView roleGoModify(HttpServletRequest req
			                          ,HttpServletResponse res) throws Exception {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		 
		IRoleModel model = new RoleModel();
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg = roleService.getRole(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/manage/role/role_Modify");
	}
	
	/*
	 * 修改保存和回显
	 */
	
	@RequestMapping("/Role/roleModify.do")
	public ModelAndView roleModify(HttpServletRequest req
			                        ,HttpServletResponse res) throws Exception {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		String role_type = ActionHelper.getNullToStr(req.getParameter("role_type"));
		String role_id = ActionHelper.getNullToStr(req.getParameter("role_id"));
		String role_name = ActionHelper.getNullToStr(req.getParameter("role_name"));
		String status = ActionHelper.getNullToStr(req.getParameter("status"));
		String remark = ActionHelper.getNullToStr(req.getParameter("remark"));
		
		IRoleModel model = new RoleModel();
		model.setSeq_id(seq_id);
		model.setRole_type(role_type);
		model.setRole_id(role_id);;
		model.setRole_name(role_name);
		model.setStatus(status);
		model.setRemark(remark);
		
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = roleService.updateRole(model,user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		req.setAttribute("seq_id",seq_id);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg));
		return new ModelAndView("ca/cacore/manage/role/role_Modify");
	}
	
	/*
	 * 角色权限分配查看
	 */
	
	@RequestMapping("/Role/rolePrivilege.do")
	public ModelAndView getRoleAuthsMenu(HttpServletRequest req
            ,HttpServletResponse res){
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		IRoleModel model = new RoleModel();
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg = roleService.getRole(model);
		ReturnMsg returnAuthsMsg = roleService.getRoleAuthsAll(model,user);
		returnMsg.setDataList(returnAuthsMsg.getDataList());
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg));
		return new ModelAndView("ca/cacore/manage/role/roleAuths");
	}
	
	@RequestMapping("/Role/addRoleAuths.do")
	public ModelAndView addRoleAuths(HttpServletRequest req
            ,HttpServletResponse res){
		int a =  ActionHelper.getNullToInteger(req.getParameterValues("menu_selected").length);
		String role_id = ActionHelper.getNullToStr(req.getParameter("role_id"));
		List<IRoleAuthsModel> modellist = new ArrayList<IRoleAuthsModel>();
		if(a>1){
			String[] stringArray = new String[a];
			stringArray = req.getParameterValues("menu_selected");
			
			for(int i= 1;i<stringArray.length;i++){
				IRoleAuthsModel rp = new RoleAuthsModel();
				String[] returnArray = new String[2];
				returnArray =stringArray[i].split(":");
				rp.setMenu_id(returnArray[0]);
				rp.setButton_id(returnArray[1]);
				rp.setRole_id(role_id);
				modellist.add(rp);
			}
			
		}else{
			IRoleAuthsModel rp = new RoleAuthsModel();
			rp.setRole_id(role_id);
			modellist.add(rp);
		}
		
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = roleService.addRoleAuths(modellist, user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		req.setAttribute("role_id", role_id);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg) );
		return new ModelAndView("ca/cacore/manage/role/roleAuths");
	}
	
	@RequestMapping("/Role/queryForVerifyData.do")
	public ModelAndView queryForVerifyData(HttpServletRequest req
            ,HttpServletResponse res){
		String role_name = ActionHelper.getNullToStr(req.getParameter("role_name"));
		String role_id = ActionHelper.getNullToStr(req.getParameter("role_id"));
		IRoleModel model = new RoleModel();
		model.setRole_name(role_name);
		model.setRole_id(role_id);
		Integer count=roleService.queryForVerifyData(model);
	    res.setContentType("text/html;charset=UTF-8"); //解决火狐浏览器ajax返回的data输出为[object XMLDocument]的问题
	    try {
			PrintWriter pw = res.getWriter();
			pw.write(count+"");  //返回数字的字符串,前台接收如果=='0'则表示不在可以添加或修改
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
