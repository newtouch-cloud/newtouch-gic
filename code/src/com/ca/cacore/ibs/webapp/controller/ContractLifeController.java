package com.ca.cacore.ibs.webapp.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.ibs.model.bo.ContractLifeBeneficiaryModel;
import com.ca.cacore.ibs.model.bo.ContractLifeHolderModel;
import com.ca.cacore.ibs.model.bo.ContractLifeInsurantModel;
import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.ca.cacore.ibs.model.bo.PolicyImageModel;
import com.ca.cacore.ibs.model.vo.ContractLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.ContractLifeProductVOModel;
import com.ca.cacore.ibs.model.vo.ContractLifeSaveVOModel;
import com.ca.cacore.ibs.model.vo.IContractLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeVOModel;
import com.ca.cacore.ibs.webapp.service.ICommonAsynRequestService;
import com.ca.cacore.ibs.webapp.service.IContractLifeService;
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

@Controller
public class ContractLifeController extends BaseController{
	@Autowired private IContractLifeService contractLifeService ;
	@Autowired private ICommonSeqDao seqDao; 
	@Autowired private ICustomerService customerService;
	@Autowired private ICommonAsynRequestService commonAsynRequestService;
	
	/**
	 * 保单录入
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/contractLife/contactLifeAdd.do")
	public ModelAndView contactLifeAdd(HttpServletRequest req,HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		
		return new ModelAndView("ca/cacore/cbs/contractLife/contractLifeAdd");
	}
	
	/**
	 * 保单保存方法
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/contractLife/contractLifeSave.do")
	public ModelAndView contractLifeSave(HttpServletRequest req,HttpServletResponse res) throws Exception {
		IUserModel user = ActionHelper.getUserFromSession(req);
		 //获取信息 -并组装数据
		 ContractLifeSaveVOModel vo = this.getContractLifeInfo(req);
		 Long policy_id = vo.getPolicy_id();//保单id给影像上传使用
		//调用保存的方法
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = contractLifeService.saveAllContractLife(vo, user);
			contractLifeService.addContractlifePrem(vo, user);
			vo = new ContractLifeSaveVOModel();//清空数据
			vo.setResult_flag("true");
			vo.setPolicy_id(policy_id);//设置保单id 影像上传使用
		}catch (BusinessException e){
			vo.setResult_flag("false");
			returnMsg.setFailMessage(e.getMessage());
		}
		List<ContractLifeSaveVOModel> list = new ArrayList<ContractLifeSaveVOModel>();
		list.add(vo);
		returnMsg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg,true);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper",rmHelper);
		//返回查看页面
		return new ModelAndView("ca/cacore/cbs/contractLife/contractLifeAdd");
	}
	
	/**
	 * 保单修改方法
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/contractLife/contractLifeUpdate.do")
	public ModelAndView contractLifeUpdate(HttpServletRequest req,HttpServletResponse res) throws Exception {
		 IUserModel user = ActionHelper.getUserFromSession(req);
		 //获取信息 -并组装数据
		 ContractLifeSaveVOModel vo = this.getContractLifeInfo(req);
		//调用修改的方法
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = contractLifeService.modifyAllContractLife(vo,user);
			contractLifeService.modifyContractlifePrem(vo, user);
			vo.setResult_flag("true");
		}catch (BusinessException e){
			vo.setResult_flag("false");
			returnMsg.setFailMessage(e.getMessage());
		}
		List<ContractLifeSaveVOModel> list = new ArrayList<ContractLifeSaveVOModel>();
		list.add(vo);
		returnMsg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper",rmHelper);
		//返回修改页面页面
		return new ModelAndView("ca/cacore/cbs/contractManage/contractLifeModify");
	}
	
	/**
	 * 根据投保单号码和销售机构带出投保单信息
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/contractLife/ajaxPolicyLife.do")
	public ModelAndView ajaxPolicyLife(HttpServletRequest req,HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		String send_code = ActionHelper.getNullToStr(req.getParameter("send_code"));//投保单号
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司机构
		PolicyLifeVOModel model = new PolicyLifeVOModel();
		model.setSend_code(send_code);
		model.setInsBranch_id(insBranch_id);
		IPolicyLifeVOModel m = contractLifeService.getPolicyLifeInfoBySendCode(model);
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");// 解决火狐浏览器ajax返回的data输出为[object// XMLDocument]的问题
		res.getWriter().print(m.getResult_flag());
		return null;
	}
	
	/**
	 * 根据投保单号码和销售机构带出投保单信息
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/contractLife/getPolicyLifeInfoBySend_code.do")
	public ModelAndView getPolicyLifeInfoBySend_code(HttpServletRequest req,HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		String send_code = ActionHelper.getNullToStr(req.getParameter("send_code"));//投保单号
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司机构
		//查询时组装查询条件
		PolicyLifeVOModel model = new PolicyLifeVOModel();
		model.setSend_code(send_code);
		model.setInsBranch_id(insBranch_id);
		model.setStatus(CodeTypeConst.POLICYLIFE_STATUS_FIRSTCOVERED);//只有待核保的才可以带出信息
		//调用查询方法
		IPolicyLifeVOModel m = contractLifeService.getPolicyLifeInfoBySendCode(model);//查询投保单的信息
		ReturnMsg returnMsg = new ReturnMsg();
		List<IPolicyLifeVOModel> list = new ArrayList<IPolicyLifeVOModel>();
		list.add(m);
		returnMsg.setDataTable(TransHelper.list2ObjectMapList(list).get(0));
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper",rmHelper);
		//投保单号码存在，带出投保单信息
		String url="ca/cacore/cbs/contractLife/contractLifeAddView";
		return new ModelAndView(url);
	}
	
	/**
	 * 保单request方法 参数组装
	 * @param req
	 * @param res
	 * @return ContractLifeSaveVOModel 保单vo实体 
	 */
	public  ContractLifeSaveVOModel getContractLifeInfo(HttpServletRequest req){
		ContractLifeSaveVOModel vo = new ContractLifeSaveVOModel();
		String send_code = ActionHelper.getNullToStr(req.getParameter("send_code"));//投保单号
		String policy_code = ActionHelper.getNullToStr(req.getParameter("policy_code"));//保单号
		String policy_idStr = ActionHelper.getNullToStr(req.getParameter("policy_id"));//保单id 更新的时候接收保单id
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司机构
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构id
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));//机构名称 回显
		String agent_id = ActionHelper.getNullToStr(req.getParameter("agent_id"));//销售人员id
		String agent_name = ActionHelper.getNullToStr(req.getParameter("agent_name"));//销售人员 回显
		String high_policy = ActionHelper.getNullToStr(req.getParameter("high_policy"));//高额件
		Date hold_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("hold_date")));//投保日期
		Date validate_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("validate_date")));//保单生效日期
		Double period_prem = ActionHelper.getNullToDouble(req.getParameter("period_prem"));//保费合计
		String pay_mode = ActionHelper.getNullToStr(req.getParameter("pay_mode"));//首期付款方式
		String pay_mode_next = ActionHelper.getNullToStr(req.getParameter("pay_mode_next"));//续期付款方式
		String bank_code = ActionHelper.getNullToStr(req.getParameter("bank_code"));//开户银行
		String account_type = ActionHelper.getNullToStr(req.getParameter("account_type"));//账户类型
		String bank_accName = ActionHelper.getNullToStr(req.getParameter("bank_accName"));//银行开户人
		String money_id = ActionHelper.getNullToStr(req.getParameter("money_id"));//币种
		String bank_account = ActionHelper.getNullToStr(req.getParameter("bank_account"));//银行账号
		String overdue_manage = ActionHelper.getNullToStr(req.getParameter("overdue_manage"));//保费逾期来付选择
		String seq_id_cl = ActionHelper.getNullToStr(req.getParameter("seq_id"));//保单的seq_id
		String status = ActionHelper.getNullToStr(req.getParameter("status"));//保单的状态
		String insurant_id = "";//第一被保人 
		Long policy_id=0l;//初始化
		//得到保单id
		if(!"".equals(policy_idStr)){
			policy_id = Long.valueOf(policy_idStr);//如果是更新或根据投保单带出保单信息的时候获取保单id
		}else{
			 //添加的时候生成保单id
			policy_idStr = this.createId(branch_id, CodeTypeConst.POLICYLIFE_SERIES_CODE);
			policy_id =Long.valueOf(policy_idStr);
		}
		
		//投保人信息
		String[] policyPeople_customer_id = req.getParameterValues("policyPeople_customer_id");//投保人的客户id
		String holder_id = policyPeople_customer_id[0];
		String[] policyPeople_relation = req.getParameterValues("policyPeople_relation_code");//投保人与主被保人的关系
		String[] policyPeople_age = req.getParameterValues("policyPeople_age");//被保人年龄
		//回显使用信息
		String[] policyPeople_relation_name = req.getParameterValues("policyPeople_relation1_name");//投保人与主被保人的关系名称
		String relation  = policyPeople_relation[0];
		//被保人信息
		String[] policyLifeInsured_customer_id = req.getParameterValues("policyLifeInsured_customer_id");//被保人的客户id
		String[] policyLifeInsured_relation1 = req.getParameterValues("policyLifeInsured_relation1");//被保人——与投保人关系 0
		String[] policyLifeInsured_relation_code = req.getParameterValues("policyLifeInsured_relation2");//被保人——与主被保人关系 0
		String[] policyLifeInsured_age = req.getParameterValues("policyLifeInsured_age");//投保人年龄
		//显示名称   
		String[] policyLifeInsured_relation1_name = req.getParameterValues("policyLifeInsured_relation1_name");//被保人——与投保人关系 0
		String[] policyLifeInsured_relation2_name = req.getParameterValues("policyLifeInsured_relation2_name");//被保人——与主被保人关系 0
		List<ContractLifeInsurantModel>insurantList = new ArrayList<ContractLifeInsurantModel>();//被保人集合
		
		//受益人信息
		String[] policyLifeBeneficiary_customer_id = req.getParameterValues("policyLifeBeneficiary_customer_id");//受益人客户id
		String[] policyLifeBeneficiary_insurant_name_value = req.getParameterValues("policyLifeBeneficiary_insurant_name_value");//受益人对应的被保人客户id
		String[] policyLifeBeneficiary_relation1 = req.getParameterValues("policyLifeBeneficiary_relation1");//受益人——与投保人关系 0
		String[] policyLifeBeneficiary_relation_code = req.getParameterValues("policyLifeBeneficiary_relation2");//受益人——与主被保人关系 
		String[] policyLifeBeneficiary_bene_type_code = req.getParameterValues("policyLifeBeneficiary_bene_type_code");//受益性质 0
		String[] policyLifeBeneficiay_bene_order = req.getParameterValues("policyLifeBeneficiary_bene_order");//受益顺序 
		String[] policyLifeBeneficiay_bene_rate = req.getParameterValues("policyLifeBeneficiary_bene_rate");//受益比例
		List<ContractLifeBeneficiaryModel>beneficiaryList = new ArrayList<ContractLifeBeneficiaryModel>();//受益人集合
		String[] policyLifeBeneficiary_age = req.getParameterValues("policyLifeBeneficiary_age");//受益人年龄
		
		//名称
		String[] policyLifeBeneficiary_relation1_name = req.getParameterValues("policyLifeBeneficiary_relation1_name");//受益人——与投保人关系 
		String[] policyLifeBeneficiary_relation2_name = req.getParameterValues("policyLifeBeneficiary_relation2_name");//受益人——与主被保人关系 
		String[] policyLifeBeneficiary_bene_type_name = req.getParameterValues("policyLifeBeneficiary_bene_type_name");//受益性质名称
		
		//产品相关
		String[] product_id_arr = req.getParameterValues("productLlife_product_id");//产品代码
		String[] product_insurant_id = req.getParameterValues("productLlife_customer_id");//产品和被保人对应
		String[] coverage_period_arr = req.getParameterValues("productLlife_coverage_period");//保障期限类型
		String[] coverage_year_arr = req.getParameterValues("productLlife_charge_year");//保障时间
		String[] charge_period_arr = req.getParameterValues("productLlife_charge_period");//缴费期限类型
		String[] charge_year_arr = req.getParameterValues("productLlife_coverage_year");//缴费年数
		String[] charge_type_arr = req.getParameterValues("productLlife_charge_type");//缴费方式
		String[] amount_arr = req.getParameterValues("productLlife_amount");//基本保障金额
		String[] period_prem_arr = req.getParameterValues("productLlife_period_prem");//保费
		String[] auth_payAge_arr = req.getParameterValues("productLlife_auth_payage");//年金领取年龄
		String[] auth_draw_arr = req.getParameterValues("productLlife_auth_draw");//年金领取方式
		String[] auth_firstPay_arr = req.getParameterValues("productLlife_auth_firstpay");//首期领取金额
		String[] dividend_choice_arr =req.getParameterValues("productLlife_dividend_choice");//红利领取方式
		String[] is_autoRen_arr =req.getParameterValues("productLlife_is_autoren");//是否自动续保
		//回显
		String[] productLlife_charge_type_name = req.getParameterValues("productLlife_charge_type_name");//缴费方式名称
		String[] productLlife_charge_period_name =req.getParameterValues("productLlife_charge_period_name");//缴费期限类型名称
		String[] productLlife_insurant_name =req.getParameterValues("productLlife_insurant_name");//缴费期限类型名称
		String [] seq_arr = req.getParameterValues("product_seq_id");//产品相关的seq_id
		
		
		//影像上传信息
		String file_ids[] = req.getParameterValues("file_id");
		
	    List<ContractLifeProductVOModel>productList = new ArrayList<ContractLifeProductVOModel>();//险种集合
	    List<IContractLifePeopleVOModel> holderListView  = new ArrayList<IContractLifePeopleVOModel>();//投保人集合
		List<IContractLifePeopleVOModel>insurantListView = new ArrayList<IContractLifePeopleVOModel>();//被保人集合
		List<IContractLifePeopleVOModel >beneficiaryListView = new ArrayList<IContractLifePeopleVOModel>();//受益人集合
		
		
		if(seq_id_cl!=null&&!"".equals(seq_id_cl)){
		 vo.setSeq_id(Integer.valueOf(seq_id_cl));//修改的时候接受页面的seq_id
		}
		vo.setPolicy_id(policy_id);//保单Id
		vo.setBranch_id(branch_id);//机构代码
		vo.setInsBranch_id(insBranch_id);//保险公司机构
		vo.setSend_code(send_code);//投保单号
		vo.setPolicy_code(policy_code);//保单号
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
		vo.setValidate_date(validate_date);//保单生效日期
		vo.setIs_answered(CodeTypeConst.CONTRACTLIFE_IS_ANSWER_N);//是否已回访
		vo.setHigh_policy(high_policy);//是否高额件
		if(status!=null&&!"".equals(status)){
			vo.setStatus(status);//保单状态:
		}
		vo.setBranch_name(branch_name);//回显
		vo.setAgent_name(agent_name);//回显
		
		//组装投保人信息
		ICustomerVOModel customerVOModel = new CustomerVOModel();
		customerVOModel.setBranch_id(branch_id);//保险公司id
		customerVOModel.setCustomer_id(policyPeople_customer_id[0]);;//保险公司id
		//根据机构和客户id查询客户详细信息
		ICustomerVOModel  holderCustomer=customerService.getCustomerNewInfo(customerVOModel);
		ContractLifeHolderModel holderModel = new ContractLifeHolderModel();//投保人model
		holderModel.setBranch_id(branch_id);//销售机构id
		holderModel.setInsBranch_id(insBranch_id);//保险公司机构
		holderModel.setPolicy_id(policy_id);//保单Id
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
		IContractLifePeopleVOModel holderVOView = new  ContractLifePeopleVOModel();
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
		vo.setHolderListView(holderListView);//投保人信息回显
		 
		for(int i = 0 ; i < policyLifeInsured_customer_id.length ; i++){
			//组装被保人信息
			ContractLifeInsurantModel insuredModel = new ContractLifeInsurantModel();
			insuredModel.setBranch_id(vo.getBranch_id());//销售机构
			insuredModel.setInsBranch_id(vo.getInsBranch_id());//保险公司机构
			IContractLifePeopleVOModel insurantVOView = new  ContractLifePeopleVOModel();//回显被保人使用的model
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
			ContractLifeBeneficiaryModel beneficiaryModel= new ContractLifeBeneficiaryModel();
			beneficiaryModel.setBranch_id(branch_id);//销售机构
			beneficiaryModel.setInsBranch_id(insBranch_id);//保险公司机构
			IContractLifePeopleVOModel beneVOView = new  ContractLifePeopleVOModel();//回显受益人使用的model
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
				seq_id  = contractLifeService.getSeq_id();
				item_id = contractLifeService.getSeq_id();
			}else{
				seq_id = Integer.valueOf(seq_arr[i]);
				item_id = contractLifeService.getSeq_id();
			}
			//组装产品信息
			ContractLifeProductVOModel productModel = new ContractLifeProductVOModel();
			productModel.setSeq_id(seq_id);
			productModel.setItem_id(item_id);
			productModel.setPrem_id(item_id);
			productModel.setFee_id(item_id);
			productModel.setBranch_id(branch_id);// 销售机构 （产品 、费用、分险种明细）
			productModel.setInsBranch_id(insBranch_id);//保险公司机构
			productModel.setSend_code(send_code);//投保单号 （产品 费用 分险种明细）
			productModel.setPolicy_code(policy_code);//保单号
			productModel.setApp_id(holder_id);//投保人 （产品 ）
			productModel.setRelation_id(relation);//投保人与主被保人关系 （产品 ）
			productModel.setSell_way(CodeTypeConst.POLICYLIFE_SELL_WAY_PERSONAL); //（产品 分险种明细）
			productModel.setPay_mode(pay_mode);//当期付款方式  默认为投保单的付款方式 （产品   分险种明细  费用）
			productModel.setPay_mode_next(pay_mode_next);//续期付款方式  默认为投保单的下期付款方式 （产品 ）
			productModel.setPolicy_year(CodeTypeConst.POLICYLIFE_YEAR);//保单年度 （产品 费用）
			productModel.setUnit(CodeTypeConst.POLICYLIFE_PRODUCT_UNIT);//份数 （产品 ）
			productModel.setMoney_id(money_id);//币种 （产品  分险种明细 费用）
			productModel.setOverdue_manage(overdue_manage);//保险费逾期未付的选择 （产品 ）
			productModel.setHold_date(hold_date);//投保日期 （产品 分险种明细）
			productModel.setValidate_date(validate_date);//保单生效日期 （产品 ）
			productModel.setDue_time(hold_date);//本次应收日期   （产品 分险种明细 ）
			productModel.setHigh_policy(high_policy);//是否高额件 （产品 ）
			productModel.setInsure_stop(CodeTypeConst.POLICYLF_PRODUCT_INSURE_STOP);//是否终止 （产品 ）
			productModel.setIns_status(CodeTypeConst.PRODUCT_STATUS_FIRSTCOVERED);//险种状态   （产品 ）
			
			//费用信息明细
			productModel.setAgent_id(agent_id);//保单销售人员 （费用 分险种明细)
			productModel.setService_id(agent_id);//保单服务人员 （费用 分险种明细)
			productModel.setPolicy_period(CodeTypeConst.POLICYLIFE_PERIOD);//缴费期次  (分险种明细    费用)
			productModel.setBank_code(bank_code);// 开户银行 (分险种明细   费用)
			productModel.setBank_account(bank_account);// 银行账号(分险种明细  费用)
			productModel.setFee_status(CodeTypeConst.FEE_STATUS_WAIT);//费用处理状态 0：待处理  (分险种明细   费用)
			productModel.setPosted(CodeTypeConst.POLICYLF_PRODUCT_POSTEDN);//是否生成记账  (分险种明细)
			//费用
			productModel.setCustomer_id(holder_id);//付费人 （费用）
			productModel.setFee_status(CodeTypeConst.FEE_STATUS_WAIT);//费用处理状态 0：待处理  （费用）
			productModel.setFee_type(CodeTypeConst.FEE_TYPE_FIRST);//费用业务类型 :41-首期保费收入
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
			
			//CBS_ContractLife_InsFee
			productModel.setFee_rate(100.0);//???手续费比例
			productModel.setFee(50.0);//手续费 ???
			
			//产品回显信息组装   
			String product_id = product_id_arr[i];
			productModel.setProduct_id(product_id);//产品id （产品  分险种明细）
			productModel.setCoverage_period(coverage_period_arr[i]);//保障期限分类 （产品 分险种明细）
			productModel.setInsurant_id(product_insurant_id[i]);//被保人id （产品  分险种明细  费用：分单被保险人？？？）
			productModel.setCoverage_year(Integer.valueOf(coverage_year_arr[i]));//保障时间 （产品  分险种明细）
			productModel.setCharge_period(charge_period_arr[i]);//缴费期限类型 （产品 ）
			productModel.setCharge_type(charge_type_arr[i]);//缴费方式（产品 分险种明细 ） 
			productModel.setCharge_next(charge_type_arr[i]);//下次缴费方式（产品 )
			productModel.setCharge_year(Integer.valueOf(charge_year_arr[i]));//缴费年限 （产品  分险种明细）
			productModel.setAmount(Double.valueOf(amount_arr[i]));//保额 （产品 ）
			productModel.setPeriod_prem(Double.valueOf(period_prem_arr[i]));//保费 （产品 分险种明细   费用？？？）
			productModel.setActual_prem(Double.valueOf(period_prem_arr[i]));//本次实缴保费  （分险种明细   费用 ？？？）
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
		    
		    //回显产品的信息
		    productModel.setProduct_name(productInfo.getProduct_name());//
		    productModel.setAmount(Double.valueOf(amount_arr[i]));//份额 保费显示
		    productModel.setInsurant_name(productLlife_insurant_name[i]);
		    productModel.setCharge_type_name(productLlife_charge_type_name[i]);//缴费方式
		    productModel.setCharge_period_name(productLlife_charge_period_name[i]);//缴费期限类型
		    productModel.setRenew(productInfo.getRenew());
		    productList.add(productModel);//回显产品的model
		}
		vo.setProductListView(productList);//产品信息集合
		
		//影像上传信息
		IPolicyImageModel policyImageModel = new PolicyImageModel();
	    policyImageModel.setFile_ids(file_ids);
	    policyImageModel.setSend_code(send_code);
	    policyImageModel.setBus_type(CodeTypeConst.IMAGE_BUSTYPE_CONTRACTLIFE);//保单影像上传
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
	
}
