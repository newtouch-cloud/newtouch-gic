package com.newtouch.peoplemanage.model.po;

import java.sql.Date;

/**
 * 员工离职信息实体类
 * @author Ming Ying
 * @time 2017-11-30
 */
public class EmpLeaveVOModel implements IEmpLeaveVOModel{
	private String seq_id; 
	private String person_no; 
	
	private Date endtime;//离职时间
	private String reason;//离职原因
	private Date apply_date; //申报时间
	private Date checkdate;//审核时间
	private String status;//办理状态
	
	
	
	
	
	private Date  create_date;
	private String  creat_user;
	private String  may_date;
	private Date  may_user;
	
	@Override
	public String getSeq_id() {
		return seq_id;
	}
	@Override
	public void setSeq_id(String seq_id) {
		this.seq_id = seq_id;
	}
	@Override
	public String getPerson_no() {
		return person_no;
	}
	@Override
	public void setPerson_no(String person_no) {
		this.person_no = person_no;
	}
	
	@Override
	public Date getEndtime() {
		return endtime;
	}
	@Override
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	@Override
	public String getReason() {
		return reason;
	}
	@Override
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public Date getApply_date() {
		return apply_date;
	}
	@Override
	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}
	@Override
	public Date getCheckdate() {
		return checkdate;
	}
	@Override
	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}
	@Override
	public String getStatus() {
		return status;
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
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
	@Override
	public void setCreat_user(String creat_user) {
		this.creat_user = creat_user;
	}
	@Override
	public String getMay_date() {
		return may_date;
	}
	@Override
	public void setMay_date(String may_date) {
		this.may_date = may_date;
	}
	@Override
	public Date getMay_user() {
		return may_user;
	}
	@Override
	public void setMay_user(Date may_user) {
		this.may_user = may_user;
	}
	
	
	
}
