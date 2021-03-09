package com.newtouch.report.webapp.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WriteException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.report.model.vo.Sysbranchcommission;
import com.newtouch.report.model.vo.Sysbranchcommissionimpl;
import com.newtouch.report.utils.ExcelUtils;
import com.newtouch.report.webapp.service.PersonalService;

@Controller
public class PersonalController extends BaseController {
	
	@Autowired
	private PersonalService personalService;
	/*
	 * @Autowired private ReportSrevice reportService; 
	 * @description:重定向到主页面面和查询
	 */

	@RequestMapping("/Report/Download.do")
	public ModelAndView Login(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));

		return new ModelAndView("ca/cacore/personal/personal");
	}
	/**
	 * 
	 *查看
	 */
	@RequestMapping("/report/personal/queryreport.do")
	public ModelAndView SeleteTeam(HttpServletRequest req,HttpServletResponse res){
		
		return null;
	}

	//下载
	@RequestMapping("/Report/DownloadExcel.do")
	public void downloadExceltest(HttpServletRequest req,
			HttpServletResponse res) throws IOException, WriteException {
		HSSFWorkbook wb = new HSSFWorkbook(); // 定义一个新的工作簿
		HSSFSheet sheet = wb.createSheet("第一个Sheet页"); // 创建第一个Sheet页
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

		List<Object[]> list = new ArrayList<Object[]>();//
		String[] entity = new String[15];// 创建一个要实体化的数组对象
		String[] entity1 = new String[15];// 创建一个要实体化的数组对象
		list.add(entity);
		list.add(entity1);
		int currentRowNum = 3;// 从第几行开始写
		int currentColNum = 0;// 从第几列开始写
		Row row = sheet.createRow(0); // 创建一个行
		Row row1 = sheet.createRow(1); // 创建第二个行
		Row row2 = sheet.createRow(2);
		// row.createCell(0).setCellValue("保险代理机构业务报表—人身险公司业务（单位：万元）");
		Cell cell = row.createCell(0);
		cell.setCellValue("保险代理机构业务报表—人身险公司业务（单位：万元）");
		cell.setCellStyle(cellStyle);
		row1.createCell(1).setCellValue("行次");
		row1.createCell(2).setCellValue("新单保费金额");
		row1.createCell(3).setCellValue("续期保单金额");
		row1.createCell(4).setCellValue("应付保费");
		row1.createCell(5).setCellValue("新单代理佣金");
		row1.createCell(6).setCellValue("续期代理佣金");
		Cell cell1 = row1.createCell(7);
		cell1.setCellValue("自营网络平台渠道");
		cell1.setCellStyle(cellStyle);
		Cell cell2 = row1.createCell(11);
		cell2.setCellValue("第三方网络平台渠道");
		cell2.setCellStyle(cellStyle);
		Cell cell3 = row2.createCell(2);
		cell3.setCellValue("累计");
		cell3.setCellStyle(cellStyle);
		Cell cell4 = row2.createCell(3);
		cell4.setCellValue("累计");
		cell4.setCellStyle(cellStyle);
		Cell cell5 = row2.createCell(4);
		cell5.setCellValue("累计");
		cell5.setCellStyle(cellStyle);
		Cell cell6 = row2.createCell(5);
		cell6.setCellValue("累计");
		cell6.setCellStyle(cellStyle);
		Cell cell7 = row2.createCell(6);
		cell7.setCellValue("累计");
		cell7.setCellStyle(cellStyle);
		row2.createCell(7).setCellValue("新单保费金额");
		row2.createCell(8).setCellValue("续期保单金额");
		row2.createCell(9).setCellValue("新单代理佣金");
		row2.createCell(10).setCellValue("续期代理佣金");
		row2.createCell(11).setCellValue("新单保费金额");
		row2.createCell(12).setCellValue("续期保单金额");
		row2.createCell(13).setCellValue("新单代理佣金");
		row2.createCell(14).setCellValue("续期代理佣金");
		row.createCell(3).setCellValue("");
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				0, // 其实列
				14 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 10));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				11, // 其实列
				14 // 结束列
		));
		sheet.setColumnWidth(1, 1500);
		sheet.setColumnWidth(2, 3300);
		sheet.setColumnWidth(3, 3300);
		sheet.setColumnWidth(5, 3300);
		sheet.setColumnWidth(6, 3300);
		sheet.setColumnWidth(7, 3300);
		sheet.setColumnWidth(8, 3300);
		sheet.setColumnWidth(9, 3300);
		sheet.setColumnWidth(10, 3300);
		sheet.setColumnWidth(11, 3300);
		sheet.setColumnWidth(12, 3300);
		sheet.setColumnWidth(13, 3300);
		sheet.setColumnWidth(14, 3300);
		// 设置response 这样就可以前台弹出框进行下载了
		String fileName = URLEncoder.encode("上报保监会报表（人身险）.xls", "UTF-8");
		ExcelUtils excelUtils = new ExcelUtils();
		excelUtils.writeExcel(list, currentRowNum, currentColNum, wb, sheet, fileName, res);
		
	}
	
	/*
	 * @description:中介公司报表查询导向页面
	 */

	@RequestMapping("/personal/goquerysysbranch.do")
	public ModelAndView goquerysysbranch(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
		String date_flag = ActionHelper.getNullToStr(req.getParameter("date_flag"));//日期标识
		String jsp_path = "";
		if ("M".equals(date_flag)) {
			jsp_path = "ca/cacore/personal/sysbranchcommission_month";
		}else {
			jsp_path = "ca/cacore/personal/sysbranchcommission";
		}
		
		return new ModelAndView(jsp_path);
	}
	
	/*
	 * @description:中介公司报表查询
	 */

	@RequestMapping("/personal/sysbranchcommission.do")
	public ModelAndView sysbranchcommission(HttpServletRequest req, HttpServletResponse res) {
		IUserModel user = ServletHelper.getSessionUser(req);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String person_flag = ActionHelper.getNullToStr(req.getParameter("person_flag"));//中介公司id
		String date_flag = ActionHelper.getNullToStr(req.getParameter("date_flag"));//日期标识
		Date term_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("term_date")));
