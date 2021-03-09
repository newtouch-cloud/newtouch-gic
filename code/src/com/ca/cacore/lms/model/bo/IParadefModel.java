package com.ca.cacore.lms.model.bo;

import java.sql.Date;

public interface IParadefModel {

	public String getSerno();

	public void setSerno(String serno);

	public String getImpmeans_no();

	public void setImpmeans_no(String impmeans_no);

	public String getPara_type();

	public void setPara_type(String para_type);

	public String getRank_no();

	public void setRank_no(String rank_no);

	public String getPara_no();

	public void setPara_no(String para_no);

	public String getPara_name();

	public void setPara_name(String para_name);

	public String getStart_value1();

	public void setStart_value1(String start_value1);

	public String getEnd_value1();

	public void setEnd_value1(String end_value1);

	public String getStart_value2();

	public void setStart_value2(String start_value2);

	public String getEnd_value2();

	public void setEnd_value2(String end_value2);

	public String getPara_value();

	public void setPara_value(String para_value);

	public String getPara_flag();

	public void setPara_flag(String para_flag);

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