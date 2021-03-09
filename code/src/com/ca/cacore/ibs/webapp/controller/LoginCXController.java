package com.ca.cacore.ibs.webapp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.core.basecontroller.BaseController;


/**
* @since:    2014年1月23日   
* @author   macohaojun
* @description:登陆车险核心、收付跳转Control
*/
@Controller
public class LoginCXController extends BaseController{
	
	
	/** 
	* @author     
	* @param     
	* @return    
	* @description:登陆承保系统
	*/
	@RequestMapping("/CBS/logincb/goLogincb.do")
	public ModelAndView goLogincb(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String cxurl=ActionHelper.getNullToStr(req.getParameter("url")).trim();//承保核心地址
		String linkUrl = "ca/cacore/cbs/redirect/redirectPolicy";
		req.setAttribute("url", cxurl);
		return new ModelAndView(linkUrl);
	}
	
	/** 
	* @author     
	* @param     
	* @return    
	* @description:登陆承保系统
	*/
	@RequestMapping("/CBS/loginsf/goLoginsf.do")
	public ModelAndView goLoginsf(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String cxurl=ActionHelper.getNullToStr(req.getParameter("url")).trim();//收付系统
		String linkUrl = "ca/cacore/cbs/redirect/redirectContract";
		req.setAttribute("url", cxurl);
		return new ModelAndView(linkUrl);
	}
	
	/** 
	* @author     
	* @param     
	* @return    
	* @description:登陆办公平台
	*/
	@RequestMapping("/CBS/login/goLogin.do")
	public ModelAndView goLogin(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String cxurl=ActionHelper.getNullToStr(req.getParameter("url")).trim();
		String linkUrl = "ca/cacore/cbs/redirect/redirect";
		req.setAttribute("url", cxurl);
		return new ModelAndView(linkUrl);
	}
	
 
}
