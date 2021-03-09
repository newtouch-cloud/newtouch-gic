package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface IPolicyLifeInsuredModel extends IPageCount{

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);

	public abstract String getCustomer_id();

	public abstract void setCustomer_id(String customer_id);

	public abstract String getRelation1();

	public abstract void setRelation1(String relation1);

	public abstract String getRelation2();

	public abstract void setRelation2(String relation2);

	public abstract String getInsurant_name();

	public abstract void setInsurant_name(String insurant_name);

	public abstract String getInsurant_certi_code();

	public abstract void setInsurant_certi_code(String insurant_certi_code);

	public abstract String getInsurant_certi_no();

	public abstract void setInsurant_certi_no(String insurant_certi_no);

	public abstract String getInsurant_tel();

	public abstract void setInsurant_tel(String insurant_tel);

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
	
	public abstract String getInsbranch_id();

	public abstract void setInsbranch_id(String insbranch_id);
	
	public Integer getInsurant_age() ;

	public void setInsurant_age(Integer insurant_age);

}