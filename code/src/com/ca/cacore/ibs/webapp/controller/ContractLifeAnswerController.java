package com.ca.cacore.ibs.webapp.controller;

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
import com.ca.cacore.ibs.model.bo.ContractLifeAnswerModel;
import com.ca.cacore.ibs.model.bo.ContractLifeModel;
import com.ca.cacore.ibs.model.bo.IContractLifeAnswerModel;
import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.vo.ContractLifeAnswerVOModel;
import com.ca.cacore.ibs.model.vo.ContractLifeVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeAnswerVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifeVOModel;
import com.ca.cacore.ibs.webapp.service.IContractLifeAnswerService;
import com.ca.cacore.common.CodeTypeConst;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 许小珍：保单回访管理录入功能
 * @author xxz521
 *
 */
@Controller
public class ContractLifeAnswerController extends BaseController{
	@Autowired private IContractLifeAnswerService contractLifeAnswerService;
	
	@RequestMapping("/policyAnswer/goPolicyAnswerAdd.do")
	public ModelAndView goPolicyAnswerAdd(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		return new ModelAndView("ca/cacore/cbs/policyAnswer/policyAnswerAdd");
	}
	
	/**
	 * 回访录入
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/policyAnswer/addPolicyAnswer.do")
	public ModelAndView addPolicyAnswer(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		IUserModel user = ActionHelper.getUserFromSession(request);
		String insBranch_id = ActionHelper.getNullToStr(request.getParameter("insBranch_id"));
		String policy_idStr = ActionHelper.getNullToStr(request.getParameter("policy_id"));
		String policy_code = ActionHelper.getNullToStr(request.getParameter("policy_code"));
		String branch_id = ActionHelper.getNullToStr(request.getParameter("branch_id"));
		String answer_type = ActionHelper.getNullToStr(request.getParameter("answer_type_code"));
		String is_success = ActionHelper.getNullToStr(request.getParameter("is_success"));
		String answer_notes = ActionHelper.getNullToStr(request.getParameter("answer_notes"));
		
		IContractLifeAnswerModel pam = new ContractLifeAnswerModel();
		pam.setInsBranch_id(insBranch_id);//销售机构
		pam.setPolicy_id(Long.valueOf(policy_idStr));//保单号
		pam.setPolicy_code(policy_code);
		pam.setBranch_id(branch_id);//机构
		pam.setAnswer_type(answer_type);//回访方式
		pam.setAnswer_notes(answer_notes);//回访说明
		pam.setIs_success(is_success);//回访是否成功
		
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = contractLifeAnswerService.addPolicyAnswer(pam,user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
			returnMsg.setDataTable(TransHelper.obj2map(pam));
		}
		request.setAttribute("rmHelper",new ReturnMsgHelper(request, returnMsg));
		return new ModelAndView("ca/cacore/cbs/policyAnswer/policyAnswerAdd");
	}
	
	
	@RequestMapping("/cbs/policyAnswer/goPolicyAnswerQuery.do")
	public ModelAndView goPolicyAnswerQuery(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/policyAnswer/policyAnswerMaintainQuery");
	}
	/**
	 * 保单回访信息维护
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/policyAnswer/queryPolicyAnswerInfo.do")
	public ModelAndView queryPolicyAnswerInfo(HttpServletRequest request,HttpServletResponse response){
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//所属公司机构
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//销售机构
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
		returnMsg = contractLifeAnswerService.queryPolicyAnswerInfo(cm);
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,cm.getPageCount(),true));
		return returnPage(response, returnMsg, "ca/cacore/cbs/policyAnswer/policyAnswerMaintainQuery");
	}
	
	/**
	 * 查询回访明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/policyAnswer/policyAnswerView.do")
	public ModelAndView policyAnswerView(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		IContractLifeAnswerVOModel model = new ContractLifeAnswerVOModel();
		Integer seq_id = ActionHelper.getNullToInteger(request.getParameter("seq_id"));
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg = contractLifeAnswerService.getPolicyAnswer(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		request.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/cbs/policyAnswer/policyAnswerView");
	}
	
	/**
	 * 跳转到回访信息修改页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/policyAnswer/goPolicyAnswerModify.do")
	public ModelAndView goPolicyAnswerModify(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		IContractLifeAnswerVOModel model = new ContractLifeAnswerVOModel();
		Integer seq_id = ActionHelper.getNullToInteger(request.getParameter("seq_id"));
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg = contractLifeAnswerService.getPolicyAnswer(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		request.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/cbs/policyAnswer/policyAnswerModify");
	}
	
	/**
	 * 修改回访信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/policyAnswer/modifyPolicyAnswer.do")
	public ModelAndView modifyPolicyAnswer(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		IUserModel user = ActionHelper.getUserFromSession(request);
		Integer seq_id = ActionHelper.getNullToInteger(request.getParameter("seq_id"));
		String insBranch_id = ActionHelper.getNullToStr(request.getParameter("insBranch_id"));
		String policy_idStr = ActionHelper.getNullToStr(request.getParameter("policy_id"));
		String policy_code = ActionHelper.getNullToStr(request.getParameter("policy_code"));
		String branch_id = ActionHelper.getNullToStr(request.getParameter("branch_id"));
		String answer_type = ActionHelper.getNullToStr(request.getParameter("answer_type_code"));
		String is_success = ActionHelper.getNullToStr(request.getParameter("is_success"));
		String answer_notes = ActionHelper.getNullToStr(request.getParameter("answer_notes"));
		String branch_name = ActionHelper.getNullToStr(request.getParameter("branch_name"));
		String app_name = ActionHelper.getNullToStr(request.getParameter("app_name"));
		
		IContractLifeAnswerVOModel pa = new ContractLifeAnswerVOModel();
		pa.setSeq_id(seq_id);
		pa.setInsBranch_id(insBranch_id);//销售机构
		pa.setPolicy_id(Long.valueOf(policy_idStr));//保单号
		pa.setPolicy_code(policy_code);//根据保单号修改
		pa.setBranch_id(branch_id);//机构
		pa.setAnswer_type(answer_type);//回访方式
		pa.setAnswer_notes(answer_notes);//回访说明
		pa.setIs_success(is_success);//回访是否成功
		pa.setBranch_name(branch_name);//回显的时候使用
		pa.setApp_name(app_name);//回显的时候使用
		
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = contractLifeAnswerService.modifyPolicyAnswer(pa, user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
			returnMsg.setDataTable(TransHelper.obj2map(pa));
		}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		request.setAttribute("rmHelper",rmHelper);
		return new ModelAndView("ca/cacore/cbs/policyAnswer/policyAnswerModify");
	}
	
	/**
	 * 回访时的异步请求：根据保险公司和保单自动带出机构和投保人信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/policyAnswer/getContractMasterInfo.do")
	public ModelAndView getContractMasterInfo(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		IContractLifeModel model = new ContractLifeModel();
		String insBranch_id = ActionHelper.getNullToStr(request.getParameter("insBranch_id"));
		String policy_code = ActionHelper.getNullToStr(request.getParameter("policy_code"));
		model.setInsBranch_id(insBranch_id);//销售机构
		model.setPolicy_code(policy_code);//保单号
		IContractLifeModel infoModel = contractLifeAnswerService.getConstractMasterInfo(model);
		String result = "";
		if(infoModel == null){
			result = "{success:'false1'}";
		}else if(CodeTypeConst.CONTRACTLIFE_IS_ANSWER_Y.equals(infoModel.getIs_answered())){
			result = "{success:'false2'}";
		}else if(!CodeTypeConst.CONTRACTLIFE_STATUS_EFFECTIVE.equals(infoModel.getStatus())){
			result = "{success:'false3'}";
		}else if(infoModel!=null&&CodeTypeConst.CONTRACTLIFE_IS_ANSWER_N.equals(infoModel.getIs_answered())){
			result = "{success:'true',branch_id:'"+ActionHelper.getNullToStr(infoModel.getBranch_id())+"',branch_name:'"+ActionHelper.getNullToStr(infoModel.getBranch_name())+"',"
					    +"app_name:'"+ActionHelper.getNullToStr(infoModel.getApp_name())+"',answered:'"+infoModel.getIs_answered()+"',policy_id:'"+infoModel.getPolicy_id()+"'}";
		}
		List<String>list = new ArrayList<String>();
		list.add(result);
		response.getWriter().print(JSONArray.fromObject(list).toString());
		return null;
	}
	
}
