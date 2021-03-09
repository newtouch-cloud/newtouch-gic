package com.newtouch.component.axis2.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.newtouch.component.axis2.common.Constant;
import com.newtouch.component.axis2.dao.IAxis2LogDao;
import com.newtouch.component.axis2.model.Axis2Log;
import com.newtouch.component.axis2.model.response.Reserve;
import com.newtouch.component.axis2.model.response.Return;
import com.newtouch.component.axis2.model.response.Root;
import com.newtouch.component.axis2.model.response.TransBody;
import com.newtouch.component.axis2.model.response.TransData;
import com.newtouch.component.axis2.model.response.TransHead;
import com.newtouch.core.context.SpringContext;
import com.thoughtworks.xstream.XStream;

/**
 * 
* @since:    2014年4月17日   
* @author    ZhangChen
* @description:封装返回报文xml格式
 */
public class ResponseXML {
	
	String xmlVersion = "<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n";//xml声明
	private XStream xstream = new XStream(); //XStream对象用于把对象转换成String型的xml
	private IAxis2LogDao axis2LogDao = (IAxis2LogDao)SpringContext.getBean("axis2LogDao");
	
	/**
	 * 
	* @param resultCode
	* @param resultMsg
	* @param transType
	* @param transDate
	* @param transTime
	* @param agentCode
	* @param agentLicNo
	* @param agentGroup
	* @return String
	* @description:xml]格式或参数格式校验失败时走这个方法
	 */
	public String responseError(String resultCode,String resultMsg,String transType,String transDate,String transTime,String agentCode,String agentLicNo, String agentGroup){
		//设置xml别名
		xstream.alias("Reserve", Reserve.class);
		xstream.alias("TransData", TransData.class);
		xstream.alias("TransHead", TransHead.class);
		xstream.alias("TransBody", TransBody.class);
		xstream.alias("Return", Return.class);
		xstream.alias("Root", Root.class);
		
		//设置xml各个节点的父子关系
		TransBody tb = new TransBody();//报文体节点
		tb.setAgentCode("");
		tb.setAgentName("");
		tb.setResv1("");
		tb.setResv2("");
		tb.setResv3("");
		tb.setResv4("");
		tb.setResv5("");
		
		Reserve r = new Reserve();//保留域节点
		r.setResv1("");
		r.setResv2("");
		r.setResv3("");
		r.setResv4("");
		r.setResv5("");
		
		Return retu = new Return();//返回信息节点
		retu.setCheckFlag(Constant.CHECKFLAG_FAILURE);//失败
		retu.setResultCode(resultCode);//返回的信息代码
		retu.setResultMsg(resultMsg);//返回的信息
		
		TransHead th = new TransHead();//报文头
		
		//检验transType的值如果是空或超过最大长度则设置为""
		if(transType != null && !"".equals(transType) && transType.length() <= 10){
			th.setTransType(transType);
		}else{
			th.setTransType("");
		}
		
		//如果数据传送日期有值则返回日期
		java.sql.Date  sqlDate = null;
		if(transDate != null && !"".equals(transDate) ){
			//日期格式转换
			SimpleDateFormat  sdf =   new SimpleDateFormat("yyyy-MM-dd");   
			try {
				java.util.Date  date  =  sdf.parse(transDate);          
				sqlDate  =  new java.sql.Date(date.getTime());
				th.setTransDate(transDate); //不抛异常的情况下把数据传送日期返回给客户端
			} catch (ParseException e) {
				th.setTransDate("");
			}
		}else{
			th.setTransDate("");//错误的时间格式不能入库date类型顾设置为""
		}
		
		//检验transTime的值如果是空或超过最大长度则设置为""
		if(transTime != null && !"".equals(transTime) && transTime.length() <= 8){
			th.setTransTime(transTime);
		}else{
			th.setTransTime("");//超过长度限制不能入库,直接设置为""
		}
		
		//设置返回处理日期和时间
		Date dealDate = new Date(System.currentTimeMillis());
		th.setDealDate(dealDate.toString());
		DateFormat df = DateFormat.getTimeInstance();//只显示出时分秒
		
		//使用xstream把java对象转换为xml格式
		th.setDealTime(df.format(new java.util.Date()));
		th.setReturn(retu);
		th.setReserve(r);
		
		TransData td = new TransData();
		td.setTransBody(tb);
		td.setTransHead(th);
		
		Root rt = new Root();//父节点
		rt.setTransData(td);
		
		//转成成xml字符串格式
		String xml = xstream.toXML(rt);
		
		//日志记录
		Axis2Log log = new Axis2Log(retu.getCheckFlag(),retu.getResultCode(),retu.getResultMsg(),transType,agentCode,
				agentLicNo, agentGroup, sqlDate,
				transTime, dealDate, th.getDealTime(),"querySaleInfo");
		axis2LogDao.insertAxis2Log(log);
		
		StringBuffer str = new StringBuffer("");
		str.append(xmlVersion);//拼接xml声明
		str.append(xml);//拼接报文
		return str.toString();
	}
	
