package com.newtouch.utils.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.newtouch.core.context.SpringContext;
import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.core.view.jsptag.select.ISelectService;
import com.newtouch.utils.stringutil.StringUtil;
//输入
public class ExcelRead {
	private Workbook workBook = null;
	private Map<String, List<Object>> excelMap = new LinkedHashMap<String, List<Object>>();
	// 忽略空白行
	private boolean ignoreBlankRow = false;
	// 当前操作的页
	private Sheet sheet = null;
	// 当前操作的行
	private Row oneRow = null;
	// 表头行数
	private int headNum = 1;

	/**
	 * 
	 * @param filePath
	 *            文件路径
	 */
	public void initExcel(String filePath) {
		// 创建工作簿
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			if (filePath.endsWith(".xls")) {
				POIFSFileSystem fs = new POIFSFileSystem(fis);
				// 创建工作簿
				this.workBook = new HSSFWorkbook(fs);
			} else {
				this.workBook = new XSSFWorkbook(filePath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if (fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void initExcel(InputStream in) {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(in);
			this.workBook = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 多Sheet Excel初始化
	 * <p>
	 * 当Excel有多个Sheet并且每个Sheet封装对象不同时调用此方法。<br>
	 * 对象数组与表头数组需要有对应关系
	 * 
	 * @param objects
	 *            用于封装每条记录的对象
	 * @param titles
	 *            Excel表头所对应的对象属性<br>
	 *            需要与Excel表头顺序相同
	 */
	public Map<String, List<Object>> initSheet(Object[] objects,
			List<String[]> titles) {
		// 有多少个工作表，就循环多少次
//		System.out.println("共[" + workBook.getNumberOfSheets() + "]个Sheet");
		for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
//			System.out.println("第[" + (i + 1) + "]个Sheet");
			excelMap.put(workBook.getSheetName(i),
					this.initSheetData(i, titles.get(i), objects[i]));
		}
		return excelMap;
	}

	/**
	 * 单Sheet Excel初始化
	 * <p>
	 * 当Excel有多少Sheet或有多个Sheet，但所有Sheet封装对象相同时调用此方法。<br>
	 * 对象数组与表头数组需要有对应关系
	 * 
	 * @param objects
	 *            用于封装每条记录的对象
	 * @param titles
	 *            Excel表头所对应的对象属性<br>
	 *            需要与Excel表头顺序相同
	 */
	public Map<String, List<Object>> initSheet(Object object, String[] titles) {
		// 有多少个工作表，就循环多少次
//		System.out.println("共[" + workBook.getNumberOfSheets() + "]个Sheet");
		for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
//			System.out.println("第[" + (i + 1) + "]个Sheet");
			excelMap.put(workBook.getSheetName(i),
					this.initSheetData(i, titles));
		}
		return excelMap;
	}
	/**
	 * 单Sheet Excel初始化
	 * <p>
	 * 当Excel有多少Sheet或有多个Sheet，但所有Sheet封装对象相同时调用此方法。<br>
	 * 对象数组与表头数组需要有对应关系
	 * 
	 * @param objects
	 *            用于封装每条记录的对象
	 * @param titles
	 *            Excel表头所对应的对象属性<br>
	 *            需要与Excel表头顺序相同
	 */
	public Map<String, List<Object>> initSheet_firstCellNull(Object object, String[] titles) {
		// 有多少个工作表，就循环多少次
		excelMap.put(workBook.getSheetName(0),
		this.initSheetData_firstCellNull(0, titles));
		return excelMap;
	}
	
	
	
	/**
	 * 单Sheet Excel初始化
	 * <p>
	 * 当Excel有多少Sheet或有多个Sheet，但所有Sheet封装对象相同时调用此方法。<br>
	 * 对象数组与表头数组需要有对应关系
	 * 
	 * @param objects
	 *            用于封装每条记录的对象
	 * @param titles
	 *            Excel表头所对应的对象属性<br>
	 *            需要与Excel表头顺序相同
	 */
	public Map<String, List<Object>> initSheet_oneSheet(Object object, String[] titles) {
			excelMap.put(workBook.getSheetName(0),
					this.initSheetData_notCell(0, titles));
		return excelMap;
	}

	/*
	 * public Map<String, List<Object>> initSheet(InputStream in, Object object,
	 * String[] titles) { this.initExcel(in); this.initSheet(object, titles); //
	 * Map<String, List<Object>> excelMap = this.initSheet(object, titles);
	 * return excelMap; }
	 */

	/**
	 * 初始化Excel中指定的Sheet
	 * <p>
	 * 对象数组与表头数组需要有对应关系
	 * 
	 * @param sheetNum
	 *            指定Sheet
	 * @param objects
	 *            用于封装每条记录的对象
	 * @param titles
	 *            Excel表头所对应的对象属性<br>
	 *            需要与Excel表头顺序相同
	 */
	public Map<String, List<Object>> initSheet(int sheetNum, Map object,
			String[] titles) {
//		System.out.println("共[" + workBook.getNumberOfSheets() + "]个Sheet");
		if (sheetNum - 1 < workBook.getNumberOfSheets()) {
			System.out.println("第[" + sheetNum + "]个Sheet");
			excelMap.put(workBook.getSheetName(sheetNum - 1),
					this.initSheetData(sheetNum - 1, titles, object));
		}
		return excelMap;
	}

	public List<Object> initSheetData(int sheetNum, String[] titles, Object obj) {
		List<Object> list = new ArrayList<Object>();
		sheet = workBook.getSheetAt(sheetNum);
		if (sheet == null)
			return list;
		// 获得有值的行数
		int rows = sheet.getPhysicalNumberOfRows();
		if (rows <= 0)
			return list;
		// 一行
		int i = 0;
		int j = 0;
		// 行循环
		while (i < rows) {
			if (i < this.headNum) {
				i++;
				j++;
				continue;
			}
			System.out.println("第[" + (i + 1) + "]行");
			oneRow = sheet.getRow(i == j ? i : j);
			// 为null表示遇到空行，继续向后取
			while ((oneRow == null || StringUtil.isNull(oneRow.getCell(0)))
					&& ignoreBlankRow && j <= rows) {
				j++;
				oneRow = sheet.getRow(j);
			}
			i++;
			j++;
			if (oneRow == null)
				continue;
			try {
				Object aObject = Class.forName(obj.getClass().getName())
						.newInstance();
				// 初始化每行的数据
				if (this.initRowData(oneRow, aObject, titles)) {
					list.add(aObject);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<Object> initSheetData(int sheetNum, String[] titles) {
		List<Object> list = new ArrayList<Object>();
		sheet = workBook.getSheetAt(sheetNum);
		if (sheet == null)
			return list;
		// 获得有值的行数
		int rows = sheet.getPhysicalNumberOfRows();
		if (rows <= 0)
			return list;
		// 一行
		int i = 0;
		int j = 0;
		// 行循环
		while (i < rows) {
			if (i < this.headNum){ //headNum=1
				i++;
				j++;
				continue;
			}
			System.out.println("第[" + (i + 1) + "]行");
			oneRow = sheet.getRow(i == j ? i : j);
			// 为null表示遇到空行，继续向后取
			while ((oneRow == null || StringUtil.isNull(oneRow.getCell(0)))&& ignoreBlankRow && j <= rows) {
				j++;
				oneRow = sheet.getRow(j);
			}
			i++;
			j++;
			if (oneRow == null)
				continue;
			try {
				Map aObject = new HashMap();
				// 初始化每行的数据
				if (this.initRowData(oneRow, aObject, titles)) {
					list.add(aObject);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//方法同上（去掉校验第一个单元格为空的情况）
	private List<Object> initSheetData_notCell(int sheetNum, String[] titles) {
		List<Object> list = new ArrayList<Object>();
		sheet = workBook.getSheetAt(sheetNum);
		if (sheet == null)
			return list;
		// 获得有值的行数
		int rows = sheet.getPhysicalNumberOfRows();
		if (rows <= 0)
			return list;
		// 一行
		int i = 0;
		int j = 0;
		// 行循环
		while (i < rows) {
			if (i < this.headNum) {
				i++;
				j++;
				continue;
			}
			System.out.println("第[" + (i + 1) + "]行");
			oneRow = sheet.getRow(i == j ? i : j);
			// 为null表示遇到空行，继续向后取
			/*while (oneRow != null) {
				j++;
				oneRow = sheet.getRow(j);
			}*/
			i++;
			j++;
			if (oneRow == null)
				continue;
			try {
				Map aObject = new HashMap();
				// 初始化每行的数据
				if (this.initRowData(oneRow, aObject, titles)) {
					list.add(aObject);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	private List<Object> initSheetData_firstCellNull(int sheetNum, String[] titles) {
		List<Object> list = new ArrayList<Object>();
		sheet = workBook.getSheetAt(sheetNum);
		if (sheet == null)
			return list;
		// 获得有值的行数
		int rows = sheet.getPhysicalNumberOfRows();
		if (rows <= 0)
			return list;
		// 一行
		int i = 0;
		int j = 0;
		// 行循环
		while (i < rows) {
			if (i < this.headNum) {
				i++;
				j++;
				continue;
			}
			System.out.println("第[" + (i + 1) + "]行");
			oneRow = sheet.getRow(i == j ? i : j);
			// 为null表示遇到空行，继续向后取
			while ((oneRow == null || (StringUtil.isNull(oneRow.getCell(0))&&StringUtil.isNull(oneRow.getCell(1))))
					&& ignoreBlankRow && j <= rows) {
				j++;
				oneRow = sheet.getRow(j);
			}
			i++;
			j++;
			if (oneRow == null)
				continue;
			try {
				Map aObject = new HashMap();
				// 初始化每行的数据
				if (this.initRowData(oneRow, aObject, titles)) {
					list.add(aObject);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 初始化一行数据，并封装到Object对象中
	 * 
	 * @param aRow
	 *            一行数据
	 * @param aObject
	 *            对应的封装对象
	 * @param titles
	 *            对应的属性名
	 */
	private boolean initRowData(Row aRow, Object aObject, String[] titles) {
		// 一个单元格
		Cell cell = null;
		// 获得列数
		int cells = titles.length;
		// 列循环
		Object obj = null;
		for (int i = 0; i < cells; i++) {
			System.out.println("第[" + (i + 1) + "]个单元格");
			// 得到一个单元格
			cell = aRow.getCell(i);
			if (cell == null)
				continue;
			obj = this.initCellData(cell);
			// 需要自动去除空行，并且每行的第一列为空时，则去除
			if (ignoreBlankRow && i == 0 && "".equals(obj))
				return false;
			try {
				BeanUtils.setProperty(aObject, titles[i], obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	private boolean initRowData(Row aRow, Map aObject, String[] titles) {
		// 一个单元格
		Cell cell = null;
		// 获得列数
		int cells = titles.length;
		// 列循环
		Object obj = null;
		for (int i = 0; i < cells; i++) {
			System.out.println("第[" + (i + 1) + "]个单元格");
			// 得到一个单元格
			cell = aRow.getCell(i);
			if (cell == null)
				continue;
			obj = this.initCellData(cell);
			// 需要自动去除空行，并且每行的第一列为空时，则去除
			if (ignoreBlankRow && i == 0 && "".equals(obj))
				return false;
			try {
				if(titles[i].indexOf("#")!=-1){
					String[] arr = titles[i].split("#");
					if(arr.length==2){
						String enumCode=getEnumCodeByName(arr[1],(String)obj);
						aObject.put(arr[0],enumCode);
					}
				}else{
					aObject.put(titles[i], obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private String getEnumCodeByName(String enumParent,String enumName){
		ServerBase serverbase = null;
		if (serverbase == null) {
			serverbase = (ServerBase) SpringContext.getBean("serverbase");
		}
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String s = "select t.enum_code as code from T_ENUM t where t.enum_parentid=? and t.enum_name=? order by t.enum_id";
		query.setSql(s);
		query.setPaginate(false);
		query.add(enumParent);
		query.add(enumName);
		List<Map<String, Object>> result =serverbase.dbHandle().queryList(query);
		return (String) result.get(0).get("code");
	}
	
	/**
	 * 初始化单元格数据
	 * 
	 * @param cell
	 *            一个单元格
	 * @return
	 */
	private Object initCellData(Cell cell) {
		Object value = "";
		// 公式
		String formula = "";
		switch (cell.getCellType()) {
		// 字符串型
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().toString();
			System.out.println("数据类型为String,值为[" + value + "]");
			break;
		// 数值型
		case Cell.CELL_TYPE_NUMERIC:
			double d = 0.0;
			// 数值型为分日期和数值型两种
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				// 如果是date类型则 ，获取该cell的date值
				value = new java.sql.Date(HSSFDateUtil.getJavaDate(
						cell.getNumericCellValue()).getTime());
				System.out.println("数据类型为Date,值为[" + value + "]");
			} else {// 纯数字
				d = cell.getNumericCellValue();
				if (d - (int) d < Double.MIN_VALUE) {
					value = Integer.toString((int) d);
				} else {
					value = Double.toString(d);
				}
				System.out.println("数据类型为Double,值为[" + value + "]");
			}

			break;
		case Cell.CELL_TYPE_FORMULA:// 公式型
			formula = cell.getCellFormula();
			switch (cell.getCachedFormulaResultType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue();
				System.out.println("数据类型为公式,公式为[" + formula
						+ "],取值为Boolean,值为[" + value + "]");
				break;
			case Cell.CELL_TYPE_NUMERIC:
				// 数值型为分日期和数值型两种
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是date类型则 ，获取该cell的date值
					value = HSSFDateUtil
							.getJavaDate(cell.getNumericCellValue());
					System.out.println("数据类型为公式,公式为[" + formula
							+ "],取值为Date,值为[" + value + "]");
				} else {// 纯数字
					value = cell.getNumericCellValue();
					System.out.println("数据类型为公式,公式为[" + formula
							+ "],取值为Double,值为[" + value + "]");
				}
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getRichStringCellValue().toString();
				System.out.println("数据类型为公式,公式为[" + formula + "],取值为String,值为["
						+ value + "]");
				break;
			default:
				value = cell.getRichStringCellValue().toString();
				System.out.println("数据类型为公式,公式为[" + formula
						+ "],转为取值为String,值为[" + value + "]");
				break;
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:// 布尔
			value = cell.getBooleanCellValue();
			System.out.println("数据类型为Boolean,值为[" + value + "]");
			break;
		// 此行表示该单元格值为空
		case Cell.CELL_TYPE_BLANK: // 空值
			value = "";
			System.out.println("单元格值为空");
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			value = "";
			System.out.println("单元格取值出现错误");
			break;
		default:
			value = cell.getRichStringCellValue().toString();
			System.out.println("默认按String类型处理,值为[" + value + "]");
		}
		return value;
	}

	/**
	 * 得到所有Sheet数据
	 * 
	 * @return Excel对象
	 */
	public Map<String, List<Object>> getExcelData() {
		return excelMap;
	}

	/**
	 * 根据Sheet名,得到指定Sheet的数据
	 * 
	 * @param sheetName
	 *            sheet名
	 * @return
	 */
	public List<Object> getExcelData(String sheetName) {
		return excelMap.get(sheetName);
	}

	/**
	 * 返回空行处理方式
	 * <p>
	 * 默认为false.
	 * 
	 * @return true：忽略空行,当遇到空行取下一个值，直到遇到非空行;<br>
	 *         false：不忽略空行,当遇到空行当作有值处理.
	 */
	public boolean isIgnoreBlankRow() {
		return ignoreBlankRow;
	}

	/**
	 * 设置空行处理方式
	 * 
	 * @param ignoreBlankRow
	 *            true：忽略空行,当遇到空行取下一个值,直到遇到非空行;<br>
	 *            false：不忽略空行,当遇到空行当作有值处理.
	 */
	public void setIgnoreBlankRow(boolean ignoreBlankRow) {
		this.ignoreBlankRow = ignoreBlankRow;
	}

	public static void main(String args[]) throws Exception {
		ExcelRead excel = new ExcelRead();
		excel.setIgnoreBlankRow(true);
		excel.initExcel("C:/Users/lenovo/Desktop/test.xls");
		ExcelTestPojo obj = new ExcelTestPojo();
		String[] titles = new String[] { "a", "b", "c", "d", "e" };
		Map<String, List<Object>> excelMap = excel.initSheet(obj, titles);
		ExcelTestPojo obj1 = (ExcelTestPojo) excelMap.get("Sheet1").get(0);
		System.out.println("fffffffffffffffffffffffffffffff=" + obj1.getA());
		// System.out.println("Sheet数：" + excelMap.size());
		// System.out.println("第1个Sheet记录数" + excelMap.get("Sheet1").size());
		// System.out.println("第2个Sheet记录数" + excelMap.get("Sheet2").size());
		// System.out.println("第3个Sheet记录数" + excelMap.get("Sheet3").size());
	}

	public int getHeadNum() {
		return headNum;
	}

	/**
	 * 设置表头行数
	 * 
	 * @param headNum
	 */
	public void setHeadNum(int headNum) {
		this.headNum = headNum;
	}
}
