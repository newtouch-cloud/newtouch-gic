package com.ca.cacore.ibs.model.vo;

import java.sql.Date;
import java.util.List;

import com.newtouch.core.model.IPageCount;

public interface IPolicyLifeVOModel  extends IPageCount{
	public String getBef_status();
	public void setBef_status(String bef_status) ;
	public String getAft_status();
	public void setAft_status(String aft_status) ;
	public String[] getProduct_insurant_id() ;
	public void setProduct_insurant_id(String[] product_insurant_id) ;
	public String[] getAuth_payAge_arr() ;
	public void setAuth_payAge_arr(String[] auth_payAge_arr) ;
	public String[] getAuth_draw_arr() ;
	public void setAuth_draw_arr(String[] auth_draw_arr);
	public String[] getAuth_firstPay_arr() ;
	public void setAuth_firstPay_arr(String[] auth_firstPay_arr) ;
	public String[] getDividend_choice_arr() ;
	public void setDividend_choice_arr(String[] dividend_choice_arr) ;
	public String[] getIs_autoRen_arr();
	public void setIs_autoRen_arr(String[] is_autoRen_arr);
	public String getResult_flag();
	public void setResult_flag(String result_flag);
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
	
	public String[] getPolicyLifeBeneficiay_bene_order() ;
	
	public void setPolicyLifeBeneficiay_bene_order(
			String[] policyLifeBeneficiay_bene_order) ;

	public String[] getPolicyLifeBeneficiay_bene_rate();

	public void setPolicyLifeBeneficiay_bene_rate(
			String[] policyLifeBeneficiay_bene_rate);
	
	public String[] getPeriod_prem_arr() ;

	public void setPeriod_prem_arr(String[] period_prem_arr);
	
	public String[] getProduct_id_arr() ;

	public void setProduct_id_arr(String[] product_id_arr);

	public String[] getCoverage_period_arr();

	public void setCoverage_period_arr(String[] coverage_period_arr);

	public String[] getCoverage_year_arr();

	public void setCoverage_year_arr(String[] coverage_year_arr) ;

	public String[] getCharge_period_arr() ;

	public void setCharge_period_arr(String[] charge_period_arr) ;

	public String[] getCharge_year_arr() ;

	public void setCharge_year_arr(String[] charge_year_arr) ;

	public String[] getCharge_type_arr();

	public void setCharge_type_arr(String[] charge_type_arr) ;

	public String[] getAmount_arr() ;

	public void setAmount_arr(String[] amount_arr) ;
	
	public Integer getItem_id() ;

	public void setItem_id(Integer item_id) ;

	public String getApp_id() ;

	public void setApp_id(String app_id) ;

	public Integer getUnit() ;

	public void setUnit(Integer unit);

	public Double getAmount() ;

	public void setAmount(Double amount) ;

	public Date getNext_due_time() ;

	public void setNext_due_time(Date next_due_time) ;
	public String getInsure_stop();

	public void setInsure_stop(String insure_stop) ;
	
	public String getFee_type() ;

	public void setFee_type(String fee_type);

	public Double getActual_prem() ;

	public void setActual_prem(Double actual_prem);

	public Integer getFee_id() ;

	public void setFee_id(Integer fee_id) ;

	public String getIns_type() ;

	public void setIns_type(String ins_type);

	public String getProduct_type();

	public void setProduct_type(String product_type) ;

	public String getProduct_type2();

	public void setProduct_type2(String product_type2) ;

	public String getProduct_type3() ;

	public void setProduct_type3(String product_type3) ;

	public String getPeriod_type() ;

	public void setPeriod_type(String period_type);

	public Date getDue_time() ;

	public void setDue_time(Date due_time);

	public Date getReceived_date() ;

	public void setReceived_date(Date received_date) ;

	public String getFee_status() ;

	public void setFee_status(String fee_status) ;

	public String getPosted() ;

	public void setPosted(String posted);

	public String getPost_id() ;

	public void setPost_id(String post_id) ;

	public Integer getCred_id() ;

	public void setCred_id(Integer cred_id) ;

	public Date getPost_time() ;

	public void setPost_time(Date post_time);
	
	public Integer getPrem_id() ;

	public void setPrem_id(Integer prem_id) ;

	public String getCustomer_id();

	public void setCustomer_id(String customer_id);
	
	public String[] getPolicyLifeInsured_relation1() ;

	public void setPolicyLifeInsured_relation1(String[] policyLifeInsured_relation1) ;
	
	public String[] getPolicyLifeInsured_relation_code() ;

	public void setPolicyLifeInsured_relation_code(String[] policyLifeInsured_relation_code);
	
	public String[] getPolicyPeople_customer_id();

	public void setPolicyPeople_customer_id(String[] policyPeople_customer_id) ;

	public String[] getPolicyLifeInsured_customer_id() ;

	public void setPolicyLifeInsured_customer_id(
			String[] policyLifeInsured_customer_id) ;

	public String[] getPolicyLifeBeneficiary_customer_id() ;

	public void setPolicyLifeBeneficiary_customer_id(
			String[] policyLifeBeneficiary_customer_id) ;

	public String[] getPolicyLifeBeneficiary_insurant_name_value() ;

	public void setPolicyLifeBeneficiary_insurant_name_value(
			String[] policyLifeBeneficiary_insurant_name_value) ;

	public String[] getPolicyLifeBeneficiary_relation1() ;

	public void setPolicyLifeBeneficiary_relation1(
			String[] policyLifeBeneficiary_relation1) ;

	public String[] getPolicyLifeBeneficiary_relation_code();

	public void setPolicyLifeBeneficiary_relation_code(
			String[] policyLifeBeneficiary_relation_code);
	public String[] getPolicyLifeBeneficiary_bene_type_code();

