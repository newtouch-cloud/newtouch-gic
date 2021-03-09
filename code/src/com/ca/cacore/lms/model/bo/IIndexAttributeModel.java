package com.ca.cacore.lms.model.bo;

import java.sql.Date;

public interface IIndexAttributeModel {

	public String getSerno();

	public void setSerno(String serno);

	public String getAttribute_no();

	public void setAttribute_no(String attribute_no);

	public String getAttribute_name();

	public void setAttribute_name(String attribute_name);

	public String getAttribute_type();

	public void setAttribute_type(String attribute_type);

	public String getResult_type();

	public void setResult_type(String result_type);

	public String getResult_rule();

	public void setResult_rule(String result_rule);

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