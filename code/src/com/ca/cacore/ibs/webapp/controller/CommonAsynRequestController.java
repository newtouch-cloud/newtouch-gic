package com.ca.cacore.ibs.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.ibs.model.vo.ContractLifeVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.IPolicyLifeVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeInfoVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifePeopleVOModel;
import com.ca.cacore.ibs.model.vo.PolicyLifeVOModel;
import com.ca.cacore.ibs.webapp.service.ICommonAsynRequestService;
import com.ca.cacore.common.CodeTypeConst;
import com.ca.cacore.csm.model.vo.CustomerVOModel;
import com.ca.cacore.csm.webapp.service.ICustomerService;
import com.ca.cacore.msss.model.bo.IProductLlifeModel;
import com.ca.cacore.msss.model.bo.ProductLlifeModel;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.utils.dateutil.DateUtil;

/**
 * 保单模块：异步请求
 * @since 2013-12-12
 * @author xuxiaozhen
 * @Description 保存查询中ajax方法Controller
 */
@Controller
public class CommonAsynRequestController {
	
	
	
	@Autowired private ICommonAsynRequestService commonAsynRequestService;
	@Autowired private ICustomerService customerService;
	
	@RequestMapping("/cbs/commonAsyn/getSalesInfo.do")
	public ModelAndView getSalesInfo(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		String sales_id = ActionHelper.getNullToStr(request.getParameter("agent_id"));
		IPolicyLifePeopleVOModel model = commonAsynRequestService.getSalesInfo(sales_id);
		String info="";
	    if(model== null){//人员不存在
			info="{success:'false2'}";
		}else if(!CodeTypeConst.SALES_STATUS_NORMAL.equals(model.getSales_status())){//人员状态不是在职1
			info="{success:'false1'}";
	   }else{//人员状态是在职
			info="{success:'true',sales_name:'"+ActionHelper.getNullToStr(model.getSales_name())+"',branch_id:'"+ActionHelper.getNullToStr(model.getBranch_id())+"',branch_name:'"+ActionHelper.getNullToStr(model.getBranch_name())+"'}";
		}
		List<String> list=new ArrayList<String>();
		list.add(info);
		response.getWriter().print(JSONArray.fromObject(list).toString());
	    return null;
	}
	/**
	 * 产品ajax查询方法
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @Description 根据产品id查询相关产品信息
	 */
	@RequestMapping("/cbs/commonAsyn/getProductInfo.do")
	public ModelAndView getProductInfo(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		String product_id = ActionHelper.getNullToStr(request.getParameter("product_id"));
		String insBranch_id = ActionHelper.getNullToStr(request.getParameter("insBranch_id"));
		IProductLlifeModel m = new ProductLlifeModel();
		m.setInsBranch_id(insBranch_id);
		m.setProduct_id(product_id);
		IProductLlifeModel model = commonAsynRequestService.getProductInfo(m);//查询产品信息
		String info="";
		if(model!=null){
			info="{success:'true',product_name:'"+ActionHelper.getNullToStr(model.getProduct_name())+"',"
					+ "ins_type:'"+ActionHelper.getNullToStr(model.getIns_type())+"',"
					+ "product_type:'"+ActionHelper.getNullToStr(model.getProduct_type())+"',"
					+ "product_type2:'"+ActionHelper.getNullToStr(model.getProduct_type2())+"',"
					+ "product_type3:'"+ActionHelper.getNullToStr(model.getProduct_type3())+"',"
					+ "status:'"+ActionHelper.getNullToStr(model.getStatus())+"',"
					+ "insuredFlag:'"+ActionHelper.getNullToStr(model.getInsuredFlag())+"',"
					+ "renew:'"+ActionHelper.getNullToStr(model.getRenew())+"'}";
		}else{
			info="{success:'false'}";
		}
		List<String> list = new ArrayList<String>();
		list.add(info);
		response.getWriter().print(JSONArray.fromObject(list).toString());
	    return null;
	}
	/**
	 * 客户ajax查询方法
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @Description 根据客户id查询相关客户信息
	 */
	@RequestMapping("/cbs/commonAsyn/getCustomerInfo.do")
	public ModelAndView getCustomerInfo(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		String certi_type = ActionHelper.getNullToStr(request.getParameter("certi_type"));
		String certi_no = ActionHelper.getNullToStr(request.getParameter("certi_no"));
		String name = ActionHelper.getNullToStr(request.getParameter("name"));
		String gender = ActionHelper.getNullToStr(request.getParameter("gender"));
		String birthday = ActionHelper.getNullToStr(request.getParameter("birthday"));
		IPolicyLifePeopleVOModel m =  new PolicyLifePeopleVOModel() ;
		m.setCerti_no(certi_no);
		m.setCerti_type(certi_type);
		m.setName(name);
		m.setGender(gender);
		m.setBirthday(DateUtil.string2Date(birthday));
		List<IPolicyLifePeopleVOModel> list= commonAsynRequestService.getCustomerInfo(m);
		String info="";
		if(list!=null&&list.size()!=0){
			IPolicyLifePeopleVOModel model = list.get(0);
			info="{success:'true',gender:'"+ActionHelper.getNullToStr(model.getGender())+"',birthday:'"+ActionHelper.getNullToStr(model.getBirthday())+"',"
			+"nationality:'"+ActionHelper.getNullToStr(model.getNationality())+"',nation:'"+ActionHelper.getNullToStr(model.getNation())+"',"
			+"homeplace:'"+ActionHelper.getNullToStr(model.getHomeplace())+"',marital_stat:'"+ActionHelper.getNullToStr(model.getMarital_stat())+"',"
			+"political:'"+ActionHelper.getNullToStr(model.getPolitical())+"',education2:'"+ActionHelper.getNullToStr(model.getEducation2())+"',"
			+"health:'"+ActionHelper.getNullToStr(model.getHealth())+"',height:'"+ActionHelper.getNullToStr(model.getHeight())+"',"
			+"weight:'"+ActionHelper.getNullToStr(model.getWeight())+"',mobile:'"+ActionHelper.getNullToStr(model.getMobile())+"',"
			+"telphone:'"+ActionHelper.getNullToStr(model.getTelphone())+"',fax:'"+ActionHelper.getNullToStr(model.getFax())+"',"
			+"email:'"+ActionHelper.getNullToStr(model.getEmail())+"',address:'"+ActionHelper.getNullToStr(model.getAddress())+"',"
			+"zip:'"+ActionHelper.getNullToStr(model.getZip())+"',job_com:'"+ActionHelper.getNullToStr(model.getJob_com())+"',"
			+"job_type:'"+ActionHelper.getNullToStr(model.getJob_type())+"',job_code:'"+ActionHelper.getNullToStr(model.getJob_code())+"',"
			+"job_tel:'"+ActionHelper.getNullToStr(model.getJob_tel())+"',is_telMsgService:'"+ActionHelper.getNullToStr(model.getIs_telMsgService())+"',"
			+"certi_validDate:'"+ActionHelper.getNullToStr(model.getCerti_validDate())+"',income_type:'"+ActionHelper.getNullToStr(model.getIncome_type())+"',"
			+"name:'"+ActionHelper.getNullToStr(model.getName())+"'}";
		}else{
			info="{success:'false'}";
		}		
		List<String> l = new ArrayList<String>();
		l.add(info);
		response.getWriter().print(JSONArray.fromObject(l).toString());
	    return null;
	}
	/**
	 * 客户ajax查询方法
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @Description 根据客户id查询相关客户信息
	 */
	@RequestMapping("/cbs/commonAsyn/getAjaxCustomerInfo.do")
	public ModelAndView getAjaxCustomerInfo(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		String customer_id = ActionHelper.getNullToStr(request.getParameter("customer_id"));//客户id
		String branch_id = ActionHelper.getNullToStr(request.getParameter("branch_id"));//机构id
		CustomerVOModel m =  new CustomerVOModel() ;
		m.setCustomer_id(customer_id);
		m.setBranch_id(branch_id);
		CustomerVOModel model= customerService.getCustomerInfoByCusBranID(m);
		String info ="";
		info="{customer_id:'"+ActionHelper.getNullToStr(model.getCustomer_id())+"',certi_no:'"+model.getCerti_no()+"',"
		   + "name:'"+model.getName()+"',certi_type:'"+model.getCerti_type()+"',gender:'"+model.getGender()+"',"
		   + "birthday:'"+model.getBirthday()+"',nationality:'"+ActionHelper.getNullToStr(model.getNationality())+"',income_type:'"+ActionHelper.getNullToStr(model.getIncome_type())+"',"
		   +"nation:'"+ActionHelper.getNullToStr(model.getNation())+"',homeplace:'"+ActionHelper.getNullToStr(model.getHomeplace())+"',marital_stat:'"+ActionHelper.getNullToStr(model.getMarital_stat())+"',"
		   +"political:'"+ActionHelper.getNullToStr(model.getPolitical())+"',education2:'"+ActionHelper.getNullToStr(model.getEducation2())+"',health:'"+ActionHelper.getNullToStr(model.getHealth())+"',"
		   + "height:'"+ActionHelper.getNullToStr(model.getHeight())+"',weight:'"+ActionHelper.getNullToStr(model.getWeight())+"',is_telmsgservice:'"+ActionHelper.getNullToStr(model.getIs_telmsgservice())+ "',"
		   + "mobile:'"+ActionHelper.getNullToStr(model.getMobile())+"',"
		   + "telphone:'"+ActionHelper.getNullToStr(model.getTelphone())+"',fax:'"+ActionHelper.getNullToStr(model.getFax())+"',email:'"+ActionHelper.getNullToStr(model.getEmail())+"',certi_validdate:'"+ActionHelper.getNullToStr(model.getCerti_validdate())+"',"
		   + "address:'"+ActionHelper.getNullToStr(model.getAddress())+"',zip:'"+ActionHelper.getNullToStr(model.getZip())+"',job_com:'"+ActionHelper.getNullToStr(model.getJob_com())+"',"
		   + "job_code:'"+ActionHelper.getNullToStr(model.getJob_code())+"',job_tel:'"+ActionHelper.getNullToStr(model.getJob_tel())+"',job_type:'"+ActionHelper.getNullToStr(model.getJob_type())+ "'}";
		List<String> list=new ArrayList<String>();
		list.add(info);
		response.getWriter().print(JSONArray.fromObject(list).toString());
		return null;
	}
	
