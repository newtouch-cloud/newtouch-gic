package com.ca.cacore.mass.webapp.controller;

import com.ca.cacore.manage.model.bo.BranchTreeModel;
import com.ca.cacore.manage.model.bo.IBranchModel;
import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.manage.webapp.service.IBranchService;
import com.ca.cacore.mass.model.bo.CompanyBranchModel;
import com.ca.cacore.mass.model.bo.ICompanyBranchModel;
import com.ca.cacore.mass.webapp.service.IInsBranchManageService;
import com.itextpdf.text.DocumentException;
import com.newtouch.component.c11assistant.*;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class InsBranchController extends BaseController {
	@Autowired
	private IInsBranchManageService insBranchService;
	@Autowired
	private IBranchService branchService;
	// 重定向到保险公司查询页面
	@RequestMapping("/mass/InsBranchManage/toInsBranchQuery.do")
	public ModelAndView toQueryBranchList(HttpServletRequest req, HttpServletResponse res) {
		checkpower0626(req,res);//zddxiu
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
		return new ModelAndView("ca/cacore/mass/insBranchManage/insBranchQuery");
	}

	// 保险公司信息列表查询
	@RequestMapping("/mass/InsBranchManage/insBranchQuery.do")
	public ModelAndView insBranchQuery(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException, DocumentException, IOException {
		//checkpower0626(req,res);//zddxiu
		ICompanyBranchModel model = new CompanyBranchModel();
		IUserModel user = ServletHelper.getSessionUser(req);
		ICompanyBranchModel companyModel = (ICompanyBranchModel) ModelHelper.getModel(model, req);
		String org_level=StringUtils.trim(req.getParameter("org_level"));
		companyModel.setBranch_level(org_level);
		companyModel.setModifyUser(user.getEmp_id());
		ReturnMsg returnMsg = insBranchService.insBranchQuery(companyModel);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg, companyModel.getPageCount(), true);
		req.setAttribute("rmHelper", rmHelper);
		return returnPage(res, returnMsg, "ca/cacore/mass/insBranchManage/insBranchQuery");
	}

	// 跳转新增页面
	@RequestMapping("/mass/InsBranchManage/toBranchAdd.do")
	public ModelAndView toBranchView(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		
		int branch_level =Integer.valueOf(req.getParameter("branch_level"));// by zdd02 20190620
		ICompanyBranchModel model = new CompanyBranchModel();
		model = (ICompanyBranchModel) ModelHelper.getModel(model, req);
		ReturnMsg returnMsg =new ReturnMsg();//zddxiu
		if(branch_level!=0) {
			model.setCpy_branch_level((Integer.toString(branch_level += 1)));
			returnMsg=insBranchService.getInsBranch(model);
		String branch_parentid = ActionHelper.getNullToStr(returnMsg.getDataTable().get("branch_no"));
		String branch_parentname = ActionHelper.getNullToStr(returnMsg.getDataTable().get("branch_cpyname"));
		/*int branch_level = Integer.valueOf(ActionHelper.getNullToStr(returnMsg.getDataTable().get("branch_level")));*/
		model.setBranch_parentid(branch_parentid);
		model.setBranch_parentname(branch_parentname);
		model.setBranch_id(model.getBleng_branchid());
		String branch_name = ActionHelper.getNullToStr(returnMsg.getDataTable().get("branch_name"));
		model.setBleng_branchname(branch_name);
		model.setBranch_name(branch_name);
		String province_code = ActionHelper.getNullToStr(returnMsg.getDataTable().get("province_code"));
		String city_code = ActionHelper.getNullToStr(returnMsg.getDataTable().get("city_code"));
		String area_code = ActionHelper.getNullToStr(returnMsg.getDataTable().get("area_code"));
		if(branch_level==2){
			model.setProvince_code(province_code);
		}
		if(branch_level==3){
			model.setProvince_code(province_code);
			model.setCity_code(city_code);
		}
		if(branch_level>=4){
			model.setProvince_code(province_code);
			model.setCity_code(city_code);
			model.setArea(area_code);
		}
		}else{
			model.setCpy_branch_level((Integer.toString(branch_level += 1)));
		}
		returnMsg.setDataTable(TransHelper.obj2map(model));
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		rmHelper.getReturnParam("branch_parentid");
		getAddsort(req,res,String.valueOf(rmHelper.getReturnParam("branch_parentid")));//zddxiu
		req.setAttribute("rmHelper", rmHelper);
		req.setAttribute("modify_flag", "2");
		return new ModelAndView("ca/cacore/mass/insBranchManage/insBranchAdd");
	}

	// 新增保险公司
	@RequestMapping("/mass/InsBranchManage/insBranchAdd.do")
	public ModelAndView insBranchAdd(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		IUserModel user = ServletHelper.getSessionUser(req);
		ICompanyBranchModel model = new CompanyBranchModel();
		model = (ICompanyBranchModel) ModelHelper.getModel(model, req);

		if(model.getBleng_branchid()==null||"".equals(model.getBleng_branchid())) {
		   model.setBleng_branchid(model.getBranch_id());
		}
		//获取当前账号可操作的branchId
		//model.getBleng_branchid()
		String userName = user.getUserName();
		//更据用户名查询最大的branch_id
		List<String> idList=insBranchService.queryIdbyUserName(userName);
		model.setBleng_branchid(idList.get(0));
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			//zdd0724
			//获取比对
			List<String> lists = insBranchService.getIsPOrLZ();
			int i=0;
			for (String string : lists) {
				if(string.equals(model.getBranch_sort())) {
					i=1;
				}
			}
			if(i==1) {
				model.setBranch_sortname("P");
			}else {
				model.setBranch_sortname("L");
			}
			returnMsg = insBranchService.insBranchAdd(model, user);
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
			returnMsg.setDataTable(TransHelper.obj2map(model));
			ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
			rmHelper.setReturnParams(returnMsg.getDataTable());
			getAddsort(req,res,String.valueOf(rmHelper.getReturnParam("branch_parentid")));//zddxiu
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			req.setAttribute("modify_flag", "2");
			return returnPage(res, returnMsg, "ca/cacore/mass/insBranchManage/insBranchAdd");
		}
		returnMsg.setDataTable(TransHelper.obj2map(model));
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		getAddsort(req,res,String.valueOf(rmHelper.getReturnParam("branch_parentid")));//zddxiu
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		req.setAttribute("modify_flag", "2");
		return returnPage(res, returnMsg, "ca/cacore/mass/insBranchManage/insBranchAdd2");
	}

	// 查询保险公司信息
	@RequestMapping("/mass/InsBranchManage/getInsBranch.do")
	public ModelAndView getInsBranch(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		ICompanyBranchModel model = new CompanyBranchModel();
		ICompanyBranchModel companyModel = (ICompanyBranchModel) ModelHelper.getModel(model, req);
	
		ReturnMsg returnMsg = insBranchService.getInsBranch1(companyModel);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/mass/insBranchManage/insBranchModify");
	}

	// 修改保险公司信息
	@RequestMapping("/mass/InsBranchManage/insBranchModify.do")
	public ModelAndView insBranchModify(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		IUserModel user = ServletHelper.getSessionUser(req);
		ICompanyBranchModel model = new CompanyBranchModel();
		model = (ICompanyBranchModel) ModelHelper.getModel(model, req);

		/*String userName = user.getUserName();
		//更据用户名查询最大的branch_id
		List<String> idList=insBranchService.queryIdbyUserName(userName);
		model.setBleng_branchid(idList.get(0));*/
		ReturnMsg returnMsg = new ReturnMsg();

		/*if(model.getBranch_parentid()==null||"".equals(model.getBranch_parentid())&&!"1".equals(model.getBranch_level())) {
			returnMsg.setFailMessage("上级保险公司代码不能为空！");
		}else if(model.getBranch_parentname()==null||"".equals(model.getBranch_parentname())&&!"1".equals(model.getBranch_level())) {
			returnMsg.setFailMessage("上级保险公司名称不能为空！");
		}else if(model.getBranch_level()==null||"".equals(model.getBranch_level())) {
			returnMsg.setFailMessage("本公司等级不能为空！");
		}else*/ if(model.getBranch_no()==null||"".equals(model.getBranch_no())) {
			returnMsg.setFailMessage("保险公司代码不能为空！");
		}else if(model.getBranch_cpyname()==null||"".equals(model.getBranch_cpyname())) {
			returnMsg.setFailMessage("保险公司名称不能为空！");
		}else if(model.getStatus()==null||"".equals(model.getStatus())) {
			returnMsg.setFailMessage("状态 不能为空！");
		/*}else if(model.getFound_date()==null) {
			returnMsg.setFailMessage("成立日期不能为空！");*/
		}else {
			returnMsg = insBranchService.insBranchModify(model, user);
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			return returnPage(res, returnMsg, "ca/cacore/mass/insBranchManage/insBranchModify");
		}
		returnMsg.setDataTable(TransHelper.obj2map(model));
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		return returnPage(res, returnMsg, "ca/cacore/mass/insBranchManage/insBranchModify");
	}

	// 保险公司明细查询
	@RequestMapping("/mass/InsBranchManage/insBranchView.do")
	public ModelAndView insBranchView(HttpServletRequest req, HttpServletResponse res) {
		Integer seq_id = Integer.valueOf(ActionHelper.getNullToStr(req.getParameter("seq_id")));
		ICompanyBranchModel model = new CompanyBranchModel();
		model.setSeq_id(seq_id);
		ReturnMsg returnMsg = insBranchService.getInsBranch(model);
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req, returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper", rmHelper);
		return new ModelAndView("ca/cacore/mass/insBranchManage/insBranchView");
	}

	// 检查保险公司名称是否已经存在
	@RequestMapping("/mass/InsBranchManage/checkInsbName.do")
	public ModelAndView getInfoByInsId(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer seq_id = Integer.valueOf(ActionHelper.getNullToStr(req.getParameter("seq_id")));
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));
		String returnInfo = "";
		if (!"".equals(branch_name)) {
			ICompanyBranchModel model = new CompanyBranchModel();
			model.setSeq_id(seq_id);
			model.setBranch_name(branch_name);
			returnInfo = returnInfo + insBranchService.checkInsbName(model);
			try {
				res.getWriter().print(returnInfo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 查询保险公司机构树
	@RequestMapping("/mass/queryCpyBranchTree.do")
	public ModelAndView querySalesFirmBranchTree(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String,Object> param=new HashMap<String,Object>();
		String org_level= StringUtils.trim(req.getParameter("org_level"));//父机构级别
		String org_id= StringUtils.trim(req.getParameter("org_id"));//父机构代码
		param.put("parentorg_level",org_level);
		param.put("parentorg_id",org_id);
		ReturnMsg returnMsg = insBranchService.queryBranchTree(param);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/util/cpyBranchTree");
	}
	public void checkpower0626(HttpServletRequest req, HttpServletResponse res) {
		IUserModel user = ServletHelper.getSessionUser(req);
		int cn =insBranchService.checkpower0626(user.getEmp_id());
		HttpSession session = req.getSession();
		session.setAttribute("cn", cn);
	}
	//获取总公司
	@RequestMapping("/mass/queryCpyBranchTree2.do")
	public ModelAndView querySalesFirmBranchTree2(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ReturnMsg returnMsg = insBranchService.querySalesFirmBranchTree2();
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
	
		return returnPage(res, returnMsg, "ca/cacore/util/cpyBranchTree");	
	}
	public void getAddsort(HttpServletRequest req, HttpServletResponse res,String brand_id) {
		List<ICompanyBranchModel> models =insBranchService.getbranchSort();
		//String sort=insBranchService.getfirstLeavelsort(brand_id);
		req.setAttribute("models", models);
		//req.setAttribute("sort", sort);
	}
	
	
	@RequestMapping("/mass/querySalesFirmBranchTreeZ.do")
	public ModelAndView querySalesFirmBranchTreeZ(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel userModel = ServletHelper.getSessionUser(req);
		IBranchModel model = new BranchTreeModel();
		model.setEmp_id(userModel.getEmp_id());
		model.setBranch_list(userModel.getDept_list());// 数据权限集合
		String org_level = StringUtils.trim(req.getParameter("org_level"));// 父机构级别
		String branchid = StringUtils.trim(req.getParameter("branchid"));// 父机构代码
		String path = StringUtils.trim(req.getParameter("path"));
		model.setBranch_id(branchid);
		model.setBranch_level(org_level);
				
		ReturnMsg returnMsg = new ReturnMsg();
		if("1".equals(path)) {
			path="ca/cacore/util/NewBranchTreeZ";
			returnMsg = insBranchService.queryBranchTreeZ(model);

		}else if("2".equals(path)) {
			path="ca/cacore/util/NewBranchTreeZ";
			 returnMsg = branchService.queryBranchTree(model);
		}else if("3".equals(path)) {
			path="ca/cacore/util/NewBranchTreeZ2";
			returnMsg = insBranchService.queryBranchTreeZ(model);
		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg,path);
	}
}
