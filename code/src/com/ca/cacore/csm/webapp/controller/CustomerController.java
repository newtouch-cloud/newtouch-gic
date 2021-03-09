package com.ca.cacore.csm.webapp.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


















import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.BranchModel;
import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.webapp.service.IBranchService;
import com.ca.cacore.common.CodeTypeConst;
import com.ca.cacore.csm.model.bo.CustomerJieChuModel;
import com.ca.cacore.csm.model.bo.CustomerModel;
import com.ca.cacore.csm.model.bo.ICustomerJieChuModel;
import com.ca.cacore.csm.model.bo.ICustomerModel;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.ca.cacore.csm.webapp.service.ICustomerService;
import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.ThreadLocalHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.excel.ExcelWrite;

/**
 * 
* @since:    2013年12月24日   
* @author    newtouchlxy
* @description:客户信息与客户联系人信息管理
 */

@Controller
public class CustomerController extends BaseController{

	@Autowired private ICustomerService customerService ;
	@Autowired private IBranchService branchService;
	@Autowired private ICommonSeqDao seqDao;  //添加客户id
	private Integer flag;

	/**
	 * 客户信息添加
	 * yanqiguang
	 * 2018年1月11日上午11:16:20
	 *  model.setPageCount(ActionHelper.getPage(req));//分页
		ReturnMsg returnMsg =customerService.getVOAll(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true);
		req.setAttribute("rmHelper",rmHelper);                          
		return returnPage(res, returnMsg, "ca/cacore/crm/customer/touchcustomerAdd");
	 * TODO
	 */
	@RequestMapping("/CRM/Customer/goAddCustomer.do")
	public ModelAndView queryCustomer(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("flag", "1");
		return new ModelAndView("ca/cacore/crm/customer/touchcustomerAdd");
	}
	

	/*
	 * @description:重定向到客户查询界面
	 */
	@RequestMapping("/CRM/Customer/toQueryCustomer.do")
	public ModelAndView toQueryCustomer(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/crm/customer/customerQuery");
	}
	
	/**
	* @param req
	* @param res
	* @return
	* @throws ModelAndView
	* @description:重定向到客户添加及维护查询界面
	 */
	@RequestMapping("/CRM/Customer/toAddAndModifyCustomer.do")
	public ModelAndView toAddAndModifyCustomer(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/crm/customer/customerQueryModify");
	}
	
