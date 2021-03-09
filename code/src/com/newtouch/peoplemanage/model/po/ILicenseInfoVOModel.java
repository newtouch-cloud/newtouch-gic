package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

public interface ILicenseInfoVOModel {
	
	public String getSeq_id() ;
	public void setSeq_id(String seq_id);
	public String getPerson_no() ;
	public void setPerson_no(String person_no) ;
	
	
	public String getLicense_name();
	public void setLicense_name(String license_name) ;
	public String getLicense_channel();
	public void setLicense_channel(String license_channel) ;
	public Date getLicense_startdate();
	public void setLicense_startdate(Date license_startdate) ;
	
	
	
	public Date getCreate_date() ;
	public void setCreate_date(Date create_date) ;
	public String getCreat_user() ;
	public void setCreat_user(String creat_user) ;
	public String getMay_date();
	public void setMay_date(String may_date);
	public Date getMay_user();
	public void setMay_user(Date may_user) ;
	
}