	/**
	 * 验证投保单号是否重复
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/commonAsyn/validateSendCode.do")
	public ModelAndView validateSendCode(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		String send_code = ActionHelper.getNullToStr(request.getParameter("send_code"));
		String insBranch_id =   ActionHelper.getNullToStr(request.getParameter("insBranch_id"));
		IPolicyLifeVOModel model = new PolicyLifeVOModel();
		model.setSend_code(send_code);
		model.setInsBranch_id(insBranch_id);
		boolean send_code_repeat  =commonAsynRequestService.validateSendCode(model);//true 不重复 false 重复
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");  //解决火狐浏览器ajax返回的data输出为[object XMLDocument]的问题
		this.encodehead(request, response).print(send_code_repeat);
	    return null;
	}
	
	/**
	 * 验证保单号是否重复
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cbs/commonAsyn/validatePolicyCode.do")
	public ModelAndView validatePolicyCode(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		String policy_code = ActionHelper.getNullToStr(request.getParameter("policy_code"));
		String insBranch_id =   ActionHelper.getNullToStr(request.getParameter("insBranch_id"));
		ContractLifeVOModel model = new ContractLifeVOModel();
		model.setPolicy_code(policy_code);
		model.setInsBranch_id(insBranch_id);
		boolean policy_code_repeat  =commonAsynRequestService.validatePolicyCode(model);//true 不重复 false 重复
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");  //解决火狐浏览器ajax返回的data输出为[object XMLDocument]的问题
		this.encodehead(request, response).print(policy_code_repeat);
	    return null;
	}
	
	/**
	 * Ajax辅助方法 获取 PrintWriter
	 * //解决火狐浏览器ajax返回的data输出为[object XMLDocument]的问题或者使用lastIndexOf方法报错lastIndexOf is no a function 
	 * @return
	 * @throws IOException 
	 * @throws IOException 
	 * request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
	 */
	private PrintWriter encodehead(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		return response.getWriter();
	}
	
	/**
	 * 二次提交更新投保单状态
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/cbs/commonAsyn/updatePolicyLifeStatus.do")
	public ModelAndView updatePolicyLifeStatus(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		String policy_id = ActionHelper.getNullToStr(request.getParameter("policy_id"));
		PolicyLifeInfoVOModel model = new PolicyLifeInfoVOModel();
		model.setPolicy_id(Long.valueOf(policy_id));
		String result_flag =  commonAsynRequestService.modifyPolicyLifeStatus(model);
		this.encodehead(request, response).print(result_flag);
	    return null;
	}
}
