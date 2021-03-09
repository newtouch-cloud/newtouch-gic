package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface IPolicyLifeHolderModel extends IPageCount{
	
	public Integer getSeq_id();

	public void setSeq_id(Integer seq_id);

	public String getBranch_id();

	public void setBranch_id(String branch_id);

	public String getSend_code();

	public void setSend_code(String send_code);

	public Long getPolicy_id();

	public void setPolicy_id(Long policy_id);

	public String getCustomer_id();

	public void setCustomer_id(String customer_id);

	public String getApp_name();

	public void setApp_name(String app_name);

	public String getApp_certi_code();

	public void setApp_certi_code(String app_certi_code);

	public String getApp_certi_no();

	public void setApp_certi_no(String app_certi_no);

	public String getApp_address();

	public void setApp_address(String app_address);

	public String getApp_tel();

	public void setApp_tel(String app_tel);

	public String getApp_zip();

	public void setApp_zip(String app_zip);

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
	
	public String getInsbranch_id() ;

	public void setInsbranch_id(String insbranch_id);
	
	public Integer getApp_age() ;

	public void setApp_age(Integer app_age) ;
}
