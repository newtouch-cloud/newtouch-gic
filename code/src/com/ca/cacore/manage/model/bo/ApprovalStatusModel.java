package com.ca.cacore.manage.model.bo;

import java.sql.Date;

public class ApprovalStatusModel {
	public Integer seq_id;
	public String approval_status_code;
	public String approval_status_name;
	public Integer ordernum;
	public String remark;
	public String createuser;
	public Date createDate;
	public String modifyuser;
	public Date modifyDate;
	public ApprovalStatusModel() {
		super();
	}
	public ApprovalStatusModel(Integer seq_id) {
		super();
		this.seq_id = seq_id;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getApproval_status_code() {
		return approval_status_code;
	}
	public void setApproval_status_code(String approval_status_code) {
		this.approval_status_code = approval_status_code;
	}
	public String getApproval_status_name() {
		return approval_status_name;
	}
	public void setApproval_status_name(String approval_status_name) {
		this.approval_status_name = approval_status_name;
	}
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
