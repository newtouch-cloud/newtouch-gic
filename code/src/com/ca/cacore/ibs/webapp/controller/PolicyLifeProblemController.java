package com.ca.cacore.ibs.webapp.controller;

import java.io.IOException;
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
import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.bo.PolicyImageModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProblemModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeProblemModel;
import com.ca.cacore.ibs.webapp.service.IPolicyLifeProblemService;
import com.ca.cacore.common.CodeTypeConst;
import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
* @since:    2014年1月23日   
* @author    xxz521
* @description: 问题件的录入和管理
 */
@Controller
public class PolicyLifeProblemController extends BaseController{
	@Autowired private IPolicyLifeProblemService policyLifeProblemService;
	@Autowired private ICommonSeqDao seqDao;
	
	/**
	* @param request
	* @param response
	* @return
	* @throws Exception ModelAndView
	* @description:跳转到新契约管理的查询页面
	 */
	@RequestMapping("/CBS/PolicyLifeProblem/goPolicyLifeProblemQuery.do")
	public ModelAndView goPolicyLifeProblemQuery(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/policyLifeProblem/policyLifeProblemInfo");
	}
	
	/**
	* @param req
	* @param res
	* @return ModelAndView
	* @description:新契约问题件的管理
	 */
	@RequestMapping("/CBS/PolicyLifeProblem/queryPolicyLifeProblem.do")
	public ModelAndView queryPolicyLifeProblem(HttpServletRequest req,HttpServletResponse res){
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));//销售机构
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司机构
		String type_code=ActionHelper.getNullToStr(req.getParameter("type_code")); //问题件类型
		String status_code=ActionHelper.getNullToStr(req.getParameter("status_code"));//问题件状态
		String send_code=ActionHelper.getNullToStr(req.getParameter("send_code"));//投保单号
		
		IPolicyLifeProblemModel model=new PolicyLifeProblemModel();
		model.setBranch_id(branch_id);
		model.setInsBranch_id(insBranch_id);
		model.setType_code(type_code);
		model.setStatus_code(status_code);
		model.setSend_code(send_code);
		model.setOrigin_type_code("1");//投保单录入问题件
		
		model.setPageCount(ActionHelper.getPage(req));
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=policyLifeProblemService.queryPolicyLifeProblem(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		return returnPage(res,returnMsg,"ca/cacore/cbs/policyLifeProblem/policyLifeProblemInfo");
	}
	
	/**
	* @param request
	* @param response
	* @return
	* @throws Exception ModelAndView
	* @description: 跳转到理赔问题件的管理页面
	 */
	@RequestMapping("/CBS/PolicyLifeProblem/goClaimsProblemQuery.do")
	public ModelAndView goClaimsProblemQuery(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/policyLifeProblem/queryClaimsProblem");
	}
	
	/**
	* @param req
	* @param res
	* @return ModelAndView
	* @description:理赔问题件的管理
	 */
	@RequestMapping("/CBS/PolicyLifeProblem/queryClaimsProblem.do")
	public ModelAndView queryClaimsProblem(HttpServletRequest req,HttpServletResponse res){
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));//销售机构
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司机构
		String type_code=ActionHelper.getNullToStr(req.getParameter("type_code")); //问题件类型
		String status_code=ActionHelper.getNullToStr(req.getParameter("status_code"));//问题件状态
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code"));//保单号
		
		IPolicyLifeProblemModel model=new PolicyLifeProblemModel();
		model.setBranch_id(branch_id);
		model.setInsBranch_id(insBranch_id);
		model.setType_code(type_code);
		model.setStatus_code(status_code);
		model.setPolicy_code(policy_code);
		model.setOrigin_type_code("3");//理赔问题件
		
		model.setPageCount(ActionHelper.getPage(req));
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=policyLifeProblemService.queryConserClaiProblem(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		return returnPage(res,returnMsg,"ca/cacore/cbs/policyLifeProblem/queryClaimsProblem");
	}
	
	/**
	* @param request
	* @param response
	* @return
	* @throws Exception ModelAndView
	* @description:跳转到保全问题件的管理
	 */
	@RequestMapping("/CBS/PolicyLifeProblem/goConservationProblemQuery.do")
	public ModelAndView goConservationProblemQuery(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/policyLifeProblem/queryConservationProblem");
	}
	
	/**
	* @param req
	* @param res
	* @return ModelAndView
	* @description: 保全问题件的管理
	 */
	@RequestMapping("/CBS/PolicyLifeProblem/queryConservationProblem.do")
	public ModelAndView queryConservationProblem(HttpServletRequest req,HttpServletResponse res){
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));//销售机构
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司机构
		String type_code=ActionHelper.getNullToStr(req.getParameter("type_code")); //问题件类型
		String status_code=ActionHelper.getNullToStr(req.getParameter("status_code"));//问题件状态
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code"));//保单号
		
		IPolicyLifeProblemModel model=new PolicyLifeProblemModel();
		model.setBranch_id(branch_id);
		model.setInsBranch_id(insBranch_id);
		model.setType_code(type_code);
		model.setStatus_code(status_code);
		model.setPolicy_code(policy_code);
		model.setOrigin_type_code("2");//保全问题件
		model.setPageCount(ActionHelper.getPage(req));
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=policyLifeProblemService.queryConserClaiProblem(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		return returnPage(res,returnMsg,"ca/cacore/cbs/policyLifeProblem/queryConservationProblem");
	}
	
	/** 
	* @author     xxz
	* @description:问题件状态更新三种变更的共用
	*/
	@RequestMapping("/PolicyLifeProblem/modifyStatus.do")
	public ModelAndView modifyStatus(HttpServletRequest req,HttpServletResponse res) throws IOException{
		int seq_id=ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		String status_code=ActionHelper.getNullToStr(req.getParameter("status_code"));
		String flag=ActionHelper.getNullToStr(req.getParameter("flag"));//1 投保单状态更新  3保全 4：理赔的状态更新
		String url="";
		IPolicyLifeProblemModel model=new PolicyLifeProblemModel();
		model.setSeq_id(seq_id);
		model.setStatus_code(status_code);
		model.setFlag(flag);//标志
		IUserModel user = ActionHelper.getUserFromSession(req);
        if("1".equals(flag)){//修改状态之后的跳转-投保单状态变更
        	url="ca/cacore/cbs/policyLifeProblem/modifyStatus";
        }else{//3保全和 4理赔的状态变更
        	url="ca/cacore/cbs/policyLifeProblem/modifyStatConserClai";
        }
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=policyLifeProblemService.modifyStatus(model,user);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView(url);
	}
	
	/**
	 * 保全和理赔
	* @param req
	* @param res
	* @return
	* @throws IOException ModelAndView
	* @description::跳转到问题件信息明细页面:明细和修改之前的查询共用
	 */
	@RequestMapping("/CBS/PolicyLifeProblem/getClaimsConserProblemView.do")
	public ModelAndView getClaimsConserProblemView(HttpServletRequest req,HttpServletResponse res) throws IOException{
		int seq_id=ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		String flag=ActionHelper.getNullToStr(req.getParameter("flag"));
		IPolicyLifeProblemModel model=new PolicyLifeProblemModel(seq_id);
		model.setFlag(flag);
		String url="";
		if("1".equals(flag)){//保全问题件明细页面
			url="ca/cacore/cbs/policyLifeProblem/viewConservationProblem";
		}else if("2".equals(flag)){//理赔问题件明细页面
			url="ca/cacore/cbs/policyLifeProblem/viewClaimsProblem";
		}else{//保全和理赔问题件修改状态页面
			url="ca/cacore/cbs/policyLifeProblem/modifyStatConserClai";
		}
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=policyLifeProblemService.getConserClaiProblemInfo(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView(url);
	}
	
	/**
	* @param req
	* @param res
	* @return
	* @throws IOException ModelAndView
	* @description::查询投保单问题件明细信息
	 */
	@RequestMapping("/CBS/PolicyLifeProblem/getPolicyLifeProblemView.do")
	public ModelAndView getPolicyLifeProblemView(HttpServletRequest req,HttpServletResponse res) throws IOException{
		int seq_id=ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		IPolicyLifeProblemModel model=new PolicyLifeProblemModel(seq_id);
		String flag=ActionHelper.getNullToStr(req.getParameter("flag"));
		String url="";
		if("1".equals(flag)){//投保单问题件明细页面
			url="ca/cacore/cbs/policyLifeProblem/viewPolicyLifeProblem";
		}else{//投保单问题件修改状态页面
			url="ca/cacore/cbs/policyLifeProblem/modifyStatus";
		}
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=policyLifeProblemService.getPolicyLifeProblemInfo(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView(url);
	}
	
	/** 
	* @author     GCH
	* @param     
	* @return    
	* @description:投保单问题件录入时，查询需要带出的信息
	*/
	@RequestMapping("/PolicyLifeProblem/getInfo.do")
	public ModelAndView getInfo(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id")).trim();
		String send_code=ActionHelper.getNullToStr(req.getParameter("send_code")).trim();
		IPolicyLifeProblemModel model=new PolicyLifeProblemModel();
		model.setInsBranch_id(insBranch_id);
		model.setSend_code(send_code);
		String info="";
		IPolicyLifeProblemModel m = policyLifeProblemService.getInfo(model);
		if(m==null){
			info="{isSuccess:'false1'}";
		}else if(CodeTypeConst.POLICYLIFE_STATUS_INVILAD.equals(m.getStatus())){
			info="{isSuccess:'false2'}";
		}else{
			info="{isSuccess:'true',branch_id:'"+m.getBranch_id()+"',branch_name:'"+m.getBranch_name()+"',status:'"+m.getStatus()+"',app_name:'"+m.getApp_name()+"',policy_id:'"+m.getPolicy_id()+"'}";
		}
		List<String> list=new ArrayList<String>();
		list.add(info);
		res.getWriter().print(JSONArray.fromObject(list).toString());
		return null;
	}
	
	/**
	* @param req
	* @param res
	* @return
	* @throws IOException ModelAndView
	* @description:理赔和保全时候的异步请求
	 */
	@RequestMapping("/CBS/PolicyLifeProblem/getInfoForClaimsConser.do")
	public ModelAndView getInfoForClaimsConser(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id")).trim();
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code")).trim();
		IPolicyLifeProblemModel model=new PolicyLifeProblemModel();
		model.setInsBranch_id(insBranch_id);
		model.setPolicy_code(policy_code);
		String info="";
		IPolicyLifeProblemModel m = policyLifeProblemService.getInfoForClaimsConser(model);
		if(m==null){
			info="{isSuccess:'false1'}";
		}else if(CodeTypeConst.CONTRACTLIFE_STATUS_STOP.equals(m.getStatus())||CodeTypeConst.CONTRACTLIFE_STATUS_INVILAD.equals(m.getStatus())){
			info="{isSuccess:'false2'}";
		}else{
			info="{isSuccess:'true',branch_id:'"+m.getBranch_id()+"',branch_name:'"+m.getBranch_name()+"',app_name:'"+m.getApp_name()+"',status:'"+m.getStatus()+"',policy_id:'"+m.getPolicy_id()+"',send_code:'"+m.getSend_code()+"'}";
		}
		List<String> list=new ArrayList<String>();
		list.add(info);
		res.getWriter().print(JSONArray.fromObject(list).toString());
		return null;
	}
	
	@RequestMapping("/PolicyLifeProblem/goPolicyLifeProblemAdd.do")
	public ModelAndView goPolicyLifeProblemAdd(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		return new ModelAndView("ca/cacore/cbs/policyLifeProblem/addPolicyLifeProblem");
	}
	/** 
	* @author     GCH
	* @param     HttpServletRequest,HttpServletResponse对象
	* @return    一个ModelAndView对象
	* @description:问题件录入
	*/
	@RequestMapping("/PolicyLifeProblem/addPolicyLifeProblem.do")
	public ModelAndView addPolicyLifeProblem(HttpServletRequest req,HttpServletResponse res) throws IOException{
		IUserModel user = ActionHelper.getUserFromSession(req);
		String send_code=ActionHelper.getNullToStr(req.getParameter("send_code")); //投保单号
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code"));//保单号回显
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构代码
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司机构代码
		String type_code=ActionHelper.getNullToStr(req.getParameter("type_code")); //问题件类型
		String origin_type=ActionHelper.getNullToStr(req.getParameter("origin_type"));//问题件业务来源
		String notes=ActionHelper.getNullToStr(req.getParameter("notes")); //问题件说明
		String return_notes=ActionHelper.getNullToStr(req.getParameter("return_notes"));//问题件回馈说明
		String status=ActionHelper.getNullToStr(req.getParameter("status"));//问题件状态
		String policy_idStr=ActionHelper.getNullToStr(req.getParameter("policy_id"));//保单Id
		String flag=ActionHelper.getNullToStr(req.getParameter("flag"));
		String app_name=ActionHelper.getNullToStr(req.getParameter("app_name")); //投保人
		String branch_name=ActionHelper.getNullToStr(req.getParameter("branch_name")); //机构名称
		Long policy_id = Long.valueOf(policy_idStr);
		String bus_type="";
		//得到问题件的seq_id
		String seq_id = seqDao.queryCommonSeq("seq_id");
		//影像上传信息
		String file_ids[] = req.getParameterValues("file_id");
		
		IPolicyLifeProblemModel model=new PolicyLifeProblemModel();
		model.setSeq_id(Integer.valueOf(seq_id));
		model.setSend_code(send_code);
		model.setBranch_id(branch_id);
		model.setInsBranch_id(insBranch_id);
		model.setType_code(type_code);
		model.setOrigin_type(origin_type);
		model.setNotes(notes);
		model.setReturn_notes(return_notes);
		model.setStatus(status);
		model.setPolicy_id(policy_id);
		model.setPolicy_code(policy_code);
		model.setBranch_name(branch_name);//回显
		model.setApp_name(app_name);//回显
		String url="";
		if("1".equals(flag)){
			bus_type=CodeTypeConst.POLICY_PROBLEM_IMAGE_BUS_TYPE;//投保单问题件影像
			url="ca/cacore/cbs/policyLifeProblem/addPolicyLifeProblem";
		}else if("2".equals(flag)){
			bus_type=CodeTypeConst.CONSER_PROBLEM_IMAGE_BUS_TYPE;//保全问题件影像
			url="ca/cacore/cbs/policyLifeProblem/addConservationProblem";
		}else{
			bus_type=CodeTypeConst.CLAIM_PROBLEM_IMAGE_BUS_TYPE;//理赔问题件影像件
			url="ca/cacore/cbs/policyLifeProblem/addClaimsProblem";
		}
		//影像上传
		IPolicyImageModel policyImageModel = new PolicyImageModel();
	    if( file_ids!=null&&file_ids.length!=0){
	    	policyImageModel.setFile_ids(file_ids);
			policyImageModel.setSend_code(send_code);//投保单号
			policyImageModel.setPolicy_id(Long.valueOf(seq_id));//问题件的seq_id保证每个问题件都有唯一的影像
			policyImageModel.setBus_type(bus_type);//影像件类型
			model.setPolicyImageModel(policyImageModel);//影像上传的model
		  }
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = policyLifeProblemService.addPolicyLifeProblem(model,user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg));
		return new ModelAndView(url);
	}
}
