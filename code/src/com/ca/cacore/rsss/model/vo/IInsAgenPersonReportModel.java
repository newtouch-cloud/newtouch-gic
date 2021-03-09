package com.ca.cacore.rsss.model.vo;

import java.sql.Date;

public interface IInsAgenPersonReportModel {

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getBranch_name();

	public void setBranch_name(String branch_name);

	public String getType_code();

	public void setType_code(String type_code);

	public String getType_name();

	public void setType_name(String type_name);

	public String getName();

	public void setName(String name);

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

	public String getSales_id();

	public void setSales_id(String sales_id);

	public String getSales_name();

	public void setSales_name(String sales_name);

	public String getGender();

	public void setGender(String gender);

	public String getIden_card();

	public void setIden_card(String iden_card);

	public String getPost();

	public void setPost(String post);

	public String getLabour_code();

	public void setLabour_code(String labour_code);

	public Date getLabour_code_time();

	public void setLabour_code_time(Date labour_code_time);

	public String getCertificate();

	public void setCertificate(String certificate);

	public Date getCertificate_date();

	public void setCertificate_date(Date certificate_date);

	public String getCertificate_no();

	public void setCertificate_no(String certificate_no);

	public Date getDepartment_time();

	public void setDepartment_time(Date department_time);

}