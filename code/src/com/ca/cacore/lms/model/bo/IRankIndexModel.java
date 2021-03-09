package com.ca.cacore.lms.model.bo;

import java.sql.Date;

public interface IRankIndexModel {

	public String getSerno();

	public void setSerno(String serno);

	public String getImpmeans_no();

	public void setImpmeans_no(String impmeans_no);

	public String getRank_no();

	public void setRank_no(String rank_no);

	public String getRela_type();

	public void setRela_type(String rela_type);

	public String getIndex_no();

	public void setIndex_no(String index_no);

	public String getDept_type();

	public void setDept_type(String dept_type);

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