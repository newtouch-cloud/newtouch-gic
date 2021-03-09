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
import com.ca.cacore.ibs.model.bo.ClaimsModel;
import com.ca.cacore.ibs.model.bo.IClaimsModel;
import com.ca.cacore.ibs.model.vo.ClaimsVOModel;
import com.ca.cacore.ibs.model.vo.IClaimsVOModel;
import com.ca.cacore.ibs.webapp.service.IClaimsService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;


/**
* @since:    2014年1月23日   
* @author   wang_ds
* @description:理赔
*/
@Controller
public class ClaimsController extends BaseController{
	@Autowired private IClaimsService claimsService ;
	
	
	/** 
	* @author     GCH
	* @param     
	* @return    
	* @description:问题件录入时，查询需要带出息
	*/
	@RequestMapping("/CBS/Claims/getPoliInfo.do")
	public ModelAndView getPoliInfo(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id")).trim();
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code")).trim();
		IClaimsModel cModel = new ClaimsModel();
		cModel.setInsBranch_id(insBranch_id);
		cModel.setPolicy_code(policy_code);
		String info="";
		String eventOption ="";
		IClaimsVOModel m = claimsService.getPoliInfo(cModel);
		if(m!=null){
			//取出多个被保人的信息
			List<IClaimsVOModel> voList = claimsService.getEventInfo(m.getPolicy_id());
			List<String> listOp=new ArrayList<String>();
			for(IClaimsVOModel vo : voList){
				eventOption = "{id:'"+vo.getEvent_id()+"',name:'"+vo.getEvent_name()+"'}";
				listOp.add(eventOption);
			}
			eventOption = JSONArray.fromObject(listOp).toString();
			
			info="{isSuccess:'true',policy_id:'"+m.getPolicy_id()+"',branch_id:'"+m.getBranch_id()+
				"',holder_id:'"+m.getHolder_id()+"',holder_name:'"+m.getHolder_name()
				 +"',insurant_id:'"+m.getInsurant_id()+"',insurant_name:'"+m.getInsurant_name()+"',validate_date:'"+m.getValidate_date()+"'}";
		}else{
			info="{isSuccess:'false'}";
		}
		List<String> list=new ArrayList<String>();
		list.add(info);
		res.getWriter().print(JSONArray.fromObject(list).toString()+"#"+eventOption);
		return null;
	}
	
	@RequestMapping("/CBS/Claims/getEventInfo.do")
	public ModelAndView getEventInfo(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		String policy_id=ActionHelper.getNullToStr(req.getParameter("policy_id")).trim();
		String eventOption ="";
		
		//取出多个被保人的信息
		List<IClaimsVOModel> voList = claimsService.getEventInfo(Long.valueOf(policy_id));
		List<String> listOp=new ArrayList<String>();
		for(IClaimsVOModel vo : voList){
			eventOption = "{id:'"+vo.getEvent_id()+"',name:'"+vo.getEvent_name()+"'}";
			listOp.add(eventOption);
		}
		if(listOp.size() != 0){
			eventOption = JSONArray.fromObject(listOp).toString();
		}else{
			eventOption="{isSuccess:'false'}";
			List<String> list=new ArrayList<String>();
			list.add(eventOption);
			eventOption= JSONArray.fromObject(list).toString();
		}
		res.getWriter().print(eventOption);
		return null;
	}
	
	@RequestMapping("/CBS/Claims/checkDateOrder.do")
	public ModelAndView checkDateOrder(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//销售机构
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code"));//保单号
		String event_date=ActionHelper.getNullToStr(req.getParameter("event_date"));//保单号
		
		IClaimsModel model = new ClaimsModel();
		model.setInsBranch_id(insBranch_id);
		model.setPolicy_code(policy_code);
		model.setEvent_date(DateUtil.string2Date(event_date));
		Boolean flag = claimsService.checkDateOrder(model);
		if(flag){
			res.getWriter().print("true");
		}else{
			res.getWriter().print("false");
		}
		return null;
	}
	
