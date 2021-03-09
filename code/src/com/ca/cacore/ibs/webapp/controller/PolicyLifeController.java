package com.ca.cacore.ibs.webapp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.ca.cacore.ibs.model.bo.PolicyLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeHolderModel;
import com.ca.cacore.ibs.model.bo.PolicyLifeInsuredModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductFeePremVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeInfoVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeProductFeePremVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeVOModel;
import com.ca.cacore.ibs.webapp.service.ICommonAsynRequestService;
import com.ca.cacore.ibs.webapp.service.IPolicyImageService;
import com.ca.cacore.ibs.webapp.service.IPolicyLifeService;
import com.ca.cacore.common.CodeTypeConst;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.csm.model.vo.ICustomerVOModel;
import com.ca.cacore.csm.webapp.service.ICustomerService;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;
import com.ca.cacore.msss.model.bo.ProductLlifeModel;
import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ThreadLocalHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.stringutil.StringUtil;
/**
 * 投保单录入
 * @author szl
 */
@Controller
public class PolicyLifeController   extends BaseController{
	@Autowired private IPolicyLifeService policyLifeService ;
	@Autowired private IPolicyImageService policyImageService;
	@Autowired private ICustomerService customerService;
	@Autowired private ICommonAsynRequestService commonAsynRequestService;
	@Autowired private ICommonSeqDao seqDao; 
	/**
	 * 投保录入
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/policyLife/policyLifeAdd.do")
	public ModelAndView policyLifeAdd(HttpServletRequest req,HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		return new ModelAndView("ca/cacore/cbs/policyLife/policyLifeAdd");
	}
	
	/**
	 * 查询人员明细 （和保单共用）
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/cbs/policyLife/customerView.do")
	public ModelAndView customerView(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		String url_="";
		String insurant_name_arr="";
		String insurant_name_value="";
		String insurant_name="";
		ICustomerVOModel plp = new CustomerVOModel();
		String branch_id=ActionHelper.getNullToStr(request.getParameter("branch_id")).trim();//机构代码
		String customer_id=ActionHelper.getNullToStr(request.getParameter("customer_id")).trim();//顾客人员代码
		String flag=ActionHelper.getNullToStr(request.getParameter("flag"));//标志：1 投保人明细 2 被保险人明细 3 受益人明细
		String policy_tr_id=ActionHelper.getNullToStr(request.getParameter("policy_tr_id"));//选中的tr的id
		String  relation1 = ActionHelper.getNullToStr(request.getParameter("relation1"));//与主被保人关系
		String relation2  = ActionHelper.getNullToStr(request.getParameter("relation2"));//被保人与主被保人关系
		String type_  = ActionHelper.getNullToStr(request.getParameter("type_"));//根据type的类型跳转到view或者update
		//跳转到投保人、被保人、受益人修改页面
		if("1".equals(flag)&&"".equals(type_)){
			plp.setRelation(relation1);
			//返回客户信息
			url_="ca/cacore/cbs/policyLife/policyPeople";
		}else if("2".equals(flag)&&"".equals(type_)){
			url_="ca/cacore/cbs/policyLife/policyLifeInsured";
		}else if("3".equals(flag)&&"".equals(type_)){
			 insurant_name_arr = ActionHelper.getNullToStr(java.net.URLDecoder.decode(request.getParameter("insured_name_value"),"UTF-8"));//被保人的集合
			 insurant_name_value = ActionHelper.getNullToStr(java.net.URLDecoder.decode(request.getParameter("insured_name"),"UTF-8"));//被保人
			 insurant_name = ActionHelper.getNullToStr(java.net.URLDecoder.decode(request.getParameter("insured_customer_id"),"UTF-8"));//被保人
			url_="ca/cacore/cbs/policyLife/policyLifeBeneficiary";
		}

		if("3".equals(flag)&&"".equals(type_)){
			String bene_type  = ActionHelper.getNullToStr(request.getParameter("bene_type"));//收益性质
			String bene_order  = ActionHelper.getNullToStr(request.getParameter("bene_order"));//收益顺序
			String bene_rate = ActionHelper.getNullToStr(request.getParameter("bene_rate"));// 收益比例
			plp.setBene_order(Integer.valueOf(bene_order));
			//plp.setBene_rate(Double.valueOf(bene_rate));
			plp.setInsurant_name(insurant_name);
			plp.setInsurant_name_arr(insurant_name_arr);
			plp.setInsurant_name_value(insurant_name_value);
			plp.setBene_type(bene_type);
		}	
		//跳转到投保人、被保人、受益人查看页面
		if("1".equals(flag)&&"view".equals(type_)){
			plp.setRelation(relation1);
			//返回投保人明细查看页面
			url_="ca/cacore/cbs/policyLifeManage/policyPeopleView";
		}else if("2".equals(flag)&&"view".equals(type_)){
			//被保人信息明细查看页面
			url_="ca/cacore/cbs/policyLifeManage/policyLifeInsuredView";
		}else if("3".equals(flag)&&"view".equals(type_)){
			//返回受益人信息查看页面
			 insurant_name_arr = ActionHelper.getNullToStr(java.net.URLDecoder.decode(request.getParameter("insured_name_value"),"UTF-8"));//被保人的集合
			 insurant_name_value = ActionHelper.getNullToStr(java.net.URLDecoder.decode(request.getParameter("insured_name"),"UTF-8"));//被保人
			 insurant_name = ActionHelper.getNullToStr(java.net.URLDecoder.decode(request.getParameter("insured_customer_id"),"UTF-8"));//被保人
			url_="ca/cacore/cbs/policyLifeManage/policyLifeBeneficiaryView";
		}

		if("3".equals(flag)&&"view".equals(type_)){
			String bene_type  = ActionHelper.getNullToStr(request.getParameter("bene_type"));//收益性质
			String bene_order  = ActionHelper.getNullToStr(request.getParameter("bene_order"));//收益顺序
			String bene_rate = ActionHelper.getNullToStr(request.getParameter("bene_rate"));// 收益比例
			plp.setBene_order(Integer.valueOf(bene_order));
			plp.setBene_rate(Double.valueOf(bene_rate));
			plp.setInsurant_name(insurant_name);
			plp.setInsurant_name_arr(insurant_name_arr);
			plp.setInsurant_name_value(insurant_name_value);
			plp.setBene_type(bene_type);
		}
		
		plp.setCustomer_id(customer_id);
		plp.setPolicy_tr_id(policy_tr_id);
		plp.setFlag(flag);
		plp.setRelation1(relation1);
		plp.setRelation2(relation2);
		plp.setBranch_id(branch_id);
		// 查询客户信息
		ReturnMsg returnMsg = policyLifeService.getCustomerView(plp);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(request,returnMsg,true);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		request.setAttribute("rmHelper",rmHelper); 
		return  new ModelAndView(url_);
	}
	
	/**
	 * 修改产品
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/policyLife/policyProductUpdate.do")
	public ModelAndView PolicyProductUpdate(HttpServletRequest req,HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		IPolicyLifeVOModel  model = new PolicyLifeVOModel();
		
		String type_ = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("type_"),"UTF-8"));// 
		String policy_tr_id = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("policy_tr_id"),"UTF-8"));// 
		String product_name = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("product_name"),"UTF-8"));// 产品名称
		String coverage_time = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("coverage_time"),"UTF-8"));//保额、档次、份数 
		String name = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("name"),"UTF-8"));//被保人姓名
		String product_id = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("product_id"),"UTF-8"));//产品id
		String charge_year = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("charge_year"),"UTF-8"));//保险期间
		String coverage_year = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("coverage_year"),"UTF-8"));//缴费年龄或年限
		String amount = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("amount"),"UTF-8"));//  保额
		String period_prem = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("period_prem_id"),"UTF-8"));//  保费
		String auth_payage = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("auth_payage"),"UTF-8"));//  年金领取年龄
		String auth_firstpay = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("auth_firstpay"),"UTF-8"));//  首期领取金额
		
		String coverage_period_value = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("coverage_period_value"),"UTF-8"));//  保障期限类型
		String charge_period = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("charge_period_value"),"UTF-8"));//  缴费期限类型
		String charge_type_value = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("charge_type_value"),"UTF-8"));//  缴费方式
		String auth_draw = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("auth_draw"),"UTF-8"));//  年金领取方式
		String dividend_choice = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("dividend_choice"),"UTF-8"));//  红利领取方式
		String is_autoren = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("is_autoren"),"UTF-8"));//  是否自动续保
		String insurant_name = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("insured_customer_id"),"UTF-8"));//被保人id集合
		String insurant_name_arr = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("policyPeople_name_value"),"UTF-8"));//被保人名称数组
		String insurant_name_selected = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("insurant_select"),"UTF-8"));//被保人名称id
		String renew = ActionHelper.getNullToStr(req.getParameter("renew"));//
		String seq_id = ActionHelper.getNullToStr(req.getParameter("product_seq_id"));//  产品的seq_id
		
		model.setPolicy_tr_id(policy_tr_id);
		model.setName(name);
		model.setProduct_name(product_name);
		model.setCoverage_time(coverage_time);
		model.setProduct_id(product_id);
		model.setCharge_year(Integer.valueOf(charge_year));
		model.setCoverage_year(Integer.valueOf(coverage_year));
		if(!"".equals(amount)){//保额不为空
			model.setAmount(Double.valueOf(amount));
		}
		if(seq_id!=null&&!"".equals(seq_id)){
			model.setSeq_id(Integer.valueOf(seq_id));//接受seq_id
		}
		
		model.setPeriod_prem(Double.valueOf(period_prem));
		if(!"".equals(auth_payage)){
			model.setAuth_payAge(Integer.valueOf(auth_payage));
		}
		if(!"".equals(auth_firstpay)){
			model.setAuth_firstPay(Double.valueOf(auth_firstpay));
		}
		if(!"".equals(auth_draw)){
			model.setAuth_draw(auth_draw);
		}
		if(!"".equals(dividend_choice)){
			model.setDividend_choice(dividend_choice);
		}
		model.setCoverage_period(coverage_period_value);
		model.setCharge_period(charge_period);
		model.setCharge_type(charge_type_value);
		model.setIs_autoRen(is_autoren);
		model.setInsurant_name(insurant_name);
		model.setInsurant_name_arr(insurant_name_arr);
		model.setInsurant_name_selected(insurant_name_selected);
		model.setRenew(renew);
		
		ReturnMsg msg = new ReturnMsg();
		msg.setDataTable(TransHelper.obj2map(model));
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,msg);
		rmHelper.setReturnParams(msg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		String url_="";
		if("view".equals(type_)){//产品明细
			url_="ca/cacore/cbs/policyLifeManage/productLlifeView";
		}else{//产品修改
			url_="ca/cacore/cbs/policyLife/productLlife";
		}
		return new ModelAndView(url_);
	}
	
	/**
	 * 客户信息保存方法：被保人、投保人、受益人（和保单共用）
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/policyLife/policyLifeSaveInfo.do")
	public ModelAndView policyLifeSaveInfo(HttpServletRequest req,HttpServletResponse res) throws Exception {
		IUserModel user = ActionHelper.getUserFromSession(req);
		IPolicyLifePeopleVOModel customer = new PolicyLifePeopleVOModel();//客户基本信息
		String name = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("name"),"UTF-8"));//投保人姓名
		String customer_id =  ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("customer_id"),"UTF-8"));//客户Iid
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构代码
		String certi_type = ActionHelper.getNullToStr(req.getParameter("certi_type"));//证件类型
		String certi_no = ActionHelper.getNullToStr(req.getParameter("certi_no"));//投保人证件号码
		String gender = ActionHelper.getNullToStr(ActionHelper.getNullToStr(req.getParameter("gender")));//性别
		Date birthday = DateUtil.string2Date( ActionHelper.getNullToStr(req.getParameter("birthday")));//出生日期
		String nationality = ActionHelper.getNullToStr((req.getParameter("nationality")));//国籍
		String nation = ActionHelper.getNullToStr(req.getParameter("nation"));//民族
		String marital_stat = ActionHelper.getNullToStr(req.getParameter("marital_stat"));//婚姻状况
		String political = ActionHelper.getNullToStr(req.getParameter("political"));//政治面貌
		String homeplace = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("homeplace"),"UTF-8"));//籍贯
		String education2 = ActionHelper.getNullToStr(req.getParameter("education2"));//教育程度
		String health = ActionHelper.getNullToStr(req.getParameter("health"));//健康状况
		Integer height = ActionHelper.getNullToInteger(req.getParameter("height"));//身高
		Integer weight = ActionHelper.getNullToInteger(req.getParameter("weight"));//体重kg
		String mobile = ActionHelper.getNullToStr(req.getParameter("mobile"));//移动电话
		String telphone = ActionHelper.getNullToStr(req.getParameter("telphone"));//住宅电话
		String fax = ActionHelper.getNullToStr(req.getParameter("fax"));//传真电话
		String email = ActionHelper.getNullToStr(req.getParameter("email"));//电子邮箱
		String address = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("address"),"UTF-8"));//家庭住址
		String zip = ActionHelper.getNullToStr(req.getParameter("zip"));//邮政编码
		String job_com = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("job_com"),"UTF-8"));//工作单位名称
		//String job_type = ActionHelper.getNullToStr(req.getParameter("job_type"));//职业类别 后来去掉职业类别
		String job_code = ActionHelper.getNullToStr(java.net.URLDecoder.decode(req.getParameter("job_code"),"UTF-8"));//职业代码
		String job_tel = ActionHelper.getNullToStr(req.getParameter("job_tel"));//办公电话
	     String certi_validDateStr =ActionHelper.getNullToStr(req.getParameter("certi_validDate"));//证件有效期限
		 Date certi_validDate = DateUtil.string2Date(certi_validDateStr);
		 String is_telMsgService =ActionHelper.getNullToStr(req.getParameter("is_telMsgService"));//是否接受手机短信服务
		String income_type = ActionHelper.getNullToStr(req.getParameter("income_type"));// 收入
		customer.setBranch_id(branch_id);
		customer.setCustomer_type(CodeTypeConst.CUSTOMER_TYPE_INSURED);// 客户类型 1 准客户 ，2 客户
		customer.setCustomer_id(customer_id);// 更新时的客户id
		customer.setName(name);
		customer.setHomeplace(homeplace);
		customer.setGender(gender);
		customer.setBirthday(birthday);
		customer.setCerti_type(certi_type);
		customer.setCerti_no(certi_no);
		customer.setEducation(education2);
		customer.setNationality(nationality);
		customer.setNation(nation);
		customer.setHeight(height);
		customer.setWeight(weight);
		customer.setPolitical(political);
		customer.setEducation2(education2);
		customer.setMarital_stat(marital_stat);
		customer.setHealth(health);
		//customer.setJob_type(job_type);
		customer.setJob_code(job_code);
		customer.setStatus(CodeTypeConst.CUSTOMER_STATUS_EFFECTIVE);// 默认为1准客户
		customer.setAddress(address);
		customer.setZip(zip);
		customer.setMobile(mobile);
		customer.setFax(fax);
		customer.setTelphone(telphone);
		customer.setEmail(email);
		customer.setJob_com(job_com);
		customer.setJob_tel(job_tel);
		customer.setCerti_validDate(certi_validDate);//
		customer.setIs_telMsgService(is_telMsgService);// 是否接受短信服务
		customer.setIncome_type(income_type);// 收入
			//根据五要素查询返回结果
	    	List<IPolicyLifePeopleVOModel> validate = commonAsynRequestService.getCustomerInfo(customer);
			if (validate == null || validate.size() == 0) {
				// 增加客户基本信息
				customer_id = policyLifeService.addCustomerAndContact(customer,user);
			} else {
				//修改客户信息a
				customer.setCustomer_id(validate.get(0).getCustomer_id());
				customer_id = policyLifeService.modifyCustomerAndContact(customer, user);
			}
		   res.getWriter().print(customer_id.toString());
		  return null;
	}
	
	/**
	 * 投保单保存方法
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/policyLife/policyLifeSave.do")
	public ModelAndView policyLifeSave(HttpServletRequest req,HttpServletResponse res) throws Exception {
		IUserModel user = ActionHelper.getUserFromSession(req);
		//获取信息 -并组装数据
		String url="";
		PolicyLifeInfoVOModel vo = this.getPolicyLifeInfo(req);
		//添加的时候生成保单id
		String policy_idStr = this.createId(vo.getBranch_id(), CodeTypeConst.POLICYLIFE_SERIES_CODE);
		Long policy_id =Long.valueOf(policy_idStr);
		vo.setPolicy_id(policy_id);
		//调用保存的方法
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = policyLifeService.saveAllPolicyLife(vo, user);
			 policyLifeService.addPolicylifePrem(vo, user);
			 //核心接口
			 vo = policyLifeService.coreCheck(vo, user);//
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
		}
		if("true".equals(vo.getResult_flag())){
			//跳转到影像上传页面
			vo = new PolicyLifeInfoVOModel();//清空数据
			vo.setResult_flag("true");
			vo.setPolicy_id(policy_id);
			returnMsg.setSuccessMessage("保存成功!是否上传影像文件?");
			/*IPolicyImageVoModel model = new PolicyImageVoModel(); 
			model.setPolicy_id(policy_id);	//通过报单号得到关联的影像及保单的信息
			returnMsg = policyImageService.viewPolicyImageForAdd(model);	 //显示投保单关联影像信息
			ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
			rmHelper.setReturnParams(returnMsg.getDataTable());  //把ReturnMsg中的数据放入ReturnMsgHelper的ReturnParams中
			req.setAttribute("rmHelper",rmHelper);	
			url = "ca/cacore/cbs/policyImage/policyImageModify";*/
			url = "ca/cacore/cbs/policyLife/policyLifeAdd";
			
		}else{
			vo.setResult_flag("false");
			//returnMsg.setSuccessMessage("保存失败");
			url = "ca/cacore/cbs/policyLifeManage/policyLifeModify";
		}
		if(vo.getLog()!=null){
			returnMsg.setSuccessMessage(vo.getLog());
		}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg,true);
		List<PolicyLifeInfoVOModel> list = new ArrayList<PolicyLifeInfoVOModel>();
		list.add(vo);
		returnMsg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper",rmHelper);
		return new ModelAndView(url);
	}
	
	/**
	 * 更新投保单
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/policyLife/policyLifeUpdate.do")
	public ModelAndView policyLifeUpdate(HttpServletRequest req,HttpServletResponse res) throws Exception {
		IUserModel user = ActionHelper.getUserFromSession(req);
		//获取信息 -并组装数据
		PolicyLifeInfoVOModel vo = this.getPolicyLifeInfo(req);
		//调用更新的方法
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = policyLifeService.modifyAllPolicyLife(vo, user);
			policyLifeService.modifyPolicylifePrem(vo, user);
			returnMsg.setSuccessMessage("修改成功");
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
		}
		vo.setResult_flag("true");
		List<PolicyLifeInfoVOModel> list = new ArrayList<PolicyLifeInfoVOModel>();
		list.add(vo);
		returnMsg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		req.setAttribute("rmHelper",  new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		
		return new ModelAndView("ca/cacore/cbs/policyLifeManage/policyLifeModify");
	}
	/**
	 * 投保单request方法 参数组装
	 * @param req
	 * @param res
	 * @return PolicyLifeInfoVOModel 投保单vo实体 
	 */
	public  PolicyLifeInfoVOModel getPolicyLifeInfo(HttpServletRequest req){
		PolicyLifeInfoVOModel vo = new PolicyLifeInfoVOModel();
		String send_code = ActionHelper.getNullToStr(req.getParameter("send_code"));//投保单号
		String policy_idStr = ActionHelper.getNullToStr(req.getParameter("policy_id"));//保单id
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司机构
		String agent_id = ActionHelper.getNullToStr(req.getParameter("agent_id"));//销售人员id
		String agent_name = ActionHelper.getNullToStr(req.getParameter("agent_name"));//销售人员 回显
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构id
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));//机构名称 回显
		String high_policy = ActionHelper.getNullToStr(req.getParameter("high_policy"));//高额件
		Date hold_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("hold_date")));//投保日期
		Double period_prem = ActionHelper.getNullToDouble(req.getParameter("period_prem"));//保费合计
		String bank_code = ActionHelper.getNullToStr(req.getParameter("bank_code"));//开户银行
		String pay_mode = ActionHelper.getNullToStr(req.getParameter("pay_mode"));//首期付款方式
		String pay_mode_next = ActionHelper.getNullToStr(req.getParameter("pay_mode_next"));//续期付款方式
		String account_type = ActionHelper.getNullToStr(req.getParameter("account_type"));//账户类型
		String bank_accName = ActionHelper.getNullToStr(req.getParameter("bank_accName"));//银行开户人
		String money_id = ActionHelper.getNullToStr(req.getParameter("money_id"));//币种
		String bank_account = ActionHelper.getNullToStr(req.getParameter("bank_account"));//银行账号
		String overdue_manage = ActionHelper.getNullToStr(req.getParameter("overdue_manage"));//保费逾期来付选择
		String seq_id_plf = ActionHelper.getNullToStr(req.getParameter("seq_id"));//投保单的seq_id
		String status = ActionHelper.getNullToStr(req.getParameter("status"));//投保单的状态
		String insurant_id = "";//第一被保人
		String holder_id ="";
		Long policy_id=0l;//初始化
		if(!"".equals(policy_idStr)){
			policy_id = Long.valueOf(policy_idStr);//如果是更新就获取保单id
		}
		//投保人信息
		String[] policyPeople_customer_id = req.getParameterValues("policyPeople_customer_id");//投保人的客户id
		if(policyPeople_customer_id!=null&&policyPeople_customer_id.length!=0){
			holder_id  = policyPeople_customer_id[0];
		}
		String[] policyPeople_relation = req.getParameterValues("policyPeople_relation_code");//投保人与主被保人的关系
		String[] policyPeople_age = req.getParameterValues("policyPeople_age");//被保人年龄：非必输项
		//回显使用信息
		String[] policyPeople_relation_name = req.getParameterValues("policyPeople_relation1_name");//投保人与主被保人的关系名称
		String relation  = policyPeople_relation[0];
		//被保人信息
		String[] policyLifeInsured_customer_id = req.getParameterValues("policyLifeInsured_customer_id");//被保人的客户id
		String[] policyLifeInsured_relation1 = req.getParameterValues("policyLifeInsured_relation1");//被保人——与投保人关系 0
		String[] policyLifeInsured_relation_code = req.getParameterValues("policyLifeInsured_relation2");//被保人——与主被保人关系 0
		String[] policyLifeInsured_age = req.getParameterValues("policyLifeInsured_age");//投保人年龄：非必输项
		//显示名称   
		String[] policyLifeInsured_relation1_name = req.getParameterValues("policyLifeInsured_relation1_name");//被保人——与投保人关系 0
		String[] policyLifeInsured_relation2_name = req.getParameterValues("policyLifeInsured_relation2_name");//被保人——与主被保人关系 0
		List<PolicyLifeInsuredModel>insurantList = new ArrayList<PolicyLifeInsuredModel>();//被保人集合
		
		//受益人信息
		String[] policyLifeBeneficiary_customer_id = req.getParameterValues("policyLifeBeneficiary_customer_id");//受益人客户id
		String[] policyLifeBeneficiary_insurant_name_value = req.getParameterValues("policyLifeBeneficiary_insurant_name_value");//受益人对应的被保人客户id
		String[] policyLifeBeneficiary_relation1 = req.getParameterValues("policyLifeBeneficiary_relation1");//受益人——与投保人关系 0
		String[] policyLifeBeneficiary_relation_code = req.getParameterValues("policyLifeBeneficiary_relation2");//受益人——与主被保人关系 
		String[] policyLifeBeneficiary_bene_type_code = req.getParameterValues("policyLifeBeneficiary_bene_type_code");//受益性质 0
		String[] policyLifeBeneficiay_bene_order = req.getParameterValues("policyLifeBeneficiary_bene_order");//受益顺序 
		String[] policyLifeBeneficiay_bene_rate = req.getParameterValues("policyLifeBeneficiary_bene_rate");//受益比例
		String[] policyLifeBeneficiary_age = req.getParameterValues("policyLifeBeneficiary_age");//受益人年龄 
		List<PolicyLifeBeneficiaryModel>beneficiaryList = new ArrayList<PolicyLifeBeneficiaryModel>();//受益人集合
		
		//名称
		String[] policyLifeBeneficiary_relation1_name = req.getParameterValues("policyLifeBeneficiary_relation1_name");//受益人——与投保人关系 
		String[] policyLifeBeneficiary_relation2_name = req.getParameterValues("policyLifeBeneficiary_relation2_name");//受益人——与主被保人关系 
		String[] policyLifeBeneficiary_bene_type_name = req.getParameterValues("policyLifeBeneficiary_bene_type_name");//受益性质名称
		
		//产品相关
		String[] product_id_arr = req.getParameterValues("productLlife_product_id");//产品代码
		String[] product_insurant_id = req.getParameterValues("productLlife_customer_id");//产品和被保人对应
		String[] coverage_period_arr = req.getParameterValues("productLlife_coverage_period");//保障期限类型
		String[] coverage_year_arr = req.getParameterValues("productLlife_coverage_year");//保障时间
		String[] charge_period_arr = req.getParameterValues("productLlife_charge_period");//缴费期限类型
		String[] charge_year_arr = req.getParameterValues("productLlife_charge_year");//缴费年数
		String[] charge_type_arr = req.getParameterValues("productLlife_charge_type");//缴费方式
		String[] amount_arr = req.getParameterValues("productLlife_amount");//基本保障金额
		String[] period_prem_arr = req.getParameterValues("productLlife_period_prem");//保费
		String[] auth_payAge_arr = req.getParameterValues("productLlife_auth_payage");//年金领取年龄:非必输项
		String[] auth_draw_arr = req.getParameterValues("productLlife_auth_draw");//年金领取方式 :非必输项
		String[] auth_firstPay_arr = req.getParameterValues("productLlife_auth_firstpay");//首期领取金额 :非必输项
		String[] dividend_choice_arr =req.getParameterValues("productLlife_dividend_choice");//红利领取方式 :非必输项
		String[] is_autoRen_arr =req.getParameterValues("productLlife_is_autoren");//是否自动续保
		//回显
		String[] productLlife_charge_type_name = req.getParameterValues("productLlife_charge_type_name");//缴费方式名称
		String[] productLlife_charge_period_name =req.getParameterValues("productLlife_charge_period_name");//缴费期限类型名称
		String[] productLlife_insurant_name =req.getParameterValues("productLlife_insurant_name");//缴费期限类型名称
		//修改使用seq_id
		String [] seq_arr = req.getParameterValues("product_seq_id");//产品相关的seq_id
		
		//影像上传信息
		String file_ids[] = req.getParameterValues("file_id");
		
	    List<IPolicyLifeProductFeePremVOModel>productList = new ArrayList<IPolicyLifeProductFeePremVOModel>();//险种集合
	    List<IPolicyLifePeopleVOModel> holderListView  = new ArrayList<IPolicyLifePeopleVOModel>();//投保人集合
		List<IPolicyLifePeopleVOModel>insurantListView = new ArrayList<IPolicyLifePeopleVOModel>();//被保人集合
		List<IPolicyLifePeopleVOModel >beneficiaryListView = new ArrayList<IPolicyLifePeopleVOModel>();//受益人集合
		List<IPolicyLifeProductVOModel >productListView = new ArrayList<IPolicyLifeProductVOModel>();//险种集合
		
		if(seq_id_plf!=null&&!"".equals(seq_id_plf)){
			vo.setSeq_id(Integer.valueOf(seq_id_plf));//修改的时候接受页面的seq_id
		}
		if(status!=null&&!"".equals(status)){
			vo.setStatus(status);
		}
		vo.setPolicy_id(policy_id);//保单Id 更新时接收
		vo.setBranch_id(branch_id);//机构代码
		vo.setInsBranch_id(insBranch_id);//保险公司机构
		vo.setSend_code(send_code);//投保单号
		vo.setPolicy_code(send_code);
		vo.setRelation_id(relation);//投保人主被保人关系
		vo.setAgent_id(agent_id);
		vo.setService_id(agent_id);
		vo.setHolder_id(policyPeople_customer_id[0]);//投保人
		vo.setHigh_policy(high_policy);
		vo.setBank_code(bank_code);
		vo.setAccount_type(account_type);
		vo.setBank_accName(bank_accName);
		vo.setMoney_id(money_id);
		vo.setBank_account(bank_account);
		vo.setSell_way(CodeTypeConst.POLICYLIFE_SELL_WAY_PERSONAL);//销售方式 个险
		vo.setPay_mode(pay_mode);//当期付款方式
		vo.setPay_mode_next(pay_mode_next);//续期付款方式
		vo.setPolicy_year(CodeTypeConst.POLICYLIFE_YEAR);//保单年度 默认为1
		vo.setPolicy_period(CodeTypeConst.POLICYLIFE_PERIOD);//缴费期次默认为1
		vo.setPeriod_prem(period_prem);//保费合计
		vo.setMoney_id(money_id);//币种
		vo.setBank_code(bank_code);//开户银行代码
		vo.setAccount_type(account_type);//银行账号类型
		vo.setBank_accName(bank_accName);//银行开户人
		vo.setBank_account(bank_account);//银行账号
		vo.setOverdue_manage(overdue_manage);//保险费逾期未付的选择
		vo.setHold_date(hold_date);//投保日期
		//vo.setValidate_date(hold_date);//保单生效日期
		vo.setIs_answered(CodeTypeConst.CONTRACTLIFE_IS_ANSWER_N);//是否已回访
		vo.setHigh_policy(high_policy);//是否高额件
		vo.setBranch_name(branch_name);
		vo.setAgent_name(agent_name);
	
		//组装投保人信息
		ICustomerVOModel customerVOModel = new CustomerVOModel();
		customerVOModel.setBranch_id(branch_id);//保险公司id
		customerVOModel.setCustomer_id(policyPeople_customer_id[0]);;//保险公司id
		//根据机构和客户id查询客户详细信息
		ICustomerVOModel  holderCustomer=customerService.getCustomerNewInfo(customerVOModel);
		PolicyLifeHolderModel holderModel = new PolicyLifeHolderModel();//投保人model
		holderModel.setBranch_id(branch_id);//销售机构id
		holderModel.setInsbranch_id(insBranch_id);//保险公司机构
		holderModel.setCustomer_id(holder_id);//投保人客户号
		holderModel.setApp_name(holderCustomer.getName());//投保人姓名
		holderModel.setApp_age(Integer.valueOf(policyPeople_age[0]));//投保人年龄 
		holderModel.setApp_certi_code(holderCustomer.getCerti_type());//证件类型
		holderModel.setApp_certi_no(holderCustomer.getCerti_no());//证件号码
		holderModel.setApp_address(holderCustomer.getAddress());//地址
		holderModel.setApp_tel(holderCustomer.getMobile());//电话
		holderModel.setApp_zip(holderCustomer.getZip());//邮编
		vo.setHolderModel(holderModel);//投保人集合
		
		//回显投保人信息
		IPolicyLifePeopleVOModel holderVOView = new  PolicyLifePeopleVOModel();
		holderVOView.setCustomer_id(holderCustomer.getCustomer_id());
		holderVOView.setGender_name(holderCustomer.getGender_name());//性别名称
		holderVOView.setCerti_type_name(holderCustomer.getCerti_type_name());//证件类型名称
		holderVOView.setCustomer_id(holder_id);//投保人客户号
		holderVOView.setName(holderCustomer.getName());//投保人姓名
		holderVOView.setCerti_type(holderCustomer.getCerti_type());//证件类型
		holderVOView.setCerti_no(holderCustomer.getCerti_no());//证件号码
		holderVOView.setAddress(holderCustomer.getAddress());//地址
		holderVOView.setMobile(holderCustomer.getMobile());//电话
		holderVOView.setEmail(holderCustomer.getEmail());
		if(policyPeople_age!=null&&policyPeople_age.length!=0){
			holderVOView.setAge(Integer.valueOf(policyPeople_age[0]));//非必输项判断
		}
		holderListView.add(holderVOView);//投保人回显集合
		vo.setRelation_name(policyPeople_relation_name[0]);//投保人与主被保人关系
		vo.setHolderListView(holderListView);//
		 
		for(int i = 0 ; i < policyLifeInsured_customer_id.length ; i++){
			//组装被保人信息
			PolicyLifeInsuredModel insuredModel = new PolicyLifeInsuredModel();
			insuredModel.setBranch_id(vo.getBranch_id());//销售机构
			insuredModel.setInsbranch_id(vo.getInsBranch_id());//保险公司机构
			IPolicyLifePeopleVOModel insurantVOView = new  PolicyLifePeopleVOModel();//回显被保人使用的model
			ICustomerVOModel  insurantCustomer = new CustomerVOModel();//查询被保人信息使用的model
			String relation2 = policyLifeInsured_relation_code[i];
			insuredModel.setCustomer_id(policyLifeInsured_customer_id[i]);//客户Id
			insuredModel.setRelation1(policyLifeInsured_relation1[i]);//与投保人关系
			insuredModel.setRelation2(relation2);//与主被保人关系
			if(CodeTypeConst.POLICYLIFE_APPLICANT_RELATION2_MYSELF.equals(relation2)){
				insurant_id = policyLifeInsured_customer_id[i];//第一被保人
			}
			//查询被保人的客户信息
			customerVOModel.setCustomer_id(policyLifeInsured_customer_id[i]);
			insurantCustomer = customerService.getCustomerNewInfo(customerVOModel);
			insuredModel.setInsurant_age(Integer.valueOf(policyLifeInsured_age[i]));//年龄
			insuredModel.setInsurant_certi_code(insurantCustomer.getCerti_type());//证件类型
			insuredModel.setInsurant_certi_no(insurantCustomer.getCerti_no());//证件号码
			insuredModel.setInsurant_name(insurantCustomer.getName());//名字
			insuredModel.setInsurant_tel(insurantCustomer.getMobile());//电话
			insurantList.add(insuredModel);
			//回显信息
			insurantVOView.setCustomer_id(policyLifeInsured_customer_id[i]);
			insurantVOView.setName(insurantCustomer.getName());
			insurantVOView.setMobile(insurantCustomer.getMobile());
			insurantVOView.setGender_name(insurantCustomer.getGender_name());
			insurantVOView.setRelation1(policyLifeInsured_relation1[i]);
			insurantVOView.setRelation2(relation2);
			insurantVOView.setAge(Integer.valueOf(policyLifeInsured_age[i]));//
			insurantVOView.setCerti_no(insurantCustomer.getCerti_no());
			insurantVOView.setCerti_type(insurantCustomer.getCerti_type());
			insurantVOView.setCerti_type_name(insurantCustomer.getCerti_type_name());
			insurantVOView.setAddress(insurantCustomer.getAddress());
			insurantVOView.setEmail(insurantCustomer.getEmail());
			insurantVOView.setRelation1_name(policyLifeInsured_relation1_name[i]);//被保人——与投保人关系 
			insurantVOView.setRelation2_name(policyLifeInsured_relation2_name[i]);//被保人——与主关被保人关系 
			insurantListView.add(insurantVOView);//添加被保人显示集合
		}
		vo.setInsurantList(insurantList);//被保人的集合
		vo.setInsurantListView(insurantListView);//被保人显示集合
		vo.setInsurant_id(insurant_id);//第一被保人
		
	
		ICustomerVOModel  beneCustomer = new CustomerVOModel();//查询受益人信息的Model
		for(int i = 0 ; i < policyLifeBeneficiary_customer_id.length ; i++){
			//组装受益人信息
			PolicyLifeBeneficiaryModel beneficiaryModel= new PolicyLifeBeneficiaryModel();
			beneficiaryModel.setBranch_id(branch_id);//销售机构
			beneficiaryModel.setInsBranch_id(insBranch_id);//保险公司机构
			IPolicyLifePeopleVOModel beneVOView = new  PolicyLifePeopleVOModel();//回显受益人使用的model
			String customer_id = policyLifeBeneficiary_customer_id[i];
			String bene_type = policyLifeBeneficiary_bene_type_code[i];
			Integer beneOrder =Integer.valueOf(policyLifeBeneficiay_bene_order[i]) ;
			Double bene_rate = Double.valueOf(policyLifeBeneficiay_bene_rate[i]);
			beneficiaryModel.setCustomer_id(customer_id);//受益人客户Id
			beneficiaryModel.setBene_order(beneOrder);//收益顺序
			beneficiaryModel.setBene_rate(bene_rate);//收益比例
			beneficiaryModel.setBene_type(bene_type);//收益性质
			beneficiaryModel.setInsurant_id(policyLifeBeneficiary_insurant_name_value[i]);//受益人对应的被保人id
			beneficiaryModel.setRelation1(policyLifeBeneficiary_relation1[i]);//受益人与投保人关系
			beneficiaryModel.setRelation2(policyLifeBeneficiary_relation_code[i]);//受益人与主被保人关系
			//查询受益人客户信息
			customerVOModel.setCustomer_id(customer_id);
			beneCustomer = customerService.getCustomerNewInfo(customerVOModel); 
			beneficiaryModel.setBene_age(Integer.valueOf(policyLifeBeneficiary_age[i]));//受益人年龄
			beneficiaryModel.setBene_certi_code(beneCustomer.getCerti_type());//受益人证件类型
			beneficiaryModel.setBene_certi_no(beneCustomer.getCerti_no());//受益人证件号码
			beneficiaryModel.setBene_tel(beneCustomer.getMobile());//受益人电话
			beneficiaryList.add(beneficiaryModel);//受益人
			//受益人的回显信息
			beneVOView.setName(beneCustomer.getName());
			beneVOView.setGender_name(beneCustomer.getGender_name());
			beneVOView.setAge(Integer.valueOf(policyLifeBeneficiary_age[i]));
			beneVOView.setCustomer_id(customer_id);
			beneVOView.setBene_order(beneOrder);
			beneVOView.setBene_rate(bene_rate);
			beneVOView.setBene_type(bene_type);
			beneVOView.setBene_type_name(policyLifeBeneficiary_bene_type_name[i]);//
			beneVOView.setCerti_no(beneCustomer.getCerti_no());
			beneVOView.setCerti_type(beneCustomer.getCerti_type());
			beneVOView.setCerti_type_name(beneCustomer.getCerti_type_name());
			beneVOView.setInsurant_id(policyLifeBeneficiary_insurant_name_value[i]);
			beneVOView.setRelation1(policyLifeBeneficiary_relation1[i]);//受益人与投保人关系
			beneVOView.setRelation2(policyLifeBeneficiary_relation_code[i]);//受益人与主被保人关系
			beneVOView.setRelation1_name(policyLifeBeneficiary_relation1_name[i]);//
		    beneVOView.setRelation2_name(policyLifeBeneficiary_relation2_name[i]);//
			beneVOView.setMobile(beneCustomer.getMobile());
			beneVOView.setAddress(beneCustomer.getAddress());
			beneVOView.setEmail(beneCustomer.getEmail());
			beneficiaryListView.add(beneVOView);//受益人显示集合添加
		}
		vo.setBeneficiaryList(beneficiaryList);//受益人信息集合
		vo.setBeneficiaryListView(beneficiaryListView);//	受益人信息回显集合
		for(int  i = 0; i < product_id_arr.length ; i++){
			Integer seq_id =0;
			Integer item_id =0;
			if(seq_arr==null||seq_arr.length==0||seq_arr[i]==null||"".equals(seq_arr[i])){
				seq_id  = policyLifeService.getSeq_id();
				item_id = policyLifeService.getSeq_id();
			}else{
				seq_id = Integer.valueOf(seq_arr[i]);
				item_id = policyLifeService.getSeq_id();
			}
			//组装产品信息
			IPolicyLifeProductFeePremVOModel productModel = new PolicyLifeProductFeePremVOModel();
			productModel.setSeq_id(seq_id);//产品相关的使用同一个seq_id
			productModel.setItem_id(item_id);//产品的项目id
			productModel.setBranch_id(branch_id);// 销售机构 （产品、分险种明细）
			productModel.setInsBranch_id(insBranch_id);//保险公司机构
			productModel.setSend_code(send_code);//投保单号 （产品  分险种明细）
			productModel.setApp_id(holder_id);//投保人 （产品 ）
			productModel.setRelation_id(relation);//投保人与主被保人关系 （产品 ）
			productModel.setSell_way(CodeTypeConst.POLICYLIFE_SELL_WAY_PERSONAL); //（产品 分险种明细）
			productModel.setPay_mode(pay_mode);//当期付款方式  默认为投保单的付款方式 （产品   分险种明细  ）
			productModel.setPay_mode_next(pay_mode_next);//续期付款方式  默认为投保单的下期付款方式 （产品 ）
			productModel.setPolicy_year(CodeTypeConst.POLICYLIFE_YEAR);//保单年度 （产品）
			productModel.setUnit(CodeTypeConst.POLICYLIFE_PRODUCT_UNIT);//份数 （产品 ）
			productModel.setMoney_id(money_id);//币种 （产品  分险种明细）
			productModel.setOverdue_manage(overdue_manage);//保险费逾期未付的选择 （产品 ）
			productModel.setHold_date(hold_date);//投保日期 （产品 分险种明细）
			productModel.setValidate_date(hold_date);//保单生效日期  （产品 ）
			productModel.setDue_time(hold_date);//本次应收日期   （产品 分险种明细  ）
			//productModel.setNext_due_time();//下次应收日期  处理???和缴费方式有关：例如：年缴-投保日期+一年 （产品 ）
			productModel.setHigh_policy(high_policy);//是否高额件 （产品 ）
			productModel.setInsure_stop(CodeTypeConst.POLICYLF_PRODUCT_INSURE_STOP);//是否终止 （产品 ）
			productModel.setIns_status(CodeTypeConst.PRODUCT_STATUS_FIRSTCOVERED);//险种状态   （产品 ）
			
			//费用信息明细
			productModel.setFee_id(item_id);//费用Id
			productModel.setAgent_id(agent_id);//保单销售人员 （ 分险种明细)
			productModel.setService_id(agent_id);//保单服务人员 （ 分险种明细)
			productModel.setPolicy_period(CodeTypeConst.POLICYLIFE_PERIOD);//缴费期次  (分险种明细 )
			productModel.setBank_code(bank_code);// 开户银行 (分险种明细)
			productModel.setBank_account(bank_account);// 银行账号(分险种明细)
			productModel.setFee_status(CodeTypeConst.FEE_STATUS_WAIT);//费用处理状态 0：待处理  (分险种明细 )
			productModel.setPosted(CodeTypeConst.POLICYLF_PRODUCT_POSTEDN);//是否生成记账  (分险种明细)
			productModel.setFee_type(CodeTypeConst.FEE_TYPE_FIRST);//费用业务类型 :41-首期保费收入
			
			//产品回显信息组装   
			IPolicyLifeProductVOModel productVOView = new PolicyLifeProductVOModel();//回显产品信息的model
			String product_id = product_id_arr[i];
			productModel.setProduct_id(product_id);//产品id （产品  分险种明细）
			productModel.setCoverage_period(coverage_period_arr[i]);//保障期限分类 （产品 分险种明细）
			productModel.setInsurant_id(product_insurant_id[i]);//被保人id （产品  分险种明细  ）
			productModel.setCoverage_year(Integer.valueOf(coverage_year_arr[i]));//保障时间 （产品  分险种明细）
			productModel.setCharge_period(charge_period_arr[i]);//缴费期限类型 （产品 ）
			productModel.setCharge_type(charge_type_arr[i]);//缴费方式（产品 分险种明细 ） 
			productModel.setCharge_next(charge_type_arr[i]);//下次缴费方式（产品 )
			productModel.setCharge_year(Integer.valueOf(charge_year_arr[i]));//缴费年限 （产品  分险种明细）
			productModel.setAmount(Double.valueOf(amount_arr[i]));//保额 （产品 ）
			productModel.setPeriod_prem(Double.valueOf(period_prem_arr[i]));//保费 （产品 分险种明细 ）
			productModel.setActual_prem(Double.valueOf(period_prem_arr[i]));//本次实缴保费  （分险种明细  ）
			//险种非必输项的判断
			if(auth_payAge_arr[i]!=null&&!"".equals(auth_payAge_arr[i])){
				productModel.setAuth_payAge(Integer.valueOf(auth_payAge_arr[i]));//年金领取年龄 （产品 ）
			}
			if(auth_draw_arr[i]!=null&&!"".equals(auth_draw_arr[i])){
				productModel.setAuth_draw(auth_draw_arr[i]);//年金领取方式 （产品 ）
			}
			if(auth_firstPay_arr[i]!=null&&!"".equals(auth_firstPay_arr[i])){
				productModel.setAuth_firstPay(Double.valueOf(auth_firstPay_arr[i]));//首期领取金额 （产品 ）
			}
			if(dividend_choice_arr[i]!=null&&!"".equals(dividend_choice_arr[i])){
				productModel.setDividend_choice(dividend_choice_arr[i]);//红利领取方式（产品 ）
			}
			//下次应收日期处理
			Calendar cal = Calendar.getInstance();
			cal.setTime(hold_date);
			if(CodeTypeConst.PRODUCT_CHANRGE_TYPE_YEAR.equals(charge_type_arr[i])){
				cal.add(Calendar.YEAR,1);
				Date  due_time_next = new java.sql.Date(cal.getTime().getTime());
				productModel.setNext_due_time(due_time_next);//产品表的下次应收日期
			}else if(CodeTypeConst.PRODUCT_CHANRGE_TYPE_HALFYEAR.equals(charge_type_arr[i])){
				cal.add(Calendar.MONTH,5);
				Date  due_time_next = new java.sql.Date(cal.getTime().getTime());
				productModel.setNext_due_time(due_time_next);//产品表的下次应收日期
			}else if(CodeTypeConst.PRODUCT_CHANRGE_TYPE_SEASON.equals(charge_type_arr[i])){
				cal.add(Calendar.MONTH,3);
				Date  due_time_next = new java.sql.Date(cal.getTime().getTime());
				productModel.setNext_due_time(due_time_next);//产品表的下次应收日期
			}else if(CodeTypeConst.PRODUCT_CHANRGE_TYPE_MONTH.equals(charge_type_arr[i])){
				cal.add(Calendar.MONTH,1);
				Date  due_time_next = new java.sql.Date(cal.getTime().getTime());
				productModel.setNext_due_time(due_time_next);//产品表的下次应收日期
			}
			productModel.setIs_autoRen(is_autoRen_arr[i]);//是否自动续保（产品 ）
			IProductLlifeModel pm  = new ProductLlifeModel();
			pm.setInsBranch_id(insBranch_id);
			pm.setProduct_id(product_id);
		    IProductLlifeModel productInfo = commonAsynRequestService.getProductInfo(pm);
		    productModel.setIns_type(productInfo.getIns_type());//主附险标志 （产品 分险种明细 ）
		    productModel.setProduct_type(productInfo.getProduct_type());//产品类型1 （产品 分险种明细 ）
		    productModel.setProduct_type2(productInfo.getProduct_type2());//产品类型2 （产品  分险种明细）
		    productModel.setProduct_type3(productInfo.getProduct_type3());//产品类型3 （产品  分险种明细）
		    productModel.setPeriod_type(productInfo.getPeriod_type());//保障期限分类 （产品 分险种明细 ）
		    productList.add(productModel);//添加产品的model
		    
		    //回显产品的信息model
		    productVOView.setProduct_id(product_id);//
		    productVOView.setProduct_name(productInfo.getProduct_name());//
		    productVOView.setAmount(Double.valueOf(amount_arr[i]));//
		    productVOView.setInsurant_id(product_insurant_id[i]);
		    productVOView.setInsurant_name(productLlife_insurant_name[i]);//
		    productVOView.setCharge_type(charge_type_arr[i]);
		    productVOView.setCharge_type_name(productLlife_charge_type_name[i]);//缴费方式
		    productVOView.setPeriod_prem(Double.valueOf(period_prem_arr[i]));
		    productVOView.setCoverage_period(coverage_period_arr[i]);
		    productVOView.setCoverage_year(Integer.valueOf(coverage_year_arr[i]));
		    productVOView.setCharge_type(charge_type_arr[i]);
		    productVOView.setCharge_period(charge_period_arr[i]);//缴费期限类型
		    productVOView.setCharge_period_name(productLlife_charge_period_name[i]);//缴费期限类型
		    productVOView.setCharge_year(Integer.valueOf(charge_year_arr[i]));
			//险种非必输项的判断
			if(auth_payAge_arr[i]!=null&&!"".equals(auth_payAge_arr[i])){
				productVOView.setAuth_payAge(Integer.valueOf(auth_payAge_arr[i]));//年金领取年龄 （产品 ）
			}
			if(auth_draw_arr[i]!=null&&!"".equals(auth_draw_arr[i])){
				productVOView.setAuth_draw(auth_draw_arr[i]);//年金领取方式 （产品 ）
			}
			if(auth_firstPay_arr[i]!=null&&!"".equals(auth_firstPay_arr[i])){
				productVOView.setAuth_firstPay(Double.valueOf(auth_firstPay_arr[i]));//首期领取金额 （产品 ）
			}
			if(dividend_choice_arr[i]!=null&&!"".equals(dividend_choice_arr[i])){
				productVOView.setDividend_choice(dividend_choice_arr[i]);//红利领取方式（产品 ）
			}
		    productVOView.setIs_autoRen(is_autoRen_arr[i]);
		    productVOView.setRenew(productInfo.getRenew());
		    productVOView.setSeq_id(productModel.getSeq_id());//产品相关的seq_id
		    productListView.add(productVOView);//回显产品的model
		}
		
		vo.setProductList(productList);//产品信息集合
		vo.setProductListView(productListView);//产品回显信息集合
		//影像上传信息
		IPolicyImageModel policyImageModel = new PolicyImageModel();
	    policyImageModel.setFile_ids(file_ids);
	    policyImageModel.setSend_code(send_code);
	    policyImageModel.setBus_type(CodeTypeConst.IMAGE_BUSTYPE_POLICYLIFE);//投保单影像件
        vo.setPolicyImageModel(policyImageModel);
		return vo;
	}
	
	/**
	 * 生成保单id
	 * @param branch_id
	 * @param series_code
	 * @return
	 */
	public String createId(String branch_id,String series_code){
		String seq_code=seqDao.queryCommonSeq("seq_id");
		try {
			//调用现成的方法对取出的识别码进行10位补0
			seq_code=StringUtil.alignLeft(seq_code, 10);
		} catch (Message e) {
			e.printStackTrace();
		}
		return branch_id.substring(0, 3)+series_code+seq_code;
	}
	
	/** 
	* 获得产品可选缴费方式的信息  ss
	* @param req
	* @param res
	* @return ModelAndView
	* @throws IOException 
	* @description:
	*/
	@RequestMapping("/CBS/PolicyLife/getChargeType.do")
	public ModelAndView getChargeType(HttpServletRequest req,HttpServletResponse res) throws IOException{
		PolicyLifeProductFeePremVOModel model=new PolicyLifeProductFeePremVOModel();
		model.setInsBranch_id(ActionHelper.getNullToStr(req.getParameter("insBranch_id")));
		model.setProduct_id(ActionHelper.getNullToStr(req.getParameter("product_id")));
		List<String> list=policyLifeService.getChargeType(model);
		String str=JSONArray.fromObject(list).toString();
		res.getWriter().print(str);
		return null;
	}
	
	/** 
	* 获得产品可选缴费期限类型的信息  ss
	* @param req
	* @param res
	* @return ModelAndView
	* @throws IOException 
	* @description:
	*/
	@RequestMapping("/CBS/PolicyLife/getCharge_Period.do")
	public ModelAndView getCharge_Period(HttpServletRequest req,HttpServletResponse res) throws IOException{
		PolicyLifeProductFeePremVOModel model=new PolicyLifeProductFeePremVOModel();
		model.setInsBranch_id(ActionHelper.getNullToStr(req.getParameter("insBranch_id")));
		model.setProduct_id(ActionHelper.getNullToStr(req.getParameter("product_id")));
		List<String> list=policyLifeService.getCharge_Period(model);
		String str=JSONArray.fromObject(list).toString();
		res.getWriter().print(str);
		return null;
	}
	
	/** 
	* 获得产品可选保障期限类型的信息  ss
	* @param req
	* @param res
	* @return ModelAndView
	* @throws IOException 
	* @description:
	*/
	@RequestMapping("/CBS/PolicyLife/getCoveragePeriod.do")
	public ModelAndView getCoveragePeriod(HttpServletRequest req,HttpServletResponse res) throws IOException{
		PolicyLifeProductFeePremVOModel model=new PolicyLifeProductFeePremVOModel();
		model.setInsBranch_id(ActionHelper.getNullToStr(req.getParameter("insBranch_id")));
		model.setProduct_id(ActionHelper.getNullToStr(req.getParameter("product_id")));
		List<String> list=policyLifeService.getCoveragePeriod(model);
		String str=JSONArray.fromObject(list).toString();
		res.getWriter().print(str);
		return null;
	}
}
