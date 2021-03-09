package com.newtouch.organization.model.vo;

import java.sql.Date;
import java.util.List;

import com.newtouch.core.model.IPageCount;
import com.newtouch.core.quanxianguanli.pojo.PageCount;

public interface IAgencyModel extends IPageCount{
	
	public PageCount getPageCount();
	public void setPageCount(PageCount pageCount);
	
	public int getStart();
	public int getLimit();
	
	public Integer getSeq_id();
	public void setSeq_id(Integer seq_id);
	
	public String getAgency_no();
	public void setAgency_no(String agency_no);
	
	public String getAgency_name();
	public void setAgency_name(String agency_name);
	
	public Date getCreate_date();
	public void setCreate_date(Date create_date);
	
	public String getCreate_user();
	public void setCreate_user(String create_user);
	
	public String getData_flag();
	public void setData_flag(String data_flag);
	
	public String getBusiness_license();
	public void setBusiness_license(String business_license);
	
	public String getPermission_license();
	public void setPermission_license(String permission_license);
	
	public Date getFound_date();
	public void setFound_date(Date found_date);
	
	public String getAgency_address();
	public void setAgency_address(String agency_address);
	
	public String getAgency_phone();
	public void setAgency_phone(String agency_phone);
	
	public String getAgency_type();
	public void setAgency_type(String agency_type);
	
	public String getBranch_id();
	public void setBranch_id(String branch_id);
	
	public String getOrg_id();
	public void setOrg_id(String org_id);
	
	public Date getMdf_date();
	public void setMdf_date(Date mdf_date);
	
	public String getMdf_user();
	public void setMdf_user(String mdf_user);
	
	public String getAgreement_no();
	public void setAgreement_no(String agreement_no);
	
	public Date getStartdate();
	public void setStartdate(Date startdate);
	
	public Date getEnddate();
	public void setEnddate(Date enddate);
	
	public String getBranch_name();
	public void setBranch_name(String branch_name);
	
	public String getOrg_name();
	public void setOrg_name(String org_name);
	
	public Date getStartdate1();
	public void setStartdate1(Date startdate1);
	
	public Date getEnddate1();
	public void setEnddate1(Date enddate1);
	
	public String getDisplayLable();
	public void setDisplayLable(String displayLable);
	
	public String getIsSelected();
	public void setIsSelected(String isSelected);
	
	public List<String> getList();
	public void setList(List<String> list);
	
	public String getSetUploadFilename();
	public void setSetUploadFilename(String setUploadFilename);
	
	public String getGetUploadFilename();
	public void setGetUploadFilename(String getUploadFilename);
	
	public String getSocial_code();
	public void setSocial_code(String social_code);
	
	public String getChannelcode();
	public void setChannelcode(String channelcode);
	
	public String getRepair_coding();
	public void setRepair_coding(String repair_coding);
	
	
	public List<Object> getOlist();
	public void setOlist(List<Object> olist);
}
