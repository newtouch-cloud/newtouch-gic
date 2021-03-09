package com.newtouch.component.s10monitor.mlog;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.newtouch.component.c11assistant.ServletHelper;

public class MRequestHeader implements java.io.Serializable{

	private static final long serialVersionUID = -2900728970061226879L;
	public String userAgent;
	public String clientIp;
	public String URL;
	public String queryString;
	
	public MRequestHeader(){};
	public MRequestHeader(ServletRequest r){
		this((HttpServletRequest)r);
	}
	public MRequestHeader(HttpServletRequest r){
		init(r);
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public void init(ServletRequest r){
		init((HttpServletRequest)r);
	}
	public void init(HttpServletRequest r){
		this.userAgent=ServletHelper.getHttpClientAgent(r);
		this.clientIp=ServletHelper.getHttpClientIpAddr(r);
		this.URL=ServletHelper.getHttpRequestURL(r);
		this.queryString=ServletHelper.getHttpRequestQueryString(r);
	}
	
	public String getURLandQueryString(){
		return this.URL+"?"+this.queryString;
	}
	
	
}
