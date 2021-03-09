package com.ca.cacore.ibs.webapp.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.bo.PolicyImageModel;
import com.ca.cacore.ibs.model.vo.IPolicyImageVoModel;
import com.ca.cacore.ibs.model.vo.PolicyImageVoModel;
import com.ca.cacore.ibs.webapp.service.IPolicyImageService;
import com.ca.cacore.common.CodeTypeConst;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c1properties.StaticProperties;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;

/**
 * 2013-12-11 保单影像管理
 * @author 张晨
 *
 */
@Controller
public class PolicyImageController extends BaseController{
	
	@Autowired private IPolicyImageService policyImageService;
	
	/**
	 * 
	 * 保单管理-->保单影像管理-->保单影像件上传Ztree导航
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/policyImage/goPolicyImageUpload.do")
	public ModelAndView goPolicyAnswerAdd(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		return new ModelAndView("ca/cacore/cbs/policyImage/policyImageUpload");
	}
	
	/**
	* @param req
	* @param res
	* @return
	* @throws ModelAndView
	* @description:重定投保单影像查询界面contract
	 */
	@RequestMapping("/cbs/policyImage/toQueryPolicyImage.do")
	public ModelAndView toQueryPolicyImage(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/policyImage/policyImageQuery");
	}
	
	/**
	* @param req
	* @param res
	* @return
	* @throws ModelAndView
	* @description:重定保单影像查询界面
	 */
	@RequestMapping("/cbs/policyImage/toQueryContractImage.do")
	public ModelAndView toQueryContractImage(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/policyImage/contractImageQuery");
	}
	
	/**
	* @param req
	* @param res
	* @return
	* @throws ModelAndView
	* @description:重定投保单影像上传界面
	 */
	@RequestMapping("/cbs/policyImage/toAddPolicyImage.do")
	public ModelAndView toAddPolicyImage(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/policyImage/policyQueryAddImage");
	}
	
	/**
	* @param req
	* @param res
	* @return
	* @throws ModelAndView
	* @description:重定保单影像上传界面
	 */
	@RequestMapping("/cbs/policyImage/toAddContractImage.do")
	public ModelAndView toAddContractImage(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/cbs/policyImage/contractQueryAddImage");
	}
	
