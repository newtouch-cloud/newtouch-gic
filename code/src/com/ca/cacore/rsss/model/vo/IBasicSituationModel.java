package com.ca.cacore.rsss.model.vo;

public interface IBasicSituationModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getReportType();

	public void setReportType(String reportType);

	public String getFirstDate();

	public void setFirstDate(String firstDate);

	public String getSecondDate();

	public void setSecondDate(String secondDate);

	public String getStatistic_year();

	public void setStatistic_year(String statistic_year);

	public String getStatistic_month();

	public void setStatistic_month(String statistic_month);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getBasic_station();

	public void setBasic_station(String basic_station);

	public String getLine();

	public void setLine(String line);

	public Double getNum();

	public void setNum(Double num);

}