package com.newtouch.component.axis2.model.reqeust;

import java.io.Serializable;
import java.sql.Date;

public class TransHead implements Serializable {
	private String TransType;
	private String TransDate;
	private String TransTime;
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

	
	
	
	

	
}