//		String branch_level = ActionHelper.getNullToStr(req.getParameter("branch_level"));//中介公司id
		
		String jsp_path = "";
		if ("M".equals(date_flag)) {
			jsp_path = "ca/cacore/personal/sysbranchcommission_month";
		}else {
			jsp_path = "ca/cacore/personal/sysbranchcommission";
		}
		
		ReturnMsg returnMsg = new ReturnMsg();
		if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, jsp_path);
		}
		if (term_date == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, jsp_path);
		}
		Sysbranchcommissionimpl commission = new Sysbranchcommissionimpl();
		commission.setBranch_id(branch_id);
		commission.setTerm_date(term_date);
		commission.setDate_flag(date_flag);
		commission.setCreateUser(user.getEmp_id());
//		commission.setBranch_level(Integer.parseInt(branch_level));
		commission.setPageCount(ActionHelper.getPage(req));// 分页
		returnMsg = personalService.sysbranchcommission(commission);
//		if ("0".endsWith(branch_level)) {
//			returnMsg = personalService.citycommission(commission);
//		}else if ("1".endsWith(branch_level)) {
//			returnMsg = personalService.sysbranchcommission(commission);
//		}else {
//			returnMsg = personalService.departcommission(commission);
//		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, commission.getPageCount(), true));
		return returnPage(res, returnMsg, jsp_path);
	}
	
	/*
	 * @description:中介公司报表导出
	 */

	@RequestMapping("/personal/downloadsysbranchcommission.do")
	public ModelAndView downloadsysbranchcommission(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel user = ServletHelper.getSessionUser(req);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		Date term_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("term_date")));
		
		String date_flag = ActionHelper.getNullToStr(req.getParameter("date_flag"));//日期标识
		String jsp_path = "";
		if ("M".equals(date_flag)) {
			jsp_path = "ca/cacore/personal/sysbranchcommission_month";
		}else {
			jsp_path = "ca/cacore/personal/sysbranchcommission";
		}
		
		ReturnMsg returnMsg = new ReturnMsg();
		if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, jsp_path);
		}
		if (term_date == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, jsp_path);
		}
		Sysbranchcommission commission = new Sysbranchcommissionimpl();
		commission.setBranch_id(branch_id);
		commission.setTerm_date(term_date);
		commission.setDate_flag(date_flag);
		commission.setCreateUser(user.getEmp_id());
		commission.setPageCount(ActionHelper.getPage(req));// 分页
		returnMsg = personalService.downloadsysbranchcommission(commission);