	/**
	* @param req
	* @param res
	* @return
	* @throws ModelAndView
	* @description:重定向到客户分析查询界面
	 */
	@RequestMapping("/CRM/Customer/toAnaCustomer.do")
	public ModelAndView toAnaCustomer(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,null,new PageCount(),true));
		return new ModelAndView("ca/cacore/crm/customer/custQueryAna");
	}
	
   /**
    * 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:客户分析查询页面功能
	 */
	@RequestMapping("/CRM/Customer/custQueryAna.do")
	public ModelAndView getVOAna(HttpServletRequest req
            ,HttpServletResponse res){
		ThreadLocalHelper.set("isOpen", true);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构代码
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));//机构名称
		String customer_type = ActionHelper.getNullToStr(req.getParameter("customer_type"));//客户类型
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//客户代码
		String name = ActionHelper.getNullToStr(req.getParameter("name"));//客户姓名
		String gender = ActionHelper.getNullToStr(req.getParameter("gender"));//客户性别
		String education2 = ActionHelper.getNullToStr(req.getParameter("education2"));//教育程度
		String job_type = ActionHelper.getNullToStr(req.getParameter("job_type"));//职业类型
		String income_type = ActionHelper.getNullToStr(req.getParameter("income_type"));//收入区间
		ICustomerVOModel model = new CustomerVOModel();
		model.setBranch_id(branch_id);
		model.setBranch_name(branch_name);
		model.setCustomer_type(customer_type);
		model.setCustomer_id(customer_id);
		model.setName(name);
		model.setGender(gender);
		model.setEducation2(education2);
		model.setJob_type(job_type);
		model.setIncome_type(income_type);
		model.setPageCount(ActionHelper.getPage(req));//分页
		ReturnMsg returnMsg =customerService.getVOAll(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));	
		return returnPage(res, returnMsg, "ca/cacore/crm/customer/custQueryAna");
	}
	
	/**
	 * 
	* 
	* @param req
	* @param res    许容华
	* @return    
	* @throws Exception ModelAndView
	* @description: 客户添加保存功能          是他  
	* 
	 */
	@RequestMapping("/CRM/Customer/addCustomerAndContact.do")
	public ModelAndView ModeddCustomer(HttpServletRequest req,HttpServletResponse res) throws Exception {
		IUserModel usermodel = ServletHelper.getSessionUser(req);
		String createuser= usermodel.getEmp_id();
		String insured_name = ActionHelper.getNullToStr(req.getParameter("insured_name"));//客户姓名
		String insured_cid = ActionHelper.getNullToStr(req.getParameter("insured_cid"));//客户身份证号
		String insured_company_type = ActionHelper.getNullToStr(req.getParameter("insured_company_type"));
		//String insured_papertype = ActionHelper.getNullToStr(req.getParameter("insured_papertype"));
		String home_address = ActionHelper.getNullToStr(req.getParameter("home_address"));//家庭住址
		String insured_mailbox = ActionHelper.getNullToStr(req.getParameter("insured_mailbox"));//客户邮箱
		String insured_phone = ActionHelper.getNullToStr(req.getParameter("insured_phone"));//客户电话
		String insured_tel = ActionHelper.getNullToStr(req.getParameter("insured_tel"));//客户固话
		//String seq_id = ActionHelper.getNullToStr(req.getParameter("seq_id"));
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));
		ICustomerVOModel model = new CustomerVOModel();
		model.setInsured_name(insured_name);
		model.setInsured_cid(insured_cid);
		model.setInsured_company_type(insured_company_type);
		//model.setInsured_papertype(insured_papertype);
		model.setHome_address(home_address);
		model.setInsured_mailbox(insured_mailbox);
		model.setInsured_phone(insured_phone);
		model.setInsured_tel(insured_tel);
		//model.setSeq_id(seq_id);
		model.setBranch_id(branch_id);
		model.setBranch_name(branch_name);
		model.setCreateUser(createuser);
		ReturnMsg returnMsg =customerService.queryCustomer1(model);	
	    req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		req.setAttribute("removeflag","1");

		//req.setAttrib ute("removeflag","1");
		return new ModelAndView("ca/cacore/crm/customer/touchcustomerAdd");
		}
	
	/**
	 * 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:客户查询页面功能
	 */
	@RequestMapping("/CRM/Customer/customerQuery.do")
	public ModelAndView getVOAll(HttpServletRequest req
            ,HttpServletResponse res){
		ThreadLocalHelper.set("isOpen", true);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构代码
		String member_id = ActionHelper.getNullToStr(req.getParameter("member_id"));//会员编号
		String customer_id=ActionHelper.getNullToStr(req.getParameter("customer_id")); //客户代码
		String name = ActionHelper.getNullToStr(req.getParameter("name"));//客户姓名
		String certi_type=ActionHelper.getNullToStr(req.getParameter("certi_type")); //证件类型
		String certi_no=ActionHelper.getNullToStr(req.getParameter("certi_no")); //证件号码
		Date birthday = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("birthday")));//	出生日期
		String mobile = ActionHelper.getNullToStr(req.getParameter("mobile"));//	移动电话
		String policyno = ActionHelper.getNullToStr(req.getParameter("policyno"));//保单号
		String claimno = ActionHelper.getNullToStr(req.getParameter("claimno"));//赔案号
		String car_no = ActionHelper.getNullToStr(req.getParameter("car_no"));//车牌号
		String insurCount = ActionHelper.getNullToStr(req.getParameter("insurCount"));//出险次数
		//insurCount  policyno car_no claimno
		IUserModel usermodel=ServletHelper.getSessionUser(req);
		ICustomerVOModel model = new CustomerVOModel();
		model.setEmp_id(usermodel.getEmp_id());
		model.setBranch_id(branch_id);
		model.setName(name);
		model.setCustomer_id(customer_id);
		model.setMember_id(member_id);
		model.setName(name);
		model.setCerti_type(certi_type);
		model.setCerti_no(certi_no);
		model.setBirthday(birthday);
		model.setMobile(mobile);
		model.setPolicyno(policyno);
		model.setClaimno(claimno);
		model.setCar_no(car_no);
		model.setInsurCount(insurCount);
		model.setPageCount(ActionHelper.getPage(req));//分页
		ReturnMsg returnMsg =customerService.getVOAll(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));	
		return returnPage(res, returnMsg, "ca/cacore/crm/customer/customerQuery");
		
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:跳转到客户添加及维护页面功能
	*/
	/**           
	 * 
	 * 许容华
	 * 2018年1月11日上午11:10:05   就是它 查询
	 */
	@RequestMapping("/CRM/Customer/goQueryAndModify.do")
	public ModelAndView goQueryAndModify(HttpServletRequest req
            ,HttpServletResponse res){
		ThreadLocalHelper.set("isOpen", true);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构代码
		//String customer_type = ActionHelper.getNullToStr(req.getParameter("customer_type"));//客户类型
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//客户代码
		//String gender=ActionHelper.getNullToStr(req.getParameter("gender")); //性别
		String insured_cid = ActionHelper.getNullToStr(req.getParameter("insured_cid"));//被保险人身份证
		String insured_name = ActionHelper.getNullToStr(req.getParameter("insured_name"));
		String org_id = ActionHelper.getNullToStr(req.getParameter("org_id"));
		String insured_phone = ActionHelper.getNullToStr(req.getParameter("insured_phone"));
		String insured_company_type = ActionHelper.getNullToStr(req.getParameter("insured_company_type"));
		IUserModel usermodel=ServletHelper.getSessionUser(req);
		ICustomerVOModel model = new CustomerVOModel();
		model.setEmp_id(usermodel.getEmp_id());
		model.setBranch_id(branch_id);
		//model.setCustomer_type(customer_type);
		model.setInsured_cid(insured_cid);
		model.setInsured_name(insured_name);
		model.setOrg_id(org_id);
		model.setInsured_phone(insured_phone);
		model.setCustomer_id(customer_id);
		model.setInsured_company_type(insured_company_type);
		model.setDept_list(usermodel.getDept_list());
		
		//model.setGender(gender);
		model.setPageCount(ActionHelper.getPage(req));//分页
		ReturnMsg returnMsg =customerService.getVOAll(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true);
		req.setAttribute("rmHelper",rmHelper);                          
		return returnPage(res, returnMsg, "ca/cacore/crm/customer/customerQueryModify");
		
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:客户查询页面的明细按钮功能
	*/
	@RequestMapping("/CRM/Customer/CustomerQueryView1.do")
	public ModelAndView customerQueryView1(HttpServletRequest req
            ,HttpServletResponse res){
		ThreadLocalHelper.set("isOpen", true);
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//客户代码
		String customer_type=ActionHelper.getNullToStr(req.getParameter("customer_type"));//客户类型
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构代码
		ICustomerModel model = new CustomerModel();
		model.setCustomer_id(customer_id);
		model.setCustomer_type(customer_type);
		model.setBranch_id(branch_id);
		model.setAttachment_type(CodeTypeConst.ATTACHMENT_BUSTYPE_CRMFENXI);
		ReturnMsg returnMsg =customerService.getView(model);
		req.setAttribute("sum1",returnMsg.getDataList().get(0).get("sum1"));
		req.setAttribute("sum2",returnMsg.getDataList().get(0).get("sum2"));
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg,true);
		req.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		if("0".equals(customer_type)){//个人
			return new ModelAndView("ca/cacore/crm/customer/customerQueryView");
		}else{//法人
			return new ModelAndView("ca/cacore/crm/customer/customerQueryViewNew");
		}
	}

	/* *
	* @description:客户查询页面和客户信息维护的明细按钮功能
	*/
	@RequestMapping("/CRM/Customer/CustomerQueryView2.do")
	public ModelAndView getQueryView1(HttpServletRequest req
            ,HttpServletResponse res) throws Exception{
		ThreadLocalHelper.set("isOpen", true);
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//客户代码
		ICustomerModel model = new CustomerModel();
		model.setCustomer_id(customer_id);
		model.setAttachment_type(CodeTypeConst.ATTACHMENT_BUSTYPE_CRMFENXI);
		ReturnMsg returnMsg =customerService.getView(model);
		req.setAttribute("sum1",returnMsg.getDataList().get(0).get("sum1"));
		req.setAttribute("sum2",returnMsg.getDataList().get(0).get("sum2"));
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		req.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/crm/customer/customerQueryView");
		
	}
	
	/**
	 * 修改客户查询  详细
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/CRM/Customer/CustomerModifyView.do")
	public ModelAndView getModifyView(HttpServletRequest req
            ,HttpServletResponse res){
		ThreadLocalHelper.set("isOpen", true);
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//客户代码
		String customer_type= ActionHelper.getNullToStr(req.getParameter("customer_type"));//客户类型
		ICustomerModel model = new CustomerModel();
		model.setAttachment_type(CodeTypeConst.ATTACHMENT_BUSTYPE_CRMFENXI);
		model.setCustomer_id(customer_id);
		ReturnMsg returnMsg =customerService.getView(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		if("0".equals(customer_type)){
			return new ModelAndView("ca/cacore/crm/customer/customerModifyView");
		}else{
			return new ModelAndView("ca/cacore/crm/customer/customerModifyViewNew");
		}
		
	}
	
	/**
	 * 修改客户查询  详细
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/CRM/Customer/CustomerAnaView.do")
	public ModelAndView getAnaView(HttpServletRequest req
            ,HttpServletResponse res){
		ThreadLocalHelper.set("isOpen", true);
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//客户代码
		ICustomerModel model = new CustomerModel();
		model.setCustomer_id(customer_id);
		ReturnMsg returnMsg =customerService.getView(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/crm/customer/customerAnaView");
	}
	
	/**
	 * 客户信息维护 修改查询单个
	 * @param req   许容华
	 * @param res  是他
	 * @return
	
	 */
	@RequestMapping("/CRM/Customer/goModify.do")
	public ModelAndView goModify(HttpServletRequest req,HttpServletResponse res){
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));
		String insured_cid = ActionHelper.getNullToStr(req.getParameter("insured_cid"));
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构代码
		//String customer_type = ActionHelper.getNullToStr(req.getParameter("customer_type"));//客户类型
		//String gender=ActionHelper.getNullToStr(req.getParameter("gender")); //性别
		String insured_name = ActionHelper.getNullToStr(req.getParameter("insured_name"));
		String org_id = ActionHelper.getNullToStr(req.getParameter("org_id"));
		String insured_phone = ActionHelper.getNullToStr(req.getParameter("insured_phone"));
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));
		IUserModel usermodel=ServletHelper.getSessionUser(req);
		ICustomerVOModel model = new CustomerVOModel();
		model.setEmp_id(usermodel.getEmp_id());
		model.setBranch_id(branch_id);
		//model.setCustomer_type(customer_type);
		model.setInsured_cid(insured_cid);
		model.setInsured_name(insured_name);
		model.setOrg_id(org_id);
		model.setInsured_phone(insured_phone);
		model.setCustomer_id(customer_id);
		model.setDept_list(usermodel.getDept_list());
		model.setBranch_name(branch_name);
		ReturnMsg returnMsg =customerService.goModify(customer_id);
		/*ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true);
		req.setAttribute("rmHelper",rmHelper); */
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		

		return new ModelAndView("ca/cacore/crm/customer/customerModifyNew");		
	}
	
	
	/**
	 * 客户信息维护 修改保存
	 * @param req
	 * @param res   许容华
	 * @return   是他   修改
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Exception
	 */
	
	@RequestMapping("/CRM/Customer/modifySave.do")
	public ModelAndView modifySave(HttpServletRequest req
			                        ,HttpServletResponse res){
		String insured_name = ActionHelper.getNullToStr(req.getParameter("insured_name"));
		String insured_cid = ActionHelper.getNullToStr(req.getParameter("insured_cid"));
		String insured_company_type = ActionHelper.getNullToStr(req.getParameter("insured_company_type"));
		String home_address = ActionHelper.getNullToStr(req.getParameter("home_address"));
		String insured_mailbox = ActionHelper.getNullToStr(req.getParameter("insured_mailbox"));
		String insured_phone = ActionHelper.getNullToStr(req.getParameter("insured_phone"));
		String insured_tel = ActionHelper.getNullToStr(req.getParameter("insured_tel"));
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));

		CustomerVOModel model = new CustomerVOModel();
		model.setInsured_name(insured_name);
		model.setInsured_cid(insured_cid);
		model.setInsured_company_type(insured_company_type);
		model.setHome_address(home_address);
		model.setInsured_mailbox(insured_mailbox);
		model.setInsured_phone(insured_phone);
        model.setInsured_tel(insured_tel);
        model.setCustomer_id(customer_id);
        model.setBranch_name(branch_name);
        model.setBranch_id(branch_id);
		//IUserModel user = ServletHelper.getSessionUser(req);;  //待有权限及用户名后再修改
		ReturnMsg returnMsg = customerService.modifySave(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		//req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		req.setAttribute("removeflag","1");

		return new ModelAndView("ca/cacore/crm/customer/customerModifyNew");
		}
	
	
	
	/**
	 * 异步检验机构代码是否存在
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/CRM/Customer/checkBranchidRepeat.do")
	public void checkBranchidRepeat(HttpServletRequest req,HttpServletResponse res) throws IOException{
		IBranchModel model = new BranchModel();
		model.setBranch_id(ActionHelper.getNullToStr(req.getParameter("branch_id").trim()));
		Integer count=branchService.checkRepeatReturnInt(model);
		String returnInfo="";
		if (count!=0) {
			//所查询的机构代码存在
			returnInfo="{isSuccess:"+true+"}";
		}else {
			//机构代码不存在
			returnInfo="{isSuccess:"+false+"}";
		}
		res.getWriter().print(returnInfo);

	}
	
	
	
	/**
	 * 插入操作  异步检验客户（证件类型和证件号码）是否存在 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/CRM/Customer/checkCustomerIsRepeat.do")
	public ModelAndView checkCustomerIsRepeat(HttpServletRequest req,HttpServletResponse res) throws IOException{
		ICustomerModel model = new CustomerModel();
		model.setCerti_no(ActionHelper.getNullToStr(req.getParameter("certi_no").trim()));
		model.setCerti_type(ActionHelper.getNullToStr(req.getParameter("certi_type").trim()));
		boolean rb =true;
		rb = customerService.customerIsRepeat(model); //true 表示客户存在,false表示客户不存在
		String returnInfo ="";
		if (!rb) {//不存在
			returnInfo=true+"";
		}else {//已存在
			returnInfo=false+"";
		}
		res.getWriter().print(returnInfo);
		return null;
	}
	
	/**
	 * 插入操作  异步检验客户（证件类型和证件号码）是否存在 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/CRM/Customer/updateCheckCustomerIsRepeat.do")
	public ModelAndView updateCheckCustomerIsRepeat(HttpServletRequest req,HttpServletResponse res) throws IOException{
		ICustomerModel model = new CustomerModel();
		model.setCerti_no(ActionHelper.getNullToStr(req.getParameter("certi_no").trim()));
		model.setCerti_type(ActionHelper.getNullToStr(req.getParameter("certi_type").trim()));
//		model.setName(ActionHelper.getNullToStr(req.getParameter("name").trim()));
//		model.setBirthday(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("birthday"))));
//		model.setGender(ActionHelper.getNullToStr(req.getParameter("gender").trim()));
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		model.setSeq_id(seq_id);
		boolean rb =true;
		rb = customerService.customerIsRepeat(model);
		String returnInfo ="";
		if (!rb) {//不存在
			returnInfo=true+"";
		}else {//已存在
			returnInfo=false+"";
		}
		res.getWriter().print(returnInfo);
		return null;
	}
	
	/**
	 * 异步检验会员编号是否存在 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/CRM/Customer/checkMemberId.do")
	public ModelAndView checkMemberId(HttpServletRequest req,HttpServletResponse res) throws IOException{
		ICustomerModel model = new CustomerModel();
		model.setMember_id(ActionHelper.getNullToStr(req.getParameter("member_id").trim()));
		
		boolean boo = customerService.checkMemberId(model); //true 表示会员编号不存在,false表示会员编号存在
		String returnInfo ="";
		if (boo) {//不存在
			returnInfo=true+"";
		}else {//已存在
			returnInfo=false+"";
		}
		res.getWriter().print(returnInfo);
		return null;
	}
	
	/**
	 * 客户信息维护导出
	 */
	@RequestMapping("/CRM/Customer/daochuCustomer.do")
	public ModelAndView daochuCustomer(HttpServletRequest req,HttpServletResponse res) throws IOException{
		//获取查询条件信息
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构代码
		String customer_type = ActionHelper.getNullToStr(req.getParameter("customer_type"));//客户类型
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//客户代码
		String name=ActionHelper.getNullToStr(req.getParameter("name")); //客户姓名
		String gender=ActionHelper.getNullToStr(req.getParameter("gender")); //性别
		IUserModel usermodel=ServletHelper.getSessionUser(req);
		ICustomerVOModel model = new CustomerVOModel();
		model.setEmp_id(usermodel.getEmp_id());
		model.setBranch_id(branch_id);
		model.setCustomer_type(customer_type);
		model.setCustomer_id(customer_id);	
		String name2=new String(name.getBytes("ISO-8859-1"),"utf-8");
		model.setName(name2);
		model.setGender(gender);
		
		//获取报表数据
		List<ICustomerVOModel> list = customerService.daochuCustomer(model);
		Map<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		// sheet
			List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
			// row 行数
			for (int j = 0; j < list.size(); j++) {
				Map<String, Object> hashMap = new HashMap<String, Object>();
				ICustomerVOModel vo = list.get(j);
				// cell 每行中每列的值 注意列字不能少
					hashMap.put("列" + 0, ActionHelper.toStr(j+1)); //序号
					hashMap.put("列" + 1, vo.getBranch_id()); //机构代码
					hashMap.put("列" + 2, vo.getBranch_name()); // 机构名称
					hashMap.put("列" + 3, vo.getType_name()); //客户类型  
					hashMap.put("列" + 4, vo.getCustomer_id()); //客户代码
					hashMap.put("列" + 5, vo.getName()); //客户姓名
					hashMap.put("列" + 6, vo.getGender_name());//性别
					hashMap.put("列" + 7, vo.getBirthday()); //出生日期
					hashMap.put("列" + 8, vo.getCerti_type_name()); //证件类型
					hashMap.put("列" + 9, vo.getCerti_no()); //证件号码
					hashMap.put("列" + 10, vo.getEducation_name()); //教育程度
					hashMap.put("列" + 11, vo.getMarital_stat_name()); //婚姻状况 
					hashMap.put("列" + 12, vo.getAddress());//家庭住址
					hashMap.put("列" + 13, vo.getMobile()); //移动电话
					hashMap.put("列" + 14, vo.getEmail()); //电子邮箱
					hashMap.put("列" + 15, vo.getJob_code()); //职业
					
				sheetList.add(hashMap);
			}
			excelMap.put("客户信息管理导出报表", sheetList); 
			// 正常表头  注意列字不能少
			LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
				head.put("列" + 0, "序号");
				head.put("列" + 1, "机构代码");
				head.put("列" + 2, "机构名称");
				head.put("列" + 3, "客户类型");
				head.put("列" + 4, "客户代码");
				head.put("列" + 5, "客户姓名");
				head.put("列" + 6, "性别");
				head.put("列" + 7, "出生日期");
				head.put("列" + 8, "证件类型");
				head.put("列" + 9, "证件号码");
				head.put("列" + 10, "教育程度");
				head.put("列" + 11, "婚姻状况");
				head.put("列" + 12, "家庭住址");
				head.put("列" + 13, "移动电话");
				head.put("列" + 14, "电子邮箱");
				head.put("列" + 15, "职业");
				
			// 设置每个sheet页的表头
			List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
			sheetHead.add(head);
			// 设置多sheet页
			Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
			map.put("客户信息管理导出报表", sheetHead); //对应上面excelMap put的招募情况报表

			ExcelWrite write = new ExcelWrite();
			//设置response 这样就可以前台弹出框进行下载了
			res.setContentType("application/msexcel");
			res.setHeader("Content-disposition", "attachment; filename=customer.xls");
			write.setResponse(res);
			write.setSheetHead(map);
			write.setMergeCell(true);
			//write.addNotLockArea("测试1", "A3:F8");
			// 设置数据，key需要跟sheet页的key相同，值为需要写的数据
			write.writeExcel(excelMap);
			return null;
	}
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	 * @throws IOException 
	* @description:客户接触录入增加页面通过客户id赋值操作
	*/
	@RequestMapping("/CRM/Customer/queryCustomerById.do")
	public ModelAndView queryCustomerById(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//获取客户代码

		ICustomerModel model = new CustomerModel();
		model.setCustomer_id(customer_id); 
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg = customerService.queryJieChu(model);
		
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/crm/customer/customerTouchAdd");
	}
	

	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:增加客户接触录入信息
	*/
	@RequestMapping("/CRM/Customer/updateCustomerJieChu.do")
	public ModelAndView addCustomerAction(HttpServletRequest req,HttpServletResponse res){
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//	客户代码
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构代码
		String name=ActionHelper.getNullToStr(req.getParameter("name"));  //姓名
		String corporation_represen=ActionHelper.getNullToStr(req.getParameter("corporation_represen"));  //姓名
		Date action_time = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("action_time")));//	接触时间
		String action_notes = ActionHelper.getNullToStr(req.getParameter("action_notes"));//	接触内容
		Date createdate=DateUtil.sysDate();
		Date modifydate=DateUtil.sysDate();
		ICustomerJieChuModel model = new CustomerJieChuModel();
		model.setCustomer_id(customer_id);
		model.setAction_time(action_time);
		model.setAction_notes(action_notes);
		model.setCreatedate(createdate);
		model.setModifydate(modifydate);
		model.setBranch_id(branch_id);
		model.setName(name);
		IUserModel user = ServletHelper.getSessionUser(req);//获取用户
		model.setCreateuser(user.getEmp_id());
		model.setModifyuser(user.getEmp_id());
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = customerService.addCustomerJieChu(model, user);
			req.setAttribute("savaHide", "true");
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));
		return new ModelAndView("ca/cacore/crm/customer/customerTouchAdd");
	}
