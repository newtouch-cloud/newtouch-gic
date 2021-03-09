package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

public interface IInsBusProReportModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getBranch_name();

	public void setBranch_name(String branch_name);

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

	public String getAgency_agr();

	public void setAgency_agr(String agency_agr);

	public String getIns_provinces();

	public void setIns_provinces(String ins_provinces);

	public String getInsurer_id();

	public void setInsurer_id(String insurer_id);

	public String getInsurer_name();

	public void setInsurer_name(String insurer_name);

	public String getAgent_product_type();

	public void setAgent_product_type(String agent_product_type);

	public Date getLabour_validate();

	public void setLabour_validate(Date labour_validate);

	public Date getLabour_enddate();

	public void setLabour_enddate(Date labour_enddate);

	public String getIs_sales_name();

	public void setIs_sales_name(String is_sales_name);

}