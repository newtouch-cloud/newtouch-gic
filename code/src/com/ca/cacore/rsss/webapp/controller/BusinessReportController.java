package com.ca.cacore.rsss.webapp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.rsss.model.vo.BusinessReportVOModel1;
import com.ca.cacore.rsss.model.vo.BusinessReportVOModel2;
import com.ca.cacore.rsss.webapp.service.IBusinessReportService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.DateHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 
* @since:    2014年1月14日   
* @author    ZhangChen
* @description:保险代理机构业务汇总表(一)保险代理机构业务汇总表(二)controller
 */
@Controller
public class BusinessReportController extends BaseController{
	@Autowired private IBusinessReportService businessReportService;
	
	/**
	 * 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:查询保险代理机构业务汇总表(一)
	 */
	@RequestMapping("/rsss/BusinessReport/queryReport1.do")
	public ModelAndView queryReport1(HttpServletRequest req,HttpServletResponse res){
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构id
		String statistic_year = ActionHelper.getNullToStr(req.getParameter("statistic_year"));//	统计年份
		String statistic_month = ActionHelper.getNullToStr(req.getParameter("statistic_month"));//	统计月份
		String statistic_time = statistic_year+statistic_month;
		BusinessReportVOModel1 model = new BusinessReportVOModel1();
		model.setBranch_id(branch_id);
		model.setStatistic_time(statistic_time);
		ReturnMsg returnMsg =businessReportService.queryReport1(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,true));
		return new ModelAndView("ca/cacore/rsss/insuranceReport/businessReport1"); 
		
	}
	
	/**
	 * 
	* 
	* @param req
	* @param res
	* @return
	* @throws IOException
	* @throws ServletException ModelAndView
	* @description: 导出保险代理机构业务汇总表(一)
	 */
	@RequestMapping("/rsss/BusinessReport/exportReport1.do")
	public ModelAndView exportReport1(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构id
		String statistic_year = ActionHelper.getNullToStr(req.getParameter("statistic_year"));//	统计年份
		String statistic_month = ActionHelper.getNullToStr(req.getParameter("statistic_month"));//	统计月份
		String statistic_time = statistic_year+statistic_month;
		BusinessReportVOModel1 model = new BusinessReportVOModel1();
		model.setBranch_id(branch_id);
		model.setStatistic_time(statistic_time);
		List<BusinessReportVOModel1> list =businessReportService.getReport1(model);
		ExportBusinessReport excel=new ExportBusinessReport();
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		OutputStream os = res.getOutputStream(); //获取输出流
		excel.doExportBusinessReport1(os, list); //调用导出方法
		
		os.flush();
        os.close();
        os = null;
		
		return null;
		
	}	
	
	/**
	 * 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:查询保险代理机构业务汇总表(二)
	 */
	@RequestMapping("/rsss/BusinessReport/queryReport2.do")
	public ModelAndView queryReport2(HttpServletRequest req,HttpServletResponse res){
		String statistic_year = ActionHelper.getNullToStr(req.getParameter("statistic_year"));//	统计年份
		String statistic_month = ActionHelper.getNullToStr(req.getParameter("statistic_month"));//	统计月份
		String statistic_time = statistic_year+statistic_month;
		BusinessReportVOModel2 model = new BusinessReportVOModel2();
		model.setStatistic_time(statistic_time);
		ReturnMsg returnMsg =businessReportService.queryReport2(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg,true);
		rmHelper.setReturnParams(returnMsg.getDataTable());  //把ReturnMsg中的数据放入ReturnMsgHelper的ReturnParams中
		req.setAttribute("rmHelper",rmHelper);
		return new ModelAndView("ca/cacore/rsss/insuranceReport/businessReport2");
		
	}
	/**
	 * 
	* 
	* @param req
	* @param res
	* @return
	* @throws IOException
	* @throws ServletException ModelAndView
	* @description: 导出保险代理机构业务汇总表(二)
	 */
	@RequestMapping("/rsss/BusinessReport/exportReport2.do")
	public ModelAndView exportReport2(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{		
		String statistic_year = ActionHelper.getNullToStr(req.getParameter("statistic_year"));//	统计年份
		String statistic_month = ActionHelper.getNullToStr(req.getParameter("statistic_month"));//	统计月份
		String statistic_time = statistic_year+statistic_month;
		BusinessReportVOModel2 model = new BusinessReportVOModel2();
		model.setStatistic_time(statistic_time);
		List<BusinessReportVOModel2> list =businessReportService.getReport2(model);
		ExportBusinessReport excel=new ExportBusinessReport();
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		OutputStream os = res.getOutputStream(); //获取输出流
		excel.doExportBusinessReport2(os, list); //调用导出方法
		
		os.flush();
        os.close();
        os = null;
		
		return null;
		
	}

}
