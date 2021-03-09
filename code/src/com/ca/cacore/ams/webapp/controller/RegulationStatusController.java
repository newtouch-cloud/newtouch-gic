package com.ca.cacore.ams.webapp.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.ams.model.vo.IPreserveRegVOModel;
import com.ca.cacore.ams.model.vo.PreserveRegVOModel;
import com.ca.cacore.ams.webapp.service.IRegulationManagerService;
import com.ca.cacore.ams.webapp.service.IRegulationStatusService;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.ThreadLocalHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;
@Controller 
public class RegulationStatusController extends BaseController{
	@Autowired private IRegulationManagerService regulationManagerService;
	@Autowired private IRegulationStatusService regulationStatusService;

	/**
	 * 
	 * @param req
	 * @param res
	 * @return  
	 * @description:跳转到规章制度状态发布页面
	 */                     
	@RequestMapping("/AMS/RegulationStatusController/toStatusChange.do")
	public ModelAndView toStatusChange(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/ams/regulationManager/regulationStatusChange");
	}
	 /**
     * 
     * @param req
     * @param res
     * @return
     * @description:根据条件查询规章信息
     */
	@RequestMapping("/AMS/RegulationStatusController/queryAction.do")
	public ModelAndView queryAction(HttpServletRequest req,HttpServletResponse res){
		ThreadLocalHelper.set("isOpen", true);
		//表单提交的数据
		String regulation_id = ActionHelper.getNullToStr(req.getParameter("regulation_id"));  //规章编号
		String regulation_name = ActionHelper.getNullToStr(req.getParameter("regulation_name")); //规章名称
		String makers = ActionHelper.getNullToStr(req.getParameter("makers"));//	制定人
		String regulation_status_code=ActionHelper.getNullToStr(req.getParameter("regulation_status_code"));//规章状态
		Date firstDate = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("firstDate")));//查询条件中的批次时间  自
		Date secondDate = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("secondDate")));//查询条件中的批次时间  到
		//创建模型层(model)对象
		IPreserveRegVOModel model = new PreserveRegVOModel();
		model.setRegulation_id(regulation_id);
		model.setRegulation_name(regulation_name);
		model.setMakers(makers);
		model.setRegulation_status_code(regulation_status_code);
		model.setFirstDate(firstDate);
		model.setSecondDate(secondDate);
		model.setPageCount(ActionHelper.getPage(req));
		//获取查询结果集  放入returnMsg中
		ReturnMsg returnMsg = regulationManagerService.queryStatus(model);
		//将查询到的信息绑定到对象上
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		//跳转页面
		return returnPage(res, returnMsg,"ca/cacore/ams/regulationManager/regulationStatusChange");	
	}
	
	 /**
     * 
     * @param req
     * @param res
     * @return
     * @description:更改规章制度的状态
     */                
	@RequestMapping("/AMS/RegulationStatusController/updateRegulationStatus.do")
	public ModelAndView updateRegulationStatus(HttpServletRequest req,HttpServletResponse res) throws Exception{
			//获取查询条件信息
			String regulation_id = ActionHelper.getNullToStr(req.getParameter("regulation_id"));  //规章编号
			String regulation_name = ActionHelper.getNullToStr(req.getParameter("regulation_name")); //规章名称
			String makers = ActionHelper.getNullToStr(req.getParameter("makers"));//	制定人
			Date firstDate = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("firstDate")));//查询条件中的批次时间  自
			Date secondDate = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("secondDate")));//查询条件中的批次时间  到
			String regulation_status_code = ActionHelper.getNullToStr(req.getParameter("regulation_status_code"));//	规章状态代码
			IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
			IPreserveRegVOModel model = new PreserveRegVOModel();
			model.setRegulation_id(regulation_id);
			model.setRegulation_name(regulation_name);
			model.setMakers(makers);
			model.setFirstDate(firstDate);
			model.setRegulation_status_code(regulation_status_code);
			model.setSecondDate(secondDate);
			model.setPageCount(ActionHelper.getPage(req));
			String[] s= null;//new一个String类型数组用来接收前台获取的多选的seq_id
			s= req.getParameterValues("seq_id");//获取seq_id
			Integer[] seq_ids=new Integer[s.length];//将String类型数组转换成Integer类型数组
			for(int i=0;i<s.length;i++){
				seq_ids[i]=Integer.parseInt(s[i]);
			}
			model.setSeq_ids(seq_ids);
			ReturnMsg returnMsg = new ReturnMsg();
			try{
				returnMsg =regulationStatusService.updateRegulationStatus(model, user);
			}catch (BusinessException e){
				returnMsg.setFailMessage(e.getMessage()); 
			}
			req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
			return returnPage(res,returnMsg,"ca/cacore/ams/regulationManager/regulationStatusChange");
	}
	
}
