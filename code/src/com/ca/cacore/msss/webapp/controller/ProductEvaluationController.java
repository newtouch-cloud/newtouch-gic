package com.ca.cacore.msss.webapp.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.common.CodeTypeConst;
import com.ca.cacore.msss.model.bo.IProductAssessmentModel;
import com.ca.cacore.msss.model.bo.ProductAssessmentModel;
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
 * @description 产品评估支持信息评估支持信息维护Controller层
 */
@Controller
public class ProductEvaluationController extends BaseController {

	@Autowired
	private IProductLlifeService productLlifeService;
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:进入到查询出寿险产品评估支持信息信息页面
	*/
	@RequestMapping("/MSSS/ProductEvaluation/toQueryLifeProductEvaluationList.do")
	public ModelAndView toQueryLifeProductEvaluationList(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/MSSS/productEvaluation/lifeProductEvaluation_Query");
	}
	
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询出寿险产品评估支持信息信息，或根据条件查询
	 */
	
	@RequestMapping("/MSSS/ProductEvaluation/queryLifeProductEvaluationList.do")
	public ModelAndView queryLifeProductEvaluationList(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司
		String product_id = ActionHelper.getNullToStr(req.getParameter("product_id"));//产品代码
		String product_name = ActionHelper.getNullToStr(req.getParameter("product_name"));//产品名称
		String product_type2 = ActionHelper.getNullToStr(req.getParameter("product_type2"));//产品评估支持信息分类2
		String product_type3 = ActionHelper.getNullToStr(req.getParameter("product_type3"));//产品评估支持信息分类3
		String ins_type = ActionHelper.getNullToStr(req.getParameter("ins_type"));//主附险标志
		String period_type = ActionHelper.getNullToStr(req.getParameter("period_type"));//保险期限类型
		String status = ActionHelper.getNullToStr(req.getParameter("status"));//产品评估支持信息状态代码
		IProductLlifeVOModel model = new ProductLlifeVOModel();
		model.setPageCount(ActionHelper.getPage(req));
		model.setInsBranch_id(insBranch_id);
		model.setProduct_id(product_id);
		model.setProduct_name(product_name);
		model.setProduct_type(CodeTypeConst.PRO_TYPE_CODE1);
		model.setProduct_type2(product_type2);
		model.setProduct_type3(product_type3);
		model.setIns_type(ins_type);
		model.setPeriod_type(period_type);
		model.setStatus(status);
		
		ReturnMsg returnMsg = productLlifeService.queryProductLlifeList(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
		return returnPage(res, returnMsg,"ca/cacore/MSSS/productEvaluation/lifeProductEvaluation_Query");
	}
	
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:进入到查询出健康险产品评估支持信息信息页面
	*/
	@RequestMapping("/MSSS/ProductEvaluation/toQueryHealthProductEvaluationList.do")
	public ModelAndView toQueryHealthProductEvaluationList(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/MSSS/productEvaluation/healthProductEvaluation_Query");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return
	* @throws Exception ModelAndView
	* @description:
	*/
	@RequestMapping("/MSSS/ProductEvaluation/queryHealthProductEvaluationList.do")
	public ModelAndView queryHealthProductEvaluationList(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司
		String product_id = ActionHelper.getNullToStr(req.getParameter("product_id"));//产品代码
		String product_name = ActionHelper.getNullToStr(req.getParameter("product_name"));//产品名称
		String product_type2 = ActionHelper.getNullToStr(req.getParameter("product_type2"));//产品评估支持信息分类2
		String product_type3 = ActionHelper.getNullToStr(req.getParameter("product_type3"));//产品评估支持信息分类3
		String ins_type = ActionHelper.getNullToStr(req.getParameter("ins_type"));//主附险标志
		String period_type = ActionHelper.getNullToStr(req.getParameter("period_type"));//保险期限类型
		String status = ActionHelper.getNullToStr(req.getParameter("status"));//产品评估支持信息状态代码
		IProductLlifeVOModel model = new ProductLlifeVOModel();
		model.setPageCount(ActionHelper.getPage(req));
		model.setInsBranch_id(insBranch_id);
		model.setProduct_id(product_id);
		model.setProduct_name(product_name);
		model.setProduct_type(CodeTypeConst.PRO_TYPE_CODE2);
		model.setProduct_type2(product_type2);
		model.setProduct_type3(product_type3);
		model.setIns_type(ins_type);
		model.setPeriod_type(period_type);
		model.setStatus(status);
		
		ReturnMsg returnMsg = productLlifeService.queryProductLlifeList(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
		return returnPage(res, returnMsg,"ca/cacore/MSSS/productEvaluation/healthProductEvaluation_Query");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:进入到查询出意外险产品评估支持信息信息页面
	*/
	@RequestMapping("/MSSS/ProductEvaluation/toQueryAccidentProductEvaluationList.do")
	public ModelAndView toQueryAccidentProductEvaluationList(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/MSSS/productEvaluation/accidentProductEvaluation_Query");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return
	* @throws Exception ModelAndView
	* @description:
	*/
	@RequestMapping("/MSSS/ProductEvaluation/queryAccidentProductEvaluationList.do")
	public ModelAndView queryAccidentProductEvaluationList(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//保险公司
		String product_id = ActionHelper.getNullToStr(req.getParameter("product_id"));//产品代码
		String product_name = ActionHelper.getNullToStr(req.getParameter("product_name"));//产品名称
		String product_type2 = ActionHelper.getNullToStr(req.getParameter("product_type2"));//产品评估支持信息分类2
		String product_type3 = ActionHelper.getNullToStr(req.getParameter("product_type3"));//产品评估支持信息分类3
		String ins_type = ActionHelper.getNullToStr(req.getParameter("ins_type"));//主附险标志
		String period_type = ActionHelper.getNullToStr(req.getParameter("period_type"));//保险期限类型
		String status = ActionHelper.getNullToStr(req.getParameter("status"));//产品评估支持信息状态代码
		IProductLlifeVOModel model = new ProductLlifeVOModel();
		model.setPageCount(ActionHelper.getPage(req));
		model.setInsBranch_id(insBranch_id);
		model.setProduct_id(product_id);
		model.setProduct_name(product_name);
		model.setProduct_type(CodeTypeConst.PRO_TYPE_CODE3);
		model.setProduct_type2(product_type2);
		model.setProduct_type3(product_type3);
		model.setIns_type(ins_type);
		model.setPeriod_type(period_type);
		model.setStatus(status);
		
		ReturnMsg returnMsg = productLlifeService.queryProductLlifeList(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
		return returnPage(res, returnMsg,"ca/cacore/MSSS/productEvaluation/accidentProductEvaluation_Query");
	}

	
	/**
	 * @param 传入Request ，Response
	 * @return 返回一个ModelAndView对象
	 * @description 查看明细
	 */
	@RequestMapping("/MSSS/ProductEvaluation/viewProductEvaluation.do")
	public ModelAndView viewProductEvaluationList(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String path = ActionHelper.getNullToStr(req.getParameter("path"));
		IProductLlifeVOModel model = new ProductLlifeVOModel();
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		model.setSeq_id(seq_id);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg  =  productLlifeService.getProductLlifeVO(model);//根据seq_id查询出该产品评估支持信息所有信息
		ReturnMsgHelper rmHelper =  new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/MSSS/productEvaluation/"+path+"ProductEvaluation_View");
	}
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询单个产品评估支持信息信息
	 */
	@RequestMapping("/MSSS/ProductEvaluation/goModifyProductEvaluationPage.do")
	public ModelAndView goModifyProductEvaluationPage(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String path = ActionHelper.getNullToStr(req.getParameter("path"));
		IProductLlifeVOModel model = new ProductLlifeVOModel();
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));//获取产品评估支持信息代码
		model.setSeq_id(seq_id);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg  =  productLlifeService.getProductLlifeVO(model);//根据seq_id查询出该产品评估支持信息所有信息
		ReturnMsgHelper rmHelper =  new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/MSSS/productEvaluation/"+path+"ProductEvaluation_Modify");
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 修改产品评估支持信息信息
	 */
	@RequestMapping("/MSSS/ProductEvaluation/modifyProductEvaluation.do")
	public ModelAndView modifyProductEvaluation(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String path = ActionHelper.getNullToStr(req.getParameter("path"));
		
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));//获取产品seq_id
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id"));//
		String product_id = ActionHelper.getNullToStr(req.getParameter("product_id"));// 险种代码
		String product_name = ActionHelper.getNullToStr(req.getParameter("product_name"));//产品评估支持信息名称
		String product_abbr = ActionHelper.getNullToStr(req.getParameter("product_abbr"));// 产品评估支持信息简称
		String product_enName = ActionHelper.getNullToStr(req.getParameter("product_enName"));//产品评估支持信息英文名称
		String product_enAbbr = ActionHelper.getNullToStr(req.getParameter("product_enAbbr"));//产品评估支持信息英文简称
		String product_type= ActionHelper.getNullToStr(req.getParameter("product_type"));//产品评估支持信息分类（产品评估支持信息大类）
		String product_type2 = ActionHelper.getNullToStr(req.getParameter("product_type2"));//产品评估支持信息分类2
		String product_type3 = ActionHelper.getNullToStr(req.getParameter("product_type3"));// 产品评估支持信息分类3
		String ins_type =  ActionHelper.getNullToStr(req.getParameter("ins_type"));//主附险标志
		String period_type =  ActionHelper.getNullToStr(req.getParameter("period_type"));//保险期限类型
		String renew =  ActionHelper.getNullToStr(req.getParameter("renew"));//是否可续保 Y 可以 N 不可以
		String surr_permit =  ActionHelper.getNullToStr(req.getParameter("surr_permit"));//是否可退保
		String insuredFlag =  ActionHelper.getNullToStr(req.getParameter("insuredFlag"));//是否多被保人
		String benefit_type =  ActionHelper.getNullToStr(req.getParameter("benefit_type"));//保障范围说明
		String []  charge_type_codes =  req.getParameterValues("charge_type_code");//缴费方式代码
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
		//获取星级评估信息开始
		Integer assess_type_code0 =  ActionHelper.getNullToInteger(req.getParameter("assess_type_code0"));//产品价格
		IProductAssessmentModel pModel0= new ProductAssessmentModel();
		pModel0.setAssessment_type("0");
		pModel0.setAssess_stars(assess_type_code0);
		Integer assess_type_code1 =  ActionHelper.getNullToInteger(req.getParameter("assess_type_code1"));//寿险
		IProductAssessmentModel pModel1= new ProductAssessmentModel();
		pModel1.setAssessment_type("1");
		pModel1.setAssess_stars(assess_type_code1);
		Integer assess_type_code2 =  ActionHelper.getNullToInteger(req.getParameter("assess_type_code2"));//重大疾病
		IProductAssessmentModel pModel2= new ProductAssessmentModel();
		pModel2.setAssessment_type("2");
		pModel2.setAssess_stars(assess_type_code2);
		Integer assess_type_code3 =  ActionHelper.getNullToInteger(req.getParameter("assess_type_code3"));//养老功能
		IProductAssessmentModel pModel3= new ProductAssessmentModel();
		pModel3.setAssessment_type("3");
		pModel3.setAssess_stars(assess_type_code3);
		Integer assess_type_code4 =  ActionHelper.getNullToInteger(req.getParameter("assess_type_code4"));//理财功能
		IProductAssessmentModel pModel4= new ProductAssessmentModel();
		pModel4.setAssessment_type("4");
		pModel4.setAssess_stars(assess_type_code4);
		Integer assess_type_code5 =  ActionHelper.getNullToInteger(req.getParameter("assess_type_code5"));//医疗保销
		IProductAssessmentModel pModel5= new ProductAssessmentModel();
		pModel5.setAssessment_type("5");
		pModel5.setAssess_stars(assess_type_code5);
		
		Integer assess_type_code6 =  ActionHelper.getNullToInteger(req.getParameter("assess_type_code6"));//医疗补充
		IProductAssessmentModel pModel6= new ProductAssessmentModel();
		pModel6.setAssessment_type("6");
		pModel6.setAssess_stars(assess_type_code6);
		
		Integer assess_type_code7 =  ActionHelper.getNullToInteger(req.getParameter("assess_type_code7"));//子女教育
		IProductAssessmentModel pModel7= new ProductAssessmentModel();
		pModel7.setAssessment_type("7");
		pModel7.setAssess_stars(assess_type_code7);
		
		Integer assess_type_code8 =  ActionHelper.getNullToInteger(req.getParameter("assess_type_code8"));//资产传承
		IProductAssessmentModel pModel8= new ProductAssessmentModel();
		pModel8.setAssessment_type("8");
		pModel8.setAssess_stars(assess_type_code8);
		//获取星级评估信息结束
		
		List<IProductAssessmentModel> productAssessmentList = new ArrayList<IProductAssessmentModel>();
		
		productAssessmentList.add(pModel0);
		productAssessmentList.add(pModel1);
		productAssessmentList.add(pModel2);
		productAssessmentList.add(pModel3);
		productAssessmentList.add(pModel4);
		productAssessmentList.add(pModel5);
		productAssessmentList.add(pModel6);
		productAssessmentList.add(pModel7);
		productAssessmentList.add(pModel8);
		
		String description =  ActionHelper.getNullToStr(req.getParameter("description"));//产品评估支持信息
		
		
		IProductLlifeVOModel model = new ProductLlifeVOModel();
		model.setSeq_id(seq_id);
		model.setProduct_id(product_id);
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
		model.setProductAssessmentList(productAssessmentList);
		model.setDescription(description);
		
		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = productLlifeService.modifyProductEvaluation(model,user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper",rmHelper);

        res.getWriter().write("{\"successFlag\": "+returnMsg.isSuccessflag()+", \"message\": \""+returnMsg.getMsgStr()+"\"}");

        return null;
	}
}
