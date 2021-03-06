package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

public interface IPolicyLifeProductFeePremVOModel {
	public Integer getLog_seq_id() ;
	public void setLog_seq_id(Integer log_seq_id);
	public String getLog_bustype();
	public void setLog_bustype(String log_bustype) ;
	public Date getLog_busdate() ;
	public void setLog_busdate(Date log_busdate) ;
	public Date getLog_date() ;
	public void setLog_date(Date log_date);
	public String getLog_remark() ;
	public void setLog_remark(String log_remark) ;
	public String getIs_autoRen();
	public void setIs_autoRen(String is_autoRen) ;
	public Integer getAuth_payAge() ;
	public void setAuth_payAge(Integer auth_payAge) ;
	public String getAuth_draw();
	public void setAuth_draw(String auth_draw);
	public Double getAuth_firstPay() ;
	public void setAuth_firstPay(Double auth_firstPay);
	public String getDividend_choice() ;
	public void setDividend_choice(String dividend_choice);
	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract Integer getItem_id();

	public abstract void setItem_id(Integer item_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getInsBranch_id();

	public abstract void setInsBranch_id(String insBranch_id);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);

	public abstract String getSend_code();

	public abstract void setSend_code(String send_code);

	public abstract String getApp_id();

	public abstract void setApp_id(String app_id);

	public abstract String getInsurant_id();

	public abstract void setInsurant_id(String insurant_id);

	public abstract String getRelation_id();

	public abstract void setRelation_id(String relation_id);

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

	public abstract String getSell_way();

	public abstract void setSell_way(String sell_way);

	public abstract String getCharge_type();

	public abstract void setCharge_type(String charge_type);

	public abstract String getCharge_next();

	public abstract void setCharge_next(String charge_next);

	public abstract String getCharge_period();

	public abstract void setCharge_period(String charge_period);

	public abstract String getPay_mode();

	public abstract void setPay_mode(String pay_mode);

	public abstract String getPay_mode_next();

	public abstract void setPay_mode_next(String pay_mode_next);

	public abstract Integer getPolicy_year();

	public abstract void setPolicy_year(Integer policy_year);

	public abstract Integer getCharge_year();

	public abstract void setCharge_year(Integer charge_year);

	public abstract String getCoverage_period();

	public abstract void setCoverage_period(String coverage_period);

	public abstract Integer getCoverage_year();

	public abstract void setCoverage_year(Integer coverage_year);

	public abstract String getPeriod_type();

	public abstract void setPeriod_type(String period_type);

	public abstract Integer getUnit();

	public abstract void setUnit(Integer unit);

	public abstract Double getAmount();

	public abstract void setAmount(Double amount);

	public abstract Double getPeriod_prem();

	public abstract void setPeriod_prem(Double period_prem);

	public abstract String getMoney_id();

	public abstract void setMoney_id(String money_id);

	public abstract String getOverdue_manage();

	public abstract void setOverdue_manage(String overdue_manage);

	public abstract Date getHold_date();

	public abstract void setHold_date(Date hold_date);

	public abstract Date getScan_time();

	public abstract void setScan_time(Date scan_time);

	public abstract Date getValidate_date();

	public abstract void setValidate_date(Date validate_date);

	public abstract Date getDue_time();

	public abstract void setDue_time(Date due_time);

	public abstract Date getNext_due_time();

	public abstract void setNext_due_time(Date next_due_time);

	public abstract Date getSign_date();

	public abstract void setSign_date(Date sign_date);

	public abstract Date getRtn_date();

	public abstract void setRtn_date(Date rtn_date);

	public abstract Date getEnd_date();

	public abstract void setEnd_date(Date end_date);

	public abstract Date getPause_time();

	public abstract void setPause_time(Date pause_time);

	public abstract String getIs_answered();

	public abstract void setIs_answered(String is_answered);

	public abstract String getHigh_policy();

	public abstract void setHigh_policy(String high_policy);

	public abstract String getInsure_stop();

	public abstract void setInsure_stop(String insure_stop);

	public abstract String getIns_status();

	public abstract void setIns_status(String ins_status);

	public abstract String getRemark();

	public abstract void setRemark(String remark);

	public abstract String getCreateuser();

	public abstract void setCreateuser(String createuser);

	public abstract Date getCreatedate();

	public abstract void setCreatedate(Date createdate);

	public abstract String getModifyuser();

	public abstract void setModifyuser(String modifyuser);

	public abstract Date getModifydate();

	public abstract void setModifydate(Date modifydate);

	public abstract Integer getFee_id();

	public abstract void setFee_id(Integer fee_id);

	public abstract String getAgent_id();

	public abstract void setAgent_id(String agent_id);

	public abstract String getService_id();

	public abstract void setService_id(String service_id);

	public abstract Integer getPolicy_period();

	public abstract void setPolicy_period(Integer policy_period);

	public abstract String getFee_type();

	public abstract void setFee_type(String fee_type);

	public abstract Double getActual_prem();

	public abstract void setActual_prem(Double actual_prem);

	public abstract String getBank_code();

	public abstract void setBank_code(String bank_code);

	public abstract String getBank_account();

	public abstract void setBank_account(String bank_account);

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

	public abstract String getCreateUser();

	public abstract void setCreateUser(String createUser);

	public abstract Date getCreateDate();

	public abstract void setCreateDate(Date createDate);

	public abstract String getModifyUser();

	public abstract void setModifyUser(String modifyUser);

	public abstract Date getModifyDate();

	public abstract void setModifyDate(Date modifyDate);

	public abstract Integer getPrem_id();

	public abstract void setPrem_id(Integer prem_id);

	public abstract String getCustomer_id();

	public abstract void setCustomer_id(String customer_id);

}