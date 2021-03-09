package com.ca.cacore.msss.webapp.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.vo.IProductFeeRateVOModel;
import com.ca.cacore.msss.model.vo.ProductFeeRateVOModel;
import com.ca.cacore.msss.webapp.service.IProFeeRateService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.ThreadLocalHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;

/**
 * 
 * @author fqy
 * @since 2014-1-10
 * @description 产品手续费率Controller层接口
 */
@Controller
public class ProFeeRateController extends BaseController {

	@Autowired
	private IProFeeRateService ProFeeRateService;

	/** 
	* @param 传入Request,Response
	* @return 返回一个ModelAndView对象
	* @description:跳转到产品手续费率管理页面
	*/
	@RequestMapping("/MSSS/ProFeeRate/toProFeeRateMana.do")
	public ModelAndView toQueryProFeeRateList(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/MSSS/productFeeRate/proFeeRateMana");
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询出所有的产品手续费率信息
	 */
	@RequestMapping("/MSSS/ProFeeRate/ProFeeRateMana.do")
	public ModelAndView queryProFeeRateList(HttpServletRequest req,HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		String insBranch_id = ActionHelper.getNullToStr(req.getParameter("insBranch_id")); // 保险公司代码
		String product_id = ActionHelper.getNullToStr(req.getParameter("product_id")); // 产品代码
		String product_name = ActionHelper.getNullToStr(req.getParameter("product_name")); // 产品名称
		String charge_type = ActionHelper.getNullToStr(req.getParameter("charge_type")); // 缴费方式
		IProductFeeRateVOModel model = new ProductFeeRateVOModel();
		model.setPageCount(ActionHelper.getPage(req));
		model.setInsBranch_id(insBranch_id);
		model.setProduct_id(product_id);
		model.setProduct_name(product_name);
		model.setCharge_type(charge_type);
		ReturnMsg returnMsg = ProFeeRateService.queryProFeeRateList(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
		return returnPage(res, returnMsg, "ca/cacore/MSSS/productFeeRate/proFeeRateMana");
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查看选中的产品手续费率明细信息
	 */
	@RequestMapping("/MSSS/ProFeeRate/ProFeeRateView.do")
	public ModelAndView queryProFeeRateView(HttpServletRequest req,HttpServletResponse res){
		String seq_id = req.getParameter("seq_id");
		IProductFeeRateVOModel model = new ProductFeeRateVOModel();
		model.setSeq_id(Integer.parseInt(seq_id));
		ReturnMsg returnMsg = ProFeeRateService.queryProFeeRateView(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/MSSS/productFeeRate/proFeeRateView");
	}
	
	/** 
	* @param 传入Request,Response
	* @return 返回一个ModelAndView对象
	* @description:跳转到产品手续费率修改页面
	*/
	@RequestMapping("/MSSS/ProFeeRate/goModifyProFeeRate.do")
	public ModelAndView goModifyProFeeRate(HttpServletRequest req,HttpServletResponse res){
		String seq_id = req.getParameter("seq_id");
		IProductFeeRateVOModel model = new ProductFeeRateVOModel();
		model.setSeq_id(Integer.parseInt(seq_id));
		ReturnMsg returnMsg = ProFeeRateService.queryProFeeRateView(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/MSSS/productFeeRate/proFeeRateModify");
	}
	
	/** 
	* @param 传入Request,Response
	* @return 返回一个ModelAndView对象
	* @description:修改选中的产品手续费率信息
	*/
	@RequestMapping("/MSSS/ProFeeRate/modifyProFeeRate.do")
	public ModelAndView modifyProFeeRate(HttpServletRequest req,HttpServletResponse res) throws Exception{
		//获得操作人员信息
		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		//获取入参信息
		IProductFeeRateVOModel model=this.getIProductFeeRateVOModel(req);
		//进行业务交易
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = ProFeeRateService.modifyProFeeRate(model, user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
			returnMsg.setDataTable(TransHelper.obj2map(model));
		}
		//设置返回参数
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		//跳转到页面
		return new ModelAndView("ca/cacore/MSSS/productFeeRate/proFeeRateModify");
	}
	
	/** 
	* @param 传入Request,Response
	* @return 返回一个ModelAndView对象
	* @description:新增产品手续费率信息
	*/
	@RequestMapping("/MSSS/ProFeeRate/insertProFeeRate.do")
	public ModelAndView insertProFeeRate(HttpServletRequest req,HttpServletResponse res) throws Exception{
		//获得操作人员信息
		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		//获取入参信息
		IProductFeeRateVOModel model=this.getIProductFeeRateVOModel(req);
		model.setCreateUser(user.getEmp_id());
		model.setModifyUser(user.getEmp_id());
		model.setPageCount(ActionHelper.getPage(req));
		//进行业务交易
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = ProFeeRateService.insertProFeeRate(model);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage());
			returnMsg.setDataTable(TransHelper.obj2map(model));
		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));
		//跳转到页面
		return new ModelAndView("ca/cacore/MSSS/productFeeRate/proFeeRateAdd");
	}
	
	/** 
	* @param 传入Request
	* @return 返回一个IProductFeeRateVOModel对象
	* @description:将页面中传来的数据封装到IProductFeeRateVOModel中
	*/
	public IProductFeeRateVOModel getIProductFeeRateVOModel(HttpServletRequest req){
		IProductFeeRateVOModel model = new ProductFeeRateVOModel();
		model.setSeq_id(ActionHelper.getNullToInteger(req.getParameter("seq_id"))); // 主键
		model.setInsBranch_id(ActionHelper.getNullToStr(req.getParameter("insBranch_id"))); // 保险公司
		model.setProduct_id(ActionHelper.getNullToStr(req.getParameter("product_id"))); //产品代码
		model.setProduct_name(ActionHelper.getNullToStr(req.getParameter("product_name"))); //产品名称
		model.setCoverage_period(ActionHelper.getNullToStr(req.getParameter("coverage_period"))); // 保障期限类型
		model.setCoverage_year(ActionHelper.getNullToStr(req.getParameter("coverage_year"))); // 保障年数
		model.setSell_way_name(ActionHelper.getNullToStr(req.getParameter("sell_way_name"))); // 销售方式
		model.setCharge_type(ActionHelper.getNullToStr(req.getParameter("charge_type"))); // 缴费方式
		model.setCharge_year(ActionHelper.getNullToInteger(req.getParameter("charge_year"))); // 缴费年限
		model.setPolicy_year(ActionHelper.getNullToStr(req.getParameter("policy_year"))); // 保单年度
		model.setPolicy_period(ActionHelper.getNullToStr(req.getParameter("policy_period"))); // 缴费次数
		model.setFee_rate(Double.valueOf(ActionHelper.getNullToStr(req.getParameter("fee_rate"))));// 手续费率
		model.setStart_date(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("start_date"))));//开始时间
		model.setEnd_date(DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("end_date"))));//结束时间
		return model;
	}
}
