package com.newtouch.component.s10monitor.mlog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class MBuslog implements java.io.Serializable{

	private static final long serialVersionUID = 1896819627950741315L;
	public int seqId;
	public int sysId;
	public int busId;
	public String appType;
	public String busCode;
	public String userId;
	public Date startTime = new Date();
	public Date endTime;
	public Date logtime = new Date();
	public String createuser="admin";
	public Date createdate=new Date();
	public String modifyuser="admin";
	public Date modifydate=new Date();
	public MRequestHeader mHeader = new MRequestHeader();

	public MBuslog(){};
	public MBuslog(ServletRequest r){
		this((HttpServletRequest)r);
	}
	public MBuslog(HttpServletRequest r){
		init(r);
	}
	public void init(HttpServletRequest r){
		this.mHeader.init(r);
	}
	public void init(ServletRequest r){
		this.mHeader.init(r);
	}

	
	public void finish(){
		this.endTime = new Date();
	}
	
	public int getSeqId() {
		return seqId;
	}

	public void setSeqId(int seqId) {
		this.seqId = seqId;
	}
	public int getSysId() {
		return sysId;
	}
	public void setSysId(int sysId) {
		this.sysId = sysId;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getBusCode() {
		return busCode;
	}
	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getLogtime() {
		return logtime;
	}
	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	public MRequestHeader getmHeader() {
		return mHeader;
	}
	public void setmHeader(MRequestHeader mHeader) {
		this.mHeader = mHeader;
	}


}
