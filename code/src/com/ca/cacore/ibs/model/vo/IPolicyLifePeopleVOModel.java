package com.ca.cacore.ibs.model.vo;

import java.sql.Date;

public interface IPolicyLifePeopleVOModel {
	public String getNation_name() ;
	public void setNation_name(String nation_name) ;
	public String getNationality_name();
	public void setNationality_name(String nationality_name) ;
	public String getIncomtype_name();
	public void setIncomtype_name(String incomtype_name);
	public String getMarital_stat_name();
	public void setMarital_stat_name(String marital_stat_name);
	public String getPolitical_name() ;
	public void setPolitical_name(String political_name);
	public String getEducation_name() ;
	public void setEducation_name(String education_name) ;
	public String getHealth_name();
	public void setHealth_name(String health_name);
	public String getIs_telmsgserviceName() ;
	public void setIs_telmsgserviceName(String is_telmsgserviceName);

 	public String getSales_status();
 	
	public void setSales_status(String sales_status) ;
	
	public Integer getAge();
	
	public void setAge(Integer age) ;
	
	public String getGender_name();

	public void setGender_name(String gender_name);

	public String getCerti_type_name();

	public void setCerti_type_name(String certi_type_name);

	public String getCustomer_type();

	public void setCustomer_type(String customer_type);

	public Date getCerti_validDate();

	public void setCerti_validDate(Date certi_validDate) ;

	public String getIs_telMsgService() ;

	public void setIs_telMsgService(String is_telMsgService);
	
	public String[] getPolicyLifeBeneficiay_bene_order() ;
	
	public void setPolicyLifeBeneficiay_bene_order(
			String[] policyLifeBeneficiay_bene_order) ;
	
	public String[] getPolicyLifeBeneficiay_bene_rate() ;
	
	public void setPolicyLifeBeneficiay_bene_rate(
			String[] policyLifeBeneficiay_bene_rate);

	public String getInsurant_name_arr();

	public void setInsurant_name_arr(String insurant_name_arr);

	public String getName_tow();

	public void setName_tow(String name_tow);

	public String getType();

	public void setType(String type);

	public String getProduct_id();

	public void setProduct_id(String product_id);

	public String getProduct_name();

	public void setProduct_name(String product_name);

	public String getCoverage_period();

	public void setCoverage_period(String coverage_period);

	public String getCoverage_year();

	public void setCoverage_year(String coverage_year);

	public String getCharge_period();

	public void setCharge_period(String charge_period);

	public String getCharge_year();

	public void setCharge_year(String charge_year);

	public String getCharge_type_code();

	public void setCharge_type_code(String charge_type_code);

	public String getCoverage_time();

	public void setCoverage_time(String coverage_time);

	public String getJsp_url();

	public void setJsp_url(String jsp_url);

	public String getInsBranch_id();

	public void setInsBranch_id(String insBranch_id);

	public String getInsBranch_name();

	public void setInsBranch_name(String insBranch_name);

	public String getAgent_id();

	public void setAgent_id(String agent_id);

	public String getAgent_name();

	public void setAgent_name(String agent_name);

	public String getPolicy_code();

	public void setPolicy_code(String policy_code);

	public String getService_id();

	public void setService_id(String service_id);

	public String getService_name();

	public void setService_name(String service_name);

	public String getHolder_id();

	public void setHolder_id(String holder_id);

	public String getInsurant_id();

	public void setInsurant_id(String insurant_id);

	public String getRelation_id();

	public void setRelation_id(String relation_id);

	public String getRelation_name();

	public void setRelation_name(String relation_name);

	public String getSell_way();

	public void setSell_way(String sell_way);

	public String getCharge_type();

	public void setCharge_type(String charge_type);

	public String getCharge_next();

	public void setCharge_next(String charge_next);

	public String getPay_mode();

	public void setPay_mode(String pay_mode);

	public String getPay_mode_next();

	public void setPay_mode_next(String pay_mode_next);

	public String getPolicy_year();

	public void setPolicy_year(String policy_year);

	public String getPolicy_period();

	public void setPolicy_period(String policy_period);

	public String getPeriod_prem();

	public void setPeriod_prem(String period_prem);

	public String getMoney_id();

