<%@page import="com.newtouch.utils.excel.ExcelWrite"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.*"%>
<%@page import="java.io.OutputStream"%>
<%@page import="org.apache.poi.ss.usermodel.Workbook"%>
<%
	String excelName = (String) request.getAttribute("excelName");
	Map<String, List<LinkedHashMap<String, String>>> excelHead = (Map<String, List<LinkedHashMap<String, String>>>) request
			.getAttribute("ExcelHead");
	Map<String, List<Map<String, Object>>> excelData = (Hashtable<String, List<Map<String, Object>>>) request
			.getAttribute("ExcelData");

	response.setContentType("application/vnd.ms-excel");
	response.addHeader("Content-Disposition", "attachment; filename="
			+ URLEncoder.encode(excelName + ".xls", "UTF-8"));

	ExcelWrite write = new ExcelWrite();
	if(excelName.startsWith("zouYiLiaoBuZhu")){
		List<String> lockArea = new ArrayList<String>();
		lockArea.add("S2:V65536");
		write.addNotLockArea("zouYiLiaoBuZhu", lockArea);
	}
	write.setResponse(response);
	// 设置表头
	write.setSheetHead(excelHead);
	// 合并表头
	write.setMergeCell(true);
	// 写数据
	Workbook wb = write.writeExcel4Jsp(excelData);
	OutputStream os = response.getOutputStream();
	wb.write(os);
	os.flush();
	os.close();
	os = null;
	response.flushBuffer();
	out.clear();
	out = pageContext.pushBody();
%>