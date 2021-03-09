package com.newtouch.component.axis2.model.reqeust;

import java.io.Serializable;

public class Root implements Serializable {
	
	private TransData TransData;
	

	public TransData getTransData() {
		return TransData;
	}

	public void setTransData(TransData transData) {
		TransData = transData;
	}

}
