package com.ca.cacore.ibs.model.bo;

import java.sql.Date;

public interface IContractLifeBeneficiaryModel {

	public abstract Integer getSeq_id();

	public abstract void setSeq_id(Integer seq_id);

	public abstract String getBranch_id();

	public abstract void setBranch_id(String branch_id);

	public abstract String getInsBranch_id();

	public abstract void setInsBranch_id(String insBranch_id);

	public abstract Long getPolicy_id();

	public abstract void setPolicy_id(Long policy_id);

	public abstract String getCustomer_id();

	public abstract void setCustomer_id(String customer_id);

	public abstract String getInsurant_id();

	public abstract void setInsurant_id(String insurant_id);

	public abstract Integer getBene_age();

	public abstract void setBene_age(Integer bene_age);

	public abstract String getBene_certi_code();

	public abstract void setBene_certi_code(String bene_certi_code);

	public abstract String getBene_certi_no();

	public abstract void setBene_certi_no(String bene_certi_no);

	public abstract String getBene_tel();

	public abstract void setBene_tel(String bene_tel);

	public abstract Integer getBene_order();

	public abstract void setBene_order(Integer bene_order);

	public abstract Double getBene_rate();

	public abstract void setBene_rate(Double bene_rate);

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

}