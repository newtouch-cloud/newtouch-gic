package com.newtouch.peoplemanage.model.vo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

/**
 * 人员管管理实体类接口
 * 
 * @author Ming Ying
 * @time 2017-11-30
 */
public interface IPersonManageVOModel extends IPageCount{

	public String getSchool();

	public void setSchool(String school);
	
	public String getContractno();

	public void setContractno(String contractno);
	
	public String getSeq_id();

	public void setSeq_id(String seq_id);

	public String getPerson_no();

	public void setPerson_no(String person_no);

	public String getPerson_name();

	public void setPerson_name(String person_name);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getTeam_id();

	public void setTeam_id(String team_id);

	public String getBranch_name();

	public void setBranch_name(String branch_name);

	public String getTeam_name();

	public void setTeam_name(String team_name);

	public Date getEntry_date();

	public void setEntry_date(Date entry_date);

	public Date getEnd_date();

	public void setEnd_date(Date end_date);

	public String getAudit_status();

	public void setAudit_status(String audit_status);

	public String getIs_party_org();

	public void setIs_party_org(String is_party_org);

	public String getPerson_type();

	public void setPerson_type(String person_type);

	public String getWork_nature();

	public void setWork_nature(String work_nature);

	public void setWork_nature_name(String work_nature_name);
	public String getWork_nature_name();

	public String getPosition();

	public void setPosition(String position);

	public String getPosition_id();

	public void setPosition_id(String position_id);

	public String getEntry_source();

	public void setEntry_source(String entry_source);

	public String getIscharge();

	public void setIscharge(String ischarge);

	public String getSuperior_position_id();

	public void setSuperior_position_id(String superior_position_id);

	public String getOrg_id();

	public void setOrg_id(String org_id);

	public String getBelong_opt_no();

	public void setBelong_opt_no(String belong_opt_no);

	public String getBelong_opt_name();

	public void setBelong_opt_name(String belong_opt_name);

	public String getBelong_idcard();

	public void setBelong_idcard(String belong_idcard);

	public String getSex();

	public void setSex(String sex);

	public Date getBirthday();

	public void setBirthday(Date birthday);

	public String getNational();

	public void setNational(String national);

	public String getPolitical();

	public void setPolitical(String political);

	public String getEducation();

	public void setEducation(String education);

	public String getIdcard_adress();

	public void setIdcard_adress(String idcard_adress);

	public String getBirth_address();

	public void setBirth_address(String birth_address);

	public String getCard_type();

	public void setCard_type(String card_type);

	public String getIdcard();

	public void setIdcard(String idcard);

	public String getPhone();

	public void setPhone(String phone);

	public String getTel();

	public void setTel(String tel);

	public String getEmail();

	public void setEmail(String email);

	public String getTechnology_no();

	public void setTechnology_no(String technology_no);

	public String getIsmarrid();

	public void setIsmarrid(String ismarrid);

	public String getHome_address();

	public void setHome_address(String home_address);

	public Date getPolitical_startdate();

	public void setPolitical_startdate(Date political_startdate);

	public Date getPolitical_joindate();

	public void setPolitical_joindate(Date political_joindate);

	public String getPolitical_company();

	public void setPolitical_company(String political_company);

	public String getParty_org();

	public void setParty_org(String party_org);

	public String getPolitical_fee();

	public void setPolitical_fee(String political_fee);

	public String getHealth();

	public void setHealth(String health);

	public String getSocial_security_no();

	public void setSocial_security_no(String social_security_no);

	public Date getWork_date();

	public void setWork_date(Date work_date);

	public String getPerson_status();

	public void setPerson_status(String person_status);

	public String getQualification_no();

	public void setQualification_no(String qualification_no);

	public String getPractice_type();

	public void setPractice_type(String practice_type);

	public String getPractice_status();

	public void setPractice_status(String practice_status);

	public String getPractice_no();

	public void setPractice_no(String practice_no);

	public Date getPractice_startdate();

	public void setPractice_startdate(Date practice_startdate);

	public Date getPractice_enddate();

	public void setPractice_enddate(Date practice_enddate);

	public String getPractice_area();

	public void setPractice_area(String practice_area);

	public String getChannel_type();

	public void setChannel_type(String channel_type);

	public String getIns_sales_no();

	public void setIns_sales_no(String ins_sales_no);

	public String getBusiness_scope();

	public void setBusiness_scope(String business_scope);

	public String getContract_type();

	public void setContract_type(String contract_type);

	public Date getStart_date();

	public void setStart_date(Date start_date);

	public Date getGraduation_date();

	public void setGraduation_date(Date graduation_date);

	public String getAddress();

	public void setAddress(String address);

	public String getMajor();

