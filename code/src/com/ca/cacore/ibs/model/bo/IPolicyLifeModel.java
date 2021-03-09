package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface IPolicyLifeModel extends IPageCount{
	
	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id) ;
	
	public Long getPolicy_id() ;

	public void setPolicy_id(Long policy_id);

	public String getBranch_id() ;

	public void setBranch_id(String branch_id) ;

	public String getInsBranch_id() ;

	public void setInsBranch_id(String insBranch_id) ;

	public String getSend_code();

	public void setSend_code(String send_code) ;

	public String getAgent_id();

	public void setAgent_id(String agent_id) ;

	public String getPolicy_code();

	public void setPolicy_code(String policy_code) ;

	public String getService_id() ;

	public void setService_id(String service_id);

	public String getHolder_id() ;

	public void setHolder_id(String holder_id) ;

	public String getInsurant_id();

	public void setInsurant_id(String insurant_id);

	public String getRelation_id() ;

	public void setRelation_id(String relation_id) ;

	public String getSell_way() ;

	public void setSell_way(String sell_way) ;

	public String getCharge_type() ;

	public void setCharge_type(String charge_type) ;

	public String getCharge_next() ;

	public void setCharge_next(String charge_next) ;

	public String getPay_mode() ;

	public void setPay_mode(String pay_mode) ;

	public String getPay_mode_next();

	public void setPay_mode_next(String pay_mode_next) ;

	public String getPolicy_year() ;

	public void setPolicy_year(String policy_year) ;

	public String getPolicy_period() ;

	public void setPolicy_period(String policy_period);

	public String getPeriod_prem() ;

	public void setPeriod_prem(String period_prem) ;

	public String getMoney_id() ;

	public void setMoney_id(String money_id) ;

	public String getBank_code();

	public void setBank_code(String bank_code) ;

	public String getAccount_type();

	public void setAccount_type(String account_type) ;

	public String getBank_accName() ;

	public void setBank_accName(String bank_accName) ;

	public String getBank_account() ;

	public void setBank_account(String bank_account) ;

	public String getOverdue_manage();

	public void setOverdue_manage(String overdue_manage);

	public Date getHold_date() ;

	public void setHold_date(Date hold_date) ;

	public Date getScan_time();

	public void setScan_time(Date scan_time);

	public Date getValidate_date() ;

	public void setValidate_date(Date validate_date) ;

	public Date getSign_date() ;

	public void setSign_date(Date sign_date) ;

	public Date getEnd_date();

	public void setEnd_date(Date end_date);

	public Date getPause_time() ;

	public void setPause_time(Date pause_time);

	public String getIs_answered() ;

	public void setIs_answered(String is_answered) ;

	public String getHigh_policy() ;
	
	public void setHigh_policy(String high_policy) ;

	public String getStatus();

	public void setStatus(String status) ;

	public String getRemark() ;

	public void setRemark(String remark);

	public String getCreateUser();

	public void setCreateUser(String createUser) ;

	public Date getCreateDate() ;

	public void setCreateDate(Date createDate);

	public String getModifyUser() ;

	public void setModifyUser(String modifyUser) ;

	public Date getModifyDate();

	public void setModifyDate(Date modifyDate);
	
	public Date getRtn_date();

	public void setRtn_date(Date rtn_date) ;

}
