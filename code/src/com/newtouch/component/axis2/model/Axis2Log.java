package com.newtouch.component.axis2.model;

import java.sql.Date;

/**
* @since:    2014年4月17日   
* @author    ZhangChen
* @description:记录实体类
 */
public class Axis2Log {

	String seq_id; //主键
	String checkFlag;//成功失败标示
	String resultCode;//回参代码
	String resultMsg;//回参信息
	String transType; //交易码
	String agentCode;//中介人员代码
	String agentLicNo;//协议号
	String agentGroup;//中介人员机构
	Date transDate; //数据传送日期
	String transTime;//数据传送时间
	Date dealDate; //数据处理日期
	String dealTime;//数据处理时间
	String service;//调用service方法名称
	String receiveXml;//核心传送来的报文
	String returnXml;//返回核心的报文
	
	
	public Axis2Log(String checkFlag, String resultCode,
			String resultMsg, String transType, String agentCode,
			String agentLicNo, String agentGroup, Date transDate,
			String transTime, Date dealDate, String dealTime,String service) {
		this.checkFlag = checkFlag;
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.transType = transType;
		this.agentCode = agentCode;
		this.agentLicNo = agentLicNo;
		this.agentGroup = agentGroup;
		this.transDate = transDate;
		this.transTime = transTime;
		this.dealDate = dealDate;
		this.dealTime = dealTime;
		this.service = service;
	}
	
	public Axis2Log(String checkFlag, String resultCode,
			String resultMsg, String transType, String agentCode,
			String agentLicNo, String agentGroup, Date transDate,
			String transTime, Date dealDate, String dealTime,String service,String receiverXml,String returnXml) {
		this.checkFlag = checkFlag;
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.transType = transType;
		this.agentCode = agentCode;
		this.agentLicNo = agentLicNo;
		this.agentGroup = agentGroup;
		this.transDate = transDate;
		this.transTime = transTime;
		this.dealDate = dealDate;
		this.dealTime = dealTime;
		this.service = service;
		this.receiveXml=receiverXml;
		this.returnXml=returnXml;
	}
	
	public Axis2Log() {

	}
	
	public String getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(String seq_id) {
		this.seq_id = seq_id;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgentLicNo() {
		return agentLicNo;
	}
	public void setAgentLicNo(String agentLicNo) {
		this.agentLicNo = agentLicNo;
	}
	public String getAgentGroup() {
		return agentGroup;
	}
	public void setAgentGroup(String agentGroup) {
		this.agentGroup = agentGroup;
	}
	public Date getDealDate() {
		return dealDate;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	public String getDealTime() {
		return dealTime;
	}
	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getReceiveXml() {
		return receiveXml;
	}

	public void setReceiveXml(String receiveXml) {
		this.receiveXml = receiveXml;
	}

	public String getReturnXml() {
		return returnXml;
	}

	public void setReturnXml(String returnXml) {
		this.returnXml = returnXml;
	}
	
	
}
