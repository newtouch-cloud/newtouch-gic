package com.newtouch.core.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


import net.sf.json.JSONObject;

import com.ca.cacore.ams.model.vo.IUserMgMtVOModel;
import com.ca.cacore.ams.model.vo.UserMgMtVOModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.context.SpringContext;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.log.Ulog;
import com.newtouch.utils.stringutil.StringUtil;

public class AuthFilter implements Filter {
	private static ServerBase serverBase = null;
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sid = ((HttpServletRequest) request).getSession().getId();
		IUserModel usermodel=ServletHelper.getSessionUser((HttpServletRequest) request);
		IUserMgMtVOModel umodel = new UserMgMtVOModel();
		if(usermodel.getEmp_id()!=null){
			umodel.setOpt_no(usermodel.getEmp_id());
			umodel.setSession_id(sid);
			IUserMgMtVOModel mgv = new UserMgMtVOModel();
			if (serverBase == null)
				serverBase = (ServerBase) SpringContext.getBean("serverbase");
			mgv = serverBase.queryOptPathInfo(umodel);
	/*		if(mgv == null){
				request.getRequestDispatcher("/AMS/userMgmtController/toOut.controller").forward(request, response);
				this.clearThreadLocal();
				return;
			}*/
		}
		long startTime = System.currentTimeMillis();
		Ulog.debug("调用开始：" + System.currentTimeMillis());
		if (serverBase == null)
			serverBase = (ServerBase) SpringContext.getBean("serverbase");
		// 维护页数
		PageCount pageCount = serverBase.getThreadLocal().get().getPageCount();
		User user = (User) ((HttpServletRequest) request).getSession()
				.getAttribute("CF_USER");
		if (user == null) {
			if ("AJAX".equals(serverBase.getThreadLocal().get().getRqstType())) {
				ReturnMsg msg = new ReturnMsg();
				msg.setFailMessage(new Message("", "登录超时,请重新登录."));
				response.getWriter().write(BaseController.return2Json(msg));
				this.clearThreadLocal();
				return ;
			}
			request.getRequestDispatcher("runloginOut2.controller").forward(
					request, response);
			this.clearThreadLocal();
			return ;
		}
		String menuId = StringUtil.trimStr(request.getParameter("funcName"));
		String funcID = StringUtil.trimStr(request.getParameter("funcID"));
		if (StringUtil.isNull(menuId) && StringUtil.isNull(funcID)) {// 如果没有菜单又没有按钮，则不是从页面跳转的，就登出
			// request.getRequestDispatcher("runloginOut2.controller").forward(
			// request, response);
			this.clearThreadLocal();
			// return;
		}
		if (!StringUtil.isNull(menuId)) {
			user.setMenuID(menuId);
		}
		if (!StringUtil.isNull(funcID))
			user.setFuncID(funcID);
		String row4Page = request.getParameter("row4Page");
		String nowPage = request.getParameter("nowPage");
		String hiddenForm = request.getParameter("hiddenform");
		String initPageType = StringUtil.trimStr(request
				.getParameter("InitPageType"));
		String rqstType = StringUtil.trimStr(request.getParameter("rqstType"));
		serverBase.getThreadLocal().get().setRqstType(rqstType);
		serverBase.getThreadLocal().get().setInitPage(initPageType);
		if (hiddenForm != null) {
			// 替换换行符，防止转json时报错
			hiddenForm = hiddenForm.replaceAll("\n", "");
			hiddenForm = hiddenForm.replaceAll("\r", "");
			JSONObject jsonObj = JSONObject.fromObject("{" + hiddenForm + "}");
			row4Page = StringUtil.trimStr(jsonObj.get("row4Page"));
			nowPage = StringUtil.trimStr(jsonObj.get("nowPage"));
			serverBase.getThreadLocal().get()
					.setForm(StringUtil.trimStr(hiddenForm));
		}
		serverBase.getThreadLocal().get().setUser(user);
		if (row4Page != null && !"".equals(row4Page)) {
			pageCount.setPageRows(Integer.parseInt(row4Page));
			pageCount.setRows4Page(Integer.parseInt(row4Page));
		}
		if (nowPage != null && !"".equals(row4Page))
			pageCount.setNowPage(Integer.parseInt(nowPage));
		else
			pageCount.setNowPage(1);

		try {
			chain.doFilter(request, response);
		} catch (RuntimeException e) {
			this.clearThreadLocal();
			e.printStackTrace();
		}
		if (!StringUtil.isNull(user) && !StringUtil.isNull(user.getMenuID()))
			Ulog.debug("调用结束[" + user.getMenuID() + "][" + user.getFuncID()
					+ "],本次请求用时：" + (System.currentTimeMillis() - startTime)
					/ 1000.0000 + "秒");
		else
			Ulog.debug("调用结束,本次请求用时："
					+ (System.currentTimeMillis() - startTime) / 1000.0000
					+ "秒");
		this.clearThreadLocal();
	}


	private void clearThreadLocal() {
		serverBase.getThreadLocal().get().setForm("");
		serverBase.getThreadLocal().get().getPageCount().setAllPage(0);
		serverBase.getThreadLocal().get().getPageCount().setNowPage(1);
		serverBase.getThreadLocal().get().setRqstType("");
		serverBase.getThreadLocal().get().setInitPage("");
		serverBase.getThreadLocal().get().getUser().setFuncID("");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
