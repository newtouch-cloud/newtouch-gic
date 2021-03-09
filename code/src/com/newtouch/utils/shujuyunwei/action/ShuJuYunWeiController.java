package com.newtouch.utils.shujuyunwei.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.shujuyunwei.service.IShuJuYunWeiService;
import com.newtouch.utils.uniqueseq.UniqueSeq;

@Controller
public class ShuJuYunWeiController extends BaseController {
	@Autowired
	private IShuJuYunWeiService shuJuYunWei;

	@RequestMapping("/db.do")
	public ModelAndView godb(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnMsg msg = new ReturnMsg();
		Map<String, Object> paramMap = this.getRequestMap(request);
		msg.isSuccessflag();
		Long id = UniqueSeq.getUniqueSeq("dbid");
		if (paramMap.get("id").equals(id + "")) {
			return new ModelAndView("newtouch/core/db/ShuJuYunWei");
		}
		return new ModelAndView("");
	}

	@RequestMapping("/doshujuyunwei.do")
	public ModelAndView dodb(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> param = this.getJsonMap(request);
		ReturnMsg msg = shuJuYunWei.chiXinSql(param);
		return BaseController
				.query4Details("newtouch/core/db/ShuJuYunWei", msg);
	}

	@RequestMapping("/db1.do")
	public ModelAndView godb1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("newtouch/core/pub/FenYe");
	}

}