	/**
	 * 
	* @param resultCode
	* @param resultMsg
	* @param transType
	* @param agentLicNo
	* @param agentGroup
	* @param transDate
	* @param transTime
	* @param agentCode
	* @param agentName
	* @return String 
	* @description: 成功或者对应参数都正确的时候走这个方法
	 */
	public String responseSuccess(String resultCode,String resultMsg,String transType,String agentLicNo, String agentGroup,String transDate,String transTime,String agentCode,String agentName){
		//设置xml别名
		xstream.alias("Reserve", Reserve.class);
		xstream.alias("TransData", TransData.class);
		xstream.alias("TransHead", TransHead.class);
		xstream.alias("TransBody", TransBody.class);
		xstream.alias("Return", Return.class);
		xstream.alias("Root", Root.class);
		
		//设置xml各个节点的父子关系
		TransBody tb = new TransBody();//报文体
		tb.setAgentCode(agentCode); //中介人员id
		tb.setAgentName(agentName);//中介人员姓名
		tb.setResv1("");
		tb.setResv2("");
		tb.setResv3("");
		tb.setResv4("");
		tb.setResv5("");
		
		Reserve r = new Reserve();//保留域
		r.setResv1("");
		r.setResv2("");
		r.setResv3("");
		r.setResv4("");
		r.setResv5("");
		
		Return retu = new Return();//返回信息
		retu.setCheckFlag(Constant.CHECKFLAG_SUCCESS);//成功
		retu.setResultCode(resultCode);//返回信息代码
		retu.setResultMsg(resultMsg); //返回信息
		
		TransHead th = new TransHead();//报文头
		th.setTransType(transType);
		th.setTransDate(transDate);  //使用接收过来的日期
		th.setTransTime(transTime);//使用接收过来的时间
		Date dealDate = new Date(System.currentTimeMillis());
		th.setDealDate(dealDate.toString()); //处理日期
		DateFormat df = DateFormat.getTimeInstance();//只显示出时分秒
		th.setDealTime(df.format(new java.util.Date()));//处理时间
		th.setReturn(retu);
		th.setReserve(r);
		
		TransData td = new TransData();
		td.setTransBody(tb);
		td.setTransHead(th);
		
		Root rt = new Root();//父节点
		rt.setTransData(td);
		
		//转成成xml字符串格式
		String xml = xstream.toXML(rt);
		
		//日期格式转换
		SimpleDateFormat  sdf =   new SimpleDateFormat("yyyy-MM-dd");   
		java.sql.Date  sqlDate = null;
		try {
			java.util.Date  date  =  sdf.parse(transDate);          
			sqlDate  =  new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}        
		
		//日志记录
		Axis2Log log = new Axis2Log(retu.getCheckFlag(),retu.getResultCode(),retu.getResultMsg(),th.getTransType(),tb.getAgentCode(),
				agentLicNo, agentGroup, sqlDate,
				th.getTransTime(), dealDate, th.getDealTime(),"querySaleInfo");
		axis2LogDao.insertAxis2Log(log);
		
		StringBuffer str = new StringBuffer("");
		str.append(xmlVersion);//拼接xml声明
		str.append(xml);//拼接报文
		return str.toString();
	}
	
}
