package com.ca.cacore.ibs.model.bo;

import java.sql.Date;


/**
 * 保单回执录入信息对应的数据表：CBS_PolicyLife_ack
 * @author xxz521
 *
 */
public class PolicyLifeAckModel implements IPolicyLifeAckModel {
	private Integer seq_id; // 序号
	private Long policy_id; // 保单号
	private String branch_id; // 机构代码
	private Date ack_branch_date;// 公司签收回执日期
	private Date ack_customer_date;// 客户签收回执日期
	private String ack_notes;//回执说明
	private String remark;//备注
	private String createUser; // 创建人
	private Date createDate; // 创建时间
	private String modifyUser; // 最后修改人
	private Date modifyDate; // 最后时间
	private String insBranch_id; // 保险公司机构
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
	public Long getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(Long policy_id) {
		this.policy_id = policy_id;
	}
	public Date getAck_branch_date() {
		return ack_branch_date;
	}
	public void setAck_branch_date(Date ack_branch_date) {
		this.ack_branch_date = ack_branch_date;
	}
	public Date getAck_customer_date() {
		return ack_customer_date;
	}
	public void setAck_customer_date(Date ack_customer_date) {
		this.ack_customer_date = ack_customer_date;
	}
	public String getAck_notes() {
		return ack_notes;
	}
	public void setAck_notes(String ack_notes) {
		this.ack_notes = ack_notes;
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
	public String getInsBranch_id() {
		return insBranch_id;
	}
	public void setInsBranch_id(String insBranch_id) {
		this.insBranch_id = insBranch_id;
	}
}
