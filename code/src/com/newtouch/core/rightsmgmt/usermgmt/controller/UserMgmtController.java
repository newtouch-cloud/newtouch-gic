package com.newtouch.core.rightsmgmt.usermgmt.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.ams.model.vo.IUserMgMtVOModel;
import com.ca.cacore.ams.model.vo.UserMgMtVOModel;
import com.ca.cacore.ams.webapp.service.IUserMgMtService;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newtouch.component.c11assistant.ModelHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.model.ISuperiorModel;
import com.newtouch.core.rightsmgmt.model.SuperiorModel;
import com.newtouch.core.rightsmgmt.rolemgmt.service.IRoleMgmtService;
import com.newtouch.core.rightsmgmt.usermgmt.service.IUserMgmtService;
import com.newtouch.utils.md5.Md5;
import com.newtouch.peoplemanage.model.vo.IPersonManageVOModel;
import com.newtouch.peoplemanage.model.vo.PersonManageVOModel;
import com.newtouch.peoplemanage.webapp.service.IPersonManageService;

@Controller
public class UserMgmtController extends BaseController {
	@Autowired
	private IUserMgmtService user;
	@Autowired
	private IRoleMgmtService role;
	@Autowired
	private IUserMgMtService userMgMtService;
	@Autowired
	private IPersonManageService personManageService;

	@RequestMapping("/goUserMgmtQueryPage.do")
	public ModelAndView goUserMgmtQueryPage(HttpServletRequest request,
			HttpServletResponse response) {
		IUserModel model = ServletHelper.getSessionUser(request);
		String flag = "";
		IPersonManageVOModel personVO = new PersonManageVOModel();
		personVO.setDept_list(model.getDept_list());
		int count = personManageService.queryUserDataAuth(personVO);
		if(count>0){
			flag = "1";
		}else{
			flag = "0";
		}
		HttpSession session =  request.getSession();
		session.setAttribute("flag", flag);
		return returnPage(response, new ReturnMsg(),
				"newtouch/core/rightsmgmt/usermgmt/QueryUsers");
	}

	@RequestMapping("/doUserMgmtQueryPage.do")
	public ModelAndView doUserMgmtQueryPage(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		IUserModel user1 = ServletHelper.getSessionUser(request);
		map.put("userName", user1.getEmp_id());
		ReturnMsg msg = user.queryUserList(map);
		
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/QueryUsers");
	}
	
	@RequestMapping("/doQueryUserMapping.do")
	public ModelAndView doQueryUserMapping(HttpServletRequest request,
			HttpServletResponse response) {		
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = user.queryUserMappingList(map);
		return returnPage(response, msg,"newtouch/core/rightsmgmt/usermgmt/QueryMappingUsers");
	}
	
	@RequestMapping("/addUserMapping.do")
	public ModelAndView addUserMapping(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		user.addUserMapping(map);
		ReturnMsg msg =user.queryUserMappingList(map);
		request.setAttribute("re_no", map.get("opt_no"));
		request.setAttribute("re_name", map.get("opt_name"));
		request.setAttribute("addFlag", "绑定成功");
		return returnPage(response, msg,"newtouch/core/rightsmgmt/usermgmt/QueryMappingUsers");
	}

	@RequestMapping("/goUserMgmtAddPage.do")
	public ModelAndView goUserMgmtAddPage(HttpServletRequest request,
			HttpServletResponse response) {
		return returnPage(response, new ReturnMsg(),
				"newtouch/core/rightsmgmt/usermgmt/AddUser");
	}

	/** 
	* 
	* @param request
	* @param response
	* @return ModelAndView
	* @description:新增用户
	*/
	@RequestMapping("/doUserMgmtAddUser.do")
	public ModelAndView doRoleMgmtAddRole(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = new ReturnMsg();
		/*if (!map.get("opt_password").equals(map.get("opt_conf_password"))) {
			msg.setFailMessage(new Message("用户密码与确认密码应保持一致！"));
			request.setAttribute("rmHelper", new ReturnMsgHelper(request, msg));
			request.setAttribute("msg", msg);
			//request.setAttribute("user", map);
			return returnPage(response, msg,
					"newtouch/core/rightsmgmt/usermgmt/AddUser");
		}*/
		msg = user.addUserList(map);//添加数据到用户表
		System.out.println(msg.isSuccessflag());
		//添加成功
		if(msg.isSuccessflag()){
			msg = user.addUserPathList(map);//添加数据到用户登录轨迹表	
		}
		System.out.println(msg.getMsgStr());
		request.setAttribute("msg", msg);
		request.setAttribute("rmHelper", new ReturnMsgHelper(request, msg, true));
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/AddUser");
	}

