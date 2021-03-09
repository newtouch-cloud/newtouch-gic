package com.ca.cacore.msss.webapp.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;
import com.ca.cacore.msss.model.bo.ProductLlifeModel;
import com.ca.cacore.msss.model.vo.IProductLlifeVOModel;
import com.ca.cacore.msss.model.vo.ProductLlifeVOModel;
import com.ca.cacore.msss.webapp.service.IProductLlifeService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.ThreadLocalHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;

/**
 * 
 * @author Wang_ds
 * @since 2013-11-10
 * @description 产品信息维护Controller层
 */
@Controller
public class ProductLlifeController extends BaseController {

	@Autowired
	private IProductLlifeService ProductLlifeService;
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:进入到查询出产品信息页面
	*/
	@RequestMapping("/MSSS/ProductLlife/toQueryProductLlifeList.do")
	public ModelAndView toQueryProductLlifeList(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/MSSS/productLlife/productLlife_Query");
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询出产品信息，或根据条件查询
	 */
	@RequestMapping("/MSSS/ProductLlife/queryProductLlifeList.do")
	public ModelAndView queryProductLlifeList(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司
		String product_id = ActionHelper.getNullToStr(req.getParameter("product_id"));//产品代码
		String product_name = ActionHelper.getNullToStr(req.getParameter("product_name"));//产品名称
		String product_type = ActionHelper.getNullToStr(req.getParameter("product_type"));//产品分类1
		String product_type2 = ActionHelper.getNullToStr(req.getParameter("product_type2"));//产品分类2
		String product_type3 = ActionHelper.getNullToStr(req.getParameter("product_type3"));//产品分类3
		String ins_type = ActionHelper.getNullToStr(req.getParameter("ins_type"));//主附险标志
		String period_type = ActionHelper.getNullToStr(req.getParameter("period_type"));//保险期限类型
		String status = ActionHelper.getNullToStr(req.getParameter("status"));//产品状态代码
		IProductLlifeVOModel model = new ProductLlifeVOModel();
		model.setPageCount(ActionHelper.getPage(req));
		model.setInsBranch_id(insBranch_id);
		model.setProduct_id(product_id);
		model.setProduct_name(product_name);
		model.setProduct_type(product_type);
		model.setProduct_type2(product_type2);
		model.setProduct_type3(product_type3);
		model.setIns_type(ins_type);
		model.setPeriod_type(period_type);
		model.setStatus(status);
		
		ReturnMsg returnMsg = ProductLlifeService.queryProductLlifeList(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
		return returnPage(res, returnMsg,"ca/cacore/MSSS/productLlife/productLlife_Query");
		
	}
	
	/**
	 * @param 传入Request ，Response
	 * @return 返回一个ModelAndView对象
	 * @description 查看明细
	 */
	@RequestMapping("/MSSS/ProductLlife/viewProductLlife.do")
	public ModelAndView viewProductLlife(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		IProductLlifeVOModel model = new ProductLlifeVOModel();
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		model.setSeq_id(seq_id);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg  =  ProductLlifeService.getProductLlifeVO(model);//根据seq_id查询出该产品所有信息
		ReturnMsgHelper rmHelper =  new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/MSSS/productLlife/productLlife_View");
	}
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询单个产品信息
	 */
	@RequestMapping("/MSSS/ProductLlife/goModifyProductLlifePage.do")
	public ModelAndView goModifyProductLlifePage(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		IProductLlifeVOModel model = new ProductLlifeVOModel();
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));//获取产品代码
		Integer item_id = ActionHelper.getNullToInteger(req.getParameter("item_id"));//获取产品是否出单
		model.setSeq_id(seq_id);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg  =  ProductLlifeService.getProductLlifeVO(model);//根据seq_id查询出该产品所有信息
		ReturnMsgHelper rmHelper =  new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper", rmHelper);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		
		if(item_id==1){  //出单后产品
			return new ModelAndView("ca/cacore/MSSS/productLlife/productLlife_Modify");
		}
		return new ModelAndView("ca/cacore/MSSS/productLlife/bef_productLlife_Modify");
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 修改产品信息
	 */
	@RequestMapping("/MSSS/ProductLlife/modifyProductLlife.do")
	public ModelAndView modifyProductLlife(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));//获取产品代码
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//
		String product_id = ActionHelper.getNullToStr(req.getParameter("master_id"));// 险种代码
		String product_name = ActionHelper.getNullToStr(req.getParameter("product_name"));//产品名称
		String product_abbr = ActionHelper.getNullToStr(req.getParameter("product_abbr"));// 产品简称
		String product_enName = ActionHelper.getNullToStr(req.getParameter("product_enName"));//产品英文名称
		String product_enAbbr = ActionHelper.getNullToStr(req.getParameter("product_enAbbr"));//产品英文简称
		String product_type= ActionHelper.getNullToStr(req.getParameter("product_type"));//产品分类（产品大类）
		String product_type2 = ActionHelper.getNullToStr(req.getParameter("product_type2"));//产品分类2
		String product_type3 = ActionHelper.getNullToStr(req.getParameter("product_type3"));// 产品分类3
		String ins_type =  ActionHelper.getNullToStr(req.getParameter("ins_type"));//主附险标志
		String period_type =  ActionHelper.getNullToStr(req.getParameter("period_type"));//保险期限类型
		String renew =  ActionHelper.getNullToStr(req.getParameter("renew"));//是否可续保 Y 可以 N 不可以
		String surr_permit =  ActionHelper.getNullToStr(req.getParameter("surr_permit"));//是否可退保
		String insuredFlag =  ActionHelper.getNullToStr(req.getParameter("insuredFlag"));//是否多被保人
		String is_auth_partreceived  = ActionHelper.getNullToStr(req.getParameter("is_auth_partreceived"));//是否可以部分领取年金
		String is_can_borrow  = ActionHelper.getNullToStr(req.getParameter("is_can_borrow"));//  是否可以保单借款
		String benefit_type =  ActionHelper.getNullToStr(req.getParameter("benefit_type"));//保障范围说明
		String []  charge_type_codes =  req.getParameterValues("charge_type_code");//缴费方式代码
		//缴费方式代码 拼接成字符串的形式
		String charge_type_code = "";
		if(charge_type_codes != null){
			for(int i=0;i<charge_type_codes.length;i++){
				if(i == charge_type_codes.length-1){
					charge_type_code =charge_type_code+charge_type_codes[i];
				}else{
				 charge_type_code =charge_type_code+charge_type_codes[i]+"#,#";
				}
			}
		}
		String []  coverage_period_codes =  req.getParameterValues("coverage_period_code");//保障期限类型代码
		//保障期限类型代码 拼接成字符串的形式
		String coverage_period_code = "";
		if(coverage_period_codes != null){
			for(int i=0;i<coverage_period_codes.length;i++){
				if(i == coverage_period_codes.length-1){
					coverage_period_code =coverage_period_code+coverage_period_codes[i];
				}else{
					coverage_period_code =coverage_period_code+coverage_period_codes[i]+"#,#";
				}
			}
		}
		String []  charge_period_codes =  req.getParameterValues("charge_period_code"); //缴费期限类型代码
		//缴费期限类型代码 拼接成字符串的形式
		String charge_period_code = "";
		if(charge_period_codes != null){
			for(int i=0;i<charge_period_codes.length;i++){
				if(i == charge_period_codes.length-1){
					charge_period_code =charge_period_code+charge_period_codes[i];
				}else{
					charge_period_code =charge_period_code+charge_period_codes[i]+"#,#";
				}
			}
		}
		String duty =  ActionHelper.getNullToStr(req.getParameter("duty"));//基本责任说明
		String policy_holder =  ActionHelper.getNullToStr(req.getParameter("policy_holder"));//投保人员限制说明
		String exception =  ActionHelper.getNullToStr(req.getParameter("exception"));//保险责任免除说明
		String start_date =  ActionHelper.getNullToStr(req.getParameter("start_date"));//起售时间
		String end_date =  ActionHelper.getNullToStr(req.getParameter("end_date"));//停售时间
		String status =  ActionHelper.getNullToStr(req.getParameter("status"));//状态代码
		String remark =  ActionHelper.getNullToStr(req.getParameter("remark"));//备注
		String []  product_ids =  req.getParameterValues("product_id");//产品附加代码
		
