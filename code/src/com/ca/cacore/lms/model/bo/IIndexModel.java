package com.ca.cacore.lms.model.bo;

import java.sql.Date;

public interface IIndexModel {

	public String getSerno();

	public void setSerno(String serno);

	public String getImpmeans_no();

	public void setImpmeans_no(String impmeans_no);

	public String getIndex_no();

	public void setIndex_no(String index_no);

	public String getIndex_name();

	public void setIndex_name(String index_name);

	public String getIndex_calcno();

	public void setIndex_calcno(String index_calcno);

	public String getIndex_order();

	public void setIndex_order(String index_order);

	public String getIs_valid();

	public void setIs_valid(String is_valid);

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