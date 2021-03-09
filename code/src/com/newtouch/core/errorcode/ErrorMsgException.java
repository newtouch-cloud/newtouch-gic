package com.newtouch.core.errorcode;

import java.util.ArrayList;
import java.util.List;

import com.newtouch.core.returnmsg.Message;

public class ErrorMsgException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Message> msgList = new ArrayList<Message>();

	public ErrorMsgException() {

	}

	public ErrorMsgException(Message msg) {
		this.msgList.add(msg);
	}

	public ErrorMsgException(String msg) {
		// this.msgList.add(msg);
	}

	public String getMessage() {
		String msg = "";
		for (Message msgObj : this.msgList) {
			msg += msgObj.getMsgInfo() + "\n";
		}
		return msg;
	}

	public void addMessage(Message msg) {
		this.msgList.add(msg);
	}
}
