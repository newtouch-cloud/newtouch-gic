package com.newtouch.peoplemanage.model.po;

import java.sql.Date;
/**
 * 人员信息实体类接口
 * @author Ming Ying
 *
 */
public interface IEmployeeVOModel {
	public String getSeq_id();
	public void setSeq_id(String seq_id);
	
	public String getPerson_id();
	public void setPerson_id(String person_id);
	
	public String getPerson_name();
	public void setPerson_name(String person_name);
	
	public String getBranch_id();
	public void setBranch_id(String branch_id);
	
	public String getTeam_id();
	public void setTeam_id(String team_id);
	
	public Date getStart_date();
	public void setStart_date(Date start_date);
	
	public Date getEnd_date();
	public void setEnd_date(Date end_date);
	
	public String getPerson_status();
	public void setPerson_status(String person_status);
	
	public String getOrg_id();
	public void setOrg_id(String Org_id);
	
	public String getBelong_opt_no();
	public void setBelong_opt_no(String belong_opt_no);
	
	
	public String getBelong_opt_name();
	public void setBelong_opt_name(String Belong_opt_name);
	
	
	public String getBelong_idcard() ;
	public void setBelong_idcard(String belong_idcard); 
	
	public Date getCreate_date();
	public void setCreate_date(Date create_date) ;
	
	public String getCreat_user() ;
	public void setCreat_user(String creat_user) ;

	public String getMay_date();
	public void setMay_date(String may_date) ;
	
	
	public Date getMay_user();
	public void setMay_user(Date may_user) ;
}
