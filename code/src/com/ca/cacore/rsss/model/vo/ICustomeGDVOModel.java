package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

public interface ICustomeGDVOModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getAge();

	public void setAge(String age);

	public String getIncom();

	public void setIncom(String incom);

	public String getMarry();

	public void setMarry(String marry);

	public String getPronum();

	public void setPronum(String pronum);

	public String getPremiun();

	public void setPremiun(String premiun);

	public String getPreavg();

	public void setPreavg(String preavg);

	public String getReportType();

	public void setReportType(String reportType);

	public Date getFirstDate();

	public void setFirstDate(Date firstDate);

	public Date getSecondDate();

	public void setSecondDate(Date secondDate);

	public String getStatistic_year();

	public void setStatistic_year(String statistic_year);

	public String getStatistic_month();

	public void setStatistic_month(String statistic_month);

}