	@RequestMapping("/doUserRoleQueryPage.do")
	public ModelAndView doUserRoleQueryPage(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = user.queryUserRoleList(map);
		request.setAttribute("opt_no", map.get("opt_no"));
		request.setAttribute("opt_name", map.get("opt_name"));
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/AddUserRoles");
	}

	/** 
	* 
	* @param request
	* @param response
	* @return ModelAndView
	* @description:用户添加角色
	*/
	@RequestMapping("/goUserRoleAdd.do")
	public ModelAndView goUserRoleAdd(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = role.addRoleUserList(map, "opt_no", "role_no");
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/AddUserRoles");
	}

	@RequestMapping("/doUserDeptAddPage.do")
	public ModelAndView doUserDeptAddPage(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		user.editUser(map);
		ReturnMsg msg = user.addUserDeptList(map);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/AddUserDepts");
	}

	@RequestMapping("/goUserMdfPage.do")
	public ModelAndView goUserMdfPage(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = user.queryUserByOptno(map);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/MdfUser");
	}

	@RequestMapping("/goUserRoleAddPage.do")
	public ModelAndView goUserRoleAddPage(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = user.queryUserByOptno(map);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/AddUserRoles");
	}

	@RequestMapping("/doUserMgmtMdfUser.do")
	public ModelAndView doUserMgmtMdfUser(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = user.mdfUserDeptList(map);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/AddUserRoles");
	}
	/**
	 * 
	 * yanqiguang
	 * 2017年11月27日下午4:38:03
	 * TODO 跳转到用户数据权限添加页面，修改机构树的查询规则
	 */
	@RequestMapping("/goUserDeptAddPage.do")
	public ModelAndView goUserDeptAddPage(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		request.setAttribute("opt_no", map.get("opt_no"));
		request.setAttribute("opt_name", map.get("opt_name"));
		map.put("data_auth_type", "DEPT");
		String is4sub = user.queryIs4Sub((String)map.get("opt_no"));
		ReturnMsg msg = user.queryUserAuthList(map);
		request.setAttribute("is4sub",is4sub);
		return returnPage(response, msg,"newtouch/core/rightsmgmt/usermgmt/AddUserDepts");
	}

	@RequestMapping("/doUserDelete.do")
	public ModelAndView doUserDelete(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg = user.delUserDeptList(map);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/QueryUsers");
	}
	
	/**
	 * to修改密码页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goUserMdfPassPage.do")
	public ModelAndView goUserMdfPassPage(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		IUserModel usermodel=ServletHelper.getSessionUser(request);
        map.put("opt_no", usermodel.getEmp_id());
		ReturnMsg msg = user.queryUserByOptno(map);
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(request,msg,true);
		rmHelper.setReturnParams(msg.getDataTable());
		request.setAttribute("rmHelper", rmHelper);
		return returnPage(response, msg,"newtouch/core/rightsmgmt/usermgmt/MdfUserPass");
	}

	@RequestMapping("/toUserMdfPassPage.do")
	public ModelAndView toUserMdfPassPage(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		IUserModel usermodel=ServletHelper.getSessionUser(request);
        map.put("opt_no", usermodel.getEmp_id());
		ReturnMsg msg = user.queryUserByOptno(map);
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(request,msg,true);
		rmHelper.setReturnParams(msg.getDataTable());
		request.setAttribute("rmHelper", rmHelper);
		return returnPage(response, msg,"newtouch/core/rightsmgmt/usermgmt/MdfUserPassWord");
	}
	
	/*@RequestMapping("/toModifyPassWordPage.controller")
	public ModelAndView toModifyPassWordPage(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		IUserModel usermodel=ServletHelper.getSessionUser(request);
        map.put("opt_no", usermodel.getEmp_id());
		ReturnMsg msg = user.queryUserByOptno(map);
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(request,msg,true);
		rmHelper.setReturnParams(msg.getDataTable());
		request.setAttribute("rmHelper", rmHelper);
		return returnPage(response, msg,"newtouch/core/rightsmgmt/usermgmt/MdfUserPassWord");
	}*/
	