//		if ("0".endsWith(branch_level)) {
//			returnMsg = personalService.downloadcitycommission(commission);
//		}else if ("1".endsWith(branch_level)) {
//			returnMsg = personalService.downloadsysbranchcommission(commission);
//		}else {
//			returnMsg = personalService.downloaddepartcommission(commission);
//		}
		this.downloadcommission(returnMsg, req, res);
		return null;
	}
	
	/*
	 * @description:代理报表查询导向页面
	 */

	@RequestMapping("/personal/goquerysysagency.do")
	public ModelAndView goquerysysagency(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
		return new ModelAndView("ca/cacore/personal/sysagencycommission");
	}
	
	/*
	 * @description:代理报表查询
	 */

	@RequestMapping("/personal/sysagencycommission.do")
	public ModelAndView sysagencycommission(HttpServletRequest req, HttpServletResponse res) {
		IUserModel user = ServletHelper.getSessionUser(req);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		Date term_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("term_date")));
		ReturnMsg returnMsg = new ReturnMsg();
		if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/personal/sysagencycommission");
		}
		if (term_date == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/personal/sysagencycommission");
		}
		Sysbranchcommission commission = new Sysbranchcommissionimpl();
		commission.setBranch_id(branch_id);
		commission.setTerm_date(term_date);
		commission.setCreateUser(user.getEmp_id());
		commission.setPageCount(ActionHelper.getPage(req));// 分页
		returnMsg = personalService.sysagencycommission(commission);
		ReturnMsg returnMsg1 = personalService.downloadagencycommission(commission);
		List<Map<String,Object>> dataList = returnMsg1.getDataList();
		Map<String, Object> map = dataList.get(0);
//		Sysbranchcommission commission = (Sysbranchcommission) map.get("collectcity");
		Sysbranchcommission commission_sum = (Sysbranchcommission) map.get("sysbranchcommission_sum");
		req.setAttribute("commission_sum", commission_sum);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, commission.getPageCount(), true));
		return returnPage(res, returnMsg, "ca/cacore/personal/sysagencycommission");
	}
	
	/*
	 * @description:代理报表导出
	 */

	@RequestMapping("/personal/downloadagencycommission.do")
	public ModelAndView downloadagencycommission(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel user = ServletHelper.getSessionUser(req);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		Date term_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("term_date")));
		ReturnMsg returnMsg = new ReturnMsg();
		if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/personal/sysagencycommission");
		}
		if (term_date == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/personal/sysagencycommission");
		}
		Sysbranchcommission commission = new Sysbranchcommissionimpl();
		commission.setBranch_id(branch_id);
		commission.setTerm_date(term_date);
		commission.setCreateUser(user.getEmp_id());
		commission.setPageCount(ActionHelper.getPage(req));// 分页
		returnMsg = personalService.downloadagencycommission(commission);
		this.downloadcommission(returnMsg, req, res);
		return null;
	}
	
	/*
	 * @description:保险公司报表查询导向页面
	 */

	@RequestMapping("/personal/goquerycpybranch.do")
	public ModelAndView goquerycpybranch(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
		
		/*HashMap<String, String> map = new HashMap<String, String>();
		map.put("branch_parentid", "00000000");
		ArrayList<HashMap<String, String>> list = cmainPolicyDao.find_cpy_branch(map);
		req.setAttribute("branch_cpy", list);*/
		
		String date_flag = ActionHelper.getNullToStr(req.getParameter("date_flag"));//日期标识
		String jsp_path = "";
		if ("M".equals(date_flag)) {
			jsp_path = "ca/cacore/personal/cpybranchcommission_month";
		}else {
			jsp_path = "ca/cacore/personal/cpybranchcommission";
		}
		
		return new ModelAndView(jsp_path);
	}
	
	
	/*
	 * @description:保险公司报表查询
	 */

	@RequestMapping("/personal/cpybranchcommission.do")
	public ModelAndView cpybranchcommission(HttpServletRequest req, HttpServletResponse res) {
		IUserModel user = ServletHelper.getSessionUser(req);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String cpy_branch_id = ActionHelper.getNullToStr(req.getParameter("cpybranch_id"));//中介公司id
//		String cpybranch_id = ActionHelper.getNullToStr(req.getParameter("cpybranch_id"));//保险公司id
		String org_level = ActionHelper.getNullToStr(req.getParameter("org_level"));//保险公司机构层级
		Integer org_level1 = 1;
		if(org_level != null && !"".equals(org_level)){
			org_level1 = Integer.parseInt(org_level);
		}
		Date term_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("term_date")));
		
		
		/*HashMap<String, String> map = new HashMap<String, String>();
		map.put("branch_parentid", "00000000");
		ArrayList<HashMap<String, String>> list = cmainPolicyDao.find_cpy_branch(map);
		req.setAttribute("branch_cpy", list);*/
		
		String date_flag = ActionHelper.getNullToStr(req.getParameter("date_flag"));//日期标识
		String jsp_path = "";
		if ("M".equals(date_flag)) {
			jsp_path = "ca/cacore/personal/cpybranchcommission_month";
		}else {
			jsp_path = "ca/cacore/personal/cpybranchcommission";
		}
		
		ReturnMsg returnMsg = new ReturnMsg();
		if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, jsp_path);
		}
