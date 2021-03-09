package com.ca.cacore.maas.webapp.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.maas.dao.protocol.IProtocolDao;
import com.ca.cacore.maas.model.bo.IProtocolModel;
import com.ca.cacore.maas.model.bo.ProtocolModel;
import com.ca.cacore.maas.webapp.service.IProtocolService;
import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.BusinessException;
import com.newtouch.component.c11assistant.DateHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.excel.ExcelWrite;
/**
* @since:    2014年4月10日   
* @author    JYF
* @description:协议管理-controller层
*/
@Controller
public class ProtocolController extends BaseController{

	@Autowired IProtocolService protocolService;
	@Autowired private ICommonSeqDao seqDao;
	@Autowired private IProtocolDao protocolDao; 

	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:协议管理-增加
	*/
	@RequestMapping("/maas/Protocol/addProtocol.do")
	public ModelAndView AddProtocol(HttpServletRequest req,HttpServletResponse res) throws Exception{
		
		IProtocolModel model=this.getProtocolModel(req);
		ReturnMsg returnMsg=new ReturnMsg();
		ReturnMsgHelper rmHelper = new ReturnMsgHelper(req,returnMsg);
		//检查是否已有协议和时间
		List<ProtocolModel> list = protocolService.checkagreement(model);
		if (list.size() > 0) {
			returnMsg.setDataTable(TransHelper.obj2map(list.get(0)));
			returnMsg.setFailMessage("已有协议，不能新增");
			req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg));
			rmHelper.setReturnMsg(returnMsg);
			return new ModelAndView("ca/cacore/maas/protocol/protocolAdd");
		}
		//设置协议号
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date=new java.util.Date();
		String agreement_no = format1.format(date);
		agreement_no = agreement_no + model.getBranch_id() + String.format("%04d", protocolService.addProtocolView()+1);
		model.setAgreement_no(agreement_no);
		try {
			String seq_id=seqDao.queryCommonSeq("seq_id");
			model.setSeq_id(Integer.valueOf(seq_id));
			returnMsg=protocolService.addProtocol(model);
		} catch (BusinessException e) {
			returnMsg.setFailMessage(e.getMessage());
		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));//保存成功带回成功信息,并刷新界面
		return returnPage(res,returnMsg,"ca/cacore/maas/protocol/protocolAdd");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:协议管理-跳转页面-查询
	*/
	@RequestMapping("/maas/Protocol/goProtocolList.do")
	public ModelAndView goProtocolList(HttpServletRequest req,HttpServletResponse res){
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,new ReturnMsg(),new PageCount(),true));
		return new ModelAndView("ca/cacore/maas/protocol/protocolQuery");
	}
	/** 
	* 
	* @param req
	* @param res
	* @return
	* @throws Exception ModelAndView
	* @description:协议管理-查询页面
	*/
	@RequestMapping("/maas/Protocol/queryProtocolList.do")
	public ModelAndView queryProtocolList(HttpServletRequest req,HttpServletResponse res) throws Exception{
		IUserModel usermodel=ServletHelper.getSessionUser(req);
		IProtocolModel model=this.getProtocolModel(req);
		model.setEmp_id(usermodel.getEmp_id());
		model.setPageCount(ActionHelper.getPage(req));
		//调用查询的方法
		ReturnMsg returnMsg=new ReturnMsg();
		returnMsg=protocolService.queryProtocol(model);
		req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,model.getPageCount(),true));
		return returnPage(res,returnMsg,"ca/cacore/maas/protocol/protocolQuery");
	}
	/** 
	* 
	* @param request
	* @param response
	* @return
	* @throws Exception ModelAndView
	* @description:协议管理-维护-页面
	*/
	@RequestMapping("/maas/Protocol/toModifyProtocol.do")
	public ModelAndView getProtocolModifyView(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		IProtocolModel model=this.getProtocolModel(request);
		//调用查询的方法
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg=protocolService.getProtocolModifyView(model);
		ReturnMsgHelper rmHelper =  new ReturnMsgHelper(request, returnMsg);
		request.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/maas/protocol/protocolModify");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return
	* @throws Exception ModelAndView
	* @description:协议管理-跳转新增页面
	*/
	@RequestMapping("/maas/Protocol/addProtocolView.do")
	public ModelAndView addProtocolView(HttpServletRequest req,HttpServletResponse res) throws Exception{
		ReturnMsg returnMsg=new ReturnMsg();
		ReturnMsgHelper rmHelper =  new ReturnMsgHelper(req, returnMsg);
		req.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/maas/protocol/protocolAdd");
	}
	
	/** 
	* 
	* @param req
	* @param res
	* @return
	* @throws Exception ModelAndView
	* @description:协议管理-修改
	*/
	@RequestMapping("/maas/Protocol/modifyProtocol.do")
	public ModelAndView modifyProtocol(HttpServletRequest req,HttpServletResponse res) throws Exception{
		IProtocolModel model=this.getProtocolModel(req);
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			returnMsg = protocolService.modifyProtocol(model); //执行保存方法
		}catch (BusinessException e){
			returnMsg.setFailMessage(e.getMessage()); 
		}
		if(returnMsg.isSuccessflag()){
			returnMsg = protocolService.getProtocolModifyView(model);
			req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg).setReturnParams(returnMsg.getDataTable()));//保存成功带回成功信息,并刷新界面
			returnMsg.setSuccessMessage("修改成功");
		}else{
			req.setAttribute("rmHelper", new ReturnMsgHelper(req,returnMsg,true));//失败带回失败信息,不刷新界面
		}
		return returnPage(res, returnMsg, "ca/cacore/maas/protocol/protocolModify");
   }
	
	/** 
	* 
	* @param request
	* @param response
	* @return
	* @throws Exception ModelAndView
	* @description:协议管理-明细
	*/
	@RequestMapping("/maas/Protocol/goProtocolModifyView.do")
	public ModelAndView goProtocolModifyView(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		IProtocolModel model=this.getProtocolModel(request);
		//调用查询的方法
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg=protocolService.getProtocolModifyView(model);
		ReturnMsgHelper rmHelper =  new ReturnMsgHelper(request, returnMsg);
		request.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/maas/protocol/protocolView");
	}
	
	/** 
	* 
	* @param request
	* @param response
	* @return
	* @throws Exception ModelAndView
	* @description:协议管理-注销
	* by  sunhao
	*/
	@RequestMapping("/maas/Protocol/upProStatus.do")
	public ModelAndView upProStatus(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		IProtocolModel model=this.getProtocolModel(request);
		//调用查询的方法
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg=protocolService.updateProtocolStatus(model);
		ReturnMsgHelper rmHelper =  new ReturnMsgHelper(request, returnMsg);
		request.setAttribute("rmHelper", rmHelper.setReturnParams(returnMsg.getDataTable()));
		return new ModelAndView("ca/cacore/maas/protocol/protocolQuery");
	}
	
	/** 
	* 
	* @param request
	* @param response
	* @return
	* @throws Exception ModelAndView
	* @description:通过保险公司获取产品名称
	*/
	@RequestMapping("/maas/Protocol/getProductName.do")
	public ModelAndView getProductName(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		String branch_id = ActionHelper.getNullToStr(request.getParameter("branch_id"));
		IProtocolModel  model = new ProtocolModel();
		model.setBranch_id(branch_id);
		//调用查询的方法
		ReturnMsg returnMsg = new ReturnMsg();
		try {
			returnMsg=protocolService.getProductName(model);
		} catch (Exception e) {
			returnMsg.setFailMessage(e.getMessage()); 
		}
		request.setAttribute("returnHepler", new ReturnMsgHelper(request,returnMsg,true));
		return returnPage(response, returnMsg, "ca/cacore/util/addRiskAndProtocolTree");
	}
	
	/** 
	* 
	* @param request
	* @param response
	* @return
	* @throws Exception ModelAndView
	* @description:通过产品名称获取产品编号
	*/
	/*@RequestMapping("/maas/Protocol/getProductCode.do")
	public ModelAndView getProductCode(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		String product_name = ActionHelper.getNullToStr(request.getParameter("product_name"));
		IProtocolModel  model = new ProtocolModel();
		model.setProduct_name(product_name);
		//调用查询的方法
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg=protocolService.getProductCode(model);
		request.setAttribute("rmHelper", new ReturnMsgHelper(request, returnMsg));	                             
		return returnPage(response, returnMsg, "ca/cacore/maas/protocol/protocolAdd");
	}*/
	
	
	/** 
	* 
	* @param req
	* @param response
	* @return
	* @throws Exception ModelAndView
	* @description:协议管理-导出
	*/
	@RequestMapping("/maas/Protocol/exportProtocol.do")
	public ModelAndView exportClassResultDeal(HttpServletRequest req ,HttpServletResponse response) throws Exception {
		IUserModel usermodel=ServletHelper.getSessionUser(req);
		IProtocolModel model=this.getProtocolModel(req);
		model.setEmp_id(usermodel.getEmp_id());
		//获取报表数据
		List<IProtocolModel> list = protocolService.queryProtocolForExport(model);
		
		Map<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		// sheet
			List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
			// row 行数
			for (int j = 0; j < list.size(); j++) {
				Map<String, Object> hashMap = new HashMap<String, Object>();
				IProtocolModel m = list.get(j);
				// cell 每行中每列的值 注意列字不能少
					hashMap.put("列" + 0, ActionHelper.toStr(j+1)); //序号
					hashMap.put("列" + 1, m.getInsBranch_name()); //保险公司
					hashMap.put("列" + 2, m.getIns_branchname()); //签约机构
					hashMap.put("列" + 3, m.getContacts_name()); //联系人
					hashMap.put("列" + 4, m.getContacts_way()); //联系方式 
					hashMap.put("列" + 5, m.getAgreement_no());//协议号
					hashMap.put("列" + 6, m.getBranch_name());//使用机构
					hashMap.put("列" + 7, m.getStartdate());//生效日期
					hashMap.put("列" + 8, m.getEnddate()); //终止日期
					hashMap.put("列" + 9, m.getDateofsign()); //协议签订日期
					hashMap.put("列" + 10, m.getPersion_id()); //签订人代码
					hashMap.put("列" + 11, m.getPersion_name()); //签订人姓名
					hashMap.put("列" + 12, m.getStatus_name()); //状态

				sheetList.add(hashMap);
			}
			excelMap.put("协议管理报表", sheetList); 
		// 正常表头  注意列字不能少
		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
			head.put("列" + 0, "序号");
			head.put("列" + 1, "保险公司");
			head.put("列" + 2, "签约机构");
			head.put("列" + 3, "联系人 ");
			head.put("列" + 4, "联系方式 ");
			head.put("列" + 5, "协议号");
			head.put("列" + 6, "使用机构");
			head.put("列" + 7, "生效日期");
			head.put("列" + 8, "终止日期");
			head.put("列" + 9, "协议签订日期");
			head.put("列" + 10, "签订人代码");
			head.put("列" + 11, "签订人姓名");
			head.put("列" + 12, "状态");
			
		// 设置每个sheet页的表头
		List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
		sheetHead.add(head);
		// 设置多sheet页
		Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
		map.put("协议管理报表", sheetHead); //对应上面excelMap put的协议管理报表

		ExcelWrite write = new ExcelWrite();
		//设置response 这样就可以前台弹出框进行下载了
		response.setContentType("application/msexcel");
		response.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		write.setResponse(response);
		write.setSheetHead(map);
		write.setMergeCell(true);
		// 设置数据，key需要跟sheet页的key相同，值为需要写的数据
		write.writeExcel(excelMap);
		return null;
	}
	/** 
	* 
	* @param request
	* @param response
	* @return
	* @throws Exception ModelAndView
	* @description:协议管理-获取签订人信息(由工号查询姓名)
	*/
	@RequestMapping("/maas/protocol/getProtocolPersonInformation.do")
	public ModelAndView getProtocolPersonInfo(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		String persion_id = ActionHelper.getNullToStr(request.getParameter("persion_id"));
		String persion_name = ActionHelper.getNullToStr(request.getParameter("persion_name"));
		String sales_id = ActionHelper.getNullToStr(request.getParameter("sales_id"));
		String sales_name = ActionHelper.getNullToStr(request.getParameter("sales_name"));
		String code = ActionHelper.getNullToStr(request.getParameter("code"));
		String name = ActionHelper.getNullToStr(request.getParameter("name"));
		
		IProtocolModel model = new ProtocolModel();
		model.setPersion_id(persion_id);
		model.setPersion_name(persion_name);
		model.setSales_id(sales_id);
		model.setSales_name(sales_name);
		model.setCode(code);
		model.setName(name);
		
		IProtocolModel model2 = protocolService.getProtocolPersonInformation(model);
		String info = "";
		if( !code.equals("")&&model2 ==null){
			info = "{success:'false1'}";
		}else if( !code.equals("")&&model2.getSystemdate()!=null){
			info = "{success:'false2'}";//已经签过协议不能再签
		}else if(!code.equals("")){
		    info = "{success:'true',code:'"+ActionHelper.getNullToStr(model2.getCode())+"',name:'"+ActionHelper.getNullToStr(model2.getName())+"'}";
		}
		List<String> list=new ArrayList<String>();
		list.add(info);
		response.getWriter().print(JSONArray.fromObject(list).toString());
		return null;
	}
	/**
	 * 保险公司协议
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/maas/protocol/toCheckBranch.do")
	public ModelAndView checkBranch(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		String branch_id = ActionHelper.getNullToStr(request.getParameter("branch_id"));
		boolean b = protocolDao.checkBranch(branch_id);
		response.getWriter().print(JSONArray.fromObject(b).toString());
		return null;
	}
	/**
	 * 保险公司协议号唯一性校验
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/maas/protocol/toCheckAgreement_no.do")
	public ModelAndView checkAgreement_no(HttpServletRequest request ,HttpServletResponse response){
		String agreement_no =  ActionHelper.getNullToStr(request.getParameter("agreement_no"));
		String branch_id = ActionHelper.getNullToStr(request.getParameter("agreement_no")); 
		IProtocolModel model = new ProtocolModel();
		model.setAgreement_no(agreement_no);
		model.setBranch_id(branch_id);
		boolean b = protocolDao.checkAgreement_no(model);
		try {
			response.getWriter().print(JSONArray.fromObject(b).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/** 
	* 
	* @param req
	* @return IProtocolModel
	* @description:协议管理-入参，反参-用于复用
	*/
	public IProtocolModel getProtocolModel(HttpServletRequest req){
		String ins_branch = ActionHelper.getNullToStr(req.getParameter("ins_branch"));//保险公司编码
		String ins_branchname=ActionHelper.getNullToStr(req.getParameter("ins_branchname"));//保险公司名称
		String contacts_name=ActionHelper.getNullToStr(req.getParameter("contacts_name"));//联系人
		String contacts_way=ActionHelper.getNullToStr(req.getParameter("contacts_way"));//联系方式
		String agreement_no=ActionHelper.getNullToStr(req.getParameter("agreement_no"));//协议号
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));//使用机构ID
		String branch_name=ActionHelper.getNullToStr(req.getParameter("branch_name"));//使用机构名称
		String  startdate=ActionHelper.getNullToStr(req.getParameter("startdate"));//生效日期
		Date  startdate1=DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("startdate1")));//生效日期 至
		String  enddate =ActionHelper.getNullToStr(req.getParameter("enddate"));//终止日期
		Date  enddate1 =DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("enddate1")));//终止日期 至
		String  dateofsign=ActionHelper.getNullToStr(req.getParameter("dateofsign"));//协议签订日期
		String persion_id=ActionHelper.getNullToStr(req.getParameter("persion_id"));//签订人代码
		String persion_name=ActionHelper.getNullToStr(req.getParameter("persion_name"));//签订人姓名
		String sales_id=ActionHelper.getNullToStr(req.getParameter("sales_id"));//人员代码
		String sales_name=ActionHelper.getNullToStr(req.getParameter("sales_name"));//人员姓名
		String status=ActionHelper.getNullToStr(req.getParameter("status"));//状态
		String status_code=ActionHelper.getNullToStr(req.getParameter("status_code"));//状态编号
		String status_name=ActionHelper.getNullToStr(req.getParameter("status_name"));//状态名称
		String code=ActionHelper.getNullToStr(req.getParameter("code"));//编号
		String name=ActionHelper.getNullToStr(req.getParameter("name"));//名称
		String insBranch_id=ActionHelper.getNullToStr(req.getParameter("cpybranch_id"));//
		String insBranch_name=ActionHelper.getNullToStr(req.getParameter("cpybranch_name"));//
		
		
		IProtocolModel model=new ProtocolModel();
		model.setIns_branch(ins_branch);
		model.setIns_branchname(ins_branchname);
		model.setContacts_name(contacts_name);
		model.setContacts_way(contacts_way);
		model.setAgreement_no(agreement_no);
		model.setBranch_id(branch_id);
		model.setBranch_name(branch_name);
		model.setStartdate(startdate);
		model.setStartdate1(startdate1);
		model.setEnddate(enddate);
		model.setEnddate1(enddate1);
		model.setDateofsign(dateofsign);
		model.setPersion_id(persion_id);
		model.setPersion_name(persion_name);
		model.setSales_id(sales_id);
		model.setSales_name(sales_name);
		model.setStatus(status);
		model.setStatus_code(status_code);
		model.setStatus_name(status_name);
		model.setCode(code);
		model.setName(name);
		model.setInsBranch_id(insBranch_id);
		model.setInsBranch_name(insBranch_name);
		return model;
	}
}