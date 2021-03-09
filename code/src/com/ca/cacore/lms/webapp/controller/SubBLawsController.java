package com.ca.cacore.lms.webapp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.lms.model.vo.BasicLawsVOModel;
import com.ca.cacore.lms.model.vo.IBasicLawsVOModel;
import com.ca.cacore.lms.webapp.service.ISubBasicLawsManagerService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;


/**
* @since:    2014年3月10日   
* @author    wang_ds
* @description:子基本法维护管理
*/
@Controller
public class SubBLawsController extends BaseController  {
	@Autowired private ISubBasicLawsManagerService subS;
	
	/** 
	* 
	* @param req
	* @param res void
	* @description:根据基本法代码带出其他信息
	*/
	@RequestMapping("/LMS/SubLawsManager/getLawsInfo.do")
	public void getLawsInfo(HttpServletRequest req,HttpServletResponse res){
		
		String basiclaw_no  = ActionHelper.getNullToStr(req.getParameter("basiclaw_no"));
		String info = subS.getLawsInfo(basiclaw_no);
		try {
			res.getWriter().print(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:添加子基本法
	*/
	@RequestMapping("/LMS/SubLawsManager/addSubLows.do")
	public ModelAndView addSubLows(HttpServletRequest req,HttpServletResponse res){
		IUserModel user=ServletHelper.getSessionUser(req);
		IBasicLawsVOModel model=new BasicLawsVOModel();
		//获取新增信息
		model.setDept_type(ActionHelper.getNullToStr(req.getParameter("dept_type")));//渠道代码
		model.setBasiclaw_no(ActionHelper.getNullToStr(req.getParameter("basiclaw_no")));//基本法代码
		model.setImpmeans_name(ActionHelper.getNullToStr(req.getParameter("impmeans_name")));//子基本法名称
		model.setStart_date(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("sub_start_date"))));//子基本法开始日期
		model.setEnd_date(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("sub_end_date"))));//子基本法结束日期
		model.setMemo(ActionHelper.getNullToStr(req.getParameter("memo")));//备注说明
		ReturnMsg returnMsg=new ReturnMsg();
		try {
			returnMsg = subS.addSubBasicLaws(model, user);
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg));
		//跳转到页面
		return new ModelAndView("ca/cacore/lms/subLawsInfo/subBasicLawsAdd");
	}
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:进入查询页面
	*/
	@RequestMapping("/LMS/SubLawsManager/toGetSubLawsList.do")
	public ModelAndView toQueryClaimsList(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/lms/subLawsInfo/subLawsQuery");
	}
	/** 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:子基本法信息主查询
	*/
	@RequestMapping("/LMS/SubLawsManager/getSubLawsList.do")
	public ModelAndView getSubLawsList(HttpServletRequest req,HttpServletResponse res){
		IBasicLawsVOModel model=new BasicLawsVOModel();
		//获取查询条件
		model.setDept_type(ActionHelper.getNullToStr(req.getParameter("dept_type")));
		model.setBasiclaw_no(ActionHelper.getNullToStr(req.getParameter("basiclaw_no")));
		model.setImpmeansver_name(ActionHelper.getNullToStr(req.getParameter("impmeansver_name")));
		model.setImpmeans_no(ActionHelper.getNullToStr(req.getParameter("impmeans_no")));
		model.setImpmeans_name(ActionHelper.getNullToStr(req.getParameter("impmeans_name")));
		model.setPageCount(ActionHelper.getPage(req));
		//获取查询结果集
		ReturnMsg returnMsg=subS.getAllSubBasicLawsInfo(model);
		//设置返回信息
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		return returnPage(res, returnMsg, "ca/cacore/lms/subLawsInfo/subLawsQuery");
	}
	
	@RequestMapping("/LMS/SubLawsManager/goSublawForMd.do")
	public ModelAndView goSublawForMd(HttpServletRequest req,HttpServletResponse res){
		IBasicLawsVOModel model=new BasicLawsVOModel();
		//获取查询参数
		model.setSerno(ActionHelper.getNullToStr(req.getParameter("serno")));
		//获得查询结果
		ReturnMsg returnMsg = subS.getSubBasiclawView(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		//将查询结果的数据放到返回对象中
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		//跳转到页面
		return new ModelAndView("ca/cacore/lms/subLawsInfo/subLawsModify");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:修改子基本法信息
	*/
	@RequestMapping("/LMS/SubLawsManager/modifySubLaws.do")
	public ModelAndView modifySubLaws(HttpServletRequest req,HttpServletResponse res){
		IUserModel user=ServletHelper.getSessionUser(req);
		IBasicLawsVOModel model=new BasicLawsVOModel();
		//获取新增信息
		//model.setDept_type(ActionHelper.getNullToStr(req.getParameter("dept_type")));
		model.setImpmeans_name(ActionHelper.getNullToStr(req.getParameter("impmeans_name")));
		model.setStart_date(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("start_date"))));
		model.setEnd_date(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("end_date"))));
		model.setData_flag(ActionHelper.getNullToStr(req.getParameter("data_flag")));
		model.setMemo(ActionHelper.getNullToStr(req.getParameter("memo")));
		model.setSerno(ActionHelper.getNullToStr(req.getParameter("serno")));
		ReturnMsg returnMsg=new ReturnMsg();
		try {
			returnMsg = subS.modSubBasicLaws(model,user);
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));
		//跳转到页面
		return new ModelAndView("ca/cacore/lms/subLawsInfo/subLawsModify");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:删除子基本法
	*/
	@RequestMapping("/LMS/SubLawsManager/delSubLaws.do")
	public ModelAndView delSubLaws(HttpServletRequest req,HttpServletResponse res){
		String serno=ActionHelper.getNullToStr(req.getParameter("serno"));
		ReturnMsg returnMsg=new ReturnMsg();
		try {
			returnMsg = subS.delSubBasicLaws(serno);
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		req.setAttribute("rmHelper1", new ReturnMsgHelper(req,returnMsg));
		//跳转到页面
		return getSubLawsList(req,res);
	}
	
	//职级查询
	/** 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:子基本法信息主查询
	*/
	@RequestMapping("/LMS/SubLawsManager/getRankList.do")
	public ModelAndView getRankList(HttpServletRequest req,HttpServletResponse res){
		
		IBasicLawsVOModel model1=new BasicLawsVOModel();
		model1.setSerno(ActionHelper.getNullToStr(req.getParameter("serno")));
		model1.setDept_type(ActionHelper.getNullToStr(req.getParameter("dept_type")));
		model1.setImpmeans_name(ActionHelper.getNullToStr(req.getParameter("impmeans_name")));
		model1.setImpmeansver_name(ActionHelper.getNullToStr(req.getParameter("impmeansver_name")));
		ReturnMsg returnMsg1=new ReturnMsg();
		returnMsg1.setDataTable(TransHelper.obj2map(model1));
		req.setAttribute("rmHelper1", new ReturnMsgHelper(req,returnMsg1));
		
		IBasicLawsVOModel model=new BasicLawsVOModel();
		
		//获取查询条件
		model.setDept_type(ActionHelper.getNullToStr(req.getParameter("dept_type")));
		model.setBasiclaw_no(ActionHelper.getNullToStr(req.getParameter("basiclaw_no")));
		model.setImpmeansver_name(ActionHelper.getNullToStr(req.getParameter("impmeansver_name")));
		model.setImpmeans_no(ActionHelper.getNullToStr(req.getParameter("impmeans_no")));
		model.setImpmeans_name(ActionHelper.getNullToStr(req.getParameter("impmeans_name")));
		model.setPageCount(ActionHelper.getPage(req));
		//获取查询结果集
		ReturnMsg returnMsg=subS.getAllSubBasicLawsInfo(model);
		//设置返回信息
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		return returnPage(res, returnMsg, "ca/cacore/lms/subLawsInfo/subRankQuery");
	}
}
