package com.ca.cacore.manage.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.FuncMenuModel;
import com.ca.cacore.manage.model.bo.IFuncMenuModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.UserModel;
import com.ca.cacore.manage.webapp.service.IFuncMenuService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ThreadLocalHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 
 * @author 王得胜
 * @since 2013-11-10
 * @discrible 功能菜单Controller层
 */
@Controller
public class FuncMenuController extends BaseController{
	
	@Autowired private IFuncMenuService FuncMenuService;

	/**
	 * @author 王得胜
	 * @param 传入Request，Response 
	 * @return  返回一个ModelAndView对象
	 * @discrible 查询出所有的功能菜单model，或根据条件查询
	 */
	@RequestMapping("/FuncMenu/queryFuncMenu.do")
	public ModelAndView queryFuncMenu(HttpServletRequest req
			                        ,HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		String menu_name = ActionHelper.getNullToStr(req.getParameter("menu_name"));
		String menu_id = ActionHelper.getNullToStr(req.getParameter("menu_id"));
		String menu_type = ActionHelper.getNullToStr(req.getParameter("menu_type"));
		IFuncMenuModel model = new FuncMenuModel() ;
		model.setMenu_name(menu_name);
		model.setMenu_id(menu_id);
		model.setMenu_type(menu_type);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = FuncMenuService.queryAllFuncMenu(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		return returnPage(res, returnMsg, "ca/cacore/manage/funcmenu/funcmenuQuery");
	}
	/**
	 * @author 王得胜
	 * @param 传入Request，Response 
	 * @return  返回一个ModelAndView对象
	 * @discrible 查看一个功能菜单model详细
	 */
	@RequestMapping("/FuncMenu/viewFuncMenu.do")
	public ModelAndView viewFuncMenu(HttpServletRequest req
			                      ,HttpServletResponse res) throws Exception {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		IFuncMenuModel model = new FuncMenuModel(seq_id);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = FuncMenuService.getFuncMenu(model);
		req.setAttribute("rmHelper",  new ReturnMsgHelper(req,returnMsg));
		return new ModelAndView("ca/cacore/manage/funcmenu/funcmenuView");
	}
	/**
	 * @author 王得胜
	 * @param 传入Request，Response 
	 * @return  返回一个ModelAndView对象
	 * @discrible 添加一个功能菜单model
	 */
	@RequestMapping("/FuncMenu/addFuncMenu.do")
	public ModelAndView addFuncMenu(HttpServletRequest req
			                     ,HttpServletResponse res) throws Exception {
		String menu_id = ActionHelper.getNullToStr(req.getParameter("menu_id"));
		String menu_name = ActionHelper.getNullToStr(req.getParameter("menu_name"));
		String menu_type = ActionHelper.getNullToStr(req.getParameter("menu_type"));
		String menu_uritype = ActionHelper.getNullToStr(req.getParameter("menu_uritype"));
		String menu_uri = ActionHelper.getNullToStr(req.getParameter("menu_uri"));
		String menu_opentype = ActionHelper.getNullToStr(req.getParameter("menu_opentype"));
		String menu_dispath = ActionHelper.getNullToStr(req.getParameter("menu_dispath"));
		Integer menu_disorder = ActionHelper.getNullToInteger(req.getParameter("menu_disorder"));
		String remark = ActionHelper.getNullToStr(req.getParameter("remark"));
		IUserModel userFromSession =ActionHelper.getUserFromSession(req);
		IUserModel user = new UserModel();
		user.setUserName("王得胜");//先放个固定值
		IFuncMenuModel model = new FuncMenuModel();
		model.setMenu_id(menu_id);// 菜单代码
		model.setMenu_name(menu_name);// 菜单名称
		model.setMenu_type(menu_type);// 菜单类型
		model.setMenu_uritype(menu_uritype);// 菜单访问类型
		model.setMenu_uri(menu_uri);// 菜单访问路径
		model.setMenu_opentype(menu_opentype);// 菜单打开方式
		model.setMenu_dispath(menu_dispath);// 菜单显示路径
		model.setMenu_disorder(menu_disorder);// 菜单显示顺序
		model.setRemark(remark);
		model.setCreateUser(user.getUserName());// 创建人
		model.setModifyUser(user.getUserName());// 最后修改人
		
		model.setMenu_allPath(menu_dispath);//临时处理的
		model.setButton_assign("Y");//临时处理的
		
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = FuncMenuService.addFuncMenu(model,user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg));
		return new ModelAndView("ca/cacore/manage/funcmenu/funcmenuAdd");
	}
	/**
	 * @author 王得胜
	 * @param 传入Request，Response 
	 * @return  返回一个ModelAndView对象
	 * @discrible 删除一个功能菜单记录
	 */
	@RequestMapping("/FuncMenu/deleteFuncMenu.do")
	public ModelAndView deleteFuncMenu(HttpServletRequest req
			                        ,HttpServletResponse res) throws Exception {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		IFuncMenuModel model = new FuncMenuModel(seq_id);
		ReturnMsg returnMsg = FuncMenuService.deleteFuncMenu(model);
		return queryFuncMenu(req,res);
	}
	/**
	 * @author 王得胜
	 * @param 传入Request，Response 
	 * @return  返回一个ModelAndView对象
	 * @discrible 获取需要修改的的功能菜单model的数据，传给前台
	 */
	@RequestMapping("/FuncMenu/goModifyFuncMenu.do")
	public ModelAndView goModifyFuncMenu(HttpServletRequest req
			                          ,HttpServletResponse res) throws Exception {
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		IFuncMenuModel model = new FuncMenuModel(seq_id);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = FuncMenuService.getFuncMenu(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/manage/funcmenu/funcmenuModify");
	}
	/**
	 * @author 王得胜
	 * @param 传入Request，Response 
	 * @return  返回一个ModelAndView对象
	 * @discrible 修改的的功能菜单model的数据，更新一条数据
	 */
	@RequestMapping("/FuncMenu/modifyFuncMenu.do")
	public ModelAndView modifyFuncMenu(HttpServletRequest req
			                        ,HttpServletResponse res) throws Exception {
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		String menu_id = ActionHelper.getNullToStr(req.getParameter("menu_id"));
		String menu_name = ActionHelper.getNullToStr(req.getParameter("menu_name"));
		String menu_type = ActionHelper.getNullToStr(req.getParameter("menu_type"));
		String menu_uritype = ActionHelper.getNullToStr(req.getParameter("menu_uritype"));
		String menu_uri = ActionHelper.getNullToStr(req.getParameter("menu_uri"));
		String menu_opentype = ActionHelper.getNullToStr(req.getParameter("menu_opentype"));
		String menu_dispath = ActionHelper.getNullToStr(req.getParameter("menu_dispath"));
		Integer menu_disorder = Integer.parseInt(ActionHelper.getNullToStr(req.getParameter("menu_disorder")));
		String status = ActionHelper.getNullToStr(req.getParameter("status"));
		String remark = ActionHelper.getNullToStr(req.getParameter("remark"));
		IUserModel userFromSession =ActionHelper.getUserFromSession(req);
		IUserModel user = new UserModel();
		user.setUserName("王得胜");//先放个固定值
		String modifyUser =user.getUserName();
	
		IFuncMenuModel model = new FuncMenuModel();
		model.setSeq_id(seq_id);
		model.setMenu_id(menu_id);// 菜单代码
		model.setMenu_name(menu_name);// 菜单名称
		model.setMenu_type(menu_type);// 菜单类型
		model.setMenu_uritype(menu_uritype);// 菜单访问类型
		model.setMenu_uri(menu_uri);// 菜单访问路径
		model.setMenu_opentype(menu_opentype);// 菜单打开方式
		model.setMenu_dispath(menu_dispath);// 菜单显示路径
		model.setMenu_disorder(menu_disorder);// 菜单显示顺序
		model.setStatus(status);//状态
		model.setRemark(remark); // 备注
		model.setModifyUser(modifyUser);// 最后修改人
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = FuncMenuService.updateFuncMenu(model);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		model.setPageCount(ActionHelper.getPage(req));
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));
		return new ModelAndView("ca/cacore/manage/funcmenu/funcmenuModify");
	}
	/**
	 * 菜单树
	 */
	@RequestMapping("/FuncMenu/funcMenuTree.do")
	public ModelAndView funcMenuTree(HttpServletRequest req
			                        ,HttpServletResponse res) throws Exception {
		List<String> lstTree= FuncMenuService.funcMenuTree();
		res.getWriter().print(JSONArray.fromObject(lstTree).toString());
		return null;
	}
	
}
