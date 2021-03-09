package com.ca.cacore.ams.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.ams.domain.IUserMgMtDomain;
import com.ca.cacore.ams.model.vo.IUserMgMtVOModel;
import com.ca.cacore.ams.model.vo.UserMgMtVOModel;
import com.ca.cacore.ams.webapp.service.IUserMgMtService;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.usermgmt.service.IUserMgmtService;
import com.newtouch.utils.md5.Md5;

@Controller
public class UserMgMtController extends BaseController{

	@Autowired private IUserMgMtService userMgmtService;
	@Autowired private IUserMgMtDomain  userMgmtDomain;
	@Autowired private IUserMgmtService user;
	/** 
	* 
	* @param req
	* @param res
	* @throws Exception void
	* @description:用户密码到期前7天开始提示
	*/
	@RequestMapping("/AMS/userMgmtController/mimaDaoQiTiShi.do")
	public void mimaDaoQiTiShi(HttpServletRequest req,HttpServletResponse res)
		throws Exception{
		//获取当前用户姓名
		IUserModel usermodel=ServletHelper.getSessionUser(req);
		String emp_id=usermodel.getEmp_id();
		IUserMgMtVOModel model=new UserMgMtVOModel();
		model.setOpt_name(emp_id);
		
		//当前时间+7天
		Date date=new Date();
		GregorianCalendar gc =new GregorianCalendar();
		SimpleDateFormat sf  =new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf  =new SimpleDateFormat("yyyy-MM-dd");
		gc.setTime(date);
		gc.add(5,+7);//时间+7的方法，5-代表天,1-代表年，2-代表月，3-代表周
		gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
		Date now = sdf.parse( sf.format(gc.getTime()));

		//查询当前用户的信息
		IUserMgMtVOModel ummv = userMgmtService.queryOptPathInfo(model);
		if(ummv!=null){
			//获取密码有效终止日期
			Date end_date=sdf.parse(ummv.getEnd_date());
			//如果当前时间+7天大于等于用户密码失效日期则提示消息
			if(now.getTime()>=end_date.getTime()){
				long l=now.getTime()-end_date.getTime();
				 long day=7-l/(24*60*60*1000);//到期前的天数
				res.getWriter().print(day);//将天数传给后台 跳到indexM.jsp页面
			}
		}else{
			res.getWriter().print(-1);
		}
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @throws Exception void
	* @description:首页登录-密码是否到期验证
	*/
	@RequestMapping("/AMS/userMgmtController/mimaYiDaoQi.do")
	public void mimaYiDaoQi(HttpServletRequest req,HttpServletResponse res)
		throws Exception{
		//获取当前用户姓名
		IUserModel usermodel=ServletHelper.getSessionUser(req);
		String emp_id=usermodel.getEmp_id();
		IUserMgMtVOModel model=new UserMgMtVOModel();
		model.setOpt_name(emp_id);
		String aoo=userMgmtService.checkPassWordIsOverDue(model);
		if(aoo!=""){
			//req.setAttribute("opt_no", opt_no);
			//req.setAttribute("opt_password", Map.get("opt_password").toString());
			//req.setAttribute("message", message);
			//return new ModelAndView("ca/cacore/login");
			res.getWriter().print("1");
		}
		
	}
	
	/** 
	* 
	* @param request
	* @param response
	* @return ModelAndView
	* @description:跳出登录-异常登录专用
	*/
	@RequestMapping("/AMS/userMgmtController/toOut.controller")
	public ModelAndView toOutLogin(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("/newtouch/core/rightsmgmt/usermgmt/ToOutLogin");
	}
	
	/** 
	* 
	* @param request
	* @param response
	* @return ModelAndView
	* @description:登陆页面-修改密码-1.跳转
	*/
	@RequestMapping("/AMS/userMgmtController/toModifyPassWordPage.controller")
	public ModelAndView toModifyPassWordPage(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = this.getRequestMap(request);
		Object opt_no=map.get("opt_no");
        IUserMgMtVOModel model=new UserMgMtVOModel();
		model.setOpt_no(opt_no.toString());
		ReturnMsg msg = userMgmtService.queryUserInfo(model);
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(request,msg,true);
		rmHelper.setReturnParams(msg.getDataTable());
		request.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("/newtouch/core/rightsmgmt/usermgmt/MdfUserPass");
	}
	
	/** 
	* 
	* @param request
	* @param response
	* @return ModelAndView
	 * @throws Exception 
	* @description:登陆页面-修改密码-2.检验新密码是否与原密码
	*/
	@RequestMapping("/AMS/userMgmtController/checkRepeatPassWord.controller")
	public void checkRepeatPassWord(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String opt_password=ActionHelper.getNullToStr(req.getParameter("opt_password"));
		String opt_no=ActionHelper.getNullToStr(req.getParameter("opt_no"));
		String password=Md5.EncoderPwdByMd5(opt_password);
		IUserMgMtVOModel model=new UserMgMtVOModel();
		model.setOpt_no(opt_no.toString());
		IUserMgMtVOModel mgt = userMgmtDomain.queryUserInfo(model);
		if(mgt!=null){
			String opt_passwordNew=mgt.getOpt_password();
			if(password.equals(opt_passwordNew)){
				res.getWriter().print("1");
			}else{
				res.getWriter().print("2");
			}
			
		}
	}
	
	/** 
	* 
	* @param request
	* @param response
	* @return ModelAndView
	* @description:登陆页面-修改密码-3.密码修改保存
	*/
	@RequestMapping("/AMS/userMgmtController/mdfUserPass.controller")
	public ModelAndView mdfUserPass(HttpServletRequest request,
			HttpServletResponse response) {
		String opt_password=ActionHelper.getNullToStr(request.getParameter("opt_password"));
		String opt_no=ActionHelper.getNullToStr(request.getParameter("opt_no"));
		String opt_conf_password=ActionHelper.getNullToStr(request.getParameter("opt_conf_password"));
		String opt_name=ActionHelper.getNullToStr(request.getParameter("opt_name"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("opt_no", opt_no);
		map.put("opt_password", opt_password);
		map.put("opt_conf_password", opt_conf_password);
		map.put("mdf_user", opt_no);
		map.put("opt_name", opt_name);
		ReturnMsg msg=new ReturnMsg();
		Date date=new Date();
		GregorianCalendar gc =new GregorianCalendar();
		SimpleDateFormat sdf  =new SimpleDateFormat("yyyy-MM-dd");
		gc.setTime(date);
		gc.add(2,+3);//时间增加、减少的方法，1-代表年，2-代表月，3-代表周，5-代表天
		gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
		String start_date=sdf.format(date);
		String end_date=sdf.format(gc.getTimeInMillis());
		IUserMgMtVOModel model=new UserMgMtVOModel();
		model.setOpt_no(opt_no.toString());
		model.setOpt_password(Md5.EncoderPwdByMd5(opt_password.toString()));
		model.setStart_date(start_date);
		model.setEnd_date(end_date);
		model.setMdf_user(opt_no);;
		msg=userMgmtService.updatePassWord(model);//更新数据到用户登录轨迹表
		msg = user.mdfUserPassWord(map);//更新数据到用户表
		msg.setDataTable(map);
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(request,msg,false);
		rmHelper.setReturnParams(map);
		request.setAttribute("rmHelper", rmHelper);
		return returnPage(response, msg,"/newtouch/core/rightsmgmt/usermgmt/MdfUserPass");
	}
	
	/** 
	* 
	* @param request
	* @param response
	* @return ModelAndView
	* @description:跳转到首页
	*/
	@RequestMapping("/AMS/userMgmtController/toLogin.controller")
	public ModelAndView toLogin(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("ca/cacore/login");
	}
	
}
