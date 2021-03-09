package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

import com.newtouch.core.model.IPageCount;

public interface IPolicyLifeBeneficiaryModel extends IPageCount{

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);

	public abstract String getCustomer_id();

	public abstract void setCustomer_id(String customer_id);

	public abstract String getBene_type();

	public abstract void setBene_type(String bene_type);

	public abstract String getRelation1();

	public abstract void setRelation1(String relation1);

	public abstract String getRelation2();

	public abstract void setRelation2(String relation2);

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
	
	public String getInsBranch_id();

	public void setInsBranch_id(String insbranch_id);
	
	public String getInsurant_id();

	public void setInsurant_id(String insurant_id);
	
	public Integer getBene_age() ;

	public void setBene_age(Integer bene_age) ;

	public String getBene_certi_code();

	public void setBene_certi_code(String bene_certi_code) ;

	public String getBene_certi_no() ;
	public void setBene_certi_no(String bene_certi_no) ;

	public String getBene_tel();

	public void setBene_tel(String bene_tel) ;

	public Integer getBene_order() ;

	public void setBene_order(Integer bene_order) ;

	public Double getBene_rate() ;

	public void setBene_rate(Double bene_rate);


}