package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

public interface IContractLifeHolderModel {

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getInsBranch_id();

	public abstract void setInsBranch_id(String insBranch_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);

	public abstract String getCustomer_id();

	public abstract void setCustomer_id(String customer_id);

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

	public abstract Integer getApp_age();

	public abstract void setApp_age(Integer app_age);

}