package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

public class CustomeGDVOModel implements ICustomeGDVOModel   {
	private Integer seq_id;//序号
	private String branch_id;//机构id
	private String age;//年龄
	private String incom;//年收入
	private String marry;//婚姻状况
	private String pronum;//新单件数
	private String premiun;//新单保费累计
	private String preavg;//新单件均保费
	private String reportType;//报表类型
	private Date firstDate;//开始时间
	private Date secondDate;//结束时间
	private String statistic_year;//统计年份
	private String statistic_month;//统计月份
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getIncom() {
		return incom;
	}
	public void setIncom(String incom) {
		this.incom = incom;
	}
	public String getMarry() {
		return marry;
	}
	public void setMarry(String marry) {
		this.marry = marry;
	}
	public String getPronum() {
		return pronum;
	}
	public void setPronum(String pronum) {
		this.pronum = pronum;
	}
	public String getPremiun() {
		return premiun;
	}
	public void setPremiun(String premiun) {
		this.premiun = premiun;
	}
	public String getPreavg() {
		return preavg;
	}
	public void setPreavg(String preavg) {
		this.preavg = preavg;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
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
	
}
