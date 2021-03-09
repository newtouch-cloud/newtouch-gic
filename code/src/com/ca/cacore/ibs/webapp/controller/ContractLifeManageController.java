package com.ca.cacore.ibs.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.vo.ContractLifeVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeVOModel;
import com.ca.cacore.ibs.webapp.service.IContractLifeManageService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;

@Controller
public class ContractLifeManageController extends BaseController {
	@Autowired private IContractLifeManageService contractLifeManageService ;
	
	@RequestMapping("/cbs/contractLifeManage/goContractlifeQuery.do")
	public ModelAndView goContractlifeQuery(HttpServletRequest req,HttpServletResponse res) throws Exception {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/contractManage/queryContractMaster");
	}
	
	@RequestMapping("/cbs/contractLifeManage/queryContractMaster.do")
	public ModelAndView queryContractMaster(HttpServletRequest request,HttpServletResponse response){
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//销售机构
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//所属公司机构
		String policy_code=ActionHelper.getNullToStr(request.getParameter("policy_code"));//保单号
		String agent_name=ActionHelper.getNullToStr(request.getParameter("agent_name"));//保单销售人员姓名
		String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人员姓名
		 
		IContractLifeVOModel cm = new ContractLifeVOModel();
		cm.setBranch_id(branch_id);//机构
		cm.setInsBranch_id(insBranch_id);//保险公司机构
		cm.setPolicy_code(policy_code);//保单号
		cm.setAgent_name(agent_name);//销售人姓名
		cm.setApp_name(app_name);//投保人姓名
		cm.setPageCount(ActionHelper.getPage(request));

		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = contractLifeManageService.queryContractMaster(cm);
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,cm.getPageCount(),true));
		String url="ca/cacore/cbs/contractManage/queryContractMaster";
		return returnPage(response, returnMsg,url );
	}
	
	@RequestMapping("/cbs/contractLifeManage/goContractLifeForModifyQuery.do")
	public ModelAndView goContractLifeForModifyQuery(HttpServletRequest req,HttpServletResponse res) throws Exception {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/contractManage/queryContractLifeForModify");
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cbs/contractLifeManage/queryContractLifeModify.do")
	public ModelAndView queryContractLifeModify(HttpServletRequest request,HttpServletResponse response){
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//销售机构
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//所属公司机构
		String policy_code=ActionHelper.getNullToStr(request.getParameter("policy_code"));//保单号
		String status=ActionHelper.getNullToStr(request.getParameter("status"));//保单状态
		String agent_name=ActionHelper.getNullToStr(request.getParameter("agent_name"));//保单销售人员姓名
		String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人员姓名
		 
		IContractLifeVOModel cm = new ContractLifeVOModel();
	
		cm.setBranch_id(branch_id);//机构
		cm.setInsBranch_id(insBranch_id);//保险公司机构
		cm.setPolicy_code(policy_code);//保单号
		cm.setStatus(status);//保单状态
		cm.setAgent_name(agent_name);//销售人姓名
		cm.setApp_name(app_name);//投保人姓名
		cm.setPageCount(ActionHelper.getPage(request));
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = contractLifeManageService.queryCLFModify(cm);
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,cm.getPageCount(),true));
		String url= "ca/cacore/cbs/contractManage/queryContractLifeForModify";
		return returnPage(response, returnMsg,url );
	}
	
	/**
	 *  跳转到保单状态变更页面
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/contractLifeManage/goContractLifeStatusQuery.do")
	public ModelAndView goContractLifeStatusQuery(HttpServletRequest req,HttpServletResponse res) throws Exception {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/contractManage/contractLifeStatusQuery");
	}
	/**
	 * 保单状态变更查询页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cbs/contractLifeManage/queryContractLifeStatus.do")
	public ModelAndView queryContractLifeStatus(HttpServletRequest request,HttpServletResponse response){
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//销售机构
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//所属公司机构
		String policy_code=ActionHelper.getNullToStr(request.getParameter("policy_code"));//保单号
		String status=ActionHelper.getNullToStr(request.getParameter("status"));//保单状态
		String agent_name=ActionHelper.getNullToStr(request.getParameter("agent_name"));//保单销售人员姓名
		String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人员姓名
		IContractLifeVOModel cm = new ContractLifeVOModel();
	
		cm.setBranch_id(branch_id);//机构
		cm.setInsBranch_id(insBranch_id);//保险公司机构
		cm.setPolicy_code(policy_code);//保单号
		cm.setStatus(status);//保单状态
		cm.setAgent_name(agent_name);//销售人姓名
		cm.setApp_name(app_name);//投保人姓名
		cm.setPageCount(ActionHelper.getPage(request));
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = contractLifeManageService.queryCLFModifyStatus(cm);
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,cm.getPageCount(),true));
		return returnPage(response, returnMsg,"ca/cacore/cbs/contractManage/contractLifeStatusQuery" );
	}
	
	/**
	 * 根据seq_id查询保单明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cbs/contractLifeManage/viewContractLife.do")
	public ModelAndView viewContractLife(HttpServletRequest request,HttpServletResponse response){
		Integer seq_id=ActionHelper.getNullToInteger(request.getParameter("seq_id"));//投保单的seq_id
		String flag = ActionHelper.getNullToStr(request.getParameter("flag"));// modify 修改页面 否则查看页面
		String url="";
		IPolicyLifeVOModel pl = new PolicyLifeVOModel();
		pl.setSeq_id(seq_id);//投保单的seq_id
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = contractLifeManageService.getContractLifeView(seq_id);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		request.setAttribute("rmHelper",rmHelper);
		if("1".equals(flag)){
			url="ca/cacore/cbs/contractManage/contractLifeView";
		}else if("2".equals(flag)){
			url="ca/cacore/cbs/contractManage/contractLifeModify";
		}else{
			url="ca/cacore/cbs/contractManage/contractLifeStatusModify";
		}
		return new ModelAndView(url);
	}
	
	/**
	 *修改保单的状态
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cbs/contractLifeManage/clfStatusModify.do")
	public ModelAndView clfStatusModify(HttpServletRequest request,HttpServletResponse response){
		IUserModel user = ActionHelper.getUserFromSession(request);
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//所属公司机构
		String policy_code=ActionHelper.getNullToStr(request.getParameter("policy_code"));//保单号
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//销售机构
		String branch_name=ActionHelper.getNullToStr(request.getParameter("branch_name"));//销售机构名称
		String bef_status=ActionHelper.getNullToStr(request.getParameter("bef_status"));//变更前的投保单状态
		String status=ActionHelper.getNullToStr(request.getParameter("aft_status"));//保单状态
		String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人姓名
		String policy_id=ActionHelper.getNullToStr(request.getParameter("policy_id"));//保单id
		
		IContractLifeVOModel cm = new ContractLifeVOModel();
		cm.setInsBranch_id(insBranch_id);
		cm.setPolicy_code(policy_code);
		cm.setBranch_id(branch_id);
		cm.setBranch_name(branch_name);//机构名称
		cm.setStatus(status);//变更后的状态
		cm.setApp_name(app_name);//投保人姓名
		cm.setBef_status(bef_status);
		cm.setAft_status(status);
		if(!"".equals(policy_id)){
			cm.setPolicy_id(Long.valueOf(policy_id));
		}
		ReturnMsg returnMsg = contractLifeManageService.modifyContractLifeStatus(cm,user);//修改状态
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		request.setAttribute("rmHelper",rmHelper);
		return new ModelAndView("ca/cacore/cbs/contractManage/contractLifeStatusModify");
	}
}
