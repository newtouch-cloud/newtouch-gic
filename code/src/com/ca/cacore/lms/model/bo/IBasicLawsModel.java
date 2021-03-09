package com.ca.cacore.lms.model.bo;

import java.sql.Date;
/**
* @since:    2014年3月2日   
* @author    ss
* @description:基本法信息	t_basiclaws
*/
public interface IBasicLawsModel {
	public String getSerno();

	public void setSerno(String serno);

	public String getBasiclaw_no();

	public void setBasiclaw_no(String basiclaw_no);

	public String getImpmeansver_name();

	public void setImpmeansver_name(String impmeansver_name);

	public String getBasiclaw_ver();

	public void setBasiclaw_ver(String basiclaw_ver);

	public String getDept_type();

	public void setDept_type(String dept_type);

	public String getIss_unit();

	public void setIss_unit(String iss_unit);

	public Date getIss_date();

	public void setIss_date(Date iss_date);

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
