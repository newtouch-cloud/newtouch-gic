package com.newtouch.component.c6javaValidate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.newtouch.core.basecontroller.BaseController;

/**
 * 2013-12-16 
 * @author ZhangChen
 *
 */
@Controller
public class JqueryValidateController extends BaseController{


	@RequestMapping("/validate/jqueryValidate.do")
	public ModelAndView jqueryValidate(HttpServletRequest req
			                        ,HttpServletResponse res) throws Exception {
		return new ModelAndView("ca/cacore/validate/validate");
	}
	
    
}
