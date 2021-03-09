package com.newtouch.component.axis2.common.test;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
* @since:    2014年4月28日   
* @author    ZhangChen
* @description:使用axis 客户端调用程序Server和Call访问Webservice
 */
public class TestCall {
	
	public static void main(String[] args) {

	try{
		String url = "http://localhost:8080/CACore/services/QuerySaleInfo?wsdl";
		Service serv = new Service();

		Call call = null;
		call = (Call) serv.createCall();

		//下面这两段代码，是为了处理返回的特殊类型写的，如果只是简单地返回基本类型，可以不用写这两段代码;
		QName qn = new QName("http://service.axis2.component.newtouch.com", "querySaleInfo");  
		call.registerTypeMapping(String.class, qn,new org.apache.axis.encoding.ser.BeanSerializerFactory(String.class, qn),
		new org.apache.axis.encoding.ser.BeanDeserializerFactory(String.class, qn));

		call.setTargetEndpointAddress(new URL(url));

		//这里的new QName的URL,就是要指向的命名空间的名称了，这个URL地址在你的wsdl打开后可以看到的，上面有写着targetNamespace="http://www.foresee.com.cn/spiderweb/webservice",这个就是你的命名空间值了;
		call.setOperationName(qn);


		// 接口的参数
		call.addParameter("xml", org.apache.axis.Constants.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
		call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型  
		//call.setReturnType(qn);// 设置返回类型，这里的返回类型可以设为是String,如:call.setReturnType(org.apache.axis.Constants.XSD_STRING);// call.setUseSOAPAction(true);
		//call.setUseSOAPAction(true);
		call.setSOAPActionURI(url);
		String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n"+
				"<Root>"+
				  "<TransData>"+
			    "<TransHead>"+
			     "<TransType>211313</TransType>"+
			      "<TransDate>2014-04-18</TransDate>"+
			      "<TransTime>18:12:40</TransTime>"+
			      "<Reserve>"+
			        "<Resv1></Resv1>"+
			        "<Resv2></Resv2>"+
			        "<Resv3></Resv3>"+
			        "<Resv4></Resv4>"+
			        "<Resv5></Resv5>"+
			      "</Reserve>"+
			    "</TransHead>"+
			    "<TransBody>"+
			      "<AgentCode>010100000001026</AgentCode>"+
			      "<AgentLicNo>003</AgentLicNo>"+
			      "<AgentGroup></AgentGroup>"+
			      "<Resv1></Resv1>"+
		          "<Resv2></Resv2>"+
		          "<Resv3></Resv3>"+
		          "<Resv4></Resv4>"+
		          "<Resv5></Resv5>"+
			    "</TransBody>"+
			  "</TransData>"+
			"</Root>";
		String xml1 = (String) call.invoke(new Object[] {xml});
		System.out.println(xml1);
		}
		 catch (Exception e) {
				//
				        System.err.println(e.toString());

				 }
	}
	
	/*try {

    String endpoint = "http://192.168.18.132:8080/CACore/services/QuerySaleInfo?wsdl";
    		
    //直接引用远程的wsdl文件

   //以下都是套路 
    Service service = new Service();
    Call call = (Call) service.createCall();
    call.setTargetEndpointAddress(endpoint);
    call.setOperationName("querySaleInfo");//WSDL里面描述的接口名称
    //call.addParameter("xml", org.apache.axis.encoding.XMLType.XSD_DATE,
   // javax.xml.rpc.ParameterMode.IN);//接口的参数
    call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型  
    String temp = "测试人员";
    String result = (String)call.invoke(new Object[]{temp});
    //给方法传递参数，并且调用方法
    System.out.println("result is "+result);

}
//
catch (Exception e) {
//
    System.err.println(e.toString());

}*/

}
