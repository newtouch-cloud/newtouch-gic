package com.ca.cacore.msss.webapp.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.vo.IPkgLifeVOModel;
import com.ca.cacore.msss.model.vo.PkgLifeVOModel;
import com.ca.cacore.msss.webapp.service.IPkgLifeService;
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
 * @description 组合产品信息维护Controller层
 */
@Controller
public class PkgLifeController extends BaseController {

	@Autowired
	private IPkgLifeService PkgLifeService;
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:进入到查询组合产品页面
	*/
	@RequestMapping("/MSSS/PkgLife/toQueryPkgLifeList.do")
	public ModelAndView toQueryPkgLifeList(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/MSSS/pkgLife/pkgLife_Query");
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询出组合产品信息，或根据条件查询
	 */
	@RequestMapping("/MSSS/PkgLife/queryPkgLifeList.do")
	public ModelAndView queryPkgLifeList(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ThreadLocalHelper.set("isOpen", true);
		String pkg_id = ActionHelper.getNullToStr(req.getParameter("pkg_id"));//组合组合产品代码
		String pkg_name = ActionHelper.getNullToStr(req.getParameter("pkg_name"));//组合组合产品名称
		String status = ActionHelper.getNullToStr(req.getParameter("status"));//组合产品状态代码
		IPkgLifeVOModel model = new PkgLifeVOModel();
		model.setPageCount(ActionHelper.getPage(req));
		model.setPkg_id(pkg_id);
		model.setPkg_name(pkg_name);
		model.setStatus(status);
		
		ReturnMsg returnMsg = PkgLifeService.queryPkgLifeList(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
		return returnPage(res, returnMsg,"ca/cacore/MSSS/pkgLife/pkgLife_Query");
		
	}
	
	/**
	 * @param 传入Request ，Response
	 * @return 返回一个ModelAndView对象
	 * @description 查看明细
	 */
	@RequestMapping("/MSSS/PkgLife/viewPkgLife.do")
	public ModelAndView viewPkgLife(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		IPkgLifeVOModel model = new PkgLifeVOModel();
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		model.setSeq_id(seq_id);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg  =  PkgLifeService.getPkgLifeVO(model);//根据seq_id查询出该组合产品所有信息
		ReturnMsgHelper rmHelper =  new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/MSSS/pkgLife/pkgLife_View");
	}
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询单个组合产品信息
	 */
	@RequestMapping("/MSSS/PkgLife/goModifyPkgLifePage.do")
	public ModelAndView goModifyPkgLifePage(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		IPkgLifeVOModel model = new PkgLifeVOModel();
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
		model.setSeq_id(seq_id);
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg  =  PkgLifeService.getPkgLifeVO(model);//根据seq_id查询出该组合产品所有信息
		ReturnMsgHelper rmHelper =  new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/MSSS/pkgLife/pkgLife_Modify");
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 修改组合产品信息
	 */
	@RequestMapping("/MSSS/PkgLife/modifyPkgLife.do")
	public ModelAndView modifyPkgLife(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));//获取组合产品代码
		String pkg_id = ActionHelper.getNullToStr(req.getParameter("pkg_id"));// 组合产品代码
		String pkg_name = ActionHelper.getNullToStr(req.getParameter("pkg_name"));//组合产品名称
		String pkg_abbr = ActionHelper.getNullToStr(req.getParameter("pkg_abbr"));// 组合产品简称
		String pkg_enName = ActionHelper.getNullToStr(req.getParameter("pkg_enName"));//组合产品英文名称
		String pkg_enAbbr = ActionHelper.getNullToStr(req.getParameter("pkg_enAbbr"));//组合产品英文简称
		String start_date =  ActionHelper.getNullToStr(req.getParameter("start_date"));//起售时间
		String end_date =  ActionHelper.getNullToStr(req.getParameter("end_date"));//停售时间
		String remark =  ActionHelper.getNullToStr(req.getParameter("remark"));//备注
		String []  product_ids =  req.getParameterValues("product_id");//缴费方式代码
		String []  insBranch_ids =  req.getParameterValues("insBranch_id");//多个保险公司代码
		
		IPkgLifeVOModel model = new PkgLifeVOModel();
		
		model.setSeq_id(seq_id);
		model.setPkg_id(pkg_id);
		model.setPkg_name(pkg_name);
		model.setPkg_abbr(pkg_abbr);
		model.setPkg_enName(pkg_enName);
		model.setPkg_enAbbr(pkg_enAbbr);
		model.setStart_date(DateUtil.string2Date(start_date));
		model.setEnd_date(DateUtil.string2Date(end_date));
		model.setRemark(remark);
		model.setProduct_id(product_ids);
		model.setInsBranch_id(insBranch_ids);
		
		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = PkgLifeService.modifyPkgLife(model,user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper",rmHelper);
		//return new ModelAndView("ca/cacore/MSSS/pkgLife/pkgLife_Modify");

        res.getWriter().write("{\"successFlag\": "+returnMsg.isSuccessflag()+", \"message\": \""+returnMsg.getMsgStr()+"\"}");

        return null;
	}
	
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 修改组合产品信息
	 */
	@RequestMapping("/MSSS/PkgLife/addPkgLife.do")
	public ModelAndView addPkgLife(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String pkg_id = ActionHelper.getNullToStr(req.getParameter("pkg_id"));// 组合产品代码
		String pkg_name = ActionHelper.getNullToStr(req.getParameter("pkg_name"));//组合产品名称
		String pkg_abbr = ActionHelper.getNullToStr(req.getParameter("pkg_abbr"));// 组合产品简称
		String pkg_enName = ActionHelper.getNullToStr(req.getParameter("pkg_enName"));//组合产品英文名称
		String pkg_enAbbr = ActionHelper.getNullToStr(req.getParameter("pkg_enAbbr"));//组合产品英文简称
		String start_date =  ActionHelper.getNullToStr(req.getParameter("start_date"));//起售时间
		String end_date =  ActionHelper.getNullToStr(req.getParameter("end_date"));//停售时间
		String remark =  ActionHelper.getNullToStr(req.getParameter("remark"));//备注
		String []  product_ids =  req.getParameterValues("product_id");//多个产品代码
		String []  insBranch_ids =  req.getParameterValues("insBranch_id");//多个保险公司代码
		
		IPkgLifeVOModel model = new PkgLifeVOModel();
	
		model.setPkg_id(pkg_id);
		model.setPkg_name(pkg_name);
		model.setPkg_abbr(pkg_abbr);
		model.setPkg_enName(pkg_enName);
		model.setPkg_enAbbr(pkg_enAbbr);
		model.setStart_date(DateUtil.string2Date(start_date));
		model.setEnd_date(DateUtil.string2Date(end_date));
		model.setRemark(remark);
		model.setProduct_id(product_ids);
		model.setInsBranch_id(insBranch_ids);
		
		IUserModel user = ServletHelper.getSessionUser(req);//从session获取user信息
		model.setPageCount(ActionHelper.getPage(req));
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = PkgLifeService.addPkgLife(model,user);
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
			}
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		
		req.setAttribute("rmHelper",rmHelper);
		//return new ModelAndView("ca/cacore/MSSS/pkgLife/pkgLife_Add");

        res.getWriter().write("{\"successFlag\": "+returnMsg.isSuccessflag()+", \"message\": \""+returnMsg.getMsgStr()+"\"}");

        return null;
	}
	//判断产品组合代码是否重复
	@RequestMapping("/MSSS/PkgLife/checkPkgIdAndNameUnique.do")
	public void checkPkgIdAndNameUnique(HttpServletRequest req,HttpServletResponse res){
		String pkg_id = ActionHelper.getNullToStr(req.getParameter("pkg_id"));
		String pkg_name = ActionHelper.getNullToStr(req.getParameter("pkg_name"));
		String returnInfo="";
		returnInfo = returnInfo+PkgLifeService.checkPkgIdAndNameUnique(pkg_id, pkg_name);
		try {
			res.getWriter().print(returnInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//查询保险公司字典
	@RequestMapping("/MSSS/PkgLife/queryInsBranch.do")
	public void queryInsBranch(HttpServletRequest req, HttpServletResponse res) {
		String returnInfo = "";
		returnInfo = returnInfo + PkgLifeService.queryInsBranch();
		PrintWriter out = null;
		try {
			res.setContentType("text/html; charset=UTF-8");
			out=res.getWriter();
			out.print(returnInfo.toString());
		} catch (Exception e) {
			e.printStackTrace();
			out.print("error");
		} finally {
			if(out != null){
			out.flush();
			out.close();
			}
		}
	}

}
