package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

public interface IEmpFamilyVOModel {
	
	public String getSeq_id() ;
	public void setSeq_id(String seq_id);
	public String getPerson_no() ;
	public void setPerson_no(String person_no) ;
	
	public String getFamily_name();
	public void setFamily_name(String family_name) ;
	public String getFamily_sex() ;
	public void setFamily_sex(String family_sex) ;
	public Date getFamily_birthday();
	public void setFamily_birthday(Date family_birthday) ;
	public String getFamily_relation() ;
	public void setFamily_relation(String family_relation) ;
	public String getFamily_position() ;
	public void setFamily_position(String family_position) ;
	public String getFamily_political();
	public void setFamily_political(String family_political);
	
	public Date getCreate_date() ;
	public void setCreate_date(Date create_date) ;
	public String getCreat_user() ;
	public void setCreat_user(String creat_user) ;
	public String getMay_date();
	public void setMay_date(String may_date);
	public Date getMay_user();
	public void setMay_user(Date may_user) ;
	
}
