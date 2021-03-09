package com.ca.cacore.ams.webapp.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ca.cacore.ams.webapp.service.IStatisticalDataService;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.ServletHelper;

/**
* @since:    2014年10月31日   
* @author    JYF
* @description:首页-统计数据
*/
@Controller
public class StatisticalDataController {

@Autowired private IStatisticalDataService statisticalDataService;
	
	/** 
	* 
	* @param req
	* @param res
	* @throws IOException void
	* @description:统计数据-年度
	*/
	@RequestMapping("/AMS/statisticalDataController/statisticalDataYear.controller")
	public void statisticalDataYear(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		IUserModel user=ServletHelper.getSessionUser(req);
		ServletOutputStream ops = res.getOutputStream();
		statisticalDataService.statisticalDataYear(ops,user);
		ops.flush();
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @throws IOException void
	* @description:统计数据-月度
	*/
	@RequestMapping("/AMS/statisticalDataController/statisticalDataMonth.controller")
	public void statisticalDataMonth(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		IUserModel user=ServletHelper.getSessionUser(req);
		ServletOutputStream ops = res.getOutputStream();
		statisticalDataService.statisticalDataMonth(ops,user);
		ops.flush();
	}
}
