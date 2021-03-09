package com.newtouch.report.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.newtouch.component.c11assistant.DateHelper;




public class ExcelUtils {
	
	
	public void writeExcel(List<Object[]> list,int currentRowNum,int currentColNum, HSSFWorkbook workbook,HSSFSheet sheet,String fileName,HttpServletResponse res ) throws IOException {
		
		for (Object[] objects : list) {//遍历list
			Row row = sheet.createRow(currentRowNum);//一个list就是一行，创建一个行
			for (Object object : objects) {//遍历每一个list的Object[]数组
				if (object==null) {
					Cell cell = row.createCell(currentColNum++, Cell.CELL_TYPE_BLANK);//如果该单元格为空，则创建一个空格子，不然数据会移位
					cell.setCellValue("");//将这个空格设置为空字符串
				}else if(object instanceof Double || object instanceof Float){//判断这个格子放的数据的类型，其他的同理
					Cell cell = row.createCell(currentColNum++, Cell.CELL_TYPE_NUMERIC);//如果是Double或者Float类型的，则用这个方式
					cell.setCellValue(Double.parseDouble(object.toString()));
				}else if(object instanceof Long || object instanceof Integer){
					Cell cell = row.createCell(currentColNum++, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(Double.parseDouble(object.toString()));
				}else if(object instanceof Date){
					Cell cell = row.createCell(currentColNum++, Cell.CELL_TYPE_STRING);
					cell.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format((Date)object));
				}else if(object instanceof Boolean){
					Cell cell = row.createCell(currentColNum++, Cell.CELL_TYPE_BOOLEAN);
					cell.setCellValue((Boolean)object);
				}else{
					Cell cell = row.createCell(currentColNum++, Cell.CELL_TYPE_STRING);
					cell.setCellValue(String.valueOf(object));
				}
			}
			currentRowNum++;//写完第一个list，跳到下一行
			currentColNum = 0;
		}
		// 设置response 这样就可以前台弹出框进行下载了
        res.setContentType("application/msexcel");
       res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHH") +fileName );
        OutputStream fileOut = res.getOutputStream();
        workbook.write(fileOut);
        fileOut.flush();
		fileOut.close();
		workbook = null;
		fileOut = null;
        
	}
	
}
