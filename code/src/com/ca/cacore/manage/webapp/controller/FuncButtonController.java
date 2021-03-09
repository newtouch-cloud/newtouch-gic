package com.ca.cacore.manage.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.FuncButtonModel;
import com.ca.cacore.manage.model.bo.IFuncButtonModel;
import com.ca.cacore.manage.webapp.service.IFuncButtonService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.ReturnMsg;
/**
 * @author guochunhua
 * @since 2013-11-14
 * @describle 功能按钮controller层
 */
@Controller
public class FuncButtonController extends BaseController{
	/**
	 *@author guochunhua
	 *@param 传入Request，Response 
	 *@return 返回一个ModelAndView对象
	 *@discrible 查询所有的功能按钮信息
	 */
    @Autowired private IFuncButtonService funcButtonService;
	@RequestMapping("/FuncButton/queryFuncButton.do")
	public ModelAndView queryFuncButton(HttpServletRequest req
			                        ,HttpServletResponse res) throws Exception {
		String menu_name=ActionHelper.getNullToStr(req.getParameter("menu_name"));//菜单代码
		String button_id=ActionHelper.getNullToStr(req.getParameter("button_id"));//按钮id
		String status  = ActionHelper.getNullToStr(req.getParameter("status"));//状态
		
		IFuncButtonModel model=new FuncButtonModel();
		
		model.setMenu_name(menu_name); //菜单代码
		model.setButtonId(button_id);//按钮id
		model.setStatus(status);//状态
		model.setPageCount(ActionHelper.getPage(req));
		
		ReturnMsg returnMsg = funcButtonService.queryAllFuncButtonCRUD(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		return returnPage(res, returnMsg, "ca/cacore/manage/funcbutton/funcButtonQuery");
	}
	
	/**
	 *@author guochunhua
	 *@param 传入Request，Response 
	 *@return 返回一个ModelAndView对象
	 *@discrible 添加功能按钮
	 */
	@RequestMapping("/FuncButton/funcButtonAdd.do")
	public ModelAndView funcButtonAdd(HttpServletRequest req,HttpServletResponse res)throws Exception{
		String menu_id=ActionHelper.getNullToStr(req.getParameter("menu_id"));//菜单代码
		String button_id=ActionHelper.getNullToStr(req.getParameter("button_id"));//按钮id
		String button_name=ActionHelper.getNullToStr(req.getParameter("button_name"));//按钮名字
		String status=ActionHelper.getNullToStr(req.getParameter("status"));//状态
		String remark=ActionHelper.getNullToStr(req.getParameter("remark"));//备注
		String createUser=ActionHelper.getNullToStr("GCH");//创建人
		String modifyUser=ActionHelper.getNullToStr("GCH");//最后修改人
		
		IFuncButtonModel model=new FuncButtonModel();
		model.setMenu_id(menu_id);//菜单代码
		model.setButtonId(button_id);//按钮id
		model.setButtonName(button_name);//按钮名字
		model.setStatus(status);//状态
		model.setRemark(remark);//备注
		model.setCreateUser(createUser);//创建人
		model.setModifyUser(modifyUser);//最后修改人
		
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = funcButtonService.addFuncButton(model);
		}catch (BusinessException e){
			returnMsg.setDataTable(TransHelper.obj2map(model));
		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount()));
		return new ModelAndView("ca/cacore/manage/funcbutton/funcButtonAdd");
	}
	
	/**
	 *@author guochunhua
	 *@param 传入Request，Response 
	 *@return 返回一个ModelAndView对象
	 *@discrible 查询一个功能按钮的详细信息
	 */
	@RequestMapping("/FuncButton/funcButtonView.do")
	public ModelAndView funcButtonView(HttpServletRequest req,HttpServletResponse res)throws Exception{
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		IFuncButtonModel model=new FuncButtonModel(seq_id);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg =funcButtonService.getFuncButton(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/manage/funcbutton/funcButtonView");
	}
	
	/**
	 *@author guochunhua
	 *@param 传入Request，Response 
	 *@return 返回一个ModelAndView对象
	 *@discrible 删除功能按钮
	 */
	@RequestMapping("/FuncButton/funcButtonDelete.do")
	public ModelAndView funcButtonDelete(HttpServletRequest req,HttpServletResponse res)throws Exception{
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		FuncButtonModel model = new FuncButtonModel(seq_id);
		funcButtonService.deleteFuncButton(model);
		return queryFuncButton(req,res);
	} 
	
	/**
	 *@author guochunhua
	 *@param 传入Request，Response 
	 *@return 返回一个ModelAndView对象
	 *@discrible 查询一个功能按钮的信息
	 */
	@RequestMapping("/FuncButton/funcButtonGoModify.do")
	public ModelAndView funcButtonGoModify(HttpServletRequest req,HttpServletResponse res)throws Exception{
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		IFuncButtonModel model=new FuncButtonModel(seq_id);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = funcButtonService.getFuncButton(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/manage/funcbutton/funcButtonModify");
	}
	
	/**
	 *@author guochunhua
	 *@param 传入Request，Response 
	 *@return 返回一个ModelAndView对象
	 *@discrible 修改功能按钮
	 */
	@RequestMapping("/FuncButton/funcButtonModify.do")
	public ModelAndView funcButtonModify(HttpServletRequest req,HttpServletResponse res)throws Exception{
		int seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id")); //序列id
		String menu_id=ActionHelper.getNullToStr(req.getParameter("menu_id"));  //菜单代码
		String button_id=ActionHelper.getNullToStr(req.getParameter("button_id"));//按钮id
		String button_name=ActionHelper.getNullToStr(req.getParameter("button_name"));//按钮名字
		String status=ActionHelper.getNullToStr(req.getParameter("status"));//状态
		String remark=ActionHelper.getNullToStr(req.getParameter("remark"));//备注
		String modifyUser=ActionHelper.getNullToStr("马朝军");//最后修改人
		
		IFuncButtonModel model=new FuncButtonModel(seq_id);
		
		model.setMenu_id(menu_id);  //菜单代码
		model.setButtonId(button_id);//按钮id
		model.setButtonName(button_name);//按钮名字
		model.setStatus(status); //状态
		model.setRemark(remark); //备注
		model.setModifyUser(modifyUser);//最后修改人
		
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = funcButtonService.updateFuncButton(model);
		}catch (BusinessException e){
			returnMsg.setDataTable(TransHelper.obj2map(model));
		}
		model.setPageCount(ActionHelper.getPage(req));
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));
		return new ModelAndView("ca/cacore/manage/funcbutton/funcButtonModify");
	}
	
	/*@RequestMapping("/FuncButton/funcmentTree.do")
	public ModelAndView funcmentTree(HttpServletRequest req,HttpServletResponse res){
		
		return returnPage(res, null, "ca/cacore/manage/funcbutton/funcmentTree.jsp");
		
	}*/
}
