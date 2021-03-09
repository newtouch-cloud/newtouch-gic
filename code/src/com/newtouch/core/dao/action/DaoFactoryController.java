package com.newtouch.core.dao.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.dao.factory.IDaoFactory;
import com.newtouch.utils.stringutil.StringUtil;

@Controller
public class DaoFactoryController extends BaseController {
	@Autowired()
	private IDaoFactory daoFactory = null;

	@RequestMapping("/godaopage.do")
	public ModelAndView goDaoPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("newtouch/core/db/ShengChenDao");
	}

	@RequestMapping("/dodaopage.do")
	public ModelAndView dogenerationDao(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> param = this.getRequestMap(request);
		String baseclm[] = param.get("baseClm").toString().split(",");
		for (String clm : baseclm) {
			daoFactory.addBaseClm(clm);
		}
		if (!StringUtil.isNull(param.get("tables"))) {
			String tables[] = param.get("tables").toString().split(",");
			for (String table : tables) {
				daoFactory.addTables(table);
			}
		}
		daoFactory.generationDao(StringUtil.trimStr(param.get("dbSourceType")),
				StringUtil.trimStr(param.get("daoDir")));
		return new ModelAndView("newtouch/core/db/ShengChenDao");
	}
}
