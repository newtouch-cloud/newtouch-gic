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

import com.ca.cacore.rsss.model.vo.HoldReportVOModel;
import com.ca.cacore.rsss.webapp.service.IHoldReportService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.DateHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 
* @since:    2014年1月15日   
* @author    ZhangChen
* @description:专业保险中介机构业务人员持证情况报表controller
 */
@Controller
public class HoldReportController extends BaseController{
	@Autowired private IHoldReportService holdReportService;
	
	
	/**
	 * 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:查询专业保险中介机构业务人员持证情况报表
	 */
	@RequestMapping("/rsss/HoldReport/queryReport.do")
	public ModelAndView queryReport(HttpServletRequest req,HttpServletResponse res){
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构id
		String statistic_year = ActionHelper.getNullToStr(req.getParameter("statistic_year"));//	统计年份
		String statistic_month = ActionHelper.getNullToStr(req.getParameter("statistic_month"));//	统计月份
		String statistic_time = statistic_year+statistic_month;
		HoldReportVOModel model = new HoldReportVOModel();
		model.setBranch_id(branch_id);
		model.setStatistic_time(statistic_time);
		ReturnMsg returnMsg =holdReportService.queryReport(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,true));
		return new ModelAndView("ca/cacore/rsss/insuranceReport/holdReport"); 
		
	}
	
	/**
	 * 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:导出专业保险中介机构业务人员持证情况报表
	 */
	@RequestMapping("/rsss/HoldReport/exportReport.do")
	public ModelAndView exportReport(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构id
		String statistic_year = ActionHelper.getNullToStr(req.getParameter("statistic_year"));//	统计年份
		String statistic_month = ActionHelper.getNullToStr(req.getParameter("statistic_month"));//	统计月份
		String statistic_time = statistic_year+statistic_month;
		HoldReportVOModel model = new HoldReportVOModel();
		model.setBranch_id(branch_id);
		model.setStatistic_time(statistic_time);
		List<HoldReportVOModel> list =holdReportService.getReport(model); //获取结果集
		ExportBusinessReport excel=new ExportBusinessReport();
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		OutputStream os = res.getOutputStream(); //获取输出流
		excel.doExportHoldReport(os, list);//调用导出方法
		os.flush();
        os.close();
        os = null;
		return null;
		
	}

}
