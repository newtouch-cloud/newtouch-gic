package com.ca.cacore.lms.model.bo;

import java.sql.Date;

public interface IRankAssessStdModel {

	public String getSerno();

	public void setSerno(String serno);

	public String getCon_no();

	public void setCon_no(String con_no);

	public String getIndex_no();

	public void setIndex_no(String index_no);

	public String getCon_flag();

	public void setCon_flag(String con_flag);

	public String getData_value();

	public void setData_value(String data_value);

	public String getWarn_flag();

	public void setWarn_flag(String warn_flag);

	public String getMemo();

	public void setMemo(String memo);

	public Date getStart_date();

	public void setStart_date(Date start_date);

	public Date getEnd_date();

	public void setEnd_date(Date end_date);

	public Date getCrt_date();

	public void setCrt_date(Date crt_date);

	public Date getMdf_date();

	public void setMdf_date(Date mdf_date);

	public String getCrt_user();

	public void setCrt_user(String crt_user);

	public String getMdf_user();

	public void setMdf_user(String mdf_user);

	public String getData_flag();

	public void setData_flag(String data_flag);

}