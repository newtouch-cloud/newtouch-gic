package com.ca.cacore.csm.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface ICustomerVOModel extends IPageCount {
	public String getRelation();

	public void setRelation(String relation);

	public String getInsurant_name_value();

	public void setInsurant_name_value(String insurant_name_value);

	public String getInsurant_name_arr();

	public void setInsurant_name_arr(String insurant_name_arr);

	public String getInsurant_name();

	public void setInsurant_name(String insurant_name);

	public String getFlag();

	public void setFlag(String flag);

	public String getPolicy_tr_id();

	public void setPolicy_tr_id(String policy_tr_id);

	public String getRelation1();

	public void setRelation1(String relation1);

	public String getRelation2();

	public void setRelation2(String relation2);

	public String getBene_type();

	public void setBene_type(String bene_type);

	public Integer getBene_order();

	public void setBene_order(Integer bene_order);

	public Double getBene_rate();

	public void setBene_rate(Double bene_rate);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getBranch_name();

	public void setBranch_name(String branch_name);

	public String getCustomer_type();

	public void setCustomer_type(String customer_type);

	public String getType_name();

	public void setType_name(String type_name);

	public String getCustomer_id();

	public void setCustomer_id(String customer_id);

	public String getName();

	public void setName(String name);

	public String getGender();

	public void setGender(String gender);

	public String getGender_name();

	public void setGender_name(String gender_name);

	public Date getBirthday();

	public void setBirthday(Date birthday);

	public String getCerti_type();

	public void setCerti_type(String certi_type);

	public String getCerti_type_name();

	public void setCerti_type_name(String certi_type_name);

	public String getCerti_no();

	public void setCerti_no(String certi_no);

	public Date getCerti_validdate();

	public void setCerti_validdate(Date certi_validdate);

	public String getEducation();

	public void setEducation(String education);

	public String getNationality();

	public void setNationality(String nationality);

	public String getNation();

	public void setNation(String nation);

	public String getSeat_address();

	public void setSeat_address(String seat_address);

	public Integer getHeight();

	public void setHeight(Integer height);

	public Integer getWeight();

	public void setWeight(Integer weight);

	public String getPolitical();

	public void setPolitical(String political);

	public String getHealth();

	public void setHealth(String health);

	public String getBank_code();

	public void setBank_code(String bank_code);

	public String getBank_account_no();

	public void setBank_account_no(String bank_account_no);

	public String getBank_account_name();

	public void setBank_account_name(String bank_account_name);

	public String getEducation2();

	public void setEducation2(String education2);

	public String getEducation_name();

	public void setEducation_name(String education_name);

	public String getMarital_stat();

	public void setMarital_stat(String marital_stat);

	public String getAddress();

	public void setAddress(String address);

	public String getMobile();

	public void setMobile(String mobile);

	public String getEmail();

	public void setEmail(String email);

	public String getJob_type();

	public void setJob_type(String job_type);

	public String getJobtype_name();

	public void setJobtype_name(String jobtype_name);

	public String getJob_code();

	public void setJob_code(String job_code);

	public String getIncome_type();

	public void setIncome_type(String income_type);

	public String getIncomtype_name();

	public void setIncomtype_name(String incomtype_name);

	public String getHomeplace();

	public void setHomeplace(String homeplace);

	public String getStatus();

	public void setStatus(String status);

	public String getRemark();

	public void setRemark(String remark);

	public String getCreateUser();

	public void setCreateUser(String createUser);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public String getModifyUser();

	public void setModifyUser(String modifyUser);

	public Date getModifyDate();

	public void setModifyDate(Date modifyDate);

	public String getIs_telmsgservice();

	public void setIs_telmsgservice(String is_telmsgservice);

	public String getZip();

	public void setZip(String zip);

	public String getFax();

	public void setFax(String fax);

	public String getTelphone();

	public void setTelphone(String telphone);

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

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);

	public String getMarital_stat_name();

	public void setMarital_stat_name(String marital_stat_name);

	public String getMember_id();

	public void setMember_id(String member_id);

	public int getStart();

	public int getLimit();

	public String getPolicyno();

	public void setPolicyno(String policyno);

	public String getCar_no();

	public void setCar_no(String car_no);

	public String getClaimno();

	public void setClaimno(String claimno);

	public String getInsurCount();

	public void setInsurCount(String insurCount);

	public String getEmp_id();

	public void setEmp_id(String emp_id);

	public String getCompany_name();

	public void setCompany_name(String company_name);

	public String getCompany_address();

	public void setCompany_address(String company_address);

	public String getCompany_telphone();

	public void setCompany_telphone(String company_telphone);

	public String getCompany_fax();

	public void setCompany_fax(String company_fax);

	public String getCompany_mobile();

	public void setCompany_mobile(String company_mobile);

	public String getCompany_postcode();

	public void setCompany_postcode(String company_postcode);

	public String getCorporation_represen();

	public void setCorporation_represen(String corporation_represen);

	public String getCompany_url();

	public void setCompany_url(String company_url);

	public String getCompany_mail();

	public void setCompany_mail(String company_mail);

	public String getCompany_industry();

	public void setCompany_industry(String company_industry);

	public String getCorporation_represen_qq();

	public void setCorporation_represen_qq(String corporation_represen_qq);

	public String getCorporation_represen_wechat();

	public void setCorporation_represen_wechat(
			String corporation_represen_wechat);

	public String getCompany_remark();

	public void setCompany_remark(String company_remark);

	public String getCorporation_contact_person();

	public void setCorporation_contact_person(String corporation_contact_person);

	public String getCorporation_contact_mobile();

	public void setCorporation_contact_mobile(String corporation_contact_mobile);

	public String getCorporation_contact_qq();

	public void setCorporation_contact_qq(String corporation_contact_qq);

	public String getCorporation_contact_wechat();

	public void setCorporation_contact_wechat(String corporation_contact_wechat);

	public String getDept_list();

	public void setDept_list(String dept_list);

	public String getInsured_cid();

	public void setInsured_cid(String insured_cid);

	public String getInsured_company_type();

	public void setInsured_company_type(String insured_company_type);

	public String getInsured_papertype();

	public void setInsured_papertype(String insured_papertype);

	public String getInsured_mailbox();

	public void setInsured_mailbox(String insured_mailbox);

	public String getInsured_phone();

	public void setInsured_phone(String insured_phone);

	public String getInsured_tel();

	public void setInsured_tel(String insured_tel);

	public String getInsured_name();

	public void setInsured_name(String insured_name);

	public String getHome_address();

	public void setHome_address(String home_address);

	public String getOrg_id();

	public void setOrg_id(String org_id);

	public String getSeq_id();

	public void setSeq_id(String seq_id);

	public double getSum();

	public void setSum(double getSum);
}