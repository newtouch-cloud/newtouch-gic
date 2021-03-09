package com.newtouch.component.axis2.model.response;

import java.io.Serializable;

public class TransData implements Serializable {
	private TransHead TransHead;
	private TransBody TransBody;
	
	public TransHead getTransHead() {
		return TransHead;
	}
	public void setTransHead(TransHead transHead) {
		TransHead = transHead;
	}
	public TransBody getTransBody() {
		return TransBody;
	}
	public void setTransBody(TransBody transBody) {
		TransBody = transBody;
	}

	

	
}
