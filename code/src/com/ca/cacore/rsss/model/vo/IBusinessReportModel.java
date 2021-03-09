package com.ca.cacore.rsss.model.vo;

public interface IBusinessReportModel {

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getBranch_name();

	public void setBranch_name(String branch_name);

	public String getStatistic_year();

	public void setStatistic_year(String statistic_year);

	public String getStatistic_month();

	public void setStatistic_month(String statistic_month);

	public String getStatistic_time();

	public void setStatistic_time(String statistic_time);

	public String getProduct_name();

	public void setProduct_name(String product_name);

	public Integer getLine();

	public void setLine(Integer line);

	public Integer getPerson_num();

	public void setPerson_num(Integer person_num);

	public Double getPerson_com();

	public void setPerson_com(Double person_com);

	public Integer getPeriod_num();

	public void setPeriod_num(Integer period_num);

	public Integer getTotal_num();

	public void setTotal_num(Integer total_num);

	public Double getPeriod_prem();

	public void setPeriod_prem(Double period_prem);

	public Double getTotal_prem();

	public void setTotal_prem(Double total_prem);

	public Double getPeriod_unreceived_prem();

	public void setPeriod_unreceived_prem(Double period_unreceived_prem);

	public Double getTotal_unreceived_prem();

	public void setTotal_unreceived_prem(Double total_unreceived_prem);

	public Double getPeriod_fee();

	public void setPeriod_fee(Double period_fee);

	public Double getTotal_fee();

	public void setTotal_fee(Double total_fee);

	public Double getPeriod_pro();

	public void setPeriod_pro(Double period_pro);

	public Double getTotal_pro();

	public void setTotal_pro(Double total_pro);

	public Double getRegistered_capital();

	public void setRegistered_capital(Double registered_capital);

	public Double getTotal_assets();

	public void setTotal_assets(Double total_assets);

}