package com.ca.cacore.msss.webapp.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.msss.model.bo.IInsRncDfnModel;
import com.ca.cacore.msss.model.bo.InsRncDfnModel;
import com.ca.cacore.msss.model.vo.IInsRncDfnVOModel;
import com.ca.cacore.msss.model.vo.InsRncDfnVOModel;
import com.ca.cacore.msss.webapp.service.IInsRncDfnService;
import com.ca.cacore.mass.domain.IInsBranchManageDomain;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.DateHelper;
import com.newtouch.component.c11assistant.ITree;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c1properties.StaticProperties;
import com.newtouch.component.c4fileDownload.FileDownLoadUtil;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.excel.ExcelUtil;
import com.newtouch.utils.excel.ExcelWrite;

/**
 * 
 * @author Wang_ds
 * @since 2013-11-10
 * @description Controller层
 */
@Controller
public class InsRncDfnController extends BaseController {

	@Autowired
	private IInsRncDfnService InsRncDfnService;
	@Autowired
	private IInsBranchManageDomain insBranchDomain;
	
	
	@RequestMapping("/msss/InsRncDfn/toQueryInsRncDfnList.do")
	public ModelAndView toQueryInsRncDfnList(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/msss/insRncDfn/insRncDfn_query");
	}
	/**
	 * @param 传入Request,Response
	 * @return 返回一个ModelAndView对象
	 * @description 查询险类信息，或根据条件查询
	 */
	@RequestMapping("/msss/InsRncDfn/queryInsRncDfnList.do")
	public ModelAndView queryInsRncDfnList(HttpServletRequest req,HttpServletResponse res) throws Exception {
		//获取其他参数
		IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息 by zdd02
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//保险公司编号
		String riskName=ActionHelper.getNullToStr(req.getParameter("riskName"));// by zdd02
		String riskCode=ActionHelper.getNullToStr(req.getParameter("riskCode"));// by zdd02
		IInsRncDfnModel model = new InsRncDfnModel();
		model.setPageCount(ActionHelper.getPage(req));
		model.setBranch_id(branch_id);
		model.setRiskName(riskName);
		model.setRiskCode(riskCode);
        model.setModifyUser(userModel.getEmp_id());
        model.setPageCount(ActionHelper.getPage(req));// 分页
		ReturnMsg returnMsg = InsRncDfnService.queryInsRncDfnList(model);
		System.out.println();
		req.setAttribute("rmHelper",new ReturnMsgHelper(req, returnMsg, model.getPageCount(), true));
		return returnPage(res, returnMsg,"ca/cacore/msss/insRncDfn/insRncDfn_query");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description: 新增其他保险公司产品
	*/
	@RequestMapping("/msss/InsRncDfn/addInsRncDfnList.do")
	public ModelAndView insRncDfnAdd(HttpServletRequest req,HttpServletResponse res){
		// getsysemlist(req,res);
		IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息 by zdd02 20190620
		String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));
		String branch_id1= ActionHelper.getNullToStr(req.getParameter("branch_id"));//保险公司编号
		String branch_id="";
		if(branch_id1.length()>0) {
			String[] split = branch_id1.split(",");
			branch_name=split[1];
			branch_id=split[0];
		}
		String org_level = ActionHelper.getNullToStr(req.getParameter("org_level"));//保险公司编号
		String classCode=ActionHelper.getNullToStr(req.getParameter("classCode"));//险类代码
		String className=ActionHelper.getNullToStr(req.getParameter("className"));//险种代码
		String product_code=ActionHelper.getNullToStr(req.getParameter("product_code"));//险种代码
		String product_name=ActionHelper.getNullToStr(req.getParameter("product_name"));//险种代码
		String riskName=ActionHelper.getNullToStr(req.getParameter("riskName"));//险种代码
		String riskCode=ActionHelper.getNullToStr(req.getParameter("riskCode"));//险种代码
		String bjtype=ActionHelper.getNullToStr(req.getParameter("bjtype"));
		String bjtypename=ActionHelper.getNullToStr(req.getParameter("bjtypename"));
		IInsRncDfnModel model=new InsRncDfnModel();
		model.setBranch_name(branch_name);//by zdd02
		model.setBranch_id(branch_id);
		model.setClassCode(classCode);
		model.setClassName(className);
		model.setProduct_code(product_code);
		model.setProduct_name(product_name);
		model.setRiskName(riskName);
		model.setRiskCode(riskCode);
		model.setModifyUser(userModel.getEmp_id());//by zdd02 20190620
		model.setBjtype(bjtype);
		model.setBjtypename(bjtypename);
		ReturnMsg returnMsg= new ReturnMsg();
		if (!"1".endsWith(org_level)) {
			returnMsg.setFailMessage("请选择总公司");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Modify");
		}
         /*if("".equals(classCode.trim())||classCode.trim()==null){//by zdd02 20190619 start
			//获取是p还是L  zdd0724
			 String isPORL=InsRncDfnService.isPORL(model);
			if("P".equals(isPORL)) {
			returnMsg.setFailMessage("险类编码不能为空！");
			returnMsg.setDataTable(TransHelper.obj2map(model));
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Add");
			}
		 }*/
          /*if("".equals(className.trim())||className.trim()==null){
			//获取是p还是L  zdd0724
			 String isPORL=InsRncDfnService.isPORL(model);
		  if("P".equals(isPORL)) {
			returnMsg.setFailMessage("险类名称不能为空！");
			returnMsg.setDataTable(TransHelper.obj2map(model));
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Add");
		  }
		}*/
		if("".equals(riskName.trim())||riskName.trim()==null){
			returnMsg.setFailMessage("险种名称不能为空！");
			returnMsg.setDataTable(TransHelper.obj2map(model));
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Add");
			
		}
          if("".equals(riskCode.trim())||riskCode.trim()==null){
			returnMsg.setFailMessage("险种编号不能为空！");
			returnMsg.setDataTable(TransHelper.obj2map(model));
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Add");
		}
         if("".equals(model.getBjtype())||model.getBjtypename()==null){
			returnMsg.setFailMessage("保监统计分类不能为空！");
			returnMsg.setDataTable(TransHelper.obj2map(model));
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Add");
		}
		if (!"1".endsWith(org_level)) {
			returnMsg.setFailMessage("请选择总公司"); 
			 returnMsg.setDataTable(TransHelper.obj2map(model));
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Add");
		}
		 String count = InsRncDfnService.selectIsOrNobranchname(model);
		 if("-1".equals(count)) {
			 returnMsg.setFailMessage(">>保险公司名称必须为总公司名称");
			 returnMsg.setDataTable(TransHelper.obj2map(model));
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Add");
		 }
		//校验是否存在
		IInsRncDfnModel dd=InsRncDfnService.selectCheckInsRncDfnModel(model);
		if(dd!=null) {
			returnMsg.setDataTable(TransHelper.obj2map(model));
			returnMsg.setFailMessage(">>该保险公司险种险别已存在！");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Add");
		}
		
