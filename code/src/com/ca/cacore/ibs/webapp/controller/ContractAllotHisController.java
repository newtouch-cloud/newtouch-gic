package com.ca.cacore.ibs.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.ibs.model.vo.ContractAllotHisVOModel;
import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;
import com.ca.cacore.ibs.webapp.service.IContractAllotHisService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ThreadLocalHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * @since:    2013年12月24日 
 * @author WanBo
 * @description: 保单分配轨迹查询Controller层
 */
@Controller
public class ContractAllotHisController extends BaseController{
	
	@Autowired
	private IContractAllotHisService contractAllotHisService;

	/**
	 * 查询保单分配轨迹
	 * @param req
	 * @param res
	 * @return 返回一个ModelAndView对象
	 * @throws Exception
	 * @description 查询保单分配轨迹
	 */
	@RequestMapping("/CBS/ContractAllotHis/queryContList.do")
	public ModelAndView queryContList(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		String branch_id=ActionHelper.getNullToStr(req.getParameter("org_id"));//获取机构代码
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//获取保险公司机构代码
		String policy_code = ActionHelper.getNullToStr(req.getParameter("policy_code"));//获取保单号
		String holder_name = ActionHelper.getNullToStr(req.getParameter("holder_name"));//获取投保人姓名   
		String bef_service_name = ActionHelper.getNullToStr(req.getParameter("bef_service_name"));//获取分配前服务人员姓名
		String aft_service_name = ActionHelper.getNullToStr(req.getParameter("aft_service_name"));//获取分配后服务人员姓名
		IContractAllotHisVOModel model = new ContractAllotHisVOModel();//进行封装
		model.setBranch_id(branch_id);
		model.setInsBranch_id(insBranch_id);
		model.setPolicy_code(policy_code);
		model.setHolder_name(holder_name);
		model.setBef_service_name(bef_service_name);
		model.setAft_service_name(aft_service_name);
		model.setPageCount(ActionHelper.getPage(req));//设置分页信息
		ReturnMsg returnMsg = contractAllotHisService.queryContList(model);//查询出所有的信息
		req.setAttribute("rmHelper",new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
		return returnPage(res, returnMsg,"ca/cacore/cbs/contractRenewalManage/contractAllotHisQuery");//跳转到保单分配轨迹页面
	}
}
