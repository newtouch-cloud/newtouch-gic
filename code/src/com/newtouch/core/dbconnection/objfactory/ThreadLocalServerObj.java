package com.newtouch.core.dbconnection.objfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;

@Component("ThreadLocalServerObj")
public class ThreadLocalServerObj extends ThreadLocal<ServerObj> {
	@Autowired
	private ServerObj serverObj;

	public ServerObj initialValue() {
		return serverObj;
	}

}