	/** 
	* 
	* @param request
	* @param response
	* @return ModelAndView
	* @description:密码修改方法
	*/
	@RequestMapping("/userMdfPass.do")
	public ModelAndView userMdfPass(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg=new ReturnMsg();
		if(!map.get("opt_password").equals(map.get("opt_conf_password"))){
			msg.setFailMessage(new Message("密码不一致，请重新录入！"));
		}
		Date date=new Date();
		GregorianCalendar gc =new GregorianCalendar();
		SimpleDateFormat sdf  =new SimpleDateFormat("yyyy-MM-dd");
		gc.setTime(date);
		gc.add(2,+3);//时间增加、减少的方法，1-代表年，2-代表月，3-代表周，5-代表天
		gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
		String start_date=sdf.format(date);
		String end_date=sdf.format(gc.getTimeInMillis());
		IUserMgMtVOModel model=new UserMgMtVOModel();
		model.setOpt_no(map.get("opt_no").toString());
		model.setOpt_password(Md5.EncoderPwdByMd5(map.get("opt_conf_password").toString()));
		model.setStart_date(start_date);
		model.setEnd_date(end_date);
		msg=userMgMtService.updatePassWord(model);//更新数据到用户登录轨迹表
		msg = user.mdfUserPassWord(map);//更新数据到用户表
		msg.setDataTable(map);
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(request,msg,false);
		rmHelper.setReturnParams(msg.getDataTable());
		request.setAttribute("rmHelper", rmHelper);
		return returnPage(response, msg,"newtouch/core/rightsmgmt/usermgmt/MdfUserPass");
	}
	
	/**
	 * 跳转修改密码界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goResetUserPassword.do")
	public ModelAndView goResetUserPassword(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		request.setAttribute("opt_no1", map.get("opt_no"));
		return new ModelAndView(
				"newtouch/core/rightsmgmt/usermgmt/ResetUserPassword");
	}
	
	
	@RequestMapping("/ModifyUserPassword.do")
	public ModelAndView ModifyUserPassword(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ReturnMsg msg=null;
		msg = user.updateUserPassWord(map);// 验证两次密码是否一致，密码加密，更新操作
		msg.setDataTable(map);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, msg, false);
		rmHelper.setReturnParams(msg.getDataTable());
		request.setAttribute("rmHelper", rmHelper);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/ResetUserPassword");

	}
	
	
	/**
	 * 跳转选择上级界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goSelectSuperior.do")
	public ModelAndView goSelectSuperior(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ISuperiorModel model = new SuperiorModel();
		model.setOpt_no((String)map.get("opt_no"));
		model.setOpt_name((String)map.get("opt_name"));
		model.setBranch_name((String)map.get("branch_name"));
		model.setBranch_id((String)map.get("branch_id"));
		ReturnMsg msg = new ReturnMsg();
		msg.setDataTable(TransHelper.obj2map(model));
		/*ReturnMsg msg = new ReturnMsg();
		SuperiorModel superiorModel = user.querySuperior(model);
		if(superiorModel==null){
			msg.setDataTable(TransHelper.obj2map(model));
		}else{
			msg.setDataTable(TransHelper.obj2map(superiorModel));
		}*/
		ArrayList<SuperiorModel> list = user.querySuperiorList(model);
		ArrayList<SuperiorModel> list1 = user.querySuperiorInfos(model);
		
