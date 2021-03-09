package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

public interface IAgentInfoVOModel {
	
	public String getSeq_id() ;
	public void setSeq_id(String seq_id);
	public String getPerson_no() ;
	public void setPerson_no(String person_no) ;
	public String getBranch_id() ;
	public void setBranch_id(String branch_id) ;
	public String getQualification_no() ;
	public void setQualification_no(String qualification_no) ;
	public String getPractice_license_type() ;
	public void setPractice_license_type(String practice_license_type); 
	public String getPractice_license_status() ;
	public void setPractice_license_status(String practice_license_status);
	public String getPractice_license_no();
	public void setPractice_license_no(String practice_license_no) ;
	public Date getPractice_startdate() ;
	public void setPractice_startdate(Date practice_startdate);
	public Date getPractice_enddate() ;
	public void setPractice_enddate(Date practice_enddate) ;
	public String getPractice_area() ;
	public void setPractice_area(String practice_area);
	public String getChannel_type() ;
	public void setChannel_type(String channel_type) ;
	public String getIns_sales_no() ;
	public void setIns_sales_no(String ins_sales_no) ;
	public String getBusiness_scope();
	public void setBusiness_scope(String business_scope);
	public String getContract_type() ;
	public void setContract_type(String contract_type);
	public Date getCreate_date() ;
	public void setCreate_date(Date create_date) ;
	public String getCreat_user() ;
	public void setCreat_user(String creat_user) ;
	public String getMay_date();
	public void setMay_date(String may_date);
	public Date getMay_user();
	public void setMay_user(Date may_user) ;
	
}
