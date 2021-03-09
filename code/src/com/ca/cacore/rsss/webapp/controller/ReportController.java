package com.ca.cacore.rsss.webapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.ca.cacore.rsss.model.vo.AentLifeInsModel;
import com.ca.cacore.rsss.model.vo.AgentPropertyModel;
import com.ca.cacore.rsss.model.vo.BasicInfomationModel;
import com.ca.cacore.rsss.model.vo.BasicSituationModel;
import com.ca.cacore.rsss.model.vo.BusinessReportModel;
import com.ca.cacore.rsss.model.vo.CustomeGDVOModel;
import com.ca.cacore.rsss.model.vo.IAentLifeInsModel;
import com.ca.cacore.rsss.model.vo.IAgentPropertyModel;
import com.ca.cacore.rsss.model.vo.IBasicInfomationModel;
import com.ca.cacore.rsss.model.vo.IBasicSituationModel;
import com.ca.cacore.rsss.model.vo.IBusinessReportModel;
import com.ca.cacore.rsss.model.vo.ICustomeGDVOModel;
import com.ca.cacore.rsss.model.vo.IInsAgenPersonReportModel;
import com.ca.cacore.rsss.model.vo.IInsBusProReportModel;
import com.ca.cacore.rsss.model.vo.IInsCompanyBusModel;
import com.ca.cacore.rsss.model.vo.IPersonalBusReportModel;
import com.ca.cacore.rsss.model.vo.IYYPersonReportVOModel;
import com.ca.cacore.rsss.model.vo.InsAgenPersonReportModel;
import com.ca.cacore.rsss.model.vo.InsBusProReportModel;
import com.ca.cacore.rsss.model.vo.InsCompanyBusModel;
import com.ca.cacore.rsss.model.vo.PersonalBusReportModel;
import com.ca.cacore.rsss.model.vo.YYPersonReportVOModel;
import com.ca.cacore.rsss.webapp.service.IBasicSituationService;
import com.ca.cacore.rsss.webapp.service.IBusinessReportCService;
import com.ca.cacore.rsss.webapp.service.ICustomeGDService;
import com.ca.cacore.rsss.webapp.service.IInsAgenPersonReportService;
import com.ca.cacore.rsss.webapp.service.IInsBusProReportService;
import com.ca.cacore.rsss.webapp.service.IInsCompanyBusService;
import com.ca.cacore.rsss.webapp.service.IPersonalBusReportService;
import com.ca.cacore.rsss.webapp.service.IYYPersonReportService;
import com.newtouch.component.c11assistant.ActionHelper;
import com.newtouch.component.c11assistant.DateHelper;
import com.newtouch.component.c11assistant.ModelHelper;
import com.newtouch.component.c11assistant.ReturnMsgHelper;
import com.newtouch.component.c11assistant.ServletHelper;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.component.c1properties.StaticProperties;
import com.newtouch.component.c4fileDownload.FileDownLoadUtil;
import com.newtouch.core.basecontroller.BaseController;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.excel.ExcelWrite;
import com.newtouch.report.utils.ExcelUtils;

/**
 * 
* @since:    2014年6月6日   
* @author    wangjianqiang
* @description:报表查询controller
 */