		returnMsg = InsRncDfnService.insRncDfnAdd(model);
		returnMsg.setDataTable(TransHelper.obj2map(model));
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
		return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Add");
	}
	
		/** 
		* 
		* @param req
		* @param res
		* @return ModelAndView
		* @description:查询其他保险公司产品信息
		*/
		@RequestMapping("/msss/InsRncDfn/getInsRncDfn.do")
		public ModelAndView getInsRncDfn(HttpServletRequest req,HttpServletResponse res){
			String seq_id=ActionHelper.getNullToStr(req.getParameter("seq_id"));
			IInsRncDfnModel model=new InsRncDfnModel();
			model.setSeq_id(Integer.parseInt(seq_id));
			IInsRncDfnModel insRncDfnModel = InsRncDfnService.getInsRncDfn(model);
			ReturnMsg returnMsg = new ReturnMsg();
			returnMsg.setDataTable(TransHelper.obj2map(insRncDfnModel));
			ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
			rmHelper.setReturnParams(returnMsg.getDataTable());
			//getsysemlist(req,res);
			req.setAttribute("rmHelper",rmHelper);
			return new ModelAndView("ca/cacore/msss/insRncDfn/insRncDfn_Modify");
		}
		/** 
		* 
		* @param req
		* @param res
		* @return ModelAndView
		* @description:修改其他保险公司产品
		*/
		@RequestMapping("/msss/InsRncDfn/modifyInsRncDfn.do")
		public ModelAndView insRncDfnModify(HttpServletRequest req,HttpServletResponse res){
			String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//保险公司编号
			String org_level = ActionHelper.getNullToStr(req.getParameter("org_level"));//保险公司编号
			String classCode=ActionHelper.getNullToStr(req.getParameter("classCode"));//险类代码
			String className=ActionHelper.getNullToStr(req.getParameter("className"));//险种代码
			String product_code=ActionHelper.getNullToStr(req.getParameter("product_code"));//险种代码
			String product_name=ActionHelper.getNullToStr(req.getParameter("product_name"));//险种代码
			Integer seq_id = ActionHelper.getNullToInteger(req.getParameter("seq_id"));
			String riskName=ActionHelper.getNullToStr(req.getParameter("riskName"));//险种代码
			String riskCode=ActionHelper.getNullToStr(req.getParameter("riskCode"));//险种代码
			String branch_name=ActionHelper.getNullToStr(req.getParameter("branch_name"));//by zdd02 20190619
			String bjtypename=ActionHelper.getNullToStr(req.getParameter("bjtypename"));//zddxiu
			String bjtype=ActionHelper.getNullToStr(req.getParameter("bjtype"));//zddxiu
			IInsRncDfnModel model=new InsRncDfnModel();
			model.setSeq_id(seq_id);
			model.setBranch_id(branch_id);
			model.setClassCode(classCode);
			model.setClassName(className);
			model.setProduct_code(product_code);
			model.setProduct_name(product_name);
			model.setRiskName(riskName);
			model.setRiskCode(riskCode);
			model.setBranch_name(branch_name);//by zdd02 20190619
			model.setOrg_level(org_level);
			model.setBjtype(bjtype);
			model.setBjtypename(bjtypename);
			ReturnMsg returnMsg= new ReturnMsg();
			if (!"1".endsWith(org_level)) {
				returnMsg.setFailMessage("请选择总公司");
				req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
				return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Modify");
			}else if("".equals(classCode.trim())||classCode.trim()==null){//by zdd02 20190619 start
				returnMsg.setFailMessage("险类编码不能为空！");
			}else if("".equals(className.trim())||className.trim()==null){
				returnMsg.setFailMessage("险类名称不能为空！");
				
			}else if("".equals(riskName.trim())||riskName.trim()==null){
				returnMsg.setFailMessage("险种名称不能为空！");
				
			}else if("".equals(riskCode.trim())||riskCode.trim()==null){
				returnMsg.setFailMessage("险种编号不能为空！");
			}else {

				 //校验是否存在 
				 String count = InsRncDfnService.selectIsOrNobranchname(model);
    			 if("-1".equals(count)) {
    				 returnMsg.setFailMessage("保险公司名称必须为总公司名称");
    				// getsysemlist(req,res);
    				returnMsg.setDataTable(TransHelper.obj2map(model));
    				return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Modify");
    			 }
    			//校验是否存在
    			IInsRncDfnModel dd=InsRncDfnService.selectCheckInsRncDfnModel(model);
    				if(dd!=null) {
    					
    					returnMsg.setFailMessage(">>该保险公司险种险别已存在！");
    					//getsysemlist(req,res);
    					returnMsg.setDataTable(TransHelper.obj2map(model));
    					req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
    					return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Modify");
    				
    				}
				if(riskCode.trim().length()>4){
					returnMsg.setFailMessage("险种编号不能超过4位");
					// getsysemlist(req,res);
					returnMsg.setDataTable(TransHelper.obj2map(model));
					req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
					return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Modify");
				}
				returnMsg = InsRncDfnService.insRncDfnModify(model);

			}
			//getsysemlist(req,res);
			returnMsg.setDataTable(TransHelper.obj2map(model));
			//by zdd02 20190619 end
			
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg).setReturnParams(returnMsg.getDataTable()));
			return returnPage(res, returnMsg, "ca/cacore/msss/insRncDfn/insRncDfn_Modify");
		}
	/** 
	* 
	* @param req
	* @param res
	* @throws IOException void
	* @description: 校验险种代码是否存在
	*/
	@RequestMapping("/msss/InsRncDfn/checkRiskCode.do")
	public void checkRiskCode(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String riskCode=ActionHelper.getNullToStr(req.getParameter("riskCode")); //险种代码
		String product_source=ActionHelper.getNullToStr(req.getParameter("product_source")); //险种代码
		String returnInfo ="";
		if(!"".equals(riskCode) && !"".equals(product_source)){
			IInsRncDfnVOModel model=new InsRncDfnVOModel();
			model.setRiskCode(riskCode);
			model.setProduct_source(product_source);
			returnInfo = returnInfo+InsRncDfnService.checkRiskCode(model);
			try {
				res.getWriter().print(returnInfo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			res.getWriter().print("true");
		}
	}
	/** 
	* 
	* @param req
	* @param res
	* @throws IOException void
	* @description: 校验险种代码是否存在（修改产品信息时）
	*/
	@RequestMapping("/msss/InsRncDfn/checkRiskCodeUnique.do")
	public void checkRiskCodeUnique(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String riskCodeOrign=ActionHelper.getNullToStr(req.getParameter("riskCodeOrign"));//原来的险种代码 
		String riskCode=ActionHelper.getNullToStr(req.getParameter("riskCode")); //修改后的险种代码
		String returnInfo ="";
		if(!"".equals(riskCode)){
			IInsRncDfnVOModel model=new InsRncDfnVOModel();
			model.setRiskCodeOrign(riskCodeOrign);
			model.setRiskCode(riskCode);;
			returnInfo = returnInfo+InsRncDfnService.checkRiskCodeUnique(model);
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
	* @param res
	* @return ModelAndView
	* @description:查询其他保险公司产品明细
	* sunhao
	*/
	@RequestMapping("/msss/InsRncDfn/getInsRncDfnByID.do")
	public ModelAndView getInsRncDfnByID(HttpServletRequest req,HttpServletResponse res){
		String seq_id=ActionHelper.getNullToStr(req.getParameter("seq_id"));
		IInsRncDfnModel model=new InsRncDfnModel();
		model.setSeq_id(Integer.parseInt(seq_id));
		IInsRncDfnModel insRncDfnModel=InsRncDfnService.getInsRncDfn(model);
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setDataTable(TransHelper.obj2map(insRncDfnModel));
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		rmHelper.setReturnParams(returnMsg.getDataTable());
		req.setAttribute("rmHelper",rmHelper);
		return new ModelAndView("ca/cacore/msss/insRncDfn/insRncDfn_View");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:修改状态
	* sunhao
	*/
	@RequestMapping("/msss/InsRncDfn/updateStatus.do")
	public ModelAndView updateStatus(HttpServletRequest req,HttpServletResponse res) throws Exception{
		String seq_id=ActionHelper.getNullToStr(req.getParameter("seq_id"));
		String status=ActionHelper.getNullToStr(req.getParameter("status"));//zddxiu
		IInsRncDfnModel model=new InsRncDfnModel();
		model.setSeq_id(Integer.parseInt(seq_id));
		model.setStatus(status);
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			returnMsg=InsRncDfnService.updateStatus(model);
		} catch (Exception e) {
			returnMsg.setFailMessage(e.getMessage()); 
		}
		req.setAttribute("returnHepler", new ReturnMsgHelper(req,returnMsg));
		/*return queryInsRncDfnList(req,res);*/
		return toQueryInsRncDfnList(req,res);
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:查询机构树
	* sunhao
	*/
	@RequestMapping("/msss/InsRncDfn/queryCompanyTree.do")
	public ModelAndView queryCompanyTree(HttpServletRequest req,HttpServletResponse res) throws Exception{
		ReturnMsg returnMsg = InsRncDfnService.queryCompanyTree();
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/util/riskAndProtocolTree");
	}
	
	/**
	 * 根据保险公司查询险类 by 孙豪
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/msss/InsRncDfn/queryclassCode.do")
	public ModelAndView queryclassCode(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));// 父节点
		IInsRncDfnModel model=new InsRncDfnModel();
		model.setBranch_id(branch_id);
		ReturnMsg returnMsg = InsRncDfnService.queryclassCode(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchModify");
	}
	

	/**
	 * 根据保险公司查询险种 by 孙豪
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/msss/InsRncDfn/queryriskCode.do")
	public ModelAndView queryriskCode(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String classCode = ActionHelper.getNullToStr(req.getParameter("classCode"));// 父节点
		IInsRncDfnModel model=new InsRncDfnModel();
		model.setClassCode(classCode);
		ReturnMsg returnMsg = InsRncDfnService.queryriskCode(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchModify");
	}
	
	/**
	 * 根据保险公司查询险别 by 孙豪
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/msss/InsRncDfn/querytype_code.do")
	public ModelAndView querytype_code(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String riskCode = ActionHelper.getNullToStr(req.getParameter("riskCode"));// 父节点
		IInsRncDfnModel model=new InsRncDfnModel();
		model.setRiskCode(riskCode);
		ReturnMsg returnMsg = InsRncDfnService.querytype_code(model);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg));
		return returnPage(res, returnMsg, "ca/cacore/manage/branch/branchModify");
	}
	
	/**
	 * 导出机构查询信息
	 * by zdd02 20190617
	 */
	@RequestMapping("/msss/InsRncDfn/exportInfo.do")
	public ModelAndView exportBranchInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel user = ServletHelper.getSessionUser(req);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//保险公司编号
		String riskName=ActionHelper.getNullToStr(req.getParameter("riskName"));// by zdd02
		String riskCode=ActionHelper.getNullToStr(req.getParameter("riskCode"));// by zdd02
		IInsRncDfnModel model = new InsRncDfnModel();
		model.setModifyUser(user.getEmp_id());
		model.setBranch_id(branch_id);
		model.setRiskCode(riskCode);
		model.setRiskName(riskName);
		// 获取报表数据
		List<IInsRncDfnModel> list = InsRncDfnService.exportInfo(model);

		Map<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		// sheet
		List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
		// row 行数
		for (int j = 0; j < list.size(); j++) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			String a = j + 1 + "";
			IInsRncDfnModel vo = list.get(j);
			// cell 每行中每列的值 注意列字不能少
			hashMap.put("列" + 0, a); // 序号
			hashMap.put("列" + 1, vo.getBranch_name()); // 
			hashMap.put("列" + 2, vo.getClassName());
			hashMap.put("列" + 3, vo.getRiskName());// 
			hashMap.put("列" + 4, vo.getRiskCode());//
			hashMap.put("列" + 5, vo.getParname());//
			hashMap.put("列" + 6, vo.getBjtypename());//
			sheetList.add(hashMap);
		}
		excelMap.put("保险公司产品信息", sheetList);
		// 正常表头 注意列字不能少
		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		head.put("列" + 0, "序号");
		head.put("列" + 1, "保险公司名称");
		head.put("列" + 2, "险类名称");
		head.put("列" + 3, "险种名称");
		head.put("列" + 4, "险种编号");
	    head.put("列" + 5, "产品所属保险公司");
		head.put("列" + 6, "保监统计分类");
		// 设置每个sheet页的表头
		List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
		sheetHead.add(head);
		// 设置多sheet页
		Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
		map.put("保险公司产品信息", sheetHead); // 对应上面excelMap put的销售人员基本信息报表

		ExcelWrite write = new ExcelWrite();
		// 设置response 这样就可以前台弹出框进行下载了
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		write.setResponse(res);
		write.setSheetHead(map);
		write.setMergeCell(true);
		// write.addNotLockArea("测试1", "A3:F8");
		// 设置数据，key需要跟sheet页的key相同，值为需要写的数据
		write.writeExcel(excelMap);
		
		return null;
	}
	
	
	/**
	 * zdd 20190609
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	 @RequestMapping("/msss/InsRncDfn/downloadTemplate.do")
	    public void downloadTemplate(HttpServletRequest req, HttpServletResponse resp) throws Exception {

	        String file_name = ActionHelper.getNullToStr(req.getParameter("file_name")); //附件id
	       
	        /*
	         * 使用虚拟目录下载方法备份
	         * */
	          String downloadPath = StaticProperties.getProperty("downloadPath"); //模板保存路径
	          File file = new File(downloadPath+file_name);  //拼接

			String fname=file_name.substring(0,file_name.lastIndexOf("."));//获取文件名前缀
			FileDownLoadUtil.downloadZipNew(file,file_name,resp);//调用下载原格式的方法
	    }
	 
	// 导入功能
	 @RequestMapping("/msss/InsRncDfn/importuploadbranch.do")
 	public void importuploadbranch(HttpServletRequest req, HttpServletResponse res, @RequestParam("file") CommonsMultipartFile mFile)
 			throws Exception {
		     res.setContentType("text/json;charset=UTF-8");
		    
	    	
			
			MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
			Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
			IUserModel user = ServletHelper.getSessionUser(req);
			String info = "";
			 List<String> listsmsgInfo = new ArrayList<>();
			 IUserModel userModel = ServletHelper.getSessionUser(req);// 从session获取user信息
		    
			try {
				for (MultipartFile file : files.values()) {
					System.out.println(file.getOriginalFilename());
					//info = cmainPolicyService.importInsurace2(file, user);
					
			    	ExcelUtil excel = new ExcelUtil();
			    	String[] titles = new String[] { "branch_id",
			    	 				"classCode",
			    	 				"classname",
			    	 				"riskname",
			    	 				"riskcode",
			    	 				"bjtypename"};
			    	ExcelUtil util = new ExcelUtil();
			    	Map<String, List<Object>> excelMap = util.initSheet4Stream(file.getInputStream(), new Object(), titles);
			    	List<IInsRncDfnModel> lists = new ArrayList<IInsRncDfnModel>();
			        Map<String,Object> map = null;
			        int i=0;
			    	for (String key : excelMap.keySet()) {
			    	
			    	Iterator<Object> mapit = excelMap.get(key).iterator();
			    	
			    	while(mapit.hasNext()){
			    		int j=0;
			    	map=(Map<String, Object>)mapit.next();
				    	if(map.size()>1) {
				    		IInsRncDfnModel model = new InsRncDfnModel();
				    		model.setModifyUser(userModel.getEmp_id());//zddxiu
				    		for (Map.Entry<String, Object> entry : map.entrySet()) {
				    			if("branch_id".equals(entry.getKey())) {
				    				String regex = "^[a-z0-9A-Z]+$";
									if(Pattern.matches(regex,String.valueOf(entry.getValue()))) {
				    				  model.setBranch_id(String.valueOf(entry.getValue()));
									  j=1;
									}
				    			}
				    			if("classCode".equals(entry.getKey())) {
				    				String regex = "^[a-z0-9A-Z]+$";
									if(!("".equals(String.valueOf(entry.getValue()))||String.valueOf(entry.getValue())==null)&&Pattern.matches(regex,String.valueOf(entry.getValue()))) {
				    				  model.setClassCode(String.valueOf(entry.getValue()));
									  j=1;
									}
				    			}
				    			if("classname".equals(entry.getKey())) {
				    				if(!("".equals(String.valueOf(entry.getValue()))||String.valueOf(entry.getValue())==null)) {
				    				model.setClassName(String.valueOf(entry.getValue()));
				    				 j=1;
				    				}
				    			    
				    			}
				    			if("riskname".equals(entry.getKey())) {
				    				if(!("".equals(String.valueOf(entry.getValue()))||String.valueOf(entry.getValue())==null)) {
				    				model.setRiskName(String.valueOf(entry.getValue()));
				    				j=1;
				    				}
				    			}
				    			if("riskcode".equals(entry.getKey())) {
				    				String regex = "^[a-z0-9A-Z]+$";
										if(Pattern.matches(regex,String.valueOf(entry.getValue()))) {
					    				model.setRiskCode(String.valueOf(entry.getValue()));
									}
				    			}
				    			if("bjtypename".equals(entry.getKey())) {
				    				model.setBjtypename(String.valueOf(entry.getValue()));	 
				    			    
				    			}
				    					
				    	         System.out.println("key= " + entry.getKey() + " and value= "+ entry.getValue());
				    		 }
				    		 if(j!=0) {
				    		 /* if(("".equals(model.getRiskCode())||model.getRiskCode()==null)&&
				    					 ("".equals(model.getRiskName())||model.getRiskName()==null)&&("".equals(model.getClassCode())||model.getClassCode()==null)&&
				    					 ("".equals(model.getClassName())||model.getClassName()==null)) {
				    				 continue;
				    			 }*/
				    		  if("".equals(model.getBranch_id())||model.getBranch_id()==null) {
									listsmsgInfo.add(">>保险公司编码不能为空/格式不正确！");
									continue;
							   }else {
					    			//权限校验
								     String ms=InsRncDfnService.checkLimitsBranchid(model);
									if(!"1".equals(ms)) {
										listsmsgInfo.add(ms);
										continue;
							    	}
								} 
				    			
				    			 if("".equals(model.getRiskCode())||model.getRiskCode()==null) {
				    				 listsmsgInfo.add(">>险种编号不能为空/格式不正确！");
										continue;
				    			 }/*else {
				    				//校验长度
					    			 if(model.getRiskCode().length()>4) {
					    				 listsmsgInfo.add(">>险种长度不得大于4！");
					    				 continue;
					    			 }
				    			 }*/
				    			 if("".equals(model.getRiskName())||model.getRiskName()==null) {
				    				 listsmsgInfo.add(">>险种名称不能为空");
										continue;
				    			 }
				    			 if("".equals(model.getBranch_id())||model.getBranch_id()==null) {
											listsmsgInfo.add(">>保险公司编码不能为空/格式不正确！");
											continue;
								  }
				    				 /*if("".equals(model.getClassCode())||model.getClassCode()==null) {
						    				//获取是p还是L  zdd0724
							    			  String isPORL=InsRncDfnService.isPORL(model);
							    			  if(!"L".equals(isPORL)) {
							    				 listsmsgInfo.add(">>险类编码不能为空/格式不正确！");
													continue;
							    			  }
							    			  
						    			 }else {*/
						    				 if(model.getClassCode()!=null && model.getClassCode().length()>4) {
							    				 listsmsgInfo.add(">>险类长度不得大于4！");
							    				 continue; 
							    			 }
						    				 /*}
				    				 if("".equals(model.getClassName())||model.getClassName()==null) {
						    				//获取是p还是L  zdd0724
						    				 String isPORL=InsRncDfnService.isPORL(model);
							    			  if(!"L".equals(isPORL)) {
							    				 listsmsgInfo.add(">>险类名称不能为空");
													continue;
							    			  }
						    			 }*/
						    			 if("".equals(model.getBjtypename())||model.getBjtypename()==null) {
						    				 listsmsgInfo.add(">>保监统计分类不能为空");
												continue;
						    			 }
				    			
				    			 
				    			 String count = InsRncDfnService.selectIsOrNobranchname(model);
				    			 if("-1".equals(count)) {
				    				 listsmsgInfo.add(">>保险公司名称必须为总公司名称");
				    				 continue;
				    			 }else if("0".equals(count)){ //验证保监统计分类是否正确
				    				 listsmsgInfo.add(">>保监统计分类与险别编码不匹配");
				    				 continue;
				    			 }
				    			 
				    			
				    			 //校验是否存在
				    			 IInsRncDfnModel dd=InsRncDfnService.selectCheckInsRncDfnModel(model);
				    			 if(dd!=null) {
				    				 /*if((model.getClassName()!=null && !"".equals(model.getClassName())) || (model.getClassCode()!=null && !"".equals(model.getClassCode()))){
				    					 model.setFlag("1");
				    				 }else{*/
				    					 listsmsgInfo.add(">>该保险公司险种险别已存在！");
				    					 continue;
				    				 /*}*/
				    			 }
				    			 lists.add(model);
				    			
			    		 }
				    	}
			    	}
			    	
			    
				}
			    	if(listsmsgInfo.size()<=0) {
			    		if(lists.size()>0) {
						for (IInsRncDfnModel model : lists) {
							/*if("1".equals(model.getFlag())){
								InsRncDfnService.insRncDfnUpdate(model);
							}else{*/
								InsRncDfnService.insRncDfnAddzdd(model);
							/*}*/
						}
		    			 listsmsgInfo.add("数据导入成功！");
			    	   }else {
			    		   listsmsgInfo.add("请填写正确数据！");
			    	   }
					}
			  }
				
				//req.setAttribute("listsmsgInfo", listsmsgInfo);
				res.getWriter().write("{\"ret\": \"0000\",\"uploadMsg\": \""+listsmsgInfo+"\"}");
			}catch (IOException e) {
				res.getWriter().write("{\"ret\": \"-0001\",\"uploadMsg\":\"导入失败\"}");
			}
		
 	}
		/**
		 * zdd 20190606
		 * @param req
		 * @param res
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/msss/InsRncDfn/importRedirect.do")
		public ModelAndView importRedirect(HttpServletRequest req
	            ,HttpServletResponse res) throws Exception {
			String linkUrl = ActionHelper.getNullToStr(req.getParameter("linkUrl"));
			return new ModelAndView(linkUrl);
		}
		
		@RequestMapping("/msss/InsRncDfn/getBaoxianType.do")
		public ModelAndView getsysemlist(HttpServletRequest req
	            ,HttpServletResponse res) {
			String branch_id = req.getParameter("branch_id");
			List<IInsRncDfnModel> models = InsRncDfnService.getsysemlist(branch_id);
			  List<Map<String,Object>> listMap2 = new ArrayList<>();
			  for (IInsRncDfnModel insRncDfnModel : models) {
				  Map<String,Object> conMap = new HashMap<>();
				  conMap.put("bjtype", insRncDfnModel.getBjtype());
				  conMap.put("bjtypename", insRncDfnModel.getBjtypename());
				  listMap2.add(conMap);
			 }
			req.setAttribute("models", models);
			ReturnMsg returnMsg = new ReturnMsg();
			returnMsg.setDataList(listMap2);
			String json = BaseController.return2Json(returnMsg);
			try {
				
				res.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping("/redirect/redirect2.do")
		public ModelAndView redirect(HttpServletRequest req
				                     ,HttpServletResponse res) throws Exception {
			//getsysemlist(req,res);
			String linkUrl = ActionHelper.getNullToStr(req.getParameter("linkUrl"));
			String branch_name = ActionHelper.getNullToStr(req.getParameter("branch_name"));
			String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));
			req.setAttribute("branch_name", branch_name);
			req.setAttribute("branch_id", branch_id);
			return new ModelAndView(linkUrl);
		}
		@RequestMapping("/msss/InsRncDfn/getBaoxianCP.do")
		public ModelAndView getBaoxianCP(HttpServletRequest req,
				HttpServletResponse res){
			List<ITree> list = insBranchDomain.querySalesFirmBranchTree2();
			List<Map<String,Object>> listMap = new ArrayList<>();
		/*	List<IInsRncDfnModel> InsRncDfnModels = InsRncDfnService.getsysemlist(String id);
			AND se.enum_code LIKE  (SELECT CASE WHEN ins_type='L' THEN '1%' ELSE '0%'  END n  FROM cpy_branch b WHERE b.branch_id='P2028')*/
			//转换为json
			
			for (ITree iTree : list) {
				System.out.println(iTree.getId()+">>"+iTree.getName());
				  Map<String, Object> map = new HashMap<>();
				  map.put("id", iTree.getId());
				  map.put("name", iTree.getName());
				  
				listMap.add(map);
			}
			ReturnMsg returnMsg = new ReturnMsg();
			returnMsg.setDataList(listMap);
			String json = BaseController.return2Json(returnMsg);
			try {
				
				res.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
}
