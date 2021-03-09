package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IContractCostVOModel extends IPageCount{

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getType();

	public abstract void setType(String type);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getBranch_name();

	public abstract void setBranch_name(String branch_name);

	public abstract String getInsBranch_id();

	public abstract void setInsBranch_id(String insBranch_id);

	public abstract String getInsBranch_name();

	public abstract void setInsBranch_name(String insBranch_name);

	public abstract String getProduct_name();

	public abstract void setProduct_name(String product_name);

	public abstract String getProduct_id();

	public abstract void setProduct_id(String product_id);

	public abstract String getProduct_type1_name();

	public abstract void setProduct_type1_name(String product_type1_name);

	public abstract String getProduct_type2_name();

	public abstract void setProduct_type2_name(String product_type2_name);

	public abstract String getProduct_type3_name();

	public abstract void setProduct_type3_name(String product_type3_name);

	public abstract String getSend_code();

	public abstract void setSend_code(String send_code);

	public abstract String getPolicy_code();

	public abstract void setPolicy_code(String policy_code);

	public abstract String getAgent_id();

	public abstract void setAgent_id(String agent_id);

	public abstract String getAgent_name();

	public abstract void setAgent_name(String agent_name);

	public abstract String getService_id();

	public abstract void setService_id(String service_id);

	public abstract String getService_name();

	public abstract void setService_name(String service_name);

	public abstract String getHolder_id();

	public abstract void setHolder_id(String holder_id);

	public abstract String getApp_name();

	public abstract void setApp_name(String app_name);

	public abstract String getInsurant_id();

	public abstract void setInsurant_id(String insurant_id);

	public abstract String getInsurant_name();

	public abstract void setInsurant_name(String insurant_name);

	public abstract String getIns_type_name();

	public abstract void setIns_type_name(String ins_type_name);

	public abstract String getPeriodtype_name();

	public abstract void setPeriodtype_name(String periodtype_name);

	public abstract String getCoverage_period_name();

	public abstract void setCoverage_period_name(String coverage_period_name);

	public abstract String getSell_way_name();

	public abstract void setSell_way_name(String sell_way_name);

	public abstract String getCharge_type_name();

	public abstract void setCharge_type_name(String charge_type_name);

	public abstract String getCoverage_year();

	public abstract void setCoverage_year(String coverage_year);

	public abstract String getFee_type();

	public abstract void setFee_type(String fee_type);

	public abstract String getPolicy_period();

	public abstract void setPolicy_period(String policy_period);

	public abstract Double getPeriod_prem();

	public abstract void setPeriod_prem(Double period_prem);

	public abstract Double getActual_prem();

	public abstract void setActual_prem(Double actual_prem);

	public abstract String getPay_mode_name();

	public abstract void setPay_mode_name(String pay_mode_name);

	public abstract String getMoney_id();

	public abstract void setMoney_id(String money_id);

	public abstract Date getHold_date();

	public abstract void setHold_date(Date hold_date);

	public abstract Date getDue_time();

	public abstract void setDue_time(Date due_time);

	public abstract Date getReceived_date();

	public abstract void setReceived_date(Date received_date);

	public abstract String getPosted();

	public abstract void setPosted(String posted);

	public abstract Integer getCred_id();

	public abstract void setCred_id(Integer cred_id);

	public abstract Date getPost_time();

	public abstract void setPost_time(Date post_time);

	public abstract String getFee_status();

	public abstract void setFee_status(String fee_status);

	public abstract String getStatus_name();

	public abstract void setStatus_name(String status_name);

	public abstract PageCount getPageCount();

	public abstract void setPageCount(PageCount pageCount);

	public abstract int getStart();

	public abstract int getLimit();
	
	public String getFeetype_name();

	public void setFeetype_name(String feetype_name);

}