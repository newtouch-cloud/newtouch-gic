package com.ca.cacore.lms.model.bo;

import java.sql.Date;

public interface IImpmeansverModel {

	public String getSerno();

	public void setSerno(String serno);

	public String getImpmeansver_no();

	public void setImpmeansver_no(String impmeansver_no);

	public String getImpmeansver_name();

	public void setImpmeansver_name(String impmeansver_name);

	public String getImpmeans_no();

	public void setImpmeans_no(String impmeans_no);

	public String getDept_no();

	public void setDept_no(String dept_no);

	public String getCalc_order();

	public void setCalc_order(String calc_order);

	public String getIss_unit();

	public void setIss_unit(String iss_unit);

	public Date getIss_date();

	public void setIss_date(Date iss_date);

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

	public String getPatch_memo();

	public void setPatch_memo(String patch_memo);

}