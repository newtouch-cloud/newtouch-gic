package com.newtouch.component.axis2.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Date;
import java.text.DateFormat;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;

import com.newtouch.component.axis2.dao.IAxis2LogDao;
import com.newtouch.component.axis2.dao.IFeeInfoDao;
import com.newtouch.component.axis2.model.Axis2Log;
import com.newtouch.component.axis2.model.FeeInfoVoModel;
import com.newtouch.core.context.SpringContext;

/**
* @since:    2014年7月1日   
* @author    wangjianqiang
* @description:服务类 QuerySaleInfo 对应的方法类
 */
public class FeeInfoUtil {
	
	private IFeeInfoDao feeInfoDao = (IFeeInfoDao)SpringContext.getBean("feeInfoDao");	
	private IAxis2LogDao axis2LogDao = (IAxis2LogDao)SpringContext.getBean("axis2LogDao");
	
	/**
	 * 
	* @param xml
	* @return String
	* @description: 处理传入报文,并返回成功/失败报文
	 */
	public String analysisXML(String xml){
			
		StringReader read = new StringReader(xml);
		InputSource source = new InputSource(read);
		SAXBuilder sb = new SAXBuilder();

		Document doc=null;
		try {
			doc = sb.build(source);
		} catch (Exception e) {
			System.out.println("┌---------------------------CheckFeeInfo服务发生异常信息开始--------------------------┐");
			System.out.println(e.getMessage());
			System.out.println("└---------------------------CheckFeeInfo服务异常信息结束-----------------------------┘");
			//JDOM解析报文失败，返回错误信息
			return returnINfoStr("jiexi",xml);
		} 
		Element root = doc.getRootElement();
		Element transData = root.getChild("TransData");
		Element transHead = transData.getChild("TransHead");
		Element transBody = transData.getChild("TransBody");
		//处理时间写入
		DateFormat df = DateFormat.getTimeInstance();//只显示出时分秒
        Element dealDate=new Element("DealDate");
        dealDate.setText(new Date(System.currentTimeMillis()).toString());
        transHead.addContent(dealDate);
        
        Element dealTime=new Element("DealTime");
        dealTime.setText(df.format(new java.util.Date()));
        transHead.addContent(dealTime);
        
        int contNum=Integer.parseInt(transBody.getChild("ContNum").getText());
        List contInfos = transBody.getChildren("ContInfo");
		if(contInfos!=null&&contInfos.size()>0&&contInfos.size()==contNum){
			int contSucc=0;//成功的条数
			double contSuccPrem=0l;//成功的金额
			int contFail=0;//失败的条数
			double contFailPrem=0l;//失败的金额
			boolean flag=true;//用来判断是否比对错误
			for (int i = 0; i < contInfos.size(); i++) {
				flag=true;
				Element contInfo = (Element)contInfos.get(i);
				String contNo=contInfo.getChild("ContNo").getText();//保单号
				String batchPolNo=contInfo.getChild("BatchPolNo").getText();//批单号
				int pCount=Integer.parseInt(contInfo.getChild("PCount").getText());//保单期数
				int pSerialNo=Integer.parseInt(contInfo.getChild("PSerialNo").getText());//缴费期次
//				String dataType=contInfo.getChild("DataType").getText();//数据类型  (01-新单 空批单号 02-续期 11-加保 12-减保 13-退保)
				String agentCode=contInfo.getChild("AgentCode").getText();//中介人员代码
				double prem=Double.valueOf(contInfo.getChild("Prem").getText()).doubleValue();//保费
				double feeRate=Double.valueOf(contInfo.getChild("FeeRate").getText()).doubleValue();//手续费比例
				double fee=Double.valueOf(contInfo.getChild("Fee").getText()).doubleValue();//手续费金额z
				//营改增后的修改
				double taxnetpremium=Double.valueOf(contInfo.getChild("Taxnetpremium").getText()).doubleValue();//含税保费
				double taxfnum=Double.valueOf(contInfo.getChild("Taxfnum").getText()).doubleValue();//含税手续费
				double taxvalnetpremium=Double.valueOf(contInfo.getChild("Taxvalnetpremium").getText()).doubleValue();//保费税额
				double taxvalfnum=Double.valueOf(contInfo.getChild("Taxvalfnum").getText()).doubleValue();//手续费税额
				/*double traveltax=Double.valueOf(contInfo.getChild("Traveltax").getText()).doubleValue();//车船税*/
				String feeCurreny=contInfo.getChild("FeeCurreny").getText();//手续费币别代码
                String comCode=contInfo.getChild("ComCode").getText();//机构代码
                //String comWarn=contInfo.getChild("ComWarn").getText();//佣金提醒字段
				FeeInfoVoModel model=new FeeInfoVoModel();
				model.setContNo(contNo);
				model.setBatchPolNo(batchPolNo);
				model.setpSerialNo(pSerialNo);
				FeeInfoVoModel feeInfo=feeInfoDao.feeInfo(model);
				//手续费对账判断代理人佣金
				/*if(agentCode==null || agentCode==""){
					contInfo.getChild("ComWarn").setAttribute("Flag","0");
				}else{
					FeeInfoVoModel model1=new FeeInfoVoModel();
					model1.setAgentCode(agentCode);
					FeeInfoVoModel agentInfo=feeInfoDao.agentInfo(model1);
				    contInfo.getChild("ComWarn").setAttribute("Flag","1");
				}*/
				if(feeInfo==null){
					flag=false;
					contInfo.getChild("ContNo").setAttribute("Flag", "0");
					contInfo.getChild("PSerialNo").setAttribute("Flag", "0");
					if(!"".equals(batchPolNo)){
						contInfo.getChild("BatchPolNo").setAttribute("Flag", "0");
					}
					contInfo.setAttribute("Flag", "1");
					contFail=contFail+1;
					contFailPrem=contFailPrem+fee;
				}else{
					contInfo.getChild("ContNo").setAttribute("Flag", "1");
					contInfo.getChild("PSerialNo").setAttribute("Flag", "1");
					if(!"".equals(batchPolNo)){
						contInfo.getChild("BatchPolNo").setAttribute("Flag", "1");
					}
					if(feeInfo.getpCount()==pCount){
						contInfo.getChild("PCount").setAttribute("Flag", "1");
					}else{
						flag=false;
						contInfo.getChild("PCount").setAttribute("Flag", "0");
					}
					/*if(feeInfo.getAgentCode()!=null&&feeInfo.getAgentCode().equals(agentCode)){
						contInfo.getChild("AgentCode").setAttribute("Flag", "1");
					}else{
						flag=false;
						contInfo.getChild("AgentCode").setAttribute("Flag", "0");
					}*/
					contInfo.getChild("AgentCode").setAttribute("Flag","1");
					if(feeInfo.getPrem()==prem){
						contInfo.getChild("Prem").setAttribute("Flag", "1");
					}else{
						flag=false;
						contInfo.getChild("Prem").setAttribute("Flag", "0");
					}
//					if(feeInfo.getFeeRate()==feeRate){
//						contInfo.getChild("FeeRate").setAttribute("Flag", "1");
//					}else{
//						flag=false;
//						contInfo.getChild("FeeRate").setAttribute("Flag", "0");
//					}
					if(feeInfo.getFee()==fee){
						contInfo.getChild("Fee").setAttribute("Flag", "1");
					}else{
						flag=false;
						contInfo.getChild("Fee").setAttribute("Flag", "0");
					}
					//营改增修改
					if(feeInfo.getTaxnetPremium()==taxnetpremium){
						contInfo.getChild("Taxnetpremium").setAttribute("Flag","1");
					}else{
						flag=false;
						contInfo.getChild("Taxnetpremium").setAttribute("Flag","0");
					}
					if(feeInfo.getTaxFnum()==taxfnum){
						contInfo.getChild("Taxfnum").setAttribute("Flag","1");
					}else{
						flag=false;
						contInfo.getChild("Taxfnum").setAttribute("Flag","0");
					}
					if(feeInfo.getTaxvalNetpremium()==taxvalnetpremium){
						contInfo.getChild("Taxvalnetpremium").setAttribute("Flag","1");
					}else{
						flag=false;
						contInfo.getChild("Taxvalnetpremium").setAttribute("Flag","0");
					}
					if(feeInfo.getTaxvalFnum()==taxvalfnum){
						contInfo.getChild("Taxvalfnum").setAttribute("Flag","1");
					}else{
						flag=false;
						contInfo.getChild("Taxvalfnum").setAttribute("Flag","0");
					}
					/*if(feeInfo.getTravelTax()==traveltax){
						contInfo.getChild("Traveltax").setAttribute("Flag","1");
					}else{
						flag=false;
						contInfo.getChild("Traveltax").setAttribute("Flag","0");
					}*/
					//xiugai
					 if(feeInfo.getFeeCurreny()!=null&&feeInfo.getFeeCurreny().equals(feeCurreny)){
						contInfo.getChild("FeeCurreny").setAttribute("Flag", "1");
					}else{
						flag=false;
						contInfo.getChild("FeeCurreny").setAttribute("Flag", "0");
					}
					if(feeInfo.getComCode()!=null&&feeInfo.getComCode().equals(comCode)){
						contInfo.getChild("ComCode").setAttribute("Flag", "1");
					}else{
						flag=false;
						contInfo.getChild("ComCode").setAttribute("Flag", "0");
					}
					
					if(flag==false){
						contInfo.setAttribute("Flag", "2");
						contFail=contFail+1;
						contFailPrem=contFailPrem+fee;
					}else{
						contInfo.setAttribute("Flag", "4");
						contSucc=contSucc+1;
						contSuccPrem=contSuccPrem+fee;
					}
				}
			}
			
			//格式化小数位
			java.text.DecimalFormat   format=new   java.text.DecimalFormat("#.##");   

			
			Element contSuccEle=new Element("ContSucc");
			contSuccEle.setText(contSucc+"");
			transBody.addContent(contSuccEle);
			
			Element contSuccPremEle=new Element("ContSuccPrem");
			contSuccPremEle.setText(format.format(contSuccPrem)+"");
			transBody.addContent(contSuccPremEle);
			
			Element contFailEle=new Element("ContFail");
			contFailEle.setText(contFail+"");
			transBody.addContent(contFailEle);
			
			Element contFailPremEle=new Element("ContFailPrem");
			contFailPremEle.setText(format.format(contFailPrem)+"");
			transBody.addContent(contFailPremEle);
			
			 //生成return模块儿
	        Element teturn=new Element("Return");
	        Element checkFlag=new Element("CheckFlag");
	        checkFlag.setText("1");
	        if(contFail!=0){
	        	checkFlag.setText("0");
	        }
	        teturn.addContent(checkFlag);
	        Element resultCode=new Element("ResultCode");
	        resultCode.setText("04");
	        if(contFail!=0){
	        	resultCode.setText("03");
	        }
	        teturn.addContent(resultCode);
	        transHead.addContent(teturn);
			
			return makeReturnXml(doc,xml);
		}else{
			//JDOM解析报文失败，返回错误信息
			return returnINfoStr("shuju",xml);
		}
	}
	
