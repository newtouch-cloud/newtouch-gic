package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;


/**
 * 
* @since:      
* @author    
* @description:营运人员效力情况报表 vo 
 */
public class YYPersonReportVOModel implements IYYPersonReportVOModel   {
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}
	public String getSecondDate() {
		return secondDate;
	}
	public void setSecondDate(String secondDate) {
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
	private String reportType;
	private String firstDate;
	private String secondDate;
	private String statistic_year;
	private String statistic_month;
	private String branch_id; //机构id
	private Date startDate;//查询开始时间
	private Date endDate;//查询截止时间
	
	private String company_name;//分公司名称
	private String branch_name;//支公司名称
	private String newPolicyNum;//新保单件数
	private String YWNum;//业务件数
	private String JAWTNum;//结案问题件数
	private String WTJLNum;//问题件率
	private String WTJJALNum;//问题件结案率
	private String TSNum;//投诉件数
	private String TSJANum;//投诉结案件数
	private String TSJALnum;//投诉结案率
	private String XZLPJNum;//协助理赔件数
	private String XZBQBGJNum;//协助保全变更件数
	private PageCount pageCount = new PageCount();//分页
	public PageCount getPageCount() {
		return pageCount;
	}
	public void setPageCount(PageCount pageCount) {
		this.pageCount = pageCount;
	}
	public int getStart() {
		return this.getPageCount().getStart();
	}
	public int getLimit() {
		return this.getPageCount().getLimit();
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getNewPolicyNum() {
		return newPolicyNum;
	}
	public void setNewPolicyNum(String newPolicyNum) {
		this.newPolicyNum = newPolicyNum;
	}
	public String getYWNum() {
		return YWNum;
	}
	public void setYWNum(String yWNum) {
		YWNum = yWNum;
	}
	public String getJAWTNum() {
		return JAWTNum;
	}
	public void setJAWTNum(String jAWTNum) {
		JAWTNum = jAWTNum;
	}
	public String getWTJLNum() {
		return WTJLNum;
	}
	public void setWTJLNum(String wTJLNum) {
		WTJLNum = wTJLNum;
	}
	public String getWTJJALNum() {
		return WTJJALNum;
	}
	public void setWTJJALNum(String wTJJALNum) {
		WTJJALNum = wTJJALNum;
	}
	public String getTSNum() {
		return TSNum;
	}
	public void setTSNum(String tSNum) {
		TSNum = tSNum;
	}
	public String getTSJANum() {
		return TSJANum;
	}
	public void setTSJANum(String tSJANum) {
		TSJANum = tSJANum;
	}
	public String getTSJALnum() {
		return TSJALnum;
	}
	public void setTSJALnum(String tSJALnum) {
		TSJALnum = tSJALnum;
	}
	public String getXZLPJNum() {
		return XZLPJNum;
	}
	public void setXZLPJNum(String xZLPJNum) {
		XZLPJNum = xZLPJNum;
	}
	public String getXZBQBGJNum() {
		return XZBQBGJNum;
	}
	public void setXZBQBGJNum(String xZBQBGJNum) {
		XZBQBGJNum = xZBQBGJNum;
	}
	
}
