package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

public interface IPolicyLifeProductFeeModel {
	
	public String getMoney_id();
	
	public void setMoney_id(String money_id);
	public Integer getCharge_year() ;
	public void setCharge_year(Integer charge_year) ;
	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract Integer getFee_id();

	public abstract void setFee_id(Integer fee_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getInsBranch_id();

	public abstract void setInsBranch_id(String insBranch_id);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);

	public abstract String getSend_code();

	public abstract void setSend_code(String send_code);

	public abstract String getAgent_id();

	public abstract void setAgent_id(String agent_id);

	public abstract String getService_id();

	public abstract void setService_id(String service_id);

	public abstract String getProduct_id();

	public abstract void setProduct_id(String product_id);

	public abstract String getIns_type();

	public abstract void setIns_type(String ins_type);

	public abstract String getProduct_type();

	public abstract void setProduct_type(String product_type);

	public abstract String getProduct_type2();

	public abstract void setProduct_type2(String product_type2);

	public abstract String getProduct_type3();

	public abstract void setProduct_type3(String product_type3);

	public abstract String getCoverage_period();

	public abstract void setCoverage_period(String coverage_period);

	public abstract String getPeriod_type();

	public abstract void setPeriod_type(String period_type);

	public abstract String getSell_way();

	public abstract void setSell_way(String sell_way);

	public abstract String getCharge_type();

	public abstract void setCharge_type(String charge_type);

	public abstract Integer getPolicy_year();

	public abstract void setPolicy_year(Integer policy_year);

	public abstract Integer getPolicy_period();

	public abstract void setPolicy_period(Integer policy_period);

	public abstract String getFee_type();

	public abstract void setFee_type(String fee_type);

	public abstract Double getPeriod_prem();

	public abstract void setPeriod_prem(Double period_prem);

	public abstract Double getActual_prem();

	public abstract void setActual_prem(Double actual_prem);

	public abstract String getPay_mode();

	public abstract void setPay_mode(String pay_mode);

	public abstract String getBank_code();

	public abstract void setBank_code(String bank_code);

	public abstract String getBank_account();

	public abstract void setBank_account(String bank_account);

	public abstract Date getHold_date();

	public abstract void setHold_date(Date hold_date);

	public abstract Date getDue_time();

	public abstract void setDue_time(Date due_time);

	public abstract Date getReceived_date();

	public abstract void setReceived_date(Date received_date);

	public abstract String getFee_status();

	public abstract void setFee_status(String fee_status);

	public abstract String getPosted();

	public abstract void setPosted(String posted);

	public abstract String getPost_id();

	public abstract void setPost_id(String post_id);

	public abstract Integer getCred_id();

	public abstract void setCred_id(Integer cred_id);

	public abstract Date getPost_time();

	public abstract void setPost_time(Date post_time);

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