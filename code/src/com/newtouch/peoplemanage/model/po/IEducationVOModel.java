package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

public interface IEducationVOModel {
	
	public String getSeq_id() ;
	public void setSeq_id(String seq_id);
	public String getPerson_no() ;
	public void setPerson_no(String person_no) ;
	
	public Date getStart_date(); 
	public void setStart_date(Date start_date) ;
	public Date getGraduation_date() ;
	public void setGraduation_date(Date graduation_date); 
	public String getAddress() ;
	public void setAddress(String address) ;
	public String getMajor() ;
	public void setMajor(String major) ;
	public String getYear();
	public void setYear(String year) ;
	public String getDegree() ;
	public void setDegree(String degree);
	public String getApprove_person() ;
	public void setApprove_person(String approve_person) ;
	public String getWork_position() ;
	public void setWork_position(String work_position) ;
	public String getIshigh_degree() ;
	public void setIshigh_degree(String ishigh_degree) ;
	public String getMajor_type() ;
	public void setMajor_type(String major_type); 
	public String getEducation_type() ;
	public void setEducation_type(String education_type) ;
	public String getDegree_type() ;
	public void setDegree_type(String degree_type) ;
	public String getType() ;
	public void setType(String type) ;
	
	
	public Date getCreate_date() ;
	public void setCreate_date(Date create_date) ;
	public String getCreat_user() ;
	public void setCreat_user(String creat_user) ;
	public String getMay_date();
	public void setMay_date(String may_date);
	public Date getMay_user();
	public void setMay_user(Date may_user) ;
	
}