//		if (cpybranch_id == null || "".equals(cpybranch_id)) {
//			returnMsg.setFailMessage("保险公司查询条件不能为空,请选择后查询。");
//			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
//			return returnPage(res, returnMsg, "ca/cacore/personal/cpybranchcommission");
//		}
		if (term_date == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/personal/cpybranchcommission");
		}
		Sysbranchcommission commission = new Sysbranchcommissionimpl();  
		commission.setBranch_id(branch_id);
		commission.setTerm_date(term_date);
		commission.setDate_flag(date_flag);
		commission.setCpy_branch_id(cpy_branch_id);
		commission.setCreateUser(user.getEmp_id());
		commission.setOrg_level(org_level1);
		Sysbranchcommission commission1 = new Sysbranchcommissionimpl();
		commission1.setBranch_id(branch_id);
		commission1.setTerm_date(term_date);
		commission1.setDate_flag(date_flag);
		commission1.setCpy_branch_id(cpy_branch_id);
		commission1.setCreateUser(user.getEmp_id());
		commission1.setOrg_level(org_level1);
//		commission.setCpybranch_id(cpybranch_id);
		//commission.setPageCount(ActionHelper.getPage(req));// 分页
		returnMsg = personalService.cpybranchcommission(commission);
		ReturnMsg returnMsg1 = personalService.downloadcpybranchcommission(commission1);
		List<Map<String,Object>> dataList = returnMsg1.getDataList();
		Map<String, Object> map1 = dataList.get(0);
		Sysbranchcommission commission_sum = (Sysbranchcommission) map1.get("sysbranchcommission_sum");
		req.setAttribute("commission_sum", commission_sum);
		/*req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, commission.getPageCount(), true));*/
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, true));
		return returnPage(res, returnMsg, jsp_path);
	}
	
	/*
	 * @description:保险公司报表导出
	 */

	@RequestMapping("/personal/downloadcpybranchcommission.do")
	public ModelAndView downloadcpybranchcommission(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel user = ServletHelper.getSessionUser(req);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String cpybranch_id = ActionHelper.getNullToStr(req.getParameter("cpybranch_id"));//保险公司id
		Date term_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("term_date")));
		String cpy_branch_id = ActionHelper.getNullToStr(req.getParameter("cpybranch_id"));//中介公司id
		String org_level = ActionHelper.getNullToStr(req.getParameter("org_level"));//保险公司机构层级
		Integer org_level1 = 1;
		if(org_level != null && !"".equals(org_level)){
			org_level1 = Integer.parseInt(org_level);
		}
		
		String date_flag = ActionHelper.getNullToStr(req.getParameter("date_flag"));//日期标识
		String jsp_path = "";
		if ("M".equals(date_flag)) {
			jsp_path = "ca/cacore/personal/cpybranchcommission_month";
		}else {
			jsp_path = "ca/cacore/personal/cpybranchcommission";
		}
		
		ReturnMsg returnMsg = new ReturnMsg();
		if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg,jsp_path);
		}
		if (term_date == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, jsp_path);
		}
		Sysbranchcommission commission = new Sysbranchcommissionimpl();
		commission.setBranch_id(branch_id);
		commission.setTerm_date(term_date);
		commission.setDate_flag(date_flag);
		commission.setCpybranch_id(cpybranch_id);
		commission.setCpy_branch_id(cpy_branch_id);
		commission.setCreateUser(user.getEmp_id());
		commission.setOrg_level(org_level1);
		commission.setPageCount(ActionHelper.getPage(req));// 分页
		returnMsg = personalService.downloadcpybranchcommission(commission);
		this.downloadcommission(returnMsg, req, res);
		return null;
	}
	
	
	
	
	/*
	 * @description:险种报表查询导向页面
	 */

	@RequestMapping("/personal/goqueryinsurance.do")
	public ModelAndView goqueryinsurance(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
		String date_flag = ActionHelper.getNullToStr(req.getParameter("date_flag"));//日期标识
		String jsp_path = "";
		if ("M".equals(date_flag)) {
			jsp_path = "ca/cacore/personal/insurancecommission_month";
		}else {
			jsp_path = "ca/cacore/personal/insurancecommission";
		}
		return new ModelAndView(jsp_path);
	}
	
	/*
	 * @description:险种公司报表查询
	 */

	@RequestMapping("/personal/insurancecommission.do")
	public ModelAndView insurancecommission(HttpServletRequest req, HttpServletResponse res) {
		IUserModel user = ServletHelper.getSessionUser(req);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String date_flag = ActionHelper.getNullToStr(req.getParameter("date_flag"));//日期标识
//		String cpybranch_id = ActionHelper.getNullToStr(req.getParameter("cpybranch_id"));//保险公司id
		Date term_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("term_date")));

		String jsp_path = "";
		if ("M".equals(date_flag)) {
			jsp_path = "ca/cacore/personal/insurancecommission_month";
		}else {
			jsp_path = "ca/cacore/personal/insurancecommission";
		}
		
		
		ReturnMsg returnMsg = new ReturnMsg();
		if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, jsp_path);
		}
