package com.ca.cacore.ibs.model.vo;

import java.sql.Date;
import java.util.List;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IContractLifeVOModel extends IPageCount {
	public String getIs_success() ;
	public void setIs_success(String is_success) ;
	public String getBef_status() ;
	public void setBef_status(String bef_status) ;
	public String getAft_status();
	public void setAft_status(String aft_status);
	public String getAnswer_type();

	public void setAnswer_type(String answer_type);

	public String getInsurant_name();

	public void setInsurant_name(String insurant_name);

	public String getProduct_id();

	public void setProduct_id(String product_id);

	public String getProduct_name();

	public void setProduct_name(String product_name);

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getType();

	public void setType(String type);

	public abstract String getJsp_url();

	public abstract void setJsp_url(String jsp_url);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getInsBranch_id();

	public abstract void setInsBranch_id(String insBranch_id);

	public abstract String getInsBranch_name();

	public abstract void setInsBranch_name(String insBranch_name);

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

	public abstract String getRelation_id();

	public abstract void setRelation_id(String relation_id);

	public abstract String getRelation_name();

	public abstract void setRelation_name(String relation_name);

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

	public abstract Double getPeriod_prem();

	public abstract void setPeriod_prem(Double period_prem);

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

	public abstract String getStatus_name();

	public abstract void setStatus_name(String status_name);

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

	public abstract String getBranch_name();

	public abstract void setBranch_name(String branch_name);

	public List<IContractLifePeopleVOModel> getHolderListView() ;
	public void setHolderListView(List<IContractLifePeopleVOModel> holderListView) ;
	public List<IContractLifePeopleVOModel> getInsurantListView();
	public void setInsurantListView(
			List<IContractLifePeopleVOModel> insurantListView) ;
	public List<IContractLifePeopleVOModel> getBeneficiaryListView() ;
	public void setBeneficiaryListView(
			List<IContractLifePeopleVOModel> beneficiaryListView) ;

	public abstract List<IContractLifeProductVOModel> getProductListView();

	public abstract void setProductListView(
			List<IContractLifeProductVOModel> productList);

	public abstract String[] getPolicyPeople_customer_id();

	public abstract void setPolicyPeople_customer_id(
			String[] policyPeople_customer_id);

	public abstract String[] getPolicyLifeInsured_customer_id();

	public abstract void setPolicyLifeInsured_customer_id(
			String[] policyLifeInsured_customer_id);

	public abstract String[] getPolicyLifeInsured_relation1();

	public abstract void setPolicyLifeInsured_relation1(
			String[] policyLifeInsured_relation1);

	public abstract String[] getPolicyLifeInsured_relation_code();

	public abstract void setPolicyLifeInsured_relation_code(
			String[] policyLifeInsured_relation_code);

	public abstract String[] getPolicyLifeBeneficiary_customer_id();

	public abstract void setPolicyLifeBeneficiary_customer_id(
			String[] policyLifeBeneficiary_customer_id);

	public abstract String[] getPolicyLifeBeneficiary_insurant_name_value();

	public abstract void setPolicyLifeBeneficiary_insurant_name_value(
			String[] policyLifeBeneficiary_insurant_name_value);

	public abstract String[] getPolicyLifeBeneficiary_relation1();

	public abstract void setPolicyLifeBeneficiary_relation1(
			String[] policyLifeBeneficiary_relation1);

	public abstract String[] getPolicyLifeBeneficiary_relation_code();

	public abstract void setPolicyLifeBeneficiary_relation_code(
			String[] policyLifeBeneficiary_relation_code);

	public abstract String[] getPolicyLifeBeneficiary_bene_type_code();

	public abstract void setPolicyLifeBeneficiary_bene_type_code(
			String[] policyLifeBeneficiary_bene_type_code);

	public abstract String[] getPolicyLifeBeneficiay_bene_order();

	public abstract void setPolicyLifeBeneficiay_bene_order(
			String[] policyLifeBeneficiay_bene_order);

	public abstract String[] getPolicyLifeBeneficiay_bene_rate();

	public abstract void setPolicyLifeBeneficiay_bene_rate(
			String[] policyLifeBeneficiay_bene_rate);

	public abstract String[] getProduct_id_arr();

	public abstract void setProduct_id_arr(String[] product_id_arr);

	public abstract String[] getCoverage_period_arr();

	public abstract void setCoverage_period_arr(String[] coverage_period_arr);

	public abstract String[] getCoverage_year_arr();

	public abstract void setCoverage_year_arr(String[] coverage_year_arr);

	public abstract String[] getCharge_period_arr();

	public abstract void setCharge_period_arr(String[] charge_period_arr);

	public abstract String[] getCharge_year_arr();

	public abstract void setCharge_year_arr(String[] charge_year_arr);

	public abstract String[] getCharge_type_arr();

	public abstract void setCharge_type_arr(String[] charge_type_arr);

	public abstract String[] getAmount_arr();

	public abstract void setAmount_arr(String[] amount_arr);

	public abstract String[] getPeriod_prem_arr();

	public abstract void setPeriod_prem_arr(String[] period_prem_arr);

	public abstract PageCount getPageCount();

	public abstract void setPageCount(PageCount pageCount);

	public abstract String getFee_type();

	public abstract void setFee_type(String fee_type);

	public abstract Integer getActual_prem();

	public abstract void setActual_prem(Integer actual_prem);

	public abstract Integer getFee_id();

	public abstract void setFee_id(Integer fee_id);

	public abstract String getIns_type();

	public abstract void setIns_type(String ins_type);

	public abstract String getProduct_type();

	public abstract void setProduct_type(String product_type);

	public abstract String getProduct_type2();

	public abstract void setProduct_type2(String product_type2);

	public abstract String getProduct_type3();

	public abstract void setProduct_type3(String product_type3);

	public abstract String getPeriod_type();

	public abstract void setPeriod_type(String period_type);

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

	public abstract Integer getPrem_id();

	public abstract void setPrem_id(Integer prem_id);

	public abstract String getCustomer_id();

	public abstract void setCustomer_id(String customer_id);

	public abstract Integer getItem_id();

	public abstract void setItem_id(Integer item_id);

	public abstract String getApp_id();

	public abstract void setApp_id(String app_id);

	public abstract Integer getUnit();

	public abstract void setUnit(Integer unit);

	public abstract Double getAmount();

	public abstract void setAmount(Double amount);

	public abstract Date getNext_due_time();

	public abstract void setNext_due_time(Date next_due_time);

	public abstract String getInsure_stop();

	public abstract void setInsure_stop(String insure_stop);

	public abstract Integer getAuth_payAge();

	public abstract void setAuth_payAge(Integer auth_payAge);

	public abstract String getAuth_draw();

	public abstract void setAuth_draw(String auth_draw);

	public abstract Double getAuth_firstPay();

	public abstract void setAuth_firstPay(Double auth_firstPay);

	public abstract String getDividend_choice();

	public abstract void setDividend_choice(String dividend_choice);

	public abstract String getIs_autoRen();

	public abstract void setIs_autoRen(String is_autoRen);

	public String getResult_flag();

	public void setResult_flag(String result_flag);

	public abstract String[] getProduct_insurant_id();

	public abstract void setProduct_insurant_id(String[] product_insurant_id);

	public abstract String[] getAuth_payAge_arr();

	public abstract void setAuth_payAge_arr(String[] auth_payAge_arr);

	public abstract String[] getAuth_draw_arr();

	public abstract void setAuth_draw_arr(String[] auth_draw_arr);

	public abstract String[] getAuth_firstPay_arr();

	public abstract void setAuth_firstPay_arr(String[] auth_firstPay_arr);

	public abstract String[] getDividend_choice_arr();

	public abstract void setDividend_choice_arr(String[] dividend_choice_arr);

	public abstract String[] getIs_autoRen_arr();

	public abstract void setIs_autoRen_arr(String[] is_autoRen_arr);

}