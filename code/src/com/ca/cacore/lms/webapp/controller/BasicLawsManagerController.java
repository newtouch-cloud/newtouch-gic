package com.ca.cacore.lms.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.lms.model.vo.BasicLawsVOModel;
import com.ca.cacore.lms.model.vo.IBasicLawsVOModel;
import com.ca.cacore.lms.webapp.service.IBasicLawsManagerService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;

/**
* @since:    2014年3月3日   
* @author    ss
* @description:基本法维护管理
*/
@Controller
public class BasicLawsManagerController extends BaseController  {
	@Autowired private IBasicLawsManagerService basicLawsManagerService;
	/** 
	* 基本法信息主查询
	* @param req
	* @param res
	* @return ModelAndView
	* @description:
	*/
	@RequestMapping("/LMS/BasicLawsManager/getAllBasicLawsInfo.do")
	public ModelAndView getAllBasicLawsInfo(HttpServletRequest req,HttpServletResponse res){
		IBasicLawsVOModel model=new BasicLawsVOModel();
		//获取查询条件
		model.setDept_type(ActionHelper.getNullToStr(req.getParameter("dept_type")));
		model.setBasiclaw_no(ActionHelper.getNullToStr(req.getParameter("basiclaw_no")));
		model.setImpmeansver_name(ActionHelper.getNullToStr(req.getParameter("impmeansver_name")));
		model.setPageCount(ActionHelper.getPage(req));
		//获取查询结果集
		ReturnMsg returnMsg=basicLawsManagerService.getAllBasicLawsInfo(model);
		//设置返回信息
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		return returnPage(res, returnMsg, "ca/cacore/lms/lawsInfo/basicLawsInfo");
	}
	
	@RequestMapping("/LMS/BasicLawsManager/addBasicLaws.do")
	public ModelAndView addBasicLaws(HttpServletRequest req,HttpServletResponse res){
		IUserModel user=ServletHelper.getSessionUser(req);
		IBasicLawsVOModel model=new BasicLawsVOModel();
		//获取新增信息
		model.setDept_type(ActionHelper.getNullToStr(req.getParameter("dept_type")));
		model.setImpmeansver_name(ActionHelper.getNullToStr(req.getParameter("impmeansver_name")));
		model.setStart_date(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("start_date"))));
		model.setEnd_date(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("end_date"))));
		model.setIss_date(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("iss_date"))));
		model.setData_flag(ActionHelper.getNullToStr(req.getParameter("data_flag")));
		ReturnMsg returnMsg=new ReturnMsg();
		try {
			returnMsg = basicLawsManagerService.addBasicLaws(model,user);
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));
		//跳转到页面
		return new ModelAndView("ca/cacore/lms/lawsInfo/basicLawsAdd");
	}
	
	@RequestMapping("/LMS/BasicLawsManager/getBasiclawView.do")
	public ModelAndView getBasiclawView(HttpServletRequest req,HttpServletResponse res){
		IBasicLawsVOModel model=new BasicLawsVOModel();
		//获取查询参数
		model.setSerno(ActionHelper.getNullToStr(req.getParameter("serno")));
		//获得查询结果
		ReturnMsg returnMsg = basicLawsManagerService.getBasiclawView(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		//将查询结果的数据放到返回对象中
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		//跳转到页面
		return new ModelAndView("ca/cacore/lms/lawsInfo/basicLawsMod");
	}
	
	@RequestMapping("/LMS/BasicLawsManager/modBasicLaws.do")
	public ModelAndView modBasicLaws(HttpServletRequest req,HttpServletResponse res){
		IUserModel user=ServletHelper.getSessionUser(req);
		IBasicLawsVOModel model=new BasicLawsVOModel();
		//获取新增信息
		model.setDept_type(ActionHelper.getNullToStr(req.getParameter("dept_type")));
		model.setImpmeansver_name(ActionHelper.getNullToStr(req.getParameter("impmeansver_name")));
		model.setStart_date(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("start_date"))));
		model.setEnd_date(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("end_date"))));
		model.setIss_date(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("iss_date"))));
		model.setData_flag(ActionHelper.getNullToStr(req.getParameter("data_flag")));
		model.setSerno(ActionHelper.getNullToStr(req.getParameter("serno")));
		ReturnMsg returnMsg=new ReturnMsg();
		try {
			returnMsg = basicLawsManagerService.modBasicLaws(model,user);
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));
		//跳转到页面
		return new ModelAndView("ca/cacore/lms/lawsInfo/basicLawsMod");
	}
	
	@RequestMapping("/LMS/BasicLawsManager/delBasicLaws.do")
	public ModelAndView delBasicLaws(HttpServletRequest req,HttpServletResponse res){
		String serno=ActionHelper.getNullToStr(req.getParameter("serno"));
		ReturnMsg returnMsg=new ReturnMsg();
		try {
			returnMsg = basicLawsManagerService.delBasicLaws(serno);
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		req.setAttribute("rmHelper1", new ReturnMsgHelper(req,returnMsg));
		//跳转到页面
		return getAllBasicLawsInfo(req,res);
	}
}