		//HttpSession session = request.getSession();
		request.setAttribute("flag", "1");
		request.setAttribute("superiorList",list);
		request.setAttribute("superiorList1",list1);
		request.setAttribute("rmHelper", new ReturnMsgHelper(request, msg).setReturnParams(msg.getDataTable()));
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/SelectSuperior");
	}
	//根据机构选择上级
	@RequestMapping("/findSuperiors.do")
	public void findSupersByBranchId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> map = this.getRequestMap(request);
		ISuperiorModel model = new SuperiorModel();
		model.setBranch_id((String)map.get("branch_id"));
		//ReturnMsg msg = new ReturnMsg();
		ArrayList<SuperiorModel> list = user.findSupersByBranchId(model);
		//msg.setDataList(TransHelper.list2MapList(list));
		ObjectMapper om = new ObjectMapper(); 
	    String userJson = om.writeValueAsString(list);
	    response.setContentType("text/html;charset=UTF‐8"); 
	    response.getWriter().write(userJson);
	    
	}
	/**
	 * 保存上级
	 * @param request
	 * @param response
	 * @return
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 */
	@RequestMapping("/goSaveSuperior.do")
	public ModelAndView goSaveSuperior(HttpServletRequest request,
			HttpServletResponse response) throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		ModelHelper modelHelper = new ModelHelper();
		ISuperiorModel model = new SuperiorModel();
		model = (ISuperiorModel) modelHelper.getModel(model, request);
		String superior = model.getSuperior_name();
		ArrayList<SuperiorModel> list0 = user.querySuperiorInfos(model);
		ArrayList<SuperiorModel> list = user.findSupersByBranchId(model);
		request.setAttribute("superiorList1",list0);
		request.setAttribute("superiorList",list);
		if(superior!=null && superior.contains("---")){
			String[] arr = superior.split("---");
			model.setSuperior_name(arr[1]);
		}
		if(superior!=null && !superior.contains("---")){
			Boolean flag = user.queryIsExistByName(superior);
			if(!flag){
				ReturnMsg msg = new ReturnMsg();
				msg.setFailMessage("该上级在所选择机构中不存在");
				msg.setDataTable(TransHelper.obj2map(model));
				request.setAttribute("rmHelper", new ReturnMsgHelper(request, msg).setReturnParams(msg.getDataTable()));
				return returnPage(response, msg,
						"newtouch/core/rightsmgmt/usermgmt/SelectSuperior");
			}
		}
		
		//SuperiorModel superiorModel = user.querySuperior(model);
		ReturnMsg msg = user.insertSuperior(model);
	
		ArrayList<SuperiorModel> list1 = user.querySuperiorInfos(model);	
		//HttpSession session = request.getSession();
		request.setAttribute("superiorList1",list1);
		request.setAttribute("flag", "1");
		request.setAttribute("rmHelper", new ReturnMsgHelper(request, msg).setReturnParams(msg.getDataTable()));
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/SelectSuperior");
	}
	
	/**
	 * 删除上级
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteSuperior.do")
	public ModelAndView deleteSuperior(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		ISuperiorModel model = new SuperiorModel();
		model.setOpt_no((String)map.get("opt_no"));
		model.setOpt_name((String)map.get("opt_name"));
		model.setBranch_name((String)map.get("branch_name"));
		model.setBranch_id((String)map.get("branch_id"));
		model.setSuperior_id((String)map.get("superior_id"));
		//model.setSuperior_no((String)map.get("superior_no"));
		ReturnMsg msg = new ReturnMsg();
		/*ReturnMsg msg = new ReturnMsg();
		SuperiorModel superiorModel = user.querySuperior(model);
		if(superiorModel==null){
			msg.setDataTable(TransHelper.obj2map(model));
		}else{
			msg.setDataTable(TransHelper.obj2map(superiorModel));
		}*/
		user.deleteSuperior(model);
		ISuperiorModel superiorModel = user.queryOpt(model);
		msg.setDataTable(TransHelper.obj2map(superiorModel));
		ArrayList<SuperiorModel> list = user.querySuperiorList(model);
		ArrayList<SuperiorModel> list1 = user.querySuperiorInfos(model);
		
		//HttpSession session = request.getSession();
		request.setAttribute("flag", "1");
		request.setAttribute("superiorList",list);
		request.setAttribute("superiorList1",list1);
		request.setAttribute("rmHelper", new ReturnMsgHelper(request, msg).setReturnParams(msg.getDataTable()));
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/SelectSuperior");
	}
	
	
	/**
	 * 添加用户界面根据用户代码获取用户相关信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 */
	@RequestMapping("/getInfo.do")
	public void getInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> map = this.getRequestMap(request);
	    //ReturnMsg msg = new ReturnMsg();
		Map<String, Object> map1 = user.getInfo(map);
		if(map1.isEmpty()){
			map1.put("branch_id", "");
			map1.put("branch_name", "");
			map1.put("opt_name", "");
			map1.put("opt_sex", "");
			map1.put("opt_mail", "");
			map1.put("opt_phone", "");
		}
	    ObjectMapper om = new ObjectMapper(); 
	    String userJson = om.writeValueAsString(map1);
	    response.setContentType("text/html;charset=UTF‐8"); 
	    response.getWriter().write(userJson);
	    //request.setAttribute("rmHelper", new ReturnMsgHelper(request, msg).setReturnParams(msg.getDataTable()));
	}
	
	
	@RequestMapping("/goUpdateOptStatus.do")
	public ModelAndView goUpdateOptStatus(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		Object opt_status = map.get("opt_status");
		if("有效".equals(opt_status)){
			opt_status = "0";
		}
		if("无效".equals(opt_status)){
			opt_status = "1";
		}
		map.put("opt_status", opt_status);
		ReturnMsg msg = user.goUpdateOptStatus(map);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/QueryUsers");
	}
}
