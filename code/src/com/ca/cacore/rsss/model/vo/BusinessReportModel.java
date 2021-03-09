package com.ca.cacore.rsss.model.vo;

public class BusinessReportModel implements IBusinessReportModel   {
	
	private String branch_id; //机构id
	private String branch_name;//机构名称
	private String statistic_year;//统计年度
	private String statistic_month;//统计月份 
	private String statistic_time;//统计时间 由统计的年份+统计的季度 组合而成
	private String product_name;//产品名称
	private Integer line;//行次
	private Integer person_num;//公司人数
	private Double  person_com;//同比(+/-)
	private Integer period_num;//保单本期数量
	private Integer total_num;//保单累计
	private Double period_prem;//本期保费金额
	private Double total_prem;//累计保费金额
	private Double period_unreceived_prem; //本期未解付保费
	private Double total_unreceived_prem; //累计未解付保费
	private Double period_fee;//本期代理手续费
	private	Double total_fee;//累计代理手续费
	private Double period_pro;//本期利润
	private	Double total_pro;//累计利润
	private Double registered_capital;//注册资本
	private Double total_assets;//资产总额
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
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getLine() {
		return line;
	}
	public void setLine(Integer line) {
		this.line = line;
	}
	public Integer getPerson_num() {
		return person_num;
	}
	public void setPerson_num(Integer person_num) {
		this.person_num = person_num;
	}
	public Double getPerson_com() {
		return person_com;
	}
	public void setPerson_com(Double person_com) {
		this.person_com = person_com;
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
	public Double getPeriod_pro() {
		return period_pro;
	}
	public void setPeriod_pro(Double period_pro) {
		this.period_pro = period_pro;
	}
	public Double getTotal_pro() {
		return total_pro;
	}
	public void setTotal_pro(Double total_pro) {
		this.total_pro = total_pro;
	}
	public Double getRegistered_capital() {
		return registered_capital;
	}
	public void setRegistered_capital(Double registered_capital) {
		this.registered_capital = registered_capital;
	}
	public Double getTotal_assets() {
		return total_assets;
	}
	public void setTotal_assets(Double total_assets) {
		this.total_assets = total_assets;
	}
	
}