//		if (cpybranch_id == null || "".equals(cpybranch_id)) {
//			returnMsg.setFailMessage("保险公司查询条件不能为空,请选择后查询。");
//			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
//			return returnPage(res, returnMsg, "ca/cacore/personal/insurancecommission");
//		}
		if (term_date == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, jsp_path);
		}
		Sysbranchcommission commission = new Sysbranchcommissionimpl();
		commission.setBranch_id(branch_id);
		commission.setTerm_date(term_date);
		commission.setDate_flag(date_flag);
		commission.setCreateUser(user.getEmp_id());
//		commission.setCpybranch_id(cpybranch_id);
		commission.setPageCount(ActionHelper.getPage(req));// 分页
		returnMsg = personalService.insurancecommission(commission);
		ReturnMsg returnMsg1 = personalService.downloadinsurancecommission(commission);
		List<Map<String,Object>> dataList = returnMsg1.getDataList();
		Map<String, Object> map = dataList.get(0);
		Sysbranchcommission commission_sum = (Sysbranchcommission) map.get("sysbranchcommission_sum");
		req.setAttribute("commission_sum", commission_sum);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, commission.getPageCount(), true));
		return returnPage(res, returnMsg, jsp_path);
	}
	
	/*
	 * @description:保险公司报表导出
	 */

	@RequestMapping("/personal/downloadinsurancecommission.do")
	public ModelAndView downloadinsurancecommission(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IUserModel user = ServletHelper.getSessionUser(req);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String date_flag = ActionHelper.getNullToStr(req.getParameter("date_flag"));//日期标识
//		String cpybranch_id = ActionHelper.getNullToStr(req.getParameter("cpybranch_id"));//保险公司id
		Date term_date = DateUtil.string2Date(ActionHelper.getNullToStr(req.getParameter("term_date")));
		ReturnMsg returnMsg = new ReturnMsg();
		if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/personal/insurancecommission");
		}
//		if (cpybranch_id == null || "".equals(cpybranch_id)) {
//			returnMsg.setFailMessage("保险公司查询条件不能为空,请选择后查询。");
//			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
//			return returnPage(res, returnMsg, "ca/cacore/personal/cpybranchcommission");
//		}
		if (term_date == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/personal/insurancecommission");
		}
		Sysbranchcommission commission = new Sysbranchcommissionimpl();
		commission.setBranch_id(branch_id);
		commission.setTerm_date(term_date);
		commission.setDate_flag(date_flag);
		commission.setCreateUser(user.getEmp_id());
