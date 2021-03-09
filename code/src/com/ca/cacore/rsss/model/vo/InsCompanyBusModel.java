package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

public class InsCompanyBusModel implements IInsCompanyBusModel     {
	
	private Integer seq_id;//序号
	private String 	branch_id;//机构id
	private String 	branch_name;//机构name
	private Date 	sta_date;//统计日期
	private String  code;//行次
	private String  type_code;//表头名字id
	private String  type_name;//表头名字类型
	private String  name;//表头名称
	private Double  period_prem;//保费金额
	private Double  amount; //应付金额(未解付保费)
	private Double  agent;//代理佣金
	private Date firstDate;//开始时间
	private Date secondDate;//结束时间
	private String statistic_year;//统计年份
	private String statistic_month;//统计月份
	private String reportType;//报表类型
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public Date getSta_date() {
		return sta_date;
	}
	public void setSta_date(Date sta_date) {
		this.sta_date = sta_date;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPeriod_prem() {
		return period_prem;
	}
	public void setPeriod_prem(Double period_prem) {
		this.period_prem = period_prem;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAgent() {
		return agent;
	}
	public void setAgent(Double agent) {
		this.agent = agent;
	}
	public Date getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}
	public Date getSecondDate() {
		return secondDate;
	}
	public void setSecondDate(Date secondDate) {
		this.secondDate = secondDate;
	}
	public String getStatistic_year() {
		return statistic_year;
	}
	public void setStatistic_year(String statistic_year) {
		this.statistic_year = statistic_year;
	}
	public String getStatistic_month() {
		return statistic_month;
	}
	public void setStatistic_month(String statistic_month) {
		this.statistic_month = statistic_month;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

}
