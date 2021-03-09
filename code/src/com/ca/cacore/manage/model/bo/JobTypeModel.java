package com.ca.cacore.manage.model.bo;

import java.sql.Date;

public class JobTypeModel {
	public Integer seq_id;
	public String jobtype_code;
	public String jobtype_name;
	public Integer orderNum;
	public String remark;
	public String createUser;
	public Date createDate;
	public String modifyUser;
	public Date modifyDate;
	public JobTypeModel() {
		super();
	}
	public JobTypeModel(Integer seq_id) {
		super();
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getJobtype_code() {
		return jobtype_code;
	}
	public void setJobtype_code(String jobtype_code) {
		this.jobtype_code = jobtype_code;
	}
	public String getJobtype_name() {
		return jobtype_name;
	}
	public void setJobtype_name(String jobtype_name) {
		this.jobtype_name = jobtype_name;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
