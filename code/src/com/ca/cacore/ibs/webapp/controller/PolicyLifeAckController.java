package com.ca.cacore.ibs.webapp.controller;

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
import com.ca.cacore.ibs.model.bo.ContractLifeModel;
import com.ca.cacore.ibs.model.bo.IContractLifeModel;
import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.bo.PolicyImageModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeAckVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeAckVOModel;
import com.ca.cacore.ibs.webapp.service.IPolicyLifeAckService;
import com.ca.cacore.common.CodeTypeConst;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;

@Controller
public class PolicyLifeAckController extends BaseController{
	@Autowired private IPolicyLifeAckService policyLifeAckService ;
	
	/**
	 * 跳转到回执录入
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/policyLifeAck/goPolicyLifeAckAdd.do")
	public ModelAndView goPolicyLifeAckAdd(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		return new ModelAndView("ca/cacore/cbs/policyLifeAck/policyLifeAckAdd");
	}
	
	/**
	 * 回执录入
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	 @RequestMapping("/cbs/policyLifeAck/addPolicyLifeAck.do")
	public ModelAndView addPolicyLifeAck(HttpServletRequest request ,HttpServletResponse response) throws Exception {
	    IUserModel user = ServletHelper.getSessionUser(request);//从session获取user信息
		String insBranch_id = ActionHelper.getNullToStr(request.getParameter("insBranch_id"));
		String branch_id = ActionHelper.getNullToStr(request.getParameter("branch_id"));
		String send_code = ActionHelper.getNullToStr(request.getParameter("send_code"));
		String policy_code = ActionHelper.getNullToStr(request.getParameter("policy_code"));
		String policy_id = ActionHelper.getNullToStr(request.getParameter("policy_id"));
		String ack_branch_dateStr = ActionHelper.getNullToStr(request.getParameter("ack_branch_date"));
		String ack_customer_dateStr  = ActionHelper.getNullToStr(request.getParameter("ack_customer_date"));
		String hold_dateStr  = ActionHelper.getNullToStr(request.getParameter("hold_date"));
		String ack_notes = ActionHelper.getNullToStr(request.getParameter("ack_notes"));
		Date ack_branch_date = DateUtil.string2Date(ack_branch_dateStr);
		Date  ack_customer_date = DateUtil.string2Date(ack_customer_dateStr);
		Date hold_date  = DateUtil.string2Date(hold_dateStr);
		String branch_name = ActionHelper.getNullToStr(request.getParameter("branch_name"));
		String app_name = ActionHelper.getNullToStr(request.getParameter("app_name"));
		String status = ActionHelper.getNullToStr(request.getParameter("status"));//投保单的状态：校验使用
		Long policy_idInt = null;
        if(!"".equals(policy_id)){
        	policy_idInt  = Long.valueOf(policy_id);
		 }
		//影像上传信息
		String file_ids[] = request.getParameterValues("file_id");
		IPolicyLifeAckVOModel pa = new PolicyLifeAckVOModel();
		IPolicyImageModel policyImageModel = new PolicyImageModel();
	    if( file_ids!=null&&file_ids.length!=0){
		    policyImageModel.setFile_ids(file_ids);
		    policyImageModel.setSend_code(send_code);//投保单号码
		    policyImageModel.setBus_type(CodeTypeConst.IMAGE_BUSTYPE_ACK);//4 保单回执影像件
		    policyImageModel.setPolicy_id(policy_idInt);
		    pa.setPolicyImageModel(policyImageModel);//影像上传
		  }
	    
		pa.setInsBranch_id(insBranch_id);//销售机构
		pa.setBranch_id(branch_id);//机构代码
		pa.setSend_code(send_code);//投保单号
		pa.setPolicy_code(policy_code);//保单号
		pa.setPolicy_id(policy_idInt);//保单id
		pa.setAck_branch_date(ack_branch_date);//公司签收回执日期
		pa.setAck_customer_date(ack_customer_date);//客户签收回执日期
		pa.setAck_notes(ack_notes);//回执情况说明
		pa.setHold_date(hold_date);//校验使用：投保日期
		pa.setAck_branch_dateStr(ack_branch_dateStr);//公司签收日期字符串
		pa.setAck_customer_dateStr(ack_customer_dateStr);//客户签收日期字符串
		pa.setHold_dateStr(hold_dateStr);//投保日期字符串
		pa.setApp_name(app_name);//回显
		pa.setBranch_name(branch_name);//回显
		pa.setStatus(status);//保单之前的回执状态---校验使用
		
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = policyLifeAckService.policyLifeAck(pa, user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
			returnMsg.setDataTable(TransHelper.obj2map(pa));
		}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg,true);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		request.setAttribute("rmHelper",rmHelper);
		return new ModelAndView("ca/cacore/cbs/policyLifeAck/policyLifeAckAdd");
	}
	
		@RequestMapping("/cbs/policyLifeAck/goPolicyLifeAckList.do")
		public ModelAndView goPolicyLifeAckList(HttpServletRequest request ,HttpServletResponse response) throws Exception {
			request.setAttribute("rmHelper",new ReturnMsgHelper(request,new ReturnMsg(),new PageCount(),true));
			return new ModelAndView("ca/cacore/cbs/policyLifeAck/policyLifeAckMaintainQuery");
		}
		/**
		 * 保单回执信息维护查询
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("/cbs/policyLifeAck/queryPolicyLifeAckList.do")
		public ModelAndView queryPolicyLifeAckList(HttpServletRequest request,HttpServletResponse response){
			String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//所属公司机构
			String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//销售机构
			String policy_code=ActionHelper.getNullToStr(request.getParameter("policy_code"));//保单号
			String agent_name=ActionHelper.getNullToStr(request.getParameter("agent_name"));//保单销售人员姓名
			String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人员姓名
			 
			IPolicyLifeAckVOModel cm = new PolicyLifeAckVOModel();
			cm.setBranch_id(branch_id);//机构
			cm.setInsBranch_id(insBranch_id);//保险公司机构
			cm.setPolicy_code(policy_code);;//保单号
			cm.setAgent_name(agent_name);//销售人姓名
			cm.setApp_name(app_name);//投保人姓名
			cm.setPageCount(ActionHelper.getPage(request));
			
			ReturnMsg returnMsg=new ReturnMsg();
			returnMsg = policyLifeAckService.queryPolicyAnswerList(cm);
			request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,cm.getPageCount(),true));
			return returnPage(response, returnMsg, "ca/cacore/cbs/policyLifeAck/policyLifeAckMaintainQuery");
		}
		
		/**
		 * 查询回执明细
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/cbs/policyLifeAck/policyLifeAckView.do")
		public ModelAndView policyLifeAckView(HttpServletRequest request ,HttpServletResponse response) throws Exception {
			IPolicyLifeAckVOModel pa = new PolicyLifeAckVOModel();
			Integer seq_id = ActionHelper.getNullToInteger(request.getParameter("seq_id"));
			pa.setSeq_id(seq_id);
			ReturnMsg returnMsg = policyLifeAckService.getPolicyLifeAckView(seq_id);
			ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
			rmHelper.setReturnParams(returnMsg.getDataTable());
			request.setAttribute("rmHelper", rmHelper);
			return new ModelAndView("ca/cacore/cbs/policyLifeAck/policyLifeAckView");
		}
		
		/**
		 * 跳转到回执信息修改页面
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/policyAnswer/goPolicyLifeAckModify.do")
		public ModelAndView goPolicyLifeAckModify(HttpServletRequest request ,HttpServletResponse response) throws Exception {
			IPolicyLifeAckVOModel pa = new PolicyLifeAckVOModel();
			Integer seq_id = ActionHelper.getNullToInteger(request.getParameter("seq_id"));
			pa.setSeq_id(seq_id);
			ReturnMsg returnMsg = policyLifeAckService.getPolicyLifeAckView(seq_id);
			ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
			rmHelper.setReturnParams(returnMsg.getDataTable());
			request.setAttribute("rmHelper", rmHelper);
			return new ModelAndView("ca/cacore/cbs/policyLifeAck/policyLifeAckModify");
		}
		
		/**
		 * 回执修改
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		 @RequestMapping("/cbs/policyLifeAck/modifyPolicyLifeAck.do")
		public ModelAndView modifyPolicyLifeAck(HttpServletRequest request ,HttpServletResponse response) throws Exception {
			IUserModel user = ActionHelper.getUserFromSession(request);//从session获取user信息
			Integer seq_id = ActionHelper.getNullToInteger(request.getParameter("seq_id"));
			String insBranch_id = ActionHelper.getNullToStr(request.getParameter("insBranch_id"));
			String branch_id = ActionHelper.getNullToStr(request.getParameter("branch_id"));
			String policy_code = ActionHelper.getNullToStr(request.getParameter("policy_code"));
			String send_code = ActionHelper.getNullToStr(request.getParameter("send_code"));//影像上传使用
			String policy_id = ActionHelper.getNullToStr(request.getParameter("policy_id"));
			String ack_branch_dateStr = ActionHelper.getNullToStr(request.getParameter("ack_branch_date"));
			String ack_customer_dateStr  = ActionHelper.getNullToStr(request.getParameter("ack_customer_date"));
			String hold_dateStr  = ActionHelper.getNullToStr(request.getParameter("hold_date"));
			String ack_notes = ActionHelper.getNullToStr(request.getParameter("ack_notes"));
			String branch_name = ActionHelper.getNullToStr(request.getParameter("branch_name"));
			String app_name = ActionHelper.getNullToStr(request.getParameter("app_name"));
			Date ack_branch_date = DateUtil.string2Date(ack_branch_dateStr);
			Date  ack_customer_date = DateUtil.string2Date(ack_customer_dateStr);
			Date hold_date  = DateUtil.string2Date(hold_dateStr);
			//影像上传信息
			String file_ids[] = request.getParameterValues("file_id");
			IPolicyImageModel policyImageModel = new PolicyImageModel();
			IPolicyLifeAckVOModel pa = new PolicyLifeAckVOModel();
			if(file_ids!=null&&file_ids.length!=0){
				    policyImageModel.setFile_ids(file_ids);
				    policyImageModel.setSend_code(send_code);
				    policyImageModel.setBus_type(CodeTypeConst.IMAGE_BUSTYPE_ACK);//4 保单回执影像件
				    policyImageModel.setPolicy_id(Long.valueOf(policy_id));
				    pa.setPolicyImageModel(policyImageModel);//影像上传
			}
			
			pa.setSeq_id(seq_id);//根据seq_id修改
			pa.setInsBranch_id(insBranch_id);//销售机构
			pa.setBranch_id(branch_id);//机构
			pa.setSend_code(send_code);//
			pa.setPolicy_code(policy_code);//保单号
			pa.setPolicy_id(Long.valueOf(policy_id));//保单id
			pa.setAck_branch_date(ack_branch_date);//公司签收回执日期
			pa.setAck_customer_date(ack_customer_date);//客户签收回执日期
			pa.setAck_notes(ack_notes);//回执情况说明
			pa.setHold_date(hold_date);//校验使用：投保日期
			pa.setAck_branch_dateStr(ack_branch_dateStr);//公司签收日期字符串
			pa.setAck_customer_dateStr(ack_customer_dateStr);//客户签收日期字符串
			pa.setHold_dateStr(hold_dateStr);//投保日期字符串
			pa.setBranch_name(branch_name);//回显
			pa.setApp_name(app_name);//回显
			
			ReturnMsg returnMsg = new ReturnMsg();
			try{
				returnMsg = policyLifeAckService.modifyPolicyLifeAck(pa, user);
			}catch (BusinessException e){
				returnMsg.setFailMessage(e.getMessage());
				returnMsg.setDataTable(TransHelper.obj2map(pa));
			}
			ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
			rmHelper.setReturnParams(returnMsg.getDataTable());
			request.setAttribute("rmHelper",rmHelper);
			return new ModelAndView("ca/cacore/cbs/policyLifeAck/policyLifeAckModify");
		}
		
	/**
	 * 回执录入时的异步请求：
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/policyLifeAck/getPolicyLifeInfo.do")
	public ModelAndView getPolicyLifeInfo(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		IContractLifeModel contractLifeModel = new ContractLifeModel();
		String insBranch_id = ActionHelper.getNullToStr(request.getParameter("insBranch_id"));
		String policy_code = ActionHelper.getNullToStr(request.getParameter("policy_code"));
		contractLifeModel.setInsBranch_id(insBranch_id);
		contractLifeModel.setPolicy_code(policy_code);
		IContractLifeModel model2 = policyLifeAckService.getContractLife(contractLifeModel);
		String info = "";
		if( model2 ==null){
			info = "{success:'false3'}";
		}else if(model2.getRtn_date()!=null){
			info = "{success:'false1'}";//已经回执
		}else if(!CodeTypeConst.CONTRACTLIFE_STATUS_WAITACK.equals(model2.getStatus())){
			info = "{success:'false2'}";//保单的状态不为：0已承保待客户回执
		}else{
		    info = "{success:'true',branch_id:'"+ActionHelper.getNullToStr(model2.getBranch_id())+"',branch_name:'"+ActionHelper.getNullToStr(model2.getBranch_name())+"',status:'"+model2.getStatus()+"',"
			+"app_name:'"+ActionHelper.getNullToStr(model2.getApp_name())+"',send_code:'"+ActionHelper.getNullToStr(model2.getSend_code())+"',hold_date:'"+ActionHelper.getNullToStr(model2.getHold_date())+"',policy_id:'"+model2.getPolicy_id()+"'}";
		}
		List<String> list=new ArrayList<String>();
		list.add(info);
		response.getWriter().print(JSONArray.fromObject(list).toString());
		return null;
	}

}
