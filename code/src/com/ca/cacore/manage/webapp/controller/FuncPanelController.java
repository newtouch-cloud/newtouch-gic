package com.ca.cacore.manage.webapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.vo.ISearchParamsModel;
import com.ca.cacore.manage.model.vo.SearchParamsModel;
import com.ca.cacore.manage.webapp.service.IFuncPanelService;
import com.newtouch.component.c11assistant.ActionHelper;

/**
* @since:    2014年2月15日   
* @author    ss
* @description:自定制常用功能
*/
@Controller
public class FuncPanelController {
	@Autowired IFuncPanelService funcPanelService;
	
	@RequestMapping("/Manage/FuncPanel/getFuncPanelConfInfo.do")
	public ModelAndView getFuncPanelConfInfo(HttpServletRequest req,HttpServletResponse res){
		String emp_id=ActionHelper.getNullToStr(req.getParameter("emp_id"));
		List<String> list=funcPanelService.getFuncPanelConfInfo(emp_id);
		try {
			res.getWriter().print(JSONArray.fromObject(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/Manage/FuncPanel/getMenuInfo.do")
	public ModelAndView getMenuInfo(HttpServletRequest req,HttpServletResponse res){
		String emp_id=ActionHelper.getNullToStr(req.getParameter("emp_id"));
		String keyWord=ActionHelper.getNullToStr(req.getParameter("keyWord"));
		ISearchParamsModel model=new SearchParamsModel();
		model.setEmp_id(emp_id);
		model.setKeyWord(keyWord);
		List<String> list=funcPanelService.getMenuInfo(model);
		try {
			res.getWriter().print(JSONArray.fromObject(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/Manage/FuncPanel/getSechInfo.do")
	public ModelAndView getSales_id(HttpServletRequest req,HttpServletResponse res){
		String emp_id=ActionHelper.getNullToStr(req.getParameter("emp_id"));
		String t_name=ActionHelper.getNullToStr(req.getParameter("t_name"));//要查询的字段名
		String table_name=ActionHelper.getNullToStr(req.getParameter("table_name"));//要查询的表名
		String keyWord=ActionHelper.getNullToStr(req.getParameter("keyWord"));
		String reg=ActionHelper.getNullToStr(req.getParameter("reg"));
		ISearchParamsModel model=new SearchParamsModel();
		model.setEmp_id(emp_id);
		model.setKeyWord(keyWord);
		model.setTable_name(table_name);
		model.setT_name(t_name);
		model.setReg(reg);
		List<String> list=funcPanelService.getSechInfo(model);
		try {
			res.getWriter().print(JSONArray.fromObject(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/Manage/FuncPanel/getManagerId.do")
	public ModelAndView getManager_id(HttpServletRequest req,HttpServletResponse res){
		String emp_id=ActionHelper.getNullToStr(req.getParameter("emp_id"));
		String t_name=ActionHelper.getNullToStr(req.getParameter("t_name"));//要查询的字段名
		String table_name=ActionHelper.getNullToStr(req.getParameter("table_name"));//要查询的表名
		String keyWord=ActionHelper.getNullToStr(req.getParameter("keyWord"));
		String reg=ActionHelper.getNullToStr(req.getParameter("reg"));
		ISearchParamsModel model=new SearchParamsModel();
		model.setEmp_id(emp_id);
		model.setKeyWord(keyWord);
		model.setTable_name(table_name);
		model.setT_name(t_name);
		model.setReg(reg);
		List<String> list=funcPanelService.getManagerId(model);
		try {
			res.getWriter().print(JSONArray.fromObject(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
