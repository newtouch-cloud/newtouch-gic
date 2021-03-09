package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

public interface IEmpLeaveVOModel {
	
	public String getSeq_id() ;
	public void setSeq_id(String seq_id);
	public String getPerson_no() ;
	public void setPerson_no(String person_no) ;
	
	public Date getEndtime() ;
	public void setEndtime(Date endtime); 
	public String getReason() ;
	public void setReason(String reason) ;
	public Date getApply_date(); 
	public void setApply_date(Date apply_date) ;
	public Date getCheckdate();
	public void setCheckdate(Date checkdate) ;
	public String getStatus() ;
	public void setStatus(String status) ;
	
	
	public Date getCreate_date() ;
	public void setCreate_date(Date create_date) ;
	public String getCreat_user() ;
	public void setCreat_user(String creat_user) ;
	public String getMay_date();
	public void setMay_date(String may_date);
	public Date getMay_user();
	public void setMay_user(Date may_user) ;
	
}
