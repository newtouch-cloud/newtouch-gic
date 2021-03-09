package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface IContractLifeModel extends IPageCount {

	public Long getPolicy_id() ;
	 
	public void setPolicy_id(Long policy_id);
	
	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getInsBranch_id();

	public abstract void setInsBranch_id(String insBranch_id);

	public String getBranch_name();

	public void setBranch_name(String branch_name);

	public String getInsBranch_name();

	public void setInsBranch_name(String insBranch_name);

	public String getApp_name();

	public void setApp_name(String app_name);

	public String getSend_code() ;
	
	public void setSend_code(String send_code) ;

	public abstract String getPolicy_code();

	public abstract void setPolicy_code(String policy_code);

	public abstract String getAgent_id();

	public abstract void setAgent_id(String agent_id);

	public abstract String getService_id();

	public abstract void setService_id(String service_id);

	public abstract String getHolder_id();

	public abstract void setHolder_id(String holder_id);

	public abstract String getInsurant_id();

	public abstract void setInsurant_id(String insurant_id);

	public abstract String getRelation_id();

	public abstract void setRelation_id(String relation_id);

	public abstract String getSell_way();

	public abstract void setSell_way(String sell_way);

	public abstract String getCharge_type();

	public abstract void setCharge_type(String charge_type);

	public abstract String getCharge_next();

	public abstract void setCharge_next(String charge_next);

	public abstract String getPay_mode();

	public abstract void setPay_mode(String pay_mode);

	public abstract String getPay_mode_next();

	public abstract void setPay_mode_next(String pay_mode_next);

	public abstract String getPolicy_year();

	public abstract void setPolicy_year(String policy_year);

	public abstract String getPolicy_period();

	public abstract void setPolicy_period(String policy_period);

	public abstract String getPeriod_prem();

	public abstract void setPeriod_prem(String period_prem);

	public abstract String getMoney_id();

	public abstract void setMoney_id(String money_id);

	public abstract String getBank_code();

	public abstract void setBank_code(String bank_code);

	public abstract String getAccount_type();

	public abstract void setAccount_type(String account_type);

	public abstract String getBank_accName();

	public abstract void setBank_accName(String bank_accName);

	public abstract String getBank_account();

	public abstract void setBank_account(String bank_account);

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

	public abstract String getStatus();

	public abstract void setStatus(String status);

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