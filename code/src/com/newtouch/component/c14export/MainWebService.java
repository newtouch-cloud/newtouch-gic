package com.newtouch.component.c14export;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

public class MainWebService {
	public static String send(WsContent context) {
		// String url = StaticProperties.getProperty("WsMainServer.WSURL");
		String message="";
		Service service = new Service();
		Call call;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress("http://localhost:8080/ExcelApplication/services/MainService");
			QName qn = new QName("urn:Content", "Content");
			call.registerTypeMapping(WsContent.class, qn,new BeanSerializerFactory(WsContent.class, qn),new BeanDeserializerFactory(WsContent.class, qn));
			call.setReturnType(qn, WsContent.class);
			call.setOperationName(new QName("urn:Content", "doService"));
			call.addParameter("context", qn, ParameterMode.IN);
			call.setReturnClass(String.class);
			message= (String) call.invoke(new Object[] { context });
		} catch (ServiceException e) {
			message="请求失败";
			e.printStackTrace();
		} catch (RemoteException e) {
			message="请求失败";
			e.printStackTrace();
		}
		return message;
	}
}