		IProductLlifeVOModel model = new ProductLlifeVOModel();
		model.setSeq_id(seq_id);
		model.setInsBranch_id(insBranch_id);
		model.setInsuredFlag(insuredFlag);
		model.setPeriod_type(period_type);
		model.setPolicy_holder(policy_holder);
		model.setProduct_abbr(product_abbr);
		model.setProduct_enAbbr(product_enAbbr);
		model.setProduct_id(product_id);
		model.setProduct_enName(product_enName);
		model.setProduct_name(product_name);
		model.setProduct_type(product_type);
		model.setProduct_type2(product_type2);
		model.setProduct_type3(product_type3);
		model.setBenefit_type(benefit_type);
		
		model.setCharge_type_codes(charge_type_codes);
		model.setCharge_type_code(charge_type_code);
		model.setCoverage_period_codes(coverage_period_codes);
		model.setCoverage_period_code(coverage_period_code);
		model.setCharge_period_codes(charge_period_codes);
		model.setCharge_period_code(charge_period_code);
		
		model.setDuty(duty);
		model.setIns_type(ins_type);
		model.setRenew(renew);
		model.setException(exception);
		model.setStart_date(DateUtil.string2Date(start_date));
		model.setEnd_date(DateUtil.string2Date(end_date));
		model.setRemark(remark);
		model.setStatus(status);
		model.setSurr_permit(surr_permit);
		model.setProduct_ids(product_ids);
		model.setIs_auth_partreceived(is_auth_partreceived);
		model.setIs_can_borrow(is_can_borrow);
		
