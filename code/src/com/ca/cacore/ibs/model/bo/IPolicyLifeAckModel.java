package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

public interface IPolicyLifeAckModel {

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);
	
	public String getBranch_id();
	
	public void setBranch_id(String branch_id);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);

	public abstract Date getAck_branch_date();

	public abstract void setAck_branch_date(Date ack_branch_date);

	public abstract Date getAck_customer_date();

	public abstract void setAck_customer_date(Date ack_customer_date);

	public abstract String getAck_notes();

	public abstract void setAck_notes(String ack_notes);

	public abstract String getRemark();

	public abstract void setRemark(String remark);

	public abstract String getCreateUser();

	public abstract void setCreateUser(String createUser);

	public abstract Date getCreateDate();

	public abstract void setCreateDate(Date createDate);

	public abstract String getModifyUser();

	public abstract void setModifyUser(String modifyUser);

	public abstract Date getModifyDate();

	public abstract void setModifyDate(Date modifyDate);

	public String getInsBranch_id();

	public void setInsBranch_id(String insBranch_id);

}