@Controller
public class ReportController extends BaseController{
	@Autowired private IBusinessReportCService businessReportcService;//查询保险代理机构业务汇总表(一)
	@Autowired private ICustomeGDService icustomegdservice;//客户群体分布表
	@Autowired private IInsCompanyBusService iinscompanybusservice;//产险
	@Autowired private IPersonalBusReportService ipersonalbusreportservice;//人身险
	@Autowired private IInsAgenPersonReportService iinsagenpersonreportservice;//保险代理机构业务人员电子档案查询
	@Autowired private IInsBusProReportService iinsbusproreportservice;//保险代理机构业务协议电子档案查询
	@Autowired private IBasicSituationService ibasicsituationservice;//基本情况表
	@Autowired private IYYPersonReportService iyypersonreportservice;//查询营运人员效力情况表
	/**
	 * @author lds
	 *页面访问地址
	 */
	@RequestMapping("/rsss/Report/toqueryReport.do")
	public ModelAndView toqueryReport(HttpServletRequest req,
			HttpServletResponse res) {
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(),
				new PageCount(), true));
		return new ModelAndView(
				"ca/cacore/rsss/insuranceReport/Report");
	}
	/**
	* 
	* @param req
	* @param res
	* @return ModelAndView
	* @description:报表查询
	 */
	@RequestMapping("/rsss/Report/queryReport.do")
	public ModelAndView queryReport(HttpServletRequest req,HttpServletResponse res){
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));
		String reportType=ActionHelper.getNullToStr(req.getParameter("reportType"));
		String firstDate=ActionHelper.getNullToStr(req.getParameter("firstDate"));
		String firstDateJbb=ActionHelper.getNullToStr(req.getParameter("firstDateJbb"));
		String secondDate=ActionHelper.getNullToStr(req.getParameter("secondDate"));
		String statistic_year=ActionHelper.getNullToStr(req.getParameter("statistic_year"));
		String statistic_month=ActionHelper.getNullToStr(req.getParameter("statistic_month"));
		ReturnMsg returnMsg =new ReturnMsg();
		String branch_name="";
		
		if("YWBBCX".equals(reportType)){//保险代理机构业务报表-产险
			String a=firstDate+"-01";
			Date c=DateUtil.string2Date(a.trim());
			//产险model
			IInsCompanyBusModel modelcx=new InsCompanyBusModel();
			modelcx.setReportType(reportType.trim());
			modelcx.setBranch_id(branch_id.trim());
			modelcx.setFirstDate(c);
			returnMsg=iinscompanybusservice.queryInsCompanyBus(modelcx);
			req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,true));
			//req.setAttribute("branch_id", branch_id);
			//req.setAttribute("branch_name",branch_name);
			req.setAttribute("firstDate", firstDate);
			req.setAttribute("reportType", reportType);
			return new ModelAndView("ca/cacore/rsss/insuranceReport/"+reportType+"Report");
		}else if("YWBBRSX".equals(reportType)){//保险代理机构业务报表-人身险
			String a=firstDate+"-01";
			Date b=DateUtil.string2Date(a.trim());
			IPersonalBusReportModel modelrsx=new PersonalBusReportModel();
			modelrsx.setBranch_id(branch_id.trim());
			modelrsx.setFirstDate(b);
			returnMsg=ipersonalbusreportservice.queryPersonalBusReport(modelrsx);
			req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,true));
			//req.setAttribute("branch_id", branch_id);
			//req.setAttribute("branch_name",branch_name);
			req.setAttribute("firstDate", firstDate);
			req.setAttribute("reportType", reportType);
			return new ModelAndView("ca/cacore/rsss/insuranceReport/"+reportType+"Report");
		}else if("JBQKB".equals(reportType)){//基本情况表
			String a=firstDateJbb+"-01";
			IBasicSituationModel modeljbqk=new BasicSituationModel();
			modeljbqk.setFirstDate(a.trim());
			returnMsg=ibasicsituationservice.queryBasicSituation(modeljbqk);
			req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,false));
			req.setAttribute("firstDateJbb", firstDateJbb);
			req.setAttribute("reportType", reportType);
			return new ModelAndView("ca/cacore/rsss/insuranceReport/"+reportType+"Report");
		}else if("YWHZB".equals(reportType)){//保险代理机构业务汇总表
			String a=firstDateJbb+"-01";
			IBusinessReportModel modelywhzb=new BusinessReportModel();
			modelywhzb.setStatistic_month(a.trim());
			modelywhzb.setBranch_id(branch_id);
			returnMsg = businessReportcService.queryBusinessReport(modelywhzb);
			req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,true));
			req.setAttribute("firstDateJbb", firstDateJbb);
			//req.setAttribute("branch_id", branch_id);
			//req.setAttribute("branch_name",branch_name);
			return new ModelAndView("ca/cacore/rsss/insuranceReport/"+reportType+"Report");
		}else if("XLQKBB".equals(reportType)){//营运人员效力情况报表
			String a=firstDateJbb+"-01";
			IYYPersonReportVOModel model=new YYPersonReportVOModel();
			model.setStatistic_month(a.trim());
			model.setPageCount(ActionHelper.getPage(req));
			returnMsg = iyypersonreportservice.queryYYPersonReport(model);
			req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,false));
			req.setAttribute("firstDateJbb", firstDateJbb);
			return new ModelAndView("ca/cacore/rsss/insuranceReport/"+reportType+"Report");
		}else if("QTFBB".equals(reportType)){//客户群体分布表
			ICustomeGDVOModel model=new CustomeGDVOModel();
			model.setStatistic_month(firstDateJbb);
			returnMsg = icustomegdservice.queryCustomeGD(model);
			req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,false));
			req.setAttribute("firstDateJbb", firstDateJbb);
			return new ModelAndView("ca/cacore/rsss/insuranceReport/"+reportType+"Report");
		}else if("RYDZDA".equals(reportType)){//保险代理机构人员电子档案
			IInsAgenPersonReportModel model=new InsAgenPersonReportModel();
			model.setBranch_id(branch_id);
			returnMsg = iinsagenpersonreportservice.queryInsAgenPersonReport(model);
			req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,true));
			//req.setAttribute("branch_id", branch_id);
			//req.setAttribute("branch_name",branch_name);
			return new ModelAndView("ca/cacore/rsss/insuranceReport/"+reportType+"Report");
		}else if("XYDZDA".equals(reportType)){//保险代理机构业务协议电子档案
			IInsBusProReportModel modeldzda=new InsBusProReportModel();
			modeldzda.setReportType(reportType);
			modeldzda.setBranch_id(branch_id);
			returnMsg=iinsbusproreportservice.queryInsBusProReport(modeldzda);
			req.setAttribute("rmHelper",new ReturnMsgHelper(req,returnMsg,true));
			//req.setAttribute("branch_id", branch_id);
			//req.setAttribute("branch_name",branch_name);
			return new ModelAndView("ca/cacore/rsss/insuranceReport/"+reportType+"Report");
		}
		return null;
	}
	
	/**
	 * @author liuhaiyue---模板写入下载
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 * @description:营运人员效力情况表导出
	 */
	@RequestMapping("/rsss/YYPersonReport/exportYYPersonReport.do")
	public ModelAndView exportPerformanceExport(HttpServletRequest req,
			HttpServletResponse res) {
		String firstDateJbb=ActionHelper.getNullToStr(req.getParameter("firstDateJbb"));
		String a=firstDateJbb+"-01";
		IYYPersonReportVOModel model=new YYPersonReportVOModel();
		model.setStatistic_month(a);
		
		List<IYYPersonReportVOModel> list = iyypersonreportservice.exportYYPersonReport(model);
		
		WritableWorkbook wwb=null;  
	    WritableSheet wws=null;  
	    FileOutputStream out =null;  
	    //获取要读取的EXCEL表格模板  
	    String downloadPath = StaticProperties.getProperty("downloadPath");//模板路径
	    String file_name="prsonnelReportEffectiveness.xls";
	    File is = new File(downloadPath+file_name);  //拼接
	    String filename=downloadPath;
	    Calendar print_date = Calendar.getInstance();  
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       //非常关键的！！！ 
	    java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.text.SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	    java.text.SimpleDateFormat sdff = new SimpleDateFormat("yyyy年MM月");
	    //写入到新的表格里  
	    File f=new File(filename,"营运人员效力情况报表_"+sdf1.format(print_date.getTime())+".xls");  
	    try {  
	        //创建新文件  
	        f.createNewFile();  
	        out = new FileOutputStream(f);  
	        //获取工作簿对象     
	        Workbook wb = Workbook.getWorkbook(is);  
	        // 创建可写入的工作簿对象    
	        wwb = Workbook.createWorkbook(out, wb);  
	        //根据工作表名获取WritableSheet对象  
	        wws=wwb.getSheet("营运人员效力情况报表");  
	//        //设置样式  
	//        NumberFormat nf = new NumberFormat("#00");
	        WritableCellFormat wcf=new WritableCellFormat(); 
	        WritableCellFormat wcf1=new WritableCellFormat();
	        WritableCellFormat wcf2=new WritableCellFormat();
	//        //设置样式  e.Row.Cells[9].Attributes.Add("class", "text");
	        wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf2.setBorder(null, jxl.format.BorderLineStyle.THIN);
	        WritableFont wf = new WritableFont(WritableFont.TIMES); 
	        wf.setPointSize(10);
	        
	        wcf2.setFont(wf);
	        wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf2.setWrap(true);
	        
	        wcf.setFont(wf);
	        wcf.setAlignment(Alignment.LEFT);
	        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf.setWrap(true);
	        
	        wcf1.setFont(wf);
	        wcf1.setWrap(true);
	        wcf1.setAlignment(Alignment.CENTRE);
	        wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
	        
	        Label label=null;  
	        label = new Label(0,2,"报送任务名称：保险专业中介机构月报报表("+firstDateJbb+")",wcf);//添加代理人姓名
	        wws.addCell(label);
	        
	        label = new Label(4,2,"报送机构：中介有限公司",wcf);
	        wws.addCell(label);
	        
	        for (int i=0 ;i<list.size();i++){
	        	IYYPersonReportVOModel vo = list.get(i);
	        	//序号
	        	label = new Label(0,i+5,String.valueOf(ActionHelper.toStr(i + 1)),wcf1);
		        wws.addCell(label);
		        String Branch_name = String.valueOf(vo.getBranch_name());
		        if("null".equals(Branch_name)){
		        	label = new Label(1,i+5," ",wcf1);
		        	wws.addCell(label);

		        }else{
		        	
		        	label = new Label(1,i+5,Branch_name,wcf1);
		        	wws.addCell(label);
		        }
		        
		        String NewPolicyNum = String.valueOf(vo.getNewPolicyNum());
		        if("null".equals(NewPolicyNum)){
		        	label = new Label(2,i+5," ",wcf1);
		        	wws.addCell(label);

		        }else{
		        	
		        	label = new Label(2,i+5,NewPolicyNum,wcf1);
		        	wws.addCell(label);
		        }
		        
		        label = new Label(3,i+5,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(4,i+5,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(5,i+5,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(6,i+5,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(7,i+5,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(8,i+5,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(9,i+5,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
		        String XZLPJNum = String.valueOf(vo.getXZLPJNum());
		        if("null".equals(XZLPJNum)){
		        	label = new Label(10,i+5," ",wcf1);
		        	wws.addCell(label);

		        }else{
		        	
		        	label = new Label(10,i+5,XZLPJNum,wcf1);
		        	wws.addCell(label);
		        }
		        	
		        
		        label = new Label(11,i+5,String.valueOf("0"),wcf1);
		        wws.addCell(label);
	        }
	        wwb.write();  
	      }catch (Exception e) {  
	        e.printStackTrace();  
	    } finally{  
	        //关闭流  
	        try {  
	            wwb.close();  
	            out.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    } 
	    /*
	     * 使用虚拟目录下载方法备份
	     * downloadZipNewExcel  这个是新添的方法，里面添加了下载之后就删除掉方法
	     * */
	      File file = new File(downloadPath+"营运人员效力情况报表_"+sdf1.format(print_date.getTime())+".xls");  //拼接
	      FileDownLoadUtil.downloadZipNewExcel(file,"营运人员效力情况报表_"+sdf1.format(print_date.getTime())+".xls",res);//调用下载原格式的方法
		/**
		 * 
		HashMap<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i< list.size(); i++) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			IYYPersonReportVOModel vo = list.get(i);
			hashMap.put("列" + 0, ActionHelper.toStr(i + 1)); // 序号
			hashMap.put("列" + 1, vo.getBranch_name()); // 支公司
			hashMap.put("列" + 2, vo.getNewPolicyNum()); // 新保单件数
			hashMap.put("列" + 3, "0"); // 业务问题件数
			hashMap.put("列" + 4, "0"); // 结案问题件数
			hashMap.put("列" + 5, "0"); // 问题件率
			hashMap.put("列" + 6, "0"); // 问题件结案率
			hashMap.put("列" + 7, "0"); // 投诉件数
			hashMap.put("列" + 8, "0"); // 投诉结案件数
			hashMap.put("列" + 9, "0"); // 投诉结案率
			hashMap.put("列" + 10, vo.getXZLPJNum()); // 协助理赔件数
			hashMap.put("列" + 11, "0"); // 协助保全变更件数
	
			sheetList.add(hashMap);
			} 
		// 合并表头
		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		// 合并表头
		excelMap.put("营运人员效力情况报表", sheetList);
		
		head.put("列" + 0, "序号");
		head.put("列" + 1, "机构");
		head.put("列" + 2, "新保单件数");
		head.put("列" + 3, "业务问题件数");
		head.put("列" + 4, "结案问题件数");
		head.put("列" + 5, "问题件率");
		head.put("列" + 6, "问题件结案率");
		head.put("列" + 7, "投诉件数");
		head.put("列" + 8, "投诉结案件数");
		head.put("列" + 9, "投诉结案率");
		head.put("列" + 10, "协助理赔件数");
		head.put("列" + 11, "协助保全变更件数");


		// 设置每个sheet页的表头
		List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
		sheetHead.add(head);
		// 设置多sheet页
		Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
		map.put("营运人员效力情况报表", sheetHead);
		ExcelWrite write = new ExcelWrite();
		//设置response 这样就可以前台弹出框进行下载了
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		write.setResponse(res);
		write.setSheetHead(map);
		write.setMergeCell(true);
		// 设置数据，key需要跟sheet页的key相同，值为需要写的数据
		write.writeExcel(excelMap);
		 */
	return null ;
	}
	/**
	 * @author liuhaiyue--模板写入
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 * @description:保险代理机构业务汇总表导出
	 */
	@RequestMapping("/rsss/BusinessReport/exportBusinessReport.do")
	public ModelAndView exportBusinessReport(HttpServletRequest req,
			HttpServletResponse res) {
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//机构id
		String branch_name=ActionHelper.getNullToStr(req.getParameter("branch_name"));
		String firstDateJbb=ActionHelper.getNullToStr(req.getParameter("firstDateJbb"));
		String statistic_year = ActionHelper.getNullToStr(req.getParameter("statistic_year"));//	统计年份
		String statistic_month = ActionHelper.getNullToStr(req.getParameter("statistic_month"));//	统计月份
		String statistic_time = statistic_year+statistic_month;
		String a=firstDateJbb+"-01";
		
		IBusinessReportModel model = new BusinessReportModel();
		model.setBranch_id(branch_id);
		model.setStatistic_month(a);
		model.setStatistic_time(statistic_time);
		ReturnMsg returnMsg = new ReturnMsg();
		req.setAttribute("rmHelper",
				new ReturnMsgHelper(req, returnMsg, true));
		List<IBusinessReportModel> list = businessReportcService.exportBusinessReport(model);
		WritableWorkbook wwb=null;  
	    WritableSheet wws=null;  
	    FileOutputStream out =null;  
	    //获取要读取的EXCEL表格模板  
	    String downloadPath = StaticProperties.getProperty("downloadPath");//模板路径
	    String file_name="insuranceAgencyBusinessSummary.xls";
	    File is = new File(downloadPath+file_name);  //拼接
	    String filename=downloadPath;
	    Calendar print_date = Calendar.getInstance();  
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       //非常关键的！！！ 
	    java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.text.SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	    java.text.SimpleDateFormat sdff = new SimpleDateFormat("yyyy年MM月");
	    //写入到新的表格里  
	    File f=new File(filename,"保险代理机构业务汇总表_"+sdf1.format(print_date.getTime())+".xls");  
	    try {  
	        //创建新文件  
	        f.createNewFile();  
	        out = new FileOutputStream(f);  
	        //获取工作簿对象     
	        Workbook wb = Workbook.getWorkbook(is);  
	        // 创建可写入的工作簿对象    
	        wwb = Workbook.createWorkbook(out, wb);  
	        //根据工作表名获取WritableSheet对象  
	        wws=wwb.getSheet("保险代理机构业务汇总表");  
	//        //设置样式  
	//        NumberFormat nf = new NumberFormat("#00");
	        WritableCellFormat wcf=new WritableCellFormat(); 
	        WritableCellFormat wcf1=new WritableCellFormat();
	        WritableCellFormat wcf2=new WritableCellFormat();
	//        //设置样式  e.Row.Cells[9].Attributes.Add("class", "text");
	        wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf2.setBorder(null, jxl.format.BorderLineStyle.THIN);
	        WritableFont wf = new WritableFont(WritableFont.TIMES); 
	        wf.setPointSize(10);
	        
	        
	        wcf2.setFont(wf);
	        wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf2.setWrap(true);
	        
	        wcf.setFont(wf);
	        wcf.setAlignment(Alignment.LEFT);
	        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf.setWrap(true);
	        
	        wcf1.setFont(wf);
	        wcf1.setWrap(true);
	        wcf1.setAlignment(Alignment.CENTRE);
	        wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
	        
	        Label label=null;  
	        label = new Label(0,2,"报送任务名称：保险专业中介机构月报报表("+firstDateJbb+")",wcf);//添加代理人姓名
	        wws.addCell(label);
	        
	        label = new Label(6,2,"报送机构："+branch_name,wcf);
	        wws.addCell(label);
	        
	        for (int i=0 ;i<list.size();i++){
	        	IBusinessReportModel vo = list.get(i);
	        	//序号
	        	label = new Label(0,i+6,String.valueOf(ActionHelper.toStr(i + 1)),wcf1);
		        wws.addCell(label);
		        String branch_name_ = String.valueOf(vo.getBranch_name().toString());
		        if("null".equals(branch_name_)){
		        	label = new Label(1,i+6,"",wcf1);
			        wws.addCell(label);
		        }else{
		        	
		        	label = new Label(1,i+6,branch_name_,wcf1);
		        	wws.addCell(label);
		        }
		        String person_num = String.valueOf(vo.getPerson_num().toString());
		        if("null".equals(person_num)){
		        	label = new Label(2,i+6,person_num,wcf1);
			        wws.addCell(label);
		        }else{
		        	
		        	label = new Label(2,i+6,person_num,wcf1);
		        	wws.addCell(label);
		        }
		        
		        String person_com = String.valueOf(vo.getPerson_com().toString());
		        if("null".equals(person_com)){
		        	label = new Label(3,i+6,"",wcf1);
			        wws.addCell(label);
		        }else{
		        	
		        	label = new Label(3,i+6,person_com,wcf1);
		        	wws.addCell(label);
		        }
		        
		        String period_num = String.valueOf(vo.getPeriod_num().toString());
		        if("null".equals(period_num)){
		        	
		        	label = new Label(4,i+6,"",wcf1);
		        	wws.addCell(label);
		        }else{
		        	label = new Label(4,i+6,period_num,wcf1);
		        	wws.addCell(label);

		        }
		        
		        String total_num = String.valueOf(vo.getTotal_num().toString());
		        if("null".equals(total_num)){
		        	
		        	label = new Label(5,i+6," ",wcf1);
			        wws.addCell(label);
		        }else{
		        	label = new Label(5,i+6,total_num,wcf1);
			        wws.addCell(label);

		        }
		        
		        String period_prem = String.valueOf(vo.getPeriod_prem().toString());
		        if("null".equals(period_prem)){
			        label = new Label(6,i+6," ",wcf1);
			        wws.addCell(label);
		        }else{
		        	label = new Label(6,i+6,period_prem,wcf1);
			        wws.addCell(label);

		        }
		        
		        String total_prem = String.valueOf(vo.getTotal_prem().toString());
		        if("null".equals(total_prem)){
		        	label = new Label(7,i+6,"",wcf1);
			        wws.addCell(label);
		        }else{
		        	label = new Label(7,i+6,total_prem,wcf1);
			        wws.addCell(label);
		        }
		        
		        label = new Label(8,i+6,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(9,i+6,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
		        String period_fee = String.valueOf(vo.getPeriod_fee().toString());
		        if("null".equals(period_fee)){
		        	label = new Label(10,i+6," ",wcf1);
			        wws.addCell(label);
		        }else{
		        	label = new Label(10,i+6,period_fee,wcf1);
			        wws.addCell(label);
		        }
		        
		        String total_fee = String.valueOf(vo.getTotal_fee().toString());
		        if("null".equals(total_fee)){
		        	label = new Label(11,i+6," ",wcf1);
			        wws.addCell(label);
		        }else{
		        	
		        	label = new Label(11,i+6,total_fee,wcf1);
		        	wws.addCell(label);
		        }
		        
		        label = new Label(12,i+6,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(13,i+6,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(14,i+6,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(15,i+6,String.valueOf("0"),wcf1);
		        wws.addCell(label);
		        
	        }
	        wwb.write();  
	      }catch (Exception e) {  
	        e.printStackTrace();  
	    } finally{  
	        //关闭流  
	        try {  
	            wwb.close();  
	            out.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    } 
	    /*
	     * 使用虚拟目录下载方法备份
	     * downloadZipNewExcel  这个是新添的方法，里面添加了下载之后就删除掉方法
	     * */
	      File file = new File(downloadPath+"保险代理机构业务汇总表_"+sdf1.format(print_date.getTime())+".xls");  //拼接
	      FileDownLoadUtil.downloadZipNewExcel(file,"保险代理机构业务汇总表_"+sdf1.format(print_date.getTime())+".xls",res);//调用下载原格式的方法
		/*Integer  person_num=0;//公司人数
		 double  person_com=0;//同比(+/-)
		 Integer  period_num=0;//保单本期数量
		 Integer  total_num = 0;//保单累计
		 double  period_prem=0;//本期保费金
		 double  total_prem=0;//累计保费金额
		 double  period_unreceived_prem=0;//本期未解付保费
		 double  total_unreceived_prem=0;//累计未解付保费
		 double  period_fee=0;//本期代理手续费
		 double  total_fee=0;////累计代理手续费
		 double  period_pro=0;//本期利润
		 double  total_pro=0;//累计利润
		 double  registered_capital=0;//注册资本
		 double  total_assets=0;//资产总额
		 String product_name = model.getProduct_name();
		for(IBusinessReportModel modelsum:list)
		{	
			person_num = person_num+ Integer.parseInt(modelsum.getPerson_num().toString());//公司人数
			person_com= person_com+Double.parseDouble(modelsum.getPerson_com().toString());//同比(+/-)
			period_num=period_num+Integer.parseInt(modelsum.getPeriod_num().toString());//保单本期数量
			total_num=total_num+Integer.parseInt(modelsum.getTotal_num().toString());//保单累计
			period_prem=period_prem+Double.parseDouble(modelsum.getPeriod_prem().toString());//本期保费金
			total_prem= total_prem+Double.parseDouble( modelsum.getTotal_prem().toString());//累计保费金额
			period_unreceived_prem=period_unreceived_prem+Double.parseDouble(modelsum.getPeriod_unreceived_prem().toString());//本期未解付保费
			total_unreceived_prem=total_unreceived_prem+Double.parseDouble(modelsum.getTotal_unreceived_prem().toString());//累计未解付保费
			period_fee=period_fee+Double.parseDouble(modelsum.getPeriod_fee().toString());//本期代理手续费
			total_fee= total_fee+Double.parseDouble(modelsum.getTotal_fee().toString());//累计代理手续费
			period_pro=period_pro+Double.parseDouble(modelsum.getPeriod_pro().toString());//本期利润
			total_pro=total_pro+Double.parseDouble(modelsum.getTotal_pro().toString());//累计利润
			registered_capital=registered_capital+Double.parseDouble(modelsum.getRegistered_capital().toString());//注册资本
			total_assets=total_assets+Double.parseDouble(modelsum.getTotal_assets().toString());//累计利润
		}
		
		BusinessReportModel mo=new BusinessReportModel();
		mo.setProduct_name("合计");
		mo.setPerson_num(person_num);
		mo.setPerson_com(person_com);
		mo.setPeriod_num(period_num);
		mo.setTotal_num(total_num);
		mo.setPeriod_prem(period_prem);
		mo.setTotal_prem(total_prem);
		mo.setPeriod_unreceived_prem(period_unreceived_prem);
		mo.setTotal_unreceived_prem(total_unreceived_prem);
		mo.setPeriod_fee(period_fee);
		mo.setTotal_fee(total_fee);
		mo.setPeriod_pro(period_pro);
		mo.setTotal_pro(total_pro);
		mo.setRegistered_capital(registered_capital);
		mo.setTotal_assets(total_assets);
		list.add(mo);*/
		/**
		 * 
		HashMap<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i< list.size(); i++) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			IBusinessReportModel vo = list.get(i);
			//hashMap.put("列" + 0, ); // 行次
			hashMap.put("列" + 1, ActionHelper.toStr(i + 1)); // 序号
			hashMap.put("列" + 2, vo.getBranch_name().toString()); // 分支机构(个)
			hashMap.put("列" + 3, vo.getPerson_num().toString()); // 公司员工数(人/现有人数)
			hashMap.put("列" + 4, vo.getPerson_com().toString()); // 公司员工数(同比/+/-)
			hashMap.put("列" + 5, vo.getPeriod_num().toString()); // 保单本期数量
			hashMap.put("列" + 6, vo.getTotal_num().toString()); // 保单累计
			hashMap.put("列" + 7, vo.getPeriod_prem().toString()); // 本期保费金额
			hashMap.put("列" + 8, vo.getTotal_prem().toString()); // 累计保费金额
			hashMap.put("列" + 9, "0"); // 本期未解付保费
			hashMap.put("列" + 10, "0"); // 累计未解付保费
			hashMap.put("列" + 11, vo.getPeriod_fee().toString()); // 本期代理手续费
			hashMap.put("列" + 12, vo.getTotal_fee().toString()); // 累计代理手续费
			hashMap.put("列" + 13, "0"); // 本期利润
			hashMap.put("列" + 14, "0"); // 本期累计
			hashMap.put("列" + 15, "0"); // 注册资本
			hashMap.put("列" + 16, "0"); // 资产总额

			sheetList.add(hashMap);
			} 
		// 合并表头
		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> headrow = new LinkedHashMap<String, String>();
		// 合并表头
		excelMap.put("保险代理机构业务汇总表", sheetList);
		
		head.put("列" + 1, "序号");
		//head.put("列" + 2, "行次");
		head.put("列" + 2, "分支机构(个)");
		head.put("列" + 3, "公司员工人数(人)");
		head.put("列" + 4, "");
		head.put("列" + 5, "保单件数(件)");
		head.put("列" + 6, "");
		head.put("列" + 7, "保费金额");
		head.put("列" + 8, "");
		head.put("列" + 9, "未解付保费");
		head.put("列" + 10, "");
		head.put("列" + 11, "代理手续费");
		head.put("列" + 12, "");
		head.put("列" + 13, "利润");
		head.put("列" + 14, "");
		head.put("列" + 15, "注册资本");
		head.put("列" + 16, "资产总额");

		
		headrow.put("列" + 1, "序号");
		//headrow.put("列" + 2, "行次");
		headrow.put("列" + 2, "分支机构(个)");
		headrow.put("列" + 3, "现有人数");
		headrow.put("列" + 4, "同比(+/-)");
		headrow.put("列" + 5, "本期");
		headrow.put("列" + 6, "累计");
		headrow.put("列" + 7, "本期");
		headrow.put("列" + 8, "累计");
		headrow.put("列" + 9, "本期");
		headrow.put("列" + 10, "累计");
		headrow.put("列" + 11, "本期");
		headrow.put("列" + 12, "累计");
		headrow.put("列" + 13, "本期");
		headrow.put("列" + 14, "累计");
		headrow.put("列" + 15, "注册资本");
		headrow.put("列" + 16, "资产总额");


		// 设置每个sheet页的表头
		List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
		sheetHead.add(head);
		sheetHead.add(headrow);
		// 设置多sheet页
		Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
		map.put("保险代理机构业务汇总表", sheetHead);
		ExcelWrite write = new ExcelWrite();
		//设置response 这样就可以前台弹出框进行下载了
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		write.setResponse(res);
		write.setSheetHead(map);
		write.setMergeCell(true);
		// 设置数据，key需要跟sheet页的key相同，值为需要写的数据
		write.writeExcel(excelMap);
		 */
	return null ;
	}
	/**
	 * @author liuhaiyue--模板写入下载
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 * @description:客户群体分布表导出
	 */
	@RequestMapping("/rsss/Report/exportCustomeGD.do")
	public ModelAndView exportCustomeGD(HttpServletRequest req,
			HttpServletResponse res) {
		String firstDateJbb=ActionHelper.getNullToStr(req.getParameter("firstDateJbb"));
		String branch_name=ActionHelper.getNullToStr(req.getParameter("branch_name"));
		ICustomeGDVOModel model=new CustomeGDVOModel();
		model.setStatistic_month(firstDateJbb);
		
		List<ICustomeGDVOModel> list = icustomegdservice.exportCustomeGD(model);
		WritableWorkbook wwb=null;  
	    WritableSheet wws=null;  
	    FileOutputStream out =null;  
	    //获取要读取的EXCEL表格模板  
	    String downloadPath = StaticProperties.getProperty("downloadPath");//模板路径
	    String file_name="collectiveDistributionStatements.xls";
	    File is = new File(downloadPath+file_name);  //拼接
	    String filename=downloadPath;
	    Calendar print_date = Calendar.getInstance();  
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       //非常关键的！！！ 
	    java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.text.SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	    java.text.SimpleDateFormat sdff = new SimpleDateFormat("yyyy年MM月");
	    
	    //写入到新的表格里  
	    File f=new File(filename,"客户群体分布表_"+sdf1.format(print_date.getTime())+".xls");  
	    try {  
	        //创建新文件  
	        f.createNewFile();  
	        out = new FileOutputStream(f);  
	        //获取工作簿对象     
	        Workbook wb = Workbook.getWorkbook(is);  
	        // 创建可写入的工作簿对象    
	        wwb = Workbook.createWorkbook(out, wb);  
	        //根据工作表名获取WritableSheet对象  
	        wws=wwb.getSheet("客户群体分布表");  
	//        //设置样式  
	        
	//        NumberFormat nf = new NumberFormat("#00");
	        WritableCellFormat wcf=new WritableCellFormat(); 
	        WritableCellFormat wcf1=new WritableCellFormat();
	        WritableCellFormat wcf2=new WritableCellFormat();
	//        //设置样式  e.Row.Cells[9].Attributes.Add("class", "text");
	        wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf2.setBorder(null, jxl.format.BorderLineStyle.THIN);
	        WritableFont wf = new WritableFont(WritableFont.TIMES); 
	        wf.setPointSize(10);
	        
	        
	        wcf2.setFont(wf);
	        wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf2.setWrap(true);
	        
	        wcf.setFont(wf);
	        wcf1.setAlignment(Alignment.RIGHT);
	        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf.setWrap(true);
	        
	        wcf1.setFont(wf);
	        wcf1.setWrap(true);
	        wcf1.setAlignment(Alignment.CENTRE);
	        wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
	        
	        Label label=null;  
	        label = new Label(0,2,"报送任务名称：保险专业中介机构月报报表("+firstDateJbb+")",wcf);//添加代理人姓名
	        wws.addCell(label);
	        

	        label = new Label(5,2,"报送机构：保险中介有限公司",wcf);

	        wws.addCell(label);
	        
	        for (int i=0 ;i<list.size();i++){
	        	ICustomeGDVOModel vo = list.get(i);
	        	//序号
	        	label = new Label(0,i+5,String.valueOf(ActionHelper.toStr(i + 1)),wcf1);
		        wws.addCell(label);
		        //年龄
		        String age =String.valueOf(vo.getAge());
		        if("null".equals(age)){
		        	label = new Label(1,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else {
		        	label = new Label(1,i+5,age,wcf1);
		        	wws.addCell(label);
		        	
		        }
		        
		        //年收入
		        String incom =String.valueOf(vo.getIncom());
		        if("null".equals(incom)){
		        	label = new Label(2,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else {
		        	label = new Label(2,i+5,incom,wcf1);
		        	wws.addCell(label);
		        	
		        }
		        
		        //婚姻状况
		        String marry =String.valueOf(vo.getMarry());
		        if("null".equals(marry)){
		        	label = new Label(3,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else {
		        	label = new Label(3,i+5,marry,wcf1);
		        	wws.addCell(label);
		        	
		        }
		        //新单件数
		        String pronum =String.valueOf(vo.getPronum());
		        if("null".equals(pronum)){
		        	label = new Label(4,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else {
		        	label = new Label(4,i+5,pronum,wcf1);
		        	wws.addCell(label);
		        	
		        }
		        //新单保费累计
		        String premiun =String.valueOf(vo.getPremiun());
		        if("null".equals(premiun)){
		        	label = new Label(5,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else {
		        	label = new Label(5,i+5,premiun,wcf1);
		        	wws.addCell(label);
		        	
		        }
		        
		        //新单均保费
		        String preavg =String.valueOf(vo.getPreavg());
		        if("null".equals(preavg)){
		        	label = new Label(6,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else {
		        	label = new Label(6,i+5,preavg,wcf1);
		        	wws.addCell(label);
		        	
		        }
		        
		        
	        }
	        wwb.write();  
	      }catch (Exception e) {  
	        e.printStackTrace();  
	    } finally{  
	        //关闭流  
	        try {  
	            wwb.close();  
	            out.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    } 
	    /*
	     * 使用虚拟目录下载方法备份
	     * downloadZipNewExcel  这个是新添的方法，里面添加了下载之后就删除掉方法
	     * */
	     
	      File file = new File(downloadPath+"客户群体分布表_"+sdf1.format(print_date.getTime())+".xls");  //拼接
	      FileDownLoadUtil.downloadZipNewExcel(file,"客户群体分布表_"+sdf1.format(print_date.getTime())+".xls",res);//调用下载原格式的方法
		/**
		HashMap<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i< list.size(); i++) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			ICustomeGDVOModel vo = list.get(i);
			hashMap.put("列" + 0, ActionHelper.toStr(i + 1)); // 序号
			hashMap.put("列" + 1, vo.getAge()); // 年龄
			hashMap.put("列" + 2, vo.getIncom()); // 年收入
			hashMap.put("列" + 3, vo.getMarry()); // 婚姻状况
			hashMap.put("列" + 4, vo.getPronum()); // 新单件数
			hashMap.put("列" + 5, vo.getPremiun()); // 新单保费累计
			hashMap.put("列" + 6, vo.getPreavg()); // 新单均保费
	
			sheetList.add(hashMap);
			} 
		// 合并表头
		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		// 合并表头
		excelMap.put("客户群体分布表", sheetList);
		
		head.put("列" + 0, "序号");
		head.put("列" + 1, "年龄");
		head.put("列" + 2, "年收入");
		head.put("列" + 3, "婚姻状况");
		head.put("列" + 4, "新单件数");
		head.put("列" + 5, "新单保费累计");
		head.put("列" + 6, "新单均保费");


		// 设置每个sheet页的表头
		List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
		sheetHead.add(head);
		// 设置多sheet页
		Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
		map.put("客户群体分布表", sheetHead);
		ExcelWrite write = new ExcelWrite();
		//设置response 这样就可以前台弹出框进行下载了
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		write.setResponse(res);
		write.setSheetHead(map);
		write.setMergeCell(true);
		// 设置数据，key需要跟sheet页的key相同，值为需要写的数据
		write.writeExcel(excelMap);
		 * 
		 */
	return null ;
	}
	
	/**
	 * 
	 * @author liuhaiyue--修改下载模板问题
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 *             ModelAndView
	 * @description:产险公司业务报表导出
	 */
	@RequestMapping("/rsss/InsCompanyBus/exportInsCompanyBus.do")
	public ModelAndView exportInsCompanyBus(HttpServletRequest req,
			HttpServletResponse res) {
		String reportType=ActionHelper.getNullToStr(req.getParameter("reportType"));
		String firstDate=ActionHelper.getNullToStr(req.getParameter("firstDate"));
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));
		String branch_name=ActionHelper.getNullToStr(req.getParameter("branch_name"));
		String a=firstDate+"-01";
	    Date c=DateUtil.string2Date(a.trim());
		
		IInsCompanyBusModel model=new InsCompanyBusModel();
		model.setFirstDate(c);
		model.setReportType(reportType.trim());
		model.setBranch_id(branch_id.trim());
		ReturnMsg returnMsg =new ReturnMsg();
		if("YWBBCX".equals(reportType)){//保险代理机构业务报表-产险
			
		}else if("YWBBRSX".equals(reportType)){//保险代理机构业务报表-人身险
			
		}else if("JBQKB".equals(reportType)){//基本情况表
			
		}else if("YWHZB".equals(reportType)){//保险代理机构业务汇总表
			
		}else if("XLQKBB".equals(reportType)){//营运人员效力情况报表
			
		}else if("QTFBB".equals(reportType)){//客户群体分布表
			
		}else if("RYDZDA".equals(reportType)){//保险代理机构人员电子档案
			
		}else if("XYDZDA".equals(reportType)){//保险代理机构业务协议电子档案
			
		}
		req.setAttribute("rmHelper",
				new ReturnMsgHelper(req, returnMsg,true));
		List<IInsCompanyBusModel> list = iinscompanybusservice.exportInsCompanyBus(model);
		WritableWorkbook wwb=null;  
	    WritableSheet wws=null;  
	    FileOutputStream out =null;  
	    //获取要读取的EXCEL表格模板  
	    String downloadPath = StaticProperties.getProperty("downloadPath");//模板路径
	    String file_name="propertyInsurance.xls";
	    File is = new File(downloadPath+file_name);  //拼接
	    String filename=downloadPath;
	    Calendar print_date = Calendar.getInstance();  
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       //非常关键的！！！ 
	    java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.text.SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	    java.text.SimpleDateFormat sdff = new SimpleDateFormat("yyyy年MM月");
	    
	    //写入到新的表格里  
	    File f=new File(filename,"产险公司业务报表_"+sdf1.format(print_date.getTime())+".xls");  
	    try {  
	        //创建新文件  
	        f.createNewFile();  
	        out = new FileOutputStream(f);  
	        //获取工作簿对象     
	        Workbook wb = Workbook.getWorkbook(is);  
	        // 创建可写入的工作簿对象    
	        wwb = Workbook.createWorkbook(out, wb);  
	        //根据工作表名获取WritableSheet对象  
	        wws=wwb.getSheet("产险公司业务报表");  
	//        //设置样式  
	        
	//        NumberFormat nf = new NumberFormat("#00");
	        WritableCellFormat wcf=new WritableCellFormat(); 
	        WritableCellFormat wcf1=new WritableCellFormat();
	        WritableCellFormat wcf2=new WritableCellFormat();
	//        //设置样式  e.Row.Cells[9].Attributes.Add("class", "text");
	        wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf2.setBorder(null, jxl.format.BorderLineStyle.THIN);
	        WritableFont wf = new WritableFont(WritableFont.TIMES); 
	        wf.setPointSize(10);
	        
	        
	        wcf2.setFont(wf);
	        wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf2.setWrap(true);
	        
	        wcf.setFont(wf);
	        wcf1.setAlignment(Alignment.RIGHT);
	        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf.setWrap(true);
	        
	        wcf1.setFont(wf);
	        wcf1.setWrap(true);
	        wcf1.setAlignment(Alignment.CENTRE);
	        wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
	        
	        Label label=null;  
	        label = new Label(0,2,"报送任务名称：保险专业中介机构月报报表("+firstDate+")",wcf);//添加代理人姓名
	        wws.addCell(label);
	        label = new Label(2,2,"报送机构："+branch_name,wcf);
	        wws.addCell(label);
	        for (int i=0 ;i<list.size();i++){
	        	IInsCompanyBusModel vo = list.get(i);
	        	label = new Label(2,i+6,String.valueOf(vo.getPeriod_prem()),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(3,i+6,String.valueOf(vo.getAmount()),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(4,i+6,String.valueOf(vo.getAgent()),wcf1);
		        wws.addCell(label);
		        
	        }
	        wwb.write();  
	      }catch (Exception e) {  
	        e.printStackTrace();  
	    } finally{  
	        //关闭流  
	        try {  
	            wwb.close();  
	            out.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    } 
	    /*
	     * 使用虚拟目录下载方法备份
	     * downloadZipNewExcel  这个是新添的方法，里面添加了下载之后就删除掉方法
	     * */
	      File file = new File(downloadPath+"产险公司业务报表_"+sdf1.format(print_date.getTime())+".xls");  //拼接
	      FileDownLoadUtil.downloadZipNewExcel(file,"产险公司业务报表_"+sdf1.format(print_date.getTime())+".xls",res);//调用下载原格式的方法
	      return null ;

	}
	/**
	 * @author liuhaiyue--模板写入下载
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 * ModelAndView
	 * @description:人身险公司业务报表导出
	 */
	@RequestMapping("/rsss/PersonalBusReport/exportPersonalBusReport.do")
	public ModelAndView exportPersonalBusReport(HttpServletRequest req,
			HttpServletResponse res) {
		String reportType=ActionHelper.getNullToStr(req.getParameter("reportType"));
		String firstDate=ActionHelper.getNullToStr(req.getParameter("firstDate"));
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));
		String branch_name=ActionHelper.getNullToStr(req.getParameter("branch_name"));
		String a=firstDate+"-01";
		Date c=DateUtil.string2Date(a);
		IPersonalBusReportModel model=new PersonalBusReportModel();
		model.setFirstDate(c);
		model.setReportType(reportType);
		model.setBranch_id(branch_id);
		List<IPersonalBusReportModel> list = ipersonalbusreportservice.exportPersonalBusReport(model);
		WritableWorkbook wwb=null;  
	    WritableSheet wws=null;  
	    FileOutputStream out =null;  
	    //获取要读取的EXCEL表格模板  
	    String downloadPath = StaticProperties.getProperty("downloadPath");//模板路径
	    String file_name="personalInsurance.xls";
	    File is = new File(downloadPath+file_name);  //拼接
	    String filename=downloadPath;
	    Calendar print_date = Calendar.getInstance();  
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       //非常关键的！！！ 
	    java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.text.SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	    //写入到新的表格里  
	    File f=new File(filename,"人身险公司业务报表_"+sdf1.format(print_date.getTime())+".xls");  
	    try {  
	        //创建新文件  
	        f.createNewFile();  
	        out = new FileOutputStream(f);  
	        //获取工作簿对象     
	        Workbook wb = Workbook.getWorkbook(is);  
	        // 创建可写入的工作簿对象    
	        wwb = Workbook.createWorkbook(out, wb);  
	        //根据工作表名获取WritableSheet对象  
	        wws=wwb.getSheet("人身险公司业务报表");  
	//        //设置样式  
	//        NumberFormat nf = new NumberFormat("#00");
	        WritableCellFormat wcf=new WritableCellFormat(); 
	        WritableCellFormat wcf1=new WritableCellFormat();
	        WritableCellFormat wcf2=new WritableCellFormat();
	//        设置样式  e.Row.Cells[9].Attributes.Add("class", "text");
	        wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf2.setBorder(null, jxl.format.BorderLineStyle.THIN);
	        WritableFont wf = new WritableFont(WritableFont.TIMES); 
	        wf.setPointSize(10);
	        
	        wcf2.setFont(wf);
	        wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf2.setWrap(true);
	        
	        wcf.setFont(wf);
	        wcf.setAlignment(Alignment.LEFT);
	        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf.setWrap(true);
	        
	        wcf1.setFont(wf);
	        wcf1.setWrap(true);
	        wcf1.setAlignment(Alignment.CENTRE);
	        wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
	        
	        Label label=null;  
	        label = new Label(0,2,"报送任务名称：保险专业中介机构月报报表("+firstDate+")",wcf);//添加代理人姓名
	        wws.addCell(label);
	        label = new Label(2,2,"报送机构："+branch_name,wcf);
	        wws.addCell(label);
	        for (int i=0 ;i<list.size();i++){
	        	IPersonalBusReportModel vo = list.get(i);
	        	label = new Label(2,i+6,String.valueOf(vo.getPeriod_prem_new()),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(3,i+6,String.valueOf(vo.getRene_period_prem()),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(4,i+6,String.valueOf(vo.getPayable_prem()),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(5,i+6,String.valueOf(vo.getNew_agent()),wcf1);
		        wws.addCell(label);
		        
		        label = new Label(6,i+6,String.valueOf(vo.getRene_agent()),wcf1);
		        wws.addCell(label);
		        
	        }
	        wwb.write();  
	      }catch (Exception e) {  
	        e.printStackTrace();  
	    } finally{  
	        //关闭流  
	        try {  
	            wwb.close();  
	            out.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    } 
	    /*
	     * 使用虚拟目录下载方法备份
	     * downloadZipNewExcel  这个是新添的方法，里面添加了下载之后就删除掉方法
	     * */
	     
	      File file = new File(downloadPath+"人身险公司业务报表_"+sdf1.format(print_date.getTime())+".xls");  //拼接
	      FileDownLoadUtil.downloadZipNewExcel(file,"人身险公司业务报表_"+sdf1.format(print_date.getTime())+".xls",res);//调用下载原格式的方法
	      return null ;
	}
	/**
	 * 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 * @description:保险代理机构人员电子档案导出
	 */
	@RequestMapping("/rsss/IInsBusProReport/exportIInsBusProReport.do")
	public ModelAndView exportIInsBusProReport(HttpServletRequest req,
			HttpServletResponse res) {
		String reportType=ActionHelper.getNullToStr(req.getParameter("reportType"));
		String firstDate=ActionHelper.getNullToStr(req.getParameter("firstDate"));
		String secondDate=ActionHelper.getNullToStr(req.getParameter("secondDate"));
		String statistic_year=ActionHelper.getNullToStr(req.getParameter("statistic_year"));
		String statistic_month=ActionHelper.getNullToStr(req.getParameter("statistic_month"));
		String branch_name =ActionHelper.getNullToStr(req.getParameter("branch_name"));
		IInsAgenPersonReportModel model=new InsAgenPersonReportModel();
		IInsAgenPersonReportModel modelinsa = new InsAgenPersonReportModel();
		model.setFirstDate(DateUtil.string2Date(firstDate));
		model.setReportType(reportType);
		model.setSecondDate(DateUtil.string2Date(secondDate));
		model.setStatistic_month(statistic_month);
		model.setStatistic_year(statistic_year);
		ReturnMsg returnMsg =new ReturnMsg();
		if("YWBBCX".equals(reportType)){//保险代理机构业务报表-产险
			
		}else if("YWBBRSX".equals(reportType)){//保险代理机构业务报表-人身险
			
		}else if("JBQKB".equals(reportType)){//基本情况表
			
		}else if("YWHZB".equals(reportType)){//保险代理机构业务汇总表
			
		}else if("XLQKBB".equals(reportType)){//营运人员效力情况报表
			
		}else if("QTFBB".equals(reportType)){//客户群体分布表
			
		}else if("RYDZDA".equals(reportType)){//保险代理机构人员电子档案
//			returnMsg = iinsagenpersonreportservice.queryInsAgenPersonReport(modelinsa);

		}else if("XYDZDA".equals(reportType)){//保险代理机构业务协议电子档案
			
		}
		req.setAttribute("rmHelper",
				new ReturnMsgHelper(req, returnMsg, true));
		List<IInsAgenPersonReportModel> list = iinsagenpersonreportservice.exportInsAgenPersonReport(model);
		
		HashMap<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i< list.size(); i++) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			IInsAgenPersonReportModel vo = list.get(i);
			hashMap.put("列" + 0, ActionHelper.toStr(i + 1)); // 序号
			hashMap.put("列" + 1, vo.getSales_id()); // 员工id
			hashMap.put("列" + 2, vo.getSales_name()); // 员工姓名
			hashMap.put("列" + 3, vo.getGender()); // 性别
			hashMap.put("列" + 4, vo.getIden_card()); //身份证 
			hashMap.put("列" + 5, vo.getPost()); // 职务
			hashMap.put("列" + 6, vo.getLabour_code()); // 劳动合同号
			hashMap.put("列" + 7, vo.getLabour_code_time()); // 劳动合同签订时间
			hashMap.put("列" + 8, vo.getCertificate()); // 资格证号
			hashMap.put("列" + 9, vo.getCertificate_date()); // 资格证获取时间
			hashMap.put("列" + 10, vo.getCertificate_no()); // 执业证号
			hashMap.put("列" + 11, vo.getDepartment_time()); // 离司时间

			sheetList.add(hashMap);
			} 
		// 合并表头
		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		// 合并表头
		excelMap.put("保险代理机构人员电子档案", sheetList);
		
		head.put("列" + 0, "序号");
		head.put("列" + 1, "员工编号");
		head.put("列" + 2, "姓名");
		head.put("列" + 3, "性别");
		head.put("列" + 4, "身份证号");
		head.put("列" + 5, "职务");
		head.put("列" + 6, "劳动合同号或代理合同号");
		head.put("列" + 7, "劳动合同号或代理合同号签订时间");
		head.put("列" + 8, "资格证号");
		head.put("列" + 9, "资格证获取时间");
		head.put("列" + 10, "执业证号");
		head.put("列" + 11, "离司时间");



		// 设置每个sheet页的表头
		List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
		sheetHead.add(head);
		// 设置多sheet页
		Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
		map.put("保险代理机构人员电子档案", sheetHead);
		ExcelWrite write = new ExcelWrite();
		//设置response 这样就可以前台弹出框进行下载了
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		write.setResponse(res);
		write.setSheetHead(map);
		write.setMergeCell(true);
		// 设置数据，key需要跟sheet页的key相同，值为需要写的数据
		write.writeExcel(excelMap);
	      return null ;
	}
	
	/**
	 * @author liuhaiyue--修改为模板写入下载
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 * @description:保险代理机构业务人员电子档案导出
	 */
	@RequestMapping("/rsss/InsAgenPersonReport/exportPersonReport.do")
	public ModelAndView exportInsAgenPersonReportQ(HttpServletRequest req,
			HttpServletResponse res) {
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));
		String branch_name =ActionHelper.getNullToStr(req.getParameter("branch_name"));
		IInsAgenPersonReportModel model=new InsAgenPersonReportModel();
		model.setBranch_id(branch_id);
		List<IInsAgenPersonReportModel> list = iinsagenpersonreportservice.exportInsAgenPersonReport(model);
		WritableWorkbook wwb=null;  
	    WritableSheet wws=null;  
	    FileOutputStream out =null;  
	    //获取要读取的EXCEL表格模板  
	    String downloadPath = StaticProperties.getProperty("downloadPath");//模板路径
	    String file_name="insurancePersonnelArchives.xls";
	    File is = new File(downloadPath+file_name);  //拼接
	    String filename=downloadPath;
	    Calendar print_date = Calendar.getInstance();  
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       //非常关键的！！！ 
	    java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.text.SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	    //写入到新的表格里  
	    File f=new File(filename,"保险代理机构业务人员电子档案_"+sdf1.format(print_date.getTime())+".xls");  
	    try {  
	        //创建新文件  
	        f.createNewFile();  
	        out = new FileOutputStream(f);  
	        //获取工作簿对象     
	        Workbook wb = Workbook.getWorkbook(is);  
	        // 创建可写入的工作簿对象    
	        wwb = Workbook.createWorkbook(out, wb);  
	        //根据工作表名获取WritableSheet对象  
	        wws=wwb.getSheet("保险代理机构业务人员电子档案");  
	//        //设置样式  
	//        NumberFormat nf = new NumberFormat("#00");
	        WritableCellFormat wcf=new WritableCellFormat(); 
	        WritableCellFormat wcf1=new WritableCellFormat();
	        WritableCellFormat wcf2=new WritableCellFormat();
	//        //设置样式  e.Row.Cells[9].Attributes.Add("class", "text");
	        wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf2.setBorder(null, jxl.format.BorderLineStyle.THIN);
	        WritableFont wf = new WritableFont(WritableFont.TIMES); 
	        wf.setPointSize(10);
	        
	        
	        wcf2.setFont(wf);
	        wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf2.setWrap(true);
	        
	        wcf.setFont(wf);
	        wcf.setAlignment(Alignment.RIGHT);
	        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf.setWrap(true);
	        
	        wcf1.setFont(wf);
	        wcf1.setWrap(true);
	        wcf1.setAlignment(Alignment.CENTRE);
	        wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
	        
	        Label label=null; 
	        label = new Label(1,2,"报送任务名称：保险专业中介机构月报报表("+sdf.format(print_date.getTime())+")",wcf);//添加代理人姓名
	        wws.addCell(label);
	        label = new Label(5,2,"报送机构："+branch_name,wcf);
	        wws.addCell(label);
	        for (int i=0 ;i<list.size();i++){
	        	IInsAgenPersonReportModel vo = list.get(i);
	        	label = new Label(0,i+5,String.valueOf(ActionHelper.toStr(i + 1)),wcf1);// 序号
		        wws.addCell(label);
		        
		     // 员工id
		        String sales_id= String.valueOf(vo.getSales_id());
		        if("null".equals(sales_id)){
		        	label = new Label(1,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else{
		        	label = new Label(1,i+5,sales_id,wcf1);
		        	wws.addCell(label);
		        	
		        }
		        
		     // 员工姓名
		        String sales_name =String.valueOf(vo.getSales_name());
		        if("null".equals(sales_name)){
		        	label = new Label(2,i+5," ",wcf1); 
		        	wws.addCell(label);

		        }else{
		        	
		        	label = new Label(2,i+5,sales_name,wcf1); 
		        	wws.addCell(label);
		        }
		        
		     // 性别
		        String gender = String.valueOf(vo.getGender());
		        if("null".equals(gender)){
		        	label = new Label(3,i+5," ",wcf1);
		        	wws.addCell(label);

		        }else{
		        	
		        	label = new Label(3,i+5,gender,wcf1);
		        	wws.addCell(label);
		        }
		        
		      //身份证号 
		        String iden_card = String.valueOf(vo.getIden_card());
		        if("null".equals(iden_card)){
		        	
		        	label = new Label(4,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else{
		        	label = new Label(4,i+5,iden_card,wcf1);
			        wws.addCell(label);
		        }
		        
		     // 职级
		        String post = String.valueOf(vo.getPost());
		        if("null".equals(post)){
		        	label = new Label(5,i+5," ",wcf1);
			        wws.addCell(label);
		        }else{
		        	
		        	label = new Label(5,i+5,post,wcf1);
		        	wws.addCell(label);
		        }
		        
		      //劳动合同
		        String labour_code = String.valueOf(vo.getLabour_code());
		        if("null".equals(labour_code)){
		        	label = new Label(6,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else{
		        	
		        	label = new Label(6,i+5,labour_code,wcf1);
		        	wws.addCell(label);
		        }	
		        	
		     // 劳动合同签订时间
		        String labour_code_time = String.valueOf(vo.getLabour_code_time());
		        if("null".equals(labour_code_time)){
		        	label = new Label(7,i+5," ",wcf1); 
		        	wws.addCell(label);

		        }else{
		        	
		        	label = new Label(7,i+5,labour_code_time,wcf1); 
		        	wws.addCell(label);
		        }
		        
		    //  资格证号
		        String certificate = String.valueOf(vo.getCertificate());
		        if("null".equals(certificate)){
		        	label = new Label(8,i+5," ",wcf1);
		        	wws.addCell(label);

		        }else{
		        	
		        	label = new Label(8,i+5,certificate,wcf1);
		        	wws.addCell(label);
		        }
		        
		      //资格证号获取时间
		        String certificate_date = String.valueOf(vo.getCertificate_date());
		        if("null".equals(certificate_date)){
		        	label = new Label(9,i+5,certificate_date,wcf1);
			        wws.addCell(label);
		        
		        }else{
		        	
		        	label = new Label(9,i+5,certificate_date,wcf1);
		        	wws.addCell(label);
		        }
		      //执业证号
		        String certificate_no = String.valueOf(vo.getCertificate_no());
		        if("null".equals(certificate_no)){
		        	label = new Label(10,i+5,certificate_no,wcf1);
			        wws.addCell(label);
		        }else{
		        	label = new Label(10,i+5,certificate_no,wcf1);
		        	wws.addCell(label);
		        }
		        
		      //离司时间
		        String deparement_time= String.valueOf(vo.getDepartment_time());
		        if("null".equals(deparement_time)){
		        	label = new Label(11,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else{
		        	label = new Label(11,i+5,deparement_time,wcf1);
		        	wws.addCell(label);
		        }
		        
	        }
	        wwb.write();  
	      }catch (Exception e) {  
	        e.printStackTrace();  
	    } finally{  
	        //关闭流  
	        try {  
	            wwb.close();  
	            out.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    } 
	    /*
	     * 使用虚拟目录下载方法备份
	     * downloadZipNewExcel  这个是新添的方法，里面添加了下载之后就删除掉方法
	     * */
	     
	      File file = new File(downloadPath+"保险代理机构业务人员电子档案_"+sdf1.format(print_date.getTime())+".xls");  //拼接
	      FileDownLoadUtil.downloadZipNewExcel(file,"保险代理机构业务人员电子档案_"+sdf1.format(print_date.getTime())+".xls",res);//调用下载原格式的方法
		/**
		 * HashMap<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i< list.size(); i++) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			IInsAgenPersonReportModel vo = list.get(i);
			hashMap.put("列" + 0, ActionHelper.toStr(i + 1)); // 序号
			hashMap.put("列" + 1, vo.getSales_id()); // 员工编号
			hashMap.put("列" + 2, vo.getSales_name()); // 姓名
			hashMap.put("列" + 3, vo.getGender()); // 性别
			hashMap.put("列" + 4, vo.getIden_card()); //身份证号
			hashMap.put("列" + 5, vo.getPost()); // 职务
			hashMap.put("列" + 6, vo.getLabour_code()); // 劳动合同或代理合同号
			hashMap.put("列" + 7, vo.getLabour_code_time()); // 劳动合同或代理合同号签订时间
			hashMap.put("列" + 8, vo.getCertificate()); // 资格证号
			hashMap.put("列" + 9, vo.getCertificate_date()); // 资格证获取时间
			hashMap.put("列" + 10, vo.getCertificate_no()); // 执业证号
			hashMap.put("列" + 11, vo.getDepartment_time()); // 离司时间
			
			sheetList.add(hashMap);
			} 
		// 合并表头
		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		// 合并表头
		excelMap.put("保险代理机构业务人员电子档案", sheetList);
		
		head.put("列" + 0, "序号");
		head.put("列" + 1, "员工编号");
		head.put("列" + 2, "姓名");
		head.put("列" + 3, "性别");
		head.put("列" + 4, "身份证号");
		head.put("列" + 5, "职务");
		head.put("列" + 6, "劳动合同或代理合同号");
		head.put("列" + 7, "劳动合同或代理合同号签订时间");
		head.put("列" + 8, "资格证号");
		head.put("列" + 9, "资格证获取时间");
		head.put("列" + 10, "执业证号");
		head.put("列" + 11, "离司时间");
		// 设置每个sheet页的表头
		List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
		sheetHead.add(head);
		// 设置多sheet页
		Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
		map.put("保险代理机构业务人员电子档案", sheetHead);
		ExcelWrite write = new ExcelWrite();
		//设置response 这样就可以前台弹出框进行下载了
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		write.setResponse(res);
		write.setSheetHead(map);
		write.setMergeCell(true);
		// 设置数据，key需要跟sheet页的key相同，值为需要写的数据
		write.writeExcel(excelMap);
		 */
	return null ;
	}
	
	
	/** 
	* @author liuhaiyue--下载方式改为模板写入下载。
	* @param req
	* @param res
	* @return ModelAndView
	* @description:电子协议档案
	*/
	@RequestMapping("/rsss/InsAgenPersonReport/exportInsAgenPersonReport.do")
	public ModelAndView exportInsAgenPersonReport(HttpServletRequest req,
			HttpServletResponse res) {
		String branch_id=ActionHelper.getNullToStr(req.getParameter("branch_id"));
		String branch_name =ActionHelper.getNullToStr(req.getParameter("branch_name"));
		IInsBusProReportModel model=new InsBusProReportModel();
		model.setBranch_id(branch_id);
		List<IInsBusProReportModel> list = iinsbusproreportservice.exportInsBusProReport(model);
		WritableWorkbook wwb=null;  
	    WritableSheet wws=null;  
	    FileOutputStream out =null;  
	    //获取要读取的EXCEL表格模板  
	    String downloadPath = StaticProperties.getProperty("downloadPath");//模板路径
	    String file_name="archivesBusinessAgreement.xls";
	    File is = new File(downloadPath+file_name);  //拼接
	    String filename=downloadPath;
	    Calendar print_date = Calendar.getInstance();  
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       //非常关键的！！！ 
	    java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.text.SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	    //写入到新的表格里  
	    File f=new File(filename,"保险代理机构业务协议电子档案_"+sdf1.format(print_date.getTime())+".xls");  
	    try {  
	        //创建新文件  
	        f.createNewFile();  
	        out = new FileOutputStream(f);  
	        //获取工作簿对象     
	        Workbook wb = Workbook.getWorkbook(is);  
	        // 创建可写入的工作簿对象    
	        wwb = Workbook.createWorkbook(out, wb);  
	        //根据工作表名获取WritableSheet对象  
	        wws=wwb.getSheet("保险代理机构业务协议电子档案");  
	//        //设置样式  
	        
	//      NumberFormat nf = new NumberFormat("#00");
	        WritableCellFormat wcf=new WritableCellFormat(); 
	        WritableCellFormat wcf1=new WritableCellFormat();
	        WritableCellFormat wcf2=new WritableCellFormat();
	//        //设置样式  e.Row.Cells[9].Attributes.Add("class", "text");
	        wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf2.setBorder(null, jxl.format.BorderLineStyle.THIN);
	        WritableFont wf = new WritableFont(WritableFont.TIMES); 
	        wf.setPointSize(10);
	        
	        
	        wcf2.setFont(wf);
	        wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf2.setWrap(true);
	        
	        wcf.setFont(wf);
	        wcf.setAlignment(Alignment.LEFT);
	        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf.setWrap(true);
	        
	        wcf1.setFont(wf);
	        wcf1.setWrap(true);
	        wcf1.setAlignment(Alignment.CENTRE);
	        wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
	        
	        Label label=null; 
//	        
	        label = new Label(0,2,"报送任务名称：保险专业中介机构月报报表("+sdf.format(print_date.getTime())+")",wcf);//添加代理人姓名
	        wws.addCell(label);
	        label = new Label(4,2,"报送机构："+branch_name,wcf);
	        wws.addCell(label);
	        for (int i=0 ;i<list.size();i++){
	        	IInsBusProReportModel vo = list.get(i);
	        	
	        	label = new Label(0,i+5,String.valueOf(ActionHelper.toStr(i + 1)),wcf1);// 序号
		        wws.addCell(label);
		        
		        //代理协议编号 
		        String agency_agr =String.valueOf(vo.getAgency_agr());
		        if("null".equals(agency_agr)){
		        	label = new Label(1,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else {
		        	label = new Label(1,i+5,agency_agr,wcf1);
		        	wws.addCell(label);
		        	
		        }
		        
		        // 保险人所属省份
		        String ins_provinces =String.valueOf(vo.getIns_provinces());
		        if("null".equals(ins_provinces)){
		        	label = new Label(2,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else{
		        	label = new Label(2,i+5,ins_provinces,wcf1); 
		        	wws.addCell(label);
		        	
		        }
		        //保险人name 
		        String insurer_name =String.valueOf(vo.getInsurer_name());
		        if("null".equals(insurer_name)){
		        	label = new Label(3,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else{
		        	label = new Label(3,i+5,insurer_name,wcf1);
		        	wws.addCell(label);
		        }
		        
		       //代理险种类别
		        String agent_product_type =String.valueOf(vo.getAgent_product_type());
		        if("null".equals(agent_product_type)){
		        	label = new Label(4,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else{
		        	label = new Label(4,i+5,agent_product_type,wcf1); 
		        	wws.addCell(label);
		        	
		        }
		        
		        //合同生效时间 
		        String labour_validate = String.valueOf(vo.getLabour_validate());
		        if("null".equals(labour_validate)){
		        	label = new Label(5,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else{
		        	
		        	label = new Label(5,i+5,labour_validate,wcf1);
		        	wws.addCell(label);
		        }
		        
		        //合同终止时间
		        String labour_enddate = String.valueOf(vo.getLabour_enddate());
		        if("null".equals(labour_enddate)){
		        	label = new Label(6,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else{
		        	
		        	label = new Label(6,i+5,labour_enddate,wcf1); 
		        	wws.addCell(label);
		        }
		        
		        //是否业务人员
		        String is_sales_name = String.valueOf(vo.getIs_sales_name());
		        if("null".equals(is_sales_name)){
		        	label = new Label(7,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else{
		        	
		        	label = new Label(7,i+5,is_sales_name,wcf1);
		        	wws.addCell(label);
		        }
		        
	        }
	        wwb.write();  
	      }catch (Exception e) {  
	        e.printStackTrace();  
	    } finally{  
	        //关闭流  
	        try {  
	            wwb.close();  
	            out.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    } 
	    /*
	     * 使用虚拟目录下载方法备份
	     * downloadZipNewExcel  这个是新添的方法，里面添加了下载之后就删除掉方法
	     * */
	     
	      File file = new File(downloadPath+"保险代理机构业务协议电子档案_"+sdf1.format(print_date.getTime())+".xls");  //拼接
	      FileDownLoadUtil.downloadZipNewExcel(file,"保险代理机构业务协议电子档案_"+sdf1.format(print_date.getTime())+".xls",res);//调用下载原格式的方法
		/**
		 * 
		HashMap<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i< list.size(); i++) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			IInsBusProReportModel vo = list.get(i);
			hashMap.put("列" + 0, ActionHelper.toStr(i + 1)); // 序号
			hashMap.put("列" + 1, vo.getAgency_agr()); // 代理协议编号
			hashMap.put("列" + 2, vo.getIns_provinces()); // 保险人所属省份
			hashMap.put("列" + 3, vo.getInsurer_name()); // 保险人name
			hashMap.put("列" + 4, vo.getAgent_product_type()); //代理险种类别 
			hashMap.put("列" + 5, vo.getLabour_validate()); // 合同生效时间
			hashMap.put("列" + 6, vo.getLabour_enddate()); // 合同终止时间
			hashMap.put("列" + 7, vo.getIs_sales_name()); // 是否业务人员
			sheetList.add(hashMap);
			} 
		// 合并表头
		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		// 合并表头
		excelMap.put("保险代理机构业务协议电子档案", sheetList);
		
		head.put("列" + 0, "序号");
		head.put("列" + 1, "代理协议编号");
		head.put("列" + 2, "保险人所属省份");
		head.put("列" + 3, "保险人");
		head.put("列" + 4, "代理险种类别");
		head.put("列" + 5, "合同生效时间");
		head.put("列" + 6, "合同终止时间");
		head.put("列" + 7, "是否业务人员");
		// 设置每个sheet页的表头
		List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
		sheetHead.add(head);
		// 设置多sheet页
		Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
		map.put("保险代理机构业务协议电子档案", sheetHead);
		ExcelWrite write = new ExcelWrite();
		//设置response 这样就可以前台弹出框进行下载了
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		write.setResponse(res);
		write.setSheetHead(map);
		write.setMergeCell(true);
		// 设置数据，key需要跟sheet页的key相同，值为需要写的数据
		write.writeExcel(excelMap);
		 */
	return null ;
	}
	/**
	 * @author liuhaiyue--模板写入下载
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 * @description:基本情况导出
	 */
	@RequestMapping("/rsss/BasicSituation/exportBasicSituation.do")
	public ModelAndView exportBasicSituation(HttpServletRequest req,
			HttpServletResponse res) {
		String firstDateJbb=ActionHelper.getNullToStr(req.getParameter("firstDateJbb"));
		String a=firstDateJbb+"-01";
		IBasicSituationModel model=new BasicSituationModel();
		model.setFirstDate(a.trim());
		List<IBasicSituationModel> list = ibasicsituationservice.exportBasicSituation(model);
		WritableWorkbook wwb=null;  
	    WritableSheet wws=null;  
	    FileOutputStream out =null;  
	    //获取要读取的EXCEL表格模板  
	    String downloadPath = StaticProperties.getProperty("downloadPath");//模板路径
	    String file_name="basicSituationTable.xls";
	    File is = new File(downloadPath+file_name);  //拼接
	    String filename=downloadPath;
	    Calendar print_date = Calendar.getInstance();  
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       //非常关键的！！！ 
	    java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.text.SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	    //写入到新的表格里  
	    File f=new File(filename,"基本情况_"+sdf1.format(print_date.getTime())+".xls");  
	    try {  
	        //创建新文件  
	        f.createNewFile();  
	        out = new FileOutputStream(f);  
	        //获取工作簿对象     
	        Workbook wb = Workbook.getWorkbook(is);  
	        // 创建可写入的工作簿对象    
	        wwb = Workbook.createWorkbook(out, wb);  
	        //根据工作表名获取WritableSheet对象  
	        wws=wwb.getSheet("基本情况");  
	//        //设置样式  
	        
	//      NumberFormat nf = new NumberFormat("#00");
	        WritableCellFormat wcf=new WritableCellFormat(); 
	        WritableCellFormat wcf1=new WritableCellFormat();
	        WritableCellFormat wcf2=new WritableCellFormat();
	//        //设置样式  e.Row.Cells[9].Attributes.Add("class", "text");
	        wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	        wcf2.setBorder(null, jxl.format.BorderLineStyle.THIN);
	        WritableFont wf = new WritableFont(WritableFont.TIMES); 
	        wf.setPointSize(10);
	        wcf2.setFont(wf);
	        wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf2.setWrap(true);
	        
	        wcf.setFont(wf);
	        wcf.setAlignment(Alignment.LEFT);
	        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf.setWrap(true);
	        
	        wcf1.setFont(wf);
	        wcf1.setWrap(true);
	        wcf1.setAlignment(Alignment.CENTRE);
	        wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
	        
	        Label label=null; 
	        label = new Label(0,2,"报送任务名称：保险专业中介机构月报报表("+firstDateJbb+")",wcf);//添加代理人姓名
	        wws.addCell(label);
	        label = new Label(2,2,"报送机构：中介有限公司",wcf);
	        wws.addCell(label);
	        for (int i=0 ;i<list.size();i++){
	        	IBasicSituationModel vo = list.get(i);
		        //数值
		        String num =String.valueOf(vo.getNum());
		        if("null".equals(num)){
		        	label = new Label(3,i+5," ",wcf1);
		        	wws.addCell(label);
		        }else {
		        	label = new Label(3,i+5,num,wcf1);
		        	wws.addCell(label);
		        	
		        }
	        }
	        wwb.write();  
	      }catch (Exception e) {  
	        e.printStackTrace();  
	    } finally{  
	        //关闭流  
	        try {  
	            wwb.close();  
	            out.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    } 
	    /*
	     * 使用虚拟目录下载方法备份
	     * downloadZipNewExcel  这个是新添的方法，里面添加了下载之后就删除掉方法
	     * */
	     
	      File file = new File(downloadPath+"基本情况_"+sdf1.format(print_date.getTime())+".xls");  //拼接
	      FileDownLoadUtil.downloadZipNewExcel(file,"基本情况_"+sdf1.format(print_date.getTime())+".xls",res);//调用下载原格式的方法
		/**
		 * 
		HashMap<String, List<Map<String, Object>>> excelMap = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> sheetList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i< list.size(); i++) {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			IBasicSituationModel vo = list.get(i);
			hashMap.put("列" + 0, ActionHelper.toStr(i + 1)); // 序号
			hashMap.put("列" + 1, vo.getBasic_station()); // 基本情况列表
			hashMap.put("列" + 2, vo.getLine()); // 行次
			hashMap.put("列" + 3, vo.getNum()); // 数值
			sheetList.add(hashMap);
			} 
		// 合并表头
		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		// 合并表头
		excelMap.put("基本情况", sheetList);
		
		head.put("列" + 0, "序号");
		head.put("列" + 1, "基本情况列表");
		head.put("列" + 2, "行次");
		head.put("列" + 3, "数值");
		// 设置每个sheet页的表头
		List<LinkedHashMap<String, String>> sheetHead = new ArrayList<LinkedHashMap<String, String>>();
		sheetHead.add(head);
		// 设置多sheet页
		Map<String, List<LinkedHashMap<String, String>>> map = new HashMap<String, List<LinkedHashMap<String, String>>>();
		map.put("基本情况", sheetHead);
		ExcelWrite write = new ExcelWrite();
		//设置response 这样就可以前台弹出框进行下载了
		res.setContentType("application/msexcel");
		res.setHeader("Content-disposition", "attachment; filename=" + DateHelper.getSysStr("yyyyMMddHHmmss") + "export.xls");
		write.setResponse(res);
		write.setSheetHead(map);
		write.setMergeCell(true);
		// 设置数据，key需要跟sheet页的key相同，值为需要写的数据
		write.writeExcel(excelMap);
		 */
	return null ;
	}
	
	/**
	 * 跳转到代理产险公司业务表页面
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/rsss/Report/agentProperty.do")
	public ModelAndView agentProperty(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(),new PageCount(), true));
		return new ModelAndView("ca/cacore/rsss/insuranceReport/agentProperty");
	}
	
	/**
	 * 代理产险公司业务表查询功能    
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/rsss/Report/queryAgentProperty.do")
	public ModelAndView queryAgentProperty(HttpServletRequest req,HttpServletResponse res) 
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		String org_id = ActionHelper.getNullToStr(req.getParameter("cpybranch_id"));//保险公司id
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String term = ActionHelper.getNullToStr(req.getParameter("term"));//期次
		String sign_path = ActionHelper.getNullToStr(req.getParameter("sign_path"));//填报口径
		ReturnMsg returnMsg = new ReturnMsg();
		/*if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/agentProperty");
		}
		if (term == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/agentProperty");
		}*/
		
		IUserModel user = ServletHelper.getSessionUser(req);
		IAgentPropertyModel agent = new AgentPropertyModel();
		agent.setOrg_id(org_id);
		agent.setBranch_id(branch_id);
		agent.setTerm(term);
		agent.setSign_path(sign_path);
		agent.setDept_list(user.getDept_list());
		
		//企业财产保险00100
		agent.setRisk_id("00100");
		IAgentPropertyModel agentProperty1 = iinscompanybusservice.queryAgentProperty(agent);
		//家庭财产保险00200
		agent.setRisk_id("002");
		IAgentPropertyModel agentProperty2 = iinscompanybusservice.queryAgentProperty(agent);
		//家庭财产保险 -其中：投资型家财险00201
		agent.setRisk_id("00201");
		IAgentPropertyModel agentProperty21 = iinscompanybusservice.queryAgentProperty(agent);
		//机动车辆保险00300
		agent.setRisk_id("003");
		IAgentPropertyModel agentProperty3 = iinscompanybusservice.queryAgentProperty(agent);
		//机动车辆保险-其中：交强险00301
		agent.setRisk_id("00301");
		IAgentPropertyModel agentProperty31 = iinscompanybusservice.queryAgentProperty(agent);
		//工程保险00400
		agent.setRisk_id("00400");
		IAgentPropertyModel agentProperty4 = iinscompanybusservice.queryAgentProperty(agent);
		//责任保险00500
		agent.setRisk_id("00500");
		IAgentPropertyModel agentProperty5 = iinscompanybusservice.queryAgentProperty(agent);
		//信用保险00600
		agent.setRisk_id("00600");
		IAgentPropertyModel agentProperty6 = iinscompanybusservice.queryAgentProperty(agent);
		//保证保险00700
		agent.setRisk_id("007");
		IAgentPropertyModel agentProperty7 = iinscompanybusservice.queryAgentProperty(agent);
		//保证保险-其中：机动车辆消费贷款保证保险00701
		agent.setRisk_id("00701");
		IAgentPropertyModel agentProperty71 = iinscompanybusservice.queryAgentProperty(agent);
		//保证保险-其中：个人贷款抵押房屋保证保险00702
		agent.setRisk_id("00702");
		IAgentPropertyModel agentProperty72 = iinscompanybusservice.queryAgentProperty(agent);
		//船舶保险00800
		agent.setRisk_id("00800");
		IAgentPropertyModel agentProperty8 = iinscompanybusservice.queryAgentProperty(agent);
		//货物运输保险00900
		agent.setRisk_id("00900");
		IAgentPropertyModel agentProperty9 = iinscompanybusservice.queryAgentProperty(agent);
		//特殊风险保险01000
		agent.setRisk_id("01000");
		IAgentPropertyModel agentProperty10 = iinscompanybusservice.queryAgentProperty(agent);
		//农业保险01100
		agent.setRisk_id("01100");
		IAgentPropertyModel agentProperty11 = iinscompanybusservice.queryAgentProperty(agent);
		//健康险01200
		agent.setRisk_id("012");
		IAgentPropertyModel agentProperty12 = iinscompanybusservice.queryAgentProperty(agent);
		//健康险-其中：投资型健康险01201
		agent.setRisk_id("01201");
		IAgentPropertyModel agentProperty121 = iinscompanybusservice.queryAgentProperty(agent);
		//意外伤害保险01300
		agent.setRisk_id("013");
		IAgentPropertyModel agentProperty13 = iinscompanybusservice.queryAgentProperty(agent);
		//意外伤害保险-其中：投资型意外险01301
		agent.setRisk_id("01301");
		IAgentPropertyModel agentProperty131 = iinscompanybusservice.queryAgentProperty(agent);
		//其他险01400
		agent.setRisk_id("01400");
		IAgentPropertyModel agentProperty14 = iinscompanybusservice.queryAgentProperty(agent);
		//总计
		IAgentPropertyModel agentProperty = iinscompanybusservice.queryTotal(agent);
		req.setAttribute("agentProperty1", agentProperty1);
		req.setAttribute("agentProperty2", agentProperty2);
		req.setAttribute("agentProperty21", agentProperty21);
		req.setAttribute("agentProperty3", agentProperty3);
		req.setAttribute("agentProperty31", agentProperty31);
		req.setAttribute("agentProperty4", agentProperty4);
		req.setAttribute("agentProperty5", agentProperty5);
		req.setAttribute("agentProperty6", agentProperty6);
		req.setAttribute("agentProperty7", agentProperty7);
		req.setAttribute("agentProperty71", agentProperty71);
		req.setAttribute("agentProperty72", agentProperty72);
		req.setAttribute("agentProperty8", agentProperty8);
		req.setAttribute("agentProperty9", agentProperty9);
		req.setAttribute("agentProperty10", agentProperty10);
		req.setAttribute("agentProperty11", agentProperty11);
		req.setAttribute("agentProperty12", agentProperty12);
		req.setAttribute("agentProperty121", agentProperty121);
		req.setAttribute("agentProperty13", agentProperty13);
		req.setAttribute("agentProperty131", agentProperty131);
		req.setAttribute("agentProperty14", agentProperty14);
		req.setAttribute("agentProperty", agentProperty);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
		return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/agentProperty");
	}
	
	/**
	 * 代理产险公司业务表导出功能
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/rsss/Report/exportAgentProperty.do")
	public ModelAndView exportAgentProperty(HttpServletRequest req,HttpServletResponse res) {
		String org_id = ActionHelper.getNullToStr(req.getParameter("cpybranch_id"));//保险公司id
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String term = ActionHelper.getNullToStr(req.getParameter("term"));//期次
		String sign_path = ActionHelper.getNullToStr(req.getParameter("sign_path"));//填报口径
		ReturnMsg returnMsg = new ReturnMsg();
		/*if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/agentProperty");
		}
		if (term == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/agentProperty");
		}*/
		
		IUserModel user = ServletHelper.getSessionUser(req);
		IAgentPropertyModel agent = new AgentPropertyModel();
		agent.setOrg_id(org_id);
		agent.setBranch_id(branch_id);
		agent.setTerm(term);
		agent.setSign_path(sign_path);
		agent.setDept_list(user.getDept_list());
		try{
			exportAgentProper(agent, req, res);
		}catch(Exception e){
			e.printStackTrace();
			returnMsg.setFailMessage("导出失败");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/agentProperty");
		}
		return null;
	}
	public void exportAgentProper(IAgentPropertyModel agent,HttpServletRequest req,HttpServletResponse res) 
			throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook(); // 定义一个新的工作簿
		HSSFSheet sheet = wb.createSheet("第一个Sheet页"); // 创建第一个Sheet页
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

		List<Object[]> list = new ArrayList<Object[]>();//
		int currentRowNum = 0;// 从第几行开始写
		int currentColNum = 0;// 从第几列开始写
		Row row1 = sheet.createRow(0); // 创建第一个行
		Row row2 = sheet.createRow(1); // 创建第二个行
		Row row3 = sheet.createRow(2); // 创建第三个行
		
		Cell cell0 = row1.createCell(0);
		cell0.setCellValue("险种名称");
		cell0.setCellStyle(cellStyle);
		Cell cell1 = row1.createCell(1);
		cell1.setCellValue("行数");
		cell1.setCellStyle(cellStyle);
		Cell cell2 = row1.createCell(2);
		cell2.setCellValue("业务笔数（保单件数）");
		cell2.setCellStyle(cellStyle);
		Cell cell4 = row1.createCell(4);
		cell4.setCellValue("保障额度/赔偿限额");
		cell4.setCellStyle(cellStyle);
		Cell cell6 = row1.createCell(6);
		cell6.setCellValue("保费金额");
		cell6.setCellStyle(cellStyle);
		Cell cell8 = row1.createCell(8);
		cell8.setCellValue("应付保费");
		cell8.setCellStyle(cellStyle);
		Cell cell10 = row1.createCell(10);
		cell10.setCellValue("代理佣金");
		cell10.setCellStyle(cellStyle);
		Cell cell12 = row1.createCell(12);
		cell12.setCellValue("自营网络平台渠道");
		cell12.setCellStyle(cellStyle);
		Cell cell16 = row1.createCell(16);
		cell16.setCellValue("第三方网络平台渠道");
		cell16.setCellStyle(cellStyle);
		//row1.createCell(9).setCellValue("年月");
		row2.createCell(2).setCellValue("本期");
		row2.createCell(3).setCellValue("累计");
		row2.createCell(4).setCellValue("本期");
		row2.createCell(5).setCellValue("累计");
		row2.createCell(6).setCellValue("本期");
		row2.createCell(7).setCellValue("累计");
		row2.createCell(8).setCellValue("本期");
		row2.createCell(9).setCellValue("累计");
		row2.createCell(10).setCellValue("本期");
		row2.createCell(11).setCellValue("累计");
		Cell cell122 = row2.createCell(12);
		cell122.setCellValue("保费金额");
		cell122.setCellStyle(cellStyle);
		Cell cell142 = row2.createCell(14);
		cell142.setCellValue("代理佣金");
		cell142.setCellStyle(cellStyle);
		Cell cell162 = row2.createCell(16);
		cell162.setCellValue("保费金额");
		cell162.setCellStyle(cellStyle);
		Cell cell182 = row2.createCell(18);
		cell182.setCellValue("代理佣金");
		cell182.setCellStyle(cellStyle);
		
		row3.createCell(12).setCellValue("本期");
		row3.createCell(13).setCellValue("累计");
		row3.createCell(14).setCellValue("本期");
		row3.createCell(15).setCellValue("累计");
		row3.createCell(16).setCellValue("本期");
		row3.createCell(17).setCellValue("累计");
		row3.createCell(18).setCellValue("本期");
		row3.createCell(19).setCellValue("累计");
		
		
		
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				2, // 结束行
				0, // 其实列
				0 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				2, // 结束行
				1, // 其实列
				1 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				2, // 其实列
				3 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				4, // 其实列
				5 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				6, // 其实列
				7 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				8, // 其实列
				9 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				10, // 其实列
				11 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				12, // 其实列
				15 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				16, // 其实列
				19 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				2, // 其实列
				2 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				3, // 其实列
				3 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				4, // 其实列
				4 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				5, // 其实列
				5 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				6, // 其实列
				6 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				7, // 其实列
				7 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				8, // 其实列
				8 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				9, // 其实列
				9 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				10, // 其实列
				10 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				11, // 其实列
				11 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				12, // 其实列
				13 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				14, // 其实列
				15 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				16, // 其实列
				17 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				18, // 其实列
				19 // 结束列
		));
	
		sheet.setColumnWidth(0, 7300);
		sheet.setColumnWidth(1, 3300);
		sheet.setColumnWidth(2, 3300);
		sheet.setColumnWidth(3, 3300);
		sheet.setColumnWidth(4, 3300);
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
		sheet.setColumnWidth(15, 3300);
		sheet.setColumnWidth(16, 3300);
		sheet.setColumnWidth(17, 3300);
		sheet.setColumnWidth(18, 3300);
		sheet.setColumnWidth(19, 3300);
		
		//企业财产保险00100
		agent.setRisk_id("00100");
		IAgentPropertyModel agentProperty1 = iinscompanybusservice.queryAgentProperty(agent);
		//家庭财产保险00200
		agent.setRisk_id("002");
		IAgentPropertyModel agentProperty2 = iinscompanybusservice.queryAgentProperty(agent);
		//家庭财产保险 -其中：投资型家财险00201
		agent.setRisk_id("00201");
		IAgentPropertyModel agentProperty21 = iinscompanybusservice.queryAgentProperty(agent);
		//机动车辆保险00300
		agent.setRisk_id("003");
		IAgentPropertyModel agentProperty3 = iinscompanybusservice.queryAgentProperty(agent);
		//机动车辆保险-其中：交强险00301
		agent.setRisk_id("00301");
		IAgentPropertyModel agentProperty31 = iinscompanybusservice.queryAgentProperty(agent);
		//工程保险00400
		agent.setRisk_id("00400");
		IAgentPropertyModel agentProperty4 = iinscompanybusservice.queryAgentProperty(agent);
		//责任保险00500
		agent.setRisk_id("00500");
		IAgentPropertyModel agentProperty5 = iinscompanybusservice.queryAgentProperty(agent);
		//信用保险00600
		agent.setRisk_id("00600");
		IAgentPropertyModel agentProperty6 = iinscompanybusservice.queryAgentProperty(agent);
		//保证保险00700
		agent.setRisk_id("007");
		IAgentPropertyModel agentProperty7 = iinscompanybusservice.queryAgentProperty(agent);
		//保证保险-其中：机动车辆消费贷款保证保险00701
		agent.setRisk_id("00701");
		IAgentPropertyModel agentProperty71 = iinscompanybusservice.queryAgentProperty(agent);
		//保证保险-其中：个人贷款抵押房屋保证保险00702
		agent.setRisk_id("00702");
		IAgentPropertyModel agentProperty72 = iinscompanybusservice.queryAgentProperty(agent);
		//船舶保险00800
		agent.setRisk_id("00800");
		IAgentPropertyModel agentProperty8 = iinscompanybusservice.queryAgentProperty(agent);
		//货物运输保险00900
		agent.setRisk_id("00900");
		IAgentPropertyModel agentProperty9 = iinscompanybusservice.queryAgentProperty(agent);
		//特殊风险保险01000
		agent.setRisk_id("01000");
		IAgentPropertyModel agentProperty10 = iinscompanybusservice.queryAgentProperty(agent);
		//农业保险01100
		agent.setRisk_id("01100");
		IAgentPropertyModel agentProperty11 = iinscompanybusservice.queryAgentProperty(agent);
		//健康险01200
		agent.setRisk_id("012");
		IAgentPropertyModel agentProperty12 = iinscompanybusservice.queryAgentProperty(agent);
		//健康险-其中：投资型健康险01201
		agent.setRisk_id("01201");
		IAgentPropertyModel agentProperty121 = iinscompanybusservice.queryAgentProperty(agent);
		//意外伤害保险01300
		agent.setRisk_id("013");
		IAgentPropertyModel agentProperty13 = iinscompanybusservice.queryAgentProperty(agent);
		//意外伤害保险-其中：投资型意外险01301
		agent.setRisk_id("01301");
		IAgentPropertyModel agentProperty131 = iinscompanybusservice.queryAgentProperty(agent);
		//其他险01400
		agent.setRisk_id("01400");
		IAgentPropertyModel agentProperty14 = iinscompanybusservice.queryAgentProperty(agent);
		//总计
		IAgentPropertyModel agentProperty = iinscompanybusservice.queryTotal(agent);
		createSheet1(sheet,"1、企业财产保险",1,agentProperty1);
		createSheet1(sheet,"2、家庭财产保险",2, agentProperty2);
		createSheet1(sheet,"    其中：投资型家财险",3, agentProperty21);
		createSheet1(sheet,"3、机动车辆保险",4, agentProperty3);
		createSheet1(sheet,"    其中：交强险",5, agentProperty31);
		createSheet1(sheet,"4、工程保险",6, agentProperty4);
		createSheet1(sheet,"5、责任保险",7, agentProperty5);
		createSheet1(sheet,"6、信用保险",8, agentProperty6);
		createSheet1(sheet,"7、保证保险",9, agentProperty7);
		createSheet1(sheet,"    其中：机动车辆消费贷款保证保险",10, agentProperty71);
		createSheet1(sheet,"    其中：个人贷款抵押房屋保证保险",11, agentProperty72);
		createSheet1(sheet,"8、船舶保险",12, agentProperty8);
		createSheet1(sheet,"9、货物运输保险",13, agentProperty9);
		createSheet1(sheet,"10、特殊风险保险",14, agentProperty10);
		createSheet1(sheet,"11、农业保险",15, agentProperty11);
		createSheet1(sheet,"12、健康险",16, agentProperty12);
		createSheet1(sheet,"    其中：投资型健康险",17, agentProperty121);
		createSheet1(sheet,"13、意外伤害保险",18, agentProperty13);
		createSheet1(sheet,"    其中：投资型意外险",19, agentProperty131);
		createSheet1(sheet,"14、其他险",20, agentProperty14);
		createSheet1(sheet,"总计",21, agentProperty);
		// 设置response 这样就可以前台弹出框进行下载了
		String fileName = URLEncoder.encode("代理产险公司业务表查询结果导出.xls", "UTF-8");
		ExcelUtils excelUtils = new ExcelUtils();
		excelUtils.writeExcel(list, currentRowNum, currentColNum, wb, sheet, fileName, res);
	}
	public void createSheet1(HSSFSheet sheet,String riskname, int row, IAgentPropertyModel agentProperty){
		Row row1 = sheet.createRow(row+2);
		row1.createCell(0).setCellValue(riskname);
		row1.createCell(1).setCellValue(row);
		row1.createCell(2).setCellValue(agentProperty.getTotal_policy_count().doubleValue());
		row1.createCell(3).setCellValue(agentProperty.getTotal_policycount_sum().doubleValue());
		row1.createCell(4).setCellValue(agentProperty.getTotal_amount().doubleValue());
		row1.createCell(5).setCellValue(agentProperty.getTotal_amount_sum().doubleValue());
		row1.createCell(6).setCellValue(agentProperty.getTotal_permium().doubleValue());
		row1.createCell(7).setCellValue(agentProperty.getTotal_permium_sum().doubleValue());
		row1.createCell(8).setCellValue(agentProperty.getTotal_ppayable().doubleValue());
		row1.createCell(9).setCellValue(agentProperty.getTotal_ppayable_sum().doubleValue());
		row1.createCell(10).setCellValue(agentProperty.getTotal_agcommiss().doubleValue());
		row1.createCell(11).setCellValue(agentProperty.getTotal_agcommiss_sum().doubleValue());
		row1.createCell(12).setCellValue(agentProperty.getTotal_autoprem().doubleValue());
		row1.createCell(13).setCellValue(agentProperty.getTotal_autoprem_sum().doubleValue());
		row1.createCell(14).setCellValue(agentProperty.getTotal_autoagcommiss().doubleValue());
		row1.createCell(15).setCellValue(agentProperty.getTotal_autoagcommiss_sum().doubleValue());
		row1.createCell(16).setCellValue(agentProperty.getTotal_trdprem().doubleValue());
		row1.createCell(17).setCellValue(agentProperty.getTotal_trdprem_sum().doubleValue());
		row1.createCell(18).setCellValue(agentProperty.getTotal_trdagcommiss().doubleValue());
		row1.createCell(19).setCellValue(agentProperty.getTotal_trdagcommiss_sum().doubleValue());
	}
	
	/**
	 * 跳转基本情况表页面
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/rsss/Report/basicInformation.do")
	public ModelAndView basicInformation(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(),new PageCount(), true));
		return new ModelAndView("ca/cacore/rsss/insuranceReport/basicInformation");
	}
	
	/**
	 * 基本情况表查询功能
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/rsss/Report/queryBasicInformation.do")
	public ModelAndView queryBasicInformation(HttpServletRequest req,HttpServletResponse res) 
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String term = ActionHelper.getNullToStr(req.getParameter("term"));//期次
		ReturnMsg returnMsg = new ReturnMsg();
		if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/basicInformation");
		}
		if (term == null || "".equals(term)) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/basicInformation");
		}
		
		IUserModel user = ServletHelper.getSessionUser(req);	
		// 设置查询条件
		ModelHelper modelHelper = new ModelHelper();
		IBasicInfomationModel model = new BasicInfomationModel();
		model = (IBasicInfomationModel) modelHelper.getModel(model, req);
		model.setDept_list(user.getDept_list());
		/*if(model!=null){
			if(model.getEntry_date1()!=null && model.getEntry_date2()!=null){
				 if(model.getEntry_date1().after(model.getEntry_date2())){
					 msg.setFailMessage("入职起始时间大于入职起止时间");
					 req.setAttribute("rmHelper", new ReturnMsgHelper(req, msg).setReturnParams(msg.getDataTable()));
					 return returnPage(res, msg, "ca/cacore/personal/goEmpAnalysis");
				 }
			 }
			if(model.getEnd_date1()!=null && model.getEnd_date2()!=null){
				 if(model.getEnd_date1().after(model.getEnd_date2())){
					 msg.setFailMessage("离职起始时间大于离职起止时间");
					 req.setAttribute("rmHelper", new ReturnMsgHelper(req, msg).setReturnParams(msg.getDataTable()));
					 return returnPage(res, msg, "ca/cacore/personal/goEmpAnalysis");
				 }
			 }
			if(model.getSys_date1()!=null && model.getSys_date2()!=null){
				 if(model.getSys_date1().after(model.getSys_date2())){
					 msg.setFailMessage("系统起始时间大于系统起止时间");
					 req.setAttribute("rmHelper", new ReturnMsgHelper(req, msg).setReturnParams(msg.getDataTable()));
					 return returnPage(res, msg, "ca/cacore/personal/goEmpAnalysis");
				 }
			 }
		}*/
		
		/*model.setBranch_id(branch_id);
		model.setTerm(term);
		model.setDept_list(user.getDept_list());*/
		IBasicInfomationModel basicInfomation = ibasicsituationservice.queryBasicInfomation1(model);
		//returnMsg.setDataTable(TransHelper.obj2map(basicInfomation));
		//IBasicInfomationModel baseinfo = null;
		//baseinfo = ibasicsituationservice.queryBasicInfomation(model);
		req.setAttribute("basicInfomation",basicInfomation);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
		return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/basicInformation" );
	}
	
	
	/**
	 * 基本情况表导出功能
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/rsss/Report/exportBasicInformation.do")
	public ModelAndView exportBasicInformation(HttpServletRequest req,HttpServletResponse res) {
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String term = ActionHelper.getNullToStr(req.getParameter("term"));//期次
		ReturnMsg returnMsg = new ReturnMsg();
		if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后导出。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/basicInformation");
		}
		if (term == null || "".equals(term)) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后导出。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/basicInformation");
		}
		IUserModel user = ServletHelper.getSessionUser(req);
		IBasicInfomationModel basicInfo = new BasicInfomationModel();
		basicInfo.setBranch_id(branch_id);
		basicInfo.setTerm(term);
		basicInfo.setDept_list(user.getDept_list());
		try{
			exportBasicInfo(basicInfo,req,res);
		}catch(Exception e){
			e.printStackTrace();
			returnMsg.setFailMessage("导出失败");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/basicInformation");
		}
		
		return null;
	}
	
	
	public void exportBasicInfo(IBasicInfomationModel basicInfo,HttpServletRequest req,HttpServletResponse res) 
			throws Exception{
		HSSFWorkbook wb = new HSSFWorkbook(); // 定义一个新的工作簿
		HSSFSheet sheet = wb.createSheet("第一个Sheet页"); // 创建第一个Sheet页
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

		List<Object[]> list = new ArrayList<Object[]>();//
		int currentRowNum = 0;// 从第几行开始写
		int currentColNum = 0;// 从第几列开始写
		Row row1 = sheet.createRow(0); // 创建第二个行
		
		
		row1.createCell(0).setCellValue("基本情况");
		row1.createCell(1).setCellValue("行数");
		row1.createCell(2).setCellValue("数值");
		
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 3500);
		sheet.setColumnWidth(2, 3500);
		//sheet.setColumnWidth(3, 3500);
		
		/*IUserModel user = ServletHelper.getSessionUser(req);
		ModelHelper modelHelper = new ModelHelper();
		IBasicInfomationModel basicInfo = new BasicInfomationModel();
		basicInfo = (IBasicInfomationModel) modelHelper.getModel(basicInfo, req);
		basicInfo.setDept_list(user.getDept_list());*/
		PageCount pageCount = basicInfo.getPageCount();
		pageCount.setNowPage(0);
		pageCount.setLimit(60000);
		pageCount.setStart(0);
		pageCount.setRows4Page(0);
		pageCount.setPage(false);
		// 获取查询结果集
		/*List<IBasicInfomationModel> relist = ibasicsituationservice.queryBasicInfomations(basicInfo);*/
		pageCount.setPage(true);
		
		
		/*for (int i = 0; i < relist.size(); i++) {
			row1 = sheet.createRow(i+1);
			BasicInfomationModel m = (BasicInfomationModel) relist.get(i);
			row1.createCell(1) .setCellValue(i+1);
			if(m!=null){
				row1.createCell(0).setCellValue(m.getBaseinfo());
			}
			if(m!=null){
				row1.createCell(2).setCellValue(m.getBaseinfo_value());
			}
		}*/
		IBasicInfomationModel model = ibasicsituationservice.queryBasicInfomation1(basicInfo);
		if(model == null){
			createSheet(sheet,"公司现有人数（1）",1,0);
			createSheet(sheet,"同比（+/-）（2）",2,0);
			createSheet(sheet,"总持证数（3）",3,0);
			createSheet(sheet,"高级管理人员人数（4）",4,0);
			createSheet(sheet,"高级管理人员持证数（5）",5,0);
			createSheet(sheet,"业务人员人数（6）",6,0);
			createSheet(sheet,"业务人员持证数（7）",7,0);
			createSheet(sheet,"非业务人员人数（8）",8,0);
			createSheet(sheet,"非业务人员持证数（9）",9,0);
			createSheet(sheet,"保单件数（件）",10,0);
		}else{
			createSheet(sheet,"公司现有人数（1）",1,model.getNumbers());
			createSheet(sheet,"同比（+/-）（2）",2,model.getCompared());
			createSheet(sheet,"总持证数（3）",3,model.getIs_practice());
			createSheet(sheet,"高级管理人员人数（4）",4,model.getGleaderNum());
			createSheet(sheet,"高级管理人员持证数（5）",5,model.getGleaderIsPracticeNum());
			createSheet(sheet,"业务人员人数（6）",6,model.getBusinessNum());
			createSheet(sheet,"业务人员持证数（7）",7,model.getBusinessIsPracticeNum());
			createSheet(sheet,"非业务人员人数（8）",8,model.getNoBusinessNum());
			createSheet(sheet,"非业务人员持证数（9）",9,model.getNoBusinessIsPracticeNum());
			createSheet(sheet,"保单件数（件）",10,model.getPolicyNum());
		}
		// 设置response 这样就可以前台弹出框进行下载了
		String fileName = URLEncoder.encode("基本情况表查询结果导出.xls", "UTF-8");
		ExcelUtils excelUtils = new ExcelUtils();
		excelUtils.writeExcel(list, currentRowNum, currentColNum, wb, sheet, fileName, res);
	}
	
	
	public void createSheet(HSSFSheet sheet, String name,int row, int num){
		Row row1 = sheet.createRow(row);
		row1.createCell(0).setCellValue(name);
		row1.createCell(1).setCellValue(row);
		row1.createCell(2).setCellValue(num);
	}
	
	
	/**
	 * 跳转到代理人身险业务表页面
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/rsss/Report/agentLifeInsurance.do")
	public ModelAndView agentLifeInsurance(HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, new ReturnMsg(),new PageCount(), true));
		return new ModelAndView("ca/cacore/rsss/insuranceReport/agentLifeInsurance");
	}
	
	
	/**
	 * 代理人身险业务表查询功能     ipersonalbusreportservice
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/rsss/Report/	queryAgentLifeInsurance.do")
	public ModelAndView queryAgentLifeInsurance(HttpServletRequest req,HttpServletResponse res) 
			throws SecurityException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		String org_id = ActionHelper.getNullToStr(req.getParameter("cpybranch_id"));//保险公司id
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String term = ActionHelper.getNullToStr(req.getParameter("term"));//期次
		String sign_path = ActionHelper.getNullToStr(req.getParameter("sign_path"));//填报口径
		ReturnMsg returnMsg = new ReturnMsg();
		/*if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/agentLifeInsurance");
		}
		if (term == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/agentLifeInsurance");
		}*/
		
		IUserModel user = ServletHelper.getSessionUser(req);
		IAentLifeInsModel agentLife = new AentLifeInsModel();
		agentLife.setOrg_id(org_id);
		agentLife.setBranch_id(branch_id);
		agentLife.setTerm(term);
		agentLife.setSign_path(sign_path);
		agentLife.setDept_list(user.getDept_list());
		returnMsg.setDataTable(TransHelper.obj2map(agentLife));
		//寿险小计
		agentLife.setRisk_id("101");
		IAentLifeInsModel agentLife0 = ipersonalbusreportservice.queryTotal(agentLife);
		//普通寿险汇总
		agentLife.setRisk_id("10101");
		IAentLifeInsModel agentLife1 = ipersonalbusreportservice.queryTotal(agentLife);
		//普通寿险-1、定期寿险
		agentLife.setRisk_id("1010101");
		IAentLifeInsModel agentLife11 = ipersonalbusreportservice.queryTotal(agentLife);
		//普通寿险-2、两全寿险
		agentLife.setRisk_id("1010102");
		IAentLifeInsModel agentLife12 = ipersonalbusreportservice.queryTotal(agentLife);
		//普通寿险-3、终身寿险
		agentLife.setRisk_id("1010103");
		IAentLifeInsModel agentLife13 = ipersonalbusreportservice.queryTotal(agentLife);
		//普通寿险-4、年金寿险
		agentLife.setRisk_id("1010104");
		IAentLifeInsModel agentLife14 = ipersonalbusreportservice.queryTotal(agentLife);
		
		//分红寿险汇总
		agentLife.setRisk_id("10102");
		IAentLifeInsModel agentLife2 = ipersonalbusreportservice.queryTotal(agentLife);
		//分红寿险-1、定期寿险
		agentLife.setRisk_id("1010201");
		IAentLifeInsModel agentLife21 = ipersonalbusreportservice.queryTotal(agentLife);
		//分红寿险-2、两全寿险
		agentLife.setRisk_id("1010202");
		IAentLifeInsModel agentLife22 = ipersonalbusreportservice.queryTotal(agentLife);
		//分红寿险-3、终身寿险
		agentLife.setRisk_id("1010203");
		IAentLifeInsModel agentLife23 = ipersonalbusreportservice.queryTotal(agentLife);
		//分红寿险-4、年金寿险
		agentLife.setRisk_id("1010204");
		IAentLifeInsModel agentLife24 = ipersonalbusreportservice.queryTotal(agentLife);
		/*//分红寿险
		agentLife.setRisk_id("10102");
		List<IAentLifeInsModel> list2 = ipersonalbusreportservice.queryByRisk(agentLife);*/
		//投资连结产品
		agentLife.setRisk_id("10103");
		IAentLifeInsModel agentLife3 = ipersonalbusreportservice.queryTotal(agentLife);
		//投资连结产品-其中：年金保险
		agentLife.setRisk_id("1010301");
		IAentLifeInsModel  agentLife31 = ipersonalbusreportservice.queryTotal(agentLife);
		//万能寿险
		agentLife.setRisk_id("10104");
		IAentLifeInsModel agentLife4 = ipersonalbusreportservice.queryTotal(agentLife);
		//万能寿险-其中：年金保险
		agentLife.setRisk_id("1010401");
		IAentLifeInsModel agentLife41 = ipersonalbusreportservice.queryTotal(agentLife);
		//意外伤害险小计
		agentLife.setRisk_id("105");
		IAentLifeInsModel agentLife5 = ipersonalbusreportservice.queryTotal(agentLife);
		//意外伤害险小计-1、一年期以内业务
		agentLife.setRisk_id("10501");
		IAentLifeInsModel agentLife51 = ipersonalbusreportservice.queryTotal(agentLife);
		//意外伤害险小计-2、一年期业务
		agentLife.setRisk_id("10502");
		IAentLifeInsModel agentLife52 = ipersonalbusreportservice.queryTotal(agentLife);
		//意外伤害险小计-3、一年期以上业务
		agentLife.setRisk_id("10503");
		IAentLifeInsModel agentLife53 = ipersonalbusreportservice.queryTotal(agentLife);
		//健康险小计
		agentLife.setRisk_id("106");
		IAentLifeInsModel agentLife6 = ipersonalbusreportservice.queryTotal(agentLife);
		//健康险小计-1、短期健康险
		agentLife.setRisk_id("10601");
		IAentLifeInsModel agentLife61 = ipersonalbusreportservice.queryTotal(agentLife);
		//健康险小计-1、短期健康险
		agentLife.setRisk_id("10602");
		IAentLifeInsModel agentLife62 = ipersonalbusreportservice.queryTotal(agentLife);
		//其他
		agentLife.setRisk_id("10700");
		IAentLifeInsModel agentLife7 = ipersonalbusreportservice.queryTotal(agentLife);
		//汇总
		agentLife.setRisk_id("10");
		IAentLifeInsModel agentLife8 = ipersonalbusreportservice.queryTotal(agentLife);
		req.setAttribute("agentLife0", agentLife0);
		req.setAttribute("agentLife1", agentLife1);
		req.setAttribute("agentLife11", agentLife11);
		req.setAttribute("agentLife12", agentLife12);
		req.setAttribute("agentLife13", agentLife13);
		req.setAttribute("agentLife14", agentLife14);
		req.setAttribute("agentLife2", agentLife2);
		req.setAttribute("agentLife21", agentLife21);
		req.setAttribute("agentLife22", agentLife22);
		req.setAttribute("agentLife23", agentLife23);
		req.setAttribute("agentLife24", agentLife24);
		req.setAttribute("agentLife3", agentLife3);
		req.setAttribute("agentLife31", agentLife31);
		req.setAttribute("agentLife4", agentLife4);
		req.setAttribute("agentLife41", agentLife41);
		req.setAttribute("agentLife5", agentLife5);
		req.setAttribute("agentLife51", agentLife51);
		req.setAttribute("agentLife52", agentLife52);
		req.setAttribute("agentLife53", agentLife53);
		req.setAttribute("agentLife6", agentLife6);
		req.setAttribute("agentLife61", agentLife61);
		req.setAttribute("agentLife62", agentLife62);
		req.setAttribute("agentLife7", agentLife7);
		req.setAttribute("agentLife8", agentLife8);
		req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg, true));
		return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/agentLifeInsurance" );

	}
	

	/**
	 * 代理人身险业务表导出功能
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/rsss/Report/exportAgentLifeInsurance.do")
	public ModelAndView exportAgentLifeInsurance(HttpServletRequest req,HttpServletResponse res) {
		String org_id = ActionHelper.getNullToStr(req.getParameter("cpybranch_id"));//保险公司id
		String branch_id = ActionHelper.getNullToStr(req.getParameter("branch_id"));//中介公司id
		String term = ActionHelper.getNullToStr(req.getParameter("term"));//期次
		String sign_path = ActionHelper.getNullToStr(req.getParameter("sign_path"));//填报口径
		ReturnMsg returnMsg = new ReturnMsg();
		/*if (branch_id == null || "".equals(branch_id)) {
			returnMsg.setFailMessage("中介公司查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/agentLifeInsurance");
		}
		if (term == null) {
			returnMsg.setFailMessage("时间查询条件不能为空,请选择后查询。");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/agentLifeInsurance");
		}*/
		
		IUserModel user = ServletHelper.getSessionUser(req);
		IAentLifeInsModel agentLife = new AentLifeInsModel();
		agentLife.setOrg_id(org_id);
		agentLife.setBranch_id(branch_id);
		agentLife.setTerm(term);
		agentLife.setSign_path(sign_path);
		agentLife.setDept_list(user.getDept_list());
		try{
			exportAgentLifeIns(agentLife,req,res);
		}catch(Exception e){
			e.printStackTrace();
			returnMsg.setFailMessage("导出失败");
			req.setAttribute("rmHelper", new ReturnMsgHelper(req, returnMsg,  true));
			return returnPage(res, returnMsg, "ca/cacore/rsss/insuranceReport/agentLifeInsurance");
		}
		
		return null;
	} 
	
	public void exportAgentLifeIns(IAentLifeInsModel agentLife,HttpServletRequest req,HttpServletResponse res) 
			throws Exception{
		HSSFWorkbook wb = new HSSFWorkbook(); // 定义一个新的工作簿
		HSSFSheet sheet = wb.createSheet("第一个Sheet页"); // 创建第一个Sheet页
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

		List<Object[]> list = new ArrayList<Object[]>();//
		int currentRowNum = 0;// 从第几行开始写
		int currentColNum = 0;// 从第几列开始写
		Row row1 = sheet.createRow(0); // 创建第一个行
		Row row2 = sheet.createRow(1); // 创建第二个行
		Row row3 = sheet.createRow(2); // 创建第三个行
		Cell cell0 = row1.createCell(0);
		cell0.setCellValue("险种名称");
		cell0.setCellStyle(cellStyle);
		Cell cell1 = row1.createCell(1);
		cell1.setCellValue("行数");
		cell1.setCellStyle(cellStyle);
		Cell cell2 = row1.createCell(2);
		cell2.setCellValue("新单保障对象数量");
		cell2.setCellStyle(cellStyle);
		Cell cell4 = row1.createCell(4);
		cell4.setCellValue("新单保障额度/赔偿限额");
		cell4.setCellStyle(cellStyle);
		Cell cell6 = row1.createCell(6);
		cell6.setCellValue("新保单保费金额");
		cell6.setCellStyle(cellStyle);
		Cell cell8 = row1.createCell(8);
		row1.createCell(8).setCellValue("续期保单金额");
		cell8.setCellStyle(cellStyle);
		Cell cell10 = row1.createCell(10);
		cell10.setCellValue("应付保费");
		cell10.setCellStyle(cellStyle);
		Cell cell12 = row1.createCell(12);
		cell12.setCellValue("新单代理佣金");
		cell12.setCellStyle(cellStyle);
		Cell cell14 = row1.createCell(14);
		cell14.setCellValue("续期代理佣金");
		cell14.setCellStyle(cellStyle);
		Cell cell16 = row1.createCell(16);
		cell16.setCellValue("自营网络平台渠道");
		cell16.setCellStyle(cellStyle);
		Cell cell24 = row1.createCell(24);
		cell24.setCellValue("第三方网络平台渠道");
		cell24.setCellStyle(cellStyle);
		//row1.createCell(15).setCellValue("年月  ");
		row2.createCell(2).setCellValue("本期");
		row2.createCell(3).setCellValue("累计");
		row2.createCell(4).setCellValue("本期");
		row2.createCell(5).setCellValue("累计");
		row2.createCell(6).setCellValue("本期");
		row2.createCell(7).setCellValue("累计");
		row2.createCell(8).setCellValue("本期");
		row2.createCell(9).setCellValue("累计");
		row2.createCell(10).setCellValue("本期");
		row2.createCell(11).setCellValue("累计");
		row2.createCell(12).setCellValue("本期");
		row2.createCell(13).setCellValue("累计");
		row2.createCell(14).setCellValue("本期");
		row2.createCell(15).setCellValue("累计");
		Cell cell162 = row2.createCell(16);
		cell162.setCellValue("新单保费金额");
		cell162.setCellStyle(cellStyle);
		Cell cell182 = row2.createCell(18);
		cell182.setCellValue("续期保单金额");
		cell182.setCellStyle(cellStyle);
		Cell cell202 = row2.createCell(20);
		cell202.setCellValue("新单代理佣金");
		cell202.setCellStyle(cellStyle);
		Cell cell222 = row2.createCell(22);
		cell222.setCellValue("续期代理佣金");
		cell222.setCellStyle(cellStyle);
		Cell cell242 = row2.createCell(24);
		cell242.setCellValue("新单保费金额");
		cell242.setCellStyle(cellStyle);
		Cell cell262 = row2.createCell(26);
		cell262.setCellValue("续期保单金额");
		cell262.setCellStyle(cellStyle);
		Cell cell282 = row2.createCell(28);
		cell282.setCellValue("新单代理佣金");
		cell282.setCellStyle(cellStyle);
		Cell cell302 = row2.createCell(30);
		cell302.setCellValue("续期代理佣金");
		cell302.setCellStyle(cellStyle);
		
		row3.createCell(16).setCellValue("本期");
		row3.createCell(17).setCellValue("累计");
		row3.createCell(18).setCellValue("本期");
		row3.createCell(19).setCellValue("累计");
		row3.createCell(20).setCellValue("本期");
		row3.createCell(21).setCellValue("累计");
		row3.createCell(22).setCellValue("本期");
		row3.createCell(23).setCellValue("累计");
		row3.createCell(24).setCellValue("本期");
		row3.createCell(25).setCellValue("累计");
		row3.createCell(26).setCellValue("本期");
		row3.createCell(27).setCellValue("累计");
		row3.createCell(28).setCellValue("本期");
		row3.createCell(29).setCellValue("累计");
		row3.createCell(30).setCellValue("本期");
		row3.createCell(31).setCellValue("累计");
		
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				2, // 结束行
				0, // 其实列
				0 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				2, // 结束行
				1, // 其实列
				1 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				2, // 其实列
				3 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				4, // 其实列
				5 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				6, // 其实列
				7 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				8, // 其实列
				9 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				10, // 其实列
				11 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				12, // 其实列
				13 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				14, // 其实列
				15 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				14, // 其实列
				15 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				16, // 其实列
				23 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(0, // 起始行
				0, // 结束行
				24, // 其实列
				31 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				2, // 其实列
				2 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				3, // 其实列
				3 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				4, // 其实列
				4 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				5, // 其实列
				5 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				6, // 其实列
				6 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				7, // 其实列
				7 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				8, // 其实列
				8 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				9, // 其实列
				9 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				10, // 其实列
				10 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				11, // 其实列
				11 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				12, // 其实列
				12 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				13, // 其实列
				13 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				14, // 其实列
				14 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				2, // 结束行
				15, // 其实列
				15 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				16, // 其实列
				17 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				18, // 其实列
				19 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				20, // 其实列
				21 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				22, // 其实列
				23 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				24, // 其实列
				25 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				26, // 其实列
				27 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				28, // 其实列
				29 // 结束列
		));
		sheet.addMergedRegion(new CellRangeAddress(1, // 起始行
				1, // 结束行
				30, // 其实列
				31 // 结束列
		));
		 
		sheet.setColumnWidth(0, 7300);
		sheet.setColumnWidth(1, 3300);
		sheet.setColumnWidth(2, 3300);
		sheet.setColumnWidth(3, 3300);
		sheet.setColumnWidth(4, 3300);
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
		sheet.setColumnWidth(15, 3300);
		sheet.setColumnWidth(16, 3300);
		sheet.setColumnWidth(17, 3300);
		sheet.setColumnWidth(18, 3300);
		sheet.setColumnWidth(19, 3300);
		sheet.setColumnWidth(20, 3300);
		sheet.setColumnWidth(21, 3300);
		sheet.setColumnWidth(22, 3300);
		sheet.setColumnWidth(23, 3300);
		sheet.setColumnWidth(24, 3300);
		sheet.setColumnWidth(25, 3300);
		sheet.setColumnWidth(26, 3300);
		sheet.setColumnWidth(27, 3300);
		sheet.setColumnWidth(28, 3300);
		sheet.setColumnWidth(29, 3300);
		sheet.setColumnWidth(30, 3300);
		sheet.setColumnWidth(31, 3300);

		PageCount pageCount = agentLife.getPageCount();
		pageCount.setNowPage(0);
		pageCount.setLimit(60000);
		pageCount.setStart(0);
		pageCount.setRows4Page(0);
		pageCount.setPage(false);
	
		//寿险小计
		agentLife.setRisk_id("101");
		IAentLifeInsModel agentLife0 = ipersonalbusreportservice.queryTotal(agentLife);
		//普通寿险汇总
		agentLife.setRisk_id("10101");
		IAentLifeInsModel agentLife1 = ipersonalbusreportservice.queryTotal(agentLife);
		//普通寿险-1、定期寿险
		agentLife.setRisk_id("1010101");
		IAentLifeInsModel agentLife11 = ipersonalbusreportservice.queryTotal(agentLife);
		//普通寿险-2、两全寿险
		agentLife.setRisk_id("1010102");
		IAentLifeInsModel agentLife12 = ipersonalbusreportservice.queryTotal(agentLife);
		//普通寿险-3、终身寿险
		agentLife.setRisk_id("1010103");
		IAentLifeInsModel agentLife13 = ipersonalbusreportservice.queryTotal(agentLife);
		//普通寿险-4、年金寿险
		agentLife.setRisk_id("1010104");
		IAentLifeInsModel agentLife14 = ipersonalbusreportservice.queryTotal(agentLife);
				
		//分红寿险汇总
		agentLife.setRisk_id("10102");
		IAentLifeInsModel agentLife2 = ipersonalbusreportservice.queryTotal(agentLife);
		//分红寿险-1、定期寿险
		agentLife.setRisk_id("1010201");
		IAentLifeInsModel agentLife21 = ipersonalbusreportservice.queryTotal(agentLife);
		//分红寿险-2、两全寿险
		agentLife.setRisk_id("1010202");
		IAentLifeInsModel agentLife22 = ipersonalbusreportservice.queryTotal(agentLife);
		//分红寿险-3、终身寿险
		agentLife.setRisk_id("1010203");
		IAentLifeInsModel agentLife23 = ipersonalbusreportservice.queryTotal(agentLife);
		//分红寿险-4、年金寿险
		agentLife.setRisk_id("1010204");
		IAentLifeInsModel agentLife24 = ipersonalbusreportservice.queryTotal(agentLife);
		/*//分红寿险
		agentLife.setRisk_id("10102");
		List<IAentLifeInsModel> list2 = ipersonalbusreportservice.queryByRisk(agentLife);*/
		//投资连结产品
		agentLife.setRisk_id("10103");
		IAentLifeInsModel agentLife3 = ipersonalbusreportservice.queryTotal(agentLife);
		//投资连结产品-其中：年金保险
		agentLife.setRisk_id("1010301");
		IAentLifeInsModel  agentLife31 = ipersonalbusreportservice.queryTotal(agentLife);
		//万能寿险
		agentLife.setRisk_id("10104");
		IAentLifeInsModel agentLife4 = ipersonalbusreportservice.queryTotal(agentLife);
		//万能寿险-其中：年金保险
		agentLife.setRisk_id("1010401");
		IAentLifeInsModel agentLife41 = ipersonalbusreportservice.queryTotal(agentLife);
		//意外伤害险小计
		agentLife.setRisk_id("105");
		IAentLifeInsModel agentLife5 = ipersonalbusreportservice.queryTotal(agentLife);
		//意外伤害险小计-1、一年期以内业务
		agentLife.setRisk_id("10501");
		IAentLifeInsModel agentLife51 = ipersonalbusreportservice.queryTotal(agentLife);
		//意外伤害险小计-2、一年期业务
		agentLife.setRisk_id("10502");
		IAentLifeInsModel agentLife52 = ipersonalbusreportservice.queryTotal(agentLife);
		//意外伤害险小计-3、一年期以上业务
		agentLife.setRisk_id("10503");
		IAentLifeInsModel agentLife53 = ipersonalbusreportservice.queryTotal(agentLife);
		//健康险小计
		agentLife.setRisk_id("106");
		IAentLifeInsModel agentLife6 = ipersonalbusreportservice.queryTotal(agentLife);
		//健康险小计-1、短期健康险
		agentLife.setRisk_id("10601");
		IAentLifeInsModel agentLife61 = ipersonalbusreportservice.queryTotal(agentLife);
		//健康险小计-1、短期健康险
		agentLife.setRisk_id("10602");
		IAentLifeInsModel agentLife62 = ipersonalbusreportservice.queryTotal(agentLife);
		//其他
		agentLife.setRisk_id("10700");
		IAentLifeInsModel agentLife7 = ipersonalbusreportservice.queryTotal(agentLife);
		//汇总
		agentLife.setRisk_id("10");
		IAentLifeInsModel agentLife8 = ipersonalbusreportservice.queryTotal(agentLife);
		
		pageCount.setPage(true);
		createSheet(sheet, "一、寿险小计",1, agentLife0);
		createSheet(sheet, "（一）普通寿险",2, agentLife1);
		//createSheets(sheet,3,list1);
		createSheet(sheet, "    1、定期寿险",3, agentLife11);
		createSheet(sheet, "    2、两全寿险",4, agentLife12);
		createSheet(sheet, "    3、终身寿险",5, agentLife13);
		createSheet(sheet, "    4、年金寿险",6, agentLife14);
		createSheet(sheet,"（二）分红寿险",7,agentLife2);
		//createSheets(sheet,4+list1.size(),list2);
		createSheet(sheet, "    1、定期寿险",8, agentLife21);
		createSheet(sheet, "    2、两全寿险",9, agentLife22);
		createSheet(sheet, "    3、终身寿险",10, agentLife23);
		createSheet(sheet, "    4、年金寿险",11, agentLife24);
		createSheet(sheet,"（三）投资连结产品",12,agentLife3);
		createSheet(sheet, "    其中：年金保险",13, agentLife31);
		//createSheets(sheet,5+list1.size()+list2.size(),list3);
		createSheet(sheet,"（四）万能寿险",14,agentLife4);
		createSheet(sheet, "    其中：年金保险",15, agentLife41);
		//createSheets(sheet,6+list1.size()+list2.size()+list3.size(),list4);
		createSheet(sheet,"二、意外伤害险小计",16,agentLife5);
		createSheet(sheet, "    1、一年期以内业务",17, agentLife51);
		createSheet(sheet, "    2、一年期业务",18, agentLife52);
		createSheet(sheet, "    3、一年期以上业务",19, agentLife53);
		//createSheets(sheet,7+list1.size()+list2.size()+list3.size()+list4.size(),list5);
		createSheet(sheet,"三、健康险小计",20,agentLife6);
		createSheet(sheet, "    1、短期健康险",21, agentLife61);
		createSheet(sheet, "    2、长期健康险",22, agentLife62);
		//createSheets(sheet,8+list1.size()+list2.size()+list3.size()+list4.size()+list5.size(),list6);
		createSheet(sheet,"四、其他",23,agentLife7);
		createSheet(sheet,"总计",24,agentLife8);
	
		// 设置response 这样就可以前台弹出框进行下载了
		String fileName = URLEncoder.encode("代理人身险业务表查询结果导出.xls", "UTF-8");
		ExcelUtils excelUtils = new ExcelUtils();
		excelUtils.writeExcel(list, currentRowNum, currentColNum, wb, sheet, fileName, res);
	}
	public void createSheet(HSSFSheet sheet, String riskname,int row, IAentLifeInsModel agentLife){
		Row row1 = sheet.createRow(row+2);
		row1.createCell(0).setCellValue(riskname);
		row1.createCell(1).setCellValue(row);
		row1.createCell(2).setCellValue(agentLife.getTotal_policy_count().doubleValue());
		row1.createCell(3).setCellValue(agentLife.getTotal_policycount_sum().doubleValue());
		row1.createCell(4).setCellValue(agentLife.getTotal_amount().doubleValue());
		row1.createCell(5).setCellValue(agentLife.getTotal_amount_sum().doubleValue());
		row1.createCell(6).setCellValue(agentLife.getTotal_newpolfee().doubleValue());
		row1.createCell(7).setCellValue(agentLife.getTotal_newpolicyfee_sum().doubleValue());
		row1.createCell(8).setCellValue(agentLife.getTotal_renewfee().doubleValue());
		row1.createCell(9).setCellValue(agentLife.getTotal_renewalfee_sum().doubleValue());
		row1.createCell(10).setCellValue(agentLife.getTotal_permium().doubleValue());
		row1.createCell(11).setCellValue(agentLife.getTotal_premium_sum().doubleValue());
		row1.createCell(12).setCellValue(agentLife.getTotal_newagent().doubleValue());
		row1.createCell(13).setCellValue(agentLife.getTotal_new_agcommiss_sum().doubleValue());
		row1.createCell(14).setCellValue(agentLife.getTotal_renewagent().doubleValue());
		row1.createCell(15).setCellValue(agentLife.getTotal_renew_agcommiss_sum().doubleValue());
		row1.createCell(16).setCellValue(agentLife.getTotal_autonewpolfee().doubleValue());
		row1.createCell(17).setCellValue(agentLife.getTotal_autonewfee_sum().doubleValue());
		row1.createCell(18).setCellValue(agentLife.getTotal_auto_renewfee().doubleValue());
		row1.createCell(19).setCellValue(agentLife.getTotal_autorenewfee_sum().doubleValue());
		row1.createCell(20).setCellValue(agentLife.getTotal_newagcommiss().doubleValue());
		row1.createCell(21).setCellValue(agentLife.getTotal_autonew_agcommiss_sum().doubleValue());
		row1.createCell(22).setCellValue(agentLife.getTotal_renewplofee().doubleValue());
		row1.createCell(23).setCellValue(agentLife.getTotal_autorenew_agcommiss_sum().doubleValue());
		row1.createCell(24).setCellValue(agentLife.getTotal_tdnewplofee().doubleValue());
		row1.createCell(25).setCellValue(agentLife.getTotal_tdnewfee_sum().doubleValue());
		row1.createCell(26).setCellValue(agentLife.getTotal_tdrenewfee().doubleValue());
		row1.createCell(27).setCellValue(agentLife.getTotal_tdrenew_sum().doubleValue());
		row1.createCell(28).setCellValue(agentLife.getTotal_newploagcommiss().doubleValue());
		row1.createCell(29).setCellValue(agentLife.getTotal_tdnew_agcommiss_sum().doubleValue());
		row1.createCell(30).setCellValue(agentLife.getTotal_renewagcomiss().doubleValue());
		row1.createCell(31).setCellValue(agentLife.getTotal_tdrenew_agcommiss_sum().doubleValue());
	}
	
	public void createSheets(HSSFSheet sheet,int row, List<IAentLifeInsModel> relist){
		for (int i = 0; i < relist.size(); i++) {
			Row row1 = sheet.createRow(row+i);
			AentLifeInsModel m = (AentLifeInsModel) relist.get(i);
			row1.createCell(1) .setCellValue(i+row);
			if(m.getRisk_name()!=null){
				row1.createCell(0) .setCellValue(m.getRisk_name());
			}else{
				row1.createCell(0) .setCellValue("");
			}
			if(m.getTotal_newpolfee()!=null){
				row1.createCell(2) .setCellValue(m.getTotal_newpolfee().doubleValue());
			}else{
				row1.createCell(2) .setCellValue(0);
			}
			if(m.getTotal_renewfee()!=null){
				row1.createCell(3) .setCellValue(m.getTotal_renewfee().doubleValue());
			}else{
				row1.createCell(3) .setCellValue(0);
			}
			if(m.getTotal_permium()!=null){
				row1.createCell(4) .setCellValue(m.getTotal_permium().doubleValue());
			}else{
				row1.createCell(4) .setCellValue(0);
			}
			if(m.getTotal_newagent()!=null){
				row1.createCell(5) .setCellValue(m.getTotal_newagent().doubleValue());
			}else{
				row1.createCell(5) .setCellValue(0);
			}
			if(m.getTotal_renewagent()!=null){
				row1.createCell(6) .setCellValue(m.getTotal_renewagent().doubleValue());
			}else{
				row1.createCell(6) .setCellValue(0);
			}
			if(m.getTotal_autonewpolfee()!=null){
				row1.createCell(7) .setCellValue(m.getTotal_autonewpolfee().doubleValue());
			}else{
				row1.createCell(7) .setCellValue(0);
			}
			if(m.getTotal_auto_renewfee()!=null){
				row1.createCell(8) .setCellValue(m.getTotal_auto_renewfee().doubleValue());
			}else{
				row1.createCell(8) .setCellValue(0);
			}
			if(m.getTotal_newagcommiss()!=null){
				row1.createCell(9) .setCellValue(m.getTotal_newagcommiss().doubleValue());
			}else{
				row1.createCell(9) .setCellValue(0);
			}
			
			if(m.getTotal_renewplofee()!=null){
				row1.createCell(10).setCellValue(m.getTotal_renewplofee().doubleValue());
			}else{
				row1.createCell(10).setCellValue(0);
			}
			if(m.getTotal_tdnewplofee()!=null){
				row1.createCell(11).setCellValue(m.getTotal_tdnewplofee().doubleValue());
			}else{
				row1.createCell(11).setCellValue(0);
			}		
			if(m.getTotal_tdrenewfee()!=null){
				row1.createCell(12).setCellValue(m.getTotal_tdrenewfee().doubleValue());
			}else{
				row1.createCell(12).setCellValue(0);
			}
			if(m.getTotal_newploagcommiss()!=null){
				row1.createCell(13).setCellValue(m.getTotal_newploagcommiss().doubleValue());
			}else{
				row1.createCell(13).setCellValue(0);
			}
			
			if(m.getTotal_renewagcomiss()!=null){
				row1.createCell(14).setCellValue(m.getTotal_renewagcomiss().doubleValue());
			}else{
				row1.createCell(14).setCellValue("");
			}
			
		}
	}
	
	
}
