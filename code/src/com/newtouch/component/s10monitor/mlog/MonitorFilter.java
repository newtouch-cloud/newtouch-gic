package com.newtouch.component.s10monitor.mlog;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.newtouch.component.c11assistant.ThreadLocalHelper;

public class MonitorFilter implements Filter{
	
	public static int busId = 100;
	
	public void init(FilterConfig arg0) throws ServletException {
	}

	public void doFilter(ServletRequest request
			            ,ServletResponse response
			            ,FilterChain filterChain) throws IOException, ServletException{
		initLog(request);
		filterChain.doFilter(request, response);
		dumpLog(request);
	}

	public void destroy() {
		
	}
	
	private void initLog(ServletRequest request){
		MBuslog blog = new MBuslog();
		blog.sysId=1;
		blog.busId=busId++;
		blog.appType="webapp";
		blog.busCode="joinPerson";
		blog.userId="admin";
		blog.init(request);
		ThreadLocalHelper.set("MBuslog", blog);
	}
	
	private void dumpLog(ServletRequest request){
		MBuslog blog = (MBuslog)ThreadLocalHelper.get("MBuslog");
		MlogManager.dumpLog(blog);
	}
	
	

}
