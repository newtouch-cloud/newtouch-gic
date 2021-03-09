package com.newtouch.component.axis2.model;

/**
* @since:    2014年7月1日   
* @author    wangjianqiang
* @description:手续费对账接口
* 
 */
public class FeeInfoVoModel {
	
	private String contNo;
	private String batchPolNo;
	private int pCount;
	private int pSerialNo;
	private String dataType;
	private String agentCode;
	private double prem;
	private double feeRate;
	private double fee;
	private String feeCurreny;
	private String comCode;
	//营改增接口
    private double taxnetPremium;//含税保费
    private double taxFnum;//含税手续费
    private double taxvalNetpremium;//保费税额
    private double taxvalFnum;//手续费税额
    private double travelTax;//车船税
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getBatchPolNo() {
		return batchPolNo;
	}
	public void setBatchPolNo(String batchPolNo) {
		this.batchPolNo = batchPolNo;
	}
	public int getpCount() {
		return pCount;
	}
	public void setpCount(int pCount) {
		this.pCount = pCount;
	}
	public int getpSerialNo() {
		return pSerialNo;
	}
	public void setpSerialNo(int pSerialNo) {
		this.pSerialNo = pSerialNo;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public double getPrem() {
		return prem;
	}
	public void setPrem(double prem) {
		this.prem = prem;
	}
	public double getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(double feeRate) {
		this.feeRate = feeRate;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getFeeCurreny() {
		return feeCurreny;
	}
	public void setFeeCurreny(String feeCurreny) {
		this.feeCurreny = feeCurreny;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	/**
	 * 营改增修改
	 * @return
	 */
	public double getTaxnetPremium() {
		return taxnetPremium;
	}
	public void setTaxnetPremium(double taxnetPremium) {
		this.taxnetPremium = taxnetPremium;
	}
	public double getTaxFnum() {
		return taxFnum;
	}
	public void setTaxFnum(double taxFnum) {
		this.taxFnum = taxFnum;
	}
	public double getTaxvalNetpremium() {
		return taxvalNetpremium;
	}
	public void setTaxvalNetpremium(double taxvalNetpremium) {
		this.taxvalNetpremium = taxvalNetpremium;
	}
	public double getTaxvalFnum() {
		return taxvalFnum;
	}
	public void setTaxvalFnum(double taxvalFnum) {
		this.taxvalFnum = taxvalFnum;
	}
	public double getTravelTax() {
		return travelTax;
	}
	public void setTravelTax(double travelTax) {
		this.travelTax = travelTax;
	}
	
	
}
