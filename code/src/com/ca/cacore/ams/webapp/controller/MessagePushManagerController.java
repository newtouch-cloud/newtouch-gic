package com.ca.cacore.ams.webapp.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.ams.model.vo.IMessagePushVOModel;
import com.ca.cacore.ams.model.vo.MessagePushVOModel;
import com.ca.cacore.ams.webapp.service.IMessagePushManagerService;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;

@Controller
public class MessagePushManagerController extends BaseController{
	@Autowired private IMessagePushManagerService messagePushManagerService;
	
	/**
	 * 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @description:跳转到消息推送页面
	 */
	@RequestMapping("/AMS/MessagePushManagerController/toQueryMessagePush.do")
	public ModelAndView toQueryMessagePush(HttpServletRequest req,HttpServletResponse res) throws BusinessException{
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/ams/messagePushManager/messagePushManagerQuery");
	}
	
	/**
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @description:根据条件查询
	 */
	@RequestMapping("/AMS/MessagePushManagerController/queryMessagePush.do")
	public ModelAndView queryMessagePush(HttpServletRequest req,HttpServletResponse res) throws BusinessException{
		
		String task_type=ActionHelper.getNullToStr(req.getParameter("task_type"));
		String task_name=ActionHelper.getNullToStr(req.getParameter("task_name"));
		
		IMessagePushVOModel model=new MessagePushVOModel();
		model.setTask_type(task_type);
		model.setTask_name(task_name);
		model.setPageCount(ActionHelper.getPage(req));
		
		ReturnMsg returnMsg=messagePushManagerService.queryMessagePush(model);	
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true);
	   
		req.setAttribute("rmHelper",rmHelper);
		return returnPage(res, returnMsg,"ca/cacore/ams/messagePushManager/messagePushManagerQuery");
	}
	
	/**
	 * 
	 * @param req
	 * @param res
	 * @return 
	 * @description:跳转到修改页面
	 */
	@RequestMapping("/AMS/MessagePushManagerController/messagePushModify.do")
	public ModelAndView messagePushModify(HttpServletRequest req,HttpServletResponse res) throws BusinessException{
		
		String taskid=ActionHelper.getNullToStr(req.getParameter("taskid"));
		IMessagePushVOModel model=new MessagePushVOModel();
		model.setTaskid(taskid);
		ReturnMsg returnMsg=messagePushManagerService.queryMessagePushByTaskId(model);
		ReturnMsgHelper rmHelper =  new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper", rmHelper);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		
		return returnPage(res, returnMsg,"ca/cacore/ams/messagePushManager/messagePushManagerModify");
	}
	
	@RequestMapping("/AMS/MessagePushManagerController/saveMessagePushModify.do")
	public ModelAndView saveMessagePushModify(HttpServletRequest req,HttpServletResponse res)throws BusinessException{
		String taskid=ActionHelper.getNullToStr(req.getParameter("taskid"));
		String task_title=ActionHelper.getNullToStr(req.getParameter("task_title"));
		String task_proce_date=ActionHelper.getNullToStr(req.getParameter("task_proce_date"));
		String task_failure=ActionHelper.getNullToStr(req.getParameter("task_failure"));
		String task_content=ActionHelper.getNullToStr(req.getParameter("task_content"));
		String task_object_id=ActionHelper.getNullToStr(req.getParameter("task_object_id"));
		String task_sales_id=ActionHelper.getNullToStr(req.getParameter("task_sales_id"));
		
		IMessagePushVOModel model=new MessagePushVOModel();
		model.setTaskid(taskid);
		model.setTask_title(task_title);
		model.setTask_proce_date(Date.valueOf(task_proce_date));
		model.setTask_failure(Date.valueOf(task_failure));
		model.setTask_content(task_content);
		model.setTask_object_id(task_object_id);
		model.setTask_sales_id(task_sales_id);
		
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = messagePushManagerService.saveMessagePushModify(model);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
		}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper",rmHelper);

		return returnPage(res, returnMsg,"ca/cacore/ams/messagePushManager/messagePushManagerModify");
	}
	
	@RequestMapping("/AMS/MessagePushManagerController/messagePushInfo.do")
	public ModelAndView messagePushInfo(HttpServletRequest req,HttpServletResponse res) throws BusinessException{
		String taskid=ActionHelper.getNullToStr(req.getParameter("taskid"));
		IMessagePushVOModel model=new MessagePushVOModel();
		model.setTaskid(taskid);
		ReturnMsg returnMsg=messagePushManagerService.queryMessagePushByTaskId(model);
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper", rmHelper);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		return returnPage(res, returnMsg,"ca/cacore/ams/messagePushManager/messagePushManagerInfo");
	}
	
	@RequestMapping("/AMS/MessagePushManagerController/queryMessagePushStatus.do")
	public void queryMessagePushStatus(HttpServletRequest req,HttpServletResponse res) throws Exception{
		String proce_status=ActionHelper.getNullToStr(req.getParameter("status"));
		//添加登录人代码验证，添加权限，只有本人提交的才可以看到报销的内容
		IUserModel usermodel = ServletHelper.getSessionUser(req);
		String createuser= usermodel.getEmp_id();
		
		String returnInfo="";
		if(!"".equals(proce_status)){
			IMessagePushVOModel model=new MessagePushVOModel();
			model.setProce_status(proce_status);
			model.setCreateuser(createuser);
			returnInfo=messagePushManagerService.queryMessagePushStatus(model);
		}else{
			returnInfo = "{isSuccess:false}";
		}
		res.getWriter().print(returnInfo);		
	}
	
	@RequestMapping("/AMS/MessagePushManagerController/toDealMessagePush.do")
	public ModelAndView toDealMessagePush(HttpServletRequest req,HttpServletResponse res) throws Exception{
		String taskid=ActionHelper.getNullToStr(req.getParameter("taskid"));
		IMessagePushVOModel model=new MessagePushVOModel();
		model.setTaskid(taskid);
		ReturnMsg returnMsg=messagePushManagerService.queryMessagePushByTaskId(model);
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper", rmHelper);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		return returnPage(res, returnMsg,"ca/cacore/ams/messagePushManager/dealPushManager");
	}
	@RequestMapping("/AMS/MessagePushManagerController/dealMessagePush.do")
	public ModelAndView dealMessagePush(HttpServletRequest req,HttpServletResponse res)throws BusinessException{
		String taskid=ActionHelper.getNullToStr(req.getParameter("taskid"));
		
		IMessagePushVOModel model=new MessagePushVOModel();
		model.setTaskid(taskid);
		
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = messagePushManagerService.dealMessagePush(model);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
		}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper",rmHelper);

		return returnPage(res, returnMsg,"ca/cacore/ams/messagePushManager/dealPushManager");
	}
	
	@RequestMapping("/AMS/MessagePushManagerController/toInfoMessagePush.do")
	public ModelAndView toInfoMessagePush(HttpServletRequest req,HttpServletResponse res)throws BusinessException{
		String taskid=ActionHelper.getNullToStr(req.getParameter("taskid"));
		IMessagePushVOModel model=new MessagePushVOModel();
		model.setTaskid(taskid);
		ReturnMsg returnMsg=messagePushManagerService.queryMessagePushByTaskId(model);
		ReturnMsgHelper rmHelper=new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper", rmHelper);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		return returnPage(res, returnMsg,"ca/cacore/ams/messagePushManager/toInfoMessagePush");
	}
	
	
	
}
