package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.ca.cacore.ibs.model.bo.IPolicyImageModel;
import com.newtouch.core.model.IPageCount;

public interface IPolicyLifeAckVOModel  extends IPageCount{
	
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
	
	public String getInsBranch_name();

	public void setInsBranch_name(String insBranch_name);

	public String getAgent_name();

	public void setAgent_name(String agent_name);

	public String getInsurant_name();

	public void setInsurant_name(String insurant_name);

	public String getService_name();

	public void setService_name(String service_name);

	public String getStatus_name();

	public void setStatus_name(String status_name);

	public String getStatus();

	public void setStatus(String status);

	public IPolicyImageModel getPolicyImageModel();

	public void setPolicyImageModel(IPolicyImageModel policyImageModel);

	public String getApp_name();

	public void setApp_name(String app_name);

	public String getBranch_name();

	public void setBranch_name(String branch_name);

	public String getSend_code();

	public void setSend_code(String send_code);

	public abstract String getPolicy_code();

	public abstract void setPolicy_code(String policy_code);

	public abstract Date getHold_date();

	public abstract void setHold_date(Date hold_date);

	public abstract String getHold_dateStr();

	public abstract void setHold_dateStr(String hold_dateStr);

	public abstract String getAck_branch_dateStr();

	public abstract void setAck_branch_dateStr(String ack_branch_dateStr);

	public abstract String getAck_customer_dateStr();

	public abstract void setAck_customer_dateStr(String ack_customer_dateStr);

}