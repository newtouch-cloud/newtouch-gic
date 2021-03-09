package com.newtouch.utils.excel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.newtouch.utils.stringutil.StringUtil;
//读取
public class ExcelWrite {
	private boolean isMergeCell = false;
	private Map<String, List<LinkedHashMap<String, String>>> sheetHead = new HashMap<String, List<LinkedHashMap<String, String>>>();
	private CreationHelper helper = null;
	private HttpServletResponse response = null;
	private CellStyle sytle_yyMMdd = null;
	private Map<String, List<Map<String, Integer>>> lockArray = new HashMap<String, List<Map<String, Integer>>>();// 锁定区域

	/**
	 * 
	 * @param excelMap
	 */
	public void writeExcel(Map<String, List<Map<String, Object>>> excelMap) {
		Workbook wb = new HSSFWorkbook();
		String[] head = null;
		// sheet
		for (String sheetName : excelMap.keySet()) {
			head = this.writeHead(sheetName, wb);
			this.writeSheet(sheetName, head, excelMap.get(sheetName), wb);
		}

		try {
			OutputStream fileOut = response.getOutputStream();
			// OutputStream fileOut = new FileOutputStream("e:\\111.xls");
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			wb = null;
			fileOut = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Workbook writeExcel4Jsp(
			Map<String, List<Map<String, Object>>> excelMap) {
		Workbook wb = new HSSFWorkbook();
		String[] head = null;
		// sheet
		for (String sheetName : excelMap.keySet()) {
			head = this.writeHead(sheetName, wb);
			this.writeSheet(sheetName, head, excelMap.get(sheetName), wb);
		}
		return wb;
	}

	/**
	 * 写excel表头
	 * 
	 * @return
	 */
	private String[] writeHead(String sheetName, Workbook wb) {
		Sheet aSheet = wb.createSheet(sheetName);

		int rownum = 0;
		String aHeadCell = "";
		int mergedCellStart = -1;
		int mergedCellEnd = -1;
		int cellNum = -1;
		String[] headList = null;
		List<LinkedHashMap<String, String>> list = sheetHead.get(sheetName);
		for (LinkedHashMap<String, String> aHead : list) {
			Row aRow = aSheet.createRow(rownum);
			// System.out.println(sheetHead.get(sheetName).size());
			mergedCellStart = -1;
			mergedCellEnd = -1;
			cellNum = -1;
			headList = new String[aHead.size()];
			for (String cellKey : aHead.keySet()) {
				// System.out.println(cellKey);
				cellNum++;
				if (rownum + 1 == sheetHead.get(sheetName).size()) {
					headList[cellNum] = cellKey;
				}
				aHeadCell = StringUtils.trim(aHead.get(cellKey));
				// 为空，且未赋过值则设置合并开始
				if ("".equals(aHeadCell) && mergedCellStart == -1) {
					mergedCellStart = cellNum;
					// 如果为最后一列，则设置合并
					if (cellNum + 1 == aHead.size()) {
						aSheet.addMergedRegion(new CellRangeAddress(rownum,
								rownum, mergedCellStart - 1, mergedCellStart));
					}
					continue;
				}
				// 不为空，并且设置为合并开始，则设置合并结束
				if (!"".equals(aHeadCell) && mergedCellStart != -1) {
					mergedCellEnd = cellNum;
				}
				// 写值
				aRow.createCell(cellNum).setCellValue(aHeadCell);
				// 如果全不为空，则设置合并单元格
				if (this.isMergeCell && mergedCellStart != -1
						&& mergedCellEnd != -1) {
					aSheet.addMergedRegion(new CellRangeAddress(rownum, rownum,
							mergedCellStart - 1, mergedCellEnd - 1));
					// System.out.println("合并第[" + (rownum) + "]行,第["
					// + mergedCellStart + "]列到第[" + mergedCellEnd + "]列");
					// 设置为初始状态
					mergedCellStart = -1;
					mergedCellEnd = -1;
				}

			}
			rownum++;
		}
		if (sheetHead.get(sheetName).size() < 2) {
			return headList;
		}
		Object obj = null;
		Object before = null;
		// 大于两行表头的情况，需要做列合并
		for (int i = 0; i < sheetHead.get(sheetName).get(0).size(); i++) {// 列循环
			obj = null;
			before = null;
			mergedCellStart = -1;
			mergedCellEnd = -1;
			for (int j = 0; j < sheetHead.get(sheetName).size(); j++) {// 行循环
				obj = this.getValue(sheetHead.get(sheetName).get(j), i);
				// 与上一个相同，则设置开始
				if (mergedCellStart == -1 && obj.equals(before)) {
					mergedCellStart = j;
				}
				if (mergedCellEnd == -1 && mergedCellStart != -1
						&& !obj.equals(before)) {
					mergedCellEnd = j;
				}
				if (this.isMergeCell && mergedCellStart != -1
						&& mergedCellEnd != -1) {
					aSheet.addMergedRegion(new CellRangeAddress(i, i,
							mergedCellStart - 1, mergedCellEnd - 1));
					// 设置为初始状态
					mergedCellStart = -1;
					mergedCellEnd = -1;
				}
				if (mergedCellStart != -1
						&& mergedCellStart == sheetHead.get(sheetName).size() - 1) {
					aSheet.addMergedRegion(new CellRangeAddress(
							mergedCellStart - 1, mergedCellStart, i, i));
					// 设置为初始状态
					mergedCellStart = -1;
					mergedCellEnd = -1;
				}
				before = obj;
			}
		}
		return headList;
	}

	private Object getValue(LinkedHashMap<String, String> link, int size) {
		int i = 0;
		for (String key : link.keySet()) {
			if (i == size)
				return link.get(key);
			i++;
		}
		return "";
	}

	/**
	 * 写excel内容
	 * 
	 * @param sheetName
	 *            sheet名称
	 * @param sheetHead
	 *            表头字段名,用于写值
	 * @param sheetValue
	 *            存放数据的值
	 * @param wb
	 *            excel对象
	 */
	private void writeSheet(String sheetName, String[] sheetHead,
			List<Map<String, Object>> sheetValue, Workbook wb) {
		Sheet aSheet = wb.getSheet(sheetName);
		// 用于格式化单元格的数据
        DataFormat format = wb.createDataFormat();

		int rownum = aSheet.getLastRowNum() + 1;
		// row
		for (Map<String, Object> rowValue : sheetValue) {
			Row aRow = aSheet.createRow(rownum);
			int cellnum = -1;
			// System.out.println("");
			// System.out.print("行[" + rownum + "]");
			// cell
			for (String cellKey : sheetHead) {
				Object obj = rowValue.get(cellKey);
				cellnum++;
				if(obj != null){
					/*aSheet.setColumnWidth(cellnum, obj.toString().getBytes().length*3*256);*/
					//by zdd02 20190617 start
					if(obj.toString().getBytes().length<255*256){
						aSheet.setColumnWidth(cellnum, obj.toString().getBytes().length < 3000 ? 3000 : obj.toString().getBytes().length);    
		            }else{
		            	aSheet.setColumnWidth(cellnum,6000 );
		            }
					//by zdd02 20190617 end
				}
				Cell aCell = aRow.createCell(cellnum);
				// System.out.print("列[" + cellnum + "]" + obj + " ");
				if (obj instanceof Boolean) {
					aCell.setCellValue((Boolean) obj);
					aSheet.setColumnWidth(cellnum, 3000);
					continue;
				}
				if (obj instanceof Double) {
					aCell.setCellValue((Double) obj);
					 // 设置单元格类型
			        CellStyle cellStyle = wb.createCellStyle();
					cellStyle = wb.createCellStyle();
					cellStyle.setDataFormat(format.getFormat("#,##0.0000"));
					aSheet.setColumnWidth(cellnum, 3000);
					continue;
				}
				if (obj instanceof String) {
					aCell.setCellValue((String) obj);
					aSheet.setColumnWidth(cellnum, 3000);
					continue;
				}
				if (obj instanceof Calendar) {
					this.initDateStyle(wb);
					aCell.setCellValue((Calendar) obj);
					aCell.setCellStyle(this.sytle_yyMMdd);
					aSheet.setColumnWidth(cellnum, 3000);
					continue;
				}
				if (obj instanceof Date) {
					this.initDateStyle(wb);
					aCell.setCellValue((Date) obj);
					aCell.setCellStyle(this.sytle_yyMMdd);
					aSheet.setColumnWidth(cellnum, 3000);
					continue;
				}
				aCell.setCellValue((String) obj);
			}
			rownum++;
		}

		// 判断是否需要设置excel保护
		if (this.lockArray.get(sheetName) == null) {
			return;
		}
		// 设置excel保护
		org.apache.poi.hssf.usermodel.HSSFSheet sheet = (org.apache.poi.hssf.usermodel.HSSFSheet) aSheet;
		sheet.protectSheet("");
		// 设置可编辑的单元格
		this.setCellLocked(sheetName, wb);
	}

	private void initDateStyle(Workbook wb) {
		if (helper == null) {
			helper = wb.getCreationHelper();
		}
		if (sytle_yyMMdd == null) {
			sytle_yyMMdd = wb.createCellStyle();
			sytle_yyMMdd.setDataFormat(helper.createDataFormat().getFormat(
					"yyyy-MM-dd"));
		}
	}

	/**
	 * 设置是否合并表头
	 * <p>
	 * 默认为false。
	 * 
	 * @param isMergeCell
	 *            true：合并表头，如果行有空格，则自动合并 <br>
	 *            false：不合并表头。
	 */
	public void setMergeCell(boolean isMergeCell) {
		this.isMergeCell = isMergeCell;
	}

	/**
	 * 设置每页表头
	 * 
	 * 
	 * @param sheetHead
	 *            Map键名为sheet名，值为表头，值的key为与查询结果对应的英文，value为中文表头。<br>
	 *            若存在多表头情况，则在List中存放多个值既可。
	 */
	public void setSheetHead(
			Map<String, List<LinkedHashMap<String, String>>> sheetHead) {
		this.sheetHead = sheetHead;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 不锁定单元格，根据sheet名称设置
	 * 
	 * @param sheetName
	 * @param lockArray
	 *            设置不需要锁定的区域 <br>
	 *            按照“列行：列行”方式设置，参考Excel中设置。<br>
	 *            列只能为大写字母；行只能为数字，行从1开始。
	 */
	public void addNotLockArea(String sheetName, List<String> lockArray) {
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		for (String lockArea : lockArray) {
			list.add(this.initLockArea(lockArea));
		}
		this.lockArray.put(sheetName, list);
	}

	/**
	 * 不锁定单元格，根据sheet名称设置
	 * 
	 * @param sheetName
	 * @param lockArray
	 *            设置不需要锁定的区域 <br>
	 *            按照“列行：列行”方式设置，参考Excel中设置。<br>
	 *            列只能为大写字母；行只能为数字，行从1开始。
	 */
	public void addNotLockArea(String sheetName, String lockArea) {
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		list.add(initLockArea(lockArea));
		this.lockArray.put(sheetName, list);
	}

	private Map<String, Integer> initLockArea(String lockArea) {
		Integer[] area = this.str2Arr(lockArea).toArray(new Integer[] {});
		Map<String, Integer> areaMap = new HashMap<String, Integer>();
		areaMap.put("startClm", area[0]);
		areaMap.put("startRow", area[1] - 1);// 录入时从1开始，取值时从0开始，所以需要减1。
		areaMap.put("endClm", area[2]);
		areaMap.put("endRow", area[3] - 1);// 录入时从1开始，取值时从0开始，所以需要减1。
		return areaMap;
	}

	private void setCellLocked(String sheetName, Workbook wb) {
		Sheet sheet = wb.getSheet(sheetName);
		CellStyle lockStyle = wb.createCellStyle();
		lockStyle.setLocked(false);
		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = 0;
		for (Map<String, Integer> lockArea : this.lockArray.get(sheetName)) {
			for (int startRow = lockArea.get("startRow"); startRow <= lockArea
					.get("endRow"); startRow++) {
				if (startRow > lastRowNum)// 不要锁定行，超出最大行，则退出
					continue;
				Row aRow = sheet.getRow(startRow);
				lastCellNum = aRow.getLastCellNum();
				for (int startClm = lockArea.get("startClm"); startClm <= lockArea
						.get("endClm"); startClm++) {
					if (startClm > lastCellNum)// 不要锁定列，超出最大列，则退出
						continue;
					Cell cell = aRow.getCell(startClm);
					cell.setCellStyle(lockStyle);
					System.out.println("取消锁定[" + startRow + "]行[" + startClm
							+ "]列");
				}
			}
		}
	}

	/**
	 * 将excel区域转换为数字弄区域
	 * 
	 * @param s
	 * @return
	 */
	private List<Integer> str2Arr(String s) {
		if (s.contains(":")) {
			String sArr[] = s.split(":");
			List<Integer> intList = new ArrayList<Integer>();
			intList.addAll(this.str2Arr(sArr[0]));
			intList.addAll(this.str2Arr(sArr[1]));
			return intList;
		}
		String array = s.toUpperCase();
		String lie = "";
		String hang = "";
		for (int i = 0; i < array.length(); i++) {
			if (Character.isUpperCase(array.charAt(i))) {
				lie += array.charAt(i);
				continue;
			}
			hang += array.charAt(i);
		}
		List<Integer> intList = new ArrayList<Integer>();
		intList.add(this.str2Num(lie));
		if ("".equals(hang)) {
			intList.add(65535);
			return intList;
		}
		intList.add(Integer.parseInt(hang));
		return intList;
	}

	/**
	 * 将excel列中的A、B、AA、AB等内容转换为0、1等数字
	 * 
	 * @param s
	 * @return
	 */
	private int str2Num(String s) {
		int r = 0;
		for (int i = 0; i < s.length(); i++) {
			r = r * 26 + s.charAt(i) - 'A' + 1;
		}
		return r - 1;
	}

	public static void main(String args[]) {
		Map<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		// sheet
		for (int i = 0; i < 2; i++) {
			List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
			// row
			for (int j = 0; j < 10; j++) {
				Map<String, Object> hashMap = new HashMap<String, Object>();
				// cell
				for (int k = 0; k < 12; k++) {
					hashMap.put("列" + k, "值" + k);
				}
				sheetList.add(hashMap);
			}
			excelMap.put("测试" + i, sheetList);
		}
		// 合并表头
		LinkedHashMap<String, String> mergehead = new LinkedHashMap<String, String>();
		for (int k = 0; k < 12; k++) {
			if (k != 0 && k % 2 == 0) {
				mergehead.put("列空" + (k + 1), "");
				mergehead.put("列空2" + (k + 2), "");
				mergehead.put("列空3" + (k + 3), "");
			}
			if (k != 0 && k % 3 == 0) {
				mergehead.put("列空4" + (k + 4), "");
				mergehead.put("列空5" + (k + 5), "");
			}
			mergehead.put("列" + k, "表头" + k);
		}
		mergehead.put("列空", "");
		// 正常表头
		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		for (int k = 0; k < 12; k++) {
			head.put("列" + k, "表头" + k);
		}
		// 设置每个sheet页的表头
		List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
		sheetHead.add(mergehead);
		sheetHead.add(head);
		// 设置多sheet页
		Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
		map.put("测试0", sheetHead);
		map.put("测试1", sheetHead);

		ExcelWrite write = new ExcelWrite();
		write.setSheetHead(map);
		write.setMergeCell(true);
		write.addNotLockArea("测试1", "A3:F8");
		// 设置数据，key需要跟sheet页的key相同，值为需要写的数据
		write.writeExcel(excelMap);
	}
}