	@RequestMapping("/CBS/Claims/addClaims.do")
	public ModelAndView addClaims(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		
		String policy_id=ActionHelper.getNullToStr(req.getParameter("policy_id"));//保单ID
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));//销售机构
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code"));//保单号
		String holder_id=ActionHelper.getNullToStr(req.getParameter("holder_id"));//投保人
		String holder_name=ActionHelper.getNullToStr(req.getParameter("holder_name"));//投保人名称
		String insurant_id=ActionHelper.getNullToStr(req.getParameter("insurant_id"));//主被保人
		String insurant_name=ActionHelper.getNullToStr(req.getParameter("insurant_name"));//主被保人名
		String event_id=ActionHelper.getNullToStr(req.getParameter("event_id"));//出险人
		String event_name=ActionHelper.getNullToStr(req.getParameter("event_name"));//出险人
		String event_date=ActionHelper.getNullToStr(req.getParameter("event_date"));//出险时间
		String event_process=ActionHelper.getNullToStr(req.getParameter("event_process"));//出险经过
		String claims_status=ActionHelper.getNullToStr(req.getParameter("claims_status"));//出险状态
		
		
		IClaimsModel modelForAdd = new ClaimsModel();
		modelForAdd.setPolicy_id(Long.valueOf(policy_id));
		modelForAdd.setBranch_id(branch_id);
		modelForAdd.setInsBranch_id(insBranch_id);
		modelForAdd.setPolicy_code(policy_code);
		modelForAdd.setHolder_id(holder_id);
		modelForAdd.setInsurant_id(insurant_id);
		modelForAdd.setEvent_id(event_id);
		modelForAdd.setEvent_date(DateUtil.string2Date(event_date));
		modelForAdd.setEvent_process(event_process);
		modelForAdd.setClaims_status(claims_status);
		
	
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = claimsService.addClaims(modelForAdd,user);
		}catch (BusinessException e){
			IClaimsVOModel modelForFail = new ClaimsVOModel();
			modelForFail.setPolicy_id(Long.valueOf(policy_id));
			modelForFail.setBranch_id(insBranch_id);
			modelForFail.setInsBranch_id(insBranch_id);
			modelForFail.setPolicy_code(policy_code);
			modelForFail.setHolder_id(holder_id);
			modelForFail.setHolder_name(holder_name);
			modelForFail.setInsurant_id(insurant_id);
			modelForFail.setInsurant_name(insurant_name);
			modelForFail.setEvent_id(event_id);
			modelForFail.setEvent_name(event_name);
			modelForFail.setEvent_date(DateUtil.string2Date(event_date));
			modelForFail.setEvent_process(event_process);
			modelForFail.setClaims_status(claims_status);
			returnMsg.setDataTable(TransHelper.obj2map(modelForFail));
			returnMsg.setFailMessage(e.getMessage());
		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg));
		return new ModelAndView("ca/cacore/cbs/claims/claimsAdd");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:进入查询页面
	*/
	@RequestMapping("/CBS/Claims/toQueryClaimsList.do")
	public ModelAndView toQueryClaimsList(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/claims/claimsQuery");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:
	*/
	@RequestMapping("/CBS/Claims/queryClaimsList.do")
	public ModelAndView queryClaimsList(HttpServletRequest req,HttpServletResponse res){
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//销售机构
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code"));//保单号
		
		IClaimsVOModel modelForQuery=new ClaimsVOModel();
		modelForQuery.setInsBranch_id(insBranch_id);
		modelForQuery.setPolicy_code(policy_code);
		modelForQuery.setPageCount(ActionHelper.getPage(req));
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=claimsService.queryClaims(modelForQuery);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,modelForQuery.getPageCount(),true));
		return returnPage(res,returnMsg,"ca/cacore/cbs/claims/claimsQuery");
	}
	
	
	/** 
	* 
	* @param req
	* @param res
	* @return
	* @throws IOException ModelAndView
	* @description:获得理赔信息
	*/
	@RequestMapping("/CBS/Claims/goModifyClaimsPage.do")
	public ModelAndView goModifyClaimsPage(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Integer seq_id=ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		
		IClaimsModel model=new ClaimsModel();
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=claimsService.getClaimsInfo(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/cbs/claims/claimsModify");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return
	* @throws IOException ModelAndView
	* @description: 理赔信息明细
	*/
	@RequestMapping("/CBS/Claims/viewClaims.do")
	public ModelAndView viewClaims(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Integer seq_id=ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		IClaimsModel model=new ClaimsModel();
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=claimsService.getClaimsInfo(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/cbs/claims/claimsView");
	}
	
	@RequestMapping("/CBS/Claims/modifyClaims.do")
	public ModelAndView modifyClaims(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		Integer seq_id=ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		
		String policy_id=ActionHelper.getNullToStr(req.getParameter("policy_id"));//保单ID
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司
		String insBranch_name=ActionHelper.getNullToStr(req.getParameter("insBranch_name"));//保险公司
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code"));//保单号
		String holder_name=ActionHelper.getNullToStr(req.getParameter("holder_name"));//投保人名称
		String insurant_name=ActionHelper.getNullToStr(req.getParameter("insurant_name"));//主被保人名
		String event_id=ActionHelper.getNullToStr(req.getParameter("event_id"));//出险人
		String event_name=ActionHelper.getNullToStr(req.getParameter("event_name"));//出险人
		String event_date=ActionHelper.getNullToStr(req.getParameter("event_date"));//出险时间
		String event_process=ActionHelper.getNullToStr(req.getParameter("event_process"));//出险经过
		String claims_status=ActionHelper.getNullToStr(req.getParameter("claims_status"));//出险状态
		
		
		IClaimsModel model=new ClaimsModel();
		model.setSeq_id(seq_id);
		model.setInsBranch_id(insBranch_id);
		model.setPolicy_code(policy_code);
		model.setEvent_id(event_id);
		model.setEvent_date(DateUtil.string2Date(event_date));
		model.setEvent_process(event_process);
		model.setClaims_status(claims_status);
		ReturnMsg returnMsg=new ReturnMsg();
		try{
			returnMsg=claimsService.modifyClaims(model,user);
		}catch(BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
		}
		
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));
		return new ModelAndView("ca/cacore/cbs/claims/claimsModify");
	}
}
