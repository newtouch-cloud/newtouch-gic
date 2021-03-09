package com.ca.cacore.ams.webapp.controller;

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
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.usermgmt.service.IUserMgmtService;

@Controller
public class UserRestPassController extends BaseController {
	@Autowired
	private IUserMgmtService user;

	/**
	 * 密码重置页面跳转
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goUserRestPassPage.do")
	public ModelAndView goUserRestPassPage(HttpServletRequest req,
			HttpServletResponse res) {
		IUserModel usermodel = ServletHelper.getSessionUser(req);
		req.setAttribute("opt_name_login", usermodel.getUserName());
		return new ModelAndView(
				"newtouch/core/rightsmgmt/usermgmt/RestUserPass");
	}

	/**
	 * to密码重置页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goUserUpdatePassPage.do")
	public ModelAndView goUserUpdatePassPage(HttpServletRequest request,
			HttpServletResponse response) {
		IUserModel usermodel = ServletHelper.getSessionUser(request);
		String opt_name_login = usermodel.getUserName();
		request.setAttribute("opt_name_login", opt_name_login);//将登陆人员名称传到前台校验是否为本人登录
		Map<String, Object> map = this.getRequestMap(request);
		/**
		 * 校验密码重置的的用户名是否存在
		 */
		ReturnMsg msg=null;
		msg = user.checkUserName(map);
		if(msg.getDataTable() == null || msg.getDataTable().size() == 0){
			msg.setFailMessage(new Message("","用户名或者工号不存在，无法重置密码!"));
		}else{
			msg = user.restUserPassWord(map);// 验证两次密码是否一致，密码加密，更新操作
		}
		msg.setDataTable(map);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, msg, false);
		rmHelper.setReturnParams(msg.getDataTable());
		request.setAttribute("rmHelper", rmHelper);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/usermgmt/RestUserPass");
	}
}
