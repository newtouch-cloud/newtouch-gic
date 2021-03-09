package com.ca.cacore.lms.model.bo;

import java.sql.Date;

public interface IRankModel {

	public String getSerno();

	public void setSerno(String serno);

	public String getImpmeans_no();

	public void setImpmeans_no(String impmeans_no);

	public String getRank_no();

	public void setRank_no(String rank_no);

	public String getRank_name();

	public void setRank_name(String rank_name);

	public Integer getRank_seniority();

	public void setRank_seniority(Integer rank_seniority);

	public String getUp_rank_no();

	public void setUp_rank_no(String up_rank_no);

	public Date getStart_date();

	public void setStart_date(Date start_date);

	public Date getEnd_date();

	public void setEnd_date(Date end_date);

	public String getMemo();

	public void setMemo(String memo);

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