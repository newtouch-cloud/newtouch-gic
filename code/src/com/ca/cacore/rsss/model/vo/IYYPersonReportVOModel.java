package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IYYPersonReportVOModel {

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

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);

	public int getStart();

	public int getLimit();

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public Date getStartDate();

	public void setStartDate(Date startDate);

	public Date getEndDate();

	public void setEndDate(Date endDate);

	public String getCompany_name();

	public void setCompany_name(String company_name);

	public String getBranch_name();

	public void setBranch_name(String branch_name);

	public String getNewPolicyNum();

	public void setNewPolicyNum(String newPolicyNum);

	public String getYWNum();

	public void setYWNum(String yWNum);

	public String getJAWTNum();

	public void setJAWTNum(String jAWTNum);

	public String getWTJLNum();

	public void setWTJLNum(String wTJLNum);

	public String getWTJJALNum();

	public void setWTJJALNum(String wTJJALNum);

	public String getTSNum();

	public void setTSNum(String tSNum);

	public String getTSJANum();

	public void setTSJANum(String tSJANum);

	public String getTSJALnum();

	public void setTSJALnum(String tSJALnum);

	public String getXZLPJNum();

	public void setXZLPJNum(String xZLPJNum);

	public String getXZBQBGJNum();

	public void setXZBQBGJNum(String xZBQBGJNum);

}