//		commission.setCpybranch_id(cpybranch_id);
		commission.setPageCount(ActionHelper.getPage(req));// 分页
		returnMsg = personalService.downloadinsurancecommission(commission);
		this.downloadcommission(returnMsg, req, res);
		return null;
	}

	
	public void downloadcommission(ReturnMsg returnMsg, HttpServletRequest req, HttpServletResponse res) throws Exception{
		HSSFWorkbook wb = new HSSFWorkbook(); // 定义一个新的工作簿
		HSSFSheet sheet = wb.createSheet("第一个Sheet页"); // 创建第一个Sheet页
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		List<Map<String,Object>> dataList = returnMsg.getDataList();
		Map<String, Object> map = dataList.get(0);
//		Sysbranchcommission commission = (Sysbranchcommission) map.get("collectcity");
		Sysbranchcommission commission_sum = (Sysbranchcommission) map.get("sysbranchcommission_sum");
		List<Sysbranchcommission> commissionlist =  (List<Sysbranchcommission>) map.get("list");
		if(commission_sum!=null){
			commissionlist.add(commission_sum);
		}
		List<Object[]> list = new ArrayList<Object[]>();//
		
		for (int i = 0; i < commissionlist.size(); i++) {
			String[] entity1 = new String[37];// 创建一个要实体化的数组对象
			
			if(i == commissionlist.size()-1){
				entity1[0] = "合计";
			}else{
				entity1[0] = commissionlist.get(i).getBranch_name();
			}   
			entity1[1] =   bigDecimalToString( commissionlist.get(i).getG_day_count());
			entity1[2] =   bigDecimalToString( commissionlist.get(i).getG_day_amount());
			entity1[3] =   bigDecimalToString( commissionlist.get(i).getG_day_premium());
			entity1[4] =   bigDecimalToString( commissionlist.get(i).getG_day_fee());
			entity1[5] =   commissionlist.get(i).getNewg_day_premium();
			entity1[6] =   commissionlist.get(i).getNewg_day_fee();
			entity1[7] =   bigDecimalToString( commissionlist.get(i).getG_month_count());
			entity1[8] =   bigDecimalToString( commissionlist.get(i).getG_month_amount());
			entity1[9] =   bigDecimalToString( commissionlist.get(i).getG_month_premium());
			entity1[10] =  bigDecimalToString( commissionlist.get(i).getG_month_fee());
			entity1[11] =  commissionlist.get(i).getNewg_month_premium();
			entity1[12] =  commissionlist.get(i).getNewg_month_fee();
			entity1[13] =  bigDecimalToString( commissionlist.get(i).getG_year_count());
			entity1[14] =  bigDecimalToString( commissionlist.get(i).getG_year_amount());
			entity1[15] =  bigDecimalToString( commissionlist.get(i).getG_year_premium());
			entity1[16] =  bigDecimalToString( commissionlist.get(i).getG_year_fee());
			entity1[17] =  commissionlist.get(i).getNewg_year_premium();
			entity1[18] =  commissionlist.get(i).getNewg_year_fee();
			entity1[19] =  bigDecimalToString( commissionlist.get(i).getU_day_count());
			entity1[20] =  bigDecimalToString( commissionlist.get(i).getU_day_amount());
			entity1[21] =  bigDecimalToString( commissionlist.get(i).getU_day_premium());
			entity1[22] =  bigDecimalToString( commissionlist.get(i).getU_day_fee());
			entity1[23] =  commissionlist.get(i).getNewu_day_premium();
			entity1[24] =  commissionlist.get(i).getNewu_day_fee();
			entity1[25] =  bigDecimalToString( commissionlist.get(i).getU_month_count());
			entity1[26] =  bigDecimalToString( commissionlist.get(i).getU_month_amount());
			entity1[27] =  bigDecimalToString( commissionlist.get(i).getU_month_premium());
			entity1[28] =  bigDecimalToString( commissionlist.get(i).getU_month_fee());
			entity1[29] =  commissionlist.get(i).getNewu_month_premium();
			entity1[30] =  commissionlist.get(i).getNewu_month_fee();
			entity1[31] =  bigDecimalToString( commissionlist.get(i).getU_year_count());
			entity1[32] =  bigDecimalToString( commissionlist.get(i).getU_year_amount());
			entity1[33] =  bigDecimalToString( commissionlist.get(i).getU_year_premium());
			entity1[34] =  bigDecimalToString( commissionlist.get(i).getU_year_fee());
			entity1[35] =  commissionlist.get(i).getNewu_year_premium();
			entity1[36] =  commissionlist.get(i).getNewu_year_fee();
			list.add(entity1);
		}
		int currentRowNum = 2;// 从第几行开始写
		int currentColNum = 0;// 从第几列开始写
		Row row = sheet.createRow(0); // 创建一个行
		Row row1 = sheet.createRow(1); // 创建第二个行
		Cell cell01 = row.createCell(1);
		cell01.setCellValue("起保口径-本日");
		cell01.setCellStyle(cellStyle);
		Cell cell02 = row.createCell(7);
		cell02.setCellValue("起保口径-本月");
		cell02.setCellStyle(cellStyle);
		Cell cell03 = row.createCell(13);
		cell03.setCellValue("起保口径-本年");
		cell03.setCellStyle(cellStyle);
		Cell cell04 = row.createCell(19);
		cell04.setCellValue("核单口径-本日");
		cell04.setCellStyle(cellStyle);
		Cell cell05 = row.createCell(25);
		cell05.setCellValue("核单口径-本月");
		cell05.setCellStyle(cellStyle);
		Cell cell06 = row.createCell(31);
		cell06.setCellValue("核单口径-本年");
		cell06.setCellStyle(cellStyle);
		row1.createCell(1).setCellValue("承保数量");
		row1.createCell(2).setCellValue("保险金额");
		row1.createCell(3).setCellValue("承保保费");
		row1.createCell(4).setCellValue("手续费");
		row1.createCell(5).setCellValue("保费同期（+/-)");
		row1.createCell(6).setCellValue("手续费同期（+/-)");
		row1.createCell(7).setCellValue("承保数量");
		row1.createCell(8).setCellValue("保险金额");
		row1.createCell(9).setCellValue("承保保费");
		row1.createCell(10).setCellValue("手续费");
		row1.createCell(11).setCellValue("保费同期（+/-)");
		row1.createCell(12).setCellValue("手续费同期（+/-)");
		row1.createCell(13).setCellValue("承保数量");
		row1.createCell(14).setCellValue("保险金额");
		row1.createCell(15).setCellValue("承保保费");
		row1.createCell(16).setCellValue("手续费");
		row1.createCell(17).setCellValue("保费同期（+/-)");
		row1.createCell(18).setCellValue("手续费同期（+/-)");
		row1.createCell(19).setCellValue("承保数量");
		row1.createCell(20).setCellValue("保险金额");
		row1.createCell(21).setCellValue("承保保费");
		row1.createCell(22).setCellValue("手续费");
		row1.createCell(23).setCellValue("保费同期（+/-)");
		row1.createCell(24).setCellValue("手续费同期（+/-)");
		row1.createCell(25).setCellValue("承保数量");
		row1.createCell(26).setCellValue("保险金额");
		row1.createCell(27).setCellValue("承保保费");
		row1.createCell(28).setCellValue("手续费");
		row1.createCell(29).setCellValue("保费同期（+/-)");
		row1.createCell(30).setCellValue("手续费同期（+/-)");
		row1.createCell(31).setCellValue("承保数量");
		row1.createCell(32).setCellValue("保险金额");
		row1.createCell(33).setCellValue("承保保费");
		row1.createCell(34).setCellValue("手续费");
		row1.createCell(35).setCellValue("保费同期（+/-)");
		row1.createCell(36).setCellValue("手续费同期（+/-)");
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				1, // 其实列
				6 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				7, // 其实列
				12 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				13, // 其实列
				18 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				19, // 其实列
				24 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				25, // 其实列
				30 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				31, // 其实列
				36 // 结束列
		));
		// 设置response 这样就可以前台弹出框进行下载了
		String fileName = URLEncoder.encode("报表导出.xls", "UTF-8");
		ExcelUtils excelUtils = new ExcelUtils();
		excelUtils.writeExcel(list, currentRowNum, currentColNum, wb, sheet, fileName, res);
	}

	
	
	
	

	String bigDecimalToString (BigDecimal bigDecimal) {
		if (bigDecimal == null) {
			return "";
		}else {
			return bigDecimal.toString();
		}
	}
}