	public void setMoney_id(String money_id);

	public String getAccount_type();

	public void setAccount_type(String account_type);

	public String getBank_accName();

	public void setBank_accName(String bank_accName);

	public String getBank_account();

	public void setBank_account(String bank_account);

	public String getOverdue_manage();

	public void setOverdue_manage(String overdue_manage);

	public Date getHold_date();

	public void setHold_date(Date hold_date);

	public Date getScan_time();

	public void setScan_time(Date scan_time);

	public Date getValidate_date();

	public void setValidate_date(Date validate_date);

	public Date getSign_date();

	public void setSign_date(Date sign_date);

	public Date getEnd_date();

	public void setEnd_date(Date end_date);

	public Date getPause_time();

	public void setPause_time(Date pause_time);

	public String getIs_answered();

	public void setIs_answered(String is_answered);

	public String getHigh_policy();

	public void setHigh_policy(String high_policy);

	public String getStatus_name();

	public void setStatus_name(String status_name);

	public Date getRtn_date();

	public void setRtn_date(Date rtn_date);

	public String[] getPolicyPeople_customer_id();

	public void setPolicyPeople_customer_id(String[] policyPeople_customer_id);

	public String[] getPolicyLifeInsured_customer_id();

	public void setPolicyLifeInsured_customer_id(
			String[] policyLifeInsured_customer_id);

	public String[] getPolicyLifeInsured_relation1();

	public void setPolicyLifeInsured_relation1(
			String[] policyLifeInsured_relation1);

	public String[] getPolicyLifeInsured_relation_code();

	public void setPolicyLifeInsured_relation_code(
			String[] policyLifeInsured_relation_code);

	public String[] getPolicyLifeBeneficiary_customer_id();

	public void setPolicyLifeBeneficiary_customer_id(
			String[] policyLifeBeneficiary_customer_id);

	public String[] getPolicyLifeBeneficiary_insurant_name_value();

	public void setPolicyLifeBeneficiary_insurant_name_value(
			String[] policyLifeBeneficiary_insurant_name_value);

	public String[] getPolicyLifeBeneficiary_relation1();

	public void setPolicyLifeBeneficiary_relation1(
			String[] policyLifeBeneficiary_relation1);

	public String[] getPolicyLifeBeneficiary_relation_code();

	public void setPolicyLifeBeneficiary_relation_code(
			String[] policyLifeBeneficiary_relation_code);

	public String[] getPolicyLifeBeneficiary_bene_type_code();

	public void setPolicyLifeBeneficiary_bene_type_code(
			String[] policyLifeBeneficiary_bene_type_code);

	public String getPolicy_tr_id();

	public void setPolicy_tr_id(String policy_tr_id);

	public String getFlag();

	public void setFlag(String flag);

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getCustomer_id();

	public abstract void setCustomer_id(String customer_id);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getTitle();

	public abstract void setTitle(String title);

	public abstract String getGender();

	public abstract void setGender(String gender);

	public abstract Date getBirthday();

	public abstract void setBirthday(Date birthday);

	public abstract String getCerti_type();

	public abstract void setCerti_type(String certi_type);

	public abstract String getCerti_no();

	public abstract void setCerti_no(String certi_no);

	public abstract String getEducation();

	public abstract void setEducation(String education);

	public abstract String getNationality();

	public abstract void setNationality(String nationality);

	public abstract String getNation();

	public abstract void setNation(String nation);

	public abstract String getHomeplace();

	public abstract void setHomeplace(String homeplace);

	public abstract String getSeat_address();

	public abstract void setSeat_address(String seat_address);

	public abstract Integer getHeight();

	public abstract void setHeight(Integer height);

	public abstract Integer getWeight();

	public abstract void setWeight(Integer weight);

	public abstract String getPolitical();

	public abstract void setPolitical(String political);

	public abstract String getEducation2();

	public abstract void setEducation2(String education2);

	public abstract String getMarital_stat();

	public abstract void setMarital_stat(String marital_stat);

	public abstract String getHealth();

	public abstract void setHealth(String health);

	public abstract String getJob_type();

	public abstract void setJob_type(String job_type);

	public abstract String getJob_code();

	public abstract void setJob_code(String job_code);

	public abstract String getIncome_type();

