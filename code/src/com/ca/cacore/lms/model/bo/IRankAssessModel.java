package com.ca.cacore.lms.model.bo;

import java.sql.Date;

public interface IRankAssessModel {

	public String getSerno();

	public void setSerno(String serno);

	public String getImpmeans_no();

	public void setImpmeans_no(String impmeans_no);

	public String getRank_no();

	public void setRank_no(String rank_no);

	public String getTarget_rank();

	public void setTarget_rank(String target_rank);

	public String getChg_type();

	public void setChg_type(String chg_type);

	public String getCon_no();

	public void setCon_no(String con_no);

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

	public String getMemo();

	public void setMemo(String memo);

}