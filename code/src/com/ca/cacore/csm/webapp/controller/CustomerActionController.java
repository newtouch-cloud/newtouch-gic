package com.ca.cacore.csm.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.csm.model.bo.CustomerActionModel;
import com.ca.cacore.csm.model.bo.CustomerModel;
import com.ca.cacore.csm.model.bo.ICustomerActionModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.vo.CustomerActionVOModel;
import com.ca.cacore.csm.model.vo.CustomerViewVOModel;
import com.ca.cacore.csm.model.vo.ICustomerActionVOModel;
import com.ca.cacore.csm.webapp.service.ICustomerActionService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.ThreadLocalHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;



/**
* @since:    2013年12月24日   
* @author    newtouchlxy
* @description:客户行为信息controller
*/
@Controller
public class CustomerActionController extends BaseController{
	
	@Autowired private ICustomerActionService customerActionService;
	
	
	
	/**
	* @param req
	* @param res
	* @return
	* @throws ModelAndView
	* @description:重定向到客户行为查询界面
	 */
	@RequestMapping("/CRM/CustomerAction/toQueryAction.do")
	public ModelAndView toQueryAction(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/crm/customerAction/customerActinQuery");
	}
	
	/**
	 * 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:分页查询客户行为信息
	 */
	@RequestMapping("/CRM/CustomerAction/queryAll.do")
	public ModelAndView queryAll(HttpServletRequest req,HttpServletResponse res){
		ThreadLocalHelper.set("isOpen", true);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));  //机构id
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id")); //客户代码
		String customer_type = ActionHelper.getNullToStr(req.getParameter("customer_type"));//	客户类型
		String name = ActionHelper.getNullToStr(req.getParameter("customer_name")); //客户名称
		String gender = ActionHelper.getNullToStr(req.getParameter("gender"));// 客户性别
		String action_type = ActionHelper.getNullToStr(req.getParameter("action_type")); //行为类型
		ICustomerActionVOModel model = new CustomerActionVOModel();
		model.setBranch_id(branch_id);
		model.setName(name);
		model.setCustomer_id(customer_id);
		model.setAction_type(action_type);
		model.setGender(gender);
		model.setCustomer_type(customer_type);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = customerActionService.queryList(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		return returnPage(res, returnMsg,"ca/cacore/crm/customerAction/customerActinQuery");		
	};
	

	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	 * @throws IOException 
	* @description:客户行为增加页面通过客户id赋值操作
	*/
	@RequestMapping("/CRM/CustomerAction/queryCustomerById.do")
	public ModelAndView queryCustomerById(HttpServletRequest req,HttpServletResponse res) throws IOException{
		ThreadLocalHelper.set("isOpen", true);
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//获取客户代码
		ICustomerModel model = new CustomerModel();
		model.setCustomer_id(customer_id); 
		CustomerViewVOModel model1 = customerActionService.queryForAction(model);
		String jsonInfo = "";
		if(model1 != null){ //通过客户代码带值并使用json格式传值在前台jsp进行赋值
			jsonInfo = "{success:'true',name:'"+model1.getName()+"',"
			+"customer_type:'"+model1.getCustomer_type()+"',"
			+"gender:'"+model1.getGender()+"',branch_id:'"+model1.getBranch_id()+"',"
			+"branch_name:'"+model1.getBranch_name()+"'}";
		}else{ //如果没查到值则返回false
			jsonInfo = "{success:'false'}";
		}
		List<String> list = new ArrayList<String>();
		list.add(jsonInfo);
		PrintWriter out = res.getWriter();
		out.print(JSONArray.fromObject(list).toString());
		out.flush();
		out.close();
		return null;
	}
	

	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:增加客户行为信息
	*/
	@RequestMapping("/CRM/CustomerAction/addCustomerAction.do")
	public ModelAndView addCustomerAction(HttpServletRequest req,HttpServletResponse res){
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//	机构代码
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//	客户代码
		String action_type = ActionHelper.getNullToStr(req.getParameter("action_type"));//	行为类型
		Date action_time = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("action_time")));//	行为时间
		String action_notes = ActionHelper.getNullToStr(req.getParameter("action_notes"));//	行为描述
		ICustomerActionModel model = new CustomerActionModel();
		model.setBranch_id(branch_id);
		model.setCustomer_id(customer_id);
		model.setAction_type(action_type);
		model.setAction_time(action_time);
		model.setAction_notes(action_notes);
		IUserModel user = ServletHelper.getSessionUser(req);//获取用户
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = customerActionService.addCustomerAction(model, user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));
		return new ModelAndView("ca/cacore/crm/customerAction/customerActionAdd");
	}
	
	/**
	 * 
	* 2014-1-6 16:01张晨
	* @param req
	* @param res
	* @return ModelAndView
	* @description:用户行为明细操作方法
	 */
	@RequestMapping("/CRM/CustomerAction/goActionDetail.do")
	public ModelAndView goActionDetail(HttpServletRequest req,HttpServletResponse res){
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//	客户代码
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));  //
		ICustomerModel model = new CustomerModel();
		model.setCustomer_id(customer_id);
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg = customerActionService.queryForDetail(model);
		ReturnMsgHelper returnMsgHelper = new ReturnMsgHelper(req,returnMsg);
		returnMsgHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper",returnMsgHelper);
		
		return new ModelAndView("ca/cacore/crm/customerAction/customerActionDetail");
	}
	
	
}