	public abstract void setIncome_type(String income_type);

	public abstract String getBank_code();

	public abstract void setBank_code(String bank_code);

	public abstract String getBank_account_no();

	public abstract void setBank_account_no(String bank_account_no);

	public abstract String getBank_account_name();

	public abstract void setBank_account_name(String bank_account_name);

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

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getSend_code();

	public abstract void setSend_code(String send_code);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);

	public abstract String getApp_name();

	public abstract void setApp_name(String app_name);

	public abstract String getApp_certi_code();

	public abstract void setApp_certi_code(String app_certi_code);

	public abstract String getApp_certi_no();

	public abstract void setApp_certi_no(String app_certi_no);

	public abstract String getApp_address();

	public abstract void setApp_address(String app_address);

	public abstract String getApp_tel();

	public abstract void setApp_tel(String app_tel);

	public abstract String getApp_zip();

	public abstract void setApp_zip(String app_zip);

	public abstract String getSex();

	public abstract void setSex(String sex);

	public abstract String getApp_email();

	public abstract void setApp_email(String app_email);

	public abstract String getRelation1();

	public abstract void setRelation1(String relation1);

	public abstract String getRelation2();

	public abstract void setRelation2(String relation2);

	public abstract String getRelation();

	public abstract void setRelation(String relation);

	public abstract String getRelation1_name();

	public abstract void setRelation1_name(String relation1_name);

	public abstract String getRelation2_name();

	public abstract void setRelation2_name(String relation2_name);

	public abstract String getInsurant_name();

	public abstract void setInsurant_name(String insurant_name);

	public abstract String getInsurant_certi_code();

	public abstract void setInsurant_certi_code(String insurant_certi_code);

	public abstract String getInsurant_certi_no();

	public abstract void setInsurant_certi_no(String insurant_certi_no);

	public abstract String getInsurant_tel();

	public abstract void setInsurant_tel(String insurant_tel);

	public abstract String getInsurant_email();

	public abstract void setInsurant_email(String insurant_email);

	public abstract String getInsurant_address();

	public abstract void setInsurant_address(String insurant_address);

	public abstract String getBene_type();

	public abstract void setBene_type(String bene_type);

	public abstract String getBen_email();

	public abstract void setBen_email(String ben_email);

	public abstract String getBene_address();

	public abstract void setBene_address(String bene_address);

	public abstract String getBene_certi_code();

	public abstract void setBene_certi_code(String bene_certi_code);

	public abstract String getBene_certi_no();

	public abstract void setBene_certi_no(String bene_certi_no);

	public abstract String getBene_tel();

	public abstract void setBene_tel(String bene_tel);

	public abstract String getBene_type_name();

	public abstract void setBene_type_name(String bene_type_name);

	public abstract String getBene_name();

	public abstract void setBene_name(String bene_name);

	public String getAddress();

	public void setAddress(String address);

	public String getZip();

	public void setZip(String zip);

	public String getMobile();

	public void setMobile(String mobile);

	public String getFax();

	public void setFax(String fax);

	public String getTelphone();

	public void setTelphone(String telphone);

	public String getEmail();

	public void setEmail(String email);

	public String getJob_com();

	public void setJob_com(String job_com);

	public String getJob_tel();

	public void setJob_tel(String job_tel);

	public Integer getLog_seq_id();

	public void setLog_seq_id(Integer log_seq_id);

	public String getLog_bustype();

	public void setLog_bustype(String log_bustype);

	public Date getLog_busdate();

	public void setLog_busdate(Date log_busdate);

	public Date getLog_date();

	public void setLog_date(Date log_date);

	public String getLog_remark();

	public void setLog_remark(String log_remark);

	public String getSales_name();

	public void setSales_name(String sales_name);

	public String getBranch_name();

	public void setBranch_name(String branch_name);
	
	//收益人更改
	public Integer getBene_age() ;

	public void setBene_age(Integer bene_age) ;

	public Integer getBene_order() ;

	public void setBene_order(Integer bene_order) ;

	public Double getBene_rate() ;
	
	public void setBene_rate(Double bene_rate) ;
	
	public String getInsurant_name_value();
	
	public void setInsurant_name_value(String insurant_name_value);

}