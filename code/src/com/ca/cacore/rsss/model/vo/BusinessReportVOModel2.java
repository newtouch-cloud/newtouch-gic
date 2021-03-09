package com.ca.cacore.rsss.model.vo;

/**
 * 
* @since:    2014年1月14日   
* @author    ZhangChen
* @description:保险代理机构业务汇总表(二)  vo
 */
public class BusinessReportVOModel2 {
	
	private Integer line;//行次
	private String branch_name;//机构名称
	private String statistic_year;//统计年度
	private String statistic_month;//统计月份 
	private String statistic_time;//统计时间 由统计的年份+统计的季度 组合而成
	
	private Integer branch_num;//分支结构数量
	private Integer branch_taux;//机构数同比增减
	private Integer staffNum;//现有员工数量
	private Integer staffTaux;//所有员工同比+-比率
	private Integer period_num;//保单本期数量
	private Integer total_num;//保单累计件数
	private Double period_prem;//本期保费金额
	private Double total_prem;//累计保费金额
	private Double period_unreceived_prem; //本期未解付保费
	private Double total_unreceived_prem; //累计未解付保费
	private Double period_fee;//代理手续费
	private	Double total_fee;//累计代理手续费
	
	
	public Integer getLine() {
		return line;
	}
	public void setLine(Integer line) {
		this.line = line;
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
	public Integer getBranch_num() {
		return branch_num;
	}
	public void setBranch_num(Integer branch_num) {
		this.branch_num = branch_num;
	}
	public Integer getBranch_taux() {
		return branch_taux;
	}
	public void setBranch_taux(Integer branch_taux) {
		this.branch_taux = branch_taux;
	}
	public Integer getStaffNum() {
		return staffNum;
	}
	public void setStaffNum(Integer staffNum) {
		this.staffNum = staffNum;
	}
	public Integer getStaffTaux() {
		return staffTaux;
	}
	public void setStaffTaux(Integer staffTaux) {
		this.staffTaux = staffTaux;
	}
	public Integer getPeriod_num() {
		return period_num;
	}
	public void setPeriod_num(Integer period_num) {
		this.period_num = period_num;
	}
	public Integer getTotal_num() {
		return total_num;
	}
	public void setTotal_num(Integer total_num) {
		this.total_num = total_num;
	}
	public Double getPeriod_prem() {
		return period_prem;
	}
	public void setPeriod_prem(Double period_prem) {
		this.period_prem = period_prem;
	}
	public Double getTotal_prem() {
		return total_prem;
	}
	public void setTotal_prem(Double total_prem) {
		this.total_prem = total_prem;
	}
	public Double getPeriod_fee() {
		return period_fee;
	}
	public void setPeriod_fee(Double period_fee) {
		this.period_fee = period_fee;
	}
	public Double getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Double total_fee) {
		this.total_fee = total_fee;
	}
	public Double getPeriod_unreceived_prem() {
		return period_unreceived_prem;
	}
	public void setPeriod_unreceived_prem(Double period_unreceived_prem) {
		this.period_unreceived_prem = period_unreceived_prem;
	}
	public Double getTotal_unreceived_prem() {
		return total_unreceived_prem;
	}
	public void setTotal_unreceived_prem(Double total_unreceived_prem) {
		this.total_unreceived_prem = total_unreceived_prem;
	}
	
	
	

	
}