	/**
	* @param req
	* @param res
	* @return
	* @throws ModelAndView
	* @description:影像管理-->投保单影像件上传-->查询按钮
	 */
	@RequestMapping("/cbs/policyImage/QueryAddPolicyImage.do")
	public ModelAndView QueryAddPolicyImage(HttpServletRequest request,HttpServletResponse response) {
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//销售机构id
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//所属公司机构
		String policy_code=ActionHelper.getNullToStr(request.getParameter("policy_code"));//投保单号
		String agent_name=ActionHelper.getNullToStr(request.getParameter("agent_name"));//保单销售人员姓名
		String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人员姓名
		 
		IPolicyImageVoModel model = new PolicyImageVoModel();
		model.setBranch_id(branch_id);//机构
		model.setInsBranch_id(insBranch_id);//保险公司机构
		model.setPolicy_code(policy_code);//保单号
		model.setAgent_name(agent_name);//销售人姓名
		model.setApp_name(app_name);//投保人姓名
		model.setPageCount(ActionHelper.getPage(request));
		
		IUserModel user = ServletHelper.getSessionUser(request); //获取用户
		model.setEmp_id(user.getEmp_id());
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyImageService.queryAddPolicyImage(model);//查询保单信息
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,model.getPageCount(),true));
		return returnPage(response, returnMsg, "ca/cacore/cbs/policyImage/policyQueryAddImage");
	}
	
	
	
	/**
	* @param req
	* @param res
	* @return
	* @throws ModelAndView
	* @description:影像管理-->保单影像件上传-->查询按钮
	 */
	@RequestMapping("/cbs/policyImage/QueryAddContractImage.do")
	public ModelAndView QueryAddContractImage(HttpServletRequest request,HttpServletResponse response) {
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//销售机构id
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//所属公司机构
		String policy_code=ActionHelper.getNullToStr(request.getParameter("policy_code"));//投保单号
		String agent_name=ActionHelper.getNullToStr(request.getParameter("agent_name"));//保单销售人员姓名
		String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人员姓名
		 
		IPolicyImageVoModel model = new PolicyImageVoModel();
		model.setBranch_id(branch_id);//机构
		model.setInsBranch_id(insBranch_id);//保险公司机构
		model.setPolicy_code(policy_code);//保单号
		model.setAgent_name(agent_name);//销售人姓名
		model.setApp_name(app_name);//投保人姓名
		model.setPageCount(ActionHelper.getPage(request));
		
		IUserModel user = ServletHelper.getSessionUser(request); //获取用户
		model.setEmp_id(user.getEmp_id());
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyImageService.queryAddContractImage(model);//查询保单信息
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,model.getPageCount(),true));
		return returnPage(response, returnMsg, "ca/cacore/cbs/policyImage/contractQueryAddImage");
	}
	
	/**
	* @param req
	* @param res
	* @return
	* @throws ModelAndView
	* @description:影像管理-->投保单影像件上传-->超链接去影像上传界面
	 */
	@RequestMapping("/cbs/policyImage/addPolicyImage.do")
	public ModelAndView addPolicyImage(HttpServletRequest req,HttpServletResponse res) {
		Long policy_id = new Long(ActionHelper.getNullToStr(req.getParameter("policy_id")));//对应保单号
		IPolicyImageVoModel model = new PolicyImageVoModel(); 
		model.setPolicy_id(policy_id);	//通过报单号得到关联的影像及保单的信息
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyImageService.viewPolicyImageForAdd(model);	 //显示投保单关联影像信息
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());  //把ReturnMsg中的数据放入ReturnMsgHelper的ReturnParams中
		req.setAttribute("rmHelper",rmHelper);	
		return new ModelAndView("ca/cacore/cbs/policyImage/policyAddImage");
	}
	
	/**
	 * 影像管理--投保单影像件上传--关联投保单及影像信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/CBS/policyImage/concernAddPolicyImage.do")
	public ModelAndView concernAddPolicyImage(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		String file_ids[] = request.getParameterValues("file_id");  //影像id,是一个数组
		String policy_id = ActionHelper.getNullToStr(request.getParameter("policy_id"));  //保单id
		String send_code = ActionHelper.getNullToStr(request.getParameter("send_code"));  //投保单id
		String bus_type = ActionHelper.getNullToStr(request.getParameter("bus_type"));  //影像件类型
		IPolicyImageModel model = new PolicyImageModel();
		model.setFile_ids(file_ids);   //把影像数组放入model中
		model.setPolicy_id(new Long(policy_id));  //添加保单id
		model.setSend_code(send_code);  //添加投保单id
		model.setBus_type(bus_type);
		IUserModel user = ServletHelper.getSessionUser(request); //获取用户
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = policyImageService.concernAddPolicyImage(model,user); //调用关联方法
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		IPolicyImageVoModel model2 = new PolicyImageVoModel(); 
		model2.setPolicy_id(new Long(policy_id));	//返回关联的影像信息
		model2.setBus_type(bus_type);
		returnMsg.setDataList(policyImageService.viewPolicyImage(model2).getDataList());//获取影像list
		request.setAttribute("rmHelper",new ReturnMsgHelper(request, returnMsg,true));
		return new ModelAndView("ca/cacore/cbs/policyImage/policyAddImage");
	}
	
	/**
	* @param req
	* @param res
	* @return
	* @throws ModelAndView
	* @description:影像管理-->保单影像件上传-->超链接去影像上传界面
	 */
	@RequestMapping("/cbs/policyImage/addContractImage.do")
	public ModelAndView addContractImage(HttpServletRequest req,HttpServletResponse res) {
		Long policy_id = new Long(ActionHelper.getNullToStr(req.getParameter("policy_id")));//对应保单号
		IPolicyImageVoModel model = new PolicyImageVoModel(); 
		model.setPolicy_id(policy_id);	//通过报单号得到关联的影像及保单的信息
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyImageService.viewContractImageForAdd(model);	 //显示投保单关联影像信息
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());  //把ReturnMsg中的数据放入ReturnMsgHelper的ReturnParams中
		req.setAttribute("rmHelper",rmHelper);	
		return new ModelAndView("ca/cacore/cbs/policyImage/contractAddImage");
	}
	
	/**
	 * 影像管理--保单影像件上传--关联保单及影像信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/CBS/policyImage/concernAddContractImage.do")
	public ModelAndView concernAddContractImage(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		String file_ids[] = request.getParameterValues("file_id");  //影像id,是一个数组
		String policy_id = ActionHelper.getNullToStr(request.getParameter("policy_id"));  //保单id
		String send_code = ActionHelper.getNullToStr(request.getParameter("send_code"));  //投保单id
		String bus_type = ActionHelper.getNullToStr(request.getParameter("bus_type"));  //影像件类型
		IPolicyImageModel model = new PolicyImageModel();
		model.setFile_ids(file_ids);   //把影像数组放入model中
		model.setPolicy_id(new Long(policy_id));  //添加保单id
		model.setSend_code(send_code);  //添加投保单id
		model.setBus_type(bus_type);
		IUserModel user = ServletHelper.getSessionUser(request); //获取用户
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = policyImageService.concernAddPolicyImage(model,user); //调用关联方法
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		IPolicyImageVoModel model2 = new PolicyImageVoModel(); 
		model2.setPolicy_id(new Long(policy_id));	//返回关联的影像信息
		model2.setBus_type(bus_type);
		returnMsg.setDataList(policyImageService.viewContractImage(model2).getDataList());//获取影像list
		request.setAttribute("rmHelper",new ReturnMsgHelper(request, returnMsg,true));
		return new ModelAndView("ca/cacore/cbs/policyImage/contractAddImage");
	}
	
	/**
	 * 把影像信息和保单号进行关联
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/CBS/policyImage/concernPolicyImage.do")
	public ModelAndView concernPolicyImage(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		String file_ids[] = request.getParameterValues("file_id");  //影像id,是一个数组
		String policy_id = ActionHelper.getNullToStr(request.getParameter("policy_id"));  //保单id
		String send_code = ActionHelper.getNullToStr(request.getParameter("send_code"));  //投保单id
		String bus_type = ActionHelper.getNullToStr(request.getParameter("bus_type"));  //影像件类型
		IPolicyImageModel model = new PolicyImageModel();
		model.setFile_ids(file_ids);   //把影像数组放入model中
		model.setPolicy_id(new Long(policy_id));  //添加保单id
		model.setSend_code(send_code);  //添加投保单id
		model.setBus_type(bus_type);
		IUserModel user = ServletHelper.getSessionUser(request); //获取用户
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = policyImageService.concernPolicyImage(model,user); //调用关联方法
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
	
		request.setAttribute("rmHelper",new ReturnMsgHelper(request, returnMsg));
		return new ModelAndView("ca/cacore/cbs/policyImage/policyImageUpload");
	}
	
	/**
	 * 投保单录入--投保单影像件上传--关联投保单及影像信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/CBS/policyImage/concernPolicyAddImage.do")
	public ModelAndView concernPolicyAddImage(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		String file_ids[] = request.getParameterValues("file_id");  //影像id,是一个数组
		String policy_id = ActionHelper.getNullToStr(request.getParameter("policy_id"));  //保单id
		String send_code = ActionHelper.getNullToStr(request.getParameter("send_code"));  //投保单id
		String bus_type = ActionHelper.getNullToStr(request.getParameter("bus_type"));  //影像件类型
		IPolicyImageModel model = new PolicyImageModel();
		model.setFile_ids(file_ids);   //把影像数组放入model中
		model.setPolicy_id(new Long(policy_id));  //添加保单id
		model.setSend_code(send_code);  //添加投保单id
		model.setBus_type(bus_type);
		IUserModel user = ServletHelper.getSessionUser(request); //获取用户
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = policyImageService.concernPolicyAddImage(model,user); //调用关联方法
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		IPolicyImageVoModel model2 = new PolicyImageVoModel(); 
		model2.setPolicy_id(new Long(policy_id));	//返回关联的影像信息
		model2.setBus_type(bus_type);
		returnMsg.setDataList(policyImageService.viewPolicyImage(model2).getDataList());//获取影像list
		request.setAttribute("rmHelper",new ReturnMsgHelper(request, returnMsg,true));
		return new ModelAndView("ca/cacore/cbs/policyImage/policyImageAdd");
	}
	
	/**
	 * 保单录入--保单影像件上传--关联保单及影像信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/CBS/policyImage/concernContractAddImage.do")
	public ModelAndView concernContractAddImage(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		String file_ids[] = request.getParameterValues("file_id");  //影像id,是一个数组
		String policy_id = ActionHelper.getNullToStr(request.getParameter("policy_id"));  //保单id
		String send_code = ActionHelper.getNullToStr(request.getParameter("send_code"));  //投保单id
		String bus_type = ActionHelper.getNullToStr(request.getParameter("bus_type"));  //影像件类型
		IPolicyImageModel model = new PolicyImageModel();
		model.setFile_ids(file_ids);   //把影像数组放入model中
		model.setPolicy_id(new Long(policy_id));  //添加保单id
		model.setSend_code(send_code);  //添加投保单id
		model.setBus_type(bus_type);
		IUserModel user = ServletHelper.getSessionUser(request); //获取用户
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = policyImageService.concernPolicyAddImage(model,user); //调用关联方法
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		IPolicyImageVoModel model2 = new PolicyImageVoModel(); 
		model2.setPolicy_id(new Long(policy_id));	//返回关联的影像信息
		model2.setBus_type(bus_type);
		returnMsg.setDataList(policyImageService.viewContractImage(model2).getDataList());//获取影像list
		request.setAttribute("rmHelper",new ReturnMsgHelper(request, returnMsg,true));
		return new ModelAndView("ca/cacore/cbs/policyImage/contractImageAdd");
	}
	
	/**
	 * 2014-2-13 19:46
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @description:保单录入-->保单影像件上传界面
	 */
	@RequestMapping("/cbs/policyImage/viewPolicyImageForAdd.do")
	public ModelAndView viewPolicyImageForAdd(HttpServletRequest request,HttpServletResponse response){
		Long policy_id = new Long(ActionHelper.getNullToStr(request.getParameter("policy_id")));//对应保单号
		IPolicyImageVoModel model = new PolicyImageVoModel(); 
		model.setPolicy_id(policy_id);	//通过报单号得到关联的影像及保单的信息
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyImageService.viewPolicyImageForAdd(model);	 //显示投保单关联影像信息
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());  //把ReturnMsg中的数据放入ReturnMsgHelper的ReturnParams中
		request.setAttribute("rmHelper",rmHelper);	
		return new ModelAndView("ca/cacore/cbs/policyImage/policyImageAdd");
	}
	
	/**
	 * 2014-2-14 15:41
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @description:保单录入-->保单影像件上传界面
	 */
	@RequestMapping("/cbs/policyImage/viewContractImageForAdd.do")
	public ModelAndView viewContractImageForAdd(HttpServletRequest request,HttpServletResponse response){
		Long policy_id = new Long(ActionHelper.getNullToStr(request.getParameter("policy_id")));//对应保单号
		IPolicyImageVoModel model = new PolicyImageVoModel(); 
		model.setPolicy_id(policy_id);	//通过报单号得到关联的影像及保单的信息
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyImageService.viewContractImageForAdd(model);	 //显示投保单关联影像信息
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());  //把ReturnMsg中的数据放入ReturnMsgHelper的ReturnParams中
		request.setAttribute("rmHelper",rmHelper);	
		return new ModelAndView("ca/cacore/cbs/policyImage/contractImageAdd");
	}

	/**
	 * 通过ajax请求,验证机构及保单号是否对应,并反馈给前台页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/CBS/policyImage/validateBranchAndPolicy.do")
	public void validateBranchAndPolicy(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		IPolicyImageModel model = new PolicyImageModel();
		String insBranch_id = ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//获取前台传过来的保险公司机构id
		String policy_code = ActionHelper.getNullToStr(request.getParameter("policy_code"));  //保单号码
		String type = ActionHelper.getNullToStr(request.getParameter("type"));  //获取上传类型的值分别有contract(保单)和policy(投保单)
		model.setInsBranch_id(insBranch_id);//销售机构
		model.setPolicy_code(policy_code);  //保单号码
		IPolicyImageModel imageModel =null;
		if("policy".equals(type)){  //必须这里判断因为调用的是两个不同的service方法
			imageModel = policyImageService.validateBranchAndPolicy(model);  //查询投保单表
		}else if("contract".equals(type)){
			imageModel = policyImageService.validateBranchAndContract(model);//查询保单表
		}
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8"); //解决火狐浏览器ajax返回的data输出为[object XMLDocument]的问题
		if(imageModel != null ){ //info拼接字符串:  投保单号码^保单id^机构编号^机构名称^投保人姓名
			String info = imageModel.getSend_code()+"^"+imageModel.getPolicy_id()+"^"+imageModel.getBranch_id()+"^"+imageModel.getBranch_name()+"^"+imageModel.getApp_name();
			pw.print(info+"");
		}else{
			pw.print(""); //如果没有值,则返回空给界面
		}
	        pw.flush();
	        pw.close();
	}
	
	/**
	 * 2013-12-19
	 * 删除影像文件,分别上传服务器保存的影像及数据库保存的影像相关信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/CBS/policyImage/deletePolicyImage.do")
	public void deletePolicyImage(HttpServletRequest request,HttpServletResponse response){
		String file_id = request.getParameter("file_id");
		boolean boo = deleteImage(request,file_id);
	    response.setContentType("text/html;charset=UTF-8"); //解决火狐浏览器ajax返回的data输出为[object XMLDocument]的问题
	    try {
			PrintWriter pw = response.getWriter();
			if(boo){
				pw.write("删除成功");
			}else{
				pw.write("影像删除出错,请手动进行删除");
			}
			
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		
	}
	
	/**
	 * 2013-12-21
	 * 在保单影像件上传页面返回时检查是否还有影像没有关联,有则进行删除影像文件操作,分别是上传服务器保存的影像及数据库保存的影像相关信息
	 * @param request
	 * @param response
	 * @return
	 * @throws 
	 */
	@RequestMapping("/CBS/policyImage/deletePolicyImageForBack.do")
	public void deletePolicyImageForBack(HttpServletRequest request,HttpServletResponse response){
		String str = request.getParameter("file_id");  //从前台获取需要删除的影像id 是通过","拼接的
		String[] file_id = str.split(",");
		ReturnMsg returnMsg = null;
		int index = 0;
		for(String fileId : file_id){ //遍历分割后的file_id数组,进行删除保存在服务端的影像文件及保存在数据库的影像信息
			index++;
			IPolicyImageModel model = new PolicyImageModel();
			model.setFile_id(fileId);
			returnMsg = policyImageService.getFileByFileId(model);
			
			//获取 id及影像在服务器保存的路径
			String rootPath = request.getSession().getServletContext().getRealPath("/");//工程目录地址
			String seq_id =   (String) returnMsg.getDataTable().get("seq_id");
			String file_path =rootPath + (String) returnMsg.getDataTable().get("file_path");
		    File file = new File(file_path);
		    boolean boo=file.delete(); //删除保存在服务器端的影像文件
		    model.setSeq_id(new Integer(seq_id));
		    policyImageService.deletePolicyImageInfo(model);  //删除本影像在数据库中的相关信息
		}
		

	    response.setContentType("text/html;charset=UTF-8"); //解决火狐浏览器ajax返回的data输出为[object XMLDocument]的问题
	    try {
			PrintWriter pw = response.getWriter();
			if(index == file_id.length){
				pw.write("1");  //1表示删除成功
			}else{
				pw.write("0");  //0表示删除出错
			}
			
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		
	}
	
	/**
	 * 2013-12-21  15:58
	 * 查询投保单影像信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cbs/policyImage/queryPolicyImage.do")
	public ModelAndView queryPolicyImage(HttpServletRequest request,HttpServletResponse response){
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//销售机构id
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//所属公司机构
		String policy_code=ActionHelper.getNullToStr(request.getParameter("policy_code"));//投保单号
		String agent_name=ActionHelper.getNullToStr(request.getParameter("agent_name"));//保单销售人员姓名
		String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人员姓名
		 
		IPolicyImageVoModel model = new PolicyImageVoModel();
		model.setBranch_id(branch_id);//机构
		model.setInsBranch_id(insBranch_id);//保险公司机构
		model.setPolicy_code(policy_code);//保单号
		model.setAgent_name(agent_name);//销售人姓名
		model.setApp_name(app_name);//投保人姓名
		model.setPageCount(ActionHelper.getPage(request));
		
		IUserModel user = ServletHelper.getSessionUser(request); //获取用户
		model.setEmp_id(user.getEmp_id());
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyImageService.queryPolicyImage(model);//查询保单信息
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,model.getPageCount(),true));
		return returnPage(response, returnMsg, "ca/cacore/cbs/policyImage/policyImageQuery");
	}
	
	/**
	 * 2014-2-8 17:04
	 * 查询保单影像信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cbs/policyImage/queryContractImage.do")
	public ModelAndView queryContractImage(HttpServletRequest request,HttpServletResponse response){
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id"));//销售机构id
		String insBranch_id=ActionHelper.getNullToStr(request.getParameter("insBranch_id"));//所属公司机构
		String policy_code=ActionHelper.getNullToStr(request.getParameter("policy_code"));//保单号
		String agent_name=ActionHelper.getNullToStr(request.getParameter("agent_name"));//保单销售人员姓名
		String app_name=ActionHelper.getNullToStr(request.getParameter("app_name"));//投保人员姓名
		 
		IPolicyImageVoModel model = new PolicyImageVoModel();
		model.setBranch_id(branch_id);//机构
		model.setInsBranch_id(insBranch_id);//保险公司机构
		model.setPolicy_code(policy_code);//保单号
		model.setAgent_name(agent_name);//销售人姓名
		model.setApp_name(app_name);//投保人姓名
		model.setPageCount(ActionHelper.getPage(request));
		
		IUserModel user = ServletHelper.getSessionUser(request); //获取用户
		model.setEmp_id(user.getEmp_id());
		
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyImageService.queryContractImage(model);//查询保单信息
		request.setAttribute("rmHelper",new ReturnMsgHelper(request,returnMsg,model.getPageCount(),true));
		return returnPage(response, returnMsg, "ca/cacore/cbs/policyImage/contractImageQuery");
	}
	
	/**
	 * 2013-12-23
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @description:影像管理-->投保单影像件查询-->影像查看方法,通过投保单id
	 */
	@RequestMapping("/cbs/policyImage/viewPolicyImage.do")
	public ModelAndView viewPolicyImage(HttpServletRequest request,HttpServletResponse response){
		Long policy_id = new Long(ActionHelper.getNullToStr(request.getParameter("policy_id")));//对应保单号
		IPolicyImageVoModel model = new PolicyImageVoModel(); 
		model.setPolicy_id(policy_id);	//通过报单号得到关联的影像及保单的信息
		model.setBus_type(CodeTypeConst.POLICY_IMAGE_BUS_TYPE); //设置查看的影像类型是投保单影像类型
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyImageService.viewPolicyImage(model);	 //显示保单关联影像信息
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());  //把ReturnMsg中的数据放入ReturnMsgHelper的ReturnParams中
		request.setAttribute("rmHelper",rmHelper);	
		return new ModelAndView("ca/cacore/cbs/policyImage/policyImageView");
	}
	
	/**
	 * 2014-2-10 10：:50
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	@RequestMapping("/cbs/policyImage/viewContractImage.do")
	public ModelAndView viewContractImage(HttpServletRequest request,HttpServletResponse response){
		Long policy_id = new Long(ActionHelper.getNullToStr(request.getParameter("policy_id")));//对应保单号
		IPolicyImageVoModel model = new PolicyImageVoModel(); 
		model.setPolicy_id(policy_id);	//通过报单号得到关联的影像及保单的信息
		model.setBus_type(CodeTypeConst.CONTRACT_IMAGE_BUS_TYPE); //设置查看的影像类型是保单影像类型
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyImageService.viewContractImage(model);	 //显示保单关联影像信息
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());  //把ReturnMsg中的数据放入ReturnMsgHelper的ReturnParams中
		request.setAttribute("rmHelper",rmHelper);	
		return new ModelAndView("ca/cacore/cbs/policyImage/contractImageView");
	}
	
	/**
	 * 2014-2-10 10：:50
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @description:影像管理-->保单影像件查询-->影像查看方法,通过保单id
	 */
	@RequestMapping("/cbs/policyImage/toModifyContractImage.do")
	public ModelAndView toModifyContractImage(HttpServletRequest request,HttpServletResponse response){
		Long policy_id = new Long(ActionHelper.getNullToStr(request.getParameter("policy_id")));//对应保单号
		IPolicyImageVoModel model = new PolicyImageVoModel(); 
		model.setPolicy_id(policy_id);	//通过报单号得到关联的影像及保单的信息
		model.setBus_type(CodeTypeConst.CONTRACT_IMAGE_BUS_TYPE); //设置查看的影像类型是保单影像类型
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyImageService.viewContractImage(model);	 //显示保单关联影像信息
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());  //把ReturnMsg中的数据放入ReturnMsgHelper的ReturnParams中
		request.setAttribute("rmHelper",rmHelper);	
		return new ModelAndView("ca/cacore/cbs/policyImage/contractImageModify");
	}
	
	/**
	 * 2014-2-13 9：:53
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @description:影像管理-->保单影像件查询-->影像修改方法
	 */
	@RequestMapping("/cbs/policyImage/modifyContractImage.do")
	public ModelAndView modifyContractImage(HttpServletRequest request,HttpServletResponse response){
		String delete_ids[] = request.getParameterValues("delete_id");//要删除图片删除id
		String file_ids[] = request.getParameterValues("file_id");  //影像id,是一个数组
		String policy_id = ActionHelper.getNullToStr(request.getParameter("policy_id"));  //保单id
		String send_code = ActionHelper.getNullToStr(request.getParameter("send_code"));  //投保单id
		String bus_type = ActionHelper.getNullToStr(request.getParameter("bus_type"));  //影像件类型
		IPolicyImageModel model = new PolicyImageModel();
		model.setFile_ids(file_ids);   //把影像数组放入model中
		model.setPolicy_id(new Long(policy_id));  //添加保单id
		model.setSend_code(send_code);  //添加投保单id
		model.setBus_type(bus_type);
		
		ReturnMsg returnMsg=new ReturnMsg();
		
		IUserModel user = ServletHelper.getSessionUser(request); //获取用户
		
		try{
			returnMsg = policyImageService.modifyPolicyImage(model,user); //调用关联方法
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		//删除影像操作,包括服务器保存图片及数据库保存的影像信息
		if(delete_ids != null){
			for(String delete_id : delete_ids){
				deleteImage(request,delete_id);
			}
			if(file_ids == null){
				returnMsg.setSuccessMessage("修改成功");
			}
		}
		IPolicyImageVoModel model2 = new PolicyImageVoModel(); 
		model2.setPolicy_id(new Long(policy_id));	//返回关联的影像信息
		model2.setBus_type(bus_type);
		returnMsg.setDataList(policyImageService.viewContractImage(model2).getDataList());//获取影像list
	
		request.setAttribute("rmHelper",new ReturnMsgHelper(request, returnMsg,true));
		return new ModelAndView("ca/cacore/cbs/policyImage/contractImageModify");
	}
	
	/**
	 * 2014-2-13 16：:46
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @description:影像管理-->投保单影像件查询-->影像查看方法,通过保单id
	 */
	@RequestMapping("/cbs/policyImage/toModifyPolicyImage.do")
	public ModelAndView toModifyPolicyImage(HttpServletRequest request,HttpServletResponse response){
		Long policy_id = new Long(ActionHelper.getNullToStr(request.getParameter("policy_id")));//对应保单号
		IPolicyImageVoModel model = new PolicyImageVoModel(); 
		model.setPolicy_id(policy_id);	//通过报单号得到关联的影像及保单的信息
		model.setBus_type(CodeTypeConst.POLICY_IMAGE_BUS_TYPE); //设置查看的影像类型是投保单影像类型
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg = policyImageService.viewPolicyImage(model);	 //显示投保单关联影像信息
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());  //把ReturnMsg中的数据放入ReturnMsgHelper的ReturnParams中
		request.setAttribute("rmHelper",rmHelper);	
		return new ModelAndView("ca/cacore/cbs/policyImage/policyImageModify");
	}
	
	/**
	 * 2014-2-13 16：:46
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @description:影像管理-->投保单影像件查询-->影像修改方法
	 */
	@RequestMapping("/cbs/policyImage/modifyPolicyImage.do")
	public ModelAndView modifyPolicyImage(HttpServletRequest request,HttpServletResponse response){
		String delete_ids[] = request.getParameterValues("delete_id");//要删除图片删除id
		String file_ids[] = request.getParameterValues("file_id");  //影像id,是一个数组
		String policy_id = ActionHelper.getNullToStr(request.getParameter("policy_id"));  //保单id
		String send_code = ActionHelper.getNullToStr(request.getParameter("send_code"));  //投保单id
		String bus_type = ActionHelper.getNullToStr(request.getParameter("bus_type"));  //影像件类型
		IPolicyImageModel model = new PolicyImageModel();
		model.setFile_ids(file_ids);   //把影像数组放入model中
		model.setPolicy_id(new Long(policy_id));  //添加保单id
		model.setSend_code(send_code);  //添加投保单id
		model.setBus_type(bus_type);
		
		ReturnMsg returnMsg=new ReturnMsg();
		
		
		IUserModel user = ServletHelper.getSessionUser(request); //获取用户
		try{
			returnMsg = policyImageService.modifyPolicyImage(model,user); //调用关联方法
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		//删除影像操作,包括服务器保存图片及数据库保存的影像信息
		if(delete_ids != null){
			for(String delete_id : delete_ids){
				deleteImage(request,delete_id);
			}
			if(file_ids == null){
				returnMsg.setSuccessMessage("修改成功");
			}
		}
		IPolicyImageVoModel model2 = new PolicyImageVoModel(); 
		model2.setPolicy_id(new Long(policy_id));	//返回关联的影像信息
		model2.setBus_type(bus_type);
		returnMsg.setDataList(policyImageService.viewPolicyImage(model2).getDataList());//获取影像list
	
		request.setAttribute("rmHelper",new ReturnMsgHelper(request, returnMsg,true));
		return new ModelAndView("ca/cacore/cbs/policyImage/policyImageModify");
	}
	
	/**
	 * 
	* 2014-2-13 17:24
	* @param request
	* @param file_id
	* @return boolean
	* @description: 删除影像图片方法
	 */
	private boolean deleteImage(HttpServletRequest request,String file_id){
		IPolicyImageModel model = new PolicyImageModel();
		model.setFile_id(file_id);
		ReturnMsg returnMsg = policyImageService.getFileByFileId(model);
		
		//获取 id及影像在服务器保存的路径
		String rootPath = StaticProperties.getProperty("path");//获取虚拟目录硬盘地址用于删除影像件
		String seq_id =   (String) returnMsg.getDataTable().get("seq_id");
		String file_path =rootPath + (String) returnMsg.getDataTable().get("file_path"); //文件保存地址
	    
	    File file = new File(file_path);
	    boolean boo=file.delete(); //删除保存在服务器端的影像文件
	    model.setSeq_id(new Integer(seq_id));
	    policyImageService.deletePolicyImageInfo(model);  //删除本影像在数据库中的相关信息
	    
	    return boo;
	}
	
}
