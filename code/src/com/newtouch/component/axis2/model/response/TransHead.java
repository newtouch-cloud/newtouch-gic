package com.newtouch.component.axis2.model.response;

import java.io.Serializable;
import java.sql.Date;

public class TransHead implements Serializable {
	private String TransType;
	private String TransDate;
	private String TransTime;
	private String DealDate;
	private String DealTime;
	private Return Return;
	private Reserve Reserve;
	
	
	
	public String getTransType() {
		return TransType;
	}
	public void setTransType(String transType) {
		TransType = transType;
	}
	public String getTransDate() {
		return TransDate;
	}
	public void setTransDate(String transDate) {
		TransDate = transDate;
	}
	public void setDealDate(String dealDate) {
		DealDate = dealDate;
	}
	public String getTransTime() {
		return TransTime;
	}
	public void setTransTime(String transTime) {
		TransTime = transTime;
	}
	public Reserve getReserve() {
		return Reserve;
	}
	public void setReserve(Reserve reserve) {
		Reserve = reserve;
	}
	public String getDealDate() {
		return DealDate;
	}
	public String getDealTime() {
		return DealTime;
	}
	public void setDealTime(String dealTime) {
		DealTime = dealTime;
	}
	public Return getReturn() {
		return Return;
	}
	public void setReturn(Return return1) {
		Return = return1;
	}

	
}
