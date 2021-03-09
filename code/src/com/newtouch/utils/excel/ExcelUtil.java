package com.newtouch.utils.excel;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.newtouch.core.returnmsg.ReturnMsg;

public class ExcelUtil {
	private Map<String, List<Object>> excelData = null;
	private ReturnMsg returnMsg = new ReturnMsg();

	/**
	 * 初始化excel中的第一个 sheet
	 * <p>
	 * 默认有一行表头
	 * 
	 * @param titles
	 *            表头，对数据库封装对象相对应
	 * @param obj
	 *            数据库封装对象
	 * @param request
	 *            上传文件所需内容
	 * @param response
	 *            上传文件所需内容
	 * @param servletConfig
	 *            上传文件所需内容
	 * @return 是否成功信息
	 */
	// public ReturnMsg initExcelData(String[] titles, Object obj,
	// HttpServletRequest request, HttpServletResponse response,
	// ServletConfig servletConfig) {
	// ReturnMsg msg = new ReturnMsg();
	// FileUpload fileUpload = new FileUpload();
	// // 上传文件
	// String fileName = fileUpload.uploadFile(request, response,
	// servletConfig);
	// if (!fileUpload.isSuccessflag()) {
	// return fileUpload.getReturnMsg();
	// }
	// // 上传成功后读取excel
	// ExcelRead excelRead = new ExcelRead();
	// // 设置忽略空行
	// excelRead.setIgnoreBlankRow(true);
	// // 初始化excel
	// excelRead.initExcel(fileName);
	// // 初始化sheet数据
	// excelRead.initSheet(1, obj, titles);
	// // 得到数据
	// excelData = excelRead.getExcelData();
	// return msg;
	// }

	public Map<String, List<Object>> initSheet4Stream(InputStream in,Object object, String[] titles) {
		ExcelRead excel = new ExcelRead();
		// 设置忽略空行
		excel.setIgnoreBlankRow(true);
		excel.initExcel(in);
		Map<String, List<Object>> excelMap = excel.initSheet(1, titles);
		return excelMap;
	}

	public Map<String, List<Object>> initSheet4Stream(String path,
			Object[] objects, List<String[]> titles) {
		ExcelRead excel = new ExcelRead();
		// 设置忽略空行
		excel.setIgnoreBlankRow(true);
		excel.initExcel(path);
		Map<String, List<Object>> excelMap = excel.initSheet(objects, titles);
		return excelMap;
	}
    public Map<String, List<Object>> initSheet4Stream(String path,
                                                      Object object, String[] titles) {
        ExcelRead excel = new ExcelRead();
        // 设置忽略空行
        excel.setIgnoreBlankRow(true);
        excel.initExcel(path);
        Map<String, List<Object>> excelMap = excel.initSheet(object, titles);
        return excelMap;
    }
    //不仅判断一行的第一个cell为空
    public Map<String, List<Object>> initSheet4Stream_firstCellNull(String path,
                                                      Object object, String[] titles) {
        ExcelRead excel = new ExcelRead();
        // 设置忽略空行
        excel.setIgnoreBlankRow(true);
        excel.initExcel(path);
        Map<String, List<Object>> excelMap = excel.initSheet_firstCellNull(object, titles);
        return excelMap;
    }
    //取第一个sheet数据
    public Map<String, List<Object>> initSheet4Stream_oneSheet(String path,
    		Object object, String[] titles) {
    	ExcelRead excel = new ExcelRead();
    	// 设置忽略空行
    	excel.setIgnoreBlankRow(false);
    	excel.initExcel(path);
    	Map<String, List<Object>> excelMap_OneSheet = excel.initSheet_oneSheet(object, titles);
    	return excelMap_OneSheet;
    }

	public Map<String, List<Object>> getExcelData() {
		return excelData;
	}

	//zzzl导入
	public ReturnMsg getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(ReturnMsg returnMsg) {
		this.returnMsg = returnMsg;
	}
}