	private String returnINfoStr(String type,String xml){
		//jdom解析整个报文失败时生成返回报文，逐级生成
        Element root = new Element("root");
        Element transData=new Element("TransData");
        root.addContent(transData);
        Element transHead=new Element("TransHead");
        
        //设置返回处理日期和时间
		DateFormat df = DateFormat.getTimeInstance();//只显示出时分秒
        Element dealDate=new Element("DealDate");
        dealDate.setText(new Date(System.currentTimeMillis()).toString());
        transHead.addContent(dealDate);
        Element dealTime=new Element("DealTime");
        dealDate.setText(df.format(new java.util.Date()));
        transHead.addContent(dealTime);
        //生成return模块儿
        Element teturn=new Element("Return");
        Element checkFlag=new Element("CheckFlag");
        checkFlag.setText("0");
        teturn.addContent(checkFlag);
        
        Element resultCode=new Element("ResultCode");
        resultCode.setText("05");//建议新增类型，报文解析失败
        if("shuju".equals(type)){
        	resultCode.setText("03");
        }
        teturn.addContent(resultCode);
        
        Element resultMsg=new Element("ResultMsg");
        resultMsg.setText("用jdom方式解析整个报文时失败，请检查报文格式");
        if("shuju".equals(type)){
        	resultCode.setText("数据总条数'contNum'和明细中数据总条数不一致，请检查！ ");
        }
        teturn.addContent(resultMsg);
        
        transHead.addContent(teturn);
        transData.addContent(transHead);
        Document doc = new Document(root);
        return makeReturnXml(doc,xml);
	}
	
	private String makeReturnXml(Document doc,String xml){
		try {
			Format format = Format.getPrettyFormat();     
			format.setEncoding("GBK");// 设置xml文件的字符为UTF-8，解决中文问题     
			XMLOutputter xmlout = new XMLOutputter(format);     
			ByteArrayOutputStream bo = new ByteArrayOutputStream();     
			xmlout.output(doc, bo);   
			//将核心访问的报文以及返回的报文存到数据库中
			Element root = doc.getRootElement();
			Element transData = root.getChild("TransData");
			Element transHead = transData.getChild("TransHead");
			//日志记录
			Axis2Log log = new Axis2Log(transHead.getChild("Return").getChild("CheckFlag").getText(),
					transHead.getChild("Return").getChild("ResultCode").getText(),"",transHead.getChild("TransType").getText(),"",
					"", "", null,
					"", null, "","checkFeeInfo",xml,bo.toString());
			axis2LogDao.insertAxis2Log(log);
			
			return bo.toString();
		} catch (IOException e) {
			System.out.println("┌---------------------------JDOM解析报文失败，生成返回报文报错，请见FeeInfoUtil文件---------┐");
			e.printStackTrace();
		} 
		return null;
	}

}
