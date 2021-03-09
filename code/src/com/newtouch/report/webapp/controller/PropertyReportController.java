package com.newtouch.report.webapp.controller;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newtouch.component.c11assistant.ReturnMsgHelper;

import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;

import com.newtouch.report.utils.ExcelUtils;





@Controller
public class PropertyReportController extends BaseController{
 
	/**
	 * 
	 *页面访问地址
	 */
	@RequestMapping("/Report/toqueryReport.do")
	public ModelAndView Login(HttpServletRequest req,HttpServletResponse res){
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(), new PageCount(), true));
	   	return new ModelAndView("ca/cacore/report/propertyReport");
	   }
	/**
	 * 
	 *查看
	 */
	@RequestMapping("/report/propertyReport/queryreport.do")
	public ModelAndView SeleteTeam(HttpServletRequest req,HttpServletResponse res){
		
		return null;
	}
	/**
	 * 
	 *信息导出
	 * @throws IOException 
	 */
	@RequestMapping("/report/propertyReport/exportreport.do")
    public void ExportReport(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("财产险");
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		/*HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示   
		cellStyle.setFont(font);*/
		
		/*
		 * 遍历数组
		 */
		
		//测试数据
		List<Object[]> list = new ArrayList<Object[]>();//
		String[]  entity = new String[9];//创建一个要实体化的数组对象
		entity[0] = "1232";//给数组赋值
		entity[1] = "1232";
		entity[2] = "1232";
		entity[3] = "1232";
		entity[4] = "1232";
		entity[5] = "1232";
		entity[6] = "1232";
		entity[7] = "1232";
		entity[8] = "1232";
		String[]  entity1 = new String[9];//创建一个要实体化的数组对象
		entity1[0] = "1232";//给数组赋值
		entity1[1] = "1232";
		entity1[2] = "1232";
		entity1[3] = "1232";
		entity1[4] = "1232";
		entity1[5] = "1232";
		entity1[6] = "1232";
		entity1[7] = "1232";
		entity1[8] = "1232";
		list.add(entity );
		list.add(entity1 );
		
		
		int currentRowNum = 3;//从第几行开始写
		int currentColNum = 0;//从第几列开始写
		
		/*
		 * 表头样式
		 */
		Row row=sheet.createRow(0); // 创建第一个行
	    Cell cell1=row.createCell(0);
		cell1.setCellValue("保险代理机构业务报表--产险公司业务（单位：万元）");
		cell1.setCellStyle(cellStyle);
		
		Row row1=sheet.createRow(1);//创建第二行
		
		Cell cell2=row1.createCell(1);
		cell2.setCellValue("行次");
		Cell cell3=row1.createCell(2);
		cell3.setCellValue("保费金额（万元）");
		Cell cell4=row1.createCell(3);
		cell4.setCellValue("跟单手续费（万元）");
		Cell cell5=row1.createCell(4);
		cell5.setCellValue("财务已结算手续费（万元）");
		Cell cell6=row1.createCell(5);
		cell6.setCellValue("自营网络平台渠道");
		cell6.setCellStyle(cellStyle);
		Cell cell7=row1.createCell(7);
		cell7.setCellValue("第三方网络平台渠道");
		cell7.setCellStyle(cellStyle);
		
		Row row2=sheet.createRow(2);//创建第三行
		
        Cell cell8=row2.createCell(2);
        cell8.setCellValue("累计");
        cell8.setCellStyle(cellStyle);
        Cell cell9=row2.createCell(3);
        cell9.setCellValue("累计");
        cell9.setCellStyle(cellStyle);
        Cell cell10=row2.createCell(4);
        cell10.setCellValue("累计");
        cell10.setCellStyle(cellStyle);
        Cell cell11=row2.createCell(5);
        cell11.setCellValue("保费金额（万元）");
        Cell cell12=row2.createCell(6);
        cell12.setCellValue("代理佣金（万元）");
        Cell cell13=row2.createCell(7);
        cell13.setCellValue("保费金额（万元）");
        Cell cell14=row2.createCell(8);
        cell14.setCellValue("代理佣金（万元）");
        
        /*
		 * 设置列宽
		 */
		sheet.setColumnWidth(2, 3880);
		sheet.setColumnWidth(3, 4200);
		sheet.setColumnWidth(4, 5200);
		sheet.setColumnWidth(5, 3880);
		sheet.setColumnWidth(6, 3880);
		sheet.setColumnWidth(7, 3880);
		sheet.setColumnWidth(8, 3880);
		
		 
        /*
		 * 合并单元格
		 */
		CellRangeAddress region1 = new CellRangeAddress(0, (short)0, 0, (short)8);
		CellRangeAddress region2 = new CellRangeAddress(1, (short)1, 5, (short)6);
        CellRangeAddress region3 = new CellRangeAddress(1, (short)1, 7, (short)8);
        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);
        sheet.addMergedRegion(region3);
        
        ExcelUtils excelUtils = new ExcelUtils();
        String fileName = URLEncoder.encode("财险表.xls","UTF-8");
		excelUtils.writeExcel(list, currentRowNum, currentColNum, workbook, sheet, fileName, res);
        
		
	}
	}	

