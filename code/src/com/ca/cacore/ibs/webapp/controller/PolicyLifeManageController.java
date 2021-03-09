package com.ca.cacore.ibs.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeVOModel;
import com.ca.cacore.ibs.webapp.service.IPolicyLifeManageService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 投保单管理模块
 * @author xxz
 *@since 2013/12/15
 */
@Controller
public class PolicyLifeManageController extends BaseController{
	@Autowired private IPolicyLifeManageService policyLifeManageService;
	
	@RequestMapping("/cbs/policyLifeManage/goPolicylifeQuery.do")
	public ModelAndView goPolicylifeQuery(HttpServletRequest req,HttpServletResponse res) throws Exception {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/policyLifeManage/PolicyLifeQuery");
	}
	/**
	 * 查询所有的投保单（不包括待核保）：跳转到投保单明细的查询页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cbs/policyLifeManage/queryPolicyLife.do")
	public ModelAndView queryPolicyLife(HttpServletRequest request,HttpServletResponse response){
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//销售机构
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//所属公司机构
		String send_code=ActionHelper.getNullToStr(request.getParameter("send_code")).trim();//投保单号
		String agent_name=ActionHelper.getNullToStr(request.getParameter("agent_name"));//保单销售人员姓名
		String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人员姓名
		 
		IPolicyLifeVOModel pl = new PolicyLifeVOModel();
		pl.setBranch_id(branch_id);//机构
		pl.setInsBranch_id(insBranch_id);//保险公司机构
		pl.setSend_code(send_code);//投保单号
		pl.setAgent_name(agent_name);//销售人姓名
		pl.setApp_name(app_name);//投保人姓名
		pl.setPageCount(ActionHelper.getPage(request));
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyLifeManageService.queryAllPolicyLife(pl);
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,pl.getPageCount(),true));
		String url = "ca/cacore/cbs/policyLifeManage/PolicyLifeQuery";
		return returnPage(response, returnMsg, url);
	}
	
	/**
	 * 跳转到投保单修改页面
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/policyLifeManage/goPolicyLifeForModifyQuery.do")
	public ModelAndView goPolicyLifeForModifyQuery(HttpServletRequest req,HttpServletResponse res) throws Exception {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/policyLifeManage/PolicyLifeQueryForModify");
	}
	/**
	 * 查询所有的投保单（不包括待核保）：跳转到投保单修改的查询页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cbs/policyLifeManage/queryPolicyLifeForModify.do")
	public ModelAndView queryPolicyLifeForModify(HttpServletRequest request,HttpServletResponse response){
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//销售机构
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//所属公司机构
		String send_code=ActionHelper.getNullToStr(request.getParameter("send_code"));//投保单号
		String status=ActionHelper.getNullToStr(request.getParameter("status"));//保单状态
		String agent_name=ActionHelper.getNullToStr(request.getParameter("agent_name"));//保单销售人员姓名
		String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人员姓名
		 
		IPolicyLifeVOModel pl = new PolicyLifeVOModel();
		pl.setBranch_id(branch_id);//机构
		pl.setInsBranch_id(insBranch_id);//保险公司机构
		pl.setSend_code(send_code);//投保单号
		pl.setStatus(status);//投保单状态
		pl.setAgent_name(agent_name);//销售人姓名
		pl.setApp_name(app_name);//投保人姓名
		pl.setPageCount(ActionHelper.getPage(request));
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyLifeManageService.queryPolicyLifeModify(pl);
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,pl.getPageCount(),true));
		String url= "ca/cacore/cbs/policyLifeManage/PolicyLifeQueryForModify";
		return returnPage(response, returnMsg, url);
	}
	

	@RequestMapping("/cbs/policyLifeManage/goPolicyLifeForStatusQuery.do")
	public ModelAndView goPolicyLifeForStatusQuery(HttpServletRequest req,HttpServletResponse res) throws Exception {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/policyLifeManage/policyLifeStatusQuery");
	}
	/**
	 * 查询所有的投保单：跳转到投保单状态修改的查询页面
	 * 约束：首期待承保的投保单不允许显示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cbs/policyLifeManage/queryPolicyLifeForStatus.do")
	public ModelAndView queryPolicyLifeForStatus(HttpServletRequest request,HttpServletResponse response){
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//销售机构
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//所属公司机构
		String send_code=ActionHelper.getNullToStr(request.getParameter("send_code"));//投保单号
		String status=ActionHelper.getNullToStr(request.getParameter("status"));//投保单状态
		String agent_name=ActionHelper.getNullToStr(request.getParameter("agent_name"));//保单销售人员姓名
		String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人员姓名
		 
		IPolicyLifeVOModel pl = new PolicyLifeVOModel();
		pl.setBranch_id(branch_id);//机构代码
		pl.setInsBranch_id(insBranch_id);//保险公司机构
		pl.setSend_code(send_code);//投保单号
		pl.setStatus(status);//投保单状态
		pl.setAgent_name(agent_name);//销售人姓名
		pl.setApp_name(app_name);//投保人姓名
		pl.setPageCount(ActionHelper.getPage(request));
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyLifeManageService.querySomePolicyLife(pl);//查询所有的投保单
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,pl.getPageCount(),true));
		String url= "ca/cacore/cbs/policyLifeManage/policyLifeStatusQuery";
		return returnPage(response, returnMsg, url);
	}
	
	/**
	 * 根据seq_id查询投保单明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cbs/policyLifeManage/viewPolicyLife.do")
	public ModelAndView viewPolicyLife(HttpServletRequest request,HttpServletResponse response){
		Integer seq_id=ActionHelper.getNullToInteger(request.getParameter("seq_id"));//投保单的seq_id
		String flag=ActionHelper.getNullToStr(request.getParameter("flag"));//修改和明细页面标志：
		IPolicyLifeVOModel pl = new PolicyLifeVOModel();
		pl.setSeq_id(seq_id);//投保单的seq_id
		ReturnMsg returnMsg = policyLifeManageService.getPolicyLifeView(pl);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request,returnMsg);
		request.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		String url="";
		if ("1".equals(flag)) {//1 明细页面
			url = "ca/cacore/cbs/policyLifeManage/policyLifeView";
		} else if("2".equals(flag)) {//修改页面
			url = "ca/cacore/cbs/policyLifeManage/policyLifeModify";//投保单的维护
		}else{
			url ="ca/cacore/cbs/policyLifeManage/policyLifeStatusModify";//投保单状态变更
		}
		return new ModelAndView(url);
	}
	
	/**
	 *修改投保单的状态
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cbs/policyLifeManage/plfStatusModify.do")
	public ModelAndView plfStatusModify(HttpServletRequest request,HttpServletResponse response){
		IUserModel user = ActionHelper.getUserFromSession(request);//从session中获取
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//所属公司机构
		String send_code=ActionHelper.getNullToStr(request.getParameter("send_code"));//投保单号
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//销售机构
		String branch_name=ActionHelper.getNullToStr(request.getParameter("branch_name"));//销售机构名称
		String bef_status=ActionHelper.getNullToStr(request.getParameter("bef_status"));//变更前的投保单状态
		String status=ActionHelper.getNullToStr(request.getParameter("aft_status"));//投保单状态
		String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人姓名
		String policy_id=ActionHelper.getNullToStr(request.getParameter("policy_id"));//保单id
		
		IPolicyLifeVOModel pl = new PolicyLifeVOModel();
		pl.setBef_status(bef_status);
		pl.setAft_status(status);
		if(!"".equals(policy_id)){
			pl.setPolicy_id(Long.valueOf(policy_id));
		}
		pl.setInsBranch_id(insBranch_id);
		pl.setSend_code(send_code);
		pl.setBranch_id(branch_id);
		pl.setBranch_name(branch_name);//机构名称
		pl.setStatus(status);//变更后的状态
		pl.setApp_name(app_name);//投保人姓名
		
		ReturnMsg returnMsg = policyLifeManageService.modifyPolicyLifeStatus(pl,user);//修改状态
		pl.setResult_flag("true");
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		request.setAttribute("rmHelper",rmHelper);
		return new ModelAndView("ca/cacore/cbs/policyLifeManage/policyLifeStatusModify");
	}
	
	
}
