package com.ca.cacore.ibs.webapp.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.ibs.model.vo.ContractCostVOModel;
import com.ca.cacore.ibs.model.vo.IContractCostVOModel;
import com.ca.cacore.ibs.webapp.service.IContractCostService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 
* @since:    2014年1月9日   
* @author    ZhangChen
* @description:保单费用管理controller
 */
@Controller
public class ContractCostController extends BaseController{
	
	@Autowired private IContractCostService contractCostService;
	
	/**
	 * 
	* 
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception ModelAndView
	* @description:菜单导向到保单费用明细查询controller
	 */
	@RequestMapping("/CBS/contractCost/goCostQuery.do")
	public ModelAndView goCostQuery(HttpServletRequest req,HttpServletResponse res) throws Exception {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/contractCost/contractCostQuery");
	}
	
	/**
	 * 
	* 
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception ModelAndView
	* @description:菜单导向到保单费用结算查询controller
	 */
	@RequestMapping("/CBS/contractCost/goSettlementQuery.do")
	public ModelAndView goSettlementQuery(HttpServletRequest req,HttpServletResponse res){
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/contractCost/contractSettlementQuery");
	}
	
	/**
	 * 
	* 
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception ModelAndView
	* @description:保单费用明细查询controller
	 */
	@RequestMapping("/CBS/contractCost/queryContractCost.do")
	public ModelAndView queryContractCost(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		String branch_id = ActionHelper.getNullToStr(request.getParameter("branch_id"));  //机构号码
		String insBranch_id = ActionHelper.getNullToStr(request.getParameter("insBranch_id"));  //保险公司id
		String policy_code = ActionHelper.getNullToStr(request.getParameter("policy_code"));  //保单号码
		String app_name = ActionHelper.getNullToStr(request.getParameter("app_name"));  //投保人
		String agent_name = ActionHelper.getNullToStr(request.getParameter("agent_name"));  //销售人员
		String cred_id = ActionHelper.getNullToStr(request.getParameter("cred_id"));  //凭证号码
		IContractCostVOModel model = new ContractCostVOModel();
		model.setBranch_id(branch_id);
		model.setInsBranch_id(insBranch_id);
		model.setPolicy_code(policy_code);
		model.setApp_name(app_name);
		model.setAgent_name(agent_name);
		if(cred_id!=""){ //cred_id(凭证号)是查询条件之一,如果使用getNullToInteger 前台返回的是null时值为0,所以需要判断
			model.setCred_id(new Integer(cred_id));
		}
		
		model.setPageCount(ActionHelper.getPage(request));
		ReturnMsg returnMsg =contractCostService.queryContractCost(model); //获取由list转换成ReturnMsg类型的结果
	
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,model.getPageCount(),true));
		return new ModelAndView("ca/cacore/cbs/contractCost/contractCostQuery");
	}
	
	

	/**
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception ModelAndView
	* @description:保单费用结算查询通用controller
	 */
	@RequestMapping("/CBS/contractCost/querycontractSettlement.do")
	public ModelAndView queryContractSettlement(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		String branch_id = ActionHelper.getNullToStr(request.getParameter("branch_id"));  //机构号码
		String insBranch_id = ActionHelper.getNullToStr(request.getParameter("insBranch_id"));  //保险公司id
		String policy_code = ActionHelper.getNullToStr(request.getParameter("policy_code"));  //保单号码
		String app_name = ActionHelper.getNullToStr(request.getParameter("app_name"));  //投保人
		String agent_name = ActionHelper.getNullToStr(request.getParameter("agent_name"));  //销售人员
		String cred_id = ActionHelper.getNullToStr(request.getParameter("cred_id"));  //凭证号码
		String fee_status = ActionHelper.getNullToStr(request.getParameter("fee_status"));  //费用处理状态
		IContractCostVOModel model = new ContractCostVOModel();
		model.setBranch_id(branch_id);
		model.setInsBranch_id(insBranch_id);
		model.setPolicy_code(policy_code);
		model.setApp_name(app_name);
		model.setAgent_name(agent_name);
		model.setFee_status(fee_status);
		if(cred_id!=""){ //cred_id(凭证号)是查询条件之一,如果使用getNullToInteger 前台返回的是null时值为0,所以需要判断
			model.setCred_id(new Integer(cred_id));
		}
		
		model.setPageCount(ActionHelper.getPage(request));
		ReturnMsg returnMsg =contractCostService.queryContractCost(model); //获取由list转换成ReturnMsg类型的结果
	
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,model.getPageCount(),true));
		return new ModelAndView("ca/cacore/cbs/contractCost/contractSettlementQuery");
	}

	
	
}
