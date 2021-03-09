package com.ca.cacore.ams.model.bo;

import java.sql.Date;

public class AnnouncementApprModel  implements IAnnouncementApprModel{
	private Integer seq_id;//主键
	private String branch_id;//通告机构
	private String announcement_id;//公告编号
	private Integer approval_id;//审批单编号
	private String applicant;//申请人
	private Date application_time;//申请时间
	private String approval;//审批人
	private Date approval_time;//审批时间
	private String approval_status;//审批状态
	private String remark;//备注
	private String createUser;//创建人
	private Date createDate;//创建时间
	private String modifyUser;//最后修改人
	private Date modifyDate;//最后修改时间
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getAnnouncement_id() {
		return announcement_id;
	}
	public void setAnnouncement_id(String announcement_id) {
		this.announcement_id = announcement_id;
	}
	public Integer getApproval_id() {
		return approval_id;
	}
	public void setApproval_id(Integer approval_id) {
		this.approval_id = approval_id;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public Date getApplication_time() {
		return application_time;
	}
	public void setApplication_time(Date application_time) {
		this.application_time = application_time;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public Date getApproval_time() {
		return approval_time;
	}
	public void setApproval_time(Date approval_time) {
		this.approval_time = approval_time;
	}
	public String getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
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