	public void setMajor(String major);

	public String getYear();

	public void setYear(String year);

	public String getDegree();

	public void setDegree(String degree);

	public String getApprove_person();

	public void setApprove_person(String approve_person);

	public String getWork_position();

	public void setWork_position(String work_position);

	public String getIshigh_degree();

	public void setIshigh_degree(String ishigh_degree);

	public String getMajor_type();

	public void setMajor_type(String major_type);

	public String getEducation_type();

	public void setEducation_type(String education_type);

	public String getDegree_type();

	public void setDegree_type(String degree_type);

	public String getType();

	public void setType(String type);

	public String getFamily_name();

	public void setFamily_name(String family_name);

	public String getFamily_sex();

	public void setFamily_sex(String family_sex);

	public Date getFamily_birthday();

	public void setFamily_birthday(Date family_birthday);

	public String getFamily_relation();

	public void setFamily_relation(String family_relation);

	public String getFamily_position();

	public void setFamily_position(String family_position);

	// public Date getEndtime() ;
	// public void setEndtime(Date endtime) ;
	public String getReason();

	public void setReason(String reason);

	public Date getApply_date();

	public void setApply_date(Date apply_date);

	public Date getCheck_date();

	public void setCheck_date(Date check_date);

	public String getStatus();

	public void setStatus(String status);

	public String getLicense_name();

	public void setLicense_name(String license_name);

	public String getLicense_channel();

	public void setLicense_channel(String license_channel);

	public Date getLicense_startdate();

	public void setLicense_startdate(Date license_startdate);

	public String getMicroshop_id();

	public void setMicroshop_id(String microshop_id);

	public String getShopkeeper_name();

	public void setShopkeeper_name(String shopkeeper_name);

	public String getIncrease_type();

	public void setIncrease_type(String increase_type);

	public String getChannel_code();

	public void setChannel_code(String channel_code);

	public String getContent_code();

	public void setContent_code(String content_code);

	public String getFlag();

	public void setFlag(String flag);

	public Date getCreate_date();

	public void setCreate_date(Date create_date);

	public String getCreat_user();

	public void setCreat_user(String creat_user);

	public String getMay_date();

	public void setMay_date(String may_date);

	public Date getMay_user();

	public void setMay_user(Date may_user);

	public String getEnum_name();

	public void setEnum_name(String enum_name);

	public String getIslogout();

	public void setIslogout(String islogout);

	public String getOrg_name();

	public void setOrg_name(String org_name);

	public String getFamily_political();

	public void setFamily_political(String family_political);

	public String getDept_list();

	public void setDept_list(String dept_list);

	// public String getPractice_no();
	// public void setPractice_no(String practice_no) ;
	public Date getFormal_date();

	public void setFormal_date(Date formal_date);

	public String getSuperior_position_name();

	public void setSuperior_position_name(String superior_position_name);

	public String getProfession_type();

	public void setProfession_type(String profession_type);

	public String getWork_relation();

	public void setWork_relation(String work_relation);
	
	// a by liu_yn start
	public String getBranch_level();
	
	public void setBranch_level(String branch_level);
	
	public String getBranch_level_code();
	
	public void setBranch_level_code(String branch_level_code);
	
	public String getGleader();
	
	public void setGleader(String gleader);
	
	public String getApproval_time();
	
	public void setApproval_time(String approval_time);
	
	public String getApproval_file();
	
	public void setApproval_file(String approval_file);
	
	public String getEmployment_term();
	
	public void setEmployment_term(String employment_term);
	
	public String getUpload_approval_file();
	
	public void setUpload_approval_file(String upload_approval_file);
	
	public Date getRetire_time();

	public void setRetire_time(Date retire_time); 
	
	public String getPerson_class();

	public void setPerson_class(String person_class);
	
	public String getBankcard_number(); 

	public void setBankcard_number(String bankcard_number);

	public String getBank_name();

	public void setBank_name(String bank_name);
	
	public String getGvalid_time();

	public void setGvalid_time(String gvalid_time);
	
	public String getIs_practice();

	public void setIs_practice(String is_practice);
	
	public String getAge();
	
	public void setAge(String age);
	
	public String getBranch_level_name();
	
	public void setBranch_level_name(String branch_level_name);
	
	public String getMark();
	
	public void setMark(String mark);
	
	public Date getEntry_date1();
	
	public void setEntry_date1(Date entry_date1);
	
	public Date getEntry_date2();
	
	public void setEntry_date2(Date entry_date2);
	
	public Date getEnd_date1();
	
	public void setEnd_date1(Date end_date1);
	
	public Date getEnd_date2();
	
	public void setEnd_date2(Date end_date2);
	//a by liu_yn end 
	

}