	public void setPolicyLifeBeneficiary_bene_type_code(String[] policyLifeBeneficiary_bene_type_code);

	public abstract Integer getSeq_id();

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getName_tow();

	public abstract void setName_tow(String name_tow);

	public abstract String getRelation();

	public abstract void setRelation(String relation);

	public abstract String getCerti_type();

	public abstract void setCerti_type(String certi_type);

	public abstract String getCerti_no();

	public abstract void setCerti_no(String certi_no);

	public abstract String getCerti_no_tow();

	public abstract void setCerti_no_tow(String certi_no_tow);

	public abstract String getGender();

	public abstract void setGender(String gender);

	public abstract String getBirthday();

	public abstract void setBirthday(String birthday);

	public abstract String getNationality();

	public abstract void setNationality(String nationality);

	public abstract String getNation();

	public abstract void setNation(String nation);

	public abstract String getHomeplace();

	public abstract void setHomeplace(String homeplace);

	public abstract String getMarital_stat();

	public abstract void setMarital_stat(String marital_stat);

	public abstract String getPolitical();

	public abstract void setPolitical(String political);

	public abstract String getEducation2();

	public abstract void setEducation2(String education2);

	public abstract String getHealth();

	public abstract void setHealth(String health);

	public abstract String getHeight();

	public abstract void setHeight(String height);

	public abstract String getWeight();

	public abstract void setWeight(String weight);

	public abstract String getMobile();

	public abstract void setMobile(String mobile);

	public abstract String getTelphone();

	public abstract void setTelphone(String telphone);

	public abstract String getFax();

	public abstract void setFax(String fax);

	public abstract String getEmail();

	public abstract void setEmail(String email);

	public abstract String getAddress();

	public abstract void setAddress(String address);

	public abstract String getZip();

	public abstract void setZip(String zip);

	public abstract String getJob_com();

	public abstract void setJob_com(String job_com);

	public abstract String getJob_type();

	public abstract void setJob_type(String job_type);

	public abstract String getJob_code();

	public abstract void setJob_code(String job_code);

	public abstract String getJob_tel();

	public abstract void setJob_tel(String job_tel);

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getPolicy_tr_id();

	public abstract void setPolicy_tr_id(String policy_tr_id);

	public abstract String getType();

	public abstract void setType(String type);

	public abstract String getRelation1();

	public abstract void setRelation1(String relation1);

	public abstract String getBene_type();

	public abstract void setBene_type(String bene_type);

	public abstract String getJsp_url();

	public abstract void setJsp_url(String jsp_url);

	public abstract String getProduct_id();

	public abstract void setProduct_id(String product_id);

	public abstract String getProduct_name();

	public abstract void setProduct_name(String product_name);

	public abstract String getCoverage_period();

	public abstract void setCoverage_period(String coverage_period);

	public abstract Integer getCoverage_year();

	public abstract void setCoverage_year(Integer coverage_year);

	public abstract String getCharge_period();

	public abstract void setCharge_period(String charge_period);

	public abstract Integer getCharge_year();

	public abstract void setCharge_year(Integer charge_year);

	public abstract String getCharge_type_code();

	public abstract void setCharge_type_code(String charge_type_code);

	public abstract String getCoverage_time();

	public abstract void setCoverage_time(String coverage_time);

	public abstract Double getPeriod_prem();

	public abstract void setPeriod_prem(Double period_prem);

	public abstract String getInsurant_name();

	public abstract void setInsurant_name(String insurant_name);

	public abstract String getInsurant_name_arr();

	public abstract void setInsurant_name_arr(String insurant_name_arr);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getInsBranch_id();

	public abstract void setInsBranch_id(String insBranch_id);

	public abstract String getSend_code();

	public abstract void setSend_code(String send_code);

	public abstract String getAgent_id();

	public abstract void setAgent_id(String agent_id);

	public abstract String getPolicy_code();

	public abstract void setPolicy_code(String policy_code);

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

	public abstract Integer getPolicy_year();

	public abstract void setPolicy_year(Integer policy_year);

	public abstract Integer getPolicy_period();

	public abstract void setPolicy_period(Integer policy_period);

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

	public abstract Date getSign_date();

	public abstract void setSign_date(Date sign_date);

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

	public abstract Date getRtn_date();

	public abstract void setRtn_date(Date rtn_date);
	
	public String getAgent_name() ;

	public void setAgent_name(String agent_name) ;

	public String getService_name() ;

	public void setService_name(String service_name);

	public String getApp_name() ;

	public void setApp_name(String app_name);

	public String getStatus_name();

	public void setStatus_name(String status_name);
	
	public String getBranch_name() ;

	public void setBranch_name(String branch_name);
	
	public String getInsBranch_name() ;

	public void setInsBranch_name(String insBranch_name);
	
	public String getRelation_name() ;
	
	public void setRelation_name(String relation_name);
	
	public String getInsurant_name_selected();
	
	public void setInsurant_name_selected(String insurant_name_selected);
	
	public String getRenew() ;
	
	public void setRenew(String renew) ;
	public List<IPolicyLifePeopleVOModel> getHolderListView();
	public void setHolderListView(List<IPolicyLifePeopleVOModel> holderListView) ;
	public List<IPolicyLifePeopleVOModel> getInsurantListView();
	public void setInsurantListView(List<IPolicyLifePeopleVOModel> insurantListView) ;
	public List<IPolicyLifePeopleVOModel> getBeneficiaryListView() ;
	public void setBeneficiaryListView(
			List<IPolicyLifePeopleVOModel> beneficiaryListView);
	public List<IPolicyLifeProductVOModel> getProductListView() ;
	public void setProductListView(List<IPolicyLifeProductVOModel> productListView) ;

}