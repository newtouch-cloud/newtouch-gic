package com.newtouch.component.axis2.common.test;

import java.sql.Date;
import com.newtouch.component.axis2.model.reqeust.Reserve;
import com.newtouch.component.axis2.model.reqeust.Root;
import com.newtouch.component.axis2.model.reqeust.TransBody;
import com.newtouch.component.axis2.model.reqeust.TransData;
import com.newtouch.component.axis2.model.reqeust.TransHead;
import com.thoughtworks.xstream.XStream;

/**
 * 
* @since:    2014年4月28日   
* @author    ZhangChen
* @description: 使用XStream测试解析报文
 */
public class Tests {
	
	public static void main(String[] args) {
		XStream xstream = new XStream();
		
		/*
		 * 设置别名,如果不设置xml中父节点名称会类的路径名称 
		 * 如Reserve节点会是com.newtouch.component.axis2.model.reqeust.Reserve
		 */
		xstream.alias("Reserve", Reserve.class);
		xstream.alias("TransData", TransData.class);
		xstream.alias("TransHead", TransHead.class);
		xstream.alias("TransBody", TransBody.class);
		xstream.alias("Root", Root.class);
		
		//常见节点元素设置各种依赖关系
		TransBody tb = new TransBody();
		tb.setAgentCode("113313");
		tb.setAgentLicNo("313");
		tb.setAgentGroup("121");
		tb.setResv1("");
		tb.setResv2("");
		tb.setResv3("");
		tb.setResv4("");
		tb.setResv5("");
		
		Reserve r = new Reserve();
		r.setResv1("");
		r.setResv2("");
		r.setResv3("");
		r.setResv4("");
		r.setResv5("");
		
		TransHead th = new TransHead();
		th.setTransDate(new Date(System.currentTimeMillis()).toString());
		th.setTransTime("18:20:00");
		th.setTransType("211311311133");
		th.setReserve(r);
		
		TransData td = new TransData();
		td.setTransBody(tb);
		td.setTransHead(th);
		
		Root rt = new Root();
		rt.setTransData(td);
		
		String xml = xstream.toXML(rt);
		
		System.out.println(xml);
		
		
		
		
		
		/*Reserve r2 = (Reserve)xstream.fromXML(xml);
		
		System.out.println(r2.getResv1());*/
		/*String fff= "<Reserve>"+
		  "<Resv1>11</Resv1>"+
		  "<Resv2>22</Resv2>"+
		  "<Resv3>33</Resv3>"+
		  "<Resv4>44</Resv4>"+
		  "<Resv5>55</Resv5>"+
		"</Reserve>";
		TEST s = new TEST();
		System.out.println(s.toString(fff,xstream));*/
	}

}
