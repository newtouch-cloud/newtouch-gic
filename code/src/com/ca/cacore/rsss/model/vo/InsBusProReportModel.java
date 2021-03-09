package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

public class InsBusProReportModel implements IInsBusProReportModel {
	
	private Integer seq_id;//序号
	private String  branch_id;//机构id
	private String  branch_name;//机构name
	private Date firstDate;//开始时间
	private Date secondDate;//结束时间
	private String statistic_year;//统计年份
	private String statistic_month;//统计月份
	private String reportType;//报表类型
	private String agency_agr;//代理协议编号
	private String ins_provinces;//保险人所属省份
	private String insurer_id;//保险人id
	private String insurer_name;//保险人name
	private String agent_product_type;//代理险种类别
	private Date   labour_validate;//合同生效时间
	private Date   labour_enddate;//合同终止时间
	private String is_sales_name;//是否是业务人员
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
	public String getAgency_agr() {
		return agency_agr;
	}
	public void setAgency_agr(String agency_agr) {
		this.agency_agr = agency_agr;
	}
	public String getIns_provinces() {
		return ins_provinces;
	}
	public void setIns_provinces(String ins_provinces) {
		this.ins_provinces = ins_provinces;
	}
	public String getInsurer_id() {
		return insurer_id;
	}
	public void setInsurer_id(String insurer_id) {
		this.insurer_id = insurer_id;
	}
	public String getInsurer_name() {
		return insurer_name;
	}
	public void setInsurer_name(String insurer_name) {
		this.insurer_name = insurer_name;
	}
	public String getAgent_product_type() {
		return agent_product_type;
	}
	public void setAgent_product_type(String agent_product_type) {
		this.agent_product_type = agent_product_type;
	}
	public Date getLabour_validate() {
		return labour_validate;
	}
	public void setLabour_validate(Date labour_validate) {
		this.labour_validate = labour_validate;
	}
	public Date getLabour_enddate() {
		return labour_enddate;
	}
	public void setLabour_enddate(Date labour_enddate) {
		this.labour_enddate = labour_enddate;
	}
	public String getIs_sales_name() {
		return is_sales_name;
	}
	public void setIs_sales_name(String is_sales_name) {
		this.is_sales_name = is_sales_name;
	}
	
}
