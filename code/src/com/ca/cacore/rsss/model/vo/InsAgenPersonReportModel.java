package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

public class InsAgenPersonReportModel implements  IInsAgenPersonReportModel   {
	
	private Integer seq_id;//序号
	private String  branch_id;//机构id
	private String  branch_name;//机构name
	private String  type_code;//表头名字id
	private String  type_name;//表头名字类型
	private String  name;//表头名称
	private Date firstDate;//开始时间
	private Date secondDate;//结束时间
	private String statistic_year;//统计年份
	private String statistic_month;//统计月份
	private String reportType;//报表类型
	private String sales_id;//员工id
	private String sales_name;//员工name
	private String gender;//性别
	private String iden_card;//身份证
	private String post;//职务
	private String labour_code;//劳动合同号
	private Date   labour_code_time;//劳动合同号签订时间
	private String certificate;//资格证
	private Date   certificate_date;//资格证获取时间
	private String certificate_no;//执业证号
	private Date  department_time;//离司时间
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
	public String getSales_id() {
		return sales_id;
	}
	public void setSales_id(String sales_id) {
		this.sales_id = sales_id;
	}
	public String getSales_name() {
		return sales_name;
	}
	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIden_card() {
		return iden_card;
	}
	public void setIden_card(String iden_card) {
		this.iden_card = iden_card;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getLabour_code() {
		return labour_code;
	}
	public void setLabour_code(String labour_code) {
		this.labour_code = labour_code;
	}
	public Date getLabour_code_time() {
		return labour_code_time;
	}
	public void setLabour_code_time(Date labour_code_time) {
		this.labour_code_time = labour_code_time;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public Date getCertificate_date() {
		return certificate_date;
	}
	public void setCertificate_date(Date certificate_date) {
		this.certificate_date = certificate_date;
	}
	public String getCertificate_no() {
		return certificate_no;
	}
	public void setCertificate_no(String certificate_no) {
		this.certificate_no = certificate_no;
	}
	public Date getDepartment_time() {
		return department_time;
	}
	public void setDepartment_time(Date department_time) {
		this.department_time = department_time;
	}
	
}
