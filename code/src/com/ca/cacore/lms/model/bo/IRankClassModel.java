package com.ca.cacore.lms.model.bo;

import java.sql.Date;

public interface IRankClassModel {

	public String getSerno();

	public void setSerno(String serno);

	public String getRank_class_no();

	public void setRank_class_no(String rank_class_no);

	public String getRank_class_name();

	public void setRank_class_name(String rank_class_name);

	public String getRank_class_type();

	public void setRank_class_type(String rank_class_type);

	public String getUp_rank_class();

	public void setUp_rank_class(String up_rank_class);

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

	public String getPatch_memo();

	public void setPatch_memo(String patch_memo);

}