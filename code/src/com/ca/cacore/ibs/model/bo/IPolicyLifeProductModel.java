package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

public interface IPolicyLifeProductModel {
	
	public String getIs_autoRen();
	public void setIs_autoRen(String is_autoRen) ;
	
	public Integer getAuth_payAge() ;
	public void setAuth_payAge(Integer auth_payAge) ;
	public String getAuth_draw();
	public void setAuth_draw(String auth_draw);
	public Integer getAuth_firstPay() ;
	public void setAuth_firstPay(Integer auth_firstPay);
	public String getDividend_choice() ;
	public void setDividend_choice(String dividend_choice);
	public Integer getSeq_id();
	
	public void setSeq_id(Integer seq_id);
	
	public Integer getItem_id();
	
	public void setItem_id(Integer item_id);
	
	public String getBranch_id() ;
	
	public void setBranch_id(String branch_id);
	
	public String getInsBranch_id();
	
	public void setInsBranch_id(String insbranch_id);
	
	public Long getPolicy_id() ;
	
	public void setPolicy_id(Long policy_id);
	
	public String getSend_code();
	
	public void setSend_code(String send_code) ;
	
	public String getApp_id() ;
	
	public void setApp_id(String app_id) ;
	
	public String getInsurant_id() ;
	
	public void setInsurant_id(String insurant_id);
	
	public String getRelation_id() ;
	
	public void setRelation_id(String relation_id) ;
	
	public String getProduct_id();
	
	public void setProduct_id(String product_id);
	
	public String getIns_type();
	
	public void setIns_type(String ins_type);
	
	public String getProduct_type();
	
	public void setProduct_type(String product_type) ;
	
	public String getProduct_type2() ;
	
	public void setProduct_type2(String product_type2) ;
	
	public String getProduct_type3() ;
	
	public void setProduct_type3(String product_type3);
	
	public String getSell_way() ;
	
	public void setSell_way(String sell_way) ;
	
	public String getCharge_type();
	
	public void setCharge_type(String charge_type);
	
	public String getCharge_next() ;
	
	public void setCharge_next(String charge_next);
	
	public String getCharge_period() ;
	
	public void setCharge_period(String charge_period);
	
	public String getPay_mode() ;
	
	public void setPay_mode(String pay_mode);
	
	public String getPay_mode_next() ;
	
	public void setPay_mode_next(String pay_mode_next) ;
	
	public Integer getPolicy_year() ;
	
	public void setPolicy_year(Integer policy_year);
	
	public Integer getCharge_year() ;
	
	public void setCharge_year(Integer charge_year) ;
	
	public String getCoverage_period();
	
	public void setCoverage_period(String coverage_period) ;
	
	public Integer getCoverage_year();
	
	public void setCoverage_year(Integer coverage_year);
	
	public String getPeriod_type();
	
	public void setPeriod_type(String period_type) ;
	
	public Integer getUnit() ;
	
	public void setUnit(Integer unit) ;
	
	public Double getAmount() ;
	
	public void setAmount(Double amount) ;
	
	public Double getPeriod_prem();
	
	public void setPeriod_prem(Double period_prem);
	
	public String getMoney_id() ;
	
	public void setMoney_id(String money_id) ;
	
	public String getOverdue_manage() ;
	
	public void setOverdue_manage(String overdue_manage);
	
	public Date getHold_date();
	
	public void setHold_date(Date hold_date) ;
	
	public Date getScan_time() ;
	
	public void setScan_time(Date scan_time);
	
	public Date getValidate_date() ;
	
	public void setValidate_date(Date validate_date);
	
	public Date getDue_time() ;
	
	public void setDue_time(Date due_time) ;
	
	public Date getNext_due_time() ;
	
	public void setNext_due_time(Date next_due_time);
	
	public Date getSign_date() ;
	
	public void setSign_date(Date sign_date) ;
	
	public Date getRtn_date() ;
	
	public void setRtn_date(Date rtn_date) ;
	
	public Date getEnd_date();
	
	public void setEnd_date(Date end_date);
	
	public Date getPause_time();
	
	public void setPause_time(Date pause_time);
	
	public String getIs_answered();
	
	public void setIs_answered(String is_answered);
	
	public String getHigh_policy() ;
	
	public void setHigh_policy(String high_policy) ;
	
	public String getInsure_stop();
	
	public void setInsure_stop(String insure_stop);
	
	public String getIns_status();
	
	public void setIns_status(String ins_status);
	
	public String getRemark();
	
	public void setRemark(String remark);
	
	public String getCreateuser() ;
	
	public void setCreateuser(String createuser) ;
	
	public Date getCreatedate() ;
	
	public void setCreatedate(Date createdate);
	
	public String getModifyuser() ;
	
	public void setModifyuser(String modifyuser);
	
	public Date getModifydate() ;
	
	public void setModifydate(Date modifydate) ;

}
