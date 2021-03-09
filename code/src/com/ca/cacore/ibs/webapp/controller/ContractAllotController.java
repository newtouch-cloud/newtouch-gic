package com.ca.cacore.ibs.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.model.bo.UserModel;
import com.ca.cacore.ibs.model.vo.ContractAllotHisVOModel;
import com.ca.cacore.ibs.model.vo.IContractAllotHisVOModel;
import com.ca.cacore.ibs.webapp.service.IContractAllotService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ThreadLocalHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.ReturnMsg;
/**
* @since:    2013年12月23日   
* @author    WanBo
* @description: 保单分配Controller层
*/
@Controller
public class ContractAllotController  extends BaseController{
	@Autowired
	private IContractAllotService contractAllotService;
	/**
	 * 跳转到保单分配页面
	 * @param req
	 * @param res
	 * @return 返回一个ModelAndView对象
	 * @throws Exception
	 * @description 跳转到保单分配页面
	 */
	@RequestMapping("/CBS/ContractAllot/queryPolicy.do")
	public ModelAndView queryPolicy(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		return new ModelAndView("ca/cacore/cbs/contractRenewalManage/ContractAllot");//跳转到保单分配页面
	}
	/**
	 * 查询保单信息列表
	 * @param req
	 * @param res
	 * @return 返回一个ModelAndView对象
	 * @throws Exception
	 * @description 根据分配前服务人员查询保单信息列表
	 */
	@RequestMapping("/CBS/ContractAllot/queryPolicyList.do")
	public ModelAndView queryPolicyList(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		IContractAllotHisVOModel model = new ContractAllotHisVOModel();
		model.setInsBranch_id(ActionHelper.getNullToStr(req.getParameter("insBranch_id")));//获取保险公司机构代码
		model.setBef_service_id(ActionHelper.getNullToStr(req.getParameter("bef_service_id")));//获取分配前服务人员代码
		List<String> list=contractAllotService.queryContList(model);//调用service层查询服务人员下所有保单
		res.getWriter().print(JSONArray.fromObject(list));
		return null;
	}
	/**
	 * 更新保单的服务人员信息
	 * @param req
	 * @param res
	 * @return 返回一个ModelAndView对象
	 * @throws Exception
	 * @description 更新保单的服务人员信息
	 */
	@RequestMapping("/CBS/ContractAllot/updatePolicyService.do")
	public ModelAndView updatePolicyService(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		IUserModel user=new UserModel();
		user.setUserName("WanBo");//先放个固定值
		IContractAllotHisVOModel model = new ContractAllotHisVOModel();
		String policy_ids[] = null;//保单id数组
		policy_ids= req.getParameterValues("policy_id");//获取保单id
		String aft_service_id=ActionHelper.getNullToStr(req.getParameter("aft_service_id"));//获取分配后服务人员代码
		model.setPolicy_ids(policy_ids);
		model.setAft_service_id(aft_service_id);
		model.setBef_service_id(ActionHelper.getNullToStr(req.getParameter("bef_service_id_hidden")));//获取分配前服务人员代码
		model.setBranch_id(ActionHelper.getNullToStr(req.getParameter("branch_id_hidden2")));//获取机构代码
		model.setInsBranch_id(ActionHelper.getNullToStr(req.getParameter("insBranch_id_hidden")));//获取保险公司机构
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = contractAllotService.updateContS(model, user);//调用service层更新保单服务人员
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		//设置界面返回信息
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));
		return new ModelAndView("ca/cacore/cbs/contractRenewalManage/ContractAllot");//跳转到保单分配新页面
	}
	/**
	 * 查询服务人员,机构和保单信息
	 * @param req
	 * @param res
	 * @return 返回一个ModelAndView对象
	 * @throws Exception
	 * @description 通过保单号查服务人员,机构和保单信息
	 */
	@RequestMapping("/CBS/ContractAllot/queryServiceByPolicyCode.do")
	public ModelAndView queryServiceByPolicyCode(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		IContractAllotHisVOModel model = new ContractAllotHisVOModel();
		model.setInsBranch_id(ActionHelper.getNullToStr(req.getParameter("insBranch_id")));//获取保险公司机构
		model.setBef_policy_code(ActionHelper.getNullToStr(req.getParameter("bef_policy_code")));//获取保单号
		List<String> list=contractAllotService.queryContInfo(model);
		res.getWriter().print(JSONArray.fromObject(list));
		return null;
		}
	/**
	 * 通过分配后服务人员查询姓名
	 * @param req
	 * @param res
	 * @return 返回一个ModelAndView对象
	 * @throws Exception
	 * @description 通过分配后服务人员查询姓名(校验)
	 */
	@RequestMapping("/CBS/ContractAllot/queryAftSName.do")
	public ModelAndView queryAftSName(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		IContractAllotHisVOModel model = new ContractAllotHisVOModel();
		model.setBef_service_id(ActionHelper.getNullToStr(req.getParameter("bef_service_id")));//获取分配前服务人员代码
		model.setAft_service_id(ActionHelper.getNullToStr(req.getParameter("aft_service_id")));//获取分配后服务人员代码
		String returnInfo="";
		IContractAllotHisVOModel serviceModel=contractAllotService.queryAftSName(model);//调用service层通过分配后服务人员查询姓名
		if(serviceModel!=null){
			if(model.getBef_service_id().equals(model.getAft_service_id())){//分配前后服务人员代码相同,返回失败
				returnInfo="{isSuccess:"+false+",isfalse:3}";
			}else{
				if(!serviceModel.getService_post_code().equals("S")){//分配后人员职级不为销售人员,返回失败
					returnInfo="{isSuccess:"+false+",isfalse:4}";
				}else{
					if(serviceModel.getAft_service_status().equals("0")){//分配后服务人员状态为无效,返回失败
						returnInfo="{isSuccess:"+false+",isfalse:1}";
					}else if(serviceModel.getAft_service_status().equals("2")){//分配后服务人员状态为离司,返回失败
						returnInfo="{isSuccess:"+false+",isfalse:2}";
					}else{//成功,带出分配后服务人员姓名
					returnInfo="{isSuccess:"+true+",aft_service_name:"+serviceModel.getAft_service_name()+"}";
					}
				}
			}
		}else{//分配后服务人员代码不存在,返回失败
			returnInfo="{isSuccess:"+false+",isfalse:0}";
		}
		res.getWriter().print(returnInfo);
		return null;
	}
	/**
	 * 根据服务人员代码查询是否存在
	 * @param req
	 * @param res
	 * @return 返回一个ModelAndView对象
	 * @throws Exception
	 * @description 根据服务人员代码查询是否存在(校验)
	 */
	@RequestMapping("/CBS/ContractAllot/queryServiceId.do")
	public ModelAndView queryServiceId(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		IContractAllotHisVOModel model = new ContractAllotHisVOModel();
		model.setInsBranch_id(ActionHelper.getNullToStr(req.getParameter("insBranch_id")));//保险公司机构代码
		model.setBef_service_id(ActionHelper.getNullToStr(req.getParameter("bef_service_id")));//分配前服务人员代码
		IContractAllotHisVOModel serviceIdModel=contractAllotService.queryServiceId(model);//调用service层查询服务人员代码是否存在
		String returnInfo="";
		if(serviceIdModel!=null){//服务人员代码存在,返回成功
			returnInfo="{isSuccess:"+true+"}";
		}else{//服务人员代码不存在,返回失败
			returnInfo="{isSuccess:"+false+"}";
		}
		res.getWriter().print(returnInfo);
		return null;
	}
	/**
	 * 根据保单号查询是否存在
	 * @param req
	 * @param res
	 * @return 返回一个ModelAndView对象
	 * @throws Exception
	 * @description 根据保单号查询是否存在(校验)
	 */
	@RequestMapping("/CBS/ContractAllot/queryPCode.do")
	public ModelAndView queryPCode(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		IContractAllotHisVOModel model = new ContractAllotHisVOModel();
		model.setInsBranch_id(ActionHelper.getNullToStr(req.getParameter("insBranch_id")));//保险公司机构
		model.setBef_service_id(ActionHelper.getNullToStr(req.getParameter("bef_service_id")));//分配前服务人员代码
		model.setBef_policy_code(ActionHelper.getNullToStr(req.getParameter("bef_policy_code")));//所属保单号
		IContractAllotHisVOModel policyCodeModel=contractAllotService.queryPCode(model);//调用service层查询
		String returnInfo="";
		if(policyCodeModel!=null){//如果保单号存在
			if(policyCodeModel.getPolicy_status().equals("200")){//如果保单状态为无效,返回失败
				returnInfo="{isSuccess:"+false+",isfalse:1}";
			}else{//保单状态符合,返回成功
			returnInfo="{isSuccess:"+true+"}";
			}
		}else{//保单号不存在,返回失败
			returnInfo="{isSuccess:"+false+",isfalse:0}";
		}
		res.getWriter().print(returnInfo);
		return null;
	}
	
}
