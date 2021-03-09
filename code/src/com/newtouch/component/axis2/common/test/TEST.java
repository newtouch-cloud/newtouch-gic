package com.newtouch.component.axis2.common.test;

import java.io.IOException;

/**
 * 
* @since:    2014年4月28日   
* @author    ZhangChen
* @description:使用wsdl2java生成客户端程序测试发送报文
 */
public class TEST {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		QuerySaleInfoStub stub = new QuerySaleInfoStub();
		QuerySaleInfoStub.QuerySaleInfo sh = new QuerySaleInfoStub.QuerySaleInfo();
		String xml = "<Root>"+
		  "<TransData>"+
	    "<TransHead>"+
	     "<TransType>211313</TransType>"+
	      "<TransDate>2014-016</TransDate>"+
	      "<TransTime>18:20:00</TransTime>"+
	      "<Reserve>"+
	        "<Resv1></Resv1>"+
	        "<Resv2></Resv2>"+
	        "<Resv3></Resv3>"+
	        "<Resv4></Resv4>"+
	        "<Resv5></Resv5>"+
	      "</Reserve>"+
	    "</TransHead>"+
	    "<TransBody>"+
	      "<AgentCode>1011</AgentCode>"+
	      "<AgentLicNo>101111</AgentLicNo>"+
	      "<AgentGroup>机构</AgentGroup>"+
	      "<Resv1></Resv1>"+
	      "<Resv2></Resv2>"+
	      "<Resv3></Resv3>"+
	      "<Resv4></Resv4>"+
	      "<Resv5></Resv5>"+
	    "</TransBody>"+
	  "</TransData>"+
	"</Root>";
		
		
		/*XStream xstream = new XStream();

		xstream.alias("Reserve", Reserve.class);
		xstream.alias("TransData", TransData.class);
		xstream.alias("TransHead", TransHead.class);
		xstream.alias("TransBody", TransBody.class);
		xstream.alias("Root", Root.class);

		
		Root root = (Root)xstream.fromXML(xml);
		System.out.println(root.getTransData().getTransHead().getTransTime());
		*/
		
		sh.setXml(xml);;
		
		System.out.println(stub.querySaleInfo(sh).get_return());
	}
}
