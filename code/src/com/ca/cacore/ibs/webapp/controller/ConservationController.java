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
import com.ca.cacore.ibs.model.bo.ConservationModel;
import com.ca.cacore.ibs.model.bo.IConservationModel;
import com.ca.cacore.ibs.model.vo.ConservationVOModel;
import com.ca.cacore.ibs.model.vo.IConservationVOModel;
import com.ca.cacore.ibs.webapp.service.IConservationService;
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
 * 
 * @author zhao
 * @ClassName: ConservationController 
 * @email zhaominqi1990@163.com 
 * @date 2014年1月26日 下午3:09:28 
 * @Description: 保全控制层
 */
@Controller
public class ConservationController extends BaseController {
	
	@Autowired
	private IConservationService conservationService;
	
	/**
	 * 
	 * @author: zhao
	 * @Description: 获取保单信息，以实现自动带出投保人和第一被保人
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException ModelAndView
	 * @throws
	 * @time : 2014年1月27日上午10:02:21
	 */
	@RequestMapping("/CBS/Conservation/getPoliInfo.do")
	public ModelAndView getPoliInfo(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));
		String policy_code = ActionHelper.getNullToStr(req.getParameter("policy_code"));
		IConservationModel cmodel = new ConservationModel();
		cmodel.setInsBranch_id(insBranch_id);
		cmodel.setPolicy_code(policy_code);
		IConservationVOModel cVoModel = conservationService.getPoliInfo(cmodel);
		//存放保单信息
		String info="";
		//存放查询出的申请人信息
		String applyOption ="";
		
		if(cVoModel!=null){
			//取出多个被保人的信息
			List<IConservationVOModel> voList = conservationService.getApplyInfo(cVoModel.getPolicy_id());
			List<String> listOp=new ArrayList<String>();
			
			//把申请人id和姓名取出   拼接成jsn字符串
			for(IConservationVOModel vo : voList){
				applyOption = "{id:'"+vo.getApplicant_id()+"',name:'"+vo.getApplicant_name()+"'}";
				listOp.add(applyOption);
			}
			applyOption = JSONArray.fromObject(listOp).toString();
			
			info="{isSuccess:'true',policy_id:'"+cVoModel.getPolicy_id()+"',branch_id:'"+cVoModel.getBranch_id()+
				"',holder_id:'"+cVoModel.getHolder_id()+"',holder_name:'"+cVoModel.getHolder_name()
				 +"',insurant_id:'"+cVoModel.getInsurant_id()+"',insurant_name:'"+cVoModel.getInsurant_name()+"',validate_date:'"+cVoModel.getValidate_date()+"'}";
		}else{
			info="{isSuccess:'false'}";
		}
		List<String> list=new ArrayList<String>();
		list.add(info);
		res.getWriter().print(JSONArray.fromObject(list).toString()+"#"+applyOption);
		return null;
	}
	

	/**
	 * 
	 * @author: zhao
	 * @Description: TODO 增加新保全信息项
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException ModelAndView
	 * @throws
	 * @time : 2014年2月7日上午11:32:10
	 */
	@RequestMapping("/CBS/Conservation/addConservation.do")
	public ModelAndView addconservation(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		
		String policy_id=ActionHelper.getNullToStr(req.getParameter("policy_id"));					//保单ID
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));					//销售机构
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));			//保险公司
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code"));				//保单号
		String holder_id=ActionHelper.getNullToStr(req.getParameter("holder_id"));					//投保人
		String holder_name=ActionHelper.getNullToStr(req.getParameter("holder_name"));				//投保人名称
		String insurant_id=ActionHelper.getNullToStr(req.getParameter("insurant_id"));				//主被保人
		String insurant_name=ActionHelper.getNullToStr(req.getParameter("insurant_name"));			//主被保人名
		String conservationItem_code=ActionHelper.getNullToStr(req.getParameter("conservationItem_code"));//保全项目id
		String conservationItem=ActionHelper.getNullToStr(req.getParameter("conservationItem_name"));//保全项目名称
		String application_Time=ActionHelper.getNullToStr(req.getParameter("application_Time"));	//申请日期
		String applicant_id=ActionHelper.getNullToStr(req.getParameter("applicant_id"));			//申请人id
		String applicant_name=ActionHelper.getNullToStr(req.getParameter("applicant_name"));		//申请人
		String replace_applicant=ActionHelper.getNullToStr(req.getParameter("replace_applicant"));	//代办人
		String remark=ActionHelper.getNullToStr(req.getParameter("remark"));	//备注
			
		
		IConservationModel modelForAdd = new ConservationModel();
		if(!policy_id.equals("")){
		modelForAdd.setPolicy_id(Long.valueOf(policy_id));
		}
		modelForAdd.setBranch_id(branch_id);
		modelForAdd.setInsBranch_id(insBranch_id);
		modelForAdd.setPolicy_code(policy_code);
		modelForAdd.setHolder_id(holder_id);
		modelForAdd.setInsurant_id(insurant_id);
		modelForAdd.setConservationItem_code(conservationItem_code);
		modelForAdd.setApplication_time(DateUtil.string2Date(application_Time));
		modelForAdd.setApplicant_id(applicant_id);
		modelForAdd.setReplace_applicant(replace_applicant);
		modelForAdd.setRemark(remark);
		
	
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = conservationService.addConservation(modelForAdd,user);
		}catch (BusinessException e){
			IConservationVOModel modelForFail = new ConservationVOModel();
			if(!policy_id.equals("")){
				modelForFail.setPolicy_id(Long.valueOf(policy_id));
			}
			modelForFail.setBranch_id(insBranch_id);
			modelForFail.setInsBranch_id(insBranch_id);
			modelForFail.setPolicy_code(policy_code);
			modelForFail.setHolder_id(holder_id);
			modelForFail.setHolder_name(holder_name);
			modelForFail.setInsurant_id(insurant_id);
			modelForFail.setInsurant_name(insurant_name);
			modelForFail.setConservationItem_code(conservationItem_code);
			modelForFail.setConservationItem_name(conservationItem);
			modelForFail.setApplicant_id(applicant_id);
			modelForFail.setApplicant_name(applicant_name);
			modelForFail.setApplication_time(DateUtil.string2Date(application_Time));
			modelForFail.setReplace_applicant(replace_applicant);
			modelForFail.setApplication_time(DateUtil.string2Date(application_Time));
			modelForFail.setReplace_applicant(replace_applicant);
			returnMsg.setDataTable(TransHelper.obj2map(modelForFail));
			returnMsg.setFailMessage(e.getMessage());
		}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/cbs/conservation/conservationAdd");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:进入查询页面
	*/
	@RequestMapping("/CBS/Conservation/toQueryConservationsList.do")
	public ModelAndView toQueryConservationsList(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/conservation/conservationQuery");
	}
	
	/**
	 * 
	 * @author: zhao
	 * @Description: 批量查询保全信息 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws
	 * @time : 2014年2月8日上午9:50:15
	 */
	@RequestMapping("/CBS/Conservation/queryConservationsList.do")
	public ModelAndView queryConservationsList(HttpServletRequest req,HttpServletResponse res){
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));	//销售机构
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code"));		//保单号
		
		IConservationVOModel modelForQuery=new ConservationVOModel();
		modelForQuery.setInsBranch_id(insBranch_id);
		modelForQuery.setPolicy_code(policy_code);
		modelForQuery.setPageCount(ActionHelper.getPage(req));
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=conservationService.queryConservations(modelForQuery);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,modelForQuery.getPageCount(),true));
		return returnPage(res,returnMsg,"ca/cacore/cbs/conservation/conservationQuery");
	}
	
	/**
	 * 
	 * @author: zhao
	 * @Description: 保全明细，跳转时带的参数为seq_id
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException ModelAndView
	 * @time : 2014年2月8日上午9:51:30
	 */
	@RequestMapping("/CBS/Conservation/viewConservations.do")
	public ModelAndView viewConservations(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Integer seq_id=ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		
		IConservationModel model=new ConservationModel();
		model.setSeq_id(seq_id);
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=conservationService.getConservationsInfo(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/cbs/conservation/conservationView");
	}
	
	/**
	 * 
	 * 
	 * @author: zhao
	 * @Description: 跳转到修改页面 ，带数据
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException ModelAndView
	 * @time : 2014年2月8日上午9:53:57
	 */
	@RequestMapping("/CBS/Conservation/goModifyConservationsPage.do")
	public ModelAndView goModifyConservationsPage(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Integer seq_id=ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		
		IConservationModel model=new ConservationModel();
		model.setSeq_id(seq_id);
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=conservationService.getConservationsInfo(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/cbs/conservation/conservationModify");
	}
	
	/**
	 * 
	 * @author: zhao
	 * @Description: 得到申请人信息,这里供前台js调用 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException ModelAndView
	 * @time : 2014年2月8日上午9:55:16
	 */
	@RequestMapping("/CBS/Conservation/getApplyInfo.do")
	public ModelAndView getApplyInfo(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		String policy_id=ActionHelper.getNullToStr(req.getParameter("policy_id")).trim();
		String applyOption ="";
		
		//取出多个被保人的信息
		List<IConservationVOModel> voList = conservationService.getApplyInfo(Long.valueOf(policy_id));
		List<String> listOp=new ArrayList<String>();
		for(IConservationVOModel vo : voList){
			applyOption = "{id:'"+vo.getApplicant_id()+"',name:'"+vo.getApplicant_name()+"'}";
			listOp.add(applyOption);
		}
		if(listOp.size() != 0){
			applyOption = JSONArray.fromObject(listOp).toString();
		}else{
			applyOption="{isSuccess:'false'}";
		}
		res.getWriter().print(applyOption);
		return null;
	}
	
	/**
	 * 
	 * @author: zhao
	 * @Description: 修改保存 ,可修改内容为申请项、申请人、申请日期、代办人、备注
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException ModelAndView
	 * @time : 2014年2月8日上午9:56:59
	 */
	@RequestMapping("/CBS/Conservation/modifyConservation.do")
	public ModelAndView modifyConservation(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		Integer seq_id=ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));	//保险机构
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code"));		//保单号
		String applicant_id=ActionHelper.getNullToStr(req.getParameter("applicant_id"));//申请人
		String conservationItem_code=ActionHelper.getNullToStr(req.getParameter("conservationItem_code"));//申请人
		String application_time=ActionHelper.getNullToStr(req.getParameter("application_time"));//申请时间
		String replace_applicant=ActionHelper.getNullToStr(req.getParameter("replace_applicant"));//代办人
		String remark = ActionHelper.getNullToStr(req.getParameter("remark"));//备注
		String insurant_name=ActionHelper.getNullToStr(req.getParameter("insurant_name"));//保单ID
		String holder_name=ActionHelper.getNullToStr(req.getParameter("holder_name"));//投保人
		String insBranch_name=ActionHelper.getNullToStr(req.getParameter("insBranch_name"));//主被保人
		String policy_id=ActionHelper.getNullToStr(req.getParameter("policy_id"));//主被保人
		
		//将修改信息保存到模型中
		IConservationModel model=new ConservationModel();
		model.setSeq_id(seq_id);
		model.setInsBranch_id(insBranch_id);
		model.setPolicy_code(policy_code);
		model.setApplicant_id(applicant_id);
		model.setApplication_time(DateUtil.string2Date(application_time));
		model.setConservationItem_code(conservationItem_code);
		model.setReplace_applicant(replace_applicant);
		model.setRemark(remark);
		ReturnMsg returnMsg=new ReturnMsg();
		try{
			returnMsg=conservationService.modifyConservation(model,user);
		}catch(BusinessException e){
			IConservationVOModel vom = new ConservationVOModel();
			vom.setSeq_id(seq_id);
			vom.setInsBranch_id(insBranch_id);
			vom.setPolicy_code(policy_code);
			vom.setApplicant_id(applicant_id);
			vom.setApplication_time(DateUtil.string2Date(application_time));
			vom.setConservationItem_code(conservationItem_code);
			vom.setReplace_applicant(replace_applicant);
			vom.setRemark(remark);
			vom.setReplace_applicant(replace_applicant);
			vom.setApplication_time(DateUtil.string2Date(application_time));
			vom.setInsurant_name(insurant_name);
			if(!policy_id.equals("")){
				vom.setPolicy_id(Long.valueOf(policy_id));
				}
			vom.setInsBranch_name(insBranch_name);
			vom.setHolder_name(holder_name);
			returnMsg.setDataTable(TransHelper.obj2map(vom));
			returnMsg.setFailMessage(e.getMessage());
		}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/cbs/conservation/conservationModify");
	}
	
	@RequestMapping("/CBS/Conservation/checkDateOrder.do")
	public ModelAndView checkDateOrder(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//销售机构
		String policy_code=ActionHelper.getNullToStr(req.getParameter("policy_code"));//保单号
		String application_time=ActionHelper.getNullToStr(req.getParameter("application_time"));//保单号
		
		IConservationModel model = new ConservationModel();
		model.setInsBranch_id(insBranch_id);
		model.setPolicy_code(policy_code);
		model.setApplication_time(DateUtil.string2Date(application_time));
		Boolean flag = conservationService.checkDateOrder(model);
		if(flag){
			res.getWriter().print("true");
		}else{
			res.getWriter().print("false");
		}
		return null;
	}
}
