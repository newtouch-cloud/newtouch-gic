/**
 * Copyright 2003-2010 UFIDA Software Engineering Co., Ltd. 
 */
package com.ca.cacore.rsss.webapp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.ca.cacore.rsss.model.vo.BusinessReportVOModel1;
import com.ca.cacore.rsss.model.vo.BusinessReportVOModel2;
import com.ca.cacore.rsss.model.vo.HoldReportVOModel;

/**
 * 
* @since:    2014年1月16日   
* @author    ZhangChen
* @description:代理结构业务汇总表(一)导出excel模板
 */
public class ExportBusinessReport {
	
	//导出保险代理机构业务汇总表(一)
    public void doExportBusinessReport1(OutputStream os, List<BusinessReportVOModel1> list)
            throws ServletException, IOException {
        try {
            WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
            WritableSheet wsheet = wbook.createSheet("代理结构业务汇总表(一)",
                    0); // sheet名称
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat wcfFormat = new WritableCellFormat(bold);
            wcfFormat.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
            wcfFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcfFormat.setBorder(Border.ALL, BorderLineStyle.THIN); 
             // 设置行的高度
            int rowIndex = 0; //行
            int columnIndex = 0; //列
            
            //合并单元格
            wsheet.mergeCells(0, 0, 1, 1);//险种/项目
            wsheet.mergeCells(2, 0, 2, 1);//行次
            wsheet.mergeCells(3, 0, 4, 0);//保单件数
            wsheet.mergeCells(5, 0, 6, 0);//保费金额
            wsheet.mergeCells(7, 0, 8, 0);//未解付保费
            wsheet.mergeCells(9, 0, 10, 0);//未解付保费
            wsheet.mergeCells(11, 0, 11, 1);//备注
            wsheet.mergeCells(0, 2, 0, 12);//财产保险类
            wsheet.mergeCells(0, 13, 0, 23);//人身保险类
            
            //写第一行
            columnIndex = 1;
            rowIndex = 0 ;
            wsheet.addCell(new Label(0, 0, "",wcfFormat));
            wsheet.addCell(new Label(2, rowIndex, "行次",wcfFormat));
            wsheet.addCell(new Label(3, rowIndex, "保险件数(件)",wcfFormat));//
            wsheet.addCell(new Label(5, rowIndex, "保费金额",wcfFormat));//
            wsheet.addCell(new Label(7, rowIndex, "未解付保费",wcfFormat));//
            wsheet.addCell(new Label(9, rowIndex, "代理手续费",wcfFormat));//
            wsheet.addCell(new Label(11, rowIndex, "备注",wcfFormat));//
            
            //第二行
            columnIndex = 3;
            rowIndex = 1 ;
            wsheet.addCell(new Label(columnIndex++, rowIndex, "本期",wcfFormat));//3
            wsheet.addCell(new Label(columnIndex++, rowIndex, "累计",wcfFormat));//4
            wsheet.addCell(new Label(columnIndex++, rowIndex, "本期",wcfFormat));//3
            wsheet.addCell(new Label(columnIndex++, rowIndex, "累计",wcfFormat));//4
            wsheet.addCell(new Label(columnIndex++, rowIndex, "本期",wcfFormat));//3
            wsheet.addCell(new Label(columnIndex++, rowIndex, "累计",wcfFormat));//4
            wsheet.addCell(new Label(columnIndex++, rowIndex, "本期",wcfFormat));//3
            wsheet.addCell(new Label(columnIndex++, rowIndex, "累计",wcfFormat));//4 
            
            //其他标题
            wsheet.addCell(new Label(0, 2, "财产保险类",wcfFormat));//
            wsheet.addCell(new Label(0, 13, "人身保险类",wcfFormat));//
            
            columnIndex = 1;
            rowIndex = 2 ;
            wsheet.addCell(new Label(columnIndex, rowIndex++, "企业财产保险",wcfFormat));//
            wsheet.addCell(new Label(columnIndex, rowIndex++, "机动车辆险",wcfFormat));//
            wsheet.addCell(new Label(columnIndex, rowIndex++, "家财险",wcfFormat));//
            wsheet.addCell(new Label(columnIndex, rowIndex++, "货运、船舶保险",wcfFormat));//
            wsheet.addCell(new Label(columnIndex, rowIndex++, "能源、核电站保险",wcfFormat));//
            wsheet.addCell(new Label(columnIndex, rowIndex++, "飞机、飞机责任和航天保险",wcfFormat));//
            wsheet.addCell(new Label(columnIndex, rowIndex++, "建筑、安装工程险",wcfFormat));//
            wsheet.addCell(new Label(columnIndex, rowIndex++, "责任、信用、保证和农业保险",wcfFormat));//
            wsheet.addCell(new Label(columnIndex, rowIndex++, "短期健康保险、意外险",wcfFormat));//
            wsheet.addCell(new Label(columnIndex, rowIndex++, "其他",wcfFormat));//
            wsheet.addCell(new Label(columnIndex, rowIndex++, "小计",wcfFormat));//
            wsheet.addCell(new Label(0, 24, "",wcfFormat));//总计前面的空格

           
            
            //填写数据             
            // lResult2 设计成序号
            WritableCellFormat wcfFormat123 = new WritableCellFormat();
            wcfFormat123.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); ////单元格中的内容垂直方向居中
            wcfFormat123.setBorder(Border.ALL, BorderLineStyle.THIN); 
                    
            //对财产保险类进行赋0值操作
            columnIndex = 2;
            rowIndex = 2;
            for(int x = 1 ;x < 13;x++){
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(x),wcfFormat123));//
            	wsheet.addCell(new Label(columnIndex++,rowIndex,"0",wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,"0",wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,"0.00",wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,"0.00",wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,"0.00",wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,"0.00",wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,"0.00",wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,"0.00",wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));//
                columnIndex = 2;
                rowIndex++;
            }
            
           // 12行开始对人身财产进行赋值操作
            columnIndex = 1;
            rowIndex = 13;
            for(int i=0 ; i <list.size();i++){
            	BusinessReportVOModel1 model = list.get(i);
            	wsheet.addCell(new Label(columnIndex++, rowIndex, model.getProduct_name(),wcfFormat));//险种名称 标题样式
                wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getLine()),wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getPeriod_num()),wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getTotal_num()),wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getPeriod_prem()),wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getTotal_prem()),wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getPeriod_unreceived_prem()),wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getTotal_unreceived_prem()),wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getPeriod_fee()),wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getTotal_fee()),wcfFormat123));//
                wsheet.addCell(new Label(columnIndex++,rowIndex,model.getRemark(),wcfFormat123));//
                if(i == 6){
                	columnIndex = 1;
                    rowIndex++;
                	wsheet.addCell(new Label(columnIndex++, rowIndex,"四、补充养老保险",wcfFormat));
                	columnIndex = 1;
                    rowIndex++;
                	wsheet.addCell(new Label(columnIndex++, rowIndex,"五、补充医疗保险",wcfFormat));
                	columnIndex = 1;
                    rowIndex++;
                	wsheet.addCell(new Label(columnIndex++, rowIndex,"六、其他",wcfFormat));
                	Integer line = model.getLine()+1;
                	columnIndex = 2;
                	rowIndex = 20;
                	for(int j = 0 ; j < 3 ;j++){
                        wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(line++),wcfFormat123));//
                        wsheet.addCell(new Label(columnIndex++,rowIndex,"0",wcfFormat123));//
                        wsheet.addCell(new Label(columnIndex++,rowIndex,"0",wcfFormat123));//
                        wsheet.addCell(new Label(columnIndex++,rowIndex,"0.00",wcfFormat123));//
                        wsheet.addCell(new Label(columnIndex++,rowIndex,"0.00",wcfFormat123));//
                        wsheet.addCell(new Label(columnIndex++,rowIndex,"0.00",wcfFormat123));//
                        wsheet.addCell(new Label(columnIndex++,rowIndex,"0.00",wcfFormat123));//
                        wsheet.addCell(new Label(columnIndex++,rowIndex,"0.00",wcfFormat123));//
                        wsheet.addCell(new Label(columnIndex++,rowIndex,"0.00",wcfFormat123));//
                        wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));//
                        columnIndex = 2;
                    	rowIndex++;
                	}
                	rowIndex--;
                }
                columnIndex = 1;
                rowIndex++;
                if(i==list.size()-1){ //总计的值等于小计
                	wsheet.addCell(new Label(columnIndex++, rowIndex,"总计",wcfFormat));//险种名称 标题样式
                    wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getLine()+1),wcfFormat123));//在小计的行基础上+1
                    wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getPeriod_num()),wcfFormat123));//
                    wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getTotal_num()),wcfFormat123));//
                    wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getPeriod_prem()),wcfFormat123));//
                    wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getTotal_prem()),wcfFormat123));//
                    wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getPeriod_unreceived_prem()),wcfFormat123));//
                    wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getTotal_unreceived_prem()),wcfFormat123));//
                    wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getPeriod_fee()),wcfFormat123));//
                    wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getTotal_fee()),wcfFormat123));//
                    wsheet.addCell(new Label(columnIndex++,rowIndex,model.getRemark(),wcfFormat123));//
                }
            }
           
                                        
            wbook.write();
            if (wbook != null) {
                wbook.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
  //导出保险代理机构业务汇总表(二)
    public void doExportBusinessReport2(OutputStream os, List<BusinessReportVOModel2> list)throws ServletException, IOException {
        try {
            WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
            WritableSheet wsheet = wbook.createSheet("导出保险代理机构业务汇总表(二)",
                    0); // sheet名称
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat wcfFormat = new WritableCellFormat(bold);
            wcfFormat.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
            wcfFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcfFormat.setBorder(Border.ALL, BorderLineStyle.THIN); 
            
            int rowIndex = 0; //行
            int columnIndex = 0; //列
            
            //合并单元格
            wsheet.mergeCells(0, 0, 0, 1);//机构名称
            wsheet.mergeCells(1, 0, 1, 1);//行次
            wsheet.mergeCells(2, 0, 3, 0);//分支机构个数
            wsheet.mergeCells(4, 0, 5, 0);//公司员工数
            wsheet.mergeCells(6, 0, 7, 0);//保单件数
            wsheet.mergeCells(8, 0, 9, 0);//保费金额
            wsheet.mergeCells(10, 0, 11, 0);//未解付保费
            wsheet.mergeCells(12, 0, 13, 0);//	代理手续费
            wsheet.mergeCells(14, 0, 15, 0);//	利润	
            wsheet.mergeCells(16, 0, 16, 1);//注册资本
            wsheet.mergeCells(17, 0, 17, 1);//资产总额
           // wsheet.mergeCells(18, 0, 18, 1);//
            
            //第一行
            columnIndex = 0;
            rowIndex = 0 ;
            wsheet.addCell(new Label(0, rowIndex, "机构名称",wcfFormat));//
            wsheet.addCell(new Label(1, rowIndex, "行次",wcfFormat));//
            wsheet.addCell(new Label(2, rowIndex, "分支机构（个）",wcfFormat));//
            wsheet.addCell(new Label(4, rowIndex, "公司员工数（人）",wcfFormat));//
            wsheet.addCell(new Label(6, rowIndex, "保单件数（件）",wcfFormat));//
            wsheet.addCell(new Label(8, rowIndex, "保费金额",wcfFormat));//
            wsheet.addCell(new Label(10, rowIndex, "未解付保费",wcfFormat));//
            wsheet.addCell(new Label(12, rowIndex, "代理手续费",wcfFormat));//
            wsheet.addCell(new Label(14, rowIndex, "利润",wcfFormat));//
            wsheet.addCell(new Label(16, rowIndex, "注册资本",wcfFormat));//
            wsheet.addCell(new Label(17, rowIndex, "资产总额",wcfFormat));//
            
            
            //第二行
            columnIndex = 2;
            rowIndex = 1 ;
            wsheet.addCell(new Label(columnIndex++, rowIndex, "现有机构个数",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "增减（+/-）",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "现有人数",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "同比（+/-）",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "本期",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "累计",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "本期",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "累计",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "本期",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "累计",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "本期",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "累计",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "本期",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "累计",wcfFormat));//

            //填写数据             
            // lResult2 设计成序号
            WritableCellFormat wcfFormat123 = new WritableCellFormat();
            wcfFormat123.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); ////单元格中的内容垂直方向居中
            wcfFormat123.setBorder(Border.ALL, BorderLineStyle.THIN); 
            
            
            //3行
            columnIndex = 0;
            rowIndex = 2;
            int line = 1; //行次
            for(int i=0;i<list.size();i++){
            	BusinessReportVOModel2 model =  list.get(i);            	
            	wsheet.addCell(new Label(columnIndex++,rowIndex,model.getBranch_name(),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,line++ +"",wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getBranch_num()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getBranch_taux()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getStaffNum()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getBranch_taux()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getPeriod_num()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getTotal_num()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getPeriod_prem()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getTotal_prem()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getPeriod_unreceived_prem()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getTotal_unreceived_prem()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getPeriod_fee()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getTotal_fee()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));
            	columnIndex = 0;
            	rowIndex++;
            	
            }
            
                                                
            wbook.write();
            if (wbook != null) {
                wbook.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //导出
    public void doExportHoldReport(OutputStream os, List<HoldReportVOModel> list)
            throws ServletException, IOException {
        try {
            WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
            WritableSheet wsheet = wbook.createSheet("专业保险中介机构业务人员持证情况报表",
                    0); // sheet名称
            WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat wcfFormat = new WritableCellFormat(bold);
            wcfFormat.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
            wcfFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcfFormat.setBorder(Border.ALL, BorderLineStyle.THIN); 
            
            int rowIndex = 0; //行
            int columnIndex = 0; //列
            
            //合并单元格
            wsheet.mergeCells(0, 0, 0, 1);//公司名称
            wsheet.mergeCells(1, 0, 3, 0);//所有员工
            wsheet.mergeCells(4, 0, 5, 0);//高级管理人员
            wsheet.mergeCells(6, 0, 7, 0);//业务人员
            wsheet.mergeCells(8, 0, 9, 0);//非业务人员
            
            //写第一行
           // wsheet.addCell(new Label(0, 0, "专业保险中介机构业务人员持证情况报表",wcfFormat));
            //第二行
            columnIndex = 0;
            rowIndex = 0 ;
            wsheet.addCell(new Label(0, rowIndex, "公司名称",wcfFormat));//
            wsheet.addCell(new Label(1, rowIndex, "所有员工",wcfFormat));//
            wsheet.addCell(new Label(4, rowIndex, "高级管理人员",wcfFormat));//
            wsheet.addCell(new Label(6, rowIndex, "业务人员",wcfFormat));//
            wsheet.addCell(new Label(8, rowIndex, "非业务人员",wcfFormat));//
            
            //第三行
            columnIndex = 1;
            rowIndex = 1 ;
            wsheet.addCell(new Label(columnIndex++, rowIndex, "人数",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "持证数",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "持证率",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "人数",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "持证数",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "人数",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "持证数",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "人数",wcfFormat));//
            wsheet.addCell(new Label(columnIndex++, rowIndex, "持证数",wcfFormat));//
            

            //填写数据             
            // lResult2 设计成序号
            WritableCellFormat wcfFormat123 = new WritableCellFormat();
            wcfFormat123.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); ////单元格中的内容垂直方向居中
            wcfFormat123.setBorder(Border.ALL, BorderLineStyle.THIN); 
                                 
           // 3行
            columnIndex = 0;
            rowIndex = 2;
            for(int i=0;i<list.size();i++){
            	HoldReportVOModel model = list.get(i);
            	wsheet.addCell(new Label(columnIndex++,rowIndex,model.getBranch_name(),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getSalesNum()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,toStr(model.getCertificateSales()),wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));
            	wsheet.addCell(new Label(columnIndex++,rowIndex,"",wcfFormat123));
            	columnIndex = 0;
            	rowIndex++;
            	
            }
                
            
                                                
            wbook.write();
            if (wbook != null) {
                wbook.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    protected String toStr(Double d){
    	DecimalFormat df = new DecimalFormat("###0.00#");//最多保留几位小数，就用几个#，最少位就用0来确定  
    	if(d==null){
    		return "0.00";
    	}
    	String s=df.format(d); 
    	
    	return s;
    }
    
    protected String toStr(Integer d){
    	DecimalFormat df = new DecimalFormat("###0");//最多保留几位小数，就用几个#，最少位就用0来确定  
    	if(d==null){
    		return "0";
    	}
    	String s=df.format(d); 
    	
    	return s;
    }
    
    protected String toStr(Object d){
    	DecimalFormat df = new DecimalFormat("###0.00#");//最多保留几位小数，就用几个#，最少位就用0来确定  
    	if(d==null){
    		return "0.00";
    	}
    	
    	String s=df.format(new Double((String)d)); 
    	
    	return s;
    }
    
}