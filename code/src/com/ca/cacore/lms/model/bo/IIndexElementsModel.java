package com.ca.cacore.lms.model.bo;

import java.sql.Date;

public interface IIndexElementsModel {

	public String getSerno();

	public void setSerno(String serno);

	public String getIndex_calcno();

	public void setIndex_calcno(String index_calcno);

	public String getVariable_no();

	public void setVariable_no(String variable_no);

	public String getVariable_name();

	public void setVariable_name(String variable_name);

	public String getAttribute_no();

	public void setAttribute_no(String attribute_no);

	public String getVariable_order();

	public void setVariable_order(String variable_order);

	public String getExpand_1();

	public void setExpand_1(String expand_1);

	public String getExpand_2();

	public void setExpand_2(String expand_2);

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