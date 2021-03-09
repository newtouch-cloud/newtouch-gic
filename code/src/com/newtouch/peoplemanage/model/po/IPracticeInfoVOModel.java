package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

public interface IPracticeInfoVOModel {
	
	public String getSeq_id() ;
	public void setSeq_id(String seq_id);
	public String getPerson_no() ;
	public void setPerson_no(String person_no) ;
	
	public String getChannel_type() ;
	public void setChannel_type(String channel_type) ;
	
	
	public String getMicroshop_id();
	public void setMicroshop_id(String microshop_id) ;
	public String getShopkeeper_name() ;
	public void setShopkeeper_name(String shopkeeper_name); 
	public String getChannel_code() ;
	public void setChannel_code(String channel_code) ;
	
	public Date getCreate_date() ;
	public void setCreate_date(Date create_date) ;
	public String getCreat_user() ;
	public void setCreat_user(String creat_user) ;
	public String getMay_date();
	public void setMay_date(String may_date);
	public Date getMay_user();
	public void setMay_user(Date may_user) ;
	
}
