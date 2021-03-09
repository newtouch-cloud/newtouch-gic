package com.newtouch.core.rightsmgmt.menumgmt.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.menumgmt.service.IMenuMgmtService;

@Controller
public class MenuMgmtController extends BaseController {

	@Autowired
	private IMenuMgmtService menuMgmtService;

	@RequestMapping("/goMenuMgmtQueryPage.do")
	public ModelAndView goMenuMgmtQueryPage(HttpServletRequest request,
			HttpServletResponse response) {
		return returnPage(response, new ReturnMsg(),
				"newtouch/core/rightsmgmt/menumgmt/QueryMenus");
	}

	@RequestMapping("/lefttreejsp.do")
	public ModelAndView goLeftTreeJsp(HttpServletRequest request,
			HttpServletResponse response) {
		List<Map<String, Object>> list = menuMgmtService.queryTree();
		ReturnMsg msg = new ReturnMsg();
		msg.setDataList(list);
		return returnPage(response, msg,
				"newtouch/core/rightsmgmt/menumgmt/LeftTree");
	}

	@RequestMapping("/addMenuMgmtInit.do")
	public ModelAndView goAddMenuMgmtJsp(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String nodename = java.net.URLDecoder.decode(
					request.getParameter("nodename"), "UTF-8");
			String nodeno = java.net.URLDecoder.decode(
					request.getParameter("nodeno"), "UTF-8");
			String nodeseq = java.net.URLDecoder.decode(
					request.getParameter("nodeseq"), "UTF-8");
			request.setAttribute("nodename", nodename);
			request.setAttribute("nodeno", nodeno);
			request.setAttribute("nodeseq", nodeseq);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnPage(response, new ReturnMsg(),
				"newtouch/core/rightsmgmt/menumgmt/AddMenu");
	}

	@RequestMapping("/addMenuMgmt.do")
	public ModelAndView addMenuMgmt(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> paramMap = getRequestMap(request);
		ReturnMsg returnMsg = menuMgmtService.insert(paramMap);
		List<Map<String, Object>> list = menuMgmtService.queryTree();
		returnMsg.setDataList(list);
		return returnPage(response, returnMsg,
				"newtouch/core/rightsmgmt/menumgmt/LeftTree");
	}

	@RequestMapping("/deleteMenuMgmt.do")
	public ModelAndView delMenuMgmt(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> paramMap = getRequestMap(request);
		ReturnMsg returnMsg = menuMgmtService.delete(paramMap);
		return returnPage(response, returnMsg,
				"newtouch/core/rightsmgmt/menumgmt/LeftTree");
	}

	@RequestMapping("/updateMenuMgmtInit.do")
	public ModelAndView goUpdateMenuMgmtJsp(HttpServletRequest request,
			HttpServletResponse response) {

		ReturnMsg returnMsg = null;
		try {
			String menu_no = java.net.URLDecoder.decode(
					request.getParameter("nodeno"), "UTF-8");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("menu_no", menu_no);
			returnMsg = menuMgmtService.query(map);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnPage(response, returnMsg,
				"newtouch/core/rightsmgmt/menumgmt/UpdateMenu");
	}

	@RequestMapping("/updateMenuMgmt.do")
	public ModelAndView updateMenuMgmt(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> paramMap = getRequestMap(request);
		ReturnMsg returnMsg = menuMgmtService.update(paramMap);
		return returnPage(response, returnMsg,
				"newtouch/core/rightsmgmt/menumgmt/LeftTree");
	}

}