		model.setIns_type_names(req.getParameterValues("ins_type_names"));//主附险名称用于后台的主附险校验
		model.setInsBranch_ids(req.getParameterValues("insBranch_ids"));//保险公司名称用于后台的是否属于同一家保险公司
		
		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = ProductLlifeService.modifyProductLlife(model,user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper",rmHelper);

        res.getWriter().write("{\"successFlag\": "+returnMsg.isSuccessflag()+", \"message\": \""+returnMsg.getMsgStr()+"\"}");

		return null;
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 修改产品信息
	 */
	@RequestMapping("/MSSS/ProductLlife/addProductLlife.do")
	public ModelAndView addProductLlife(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司
		String product_id = ActionHelper.getNullToStr(req.getParameter("master_id"));// 主产品代码
		String product_name = ActionHelper.getNullToStr(req.getParameter("product_name"));//产品名称
		String product_abbr = ActionHelper.getNullToStr(req.getParameter("product_abbr"));// 产品简称
		String product_enName = ActionHelper.getNullToStr(req.getParameter("product_enName"));//产品英文名称
		String product_enAbbr = ActionHelper.getNullToStr(req.getParameter("product_enAbbr"));//产品英文简称
		String product_type= ActionHelper.getNullToStr(req.getParameter("product_type"));//产品分类（产品大类）
		String product_type2 = ActionHelper.getNullToStr(req.getParameter("product_type2"));//产品分类2
		String product_type3 = ActionHelper.getNullToStr(req.getParameter("product_type3"));// 产品分类3
		String ins_type =  ActionHelper.getNullToStr(req.getParameter("ins_type"));//主附险标志
		String period_type =  ActionHelper.getNullToStr(req.getParameter("period_type"));//保险期限类型
		String renew =  ActionHelper.getNullToStr(req.getParameter("renew"));//是否可续保 Y 可以 N 不可以
		String is_auth_partreceived  = ActionHelper.getNullToStr(req.getParameter("is_auth_partreceived"));//是否可以部分领取年金
		String is_can_borrow  = ActionHelper.getNullToStr(req.getParameter("is_can_borrow"));//  是否可以保单借款
		String surr_permit =  ActionHelper.getNullToStr(req.getParameter("surr_permit"));//是否可退保
		String insuredFlag =  ActionHelper.getNullToStr(req.getParameter("insuredFlag"));//是否多被保人
		String benefit_type =  ActionHelper.getNullToStr(req.getParameter("benefit_type"));//保障范围说明
		String duty =  ActionHelper.getNullToStr(req.getParameter("duty"));//基本责任说明
		String policy_holder =  ActionHelper.getNullToStr(req.getParameter("policy_holder"));//投保人员限制说明
		String exception =  ActionHelper.getNullToStr(req.getParameter("exception"));//保险责任免除说明
		String start_date =  ActionHelper.getNullToStr(req.getParameter("start_date"));//起售时间
		String end_date =  ActionHelper.getNullToStr(req.getParameter("end_date"));//停售时间
		String remark =  ActionHelper.getNullToStr(req.getParameter("remark"));//是否有产品
		String []  charge_type_codes =  req.getParameterValues("charge_type_code");//缴费方式代码
		//缴费方式代码 拼接成字符串的形式
		String charge_type_code = "";
		if(charge_type_codes != null){
			for(int i=0;i<charge_type_codes.length;i++){
				if(i == charge_type_codes.length-1){
					charge_type_code =charge_type_code+charge_type_codes[i];
				}else{
				 charge_type_code =charge_type_code+charge_type_codes[i]+"#,#";
				}
			}
		}
		String []  coverage_period_codes =  req.getParameterValues("coverage_period_code");//保障期限类型代码
		//保障期限类型代码 拼接成字符串的形式
		String coverage_period_code = "";
		if(coverage_period_codes != null){
			for(int i=0;i<coverage_period_codes.length;i++){
				if(i == coverage_period_codes.length-1){
					coverage_period_code =coverage_period_code+coverage_period_codes[i];
				}else{
					coverage_period_code =coverage_period_code+coverage_period_codes[i]+"#,#";
				}
			}
		}
		String []  charge_period_codes =  req.getParameterValues("charge_period_code"); //缴费期限类型代码
		//缴费期限类型代码 拼接成字符串的形式
		String charge_period_code = "";
		if(charge_period_codes != null){
			for(int i=0;i<charge_period_codes.length;i++){
				if(i == charge_period_codes.length-1){
					charge_period_code =charge_period_code+charge_period_codes[i];
				}else{
					charge_period_code =charge_period_code+charge_period_codes[i]+"#,#";
				}
			}
		}
		
		String []  product_ids =  req.getParameterValues("product_id");//产品附加代码
		
		IProductLlifeVOModel model = new ProductLlifeVOModel();
		model.setInsBranch_id(insBranch_id);
		model.setInsuredFlag(insuredFlag);
		model.setPeriod_type(period_type);
		model.setPolicy_holder(policy_holder);
		model.setProduct_abbr(product_abbr);
		model.setProduct_enAbbr(product_enAbbr);
		model.setProduct_id(product_id);
		model.setProduct_enName(product_enName);
		model.setProduct_name(product_name);
		model.setProduct_type(product_type);
		model.setProduct_type2(product_type2);
		model.setProduct_type3(product_type3);
		model.setBenefit_type(benefit_type);
		model.setDuty(duty);
		model.setIns_type(ins_type);
		model.setRenew(renew);
		model.setIs_auth_partreceived(is_auth_partreceived);
		model.setIs_can_borrow(is_can_borrow);
		model.setException(exception);
		model.setStart_date(DateUtil.string2Date(start_date));
		model.setEnd_date(DateUtil.string2Date(end_date));
		model.setRemark(remark);
		model.setSurr_permit(surr_permit);
		
		model.setCharge_type_codes(charge_type_codes);
		model.setCharge_type_code(charge_type_code);
		model.setCoverage_period_codes(coverage_period_codes);
		model.setCoverage_period_code(coverage_period_code);
		model.setCharge_period_codes(charge_period_codes);
		model.setCharge_period_code(charge_period_code);
		
		model.setProduct_ids(product_ids);
		model.setIns_type_names(req.getParameterValues("ins_type_names"));//主附险名称用于后台的主附险校验
		model.setInsBranch_ids(req.getParameterValues("insBranch_ids"));//保险公司名称用于后台的是否属于同一家保险公司

		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = ProductLlifeService.addProductLlife(model,user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
		}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper",rmHelper);

        res.getWriter().write("{\"successFlag\": "+returnMsg.isSuccessflag()+", \"message\": \""+returnMsg.getMsgStr()+"\"}");

        return null;
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return
	* @throws Exception ModelAndView
	* @description:删除未出单的产品
	*/
	@RequestMapping("/MSSS/ProductLlife/deleteProductLlife.do")
	public ModelAndView deleteProductLlife(HttpServletRequest req,HttpServletResponse res) throws Exception {
		
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_idForDel"));//获取产品代码
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_idForDel"));//保险公司
		String product_id = ActionHelper.getNullToStr(req.getParameter("product_idForDel"));// 主产品代码
		String ins_type =  ActionHelper.getNullToStr(req.getParameter("ins_typeForDel"));//主附险标志
		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		IProductLlifeModel plifem = new ProductLlifeModel();
		plifem.setSeq_id(seq_id);
		plifem.setInsBranch_id(insBranch_id);
		plifem.setProduct_id(product_id);
		plifem.setIns_type(ins_type);
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = ProductLlifeService.deteleProductLlife(plifem,user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
		}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper2",rmHelper);
		return queryProductLlifeList(req,res);
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @throws IOException void
	* @description:根据输入的产品代码，选定的保险公司带出产品信息
	*/
	@RequestMapping("/MSSS/ProductLlife/getProductLife4Pkg.do")
	public void getProductLife4Pkg(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String product_id=ActionHelper.getNullToStr(req.getParameter("product_id"));
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));
		String returnInfo ="";
		if(!"".equals(product_id) && !"".equals(insBranch_id) ){
		IProductLlifeVOModel model=new ProductLlifeVOModel();
		model.setProduct_id(product_id);
		model.setInsBranch_id(insBranch_id);
			returnInfo = ProductLlifeService.getProductLife4Pkg(model);
		}else{
			returnInfo = "{isSuccess:false}";
		}
		res.getWriter().print(returnInfo);
	}
	
	/** 
	* 
	* @param req
	* @param res void
	* @description:判断产品代码是否重复（同一家保险公司）
	*/
	@RequestMapping("/MSSS/ProductLlife/checkProductIdUnique.do")
	public void checkProductIdUnique(HttpServletRequest req,HttpServletResponse res){
		String master_id=ActionHelper.getNullToStr(req.getParameter("master_id"));
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));
		String returnInfo ="";
		if(!"".equals(master_id) && !"".equals(insBranch_id) ){
		IProductLlifeVOModel model=new ProductLlifeVOModel();
		model.setProduct_id(master_id);
		model.setInsBranch_id(insBranch_id);
		returnInfo = returnInfo+ProductLlifeService.checkProductIdUnique(model);
		try {
			res.getWriter().print(returnInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
	
	/** 
	* 
	* @param req
	* @param res void
	* @description:判断产品代码是否重复（同一家保险公司）
	*/
	@RequestMapping("/MSSS/ProductLlife/checkProductNameUnique.do")
	public void checkProductNameUnique(HttpServletRequest req,HttpServletResponse res){
		Integer seq_id=ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		String product_name=ActionHelper.getNullToStr(req.getParameter("product_name"));
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("insBranch_id"));
		String returnInfo ="";
		if(!"".equals(product_name) && !"".equals(insBranch_id) ){
		IProductLlifeVOModel model=new ProductLlifeVOModel();
		model.setSeq_id(seq_id);
		model.setProduct_name(product_name);
		model.setInsBranch_id(insBranch_id);
		returnInfo = returnInfo+ProductLlifeService.checkProductNameUnique(model);
		try {
			res.getWriter().print(returnInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
}
