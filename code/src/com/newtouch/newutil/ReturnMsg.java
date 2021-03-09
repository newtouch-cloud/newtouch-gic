package com.newtouch.newutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newtouch.core.returnmsg.CFMessageException;
import com.newtouch.core.returnmsg.Message;

public class ReturnMsg {

	private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

	private Map<String, Object> dataTable = new HashMap<String, Object>();

	private List<Message> msgList = new ArrayList<Message>();
	private List<Message> warnList = new ArrayList<Message>();
	private List<Message> detailedList = new ArrayList<Message>();
	private boolean successflag = true;
	private CFMessageException exception = new CFMessageException();

	public CFMessageException getException() {
		if (!msgList.isEmpty()) {
			for (Message msg : msgList)
				exception.addMessage(msg);
		}
		return exception;
	}

	public CFMessageException getWarn() {
		if (!warnList.isEmpty()) {
			for (Message msg : warnList)
				exception.addMessage(msg);
		}
		return exception;
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}

	public List<Message> getMsgList() {
		return msgList;
	}

	public List<Message> getWarnList() {
		return warnList;
	}

	public List<Message> getDtildMessage() {
		return this.detailedList;
	}

	private void setMessage(Message message) {
		msgList.add(message);
	}

	/**
	 * 
	 * @return true：成功(默认)<br>
	 *         false：失败
	 */
	public boolean isSuccessflag() {
		return successflag;
	}

	/**
	 * 
	 * @return true：存在警告信息<br>
	 *         false：无警告信息
	 */
	public boolean isWarning() {
		return !this.warnList.isEmpty();
	}

	private void setSuccessflag(boolean successflag) {
		this.successflag = successflag;
	}

	public Map<String, Object> getDataTable() {
		return dataTable;
	}

	public void setDataTable(Map<String, Object> dataTable) {
		this.dataTable = dataTable;
	}

	public void setSuccessMessage(Message message) {
		this.setSuccessflag(true);
		this.clearError();
		this.setMessage(message);
	}

	public void setFailMessage(Message message) {
		this.setSuccessflag(false);
		this.setMessage(message);
	}

	public void setFailMessage(List<Message> msgList) {
		for (Message msg : msgList) {
			this.setFailMessage(msg);
		}
	}

	public void setSuccessMessage(List<Message> msgList) {
		for (Message msg : msgList) {
			this.setSuccessMessage(msg);
		}
	}

	public void setWarnMessage(Message message) {
		this.warnList.add(message);
	}

	public void setWarnMessage(List<Message> msgList) {
		for (Message msg : msgList) {
			this.warnList.add(msg);
		}
	}

	public void setDtildMessage(Message message) {
		this.detailedList.add(message);
	}

	public void setDtildMessage(List<Message> msgList) {
		for (Message msg : msgList) {
			this.detailedList.add(msg);
		}
	}

	private void clearError() {
		if (msgList != null && !msgList.isEmpty()) {
			msgList.clear();
		}
	}

	public void clear() {
		if (msgList != null && !msgList.isEmpty()) {
			msgList.clear();
		}
		if (warnList != null && !warnList.isEmpty()) {
			warnList.clear();
		}
		if (detailedList != null && !detailedList.isEmpty()) {
			detailedList.clear();
		}
		if (this.dataList != null)
			this.dataList.clear();
		if (this.dataTable != null)
			this.dataTable.clear();
	}

	public void setReturnMsg(ReturnMsg msg) {
		if (!msg.isSuccessflag()) {
			this.setFailMessage(msg.getMsgList());
		}
		if (!msg.getWarnList().isEmpty()) {
			this.setWarnMessage(msg.getWarnList());
		}
	}
}
