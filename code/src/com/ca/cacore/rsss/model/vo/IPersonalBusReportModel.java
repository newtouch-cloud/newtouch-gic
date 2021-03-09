package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

public interface IPersonalBusReportModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getBranch_name();

	public void setBranch_name(String branch_name);

	public String getCode();

	public void setCode(String code);

	public Double getPeriod_prem_new();

	public void setPeriod_prem_new(Double period_prem_new);

	public Double getRene_period_prem();

	public void setRene_period_prem(Double rene_period_prem);

	public Double getPayable_prem();

	public void setPayable_prem(Double payable_prem);

	public Double getNew_agent();

	public void setNew_agent(Double new_agent);

	public Double getRene_agent();

	public void setRene_agent(Double rene_agent);

	public Date getFirstDate();

	public void setFirstDate(Date firstDate);

	public Date getSecondDate();

	public void setSecondDate(Date secondDate);

	public String getStatistic_year();

	public void setStatistic_year(String statistic_year);

	public String getStatistic_month();

	public void setStatistic_month(String statistic_month);

	public String getReportType();

	public void setReportType(String reportType);

	public String getType_code();

	public void setType_code(String type_code);

	public String getType_name();

	public void setType_name(String type_name);

	public String getName();

	public void setName(String name);

}