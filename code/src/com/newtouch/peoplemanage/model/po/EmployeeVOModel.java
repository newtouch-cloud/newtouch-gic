package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

public class EmployeeVOModel implements IEmployeeVOModel{
	private String  seq_id;
	private String  person_id;
	private String  person_name;
	private String  branch_id;
	private String  team_id;
	private Date  start_date;
	private Date  end_date;
	private String  person_status;
	private String  org_id;
	private String  belong_opt_no;
	private String  belong_opt_name;
	private String  belong_idcard;
	private Date  create_date;
	private String  creat_user;
	private String  may_date;
	private Date  may_user;
	
	
	@Override
	public String getSeq_id() {
		// TODO Auto-generated method stub
		return seq_id;
	}

	@Override
	public void setSeq_id(String seq_id) {
		// TODO Auto-generated method stub
		this.seq_id = seq_id;
	}

	@Override
	public String getPerson_id() {
		// TODO Auto-generated method stub
		return person_id;
	}

	@Override
	public void setPerson_id(String person_id) {
		// TODO Auto-generated method stub
		this.person_id = person_id;
		
	}

	@Override
	public String getPerson_name() {
		// TODO Auto-generated method stub
		return person_name;
	}

	@Override
	public void setPerson_name(String person_name) {
		// TODO Auto-generated method stub
		this.person_name = person_name;
	}

	@Override
	public String getBranch_id() {
		// TODO Auto-generated method stub
		return branch_id;
	}

	@Override
	public void setBranch_id(String branch_id) {
		// TODO Auto-generated method stub
		this.branch_id = branch_id;
	}

	@Override
	public String getTeam_id() {
		// TODO Auto-generated method stub
		return team_id;
	}

	@Override
	public void setTeam_id(String team_id) {
		// TODO Auto-generated method stub
		this.team_id = team_id;
	}

	@Override
	public Date getStart_date() {
		// TODO Auto-generated method stub
		return start_date;
	}

	@Override
	public void setStart_date(Date start_date) {
		// TODO Auto-generated method stub
		this.start_date = start_date;
	}

	@Override
	public Date getEnd_date() {
		// TODO Auto-generated method stub
		return end_date;
	}

	@Override
	public void setEnd_date(Date end_date) {
		// TODO Auto-generated method stub
		this.end_date = end_date;
	}

	@Override
	public String getPerson_status() {
		// TODO Auto-generated method stub
		return person_status;
	}

	@Override
	public void setPerson_status(String person_status) {
		// TODO Auto-generated method stub
		this.person_status = person_status;
	}

	@Override
	public String getOrg_id() {
		// TODO Auto-generated method stub
		return org_id;
	}

	@Override
	public void setOrg_id(String org_id) {
		// TODO Auto-generated method stub
		this.org_id = org_id;
	}
	@Override
	public String getBelong_opt_no() {
		return belong_opt_no;
	}
	@Override
	public void setBelong_opt_no(String belong_opt_no) {
		this.belong_opt_no = belong_opt_no;
	}
	@Override
	public String getBelong_opt_name() {
		return belong_opt_name;
	}
	@Override
	public void setBelong_opt_name(String belong_opt_name) {
		this.belong_opt_name = belong_opt_name;
	}
	@Override
	public String getBelong_idcard() {
		return belong_idcard;
	}
	@Override
	public void setBelong_idcard(String belong_idcard) {
		this.belong_idcard = belong_idcard;
	}
	@Override
	public Date getCreate_date() {
		return create_date;
	}
	@Override
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String getCreat_user() {
		return creat_user;
	}

	public void setCreat_user(String creat_user) {
		this.creat_user = creat_user;
	}

	public String getMay_date() {
		return may_date;
	}

	public void setMay_date(String may_date) {
		this.may_date = may_date;
	}

	public Date getMay_user() {
		return may_user;
	}

	public void setMay_user(Date may_user) {
		this.may_user = may_user;
	}

	
	
}
