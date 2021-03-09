package com.ca.cacore.rsss.model.vo;

/**
 * 
* @since:    2014年1月14日   
* @author    ZhangChen
* @description:专业保险中介机构业务人员持证情况报表 vo 
* 关联  sys_branch Smis_Sales_Backup smis_team_backup smis_rank SMIS_Sales_Credentials
 */
public class HoldReportVOModel {
	
	private String branch_id; //机构id
	private String branch_name;//机构名称
	private String statistic_year;//统计年度
	private String statistic_month;//统计月份 
	private String statistic_time;//统计时间 由统计的年份+统计的季度 组合而成
	private Integer salesNum;//业务人员人数
	private Integer certificateSales;//业务人员持证数量
	
	
	
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
	public String getStatistic_time() {
		return statistic_time;
	}
	public void setStatistic_time(String statistic_time) {
		this.statistic_time = statistic_time;
	}
	public Integer getSalesNum() {
		return salesNum;
	}
	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}
	public Integer getCertificateSales() {
		return certificateSales;
	}
	public void setCertificateSales(Integer certificateSales) {
		this.certificateSales = certificateSales;
	}
	
	
	
}
