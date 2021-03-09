package com.newtouch.report.webapp.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.newtouch.component.c11assistant.ModelHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.report.model.vo.BusinessTypeModel;
import com.newtouch.report.model.vo.IBusinessTypeModel;
import com.newtouch.report.utils.ExcelUtils;
import com.newtouch.report.webapp.service.IBussinessTypeService;

@Controller
public class BussinessTypeController extends BaseController{
	
	@Autowired
	private IBussinessTypeService businessTypeService;

	@RequestMapping("/bussiness/goBusinessType.do")
	public ModelAndView goBusinessType(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(),new PageCount(), true));
		return new ModelAndView("ca/cacore/personal/goBusinessType");
	}
	
	/**
	 * 业务类型报表-查询
	 * @param req
	 * @param res
	 * @return
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 */
	@RequestMapping("/bussiness/queryBusinessType.do")
	public ModelAndView queryBusinessType(HttpServletRequest req,HttpServletResponse res) throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		IUserModel user = ServletHelper.getSessionUser(req);
		ReturnMsg returnMsg = new ReturnMsg();
		ModelHelper modelHelper = new ModelHelper();
		IBusinessTypeModel businessTypeModel = new BusinessTypeModel();
		businessTypeModel = (IBusinessTypeModel) modelHelper.getModel(businessTypeModel, req);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String term = ActionHelper.getNullToStr(req.getParameter("term"));//期次
		
		businessTypeModel.setBranch_id(branch_id);
		businessTypeModel.setTerm(term);
		businessTypeModel.setEmpId(user.getEmp_id());
		businessTypeModel.setDept_list(user.getDept_list());
		returnMsg.setDataTable(TransHelper.obj2map(businessTypeModel));
		if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/personal/goBusinessType");
		}
		if (term == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/personal/goBusinessType");
		}
		returnMsg = businessTypeService.queryBusinessType(businessTypeModel);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, businessTypeModel.getPageCount(), true));
		return new ModelAndView("ca/cacore/personal/goBusinessType");
	}
	
	/**
	 * 业务类型报表-导出
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/bussiness/exportBusinessType.do")
	public ModelAndView exportBusinessType(HttpServletRequest req,HttpServletResponse res) {
		ReturnMsg returnMsg = new ReturnMsg();
		IUserModel user = ServletHelper.getSessionUser(req);
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String term = ActionHelper.getNullToStr(req.getParameter("term"));//期次
		IBusinessTypeModel businessTypeModel = new BusinessTypeModel();
		businessTypeModel.setBranch_id(branch_id);
		businessTypeModel.setTerm(term);
		businessTypeModel.setEmpId(user.getEmp_id());
		businessTypeModel.setDept_list(user.getDept_list());
		returnMsg.setDataTable(TransHelper.obj2map(businessTypeModel));
		try{
		if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后导出。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/personal/goBusinessType");
		}
		if (term == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后导出。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/personal/goBusinessType");
		}
		
		
		HSSFWorkbook wb = new HSSFWorkbook(); // 定义一个新的工作簿
		HSSFSheet sheet = wb.createSheet("第一个Sheet页"); // 创建第一个Sheet页
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

		List<Object[]> list = new ArrayList<Object[]>();//
		int currentRowNum = 0;// 从第几行开始写
		int currentColNum = 0;// 从第几列开始写
		Row row = sheet.createRow(0); // 创建第一个行
		Row row1 = sheet.createRow(1); // 创建第二个行
		Cell cell1 = row.createCell(0);
		cell1.setCellValue("分公司名称");
		cell1.setCellStyle(cellStyle);
		Cell cell2 = row.createCell(1);
		cell2.setCellValue("业务类型");
		cell2.setCellStyle(cellStyle);
		Cell cell3 = row.createCell(2);
		cell3.setCellValue("核单口径-本月");
		cell3.setCellStyle(cellStyle);
		Cell cell4 = row.createCell(6);
		cell4.setCellValue("核单口径-本年");
		cell4.setCellStyle(cellStyle);
		row1.createCell(2).setCellValue("保单数量");
		row1.createCell(3).setCellValue("代理保费");
		row1.createCell(4).setCellValue("跟单手续费");
		row1.createCell(5).setCellValue("财务已结手续费");
		row1.createCell(6).setCellValue("保单数量");
		row1.createCell(7).setCellValue("代理保费");
		row1.createCell(8).setCellValue("跟单手续费");
		row1.createCell(9).setCellValue("财务已结手续费");
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				2, // 其实列
				5 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				6, // 其实列
				9 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				1, // 结束行
				0, // 其实列
				0 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				1, // 结束行
				1, // 其实列
				1 // 结束列
		));
		
		sheet.setColumnWidth(0, 3500);
		sheet.setColumnWidth(1, 3500);
		sheet.setColumnWidth(2, 3500);
		sheet.setColumnWidth(3, 3500);
		sheet.setColumnWidth(4, 3500);
		sheet.setColumnWidth(5, 3500);
		sheet.setColumnWidth(6, 3500);
		sheet.setColumnWidth(7, 3500);
		sheet.setColumnWidth(8, 3500);
		sheet.setColumnWidth(9, 3500);
		
		PageCount pageCount = businessTypeModel.getPageCount();
		pageCount.setNowPage(0);
		pageCount.setLimit(60000);
		pageCount.setStart(0);
		pageCount.setRows4Page(0);
		pageCount.setPage(false);
		// 获取查询结果集
		List<IBusinessTypeModel> relist = businessTypeService.queryBusinessType1(businessTypeModel);
		pageCount.setPage(true);
		for (int i = 0; i < relist.size(); i++) {
			row1 = sheet.createRow(i+2);
			IBusinessTypeModel m = (BusinessTypeModel) relist.get(i);
			if(m!=null && m.getBranch_name() != null){
				row1.createCell(0).setCellValue(m.getBranch_name());
			}
			if(m!=null && m.getBusiness_flag() != null){
				row1.createCell(1).setCellValue(m.getBusiness_flag());
			}
			if(m!=null && m.getU_month_count()!=null){
				row1.createCell(2).setCellValue(m.getU_month_count());
			}
			if(m!=null && m.getU_month_premium()!=null){
				row1.createCell(3).setCellValue(m.getU_month_premium());
			}
			if(m!=null && m.getU_month_fee()!=null){
				row1.createCell(4).setCellValue(m.getU_month_fee());
			}
			if(m!=null && m.getU_month_paidfee()!=null){
				row1.createCell(5).setCellValue(m.getU_month_paidfee());
			}
			if(m!=null && m.getU_year_count()!=null){
				row1.createCell(6).setCellValue(m.getU_year_count());
			}
			if(m!=null && m.getU_year_premium()!=null){
				row1.createCell(7).setCellValue(m.getU_year_premium());
			}
			if(m!=null && m.getU_year_fee()!=null){
				row1.createCell(8).setCellValue(m.getU_year_fee());
			}
			if(m!=null && m.getU_year_paidfee()!=null){
				row1.createCell(9).setCellValue(m.getU_year_paidfee());
			}
		}
		// 设置response 这样就可以前台弹出框进行下载了
		String fileName = URLEncoder.encode("业务类型报表.xls", "UTF-8");
		ExcelUtils excelUtils = new ExcelUtils();
		excelUtils.writeExcel(list, currentRowNum, currentColNum, wb, sheet, fileName, res);
		}catch(Exception e){
			returnMsg.setFailMessage("导出失败");
			e.printStackTrace();
		}
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
		return new ModelAndView("ca/cacore/personal/goBusinessType");
	}
}
