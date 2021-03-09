package com.newtouch.component.c11assistant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.core.basecontroller.BaseController;

@Controller
public class RedirectController extends BaseController{
	
	@RequestMapping("/redirect/redirect.do")
	public ModelAndView redirect(HttpServletRequest req
			                     ,HttpServletResponse res) throws Exception {
		String linkUrl = ActionHelper.getNullToStr(req.getParameter("linkUrl"));
		return new ModelAndView(linkUrl);
	}
	
}
