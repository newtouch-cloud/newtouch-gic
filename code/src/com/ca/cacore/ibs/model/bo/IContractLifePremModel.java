package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

public interface IContractLifePremModel {

	public String getProduct_id() ;
	
	public void setProduct_id(String product_id) ;
	
	public abstract Integer getPrem_id();

	public abstract void setPrem_id(Integer prem_id);

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getInsBranch_id();

	public abstract void setInsBranch_id(String insBranch_id);

	public abstract Integer getPolicy_id();

	public abstract void setPolicy_id(Integer policy_id);

	public abstract String getSend_code();

	public abstract void setSend_code(String send_code);

	public abstract String getPolicy_code();

	public abstract void setPolicy_code(String policy_code);

	public abstract String getAgent_id();

	public abstract void setAgent_id(String agent_id);

	public abstract String getService_id();

	public abstract void setService_id(String service_id);

	public abstract String getCustomer_id();

	public abstract void setCustomer_id(String customer_id);

	public abstract String getInsurant_id();

	public abstract void setInsurant_id(String insurant_id);

	public abstract Integer getPolicy_year();

	public abstract void setPolicy_year(Integer policy_year);

	public abstract Integer getPolicy_period();

	public abstract void setPolicy_period(Integer policy_period);

	public abstract String getFee_type();

	public abstract void setFee_type(String fee_type);

	public abstract String getMoney_id();

	public abstract void setMoney_id(String money_id);

	public abstract String getPay_mode();

	public abstract void setPay_mode(String pay_mode);

	public abstract String getBank_code();

	public abstract void setBank_code(String bank_code);

	public abstract String getBank_account();

	public abstract void setBank_account(String bank_account);

	public abstract Double getPeriod_prem();

	public abstract void setPeriod_prem(Double period_prem);

	public abstract Double getActual_prem();

	public abstract void setActual_prem(Double actual_prem);

	public abstract Date getDue_time();

	public abstract void setDue_time(Date due_time);

	public abstract String getFee_status();

	public abstract void setFee_status(String fee_status);

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

}