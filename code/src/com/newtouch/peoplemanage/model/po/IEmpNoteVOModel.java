package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

public interface IEmpNoteVOModel {
	public void setSeq_id(String seq_id) ;
	public String getPerson_no() ;
	public void setPerson_no(String person_no);
	public String getContent_code();
	public void setContent_code(String content_code) ;
	public String getFlag() ;
	public void setFlag(String flag) ;
	public Date getCreate_date();
	public void setCreate_date(Date create_date);
	public String getCreat_user() ;
	public void setCreat_user(String creat_user) ;
	public String getMay_date() ;
	public void setMay_date(String may_date); 
	public Date getMay_user() ;
	public void setMay_user(Date may_user) ;
}
