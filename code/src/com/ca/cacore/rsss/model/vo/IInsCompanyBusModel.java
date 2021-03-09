package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

public interface IInsCompanyBusModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getBranch_name();

	public void setBranch_name(String branch_name);

	public Date getSta_date();

	public void setSta_date(Date sta_date);

	public String getCode();

	public void setCode(String code);

	public String getType_code();

	public void setType_code(String type_code);

	public String getType_name();

	public void setType_name(String type_name);

	public String getName();

	public void setName(String name);

	public Double getPeriod_prem();

	public void setPeriod_prem(Double period_prem);

	public Double getAmount();

	public void setAmount(Double amount);

	public Double getAgent();

	public void setAgent(Double agent);

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

}