//	/**
//     * 
//    * @param req
//    * @param res
//    * @throws Exception void
//    * @description: 客户价值分析上传操作
//     */
//	 @RequestMapping("/CRM/Customer/uploadClassResult.do")
//	    public ModelAndView uploadClassResult(HttpServletRequest req, HttpServletResponse resp) {
//		 	//上传法律合同附件
//			ReturnMsg returnMsg = AttachmentUploadUtil.uploadAttachment(CodeTypeConst.ATTACHMENT_BUSTYPE_ANNOUNCEMENT,model.getAnnouncement_id(),req,res); //保存文件操作
//			//model.setUpload_time((Date)returnMsg.getDataTable().get("upload_time"));//设置附件上传时间如果没有附件则为null
//	 }
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	 * @throws Exception 
	* @description:客户查询页面的明细按钮功能-由其他地方链接过来
	*/
	@RequestMapping("/CRM/Customer/CustomerQueryView.do")
	public ModelAndView getQueryView(HttpServletRequest req
            ,HttpServletResponse res) throws Exception{
		ThreadLocalHelper.set("isOpen", true);
		
		/*参数请包装为json字符串（参数若含有中文，请惊醒utf-8转码），然后加密 
		参数说明：queryType ，查询类型（1车险，2意外险，3货运险，4财产险） 
		policyNo ，保单号码 
		otherTag ，被保险人身份证号（或组织机构代码） 
		name ，被保险人名称（财产险需要） 
		  
		java中参数示例：车险示例"{\"queryType\":\"1\",\"policyNo\":\"6090904000511000064\",\"otherTag\":\"120108198405030983\"}" 
		意外险示例"{\"queryType\":\"2\",\"policyNo\":\"6090111002703000014\",\"otherTag\":\"0000009071\"}" 
		货运险示例"{\"queryType\":\"3\",\"policyNo\":\"6090111000901000023\",\"otherTag\":\"a\"}" 
		财产险示例"{\"queryType\":\"4\",\"policyNo\":\"6090111002701000013\",\"otherTag\":\"123321197801011234\",\"name\":\""+java.net.URLEncoder.encode("彩", "utf-8")+"\"}" 
*/		
        //链接地址：http://10.75.5.34:9080/search/application/xinzhi/queryInsurance.jsp?param=
		//根据-保单号-去查询-车险类型 保单表CBS_SMC_CMAIN  险种表CBS_SMC_RISKCODE
		String customer_id = ActionHelper.getNullToStr(req.getParameter("customer_id"));//客户代码
		String policyno=ActionHelper.getNullToStr(req.getParameter("policyno"));
		ICustomerModel model = new CustomerModel();
		//model.setCustomer_id(customer_id);
		model.setPolicyno(policyno);
		ICustomerModel m=customerService.getClasscode(model);
		//拼接参数param
		String a="{\"queryType\":\""+m.getBusinesstype()+"\",\"policyNo\":\""+m.getPolicyno()+"\",\"otherTag\":\""+m.getCerti_no()+"\"}";
		String c="{\"queryType\":\""+m.getBusinesstype()+"\",\"policyNo\":\""+m.getPolicyno()+"\",\"otherTag\":\""+m.getCerti_no()+"\",\"name\":\""+java.net.URLEncoder.encode(m.getName(), "utf-8")+"\"}";
		String b=DesUtil.defaultEncrypt(a);//调用加密方法
		String d=DesUtil.defaultEncrypt(c);
		m.setAa(b);
		m.setBb(d);
		req.setAttribute("a", m);
		return  new ModelAndView("ca/cacore/crm/customer/redirectCustomerView");
	}
}
