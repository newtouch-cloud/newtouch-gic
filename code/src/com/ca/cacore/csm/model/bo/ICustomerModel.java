package com.ca.cacore.csm.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface ICustomerModel extends IPageCount{
	public Date getUpload_time();
	
	public void setUpload_time(Date upload_time);

	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getCustomer_id();

	public void setCustomer_id(String customer_id);

	public String getName();

	public void setName(String name);

	public String getTitle();

	public void setTitle(String title);

	public String getGender();

	public void setGender(String gender);

	public Date getBirthday();

	public void setBirthday(Date birthday);
	
	public String getCustomer_type();

	public void setCustomer_type(String customer_type);

	public String getCerti_type();

	public void setCerti_type(String certi_type);

	public String getCerti_no();

	public void setCerti_no(String certi_no);

	public String getEducation();

	public void setEducation(String education);

	public String getNationality();

	public void setNationality(String nationality);

	public String getNation();

	public void setNation(String nation);

	public String getHomeplace();

	public void setHomeplace(String homeplace);

	public String getSeat_address();

	public void setSeat_address(String seat_address);

	public String getHeight();

	public void setHeight(String height);

	public String getWeight();

	public void setWeight(String weight);

	public String getPolitical();

	public void setPolitical(String political);

	public String getEducation2();

	public void setEducation2(String education2);

	public String getMarital_stat();

	public void setMarital_stat(String marital_stat);

	public String getHealth();

	public void setHealth(String health);

	public String getJob_type();

	public void setJob_type(String job_type);

	public String getJob_code();

	public void setJob_code(String job_code);

	public String getIncome_type();

	public void setIncome_type(String income_type);

	public String getIncome();

	public void setIncome(String income);

	public String getBank_code();

	public void setBank_code(String bank_code);

	public String getBank_account_no();

	public void setBank_account_no(String bank_account_no);

	public String getBank_account_name();

	public void setBank_account_name(String bank_account_name);

	public String getStatus();

	public void setStatus(String status);

	public String getRemark();

	public void setRemark(String remark);

	public String getCreateuser();

	public void setCreateuser(String createuser);

	public Date getCreatedate();

	public void setCreatedate(Date createdate);

	public String getModifyuser();

	public void setModifyuser(String modifyuser);

	public Date getModifydate();

	public void setModifydate(Date modifydate);
	
	public Date getCerti_validdate();

	public void setCerti_validdate(Date certi_validdate);
	
	public String getIs_telmsgservice();

	public void setIs_telmsgservice(String is_telmsgservice);

	public PageCount getPageCount();

	public void setPageCount(PageCount pageCount);
	
	public String getMember_id();

	public void setMember_id(String member_id);
	
	public String getFile_id();//附件id
	
	public void setFile_id(String file_id);
	
	public String getFile_name();//附件名称
	
	public void setFile_name(String file_name);
	
	public String getFile_flag();  //附件标示
	
	public void setFile_flag(String file_flag);
	
	public String getAttachment_type();
	
	public void setAttachment_type(String attachment_type);

	public String getPolicyno();
	
	public void setPolicyno(String policyno);
	
    public String getClasscode();
	
	public void setClasscode(String classcode);
	
	public String getAa();
	public void setAa(String aa);
	public String getBb();
	public void setBb(String bb);
	public String getBusinesstype();
	public void setBusinesstype(String businesstype);
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
	public void setCorporation_represen_wechat(String corporation_represen_wechat);
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
	public String getBranch_id();
	public void setBranch_id(String branch_id);
	public String getBranch_name();
	public void setBranch_name(String branch_name);
}